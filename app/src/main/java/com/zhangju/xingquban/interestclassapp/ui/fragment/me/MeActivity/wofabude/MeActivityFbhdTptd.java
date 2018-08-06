package com.zhangju.xingquban.interestclassapp.ui.fragment.me.MeActivity.wofabude;

import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.LinearLayout;

import com.zhangju.xingquban.R;
import com.zhangju.xingquban.interestclassapp.base.BaseActivity;
import com.zhangju.xingquban.interestclassapp.ui.widget.PublicHead;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MeActivityFbhdTptd extends BaseActivity {


    @BindView(R.id.me_activity_fbhd_tptdHead)
    PublicHead meActivityFbhdTptdHead;
    @BindView(R.id.me_activity_fbhd_tptdKq)
    CheckBox meActivityFbhdTptdKq;
    @BindView(R.id.me_activity_fbhd_tptdKqBm)
    LinearLayout meActivityFbhdTptdKqBm;

    @Override
    public int getLayout() {
        return R.layout.activity_me_fbhd_tptd;
    }

    @Override
    public void initView() {
        setMeActivityFbhdTptdHead();
    }

    @Override
    public void initData() {

    }

    @Override
    public void initListener() {

    }

    @OnClick({R.id.me_activity_fbhd_tptdKq})
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.me_activity_fbhd_tptdKq:
                meActivityFbhdTptdKqBm.setVisibility(View.VISIBLE);
                break;
        }
    }

    public void setMeActivityFbhdTptdHead() {
        meActivityFbhdTptdHead.setTitle("投票设置");
        meActivityFbhdTptdHead.setShow(true, false, true);
        meActivityFbhdTptdHead.setRightTitle("完成");
        meActivityFbhdTptdHead.setRightTextColor(R.color.color_main);
        meActivityFbhdTptdHead.setBackClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        meActivityFbhdTptdHead.setRightClickListener(new View.OnClickListener() {
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
