package com.zhangju.xingquban.refactoring.utils;

import android.content.Context;
import android.os.Environment;
import android.view.View;
import android.widget.TextView;

import com.tencent.bugly.Bugly;
import com.tencent.bugly.beta.Beta;
import com.tencent.bugly.beta.UpgradeInfo;
import com.tencent.bugly.beta.download.DownloadTask;
import com.tencent.bugly.beta.ui.UILifecycleListener;
import com.zhangju.xingquban.R;

/**
 * Created by loveMeeko on 2018/5/23.
 */
public class BuglyUpdate {
    public static final String APP_CHANNEL = "DEBUG"; // TODO 自定义渠道
    public static int upgradeType;
    public static DownloadTask task;

    public static void initUpdateConfig(final Context context) {
        Beta.autoInit = true;
        Beta.autoCheckUpgrade = true;//true表示初始化时自动检查升级; false表示不会自动检查升级,需要手动调用Beta.checkUpgrade()方法;
        Beta.upgradeCheckPeriod = 60 * 1000;//设置升级检查周期为60s(默认检查周期为0s)，60s内SDK不重复向后台请求策略);
        Beta.initDelay = 1000;//设置启动延时为1s（默认延时3s），APP启动1s后初始化SDK，避免影响APP启动速度;
        Beta.largeIconId = R.drawable.app_icon;
        Beta.smallIconId = R.drawable.app_icon;
        Beta.storageDir = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS);
        Beta.showInterruptedStrategy = false; // 点击过确认的弹窗在APP下次启动自动检查更新时会再次显示;
        Beta.autoDownloadOnWifi = false;
        Beta.enableNotification = true;
        Beta.canShowApkInfo = true;
        Beta.upgradeDialogLayoutId = R.layout.upgrade_dialog;
        Beta.upgradeDialogLifecycleListener = new UILifecycleListener<UpgradeInfo>() {
            @Override
            public void onCreate(final Context context, View view, UpgradeInfo upgradeInfo) {
                upgradeType = upgradeInfo.upgradeType;
            }

            @Override
            public void onStart(Context context, View view, UpgradeInfo upgradeInfo) {

            }

            @Override
            public void onResume(final Context context, View view, UpgradeInfo upgradeInfo) {
                // 注：可通过这个回调方式获取布局的控件，如果设置了id，可通过findViewById方式获取，如果设置了tag，可以通过findViewWithTag，具体参考下面例子:
                // 通过id方式获取控件，并更改imageview图片
                TextView tv = (TextView) view.findViewById(R.id.title);
                tv.setText(upgradeInfo.title);

                TextView beta = (TextView) view.findViewWithTag("beta_title");
                beta.setText("");

                TextView info = (TextView) view.findViewWithTag("beta_upgrade_info");
                info.setText("");

            }

            @Override
            public void onPause(Context context, View view, UpgradeInfo upgradeInfo) {

            }

            @Override
            public void onStop(Context context, View view, UpgradeInfo upgradeInfo) {
            }

            @Override
            public void onDestroy(Context context, View view, UpgradeInfo upgradeInfo) {
            }

        };
        Bugly.init(context, "37dc7f1977", false);
    }
}
