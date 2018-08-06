package com.zhangju.xingquban.interestclassapp.ui.fragment.me.Wallet.walletXqd;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.zhangju.xingquban.R;
import com.zhangju.xingquban.interestclassapp.base.BaseActivity;
import com.zhangju.xingquban.interestclassapp.ui.widget.PublicHead;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MeWalletXqd extends BaseActivity {

    @BindView(R.id.me_wallet_xqb_head)
    PublicHead meWalletXqbHead;
    @BindView(R.id.chongzhijine)
    TextView chongzhijine;
    @BindView(R.id.view3)
    View view3;

    @Override
    public int getLayout() {
        return R.layout.activity_me_wallet_xqd;
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

    @Override
    public void onClick(View v) {

    }

    public void getMeWalletsqbHead() {
        meWalletXqbHead.setTitle("充值");
        meWalletXqbHead.setShow(true, false, true);
        meWalletXqbHead.setRightTitle("充值记录");
        meWalletXqbHead.setRightTextColor(R.color.f959595);
        meWalletXqbHead.setBackClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
    }
}
