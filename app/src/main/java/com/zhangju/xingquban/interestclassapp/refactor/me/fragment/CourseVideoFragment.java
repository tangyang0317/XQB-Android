package com.zhangju.xingquban.interestclassapp.refactor.me.fragment;

import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;

import com.fastlib.annotation.Bind;
import com.fastlib.annotation.ContentView;
import com.fastlib.annotation.Event;
import com.fastlib.annotation.LocalData;
import com.fastlib.app.FastFragment;
import com.zhangju.xingquban.R;
import com.zhangju.xingquban.interestclassapp.refactor.common.bean.EventRefresh;
import com.zhangju.xingquban.interestclassapp.refactor.me.activity.CourseVideoActivity;
import com.zhangju.xingquban.interestclassapp.refactor.me.adapter.CourseVideoAdapter;
import com.zhangju.xingquban.interestclassapp.refactor.me.adapter.LatestCourseVideoAdapter;
import com.zhangju.xingquban.interestclassapp.refactor.me.bean.ResponseCourseVideo;

import java.util.List;

/**
 * Created by sgfb on 2017/11/21.
 * 我的视频列表
 */
@ContentView(com.fastlib.R.layout.list)
public class CourseVideoFragment extends FastFragment{
    public static final String ARG_SER_COURSE_VIDEOS="courseVideos"; //视频列表 如果不存在默认为最近视频列表

    @LocalData(ARG_SER_COURSE_VIDEOS)
    List<ResponseCourseVideo> mVideos;
    @Bind(R.id.refresh)
    SwipeRefreshLayout mRefresh;
    @Bind(R.id.list)
    RecyclerView mList;
    LatestCourseVideoAdapter mLatestAdapter;
    CourseVideoAdapter mAdapter;

    @Override
    protected void alreadyPrepared() {
        if(mVideos==null){
            mList.setAdapter(mLatestAdapter =new LatestCourseVideoAdapter(getContext()));
            mLatestAdapter.setRefreshLayout(mRefresh);
            mRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
                @Override
                public void onRefresh() {
                    mLatestAdapter.refresh();
                }
            });
        }
        else{
            mList.setAdapter(mAdapter=new CourseVideoAdapter(getContext(),mVideos));
            mRefresh.setEnabled(false);
        }
    }

    @Event
    private void eRefreshList(EventRefresh event){
        if(event.getmTargetClass()==CourseVideoActivity.class&&mLatestAdapter!=null)
            mLatestAdapter.refresh();
    }
}