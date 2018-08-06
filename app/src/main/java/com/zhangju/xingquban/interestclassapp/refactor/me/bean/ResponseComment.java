package com.zhangju.xingquban.interestclassapp.refactor.me.bean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by sgfb on 2017/10/27.
 * 评论接口返回实体
 */
public class ResponseComment implements Serializable{
    public boolean isThumb;
    public int levels;
    public int thumbNum;
    public int commentCommentsNum;
    public int display;
    public String customerId;
    public String customerPicture;
    public String customerName;
    public String id;
    public String summary;
    public String addUserTime;
    public List<String> picList;
    public List<ResponseOrder.Lesson> lessons;
    public List<ThumbUser> thumbComments;
    public List<SubComment> commentComments;

    public class SubComment implements Serializable{
        public String id;
        public String customerName;
        public String summary;
        public String customerPicture;
    }

    public class ThumbUser implements Serializable{
        public String id;
        public String customerPicture;
    }
}