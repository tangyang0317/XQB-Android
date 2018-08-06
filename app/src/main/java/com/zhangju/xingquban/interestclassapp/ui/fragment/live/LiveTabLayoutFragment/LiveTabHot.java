package com.zhangju.xingquban.interestclassapp.ui.fragment.live.LiveTabLayoutFragment;

import android.content.Intent;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
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
import com.zhangju.xingquban.interestclassapp.RetrofitInterface.NetWork;
import com.zhangju.xingquban.interestclassapp.adapter.baseadpter.OnListItemClickListener;
import com.zhangju.xingquban.interestclassapp.adapter.pop.RootListViewAdapter;
import com.zhangju.xingquban.interestclassapp.adapter.pop.SubListViewAdapter;
import com.zhangju.xingquban.interestclassapp.application.MyApp;
import com.zhangju.xingquban.interestclassapp.base.BaseFragment;
import com.zhangju.xingquban.interestclassapp.bean.HotNewsLiveBean;
import com.zhangju.xingquban.interestclassapp.bean.LivePayBean;
import com.zhangju.xingquban.interestclassapp.bean.NearSubjectBean;
import com.zhangju.xingquban.interestclassapp.refactor.common.bean.EventRefresh;
import com.zhangju.xingquban.interestclassapp.refactor.me.activity.LoginActivity;
import com.zhangju.xingquban.interestclassapp.refactor.user.EventUserDataChanged;
import com.zhangju.xingquban.interestclassapp.refactor.user.User;
import com.zhangju.xingquban.interestclassapp.refactor.user.UserManager;
import com.zhangju.xingquban.interestclassapp.swiperefrsh.SwipeRefreshAdapterView;
import com.zhangju.xingquban.interestclassapp.swiperefrsh.SwipeRefreshRecyclerView;
import com.zhangju.xingquban.interestclassapp.ui.activity.find.liveradio.LiveWatchActivity;
import com.zhangju.xingquban.interestclassapp.ui.activity.find.liveradio.StartLiveActivity;
import com.zhangju.xingquban.interestclassapp.ui.adapter.live.HotLiveAdapter;
import com.zhangju.xingquban.interestclassapp.ui.fragment.live.LivePayActivity;
import com.zhangju.xingquban.interestclassapp.util.ScreenUtils;
import com.zhangju.xingquban.interestclassapp.util.ToastUtil;
import com.zhangju.xingquban.interestclassapp.util.UrlUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

//热门tab
public class LiveTabHot extends BaseFragment implements SwipeRefreshLayout.OnRefreshListener, SwipeRefreshAdapterView.OnListLoadListener {
    @BindView(R.id.pop_location)
    View popLocation;
    @BindView(R.id.swipe_refresh_hotlist)
    SwipeRefreshRecyclerView swipeRefreshHotlist;
    @BindView(R.id.pop_background)
    TextView pop_background;
    private String TAG = "liveTabHot";

    @BindView(R.id.btn_live_jump)
    ImageView btnLiveJump;

    @BindView(R.id.relative_condition)
    RelativeLayout relativeCondition;
    Unbinder unbinder;
    private HotLiveAdapter hotAdapter;
    HotNewsLiveBean hotBean;
    private List<HotNewsLiveBean.AaDataBean> mHotList = new ArrayList<>();//热门数据

    private String token;
    private String mRoomId;
    private String mChatroomId;
    private int pager_num = 0;
    public boolean load = true;


    private String[] rootList;
    private String[][] itemList;


    private ListView subListView;
    private LinearLayout popupLayout;
    private FrameLayout subLayout;
    private int selectedPosition;
    private PopupWindow mPopupWindow;
    private ListView rootListView;

    private int pageIndex = 0;//页码
    private int sumPage = 0;//分页总数
    boolean isRefresh;


    @Override
    public void initData() {
        getsubCondition();
    }

    /**
     * 进入直播间
     *
     * @param roomId
     *///1 直播 2回播
    private void getVideoInfo(String roomId, final int position, final int type) {
        HttpUtils httpUtils = new HttpUtils();
        final String token = UserManager.getInstance().getToken();
        RequestParams params = new RequestParams();
        params.addHeader("X-CustomToken", token);
        if (type == 2) {
            params.addBodyParameter("liveVdoId", roomId);
        } else {
            params.addBodyParameter("id", roomId);
        }
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
                                    String chatroomId = bean.getAaData().getChatroomId() == null ? "" : bean.getAaData().getChatroomId();//
                                    String rtmpPullUrl = bean.getAaData().getChannels().getRtmpPullUrl() == null ? "" : bean.getAaData().getChannels().getRtmpPullUrl();
                                    String accid = bean.getAaData().getChatUser().getAccid() == null ? "" : bean.getAaData().getChatUser().getAccid();
                                    String appKey = bean.getAttachData().getAppKey() == null ? "" : bean.getAttachData().getAppKey();
                                    String chatToken = bean.getAaData().getChatUser().getToken() == null ? "" : bean.getAaData().getChatUser().getToken();
                                    int stdCoin = bean.getAaData().getStdCoin();
                                    String followes = bean.getAaData().getChatUser().getFollowes() == null ? "" : bean.getAaData().getChatUser().getFollowes();
                                    mRoomId = bean.getAaData().getRoomsId();

                                    String roomName = mHotList.  get(position).getRoomName();//直播名
                                    String roomPic = mHotList.get(position).getModels().getRoomPic();//直播图


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
                                    String liveVdoUrl = bean.getAttachData().getLiveVdoUrl() == null ? "" : bean.getAttachData().getLiveVdoUrl();
                                    String orig_video_key = bean.getAaData().getOrig_video_key() == null ? "" : bean.getAaData().getOrig_video_key();
                                    boolean addOp = bean.getAaData().isAddOp();
                                    String roomName = mHotList.get(position).getRoomName();
                                    String pic = mHotList.get(position).getRoomPic();


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


    //获取科目筛选条件
    private void getsubCondition() {
        NetWork.getNearSubject().getKemuAllData()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observerSubject);
    }

    //获取所有科目的信息
    private Observer<NearSubjectBean> observerSubject = new Observer<NearSubjectBean>() {
        @Override
        public void onCompleted() {
            Log.e(TAG, "======onNext=======: ");
        }

        @Override
        public void onError(Throwable e) {
            ToastUtil.makeText(getContext(), e.getMessage(), ToastUtil.LENGTH_LONG).show();
        }

        @Override
        public void onNext(NearSubjectBean mnearSubjectBean) {
            if (mnearSubjectBean.getAaData() == null) return;
            try {
                List<NearSubjectBean.AaDataBean> allList = mnearSubjectBean.getAaData();
                rootList = new String[allList.size()];
                itemList = new String[allList.size()][];
                for (int i = 0; i < allList.size(); i++) {
                    rootList[i] = allList.get(i).getName();
                    List<NearSubjectBean.AaDataBean.ChildsBean> childs = allList.get(i).getChilds();
                    if (childs != null) {
                        itemList[i] = new String[childs.size()];
                        for (int j = 0; j < childs.size(); j++) {
                            itemList[i][j] = childs.get(j).getName();
                        }
                    }
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    };

    @Override
    public int getMyLayout() {
        return R.layout.activity_live_tab_hot;
    }

    @Override
    public void initView() {
        setHotListAdapter();
        EventObserver.getInstance().subscribe(getActivity(), this);

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

    @Override
    public void onDestroy() {
        super.onDestroy();
        EventObserver.getInstance().unsubscribe(getActivity(), this);
    }

    @Override
    public void onResume() {
        super.onResume();


        if (isRefresh) {
            swipeRefreshHotlist.autoRefresh();
            isRefresh = false;
        }


    }

    @Event
    private void EventRefresh(EventRefresh eventRefresh) {

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

    private void setHotListAdapter() {
        swipeRefreshHotlist.setOnRefreshListener(this);
        swipeRefreshHotlist.setOnListLoadListener(this);
        swipeRefreshHotlist.autoRefresh();

        hotAdapter = new HotLiveAdapter(getActivity(), mHotList);
        swipeRefreshHotlist.setLayoutManager(new LinearLayoutManager(getActivity()));
        swipeRefreshHotlist.setAdapter(hotAdapter);

        //进入直播或者回播视频
        hotAdapter.setOnListItemClickListener(new OnListItemClickListener() {
            @Override
            public void onItemClickListener(int position, View v) {
                if (!UserManager.getInstance().isLogin()) {
                    startActivity(new Intent(getActivity(), LoginActivity.class));
                } else {
                    String id1 = mHotList.get(position).getModels().getId();
                    int rstype = mHotList.get(position).getRstype();//1 直播 2回播

                    getVideoInfo(id1, position, rstype);//获取该视频数据
                }

            }
        });
    }

    //下拉刷新
    @Override
    public void onRefresh() {
        pageIndex = 0;
        getHotDataList();
    }

    //上拉加载
    @Override
    public void onListLoad() {
        pageIndex++;
        if (pageIndex + 1 > sumPage) {
            swipeRefreshHotlist.setEnabledLoad(false);
            swipeRefreshHotlist.setLoading(false);

            return;
        }
        getHotDataList();
    }


    @Override
    public void initListener() {
    }

    @OnClick({R.id.btn_live_jump, R.id.relative_condition})
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
            case R.id.relative_condition:
                if (rootList != null && rootList.length > 0) {
                    showPopBtn(ScreenUtils.getScreenWidth(getActivity()), ScreenUtils.getScreenHeight(getActivity()));
                }
                break;
        }
    }

    private void jumpLive() {
        Intent intent = new Intent(getActivity(), StartLiveActivity.class);
        startActivity(intent);
    }

    private void getHotDataList() {
        HttpUtils httpUtils = new HttpUtils();
        final RequestParams params = new RequestParams();
        params.addBodyParameter("pageIndex", pageIndex + "");
        params.addBodyParameter("pageSize", "10");
        httpUtils.send(HttpRequest.HttpMethod.POST,
                UrlUtils.URL_LIVELIST,
                params,
                new RequestCallBack<String>() {
                    @Override
                    public void onSuccess(ResponseInfo<String> responseInfo) {
                        String json = responseInfo.result;
                        hotBean = JSONObject.parseObject(json, HotNewsLiveBean.class);
                        sumPage = hotBean.getTotal();
                        if (pageIndex == 0) {
                            mHotList.clear();
                        }
                        if (hotBean.isSuccess() && hotBean.getAaData() != null && hotBean.getAaData().size() > 0) {
                            List<HotNewsLiveBean.AaDataBean> hotList = hotBean.getAaData();
                            if (hotList != null && hotList.size() > 0) {

                                mHotList.addAll(hotList);
                            }
                        }
                        hotAdapter.notifyDataSetChanged();
                        setSwipeEnable();
                    }

                    @Override
                    public void onFailure(HttpException e, String s) {
                        setSwipeEnable();
                    }
                });
    }

    private void setSwipeEnable() {
        if (swipeRefreshHotlist != null) {
            swipeRefreshHotlist.setRefreshing(false);
            swipeRefreshHotlist.setLoading(false);
            swipeRefreshHotlist.setEnabledLoad(true);
        }
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


    private void showPopBtn(int screenWidth, int screenHeight) {
        LayoutInflater inflater = LayoutInflater.from(getActivity());
        popupLayout = (LinearLayout) inflater.inflate(
                R.layout.popupwindow_layout, null, false);
        rootListView = (ListView) popupLayout.findViewById(R.id.root_listview);
        final RootListViewAdapter adapter = new RootListViewAdapter(
                getActivity());
        adapter.setItems(rootList);
        rootListView.setAdapter(adapter);
        /**
         * 子popupWindow
         */
        subLayout = (FrameLayout) popupLayout.findViewById(R.id.sub_popupwindow);
        /**
         * 初始化subListview
         */
        subListView = (ListView) popupLayout.findViewById(R.id.sub_listview);
        /**
         * 弹出popupwindow时，二级菜单默认隐藏，当点击某项时，二级菜单再弹出
         */
        subLayout.setVisibility(View.INVISIBLE);
        /**
         * 初始化mPopupWindow
         */
        mPopupWindow = new PopupWindow(popupLayout, screenWidth,
                ViewGroup.LayoutParams.WRAP_CONTENT, true);

        /**
         * 有了mPopupWindow.setBackgroundDrawable(new BitmapDrawable());
         * 这句可以使点击popupwindow以外的区域时popupwindow自动消失 但这句必须放在showAsDropDown之前
         */
        mPopupWindow.setBackgroundDrawable(new BitmapDrawable());
        /**
         * popupwindow的位置，第一个参数表示位于哪个控件之下 第二个参数表示向左右方向的偏移量，正数表示向左偏移，负数表示向右偏移
         * 第三个参数表示向上下方向的偏移量，正数表示向下偏移，负数表示向上偏移
         *
         */
        mPopupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                pop_background.setVisibility(View.GONE);
            }
        });

        mPopupWindow.showAsDropDown(popLocation, -5, 0);// 在控件下方显示popwindow
        mPopupWindow.update();
        rootListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                /**
                 * 选中root某项时改变该ListView item的背景色
                 */
                adapter.setSelectedPosition(position);
                adapter.notifyDataSetInvalidated();
                selectedPosition = position;
                SubListViewAdapter subAdapter = new SubListViewAdapter(
                        getActivity(), itemList, position);
                subListView.setAdapter(subAdapter);
                /**
                 * 选中某个根节点时，使显示相应的子目录可见
                 */
                subLayout.setVisibility(View.VISIBLE);
                subListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(
                            AdapterView<?> parent, View view,
                            int position, long id) {
                        popupLayout.setVisibility(View.GONE);
                        swipeRefreshHotlist.autoRefresh();
                        mPopupWindow.dismiss();
                    }
                });
            }
        });
        pop_background.setVisibility(View.VISIBLE);

    }
}
