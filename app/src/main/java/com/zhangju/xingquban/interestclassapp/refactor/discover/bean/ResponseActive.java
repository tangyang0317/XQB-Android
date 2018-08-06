package com.zhangju.xingquban.interestclassapp.refactor.discover.bean;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Administrator on 2017/11/30.
 * 活动列表返回实体
 */
public class ResponseActive{
    public float price;
    public int voteChannelType; //投票通道1关闭 2开启
    public String title;
    public String sponsor;
    public String atime;
    @SerializedName("FinalTime")
    public String endTime;
    public String titlePic;
    public String place;
}