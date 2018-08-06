package com.zhangju.xingquban.interestclassapp.ui.fragment.me.Wallet.walletTix;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.zhangju.xingquban.R;
import com.zhangju.xingquban.interestclassapp.base.BaseActivity;
import com.zhangju.xingquban.interestclassapp.ui.widget.PublicHead;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MeWalletTix extends BaseActivity {


    @BindView(R.id.wallet_tixian_head)
    PublicHead walletTixianHead;
    @BindView(R.id.textView7)
    TextView textView7;
    @BindView(R.id.me_vip_jigouvip1)
    ImageView meVipJigouvip1;
    @BindView(R.id.me_vip_jigoutext1)
    TextView meVipJigoutext1;
    @BindView(R.id.rt_me_vip_weixin)
    RelativeLayout rtMeVipWeixin;

    @Override
    public int getLayout() {
        return R.layout.activity_me_wallet_tix;
    }

    @Override
    public void initView() {
        getMeWallettxHead();
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

    public void getMeWallettxHead() {
        walletTixianHead.setTitle("提现");
        walletTixianHead.setShow(true, false, true);
        walletTixianHead.setRightTitle("充值记录");
        walletTixianHead.setRightTextColor(R.color.f959595);
        walletTixianHead.setBackClickListener(new View.OnClickListener() {
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
