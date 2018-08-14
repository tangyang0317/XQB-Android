package com.zhangju.xingquban.interestclassapp.ui.fragment.me.MyRecrouse;

import android.content.Intent;
import android.location.Location;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;

import com.amap.api.maps.AMap;
import com.amap.api.maps.CameraUpdateFactory;
import com.amap.api.maps.MapView;
import com.amap.api.maps.UiSettings;
import com.amap.api.maps.model.LatLng;
import com.amap.api.maps.model.MyLocationStyle;
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
public class LocationActive extends AppCompatActivity implements AMap.OnMyLocationChangeListener, PoiSearch.OnPoiSearchListener {

    EditText editinput;
    RecyclerView recyclerAddress;

    MapView mapview;

    private AMap aMap;
    private MyLocationStyle myLocationStyle;
    private LocationAdapter locationAdapter;
    private List<LocationBean> mLocationList = new ArrayList<>();//地理位置数据

    private String searchCity = "杭州";
    private String keyWord;//搜索的关键字
    private PoiSearch.Query query;// Poi查询条件类
    private PoiSearch poiSearch;// POI搜索
    private PoiResult poiResult; // poi返回的结果


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_location);
        mapview = (MapView) findViewById(R.id.mapview);

        editinput = (EditText) findViewById(R.id.editinput);
        recyclerAddress = (RecyclerView) findViewById(R.id.recycler_address);
        mapview.onCreate(savedInstanceState);

        initMapview();
        intiEditSearch();
        setLocationAdapter();
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
                Intent intent=new Intent();
                intent.putExtra("address",address);
                intent.putExtra("lat",lat);
                intent.putExtra("lng",lng);
                intent.putExtra("areaName",locationBean.getAresName());
                intent.putExtra("areaCode",locationBean.getAreaId());
                intent.putExtra("provinceName",locationBean.getProvinceName());
                intent.putExtra("provinceCode",locationBean.getProvinceId());
                intent.putExtra("cityId",locationBean.getCityCode());
                intent.putExtra("cityName",locationBean.getCityName());
                setResult(RESULT_OK,intent);
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


    private void initMapview() {
        if (aMap == null) {
            aMap = mapview.getMap();
            // 如果要设置定位的默认状态，可以在此处进行设置
            myLocationStyle = new MyLocationStyle();
            aMap.setMyLocationStyle(myLocationStyle);

            UiSettings uiSettings = aMap.getUiSettings();


            aMap.getUiSettings().setMyLocationButtonEnabled(false);// 设置默认定位按钮是否显示
            aMap.setMyLocationEnabled(true);// 设置为true表示显示定位层并可触发定位，false表示隐藏定位层并不可触发定位，默认是false
        }
        //设置SDK 自带定位消息监听
        aMap.moveCamera(CameraUpdateFactory.zoomTo(14));
        aMap.setOnMyLocationChangeListener(this);

        aMap.setMyLocationStyle(myLocationStyle.myLocationType(MyLocationStyle.LOCATION_TYPE_SHOW));//	// 只定位，不进行其他操作


    }

    ///搜索执行
    private void doSearchQuery() {
        query = new PoiSearch.Query(keyWord, "", searchCity);// 第一个参数表示搜索字符串，第二个参数表示poi搜索类型，第三个参数表示poi搜索区域（空字符串代表全国）
        query.setPageSize(10);// 设置每页最多返回多少条poiitem
        query.setPageNum(1);// 设置查第一页

        poiSearch = new PoiSearch(this, query);
        poiSearch.setOnPoiSearchListener(this);
        poiSearch.searchPOIAsyn();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //在activity执行onDestroy时执行mMapView.onDestroy()，销毁地图
        mapview.onDestroy();
    }

    @Override
    protected void onResume() {
        super.onResume();
        //在activity执行onResume时执行mMapView.onResume ()，重新绘制加载地图
        mapview.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        //在activity执行onPause时执行mMapView.onPause ()，暂停地图的绘制
        mapview.onPause();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        //在activity执行onSaveInstanceState时执行mMapView.onSaveInstanceState (outState)，保存地图当前的状态
        mapview.onSaveInstanceState(outState);
    }

    @Override
    public void onMyLocationChange(Location location) {
        if (location != null) {
            Bundle bundle = location.getExtras();
            searchCity = bundle.getString("City");

            LatLng latLng = new LatLng(location.getLatitude(), location.getLongitude());
            aMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng, 14));
        }
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
                        PoiItem item=poiItems.get(i);
                        LatLonPoint latLonPoint =item.getLatLonPoint();
                        String title = poiItems.get(i).getTitle();
                        LocationBean bean=new LocationBean(title, latLonPoint.getLatitude(), latLonPoint.getLongitude());
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
