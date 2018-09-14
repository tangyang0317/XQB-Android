package com.zhangju.xingquban.interestclassapp.application;

import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap.Config;
import android.os.Build;
import android.os.Environment;
import android.os.Handler;
import android.os.Looper;
import android.support.multidex.MultiDex;
import android.util.Log;

import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationClientOption;
import com.amap.api.location.AMapLocationListener;
import com.facebook.stetho.Stetho;
import com.fastlib.db.FastDatabase;
import com.fastlib.net.GlobalListener;
import com.fastlib.net.NetManager;
import com.fastlib.net.Request;
import com.fastlib.utils.N;
import com.imnjh.imagepicker.PickerConfig;
import com.imnjh.imagepicker.SImagePicker;
import com.lidroid.xutils.BitmapUtils;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.bitmap.BitmapGlobalConfig;
import com.lidroid.xutils.cache.MD5FileNameGenerator;
import com.mob.MobSDK;
import com.netease.nimlib.sdk.NIMClient;
import com.netease.nimlib.sdk.auth.LoginInfo;
import com.nostra13.universalimageloader.cache.disc.impl.UnlimitedDiskCache;
import com.nostra13.universalimageloader.cache.memory.MemoryCache;
import com.nostra13.universalimageloader.cache.memory.impl.LRULimitedMemoryCache;
import com.nostra13.universalimageloader.cache.memory.impl.LruMemoryCache;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.QueueProcessingType;
import com.tencent.bugly.crashreport.CrashReport;
import com.zhangju.xingquban.BuildConfig;
import com.zhangju.xingquban.R;
import com.zhangju.xingquban.interestclassapp.bean.City;
import com.zhangju.xingquban.interestclassapp.bean.Data;
import com.zhangju.xingquban.interestclassapp.refactor.common.bean.Response;
import com.zhangju.xingquban.interestclassapp.refactor.location.Location;
import com.zhangju.xingquban.interestclassapp.refactor.location.LocationManager;
import com.zhangju.xingquban.interestclassapp.refactor.me.activity.LoginActivity;
import com.zhangju.xingquban.interestclassapp.refactor.user.UserManager;
import com.zhangju.xingquban.interestclassapp.ui.sys.SystemUtil;
import com.zhangju.xingquban.interestclassapp.util.ToastUtil;
import com.zhangju.xingquban.interestclassapp.util.imageloader.GlideImageLoader;
import com.zhangju.xingquban.refactoring.dblite.DataBaseHelper;
import com.zhangju.xingquban.refactoring.utils.BuglyUpdate;

import java.io.File;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import cn.jpush.android.api.JPushInterface;

/**
 * Created by john on 2015/11/18
 */
public class MyApp extends Application {
    public final static int DB_VERSION = 5;
    public static final String URL = "http://my.xqban.com"; //正式服务器
    //    public static final String URL = "http://test.xqban.com"; //测试服务器
    //    public static final String URL="http://myqqy.vicp.io/std"; //本地测试服务+器
    public static MyApp instance;
    public final static String TOKEN = "mToken";
    public final static String CUSTOMERID = "customerId";
    public static Data customerData;//用户的信息
    public static HttpUtils httpUtils;
    public static BitmapUtils bitmapUtils;
    public static DisplayImageOptions mNormalImageOptions;
    public static final String SDCARD_PATH = Environment
            .getExternalStorageDirectory().toString();
    public static String IMAGES_FOLDER = null;
    public static ImageLoader imageLoader;
    public static String userId = null;
    public static String categoriesId = null; // 直播间选择的科目id
    public static String LiveSubject = null; // 直播间选择的科目名字
    public static boolean isUpdataShowFirst = true; // 是否显示过升级提示
    /*
     * 图片存放路径
     */
    // 磁盘的根路径
    public static String DISK_ROOT = null;
    // 图片存放的路径
    public static String DISK_PICTURE = null;
    // 图片存放的目录名称
    public static String FLOADER_NAME = "Images";
    private File file;
    public static File tempPictures;
    /*
     * 视频存放路径
     */
    public static String DISK_VIDEO = null;
    public static String VIDEO_NAME = "movies";
    public static File videos;
    // 初始化
    /*
     * 经纬度 全局
     */
    public static double latitude;
    public static double longitude;
    public static ArrayList<City.AaDataBean> recentlyCity = new ArrayList<>(6);
    // 默认的cityName和cityId , 获取城市列表的时候自动更新
    public static String defaultCityName = "上海市";
    public static String defaultCityId = "310000";
    private static final String TAG = "JPush";
    protected boolean isInited = false; //支持测试类的字段
    public ActivityStack mActivityStack;

    //声明AMapLocationClient类对象
    public AMapLocationClient mLocationClient = null;

    @Override
    public void onCreate() {
        super.onCreate();
        MultiDex.install(this);
        if (!isInited)
            init();
    }

    private void init() {
        instance = this;
        mActivityStack = new ActivityStack(this);
        BuglyUpdate.initUpdateConfig(this);
        CrashReport.initCrashReport(getApplicationContext(), "37dc7f1977", false);
        NetManager.getInstance().setRootAddress(URL + "/admnxzcmr");
        FastDatabase.getConfig().setVersion(DB_VERSION);
        if (UserManager.getInstance().isLogin()) {
            NetManager.getInstance().setGlobalHead(Request.ExtraHeader.create(false, "X-CustomToken", UserManager.getInstance()
                    .getToken()));
        }
        JPushInterface.setDebugMode(BuildConfig.DEBUG);    // 设置开启日志,发布时请关闭日志
        JPushInterface.init(this);

        // shareSDK
        MobSDK.init(this);

        // 初始化网易云信SDK
        initIMSDK();
        initLocation();
        bitmapUtils = new BitmapUtils(this);
        httpUtils = new HttpUtils();
        initDiskPath();
        com.zhangju.xingquban.interestclassapp.config.Logger.setTag("xingquban");
        com.zhangju.xingquban.interestclassapp.config.Logger.setDebug(true);
        /****初始化数据库****/
        DataBaseHelper.getHelper(this);

        /****Steho 初始化****/
        Stetho.initializeWithDefaults(this);

        ToastUtil.init(this);
        SImagePicker.init(new PickerConfig.Builder().setAppContext(this)
                .setImageLoader(new GlideImageLoader())
                .setToolbaseColor(getResources().getColor(R.color.color_main))
                .build());
        initGlobalNetCallback();
    }

    private void initGlobalNetCallback() {
        NetManager.getInstance().setGlobalListener(new GlobalListener() {
            @Override
            public Object onResponseListener(Request r, Object result, Object result2) {
                if (result instanceof Response) {
                    Response response = (Response) result;
                    if (!response.success) {
                        final StringBuilder errMsg = new StringBuilder();
                        if (response.errMsg != null && !response.errMsg.isEmpty()) {
                            for (Object s : response.errMsg.values()) {
                                if (s != null)
                                    errMsg.append(s.toString()).append("\n");
                            }
                            if (errMsg.length() > 0) {
                                errMsg.deleteCharAt(errMsg.length() - 1);
                                //过期登录特殊判断
                                if (errMsg.toString().contains("登陆")) {
                                    N.showLong(MyApp.this, "登录过期，请重新登录");
                                    UserManager.getInstance().logout();
                                    Intent intent = new Intent(MyApp.this, LoginActivity.class);
                                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                                    startActivity(intent);
                                } else {
                                    Handler handler = new Handler(Looper.getMainLooper());
                                    handler.post(new Runnable() {
                                        @Override
                                        public void run() {
                                            N.showShort(MyApp.this, errMsg);
                                        }
                                    });
                                }
                            }
                        }
                    }
                }
                return super.onResponseListener(r, result, result2);
            }

            @Override
            public String onErrorListener(Request r, String error) {
                N.showShort(MyApp.this, "网络异常,请检查您的网络配置");
                return super.onErrorListener(r, error);
            }
        });
    }

    /**
     * 初始化云信SDK   确保在主线程中初始化
     */
    private void initIMSDK() {
        NIMClient.init(this, loginInfo(), null);
        if (inMainProcess()) {
            // 注册通知消息过滤器
        }
    }

    // 如果已经存在用户登录信息，返回LoginInfo，否则返回null即可
    private LoginInfo loginInfo() {
        return null;
    }

    public boolean inMainProcess() {
        String packageName = getPackageName();
        String processName = SystemUtil.getProcessName(this);
        return packageName.equals(processName);
    }

    /**
     * 初始化磁盘路径(以sdcard作为根路径)
     */
    private void initDiskPath() {
        // 1.判断当前的sdcard是否挂载
        if (Environment.getExternalStorageState().equals(
                Environment.MEDIA_MOUNTED)) { // 挂载成功 /sdcard
            DISK_ROOT = Environment.getExternalStorageDirectory()
                    .getAbsolutePath();
        } else { // 挂载失败 /data/data/
            DISK_ROOT = Environment.getDataDirectory().getAbsolutePath();
        }
        // 图片的路径
        // 1./sdcard/包名/Image
        // 2./data/data/包名/Image
        DISK_PICTURE = DISK_ROOT + File.separator + getPackageName()
                + File.separator + FLOADER_NAME;
        file = new File(DISK_PICTURE);

        tempPictures = new File(DISK_ROOT + File.separator + getPackageName()
                + File.separator + "tempPictures");

        DISK_VIDEO = DISK_ROOT + File.separator + getPackageName()
                + File.separator + VIDEO_NAME;
        videos = new File(DISK_VIDEO);
        if (!file.exists()) { // 如果存在直接构建
            // 构建文件目录
            file.mkdirs();
        }
        if (!videos.exists()) {
            videos.mkdirs();
        }
        if (!tempPictures.exists()) { // 如果存在直接构建
            // 构建文件目录
            tempPictures.mkdirs();
        } else {//上传头像剪裁后图片文件夹清理
            File[] files = tempPictures.listFiles();
            if (files != null && files.length > 0) {
                for (int i = 0; i < files.length; i++) {
                    files[i].delete();
                }
            }
            //			String[] paths = tempPictures.list();
            //			getContentResolver().delete(Media.EXTERNAL_CONTENT_URI, Media.DATA + "=?",paths);
        }
    }


    private void initLocation() {
        mLocationClient = new AMapLocationClient(this);
        AMapLocationClientOption option = new AMapLocationClientOption();
        option.setInterval(1000);
        option.setLocationMode(AMapLocationClientOption.AMapLocationMode.Hight_Accuracy);
        option.setOnceLocation(true);
        mLocationClient.setLocationOption(option);
        mLocationClient.startLocation();
        mLocationClient.setLocationListener(new AMapLocationListener() {
            @Override
            public void onLocationChanged(AMapLocation amapLocation) {
                if (amapLocation != null) {
                    if (amapLocation.getErrorCode() == 0) {
                        //定位成功回调信息，设置相关消息
                        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                        Date date = new Date(amapLocation.getTime());
                        df.format(date);//定位时间
                        //存取经纬度
                        Location location = LocationManager.getInstance().getLocation();
                        location.latitude = Double.toString(amapLocation.getLatitude());
                        location.longitude = Double.toString(amapLocation.getLongitude());
                        location.locationCity = amapLocation.getCity();
                        LocationManager.getInstance().updateLocation(location);
                    } else {
                        //显示错误信息ErrCode是错误码，errInfo是错误信息，详见错误码表。
                        Log.e("Location", "location Error, ErrCode:"
                                + amapLocation.getErrorCode() + ", errInfo:"
                                + amapLocation.getErrorInfo());
                    }
                }
            }
        });

    }

}
