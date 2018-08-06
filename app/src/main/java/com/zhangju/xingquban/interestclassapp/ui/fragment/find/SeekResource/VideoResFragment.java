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
import com.zhangju.xingquban.interestclassapp.adapter.VideoResourceAdapter;
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
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * 项目名称：InterestClassApp
 * Created by Dr. Zhu on 2017/7/4.
 */

public class VideoResFragment
        extends BaseFragment
        implements SwipeRefreshAdapterView.OnListLoadListener, SwipeRefreshLayout.OnRefreshListener {
    @BindView(R.id.swipe_refresh_audio)
    SwipeRefreshRecyclerView swipeRefreshAudio;
    Unbinder unbinder;

    private List<ResouecesAll.AaDataBean> mVideoList = new ArrayList<>();//数据
    private VideoResourceAdapter videoAdapter;

    private int pageIndex = 0;//
    private int total     = 0;//总页数

    boolean isRefresh;
    String mFilter = "";
    private Subscription mSubscribe;

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

    @Override
    public void initData() {
    }

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
                    mVideoList.clear();
                }
                if (resourcesAudio.getAaData() != null && resourcesAudio.getAaData().size() > 0) {
                    mVideoList.addAll(resourcesAudio.getAaData());
                }

                videoAdapter.notifyDataSetChanged();
                setSwipe();
            }

        }
    };

    @Override
    public int getMyLayout() {
        return R.layout.allresource_fragment;
    }

    @Override
    public void initView() {
        setVideoResourceAdapter();

    }

    private void setVideoResourceAdapter() {
        swipeRefreshAudio.setOnListLoadListener(this);
        swipeRefreshAudio.setOnRefreshListener(this);
        swipeRefreshAudio.autoRefresh();

        videoAdapter = new VideoResourceAdapter(getActivity(), mVideoList);
        swipeRefreshAudio.setLayoutManager(new LinearLayoutManager(getActivity()));
        swipeRefreshAudio.setAdapter(videoAdapter);

        videoAdapter.setOnListItemClickListener(new OnListItemClickListener() {
            @Override
            public void onItemClickListener(int position, View v) {
                String id = mVideoList.get(position).getId();
                String fileUrl = mVideoList.get(position).getVideoList().get(0).getFileUrl();

                Intent intent = new Intent(getActivity(), AudioDetailActivity.class);

                intent.putExtra("types", "video");
                intent.putExtra("resId", id);
                startActivity(intent);
            }
        });
        videoAdapter.setMyOnClickListener(new AllTypeResourceAdapter.MyOnClickListener() {
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
    public void onListLoad() {
        pageIndex++;
        if (pageIndex + 1 > total) {
            swipeRefreshAudio.setEnabledLoad(false);
            swipeRefreshAudio.setLoading(false);
            return;
        }
        sendTestRequest();
    }

    @Override
    public void onRefresh() {
        pageIndex = 0;
        sendTestRequest();
    }

    private void setSwipe() {
        //        try {
        if (swipeRefreshAudio != null) {
            swipeRefreshAudio.setRefreshing(false);
            swipeRefreshAudio.setLoading(false);
            swipeRefreshAudio.setEnabledLoad(true);
        }

        //        } catch (Exception ex) {
        //            ex.printStackTrace();
        //
        //        }
    }

    @Override
    public void initListener() {

    }

    @Override
    public void onClick(View v) {

    }

    public void sendTestRequest() {
        addSubscriber(NetWork.getReources().getReourceslist(pageIndex + "", "10", "video", mFilter)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer));
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        unbinder = ButterKnife.bind(this, rootView);
        EventObserver.getInstance().subscribe(getContext(), this);
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
