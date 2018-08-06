package com.zhangju.xingquban.interestclassapp.refactor.me.activity;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.util.Pair;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.HorizontalScrollView;

import com.fastlib.adapter.CommonFragmentViewPagerAdapter;
import com.fastlib.annotation.Bind;
import com.fastlib.annotation.ContentView;
import com.fastlib.app.FastActivity;
import com.fastlib.widget.TitleBar;
import com.zhangju.xingquban.R;
import com.zhangju.xingquban.interestclassapp.refactor.me.bean.ResponseOrder;
import com.zhangju.xingquban.interestclassapp.refactor.me.fragment.OrderListFragment;
import com.zhangju.xingquban.interestclassapp.util.RecyclerItemDecoration.DensityUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by sgfb on 2017/10/29.
 * 我的订单
 */
@ContentView(R.layout.act_order)
public class OrderActivity extends FastActivity{
    @Bind(R.id.titleBar)
    TitleBar mTitleBar;
    @Bind(R.id.tabLayout)
    TabLayout mTabLayout;
    @Bind(R.id.viewPager)
    ViewPager mViewPager;

    @Override
    protected void alreadyPrepared() {
        List<Pair<String,Fragment>> pages=new ArrayList<>();

        pages.add(Pair.<String, Fragment>create("全部订单",OrderListFragment.getInstance(ResponseOrder.STATUS_NONE,false)));
        pages.add(Pair.<String, Fragment>create("待付款",OrderListFragment.getInstance(ResponseOrder.STATUS_UNPAY,false)));
        pages.add(Pair.<String, Fragment>create("可使用",OrderListFragment.getInstance(ResponseOrder.STATUS_VALID,false)));
        CommonFragmentViewPagerAdapter viewPagerAdapter=new CommonFragmentViewPagerAdapter(getSupportFragmentManager(),pages);
        mViewPager.setAdapter(viewPagerAdapter);
        mTabLayout.setupWithViewPager(mViewPager);
        mTitleBar.setOnLeftClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
