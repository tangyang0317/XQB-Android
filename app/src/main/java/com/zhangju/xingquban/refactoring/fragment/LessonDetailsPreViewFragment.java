package com.zhangju.xingquban.refactoring.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.zhangju.xingquban.R;
import com.zhangju.xingquban.interestclassapp.base.BaseFragment;
import com.zhangju.xingquban.interestclassapp.bean.near.LessonXqBean;
import com.zhangju.xingquban.interestclassapp.refactor.me.adapter.OrgProfileDisplayAdapter;
import com.zhangju.xingquban.interestclassapp.refactor.me.bean.ResponseOrgProfile;

import java.util.HashMap;

/**
 * 课程详情
 *
 * @packageName com.zhangju.xingquban.refactoring.fragment
 * @FileName LessonDetailsFragment
 * @Author tangyang
 * @DATE 2018/8/24
 **/
public class LessonDetailsPreViewFragment extends BaseFragment {

    private RecyclerView coreTextRecycleView;
    OrgProfileDisplayAdapter mDisplayAdapter;

    public static Fragment getInstance(HashMap<String, String> aaDataBean) {
        LessonDetailsPreViewFragment lessonAttrFragment = new LessonDetailsPreViewFragment();
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
        mDisplayAdapter = new OrgProfileDisplayAdapter(getActivity());
        ResponseOrgProfile responseOrgProfile = new ResponseOrgProfile();
        responseOrgProfile.setIntro(getLessonBean().get("summary"));
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
