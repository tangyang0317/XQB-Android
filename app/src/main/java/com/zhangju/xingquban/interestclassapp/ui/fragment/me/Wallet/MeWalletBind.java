package com.zhangju.xingquban.interestclassapp.ui.fragment.me.Wallet;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import com.zhangju.xingquban.R;
import com.zhangju.xingquban.interestclassapp.base.BaseActivity;
import com.zhangju.xingquban.interestclassapp.ui.fragment.me.Settings.MyDialog;
import com.zhangju.xingquban.interestclassapp.ui.widget.PublicHead;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 我的钱包 绑定支付宝/微信
 */
public class MeWalletBind extends BaseActivity {


    @BindView(R.id.wallet_head)
    PublicHead walletHead;
    @BindView(R.id.wallet_bind_tianjia)
    LinearLayout walletBindTianjia;
//    @BindView(R.id.wallet_bind_dialog)
//    ImageView walletBindDialog;
//    @BindView(R.id.wallet_dialog_zhifubao)
//    LinearLayout walletDialogZhifubao;
//    @BindView(R.id.wallet_dialog_weixin)
//    LinearLayout walletDialogWeixin;

    private View view;
    private MyDialog ab;

    @Override
    public int getLayout() {
        return R.layout.activity_me_wallet_bind;
    }

    @Override
    public void initView() {
        getHead();
        getDialog();
    }

    //xml顶部H导航Head
    public void getHead() {
        walletHead.setTitle("我的账户");
        walletHead.setShow(true, true, false);
        walletHead.setImgSearch(R.mipmap.wallet_bind_tob);
        walletHead.setAddClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ab.show();
            }
        });
        walletHead.setBackClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    //Dialog弹窗
    public void getDialog() {
        view = getLayoutInflater().inflate(R.layout.me_bind_view_dialog, null);
        ab = new MyDialog(MeWalletBind.this, view, R.style.dialog);
        view.findViewById(R.id.wallet_bind_dialog).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ab.cancel();
            }
        });
        view.findViewById(R.id.wallet_dialog_zhifubao).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MeWalletBind.this, WalletBindAddActivity.class);
                intent.putExtra("tag", true);
                startActivity(intent);
            }
        });
        view.findViewById(R.id.wallet_dialog_weixin).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MeWalletBind.this, WalletBindAddActivity.class);
                intent.putExtra("tag", false);
                startActivity(intent);
            }
        });
    }

    @Override
    public void initData() {

    }

    @Override
    public void initListener() {

    }

    @OnClick({R.id.wallet_bind_tianjia})
    public void onClick(View v) {
        switch (v.getId()) {

            case R.id.wallet_bind_tianjia:
                ab.show();
                break;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
    }
}
