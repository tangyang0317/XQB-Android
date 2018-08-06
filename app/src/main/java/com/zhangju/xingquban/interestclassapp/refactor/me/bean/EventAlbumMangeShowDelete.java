package com.zhangju.xingquban.interestclassapp.refactor.me.bean;

/**
 * Created by sgfb on 2017/11/6.
 * 机构管理相册管理显示和隐藏删除
 */
public class EventAlbumMangeShowDelete{
    private boolean deleteFlag;

    public EventAlbumMangeShowDelete(boolean deleteFlag) {
        this.deleteFlag = deleteFlag;
    }

    public boolean isDeleteFlag() {
        return deleteFlag;
    }
}