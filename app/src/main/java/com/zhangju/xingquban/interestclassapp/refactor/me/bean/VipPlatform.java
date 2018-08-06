package com.zhangju.xingquban.interestclassapp.refactor.me.bean;

import java.io.Serializable;

/**
 * Created by hqf on 2017/11/25.
 * vip商户信息平台bean
 */

public class VipPlatform implements Serializable{
    private String cityName;
    private String id;
    private String infoContent;
    private String title;
    private String teacherName;
    private String addUserTime;
    private Integer infoType;

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getInfoContent() {
        return infoContent;
    }

    public void setInfoContent(String infoContent) {
        this.infoContent = infoContent;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTeacherName() {
        return teacherName;
    }

    public void setTeacherName(String teacherName) {
        this.teacherName = teacherName;
    }

    public String getAddUserTime() {
        return addUserTime;
    }

    public void setAddUserTime(String addUserTime) {
        this.addUserTime = addUserTime;
    }

    public Integer getInfoType() {
        return infoType;
    }

    public void setInfoType(Integer infoType) {
        this.infoType = infoType;
    }
}
