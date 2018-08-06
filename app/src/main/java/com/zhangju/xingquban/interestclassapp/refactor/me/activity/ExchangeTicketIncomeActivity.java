package com.zhangju.xingquban.interestclassapp.refactor.me.activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.text.TextUtils;
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
 * Created by Administrator on 2017/11/25.
 * 门票收益
 */
@ContentView(R.layout.act_ticket_income)
public class ExchangeTicketIncomeActivity extends FastActivity{
    public static final String ARG_SER_WALLET="wallet";

    @LocalData(ARG_SER_WALLET)
    ResponseWallet mWallet;
    @Bind(R.id.titleBar)
    TitleBar mTitleBar;
    @Bind(R.id.incomeCount)
    TextView mIncomeCount;
    @Bind(R.id.agreement)
    TextView mAgreement;
    @Bind(R.id.exchangeCount)
    EditText mExchangeCount;
    @Bind(R.id.checkAgreement)
    CheckBox mAgreementCheckBox;
    long mCurrencyId=-1,mToCurrencyId=-1;
    String mCurrencyRateId;

    @Override
    protected void alreadyPrepared() {
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
                Intent intent=new Intent(ExchangeTicketIncomeActivity.this,WalletTransactionHistoryActivity.class);
                intent.putExtra(WalletTransactionHistoryActivity.ARG_STR_COIN_ID,"7");
                startActivity(intent);
            }
        });
        for(ResponseWallet.WalletRemain remain:mWallet.currency){
            if("门票收益".equals(remain.name)){
                DecimalFormat df=new DecimalFormat("##.##");
                mIncomeCount.setText(df.format(remain.coinNum));
                break;
            }
        }
        for(ResponseWallet.Rate rate:mWallet.currencyRate){
            if("门票收益".equals(rate.currency.name)&&"余额".equals(rate.toCurrency.name)){
                mCurrencyId=rate.currencyId;
                mToCurrencyId=rate.toCurrencyId;
                mCurrencyRateId=rate.id;
                break;
            }
        }
    }

    @Bind(R.id.commit)
    private void commit(){
        if(TextUtils.isEmpty(mExchangeCount.getText().toString())) return;
        if(!mAgreementCheckBox.isChecked()){
            N.showShort(this,"请同意用户兑换协议");
            return;
        }
        float exchangeCount=0;
        try{
            exchangeCount=Float.parseFloat(mExchangeCount.getText().toString());
            if(exchangeCount>Float.parseFloat(mIncomeCount.getText().toString())){
                N.showShort(ExchangeTicketIncomeActivity.this,"兑换的金额不能大于余额");
                return;
            }
        }catch (NumberFormatException e){
            e.printStackTrace();
        }
        FastDialog.showMessageDialog("确认兑换人民币到余额？",true).show(getSupportFragmentManager(), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                float exchangeCount=Float.parseFloat(mExchangeCount.getText().toString());
                Request request=Request.obtain(MeInterface.POST_EXCHANGE);
                request.put("fromAmount",exchangeCount);
                request.put("currencyId",mCurrencyId);
                request.put("toCurrencyId",mToCurrencyId);
                request.put("currencyRateId",mCurrencyRateId);
                request.setListener(new SimpleListener<Response>(){

                    @Override
                    public void onResponseListener(Request r, Response result){
                        if(result.success){
                            N.showShort(ExchangeTicketIncomeActivity.this,"兑换成功");
                            EventObserver.getInstance().sendEvent(ExchangeTicketIncomeActivity.this,new EventWalletChanged());
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
