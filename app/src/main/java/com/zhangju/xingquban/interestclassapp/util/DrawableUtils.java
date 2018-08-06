package com.zhangju.xingquban.interestclassapp.util;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.widget.TextView;

/**
 * Created by ydw on 2017/12/6.
 */

public class DrawableUtils {
    //setDrawble
    public static void setDrawableLeft(Context context, int resId, TextView textView) {
        Drawable drawable = context.getResources().getDrawable(resId);
        drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
        textView.setCompoundDrawables(drawable, null, null, null);
    }
}
