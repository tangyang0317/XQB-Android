package com.zhangju.xingquban.interestclassapp.refactor.me.bean;

/**
 * Created by sgfb on 2017/11/2.
 * 发布活动数据更新事件
 */
public class EventPublishDataEdited{
    private PublishActiveInfo mInfo;

    public EventPublishDataEdited(PublishActiveInfo mInfo) {
        this.mInfo = mInfo;
    }

    public PublishActiveInfo getmInfo() {
        return mInfo;
    }
}