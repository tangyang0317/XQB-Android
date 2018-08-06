package com.zhangju.xingquban.interestclassapp.util.click;

/**
 * @Created by  liush on 2017/2/18.
 * @describe ${防止按钮被多次点击}
 */

public class NoDoubleClick {
    private static long lastClickTime;

    /**
     * @return 当一秒内被点击一次以上则返回false
     */
    public static boolean isNotDoubleClick() {
        long time  = System.currentTimeMillis();
        long timeD = time - lastClickTime;
        if (0 < timeD && timeD < 1000) {
            return false;
        }
        lastClickTime = time;
        return true;
    }
}
