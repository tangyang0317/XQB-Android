package com.zhangju.xingquban.interestclassapp.refactor.me.bean;

/**
 * Created by sgfb on 2017/11/8.
 * 我的直播间中发布历史返回实体
 */
public class ResponsePublishHistory{
    public boolean isAddOp;
    public int seeCount;
    public int likesCount;
    public String id;
    public String orig_video_key;
    public String sdate;
    public LiveRecord liveRecord;
    public ChatUser chatUser;

    public class LiveRecord{
        public String roomPic;
        public String address;
        public String roomName;
    }
}