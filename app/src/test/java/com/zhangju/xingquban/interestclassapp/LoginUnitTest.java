package com.zhangju.xingquban.interestclassapp;

import android.content.Intent;

import com.fastlib.net.Request;
import com.zhangju.xingquban.R;
import com.zhangju.xingquban.interestclassapp.app.AppRobolectricTestRunner;
import com.zhangju.xingquban.interestclassapp.refactor.me.activity.ForgotPasswordActivity;
import com.zhangju.xingquban.interestclassapp.refactor.me.activity.LoginActivity;
import com.zhangju.xingquban.interestclassapp.refactor.me.activity.LoginByOtherActivity;
import com.zhangju.xingquban.interestclassapp.refactor.me.activity.RegisterActivity;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.shadows.ShadowApplication;
import org.robolectric.shadows.ShadowLog;

import static junit.framework.Assert.assertNotNull;
import static junit.framework.TestCase.assertEquals;

/**
 * Created by Administrator on 2017/12/21.
 * 登录模块单元测试
 */
@RunWith(AppRobolectricTestRunner.class)
public class LoginUnitTest{
    private LoginActivity activity;

    @Before
    public void setup(){
        ShadowLog.stream=System.out;
        activity= Robolectric.setupActivity(LoginActivity.class);
    }

    /**
     * 测试跳转注册界面
     */
    @Test
    public void clickRegister_toRegister(){
        activity.findViewById(R.id.register).performClick();

        Intent expectedIntent=new Intent(activity, RegisterActivity.class);
        Intent actual= ShadowApplication.getInstance().getNextStartedActivity();
        assertEquals(expectedIntent.getComponent(),actual.getComponent());
    }

    /**
     * 测试跳转忘记密码
     */
    @Test
    public void clickForgotPass_toForgotPass(){
        activity.findViewById(R.id.forgetPassword).performClick();

        Intent expectedIntent=new Intent(activity, ForgotPasswordActivity.class);
        Intent actual=ShadowApplication.getInstance().getNextStartedActivity();
        assertEquals(expectedIntent.getComponent(),actual.getComponent());
    }

    /**
     * 测试跳转其他登录方式
     */
    @Test
    public void clickLoginOther_toLoginOther(){
        activity.findViewById(R.id.login_number).performClick();

        Intent expectedIntent=new Intent(activity, LoginByOtherActivity.class);
        Intent actualIntent=ShadowApplication.getInstance().getNextStartedActivity();
        assertEquals(expectedIntent.getComponent(),actualIntent.getComponent());
    }
}