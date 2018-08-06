package com.zhangju.xingquban.interestclassapp.ui.fragment.me.Institutions.kcgl;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;

import com.zhangju.xingquban.R;
import com.zhangju.xingquban.interestclassapp.base.BaseActivity;
import com.zhangju.xingquban.interestclassapp.ui.widget.PublicHead;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MeJiGouKcgl extends BaseActivity {

    @BindView(R.id.me_jigou_kcgl_head)
    PublicHead meJigouKcglHead;
    @BindView(R.id.me_jigou_kcgl_skkm)
    RelativeLayout meJigouKcglSkkm;
    @BindView(R.id.me_jigou_kcgl_kmtj)
    RelativeLayout meJigouKcglKmtj;

    @Override
    public int getLayout() {
        return R.layout.activity_me_ji_gou_kcgl;
    }

    @Override
    public void initView() {
        setMeJigouKcglHead();
    }

    public void setMeJigouKcglHead() {
        meJigouKcglHead.setTitle("课程设置");
        meJigouKcglHead.setShow(true, false, false);
        meJigouKcglHead.setBackClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    @Override
    public void initData() {

    }

    @Override
    public void initListener() {

    }

    @OnClick({R.id.me_jigou_kcgl_skkm, R.id.me_jigou_kcgl_kmtj})
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.me_jigou_kcgl_skkm:
                startActivity(new Intent(MeJiGouKcgl.this, MeJiGouKcglSkkm.class));
                break;
            case R.id.me_jigou_kcgl_kmtj:
                startActivity(new Intent(MeJiGouKcgl.this, MeJiGouKcglKctj.class));
                break;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
    }
}
