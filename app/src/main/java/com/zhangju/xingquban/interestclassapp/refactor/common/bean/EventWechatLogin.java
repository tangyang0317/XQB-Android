package com.zhangju.xingquban.interestclassapp.refactor.common.bean;

/**
 * Created by sgfb on 2017/11/10.
 * 微信授权登录成功后回调事件
 */
public class EventWechatLogin{
    private String mOpenid;
    private String access_token;

    public EventWechatLogin(String mOpenid, String access_token) {
        this.mOpenid = mOpenid;
        this.access_token = access_token;
    }

    public String getmOpenid() {
        return mOpenid;
    }

    public String getAccess_token() {
        return access_token;
    }
}