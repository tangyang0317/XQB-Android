package com.zhangju.xingquban.interestclassapp.refactor.me.bean;

/**
 * Created by sgfb on 2017/11/22.
 * 提交事务事件推动
 */
public class EventCommit{
    private Class mCla;

    public EventCommit(Class mCla) {
        this.mCla = mCla;
    }

    public Class getmCla() {
        return mCla;
    }
}