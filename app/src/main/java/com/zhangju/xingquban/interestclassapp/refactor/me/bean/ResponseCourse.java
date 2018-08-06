package com.zhangju.xingquban.interestclassapp.refactor.me.bean;

import com.google.gson.annotations.SerializedName;

/**
 * Created by sgfb on 2017/11/17.
 * 课程详情
 */
public class ResponseCourse{
    public boolean isCantry;
    @SerializedName("resId")
    public String teacherName;
    public String name;
    public String picture;
    public String courses;
    public String timelength;
    public String price;
    public String vipPrice;
    public String allows;
    public String lessonDate;
    public String descript;
    public String summary;
    public String method; //授课方式
    public String region;
    public String catagoryName; //授课科目
    public String categoriesId;
    public String lng,lat;
    public String areasId,areasName;
    public String provinceId,provinceName;
    public String cityId,cityName;
}
