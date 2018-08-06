package com.zhangju.xingquban.interestclassapp.ui.fragment.me.user;

import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import com.zhangju.xingquban.R;
import com.zhangju.xingquban.interestclassapp.base.BaseActivity;
import com.zhangju.xingquban.interestclassapp.ui.widget.PublicHead;

import butterknife.BindView;
import butterknife.ButterKnife;

//教师认证
public class MeUserTeacher extends BaseActivity {

    @BindView(R.id.me_user_teacher)
    PublicHead meUserTeacher;
    @BindView(R.id.me_user_teacherAdd)
    LinearLayout meUserTeacherAdd;

    @Override
    public int getLayout() {
        return R.layout.activity_me_user_teacher;
    }

    @Override
    public void initView() {
        setMeUserOrganization();
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

    public void setMeUserOrganization() {
        meUserTeacher.setTitle("老师认证");
        meUserTeacher.setShow(true, false, true);
        meUserTeacher.setRightTitle("上传");
        meUserTeacher.setRightTextColor(R.color.color_main);
        meUserTeacher.setBackClickListener(new View.OnClickListener() {
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
