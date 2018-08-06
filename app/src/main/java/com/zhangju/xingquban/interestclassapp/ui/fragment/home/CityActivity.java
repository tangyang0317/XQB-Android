package com.zhangju.xingquban.interestclassapp.ui.fragment.home;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationClientOption;
import com.amap.api.location.AMapLocationListener;
import com.zhangju.xingquban.R;
import com.zhangju.xingquban.interestclassapp.RetrofitInterface.NetWork;
import com.zhangju.xingquban.interestclassapp.adapter.CitySortAdapter;
import com.zhangju.xingquban.interestclassapp.adapter.HotCityAdapter;
import com.zhangju.xingquban.interestclassapp.adapter.baseadpter.OnListItemClickListener;
import com.zhangju.xingquban.interestclassapp.bean.CityConvertBean;
import com.zhangju.xingquban.interestclassapp.bean.CityNameBean;
import com.zhangju.xingquban.interestclassapp.hplper.ScrollGridManager;
import com.zhangju.xingquban.interestclassapp.refactor.location.Location;
import com.zhangju.xingquban.interestclassapp.refactor.location.LocationManager;
import com.zhangju.xingquban.interestclassapp.ui.widget.SideBar;
import com.zhangju.xingquban.interestclassapp.util.PinyinComparator;
import com.zhangju.xingquban.interestclassapp.util.SortUtils;
import com.zhangju.xingquban.interestclassapp.util.SpUtil;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import rx.Observer;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;


public class CityActivity
        extends AppCompatActivity {
    private static final String TAG = "CityActivity";


    RecyclerView recyclerRecent;
    RecyclerView recyclerHot;
    TextView tvShowCity;

    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.dialog)
    TextView dialog;
    @BindView(R.id.sideBar)
    SideBar sideBar;
    @BindView(R.id.back)
    ImageView mBack;
    @BindView(R.id.opertion)
    TextView mOpertion;
    @BindView(R.id.et_search_city)
    EditText mEtSearchCity;
   /* @BindView(R.id.me_recrouse_popw_ypHead)
    PublicHead meRecrousePopwYpHead;*/


    private AMapLocationClient mLocationClient;
    private Subscription subscription;
    private List<CityNameBean.AaDataBean> cityData = new ArrayList<>();//全部城市

    private List<CityNameBean.AaDataBean> hotCityList = new ArrayList<>();//热门城市数据展示
    private List<CityNameBean.AaDataBean> historyCityList = new ArrayList<>();//历史数据展示

    private CitySortAdapter citySortAdapter;
    private View mView;//头资源

    private HotCityAdapter hotCityAdapter;
    private HotCityAdapter historyAdpter;
    private LinearLayoutManager manager;

    private String intentCity;
    private String intentCityCode;
    private String mCityPid;


    //    private LoadingDialog loadingDialog;


    /**
     * 根据拼音来排列RecyclerView里面的数据类
     */
    private PinyinComparator pinyinComparator;


    //全部城市数据
    private Observer<CityNameBean> observer1 = new Observer<CityNameBean>() {
        @Override
        public void onCompleted() {
        }

        @Override
        public void onError(Throwable e) {
            //            loadingDialog.dismiss();
        }

        @Override
        public void onNext(CityNameBean cityNameBean) {
            cityData.addAll(SortUtils.filledData(cityNameBean.getAaData().toArray(new CityNameBean.AaDataBean[cityNameBean
                    .getAaData().size()])));
            // 根据a-z进行排序源数据
            Collections.sort(cityData, pinyinComparator);
            citySortAdapter.notifyDataSetChanged();
            //            loadingDialog.dismiss();
        }
    };
    //热门数据
    private Observer<CityNameBean> observer2 = new Observer<CityNameBean>() {
        @Override
        public void onCompleted() {
        }

        @Override
        public void onError(Throwable e) {
        }

        @Override
        public void onNext(CityNameBean cityNameBean) {
            hotCityList.addAll(cityNameBean.getAaData());
            hotCityAdapter.notifyDataSetChanged();

        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_city);
        ButterKnife.bind(this);
        initView();
        initData();
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    private void initView() {
        //        loadingDialog=new LoadingDialog(CityActivity.this);
        //        loadingDialog.setLoading("数据加载...");
        //        loadingDialog.show();

        manager = new LinearLayoutManager(this);
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(manager);

        intiHeadtop();
        mHeadView();
        setHotAdapterList();
        setHistoryAdapterList();
        setSortCityAdapter();

        mEtSearchCity.addTextChangedListener(watcher);

        sideBar.setTextView(dialog);
        //设置右侧SideBar触摸监听
        sideBar.setOnTouchingLetterChangedListener(new SideBar.OnTouchingLetterChangedListener() {

            @Override
            public void onTouchingLetterChanged(String s) {
                //该字母首次出现的位置
                int position = citySortAdapter.getPositionForSection(s.charAt(0));
                if (position != -1) {
                    manager.scrollToPositionWithOffset(position, 1);
                }
            }
        });
    }

    private void intiHeadtop() {

        mOpertion.setText("清除记录");
        mBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                if (imm != null && mBack != null) {
                    imm.hideSoftInputFromWindow(mBack.getWindowToken(), 0);
                }
                mLocationClient.stopLocation();
                finish();
            }
        });
        mOpertion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                historyCityList.clear();
                SpUtil.removeKey(CityActivity.this, "hisList");
                historyAdpter.notifyDataSetChanged();

            }
        });
    }

    //历史数据展示
    private void setHistoryAdapterList() {
        historyAdpter = new HotCityAdapter(CityActivity.this, historyCityList);
        ScrollGridManager scrollGridManager = new ScrollGridManager(CityActivity.this, 3);
        scrollGridManager.setScrollEnabled(false);
        recyclerRecent.setLayoutManager(scrollGridManager);
        recyclerRecent.setAdapter(historyAdpter);
        historyAdpter.setOnListItemClickListener(new OnListItemClickListener() {
            @Override
            public void onItemClickListener(int position, View v) {
                CityNameBean.AaDataBean data = historyCityList.get(position);
                intentCity = data.getName();
                intentCityCode = data.getId();
                mCityPid = data.getPid();
                Intent intent = new Intent();
                intent.putExtra("city", intentCity);
                intent.putExtra("cityId", intentCityCode);
                intent.putExtra("cityPid", mCityPid);
                updataLocation(intentCityCode, intentCity, mCityPid);
                setResult(RESULT_OK, intent);
                finish();
            }
        });
    }

    //headview
    private void mHeadView() {
        mView = LayoutInflater.from(CityActivity.this).inflate(R.layout.city_head, recyclerView, false);
        tvShowCity = (TextView) mView.findViewById(R.id.tv_show_city);
        recyclerRecent = (RecyclerView) mView.findViewById(R.id.recycler_recent);
        recyclerHot = (RecyclerView) mView.findViewById(R.id.recycler_hot);
        tvShowCity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.putExtra("city", intentCity);
                intent.putExtra("cityId", intentCityCode);
                intent.putExtra("cityPid", mCityPid);
                updataLocation(intentCityCode, intentCity, mCityPid);
                setResult(RESULT_OK, intent);

                finish();
            }
        });

    }

    //排序城市列表
    private void setSortCityAdapter() {
        pinyinComparator = new PinyinComparator();
        citySortAdapter = new CitySortAdapter(this, cityData, mView);
        recyclerView.setAdapter(citySortAdapter);
        citySortAdapter.setCityPos(new CitySortAdapter.CityPos() {
            @Override
            public void getPos(int postion) {
                List<CityNameBean.AaDataBean> filterList = citySortAdapter.getFilterList();
                CityNameBean.AaDataBean data = filterList.get(postion);
                historyCityList.add(data);
                if (historyCityList != null && historyCityList.size() > 1) {
                    removeDuplicate(historyCityList);
                }
                SpUtil.putList(CityActivity.this, "hisList", historyCityList);

                intentCity = data.getName();
                intentCityCode = data.getId();
                mCityPid = data.getPid();
                updataLocation(intentCityCode, intentCity, mCityPid);

                Intent intent = new Intent();
                intent.putExtra("city", intentCity);
                intent.putExtra("cityId", intentCityCode);
                intent.putExtra("cityPid", mCityPid);
                setResult(RESULT_OK, intent);
                finish();
            }
        });
    }

    //热门数据展示
    private void setHotAdapterList() {
        hotCityAdapter = new HotCityAdapter(CityActivity.this, hotCityList);
        ScrollGridManager scrollGridManager = new ScrollGridManager(CityActivity.this, 3);
        scrollGridManager.setScrollEnabled(false);
        recyclerHot.setLayoutManager(scrollGridManager);
        recyclerHot.setAdapter(hotCityAdapter);
        hotCityAdapter.setOnListItemClickListener(new OnListItemClickListener() {
            @Override
            public void onItemClickListener(int position, View v) {
                CityNameBean.AaDataBean data = hotCityList.get(position);
                historyCityList.add(data);
                if (historyCityList != null && historyCityList.size() > 1) {
                    removeDuplicate(historyCityList);
                }
                updataLocation(data.getId(), data.getName(), data.getPid());
                SpUtil.putList(CityActivity.this, "hisList", historyCityList);
                Intent intent = new Intent();
                intent.putExtra("city", data.getName());
                intent.putExtra("cityId", data.getId());
                intent.putExtra("cityPid", data.getPid());
                setResult(RESULT_OK, intent);
                finish();
            }
        });


    }

    private void updataLocation(String cityid, String cityname, String cityPid) {
        LocationManager instance = LocationManager.getInstance();
        Location location = instance.getLocation();
        location.latitude = LocationManager.getInstance().getLocation().latitude;
        location.longitude = LocationManager.getInstance().getLocation().longitude;
        location.cityId = cityid;
        location.cityPid = cityPid;
        location.locationCity = cityname;
        LocationManager.getInstance().updateLocation(location);
    }

    private void initData() {
        //获取热门城市数据
        subscription = NetWork.getICityName().getHotCity()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer2);

        List<CityNameBean.AaDataBean> hisList = SpUtil.getList(CityActivity.this, "hisList");
        if (hisList != null && hisList.size() > 0) {
            historyCityList.addAll(hisList);

        }
        historyAdpter.notifyDataSetChanged();


        //获取全部城市数据
        List<CityNameBean.AaDataBean> dataBeen = LocationManager.getInstance().getCityData();
        if (dataBeen != null && dataBeen.size() > 0) {
            cityData.addAll(dataBeen);

            Collections.sort(cityData, pinyinComparator);
            citySortAdapter.notifyDataSetChanged();
        } else {
            subscription = NetWork.getICityName().getCityName()
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(observer1);
        }


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
                        amapLocation.getLocationType();//获取当前定位结果来源，如网络定位结果，详见定位类型表
                        amapLocation.getLatitude();//获取纬度
                        amapLocation.getLongitude();//获取经度

                        getCityCode(String.valueOf(amapLocation.getLatitude()), String.valueOf(amapLocation.getLongitude()));

                        amapLocation.getAccuracy();//获取精度信息
                        amapLocation.getCity();
                        tvShowCity.setText(amapLocation.getCity());
                        tvShowCity.setBackgroundResource(R.drawable.city_background);
                        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                        Date date = new Date(amapLocation.getTime());
                        df.format(date);//定位时间


                        intentCity = amapLocation.getCity();
                        intentCityCode = amapLocation.getAdCode();


                        //存取经纬度
                        SharedPreferences sharedPreferences = getSharedPreferences("dhy", Context.MODE_PRIVATE);
                        SharedPreferences.Editor editor = sharedPreferences.edit();
                        double latitude = amapLocation.getLatitude();
                        double longitude = amapLocation.getLongitude();
                        String city = amapLocation.getCity();
                        String s = Double.toString(latitude);
                        String s1 = Double.toString(longitude);
                        String cityCode = amapLocation.getCityCode();

                        editor.putString("getLatitude", s).putString("getLongitude", s1)
                                .putString("CityName", city)
                                .commit();


                    } else {
                        //显示错误信息ErrCode是错误码，errInfo是错误信息，详见错误码表。
                        Log.e("AmapError", "location Error, ErrCode:"
                                + amapLocation.getErrorCode() + ", errInfo:"
                                + amapLocation.getErrorInfo());
                    }
                }
            }
        });
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
                    intentCityCode = addressComponent.getAdcode();
                }
            }
        }
    };

    @Override
    protected void onStop() {
        super.onStop();
        mLocationClient.stopLocation();
    }

    @Override
    protected void onDestroy() {
        mLocationClient.onDestroy();
        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        if (imm != null && mBack != null) {
            imm.hideSoftInputFromWindow(mBack.getWindowToken(), 0);
        }
        mLocationClient.stopLocation();
        finish();
        super.onDestroy();
    }


    //历史数据去重
    private List removeDuplicate(List<CityNameBean.AaDataBean> list) {
        for (int i = 0; i < list.size() - 1; i++) {
            for (int j = list.size() - 1; j > i; j--) {
                if (list.get(j).getId().equals(list.get(i).getId())) {
                    list.remove(j);
                }
            }
        }
        return list;
    }

    TextWatcher watcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            citySortAdapter.getFilter().filter(s.toString());
            if (s.length() != 0) {
                if (citySortAdapter.getItemCount() > 1) {
                    recyclerView.scrollToPosition(1);
                    LinearLayoutManager mLayoutManager =
                            (LinearLayoutManager) recyclerView.getLayoutManager();
                    mLayoutManager.scrollToPositionWithOffset(1, 0);
                }
            }
        }

        @Override
        public void afterTextChanged(Editable s) {

        }
    };


}
