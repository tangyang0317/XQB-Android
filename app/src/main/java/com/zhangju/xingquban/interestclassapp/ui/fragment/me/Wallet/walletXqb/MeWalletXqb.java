package com.zhangju.xingquban.interestclassapp.ui.fragment.me.Wallet.walletXqb;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;

import com.zhangju.xingquban.R;
import com.zhangju.xingquban.interestclassapp.base.BaseActivity;
import com.zhangju.xingquban.interestclassapp.ui.fragment.me.Wallet.MeWalletCZture;
import com.zhangju.xingquban.interestclassapp.ui.fragment.me.Wallet.MeWalletJYJL;
import com.zhangju.xingquban.interestclassapp.ui.widget.PublicHead;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MeWalletXqb extends BaseActivity {

    @BindView(R.id.me_wallet_xqb_head)
    PublicHead meWalletXqbHead;
    @BindView(R.id.cb_live_start_agaree)
    CheckBox cbLiveStartAgaree;
    @BindView(R.id.tv_live_start_agreement)
    TextView tvLiveStartAgreement;
    @BindView(R.id.wallet_duihuan_xqb)
    Button walletDuihuanXqb;

    @Override
    public int getLayout() {
        return R.layout.activity_me_wallet_xqb;
    }

    @Override
    public void initView() {
        getMeWalletsqbHead();
    }

    @Override
    public void initData() {

    }

    @Override
    public void initListener() {

    }

    @OnClick({R.id.wallet_duihuan_xqb})
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.wallet_duihuan_xqb:
                startActivity(new Intent(MeWalletXqb.this, MeWalletCZture.class));
                break;
        }
    }

    public void getMeWalletsqbHead() {
        meWalletXqbHead.setTitle("兑换");
        meWalletXqbHead.setShow(true, false, true);
        meWalletXqbHead.setRightTitle("收益记录");
        meWalletXqbHead.setRightTextColor(R.color.f959595);
        meWalletXqbHead.setBackClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        meWalletXqbHead.setRightClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MeWalletXqb.this, MeWalletJYJL.class));
            }
        });
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
    }
}
