package com.zhangju.xingquban.interestclassapp.ui.fragment.find.SeekResource;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.fastlib.annotation.ContentView;
import com.fastlib.annotation.Event;
import com.fastlib.app.EventObserver;
import com.fastlib.app.FastFragment;
import com.fastlib.net.Request;
import com.fastlib.net.SimpleListener;
import com.zhangju.xingquban.R;
import com.zhangju.xingquban.interestclassapp.adapter.AllTypeResourceAdapter;
import com.zhangju.xingquban.interestclassapp.adapter.AudioResourceAdapter;
import com.zhangju.xingquban.interestclassapp.adapter.baseadpter.OnListItemClickListener;
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

/**
 * 资源音频
 */
@ContentView(R.layout.allresource_fragment)
public class AudioResFragment extends FastFragment implements SwipeRefreshAdapterView.OnListLoadListener, SwipeRefreshLayout.OnRefreshListener {
    @BindView(R.id.swipe_refresh_audio)
    SwipeRefreshRecyclerView swipeRefreshAudio;

    Unbinder unbinder;
    private List<ResouecesAll.AaDataBean> mAudioList = new ArrayList<>();//数据
    private AudioResourceAdapter audioAdapter;

    private int pageIndex = 0;//
    private int total = 0;//总页数
    private String mFilter="";

    @Override
    protected void alreadyPrepared() {
        setAudioResourceAdapter();
    }


    //音频资源Adpter
    private void setAudioResourceAdapter() {
        swipeRefreshAudio.setOnListLoadListener(this);
        swipeRefreshAudio.setOnRefreshListener(this);
        swipeRefreshAudio.autoRefresh();

        audioAdapter = new AudioResourceAdapter(getActivity(), mAudioList);
        swipeRefreshAudio.setLayoutManager(new LinearLayoutManager(getActivity()));
        swipeRefreshAudio.setAdapter(audioAdapter);

        audioAdapter.setOnListItemClickListener(new OnListItemClickListener() {
            @Override
            public void onItemClickListener(int position, View v) {
                String id = mAudioList.get(position).getId();
//                String fileUrl = mAudioList.get(position).getVideoList().get(0).getFileUrl();

                Intent intent = new Intent(getActivity(), AudioDetailActivity.class);

                intent.putExtra("types", "audio");
                intent.putExtra("resId", id);
                startActivity(intent);
            }
        });
        audioAdapter.setMyOnClickListener(new AllTypeResourceAdapter.MyOnClickListener() {
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
        sendRequest();
    }

    @Override
    public void onRefresh() {
        pageIndex = 0;
        sendRequest();
    }

    private void setSwipe() {
        if (swipeRefreshAudio != null) {
            swipeRefreshAudio.setRefreshing(false);
            swipeRefreshAudio.setLoading(false);
            swipeRefreshAudio.setEnabledLoad(true);
        }
    }

    public void sendRequest() {
        final Request request = Request.obtain(ResInterface.POST_RES_AUDIO_LIST);

        request.put("pageIndex", pageIndex);
        request.put("pageSize", "10");
        request.put("types", "audio");
        request.put("allStringQuery",mFilter);
        request.setListener(new SimpleListener<ResouecesAll>() {

            @Override
            public void onResponseListener(Request r, ResouecesAll resourcesAudio) {
                if (resourcesAudio.isSuccess()) {
                    total = resourcesAudio.getTotal();
                    if (pageIndex == 0) {
                        mAudioList.clear();
                    }
                    if (resourcesAudio.getAaData() != null && resourcesAudio.getAaData().size() > 0) {
                        mAudioList.addAll(resourcesAudio.getAaData());
                    }
                    audioAdapter.notifyDataSetChanged();
                    setSwipe();
                }
            }

            @Override
            public void onErrorListener(Request r, String error) {
                super.onErrorListener(r, error);
                setSwipe();
            }
        });
        net(request);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        unbinder = ButterKnife.bind(this, rootView);
        EventObserver.getInstance().subscribe(getContext(),this);
        return rootView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
        EventObserver.getInstance().unsubscribe(getContext(),this);
    }

    @Event
    private void eFilterContent(EventResourceFilterContent event){
        mFilter=event.getmFilter();
        sendRequest();
    }
}