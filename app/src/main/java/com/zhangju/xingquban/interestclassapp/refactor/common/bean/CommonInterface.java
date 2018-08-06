package com.zhangju.xingquban.interestclassapp.refactor.common.bean;

import com.zhangju.xingquban.interestclassapp.application.MyApp;
import com.zhangju.xingquban.interestclassapp.bean.HomeCityIdBaen;
import com.zhangju.xingquban.interestclassapp.refactor.me.bean.ResponseUploadImage;

/**
 * Created by sgfb on 2017/10/27.
 * 一些通用接口
 */
public interface CommonInterface{
    /**
     * 上传图像
     * in:files
     * out:[{@link ResponseUploadImage}]
     */
    String POST_UPLOAD_IMAGE="/customer/upload.json";

    /**
     * 省份列表
     * in:分页
     * out:[{@link ResponseProvince}]
     */
    String POST_PROVICE_LIST="/provinces/ls.json";

    /**
     * 城市列表
     * in:id,分页
     * out:[{@link ResponseCity}]
     */
    String POST_CITY_LIST="/provinces/lsAll.json";

    /**
     * 根据城市名获取城市ID
     * in:name
     * out:{@link HomeCityIdBaen}
     */
    String POST_CITY_ID_BY_NAME="/cities/ls.json";

    /**
     * 入驻兴趣班协议链接
     */
    String URL_TO_MEMBER_AGREEMENT="http://mob.xqban.com/xy/Settled.html";

    /**
     * 用户充值协议
     */
    String URL_RECHARGE_AGREEMENT ="http://www.xqban.com/xy/recharge.html";

    /**
     * 用户兑换协议
     */
    String URL_EXCHANGE_AGREEMENT="http://www.xqban.com/xy/exchange.html";
    /**
     * 找比赛
     */
    String URL_FIND_COMPETITION="https://m.xqban.com/static/wechat/competition/#/activity/list?token=";

    /**
     * 关于我们
     */
    String URL_ABOUT_US= MyApp.URL+"/admnxzcmr/chgpic/aboutus?sysVersion=";
}