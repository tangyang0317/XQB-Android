package com.zhangju.xingquban.interestclassapp.ui.fragment.me.MeActivity.huodongshouru;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.zhangju.xingquban.R;
import com.zhangju.xingquban.interestclassapp.base.BaseActivity;
import com.zhangju.xingquban.interestclassapp.ui.widget.PublicHead;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MeActivityHdsr extends BaseActivity {


    @BindView(R.id.me_activity_hdsr_head)
    PublicHead meActivityHdsrHead;
    @BindView(R.id.me_activity_hdsr_yjsMoney)
    TextView meActivityHdsrYjsMoney;
    @BindView(R.id.me_activity_hdsr_djsMoney)
    TextView meActivityHdsrDjsMoney;

    @Override
    public int getLayout() {
        return R.layout.activity_me_hdsr;
    }

    @Override
    public void initView() {
        setMeActivityHdsrHead();
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

    public void setMeActivityHdsrHead() {
        meActivityHdsrHead.setTitle("活动收入");
        meActivityHdsrHead.setShow(true, false, false);
        meActivityHdsrHead.setBackClickListener(new View.OnClickListener() {
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
