package com.zhangju.xingquban.interestclassapp.refactor.me.bean;

import java.io.Serializable;

/**
 * Created by hqf on 2017/11/25.
 * vip商户--生源推荐
 */

public class StudentIntroduce implements Serializable {
    private String cityName;
    private String content;
    private String title;
    private String catagoriesName;
    private String categoriesId;
    private String id;
    private String addUserTime;
    private Integer click;
    private Integer infoType;

    public Integer getInfoType() {
        return infoType;
    }

    public void setInfoType(Integer infoType) {
        this.infoType = infoType;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCatagoriesName() {
        return catagoriesName;
    }

    public void setCatagoriesName(String catagoriesName) {
        this.catagoriesName = catagoriesName;
    }

    public String getCategoriesId() {
        return categoriesId;
    }

    public void setCategoriesId(String categoriesId) {
        this.categoriesId = categoriesId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAddUserTime() {
        return addUserTime;
    }

    public void setAddUserTime(String addUserTime) {
        this.addUserTime = addUserTime;
    }

    public Integer getClick() {
        return click;
    }

    public void setClick(Integer click) {
        this.click = click;
    }
}
