package com.zhangju.xingquban.interestclassapp.ui.fragment.me.MeActivity.wofabude;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;

import com.zhangju.xingquban.R;
import com.zhangju.xingquban.interestclassapp.base.BaseActivity;
import com.zhangju.xingquban.interestclassapp.ui.widget.PublicHead;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MeActivityFaqihdThree extends BaseActivity {


    @BindView(R.id.me_activity_wfbdFbhd_Head)
    PublicHead meActivityWfbdFbhdHead;
    @BindView(R.id.me_activity_image1)
    ImageView meActivityImage1;
    @BindView(R.id.me_activity_image2)
    ImageView meActivityImage2;
    @BindView(R.id.imageView)
    ImageView imageView;
    @BindView(R.id.me_activity_fbhdTwo_button)
    Button meActivityFbhdTwoButton;
    @BindView(R.id.me_activity_fbhdThree_tianjia)
    LinearLayout meActivityFbhdThreeTianjia;
    @BindView(R.id.me_jg_jgjjTj_tj)
    ImageView meJgJgjjTjTj;
    @BindView(R.id.me_activity_fbhdThree_tjxc)
    LinearLayout meActivityFbhdThreeTjxc;
    @BindView(R.id.me_jg_jgjjTj_tj3)
    ImageView meJgJgjjTjTj3;
    @BindView(R.id.me_activity_fbhdThree_xianshi)
    ScrollView meActivityFbhdThreeXianshi;

    @Override
    public int getLayout() {
        return R.layout.activity_me_faqihd_three;
    }

    @Override
    public void initView() {
        setMeActivityWfbdFbhdHead();
    }

    @Override
    public void initData() {

    }

    @Override
    public void initListener() {

    }

    @OnClick({R.id.me_activity_fbhdTwo_button})
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.me_activity_fbhdTwo_button:
                meActivityFbhdThreeTianjia.setVisibility(View.GONE);
                meActivityFbhdThreeXianshi.setVisibility(View.VISIBLE);
                break;

        }
    }

    public void setMeActivityWfbdFbhdHead() {
        meActivityWfbdFbhdHead.setTitle("发布活动");
        meActivityWfbdFbhdHead.setShow(true, false, true);
        meActivityWfbdFbhdHead.setRightTitle("下一步");
        meActivityWfbdFbhdHead.setRightTextColor(R.color.color_main);
        meActivityWfbdFbhdHead.setBackClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        meActivityWfbdFbhdHead.setRightClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MeActivityFaqihdThree.this, MeActivityFaqihdFour.class));
            }
        });
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
    }
}
