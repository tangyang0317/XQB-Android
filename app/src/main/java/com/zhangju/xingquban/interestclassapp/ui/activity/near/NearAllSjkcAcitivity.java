package com.zhangju.xingquban.interestclassapp.ui.activity.near;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.fastlib.annotation.Bind;
import com.fastlib.annotation.ContentView;
import com.fastlib.annotation.LocalData;
import com.fastlib.app.FastActivity;
import com.fastlib.widget.TitleBar;
import com.zhangju.xingquban.R;
import com.zhangju.xingquban.interestclassapp.bean.HomeRecylerBean;
import com.zhangju.xingquban.interestclassapp.ui.fragment.home.HomeDataTeacherBean;
import com.zhangju.xingquban.interestclassapp.ui.fragment.home.sjkc.CurriculumXqActivity;
import com.zhangju.xingquban.interestclassapp.ui.fragment.home.sjkc.HomeDataSjkcAdapter;
import com.zhangju.xingquban.interestclassapp.ui.fragment.home.sjkc.HomeDataSjkcXq;


/**
 * Created by zgl on 2017/11/13.
 */
@ContentView(R.layout.act_allsjkc_active)
public class NearAllSjkcAcitivity extends FastActivity {
    public static final String ARG_STRING_DATA = "DATA";
    public static final String ARG_STRING_TEACHER = "TEACHER";
    @LocalData(ARG_STRING_DATA)
    HomeRecylerBean.AaDataBean data;
    @LocalData(ARG_STRING_TEACHER)
    HomeDataTeacherBean teacherInfo;

    @Bind(R.id.titleBar)
    TitleBar titleBar;
    @Bind(R.id.horizontalList)
    RecyclerView horizontalList;
    private HomeDataSjkcAdapter homeDataSjkcAdapter;
    private Bundle bundle;

    @Override
    protected void alreadyPrepared() {
        initview();
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        horizontalList.setLayoutManager(linearLayoutManager);
        homeDataSjkcAdapter = new HomeDataSjkcAdapter(this, teacherInfo);
        horizontalList.setAdapter(homeDataSjkcAdapter);
        homeDataSjkcAdapter.setOnItemClickListener(new HomeDataSjkcAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                HomeDataTeacherBean.AaDataBean.LessonsBean lessonsBea = teacherInfo.getAaData().get(0).getLessons().get(position);
                CurriculumXqActivity.lanuchActivity(NearAllSjkcAcitivity.this, lessonsBea.getId());
            }
        });
        homeDataSjkcAdapter.setMaxCount(Integer.MAX_VALUE);
    }

    private void initview() {
        titleBar.setOnLeftClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

}
