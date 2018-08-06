package com.zhangju.xingquban.interestclassapp.refactor.me.fragment;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;

import com.fastlib.annotation.Bind;
import com.fastlib.annotation.ContentView;
import com.fastlib.app.FastFragment;
import com.zhangju.xingquban.R;
import com.zhangju.xingquban.interestclassapp.refactor.me.adapter.OrderAdapter;

/**
 * Created by sgfb on 2017/10/30.
 * 订单列表
 */
@ContentView(com.fastlib.R.layout.list)
public class OrderListFragment extends FastFragment{
    public static final String ARG_INT_STATUS="status";
    public static final String ARG_BOOL_BUSINESS="isBusiness";

    @Bind(com.fastlib.R.id.refresh)
    SwipeRefreshLayout mRefresh;
    @Bind(com.fastlib.R.id.list)
    RecyclerView mList;
    OrderAdapter mAdapter;

    public static OrderListFragment getInstance(int status, boolean isBusiness){
        OrderListFragment fragment=new OrderListFragment();
        Bundle bundle=new Bundle();

        bundle.putInt(ARG_INT_STATUS,status);
        bundle.putBoolean(ARG_BOOL_BUSINESS,isBusiness);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    protected void alreadyPrepared(){
        mList.setAdapter(mAdapter=new OrderAdapter(getContext(),getArguments().getInt(ARG_INT_STATUS),getArguments().getBoolean(ARG_BOOL_BUSINESS,false)?1:0));
        mAdapter.setRefreshLayout(mRefresh);
        mRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                mAdapter.refresh();
            }
        });
    }
}
