package com.zhangju.xingquban.interestclassapp.refactor.common.activity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.webkit.JavascriptInterface;
import android.webkit.WebSettings;
import android.webkit.WebViewClient;

import com.fastlib.base.AbsWebViewActivity;
import com.fastlib.widget.TitleBar;
import com.zhangju.xingquban.R;
import com.zhangju.xingquban.interestclassapp.refactor.me.activity.LoginActivity;
import com.zhangju.xingquban.interestclassapp.ui.main.MainActivity;

import java.io.UnsupportedEncodingException;

/**
 * Created by sgfb on 2017/11/22.
 * webview跳转
 */
public class WebViewActivity
        extends AbsWebViewActivity {
    TitleBar mTitleBar;
    private boolean mIsLauncher;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_webview);
        mIsLauncher = getIntent().getBooleanExtra("isLauncher", false);
        mTitleBar = (TitleBar) findViewById(R.id.titleBar);

        mTitleBar.setOnLeftClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mWebView.canGoBack()) {
                    mWebView.goBack();
                }else {
                    if (mIsLauncher) {
                        startActivity(new Intent(WebViewActivity.this, MainActivity.class));
                    }
                    finish();
                }
            }
        });
        try {
            init(R.id.webview, R.id.progressbar);
            mWebView.addJavascriptInterface(this, "androidWebView");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void webTitle(String title) {
        mTitleBar.getTitle().setText(title);
    }

    @Override
    public void onBackPressed() {
        if (mIsLauncher) {
            startActivity(new Intent(WebViewActivity.this, MainActivity.class));
            finish();
        } else {
            super.onBackPressed();
        }
    }

    @JavascriptInterface
    public void goLogin() {
        startActivity(new Intent(WebViewActivity.this, LoginActivity.class));
    }

}
