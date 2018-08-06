package com.zhangju.xingquban.interestclassapp.refactor.discover.fragment;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;

import com.fastlib.annotation.Bind;
import com.fastlib.annotation.ContentView;
import com.fastlib.annotation.LocalData;
import com.fastlib.app.FastFragment;
import com.zhangju.xingquban.R;
import com.zhangju.xingquban.interestclassapp.refactor.discover.adapter.ActiveAdapter;
import com.zhangju.xingquban.interestclassapp.refactor.me.bean.MeInterface;

/**
 * Created by Administrator on 2017/11/30.
 * 活动列表
 */
@ContentView(com.fastlib.R.layout.list)
public class ActiveListFragment extends FastFragment{
    public static final String ARG_STR_ID="id"; //-1为hot

    @LocalData(ARG_STR_ID)
    String mId;
    @Bind(R.id.refresh)
    SwipeRefreshLayout mRefresh;
    @Bind(R.id.list)
    RecyclerView mList;
    ActiveAdapter mAdapter;

    public static ActiveListFragment getInstance(String id){
        Bundle bundle=new Bundle();
        ActiveListFragment fragment=new ActiveListFragment();

        bundle.putString(ARG_STR_ID,id);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    protected void alreadyPrepared(){
        mList.setAdapter(mAdapter=new ActiveAdapter(getContext(),"-1".equals(mId)? MeInterface.POST_ACTIVE_LIST_HOT:MeInterface.POST_ACTIVE_LIST,mId));
        mAdapter.setRefreshLayout(mRefresh);
        mRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                mAdapter.refresh();
            }
        });
    }
}
