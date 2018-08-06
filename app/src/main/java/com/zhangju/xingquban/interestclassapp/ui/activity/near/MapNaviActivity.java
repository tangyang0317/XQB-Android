package com.zhangju.xingquban.interestclassapp.ui.activity.near;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.amap.api.maps.AMap;
import com.amap.api.maps.CameraUpdateFactory;
import com.amap.api.maps.MapView;
import com.amap.api.maps.model.BitmapDescriptorFactory;
import com.amap.api.maps.model.CameraPosition;
import com.amap.api.maps.model.LatLng;
import com.amap.api.maps.model.Marker;
import com.amap.api.maps.model.MarkerOptions;
import com.zhangju.xingquban.R;
import com.zhangju.xingquban.interestclassapp.base.BaseActivity;
import com.zhangju.xingquban.interestclassapp.util.ToastUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 项目名称：MeijuApp
 * Created by Dr. Zhu on 2017/9/9.
 */

public class MapNaviActivity extends BaseActivity {
	public static final String ARG_DOUBLE_LNG="lng";
	public static final String ARG_DOUBLE_LAT="lat";
	public static final String ARG_STRING_NAME="name";
	@BindView(R.id.back)
	ImageView back;
	@BindView(R.id.navga)
	TextView navga;
	@BindView(R.id.toolbar)
	RelativeLayout toolbar;
	@BindView(R.id.height1)
	LinearLayout height1;
	@BindView(R.id.bmapView)
	MapView mMapView;

	private double lng;
	private double lat;
	private String  name;
	private MapView mBaiduMap;
	private Button button;
	private	AMap aMap;
	@Override
	public int getLayout() {
		return R.layout.map_navigation;
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		mMapView.onCreate(savedInstanceState);// 此方法须覆写，虚拟机需要在很多情况下保存地图绘制的当前状态。
	}

	@Override
	public void initView() {
		lng= (double) getIntent().getExtras().get("lng");
		lat= (double) getIntent().getExtras().get("lat");
		name= (String) getIntent().getExtras().get("name");

		aMap = mMapView.getMap();

		LatLng pt = new LatLng(lat,lng);
		aMap.moveCamera(CameraUpdateFactory.newCameraPosition(CameraPosition.builder().zoom(17).target(pt).build()));
		CameraPosition.builder().zoom(17).target(pt);
		MarkerOptions mark = new MarkerOptions();
		mark.anchor(0.5f, 1);
		mark.position(pt);
		mark.title("导航到"+name);
		mark.snippet("导航到"+name);
		/*mark.draggable(true);*/
		mark.icon(BitmapDescriptorFactory.fromResource(R.drawable.mark_local));//设置图标
		aMap.addMarker(mark);

		aMap.showMapText(true);
	}
	/**
	 * 设置中心点
	 */
	private void setUserMapCenter() {

	/*	LatLng point = new LatLng(Double.valueOf(lat),Double.valueOf(lng));
		//定义地图状态
		Maps mMapStatus = new MapStatus.Builder()
				.target(point)
				.zoom(18)
				.build();
		//定义MapStatusUpdate对象，以便描述地图状态将要发生的变化
		MapStatusUpdate mMapStatusUpdate = MapStatusUpdateFactory.newMapStatus(mMapStatus);
		//改变地图状态
		aMap.setMapStatus(mMapStatusUpdate);*/

	}
	@Override
	public void initData() {

	}

	@Override
	public void initListener() {
		aMap.setOnMarkerClickListener(new AMap.OnMarkerClickListener() {
			@Override
			public boolean onMarkerClick(Marker marker) {
				if(isAvilible(MapNaviActivity.this,"com.baidu.BaiduMap")){
					Intent intent=new Intent();
					intent.setData(Uri.parse("baidumap://map/marker?location="+lat+","+lng+"&title="+name+"&traffic=on"));
					startActivity(intent);
				}else if(isAvilible(MapNaviActivity.this,"com.autonavi.minimap")){
					Intent intent=new Intent();
					intent.setData(Uri.parse("androidamap://navi?sourceApplication=公司&poiname="+name+"&lat="+lat+"&lon"+lng+ "&dev=0"));
					startActivity(intent);
				}else {
					ToastUtil.showToast("您未安装导航软件");
				}
				return false;
			}
		});
/*		button.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				if(isAvilible(MapNaviActivity.this,"com.baidu.BaiduMap")){
					Intent intent=new Intent();
					intent.setData(Uri.parse("baidumap://map/marker?location="+lat+","+lng+"&title="+name+"&traffic=on"));
					startActivity(intent);
				}else if(isAvilible(MapNaviActivity.this,"com.autonavi.minimap")){
					Intent intent=new Intent();
					intent.setData(Uri.parse("androidamap://navi?poiname="+name+"&lat="+lat+"&lon"+lng+"&dev=0"));
					startActivity(intent);
				}else {
					ToastUtil.showToast("您未安装导航软件");
				}

			}
		});*/
	}
	@OnClick({R.id.back,R.id.bmapView,R.id.navga})
	@Override
	public void onClick(View v) {
		switch (v.getId()){
			case R.id.back:
				finish();
				break;
			case R.id.bmapView:
				break;
			case R.id.navga:
				if(isAvilible(MapNaviActivity.this,"com.baidu.BaiduMap")){
					Intent intent=new Intent();
					intent.setData(Uri.parse("baidumap://map/marker?location="+lat+","+lng+"&title="+name+"&traffic=on"));
					startActivity(intent);
				}else if(isAvilible(MapNaviActivity.this,"com.autonavi.minimap")){
					Intent intent=new Intent();
					intent.setData(Uri.parse("androidamap://route?sourceApplication=softname&sname=我的位置&dlat="+lat+"&dlon="+lng+"&dname="+name+"&dev=0&m=0&t=1"));
//					intent.setData(Uri.parse("androidamap://navi?sourceApplication=公司&poiname="+name+"&lat="+lat+"&lon"+lng+ "&dev=0"));
					startActivity(intent);
				}else {
					ToastUtil.showToast("您未安装导航软件");
				}
				break;
		}
	}

	public static boolean isAvilible(Context context, String packageName){
		//获取packagemanager
		final PackageManager packageManager = context.getPackageManager();
		//获取所有已安装程序的包信息
		List<PackageInfo> packageInfos = packageManager.getInstalledPackages(0);
		//用于存储所有已安装程序的包名
		List<String> packageNames = new ArrayList<String>();
		//从pinfo中将包名字逐一取出，压入pName list中
		if(packageInfos != null){
			for(int i = 0; i < packageInfos.size(); i++){
				String packName = packageInfos.get(i).packageName;
				packageNames.add(packName);
			}
		}
		//判断packageNames中是否有目标程序的包名，有TRUE，没有FALSE
		return packageNames.contains(packageName);
	}
}
