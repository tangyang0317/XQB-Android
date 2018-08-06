package com.zhangju.xingquban.interestclassapp.util;


import com.zhangju.xingquban.interestclassapp.application.MyApp;

/**
 * Created by zsl on 2017/6/21.
 */

public class BaseUrl {

    /*网络测试服务器*/
//    public static final String url = "http://test.xqban.com:80/";
    /*公司服务器*/
    public static final String url = MyApp.URL+"/";
    /*柴磊磊服务器*/
//    public static final String url = "http://myqqy.vicp.io/std/";
//    public static final String url = "http://myqqy.vicp.io/std/";

    /*
       开通会员
         */
    public static final String vip = "http://xqb.bicikeji.com:8080/std/admnxzcmr/members/pay.json";
//    static {
//        if (AppConfig.DEBUG) {
//            //            url = "http://192.168.1.9:80/stdv2/";
////            url = "http://test.xqban.com/";
//            url = "http://m.xqban.com:80/";
//            //            url = "http://192.168.1.38:8080/std/";
//            //            url = "http://x14850701c.iask.in/atm/";
//        } else {
//            url = "http://m.xqban.com:80/";
//        }
//    }

    public static final String URL_CAPTCHA = MyApp.URL + "admnxzcmr/customer/sendRegCode.json";//个人中心验证码
}
