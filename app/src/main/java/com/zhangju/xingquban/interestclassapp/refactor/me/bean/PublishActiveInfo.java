package com.zhangju.xingquban.interestclassapp.refactor.me.bean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by sgfb on 2017/11/1.
 * 发布活动信息
 */
public class PublishActiveInfo implements Serializable{
    public BaseInfo baseInfo;
    public ActiveFeatured featured;
    public ActiveSchedules schedules;
    public ActiveNotes notes;

    /**
     * 基本信息
     */
    public static class BaseInfo implements Serializable{
        public int voteStatus; //投票通道 0未开启 2开启
        public int voteType; //投票类型 1个人简介，2-视频作品，3-图片作品，4-文本作品
        public float vipPrice;
        public String longitude="120.2131";
        public String latitude="30.291";
        public String statement;
        public String place;
        public String title;
        public String titlePic; //图片本地字段
        public String titlePicUrl; //图片服务器字段
        public String host;
        public String startDate;
        public String endDate;
        public String categoryName;
        public String categoryId;
        public SignupSettings signupSettings=new SignupSettings();

        /**
         * 报名设置
         */
        public static class SignupSettings implements Serializable{
            public int ticketCount;
            public int signUpType; //参与方式 0无需报名 2在线报名
            public int needName; //0不需要参加人姓名 2需要参加人姓名
            public int needPhone; //0不需要参加人手机 2需要参加人手机
            public int needSex; //0不需要参加人性别 2需要参加人性别
            public int needAge; //0不需要参加人年龄 2需要参加人年龄
            public int freeTicketCount;
            public int buyLimit; //购买限制 默认0不限购
            public int priceType; //0免费 2收费
            public float price;
        }
    }

    /**
     * 活动特色
     */
    public static class ActiveFeatured implements Serializable{
        public List<PublishActiveFeature> featured; //活动特色
    }

    /**
     * 活动行程
     */
    public static class ActiveSchedules implements Serializable{
        public List<PublishActiveTrip> schedules; //活动行程
    }

    /**
     * 活动须知
     */
    public static class ActiveNotes implements Serializable{
        public String notes; //活动须知
    }
}