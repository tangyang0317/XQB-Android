package com.zhangju.xingquban.interestclassapp;

import android.support.test.espresso.Espresso;
import android.support.test.espresso.action.ViewActions;
import android.support.test.espresso.assertion.ViewAssertions;
import android.support.test.espresso.matcher.ViewMatchers;
import android.support.test.filters.LargeTest;
import android.support.test.filters.MediumTest;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.view.View;
import android.view.ViewManager;
import android.widget.Spinner;

import com.zhangju.xingquban.R;
import com.zhangju.xingquban.interestclassapp.test.TestActivity;
import com.zhangju.xingquban.interestclassapp.ui.fragment.home.CityActivity;

import org.hamcrest.Matcher;
import org.hamcrest.Matchers;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static junit.framework.Assert.assertEquals;

/**
 * Created by Administrator on 2017/12/23.
 */
@RunWith(AndroidJUnit4.class)
@MediumTest
public class SelectCityTest{
    @Rule
    public ActivityTestRule<TestActivity> mTestActivity=new ActivityTestRule<>(TestActivity.class);

    @Test
    public void testTest(){
        Espresso.onView(Matchers.allOf(ViewMatchers.withText("张三"),ViewMatchers.hasSibling(ViewMatchers.withText("3")))).perform(ViewActions.click());
    }
}