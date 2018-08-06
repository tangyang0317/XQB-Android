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
import com.zhangju.xingquban.interestclassapp.adapter.near.NearAllVideoLessonsAdapter;
import com.zhangju.xingquban.interestclassapp.bean.HomeRecylerBean;
import com.zhangju.xingquban.interestclassapp.ui.fragment.home.HomeDataTeacherBean;
import com.zhangju.xingquban.interestclassapp.ui.fragment.home.spkc.HomeDataSpkcXq;


/**
 * Created by zgl on 2017/11/13.
 */
@ContentView(R.layout.act_allsjkc_active)
public class NearAllVideoLessonsAcitivity extends FastActivity {
    public static final String ARG_STRING_DATA ="DATA";
    public static final String ARG_STRING_TEACHER ="TEACHER";
    @LocalData(ARG_STRING_DATA)
    HomeRecylerBean.AaDataBean data;
    @LocalData(ARG_STRING_TEACHER)
    HomeDataTeacherBean teacherInfo;

    @Bind(R.id.titleBar)
    TitleBar titleBar;
    @Bind(R.id.horizontalList)
    RecyclerView horizontalList;
    private NearAllVideoLessonsAdapter homeDataSjkcAdapter;
    private Intent intent;
    private Bundle bundle;
    @Override
    protected void alreadyPrepared() {
        initview();
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false) ;
        horizontalList.setLayoutManager(linearLayoutManager);
        homeDataSjkcAdapter = new NearAllVideoLessonsAdapter(this, data);
        horizontalList.setAdapter(homeDataSjkcAdapter);
        homeDataSjkcAdapter.setOnItemClickListener(new NearAllVideoLessonsAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Intent intent = new Intent(NearAllVideoLessonsAcitivity.this, HomeDataSpkcXq.class);
                bundle=new Bundle();
                bundle.putSerializable(HomeDataSpkcXq.ARG_BEAN_SHIPINXQ,teacherInfo.getAaData().get(0).getVideoLesson().get(position));
                bundle.putString(HomeDataSpkcXq.ARG_STRING_NAME,teacherInfo.getAaData().get(0).getSigname());
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });
    }
    private void initview(){
        titleBar.setOnLeftClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

}
