package com.zhangju.xingquban.interestclassapp;

import android.support.test.espresso.Espresso;
import android.support.test.espresso.ViewAssertion;
import android.support.test.espresso.ViewInteraction;
import android.support.test.espresso.action.ViewActions;
import android.support.test.espresso.assertion.ViewAssertions;
import android.support.test.espresso.matcher.ViewMatchers;
import android.support.test.filters.LargeTest;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.zhangju.xingquban.R;
import com.zhangju.xingquban.interestclassapp.refactor.me.activity.LoginActivity;

import org.hamcrest.Matchers;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * Created by Administrator on 2017/12/22.
 */
@RunWith(AndroidJUnit4.class)
@LargeTest
public class LoginTest{
    @Rule
    public ActivityTestRule<LoginActivity> mActivityRule=new ActivityTestRule<>(LoginActivity.class);

    @Test
    public void inputBaseArgs(){
        Espresso.onView(ViewMatchers.withId(R.id.phone)).perform(ViewActions.typeText("13411111111"));
        Espresso.onView(ViewMatchers.withId(R.id.password)).perform(ViewActions.typeText("123456"));
        Espresso.onView(Matchers.allOf(ViewMatchers.withId(R.id.phone),ViewMatchers.withText("hello")));
    }
}