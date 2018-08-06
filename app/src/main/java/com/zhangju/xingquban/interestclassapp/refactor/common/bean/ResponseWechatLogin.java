package com.zhangju.xingquban.interestclassapp.refactor.common.bean;

/**
 * Created by sgfb on 2017/11/10.
 * 微信登录得到code后请求openid返回
 */
public class ResponseWechatLogin{
    public int expires_in;
    public String access_token;
    public String refresh_token;
    public String openid;
    public String scope;
    public String unionid;

    //错误时返回的字段
    public String errcode;
    public String errmsg;
}