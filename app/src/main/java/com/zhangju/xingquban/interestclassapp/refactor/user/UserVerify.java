package com.zhangju.xingquban.interestclassapp.refactor.user;

import com.fastlib.annotation.Database;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Administrator on 2017/10/25.
 * 用户验证实体
 */
public class UserVerify{
    @Database(keyPrimary = true)
    public final int dbId=1;

    public boolean isLogin;
    public String loginName; //登录账号
    //以上是数据库字段

    @SerializedName("customerId")
    public long id;
    public String token;
    @SerializedName("bindingphone")
    public boolean phone;
}