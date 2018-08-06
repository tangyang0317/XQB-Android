package com.zhangju.xingquban.interestclassapp.refactor.me.fragment;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.fastlib.annotation.Bind;
import com.fastlib.annotation.ContentView;
import com.fastlib.annotation.LocalData;
import com.fastlib.app.FastFragment;
import com.zhangju.xingquban.R;
import com.zhangju.xingquban.interestclassapp.refactor.me.adapter.MyTicketAdapter;

/**
 * Created by sgfb on 2017/11/15.
 * 我的票劵
 */
@ContentView(R.layout.frag_my_ticket)
public class MyTicketFragment extends FastFragment{
    public static final String ARG_INT_STATUS="status";

    @LocalData(ARG_INT_STATUS)
    int mStatus;
    @Bind(R.id.emptyView)
    View mEmptyView;
    @Bind(R.id.list)
    RecyclerView mList;
    @Bind(R.id.refresh)
    SwipeRefreshLayout mRefresh;
    MyTicketAdapter mAdapter;

    public static MyTicketFragment getInstance(int status){
        Bundle bundle=new Bundle();
        MyTicketFragment fragment=new MyTicketFragment();

        bundle.putInt(ARG_INT_STATUS,status);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    protected void alreadyPrepared(){
        mList.setAdapter(mAdapter=new MyTicketAdapter(getContext(),mStatus));
        mAdapter.registerAdapterDataObserver(new RecyclerView.AdapterDataObserver() {
            @Override
            public void onChanged() {
                super.onChanged();
                mEmptyView.setVisibility(mAdapter.getData()==null||mAdapter.getData().isEmpty()?View.VISIBLE:View.GONE);
            }
        });
        mAdapter.setRefreshLayout(mRefresh);
        mRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                mAdapter.refresh();
            }
        });
    }
}
