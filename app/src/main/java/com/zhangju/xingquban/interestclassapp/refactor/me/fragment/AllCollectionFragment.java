package com.zhangju.xingquban.interestclassapp.refactor.me.fragment;

import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;

import com.fastlib.adapter.MultiTypeAdapter;
import com.fastlib.annotation.Bind;
import com.fastlib.annotation.ContentView;
import com.fastlib.annotation.Event;
import com.fastlib.app.FastFragment;
import com.fastlib.app.task.Action;
import com.fastlib.app.task.NetAction;
import com.fastlib.app.task.NoReturnAction;
import com.fastlib.app.task.Task;
import com.fastlib.app.task.ThreadType;
import com.fastlib.net.Request;
import com.fastlib.net.SimpleListener;
import com.zhangju.xingquban.interestclassapp.refactor.common.bean.Response;
import com.zhangju.xingquban.interestclassapp.refactor.me.adapter.AllCollectionAdapter;
import com.zhangju.xingquban.interestclassapp.refactor.me.bean.EventCollectionDeleteStatusChanged;
import com.zhangju.xingquban.interestclassapp.refactor.me.bean.EventDeleteCollection;
import com.zhangju.xingquban.interestclassapp.refactor.me.bean.MeInterface;
import com.zhangju.xingquban.interestclassapp.refactor.me.bean.ResponseCollection;

import java.util.Collections;
import java.util.List;

/**
 * Created by sgfb on 2017/10/31.
 * 所有类型收藏
 */
@ContentView(com.fastlib.R.layout.list)
public class AllCollectionFragment extends FastFragment{
    @Bind(com.fastlib.R.id.refresh)
    SwipeRefreshLayout mRefresh;
    @Bind(com.fastlib.R.id.list)
    RecyclerView mList;
    AllCollectionAdapter mAdapter;

    @Override
    protected void alreadyPrepared() {
        mList.setAdapter(mAdapter=new AllCollectionAdapter(getContext()));
        mAdapter.setRefresh(mRefresh);
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
        List<MultiTypeAdapter.RecyclerItem> list=mAdapter.getRecyclerItem();

        if(list==null) return;
        startTask(Task.beginCycle(list)
                .filter(new Action<MultiTypeAdapter.RecyclerItem, Boolean>() {
                    @Override
                    protected Boolean execute(MultiTypeAdapter.RecyclerItem param) throws Throwable{
                        return param.mData instanceof ResponseCollection&&((ResponseCollection)param.mData).isDeleteChecked;
                    }
                })
                .next(new Action<MultiTypeAdapter.RecyclerItem,ResponseCollection>(){

                    @Override
                    protected ResponseCollection execute(MultiTypeAdapter.RecyclerItem param) throws Throwable {
                        return (ResponseCollection) param.mData;
                    }
                })
                .next(new Action<ResponseCollection,Request>(){

                    @Override
                    protected Request execute(ResponseCollection param) throws Throwable {
                        return Request.obtain(MeInterface.POST_COLLECTION_DELETE).put("id",param.id);
                    }
                })
                .next(new NetAction<Response>(){

                    @Override
                    protected void executeAdapt(Response response, Request request) {
                        if(!response.success) stopTask();
                    }
                })
                .again(new NoReturnAction<List<Response>>() {
                    @Override
                    public void executeAdapt(List<Response> param) {
                        mAdapter.refresh();
                    }
                }, ThreadType.MAIN));
    }
}