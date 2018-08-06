package com.zhangju.xingquban.interestclassapp.refactor.user;

/**
 * Created by sgfb on 2017/10/25.
 * 登录状态变化时发送的事件
 */
public class EventLogin{
    private boolean isLogin; //true为登录，false登出

    public EventLogin(boolean isLogin) {
        this.isLogin = isLogin;
    }

    public boolean isLogin() {
        return isLogin;
    }
}