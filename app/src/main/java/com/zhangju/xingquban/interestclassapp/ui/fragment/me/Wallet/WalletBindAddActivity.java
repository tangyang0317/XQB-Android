package com.zhangju.xingquban.interestclassapp.ui.fragment.me.Wallet;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.zhangju.xingquban.R;
import com.zhangju.xingquban.interestclassapp.base.BaseActivity;
import com.zhangju.xingquban.interestclassapp.ui.widget.PublicHead;
import com.zhangju.xingquban.interestclassapp.util.ToastUtil;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 我的钱包绑定支付宝/微信
 */
public class WalletBindAddActivity extends BaseActivity {


    @BindView(R.id.wallet_bind_addActivity)
    PublicHead walletBindAddActivity;
    @BindView(R.id.iv_wallet_bind_add_icon)
    ImageView ivWalletBindAddIcon;
    @BindView(R.id.tv_wallet_bind_add_text)
    TextView tvWalletBindAddText;
    @BindView(R.id.tv_wallet_bind_add_account)
    TextView tvWalletBindAddAccount;
    @BindView(R.id.et_wallet_bind_add_num)
    EditText etWalletBindAddNum;
    @BindView(R.id.et_wallet_bind_add_name_input)
    EditText etWalletBindAddNameInput;
    @BindView(R.id.tv_wallet_bind_add_tip)
    TextView tvWalletBindAddTip;
    @BindView(R.id.tv_wallet_bind_add_bindnow)
    TextView tvWalletBindAddBindnow;

    @Override
    public int getLayout() {
        return R.layout.activity_wallet_bind_add;
    }

    @Override
    public void initView() {
        getHead();
        Intent intent = getIntent();
        intent.getBooleanExtra("tag", true);
        if (!intent.getBooleanExtra("tag", true)) {
            ivWalletBindAddIcon.setImageResource(R.mipmap.me_wallet_bind_weixin);
            tvWalletBindAddText.setText("绑定的微信账号将成为你的提现账户");
            tvWalletBindAddTip.setText("注意：请仔细确认微信账户和姓名是否正确，否则提现操作都将失败");

        }

    }

    public void getHead() {
        walletBindAddActivity.setTitle("提现绑定账户");
        walletBindAddActivity.setShow(true, false, false);
        walletBindAddActivity.setBackClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    @Override
    public void initData() {

    }

    @Override
    public void initListener() {

    }

    @OnClick({R.id.tv_wallet_bind_add_bindnow})
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_wallet_bind_add_bindnow:
                if (getIntent().getBooleanExtra("tag", true)) {
                    ToastUtil.showToast("支付宝点击事件");
                } else {
                    ToastUtil.showToast("微信点击事件");
                }

                break;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
    }
}
