package com.zhangju.xingquban.interestclassapp.refactor.me.bean;

/**
 * Created by sgfb on 2017/11/11.
 * 扫码返回
 */
public class ResponseScanCode{
    public boolean genQcode;
    public int scanQcodeStatus; //0没扫 1全扫 -1部分扫
    public int qcodeNum;
    public int isCommented;
    public int status; //0预订 待付款,1已支付,2有效,3完结,-1:取消,-2超时
    public int expireStatus; //0没有过期未使用,1已超时未使用,-1部分超时
    public double amount;
    public String payTime;
    public String firstScanTime;
    public String customerId;
    public String id;
    public String lastsCanTime;
    public String pdtNum;
    public String sno;
    public String phone;
    public String confirmData;
    public Object dlist;
}