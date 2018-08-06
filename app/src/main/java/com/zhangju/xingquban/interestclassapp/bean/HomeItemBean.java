package com.zhangju.xingquban.interestclassapp.bean;

/**
 * Created by Administrator on 2017/7/2.
 */

public class HomeItemBean {
    String HomeItemString;
    int HomeItemInt;

    public HomeItemBean(String homeItemString, int homeItemInt) {
        this.HomeItemString = homeItemString;
        this.HomeItemInt = homeItemInt;
    }

    public String getHomeItemString() {
        return HomeItemString;
    }

    public void setHomeItemString(String homeItemString) {
        HomeItemString = homeItemString;
    }

    public int getHomeItemInt() {
        return HomeItemInt;
    }

    public void setHomeItemInt(int homeItemInt) {
        HomeItemInt = homeItemInt;
    }
}
