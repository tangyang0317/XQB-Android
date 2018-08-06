package com.zhangju.xingquban.interestclassapp.util;

import android.content.Context;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.zhangju.xingquban.R;


/**
 * Created by john on 2016/6/16.
 */
public class ToastUtil
        extends Toast {
    private static Toast mToast = null;
    private static Context sContext;
    private static String  oldMsg;
    private static long oneTime = 0;
    private static long twoTime = 0;

    /**
     * 自定义Toast样式
     *
     *
     * @param context
     * @param resId
     * @param text
     * @param duration hrq 2014-7-10下午2:15:36
     * @description
     */
    public static Toast makeText(Context context, int resId, CharSequence text, int duration) {
        Toast result = new Toast(context);

        // 获取LayoutInflater对象
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        // 由layout文件创建一个View对象
        View layout = inflater.inflate(R.layout.view_toast, null);

        // 实例化ImageView和TextView对象
        TextView textView = (TextView) layout.findViewById(R.id.message);

        textView.setText(text);

        result.setView(layout);
        result.setGravity(Gravity.CENTER_VERTICAL, 0, 0);
        result.setDuration(duration);
        return result;
    }

    public ToastUtil(Context context) {
        super(context);
    }

    public static void showToast(Context context, String content) {
        showToast(content);
//        mToast = ToastUtil.makeText(context, R.layout.view_toast, content, Toast.LENGTH_SHORT);
//        mToast.show();
    }


    public static void init(Context context) {
        sContext = context;
    }

    // 不重复弹出
    public static void showToast(String s) {
        if (s == null) {
            s = "is null!";
        }
        if (mToast == null) {
            mToast = Toast.makeText(sContext, s, Toast.LENGTH_SHORT);
            mToast.show();
            oneTime = System.currentTimeMillis();
        } else {
            twoTime = System.currentTimeMillis();
            if (s.equals(oldMsg)) {
                if (twoTime - oneTime > Toast.LENGTH_SHORT) {
                    mToast.show();
                }
            } else {
                oldMsg = s;
                mToast.setText(s);
                mToast.show();
            }
            oneTime = twoTime;
        }
    }


}
