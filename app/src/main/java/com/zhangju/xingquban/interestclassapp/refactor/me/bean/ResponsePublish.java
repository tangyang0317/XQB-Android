package com.zhangju.xingquban.interestclassapp.refactor.me.bean;

import com.google.gson.annotations.SerializedName;

/**
 * Created by sgfb on 2017/10/31.
 * 我发布的列表返回实体
 */
public class ResponsePublish{
    public int buyNumber;
    public int commentNum;
    public int collectionNum;
    public float price;
    public String id;
    public String titlePic;
    public String title;
    public String place;
    public String judgeTime;
    @SerializedName("FinalTime")
    public String date;
}