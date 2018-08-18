package com.zhangju.xingquban.interestclassapp.ui.fragment.me.MyRecrouse;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;

import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationClientOption;
import com.amap.api.location.AMapLocationListener;
import com.amap.api.services.core.AMapException;
import com.amap.api.services.core.LatLonPoint;
import com.amap.api.services.core.PoiItem;
import com.amap.api.services.poisearch.PoiResult;
import com.amap.api.services.poisearch.PoiSearch;
import com.zhangju.xingquban.R;
import com.zhangju.xingquban.interestclassapp.adapter.LocationAdapter;
import com.zhangju.xingquban.interestclassapp.adapter.baseadpter.OnListItemClickListener;
import com.zhangju.xingquban.interestclassapp.bean.me.LocationBean;

import java.util.ArrayList;
import java.util.List;

//地图poi位置检索
public class LocationActive extends AppCompatActivity implements PoiSearch.OnPoiSearchListener {
    EditText editinput;
    RecyclerView recyclerAddress;
    private LocationAdapter locationAdapter;
    private List<LocationBean> mLocationList = new ArrayList<>();//地理位置数据

    private String searchCity = "杭州";
    private String keyWord = "杭州";//搜索的关键字
    private PoiSearch.Query query;// Poi查询条件类
    private PoiSearch poiSearch;// POI搜索
    private PoiResult poiResult; // poi返回的结果

    //声明mlocationClient对象
    public AMapLocationClient mlocationClient;
    //声明mLocationOption对象
    public AMapLocationClientOption mLocationOption = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_location);
        editinput = findViewById(R.id.editinput);
        recyclerAddress = findViewById(R.id.recycler_address);
        initLocation();
        doSearchQuery();
        intiEditSearch();
        setLocationAdapter();
    }

    /***
     * 初始化定位
     */
    private void initLocation() {
        mlocationClient = new AMapLocationClient(this);
        //初始化定位参数
        mLocationOption = new AMapLocationClientOption();
        //设置定位监听
        mlocationClient.setLocationListener(new AMapLocationListener() {
            @Override
            public void onLocationChanged(AMapLocation aMapLocation) {
                if (aMapLocation != null && aMapLocation.getErrorCode() == 0) {
                    keyWord = aMapLocation.getCity();
                    searchCity = aMapLocation.getCity();
                }
            }
        });
        //设置定位模式为高精度模式，Battery_Saving为低功耗模式，Device_Sensors是仅设备模式
        mLocationOption.setLocationMode(AMapLocationClientOption.AMapLocationMode.Hight_Accuracy);
        //设置定位间隔,单位毫秒,默认为2000ms
        mLocationOption.setInterval(2000);
        //设置定位参数
        mlocationClient.setLocationOption(mLocationOption);
        // 此方法为每隔固定时间会发起一次定位请求，为了减少电量消耗或网络流量消耗，
        // 注意设置合适的定位时间的间隔（最小间隔支持为1000ms），并且在合适时间调用stopLocation()方法来取消定位请求
        // 在定位结束后，在合适的生命周期调用onDestroy()方法
        // 在单次定位情况下，定位无论成功与否，都无需调用stopLocation()方法移除请求，定位sdk内部会移除
        //启动定位
        mlocationClient.startLocation();
    }

    private void setLocationAdapter() {
        locationAdapter = new LocationAdapter(LocationActive.this, mLocationList);
        recyclerAddress.setLayoutManager(new LinearLayoutManager(LocationActive.this));
        recyclerAddress.setAdapter(locationAdapter);
        locationAdapter.setOnListItemClickListener(new OnListItemClickListener() {
            @Override
            public void onItemClickListener(int position, View v) {
                LocationBean locationBean = mLocationList.get(position);
                String address = locationBean.getAddress();
                String lat = locationBean.getLat().toString();
                String lng = locationBean.getLng().toString();
                Intent intent = new Intent();
                intent.putExtra("address", address);
                intent.putExtra("lat", lat);
                intent.putExtra("lng", lng);
                intent.putExtra("areaName", locationBean.getAresName());
                intent.putExtra("areaCode", locationBean.getAreaId());
                intent.putExtra("provinceName", locationBean.getProvinceName());
                intent.putExtra("provinceCode", locationBean.getProvinceId());
                intent.putExtra("cityId", locationBean.getCityCode());
                intent.putExtra("cityName", locationBean.getCityName());
                setResult(RESULT_OK, intent);
                finish();
            }
        });
    }

    private void intiEditSearch() {
        editinput.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                keyWord = s.toString().trim();
                doSearchQuery();
            }

            @Override
            public void afterTextChanged(Editable s) {


            }
        });
    }


    ///搜索执行
    private void doSearchQuery() {
        query = new PoiSearch.Query(keyWord, "", searchCity);// 第一个参数表示搜索字符串，第二个参数表示poi搜索类型，第三个参数表示poi搜索区域（空字符串代表全国）
        query.setPageSize(20);// 设置每页最多返回多少条poiitem
        query.setPageNum(1);// 设置查第一页
        poiSearch = new PoiSearch(this, query);
        poiSearch.setOnPoiSearchListener(this);
        poiSearch.searchPOIAsyn();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    public void onPoiSearched(PoiResult result, int rCode) {
        if (rCode == AMapException.CODE_AMAP_SUCCESS) {
            if (result != null && result.getQuery() != null) {// 搜索poi的结果
                if (result.getQuery().equals(query)) {// 是否是同一条
                    poiResult = result;
                    // 取得搜索到的poiitems有多少页
                    List<PoiItem> poiItems = poiResult.getPois();// 取得第一页的poiitem数据，页数从数字0开始
                    mLocationList.clear();
                    for (int i = 0; i < poiItems.size(); i++) {
                        PoiItem item = poiItems.get(i);
                        LatLonPoint latLonPoint = item.getLatLonPoint();
                        String title = poiItems.get(i).getTitle();
                        LocationBean bean = new LocationBean(title, latLonPoint.getLatitude(), latLonPoint.getLongitude());
                        bean.setAreaId(item.getAdCode());
                        bean.setAresName(item.getAdName());
                        bean.setCityCode(item.getCityCode());
                        bean.setCityName(item.getCityName());
                        bean.setProvinceId(item.getProvinceCode());
                        bean.setProvinceName(item.getProvinceName());
                        mLocationList.add(bean);
                    }
                }
            }
        } else {
            mLocationList.clear();
        }
        locationAdapter.notifyDataSetChanged();
    }

    @Override
    public void onPoiItemSearched(PoiItem poiItem, int i) {

    }
}
