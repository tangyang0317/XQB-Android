package com.zhangju.xingquban.interestclassapp.refactor.me.activity;

import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.fastlib.annotation.Bind;
import com.fastlib.annotation.ContentView;
import com.fastlib.annotation.Event;
import com.fastlib.app.FastActivity;
import com.fastlib.widget.TitleBar;
import com.zhangju.xingquban.R;
import com.zhangju.xingquban.interestclassapp.refactor.common.bean.EventRefresh;
import com.zhangju.xingquban.interestclassapp.refactor.me.activity.publish_active.PublishActiveActivity;
import com.zhangju.xingquban.interestclassapp.refactor.me.adapter.ActivePublishedAdapter;

/**
 * Created by sgfb on 2017/11/15.
 * 活动模块我的发布（教师/机构端）
 */
@ContentView(R.layout.act_active_published)
public class ActivePublishedActivity extends FastActivity{
    @Bind(R.id.titleBar)
    TitleBar mTitleBar;
    @Bind(R.id.refresh)
    SwipeRefreshLayout mRefresh;
    @Bind(R.id.list)
    RecyclerView mList;
    @Bind(R.id.emptyView)
    View mEmptyView;
    ActivePublishedAdapter mAdapter;

    @Override
    protected void alreadyPrepared(){
        mList.setAdapter(mAdapter=new ActivePublishedAdapter(this));
        mAdapter.setRefreshLayout(mRefresh);
        mRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                mAdapter.refresh();
            }
        });
        mTitleBar.setOnRightClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(PublishActiveActivity.class);
            }
        });
        mTitleBar.setOnLeftClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        mAdapter.registerAdapterDataObserver(new RecyclerView.AdapterDataObserver() {
            @Override
            public void onChanged() {
                super.onChanged();
                mEmptyView.setVisibility(mAdapter.getData()!=null&&!mAdapter.getData().isEmpty()?View.GONE:View.VISIBLE);
            }
        });
    }

    @Bind(R.id.add)
    private void add(){
        startActivity(PublishActiveActivity.class);
    }

    @Event
    private void refreshList(EventRefresh event){
        if(event.getmTargetClass()==getClass()){
            mAdapter.refresh();
        }
    }
}