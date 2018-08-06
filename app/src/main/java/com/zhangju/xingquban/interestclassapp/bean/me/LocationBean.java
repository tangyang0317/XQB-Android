package com.zhangju.xingquban.interestclassapp.bean.me;

import java.io.Serializable;

/**
 * Created by ydw on 2017/10/23.
 */
//LocationActivity 展示bean
public class LocationBean implements Serializable{
    private String address;
    private Double lat;
    private Double lng;
    private String areaId;
    private String aresName;
    private String cityName;
    private String cityCode;
    private String provinceId;
    private String provinceName;

    public LocationBean(String address, Double lat, Double lng) {
        this.address = address;
        this.lat = lat;
        this.lng = lng;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Double getLat() {
        return lat;
    }

    public void setLat(Double lat) {
        this.lat = lat;
    }

    public Double getLng() {
        return lng;
    }

    public void setLng(Double lng) {
        this.lng = lng;
    }

    public String getProvinceName() {
        return provinceName;
    }

    public void setProvinceName(String provinceName) {
        this.provinceName = provinceName;
    }

    public String getAresName() {
        return aresName;
    }

    public void setAresName(String aresName) {
        this.aresName = aresName;
    }

    public String getAreaId() {
        return areaId;
    }

    public void setAreaId(String areaId) {
        this.areaId = areaId;
    }

    public String getCityCode() {
        return cityCode;
    }

    public void setCityCode(String cityCode) {
        this.cityCode = cityCode;
    }

    public String getProvinceId() {
        return provinceId;
    }

    public void setProvinceId(String provinceId) {
        this.provinceId = provinceId;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }
}
