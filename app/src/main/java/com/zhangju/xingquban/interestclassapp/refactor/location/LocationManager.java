package com.zhangju.xingquban.interestclassapp.refactor.location;

import android.text.TextUtils;

import com.fastlib.app.EventObserver;
import com.fastlib.db.FastDatabase;
import com.fastlib.net.Request;
import com.fastlib.net.SimpleListener;
import com.zhangju.xingquban.interestclassapp.application.MyApp;
import com.zhangju.xingquban.interestclassapp.bean.CityNameBean;
import com.zhangju.xingquban.interestclassapp.bean.HomeCityIdBaen;
import com.zhangju.xingquban.interestclassapp.refactor.common.bean.CommonInterface;

import java.util.List;

/**
 * Created by sgfb on 2017/10/30.
 * 定位管理器
 */
public class LocationManager {
    private static LocationManager mInstance;
    private Location mLocation;
    private List<CityNameBean.AaDataBean> cityData;

    public List<CityNameBean.AaDataBean> getCityData() {
        return cityData;
    }

    public void setCityData(List<CityNameBean.AaDataBean> cityData) {
        this.cityData = cityData;
    }



    private LocationManager() {
        if (mLocation == null)
            mLocation = FastDatabase.getDefaultInstance(MyApp.instance).getFirst(Location.class);
    }

    public synchronized static LocationManager getInstance() {
        if (mInstance == null) mInstance = new LocationManager();
        return mInstance;
    }

    public void updateLocation(Location location) {
        if (location == null) return; //抛弃空定位
        mLocation = location;
        FastDatabase.getDefaultInstance(MyApp.instance).saveOrUpdate(mLocation);
        EventObserver.getInstance().sendEvent(MyApp.instance, new EventLocationChanged());
    }

    public Location getLocation() {
        try {
            if(mLocation==null)
                mLocation=new Location();
            return mLocation.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 从服务器用城市名获取到城市ID（与高德地图的城市ID不同）
     */
    public void convertCityId(){
        if(mLocation==null|| TextUtils.isEmpty(mLocation.locationCity)){
            System.out.println("城市数据异常，无法刷新城市ID");
            return;
        }
        Request request=new Request(CommonInterface.POST_CITY_ID_BY_NAME);
        request.put("name",mLocation.locationCity);
        request.setListener(new SimpleListener<HomeCityIdBaen>(){

            @Override
            public void onResponseListener(Request r, HomeCityIdBaen result) {
                if(result!=null&&result.isSuccess()&&result.getAaData()!=null&&!result.getAaData().isEmpty()){
                    Location location=getLocation();
                    location.cityId=result.getAaData().get(0).getId();
                    location.cityPid=result.getAaData().get(0).getPid();
                    updateLocation(location);
                }
            }
        });
        request.start();
    }
}