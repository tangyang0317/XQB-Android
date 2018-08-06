package com.zhangju.xingquban.interestclassapp.refactor.me.bean;

/**
 * Created by sgfb on 2017/11/8.
 * 我的直播间一级界面返回实体
 */
public class ResponseMyLiveRoom{
    public int gender;
    public String name;
    public String icon;
    public String ex;

    public class Extra{
        public int fllows;
        public int fans;
        public int likes;
        public String id;
        public String give;
    }
}