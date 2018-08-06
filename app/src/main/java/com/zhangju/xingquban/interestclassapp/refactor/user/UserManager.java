package com.zhangju.xingquban.interestclassapp.refactor.user;

import android.support.annotation.NonNull;

import com.fastlib.app.EventObserver;
import com.fastlib.db.FastDatabase;
import com.fastlib.net.NetManager;
import com.fastlib.net.Request;
import com.zhangju.xingquban.interestclassapp.application.MyApp;
import com.zhangju.xingquban.interestclassapp.refactor.common.utils.ThirdPartyUtils;

/**
 * Created by sgfb on 2017/10/25.
 * 用户管理器.用户验证通过是登录状态但是并不代表是有用户数据的，因为用户数据的接口和登录接口分离
 */
public class UserManager{
    private static UserManager sOwer;
    private UserVerify mUserVerify;
    private User mUser;

    private UserManager(){
        mUserVerify = FastDatabase.getDefaultInstance(MyApp.instance).getFirst(UserVerify.class);
        if(mUserVerify!=null&&mUserVerify.isLogin)
            mUser=FastDatabase.getDefaultInstance(MyApp.instance).getFirst(User.class);
        if(mUser==null) mUser=new User(); //保持User不为null
    }

    public static synchronized UserManager getInstance(){
        if(sOwer==null) sOwer=new UserManager();
        return sOwer;
    }

    /**
     * 是否登入状态
     * @return true为登录，false未登录
     */
    public boolean isLogin(){
        return mUserVerify !=null&& mUserVerify.isLogin;
    }

    public User getUser(){
        try {
            return mUser==null?null:mUser.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 获取最后一次登入的账号
     * @return
     */
    public String getLastLogin(){
        return mUserVerify !=null? mUserVerify.loginName:"";
    }

    /**
     * 登入
     * @param verify
     */
    public void login(@NonNull UserVerify verify){
        mUserVerify =verify;
        mUserVerify.isLogin=true;
        NetManager.getInstance().setGlobalHead(Request.ExtraHeader.create(false,"X-CustomToken",verify.token));
        FastDatabase.getDefaultInstance(MyApp.instance).saveOrUpdate(mUserVerify);
        EventObserver.getInstance().sendEvent(MyApp.instance,new EventLogin(true));
    }

    /**
     * 登出
     */
    public void logout(){
        ThirdPartyUtils.getInstance(MyApp.instance).mTencent.logout(MyApp.instance);
        NetManager.getInstance().setGlobalHead(Request.ExtraHeader.create(false,"X-CustomeToken",""));
        if(mUserVerify !=null) mUserVerify.isLogin=false;
        mUser=new User();
        FastDatabase.getDefaultInstance(MyApp.instance).saveOrUpdate(mUserVerify);
        EventObserver.getInstance().sendEvent(MyApp.instance,new EventLogin(false));
    }

    /**
     * 更新用户信息
     * @param latestUser 最新用户信息
     */
    public void refreshUser(@NonNull User latestUser){
        mUser =latestUser;
        FastDatabase.getDefaultInstance(MyApp.instance).saveOrUpdate(mUser);
        EventObserver.getInstance().sendEvent(MyApp.instance,new EventUserDataChanged());
    }

    /**
     * 获取Token如果未登录返回null
     * @return 登录状态返回Token，未登录返回null
     */
    public String getToken(){
        return isLogin()?mUserVerify.token:null;
    }
}