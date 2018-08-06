package com.zhangju.xingquban.interestclassapp.refactor.me.fragment;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.fastlib.annotation.Bind;
import com.fastlib.annotation.ContentView;
import com.fastlib.annotation.Event;
import com.fastlib.annotation.LocalData;
import com.fastlib.app.FastFragment;
import com.zhangju.xingquban.R;
import com.zhangju.xingquban.interestclassapp.refactor.common.bean.EventRefresh;
import com.zhangju.xingquban.interestclassapp.refactor.common.widget.SlideTouchHelper;
import com.zhangju.xingquban.interestclassapp.refactor.me.activity.ResourceManageActivity;
import com.zhangju.xingquban.interestclassapp.refactor.me.adapter.ResourceManageAdapter;

/**
 * Created by sgfb on 2017/11/6.
 * 资源管理
 */
@ContentView(R.layout.frag_resource_manage)
public class ResourceManageFragment extends FastFragment{
    public static final String ARG_INT_IS_CHARGE="isCharge"; //0不收费 1收费

    @LocalData(ARG_INT_IS_CHARGE)
    int isCharge;
    @Bind(R.id.refresh)
    SwipeRefreshLayout mRefresh;
    @Bind(R.id.emptyView)
    View mEmptyView;
    @Bind(R.id.list)
    RecyclerView mList;
    ResourceManageAdapter mAdapter;

    public static ResourceManageFragment getInstance(boolean charge){
        Bundle bundle=new Bundle();
        ResourceManageFragment fragment=new ResourceManageFragment();

        bundle.putInt(ARG_INT_IS_CHARGE,charge?1:0);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    protected void alreadyPrepared() {
        mAdapter=new ResourceManageAdapter(getContext(),isCharge);
        mAdapter.registerAdapterDataObserver(new RecyclerView.AdapterDataObserver() {
            @Override
            public void onChanged() {
                super.onChanged();
                mEmptyView.setVisibility(mAdapter.getData()==null||mAdapter.getData().isEmpty()?View.VISIBLE:View.GONE);
                mRefresh.setVisibility(mAdapter.getData()==null||mAdapter.getData().isEmpty()?View.GONE:View.VISIBLE);
            }
        });
        mList.setAdapter(mAdapter);
        mList.addOnItemTouchListener(new SlideTouchHelper(mAdapter));
        mAdapter.setRefreshLayout(mRefresh);
        mRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                mAdapter.refresh();
            }
        });
        mAdapter.refresh();
    }

    @Bind(R.id.add)
    private void add(){
        ((ResourceManageActivity)getActivity()).showSelectAdd();
    }

    @Event
    private void eRefresh(EventRefresh event){
        if(event.getmTargetClass()==getClass()){
            mRefresh.setRefreshing(true);
            mRefresh.postDelayed(new Runnable() {
                @Override
                public void run() {
                    mAdapter.refresh();
                }
            },1500);
        }
    }
}
