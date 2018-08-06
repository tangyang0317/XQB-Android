package com.zhangju.xingquban.interestclassapp.refactor.me.bean;

import java.io.Serializable;

/**
 * Created by Administrator on 2017/11/25.
 * 账户绑定支付方式
 */
public class ResponseBoundAccount implements Serializable{
    public String id;
    public String username;
    public String account;
    public PayType paytype;

    public class PayType implements Serializable{
        public String id;
        public String name;
        public String keyname; //aipay or qqpay
    }
}