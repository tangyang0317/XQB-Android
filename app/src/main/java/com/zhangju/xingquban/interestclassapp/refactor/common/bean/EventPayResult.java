package com.zhangju.xingquban.interestclassapp.refactor.common.bean;

/**
 * Created by sgfb on 2017/11/11.
 * 支付结果广播
 */
public class EventPayResult{
    private boolean isSuccess;
    private String mPayType;

    public EventPayResult(boolean isSuccess, String mPayType) {
        this.isSuccess = isSuccess;
        this.mPayType = mPayType;
    }

    public boolean isSuccess() {
        return isSuccess;
    }

    public String getmPayType() {
        return mPayType;
    }
}