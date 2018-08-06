package com.zhangju.xingquban.interestclassapp.ui.fragment.me.MeActivity.wofabude;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;

import com.zhangju.xingquban.R;
import com.zhangju.xingquban.interestclassapp.base.BaseActivity;
import com.zhangju.xingquban.interestclassapp.ui.widget.PublicHead;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MeActivityFaqihdFour extends BaseActivity {

    @BindView(R.id.me_activity_fbhdFour)
    PublicHead meActivityFbhdFour;
    @BindView(R.id.me_activity_image1)
    ImageView meActivityImage1;
    @BindView(R.id.me_activity_image2)
    ImageView meActivityImage2;
    @BindView(R.id.imageView)
    ImageView imageView;
    @BindView(R.id.me_activity_fbhdTwo_button)
    Button meActivityFbhdTwoButton;
    @BindView(R.id.me_activity_fbhdFour_tianjia)
    LinearLayout meActivityFbhdFourTianjia;
    @BindView(R.id.me_activity_fbhdThree_tjxc)
    RelativeLayout meActivityFbhdThreeTjxc;
    @BindView(R.id.me_activity_fbhdFour_xianshi)
    ScrollView meActivityFbhdFourXianshi;

    @Override
    public int getLayout() {
        return R.layout.activity_me_faqihd_four;
    }

    @Override
    public void initView() {
        setMeActivityFbhdFour();
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

    public void setMeActivityFbhdFour() {
        meActivityFbhdFour.setTitle("发布活动");
        meActivityFbhdFour.setShow(true, false, true);
        meActivityFbhdFour.setRightTitle("预览");
        meActivityFbhdFour.setRightTextColor(R.color.color_main);
        meActivityFbhdFour.setBackClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        meActivityFbhdFour.setRightClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MeActivityFaqihdFour.this, MeActivityFbhdYuLan.class));
            }
        });
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
    }
}
