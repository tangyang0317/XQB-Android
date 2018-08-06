package com.zhangju.xingquban.interestclassapp.refactor.me.activity;

import android.content.Intent;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.CheckBox;
import android.widget.TextView;

import com.fastlib.annotation.Bind;
import com.fastlib.annotation.ContentView;
import com.fastlib.annotation.LocalData;
import com.fastlib.app.FastActivity;
import com.fastlib.utils.DensityUtils;
import com.fastlib.utils.N;
import com.fastlib.utils.Utils;
import com.fastlib.widget.TitleBar;
import com.zhangju.xingquban.R;
import com.zhangju.xingquban.interestclassapp.refactor.common.PayDialog;
import com.zhangju.xingquban.interestclassapp.refactor.common.activity.WebViewActivity;
import com.zhangju.xingquban.interestclassapp.refactor.common.bean.CommonInterface;
import com.zhangju.xingquban.interestclassapp.refactor.common.utils.GridDivider;
import com.zhangju.xingquban.interestclassapp.refactor.me.adapter.FunbeanAdapter;
import com.zhangju.xingquban.interestclassapp.refactor.me.bean.ResponseWallet;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Created by sgfb on 2017/11/23.
 * 兑换兴趣豆
 */
@ContentView(R.layout.act_fun_bean)
public class ExchangeFunBeanActivity extends FastActivity{
    public static final String ARG_SER_WALLET="walletData";

    @LocalData(ARG_SER_WALLET)
    ResponseWallet mWallet;
    @Bind(R.id.titleBar)
    TitleBar mTitleBar;
    @Bind(R.id.funbeanRemain)
    TextView mFunbeanRemain;
    @Bind(R.id.checkAgreement)
    CheckBox mCheckAgreement;
    @Bind(R.id.agreement)
    TextView mAgreement;
    @Bind(R.id.grid)
    RecyclerView mGrid;
    FunbeanAdapter mAdapter;

    @Override
    protected void alreadyPrepared(){
        mAgreement.setText(Utils.getTextSomeOtherColor(5,11,mAgreement.getText().toString(),getResources().getColor(R.color.EF4E4C)));
        DecimalFormat df=new DecimalFormat("##.##");
        mTitleBar.setOnLeftClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
//        mGrid.addItemDecoration(new GridDivider(DensityUtils.dp2px(this,15),DensityUtils.dp2px(this,15)));

        mGrid.setAdapter(mAdapter=new FunbeanAdapter(this,computeRate(mWallet)));
        for(ResponseWallet.WalletRemain remain:mWallet.currency){
            if("兴趣豆".equals(remain.name)){
                mFunbeanRemain.setText(df.format(remain.coinNum));
                break;
            }
        }
    }

    /**
     * 重组兴趣豆汇率
     * @param wallet 汇率表
     * @return 返回一组汇率左兴趣豆，右人民币
     */
    private List<ResponseWallet.Rate> computeRate(ResponseWallet wallet){
        List<ResponseWallet.Rate> list=new ArrayList<>();
        if(wallet.currencyRate!=null&&!wallet.currencyRate.isEmpty()){
            for(ResponseWallet.Rate rate:wallet.currencyRate){
                if("人民币".equals(rate.currency.name)&&"兴趣豆".equals(rate.toCurrency.name)){
                    if(rate.sons==null||rate.sons.isEmpty()) return list;
                    for(ResponseWallet.Rate childRate:rate.sons){
                        list.add(childRate);
                    }
                    Collections.sort(list, new Comparator<ResponseWallet.Rate>() {
                        @Override
                        public int compare(ResponseWallet.Rate o1, ResponseWallet.Rate o2) {
                            return (int) (o1.fromAmount-o2.fromAmount);
                        }
                    });
                    return list;
                }
            }
        }
        return list;
    }

    @Bind(R.id.commit)
    private void commit(){
        if(!mCheckAgreement.isChecked()){
            N.showShort(this,"购买前需要同意充值协议");
            return;
        }
        ResponseWallet.Rate rate=mAdapter.getItemAtPosition(mAdapter.getmSelectItem());
        PayDialog dialog=PayDialog.getInstance(this,rate.id);
        getSupportFragmentManager()
                .beginTransaction()
                .replace(android.R.id.content,dialog)
                .commit();
    }

    @Bind(R.id.agreement)
    private void openAgreement(){
        Intent intent=new Intent(this, WebViewActivity.class);
        intent.putExtra(WebViewActivity.ARG_URL, CommonInterface.URL_RECHARGE_AGREEMENT);
        startActivity(intent);
    }
}