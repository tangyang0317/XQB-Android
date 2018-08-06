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
import com.zhangju.xingquban.interestclassapp.refactor.me.fragment.MyTicketFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by sgfb on 2017/11/15.
 * 我的票劵
 */
@ContentView(R.layout.act_my_tickets)
public class MyTicketActivity extends FastActivity{
    @Bind(R.id.titleBar)
    TitleBar mTitleBar;
    @Bind(R.id.tabLayout)
    TabLayout mTabLayout;
    @Bind(R.id.viewPager)
    ViewPager mViewPager;

    @Override
    protected void alreadyPrepared(){
        List<Pair<String,Fragment>> pages=new ArrayList<>();

        pages.add(Pair.<String, Fragment>create("全部", MyTicketFragment.getInstance(0)));
        pages.add(Pair.<String, Fragment>create("待参加", MyTicketFragment.getInstance(1)));
        pages.add(Pair.<String, Fragment>create("已完成", MyTicketFragment.getInstance(3)));
        pages.add(Pair.<String, Fragment>create("退票", MyTicketFragment.getInstance(-3)));
        mViewPager.setAdapter(new CommonFragmentViewPagerAdapter(getSupportFragmentManager(),pages));
        mTabLayout.setupWithViewPager(mViewPager);
        mTitleBar.setOnLeftClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}