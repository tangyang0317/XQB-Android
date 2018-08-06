package com.zhangju.xingquban.interestclassapp.ui.fragment.find.SeekResource;

/**
 * Created by ydw on 2017/11/11.
 */

public interface ResInterface {
    /**
     * 音频列表
     */

    String POST_RES_AUDIO_LIST = "/resources/ls.json";

    /**
     * 资源详情
     */
    String POST_RES_COMMENT = "/comment/ls.json";

    /**
     * 资源详情顶部
     */
    String POST_RES_TOP = "/resources/ls.json";
    /***
     * 点赞
     */
    String POST_RES_ISPRISE = "/links/add.json";
    /**
     * 收藏
     */
    String POST_RES_ISCOLLECT = "/collection/add.json";

    /**
     * 评论
     */
    String POST_RES_MESSAGE = "/comment/add.json";
    /**
     * 支付类型获取
     */
    String POST_RES_PAY_TYPE = "/paytype/ls.json";
    /**
     * 支付订单id获取
     */
    String POST_RES_PAY_ID = "/orders/add.json";
    /**
     * 直播付费
     */
    String POST_LIVE_PAY = "/payv2/rooms.json";


}
