package com.zhangju.xingquban.refactoring;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.alibaba.fastjson.JSONObject;
import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationClientOption;
import com.amap.api.location.AMapLocationListener;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.fastlib.app.EventObserver;
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
import com.zhangju.xingquban.interestclassapp.application.MyApp;
import com.zhangju.xingquban.interestclassapp.base.BaseFragment;
import com.zhangju.xingquban.interestclassapp.bean.CityConvertBean;
import com.zhangju.xingquban.interestclassapp.bean.HomeRecylerBean;
import com.zhangju.xingquban.interestclassapp.bean.NearDataBean;
import com.zhangju.xingquban.interestclassapp.bean.NearSubjectBean;
import com.zhangju.xingquban.interestclassapp.refactor.common.bean.CommonInterface;
import com.zhangju.xingquban.interestclassapp.refactor.location.Location;
import com.zhangju.xingquban.interestclassapp.refactor.location.LocationManager;
import com.zhangju.xingquban.interestclassapp.refactor.me.activity.LoginActivity;
import com.zhangju.xingquban.interestclassapp.refactor.me.activity.NotificationActivity;
import com.zhangju.xingquban.interestclassapp.refactor.user.UserManager;
import com.zhangju.xingquban.interestclassapp.ui.activity.near.CurriculumActivity;
import com.zhangju.xingquban.interestclassapp.ui.fragment.find.SeekMessage.FindMessage;
import com.zhangju.xingquban.interestclassapp.ui.fragment.find.SeekResource.FindResource;
import com.zhangju.xingquban.interestclassapp.ui.fragment.find.Wenda.Question.fresh.MyQuestionMain;
import com.zhangju.xingquban.interestclassapp.ui.fragment.home.CityActivity;
import com.zhangju.xingquban.interestclassapp.ui.fragment.home.ExperienceActivity;
import com.zhangju.xingquban.interestclassapp.ui.fragment.home.HomeRecyclerViewData;
import com.zhangju.xingquban.interestclassapp.ui.fragment.home.contest.ContestWebActivity1;
import com.zhangju.xingquban.interestclassapp.ui.fragment.home.homeviewpage.HomeClassPage1;
import com.zhangju.xingquban.interestclassapp.ui.fragment.home.homeviewpage.HomeClassPage2;
import com.zhangju.xingquban.interestclassapp.ui.fragment.home.homeviewpage.HomeClassPage3;
import com.zhangju.xingquban.interestclassapp.ui.fragment.home.zyzs.ChangeCityEvent;
import com.zhangju.xingquban.interestclassapp.ui.fragment.near.DistrictActivity_Copy;
import com.zhangju.xingquban.interestclassapp.ui.fragment.near.NearShare.NearShareActivity;
import com.zhangju.xingquban.interestclassapp.util.ToastUtil;
import com.zhangju.xingquban.interestclassapp.util.UrlUtils;
import com.zhangju.xingquban.interestclassapp.util.click.NoDoubleClick;
import com.zhangju.xingquban.interestclassapp.view.BannerHelper;
import com.zhangju.xingquban.refactoring.adapter.BusinessListAdapter;
import com.zhangju.xingquban.refactoring.view.XQBLoadMoreView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

import static android.app.Activity.RESULT_OK;

/**
 * @packageName com.zhangju.xingquban.interestclassapp.tangyang
 * @FileName IndexFragment
 * @Author tangyang
 * @DATE 2018/8/2
 **/
public class IndexFragment extends BaseFragment implements SwipeRefreshLayout.OnRefreshListener, BaseQuickAdapter.RequestLoadMoreListener {

    @BindView(R.id.indexSRF)
    SwipeRefreshLayout indexSRF;
    @BindView(R.id.indexRV)
    RecyclerView indexRV;
    @BindView(R.id.head_height)
    LinearLayout headLayout;
    @BindView(R.id.home_cityText)
    TextView indexCityTxt;
    @BindView(R.id.nearShareActivity)
    LinearLayout nearShareActivity;
    @BindView(R.id.home_message)
    LinearLayout indexMessageLay;

    Banner banner;
    LinearLayout homeHeadTeacher;
    LinearLayout homeHeadAgencies;
    LinearLayout homeHeadResourecs;
    LinearLayout homeHeadBrodcase;
    LinearLayout homeHeadZhibotuijian;
    RelativeLayout homeHeadWangluoshipin;
    RelativeLayout homeHeadHuodongzixun;
    LinearLayout homeHeadYouxiulaoshi;
    LinearLayout homeHeadYouxiujigou;
    LinearLayout homeHeadJingpinkecheng;
    RecyclerView homeRecyclerview;
    ViewPager homeItemCp;
    RecyclerView homePageIndicator;

    private BannerHelper mBannerHelper;
    private FragmentItemAdapter fragmentItemAdapter;
    private IndicatorAdapter indicatorAdapter;

    private String city;
    String latitude;
    String longtitude;
    private String requestCity = "上海市";
    private String requestCityCode = "310000";
    private String mRequestCityPid = "100000";
    private String myLat = "30.292678";
    private String myLng = "120.036981";

    private Intent intent;
    private Bundle bundle;


    private BusinessListAdapter businessListAdapter;
    private int currPage = 0;


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


    /***
     * 加载banner数据
     */
    private void loadBanner() {
        /***banner**/
        mBannerHelper = new BannerHelper(getActivity());
        mBannerHelper.init(banner).loadBannerDate("4");
    }


    @Override
    public void initData() {
        currPage = 0;
        loadBottomData(currPage);

        loadBanner();

        /**中间类别数据**/
        NetWork.getNearSubject().getKemuAllData()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);

        /***指示器***/
        intiInditors();

        /***初始化定位***/
        initLocation();

    }

    /****
     * 初始化定位
     */
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
                        latitude = Double.toString(amapLocation.getLatitude());
                        longtitude = Double.toString(amapLocation.getLongitude());
                        requestCity = amapLocation.getCity();
                        if (LocationManager.getInstance().getLocation().locationCity.equals("默认城市")) {
                            indexCityTxt.setText(requestCity);
                            getCityCode(latitude, longtitude);
                            LocationManager instance = LocationManager.getInstance();
                            Location location = instance.getLocation();
                            location.latitude = latitude;
                            location.longitude = longtitude;
                            location.cityId = amapLocation.getAdCode();
                            location.cityPid = "100000";
                            location.locationCity = city;
                            LocationManager.getInstance().updateLocation(location);
                        } else {
                            indexCityTxt.setText(LocationManager.getInstance().getLocation().locationCity);
                            LocationManager.getInstance().convertCityId();
                            currPage = 0;
                            loadBottomData(currPage);
                            loadBanner();
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


    private void getCityCode(String lat, String lng) {
        NetWork.getuserData().getConvertLocation(lat, lng, "city")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observerCity);
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
                    location.latitude = latitude;
                    location.longitude = longtitude;
                    location.cityId = requestCityCode;
                    location.cityPid = "100000";
                    location.locationCity = city;
                    LocationManager.getInstance().updateLocation(location);
                }
//                setCityText();
                LocationManager.getInstance().convertCityId();
                currPage = 0;
                loadBottomData(currPage);
                loadBanner();
            }

        }
    };


    /***
     *加载底部商家数据
     * @param pageIndex
     */
    private void loadBottomData(final int pageIndex) {
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
                                    indexSRF.setRefreshing(false);
                                    if (dataBeanList != null && dataBeanList.size() > 0) {
                                        if (dataBeanList.size() < 10) {
                                            businessListAdapter.loadMoreComplete();
                                            businessListAdapter.loadMoreEnd();
                                            businessListAdapter.setNewData(dataBeanList);
                                        } else {
                                            businessListAdapter.setEnableLoadMore(true);
                                            businessListAdapter.setNewData(dataBeanList);
                                        }
                                    } else {
                                        businessListAdapter.loadMoreEnd();
                                    }
                                } else {
                                    if (dataBeanList != null && dataBeanList.size() > 0) {
                                        businessListAdapter.loadMoreComplete();
                                        businessListAdapter.addData(dataBeanList);
                                    } else {
                                        businessListAdapter.loadMoreComplete();
                                        businessListAdapter.loadMoreEnd();
                                        businessListAdapter.addData(dataBeanList);
                                    }
                                }
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
    public int getMyLayout() {
        return R.layout.fragment_index;
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

            indexCityTxt.setText(requestCity);
            currPage = 0;
            LocationManager.getInstance().convertCityId();
            loadBanner();
            loadBottomData(currPage);
            EventObserver.getInstance().sendEvent(getActivity(), new ChangeCityEvent());
        }
    }


    @Override
    public void initView() {
        indexSRF.setOnRefreshListener(this);
        indexSRF.setColorSchemeColors(getResources().getColor(R.color.colorPrimary));
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        indexRV.setLayoutManager(linearLayoutManager);
        businessListAdapter = new BusinessListAdapter();
        businessListAdapter.setLoadMoreView(new XQBLoadMoreView());
        businessListAdapter.setOnLoadMoreListener(this, indexRV);
        View view = LayoutInflater.from(getActivity()).inflate(R.layout.home_head, null);
        banner = view.findViewById(R.id.home_banner);
        homeHeadTeacher = view.findViewById(R.id.home_head_teacher);
        homeHeadAgencies = view.findViewById(R.id.home_head_agencies);
        homeHeadResourecs = view.findViewById(R.id.home_head_resourecs);
        homeHeadBrodcase = view.findViewById(R.id.home_head_brodcase);
        homeHeadZhibotuijian = view.findViewById(R.id.home_head_zhibotuijian);
        homeHeadWangluoshipin = view.findViewById(R.id.home_head_wangluoshipin);
        homeHeadHuodongzixun = view.findViewById(R.id.home_head_huodongzixun);
        homeHeadYouxiulaoshi = view.findViewById(R.id.home_head_youxiulaoshi);
        homeHeadYouxiujigou = view.findViewById(R.id.home_head_youxiujigou);
        homeHeadJingpinkecheng = view.findViewById(R.id.home_head_jingpinkecheng);
        homeRecyclerview = view.findViewById(R.id.home_recyclerview);
        homeItemCp = view.findViewById(R.id.home_item_cp);
        homePageIndicator = view.findViewById(R.id.home_page_Indicator);
        businessListAdapter.addHeaderView(view);
        indexRV.setAdapter(businessListAdapter);
        businessListAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                Intent intent = new Intent(getActivity(), HomeRecyclerViewData.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("homeXq", businessListAdapter.getItem(position));
                intent.putExtra("tag", position);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });
    }

    @Override
    public void initListener() {
        homeHeadTeacher.setOnClickListener(this);
        homeHeadAgencies.setOnClickListener(this);
        homeHeadResourecs.setOnClickListener(this);
        homeHeadBrodcase.setOnClickListener(this);
        homeHeadZhibotuijian.setOnClickListener(this);
        homeHeadWangluoshipin.setOnClickListener(this);
        homeHeadHuodongzixun.setOnClickListener(this);
        homeHeadYouxiulaoshi.setOnClickListener(this);
        homeHeadYouxiujigou.setOnClickListener(this);
        homeHeadJingpinkecheng.setOnClickListener(this);
        indexCityTxt.setOnClickListener(this);
        nearShareActivity.setOnClickListener(this);
        indexMessageLay.setOnClickListener(this);
    }

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

    @Override
    public void onDestroy() {
        super.onDestroy();
        EventObserver.getInstance().unsubscribe(getActivity(), this);
        if (mBannerHelper != null) {
            mBannerHelper.stopBanner();
        }
    }

    @Override
    public void onRefresh() {
        currPage = 0;
        loadBottomData(currPage);
    }

    @Override
    public void onLoadMoreRequested() {
        currPage++;
        loadBottomData(currPage);
    }
}
