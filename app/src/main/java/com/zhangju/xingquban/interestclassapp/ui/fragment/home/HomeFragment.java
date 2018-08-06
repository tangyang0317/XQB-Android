package com.zhangju.xingquban.interestclassapp.ui.fragment.home;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.alibaba.fastjson.JSONObject;
import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationClientOption;
import com.amap.api.location.AMapLocationListener;
import com.fastlib.annotation.Event;
import com.fastlib.app.EventObserver;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshScrollView;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.RequestParams;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest;
import com.youth.banner.Banner;
import com.zhangju.xingquban.R;
import com.zhangju.xingquban.interestclassapp.RetrofitInterface.NetWork;
import com.zhangju.xingquban.interestclassapp.adapter.FragmentItemAdapter;
import com.zhangju.xingquban.interestclassapp.adapter.IndicatorAdapter;
import com.zhangju.xingquban.interestclassapp.adapter.baseadpter.OnListItemClickListener;
import com.zhangju.xingquban.interestclassapp.adapter.home.HomeBottomAdapter;
import com.zhangju.xingquban.interestclassapp.application.MyApp;
import com.zhangju.xingquban.interestclassapp.base.BaseFragment;
import com.zhangju.xingquban.interestclassapp.bean.CityConvertBean;
import com.zhangju.xingquban.interestclassapp.bean.CityNameBean;
import com.zhangju.xingquban.interestclassapp.bean.HomeBannerBean;
import com.zhangju.xingquban.interestclassapp.bean.HomeRecylerBean;
import com.zhangju.xingquban.interestclassapp.bean.NearDataBean;
import com.zhangju.xingquban.interestclassapp.bean.NearSubjectBean;
import com.zhangju.xingquban.interestclassapp.hplper.ScrollLinearLayoutManager;
import com.zhangju.xingquban.interestclassapp.refactor.common.activity.WebViewActivity;
import com.zhangju.xingquban.interestclassapp.refactor.common.bean.CommonInterface;
import com.zhangju.xingquban.interestclassapp.refactor.location.EventLocationChanged;
import com.zhangju.xingquban.interestclassapp.refactor.location.Location;
import com.zhangju.xingquban.interestclassapp.refactor.location.LocationManager;
import com.zhangju.xingquban.interestclassapp.refactor.me.activity.LoginActivity;
import com.zhangju.xingquban.interestclassapp.refactor.me.activity.NotificationActivity;
import com.zhangju.xingquban.interestclassapp.refactor.user.UserManager;
import com.zhangju.xingquban.interestclassapp.ui.activity.near.CurriculumActivity;
import com.zhangju.xingquban.interestclassapp.ui.fragment.find.SeekMessage.FindMessage;
import com.zhangju.xingquban.interestclassapp.ui.fragment.find.SeekResource.FindResource;
import com.zhangju.xingquban.interestclassapp.ui.fragment.find.Wenda.Question.fresh.MyQuestionMain;
import com.zhangju.xingquban.interestclassapp.ui.fragment.find.mediscover.FindLiveActivity;
import com.zhangju.xingquban.interestclassapp.ui.fragment.home.contest.ContestWebActivity;
import com.zhangju.xingquban.interestclassapp.ui.fragment.home.contest.ContestWebActivity1;
import com.zhangju.xingquban.interestclassapp.ui.fragment.home.homeviewpage.HomeClassPage1;
import com.zhangju.xingquban.interestclassapp.ui.fragment.home.homeviewpage.HomeClassPage2;
import com.zhangju.xingquban.interestclassapp.ui.fragment.home.homeviewpage.HomeClassPage3;
import com.zhangju.xingquban.interestclassapp.ui.fragment.home.zyzs.ChangeCityEvent;
import com.zhangju.xingquban.interestclassapp.ui.fragment.near.DistrictActivity_Copy;
import com.zhangju.xingquban.interestclassapp.ui.fragment.near.NearShare.NearShareActivity;
import com.zhangju.xingquban.interestclassapp.util.SortUtils;
import com.zhangju.xingquban.interestclassapp.util.ToastUtil;
import com.zhangju.xingquban.interestclassapp.util.UrlUtils;
import com.zhangju.xingquban.interestclassapp.util.click.NoDoubleClick;
import com.zhangju.xingquban.interestclassapp.view.BannerHelper;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import rx.Observer;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

import static android.app.Activity.RESULT_OK;
import static com.bumptech.glide.gifdecoder.GifHeaderParser.TAG;

/**
 * Created by dhy on 2017/6/14.
 */
public class HomeFragment extends BaseFragment {
    @BindView(R.id.home_cityText)
    TextView home_cityText;
    /*    @BindView(R.id.home_edittext)
        ImageView homeEdittext;*/
    @BindView(R.id.home_banner)
    Banner banner;
    @BindView(R.id.home_head_teacher)
    LinearLayout homeHeadTeacher;
    @BindView(R.id.home_head_agencies)
    LinearLayout homeHeadAgencies;
    @BindView(R.id.home_head_resourecs)
    LinearLayout homeHeadResourecs;
    @BindView(R.id.home_head_brodcase)
    LinearLayout homeHeadBrodcase;
    @BindView(R.id.home_head_zhibotuijian)
    LinearLayout homeHeadZhibotuijian;
    @BindView(R.id.home_head_wangluoshipin)
    RelativeLayout homeHeadWangluoshipin;
    @BindView(R.id.home_head_huodongzixun)
    RelativeLayout homeHeadHuodongzixun;
    @BindView(R.id.home_head_youxiulaoshi)
    LinearLayout homeHeadYouxiulaoshi;
    @BindView(R.id.home_head_youxiujigou)
    LinearLayout homeHeadYouxiujigou;
    @BindView(R.id.home_head_jingpinkecheng)
    LinearLayout homeHeadJingpinkecheng;
    @BindView(R.id.home_recyclerview)
    RecyclerView homeRecyclerview;
    @BindView(R.id.home_item_cp)
    ViewPager homeItemCp;
    @BindView(R.id.home_page_Indicator)
    RecyclerView homePageIndicator;
    @BindView(R.id.home_pulltorefresh)
    PullToRefreshScrollView homePulltorefresh;
    @BindView(R.id.home_message)
    LinearLayout home_message;

    @BindView(R.id.head_height)
    LinearLayout headheight;

    private List<String> images = new ArrayList<>();
    private FragmentItemAdapter fragmentItemAdapter;

    private Subscription suscription;
    private String types;


    private String requestCity = "上海市";
    private String requestCityCode = "310000";
    private String mRequestCityPid = "100000";
    private String myLat = "30.292678";
    private String myLng = "120.036981";
    private String city;
    String s;
    String s1;
    private int pageIndex = 0;
    private List<HomeRecylerBean.AaDataBean> homeDataList = new ArrayList<>();//底部数据集合
    private HomeBottomAdapter homeBottomAdapter;
    private List<HomeBannerBean.AaDataBean> bannerBeanList = new ArrayList<>();
    private static int WRITE_PERMISSION_REQ_CODE = 100;
    private IndicatorAdapter indicatorAdapter;
    private Intent intent;
    private Bundle bundle;
    private List<CityNameBean.AaDataBean> cityData = new ArrayList<>();//全部城市
    Observer<NearSubjectBean> observer = new Observer<NearSubjectBean>() {
        @Override
        public void onCompleted() {

        }

        @Override
        public void onError(Throwable e) {

        }

        @Override
        public void onNext(NearSubjectBean homeViewPage) {
            List<Fragment> list = new ArrayList<>();
            list.add(HomeClassPage1.newInstance(homeViewPage));
            list.add(HomeClassPage2.newInstance(homeViewPage));
            list.add(HomeClassPage3.newInstance(homeViewPage));
            fragmentItemAdapter = new FragmentItemAdapter(getFragmentManager(), list);
            homeItemCp.setAdapter(fragmentItemAdapter);
        }
    };

    Observer<HomeBannerBean> observer1 = new Observer<HomeBannerBean>() {

        @Override
        public void onCompleted() {
            images.clear();
            if (bannerBeanList != null && bannerBeanList.size() > 0) {
                for (int i = 0; i < bannerBeanList.size(); i++) {
                    images.add(bannerBeanList.get(i).getPictureurl());
                }
            }

            if (images.size() > 0) {
            } else {
                homePageIndicator.setVisibility(View.GONE);
            }
        }


        @Override
        public void onError(Throwable e) {
            ToastUtil.showToast(e.toString());
        }

        @Override
        public void onNext(HomeBannerBean homeBannerBean) {
            Log.i(TAG, "onNext=================: " + homeBannerBean.toString());

            bannerBeanList.clear();
            if (homeBannerBean.isSuccess() && homeBannerBean.getAaData() != null && homeBannerBean.getAaData().size() > 0) {
                bannerBeanList.addAll(homeBannerBean.getAaData());
            }
        }
    };
    private BannerHelper mBannerHelper;

    /**
     * 在oncreate方法中初始化数据
     */
    @Override
    public void initData() {
        //获取城市数据
        getCityData();

        //获取用户所在城市
        getMyLocation();//

        getHomeViewPage();

        //刷新
        getPulltoRefish();

        //底部数据
        setHomedataListAdapter();

        //指示器
        intiInditors();

    }

    private void getCityData() {
        NetWork.getICityName().getCityName()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer2);
    }


    //全部城市数据
    private Observer<CityNameBean> observer2 = new Observer<CityNameBean>() {
        @Override
        public void onCompleted() {
        }

        @Override
        public void onError(Throwable e) {
//            loadingDialog.dismiss();
        }

        @Override
        public void onNext(CityNameBean cityNameBean) {
            cityData.addAll(SortUtils.filledData(cityNameBean.getAaData().toArray(new CityNameBean.AaDataBean[cityNameBean.getAaData().size()])));
            if (cityData.size() > 0) {
                LocationManager.getInstance().setCityData(cityData);
            }
        }
    };

    //用户所在城市获取
    private void getMyLocation() {
        if (PackageManager.PERMISSION_GRANTED == ActivityCompat.checkSelfPermission(getContext(), Manifest
                .permission.ACCESS_COARSE_LOCATION)) {
            initLocation();
        } else {
            checkPublishPermission(getContext());
        }
    }

    private void setHomedataListAdapter() {
        homeBottomAdapter = new HomeBottomAdapter(getActivity(), homeDataList);
        ScrollLinearLayoutManager linearLayoutManager = new ScrollLinearLayoutManager(getActivity());
        linearLayoutManager.setScrollEnabled(false);
        homeRecyclerview.setLayoutManager(linearLayoutManager);
        homeRecyclerview.setAdapter(homeBottomAdapter);

        //item操作事件
        homeBottomAdapter.setOnListItemClickListener(new OnListItemClickListener() {
            @Override
            public void onItemClickListener(int position, View v) {
                Intent intent = new Intent(getActivity(), HomeRecyclerViewData.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("homeXq", homeDataList.get(position));
                intent.putExtra("tag", position);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });

    }

    //自定义指示器Recyclerview
    private void intiInditors() {
        indicatorAdapter = new IndicatorAdapter(getActivity(), 3);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        homePageIndicator.setLayoutManager(linearLayoutManager);
        homePageIndicator.setAdapter(indicatorAdapter);

        homeItemCp.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }

            @Override
            public void onPageSelected(int position) {
                indicatorAdapter.setPosition(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {
            }
        });

    }

    //首页ViewPage 课程数据
    public void getHomeViewPage() {
        NetWork.getNearSubject().getKemuAllData()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);
    }

    //首页轮播数据
    public void getHomeBanner() {
        initBanner();
/*
        //取
        suscription = NetWork.getHomeBanner().getHomeBanner(LocationManager.getInstance().getLocation().cityId, "1")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer1);*/
    }

    //首页下面数据
    private void getHomeData() {
        HttpUtils httpUtils = new HttpUtils();
        RequestParams params = new RequestParams();
        params.addHeader("X-CustomToken", UserManager.getInstance().getToken());
        params.addBodyParameter("pageIndex", pageIndex + "");
        params.addBodyParameter("pageSize", "30");
        params.addBodyParameter("cityId", LocationManager.getInstance().getLocation().cityId);
        params.addBodyParameter("degreeId", "2");
        params.addBodyParameter("lat", LocationManager.getInstance().getLocation().latitude);
        params.addBodyParameter("lng", LocationManager.getInstance().getLocation().longitude);
        String url = UrlUtils.URL_HOME_BOTTOM;
        httpUtils.send(HttpRequest.HttpMethod.POST,
                url,
                params,
                new RequestCallBack<String>() {
                    @Override
                    public void onSuccess(ResponseInfo<String> responseInfo) {
                        String json = responseInfo.result;
                        HomeRecylerBean homeRecylerBean = JSONObject.parseObject(json, HomeRecylerBean.class);
                        try {
                            boolean success = homeRecylerBean.isSuccess();
                            if (success) {
                                List<NearDataBean.AaDataBean> dataBeanList = homeRecylerBean.getAaData();
                                if (pageIndex == 0) {
                                    homeDataList.clear();
                                }
                                if (dataBeanList != null && dataBeanList.size() > 0) {
                                    homeDataList.clear();
                                    homeDataList.addAll(dataBeanList);
                                }
                            }

                            homeBottomAdapter.notifyDataSetChanged();
                            homePulltorefresh.onRefreshComplete();

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


    //界面加载
    public void getPulltoRefish() {
        homePulltorefresh.setMode(PullToRefreshBase.Mode.BOTH);
        homePulltorefresh.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener2<ScrollView>() {
            @Override
            public void onPullDownToRefresh(PullToRefreshBase<ScrollView> refreshView) {
                homeDataList.clear();
                pageIndex = 0;
                getHomeData();
                pageIndex = 0;
                getHomeViewPage();
            }

            @Override
            public void onPullUpToRefresh(PullToRefreshBase<ScrollView> refreshView) {
                pageIndex++;
                getHomeData();
            }
        });
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        switch (requestCode) {
            case 100:
                initLocation();
                break;
            default:
                checkPublishPermission(getActivity());
                break;
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        //用户选择城市数据搜索
        if (requestCode == 1 && resultCode == RESULT_OK) {
            String city = data.getStringExtra("city");
            if (!city.equals(requestCity)) {
                requestCity = city;
                requestCityCode = data.getStringExtra("cityId");
                mRequestCityPid = data.getStringExtra("citypId");
            }

            home_cityText.setText(requestCity);
            pageIndex = 0;
//            setCityText();
            LocationManager.getInstance().convertCityId();
            getHomeBanner();
            getHomeData();
            EventObserver.getInstance().sendEvent(getActivity(), new ChangeCityEvent());
        }
    }

    @Event
    private void changeCity(ChangeCityEvent changeCityEvent) {
        home_cityText.setText(LocationManager.getInstance().getLocation().locationCity);
        pageIndex = 0;
//        setCityText();
        LocationManager.getInstance().convertCityId();
        getHomeData();
    }

    @Event
    private void cityIdConverted(EventLocationChanged event) {
        getHomeBanner();
    }

    /**
     * 取得需要加载的布局
     */
    @Override
    public int getMyLayout() {
        return R.layout.home_fragment;
    }

    /**
     * 在onCreateView中操作数据和控件
     */
    @Override
    public void initView() {
        EventObserver.getInstance().subscribe(getActivity(), this);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        EventObserver.getInstance().unsubscribe(getActivity(), this);
        if (mBannerHelper != null) {
            mBannerHelper.stopBanner();
        }
    }

    /**
     * 设置监听
     */
    @Override
    public void initListener() {

    }

    @OnClick({R.id.home_cityText, R.id.home_head_teacher, R.id.home_head_agencies, R.id.home_head_resourecs, R.id.home_head_brodcase,
            R.id.home_head_zhibotuijian, R.id.home_head_wangluoshipin, R.id.home_head_huodongzixun, R.id.home_head_youxiulaoshi,
            R.id.home_head_youxiujigou, R.id.home_head_jingpinkecheng, R.id.home_message, R.id.nearShareActivity})
    public void onClick(View v) {
        switch (v.getId()) {

            //搜索
            case R.id.nearShareActivity:
                startActivity(new Intent(getActivity(), NearShareActivity.class));
                break;

            //定位
            case R.id.home_cityText:
                intent = new Intent(getActivity(), CityActivity.class);
                startActivityForResult(intent, 1);
                break;
            //找老师
            case R.id.home_head_teacher:
                intent = new Intent(getActivity(), DistrictActivity_Copy.class);
                bundle = new Bundle();
                bundle.putInt("degreeid", 1);
                bundle.putString("bannerType", "10");
                intent.putExtras(bundle);
                startActivity(intent);
                break;

            //找机构
            case R.id.home_head_agencies:
                intent = new Intent(getActivity(), DistrictActivity_Copy.class);
                bundle = new Bundle();
                bundle.putInt("degreeid", 2);
                bundle.putString("bannerType", "11");
                intent.putExtras(bundle);
                startActivity(intent);
                break;

            //找资源
            case R.id.home_head_resourecs:

                startActivity(new Intent(getActivity(), FindResource.class));
                break;

            //找直播
            // 找比赛
            case R.id.home_head_brodcase:
//                startActivity(new Intent(getActivity(), FindLiveActivity.class));
                if (getActivity() != null) {
                    if (NoDoubleClick.isNotDoubleClick()) {
                        Intent intent = new Intent(getActivity(), ContestWebActivity1.class);
                        intent.putExtra(ContestWebActivity1.ARG_URL, CommonInterface.URL_FIND_COMPETITION + UserManager.getInstance().getToken());
                        startActivity(intent);
                    }
                }
                break;

            //直播推荐
            case R.id.home_head_zhibotuijian:
                startActivity(new Intent(getContext(), ExperienceActivity.class));
//                startActivity(new Intent(getActivity(), FindActivity.class));
                break;

            //优秀问答
            case R.id.home_head_wangluoshipin:
                startActivity(new Intent(getActivity(), MyQuestionMain.class));
                break;

            //活动资讯
            case R.id.home_head_huodongzixun:
                startActivity(new Intent(getActivity(), FindMessage.class));
                break;

            //优秀机构
            case R.id.home_head_youxiulaoshi:

                intent = new Intent(getActivity(), DistrictActivity_Copy.class);
                bundle = new Bundle();
                bundle.putInt("degreeid", 2);
                bundle.putBoolean("avgScore", true);
                bundle.putString("bannerType", "15");
                intent.putExtras(bundle);
                startActivity(intent);
                break;

            //优秀教师
            case R.id.home_head_youxiujigou:
                intent = new Intent(getActivity(), DistrictActivity_Copy.class);
                bundle = new Bundle();
                bundle.putInt("degreeid", 1);
                bundle.putBoolean("avgScore", true);
                bundle.putString("bannerType", "14");
                intent.putExtras(bundle);
                startActivity(intent);
                break;
            //精品课程
            case R.id.home_head_jingpinkecheng:
                startActivity(new Intent(getActivity(), CurriculumActivity.class));
                break;

            //消息
            case R.id.home_message:
                if (!checkLogin()) return;
                startActivity(new Intent(getActivity(), NotificationActivity.class));
                break;
        }
    }

    /**
     * 检查登录状态，如果未登录调起登录界面
     */
    private boolean checkLogin() {
        if (UserManager.getInstance().isLogin()) return true;
        else {
            startActivity(new Intent(getActivity(), LoginActivity.class));
            return false;
        }
    }

    private void initBanner() {
        mBannerHelper = new BannerHelper(getActivity());
        mBannerHelper.init(banner).loadBannerDate("4");
       /* //设置图片加载器
        banner.setImageLoader(new GlideImageLoader());
        //设置图片集合
        banner.setImages(images);
        //显示圆形指示器
        banner.setBannerStyle(BannerConfig.CIRCLE_INDICATOR);
        banner.setSelected(true);
        //banner设置方法全部调用完毕时最后调用
        banner.start();
        //
        banner.setOnBannerListener(new OnBannerListener() {
            @Override
            public void OnBannerClick(int position) {
                Intent intent=new Intent(getContext(), WebViewActivity.class);
                intent.putExtra(WebViewActivity.ARG_URL,bannerBeanList.get(position).getUrl());
                startActivity(intent);
            }
        });*/
//        banners.setOnBannerClickListener(new OnBannerClickListener() {
//            @Override
//            public void OnBannerClick(int position) {
//                Log.i("look", position + "");
//                ToastUtil.showToast(""+position);
//                String url = bannerBeanList.get(position - 1).getUrl();
//                Intent intent = new Intent(getActivity(), HomeWebView.class);
//                intent.putExtra("url", url);
//                startActivity(intent);
//            }
//        });
    }

    Observer<CityConvertBean> observerCity = new Observer<CityConvertBean>() {
        @Override
        public void onCompleted() {
        }

        @Override
        public void onError(Throwable e) {
            Log.e("errs", e.toString());
        }

        @Override
        public void onNext(CityConvertBean result) {
            boolean success = result.isSuccess();
            if (success) {
                CityConvertBean.AaDataBean.Districtes addressComponent = result.getAaData().getAddressComponent();
                if (addressComponent != null) {
                    requestCityCode = addressComponent.getAdcode();
                    LocationManager instance = LocationManager.getInstance();
                    Location location = instance.getLocation();
                    location.latitude = s;
                    location.longitude = s1;
                    location.cityId = requestCityCode;
                    location.cityPid = "100000";
                    location.locationCity = city;
                    LocationManager.getInstance().updateLocation(location);
                }
//                setCityText();
                LocationManager.getInstance().convertCityId();
                getHomeData();

                getHomeBanner();
            }

        }
    };

    private void getCityCode(String lat, String lng) {
        NetWork.getuserData().getConvertLocation(lat, lng, "city")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observerCity);

    }

    private void initLocation() {
        AMapLocationClient mLocationClient = new AMapLocationClient(getContext());
        AMapLocationClientOption option = new AMapLocationClientOption();
        option.setInterval(1000);
        option.setLocationMode(AMapLocationClientOption.AMapLocationMode.Hight_Accuracy);
        option.setOnceLocation(true);
        mLocationClient.setLocationOption(option);
        mLocationClient.setLocationListener(new AMapLocationListener() {
            @Override
            public void onLocationChanged(AMapLocation amapLocation) {
                if (amapLocation != null) {
                    if (amapLocation.getErrorCode() == 0) {
                        city = amapLocation.getCity();//称呼四
                        s = Double.toString(amapLocation.getLatitude());
                        s1 = Double.toString(amapLocation.getLongitude());
                        requestCity = amapLocation.getCity();
                        if (LocationManager.getInstance().getLocation().locationCity.equals("默认城市")) {
                            home_cityText.setText(requestCity);
                            getCityCode(s, s1);
                            LocationManager instance = LocationManager.getInstance();
                            Location location = instance.getLocation();
                            location.latitude = s;
                            location.longitude = s1;
                            location.cityId = amapLocation.getAdCode();
                            location.cityPid = "100000";
                            location.locationCity = city;
                            LocationManager.getInstance().updateLocation(location);
                        } else {
                            home_cityText.setText(LocationManager.getInstance().getLocation().locationCity);
//                            setCityText();
                            LocationManager.getInstance().convertCityId();
                            getHomeData();
                            getHomeBanner();
                        }
                    } else {
                        //显示错误信息ErrCode是错误码，errInfo是错误信息，详见错误码表。
                        Log.e("AmapError", "location Error, ErrCode:"
                                + amapLocation.getErrorCode() + ", errInfo:"
                                + amapLocation.getErrorInfo());
                    }
                }
            }
        });
        mLocationClient.startLocation();
    }

    /**
     * 权限检测
     *
     * @param context
     * @return
     */
    public boolean checkPublishPermission(Context context) {
        if (Build.VERSION.SDK_INT >= 23) {
            List<String> permissions = new ArrayList<>();
            if (PackageManager.PERMISSION_GRANTED != ActivityCompat.checkSelfPermission(context, Manifest
                    .permission.WRITE_EXTERNAL_STORAGE)) {
                permissions.add(Manifest.permission.WRITE_EXTERNAL_STORAGE);
            }
            if (PackageManager.PERMISSION_GRANTED != ActivityCompat.checkSelfPermission(context, Manifest
                    .permission.ACCESS_FINE_LOCATION)) {
                permissions.add(Manifest.permission.ACCESS_FINE_LOCATION);
            }
            if (PackageManager.PERMISSION_GRANTED != ActivityCompat.checkSelfPermission(context, Manifest
                    .permission.ACCESS_COARSE_LOCATION)) {
                permissions.add(Manifest.permission.ACCESS_COARSE_LOCATION);
            }
            if (PackageManager.PERMISSION_GRANTED != ActivityCompat.checkSelfPermission(context, Manifest
                    .permission.CAMERA)) {
                permissions.add(Manifest.permission.CAMERA);
            }
            if (permissions.size() != 0) {
                HomeFragment.this.requestPermissions(
                        (String[]) permissions.toArray(new String[0]),
                        WRITE_PERMISSION_REQ_CODE);

                return false;
            }
        }
        return true;
    }
}
