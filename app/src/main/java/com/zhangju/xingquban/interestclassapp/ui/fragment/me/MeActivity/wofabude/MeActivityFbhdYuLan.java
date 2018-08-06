package com.zhangju.xingquban.interestclassapp.ui.fragment.me.MeActivity.wofabude;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.zhangju.xingquban.R;
import com.zhangju.xingquban.interestclassapp.base.BaseActivity;
import com.zhangju.xingquban.interestclassapp.ui.widget.PublicHead;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MeActivityFbhdYuLan extends BaseActivity {


    @BindView(R.id.me_activity_wfbd_yulan)
    PublicHead meActivityWfbdYulan;

    @Override
    public int getLayout() {
        return R.layout.activity_me_fbhd_yu_lan;
    }

    @Override
    public void initView() {
        setMeActivityWfbdYulan();
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

    public void setMeActivityWfbdYulan() {
        meActivityWfbdYulan.setTitle("活动详情");
        meActivityWfbdYulan.setShow(true, false, true);
        meActivityWfbdYulan.setRightTitle("发布");
        meActivityWfbdYulan.setRightTextColor(R.color.color_main);
        meActivityWfbdYulan.setBackClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        meActivityWfbdYulan.setRightClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MeActivityFbhdYuLan.this, MeActivityFbhdSucess.class));
            }
        });
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
    }
}
