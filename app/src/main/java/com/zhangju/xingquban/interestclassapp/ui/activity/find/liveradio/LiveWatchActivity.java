package com.zhangju.xingquban.interestclassapp.ui.activity.find.liveradio;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.view.ViewPager;
import android.text.TextUtils;
import android.util.Log;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethod;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.fastjson.JSONObject;
import com.bumptech.glide.Glide;
import com.fastlib.net.Request;
import com.fastlib.net.SimpleListener;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.RequestParams;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest;
import com.netease.nimlib.sdk.AbortableFuture;
import com.netease.nimlib.sdk.NIMClient;
import com.netease.nimlib.sdk.Observer;
import com.netease.nimlib.sdk.RequestCallback;
import com.netease.nimlib.sdk.auth.AuthService;
import com.netease.nimlib.sdk.auth.LoginInfo;
import com.netease.nimlib.sdk.chatroom.ChatRoomMessageBuilder;
import com.netease.nimlib.sdk.chatroom.ChatRoomService;
import com.netease.nimlib.sdk.chatroom.ChatRoomServiceObserver;
import com.netease.nimlib.sdk.chatroom.constant.MemberQueryType;
import com.netease.nimlib.sdk.chatroom.constant.MemberType;
import com.netease.nimlib.sdk.chatroom.model.ChatRoomInfo;
import com.netease.nimlib.sdk.chatroom.model.ChatRoomKickOutEvent;
import com.netease.nimlib.sdk.chatroom.model.ChatRoomMember;
import com.netease.nimlib.sdk.chatroom.model.ChatRoomMessage;
import com.netease.nimlib.sdk.chatroom.model.EnterChatRoomData;
import com.netease.nimlib.sdk.chatroom.model.EnterChatRoomResultData;
import com.netease.nimlib.sdk.chatroom.model.MemberOption;
import com.netease.nimlib.sdk.friend.FriendService;
import com.netease.nimlib.sdk.msg.constant.MsgTypeEnum;
import com.netease.nimlib.sdk.uinfo.UserService;
import com.netease.nimlib.sdk.uinfo.model.NimUserInfo;
import com.zhangju.xingquban.R;
import com.zhangju.xingquban.interestclassapp.bean.live.ChatRoomAddFollowBean;
import com.zhangju.xingquban.interestclassapp.bean.live.ChatRoomBaseItem;
import com.zhangju.xingquban.interestclassapp.bean.live.ChatRoomFollowBean;
import com.zhangju.xingquban.interestclassapp.bean.live.ChatRoomItemBean1;
import com.zhangju.xingquban.interestclassapp.bean.live.ChatRoomItemBean2;
import com.zhangju.xingquban.interestclassapp.bean.live.ChatUserInfoBean;
import com.zhangju.xingquban.interestclassapp.bean.live.IMBlackListBean;
import com.zhangju.xingquban.interestclassapp.bean.live.LiveExitDataBean;
import com.zhangju.xingquban.interestclassapp.bean.live.LiveGiftBean;
import com.zhangju.xingquban.interestclassapp.bean.live.LiveGiftSendBean;
import com.zhangju.xingquban.interestclassapp.refactor.common.bean.Response;
import com.zhangju.xingquban.interestclassapp.refactor.common.utils.ThirdPartyUtils;
import com.zhangju.xingquban.interestclassapp.refactor.location.LocationManager;
import com.zhangju.xingquban.interestclassapp.refactor.me.activity.ExchangeFunBeanActivity;
import com.zhangju.xingquban.interestclassapp.refactor.me.bean.MeInterface;
import com.zhangju.xingquban.interestclassapp.refactor.me.bean.ResponseWallet;
import com.zhangju.xingquban.interestclassapp.refactor.user.UserManager;
import com.zhangju.xingquban.interestclassapp.ui.adapter.live.ChatRoomAdapter;
import com.zhangju.xingquban.interestclassapp.ui.adapter.live.ChatRoomMemberAdapter;
import com.zhangju.xingquban.interestclassapp.ui.adapter.live.MyGridViewAdapter;
import com.zhangju.xingquban.interestclassapp.ui.fragment.find.SeekResource.NewsDetailActivity;
import com.zhangju.xingquban.interestclassapp.ui.main.MainActivity;
import com.zhangju.xingquban.interestclassapp.ui.sys.KeyboardChangeListener;
import com.zhangju.xingquban.interestclassapp.util.ToastUtil;
import com.zhangju.xingquban.interestclassapp.util.UrlUtils;
import com.zhangju.xingquban.interestclassapp.util.click.NoDoubleClick;
import com.zhangju.xingquban.interestclassapp.view.GlideCircleTrans;
import com.zhangju.xingquban.interestclassapp.view.dialog.ShareDialog;
import com.zhangju.xingquban.interestclassapp.view.gridview.GridViewPager;
import com.zhangju.xingquban.interestclassapp.view.gridview.GridViewPagerDataAdapter;
import com.zhangju.xingquban.interestclassapp.view.listView.HorizontalListView;
import com.zhangju.xingquban.interestclassapp.view.livestream.NEMediaController;
import com.zhangju.xingquban.interestclassapp.view.livestream.NEVideoView;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import cn.sharesdk.framework.Platform;
import cn.sharesdk.framework.PlatformActionListener;
import cn.sharesdk.framework.ShareSDK;
import cn.sharesdk.tencent.qq.QQ;
import cn.sharesdk.tencent.qzone.QZone;
import cn.sharesdk.wechat.friends.Wechat;
import cn.sharesdk.wechat.moments.WechatMoments;


/**
 * Created by Administrator on 2016/10/25.
 *
 * @直播拉流界面
 */
public class LiveWatchActivity
        extends Activity
        implements View.OnClickListener, AdapterView.OnItemClickListener, ChatRoomMemberCache.onCustomMessageListener {


    private static final String TAG = "LiveWatchActivity";
    // 云信聊天室系统默认分组
    private static final String BLACKLIST = "1";    // 黑名单
    private static final String NOSOUND = "2";      // 静音
    private static final String FRIENDS = "3";      // 好友
    private Context mContext;

    private NEVideoView mVideoView;
    private NEMediaController mMediaController;
    private View mLoadingView;

    private int mMediaType = 0; //播放缓冲策略 0为直播低延时  1 为点播抗抖动

    private boolean mHardware = false; // 解码类型 , true为硬解码, false软解码
    private String mVideoPath; //拉流地址
    private ImageView mVideoSpeak;
    private ImageView mVideoShare;
    private ImageView mIvGone;
    private View mSpeakView;      // 发消息弹窗
    private View mDetailsView; // 用户信息弹窗


    private View mGiftView;    // 礼物


    private EditText mEtSpeak;
    private Button mBtnSpeakSend;
    private ImageView mIvWindowAvatar; // 弹窗的头像
    private ChatRoomMember mChatRoomMember; // 被选中的用户资料
    private ChatRoomMember mMyMember;
    private LinearLayout mLlWindowShutup; // 禁言or拉黑
    private TextView mTvWindowShutup;
    private LinearLayout mLlWindowOut;    // 踢出or举报


    private AbortableFuture<LoginInfo> mLoginRequest;
    private String token;
    private String mAppKey;
    private String mAccid;
    private String mChatroomId;
    private String mChatToken;
    private ListView mLvChatroom;

    private HorizontalListView mHlvAvatars; // 观众头像列表

    private ArrayList<ChatRoomBaseItem> mMessageData = new ArrayList<>();
    private List<ChatRoomMember> mAvatarData = new ArrayList<>();
    private Map<String, ChatRoomMember> memberCache = new ConcurrentHashMap<>();
    private ChatRoomAdapter mChatRoomAdapter;
    private ChatRoomMemberAdapter mChatRoomMemberAdapter;
    private PopupWindow mPopupWindow;

    private boolean mFlagGone = false;


    private long updateTime = 0; // 非游客的updateTime
    private long enterTime = 0; // 游客的enterTime

    private boolean isNormalEmpty = false; // 固定成员是否拉取完
    private static final int LIMIT = 100;
    private ImageView mIvVideoExit;
    private FrameLayout mRlMain;
    private TextView mTvWindowId;
    private TextView mTvWindowAge;
    private TextView mTvWindowFllows;
    private TextView mTvWindowName;
    private TextView mTvWindowGive;
    private TextView mTvWindowGet;
    private TextView mTvWindowFans;
    private String mMyFollowes;
    private LinearLayout mLlWindowFllow;
    private TextView mTvWindowOut;
    private ChatUserInfoBean mChatUserInfoBean;
    private TextView mTvWindowSetManage;
    private TextView mTvWindowFllow;
    private boolean isManage;
    private String mAccount; // 自己的accid
    private String mFollowString = "";
    private ImageView mIvWindowShutup;
    private ImageView mIvWindowOut;
    private FrameLayout mProgress;
    private String mLiveAvatar;
    private TextView mTvFollow;
    private TextView mTvRoomid;
    private ImageView mIvGift;
    private RelativeLayout mRlGone;
    private ImageView mIvAvatar;
    private TextView mTvPeopleNum;
    private TextView mTvName;
    private ChatRoomMember mAnchorMember;   // 主播资料
    private String mCreatorAccid;   // 主播账号
    private String mLiveRoomId;     // 直播间账号


    private String mLiveName;
    private PopupWindow mPopShareWindow;


    private List<LiveGiftBean.AaDataBean> mGiftGridList; // 礼物列表

    private GridViewPager mGvpWindowMain;   // 礼物界面的viewpager
    private int mGiftIndex;   //当前被选中的礼物的索引
    private int mPageSum;     // viewPager总页数

    private LinearLayout.LayoutParams mDotParams;
    private LinearLayout mLlLayoutDots; // 指示器容器
    private List<ImageView> dots = new ArrayList<ImageView>(); // 指示器容器列表
    private int mCurrPageIndex; // 指示器当前索引
    private TextView mTvWindowTopup;
    private TextView mTvWindowSend;
    private LinearLayout mLlgiftcontent;

    private int giftNum = 0;        // 发送的礼物数
    private boolean giftNumFlag = true;


    /**
     * 分享操作
     */
    private ShareDialog shareDialog;
    private String sharetitle = "为兴趣而生，为梦想而来";
    private String shareid = "";
    private String shareUrl = "http://my.xqban.com/admnxzcmr/rooms/share?id=" + shareid;
    private String image = "";
    private ArrayList<String> mlist = new ArrayList<>();
    private String shareContent = "";

//    http://my.xqban.com/admnxzcmr/rooms/share?id=58

    Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            if (mGiftGridList != null && mGiftGridList.size() > mGiftIndex) {
                LiveGiftBean.AaDataBean aaDataBean = mGiftGridList.get(mGiftIndex);
                String id = aaDataBean.getId(); // 礼物id

                switch (msg.what) {
                    case 0:     // 发送礼物
                        if (giftNum == 0) return;
                        sendGifts(id, giftNum);
                        giftNum = 0;
                        giftNumFlag = true;
                        break;
                    case 1:     // 切换礼物类型
                        if (giftNum > 0) {
                            sendGifts(id, giftNum);
                            giftNum = 0;
                            giftNumFlag = true;
                        } else {
                            Log.d(TAG, "handleMessage: 并没有累计的礼物发送请求");
                        }
                        break;
                }
            }
        }
    };
    private double mStdbeanAmount;
    private String mStdCoin;
    private TextView mTvXqb;

    /**
     * 送礼
     *
     * @param id
     * @param giftNum
     */
    private void sendGifts(String id, int giftNum) {
        if (mProgress != null) {
            mProgress.setVisibility(View.VISIBLE);
        }
        String url = UrlUtils.URL_LIVE_GIFT_SEND;
        HttpUtils utils = new HttpUtils();
        RequestParams params = new RequestParams();
        params.addHeader("X-CustomToken", token);
        params.addBodyParameter("toAccid", mCreatorAccid);
        params.addBodyParameter("giftId", id);
        params.addBodyParameter("chatroomId", mChatroomId);
        params.addBodyParameter("counts", giftNum + "");

        utils.send(HttpRequest.HttpMethod.POST,
                url,
                params,
                new RequestCallBack<String>() {
                    @Override
                    public void onSuccess(ResponseInfo<String> responseInfo) {
                        String json = responseInfo.result;
                        LiveGiftSendBean bean = JSONObject.parseObject(json, LiveGiftSendBean.class);
                        if (bean.isSuccess()) {
                            LiveGiftSendBean.AttachDataBean attachData = bean.getAttachData();
                            if (attachData != null) {
                                double stdbeanAmount = attachData.getStdbeanAmount();  //  兴趣豆余额
                                mTvWindowTopup.setText("充值: " + (int) stdbeanAmount + "豆  >");
                            }
                        } else {
                            ToastUtil.showToast(mContext, bean.getErrMsg().toString());
                        }

                        Log.d(TAG, "onSuccess: sendGifts = " + json);
                        if (mProgress != null) {
                            mProgress.setVisibility(View.GONE);
                        }
                    }

                    @Override
                    public void onFailure(HttpException e, String s) {
                        ToastUtil.showToast(mContext, "请检查您的网络连接");
                        if (mProgress != null) {
                            mProgress.setVisibility(View.GONE);
                        }
                    }
                });
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_live_watch);
        mContext = this;
        SharedPreferences spToken = getSharedPreferences("mToken", Context.MODE_PRIVATE);
        token = UserManager.getInstance().getToken();


        //应用运行时，保持屏幕高亮，不锁屏
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);

        initVideoData();
        initVideoView();
        initButton();
        mProgress.setVisibility(View.VISIBLE);
        initChatroom();

        mGiftGridList = new ArrayList<>();
        initGiftList();
        initShare();
    }

    private void initShare() {
        shareDialog = new ShareDialog(LiveWatchActivity.this, R.style.ActionSheetDialogStyle);

        shareDialog.QQZone(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mlist.add(image);
                shareDialog.dismiss();
                ThirdPartyUtils.getInstance(LiveWatchActivity.this).shareUrlToZone(LiveWatchActivity.this, shareUrl, sharetitle, shareContent, mlist);

            }
        });

        shareDialog.qq(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                shareDialog.dismiss();
                ThirdPartyUtils.getInstance(LiveWatchActivity.this).shareToQQ(LiveWatchActivity.this, shareUrl, sharetitle, shareContent, image);
            }
        });
        shareDialog.wechat(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                shareDialog.dismiss();
                ThirdPartyUtils.getInstance(LiveWatchActivity.this).shareUrlToWechat(shareUrl, sharetitle, shareContent, image, false, true);
            }
        });
        shareDialog.wechat_zone(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                shareDialog.dismiss();
                ThirdPartyUtils.getInstance(LiveWatchActivity.this).shareUrlToWechat(shareUrl, sharetitle, shareContent, image, false, false);

            }
        });
        shareDialog.weibo(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                shareDialog.dismiss();
                ThirdPartyUtils.getInstance(LiveWatchActivity.this).shareToWeibo(LiveWatchActivity.this, sharetitle, image);

            }
        });
    }

    /**
     * 初始化礼物列表
     */
    private void initGiftList() {
        String url = UrlUtils.URL_LIVE_GIFT_LIST;
        HttpUtils utils = new HttpUtils();
        String token = UserManager.getInstance().getToken();
        RequestParams params = new RequestParams();
        params.addHeader("X-CustomToken", token);
        utils.send(HttpRequest.HttpMethod.POST,
                url,
                params,
                new RequestCallBack<String>() {
                    @Override
                    public void onSuccess(ResponseInfo<String> responseInfo) {
                        String json = responseInfo.result;
                        LiveGiftBean bean = JSONObject.parseObject(json, LiveGiftBean.class);
                        if (bean.isSuccess()) {
                            List<LiveGiftBean.AaDataBean> liveGiftList = bean.getAaData();
                            // 兴趣豆余额
                            mStdbeanAmount = bean.getAttachData().getStdbeanAmount();
                            mTvWindowTopup.setText("充值: " + (int) mStdbeanAmount + "豆  >");
                            mGiftGridList.clear();
                            mGiftGridList.addAll(liveGiftList);
                            initGridViewPager();
                        } else {
                            String errStr = bean.getErrMsg().toString();
                            Log.d(TAG, "onSuccess: err:" + errStr);
                        }

                    }

                    @Override
                    public void onFailure(HttpException e, String s) {

                    }
                });
    }

    /**
     * 初始化礼物列表界面
     */
    private void initGridViewPager() {
        // 行数
        int rowInOnePage = 2;
        // 每行的条目数
        int columnInOnePage = 5;
        mGvpWindowMain.setGridViewPagerDataAdapter(new GridViewPagerDataAdapter<LiveGiftBean
                .AaDataBean>(mGiftGridList, rowInOnePage, columnInOnePage) {

            @Override
            public BaseAdapter getGridViewAdapter(List<LiveGiftBean.AaDataBean> currentList, int
                    pageIndex) {
                return new MyGridViewAdapter(LiveWatchActivity.this, currentList);
            }

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id, int
                    pageIndex) {
                for (int i = 0; i < parent.getCount(); i++) {
                    View child = parent.getChildAt(i); // 获取单个条目
                    LinearLayout item = (LinearLayout) child.findViewById(R.id.ll_giftlist_item);
                    ImageView icon = (ImageView) child.findViewById(R.id.iv_giftlist_icon);

                    if (position == i) {
                        icon.setBackgroundResource(R.drawable.shape_bg_gift_grid_item_selector);
                        mHandler.removeCallbacksAndMessages(null);
                        mHandler.sendEmptyMessage(1);
                    } else {
                        icon.setBackgroundColor(Color.TRANSPARENT);
                    }
                }
                mGiftIndex = (position + (rowInOnePage * columnInOnePage) * pageIndex);
            }

            @Override
            public void getPageSum(int pageSum) {
                mPageSum = pageSum;
                initDot(pageSum);
            }
        });

        mGvpWindowMain.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int
                    positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                //旧点不亮
                dots.get(mCurrPageIndex % mPageSum).setSelected(false);
                //设置新的点
                mCurrPageIndex = position;
                //新点亮起来
                dots.get(mCurrPageIndex % mPageSum).setSelected(true);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    /**
     * 注册消息监听
     *
     * @param register
     */
    private void registerObservers(boolean register) {
        ChatRoomMemberCache.getInstance().registerObservers(register);
        NIMClient.getService(ChatRoomServiceObserver.class).observeKickOutEvent(kickOutObserver, register);
    }

    Observer<ChatRoomKickOutEvent> kickOutObserver = new Observer<ChatRoomKickOutEvent>() {
        @Override
        public void onEvent(ChatRoomKickOutEvent chatRoomKickOutEvent) {
            Toast.makeText(LiveWatchActivity.this, "被踢出聊天室", Toast.LENGTH_SHORT).show();
            exitChatRoom();
        /*    Intent intent = new Intent(mContext, .class);
            startActivity(intent);*/
            finish();
        }
    };

    /**
     * 初始化聊天室
     */
    private void initChatroom() {
        mChatRoomAdapter = new ChatRoomAdapter(mContext, mMessageData);
        mLvChatroom.setAdapter(mChatRoomAdapter);
        ChatRoomMemberCache.getInstance().InitMessageData(mChatRoomAdapter);
        mChatRoomAdapter.addItem(new ChatRoomItemBean1(0, getString(R.string.live_notice)));

        mChatRoomMemberAdapter = new ChatRoomMemberAdapter(mContext, mAvatarData);
        mHlvAvatars.setAdapter(mChatRoomMemberAdapter);
        ChatRoomMemberCache.getInstance().InitMemberData(mChatRoomMemberAdapter, mAvatarData, mChatroomId,
                mTvPeopleNum);
        ChatRoomMemberCache.getInstance().setOnCustomMessageListener(this);

        // 登录
        mLoginRequest = NIMClient.getService(AuthService.class).login(new LoginInfo(mAccid, mChatToken, mAppKey));
        mLoginRequest.setCallback(new RequestCallback<LoginInfo>() {
            @Override
            public void onSuccess(LoginInfo loginInfo) {
                mAccount = loginInfo.getAccount();
                String token1 = loginInfo.getToken();
                Log.d("liveingActivity", "聊天室登录成功" + mAccount + "=======" + token1);
                //                ToastUtil.showToast(mContext, "聊天室登录成功");

                // 进入聊天室
                EnterChatRoomData data = new EnterChatRoomData(mChatroomId);
                NIMClient.getService(ChatRoomService.class).enterChatRoom(data)
                        .setCallback(new RequestCallback<EnterChatRoomResultData>() {

                            @Override
                            public void onSuccess(EnterChatRoomResultData enterChatRoomResultData) {
                                // 聊天室成员信息
                                mMyMember = enterChatRoomResultData.getMember();
                                if (mMyMember.getMemberType() == MemberType.ADMIN) {
                                    isManage = true;
                                } else {
                                    isManage = false;
                                }

                                // 聊天室信息
                                ChatRoomInfo roomInfo = enterChatRoomResultData.getRoomInfo();

                                // 直播间创建者账号
                                mCreatorAccid = roomInfo.getCreator();
                                getAnchorMember(mCreatorAccid); // 获取主播个人信息


                                // 初始化观众列表
                                initAudienceList();

                                // 获取我的关注列表
                                myFollowList();

                                // 注册接受消息监听
                                registerObservers(true);

                            }


                            @Override
                            public void onFailed(int i) {
                                ToastUtil.showToast(mContext, "进入聊天室失败,请检查您的网络连接");
                                mProgress.setVisibility(View.GONE);
                            }

                            @Override
                            public void onException(Throwable throwable) {
                                ToastUtil.showToast(mContext, "进入聊天室失败,请检查您的网络连接");
                                mProgress.setVisibility(View.GONE);
                            }
                        });
            }

            @Override
            public void onFailed(int i) {
                ToastUtil.showToast(mContext, "聊天室登录失败,错误码:" + i);
                Log.d("StartLiveActivity", "聊天室登录失败" + i);
                mProgress.setVisibility(View.GONE);
            }

            @Override
            public void onException(Throwable throwable) {
                Log.d("StartLiveActivity", throwable.toString());
                mProgress.setVisibility(View.GONE);
            }
        });
    }

    /**
     * 获取主播个人信息
     *
     * @param creator
     */
    private void getAnchorMember(final String creator) {
        ChatRoomMemberCache.getInstance().fetchMember(mChatroomId, creator, new
                SimpleCallback<ChatRoomMember>() {
                    @Override
                    public void onResult(boolean success, ChatRoomMember member) {
                        if (success) {
                            // 主播个人信息
                            // 主播头像
                            mLiveAvatar = member.getAvatar();
                            mLiveName = member.getNick();

                            mTvName.setText(mLiveName + "");
                            Glide.with(mContext)
                                    .load(mLiveAvatar)
                                    .error(R.drawable.default_icon)
                                    .placeholder(R.drawable.default_icon)
                                    .transform(new GlideCircleTrans(mContext))
                                    .into(mIvAvatar);
                            mAnchorMember = member;
                            // 初始化分享
                            image = mLiveAvatar;
                            shareContent = mLiveName + "正在[兴趣班]直播,快来欣赏吧";
                        }

                    }
                });
    }

    /**
     * 消费掉framelayout的点击事件
     */
    private void setPrpgress() {
        mProgress.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return true;
            }
        });
    }


    private void initButton() {
        mRlMain = (FrameLayout) findViewById(R.id.rl_live_watch_main);

        mIvAvatar = (ImageView) findViewById(R.id.iv_livevideo_avatar);     // 主播头像
        mTvName = (TextView) findViewById(R.id.tv_livevideo_name);          // 主播昵称
        mVideoSpeak = (ImageView) findViewById(R.id.iv_livevideo_speak);    // 发言
        mVideoShare = (ImageView) findViewById(R.id.iv_livevideo_share);    // 分享
        mIvGone = (ImageView) findViewById(R.id.iv_livevideo_gone);         // 隐藏
        mRlGone = (RelativeLayout) findViewById(R.id.rl_livevideo_gone);    // 可以被隐藏的界面
        mIvVideoExit = (ImageView) findViewById(R.id.iv_livevideo_exit);    // 退出
        mTvFollow = (TextView) findViewById(R.id.tv_livevideo_follow);      // 关注主播
        mTvRoomid = (TextView) findViewById(R.id.tv_livevideo_roomid);      // 房间号
        mTvRoomid.setText("直播号: " + mChatroomId);
        mTvXqb = (TextView) findViewById(R.id.tv_livevideo_xqb);            // 兴趣币
        if (mStdCoin != null) {
            mTvXqb.setText(mStdCoin);
        }
        mTvPeopleNum = (TextView) findViewById(R.id.tv_livevideo_num);      // 观众数
        mIvGift = (ImageView) findViewById(R.id.iv_livevideo_gift);         // 送礼
        mLvChatroom = (ListView) findViewById(R.id.lv_livevideo_chatroom);  // 聊天室界面
        mHlvAvatars = (HorizontalListView) findViewById(R.id.hlv_livevideo_avatars);  //上方展示的头像列表


        // 发言
        mSpeakView = getLayoutInflater().inflate(R.layout.popupwindow_liveing_speak, null);     // 发言
        mDetailsView = getLayoutInflater().inflate(R.layout.popupwindow_liveing_details, null); // 用户信息弹窗
        mGiftView = getLayoutInflater().inflate(R.layout.popupwindow_live_gift, null);   // 礼物



        mEtSpeak = (EditText) mSpeakView.findViewById(R.id.et_liveing_speak);
        mBtnSpeakSend = (Button) mSpeakView.findViewById(R.id.btn_liveing_speak_send);          // 发送消息

        // 关注页面(用户资料)
        // 用户详情页头像
        mIvWindowAvatar = (ImageView) mDetailsView.findViewById(R.id.iv_liveing_window_avatar); //头像
        mTvWindowId = (TextView) mDetailsView.findViewById(R.id.tv_liveing_details_id);         // ID
        mTvWindowAge = (TextView) mDetailsView.findViewById(R.id.tv_liveing_details_age);       // 年龄
        mTvWindowName = (TextView) mDetailsView.findViewById(R.id.tv_liveing_details_name);     // 姓名
        mTvWindowFllows = (TextView) mDetailsView.findViewById(R.id.tv_liveing_details_fllows); // 关注数
        mTvWindowFans = (TextView) mDetailsView.findViewById(R.id.tv_liveing_details_fans);     // 粉丝
        mTvWindowGet = (TextView) mDetailsView.findViewById(R.id.tv_liveing_details_get);       // 收礼
        mTvWindowGive = (TextView) mDetailsView.findViewById(R.id.tv_liveing_details_give);     // 送礼

        mLlWindowShutup = (LinearLayout) mDetailsView.findViewById(R.id.ll_liveing_details_shutup); // 禁言or拉黑
        mTvWindowShutup = (TextView) mDetailsView.findViewById(R.id.tv_liveing_details_shutup);
        mIvWindowShutup = (ImageView) mDetailsView.findViewById(R.id.iv_liveing_details_shutup);
        mLlWindowOut = (LinearLayout) mDetailsView.findViewById(R.id.ll_liveing_details_out);       // 踢人or举报
        mTvWindowOut = (TextView) mDetailsView.findViewById(R.id.tv_liveing_details_out);
        mIvWindowOut = (ImageView) mDetailsView.findViewById(R.id.iv_liveing_details_out);

        mProgress = (FrameLayout) findViewById(R.id.frame_progress); // 加载动画

        mTvWindowFllow = (TextView) mDetailsView.findViewById(R.id.tv_liveing_details_fllow); // 关注按钮
        mTvWindowSetManage = (TextView) mDetailsView.findViewById(R.id.tv_liveing_details_setManage); // 设置场控
        mTvWindowSetManage.setVisibility(View.GONE);
        // 礼物

        mGvpWindowMain = (GridViewPager) mGiftView.findViewById(R.id.gridvp_gift_main); // viewPager
        mLlLayoutDots = (LinearLayout) mGiftView.findViewById(R.id.llLayoutDots);       // 圆点指示器
        mTvWindowTopup = (TextView) mGiftView.findViewById(R.id.tv_window_gift_topup);  // 充值
        mTvWindowSend = (TextView) mGiftView.findViewById(R.id.tv_window_gift_send);    // 发送礼物
        mLlgiftcontent = (LinearLayout) findViewById(R.id.ll_live_gift_content);        // 礼物出现的位置


        mIvVideoExit.setOnClickListener(this);          // 退出
        mVideoSpeak.setOnClickListener(this);           // 发言
        mVideoShare.setOnClickListener(this);           // 分享
        mIvGone.setOnClickListener(this);               // 隐藏控件
        mTvFollow.setOnClickListener(this);
        mIvGift.setOnClickListener(this);               // 送礼
        mIvAvatar.setOnClickListener(this);

        mBtnSpeakSend.setOnClickListener(this);
        mLlWindowOut.setOnClickListener(this);          //踢出or举报
        mLlWindowShutup.setOnClickListener(this);       //禁言or拉黑
        mTvWindowSetManage.setOnClickListener(this);
        mTvWindowFllow.setOnClickListener(this);

        mTvWindowTopup.setOnClickListener(this);        // 兴趣豆充值
        mTvWindowSend.setOnClickListener(this);         // 发送礼物

        mHlvAvatars.setOnItemClickListener(this);       // 头像列表
        mLvChatroom.setOnItemClickListener(this);       // 消息列表

        ChatRoomMemberCache.getInstance().initGiftView(mContext, mLlgiftcontent);   // 初始化礼物动画

        setPrpgress(); //消费缓冲动画的点击事件
    }

    private void initVideoView() {
        //  创建NEVideoView 和 NEMediaController 实例
        mVideoView = (NEVideoView) findViewById(R.id.view_live_watch_video);

        mMediaController = new NEMediaController(this);
        //  将 NEMediaController 与 NEVideoView 进行绑定
        mVideoView.setMediaController(mMediaController);

        // 设置缓冲动画
        //mLoadingView = findViewById(R.id.buffering_prompt);
        //mVideoView.setBufferingIndicator(mLoadingView);

        //  设置缓冲策略低        延时和抗抖动两种模式，0为低延时，1为抗抖动
        mVideoView.setBufferStrategy(mMediaType);


        mVideoView.setHardwareDecoder(mHardware); //硬件解码还是软件解码
        mVideoView.setVideoPath(mVideoPath); // 拉流
        mVideoView.requestFocus();
        mVideoView.start();
    }

    private void initVideoData() {
        Intent intent = getIntent();
        mChatroomId = intent.getStringExtra("chatroomId");
        mVideoPath = intent.getStringExtra("rtmpPullUrl");
        mAccid = intent.getStringExtra("accid");
        mAppKey = intent.getStringExtra("appKey");
        mChatToken = intent.getStringExtra("chatToken");
        mMyFollowes = intent.getStringExtra("myFollowes"); // 当前用户的关注列表
        mLiveRoomId = intent.getStringExtra("roomId"); // 当前直播间id
        mStdCoin = intent.getStringExtra("stdCoin"); // 当前用户兴趣币shareid
        shareid = mLiveRoomId;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_livevideo_speak: // 发言按钮
                // 监听软键盘状态
                if (NoDoubleClick.isNotDoubleClick()) {
                    final PopupWindow popSpeakWindow = showPopupWindow(mSpeakView);
                    new KeyboardChangeListener(this).setKeyBoardListener(new KeyboardChangeListener.KeyBoardListener() {
                        @Override
                        public void onKeyboardChange(boolean isShow, int keyboardHeight) {
                            if (isShow) {
                                popSpeakWindow.dismiss();
                            }
                        }
                    });
                    // 获取焦点
                    mEtSpeak.requestFocus();
                    InputMethodManager imm = (InputMethodManager) mEtSpeak.getContext().getSystemService(Context
                            .INPUT_METHOD_SERVICE);
                    imm.toggleSoftInput(0, InputMethod.SHOW_FORCED);
                }
                break;

            case R.id.iv_livevideo_share:   //分享
                shareDialog.show();
                break;

            case R.id.iv_livevideo_gone:    //隐藏多余界面
                mFlagGone = !mFlagGone;
                if (mFlagGone) {
                    mRlGone.setVisibility(View.GONE);
                    mIvGone.setImageResource(R.mipmap.livein_iocn_cls);
                } else {
                    mRlGone.setVisibility(View.VISIBLE);
                    mIvGone.setImageResource(R.mipmap.livein_iocn_cls_normal);
                }
                break;

            case R.id.iv_livevideo_exit:    //退出

                finish();
                break;
            case R.id.iv_livevideo_gift:    //弹出礼物菜单
                if (NoDoubleClick.isNotDoubleClick()) {
                    showPopupWindow(mGiftView);
                }
                break;

            case R.id.iv_livevideo_avatar:  //主播头像
                if (mAnchorMember == null) {
                    return;
                }
                mChatRoomMember = mAnchorMember;
                initUsetInfo(mAnchorMember);
                if (NoDoubleClick.isNotDoubleClick()) {
                    showPopupWindow(mDetailsView);
                }
                break;

            case R.id.tv_livevideo_follow:  //关注主播
                if (NoDoubleClick.isNotDoubleClick()) {
                    try {
                        followAdd(mAnchorMember.getAccount());
                    }catch (Exception ex){
                        ex.printStackTrace();
                    }

                }
                break;

            case R.id.btn_liveing_speak_send:   // 发送消息按钮
                String content = mEtSpeak.getText().toString();
                if (!TextUtils.isEmpty(content.trim())) {
                    sendMessage(content);
                    mEtSpeak.setText("");
                    //                    ToastUtil.showToast(mContext, "发送消息成功");
                } else {
                    ToastUtil.showToast(mContext, "发送的内容不能为空");
                }
                break;


            case R.id.ll_liveing_details_out:   // 踢人or举报

                if (mChatRoomMember.getAccount().equals(mMyMember.getAccount())) {
                    ToastUtil.showToast(mContext, "不能以自己为目标");
                    return;
                }
                if (isManage) { //如果用户身份为管理员    执行踢人操作
                    outMember(mChatRoomMember);
                }
                break;
            case R.id.ll_liveing_details_shutup: // 禁言or拉黑
                if (mChatRoomMember.getAccount().equals(mMyMember.getAccount())) {
                    ToastUtil.showToast(mContext, "不能以自己为目标");
                    return;
                }
                if (isManage) { //如果用户身份为管理员  执行禁言操作
                    shutupBitch(mChatRoomMember);
                } else {
                    addBlack(mChatRoomMember); // 拉黑
                }
                break;

            case R.id.tv_liveing_details_fllow:    // 关注
                if (mChatRoomMember.getAccount().equals(mMyMember.getAccount())) {
                    ToastUtil.showToast(mContext, "不能以自己为目标");
                    return;
                }
                if (mFollowString.contains(mChatRoomMember.getAccount())) {
                    mTvWindowFllow.setText("取消关注");
                } else {
                    mTvWindowFllow.setText("关注");
                }
                followAdd(mChatRoomMember.getAccount());
                break;

            case R.id.tv_window_gift_send:      // 发送礼物
                if (mGiftIndex == -1) {
                    return;
                }
                if (mGiftGridList != null && mGiftGridList.size() > mGiftIndex) {
                    giftNum++;
                    if (giftNumFlag) {
                        giftNumFlag = false;
                        mHandler.sendEmptyMessageDelayed(0, 1000); //打包礼物发送请求,统计点击次数1秒后发送
                    }
                }

                break;
            case R.id.tv_window_gift_topup:     // 充值兴趣豆
//                Intent intent1 = new Intent(LiveWatchActivity.this, ExchangeFunBeanActivity.class);
//                intent1.putExtra(ExchangeFunBeanActivity.ARG_SER_WALLET, mWallet);
//                startActivity(intent1);

//                Bundle bundle = new Bundle();
//                if (mStdbeanAmount != -1) {
//                    bundle.putDouble("coinNum", mStdbeanAmount);
//                    bundle.putString("alias", "stdbean");
//                    intent1.putExtras(bundle);
//                    startActivity(intent1);
//                }
                requestWallet();
                break;

//            Intent intent=new Intent(this,ExchangeFunBeanActivity.class);
//            intent.putExtra(ExchangeFunBeanActivity.ARG_SER_WALLET,mWallet);
//            startActivity(intent);
        }
    }

    /**
     * 请求钱包信息
     */
//    private void requestWallet(){
//        Request request=Request.obtain(MeInterface.POST_WALLET);
//        request.setListener(new SimpleListener<Response<ResponseWallet>>(){
//
//            @Override
//            public void onResponseListener(Request r, Response<ResponseWallet> result){
//                DecimalFormat df=new DecimalFormat("##.##");
//                mRemain.setText(df.format(result.data.money.coinNum));
//
//                mWallet=result.data;
//            }
//        });
//        net(request);
//    }

    private void requestWallet() {
        HttpUtils httpUtils = new HttpUtils();
        RequestParams params = new RequestParams();
        params.addHeader("X-CustomToken", token);

        String url = UrlUtils.URL_MYBLACK_WALET;
        httpUtils.send(HttpRequest.HttpMethod.POST,
                url,
                params, new RequestCallBack<String>() {

                    @Override
                    public void onSuccess(ResponseInfo<String> responseInfo) {
                        try {
                            Gson gson=new Gson();
                            Response<ResponseWallet> response=gson.fromJson(responseInfo.result,new TypeToken<Response<ResponseWallet>>(){}.getType());
                            if(response.success){
                                ResponseWallet data = response.data;

                                Intent intent1 = new Intent(LiveWatchActivity.this, ExchangeFunBeanActivity.class);
                                intent1.putExtra(ExchangeFunBeanActivity.ARG_SER_WALLET, data);
                                startActivity(intent1);
                            }


                        }catch (Exception ex){
                            ex.printStackTrace();
                        }



                    }

                    @Override
                    public void onFailure(HttpException e, String s) {
                        HttpException e1 = e;
                    }
                });
    }


    /**
     * 拉黑
     *
     * @param member
     */
    private void addBlack(ChatRoomMember member) {
        final String accid = mChatUserInfoBean.getAccid();

        HttpUtils httpUtils = new HttpUtils();
        RequestParams params = new RequestParams();
        params.addHeader("X-CustomToken", token);
        params.addBodyParameter("targetAccid", accid);
        params.addBodyParameter("syskey", BLACKLIST);
        String url = UrlUtils.URL_MYBLACK_ADD;
        httpUtils.send(HttpRequest.HttpMethod.POST,
                url,
                params, new RequestCallBack<String>() {
                    @Override
                    public void onSuccess(ResponseInfo<String> responseInfo) {
                        String json = responseInfo.result;
                        IMBlackListBean bean = JSONObject.parseObject(json, IMBlackListBean.class);
                        String aaData = bean.getAaData();
                        if (aaData == null) {
                            mTvWindowShutup.setText("拉黑");
                        } else {
                            if (aaData.contains(accid)) {
                                mTvWindowShutup.setText("取消拉黑");
                            } else {
                                mTvWindowShutup.setText("拉黑");
                            }
                        }
                    }

                    @Override
                    public void onFailure(HttpException e, String s) {

                    }
                });

    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        switch (parent.getId()) {
            case R.id.hlv_livevideo_avatars:
                mChatRoomMember = mAvatarData.get(position);
                if (mChatRoomMember != null) {
                    initUsetInfo(mChatRoomMember); // 初始化用户信息
                    int memberLevel = mChatRoomMember.getMemberLevel();
                    showPopupWindow(mDetailsView);
                }

                break;
            case R.id.lv_livevideo_chatroom:
                int item_type = mMessageData.get(position).getItem_type();
                ChatRoomMessage message = null;
                if (item_type == 0) { // 系统通知
                    ChatRoomMember member = ((ChatRoomItemBean1) mMessageData.get(position)).getMember();
                    if (member != null) {
                        mChatRoomMember = member;
                        initUsetInfo(member); // 初始化用户信息
                        showPopupWindow(mDetailsView);
                    }
                } else if (item_type == 1) { // 系统消息
                    message = ((ChatRoomItemBean1) mMessageData.get(position)).getMessage();

                } else if (item_type == 2) { // 聊天消息
                    message = ((ChatRoomItemBean2) mMessageData.get(position)).getMessage();
                }
                if (message != null) { // 获取用户详细信息
                    String account = message.getFromAccount(); // 用户id
                    // 根据用户id获取用户的member
                    ChatRoomMemberCache.getInstance().fetchMember(mChatroomId, account, new
                            SimpleCallback<ChatRoomMember>() {
                                @Override
                                public void onResult(boolean success, ChatRoomMember member) {
                                    if (success) {
                                        mChatRoomMember = member;
                                        initUsetInfo(member); // 初始化用户信息
                                        showPopupWindow(mDetailsView);
                                    }
                                }
                            });
                }
                break;
        }

    }

    /**
     * 初始化用户信息
     */
    private void initUsetInfo(final ChatRoomMember member) {
        mProgress.setVisibility(View.VISIBLE);
        final String account = member.getAccount(); // 用户账号
        ArrayList accounts = new ArrayList();
        accounts.add(account);
        NIMClient.getService(UserService.class).fetchUserInfo(accounts).setCallback(new RequestCallback<List
                <NimUserInfo>>() {
            @Override
            public void onSuccess(List<NimUserInfo> userInfos) {
                if (userInfos != null && userInfos.size() > 0) {
                    String json = userInfos.get(0).getExtension();
                    mChatUserInfoBean = JSONObject.parseObject(json, ChatUserInfoBean.class);
                    if (mChatUserInfoBean != null) {
                        String avatar = member.getAvatar(); // 用户头像
                        // 添加头像
                        Glide.with(mContext)
                                .load(avatar)
                                .error(R.drawable.default_icon)
                                .placeholder(R.drawable.default_icon)
                                .transform(new GlideCircleTrans(mContext))
                                .into(mIvWindowAvatar);
                        if (mChatUserInfoBean != null) {
                            String name = member.getNick(); // 用户昵称
                            String userId = mChatUserInfoBean.getId();   // 用户ID
                            String age = mChatUserInfoBean.getAge() != null ? mChatUserInfoBean.getAge() : ""; // 用户年龄
                            int fllows = mChatUserInfoBean.getFllows();  // 关注数
                            int fans = mChatUserInfoBean.getFans();      // 粉丝数
                            int get = mChatUserInfoBean.getGet();        // 收礼数
                            int give = mChatUserInfoBean.getGive();      // 送礼数

                            mTvWindowId.setText("id: " + userId);
                            mTvWindowAge.setText(age);
                            mTvWindowName.setText(name);
                            mTvWindowFllows.setText(fllows + "");
                            mTvWindowFans.setText(fans + "");
                            mTvWindowGet.setText(get + "");
                            mTvWindowGive.setText(give + "");
                            if (mFollowString.contains(account)) {
                                mTvWindowFllow.setText("取消关注");
                                if (account.equals(mAnchorMember.getAccount())) {
                                    mTvFollow.setText("取消关注");
                                }
                            } else {
                                mTvWindowFllow.setText("关注");
                                if (account.equals(mAnchorMember.getAccount())) {
                                    mTvFollow.setText("关注");
                                }
                            }
                            // 获取我的个人信息
                            ChatRoomMemberCache.getInstance().fetchMember(mChatroomId, mAccid, new
                                    SimpleCallback<ChatRoomMember>() {
                                        @Override
                                        public void onResult(boolean success, ChatRoomMember result) {
                                            if (success) {
                                                mMyMember = result; // 更新本人的信息
                                                if (mMyMember.getMemberType() == MemberType.ADMIN) { // 如果为管理员
                                                    isManage = true;
                                                    mTvWindowOut.setText("踢人");
                                                    mIvWindowOut.setImageResource(R.mipmap
                                                            .direct_seeding_iconfont_icon26);
                                                    mIvWindowShutup.setImageResource(R.mipmap
                                                            .direct_seeding_remindiconfont_jinyan);
                                                    if (member.isMuted()) {
                                                        mTvWindowShutup.setText("解除禁言");
                                                    } else {
                                                        mTvWindowShutup.setText("禁言");
                                                    }
                                                } else {
                                                    isManage = false;
                                                    mTvWindowOut.setText("举报");
                                                    mIvWindowOut.setImageResource(R.mipmap
                                                            .direct_seeding_remind);
                                                    boolean inBlackList = inBlackList(account);
                                                    mIvWindowShutup.setImageResource(R.mipmap
                                                            .direct_seeding_shield_ing);
                                                    if (inBlackList) {
                                                        mTvWindowShutup.setText("取消拉黑");
                                                    } else {
                                                        mTvWindowShutup.setText("拉黑");
                                                    }
                                                }
                                                mProgress.setVisibility(View.GONE);
                                            } else {
                                                ToastUtil.showToast(mContext, "获取个人信息失败,请检查您的网络连接");
                                                mProgress.setVisibility(View.GONE);
                                            }
                                        }
                                    });
                            mProgress.setVisibility(View.GONE);
                        }
                    }
                }
            }

            @Override
            public void onFailed(int i) {
                ToastUtil.showToast(mContext, "从服务器获取用户资料失败" + i);
                mProgress.setVisibility(View.GONE);
            }

            @Override
            public void onException(Throwable throwable) {
                mProgress.setVisibility(View.GONE);
            }
        });
    }

    /**
     * 判断当前用户是否在个人黑名单列表中
     *
     * @param account
     * @return
     */
    private boolean inBlackList(String account) {
        boolean inBlackList = NIMClient.getService(FriendService.class).isInBlackList(account);
        return inBlackList;
    }

    /**
     * 聊天室相关
     * 发送消息
     *
     * @param content
     **/
    private void sendMessage(final String content) {

        if (content != null) {
            // 创建文本消息
            final ChatRoomMessage message = ChatRoomMessageBuilder.createChatRoomTextMessage(
                    mChatroomId, // 聊天室id
                    content // 文本内容
            );
            // 发送消息。如果需要关心发送结果，可设置回调函数。发送完成时，会收到回调。如果失败，会有具体的错误码。
            NIMClient.getService(ChatRoomService.class).sendMessage(message, true).setCallback(new RequestCallback<Void>() {
                @Override
                public void onSuccess(Void aVoid) {
                    Log.d("LiveWatchActivity", "消息发送成功" + content);
                    if (message.getContent() != null && message.getMsgType() == MsgTypeEnum.text) {
                        mChatRoomAdapter.addItem(new ChatRoomItemBean2(2, message));
                    }
                }

                @Override
                public void onFailed(int i) {
                    switch (i) {
                        case 13004: // 被禁言
                            ToastUtil.showToast("您已被禁言");
                            break;
                        default:
                            ToastUtil.showToast("消息发送失败,错误码:" + i);
                            break;
                    }
                    Log.d("LiveWatchActivity", "消息发送失败" + i);
                }

                @Override
                public void onException(Throwable throwable) {
                    Log.d("LiveWatchActivity", "消息发送错误" + throwable);
                    ToastUtil.showToast(mContext, "消息发送错误" + throwable);
                }
            });
        }
    }

    /**
     * 从底部弹出对话框
     */
    private PopupWindow showPopupWindow(View view) {
        final PopupWindow popupWindow = new PopupWindow(view, WindowManager.LayoutParams.MATCH_PARENT,
                WindowManager.LayoutParams
                        .WRAP_CONTENT, true);

        // 设置背景透明实现变暗效果
        /*WindowManager.LayoutParams layoutParams = getWindow().getAttributes();
        layoutParams.alpha = 0.7f;
        getWindow().setAttributes(layoutParams);*/
        popupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                WindowManager.LayoutParams layoutParams = getWindow().getAttributes();
                layoutParams.alpha = 1f;
                getWindow().setAttributes(layoutParams);
            }
        });
        popupWindow.setAnimationStyle(R.style.anim_bottombar);
        popupWindow.setTouchable(true);
        popupWindow.setOutsideTouchable(true);
        popupWindow.setBackgroundDrawable(new BitmapDrawable(getResources(), (Bitmap) null));

        popupWindow.getContentView().setFocusableInTouchMode(true);
        popupWindow.getContentView().setFocusable(true);
        popupWindow.getContentView().setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (keyCode == KeyEvent.KEYCODE_MENU && event.getRepeatCount() == 0 && event.getAction()
                        == KeyEvent.ACTION_DOWN) {
                    if (popupWindow != null && popupWindow.isShowing()) {
                        popupWindow.dismiss();
                    }
                    return true;
                }
                return false;
            }
        });
        popupWindow.setSoftInputMode(PopupWindow.INPUT_METHOD_NEEDED);
        popupWindow.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);
        if (mRlMain != null) {
            popupWindow.showAtLocation(mRlMain, Gravity.BOTTOM, 0, 0);
        }

        return popupWindow;
    }

    /**
     * 初始化观众列表
     */
    private void initAudienceList() {

        // if (!isNormalEmpty) {
        //     拉取固定在线成员
        //     getMembers(MemberQueryType.ONLINE_NORMAL, updateTime, 0);
        // } else {
        //     拉取非固定成员
        getMembers(MemberQueryType.GUEST, enterTime, 0);
        //        }
    }

    /**
     * 获取成员列表
     */
    private void getMembers(final MemberQueryType memberQueryType, final long time, int limit) {
        ChatRoomMemberCache.getInstance().fetchRoomMembers(mChatroomId, memberQueryType, 0, 50, new
                SimpleCallback<List<ChatRoomMember>>() {
                    @Override
                    public void onResult(boolean success, List<ChatRoomMember> result) {
                        if (success) {
                            /*addMembers(result);
                            if (memberQueryType == MemberQueryType.ONLINE_NORMAL && result.size() < LIMIT) {
                                isNormalEmpty = true; // 固定成员已经拉完
                                getMembers(MemberQueryType.GUEST, enterTime, result.size());
                            }*/
                            mAvatarData.clear();
                            mAvatarData.addAll(result);
                            mChatRoomMemberAdapter.notifyDataSetChanged();
                        }
                    }
                });
    }

    private void addMembers(List<ChatRoomMember> members) {
        for (ChatRoomMember member : members) {
            if (!isNormalEmpty) {
                updateTime = member.getUpdateTime();
            } else {
                enterTime = member.getEnterTime();
            }
            if (memberCache.containsKey(member.getAccount())) {
                mAvatarData.remove(memberCache.get(member.getAccount()));
            }
            memberCache.put(member.getAccount(), member);

            mAvatarData.add(member);
        }
        Collections.sort(mAvatarData, comp);
        mChatRoomMemberAdapter.notifyDataSetChanged();
    }

    /**
     * 退出聊天室
     */
    private void exitChatRoom() {
        NIMClient.getService(ChatRoomService.class).exitChatRoom(mChatroomId);
        clearCache();
    }


    @Override
    protected void onDestroy() {
        mVideoView.release_resource();
        exitChatRoom();
        registerObservers(false);
        super.onDestroy();
    }

    private void clearCache() {
        updateTime = 0;
        enterTime = 0;
        isNormalEmpty = false;
        mAvatarData.clear();
        memberCache.clear();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();

        finish();
    }

    private static Map<MemberType, Integer> compMap = new HashMap<>();

    static {
        compMap.put(MemberType.CREATOR, 0);
        compMap.put(MemberType.ADMIN, 1);
        compMap.put(MemberType.NORMAL, 2);
        compMap.put(MemberType.LIMITED, 3);
        compMap.put(MemberType.GUEST, 4);
    }

    private static Comparator<ChatRoomMember> comp = new Comparator<ChatRoomMember>() {
        @Override
        public int compare(ChatRoomMember lhs, ChatRoomMember rhs) {
            if (lhs == null) {
                return 1;
            }

            if (rhs == null) {
                return -1;
            }
            return compMap.get(lhs.getMemberType()) - compMap.get(rhs.getMemberType());
        }
    };

    /**
     * 踢人
     *
     * @param chatRoomMember
     */
    private void outMember(final ChatRoomMember chatRoomMember) {
        Map<String, Object> reason = new HashMap<>();
        reason.put("reason", "请文明发言");
        NIMClient.getService(ChatRoomService.class).kickMember(mChatroomId, chatRoomMember.getAccount(), reason)
                .setCallback(new RequestCallback<Void>() {
                    @Override
                    public void onSuccess(Void param) {
                        ToastUtil.showToast(mContext, "踢人成功");
                        ChatRoomMember del = null;
                        for (ChatRoomMember member : mAvatarData) {
                            if (member.getAccount().equals(chatRoomMember.getAccount())) {
                                del = member;
                                break;
                            }
                        }

                        if (del != null) {
                            mAvatarData.remove(del);
                            memberCache.remove(del.getAccount());
                        }
                        mChatRoomMemberAdapter.notifyDataSetChanged();
                    }

                    @Override
                    public void onFailed(int code) {
                        Log.d(TAG, "kick member failed:" + code);
                    }

                    @Override
                    public void onException(Throwable exception) {
                        Log.d(TAG, "kick member exception:" + exception);
                    }
                });
    }

    /**
     * 禁言 只有场控及主播可以使用且不能以自己为目标
     *
     * @param chatRoomMember
     */
    private void shutupBitch(ChatRoomMember chatRoomMember) {
        mProgress.setVisibility(View.VISIBLE);
        String account = chatRoomMember.getAccount(); // 当前被选中用户id
        MemberOption option = new MemberOption(mChatroomId, account);
        NIMClient.getService(ChatRoomService.class)
                .markChatRoomMutedList(!chatRoomMember.isMuted(), option)
                .setCallback(new RequestCallback<ChatRoomMember>() {

                    @Override
                    public void onSuccess(ChatRoomMember member) {
                        if (member.isMuted()) {
                            mTvWindowShutup.setText("解除禁言");
                        } else {
                            mTvWindowShutup.setText("禁言");
                        }
                        mProgress.setVisibility(View.GONE);
                    }

                    @Override
                    public void onFailed(int i) {
                        ToastUtil.showToast(mContext, "禁言失败" + i);
                        mProgress.setVisibility(View.GONE);
                    }

                    @Override
                    public void onException(Throwable throwable) {
                        mProgress.setVisibility(View.GONE);
                    }
                });
    }

    /**
     * 添加/取消关注
     */
    private void followAdd(final String accid) {
        mProgress.setVisibility(View.VISIBLE);
        HttpUtils utils = new HttpUtils();
        RequestParams params = new RequestParams();
        params.addHeader("X-CustomToken", token);
        params.addBodyParameter("targetAccid", accid);
        String url = UrlUtils.URL_FOLLOW_ADD;
        utils.send(HttpRequest.HttpMethod.POST,
                url,
                params,
                new RequestCallBack<String>() {
                    @Override
                    public void onSuccess(ResponseInfo<String> responseInfo) {
                        String json = responseInfo.result;
                        ChatRoomAddFollowBean bean = JSONObject.parseObject(json,
                                ChatRoomAddFollowBean.class);
                        mFollowString = bean.getAaData() == null ? "" : bean.getAaData();
                        if (mFollowString.contains(accid)) {
                            if (accid.equals(mCreatorAccid)) {
                                mTvFollow.setText("取消关注");
                            }
                            mTvWindowFllow.setText("取消关注");
                        } else {
                            mTvWindowFllow.setText("关注");
                            if (accid.equals(mCreatorAccid)) {
                                mTvFollow.setText("关注");
                            }
                        }
                        mProgress.setVisibility(View.GONE);
                    }

                    @Override
                    public void onFailure(HttpException e, String s) {
                        mProgress.setVisibility(View.GONE);
                        ToastUtil.showToast(mContext, "操作失败" + s);
                    }
                });

    }

    /**
     * 个人关注列表
     */
    private void myFollowList() {
        HttpUtils utils = new HttpUtils();
        String url = UrlUtils.URL_GET_FOLLOW;
        RequestParams params = new RequestParams();
        params.addHeader("X-CustomToken", token);
        utils.send(HttpRequest.HttpMethod.POST,
                url,
                new RequestCallBack<String>() {
                    @Override
                    public void onSuccess(ResponseInfo<String> responseInfo) {
                        String json = responseInfo.result;
                        ChatRoomFollowBean bean = JSONObject.parseObject(json, ChatRoomFollowBean.class);
                        ChatRoomFollowBean.AaDataBean aaData = bean.getAaData();
                        if (aaData != null) {
                            List<ChatRoomFollowBean.AaDataBean.FollowesBean> followes = aaData.getFollowes();
                            if (followes != null && followes.size() > 0) {
                                StringBuffer buffer = new StringBuffer(); // 获取关注列表添加到字符串
                                for (ChatRoomFollowBean.AaDataBean.FollowesBean followe : followes) {
                                    String targetAccid = followe.getTargetAccid(); //  被关注者的accid
                                    buffer.append(targetAccid + ",");
                                }
                                mFollowString = buffer.toString();
                            }
                        }
                        if (mFollowString.contains(mCreatorAccid)) {
                            mTvFollow.setText("取消关注");
                        } else {
                            mTvFollow.setText("关注");
                        }
                        mProgress.setVisibility(View.GONE);
                    }

                    @Override
                    public void onFailure(HttpException e, String s) {
                        mProgress.setVisibility(View.GONE);
                    }
                });

    }


    private void initDot(int pageSum) {
        mDotParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT);
        mDotParams.rightMargin = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 10,
                getResources().getDisplayMetrics());
        if (pageSum > 0) {
            for (int i = 0; i < pageSum; i++) {
                ImageView imageView = new ImageView(getApplicationContext());

                //添加背景 选择器
                //imageView.setBackgroundResource(R.drawable.dot_selector);
                //添加图片选择器
                imageView.setImageResource(R.drawable.dot_selector);

                //默认为不选择灰色
                imageView.setSelected(false);

                //添加到dot容器
                mLlLayoutDots.addView(imageView, mDotParams);

                dots.add(imageView);
            }
            //设置第一个为选择状态
            dots.get(0).setSelected(true);
        }

    }

    /**
     * 接收到聊天室自定义消息的回调
     *
     * @param stdCoin
     */
    @Override
    public void onGitGiftMessage(String stdCoin) {
        if (stdCoin == null) return;
        mTvXqb.setText(stdCoin);
    }

    /**
     * 直播结束回调
     *
     * @param json
     */
    @Override
    public void onLiveExitMessage(String json) {
        LiveExitDataBean bean = JSONObject.parseObject(json, LiveExitDataBean.class);
        List<LiveExitDataBean.AttachBean> attach = bean.getAttach();
        if (attach == null) return;
        LiveExitDataBean.AttachBean attachBean = attach.get(0);
        if (attachBean == null) return;
        // 主播头像
        String icon = attachBean.getIcon();
        // 直播时长
        String caseTimeStr = attachBean.getCaseTimeStr();
        // 直播间标题
        String roomName = attachBean.getRoomName();
        // 在线人数
        String onlineUserCount = attachBean.getOnlineUserCount();
        Intent intent = new Intent(mContext, LiveLookExitActivity.class);
        intent.putExtra("onlineUserCount", onlineUserCount);
        intent.putExtra("icon", icon);
        intent.putExtra("roomName", roomName);
        startActivity(intent);
        finish();
    }
}
