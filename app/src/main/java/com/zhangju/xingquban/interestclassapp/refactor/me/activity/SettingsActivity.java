package com.zhangju.xingquban.interestclassapp.refactor.me.activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.provider.Settings;
import android.text.format.Formatter;
import android.view.View;
import android.widget.TextView;

import com.fastlib.annotation.Bind;
import com.fastlib.annotation.ContentView;
import com.fastlib.app.FastActivity;
import com.fastlib.app.FastDialog;
import com.fastlib.app.task.NoParamAction;
import com.fastlib.app.task.NoReturnAction;
import com.fastlib.app.task.Task;
import com.fastlib.app.task.ThreadType;
import com.fastlib.db.SaveUtil;
import com.fastlib.widget.TitleBar;
import com.zhangju.xingquban.R;
import com.zhangju.xingquban.interestclassapp.refactor.common.activity.WebViewActivity;
import com.zhangju.xingquban.interestclassapp.refactor.common.bean.CommonInterface;
import com.zhangju.xingquban.interestclassapp.refactor.user.UserManager;
import com.zhangju.xingquban.interestclassapp.ui.fragment.me.Settings.AboutUsActivity;

/**
 * Created by sgfb on 2017/10/26.
 * 系统设置
 */
@ContentView(R.layout.act_settings)
public class SettingsActivity extends FastActivity {
    @Bind(R.id.titleBar)
    TitleBar mTitleBar;
    @Bind(R.id.cache)
    TextView mCache;
    @Bind(R.id.logout)
    View mLogout;

    @Override
    protected void alreadyPrepared() {
        mLogout.setVisibility(UserManager.getInstance().isLogin() ? View.VISIBLE : View.INVISIBLE);
        mTitleBar.setOnLeftClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        //获取缓存大小
        startTask(Task.begin(new NoParamAction<String>() {

            @Override
            protected String executeAdapt() {
                long size = SaveUtil.cacheSize(SettingsActivity.this);
                return Formatter.formatFileSize(SettingsActivity.this, size);
            }
        })
                .next(new NoReturnAction<String>() {
                    @Override
                    public void executeAdapt(String param) {
                        mCache.setText(param);
                    }
                }, ThreadType.MAIN));
    }

    @Bind(R.id.pushSetting)
    private void openPushSetting() {
        startActivity(new Intent(Settings.ACTION_SETTINGS));
    }

    @Bind(R.id.locationSetting)
    private void openLocationSetting() {
        startActivity(new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS));
    }

    @Bind(R.id.cacheSetting)
    private void clearCache() {
        FastDialog.showMessageDialog("确定清除缓存?", true).show(getSupportFragmentManager(), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                mCache.setText("0B");
                mThreadPool.execute(new Runnable() {
                    @Override
                    public void run() {
                        SaveUtil.clearCache(SettingsActivity.this);
                    }
                });
            }
        });
    }

    @Bind(R.id.aboutUs)
    private void openAboutUs() { //张老师
        Intent intent=new Intent(this, WebViewActivity.class);
        intent.putExtra(WebViewActivity.ARG_TITLE,"关于我们");
        intent.putExtra(WebViewActivity.ARG_URL,CommonInterface.URL_ABOUT_US+getlocalVersion());
        startActivity(intent);
//        startActivity(AboutUsActivity.class);
    }

    @Bind(R.id.logout)
    private void logout() {
        FastDialog.showMessageDialog("确定要退出登录？",true).show(getSupportFragmentManager(), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                UserManager.getInstance().logout();
                finish();
            }
        });
    }

    /**
     * 获取本地版本号
     * @return
     */
    public String getlocalVersion() {
        String localversion = null;
        try {
            PackageInfo info = getPackageManager().getPackageInfo(getPackageName(), 0);
            localversion = info.versionName;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return localversion;
    }
}
