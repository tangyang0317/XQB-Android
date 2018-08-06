package com.zhangju.xingquban.interestclassapp.ui.fragment.me.Wallet;

import android.os.Bundle;
import android.view.View;

import com.zhangju.xingquban.R;
import com.zhangju.xingquban.interestclassapp.base.BaseActivity;
import com.zhangju.xingquban.interestclassapp.ui.widget.PublicHead;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MeWalletCZture extends BaseActivity {


    @BindView(R.id.me_wallet_dhcg)
    PublicHead meWalletDhcg;

    @Override
    public int getLayout() {
        return R.layout.activity_me_wallet_czture;
    }

    @Override
    public void initView() {
        getMeWalledhcgxHead();
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

    public void getMeWalledhcgxHead() {
        meWalletDhcg.setTitle("兑换");
        meWalletDhcg.setShow(true, false, false);
        meWalletDhcg.setBackClickListener(new View.OnClickListener() {
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
