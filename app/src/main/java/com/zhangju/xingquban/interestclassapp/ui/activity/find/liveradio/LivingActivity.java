package com.zhangju.xingquban.interestclassapp.ui.activity.find.liveradio;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.media.AudioFormat;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.text.TextUtils;
import android.util.Log;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.WindowManager;
import android.view.inputmethod.InputMethod;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
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
import com.fastlib.net.DefaultDownload;
import com.fastlib.net.Request;
import com.fastlib.net.SimpleListener;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.RequestParams;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest;
import com.netease.LSMediaCapture.lsMediaCapture;
import com.netease.LSMediaCapture.lsMessageHandler;
import com.netease.nimlib.sdk.AbortableFuture;
import com.netease.nimlib.sdk.NIMClient;
import com.netease.nimlib.sdk.RequestCallback;
import com.netease.nimlib.sdk.auth.AuthService;
import com.netease.nimlib.sdk.auth.LoginInfo;
import com.netease.nimlib.sdk.chatroom.ChatRoomMessageBuilder;
import com.netease.nimlib.sdk.chatroom.ChatRoomService;
import com.netease.nimlib.sdk.chatroom.constant.MemberQueryType;
import com.netease.nimlib.sdk.chatroom.constant.MemberType;
import com.netease.nimlib.sdk.chatroom.model.ChatRoomInfo;
import com.netease.nimlib.sdk.chatroom.model.ChatRoomMember;
import com.netease.nimlib.sdk.chatroom.model.ChatRoomMessage;
import com.netease.nimlib.sdk.chatroom.model.EnterChatRoomData;
import com.netease.nimlib.sdk.chatroom.model.EnterChatRoomResultData;
import com.netease.nimlib.sdk.chatroom.model.MemberOption;
import com.netease.nimlib.sdk.friend.FriendService;
import com.netease.nimlib.sdk.uinfo.UserService;
import com.netease.nimlib.sdk.uinfo.model.NimUserInfo;
import com.zhangju.xingquban.R;
import com.zhangju.xingquban.interestclassapp.application.MyApp;
import com.zhangju.xingquban.interestclassapp.bean.live.ChatRoomAddFollowBean;
import com.zhangju.xingquban.interestclassapp.bean.live.ChatRoomBaseItem;
import com.zhangju.xingquban.interestclassapp.bean.live.ChatRoomFollowBean;
import com.zhangju.xingquban.interestclassapp.bean.live.ChatRoomItemBean1;
import com.zhangju.xingquban.interestclassapp.bean.live.ChatRoomItemBean2;
import com.zhangju.xingquban.interestclassapp.bean.live.ChatRoomManageBean;
import com.zhangju.xingquban.interestclassapp.bean.live.ChatUserInfoBean;
import com.zhangju.xingquban.interestclassapp.bean.live.IMBlackListBean;
import com.zhangju.xingquban.interestclassapp.bean.live.LiveExitBean;
import com.zhangju.xingquban.interestclassapp.refactor.common.utils.FileHelper;
import com.zhangju.xingquban.interestclassapp.refactor.common.utils.ThirdPartyUtils;
import com.zhangju.xingquban.interestclassapp.refactor.user.UserManager;
import com.zhangju.xingquban.interestclassapp.service.AlertService;
import com.zhangju.xingquban.interestclassapp.ui.adapter.live.ChatRoomAdapter;
import com.zhangju.xingquban.interestclassapp.ui.adapter.live.ChatRoomManageAdapter;
import com.zhangju.xingquban.interestclassapp.ui.adapter.live.ChatRoomMemberAdapter;
import com.zhangju.xingquban.interestclassapp.ui.fragment.live.widget.NetWorkInfoDialog;
import com.zhangju.xingquban.interestclassapp.ui.sys.KeyboardChangeListener;
import com.zhangju.xingquban.interestclassapp.util.ToastUtil;
import com.zhangju.xingquban.interestclassapp.util.UrlUtils;
import com.zhangju.xingquban.interestclassapp.util.click.NoDoubleClick;
import com.zhangju.xingquban.interestclassapp.view.GlideCircleTrans;
import com.zhangju.xingquban.interestclassapp.view.dialog.ShareDialog;
import com.zhangju.xingquban.interestclassapp.view.listView.HorizontalListView;
import com.zhangju.xingquban.interestclassapp.view.livestream.OpenGLSurfaceView;
import com.zhangju.xingquban.interestclassapp.view.me.AlertDialogIOS;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import butterknife.ButterKnife;
import jp.co.cyberagent.android.gpuimage.GPUImageFaceFilter;
import jp.co.cyberagent.android.gpuimage.GPUImageFilter;

/**
 * Created by lsh on 2016/8/31.
 * <p/>
 * 直播页面
 */

public class LivingActivity
        extends Activity
        implements OnClickListener, lsMessageHandler,
        AdapterView.OnItemClickListener, ChatRoomMemberCache.onCustomMessageListener {


    /**
     * 需要变换的角色名:(owner:房主,manager:管理员,common:普通成员,blacklisted:拉黑,muted:永久禁言,guest:游客)
     */
    private String chatroomCommand = "manager";
    private AbortableFuture<LoginInfo> mLoginRequest; // 聊天室登录请求
    private static final int LIMIT = 100;
    private long updateTime = 0; // 非游客的updateTime
    private long enterTime = 0; // 游客的enterTime

    // 云信聊天室系统默认分组
    private static final String BLACKLIST = "1";    // 黑名单
    private static final String NOSOUND = "2";      // 静音
    private static final String FRIENDS = "3";      // 好友

    private boolean isNormalEmpty = false; // 固定成员是否拉取完


    private lsMediaCapture mLSMediaCapture = null;
    private String mliveStreamingURL = null;
    private String mMixAudioFilePath = null;
    private File mMP3AppFileDirectory = null;
    private String mVideoResolution = null;
    private boolean mUseFilter = false;

    private Handler mHandler;

    //状态变量
    private boolean m_liveStreamingOn = false;
    private boolean m_liveStreamingInit = false;
    private boolean m_liveStreamingInitFinished = false;
    private boolean m_tryToStopLivestreaming = false;
    private boolean m_startVideoCamera = false;

    private Intent mIntentLiveStreamingStopFinished = new Intent("LiveStreamingStopFinished");


    //SDK统计相关变量
    private lsMediaCapture.LSLiveStreamingParaCtx mLSLiveStreamingParaCtx = null;
    private lsMediaCapture.Statistics mStatistics = null;

    //SDK日志级别相关变量
    private int mLogLevel = LS_LOG_ERROR;
    private String mLogPath = null;


    //滤镜模式的view
    private OpenGLSurfaceView mSurfaceView;

    //伴音相关
    private int m_mixAudioVolume;
    //    private HeadsetPlugReceiver mHeadsetPlugReceiver = null;
    private int mRouteMode = AUDIO_ROUTE_LOUDERSPEAKER;

    //视频截图相关变量
    private String mScreenShotFilePath = "/sdcard/";//视频截图文件路径
    private String mScreenShotFileName = "test.jpg";//视频截图文件名

    //查询摄像头支持的采集分辨率信息相关变量
    private Thread mCameraThread;
    private Looper mCameraLooper;
    private int mCameraID = CAMERA_POSITION_BACK;//默认查询的是后置摄像头

    private float mCurrentDistance;
    private float mLastDistance = -1;

    public static final int CAMERA_POSITION_BACK = 0;
    public static final int CAMERA_POSITION_FRONT = 1;

    public static final int CAMERA_ORIENTATION_PORTRAIT = 0;
    public static final int CAMERA_ORIENTATION_LANDSCAPE = 1;
    public static final int CAMERA_ORIENTATION_PORTRAIT_UPSIDEDOWN = 2;
    public static final int CAMERA_ORIENTATION_LANDSCAPE_LEFTSIDERIGHT = 3;

    public static final int LS_VIDEO_CODEC_AVC = 0;
    public static final int LS_VIDEO_CODEC_VP9 = 1;
    public static final int LS_VIDEO_CODEC_H265 = 2;

    public static final int LS_AUDIO_STREAMING_LOW_QUALITY = 0;
    public static final int LS_AUDIO_STREAMING_HIGH_QUALITY = 1;

    public static final int LS_AUDIO_CODEC_AAC = 0;
    public static final int LS_AUDIO_CODEC_SPEEX = 1;
    public static final int LS_AUDIO_CODEC_MP3 = 2;
    public static final int LS_AUDIO_CODEC_G711A = 3;
    public static final int LS_AUDIO_CODEC_G711U = 4;

    public static final int FLV = 0;
    public static final int RTMP = 1;

    public static final int HAVE_AUDIO = 0;
    public static final int HAVE_VIDEO = 1;
    public static final int HAVE_AV = 2;

    public static final int OpenQoS = 0;
    public static final int CloseQoS = 1;

    //伴音相关宏定义：
    //AUDIO_ROUTE_EARPIECE：有线耳机模式
    //AUDIO_ROUTE_LOUDERSPEAKER：外放模式
    //AUDIO_ROUTE_BLUETOOTH：蓝牙耳机模式
    public static final int AUDIO_ROUTE_EARPIECE = 0;
    public static final int AUDIO_ROUTE_LOUDERSPEAKER = 1;
    public static final int AUDIO_ROUTE_BLUETOOTH = 2;

    public static final int LS_LOG_QUIET = 0x00;            //!< log输出模式：不输出
    public static final int LS_LOG_ERROR = 1;               //!< log输出模式：输出错误
    public static final int LS_LOG_WARNING = 1 << 1;          //!< log输出模式：输出警告
    public static final int LS_LOG_INFO = 1 << 2;          //!< log输出模式：输出信息
    public static final int LS_LOG_DEBUG = 1 << 3;          //!< log输出模式：输出调试信息
    public static final int LS_LOG_DETAIL = 1 << 4;          //!< log输出模式：输出详细
    public static final int LS_LOG_RESV = 1 << 5;          //!< log输出模式：保留
    public static final int LS_LOG_LEVEL_COUNT = 6;
    public static final int LS_LOG_DEFAULT = LS_LOG_WARNING;    //!< log输出模式：默认输出警告

    //广播相关变量
    //    private MsgReceiver               msgReceiver;
    //    private audioMixVolumeMsgReceiver audioMixVolumeMsgReceiver;

    private static final String TAG = "NeteaseLiveStream";

    //Activity环境变量
    private Context mContext;

    private int mVideoPreviewWidth, mVideoPreviewHeight;

    private Intent mNetInfoIntent = new Intent(NetWorkInfoDialog.NETINFO_ACTION);

    private Intent mAlertServiceIntent;

    private boolean mAlertServiceOn = false;

    private long mLastVideoProcessErrorAlertTime = 0;
    private long mLastAudioProcessErrorAlertTime = 0;


    private boolean mFlag = false; // 闪光灯开关
    private boolean mHardWareEncEnable = false;

    private String token;
    private String mId;  // 直播id
    private String mPushUrl; // 推流地址
    private String mChatroomId;
    private ImageView mLiveingSwitch;
    private ImageView mLiveingBack;
    private ImageView mLiveFlashlight;
    private ImageView mLiveSpeak;
    private ImageView mLiveShare;
    private ImageView mLiveGone;
    private TextView mLiveNum;
    private TextView mLiveXqb;
    private TextView mTvLiveRoomId;
    private TextView mLiveManage; // 场控按钮
    private View mManageView; // 场控弹窗
    private View mSpeakView;  // 聊天弹窗
    private View mDetailsView; // 用户信息弹窗
    private View mShareView;  // 分享弹窗

    private FrameLayout mProgress;

    private LinearLayout mTopbuttons;
    private boolean mFlagGone = false;
    private EditText mEtSpeak;
    private RelativeLayout mRlMain;
    private int mKeyboardHeight;
    private Button mBtnSpeakSend;
    private String mAccid;
    private String mToken;
    private String mAppKey;
    private ListView mLvChatroom;

    private HorizontalListView mHlv_avatars; // 观众头像列表

    private ArrayList<ChatRoomBaseItem> mMessageData = new ArrayList<>();
    private List<ChatRoomMember> mAvatarData = new ArrayList<>();
    private Map<String, ChatRoomMember> memberCache = new ConcurrentHashMap<>();
    private ChatRoomAdapter mChatRoomAdapter;
    private ChatRoomMemberAdapter mChatRoomMemberAdapter;
    private PopupWindow mPopupWindow;

    // 聊天室成员信息弹窗内的展示
    private ImageView mIvWindowAvatar;
    private TextView mTvWindowId;
    private TextView mTvWindowAge;
    private TextView mTvWindowFllows;
    private TextView mTvWindowName;
    private TextView mTvWindowGive;
    private TextView mTvWindowGet;
    private TextView mTvWindowSetManage; // 设置场控,只有主播才有
    private TextView mTvWindowFllow;      // 关注按钮
    //    private LinearLayout mLlWindowFllow;  // 关注按钮


    private TextView mTvWindowFans;
    private ChatRoomMember mMyMember;       // 自己的资料
    private ChatRoomMember mChatRoomMember; // 被选中的用户资料
    private LinearLayout mLlWindowOut;
    private LinearLayout mLlWindowShutup;
    private TextView mTvWindowShutup;
    private TextView mTvWindowOut;
    private ImageView mIvWindowShutup;
    private ImageView mIvWindowOut;

    private String mMyFollowes;
    private ChatUserInfoBean mChatUserInfoBean;
    private String mFollowString = "";
    private ChatRoomManageAdapter mChatRoomManageAdapter;
    private ArrayList<ChatRoomManageBean.AaDataBean> mChatRoomManageList;
    private ListView mLvMyManage;
    private TextView mTvMyManageText;
    private List<ChatRoomManageBean.AaDataBean> ManageBeanAaData;


    private String mLiveAvatar; // 主播头像
    private String mLiveName;   // 主播昵称

    private LinearLayout mLlgiftcontent;
    private String mStdCoin;

    /**
     * 分享相关
     * @param msg
     * @param object
     */
    /**
     * 分享操作
     */
    private ShareDialog shareDialog;
    private String sharetitle = "为兴趣而生，为梦想而来";
    private String shareid = "";
    private String shareUrl = "http://my.xqban.com/admnxzcmr/rooms/share?id=";
    private String image = "";
    private ArrayList<String> mlist = new ArrayList<>();
    private String shareContent = "";

//    http://my.xqban.com/admnxzcmr/rooms/share?id=58


    //    private String mManageString = ChatRoomMemberCache.sStringBuffer.toString();;


    //处理SDK抛上来的异常和事件，用户需要在这里监听各种消息，进行相应的处理。
    //例如监听断网消息，用户根据断网消息进行直播重连
    //注意：直播重连请使用restartLiveStream，在网络带宽较低导致发送码率帧率降低时，可以调用这个接口重启直播，改善直播质量
    //在网络断掉的时候（用户可以监听 MSG_RTMP_URL_ERROR 和 MSG_BAD_NETWORK_DETECT
    // ），用户不可以立即调用改接口，而是应该在网络重新连接以后，主动调用这个接口。
    //如果在网络没有重新连接便调用这个接口，直播将不会重启
    @Override
    public void handleMessage(int msg, Object object) {
        switch (msg) {
            case MSG_INIT_LIVESTREAMING_OUTFILE_ERROR://初始化直播出错
            case MSG_INIT_LIVESTREAMING_VIDEO_ERROR:
            case MSG_INIT_LIVESTREAMING_AUDIO_ERROR: {
                if (m_liveStreamingInit) {
                    Bundle bundle = new Bundle();
                    bundle.putString("alert", "初始化直播出错");
                    Intent intent = new Intent(LivingActivity.this, AlertService.class);
                    intent.putExtras(bundle);
                    startService(intent);
                    mAlertServiceOn = true;
                }
                break;
            }
            case MSG_START_LIVESTREAMING_ERROR://开始直播出错
            {
                break;
            }
            case MSG_STOP_LIVESTREAMING_ERROR://停止直播出错
            {
                if (m_liveStreamingOn) {
                    Bundle bundle = new Bundle();
                    bundle.putString("alert", "停止直播出错");
                    Intent intent = new Intent(LivingActivity.this, AlertService.class);
                    intent.putExtras(bundle);
                    startService(intent);
                    mAlertServiceOn = true;
                }
                break;
            }
            case MSG_AUDIO_PROCESS_ERROR://音频处理出错
            {
                if (m_liveStreamingOn && System.currentTimeMillis() -
                        mLastAudioProcessErrorAlertTime >= 10000) {
                    Bundle bundle = new Bundle();
                    bundle.putString("alert", "音频处理出错");
                    Intent intent = new Intent(LivingActivity.this, AlertService.class);
                    intent.putExtras(bundle);
                    startService(intent);
                    mAlertServiceOn = true;
                    mLastAudioProcessErrorAlertTime = System.currentTimeMillis();
                }

                break;
            }
            case MSG_VIDEO_PROCESS_ERROR://视频处理出错
            {
                if (m_liveStreamingOn && System.currentTimeMillis() -
                        mLastVideoProcessErrorAlertTime >= 10000) {
                    Bundle bundle = new Bundle();
                    bundle.putString("alert", "视频处理出错");
                    Intent intent = new Intent(LivingActivity.this, AlertService.class);
                    intent.putExtras(bundle);
                    startService(intent);
                    mAlertServiceOn = true;
                    mLastVideoProcessErrorAlertTime = System.currentTimeMillis();
                }
                break;
            }
            case MSG_START_PREVIEW_ERROR://视频预览出错，可能是获取不到camera的使用权限
            {
                //Log.i(TAG, "test: in handleMessage, MSG_START_PREVIEW_ERROR");
                Toast.makeText(getApplication(), "视频预览出错,请检查您是否禁用了摄像头", Toast.LENGTH_LONG).show();
                break;
            }
            case MSG_AUDIO_RECORD_ERROR://音频采集出错，获取不到麦克风的使用权限
            {
                //Log.i(TAG, "test: in handleMessage, MSG_AUDIO_RECORD_ERROR");
                Toast.makeText(getApplication(), "音频采集出错,请检查您是否禁用了麦克风", Toast.LENGTH_LONG).show();
                break;
            }
            case MSG_RTMP_URL_ERROR://断网消息
            {
                //Log.i(TAG, "test: in handleMessage, MSG_RTMP_URL_ERROR");
                Toast.makeText(getApplication(), "检测到网络连接已断开", Toast.LENGTH_LONG).show();
                break;
            }
            case MSG_URL_NOT_AUTH://直播URL非法，URL格式不符合视频云要求
            {
                if (m_liveStreamingInit) {
                    Bundle bundle = new Bundle();
                    bundle.putString("alert", "MSG_URL_NOT_AUTH");
                    Intent intent = new Intent(LivingActivity.this, AlertService.class);
                    intent.putExtras(bundle);
                    startService(intent);
                    mAlertServiceOn = true;
                }
                break;
            }
            case MSG_SEND_STATICS_LOG_ERROR://发送统计信息出错
            {
                //Log.i(TAG, "test: in handleMessage, MSG_SEND_STATICS_LOG_ERROR");
                break;
            }
            case MSG_SEND_HEARTBEAT_LOG_ERROR://发送心跳信息出错
            {
                //Log.i(TAG, "test: in handleMessage, MSG_SEND_HEARTBEAT_LOG_ERROR");
                break;
            }
            case MSG_AUDIO_SAMPLE_RATE_NOT_SUPPORT_ERROR://音频采集参数不支持
            {
                //Log.i(TAG, "test: in handleMessage, MSG_AUDIO_SAMPLE_RATE_NOT_SUPPORT_ERROR");
                break;
            }
            case MSG_AUDIO_PARAMETER_NOT_SUPPORT_BY_HARDWARE_ERROR://音频参数不支持
            {
                //Log.i(TAG, "test: in handleMessage,
                // MSG_AUDIO_PARAMETER_NOT_SUPPORT_BY_HARDWARE_ERROR");
                break;
            }
            case MSG_NEW_AUDIORECORD_INSTANCE_ERROR://音频实例初始化出错
            {
                //Log.i(TAG, "test: in handleMessage, MSG_NEW_AUDIORECORD_INSTANCE_ERROR");
                break;
            }
            case MSG_AUDIO_START_RECORDING_ERROR://音频采集出错
            {
                //Log.i(TAG, "test: in handleMessage, MSG_AUDIO_START_RECORDING_ERROR");
                break;
            }
            case MSG_QOS_TO_STOP_LIVESTREAMING://网络QoS极差，视频码率档次降到最低
            {
                Toast.makeText(getApplication(), "检测到您当前网络极差，视频码率档次降到最低", Toast.LENGTH_LONG).show();
                Log.i(TAG, "test: in handleMessage, MSG_QOS_TO_STOP_LIVESTREAMING");
                break;
            }
            case MSG_HW_VIDEO_PACKET_ERROR://视频硬件编码出错反馈消息
            {
                if (m_liveStreamingOn) {
                    Bundle bundle = new Bundle();
                    bundle.putString("alert", "MSG_HW_VIDEO_PACKET_ERROR");
                    Intent intent = new Intent(LivingActivity.this, AlertService.class);
                    intent.putExtras(bundle);
                    startService(intent);
                    mAlertServiceOn = true;
                }
                break;
            }
            case MSG_WATERMARK_INIT_ERROR://视频水印操作初始化出错
            {
                break;
            }
            case MSG_WATERMARK_PIC_OUT_OF_VIDEO_ERROR://视频水印图像超出原始视频出错
            {
                //Log.i(TAG, "test: in handleMessage: MSG_WATERMARK_PIC_OUT_OF_VIDEO_ERROR");
                break;
            }
            case MSG_WATERMARK_PARA_ERROR://视频水印参数设置出错
            {
                //Log.i(TAG, "test: in handleMessage: MSG_WATERMARK_PARA_ERROR");
                break;
            }
            case MSG_CAMERA_PREVIEW_SIZE_NOT_SUPPORT_ERROR://camera采集分辨率不支持
            {
                //Log.i(TAG, "test: in handleMessage: MSG_CAMERA_PREVIEW_SIZE_NOT_SUPPORT_ERROR");
                break;
            }
            case MSG_START_PREVIEW_FINISHED://camera采集预览完成
            {
                Log.i(TAG, "test: MSG_START_PREVIEW_FINISHED   camera采集预览完成");

                break;
            }
            case MSG_START_LIVESTREAMING_FINISHED://开始直播完成
            {
                break;
            }
            case MSG_STOP_LIVESTREAMING_FINISHED://停止直播完成
            {
                mIntentLiveStreamingStopFinished.putExtra("LiveStreamingStopFinished", 1);
                sendBroadcast(mIntentLiveStreamingStopFinished);
                break;
            }
            case MSG_STOP_VIDEO_CAPTURE_FINISHED: {
                //Log.i(TAG, "test: in handleMessage: MSG_STOP_VIDEO_CAPTURE_FINISHED");
                if (!m_tryToStopLivestreaming && mLSMediaCapture != null) {
                    //继续视频推流，推最后一帧图像
                    mLSMediaCapture.backgroundVideoEncode();
                }
                break;
            }
            case MSG_STOP_RESUME_VIDEO_CAPTURE_FINISHED: {
                //Log.i(TAG, "test: in handleMessage: MSG_STOP_RESUME_VIDEO_CAPTURE_FINISHED");
                //开启视频preview
                if (mLSMediaCapture != null) {
                    mLSMediaCapture.resumeVideoPreview();
                    m_liveStreamingOn = true;
                    //开启视频推流，推正常帧
                    mLSMediaCapture.startVideoLiveStream();
                }
                break;
            }
            case MSG_STOP_AUDIO_CAPTURE_FINISHED: {
                //Log.i(TAG, "test: in handleMessage: MSG_STOP_AUDIO_CAPTURE_FINISHED");
                if (!m_tryToStopLivestreaming && mLSMediaCapture != null) {
                    //继续音频推流，推静音帧
                    mLSMediaCapture.backgroundAudioEncode();
                }
                break;
            }
            case MSG_STOP_RESUME_AUDIO_CAPTURE_FINISHED: {
                //Log.i(TAG, "test: in handleMessage: MSG_STOP_RESUME_AUDIO_CAPTURE_FINISHED");
                //开启音频推流，推正常帧
                mLSMediaCapture.startAudioLiveStream();
                break;
            }
            case MSG_SWITCH_CAMERA_FINISHED://切换摄像头完成
            {
                int cameraId = (Integer) object;//切换之后的camera id
                break;
            }
            case MSG_SEND_STATICS_LOG_FINISHED://发送统计信息完成
            {
                //Log.i(TAG, "test: in handleMessage, MSG_SEND_STATICS_LOG_FINISHED");
                break;
            }
            case MSG_SERVER_COMMAND_STOP_LIVESTREAMING://服务器下发停止直播的消息反馈，暂时不使用
            {
                //Log.i(TAG, "test: in handleMessage, MSG_SERVER_COMMAND_STOP_LIVESTREAMING");
                break;
            }
            case MSG_GET_STATICS_INFO://获取统计信息的反馈消息
            {
                //Log.i(TAG, "test: in handleMessage, MSG_GET_STATICS_INFO");
                //显示统计信息，用户可以从统计信息中掌握网络发送数据的情况
                Message message = new Message();
                mStatistics = (lsMediaCapture.Statistics) object;

                Bundle bundle = new Bundle();
                bundle.putInt("FR", mStatistics.videoEncodeFrameRate);
                bundle.putInt("VBR", mStatistics.videoEncodeBitRate);
                bundle.putInt("ABR", mStatistics.audioEncodeBitRate);
                bundle.putInt("TBR", mStatistics.totalRealSendBitRate);
                message.setData(bundle);

                if (mHandler != null) {
                    mHandler.sendMessage(message);
                }
                break;
            }
            case MSG_BAD_NETWORK_DETECT://如果连续一段时间（10s）实际推流数据为0，会反馈这个错误消息
            {
                //Log.i(TAG, "test: in handleMessage, MSG_BAD_NETWORK_DETECT");
                break;
            }
            case MSG_SCREENSHOT_FINISHED://视频截图完成后的消息反馈
            {
                //Log.i(TAG, "test: in handleMessage, MSG_SCREENSHOT_FINISHED, buffer is " +
                // (byte[]) object);
                getScreenShotByteBuffer((byte[]) object);
                break;
            }
            case MSG_SET_CAMERA_ID_ERROR://设置camera出错（对于只有一个摄像头的设备，如果调用了不存在的摄像头，会反馈这个错误消息）
            {
                //Log.i(TAG, "test: in handleMessage, MSG_SET_CAMERA_ID_ERROR");
                break;
            }
            case MSG_SET_GRAFFITI_ERROR://设置涂鸦出错消息反馈
            {
                //Log.i(TAG, "test: in handleMessage, MSG_SET_GRAFFITI_ERROR");
                break;
            }
            case MSG_MIX_AUDIO_FINISHED://伴音一首MP3歌曲结束后的反馈
            {
                //Log.i(TAG, "test: in handleMessage, MSG_MIX_AUDIO_FINISHED");
                break;
            }
            case MSG_URL_FORMAT_NOT_RIGHT://推流url格式不正确
            {
                //Log.i(TAG, "test: in handleMessage, MSG_URL_FORMAT_NOT_RIGHT");
                break;
            }
            case MSG_URL_IS_EMPTY://推流url为空
            {
                //Log.i(TAG, "test: in handleMessage, MSG_URL_IS_EMPTY");
                break;
            }
        }
    }

    //获取截屏图像的数据
    public void getScreenShotByteBuffer(byte[] screenShotByteBuffer) {
        FileOutputStream outStream = null;
        String screenShotFilePath = mScreenShotFilePath + mScreenShotFileName;
        if (screenShotFilePath != null) {
            try {
                if (screenShotFilePath != null) {
                    outStream = new FileOutputStream(String.format(screenShotFilePath));
                    outStream.write(screenShotByteBuffer);
                    outStream.close();
                }
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } finally {

            }
        }
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        String avatar;
        switch (parent.getId()) {
            case R.id.hlv_liveing_avatars:
                mChatRoomMember = mAvatarData.get(position);
                if (mChatRoomMember != null) {
                    initUsetInfo(mChatRoomMember); // 初始化用户信息
                    int memberLevel = mChatRoomMember.getMemberLevel();
                    showPopupWindow(mDetailsView);
                }
                break;
            case R.id.lv_liveing_chatroom:
                int item_type = mMessageData.get(position).getItem_type();
                ChatRoomMessage message = null;
                if (item_type == 0) { // 系统通知
                    mChatRoomMember = ((ChatRoomItemBean1) mMessageData.get(position)).getMember();
                    if (mChatRoomMember != null) {
                        initUsetInfo(mChatRoomMember); // 初始化用户信息
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
                                        if (member != null) {
                                            mChatRoomMember = member;
                                            initUsetInfo(mChatRoomMember); // 初始化用户信息
                                            showPopupWindow(mDetailsView);
                                        }
                                    } else {
                                        ToastUtil.showToast("获取用户信息失败,请检查您的网络连接");
                                    }
                                }
                            });
                }
                break;
        }

    }

    /**
     * 设置场控
     *
     * @param member
     */
    public void setManage(ChatRoomMember member) {
        mProgress.setVisibility(View.VISIBLE);
        String accid = member.getAccount();
        if (accid == null && mChatUserInfoBean != null) {
            accid = mChatUserInfoBean.getAccid();
        }
        if (mChatRoomMember.getMemberType() == MemberType.ADMIN) { // 如果当前被操作的用户已经是管理员了就取消管理
            chatroomCommand = "guest";
            mTvWindowSetManage.setText("取消场控");
        } else {
            chatroomCommand = "manager";
            mTvWindowSetManage.setText("设为场控");
        }

        HttpUtils httpUtils = new HttpUtils();
        RequestParams params = new RequestParams();
        params.addHeader("X-CustomToken", token);
        params.addBodyParameter("chatroomId", mChatroomId);
        params.addBodyParameter("targetAccid", accid);
        params.addBodyParameter("command", chatroomCommand);
        params.addBodyParameter("isOut", "true");

        String url = UrlUtils.URL_CHATROOMMANAGE_ADD;
        httpUtils.send(HttpRequest.HttpMethod.POST, url, params, new RequestCallBack<String>() {

            @Override
            public void onSuccess(ResponseInfo<String> responseInfo) {
                String json = responseInfo.result;
                myChatRoomManage(); //如果设置成功则更新场控列表
                mProgress.setVisibility(View.GONE);
            }

            @Override
            public void onFailure(HttpException e, String s) {
                mProgress.setVisibility(View.GONE);
            }
        });

    }

    /**
     * 接收到礼物消息的回调
     *
     * @param stdCoin
     */
    @Override
    public void onGitGiftMessage(String stdCoin) {
        if (stdCoin == null) return;
        mLiveXqb.setText(stdCoin);
    }

    @Override
    public void onLiveExitMessage(String json) {

    }

    //监听设备耳机插拔的广播消息，支持有线耳机和外放模式
    public class HeadsetPlugReceiver
            extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            if (intent.getAction() != null && intent.getAction().equals(Intent
                    .ACTION_HEADSET_PLUG)) {
                if (intent.getIntExtra("state", 0) == 0) {
                    mRouteMode = AUDIO_ROUTE_LOUDERSPEAKER;//外放
                } else if (intent.getIntExtra("state", 0) == 1) {
                    mRouteMode = AUDIO_ROUTE_EARPIECE;//耳机
                }

                if (mLSMediaCapture != null) {
                    mLSMediaCapture.setAudioRouteMode(mRouteMode);
                }
            }
        }


    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
        // 隐藏底部虚拟按键
        //        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager
        // .LayoutParams
        // .FLAG_FULLSCREEN);
        ButterKnife.bind(this);


        Bundle bundle = initData();// 初始化数据

        //应用运行时，保持屏幕高亮，不锁屏
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);

        //从直播设置页面获取推流URL和分辨率信息
        //alert1用于检测设备SDK版本是否符合开启滤镜要求，alert2用于检测设备的硬件编码模块（与滤镜相关）是否正常
        //这里添加获取到的屏幕分辨率信息, 直播推流等信息
        //        mliveStreamingURL = getIntent().getStringExtra("mediaPath");
        mliveStreamingURL = mPushUrl;
        mVideoResolution = "SD";
        mUseFilter = true;  // 开启滤镜模式


        if (mVideoResolution.equals("HD")) {
            mVideoPreviewWidth = 1280;
            mVideoPreviewHeight = 720;
        } else if (mVideoResolution.equals("SD")) {
            mVideoPreviewWidth = 640;
            mVideoPreviewHeight = 480;
        } else {
            mVideoPreviewWidth = 320;
            mVideoPreviewHeight = 240;
        }

        m_liveStreamingOn = false;
        m_tryToStopLivestreaming = false;

        mContext = this;

        //以下为SDK调用主要步骤，请用户参考使用
        //1、创建直播实例
        mLSMediaCapture = new lsMediaCapture(this, mContext, mVideoPreviewWidth,
                mVideoPreviewHeight, mUseFilter);
        /*mLSMediaCapture.setFilterType(new GPUImageFaceFilter()); // 设置美颜模式
        mLSMediaCapture.setFilterStrength(10); // 设置美颜级别   默认直接最高*/

        //        Intent intent = new Intent(this, LiveingUiActivity.class);
        //        startActivity(intent);

        //2、设置直播参数
        paraSet();

        //3、加载界面
        if (mUseFilter && (mLSLiveStreamingParaCtx.eOutStreamType.outputStreamType == HAVE_AV ||
                mLSLiveStreamingParaCtx.eOutStreamType.outputStreamType == HAVE_VIDEO)) {
            setContentView(R.layout.activity_liveing);
            mSurfaceView = (OpenGLSurfaceView) findViewById(R.id.gl_surfaceView);
        }


        //4 设置日志级别和日志文件路径
        getLogPath();
        if (mLSMediaCapture != null) {
            mLSMediaCapture.setTraceLevel(mLogLevel, mLogPath);
        }

        //5 设置视频预览参数
        if (mLSLiveStreamingParaCtx.eOutStreamType.outputStreamType == HAVE_AV ||
                mLSLiveStreamingParaCtx.eOutStreamType.outputStreamType == HAVE_VIDEO) {
            mSurfaceView.setPreviewSize(mVideoPreviewWidth, mVideoPreviewHeight);
        } else if (mLSLiveStreamingParaCtx.eOutStreamType.outputStreamType == HAVE_AV ||
                mLSLiveStreamingParaCtx.eOutStreamType.outputStreamType == HAVE_VIDEO) {

            mSurfaceView.setPreviewSize(mVideoPreviewWidth, mVideoPreviewHeight);
        }


        if (mLSMediaCapture != null) {
            boolean ret = false;

            //6 开启视频预览
            if (mLSLiveStreamingParaCtx.eOutStreamType.outputStreamType == HAVE_AV ||
                    mLSLiveStreamingParaCtx.eOutStreamType.outputStreamType == HAVE_VIDEO) {
                mLSMediaCapture.startVideoPreviewOpenGL(mSurfaceView, mLSLiveStreamingParaCtx
                        .sLSVideoParaCtx.cameraPosition.cameraPosition);
                m_startVideoCamera = true;
            }

            mLSMediaCapture.setFilterType(new GPUImageFaceFilter()); // 设置美颜模式
            //            mLSMediaCapture.setFilterStrength(1); // 设置美颜级别   默认直接最高
            //7 初始化直播推流
            ret = mLSMediaCapture.initLiveStream(mliveStreamingURL, mLSLiveStreamingParaCtx);

            if (ret) {
                m_liveStreamingInit = true;
                m_liveStreamingInitFinished = true;
            } else {
                m_liveStreamingInit = true;
                m_liveStreamingInitFinished = false;
            }
        }
        // 控件的初始化
        buttonInit();
        //   mProgress.setVisibility(View.VISIBLE);
        // 初始化聊天室

        mChatRoomManageList = new ArrayList<>();
        mChatRoomManageAdapter = new ChatRoomManageAdapter(mContext, mChatRoomManageList
        );
        mLvMyManage.setAdapter(mChatRoomManageAdapter);
        initNIM(bundle);
        initShare();
        mChatRoomManageAdapter.setCancelClick(new ChatRoomManageAdapter.CancelClick() {
            @Override
            public void onCancelClick(View view, int position) {

                if (ManageBeanAaData != null && ManageBeanAaData.size() > 0) {
                    String accid = ManageBeanAaData.get(position).getChatUser().getAccid();
                    followAdd(accid);
                }
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
    }


    /**
     * 从底部弹出对话框
     */
    private PopupWindow showPopupWindow(View view) {
        final PopupWindow popupWindow = new PopupWindow(view, WindowManager.LayoutParams
                .MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT, true);

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
                if (keyCode == KeyEvent.KEYCODE_MENU && event.getRepeatCount() == 0 && event
                        .getAction() == KeyEvent.ACTION_DOWN) {
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

        popupWindow.showAtLocation(mRlMain, Gravity.BOTTOM, 0, 0);
        return popupWindow;
    }

    /**
     * 初始化数据
     *
     * @return
     */
    private Bundle initData() {
        Intent intent = getIntent();
        Bundle bundle = intent.getBundleExtra("startLive");
        token = UserManager.getInstance().getToken();
        mPushUrl = bundle.getString("pushUrl", null); // 推流地址
        mId = bundle.getString("id", null);
        mStdCoin = bundle.getString("stdCoin", null);

        // 聊天室相关
        mAccid = bundle.getString("accid", null);
        mToken = bundle.getString("liveToken", null);
        mChatroomId = bundle.getString("chatroomId", null);
        mAppKey = bundle.getString("appKey", null);
        mMyFollowes = bundle.getString("myFollowes", null); // 当前用户的关注列表
        return bundle;
    }

    /**
     * 初始化聊天室
     *
     * @param bundle
     */
    private void initNIM(Bundle bundle) {

        mMessageData.clear();
        mChatRoomAdapter = new ChatRoomAdapter(mContext, mMessageData);
        ChatRoomMemberCache.getInstance().InitMessageData(mChatRoomAdapter);
        mLvChatroom.setAdapter(mChatRoomAdapter);
        mChatRoomAdapter.addItem(new ChatRoomItemBean1(0, getString(R.string.live_notice)));

        mChatRoomMemberAdapter = new ChatRoomMemberAdapter(mContext, mAvatarData);
        ChatRoomMemberCache.getInstance().InitMemberData(mChatRoomMemberAdapter, mAvatarData,
                mChatroomId, mLiveNum);

        // 将view传递过去做处理
        ChatRoomMemberCache.getInstance().InitChatRoomView(mTvWindowSetManage);
        ChatRoomMemberCache.getInstance().setOnCustomMessageListener(this);

        mHlv_avatars.setAdapter(mChatRoomMemberAdapter);
        Log.e("liveingActivity", "聊天室登录中");
        // 登录
        mLoginRequest = NIMClient.getService(AuthService.class).login(new LoginInfo(mAccid,
                mToken, mAppKey));
        RequestCallback<LoginInfo> requestCallback = new RequestCallback<LoginInfo>() {
            @Override
            public void onSuccess(LoginInfo loginInfo) {

                String account = loginInfo.getAccount();
                String token1 = loginInfo.getToken();
                Log.e("liveingActivity", "聊天室登录成功" + account + "=======" + token1);
                ToastUtil.showToast(mContext, "聊天室登录成功");

                // 进入聊天室
                EnterChatRoomData data = new EnterChatRoomData(mChatroomId);
                NIMClient.getService(ChatRoomService.class).enterChatRoom(data).setCallback(new RequestCallback<EnterChatRoomResultData>() {

                    @Override
                    public void onSuccess(EnterChatRoomResultData enterChatRoomResultData) {
                        // 聊天室成员信息
                        mMyMember = enterChatRoomResultData.getMember();
                        mLiveAvatar = mMyMember.getAvatar();
                        mLiveName = mMyMember.getNick();
                        // 初始化分享
                        //  initShareCheck();
                        mTvWindowSetManage.setVisibility(View.VISIBLE); // 如果是主播就显示场控按钮

                        // 获取我的场控列表
                        myChatRoomManage();

                        // 获取我的关注列表
                        myFollowList();

                        // 聊天室信息
                        ChatRoomInfo roomInfo = enterChatRoomResultData.getRoomInfo();

                        // 初始化观众列表
                        initAudienceList();

                        // 注册接受消息监听
                        registerObservers(true);
//                        ToastUtil.showToast(mContext, "进入聊天室成功");

                    }


                    @Override
                    public void onFailed(int i) {
                        ToastUtil.showToast("进入聊天室失败,请检查您的网络连接");
                        mProgress.setVisibility(View.GONE);

                    }

                    @Override
                    public void onException(Throwable throwable) {
                        mProgress.setVisibility(View.GONE);

                    }
                });

            }

            @Override
            public void onFailed(int i) {
                ToastUtil.showToast("进入聊天失败i");
                Log.e("StartLiveActivity", "聊天室登录失败" + i);
                mProgress.setVisibility(View.GONE);
            }

            @Override
            public void onException(Throwable throwable) {
                ToastUtil.showToast("进入聊天异常" + throwable.toString());
                Log.e("StartLiveActivity", throwable.toString());
                mProgress.setVisibility(View.GONE);
            }
        };
        mLoginRequest.setCallback(requestCallback);
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        exitChatRoom();
        registerObservers(false);
        closeLive(new onCloseLiveSuccessListener() {
            @Override
            public void onSuccess(String json) {
                liveDestroy();
            }
        });
        mMessageData.clear();

    }


    /**
     * 退出聊天室
     */
    private void exitChatRoom() {
        NIMClient.getService(ChatRoomService.class).exitChatRoom(mChatroomId);
        clearCache();
    }

    /**
     * 停止直播
     */
    private void liveDestroy() {
        if (mSurfaceView != null) {
            mSurfaceView.setVisibility(View.GONE);
        }
        if (mHandler != null) {
            mHandler.removeCallbacksAndMessages(null);
        }

        if (m_liveStreamingInit) {
            m_liveStreamingInit = false;
        }


        //Demo层报警信息操作的销毁
        if (mAlertServiceOn) {
            mAlertServiceIntent = new Intent(LivingActivity.this, AlertService.class);
            stopService(mAlertServiceIntent);
            mAlertServiceOn = false;
        }

        //停止直播调用相关API接口
        if (mLSMediaCapture != null && m_liveStreamingOn) {

            //停止直播，释放资源
            mLSMediaCapture.stopLiveStreaming();

            //如果音视频或者单独视频直播，需要关闭视频预览
            if (m_startVideoCamera) {
                mLSMediaCapture.stopVideoPreview();
                mLSMediaCapture.destroyVideoPreview();
            }

            //反初始化推流实例，当它与stopLiveStreaming连续调用时，参数为false
            mLSMediaCapture.uninitLsMediaCapture(false);
            mLSMediaCapture = null;

            mIntentLiveStreamingStopFinished.putExtra("LiveStreamingStopFinished", 2);
            sendBroadcast(mIntentLiveStreamingStopFinished);
        } else if (mLSMediaCapture != null && m_startVideoCamera) {
            mLSMediaCapture.stopVideoPreview();
            mLSMediaCapture.destroyVideoPreview();

            //反初始化推流实例，当它不与stopLiveStreaming连续调用时，参数为true
            mLSMediaCapture.uninitLsMediaCapture(true);
            mLSMediaCapture = null;

            mIntentLiveStreamingStopFinished.putExtra("LiveStreamingStopFinished", 1);
            sendBroadcast(mIntentLiveStreamingStopFinished);
        } else if (!m_liveStreamingInitFinished) {
            mIntentLiveStreamingStopFinished.putExtra("LiveStreamingStopFinished", 1);
            sendBroadcast(mIntentLiveStreamingStopFinished);

            //反初始化推流实例，当它不与stopLiveStreaming连续调用时，参数为true
            mLSMediaCapture.uninitLsMediaCapture(true);
        }

        if (m_liveStreamingOn) {
            m_liveStreamingOn = false;
        }

    }

    protected void onPause() {
        if (mLSMediaCapture != null) {
            if (!m_tryToStopLivestreaming && m_liveStreamingOn) {
                if (mLSLiveStreamingParaCtx.eOutStreamType.outputStreamType == HAVE_AV ||
                        mLSLiveStreamingParaCtx.eOutStreamType.outputStreamType == HAVE_VIDEO) {
                    //推最后一帧图像
                    mLSMediaCapture.backgroundVideoEncode();
                } else if (mLSLiveStreamingParaCtx.eOutStreamType.outputStreamType == HAVE_AUDIO) {
                    //释放音频采集资源
                    mLSMediaCapture.stopAudioRecord();
                }
            }
        }
        super.onPause();
    }

    protected void onRestart() {
        super.onRestart();
    }

    protected void onResume() {
        super.onResume();
        if (mLSMediaCapture != null) {
            if (mLSLiveStreamingParaCtx.eOutStreamType.outputStreamType == HAVE_AV ||
                    mLSLiveStreamingParaCtx.eOutStreamType.outputStreamType == HAVE_VIDEO) {
                //关闭推流固定图像，正常推流
                mLSMediaCapture.resumeVideoEncode();
            } else if (mLSLiveStreamingParaCtx.eOutStreamType.outputStreamType == HAVE_AUDIO) {
                //关闭推流静音帧
                mLSMediaCapture.resumeAudioEncode();
            }
        }
    }

    protected void onStart() {
        super.onStart();
        if (!m_liveStreamingOn) {
            if (mliveStreamingURL.isEmpty()) return;
            startAV();
        }

    }

    protected void onStop() {
        super.onStop();
    }


    //获取日志文件路径
    public void getLogPath() {
        try {
            if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
                mLogPath = Environment.getExternalStorageDirectory() + "/log/";
            }
        } catch (Exception e) {
            Log.e(TAG, "an error occured while writing file...", e);
        }
    }

    /**
     * 初始化按钮
     */
    public void buttonInit() {
        mLiveingSwitch = (ImageView) findViewById(R.id.iv_liveing_switch);
        mLiveingBack = (ImageView) findViewById(R.id.iv_liveing_back);
        mLiveFlashlight = (ImageView) findViewById(R.id.iv_liveing_flashlight);
        mLiveManage = (TextView) findViewById(R.id.tv_liveing_manage); // 场控

        mLiveSpeak = (ImageView) findViewById(R.id.iv_liveing_speak);
        mLiveShare = (ImageView) findViewById(R.id.iv_liveing_share);
        mLiveGone = (ImageView) findViewById(R.id.iv_liveing_gone);

        mLiveNum = (TextView) findViewById(R.id.tv_liveing_audiencee_num); // 观众数
        mLiveXqb = (TextView) findViewById(R.id.tv_liveing_xqb); // 兴趣币
        mTvLiveRoomId = (TextView) findViewById(R.id.tv_liveing_roomid); //房间号
        mTvLiveRoomId.setText("直播号: " + mChatroomId);
        if (mStdCoin != null) { // 兴趣币
            mLiveXqb.setText(mStdCoin);
        }
        // Button弹框
        mManageView = getLayoutInflater().inflate(R.layout.popupwindow_liveing_manage, null); // 场控
        mSpeakView = getLayoutInflater().inflate(R.layout.popupwindow_liveing_speak, null); // 发言
        mDetailsView = getLayoutInflater().inflate(R.layout.popupwindow_liveing_details, null);
        // 用户详情页面
        mShareView = getLayoutInflater().inflate(R.layout.popupwindow_liveing_share, null);   // 分享

        // 我的场控列表
        mLvMyManage = (ListView) mManageView.findViewById(R.id.lv_liveing_manage);
        mTvMyManageText = (TextView) mManageView.findViewById(R.id.tv_live_myManage_nulltext);

        // 聊天发言界面
        mEtSpeak = (EditText) mSpeakView.findViewById(R.id.et_liveing_speak);
        mBtnSpeakSend = (Button) mSpeakView.findViewById(R.id.btn_liveing_speak_send); // 发送消息


        // 关注页面(用户资料)
        // 用户详情页头像
        // 关注页面(用户资料)
        // 用户详情页头像
        mIvWindowAvatar = (ImageView) mDetailsView.findViewById(R.id.iv_liveing_window_avatar); //头像
        mTvWindowId = (TextView) mDetailsView.findViewById(R.id.tv_liveing_details_id);
        // ID
        mTvWindowAge = (TextView) mDetailsView.findViewById(R.id.tv_liveing_details_age);
        // 年龄
        mTvWindowName = (TextView) mDetailsView.findViewById(R.id.tv_liveing_details_name);
        // 姓名
        mTvWindowFllows = (TextView) mDetailsView.findViewById(R.id.tv_liveing_details_fllows);
        // 关注数
        mTvWindowFans = (TextView) mDetailsView.findViewById(R.id.tv_liveing_details_fans);
        // 粉丝
        mTvWindowGet = (TextView) mDetailsView.findViewById(R.id.tv_liveing_details_get);
        // 收礼
        mTvWindowGive = (TextView) mDetailsView.findViewById(R.id.tv_liveing_details_give);
        // 送礼


        mLlWindowShutup = (LinearLayout) mDetailsView.findViewById(R.id
                .ll_liveing_details_shutup); // 禁言or拉黑
        mTvWindowShutup = (TextView) mDetailsView.findViewById(R.id.tv_liveing_details_shutup);
        mIvWindowShutup = (ImageView) mDetailsView.findViewById(R.id.iv_liveing_details_shutup);

        mLlWindowOut = (LinearLayout) mDetailsView.findViewById(R.id.ll_liveing_details_out);
        // 踢人or举报
        mTvWindowOut = (TextView) mDetailsView.findViewById(R.id.tv_liveing_details_out);
        mIvWindowOut = (ImageView) mDetailsView.findViewById(R.id.iv_liveing_details_out);

        mIvWindowOut.setImageResource(R.mipmap.direct_seeding_iconfont_icon26);
        mIvWindowShutup.setImageResource(R.mipmap.direct_seeding_remindiconfont_jinyan);

        //        mLlWindowFllow = (LinearLayout) mDetailsView.findViewById(R.id
        // .ll_liveing_details_fllow);   // 关注按钮
        mTvWindowFllow = (TextView) mDetailsView.findViewById(R.id.tv_liveing_details_fllow); //
        // 关注按钮
        mTvWindowSetManage = (TextView) mDetailsView.findViewById(R.id
                .tv_liveing_details_setManage); //
        // 设置场控details_setManage); // 设置场控

        mProgress = (FrameLayout) findViewById(R.id.frame_progress); // 加载动画

        mLvChatroom = (ListView) findViewById(R.id.lv_liveing_chatroom); // 聊天消息展示
        // 被隐藏或显示的布局
        mTopbuttons = (LinearLayout) findViewById(R.id.ll_liveing_topbuttons);

        mRlMain = (RelativeLayout) findViewById(R.id.rl_liveing_main);

        // 观众列表
        mHlv_avatars = (HorizontalListView) findViewById(R.id.hlv_liveing_avatars);
        // 聊天列表

        mLlgiftcontent = (LinearLayout) findViewById(R.id.ll_live_gift_content);        // 礼物出现的位置


        mLiveingSwitch.setOnClickListener(this);
        mLiveingBack.setOnClickListener(this);
        mLiveFlashlight.setOnClickListener(this);
        mLiveManage.setOnClickListener(this);
        mLiveSpeak.setOnClickListener(this);
        mLiveShare.setOnClickListener(this);
        mLiveGone.setOnClickListener(this);
        mBtnSpeakSend.setOnClickListener(this);         // 发消息
        mLlWindowOut.setOnClickListener(this);          // 踢人
        mLlWindowShutup.setOnClickListener(this);       // 禁言
        mTvWindowSetManage.setOnClickListener(this);    // 设置场控
        mTvWindowFllow.setOnClickListener(this);        // 关注


        mHlv_avatars.setOnItemClickListener(this);
        mLvChatroom.setOnItemClickListener(this);

        ChatRoomMemberCache.getInstance().initGiftView(mContext, mLlgiftcontent);   // 初始化礼物动画


        setPrpgress(); // 消费progress的点击事件

    }

    /**
     * 初始化观众列表
     */
    private void initAudienceList() {


        //        if (!isNormalEmpty) {
        // 拉取固定在线成员
        //            getMembers(MemberQueryType.ONLINE_NORMAL, updateTime, 0);
        //        } else {
        // 拉取非固定成员
        getMembers(MemberQueryType.GUEST, enterTime, 0);
        //        }
    }

    private void clearCache() {
        updateTime = 0;
        enterTime = 0;
        isNormalEmpty = false;
        mAvatarData.clear();
        memberCache.clear();
    }

    /**
     * 获取成员列表
     * 暂时先写死  只获取最新的80条
     */
    private void getMembers(final MemberQueryType memberQueryType, final long time, int limit) {
        ChatRoomMemberCache.getInstance().fetchRoomMembers(mChatroomId, memberQueryType, 0, 80,
                new SimpleCallback<List<ChatRoomMember>>() {
                    @Override
                    public void onResult(boolean success, List<ChatRoomMember> result) {
                        if (success) {
                            //                            addMembers(result);
                            //                            mAvatarData.clear();
                            //                            mAvatarData.addAll(result);
                            //                            mChatRoomMemberAdapter.notifyDataSetChanged();
                            /*if (memberQueryType == MemberQueryType.ONLINE_NORMAL && result.size
                            () < LIMIT) {
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
            //            if (!isNormalEmpty) {
            //                updateTime = member.getUpdateTime();
            //            } else {
            //                enterTime = member.getEnterTime();
            //            }
            if (memberCache.containsKey(member.getAccount())) {
                mAvatarData.remove(memberCache.get(member.getAccount()));
            }
            memberCache.put(member.getAccount(), member);
            mAvatarData.add(member);
        }
        Collections.sort(mAvatarData, comp);
        mChatRoomMemberAdapter.notifyDataSetChanged();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_liveing_switch:        // 切换前后摄像头
                switchCamera();
                break;
            case R.id.iv_liveing_back:          // 关闭直播
                if (NoDoubleClick.isNotDoubleClick()) {
                    showExitLiveingDialog();
                }
                break;
            case R.id.iv_liveing_flashlight:    // 闪光灯
                mFlag = !mFlag;
                mLSMediaCapture.setCameraFlashPara(mFlag);
                break;
            case R.id.tv_liveing_manage:        //场控
                if (NoDoubleClick.isNotDoubleClick()) {
                    myChatRoomManage();
                    showPopupWindow(mManageView);
                }

                break;
            case R.id.iv_liveing_speak:         // 发言

                // 监听软键盘状态
                mPopupWindow = showPopupWindow(mSpeakView);
                new KeyboardChangeListener(this).setKeyBoardListener(new KeyboardChangeListener
                        .KeyBoardListener() {
                    @Override
                    public void onKeyboardChange(boolean isShow, int keyboardHeight) {
                        if (isShow) {
                            mPopupWindow.dismiss();
                        }
                    }
                });
                // 获取焦点
                mEtSpeak.requestFocus();
                InputMethodManager imm = (InputMethodManager) mEtSpeak.getContext()
                        .getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.toggleSoftInput(0, InputMethod.SHOW_FORCED);
                break;
            case R.id.iv_liveing_share:         //分享
//                if (NoDoubleClick.isNotDoubleClick()) {
//                    mPopShareWindow = showPopupWindow(mShareView);
//                }
                shareDialog.show();
                break;

            case R.id.iv_liveing_gone:          //隐藏多余界面
                mFlagGone = !mFlagGone;
                if (mFlagGone) {
                    mTopbuttons.setVisibility(View.GONE);
                    mLiveSpeak.setVisibility(View.GONE);
                    mLiveShare.setVisibility(View.GONE);
                    mLiveGone.setImageResource(R.mipmap.livein_iocn_cls);
                } else {
                    mTopbuttons.setVisibility(View.VISIBLE);
                    mLiveSpeak.setVisibility(View.VISIBLE);
                    mLiveShare.setVisibility(View.VISIBLE);
                    mLiveGone.setImageResource(R.mipmap.livein_iocn_cls_normal);
                }
                break;

            case R.id.btn_liveing_speak_send:   // 发送消息按钮
                String content = mEtSpeak.getText().toString();
                if (!TextUtils.isEmpty(content.trim())) {
                    mEtSpeak.setText("");
                    sendMessage(content);
                } else {
                    ToastUtil.showToast("发送的内容不能为空");
                }
                break;

            case R.id.ll_liveing_details_out:   // 踢人or举报

                if (mChatRoomMember.getAccount().equals(mMyMember.getAccount())) {
                    ToastUtil.showToast("不能以自己为目标");
                    return;
                }
                outMember(mChatRoomMember);
                break;
            case R.id.ll_liveing_details_shutup: // 禁言or拉黑
                if (mChatRoomMember.getAccount().equals(mMyMember.getAccount())) {
                    ToastUtil.showToast("不能以自己为目标");
                    return;
                }
                shutupBitch(mChatRoomMember);
                break;
            case R.id.tv_liveing_details_setManage: // 设为场控
                if (mChatRoomMember.getAccount().equals(mMyMember.getAccount())) {
                    ToastUtil.showToast("不能以自己为目标");
                    return;
                }
                setManage(mChatRoomMember);
                break;

            case R.id.tv_liveing_details_fllow:    // 关注
                if (mChatRoomMember.getAccount().equals(mMyMember.getAccount())) {
                    ToastUtil.showToast("不能以自己为目标");
                    return;
                }
                if (mFollowString.contains(mChatRoomMember.getAccount())) {
                    mTvWindowFllow.setText("取消关注");
                } else {
                    mTvWindowFllow.setText("关注");
                }
                followAdd(mChatRoomMember.getAccount());
                break;

            case R.id.tv_live_share_exit:

                break;
        }
    }


    /**
     * 禁言 只有场控及主播可以使用且不能以自己为目标
     *
     * @param chatRoomMember
     */
    private void shutupBitch(ChatRoomMember chatRoomMember) {
        String account = chatRoomMember.getAccount(); // 当前被选中用户id
        MemberOption option = new MemberOption(mChatroomId, account);
        NIMClient.getService(ChatRoomService.class).markChatRoomMutedList(!chatRoomMember.isMuted
                (), option).setCallback(new RequestCallback<ChatRoomMember>() {

            @Override
            public void onSuccess(ChatRoomMember member) {
                if (member.isMuted()) {
                    mTvWindowShutup.setText("解除禁言");
                } else {
                    mTvWindowShutup.setText("禁言");
                }
            }

            @Override
            public void onFailed(int i) {
                ToastUtil.showToast("禁言失败" + i);
            }

            @Override
            public void onException(Throwable throwable) {

            }
        });
    }

    // 开始直播
    private void startAV() {
        if (mLSMediaCapture != null && m_liveStreamingInitFinished) {

            // 开始直播
            mLSMediaCapture.startLiveStreaming();
            m_liveStreamingOn = true;
            ToastUtil.showToast("开始直播");
        }
    }

    /**
     * 聊天室相关
     * 发送消息
     *
     * @param content
     */
    private void sendMessage(final String content) {
        if (content != null) {
            // 创建文本消息
            final ChatRoomMessage message = ChatRoomMessageBuilder.createChatRoomTextMessage
                    (mChatroomId, // 聊天室id
                            content // 文本内容
                    );

            // 发送消息。如果需要关心发送结果，可设置回调函数。发送完成时，会收到回调。如果失败，会有具体的错误码。
            NIMClient.getService(ChatRoomService.class).sendMessage(message, true).setCallback
                    (new RequestCallback<Void>() {
                        @Override
                        public void onSuccess(Void aVoid) {
                            Log.d("liveingActivity", "消息发送成功" + content);
                            mEtSpeak.setText("");
                            if (message.getContent() != null && !message.getContent().equals("")) {
                                mChatRoomAdapter.addItem(new ChatRoomItemBean2(2, message));
                            }
                        }

                        @Override
                        public void onFailed(int i) {
                            Log.d("liveingActivity", "消息发送失败" + i);
                            ToastUtil.showToast("消息发送失败" + i);
                        }

                        @Override
                        public void onException(Throwable throwable) {
                            Log.d("liveingActivity", "消息发送错误" + throwable);
                            ToastUtil.showToast("消息发送错误" + throwable);
                        }
                    });
        }
    }

    /**
     * 切换前置摄像头
     */
    private void switchCamera() {
        if (mLSMediaCapture != null) {
            mLSMediaCapture.switchCamera();
        }
    }

    public void paraSet() {
        //创建参数实例
        mLSLiveStreamingParaCtx = mLSMediaCapture.new LSLiveStreamingParaCtx();
        mLSLiveStreamingParaCtx.eHaraWareEncType = mLSLiveStreamingParaCtx.new HardWareEncEnable();
        mLSLiveStreamingParaCtx.eOutFormatType = mLSLiveStreamingParaCtx.new OutputFormatType();
        mLSLiveStreamingParaCtx.eOutStreamType = mLSLiveStreamingParaCtx.new OutputStreamType();
        mLSLiveStreamingParaCtx.sLSAudioParaCtx = mLSLiveStreamingParaCtx.new LSAudioParaCtx();
        mLSLiveStreamingParaCtx.sLSAudioParaCtx.codec = mLSLiveStreamingParaCtx.sLSAudioParaCtx
                .new LSAudioCodecType();
        mLSLiveStreamingParaCtx.sLSVideoParaCtx = mLSLiveStreamingParaCtx.new LSVideoParaCtx();
        mLSLiveStreamingParaCtx.sLSVideoParaCtx.codec = mLSLiveStreamingParaCtx.sLSVideoParaCtx
                .new LSVideoCodecType();
        mLSLiveStreamingParaCtx.sLSVideoParaCtx.cameraPosition = mLSLiveStreamingParaCtx
                .sLSVideoParaCtx.new CameraPosition();
        mLSLiveStreamingParaCtx.sLSVideoParaCtx.interfaceOrientation = mLSLiveStreamingParaCtx
                .sLSVideoParaCtx.new CameraOrientation();
        mLSLiveStreamingParaCtx.sLSQoSParaCtx = mLSLiveStreamingParaCtx.new LSQoSParaCtx();

        //设置摄像头信息，并开始本地视频预览
        mLSLiveStreamingParaCtx.sLSVideoParaCtx.cameraPosition.cameraPosition =
                CAMERA_POSITION_BACK;
        //默认后置摄像头，用户可以根据需要调整

        //输出格式：视频、音频和音视频
        mLSLiveStreamingParaCtx.eOutStreamType.outputStreamType = HAVE_AV;//默认音视频推流

        //输出封装格式
        mLSLiveStreamingParaCtx.eOutFormatType.outputFormatType = RTMP;//默认RTMP推流

        //摄像头参数配置
        mLSLiveStreamingParaCtx.sLSVideoParaCtx.interfaceOrientation.interfaceOrientation =
                CAMERA_ORIENTATION_PORTRAIT;//默认竖屏

        //音频编码参数配置
        mLSLiveStreamingParaCtx.sLSAudioParaCtx.samplerate = 44100;
        mLSLiveStreamingParaCtx.sLSAudioParaCtx.bitrate = 64000;
        mLSLiveStreamingParaCtx.sLSAudioParaCtx.frameSize = 2048;
        mLSLiveStreamingParaCtx.sLSAudioParaCtx.audioEncoding = AudioFormat.ENCODING_PCM_16BIT;
        mLSLiveStreamingParaCtx.sLSAudioParaCtx.channelConfig = AudioFormat.CHANNEL_IN_MONO;
        mLSLiveStreamingParaCtx.sLSAudioParaCtx.codec.audioCODECType = LS_AUDIO_CODEC_AAC;

        //硬件编码参数设置
        mLSLiveStreamingParaCtx.eHaraWareEncType.hardWareEncEnable = mHardWareEncEnable;//默认关闭硬件编码

        //网络QoS开关
        mLSLiveStreamingParaCtx.sLSQoSParaCtx.qosType = OpenQoS;//默认打开QoS

        //视频编码参数配置，视频码率可以由用户任意设置，视频分辨率按照如下表格设置
        //采集分辨率     编码分辨率     建议码率
        //1280x720     1280x720     1500kbps
        //1280x720     960x540      800kbps
        //960x720      960x720      1000kbps
        //960x720      960x540      800kbps
        //960x540      960x540      800kbps
        //640x480      640x480      600kbps
        //640x480      640x360      500kbps
        //320x240      320x240      250kbps
        //320x240      320x180      200kbps

        //如下是编码分辨率等信息的设置
        if (mVideoResolution.equals("HD")) {
            mLSLiveStreamingParaCtx.sLSVideoParaCtx.fps = 20;
            mLSLiveStreamingParaCtx.sLSVideoParaCtx.bitrate = 1500000;
            mLSLiveStreamingParaCtx.sLSVideoParaCtx.codec.videoCODECType = LS_VIDEO_CODEC_AVC;
            mLSLiveStreamingParaCtx.sLSVideoParaCtx.width = 1280;
            mLSLiveStreamingParaCtx.sLSVideoParaCtx.height = 720;
        } else if (mVideoResolution.equals("SD")) {
            mLSLiveStreamingParaCtx.sLSVideoParaCtx.fps = 20;
            mLSLiveStreamingParaCtx.sLSVideoParaCtx.bitrate = 650000;
            mLSLiveStreamingParaCtx.sLSVideoParaCtx.codec.videoCODECType = LS_VIDEO_CODEC_AVC;
            mLSLiveStreamingParaCtx.sLSVideoParaCtx.width = 640;
            mLSLiveStreamingParaCtx.sLSVideoParaCtx.height = 480;
        } else {
            mLSLiveStreamingParaCtx.sLSVideoParaCtx.fps = 15;
            mLSLiveStreamingParaCtx.sLSVideoParaCtx.bitrate = 250000;
            mLSLiveStreamingParaCtx.sLSVideoParaCtx.codec.videoCODECType = LS_VIDEO_CODEC_AVC;
            mLSLiveStreamingParaCtx.sLSVideoParaCtx.width = 320;
            mLSLiveStreamingParaCtx.sLSVideoParaCtx.height = 240;
        }
    }

    //视频缩放和摄像头对焦操作相关方法
    @Override
    public boolean onTouchEvent(MotionEvent event) {

        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                //Log.i(TAG, "test: down!!!");
                //调用摄像头对焦操作相关API
                if (mLSMediaCapture != null) {
                    mLSMediaCapture.setCameraFocus();
                }
                break;
            case MotionEvent.ACTION_MOVE:
                //Log.i(TAG, "test: move!!!");
                /**
                 * 首先判断按下手指的个数是不是大于两个。
                 * 如果大于两个则执行以下操作（即图片的缩放操作）。
                 */
                if (event.getPointerCount() >= 2) {

                    float offsetX = event.getX(0) - event.getX(1);
                    float offsetY = event.getY(0) - event.getY(1);
                    /**
                     * 原点和滑动后点的距离差
                     */
                    mCurrentDistance = (float) Math.sqrt(offsetX * offsetX + offsetY * offsetY);
                    if (mLastDistance < 0) {
                        mLastDistance = mCurrentDistance;
                    } else {
                        /**
                         * 如果当前滑动的距离（currentDistance）比最后一次记录的距离（lastDistance）相比大于5英寸（也可以为其他尺寸），
                         * 那么现实图片放大
                         */
                        if (mCurrentDistance - mLastDistance > 5) {
                            //Log.i(TAG, "test: 放大！！！");
                            if (mLSMediaCapture != null) {
                                mLSMediaCapture.setCameraZoomPara(true);
                            }

                            mLastDistance = mCurrentDistance;
                            /**
                             * 如果最后的一次记录的距离（lastDistance）与当前的滑动距离（currentDistance）相比小于5英寸，
                             * 那么图片缩小。
                             */
                        } else if (mLastDistance - mCurrentDistance > 5) {
                            //Log.i(TAG, "test: 缩小！！！");
                            if (mLSMediaCapture != null) {
                                mLSMediaCapture.setCameraZoomPara(false);
                            }
                            mLastDistance = mCurrentDistance;
                        }
                    }
                }
                break;
            case MotionEvent.ACTION_UP:
                //Log.i(TAG, "test: up!!!");
                break;
        }
        return true;
    }

    @Override
    public void onBackPressed() {
        showExitLiveingDialog();
    }

    /**
     * 停止直播
     */
    //退出提示框
    private void showExitLiveingDialog() {
        new AlertDialogIOS(LivingActivity.this).builder().setTitle("提示")
                .setMsg("直播将结束,确认退出?")
                .setPositiveButton("确认", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        exitChatRoom(); // 退出聊天室
                        closeLive(new onCloseLiveSuccessListener() {
                            @Override
                            public void onSuccess(String json) {
                                m_tryToStopLivestreaming = true;
                                mLSMediaCapture.stopLiveStreaming();
                                initExitJson(json);
                            }
                        });

                    }
                }).setNegativeButton("取消", new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        }).show();
    }


    /**
     * @param json
     */
    private void initExitJson(String json) {
        LiveExitBean bean = JSONObject.parseObject(json, LiveExitBean.class);
        LiveExitBean.AaDataBean aaData = bean.getAaData();
        if (!bean.isSuccess()) {
            ToastUtil.showToast(bean.getErrMsg().toString());
        }
        startExitActivity(aaData);
    }

    /**
     * 展示退出页面
     *
     * @param aaData
     */
    private void startExitActivity(LiveExitBean.AaDataBean aaData) {
        if (aaData != null) {
            // 房主头像
          /*  String icon = SpUtil.getString(mContext, "liveAvatar") != null ? SpUtil.getString
                    (mContext, "liveAvatar") : aaData.getIcon();*/
          /*  String icon = SpUtil.getString(mContext, "liveAvatar") != null ? SpUtil.getString
                    (mContext, "liveAvatar") : aaData.getIcon();*/
            // 房间号
            String roomsId = aaData.getRoomsId() == null ? "" : aaData.getRoomsId();
            // 直播时长
            String caseTimeStr = aaData.getCaseTimeStr() == null ? "" : aaData.getCaseTimeStr();
            // 关注
            int follows = aaData.getFollows();
            // 兴趣币
            int stdCoin = aaData.getStdCoin();
            Intent intent = new Intent(mContext, LiveExitActivity.class);
            intent.putExtra("roomsId", roomsId);
            intent.putExtra("caseTimeStr", caseTimeStr);
            intent.putExtra("follows", follows);
            intent.putExtra("stdCoin", stdCoin);
            intent.putExtra("image", image);
            intent.putExtra("shareContent", shareContent);

            startActivity(intent);
            finish();
        } else {
            LivingActivity.this.finish();
        }
    }

    /**
     * 关闭直播间的网络请求
     */
    private void closeLive(final onCloseLiveSuccessListener onCloseLiveSuccessListener) {
        String url = UrlUtils.URL_LIVECLOSE;
        HttpUtils http = MyApp.httpUtils;
        RequestParams params = new RequestParams();
        params.addHeader("X-CustomToken", token);
        params.addBodyParameter("id", mId);
        http.send(HttpRequest.HttpMethod.POST, url, params, new RequestCallBack<String>() {
            @Override
            public void onSuccess(ResponseInfo<String> responseInfo) {
                String json = responseInfo.result;
                onCloseLiveSuccessListener.onSuccess(json);
                //                        ToastUtil.showToast(mContext, "退出直播" + json);
                //   mProgress.setVisibility(View.GONE);
            }

            @Override
            public void onFailure(HttpException e, String s) {
                ToastUtil.showToast("网络错误");
                mProgress.setVisibility(View.GONE);
                finish();
            }
        });
    }

    public interface onCloseLiveSuccessListener {
        void onSuccess(String json);
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
        reason.put("reason", "就是不爽！");
        NIMClient.getService(ChatRoomService.class).kickMember(mChatroomId, chatRoomMember
                .getAccount(), reason).setCallback(new RequestCallback<Void>() {
            @Override
            public void onSuccess(Void param) {
                ToastUtil.showToast("踢人成功");
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
     * 初始化用户信息
     */
    private void initUsetInfo(final ChatRoomMember member) {
        mProgress.setVisibility(View.VISIBLE);
        final String account = member.getAccount(); // 用户账号
       /* NimUserInfo user = NIMClient.getService(UserService.class).getUserInfo(account); //
       从本地数据库获取用户个人资料
        if (user == null) {   // 从云信数据库获取用户个人资料*/
        ArrayList accounts = new ArrayList();
        accounts.add(account);
        NIMClient.getService(UserService.class).fetchUserInfo(accounts).setCallback(new RequestCallback<List<NimUserInfo>>
                () {
            @Override
            public void onSuccess(List<NimUserInfo> userInfos) {
                if (userInfos != null && userInfos.size() > 0) {
                    String json = userInfos.get(0).getExtension();
                    mChatUserInfoBean = JSONObject.parseObject(json, ChatUserInfoBean.class);
                    if (mChatUserInfoBean != null) {
                        String avatar = member.getAvatar(); // 用户头像
                        image = avatar;//分享图
                        // 添加头像
                        Glide.with(mContext)
                                //                                .load(UrlUtils.url + avatar)
                                .load(avatar).error(R.drawable.default_icon).placeholder(R
                                .drawable.default_icon).transform(new GlideCircleTrans(mContext))
                                .into(mIvWindowAvatar);
                        if (mChatUserInfoBean != null) {
                            String name = member.getNick(); // 用户昵称
                            String userId = mChatUserInfoBean.getId();   // 用户ID
                            String age = mChatUserInfoBean.getAge() != null ?
                                    mChatUserInfoBean.getAge() : ""; //
                            // 用户年龄
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
                            mTvWindowOut.setText("踢人");

                            if (mFollowString.contains(mChatRoomMember.getAccount())) {
                                mTvWindowFllow.setText("取消关注");
                            } else {
                                mTvWindowFllow.setText("关注");
                            }
                            // 更新个人信息
                            ChatRoomMemberCache.getInstance().fetchMember(mChatroomId, account,
                                    new SimpleCallback<ChatRoomMember>() {
                                        @Override
                                        public void onResult(boolean success, ChatRoomMember result) {
                                            if (success) {
                                                mChatRoomMember = result; // 更新信息
                                                if (member.isMuted()) {
                                                    mTvWindowShutup.setText("解除禁言");
                                                } else {
                                                    mTvWindowShutup.setText("禁言");
                                                }
                                                if (mChatRoomMember.getMemberType() == MemberType.ADMIN) { // 如果为管理员
                                                    chatroomCommand = "guest";
                                                    mTvWindowSetManage.setText("取消场控");
                                                } else {
                                                    chatroomCommand = "manager";
                                                    mTvWindowSetManage.setText("设为场控");
                                                }
                                                mProgress.setVisibility(View.GONE);
                                            } else {
                                                ToastUtil.showToast("获取个人信息失败,请检查您的网络连接");
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
                ToastUtil.showToast("从服务器获取用户资料失败" + i);
                mProgress.setVisibility(View.GONE);
            }

            @Override
            public void onException(Throwable throwable) {
                mProgress.setVisibility(View.GONE);
            }
        });

    }

    /**
     * 拉黑
     *
     * @param member 万一用得到捏
     */
    private void addBlack(ChatRoomMember member) {
        final String accid = mChatUserInfoBean.getAccid();
        mProgress.setVisibility(View.VISIBLE);
        HttpUtils httpUtils = new HttpUtils();
        RequestParams params = new RequestParams();
        params.addHeader("X-CustomToken", token);
        params.addBodyParameter("targetAccid", accid);
        params.addBodyParameter("syskey", BLACKLIST);
        String url = UrlUtils.URL_MYBLACK_ADD;
        httpUtils.send(HttpRequest.HttpMethod.POST, url, params, new RequestCallBack<String>() {
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
                mProgress.setVisibility(View.GONE);
            }

            @Override
            public void onFailure(HttpException e, String s) {
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
     * 查询我的场控
     */
    private void myChatRoomManage() {
        HttpUtils httpUtils = new HttpUtils();
        RequestParams params = new RequestParams();
        params.addHeader("X-CustomToken", token);
        params.addBodyParameter("types", "manager");
        String url = UrlUtils.URL_CHATROOMMANAGE;
        httpUtils.send(HttpRequest.HttpMethod.POST, url, params, new RequestCallBack<String>() {

            @Override
            public void onSuccess(ResponseInfo<String> responseInfo) {
                String json = responseInfo.result;
                ChatRoomManageBean bean = JSONObject.parseObject(json, ChatRoomManageBean.class);
                ManageBeanAaData = bean.getAaData();
                mChatRoomManageList.clear();
                mChatRoomManageList.addAll(ManageBeanAaData);
                mChatRoomManageAdapter.notifyDataSetChanged();
                if (ManageBeanAaData.size() < 1) {
                    mTvMyManageText.setVisibility(View.VISIBLE);
                } else {
                    mTvMyManageText.setVisibility(View.GONE);
                }
                List<ChatRoomManageBean.AaDataBean> aaData = bean.getAaData();
                //分享直播id获取
                if (aaData != null && aaData.size() > 0) {
                    shareid = aaData.get(0).getRoomsId();
                    image = mLiveAvatar;//分享图
                    shareContent = mLiveName + "正在[兴趣班]直播,快来欣赏吧";
                }
            }

            @Override
            public void onFailure(HttpException e, String s) {

            }
        });
    }

    /**
     * 实现类，响应按钮点击事件
     */
//    private ChatRoomManageAdapter.MyClickListener mListener = new ChatRoomManageAdapter
//            .MyClickListener() {
//        @Override
//        public void myOnClick(int position, View v) {
//            ToastUtil.showToast("这个按钮被点击了");
//            if (ManageBeanAaData != null && ManageBeanAaData.size() > 0) {
//                String accid = ManageBeanAaData.get(position).getChatUser().getAccid();
//                followAdd(accid);
//            }
//        }
//    };

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
        utils.send(HttpRequest.HttpMethod.POST, url, params, new RequestCallBack<String>() {
            @Override
            public void onSuccess(ResponseInfo<String> responseInfo) {
                String json = responseInfo.result;
                ChatRoomAddFollowBean bean = JSONObject.parseObject(json, ChatRoomAddFollowBean
                        .class);
                mFollowString = bean.getAaData() == null ? "" : bean.getAaData();
                if (mFollowString.contains(accid)) {
                    mTvWindowFllow.setText("取消关注");
                } else {
                    mTvWindowFllow.setText("关注");
                }
                mProgress.setVisibility(View.GONE);
                myChatRoomManage();
            }

            @Override
            public void onFailure(HttpException e, String s) {
                mProgress.setVisibility(View.GONE);
            }
        });
    }

    /**
     * 个人关注列表
     */
    private void myFollowList() {
        mProgress.setVisibility(View.VISIBLE);
        HttpUtils utils = new HttpUtils();
        RequestParams params = new RequestParams();
        params.addHeader("X-CustomToken", token);
        String url = UrlUtils.URL_GET_FOLLOW;
        utils.send(HttpRequest.HttpMethod.POST,
                url,
                params,
                new RequestCallBack<String>() {
                    @Override
                    public void onSuccess(ResponseInfo<String> responseInfo) {
                        String json = responseInfo.result;
                        ChatRoomFollowBean bean = JSONObject.parseObject(json,
                                ChatRoomFollowBean.class);
                        ChatRoomFollowBean.AaDataBean aaData = bean.getAaData();
                        if (aaData != null) {
                            List<ChatRoomFollowBean.AaDataBean.FollowesBean> followes = aaData
                                    .getFollowes();
                            if (followes != null && followes.size() > 0) {
                                StringBuffer buffer = new StringBuffer();
                                for (ChatRoomFollowBean.AaDataBean.FollowesBean followe : followes) {
                                    String targetAccid = followe.getTargetAccid(); //  被关注者的accid
                                    buffer.append(targetAccid + ",");
                                }
                                mFollowString = buffer.toString();
                            }
                        }
                        mProgress.setVisibility(View.GONE);
                    }

                    @Override
                    public void onFailure(HttpException e, String s) {
                        mProgress.setVisibility(View.GONE);
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


    private void initShare() {
        shareDialog = new ShareDialog(LivingActivity.this, R.style.ActionSheetDialogStyle);

        shareDialog.QQZone(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mlist.add(image);
                shareDialog.dismiss();
                ThirdPartyUtils.getInstance(LivingActivity.this).shareUrlToZone(LivingActivity.this, shareUrl + shareid, sharetitle, shareContent, mlist);

            }
        });

        shareDialog.qq(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                shareDialog.dismiss();
                ThirdPartyUtils.getInstance(LivingActivity.this).shareToQQ(LivingActivity.this, shareUrl + shareid, sharetitle, shareContent, image);
            }
        });
        shareDialog.wechat(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                shareDialog.dismiss();
                ThirdPartyUtils.getInstance(LivingActivity.this).shareUrlToWechat(shareUrl + shareid, sharetitle, shareContent, image, false, true);
            }
        });
        shareDialog.wechat_zone(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                shareDialog.dismiss();
                ThirdPartyUtils.getInstance(LivingActivity.this).shareUrlToWechat(shareUrl + shareid, sharetitle, shareContent, image, false, false);

            }
        });
        shareDialog.weibo(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                shareDialog.dismiss();
                ThirdPartyUtils.getInstance(LivingActivity.this).shareToWeibo(LivingActivity.this, sharetitle, image);

            }
        });
    }
}

