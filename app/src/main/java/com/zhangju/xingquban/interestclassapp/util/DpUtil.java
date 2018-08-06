package com.zhangju.xingquban.interestclassapp.util;

import android.content.res.Resources;

/**
 * Created by zsl on 2017/10/14.
 */

public class DpUtil {
    public static int px2dip(int pxValue)
    {
        final float scale = Resources.getSystem().getDisplayMetrics().density;
        return (int) (pxValue / scale + 0.5f);
    }


    public static int dip2px(float dipValue)
    {
        final float scale = Resources.getSystem().getDisplayMetrics().density;
        return (int) (dipValue * scale + 0.5f);
    }
}
