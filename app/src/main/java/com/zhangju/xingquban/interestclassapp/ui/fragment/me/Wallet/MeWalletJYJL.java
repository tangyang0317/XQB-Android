package com.zhangju.xingquban.interestclassapp.ui.fragment.me.Wallet;

import android.view.View;

import com.zhangju.xingquban.R;
import com.zhangju.xingquban.interestclassapp.base.BaseActivity;
import com.zhangju.xingquban.interestclassapp.ui.widget.PublicHead;

import butterknife.BindView;

public class MeWalletJYJL extends BaseActivity {

    @BindView(R.id.me_wallet_jiaoyi)
    PublicHead meWalletJiaoyi;

    @Override
    public int getLayout() {
        return R.layout.activity_me_wallet_jyjl;
    }

    @Override
    public void initView() {
        getMeWalledjyjlHead();
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

    public void getMeWalledjyjlHead() {
        meWalletJiaoyi.setTitle("交易记录");
        meWalletJiaoyi.setShow(true, false, false);
        meWalletJiaoyi.setBackClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
