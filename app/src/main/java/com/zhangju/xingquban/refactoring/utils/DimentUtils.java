package com.zhangju.xingquban.refactoring.utils;

import android.content.Context;

/**
 * @packageName com.zhangju.xingquban.refactoring.utils
 * @FileName DimentUtils
 * @Author tangyang
 * @DATE 2018/8/13
 **/
public class DimentUtils {

    public static int getDiment(Context context, int id) {
        return (int) context.getResources().getDimension(id);
    }

    /**
     * getDimensionPixelSize
     *
     * @param id
     * @return
     */
    public static int getDimensionPixelSize(Context context, int id) {
        return context.getResources().getDimensionPixelSize(id);
    }

    /**
     * 获取屏幕宽度
     *
     * @return
     */
    public static int getScreenWidth(Context context) {
        return context.getResources().getDisplayMetrics().widthPixels;
    }

    /**
     * 获取屏幕高度
     *
     * @return
     */
    public static int getScreenHeight(Context context) {
        return context.getResources().getDisplayMetrics().heightPixels;
    }

    /**
     * @param dipValue dip
     * @return
     */
    public static int dip2px(Context context, float dipValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dipValue * scale + 0.5f);
    }

    /**
     * @param pxValue px
     * @return
     */
    public static int px2dip(Context context, float pxValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (pxValue / scale + 0.5f);
    }

}
