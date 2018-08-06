package com.zhangju.xingquban.interestclassapp.ui.activity.find.liveradio;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.hardware.Camera;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.text.TextUtils;
import android.util.Log;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.fastlib.widget.RoundImageView;
import com.imnjh.imagepicker.SImagePicker;
import com.imnjh.imagepicker.activity.PhotoPickerActivity;
import com.zhangju.xingquban.R;
import com.zhangju.xingquban.interestclassapp.RetrofitInterface.NetWork;
import com.zhangju.xingquban.interestclassapp.bean.live.LiveStartBean;
import com.zhangju.xingquban.interestclassapp.refactor.common.utils.ThirdPartyUtils;
import com.zhangju.xingquban.interestclassapp.refactor.location.LocationManager;
import com.zhangju.xingquban.interestclassapp.refactor.user.User;
import com.zhangju.xingquban.interestclassapp.refactor.user.UserManager;
import com.zhangju.xingquban.interestclassapp.ui.activity.AgreementsActivity;
import com.zhangju.xingquban.interestclassapp.ui.fragment.live.StartLive.PermissionUtils;
import com.zhangju.xingquban.interestclassapp.util.SpUtil;
import com.zhangju.xingquban.interestclassapp.util.ToastUtil;
import com.zhangju.xingquban.interestclassapp.util.UrlUtils;
import com.zhangju.xingquban.interestclassapp.util.click.NoDoubleClick;
import com.zhangju.xingquban.interestclassapp.util.location.LocationHelper;
import com.zhangju.xingquban.interestclassapp.view.dialog.MyPopupWindow;
import com.zhangju.xingquban.interestclassapp.view.dialog.SetNumDialog;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

//开始直播页面StartLiveActivity
public class StartLiveActivity extends Activity implements View.OnClickListener, CompoundButton.OnCheckedChangeListener, SurfaceHolder.Callback {

    private SurfaceView surfaceview;
    private SurfaceHolder surfaceholder;
    private Camera camera = null;
    private LinearLayout mLinearLayout, location, payToLive, choiceSubject;
    private Button mStartLive;
    private CheckBox weibo, moments, wechat, qq, qzone;
    private ImageView cancel;
    private EditText mEditText;
    private List<CheckBox> cbs;
    private final static int PERMISSION_REQUEST = 50;   // 相机权限
    private final static int REQUEST_CODE_GPS = 0;    // 定位权限

    private Object mLiveString;
    private RoundImageView mIvTitleIcon;
    private String sessionid;
    private Context mContext;
    private FrameLayout mFrameLayout;
    private LinearLayout mLlSetpay;
    private View mPwSetpay;
    private RelativeLayout mRlMain;
    private Button mBtnSetPrice;
    private Button mBtnSetNull;
    private Button mBtnDialogDismiss;
    private PopupWindow mPopupWindow;

    private String mAvatarPic; // 用户头像

    private int price = 0; // 直播间价格
    private LinearLayout mLlChoiceSubject;
    private TextView mTvChoiceSubject;

    private CheckBox mCbAgaree;
    private TextView mTvAgreement;
    private int mShareFlag = -1; // 记录分享的类型
    private EditText mEtSetTitle;
    private TextView mTvLocation;

    private SharedPreferences cityName;
    private SharedPreferences cityId;
    private LinearLayout mLlLocation;


    private String typeName;//科目类型名称
    private String typeid;//科目类型id
    private String subjectName;//具体科目名称
    private String subjectId;//具体科目id


    /**
     * 接收选择图片的本地路径
     */
    private ArrayList<String> pathList;

    /**
     * 分享
     * @param savedInstanceState
     */

    /**
     * 分享操作
     */

    private String sharetitle = "为兴趣而生，为梦想而来";
    private String shareid = "";
    private String shareUrl = "http://my.xqban.com/admnxzcmr/rooms/share?id=" ;
    private String image = "";
    private ArrayList<String> mlist = new ArrayList<>();
    private String shareContent = "";
    private int shareType = 0;//1微信 2微信朋友圈 3qq 4空间  5微博

    private ArrayList<String> listName=new ArrayList<>();//选中科目

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_live);
        mContext = this;

        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
        pathList = new ArrayList< >();
        initView();
        initData();
        initWidget();
        initSurface();
        initCb();
        checkPublishPermission();

    }


    private void initData() {
        mTvLocation.setText(LocationManager.getInstance().getLocation().locationCity);
        User user = UserManager.getInstance().getUser();
        Glide.with(this)
                .load(user.picture)
                .placeholder(R.mipmap.me_touxiang)
                .error(R.mipmap.me_touxiang)
                .dontTransform()
                .dontAnimate()
                .into(mIvTitleIcon);
    }

    private void initView() {
        mStartLive = (Button) findViewById(R.id.btn_live_start);        // 开始直播
        mFrameLayout = (FrameLayout) findViewById(R.id.frame_progress); // 加载动画
        mIvTitleIcon = (RoundImageView) findViewById(R.id.title_icon);         // 直播封面
        mRlMain = (RelativeLayout) findViewById(R.id.rl_live_start_main);
        mLlSetpay = (LinearLayout) findViewById(R.id.ll_live_start_setpay); // 设置支付方式
        mLlChoiceSubject = (LinearLayout) findViewById(R.id.ll_live_start_choice_subject);  // 选择直播科目
        mTvChoiceSubject = (TextView) findViewById(R.id.tv_live_start_choice_subject);
        mEtSetTitle = (EditText) findViewById(R.id.et_live_start_title); // 设置直播间标题
        mTvLocation = (TextView) findViewById(R.id.tv_live_start_location); // 位置
        // 位置
        mLlLocation = (LinearLayout) findViewById(R.id.ll_live_start_location);

        mCbAgaree = (CheckBox) findViewById(R.id.cb_live_start_agaree);       // 是否同意直播协议
        mTvAgreement = (TextView) findViewById(R.id.tv_live_start_agreement);   // 直播协议

        mPwSetpay = getLayoutInflater().inflate(R.layout.popupwindow_live_setpay, null);

        // 弹窗按钮
        mBtnSetPrice = (Button) mPwSetpay.findViewById(R.id.btn_live_setpay_setpirce);        //设置门票价格
        mBtnSetNull = (Button) mPwSetpay.findViewById(R.id.btn_live_setpay_setnull); // 恢复免费
        mBtnDialogDismiss = (Button) mPwSetpay.findViewById(R.id.btn_live_setpay_dismiss); // 取消

        // 用户信息弹窗

        mStartLive.setOnClickListener(this);
        mIvTitleIcon.setOnClickListener(this);
        mLlSetpay.setOnClickListener(this);
        mLlLocation.setOnClickListener(this);
        mBtnSetPrice.setOnClickListener(this);
        mBtnSetNull.setOnClickListener(this);
        mBtnDialogDismiss.setOnClickListener(this);
        mLlChoiceSubject.setOnClickListener(this);
        mTvAgreement.setOnClickListener(this);

        setPrpgress(); // 消费掉framelayout的点击事件
    }

    private void initSurface() {
        surfaceholder = surfaceview.getHolder();
        surfaceholder.addCallback(this);
    }

    private void initCb() {
        weibo = (CheckBox) findViewById(R.id.SinaWeibo);
        moments = (CheckBox) findViewById(R.id.cb_live_share_WechatMoments);
        wechat = (CheckBox) findViewById(R.id.cb_live_share_Wechat);
        qq = (CheckBox) findViewById(R.id.cb_live_share_QQ);
        qzone = (CheckBox) findViewById(R.id.cb_live_share_QZone);

        weibo.setOnCheckedChangeListener(this);
        moments.setOnCheckedChangeListener(this);
        wechat.setOnCheckedChangeListener(this);
        qq.setOnCheckedChangeListener(this);
        qzone.setOnCheckedChangeListener(this);

        cbs = new ArrayList<>();
        cbs.add(weibo);
        cbs.add(moments);
        cbs.add(wechat);
        cbs.add(qq);
        cbs.add(qzone);
        for (int i = 0; i < mLinearLayout.getChildCount(); i++) {
            CheckBox cb = (CheckBox) mLinearLayout.getChildAt(i);
            cb.setTag(i);
            cbs.add(cb);
        }
    }

    private void initWidget() {
        cancel = (ImageView) findViewById(R.id.tv_order_topay);
        cancel.setOnClickListener(this);
        mLinearLayout = (LinearLayout) findViewById(R.id.share_list);
        surfaceview = (SurfaceView) findViewById(R.id.surfaceView);
    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        if (isChecked) {
            for (int i = 0; i < cbs.size(); i++) {
                if (buttonView.getId() != cbs.get(i).getId()) {
                    cbs.get(i).setChecked(false);
                }
            }
        }

        switch (buttonView.getId()) {
            case R.id.SinaWeibo:
                shareType = 5;
                break;
            case R.id.cb_live_share_WechatMoments:
                shareType = 2;
                break;
            case R.id.cb_live_share_Wechat:
                shareType = 1;
                break;
            case R.id.cb_live_share_QQ:
                shareType = 3;

                break;
            case R.id.cb_live_share_QZone:
                shareType = 4;
                break;
        }
    }

    @Override
    public void surfaceCreated(final SurfaceHolder holder) {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) {
            openCamera(holder);
        } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            /*6.0权限判断*/
            PermissionUtils.newInstance().requestPermission(this,
                    PERMISSION_REQUEST,
                    Manifest.permission.CAMERA,
                    new Runnable() {
                        @Override
                        public void run() {
                            openCamera(holder);
                        }
                    });
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        switch (requestCode) {
            case PERMISSION_REQUEST:
                if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    openCamera(surfaceview.getHolder());
                } else {
                    Toast.makeText(this, R.string.please_to_open_permission_for_storage, Toast.LENGTH_SHORT).show();
                }
                break;
            case REQUEST_CODE_GPS:
                break;


            default:
                break;
        }
    }

    private void openCamera(SurfaceHolder holder) {
        //获取camera对象
        if (camera == null) {
            camera = Camera.open(Camera.CameraInfo.CAMERA_FACING_FRONT);
            try {
                //设置预览监听
                camera.setPreviewDisplay(holder);
                Camera.Parameters parameters = camera.getParameters();

                if (this.getResources().getConfiguration().orientation
                        != Configuration.ORIENTATION_LANDSCAPE) {
                    parameters.set("orientation", "portrait");
                    camera.setDisplayOrientation(90);
                    parameters.setRotation(90);
                } else {
                    parameters.set("orientation", "landscape");
                    camera.setDisplayOrientation(0);
                    parameters.setRotation(0);
                }
                camera.setParameters(parameters);
                //启动摄像头预览
                camera.startPreview();
                System.out.println("camera.startpreview");

            } catch (IOException e) {
                e.printStackTrace();
                camera.release();
                System.out.println("camera.release");
            }
        }

    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
        Camera.Parameters mParameters = camera.getParameters();
        mParameters.setPreviewSize(width, height);
        //        setPictureSize(mParameters);
        //        camera.setParameters(mParameters);
    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        if (camera != null) {
            camera.stopPreview();
            camera.release();
            camera = null;
        }
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_order_topay:
                finish();
                break;
            case R.id.btn_live_start: // 开始直播

                if (!mCbAgaree.isChecked()) {
                    ToastUtil.showToast(mContext, "同意兴趣班直播协议后方可开始直播");
                    return;
                }

                if (shareType == 0) {

                    if (NoDoubleClick.isNotDoubleClick()) {
                        mFrameLayout.setVisibility(View.VISIBLE);
                        String url = UrlUtils.URL_LIVESTART;
                        addLiveRoom(url);
                        SpUtil.putString(mContext, "setLivePrice", null); // 将缓存在sp的价格清空
                    }
                } else {
                    toShare();
                }
                break;
            case R.id.title_icon: // 选择直播封面
                selectpic();
                break;
            case R.id.ll_live_start_choice_subject:
                Intent intent = new Intent(StartLiveActivity.this, CourseChoiceActivity.class);
                intent.putExtra("type", false);
                intent.putExtra(CourseChoiceActivity.ARG_LIST_STR_NAME,listName);
                startActivityForResult(intent, 3);
                break;
            case R.id.ll_live_start_setpay:
                mPopupWindow = MyPopupWindow.getInstance().makePopupWindow(StartLiveActivity.this,
                        mPwSetpay);
                mPopupWindow.showAtLocation(mRlMain, Gravity.BOTTOM, 0, 0);
                break;

            case R.id.btn_live_setpay_setpirce:     // 设置门票价格

                mPopupWindow.dismiss();
                showSetDialog();
                break;
            case R.id.btn_live_setpay_setnull:
                price = 0;
                SpUtil.putString(mContext, "setLivePrice", null);
                mPopupWindow.dismiss();
                break;
            case R.id.btn_live_setpay_dismiss:
                if (mPopupWindow == null) {
                    return;
                }
                mPopupWindow.dismiss();
                break;

            case R.id.tv_live_start_agreement: // 直播协议
                Intent intent1 = new Intent(StartLiveActivity.this, AgreementsActivity.class);
                intent1.putExtra("title", "兴趣班用户直播协议");
                intent1.putExtra("url", UrlUtils.URL_LIVE_AGREEMENT);
                startActivity(intent1);
                break;
            case R.id.ll_live_start_location: // 定位
                initLocation();
                break;
        }
    }


    private void showSetDialog() {
        final SetNumDialog myDialog = new SetNumDialog(mContext);
        myDialog.setYesOnclickListener("确定", new SetNumDialog.OnYesOnclickListener() {
            @Override
            public void onYesClick() {
                price = myDialog.getPrice();
                if (price>0) {
                    SpUtil.putString(mContext, "setLivePrice", price + "");
                    myDialog.dismiss();
                }
            }
        });
        myDialog.setNoOnclickListener("取消", new SetNumDialog.OnNoOnclickListener() {
            @Override
            public void onNoClick() {
                myDialog.dismiss();
            }
        });
        myDialog.show();
    }

    Observer<LiveStartBean> observer = new Observer<LiveStartBean>() {
        @Override
        public void onCompleted() {

        }

        @Override
        public void onError(Throwable e) {
            Log.e("2017", e.toString());
        }

        @Override
        public void onNext(LiveStartBean liveStartBean) {
            if (liveStartBean.isSuccess() && liveStartBean.getAaData().getChannels() != null) {

                LiveStartBean.AaDataBean aaData = liveStartBean.getAaData();
                if (aaData != null && aaData.getChannels() != null) {
                    // 获取token, 设置直播间的标题, 直播间封面
                    if (liveStartBean.isIsLogin()) {
                        try {

                            //分享
                            shareid = aaData.getChannels().getRoomsId();

                            image = aaData.getRoomPic();


                            Bundle bundle = new Bundle();
                            // 初始化聊天室
                            initNIM(liveStartBean, bundle);
                            // 释放摄像头
                            if (camera != null) {
                                camera.stopPreview();
                                camera.release();
                                camera = null;
                            }
                            String pushUrl = aaData.getChannels().getPushUrl(); // 推流地址
                            String id = aaData.getId(); // 用户id
                            String followes = aaData.getChatUser().getFollowes(); // 我的关注列表
                            String name = aaData.getChatUser().getName(); // 用户昵称
                            String stdCoin = aaData.getStdCoin();   // 用户兴趣币
                            if (mShareFlag != -1) {  // 执行分享

                            }
                            bundle.putString("mToken", UserManager.getInstance().getToken());
                            Intent intent = new Intent(mContext, LivingActivity.class);


                            bundle.putString("stdCoin", stdCoin);
                            bundle.putString("pushUrl", pushUrl);
                            bundle.putString("id", id);
                            bundle.putString("myFollowes", followes);
                            intent.putExtra("startLive", bundle);
                            startActivity(intent);
                            mFrameLayout.setVisibility(View.GONE);
                            StartLiveActivity.this.finish();
                        } catch (Exception ex) {
                            ex.printStackTrace();
                        }
                    } else {
                        ToastUtil.showToast(mContext, "您未登录或者登录已过期,请重新登录");
                    }
                } else {
                    ToastUtil.showToast(mContext, "服务器返回值异常,无法发起直播");
                }

            }
        }
    };
    /**
     * 6.0权限处理
     **/
    private final int WRITE_PERMISSION_REQ_CODE = 100;
    private boolean checkPublishPermission() {
        if (Build.VERSION.SDK_INT >= 23) {
            List<String> permissions = new ArrayList<>();

            if (PackageManager.PERMISSION_GRANTED != ActivityCompat.checkSelfPermission(mContext, Manifest
                    .permission.CAMERA)) {
                permissions.add(Manifest.permission.CAMERA);
            }
            if (PackageManager.PERMISSION_GRANTED != ActivityCompat.checkSelfPermission(mContext, Manifest
                    .permission.RECORD_AUDIO)) {
                permissions.add(Manifest.permission.RECORD_AUDIO);
            }
            if (PackageManager.PERMISSION_GRANTED != ActivityCompat.checkSelfPermission(mContext, Manifest
                    .permission.READ_PHONE_STATE)) {
                permissions.add(Manifest.permission.READ_PHONE_STATE);
            }
            if (permissions.size() != 0) {
                ActivityCompat.requestPermissions((Activity) mContext,
                        (String[]) permissions.toArray(new String[0]),
                        WRITE_PERMISSION_REQ_CODE);
                return false;
            }
        }
        return true;
    }

    /*后台创建直播间*/
    private void addLiveRoom(String url) {
        String title = mEtSetTitle.getText().toString().trim();
        User user = UserManager.getInstance().getUser();
        String token = UserManager.getInstance().getToken();
        String signame = user.signame;
        shareContent = signame + "正在[兴趣班]直播,快来欣赏吧";
        mAvatarPic = user.picture;
        image=mAvatarPic;
        if (TextUtils.isEmpty(title)) {
            title = "安卓直播";
        }


        NetWork.getLiveUpUrl().getLiveStartUrl(token, title, mAvatarPic, price + "")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);
    }

    /**
     * 初始化聊天室数据并传递到直播界面
     *
     * @param bean
     * @param bundle
     */

    private void initNIM(LiveStartBean bean, Bundle bundle) {
        LiveStartBean.AaDataBean aaData = bean.getAaData();
        LiveStartBean.AaDataBean.ChatUserBean chatUser = aaData.getChatUser(); // 聊天室相关参数
        String accid = chatUser.getAccid(); // 主播云信ID
        String token = chatUser.getToken(); // 主播云信token
        String chatroomId = aaData.getChatroomId(); // 云信直播间ID
        String appKey = bean.getAttachData().getAppKey(); //云信appkey
        bundle.putString("accid", accid);
        bundle.putString("liveToken", token);
        bundle.putString("chatroomId", chatroomId);
        bundle.putString("appKey", appKey);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        //图片获取
        if (requestCode == 101) {
            pathList = data.getStringArrayListExtra(PhotoPickerActivity.EXTRA_RESULT_SELECTION);
            displayImage(pathList.get(0));
        }
        //科目返回
        if (requestCode == 3 && resultCode == RESULT_OK) {
            typeName = data.getStringExtra("typeName");
            typeid = data.getStringExtra("typeId");
            subjectName = data.getStringExtra("subName");
            subjectId = data.getStringExtra("subId");
            mTvChoiceSubject.setText(subjectName);
            listName.clear();
            listName.add(subjectName);
        }
    }


    /**
     * 通过imagepath来绘制immageview图像
     *
     * @param imagPath
     */
    private void displayImage(String imagPath) {
        if (imagPath != null) {
            Bitmap bitmap = BitmapFactory.decodeFile(imagPath);
            mIvTitleIcon.setImageBitmap(bitmap);
        } else {
            Toast.makeText(this, "图片获取失败", Toast.LENGTH_SHORT).show();
        }
    }

    /**
     * 消费掉framelayout的点击事件
     */
    private void setPrpgress() {
        mFrameLayout.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return true;
            }
        });
    }


    private void initLocation() {
        LocationHelper locationHelper = LocationHelper.newInstance();
        if (locationHelper.isOPen(mContext)) {
            // 检查定位权限
            if (ContextCompat.checkSelfPermission(mContext,
                    Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) { // 6.0申请权限
                    String[] permissions = {Manifest.permission.ACCESS_COARSE_LOCATION};
                    requestPermissions(permissions, REQUEST_CODE_GPS);
                } else {
                    ToastUtil.showToast("禁用定位权限可能会影响部分功能的使用,建议您打开定位权限");
                }
            } else {
            }
        } else {
            ToastUtil.showToast("请打开GPS");
        }
    }

    public void selectpic() {
        SImagePicker
                .from(StartLiveActivity.this)
                .maxCount(1)
                .rowCount(3)
                .showCamera(true)
                .pickMode(SImagePicker.MODE_IMAGE)
                .forResult(101);
    }

    /**
     * 分享处理
     */
    boolean isStartLive=false;
    //1微信 2微信朋友圈 3qq 4空间  5微博
    private void toShare() {
        isStartLive=true;
        switch (shareType) {

            case 1:
                ThirdPartyUtils.getInstance(StartLiveActivity.this).shareUrlToWechat(shareUrl+shareid, sharetitle, shareContent, image, false,true);

                break;
            case 2:
                ThirdPartyUtils.getInstance(StartLiveActivity.this).shareUrlToWechat(shareUrl+shareid, sharetitle, shareContent, image, false,false);

                break;
            case 3:
                ThirdPartyUtils.getInstance(StartLiveActivity.this).shareToQQ(StartLiveActivity.this, shareUrl+shareid, sharetitle, shareContent, image);

                break;
            case 4:
                ThirdPartyUtils.getInstance(StartLiveActivity.this).shareUrlToZone(StartLiveActivity.this, shareUrl+shareid, sharetitle, shareContent, mlist);

                break;
            case 5:
                ThirdPartyUtils.getInstance(StartLiveActivity.this).shareToWeibo(StartLiveActivity.this, sharetitle, image);

                break;
        }
    }



    @Override
    protected void onResume() {
        super.onResume();
        if (isStartLive) {
            isStartLive=false;
            if (NoDoubleClick.isNotDoubleClick()) {
                mFrameLayout.setVisibility(View.VISIBLE);
                String url = UrlUtils.URL_LIVESTART;
                addLiveRoom(url);
                SpUtil.putString(mContext, "setLivePrice", null); // 将缓存在sp的价格清空
            }
        }

    }
}
