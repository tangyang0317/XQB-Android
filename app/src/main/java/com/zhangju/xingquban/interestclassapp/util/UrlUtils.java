package com.zhangju.xingquban.interestclassapp.util;


import com.zhangju.xingquban.interestclassapp.application.MyApp;

public class UrlUtils {


    /**
     * 图片 视频 地址
     */
    public static String getRealUrl(String url) {
        if (url != null && url.startsWith("http://")) {
            return url;
        } else {
            return URL_ZHANGJU + url;
        }
    }

    //    http://192.168.1.9/stdv2/admnxzcmr/members/ls.json;jsessionid=

    //url下载地址

    //	public static final String url1 = "http://192.168.1.9:80/stdv2/";
    //	public static final String url3 = "http://x14850701c.iask.in/std/";
//    public static final String url="http://xqb.bicikeji.com:8080/std/";
    public static final String url= MyApp.URL+"/";




    //    public static final String URL_ABOUTUS            = "http://www.xqban.com/xy/about_us.html";// 关于我们
    public static final String URL_ABOUTUS            = "http://test.xqban.com/admnxzcmr/chgpic/aboutus?sysVersion=";// 关于我们
    public static final String URL_REGISTER_AGREEMENT = "http://www.xqban.com/xy/user_agreement.html";// 注册协议
    public static final String URL_LIVE_AGREEMENT     = "http://www.xqban.com/xy/broadcast.html";// 直播协议
    public static final String URL_ENTER_AGREEMENT    = "http://mob.xqban.com/xy/Settled.html";// 入驻协议


    public static final String URL_BANNER       = url + "admnxzcmr/chgpic/ls.json";//banners
    public static final String URL_CATAGORIES   = url + "admnxzcmr/catagories/ls.json";//发现课程分类
    public static final String URL_RESOURCES    = url + "admnxzcmr/resources/ls.json";//发现资源
    public static final String URL_REGISTER     = url + "admnxzcmr/customer/register.json";//个人中心注册
    public static final String URL_CAPTCHA      = url + "admnxzcmr/customer/sendRegCode.json";//个人中心验证码
    public static final String URL_FORTPASSWORD = url + "admnxzcmr/customer/forgetPwd.json";//个人中心验证码
    public static final String URL_HOT          = url + "admnxzcmr/resources/hots.json";//资源热门
    public static final String URL_LIKE         = url + "admnxzcmr/links/add.json";//点赞
    public static final String URL_LOGIN        = url + "admnxzcmr/customer/login.json";//个人中心登录
    public static final String URL_CUSTOMER     = url + "admnxzcmr/customer/ls.json";//用户资料
    public static final String URL_EDIT         = url + "admnxzcmr/customer/add.json";//修改资料
    public static final String URL_ZHANGJU      = url + "";
    public static final String URL_PROVINCE     = url + "admnxzcmr/provinces/ls.json";//省
    public static final String URL_CITYLIST     = url + "admnxzcmr/provinces/pinyinlist.json"; // 区域列表
    public static final String URL_CITYLIST_HOT = url + "admnxzcmr/hotcity/ls.json"; // 热门区域列表


    public static final String URL_GIS = url + "admnxzcmr/gis/ls.json";//GIS

    public static final String URL_CITIE             = url + "admnxzcmr/cities/ls.json";//市
    public static final String URL_AREA              = url + "admnxzcmr/areas/ls.json";//区
    public static final String URL_DEGREE            = url + "admnxzcmr/degree/ls.json";//用户级别 老师or机构
    public static final String URL_TEACH             = url + "admnxzcmr/teacher/ls.json";//找老师找机构
    public static final String URL_EDITTEACH         = url + "admnxzcmr/teacher/add.json";//老师机构修改
    public static final String URL_UPLOAD            = url + "admnxzcmr/customer/upload.json";//上传文件 头像
    public static final String URL_EXEUPLOAD         = url + "admnxzcmr/exe/upload.json";//上传文件 优秀作品
    public static final String URL_SHOWUPLOAD        = url + "admnxzcmr/show/upload.json";//上传文件 教学展示
    public static final String URL_ENVUPLOAD         = url + "admnxzcmr/env/upload.json";//上传文件 教学环境
    public static final String URL_LESSONUPLOAD      = url + "admnxzcmr/env/upload.json";//上传文件 课程
    public static final String URL_COMMENT           = url + "admnxzcmr/comment/ls.json";//获取评论
    public static final String URL_ADD_COMMENT       = url + "admnxzcmr/comment/add.json";//添加评论
    public static final String URL_EXES              = url + "admnxzcmr/exe/ls.json";//优秀作品
    public static final String URL_ADDEXES           = url + "admnxzcmr/exe/add.json";//添加优秀作品
    public static final String URL_DELEXES           = url + "admnxzcmr/exe/dels.json";//删除优秀作品
    public static final String URL_ENVIRONMENT       = url + "admnxzcmr/env/ls.json";//教学环境
    public static final String URL_ADDENVIRONMENT    = url + "admnxzcmr/env/add.json";//添加教学环境
    public static final String URL_DELENVIRONMENT    = url + "admnxzcmr/env/dels.json";//删除教学环境
    public static final String URL_SHOWS             = url + "admnxzcmr/show/ls.json";//教学展示
    public static final String URL_ADDSHOWS          = url + "admnxzcmr/show/add.json";//添加教学展示
    public static final String URL_DELSHOWS          = url + "admnxzcmr/show/dels.json";//删除教学展示
    public static final String URL_RES               = url + "admnxzcmr/res/ls.json";//师资力量
    public static final String URL_NEARBY            = url + "admnxzcmr/customer/search.json";//附近的人
    public static final String URL_APPLYFOR          = url + "admnxzcmr/chgDegree/add.json";//申请老师和机构
    public static final String URL_IDENTITY          = url + "admnxzcmr/realnameAuth/add.json";//身份认证
    public static final String URL_QUALIFICATION     = url + "admnxzcmr/qcAuth/add.json";//资格认证
    public static final String URL_EDUCATION         = url + "admnxzcmr/degreeAuth/add.json";//学历认证
    public static final String URL_TEACHER           = url + "admnxzcmr/tqcAuth/add.json";//教师认证
    public static final String URL_RESET_PASSWORD    = url + "admnxzcmr/customer/resetPwd.json";//重置密码
    public static final String URL_LESSON            = url + "admnxzcmr/lesson/ls.json";//课程
    public static final String URL_DELLESSON         = url + "admnxzcmr/lesson/del.json";//课程删除
    public static final String URL_ADDORDER          = url + "admnxzcmr/orders/add.json";//下单
    public static final String URL_DELORDER          = url + "admnxzcmr/orders/del.json";//取消订单
    public static final String URL_MY_ORDER          = url + "admnxzcmr/orders/lsv4.json";// 订单列表
    public static final String URL_MY_ORDER_LIST     = url + "admnxzcmr/orders/lsv5.json"; // 最新款订单列表
    public static final String URL_MY_ORDER_QCODE    = url + "admnxzcmr/orders/qcode.json"; // 券码列表
    public static final String URL_ORDER_SCAN_QRCODE = url + "admnxzcmr/orders/scan.json"; // 扫码


    public static final String URL_QUERYORDER         = url + "admnxzcmr/orders/ls.json";//学生查询
    public static final String URL_QUERYORDER_TEACHER = url + "admnxzcmr/orders/ls3.json";//老师机构查询
    public static final String URL_FINDCOMMENT        = url + "admnxzcmr/teacherComment/ls.json";//机构老师评论查询
    public static final String URL_ADDCOMMENT         = url + "admnxzcmr/teacherComment/add.json";//机构老师评论添加
    public static final String URL_COURSE_ADDCOMMENT  = url + "admnxzcmr/lessonComment/add.json";//课程评论添加
    public static final String URL_COURSE_FINDCOMMENT = url + "admnxzcmr/lessonComment/ls.json";//课程评论评论查询
    public static final String URL_MY_COURSE          = url + "admnxzcmr/ordersDetail/ls3.json";//教师 与学生的 课程列表
    public static final String URL_PAY                = url + "admnxzcmr/orders/pay.json";//支付
    public static final String URL_STARTTEACH         = url + "admnxzcmr/lesson/start.json";//开始授课
    public static final String URL_ENDTEACH           = url + "admnxzcmr/lesson/end.json";//结束授课
    public static final String URL_FEEDBACK           = url + "admnxzcmr/feedback/add.json";//反馈

    public static final String URL_WALLET            = url + "admnxzcmr/customer/money.json";//钱包
    public static final String URL_WALLET2           = url + "admnxzcmr/customer/money2.json";//新版钱包
    public static final String URL_WALLET_RECORD     = url + "admnxzcmr/stdLedger/lsPhone.json";//交易记录
    public static final String URL_WALLET_MYBIND     = url + "admnxzcmr/coinCard/ls.json";//已绑定的银行卡
    public static final String URL_WALLET_MYBIND_ADD = url + "admnxzcmr/coinCard/add.json";//绑定银行卡
    public static final String URL_WALLET_MYBIND_DEL = url + "admnxzcmr/coinCard/del.json";//解绑指定的银行卡
    public static final String URL_WALLET_SET_PWD    = url + "admnxzcmr/stdcoin/pwd.json";  // 设置支付密码
    public static final String URL_WALLET_DRAWCASH   = url + "admnxzcmr/drawCash/add.json"; // 申请提现
    public static final String URL_WALLET_TOPUP      = url + "admnxzcmr/payv2/topup.json"; // 申请提现
    public static final String URL_WALLET_CONVERT    = url + "admnxzcmr/stdchg/add.json";   // 币种兑换


    public static final String URL_SIGN                = url + "admnxzcmr/customer/sign.json";//签到
    public static final String URL_ADDCOLLECT          = url + "admnxzcmr/collection/add.json";//添加收藏
    public static final String URL_MYCOLLECT           = url + "admnxzcmr/collection/ls.json";//我的收藏
    public static final String URL_DELCOLLECT          = url + "admnxzcmr/collection/del.json";//我的收藏删除
    public static final String URL_MYCOMMENT           = url + "admnxzcmr/teacherComment/ls3.json";//我的评论
    public static final String URL_STUDENT             = url + "admnxzcmr/customer/get.json";//学生
    public static final String URL_ACTIVE              = url + "admnxzcmr/activity/ls.json";//活动
    public static final String URL_INPOUR              = url + "admnxzcmr/deposit/add.json";//充值
    //    public static final String URL_INPOUR= url+"admnxzcmr/members/pay.json";//充值   后台测试
    public static final String URL_MESSAGE             = url + "admnxzcmr/push/ls.json";//消息
    public static final String URL_CITYANDAREA         = url + "admnxzcmr/provinces/lsAll.json";//传入省份id查找城市 和区域
    public static final String URL_ADDLESSON           = url + "admnxzcmr/lesson/add.json";//课程
    public static final String URL_ADDPLAN             = url + "admnxzcmr/plan2/add.json";//老师作息添加
    public static final String URL_PLAN                = url + "admnxzcmr/plan2/ls.json";//老师作息
    public static final String URL_CHANGE_ACCOUNT      = url + "admnxzcmr/customer/reviseAccount.json";//修改手机号码
    public static final String URL_UPDATE              = url + "admnxzcmr/version/topReleaseVersion.json";//版本更新
    public static final String URL_THIRD_LOGIN         = url + "admnxzcmr/customer/thirdLog.json";//第三方登录
    public static final String URL_BIND                = url + "admnxzcmr/customer/bindingphone.json";//绑定手机
    public static final String URL_SET_PASSWORD        = url + "admnxzcmr/customer/setpwd.json";//设置
    public static final String URL_SHOW                = url + "admnxzcmr/resources/share";//分享
    public static final String URL_SHOWACTIVITY        = url + "admnxzcmr/activity/share";//活动分享
    public static final String URL_SHOWALESSON         = url + "admnxzcmr/lesson/share";//课程分享
    public static final String URL_INFORMATION         = url + "admnxzcmr/ipm/index";//信息平台
    public static final String URL_INFORMATION_RELEASE = url + "admnxzcmr/ipm/Release";//信息平台
    public static final String URL_SHAREH5             = url + "admnxzcmr/appversion/ls";//有奖分享的url
    public static final String URL_SHAREH_ADD          = url + "admnxzcmr/appversion/ls";//分享成功后通知服务器

    public static final String URL_LOGO            = "http://my.xqban.com/rs/app/images/down_logo.png";//logo图片网络地址
    public static final String URL_SIGNIN          = url + "admnxzcmr/customer/sign.json";//登录自动签到
    public static final String URL_VIP             = url + "admnxzcmr/members/ls.json";// 会员信息
    public static final String URL_VIP_PAY         = url + "admnxzcmr/members/pay.json"; // 支付并开通会员
    public static final String URL_COUPONS_ADD     = url + "admnxzcmr/wcpn/add.json";   //  领取优惠券
    public static final String URL_COUPONS_LIST    = url + "admnxzcmr/uodetail/ls.json";   // 钱包页面内的优惠券列表
    public static final String URL_COUPONS_DETAILS = url + "admnxzcmr/couponusecontext/ls.json";  //优惠券详情
    public static final String URL_VIP_PAYCALL     = url + "admnxzcmr/payv2/paycall.json";

    public static final String URL_VIP_PAY_TYPE    = url + "admnxzcmr/payv2/productlist.json";// 会员信息
    public static final String URL_LIVELIST        = url + "admnxzcmr/liveRecord/liveOrVdo.json";// 直播间列表
    public static final String URL_LIVELIST_NEW = url + "admnxzcmr/rooms/ls.json";// 直播间最新列表
    public static final String URL_LIVELIST_ATTENTION = url + "admnxzcmr/liveRecord/like.json";// 直播间关注列表
    public static final String URL_LIVESTART       = url + "admnxzcmr/rooms/add.json";// 发起直播
    public static final String URL_LIVECLOSE       = url + "admnxzcmr/rooms/exit.json"; // 关闭直播间
    public static final String URL_INITROOM        = url + "admnxzcmr/rooms/see.json"; // 进入直播间
    public static final String URL_PAYTYPE         = url + "admnxzcmr/paytype/ls.json"; // 获取支付类型
    public static final String URL_PAYTV2          = url + "admnxzcmr/payv2/rooms.json"; // 付费房间直播
    public static final String URL_LIVESHARE       = url + "admnxzcmr/rooms/share"; // 付费房间直播
    public static final String URL_LIVE_GIFT_LIST  = url + "admnxzcmr/gift/ls.json"; // 直播礼物列表
    public static final String URL_LIVE_GIFT_SEND  = url + "admnxzcmr/sendGift/add.json"; // 发礼物
    public static final String POST_RECORD_LIKE = url + "admnxzcmr/upvote/add.json";//喜欢的收藏
    public static final String POST_QUESTION_COMMIT = url + "admnxzcmr/myQuestion/add.json";//喜欢的收藏
    // 个人添加黑名单
    public static final String URL_MYBLACK_ADD        = url + "admnxzcmr/chatGroupList/add2.json";
    //qinabao                                                   /customer/money2.json
    public static final String URL_MYBLACK_WALET        = url + "admnxzcmr/customer/money2.json";
    // 添加直播间场控
    public static final String URL_CHATROOMMANAGE_ADD = url + "admnxzcmr/chatroomRoles/add.json";
    public static final String URL_CHATROOMMANAGE     = url + "admnxzcmr/roomRoles/ls.json";
    // 关注/取消关注
    public static final String URL_FOLLOW_ADD         = url + "admnxzcmr/roomFollows/add.json";
    // 获取我的关注列表
    public static final String URL_GET_FOLLOW         = url + "admnxzcmr/chatUser/get.json";

    public static final String URL_LAUNCH_PIC = url + "admnxzcmr/chgpic/ls.json";   // 闪屏页广告

    // 直播间默认标题图片
    public static final String URL_LIVEPIC = "http://video.xqban.com/RegTeacher/2016-10-12/1476253938187_img_4597.jpg";
    //主页下面
    public static final String URL_HOME_BOTTOM =url+"admnxzcmr/teacher/ls.json";

    //广告列表
    public static final String URL_ADVERT_LIST = url + "/admnxzcmr/advert/list.json";

    //信息平台
    public static final String URL_MESSAGE_PLATFORM =url+"admnxzcmr/ipm/add.json";
    /**
     * 生源平台
     */
   public static final String POST_STUDENT_INTRODUCE =url+"stdResource/ls.json";

}
