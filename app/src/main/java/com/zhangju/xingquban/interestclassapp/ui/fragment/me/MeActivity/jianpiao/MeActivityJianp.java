package com.zhangju.xingquban.interestclassapp.ui.fragment.me.MeActivity.jianpiao;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.zhangju.xingquban.R;
import com.zhangju.xingquban.interestclassapp.base.BaseActivity;
import com.zhangju.xingquban.interestclassapp.ui.widget.PublicHead;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MeActivityJianp extends BaseActivity {


    @BindView(R.id.me_activity_jpHead)
    PublicHead meActivityJpHead;
    @BindView(R.id.find_tab_button_erweima)
    ImageView findTabButtonErweima;
    @BindView(R.id.find_tab_button_sdsr)
    RelativeLayout findTabButtonSdsr;
    @BindView(R.id.me_activity_jpEdit)
    EditText meActivityJpEdit;
    @BindView(R.id.me_activity_jpButton)
    Button meActivityJpButton;
    @BindView(R.id.me_activity_jpErweima)
    LinearLayout meActivityJpErweima;
    @BindView(R.id.me_activity_jpSdyz)
    RelativeLayout meActivityJpSdyz;

    @Override
    public int getLayout() {
        return R.layout.activity_me_jianp;
    }

    @Override
    public void initView() {
        setMeActivityJpHead();
    }

    @Override
    public void initData() {

    }

    @Override
    public void initListener() {

    }

    @OnClick({R.id.find_tab_button_sdsr, R.id.find_tab_button_erweima})
    public void onClick(View v) {
        switch (v.getId()) {
            //二维码
            case R.id.find_tab_button_erweima:

                break;

            //手动输入
            case R.id.find_tab_button_sdsr:
                meActivityJpErweima.setVisibility(View.GONE);
                meActivityJpSdyz.setVisibility(View.VISIBLE);
                break;


        }
    }

    public void setMeActivityJpHead() {
        meActivityJpHead.setTitle("验票");
        meActivityJpHead.setShow(true, false, false);
        meActivityJpHead.setBackClickListener(new View.OnClickListener() {
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
