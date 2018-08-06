package com.zhangju.xingquban.interestclassapp.refactor.me.bean;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by sgfb on 2017/11/21.
 * 视频课程返回实体
 */
public class ResponseCourseVideo implements Serializable{
    public int price;
    public String id;
    public String addUserTime;
    public String videoTitlePic;
    public String videoStr;
    public String title;
    @SerializedName("FilesName")
    public String fromFolder;
    public String comment;
    public String address;
    public String contactWay;
    public String videoFilesId;
    public String longitude,latitude;
    public String isCharge;
}
