package com.zhangju.xingquban.interestclassapp.ui.fragment.me.Wallet;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.zhangju.xingquban.R;
import com.zhangju.xingquban.interestclassapp.base.BaseActivity;
import com.zhangju.xingquban.interestclassapp.ui.fragment.me.Wallet.walletCz.MeWalletCz;
import com.zhangju.xingquban.interestclassapp.ui.fragment.me.Wallet.walletMenpiao.MeWalletMenp;
import com.zhangju.xingquban.interestclassapp.ui.fragment.me.Wallet.walletTix.MeWalletTix;
import com.zhangju.xingquban.interestclassapp.ui.fragment.me.Wallet.walletXqb.MeWalletXqb;
import com.zhangju.xingquban.interestclassapp.ui.fragment.me.Wallet.walletYouhuiq.MeWalletYouhuiq;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 我的钱包Activity
 */
public class MyWallet extends BaseActivity {

    @BindView(R.id.rl_wallet_title)
    RelativeLayout rlWalletTitle;
    @BindView(R.id.bind)
    RelativeLayout walletBind;
    @BindView(R.id.rl_wallet_balance)
    RelativeLayout rlWalletBalance;
    @BindView(R.id.rl_wallet_head)
    RelativeLayout rlWalletHead;
    @BindView(R.id.recharge)
    LinearLayout walletChongzhi;
    @BindView(R.id.remainLayout)
    LinearLayout walletYue;
    @BindView(R.id.withdraw)
    LinearLayout walletTixian;
    @BindView(R.id.funCoin)
    RelativeLayout walletXingqubi;
    @BindView(R.id.ticketIncome)
    RelativeLayout walletMenpiao;
    @BindView(R.id.discountTicket)
    RelativeLayout walletYouhuiquan;

    @Override
    public int getLayout() {
        return R.layout.activity_my_wallet;
    }

    @Override
    public void initView() {

    }

    @Override
    public void initData() {

    }

    @Override
    public void initListener() {

    }

    @OnClick({R.id.bind, R.id.back, R.id.recharge, R.id.remainLayout, R.id.withdraw, R.id.funCoin, R.id.ticketIncome, R.id.discountTicket
    })
    public void onClick(View v) {
        switch (v.getId()) {

            case R.id.bind:
                startActivity(new Intent(MyWallet.this, MeWalletBind.class));
                break;

            //充值
            case R.id.recharge:
                startActivity(new Intent(MyWallet.this, MeWalletCz.class));
                break;

            //余额
            case R.id.remainLayout:

                break;

            //提现
            case R.id.withdraw:
                startActivity(new Intent(MyWallet.this, MeWalletTix.class));
                break;

            //兴趣豆
//            case R.id.wallet_xingqudou:
//                startActivity(new Intent(MyWallet.this, MeWalletXqd.class));
//                break;

            //兴趣币
            case R.id.funCoin:
                startActivity(new Intent(MyWallet.this, MeWalletXqb.class));
                break;

            //门票收益
            case R.id.ticketIncome:
                startActivity(new Intent(MyWallet.this, MeWalletMenp.class));
                break;

            //优惠券
            case R.id.discountTicket:
                startActivity(new Intent(MyWallet.this, MeWalletYouhuiq.class));
                break;

            //交易记录
//            case R.id.wallet_jiaoyi:
//                startActivity(new Intent(MyWallet.this, MeWalletJYJL.class));
//                break;

            //返回
            case R.id.back:
                finish();
                break;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
    }
}
