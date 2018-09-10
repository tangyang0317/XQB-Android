package com.zhangju.xingquban.refactoring;

import android.Manifest;
import android.annotation.TargetApi;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
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
import com.orhanobut.logger.Logger;
import com.tbruyelle.rxpermissions2.RxPermissions;
import com.youth.banner.Banner;
import com.zhangju.xingquban.R;
import com.zhangju.xingquban.interestclassapp.RetrofitInterface.NetWork;
import com.zhangju.xingquban.interestclassapp.application.MyApp;
import com.zhangju.xingquban.interestclassapp.base.BaseFragment;
import com.zhangju.xingquban.interestclassapp.bean.CityConvertBean;
import com.zhangju.xingquban.interestclassapp.bean.HomeRecylerBean;
import com.zhangju.xingquban.interestclassapp.bean.NearDataBean;
import com.zhangju.xingquban.interestclassapp.refactor.common.bean.CommonInterface;
import com.zhangju.xingquban.interestclassapp.refactor.location.Location;
import com.zhangju.xingquban.interestclassapp.refactor.location.LocationManager;
import com.zhangju.xingquban.interestclassapp.refactor.me.activity.LoginActivity;
import com.zhangju.xingquban.interestclassapp.refactor.me.activity.NotificationActivity;
import com.zhangju.xingquban.interestclassapp.refactor.user.UserManager;
import com.zhangju.xingquban.interestclassapp.ui.activity.near.CurriculumActivity;
import com.zhangju.xingquban.interestclassapp.ui.activity.near.HomeDistrictActivity;
import com.zhangju.xingquban.interestclassapp.ui.fragment.find.SeekMessage.FindMessage;
import com.zhangju.xingquban.interestclassapp.ui.fragment.find.SeekResource.FindResource;
import com.zhangju.xingquban.interestclassapp.ui.fragment.find.Wenda.Question.fresh.MyQuestionMain;
import com.zhangju.xingquban.interestclassapp.ui.fragment.home.CityActivity;
import com.zhangju.xingquban.interestclassapp.ui.fragment.home.ExperienceActivity;
import com.zhangju.xingquban.interestclassapp.ui.fragment.home.HomeRecyclerViewData;
import com.zhangju.xingquban.interestclassapp.ui.fragment.home.contest.ContestWebActivity1;
import com.zhangju.xingquban.interestclassapp.ui.fragment.home.zyzs.ChangeCityEvent;
import com.zhangju.xingquban.interestclassapp.ui.fragment.near.DistrictActivity_Copy;
import com.zhangju.xingquban.interestclassapp.ui.fragment.near.NearShare.NearShareActivity;
import com.zhangju.xingquban.interestclassapp.util.ToastUtil;
import com.zhangju.xingquban.interestclassapp.util.UrlUtils;
import com.zhangju.xingquban.interestclassapp.util.click.NoDoubleClick;
import com.zhangju.xingquban.interestclassapp.view.BannerHelper;
import com.zhangju.xingquban.refactoring.adapter.BusinessListAdapter;
import com.zhangju.xingquban.refactoring.adapter.CategoryViewPagerAdapter;
import com.zhangju.xingquban.refactoring.adapter.IndexCategoryGridAdapter;
import com.zhangju.xingquban.refactoring.dblite.CategoryDao;
import com.zhangju.xingquban.refactoring.entity.CategoryBean;
import com.zhangju.xingquban.refactoring.observer.XObserver;
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
    ViewPager homeItemCp;
    //圆点指示器
    private LinearLayout group;
    //小圆点图片的集合
    private ImageView[] ivPoints;
    private BannerHelper mBannerHelper;
    private Intent intent;
    private Bundle bundle;

    private BusinessListAdapter businessListAdapter;
    private int currPage = 0;

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
        /***初始化定位***/
        requestPermission();
        loadBottomData(currPage);
        loadBanner();
        loadCategoryData();
    }

    /**
     * 申请权限
     */
    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    public void requestPermission() {
        new RxPermissions(this)
                .request(Manifest.permission.ACCESS_FINE_LOCATION,
                        Manifest.permission.ACCESS_COARSE_LOCATION,
                        Manifest.permission.CAMERA,
                        Manifest.permission.CALL_PHONE,
                        Manifest.permission.READ_EXTERNAL_STORAGE,
                        Manifest.permission.WRITE_EXTERNAL_STORAGE,
                        Manifest.permission.READ_PHONE_STATE)//多个权限用","隔开
                .subscribe(new XObserver<Boolean>() {
                    @Override
                    protected void success(Boolean aBoolean) {
                        if (aBoolean) {
                            initLocation();
                            Logger.d("权限获取成功");
                        }
                    }

                    @Override
                    protected void error(String error) {
                        Logger.d("权限获取失败，部分功能将无法使用");
                        ToastUtil.showToast("");
                    }
                });

    }

    /***
     * 加载类别View
     */
    private void loadCategoryData() {
        List<CategoryBean> categoryBeanList = new CategoryDao(getActivity()).queryLevelOneAll();
        final int totalPage = (int) Math.ceil(categoryBeanList.size() * 1.0 / 10);
        List<View> viewList = new ArrayList<>();
        for (int i = 0; i < totalPage; i++) {
            final GridView gridView = (GridView) LayoutInflater.from(getActivity()).inflate(R.layout.view_category_grid, null);
            gridView.setAdapter(new IndexCategoryGridAdapter(getActivity(), categoryBeanList, i, 10));
            gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int postion, long l) {
                    Intent intent = new Intent(getActivity(), HomeDistrictActivity.class);
                    intent.putExtra("categoryId", ((CategoryBean) adapterView.getItemAtPosition(postion)).getId());
                    startActivity(intent);
                }
            });
            viewList.add(gridView);
        }
        homeItemCp.setAdapter(new CategoryViewPagerAdapter(viewList));

        //添加小圆点
        ivPoints = new ImageView[totalPage];
        for (int i = 0; i < totalPage; i++) {
            //循坏加入点点图片组
            ivPoints[i] = new ImageView(getActivity());
            if (i == 0) {
                ivPoints[i].setImageResource(R.drawable.indicator_red);
            } else {
                ivPoints[i].setImageResource(R.drawable.indicator_gray);
            }
            ivPoints[i].setPadding(8, 0, 8, 0);
            group.addView(ivPoints[i]);
        }
        //设置ViewPager的滑动监听，主要是设置点点的背景颜色的改变
        homeItemCp.setOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() {
            @Override
            public void onPageSelected(int position) {
                // TODO Auto-generated method stub
                //currentPage = position;
                for (int i = 0; i < totalPage; i++) {
                    if (i == position) {
                        ivPoints[i].setImageResource(R.drawable.indicator_red);
                    } else {
                        ivPoints[i].setImageResource(R.drawable.indicator_gray);
                    }
                }
            }
        });


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
                        indexCityTxt.setText(amapLocation.getCity());
                        getCityCode(Double.toString(amapLocation.getLatitude()), Double.toString(amapLocation.getLongitude()));
                        LocationManager instance = LocationManager.getInstance();
                        Location location = instance.getLocation();
                        location.latitude = Double.toString(amapLocation.getLatitude());
                        location.longitude = Double.toString(amapLocation.getLongitude());
                        location.cityId = amapLocation.getAdCode();
                        location.cityPid = "100000";
                        location.locationCity = amapLocation.getCity();
                        LocationManager.getInstance().updateLocation(location);
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
                    LocationManager instance = LocationManager.getInstance();
                    Location location = instance.getLocation();
                    location.latitude = instance.getLocation().latitude;
                    location.longitude = instance.getLocation().longitude;
                    location.cityId = addressComponent.getAdcode();
                    location.cityPid = "100000";
                    location.locationCity = instance.getLocation().locationCity;
                    LocationManager.getInstance().updateLocation(location);
                }
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
        String cityId = TextUtils.isEmpty(LocationManager.getInstance().getLocation().cityId) ? "310000" : LocationManager.getInstance().getLocation().cityId;
        String lat = TextUtils.isEmpty(LocationManager.getInstance().getLocation().latitude) ? "30.292678" : LocationManager.getInstance().getLocation().latitude;
        String lng = TextUtils.isEmpty(LocationManager.getInstance().getLocation().cityId) ? "120.036981" : LocationManager.getInstance().getLocation().longitude;
        params.addHeader("X-CustomToken", UserManager.getInstance().getToken());
        params.addBodyParameter("pageIndex", pageIndex + "");
        params.addBodyParameter("pageSize", "30");
        params.addBodyParameter("cityId", cityId);
        params.addBodyParameter("degreeId", "2");
        params.addBodyParameter("lat", lat);
        params.addBodyParameter("lng", lng);
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
            String cityId = data.getStringExtra("cityId");
            String cityPid = data.getStringExtra("citypId");
            indexCityTxt.setText(city);
            LocationManager instance = LocationManager.getInstance();
            Location location = instance.getLocation();
            location.cityId = cityId;
            location.cityPid = cityPid;
            location.locationCity = city;
            LocationManager.getInstance().updateLocation(location);
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
        group = view.findViewById(R.id.points);
        homeItemCp = view.findViewById(R.id.home_item_cp);
        businessListAdapter.addHeaderView(view);
        indexRV.setAdapter(businessListAdapter);
        businessListAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                HomeRecyclerViewData.launchActivity(getActivity(), businessListAdapter.getItem(position).getId());
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
