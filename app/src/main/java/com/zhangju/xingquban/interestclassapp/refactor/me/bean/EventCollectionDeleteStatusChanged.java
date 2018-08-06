package com.zhangju.xingquban.interestclassapp.refactor.me.bean;

/**
 * Created by sgfb on 2017/10/31.
 * 收藏是否显示删除事件
 */
public class EventCollectionDeleteStatusChanged{
    private boolean isShowDelete;

    public EventCollectionDeleteStatusChanged(boolean isShowDelete) {
        this.isShowDelete = isShowDelete;
    }

    public boolean isShowDelete() {
        return isShowDelete;
    }
}