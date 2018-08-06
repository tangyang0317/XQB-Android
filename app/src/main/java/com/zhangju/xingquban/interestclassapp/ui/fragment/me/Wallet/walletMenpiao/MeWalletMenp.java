package com.zhangju.xingquban.interestclassapp.ui.fragment.me.Wallet.walletMenpiao;

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

public class MeWalletMenp extends BaseActivity {


    @BindView(R.id.cb_live_start_agaree)
    CheckBox cbLiveStartAgaree;
    @BindView(R.id.tv_live_start_agreement)
    TextView tvLiveStartAgreement;
    @BindView(R.id.wallet_yhmp)
    Button walletYhmp;
    @BindView(R.id.me_wallet_mp_head)
    PublicHead meWalletMpHead;

    @Override
    public int getLayout() {
        return R.layout.activity_me_wallet_menp;
    }

    @Override
    public void initView() {
        getMeWalletmenpHead();
    }

    @Override
    public void initData() {

    }

    @Override
    public void initListener() {

    }

    @OnClick({R.id.wallet_yhmp})
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.wallet_yhmp:
                startActivity(new Intent(MeWalletMenp.this, MeWalletCZture.class));
                break;
        }
    }


    public void getMeWalletmenpHead() {
        meWalletMpHead.setTitle("充值");
        meWalletMpHead.setShow(true, false, true);
        meWalletMpHead.setRightTitle("兑换记录");
        meWalletMpHead.setRightTextColor(R.color.f959595);
        meWalletMpHead.setBackClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        meWalletMpHead.setRightClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MeWalletMenp.this, MeWalletJYJL.class));
            }
        });
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
    }
}
