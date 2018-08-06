package com.zhangju.xingquban.interestclassapp.ui.fragment.me.Institutions.kcgl;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.zhangju.xingquban.R;
import com.zhangju.xingquban.interestclassapp.base.BaseActivity;
import com.zhangju.xingquban.interestclassapp.ui.widget.PublicHead;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MeJiGouKcglKctjSKfs extends BaseActivity {

    @BindView(R.id.MeJiGouKcglKctjSKfs)
    PublicHead MeJiGouKcglKctjSKfs;
    @BindView(R.id.student_text)
    TextView studentText;
    @BindView(R.id.Student)
    RelativeLayout Student;
    @BindView(R.id.familyEducation_text)
    TextView familyEducationText;
    @BindView(R.id.FamilyEducation)
    RelativeLayout FamilyEducation;
    @BindView(R.id.negotiated_text)
    TextView negotiatedText;
    @BindView(R.id.NegotiatedCity)
    RelativeLayout NegotiatedCity;
    private Intent intent;

    @Override
    public int getLayout() {
        return R.layout.activity_me_ji_gou_kcgl_kctj_skfs;
    }

    @Override
    public void initView() {
        setMeJiGouKcglKctjSKfs();
    }

    @Override
    public void initData() {

    }

    @Override
    public void initListener() {

    }

    @OnClick({R.id.Student, R.id.FamilyEducation, R.id.NegotiatedCity})
    public void onClick(View v) {
        switch (v.getId()) {

            case R.id.Student:
                intent = new Intent();
                Bundle bundle = new Bundle();
                String s = studentText.getText().toString();
                bundle.putString("a", s);
                intent.putExtras(bundle);
                setResult(1, intent);
                finish();
                break;

            case R.id.FamilyEducation:
                intent = new Intent();
                Bundle bundle1 = new Bundle();
                String s1 = familyEducationText.getText().toString();
                bundle1.putString("a", s1);
                intent.putExtras(bundle1);
                setResult(1, intent);
                finish();
                break;

            case R.id.NegotiatedCity:
                intent = new Intent();
                Bundle bundle2 = new Bundle();
                String s2 = negotiatedText.getText().toString();
                bundle2.putString("a", s2);
                intent.putExtras(bundle2);
                setResult(1, intent);
                finish();
                break;
        }
    }

    public void setMeJiGouKcglKctjSKfs() {
        MeJiGouKcglKctjSKfs.setTitle("授课方式");
        MeJiGouKcglKctjSKfs.setShow(true, false, false);
        MeJiGouKcglKctjSKfs.setBackClickListener(new View.OnClickListener() {
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
