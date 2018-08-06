package com.zhangju.xingquban.interestclassapp.ui.fragment.live.LiveTabLayoutFragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.alibaba.fastjson.JSONObject;
import com.fastlib.annotation.Event;
import com.fastlib.app.EventObserver;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.RequestParams;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest;
import com.zhangju.xingquban.R;
import com.zhangju.xingquban.interestclassapp.adapter.baseadpter.OnListItemClickListener;
import com.zhangju.xingquban.interestclassapp.application.MyApp;
import com.zhangju.xingquban.interestclassapp.base.BaseFragment;
import com.zhangju.xingquban.interestclassapp.bean.LivePayBean;
import com.zhangju.xingquban.interestclassapp.bean.live.LiveNewListBean;
import com.zhangju.xingquban.interestclassapp.refactor.common.bean.EventRefresh;
import com.zhangju.xingquban.interestclassapp.refactor.me.activity.LoginActivity;
import com.zhangju.xingquban.interestclassapp.refactor.user.EventUserDataChanged;
import com.zhangju.xingquban.interestclassapp.refactor.user.User;
import com.zhangju.xingquban.interestclassapp.refactor.user.UserManager;
import com.zhangju.xingquban.interestclassapp.swiperefrsh.SwipeRefreshAdapterView;
import com.zhangju.xingquban.interestclassapp.swiperefrsh.SwipeRefreshRecyclerView;
import com.zhangju.xingquban.interestclassapp.ui.activity.find.liveradio.LiveWatchActivity;
import com.zhangju.xingquban.interestclassapp.ui.activity.find.liveradio.StartLiveActivity;
import com.zhangju.xingquban.interestclassapp.ui.adapter.live.NewLiveAdapter;
import com.zhangju.xingquban.interestclassapp.ui.fragment.live.LivePayActivity;
import com.zhangju.xingquban.interestclassapp.util.ToastUtil;
import com.zhangju.xingquban.interestclassapp.util.UrlUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

//最新tab
public class LiveTabNewEst
        extends BaseFragment
        implements SwipeRefreshAdapterView.OnListLoadListener, SwipeRefreshLayout.OnRefreshListener {
    @BindView(R.id.btn_live_jump)
    ImageView                btnLiveJump;
    @BindView(R.id.swipe_refresh_newslist)
    SwipeRefreshRecyclerView swipeRefreshNewslist;
    Unbinder unbinder;

    private NewLiveAdapter newLiveAdapter;

    private String token;
    private String mRoomId;
    private String mChatroomId;

    private int pageIndex = 0;
    int total = 0;
    boolean isRefresh;
    private List<LiveNewListBean.AaDataBean> mNewsList = new ArrayList<>();

    @Override
    public void initData() {
        EventObserver.getInstance().subscribe(getActivity(), this);
    }

    @Event
    private void EventRefresh(EventRefresh eventRefresh) {

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        EventObserver.getInstance().unsubscribe(getActivity(), this);
    }

    @Event
    private void EventUserDataChanged(EventUserDataChanged eventRefresh) {
        User.Degree degree = UserManager.getInstance().getUser().degree;
        boolean isCommon = degree.isCommon;
        //普通用户不能进行直播操作
        if (isCommon) {
            btnLiveJump.setVisibility(View.GONE);
        } else {
            btnLiveJump.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void onResume() {
        super.onResume();


        if (isRefresh) {
            swipeRefreshNewslist.autoRefresh();
            isRefresh = false;
        }

    }

    @Override
    public void onPause() {
        super.onPause();
        isRefresh = true;
    }

    @Override
    public int getMyLayout() {
        return R.layout.activity_live_tab_new_est;
    }

    @Override
    public void initView() {
        setNewsAdapter();
        User.Degree degree = UserManager.getInstance().getUser().degree;
        boolean isCommon = degree.isCommon;
        //普通用户不能进行直播操作


        if (isCommon) {
            btnLiveJump.setVisibility(View.GONE);
        } else {
            btnLiveJump.setVisibility(View.VISIBLE);
        }


    }

    private void setNewsAdapter() {
        swipeRefreshNewslist.setOnRefreshListener(this);
        swipeRefreshNewslist.setOnListLoadListener(this);
        swipeRefreshNewslist.autoRefresh();


        newLiveAdapter = new NewLiveAdapter(getActivity(), mNewsList);
        swipeRefreshNewslist.setLayoutManager(new GridLayoutManager(getActivity(), 2));
        swipeRefreshNewslist.setAdapter(newLiveAdapter);

        newLiveAdapter.setOnListItemClickListener(new OnListItemClickListener() {
            @Override
            public void onItemClickListener(int position, View v) {


                if (!UserManager.getInstance().isLogin()) {
                    startActivity(new Intent(getActivity(), LoginActivity.class));
                } else {

                    mChatroomId = mNewsList.get(position).getChatroomId();
                    int status = mNewsList.get(position).getStatus();//1直播
                    mRoomId = mNewsList.get(position).getId();


                    initRoom(status, position);//获取该视频数据
                }

            }
        });
    }

    //上拉加载更多
    @Override
    public void onListLoad() {
        pageIndex++;
        if (pageIndex + 1 > total) {
            swipeRefreshNewslist.setEnabledLoad(false);
            swipeRefreshNewslist.setLoading(false);
            return;
        }
        getNewsData();

    }

    //下拉刷新
    @Override
    public void onRefresh() {

        pageIndex = 0;
        getNewsData();

    }

    @Override
    public void initListener() {
        btnLiveJump.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_live_jump:
                if (!UserManager.getInstance().isLogin()) {
                    startActivity(new Intent(getActivity(), LoginActivity.class));
                } else {
                    jumpLive();
                }

                break;
        }

    }

    private void jumpLive() {
        Intent intent = new Intent(getActivity(), StartLiveActivity.class);
        startActivity(intent);
    }


    private void getNewsData() {
        HttpUtils httpUtils = new HttpUtils();

        UserManager instance = UserManager.getInstance();
        RequestParams params = new RequestParams();
        params.addBodyParameter("pageIndex", pageIndex + "");
        params.addBodyParameter("pageSize", "10");
        params.addBodyParameter("sortJson", "[{field : 'onlineUserCount',isAsc: false}]");

        params.addHeader("X-CustomToken", instance.getToken());
        httpUtils.send(HttpRequest.HttpMethod.POST,
                UrlUtils.URL_LIVELIST_NEW,
                params,
                new RequestCallBack<String>() {
                    @Override
                    public void onSuccess(ResponseInfo<String> responseInfo) {
                        String json = responseInfo.result;
                        LiveNewListBean liveNewListBean = JSONObject.parseObject(json, LiveNewListBean.class);
                        if (pageIndex == 0) {
                            mNewsList.clear();
                        }
                        if (liveNewListBean.isSuccess()) {
                            total = liveNewListBean.getTotal();
                            ArrayList<LiveNewListBean.AaDataBean> newsData = liveNewListBean.getAaData();
                            if (newsData != null && newsData.size() > 0) {

                                mNewsList.addAll(newsData);
                            }
                        }
                        newLiveAdapter.notifyDataSetChanged();
                        setSwipeEnable();
                    }

                    @Override
                    public void onFailure(HttpException e, String s) {
                        setSwipeEnable();
                    }
                });
    }

    private void setSwipeEnable() {
        if (swipeRefreshNewslist != null) {
            swipeRefreshNewslist.setRefreshing(false);
            swipeRefreshNewslist.setLoading(false);
            swipeRefreshNewslist.setEnabledLoad(true);
        }
    }

    /**
     * 进入直播间
     *
     * @param
     */
    private void initRoom(final int type, final int postion) {
        HttpUtils httpUtils = new HttpUtils();
        RequestParams params = new RequestParams();
        final String token = UserManager.getInstance().getToken();
        params.addHeader("X-CustomToken", token);
        params.addBodyParameter("id", mRoomId);
        String url = UrlUtils.URL_INITROOM;
        httpUtils.send(HttpRequest.HttpMethod.POST,
                url,
                params,
                new RequestCallBack<String>() {
                    @Override
                    public void onSuccess(ResponseInfo<String> responseInfo) {
                        String json = responseInfo.result;
                        LivePayBean bean = JSONObject.parseObject(json, LivePayBean.class);
                        try {
                            if (bean != null && bean.getAttachData() != null) {
                                boolean pay = bean.getAttachData().isPay();//是否支付费用
                                //当视频免费或者是已付费
                                Intent intent = new Intent();
                                //直播
                                if (type == 1) {
                                    String chatroomId = bean.getAaData().getChatroomId() == null ? "" : bean.getAaData()
                                            .getChatroomId();//
                                    String rtmpPullUrl = bean.getAaData().getChannels().getRtmpPullUrl() == null ? "" : bean
                                            .getAaData().getChannels().getRtmpPullUrl();
                                    String accid = bean.getAaData().getChatUser().getAccid() == null ? "" : bean.getAaData()
                                            .getChatUser().getAccid();
                                    String appKey = bean.getAttachData().getAppKey() == null ? "" : bean.getAttachData()
                                            .getAppKey();
                                    String chatToken = bean.getAaData().getChatUser().getToken() == null ? "" : bean.getAaData
                                            ().getChatUser().getToken();
                                    int stdCoin = bean.getAaData().getStdCoin();
                                    String followes = bean.getAaData().getChatUser().getFollowes() == null ? "" : bean
                                            .getAaData().getChatUser().getFollowes();
                                    mRoomId = bean.getAaData().getRoomsId();

                                    //                                    intent.putExtra("roomName",roomName);
                                    //                                    intent.putExtra("roomPic",roomPic);
                                    String roomName = mNewsList.get(postion).getRoomName();
                                    String roomPic = mNewsList.get(postion).getRoomPic();

                                    if (pay) {
                                        intent.setClass(getActivity(), LiveWatchActivity.class);
                                    } else {
                                        double roomPrice = bean.getAttachData().getRoomPrice();
                                        double seeBalances = bean.getAttachData().getSeeBalances(); // 观看者的余额
                                        intent = new Intent(getActivity(), LivePayActivity.class);
                                        intent.putExtra("roomPrice", roomPrice);
                                        intent.putExtra("seeBalances", seeBalances);
                                        intent.putExtra("roomName", roomName);
                                        intent.putExtra("roomPic", roomPic);
                                    }
                                    intent.putExtra("stdCoin", stdCoin);
                                    intent.putExtra("chatToken", chatToken);
                                    intent.putExtra("accid", accid);
                                    intent.putExtra("appKey", appKey);
                                    intent.putExtra("rtmpPullUrl", rtmpPullUrl);
                                    intent.putExtra("chatroomId", chatroomId);
                                    intent.putExtra("mToken", token);
                                    intent.putExtra("myFollowes", followes);
                                    intent.putExtra("roomId", mRoomId);

                                    //录播
                                } else {
                                    String id = bean.getAaData().getId() == null ? "" : bean.getAaData().getId();
                                    String liveVdoUrl = bean.getAttachData().getLiveVdoUrl() == null ? "" : bean.getAttachData
                                            ().getLiveVdoUrl();
                                    String orig_video_key = bean.getAaData().getOrig_video_key() == null ? "" : bean.getAaData
                                            ().getOrig_video_key();
                                    boolean addOp = bean.getAaData().isAddOp();
                                    String roomName = mNewsList.get(postion).getRoomName();
                                    String pic = mNewsList.get(postion).getRoomPic();

                                    intent.setClass(getActivity(), LiveVideoPlayerActivity.class);
                                    intent.putExtra("id", id);
                                    intent.putExtra("pic", pic);
                                    intent.putExtra("title", roomName);
                                    intent.putExtra("url", liveVdoUrl + "/" + orig_video_key);
                                    intent.putExtra("isfocus", addOp);
                                }
                                startActivity(intent);
                            }
                        } catch (Exception ex) {
                            ex.printStackTrace();
                        }
                    }

                    @Override
                    public void onFailure(HttpException e, String s) {
                        ToastUtil.showToast(MyApp.instance, "请求服务器异常,请检查您的网络连接");
                    }
                });
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
    }

}
