package com.zhangju.xingquban.interestclassapp.refactor.me.fragment;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;

import com.fastlib.annotation.Bind;
import com.fastlib.annotation.ContentView;
import com.fastlib.annotation.Event;
import com.fastlib.annotation.LocalData;
import com.fastlib.app.FastFragment;
import com.zhangju.xingquban.R;
import com.zhangju.xingquban.interestclassapp.refactor.common.bean.EventRefresh;
import com.zhangju.xingquban.interestclassapp.refactor.me.adapter.BusinessOrderAdapter;

/**
 * Created by sgfb on 2017/11/11.
 * 商户工单列表
 */
@ContentView(R.layout.frag_business_order)
public class BusinessOrderFragment extends FastFragment{
    public static final String ARG_INT_STATUS="status";

    @LocalData(ARG_INT_STATUS)
    int mStatus;
    @Bind(R.id.refresh)
    SwipeRefreshLayout mRefresh;
    @Bind(R.id.list)
    RecyclerView mList;
    BusinessOrderAdapter mAdapter;

    public static BusinessOrderFragment getInstance(int status){
        Bundle bundle=new Bundle();
        BusinessOrderFragment fragment=new BusinessOrderFragment();
        bundle.putInt(ARG_INT_STATUS,status);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    protected void alreadyPrepared(){
        mList.setAdapter(mAdapter=new BusinessOrderAdapter(getContext(),mStatus));
        mRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                mAdapter.refresh();
            }
        });
        mAdapter.setRefreshLayout(mRefresh);
    }

    @Event
    private void eRefresh(EventRefresh event){
        if(event.getmTargetClass()==getClass()) {
            mRefresh.setRefreshing(true);
            mAdapter.refresh();
        }
    }
}