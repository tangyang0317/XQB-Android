package com.zhangju.xingquban.refactoring.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.zhangju.xingquban.R;
import com.zhangju.xingquban.interestclassapp.base.BaseFragment;
import com.zhangju.xingquban.interestclassapp.bean.near.CurriculumBean;
import com.zhangju.xingquban.interestclassapp.refactor.me.adapter.OrgProfileDisplayAdapter;
import com.zhangju.xingquban.interestclassapp.refactor.me.bean.ResponseOrgProfile;

/**
 * 课程详情
 *
 * @packageName com.zhangju.xingquban.refactoring.fragment
 * @FileName LessonDetailsFragment
 * @Author tangyang
 * @DATE 2018/8/24
 **/
public class LessonDetailsFragment extends BaseFragment {

    private RecyclerView coreTextRecycleView;
    OrgProfileDisplayAdapter mDisplayAdapter;

    public static Fragment getInstance(CurriculumBean.AaDataBean aaDataBean) {
        LessonDetailsFragment lessonAttrFragment = new LessonDetailsFragment();
        Bundle bundle = new Bundle();
        bundle.putSerializable("lesson", aaDataBean);
        lessonAttrFragment.setArguments(bundle);
        return lessonAttrFragment;
    }

    private CurriculumBean.AaDataBean getLessonBean() {
        Bundle bundle = getArguments();
        CurriculumBean.AaDataBean lesson = (CurriculumBean.AaDataBean) bundle.getSerializable("lesson");
        return lesson;
    }

    @Override
    public void initData() {
        mDisplayAdapter = new OrgProfileDisplayAdapter(getActivity());
        ResponseOrgProfile responseOrgProfile = new ResponseOrgProfile();
        responseOrgProfile.setIntro(getLessonBean().getSummary());
        responseOrgProfile.setId("0");
        mDisplayAdapter.setData(responseOrgProfile);
        coreTextRecycleView.setAdapter(mDisplayAdapter);
    }

    @Override
    public int getMyLayout() {
        return R.layout.act_lesson_details;
    }

    @Override
    public void initView() {
        coreTextRecycleView = view.findViewById(R.id.coreTextRecycleView);
    }

    @Override
    public void initListener() {

    }

    @Override
    public void onClick(View view) {

    }
}
