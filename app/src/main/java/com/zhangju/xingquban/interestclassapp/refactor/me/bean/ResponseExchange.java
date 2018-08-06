package com.zhangju.xingquban.interestclassapp.refactor.me.bean;

/**
 * Created by sgfb on 2017/11/11.
 * 充值，开通会员调支付返回
 */
public class ResponseExchange {
    //请求微信支付返回字段
    public String appid;
    public String noncestr;
    public String packageValue;
    public String partnerid;
    public String prepayid;
    public String sign;
    public String sno;
    public String timestamp;

    //请求支付宝支付返回字段
    public String amount;
    public String payType;
}