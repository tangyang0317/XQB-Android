package com.zhangju.xingquban.interestclassapp.config;

/**
 * Created by Administrator on 2016/11/3.
 */
public class Constant {
    //首页序号
    public static final int MAIN_HOME = 0;
    //附近圈序号
    public static final int MAIN_NEAR = 1;
    //直播应用序号
    public static final int MAIN_LIVE = 2;
    //发现碎片序号
    public static final int MAIN_FIND = 3;
    //我的碎片序号
    public static final int MAIN_ME = 4;


    //引导页首次登陆储存的key
    public static final String GUIDANCE_FIRST_KEY = "first";


    //聊天界面
    //我要提问页面跳转到相机的跳转码
    public static final int CHAT_TAKE_CAMERA = 9;
    //我要提问页面跳转到剪裁图片的跳转码
    public static final int CHAT_CROP_PHOTO = 10;
    //我要提问页面跳转到相册的跳转码
    public static final int CHAT_TAKE_ALBUM = 12;

    //创建交友圈
    //从创建农友圈跳转到相机的跳转码
    public static final int FOUNDGROUP_TAKE_CAMERA = 13;
    //创建农友圈转到剪裁图片的跳转码
    public static final int FOUNDGROUP_CROP_PHOTO = 14;
    //创建农友圈跳转到相册的跳转码
    public static final int FOUNDGROUP_TAKE_ALBUM = 15;


    //注册页面跳转到选择页面时，选取所在地区的类型号
    public static final int REGISTER_OPTIONLIST_DISTRICT = 0;
    //注册页面跳转到选择页面时，选取用户类型的类型号
    public static final int REGISTER_OPTIONLIST_TYPE = 1;
    //选择页面跳回注册页面时，回传值的Key
    public static final String OPTIONLIST_REGISTER_RESULT = "result";
    public static final String REGISTER_OPTIONLIST_REQUEST_TYPEP = "UserType";
    public static final String REGISTER_OPTIONLIST_REQUEST_DETRICT = "userDistrict";


    //发布通知页面跳转到选择页面时，选取行政区域的类型号
    public static final int RELEASE_OPTIONLIST_REGION = 2;
    //发布通知页面跳转到选择页面时，选取管理机构的类型号
    public static final int RELEASE_OPTIONLIST_ORGANIZATION = 3;
    //选择页面跳回发布通知页面时，回传值的Key
    public static final String OPTIONLIST_RELEASE_RESULT = "result";


    //我要提问页面跳转到选择页面时，选取问题类型的类型号
    public static final int MYQUESTION_OPTIONLIST_PROBLEMTYPE = 5;
    //我要提问页面跳转到相机的类型号
    public static final int MYQUESTION_TAKE_PHOTO = 6;
    //我要提问页面跳转到剪裁图片的类型号
    public static final int MYQUESTION_CROP_PHOTO = 7;
    //我要提问页面跳转到相册的类型号
    public static final int MYQUESTION_CROP_ALBUM = 8;
    //选择页面跳回我要提问页面时，回传值的Key
    public static final String OPTIONLIST_MYQUESTION_RESULT = "result";
    public static final String MYQUESTION_OPTIONLIST_REQUEST_PROBLEMTYPE = "problemType";


    //植物保护页面病害按钮的序列号
    public static final int PLANT_PROTECT_RADIOBUTTON_DISEASE = 0;
    //植物保护页面虫害按钮的序列号
    public static final int PLANT_PROTECT_RADIOBUTTON_PEST = 1;
    //植物保护页面草害按钮的序列号
    public static final int PLANT_PROTECT_RADIOBUTTON_WEEDS = 2;


    //传入种类搜索的Key
    public static final String VARSEARCH_VARRESULT_RESULT = "result";


    //跳转到选在页面时传值的key
    public static final String OPTIONLIST_REQUEST_CODE = "request";


    public static final String HOME_GOTO_TITLE = "title";


    //品牌种业，相关品种
    public static final String COMPANY_SEED_NAME = "品种名称：";
    public static final String COMPANY_SEED_VARIETY = "作物种类：";
    public static final String COMPANY_SEED_ATTRIBUTE = "作物属性：";
    public static final String COMPANY_SEED_INFO = "审定品种：";
}
