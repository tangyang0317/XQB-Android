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
import com.zhangju.xingquban.interestclassapp.ui.fragment.home.wydp.HomeDataCommentAdapter;
import com.zhangju.xingquban.interestclassapp.ui.fragment.home.wydp.HomeDataWdplXq;


/**
 * Created by zgl on 2017/11/13.
 */
@ContentView(R.layout.act_nearallcomment_active)
public class NearAllCommentActivity extends FastActivity{
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
    private HomeDataCommentAdapter homeDataCommentAdapter;
    private Intent intent;
    private Bundle bundle;
    @Override
    protected void alreadyPrepared() {

        initview();
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(NearAllCommentActivity.this) {
            @Override
            public boolean canScrollVertically() {
                return false;
            }
        };
        horizontalList.setLayoutManager(linearLayoutManager);
        homeDataCommentAdapter = new HomeDataCommentAdapter(NearAllCommentActivity.this, teacherInfo);
        horizontalList.setAdapter(homeDataCommentAdapter);
        homeDataCommentAdapter.setOnItemClickListener(new HomeDataCommentAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                intent = new Intent(NearAllCommentActivity.this, HomeDataWdplXq.class);
                bundle=new Bundle();
                bundle.putSerializable(HomeDataWdplXq.ARG_STRING_COMMENTID,teacherInfo.getAaData().get(0).getCommentsList().get(position));
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
