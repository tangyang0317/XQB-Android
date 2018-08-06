package com.zhangju.xingquban.interestclassapp.refactor.me.activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import com.fastlib.annotation.Bind;
import com.fastlib.annotation.ContentView;
import com.fastlib.annotation.LocalData;
import com.fastlib.app.EventObserver;
import com.fastlib.app.FastActivity;
import com.fastlib.app.FastDialog;
import com.fastlib.net.Request;
import com.fastlib.net.SimpleListener;
import com.fastlib.utils.N;
import com.fastlib.utils.Utils;
import com.fastlib.widget.TitleBar;
import com.zhangju.xingquban.R;
import com.zhangju.xingquban.interestclassapp.refactor.common.activity.WebViewActivity;
import com.zhangju.xingquban.interestclassapp.refactor.common.bean.CommonInterface;
import com.zhangju.xingquban.interestclassapp.refactor.common.bean.Response;
import com.zhangju.xingquban.interestclassapp.refactor.me.bean.EventWalletChanged;
import com.zhangju.xingquban.interestclassapp.refactor.me.bean.MeInterface;
import com.zhangju.xingquban.interestclassapp.refactor.me.bean.ResponseWallet;

import java.text.DecimalFormat;

/**
 * Created by sgfb on 2017/11/24.
 * 兴趣币
 */
@ContentView(R.layout.act_fun_coin)
public class ExchangeFunCoinActivity extends FastActivity{
    public static final String ARG_SER_WALLET="wallet";

    @LocalData(ARG_SER_WALLET)
    ResponseWallet mWallet;
    @Bind(R.id.titleBar)
    TitleBar mTitleBar;
    @Bind(R.id.coinRemain)
    TextView mCoinRemain;
    @Bind(R.id.exchangeFunbeanCount)
    TextView mExchangeFunbeanCount;
    @Bind(R.id.exchangeRemainCount)
    TextView mExchangeRemainCount;
    @Bind(R.id.agreement)
    TextView mAgreement;
    @Bind(R.id.exchangeCount)
    EditText mExchangeCount;
    @Bind(R.id.checkAgreement)
    CheckBox mAgreementCheck;
    @Bind(R.id.exchangeFunbeanLayout)
    View mExchangeFunbeanLayout;
    @Bind(R.id.exchangeRemainLayout)
    View mExchangeRemainLayout;
    Drawable mDefaultExchangeDrawable,mExchangeFunBeanSelectedDrawable,mExchangeRemainDrawable;
    int mSelectIndex=0;
    float mRemain=0;
    float mFunBeanRate=1,mRemainRate=1;
    String mCurrencyId,mFunbeanCurrencyId,mRemainCurrencyId,mFunbeanRateId,mRemainRateId;

    @Override
    protected void alreadyPrepared() {
        final DecimalFormat df=new DecimalFormat("##.##");

        mAgreement.setText(Utils.getTextSomeOtherColor(5,11,mAgreement.getText().toString(),getResources().getColor(R.color.EF4E4C)));
        mTitleBar.setOnLeftClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        mTitleBar.setOnRightClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(ExchangeFunCoinActivity.this,WalletTransactionHistoryActivity.class);
                intent.putExtra(WalletTransactionHistoryActivity.ARG_STR_COIN_ID,"5");
                startActivity(intent);
            }
        });
        mDefaultExchangeDrawable=getResources().getDrawable(R.mipmap.wallet_xqb_bg);
        mExchangeFunBeanSelectedDrawable= Utils.tintDrawable(getResources().getDrawable(R.mipmap.wallet_xqb_bg),Color.parseColor("#FFD672"));
        mExchangeRemainDrawable=Utils.tintDrawable(getResources().getDrawable(R.mipmap.wallet_xqb_bg),Color.parseColor("#F47876"));
        for(ResponseWallet.WalletRemain walletRemain:mWallet.currency){
            if("兴趣币".equals(walletRemain.name)){
                mCurrencyId=String.valueOf(walletRemain.id);
                mRemain=walletRemain.coinNum;
                mCoinRemain.setText(df.format(walletRemain.coinNum));
                break;
            }
        }
        for(ResponseWallet.Rate rate:mWallet.currencyRate){
            if("兴趣币".equals(rate.currency.name)){
                if("兴趣豆".equals(rate.toCurrency.name)){
                    mFunbeanRateId=rate.id;
                    mFunbeanCurrencyId=rate.toCurrency.id;
                    mFunBeanRate=rate.rate;
                }
                else if("余额".equals(rate.toCurrency.name)){
                    mRemainRateId=rate.id;
                    mRemainCurrencyId=rate.toCurrency.id;
                    mRemainRate=rate.rate;
                }
            }
        }
        mExchangeCount.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                String countStr=mExchangeCount.getText().toString();
                int count=0;
                try{
                    count=TextUtils.isEmpty(countStr)?0:Integer.parseInt(countStr);
                }catch (NumberFormatException e){
                    e.printStackTrace();
                }
                mExchangeFunbeanCount.setText(df.format(count*mFunBeanRate));
                mExchangeRemainCount.setText(df.format(count*mRemainRate));
            }
        });
        selectExchangeToFunbean();
    }

    @Bind(R.id.exchangeToFunbean)
    private void selectExchangeToFunbean(){
        mSelectIndex=0;
        mExchangeFunbeanLayout.setBackground(mExchangeFunBeanSelectedDrawable);
        mExchangeRemainLayout.setBackground(mDefaultExchangeDrawable);
    }

    @Bind(R.id.exchangeToRemain)
    private void selectExchangeToRemain(){
        mSelectIndex=1;
        mExchangeFunbeanLayout.setBackground(mDefaultExchangeDrawable);
        mExchangeRemainLayout.setBackground(mExchangeRemainDrawable);
    }

    @Bind(R.id.commit)
    private void commit(){
        if(!mAgreementCheck.isChecked()){
            N.showShort(this,"请同意用户兑换协议");
            return;
        }
        int toExchangeCount=Integer.parseInt(mExchangeCount.getText().toString());
        if(toExchangeCount>mRemain){
            N.showShort(this,"输入金额大于剩余金额");
            return;
        }
        FastDialog.showMessageDialog("确认使用"+toExchangeCount+"兴趣币兑换？",true).show(getSupportFragmentManager(), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which){
                Request request=Request.obtain(MeInterface.POST_EXCHANGE);
                request.put("currencyId",mCurrencyId);
                request.put("toCurrencyId",mSelectIndex==0?mFunbeanCurrencyId:mRemainCurrencyId);
                request.put("currencyRateId",mSelectIndex==0?mFunbeanRateId:mRemainRateId);
                request.put("fromAmount",mExchangeCount.getText().toString());
                request.setListener(new SimpleListener<Response>(){

                    @Override
                    public void onResponseListener(Request r, Response result) {
                        if(result.success){
                            N.showLong(ExchangeFunCoinActivity.this,"兑换成功");
                            EventObserver.getInstance().sendEvent(ExchangeFunCoinActivity.this,new EventWalletChanged());
                            finish();
                        }
                    }
                });
                net(request);
            }
        });
    }

    @Bind(R.id.agreement)
    private void openAgreement(){
        Intent intent=new Intent(this, WebViewActivity.class);
        intent.putExtra(WebViewActivity.ARG_URL, CommonInterface.URL_EXCHANGE_AGREEMENT);
        startActivity(intent);
    }
}
