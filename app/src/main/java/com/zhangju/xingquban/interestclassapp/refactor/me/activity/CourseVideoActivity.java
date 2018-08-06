package com.zhangju.xingquban.interestclassapp.refactor.me.activity;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.util.Pair;
import android.support.v4.view.ViewPager;
import android.view.View;

import com.fastlib.adapter.CommonFragmentViewPagerAdapter;
import com.fastlib.annotation.Bind;
import com.fastlib.annotation.ContentView;
import com.fastlib.app.FastActivity;
import com.fastlib.widget.TitleBar;
import com.zhangju.xingquban.R;
import com.zhangju.xingquban.interestclassapp.refactor.me.fragment.CourseVideoFolderFragment;
import com.zhangju.xingquban.interestclassapp.refactor.me.fragment.CourseVideoFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by sgfb on 2017/11/21.
 * 机构管理视频
 */
@ContentView(R.layout.act_course_video)
public class CourseVideoActivity extends FastActivity{
    @Bind(R.id.titleBar)
    TitleBar mTitleBar;
    @Bind(R.id.tabLayout)
    TabLayout mTabLayout;
    @Bind(R.id.viewPager)
    ViewPager mViewPager;

    @Override
    protected void alreadyPrepared(){
        List<Pair<String,Fragment>> pages=new ArrayList<>();
        pages.add(Pair.<String, Fragment>create("最近",new CourseVideoFragment()));
        pages.add(Pair.<String, Fragment>create("视频夹",new CourseVideoFolderFragment()));
        mViewPager.setAdapter(new CommonFragmentViewPagerAdapter(getSupportFragmentManager(),pages));
        mTabLayout.setupWithViewPager(mViewPager);
        mTitleBar.setOnLeftClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        mTitleBar.setOnRightClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(PublishCourseVideoActivity.class);
            }
        });
    }
}