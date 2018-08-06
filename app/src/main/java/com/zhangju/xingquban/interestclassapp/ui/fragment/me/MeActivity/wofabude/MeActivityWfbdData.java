package com.zhangju.xingquban.interestclassapp.ui.fragment.me.MeActivity.wofabude;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import com.zhangju.xingquban.R;
import com.zhangju.xingquban.interestclassapp.base.BaseActivity;
import com.zhangju.xingquban.interestclassapp.ui.fragment.me.MeActivity.huodongshouru.MeActivityHdsr;
import com.zhangju.xingquban.interestclassapp.ui.fragment.me.MeActivity.jianpiao.MeActivityJianp;
import com.zhangju.xingquban.interestclassapp.ui.fragment.me.MeActivity.wofabude.baomingguanli.MeActivityWfbdDataBmgl;
import com.zhangju.xingquban.interestclassapp.ui.widget.PublicHead;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MeActivityWfbdData extends BaseActivity {

    @BindView(R.id.me_activity_wfbd_dataHead)
    PublicHead meActivityWfbdDataHead;
    @BindView(R.id.me_activity_fbhd_bmgl)
    LinearLayout meActivityFbhdBmgl;
    @BindView(R.id.me_activity_fbhd_bjhd)
    LinearLayout meActivityFbhdBjhd;
    @BindView(R.id.me_activity_ksjp)
    LinearLayout meActivityKsjp;
    @BindView(R.id.me_activity_hdsr)
    LinearLayout meActivityHdsr;
    @BindView(R.id.me_activity_fbhd_fxhd)
    LinearLayout meActivityFbhdFxhd;

    @Override
    public int getLayout() {
        return R.layout.activity_me_wfbd_data;
    }

    @Override
    public void initView() {
        setMeActivityWfbdDataHead();
    }

    @Override
    public void initData() {

    }

    @Override
    public void initListener() {

    }

    @OnClick({R.id.me_activity_fbhd_bmgl, R.id.me_activity_fbhd_bjhd, R.id.me_activity_ksjp, R.id.me_activity_hdsr, R.id.me_activity_fbhd_fxhd})
    public void onClick(View v) {
        switch (v.getId()) {

            //报名管理
            case R.id.me_activity_fbhd_bmgl:
                startActivity(new Intent(this, MeActivityWfbdDataBmgl.class));
                break;

            //编辑活动
            case R.id.me_activity_fbhd_bjhd:

                break;

            //开始检票
            case R.id.me_activity_ksjp:
                startActivity(new Intent(this, MeActivityJianp.class));
                break;

            //活动收入
            case R.id.me_activity_hdsr:
                startActivity(new Intent(this, MeActivityHdsr.class));
                break;

            //分享活动
            case R.id.me_activity_fbhd_fxhd:
                startActivity(new Intent(this, MeActivityWfbdHdfx.class));
                break;


        }
    }

    public void setMeActivityWfbdDataHead() {
        meActivityWfbdDataHead.setTitle("活动管理");
        meActivityWfbdDataHead.setShow(true, false, false);
        meActivityWfbdDataHead.setBackClickListener(new View.OnClickListener() {
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
