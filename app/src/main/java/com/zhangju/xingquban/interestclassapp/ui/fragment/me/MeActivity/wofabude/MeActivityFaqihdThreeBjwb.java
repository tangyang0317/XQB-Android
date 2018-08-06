package com.zhangju.xingquban.interestclassapp.ui.fragment.me.MeActivity.wofabude;

import android.os.Bundle;
import android.view.View;

import com.zhangju.xingquban.R;
import com.zhangju.xingquban.interestclassapp.base.BaseActivity;
import com.zhangju.xingquban.interestclassapp.ui.widget.PublicHead;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MeActivityFaqihdThreeBjwb extends BaseActivity {


    @BindView(R.id.me_activity_fbhdThree_bjwb)
    PublicHead meActivityFbhdThreeBjwb;

    @Override
    public int getLayout() {
        return R.layout.activity_me_faqihd_three_bjwb;
    }

    @Override
    public void initView() {
        setMeActivityFbhdThreeBjwb();
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

    public void setMeActivityFbhdThreeBjwb() {
        meActivityFbhdThreeBjwb.setTitle("编辑文本");
        meActivityFbhdThreeBjwb.setShow(true, false, true);
        meActivityFbhdThreeBjwb.setRightTitle("完成");
        meActivityFbhdThreeBjwb.setRightTextColor(R.color.color_main);
        meActivityFbhdThreeBjwb.setBackClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        meActivityFbhdThreeBjwb.setRightClickListener(new View.OnClickListener() {
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
