package com.zhangju.xingquban.interestclassapp.refactor.me.activity;

import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.fastlib.annotation.Bind;
import com.fastlib.annotation.ContentView;
import com.fastlib.annotation.LocalData;
import com.fastlib.app.FastActivity;
import com.fastlib.widget.TitleBar;
import com.zhangju.xingquban.R;
import com.zhangju.xingquban.interestclassapp.refactor.common.widget.SlideTouchHelper;
import com.zhangju.xingquban.interestclassapp.refactor.me.adapter.CustomCourseAdapter;
import com.zhangju.xingquban.interestclassapp.refactor.me.bean.ResponseOrder;

import java.util.ArrayList;

/**
 * Created by sgfb on 2017/11/17.
 * 课程列表
 */
@ContentView(R.layout.act_course_list)
public class CourseListActivity extends FastActivity{
    public static final String ARG_SER_COURSE_LIST="courseList";

    @LocalData(ARG_SER_COURSE_LIST)
    ArrayList<ResponseOrder.Lesson> mCourseList;
    @Bind(R.id.titleBar)
    TitleBar mTitleBar;
    @Bind(R.id.list)
    RecyclerView mList;
    CustomCourseAdapter mAdapter;

    @Override
    protected void alreadyPrepared(){
        mList.setAdapter(mAdapter=new CustomCourseAdapter(this,mCourseList));
        mList.addOnItemTouchListener(new SlideTouchHelper(mAdapter));
        mTitleBar.setOnLeftClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                finish();
            }
        });
        Intent intent=new Intent();
        intent.putExtra(ARG_SER_COURSE_LIST,mCourseList);
        setResult(RESULT_OK,intent);
    }
}
