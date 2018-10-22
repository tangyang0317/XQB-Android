package com.zhangju.xingquban.refactoring.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.View;
import android.widget.TextView;

import com.zhangju.xingquban.R;
import com.zhangju.xingquban.interestclassapp.base.BaseFragment;
import com.zhangju.xingquban.interestclassapp.bean.near.LessonXqBean;

import java.util.HashMap;
import java.util.Locale;

/**
 * 课程属性
 *
 * @packageName com.zhangju.xingquban.refactoring.fragment
 * @FileName LessonAttrFragment
 * @Author tangyang
 * @DATE 2018/8/24
 **/
public class LessonAttrPreViewFragment extends BaseFragment {

    private TextView isCantry_Boolean, methodType_scfs, tv_scdd, courseCount, courseLength;

    public static Fragment getInstance(HashMap<String, String> aaDataBean) {
        LessonAttrPreViewFragment lessonAttrFragment = new LessonAttrPreViewFragment();
        Bundle bundle = new Bundle();
        bundle.putSerializable("lesson", aaDataBean);
        lessonAttrFragment.setArguments(bundle);
        return lessonAttrFragment;
    }

    private HashMap<String, String> getLessonBean() {
        Bundle bundle = getArguments();
        HashMap<String, String> lesson = (HashMap<String, String>) bundle.getSerializable("lesson");
        return lesson;
    }

    @Override
    public void initData() {
        isCantry_Boolean.setText(getLessonBean().get("isCantry").equals("1") ? "可以试听" : "不可以试听");
        /*授课方式*/
        switch (getLessonBean().get("methodType")) {
            case "1":
                methodType_scfs.setText("学生上门");
                break;
            case "2":
                methodType_scfs.setText("家教上门");
                break;
            case "3":
                methodType_scfs.setText("协商地点");
                break;
        }
        tv_scdd.setText(getLessonBean().get("methodType").equals("3") ? "协商地点" : getLessonBean().get("region"));
        courseCount.setText(String.valueOf(getLessonBean().get("courses")) + "节");
        courseLength.setText(String.valueOf(getLessonBean().get("timelength")) + "分钟");
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
