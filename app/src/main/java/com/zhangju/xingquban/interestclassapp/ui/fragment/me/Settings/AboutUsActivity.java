package com.zhangju.xingquban.interestclassapp.ui.fragment.me.Settings;

import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.TextView;

import com.zhangju.xingquban.R;
import com.zhangju.xingquban.interestclassapp.refactor.common.bean.CommonInterface;

public class AboutUsActivity extends AppCompatActivity {
    private TextView summary;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_aboutus);
        initView();
        findViewById(R.id.back).setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }

    private void initView() {
        WebView webAboutUs = (WebView) findViewById(R.id.web_me_aboutus);
        String version = getlocalVersion();
        webAboutUs.loadUrl(CommonInterface.URL_ABOUT_US + version);
        WebSettings settings = webAboutUs.getSettings();
        settings.setBuiltInZoomControls(false);// 显示缩放按钮(wap网页不支持)
        settings.setUseWideViewPort(false);// 支持双击缩放(wap网页不支持)
        settings.setJavaScriptEnabled(true);
        settings.setDatabaseEnabled(true);
        settings.setAppCacheEnabled(true);
        String absolutePath = getApplicationContext().getCacheDir().getAbsolutePath();
        settings.setAppCachePath(absolutePath);
    }

    public void onResume() {
        super.onResume();
//		MobclickAgent.onResume(this);
    }

    public void onPause() {
        super.onPause();
//		MobclickAgent.onPause(this);
    }

    /**
     * 获取本地版本号
     *
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
