package com.zhangju.xingquban.refactoring.utils;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

import java.util.List;

/**
 * @packageName com.zhangju.xingquban.refactoring.utils
 * @FileName SoftKeyboardUtil
 * @Author tangyang
 * @DATE 2018/9/1
 **/
public class SoftKeyboardUtil {

    /**
     * 隐藏软键盘(只适用于Activity，不适用于Fragment)
     */
    public static void hideSoftKeyboard(Activity activity) {
        View view = activity.getCurrentFocus();
        if (view != null) {
            InputMethodManager inputMethodManager = (InputMethodManager) activity.getSystemService(Activity.INPUT_METHOD_SERVICE);
            inputMethodManager.hideSoftInputFromWindow(view.getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
        }
    }

    /***
     * 显示软键盘
     * @param activity
     * @param view
     */
    public static void showSoftKeyboard(Activity activity, View view) {
        InputMethodManager imm = (InputMethodManager) activity.getSystemService(Context.INPUT_METHOD_SERVICE);
        //显示软键盘
        imm.showSoftInputFromInputMethod(view.getWindowToken(), 0);
    }

    /**
     * 隐藏软键盘(可用于Activity，Fragment)
     */
    public static void hideSoftKeyboard(Context context, List<View> viewList) {
        if (viewList == null) return;
        InputMethodManager inputMethodManager = (InputMethodManager) context.getSystemService(Activity.INPUT_METHOD_SERVICE);
        for (View v : viewList) {
            inputMethodManager.hideSoftInputFromWindow(v.getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
        }
    }
}
