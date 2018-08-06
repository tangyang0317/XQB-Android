package com.zhangju.xingquban.interestclassapp.refactor.me.bean;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by sgfb on 2017/11/15.
 * 活动发布列表（老师/机构端）
 */
public class ResponseActivePublished implements Serializable{
    @SerializedName("bondNumber")
    public int applyCount;
    @SerializedName("collectionCounts")
    public int collectionCount;
    public int status;
    public double price;
    @SerializedName("titlePic")
    public String cover;
    public String title;
    @SerializedName("place")
    public String location;
    @SerializedName("atime")
    public String date;
    public String id;
}