package com.zhangju.xingquban.interestclassapp.ui.fragment.me.MeActivity.wofabude;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.zhangju.xingquban.R;
import com.zhangju.xingquban.interestclassapp.base.BaseActivity;
import com.zhangju.xingquban.interestclassapp.ui.widget.PublicHead;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MeActivityFaqihd extends BaseActivity {

    @BindView(R.id.me_activity_wfbdFqhdHead)
    PublicHead meActivityWfbdFqhdHead;
    @BindView(R.id.me_activity_fbhd_hdcd)
    LinearLayout meActivityFbhdHdcd;
    @BindView(R.id.me_activity_fbhd_bmsz)
    RelativeLayout meActivityFbhdBmsz;
    @BindView(R.id.me_activity_fbhd_tptd)
    RelativeLayout meActivityFbhdTptd;

    @Override
    public int getLayout() {
        return R.layout.activity_me_faqihd;
    }

    @Override
    public void initView() {
        setMeActivityWfbdFqhdHead();
    }

    @Override
    public void initData() {

    }

    @Override
    public void initListener() {

    }

    @OnClick({R.id.me_activity_fbhd_hdcd, R.id.me_activity_fbhd_bmsz, R.id.me_activity_fbhd_tptd})
    public void onClick(View v) {
        switch (v.getId()) {
            //活动地址
            case R.id.me_activity_fbhd_hdcd:

                break;
            //报名设置
            case R.id.me_activity_fbhd_bmsz:
                startActivity(new Intent(this, MeActivityFbhdBmsz.class));
                break;
            //投票通道
            case R.id.me_activity_fbhd_tptd:
                startActivity(new Intent(this, MeActivityFbhdTptd.class));
                break;
        }

    }

    public void setMeActivityWfbdFqhdHead() {
        meActivityWfbdFqhdHead.setTitle("发布活动");
        meActivityWfbdFqhdHead.setShow(true, false, true);
        meActivityWfbdFqhdHead.setRightTitle("下一步");
        meActivityWfbdFqhdHead.setRightTextColor(R.color.color_main);
        meActivityWfbdFqhdHead.setBackClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        meActivityWfbdFqhdHead.setRightClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MeActivityFaqihd.this, MeActivityFaqihdTwo.class));
            }
        });
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
    }
}
