package com.zhangju.xingquban.interestclassapp.refactor.me.bean;

import java.util.List;

/**
 * Created by sgfb on 2017/11/6.
 * 我的资源
 */
public class ResponseResource{
    public int clickRate;
    public int commentCounts;
    public String id;
    public String title;
    public String addUserTime;
    public String types;
    public String titlePicture; //此字段依赖视频类型
    public List<Picture> pictureList;

    public class Picture{
        public String fileUrl;
    }
}