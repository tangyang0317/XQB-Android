package com.zhangju.xingquban.interestclassapp.refactor.common.bean;

/**
 * Created by sgfb on 2017/11/8.
 * 刷新事件
 */
public class EventRefresh{
    private Class mTargetClass;

    public EventRefresh(Class mTargetClass) {
        this.mTargetClass = mTargetClass;
    }

    public Class getmTargetClass() {
        return mTargetClass;
    }
}
