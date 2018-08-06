package com.zhangju.xingquban.interestclassapp.util;

import android.app.Activity;

import java.lang.ref.WeakReference;

/**
 * Created by wxy on 2017/12/11.
 */

public class ShareMannager {
    public interface ShareListener {
        void onShareStart();

        void onShareSuccess();

        void onShareCancel();

        void onShareFail();
    }

    private Activity mActivity;

    private ShareMannager(Activity activity) {
        mActivity = (Activity) (new WeakReference(activity)).get();
    }

    public static ShareMannager newInstance(Activity activity) {
        return new ShareMannager(activity);
    }


    public void share(){

    }



}
