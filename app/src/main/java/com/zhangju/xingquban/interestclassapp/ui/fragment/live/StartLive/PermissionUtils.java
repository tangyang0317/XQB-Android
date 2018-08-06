package com.zhangju.xingquban.interestclassapp.ui.fragment.live.StartLive;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Fragment;
import android.content.Context;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;

import com.zhangju.xingquban.R;

import java.util.HashMap;
import java.util.Map;

import static android.support.v4.app.ActivityCompat.requestPermissions;

/**
 * Created by zsl on 2017/6/19.
 */

public class PermissionUtils {
    private Map<Integer, Runnable> allowablePermissionRunnables = new HashMap<>();
    private Map<Integer, Runnable> disallowablePermissionRunnables = new HashMap<>();
    private static PermissionUtils util;

    private PermissionUtils() {
    }

    //外部调用
    public static PermissionUtils newInstance() {
        if (util == null) {
            util = new PermissionUtils();
        }
        return util;
    }

    /**
     * Activity请求权限
     *
     * @param activity
     * @param id                请求授权的id 唯一标识即可
     * @param permission        请求的权限
     * @param allowableRunnable 同意授权后的操作
     */
    public void requestPermission(final Activity activity, final int id, final String permission, Runnable allowableRunnable) {
        if (allowableRunnable == null) {
            throw new IllegalArgumentException("allowableRunnable == null");
        }
        allowablePermissionRunnables.put(id, allowableRunnable);
        //版本判断
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            //减少是否拥有权限
            int checkCallPhonePermission = ContextCompat.checkSelfPermission(activity.getApplicationContext(), permission);
            if (checkCallPhonePermission != PackageManager.PERMISSION_GRANTED) {
                //弹出对话框接收权限
                if (ActivityCompat.shouldShowRequestPermissionRationale(activity, permission)) {
                    showMessageOKCancel(activity, "你需要开启相应权限,才能正常使用该应用!",
                            new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    requestPermissions(activity, new String[]{permission},
                                            id);
                                }
                            });
                    return;
                }
                requestPermissions(activity, new String[]{permission}, id);
                return;
            } else {
                allowableRunnable.run();
            }
        } else {
            allowableRunnable.run();
        }
    }

    /**
     * Activity请求权限
     *
     * @param fragment
     * @param id                请求授权的id 唯一标识即可
     * @param permission        请求的权限
     * @param allowableRunnable 同意授权后的操作
     */
    public void requestPermission(Fragment fragment, int id, String permission, Runnable allowableRunnable) {
        if (allowableRunnable == null) {
            throw new IllegalArgumentException("allowableRunnable == null");
        }
        allowablePermissionRunnables.put(id, allowableRunnable);
        //版本判断
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            //减少是否拥有权限
            int checkCallPhonePermission = ContextCompat.checkSelfPermission(fragment.getActivity(), permission);
            if (checkCallPhonePermission != PackageManager.PERMISSION_GRANTED) {
                //弹出对话框接收权限
                fragment.requestPermissions(new String[]{permission}, id);
                return;
            } else {
                allowableRunnable.run();
            }
        } else {
            allowableRunnable.run();
        }
    }

    public void requestPermission(android.support.v4.app.Fragment fragment, int id, String permission, Runnable allowableRunnable) {
        if (allowableRunnable == null) {
            throw new IllegalArgumentException("allowableRunnable == null");
        }
        allowablePermissionRunnables.put(id, allowableRunnable);
        //版本判断
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            //减少是否拥有权限
            int checkCallPhonePermission = ContextCompat.checkSelfPermission(fragment.getActivity(), permission);
            if (checkCallPhonePermission != PackageManager.PERMISSION_GRANTED) {
                //弹出对话框接收权限
                fragment.requestPermissions(new String[]{permission}, id);
                return;
            } else {
                allowableRunnable.run();
            }
        } else {
            allowableRunnable.run();
        }
    }

    private void showMessageOKCancel(Context context, String message, DialogInterface.OnClickListener okListener) {
        new AlertDialog.Builder(context)
                .setMessage(message)
                .setPositiveButton(R.string.OK, okListener)
                .setNegativeButton(R.string.cancel, null)
                .create()
                .show();
    }

}
