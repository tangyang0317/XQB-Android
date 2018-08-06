package com.zhangju.xingquban.interestclassapp.refactor.me.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.View;

import com.fastlib.annotation.Bind;
import com.fastlib.annotation.ContentView;
import com.fastlib.annotation.LocalData;
import com.fastlib.app.FastActivity;
import com.fastlib.widget.TitleBar;
import com.zhangju.xingquban.R;
import com.zhangju.xingquban.interestclassapp.refactor.me.bean.ResponseCourseVideo;
import com.zhangju.xingquban.interestclassapp.refactor.me.fragment.CourseVideoFragment;

import java.util.ArrayList;

/**
 * Created by sgfb on 2017/11/21.
 * 课程视频列表（复用最新课程列表）
 */
@ContentView(R.layout.act_course_video_list)
public class CourseVideoListActivity extends FastActivity{
    public static final String ARG_SER_COURSE_VIDEO="CourseVideo";

    @LocalData(ARG_SER_COURSE_VIDEO)
    ArrayList<ResponseCourseVideo> mVideos;
    @Bind(R.id.titleBar)
    TitleBar mTitleBar;

    @Override
    protected void alreadyPrepared(){
        mTitleBar.setOnLeftClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        Fragment fragment=new CourseVideoFragment();
        Bundle bundle=new Bundle();
        bundle.putSerializable(CourseVideoFragment.ARG_SER_COURSE_VIDEOS,mVideos);
        fragment.setArguments(bundle);
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.main,fragment)
                .commit();
    }
}