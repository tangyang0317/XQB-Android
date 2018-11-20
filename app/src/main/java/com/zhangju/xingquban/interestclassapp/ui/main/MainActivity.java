package com.zhangju.xingquban.interestclassapp.ui.main;

import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.LinearLayoutManager;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.alibaba.fastjson.JSONObject;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.RequestParams;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest;
import com.orhanobut.logger.Logger;
import com.youth.banner.BannerConfig;
import com.zhangju.xingquban.BuildConfig;
import com.zhangju.xingquban.R;
import com.zhangju.xingquban.interestclassapp.RetrofitInterface.NetWork;
import com.zhangju.xingquban.interestclassapp.application.MyApp;
import com.zhangju.xingquban.interestclassapp.base.BaseActivity;
import com.zhangju.xingquban.interestclassapp.bean.AdBannerBean;
import com.zhangju.xingquban.interestclassapp.bean.Data;
import com.zhangju.xingquban.interestclassapp.bean.EditBean;
import com.zhangju.xingquban.interestclassapp.bean.ResDeatailTopBean;
import com.zhangju.xingquban.interestclassapp.config.Constant;
import com.zhangju.xingquban.interestclassapp.hplper.FragmentHelper;
import com.zhangju.xingquban.interestclassapp.refactor.common.UpdateService;
import com.zhangju.xingquban.interestclassapp.refactor.location.Location;
import com.zhangju.xingquban.interestclassapp.refactor.location.LocationManager;
import com.zhangju.xingquban.interestclassapp.refactor.me.activity.LoginActivity;
import com.zhangju.xingquban.interestclassapp.refactor.me.fragment.MeFragment;
import com.zhangju.xingquban.interestclassapp.refactor.user.UserManager;
import com.zhangju.xingquban.interestclassapp.ui.fragment.find.FindFragment;
import com.zhangju.xingquban.interestclassapp.ui.fragment.find.SeekResource.AudioDetailActivity;
import com.zhangju.xingquban.interestclassapp.ui.fragment.find.SeekResource.NewsDetailActivity;
import com.zhangju.xingquban.interestclassapp.ui.fragment.find.SeekResource.PicDetailActivity;
import com.zhangju.xingquban.interestclassapp.ui.fragment.home.GlideImageLoader;
import com.zhangju.xingquban.interestclassapp.ui.fragment.live.LiveFragment;
import com.zhangju.xingquban.interestclassapp.ui.fragment.me.Settings.MyDialog;
import com.zhangju.xingquban.interestclassapp.ui.fragment.near.NearbyFragment;
import com.zhangju.xingquban.interestclassapp.util.SpUtil;
import com.zhangju.xingquban.interestclassapp.util.ToastUtil;
import com.zhangju.xingquban.interestclassapp.util.UrlUtils;
import com.zhangju.xingquban.refactoring.IndexFragment;
import com.zhangju.xingquban.refactoring.activity.ResourcePictureDetailsActivity;
import com.zhangju.xingquban.refactoring.adapter.ResourceLevelTwoAdapter;
import com.zhangju.xingquban.refactoring.bean.PushJsonBean;
import com.zhangju.xingquban.refactoring.entity.BaseResponseBean;
import com.zhangju.xingquban.refactoring.fragment.ResourceFragment;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

import butterknife.BindView;
import cn.jpush.android.api.JPushInterface;
import cn.jpush.android.api.TagAliasCallback;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class MainActivity extends BaseActivity {
    public static final String ARG_INT_JUMP_PAGE = "jumpPage";
    @BindView(R.id.main_btn_home)
    RadioButton mainBtnHome;
    @BindView(R.id.main_btn_near)
    RadioButton mainBtnNear;
    @BindView(R.id.main_btn_live)
    RadioButton mainBtnLive;
    @BindView(R.id.main_btn_find)
    RadioButton mainBtnFind;
    @BindView(R.id.main_btn_me)
    RadioButton mainBtnMe;
    @BindView(R.id.main_radioGroup)
    RadioGroup main_radioGroup;

    private List<Fragment> fragmentList = new ArrayList<>();
    private FragmentManager fragmentManager;
    private final int WRITE_PERMISSION_REQ_CODE = 100;
    private long exitTime = 0;
    private int index = 0;
    private String serviceVersion;
    private String uploadUrl;

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        if (intent.getSerializableExtra("data") != null) {
            PushJsonBean.ExtrasBean pushJsonBean = (PushJsonBean.ExtrasBean) intent.getSerializableExtra("data");
            if (pushJsonBean != null) {
                openNotification(pushJsonBean);
            }
        }
    }


    @Override
    public void initData() {
        //添加fragment//
        fragmentList.add(new IndexFragment());
        fragmentList.add(new NearbyFragment());
        fragmentList.add(new ResourceFragment());
        fragmentList.add(new FindFragment());
        fragmentList.add(new MeFragment());
        fragmentManager = getSupportFragmentManager();
        //默认展示首页
        FragmentHelper.replaceFragment(fragmentManager, fragmentList.get(Constant.MAIN_HOME), R.id.main_frame_empty);
        PushJsonBean.ExtrasBean pushJsonBean = (PushJsonBean.ExtrasBean) getIntent().getSerializableExtra("data");
        if (pushJsonBean != null) {
            openNotification(pushJsonBean);
        }
        // initLogin();
        index = mainBtnHome.getId();
        if (!TextUtils.isEmpty(UserManager.getInstance().getUser().id)) {
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    setPushAliasAndTags();
                }
            }, 1000);
        }
        getServiceVersion();
        loadAdData();
    }

    private void openNotification(PushJsonBean.ExtrasBean pushJsonBean) {
        if (pushJsonBean != null) {
            if ("3".equals(pushJsonBean.getPushInfotype())) {
                //跳转到资源
                String resId = pushJsonBean.getTypeId();
                if (!TextUtils.isEmpty(resId)) {
                    getResourceDetailsTopData(resId);
                }
            }
        }
    }


    /***
     * 获取资源详情数据
     */
    private void getResourceDetailsTopData(String resId) {
        NetWork.getReources().getResourceDetailsTopData(resId, String.valueOf(0), String.valueOf(10))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(topDataObserva);
    }


    Observer<BaseResponseBean<List<ResDeatailTopBean.AaDataBean>>> topDataObserva = new Observer<BaseResponseBean<List<ResDeatailTopBean.AaDataBean>>>() {
        @Override
        public void onCompleted() {

        }

        @Override
        public void onError(Throwable e) {

        }

        @Override
        public void onNext(BaseResponseBean<List<ResDeatailTopBean.AaDataBean>> listBaseResponseBean) {
            if (listBaseResponseBean.isSuccess()) {
                if (listBaseResponseBean.getAaData() != null && listBaseResponseBean.getAaData().size() > 0) {
                    ResDeatailTopBean.AaDataBean dataBean = listBaseResponseBean.getAaData().get(0);
                    String types = dataBean.getTypes();
                    String id = dataBean.getId();
                    if (types.equals("video")) {
                        Intent intent = new Intent(MainActivity.this, AudioDetailActivity.class);
                        intent.putExtra("types", "video");
                        intent.putExtra("resId", id);
                        startActivity(intent);
                    } else if (types.equals("picture")) {
                        ResourcePictureDetailsActivity.launcherThis(MainActivity.this, id);
                    } else if (types.equals("article")) {
                        Intent intent = new Intent(MainActivity.this, NewsDetailActivity.class);
                        intent.putExtra("id", id);
                        startActivity(intent);
                    } else if (types.equals("audio")) {
                        Intent intent = new Intent(MainActivity.this, AudioDetailActivity.class);
                        intent.putExtra("types", "audio");
                        intent.putExtra("resId", id);
                        startActivity(intent);
                    }
                }
            }
        }
    };


    /***
     * 设置推送别名
     */
    private void setPushAliasAndTags() {
        Set<String> tags = new HashSet<String>();
        tags.add(UserManager.getInstance().getUser().id);
        JPushInterface.setAliasAndTags(this, UserManager.getInstance().getUser().id, tags, new TagAliasCallback() {
            @Override
            public void gotResult(int i, String s, Set<String> set) {
                Logger.d("推送别名设置成功" + s);
            }
        });
    }

    @Override
    public void initView() {

    }

    @Override
    public void initListener() {
        //为底部按钮设置点击监听
        mainBtnHome.setOnClickListener(this);
        mainBtnNear.setOnClickListener(this);
        mainBtnLive.setOnClickListener(this);
        mainBtnFind.setOnClickListener(this);
        mainBtnMe.setOnClickListener(this);
        if (getIntent().getIntExtra(ARG_INT_JUMP_PAGE, 0) != 0)
            mainBtnMe.performClick();
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.main_btn_home:
                showFragment(Constant.MAIN_HOME);
                index = v.getId();
                break;
            case R.id.main_btn_near:
                showFragment(Constant.MAIN_NEAR);
                index = v.getId();
                break;
            case R.id.main_btn_live:
                showFragment(Constant.MAIN_LIVE);
                index = v.getId();
                break;
            case R.id.main_btn_find:
                showFragment(Constant.MAIN_FIND);
                index = v.getId();

                break;
            case R.id.main_btn_me:
                showFragment(Constant.MAIN_ME);
                index = v.getId();
                break;
        }
    }

    /**
     * 展示被选择的fragment
     */
    private void showFragment(int i) {
        FragmentHelper.switchFragment(fragmentManager, fragmentList, i, R.id.main_frame_empty);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    @Override
    public int getLayout() {
        return R.layout.activity_main;
    }


    /**
     * 获取本地版本号
     *
     * @return
     */
    public String getlocalVersion() {
        String localversion = null;
        try {
            PackageInfo info = getPackageManager().getPackageInfo(getPackageName(), 0);
            localversion = info.versionName;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return localversion;
    }

    private void checkUpdate() {
        String localversion = getlocalVersion();
        if (localversion != null && serviceVersion != null && !localversion.equals(serviceVersion)) {
            if (MyApp.isUpdataShowFirst) {
                updateVersion();
            }
        }

    }

    private void updateVersion() {
        View view = getLayoutInflater().inflate(R.layout.view_updata, null);
        final MyDialog ab = new MyDialog(this, view, R.style.dialog);
        ab.setCanceledOnTouchOutside(false);
        ab.show();
        view.findViewById(R.id.button1).setOnClickListener(new View.OnClickListener() {//点击否的时候取消dialog
            @Override
            public void onClick(View v) {
                //                ab.cancel();
                if (BuildConfig.DEBUG) {
                    ab.cancel();
                } else {
                    finish();
                }
            }
        });
        view.findViewById(R.id.button2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, UpdateService.class);
                intent.putExtra("url", uploadUrl);
                startService(intent);
                ab.cancel();
            }
        });
        //        MyApp.isUpdataShowFirst = false;
    }

    /**
     * 获取服务器版本号
     *
     * @return
     */
    public void getServiceVersion() {
        HttpUtils mHttpUtils = new HttpUtils();
        mHttpUtils.send(HttpRequest.HttpMethod.GET,
                UrlUtils.URL_UPDATE,
                new RequestCallBack<String>() {
                    @Override
                    public void onFailure(HttpException arg0, String arg1) {
                        System.out.println("版本更新请求返回失败:" + arg1);
                    }

                    @Override
                    public void onSuccess(ResponseInfo<String> arg0) {
                        String data = arg0.result;
                        EditBean mBean = JSONObject.parseObject(data, EditBean.class);
                        if (mBean.isSuccess()) {
                            Data mData = mBean.getAaData();
                            if (mData != null) {
                                serviceVersion = mData.getVersionNumber();
                                uploadUrl = mData.getUploadUrl();
                                checkUpdate();
                            }
                        }
                    }
                });
    }

    /**
     * 捕捉返回事件按钮
     * <p>
     * 因为此 Activity 继承 TabActivity 用 onKeyDown 无响应，所以改用 dispatchKeyEvent
     * 一般的 Activity 用 onKeyDown 就可以了
     */

    @Override
    public boolean dispatchKeyEvent(KeyEvent event) {
        if (event.getKeyCode() == KeyEvent.KEYCODE_BACK) {
            if (event.getAction() == KeyEvent.ACTION_DOWN && event.getRepeatCount() == 0) {
                this.exitApp();
            }
            return true;
        }
        return super.dispatchKeyEvent(event);
    }

    /**
     * 退出程序
     */
    private void exitApp() {
        // 判断2次点击事件时间
        if ((System.currentTimeMillis() - exitTime) > 2000) {
            ToastUtil.showToast("再按一次退出程序");
            exitTime = System.currentTimeMillis();
        } else {
            finish();
        }

    }

    //    launcher广告
    public void loadAdData() {
        LocationManager instance = LocationManager.getInstance();
        Location location = instance.getLocation();
        HttpUtils httpUtils = new HttpUtils();
        RequestParams params = new RequestParams();
        params.addQueryStringParameter("pageIndex", "1");
        params.addQueryStringParameter("pageSize", "0");
        params.addQueryStringParameter("advertPutInProvince", location.cityPid);
        params.addQueryStringParameter("advertPutInCity", location.cityId);
        params.addQueryStringParameter("advertSection", "2");
        params.addQueryStringParameter("isPut", "1");
        params.addQueryStringParameter("status", "1");
        params.addQueryStringParameter("advertType", "1");
        params.addQueryStringParameter("advertClassification", "1");
        params.addHeader("X-CustomToken", UserManager.getInstance().getToken());

        String url = UrlUtils.URL_ADVERT_LIST;
        httpUtils.send(HttpRequest.HttpMethod.POST,
                url,
                params,
                new RequestCallBack<String>() {
                    @Override
                    public void onSuccess(ResponseInfo<String> responseInfo) {
                        String json = responseInfo.result;
                        AdBannerBean bean = JSONObject.parseObject(json, AdBannerBean.class);
                        if (bean.isSuccess()) {
                            AdBannerBean.AaDataBean aaData = bean.getAaData();
                            if (aaData != null && aaData.getList().size() > 0) {
                                int size = aaData.getList().size();
                                Random random = new Random();
                                int i = random.nextInt(size);
                                String advertUrl = aaData.getList().get(i).getAdvertUrl();
                                if (advertUrl != null) {
                                    SpUtil.putString(mContext, "launcherAdUrl", advertUrl);
                                } else {
                                    SpUtil.putString(mContext, "launcherAdUrl", "");
                                }
                                String advertGoToUrl = aaData.getList().get(0).getAdvertGoToUrl();
                                if (advertGoToUrl != null) {
                                    SpUtil.putString(mContext, "launcherAdGotoUrl", advertGoToUrl);
                                }
                            }
                        } else {
                            ToastUtil.showToast(bean.getErrMsg().toString());
                        }

                    }

                    @Override
                    public void onFailure(HttpException e, String s) {
                        ToastUtil.showToast(MyApp.instance, "请求服务器异常,请检查您的网络连接");
                    }
                });
    }
}
