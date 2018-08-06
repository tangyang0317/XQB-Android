package com.zhangju.xingquban.interestclassapp.ui.fragment.me.Wallet.walletYouhuiq;

import android.os.Bundle;
import android.view.View;

import com.zhangju.xingquban.R;
import com.zhangju.xingquban.interestclassapp.base.BaseActivity;
import com.zhangju.xingquban.interestclassapp.ui.widget.PublicHead;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MeWalletYouhuiq extends BaseActivity {

    @BindView(R.id.me_wallet_yhq)
    PublicHead meWalletYhq;

    @Override
    public int getLayout() {
        return R.layout.activity_me_wallet_youhuiq;
    }

    @Override
    public void initView() {
        getMeWalledhyhqHead();
    }

    @Override
    public void initData() {

    }

    @Override
    public void initListener() {

    }

    @OnClick({})
    public void onClick(View v) {
        switch (v.getId()) {
        }
    }

    public void getMeWalledhyhqHead() {
        meWalletYhq.setTitle("我的优惠券");
        meWalletYhq.setShow(true, false, false);
        meWalletYhq.setBackClickListener(new View.OnClickListener() {
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
