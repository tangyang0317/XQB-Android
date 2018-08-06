package com.zhangju.xingquban.interestclassapp.ui.fragment.me.Wallet.walletCz;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.zhangju.xingquban.R;
import com.zhangju.xingquban.interestclassapp.base.BaseActivity;
import com.zhangju.xingquban.interestclassapp.ui.widget.PublicHead;
import com.zhangju.xingquban.interestclassapp.util.ToastUtil;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MeWalletCz extends BaseActivity {


    @BindView(R.id.me_walletcz_head)
    PublicHead meWalletczHead;
    @BindView(R.id.image_wallet_cz)
    ImageView imageWalletCz;
    @BindView(R.id.me_vip_jigouvip1)
    ImageView meVipJigouvip1;
    @BindView(R.id.vip_radiobutton_weixin)
    RadioButton vipRadiobuttonWeixin;
    @BindView(R.id.me_vip_jigoutext1)
    TextView meVipJigoutext1;
    @BindView(R.id.rt_me_vip_weixin)
    RelativeLayout rtMeVipWeixin;
    @BindView(R.id.me_vip_jigouvip2)
    ImageView meVipJigouvip2;
    @BindView(R.id.vip_radiobutton_zhifubao)
    RadioButton vipRadiobuttonZhifubao;
    @BindView(R.id.iv_my_vip_zhifubao)
    ImageView ivMyVipZhifubao;
    @BindView(R.id.me_vip_jigoutext2)
    TextView meVipJigoutext2;
    @BindView(R.id.view2)
    View view2;
    @BindView(R.id.rt_me_vip_zhifubao)
    RelativeLayout rtMeVipZhifubao;
    @BindView(R.id.me_vip_kaitong)
    Button meVipKaitong;

    private int PayType = 1;

    @Override
    public int getLayout() {
        return R.layout.activity_me_wallet_cz;
    }


    @Override
    public void initView() {
        getMeWalletczHead();
        vipRadiobuttonWeixin.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                vipRadiobuttonZhifubao.setChecked(false);
                PayType = 1;
            }
        });
        vipRadiobuttonZhifubao.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                vipRadiobuttonWeixin.setChecked(false);
                PayType = 2;
            }
        });
    }

    @Override
    public void initData() {

    }

    @Override
    public void initListener() {

    }

    @OnClick({R.id.me_vip_kaitong})
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.me_vip_kaitong:
                if (PayType == 1) {
                    ToastUtil.showToast("微信");
                } else {
                    ToastUtil.showToast("支付宝");
                }
                break;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
    }

    public void getMeWalletczHead() {
        meWalletczHead.setTitle("确认充值");
        meWalletczHead.setShow(true, false, false);
        meWalletczHead.setBackClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
