package com.zhangju.xingquban.interestclassapp.refactor.me.bean;

/**
 * Created by sgfb on 2017/11/13.
 * 申请成为老师/机构的提交事件
 */
public class EventApplyInMember{
    private Class mCla;

    public EventApplyInMember(Class mCla) {
        this.mCla = mCla;
    }

    public Class getmCla() {
        return mCla;
    }
}