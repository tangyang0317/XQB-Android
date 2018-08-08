package com.zhangju.xingquban.interestclassapp.ui.fragment.find.SeekResource;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.fastlib.annotation.Event;
import com.fastlib.app.EventObserver;
import com.zhangju.xingquban.R;
import com.zhangju.xingquban.interestclassapp.RetrofitInterface.NetWork;
import com.zhangju.xingquban.interestclassapp.adapter.AllTypeResourceAdapter;
import com.zhangju.xingquban.interestclassapp.adapter.baseadpter.OnListItemClickListener;
import com.zhangju.xingquban.interestclassapp.base.BaseFragment;
import com.zhangju.xingquban.interestclassapp.bean.ResouecesAll;
import com.zhangju.xingquban.interestclassapp.refactor.me.activity.LoginActivity;
import com.zhangju.xingquban.interestclassapp.refactor.user.UserManager;
import com.zhangju.xingquban.interestclassapp.swiperefrsh.SwipeRefreshAdapterView;
import com.zhangju.xingquban.interestclassapp.swiperefrsh.SwipeRefreshRecyclerView;
import com.zhangju.xingquban.interestclassapp.ui.fragment.find.EventResourceFilterContent;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * 资源全部
 */

public class AllResourceFragment extends BaseFragment implements SwipeRefreshLayout.OnRefreshListener, SwipeRefreshAdapterView.OnListLoadListener {
    @BindView(R.id.swipe_refresh_audio)
    SwipeRefreshRecyclerView swipeRefreshAudio;
    Unbinder unbinder;
    private List<ResouecesAll.AaDataBean> mResourceList = new ArrayList<>();
    private AllTypeResourceAdapter allAdapter;
    private int pageIndex = 0;//
    private int total = 0;//总页数
    boolean isRefresh;
    String mFilter = "";

    @Override
    public void initData() {

    }

   /* @Override
    public void onResume() {
        super.onResume();
        if (isRefresh) {
            swipeRefreshAudio.autoRefresh();
            isRefresh = false;
        }
    }

    @Override
    public void onPause() {
        super.onPause();
        isRefresh = true;
    }*/

    Observer<ResouecesAll> observer = new Observer<ResouecesAll>() {
        @Override
        public void onCompleted() {

        }

        @Override
        public void onError(Throwable e) {
            Log.e("xqb-error", String.valueOf(e));
            setSwipe();
        }

        @Override
        public void onNext(ResouecesAll resourcesAudio) {

            if (resourcesAudio.isSuccess()) {
                total = resourcesAudio.getTotal();
                if (pageIndex == 0) {
                    mResourceList.clear();
                }

                if (resourcesAudio.getAaData() != null && resourcesAudio.getAaData().size() > 0) {
                    mResourceList.addAll(resourcesAudio.getAaData());
                }
                setSwipe();
                allAdapter.notifyDataSetChanged();
            }
        }
    };


    private void setSwipe() {
        if (swipeRefreshAudio != null) {
            swipeRefreshAudio.setRefreshing(false);
            swipeRefreshAudio.setLoading(false);
            swipeRefreshAudio.setEnabledLoad(true);
        }
    }


    @Override
    public int getMyLayout() {
        return R.layout.allresource_fragment;
    }

    @Override
    public void initView() {
        EventObserver.getInstance().subscribe(getContext(), this);
        setResourceAllAdapter();
    }

    private void setResourceAllAdapter() {
        swipeRefreshAudio.setOnRefreshListener(this);
        swipeRefreshAudio.setOnListLoadListener(this);
        swipeRefreshAudio.autoRefresh();

        allAdapter = new AllTypeResourceAdapter(getActivity(), mResourceList);
        swipeRefreshAudio.setLayoutManager(new LinearLayoutManager(getActivity()));
        swipeRefreshAudio.setAdapter(allAdapter);

        allAdapter.setOnListItemClickListener(new OnListItemClickListener() {
            @Override
            public void onItemClickListener(int position, View v) {
                String types = mResourceList.get(position).getTypes() == null ? "" : mResourceList.get(position).getTypes();//类型判断
                String id = mResourceList.get(position).getId();
                if (types.equals("video")) {

                    String fileUrl = mResourceList.get(position).getVideoList().get(0).getFileUrl();
                    Intent intent = new Intent(getActivity(), AudioDetailActivity.class);

                    intent.putExtra("types", "video");
                    intent.putExtra("resId", id);
                    startActivity(intent);

                } else if (types.equals("picture")) {

                    Intent intent = new Intent(getActivity(), PicDetailActivity.class);
                    intent.putExtra("id", id);
                    startActivity(intent);
                } else if (types.equals("article")) {
                    Intent intent = new Intent(getActivity(), NewsDetailActivity.class);
                    intent.putExtra("id", id);
                    startActivity(intent);
                } else if (types.equals("audio")) {
                    Intent intent = new Intent(getActivity(), AudioDetailActivity.class);
                    intent.putExtra("types", "audio");
                    intent.putExtra("resId", id);
                    startActivity(intent);
                }


            }
        });

        allAdapter.setMyOnClickListener(new AllTypeResourceAdapter.MyOnClickListener() {
            @Override
            public void onChildClickListener(int pos, String id) {
                if (!UserManager.getInstance().isLogin()) {
                    startActivity(new Intent(getContext(), LoginActivity.class));
                } else {
                    Intent intent = new Intent(getContext(), ResSendActivity.class);
                    intent.putExtra("id", id);
                    startActivity(intent);
                }
            }
        });
    }

    @Override
    public void onRefresh() {
        pageIndex = 0;
        getResourceAll();
    }

    @Override
    public void onListLoad() {
        pageIndex++;
        if (pageIndex + 1 > total) {
            swipeRefreshAudio.setLoading(false);
            swipeRefreshAudio.setEnabledLoad(false);
            return;

        }
        getResourceAll();
    }

    @Override
    public void initListener() {

    }

    @Override
    public void onClick(View v) {

    }

    //获取所有资源
    public void getResourceAll() {
        addSubscriber(NetWork.getReources().getReourceslist(pageIndex + "", "10", "", mFilter)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer));
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        unbinder = ButterKnife.bind(this, rootView);
        return rootView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
        EventObserver.getInstance().unsubscribe(getContext(), this);
    }

    @Event
    private void eFilterContent(EventResourceFilterContent event) {
        mFilter = event.getmFilter();
        swipeRefreshAudio.autoRefresh();
    }
}