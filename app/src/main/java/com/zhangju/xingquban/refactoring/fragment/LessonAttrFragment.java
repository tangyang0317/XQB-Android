package com.zhangju.xingquban.refactoring.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.View;
import android.widget.TextView;

import com.zhangju.xingquban.R;
import com.zhangju.xingquban.interestclassapp.base.BaseFragment;
import com.zhangju.xingquban.interestclassapp.bean.near.CurriculumBean;
import com.zhangju.xingquban.interestclassapp.bean.near.LessonXqBean;

import java.util.Locale;

/**
 * 课程属性
 *
 * @packageName com.zhangju.xingquban.refactoring.fragment
 * @FileName LessonAttrFragment
 * @Author tangyang
 * @DATE 2018/8/24
 **/
public class LessonAttrFragment extends BaseFragment {

    private TextView isCantry_Boolean, methodType_scfs, tv_scdd, courseCount, courseLength;

    public static Fragment getInstance(LessonXqBean.AaDataBean aaDataBean) {
        LessonAttrFragment lessonAttrFragment = new LessonAttrFragment();
        Bundle bundle = new Bundle();
        bundle.putSerializable("lesson", aaDataBean);
        lessonAttrFragment.setArguments(bundle);
        return lessonAttrFragment;
    }

    private LessonXqBean.AaDataBean getLessonBean() {
        Bundle bundle = getArguments();
        LessonXqBean.AaDataBean lesson = (LessonXqBean.AaDataBean) bundle.getSerializable("lesson");
        return lesson;
    }

    @Override
    public void initData() {
        isCantry_Boolean.setText(getLessonBean().isIsCantry() ? "可以试听" : "不可以试听");
        /*授课方式*/
        switch (getLessonBean().getMethodType()) {
            case 1:
                methodType_scfs.setText("学生上门");
                break;
            case 2:
                methodType_scfs.setText("家教上门");
                break;
            case 3:
                methodType_scfs.setText("协商地点");
                break;
        }
        tv_scdd.setText(getLessonBean().getMethodType() == 3 ? "协商地点" : getLessonBean().getRegion());
        courseCount.setText(String.format(Locale.getDefault(), "%d节", getLessonBean().getCourses()));
        courseLength.setText(String.format(Locale.getDefault(), "%s分钟", getLessonBean().getTimelength()));
    }

    @Override
    public int getMyLayout() {
        return R.layout.act_lesson_attr;
    }

    @Override
    public void initView() {
        isCantry_Boolean = view.findViewById(R.id.isCantry_Boolean);
        methodType_scfs = view.findViewById(R.id.methodType_scfs);
        tv_scdd = view.findViewById(R.id.tv_scdd);
        courseCount = view.findViewById(R.id.courseCount);
        courseLength = view.findViewById(R.id.courseLength);
    }

    @Override
    public void initListener() {

    }

    @Override
    public void onClick(View view) {

    }
}
