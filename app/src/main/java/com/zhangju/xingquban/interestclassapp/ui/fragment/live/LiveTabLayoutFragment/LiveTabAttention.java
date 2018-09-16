package com.zhangju.xingquban.interestclassapp.ui.fragment.live.LiveTabLayoutFragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

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
import com.zhangju.xingquban.interestclassapp.bean.live.LiveAttentionListBean;
import com.zhangju.xingquban.interestclassapp.refactor.me.activity.LoginActivity;
import com.zhangju.xingquban.interestclassapp.refactor.user.EventUserDataChanged;
import com.zhangju.xingquban.interestclassapp.refactor.user.User;
import com.zhangju.xingquban.interestclassapp.refactor.user.UserManager;
import com.zhangju.xingquban.interestclassapp.swiperefrsh.SwipeRefreshAdapterView;
import com.zhangju.xingquban.interestclassapp.swiperefrsh.SwipeRefreshRecyclerView;
import com.zhangju.xingquban.interestclassapp.ui.activity.find.liveradio.LiveWatchActivity;
import com.zhangju.xingquban.interestclassapp.ui.activity.find.liveradio.StartLiveActivity;
import com.zhangju.xingquban.interestclassapp.ui.adapter.live.AttentionAdapter;
import com.zhangju.xingquban.interestclassapp.ui.fragment.live.LivePayActivity;
import com.zhangju.xingquban.interestclassapp.util.ToastUtil;
import com.zhangju.xingquban.interestclassapp.util.UrlUtils;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

//关注tab
public class LiveTabAttention extends BaseFragment implements SwipeRefreshLayout.OnRefreshListener, SwipeRefreshAdapterView.OnListLoadListener {

    @BindView(R.id.btn_live_jump)
    ImageView btnLiveJump;
    @BindView(R.id.swipe_refresh_attentiolist)
    SwipeRefreshRecyclerView swipeRefreshAttentiolist;
    Unbinder unbinder;
    @BindView(R.id.tv_see_more)
    TextView tvSeeMore;
    @BindView(R.id.line_no_more)
    LinearLayout lineNoMore;
    private AttentionAdapter attentionAdapter;

    private ArrayList<LiveAttentionListBean.AaDataBean> mAaDataList = new ArrayList<>();
    private String token;
    private String mRoomId;
    private String mChatroomId;


    private int pageIndex = 0;
    private int total = 0;//总页数
    boolean isRefresh;

    @Override
    public void initData() {
        EventObserver.getInstance().subscribe(getActivity(), this);

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        EventObserver.getInstance().unsubscribe(getActivity(), this);
    }

    @Override
    public int getMyLayout() {
        return R.layout.activity_live_tab_attention;
    }

    @Override
    public void initView() {
        setAttentionListAdapter();
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
            swipeRefreshAttentiolist.autoRefresh();
            isRefresh = false;
        }

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
    public void onPause() {
        super.onPause();
        isRefresh = true;

    }

    private void setAttentionListAdapter() {

        swipeRefreshAttentiolist.setOnRefreshListener(this);
        swipeRefreshAttentiolist.setOnListLoadListener(this);
        swipeRefreshAttentiolist.autoRefresh();


        attentionAdapter = new AttentionAdapter(getActivity(), mAaDataList);
        swipeRefreshAttentiolist.setLayoutManager(new LinearLayoutManager(getActivity()));
        swipeRefreshAttentiolist.setAdapter(attentionAdapter);

        //进入直播
        attentionAdapter.setOnListItemClickListener(new OnListItemClickListener() {
            @Override
            public void onItemClickListener(int position, View v) {
                if (!UserManager.getInstance().isLogin()) {
                    startActivity(new Intent(getActivity(), LoginActivity.class));
                } else {

                    mChatroomId = mAaDataList.get(position).getChatroomId();

                    Integer id1 = mAaDataList.get(position).getLiveRecord().getId();
                    //  2回播

                    getVideoInfo(id1, position);//获取该视频数据
                }

            }
        });

    }

    /**
     * 进入直播间
     *
     * @param roomId
     *///1 直播 2回播
    private void getVideoInfo(Integer roomId, final int position) {
        HttpUtils httpUtils = new HttpUtils();
        final String token = UserManager.getInstance().getToken();
        RequestParams params = new RequestParams();
        params.addHeader("X-CustomToken", token);


        params.addBodyParameter("id", roomId + "");

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

                                String chatroomId = bean.getAaData().getChatroomId() == null ? "" : bean.getAaData().getChatroomId();//
                                String rtmpPullUrl = bean.getAaData().getChannels().getRtmpPullUrl() == null ? "" : bean.getAaData().getChannels().getRtmpPullUrl();
                                String accid = bean.getAaData().getChatUser().getAccid() == null ? "" : bean.getAaData().getChatUser().getAccid();
                                String appKey = bean.getAttachData().getAppKey() == null ? "" : bean.getAttachData().getAppKey();
                                String chatToken = bean.getAaData().getChatUser().getToken() == null ? "" : bean.getAaData().getChatUser().getToken();
                                int stdCoin = bean.getAaData().getStdCoin();
                                String followes = bean.getAaData().getChatUser().getFollowes() == null ? "" : bean.getAaData().getChatUser().getFollowes();
                                mRoomId = bean.getAaData().getRoomsId();

                                String roomPic = mAaDataList.get(position).getRoomPic();
                                String roomName = mAaDataList.get(position).getRoomName();

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
    public void initListener() {

    }

    @OnClick({R.id.tv_see_more, R.id.btn_live_jump})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_live_jump:
                if (!UserManager.getInstance().isLogin()) {
                    startActivity(new Intent(getActivity(), LoginActivity.class));
                } else {
                    jumpLive();
                }
                break;
            //广播通知切换
            case R.id.tv_see_more:
                Intent intent = new Intent();
                intent.setAction("cuttab");
                intent.putExtra("index", 1);
                getActivity().sendBroadcast(intent);

        }
    }


    private void jumpLive() {
        Intent intent = new Intent(getActivity(), StartLiveActivity.class);
        startActivity(intent);
    }


    private void setSwipeEnable() {
        swipeRefreshAttentiolist.setRefreshing(false);
        swipeRefreshAttentiolist.setLoading(false);
        swipeRefreshAttentiolist.setEnabledLoad(true);
    }

    //获取关注列表数据
    private void getAttentiomListData() {
        HttpUtils httpUtils = new HttpUtils();

        UserManager instance = UserManager.getInstance();
        RequestParams params = new RequestParams();
        params.addBodyParameter("pageIndex", pageIndex + "");
        params.addBodyParameter("pageSize", "10");

        params.addHeader("X-CustomToken", instance.getToken());
        httpUtils.send(HttpRequest.HttpMethod.POST,
                UrlUtils.URL_LIVELIST_ATTENTION,
                params,
                new RequestCallBack<String>() {
                    @Override
                    public void onSuccess(ResponseInfo<String> responseInfo) {
                        String json = responseInfo.result;
                        try {
                            LiveAttentionListBean liveAttentionListBean = JSONObject.parseObject(json, LiveAttentionListBean.class);
                            if (liveAttentionListBean.isSuccess()) {
                                total = liveAttentionListBean.getTotal();
                                ArrayList<LiveAttentionListBean.AaDataBean> attentionList = liveAttentionListBean.getAaData();

                                if (pageIndex == 0) {
                                    mAaDataList.clear();
                                }
                                if (attentionList != null && attentionList.size() > 0) {

                                    mAaDataList.addAll(attentionList);
                                }


                            }
                            attentionAdapter.notifyDataSetChanged();
                            setSwipeEnable();
                            //判断是否有数据展示
                            if (mAaDataList.size() > 0) {
                                lineNoMore.setVisibility(View.GONE);
                            } else {
                                lineNoMore.setVisibility(View.VISIBLE);
                            }


                        } catch (Exception ex) {
                            ex.printStackTrace();
                        }

                    }

                    @Override
                    public void onFailure(HttpException e, String s) {
                        setSwipeEnable();
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

    //下拉刷新
    @Override
    public void onRefresh() {
        pageIndex = 0;
        getAttentiomListData();

    }

    //上拉加载更多
    @Override
    public void onListLoad() {
        pageIndex++;
        if (pageIndex + 1 > total) {
            swipeRefreshAttentiolist.setEnabledLoad(false);
            swipeRefreshAttentiolist.setLoading(false);
            return;
        }
        getAttentiomListData();

    }


    @Override
    public void onClick(View v) {

    }
}
