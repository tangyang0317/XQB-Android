package com.zhangju.xingquban.interestclassapp;

import android.support.test.espresso.Espresso;
import android.support.test.espresso.action.ViewActions;
import android.support.test.espresso.matcher.ViewMatchers;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.zhangju.xingquban.R;
import com.zhangju.xingquban.interestclassapp.ui.main.MainActivity;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * Created by Administrator on 2017/12/23.
 */
@RunWith(AndroidJUnit4.class)
public class MeTest{
    @Rule
    public ActivityTestRule<MainActivity> mActivity=new ActivityTestRule<>(MainActivity.class);

    @Test
    public void testFunctionList(){
        Espresso.onView(ViewMatchers.withId(R.id.main_btn_me)).perform(ViewActions.click());
        Espresso.onView(ViewMatchers.withId(R.id.feedback)).perform(ViewActions.scrollTo(),ViewActions.click());
    }
}
