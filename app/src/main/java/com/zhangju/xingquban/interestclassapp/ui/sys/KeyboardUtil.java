package com.zhangju.xingquban.interestclassapp.ui.sys;

import android.graphics.Rect;
import android.view.View;
import android.view.ViewTreeObserver;

/**
 * Created by liush on 2016/11/14 0014.
 */

public class KeyboardUtil {
    private int  keyboardHeight;
    private static KeyboardUtil mKeyboardUtil = new KeyboardUtil();

    public static KeyboardUtil getInstance() {
        return mKeyboardUtil;
    }

    public int getKeyboardHeight(final View view) {
        view.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                Rect r = new Rect();
                view.getWindowVisibleDisplayFrame(r);

                int screenHeight = view.getRootView().getHeight();
                keyboardHeight = screenHeight - (r.bottom - r.top);
            }
        });
        return keyboardHeight;
    }
}
