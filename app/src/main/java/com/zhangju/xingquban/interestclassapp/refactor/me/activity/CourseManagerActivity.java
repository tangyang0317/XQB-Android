package com.zhangju.xingquban.interestclassapp.refactor.me.activity;

import android.support.v7.widget.RecyclerView;

import com.fastlib.annotation.Bind;
import com.fastlib.annotation.ContentView;
import com.fastlib.app.FastActivity;
import com.fastlib.widget.TitleBar;
import com.zhangju.xingquban.R;

/**
 * Created by sgfb on 2017/11/3.
 * 课程安排
 */
@ContentView(R.layout.act_course_manager)
public class CourseManagerActivity extends FastActivity{
    @Bind(R.id.titleBar)
    TitleBar mTitleBar;
    @Bind(R.id.courseGrid)
    RecyclerView mCourseGrid;

    @Override
    protected void alreadyPrepared(){

    }
}
