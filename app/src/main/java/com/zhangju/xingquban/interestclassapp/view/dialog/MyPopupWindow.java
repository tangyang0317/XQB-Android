package com.zhangju.xingquban.interestclassapp.view.dialog;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.view.KeyEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.PopupWindow;

import com.zhangju.xingquban.R;

/**
 * Created by liush on 2016/11/30 0030.
 *
 * @从指定根布局弹出poupWindow
 */
public class MyPopupWindow {

    private static MyPopupWindow mPopupWindow = new MyPopupWindow();

    public static MyPopupWindow getInstance() {
        return mPopupWindow;
    }

    private MyPopupWindow() {
    }

    public PopupWindow makePopupWindow(final Activity activity, View view) {

        final PopupWindow popupWindow = new PopupWindow(view, WindowManager.LayoutParams.MATCH_PARENT,
                WindowManager.LayoutParams.WRAP_CONTENT, true);

        // 设置背景透明实现变暗效果
        WindowManager.LayoutParams layoutParams = activity.getWindow().getAttributes();
        layoutParams.alpha = 0.7f;
        activity.getWindow().setAttributes(layoutParams);
        popupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                WindowManager.LayoutParams layoutParams = activity.getWindow().getAttributes();
                layoutParams.alpha = 1f;
                activity.getWindow().setAttributes(layoutParams);
            }
        });
        popupWindow.setAnimationStyle(R.style.anim_bottombar);
        popupWindow.setTouchable(true);
        popupWindow.setOutsideTouchable(true);
        popupWindow.setBackgroundDrawable(new BitmapDrawable(activity.getResources(), (Bitmap) null));

        popupWindow.getContentView().setFocusableInTouchMode(true);
        popupWindow.getContentView().setFocusable(true);
        popupWindow.getContentView().setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (keyCode == KeyEvent.KEYCODE_MENU && event.getRepeatCount() == 0 && event.getAction()
                        == KeyEvent.ACTION_DOWN) {
                    if (popupWindow != null && popupWindow.isShowing()) {
                        popupWindow.dismiss();
                    }
                    return true;
                }
                return false;
            }
        });
        popupWindow.setSoftInputMode(PopupWindow.INPUT_METHOD_NEEDED);
        popupWindow.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);
        return popupWindow;
    }
    //popupWindow.showAtLocation(activity.findViewById(R.id.rl_liveing_main), Gravity.BOTTOM, 0, 0);
}
