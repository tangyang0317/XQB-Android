package com.zhangju.xingquban.interestclassapp.refactor.me.fragment;

import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;

import com.fastlib.annotation.Bind;
import com.fastlib.annotation.ContentView;
import com.fastlib.annotation.Event;
import com.fastlib.app.FastFragment;
import com.fastlib.net.Request;
import com.fastlib.net.SimpleListener;
import com.zhangju.xingquban.interestclassapp.refactor.common.bean.Response;
import com.zhangju.xingquban.interestclassapp.refactor.me.adapter.StoreCollectionAdapter;
import com.zhangju.xingquban.interestclassapp.refactor.me.bean.EventCollectionDeleteStatusChanged;
import com.zhangju.xingquban.interestclassapp.refactor.me.bean.EventDeleteCollection;
import com.zhangju.xingquban.interestclassapp.refactor.me.bean.MeInterface;
import com.zhangju.xingquban.interestclassapp.refactor.me.bean.ResponseCollection;

import java.util.List;

/**
 * Created by sgfb on 2017/10/30.
 * 老师机构列表页
 */
@ContentView(com.fastlib.R.layout.list)
public class StoreCollectionListFragment extends FastFragment{
    @Bind(com.fastlib.R.id.refresh)
    SwipeRefreshLayout mRefresh;
    @Bind(com.fastlib.R.id.list)
    RecyclerView mList;
    StoreCollectionAdapter mAdapter;

    @Override
    protected void alreadyPrepared(){
        mList.setAdapter(mAdapter=new StoreCollectionAdapter(getContext()));
        mAdapter.setRefreshLayout(mRefresh);
        mRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                mAdapter.refresh();
            }
        });
    }

    @Event
    private void changeDeleteStatus(EventCollectionDeleteStatusChanged event){
        mAdapter.setShowDelete(event.isShowDelete());
    }

    @Event
    private void deleteSelectedCollection(EventDeleteCollection event){
        mAdapter.setShowDelete(false);
        List<ResponseCollection> list=mAdapter.getData();

        if(list==null) return;
        for(ResponseCollection collection:list){
            if(collection.isDeleteChecked){
                Request request=Request.obtain(MeInterface.POST_COLLECTION_DELETE);
                request.put("id",collection.id);
                request.setListener(new SimpleListener<Response>(){

                    @Override
                    public void onResponseListener(Request r, Response result) {
                        if(result.success) mAdapter.refresh();
                    }
                });
                net(request);
            }
        }
    }
}
