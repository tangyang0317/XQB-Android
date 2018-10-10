package com.zhangju.xingquban.refactoring.utils;

import android.app.ActivityManager;
import android.content.Context;
import android.util.Log;

import com.orhanobut.logger.Logger;

import java.util.List;

/**
 * @packageName com.zhangju.xingquban.refactoring.utils
 * @FileName SystemUtils
 * @Author tangyang
 * @DATE 2018/10/9
 **/
public class SystemUtils {

    public static boolean isAppaLive(Context context, String str) {
        ActivityManager am = (ActivityManager) context
                .getSystemService(Context.ACTIVITY_SERVICE);
        List<ActivityManager.RunningTaskInfo> list = am.getRunningTasks(100);
        boolean isAppRunning = false;
        for (ActivityManager.RunningTaskInfo info : list) {
            if (info.topActivity.getPackageName().equals(str)//如果想要手动输入的话可以str换成<span style="font-family: Arial, Helvetica, sans-serif;">MY_PKG_NAME，下面相同</span>
                    || info.baseActivity.getPackageName().equals(str)) {
                isAppRunning = true;
                Logger.d(info.topActivity.getPackageName()
                        + " info.baseActivity.getPackageName()="
                        + info.baseActivity.getPackageName());
                break;
            }
        }
        return isAppRunning;
    }

}
