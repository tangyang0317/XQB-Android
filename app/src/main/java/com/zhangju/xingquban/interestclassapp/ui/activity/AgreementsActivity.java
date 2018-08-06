package com.zhangju.xingquban.interestclassapp.ui.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.TextView;

import com.zhangju.xingquban.R;


/**
 * Created by liush on 2016/12/13 0013.
 *
 * @用户协议
 * 调用前需用intent传入标题及协议内容的URL
 */
public class AgreementsActivity extends Activity {

    private String mTitle;
    private String mUrl;
    private TextView mTvTitle;
    private WebView mWebAgreement;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agreements);

        initData();
        initView();
    }

    private void initView() {
        ImageView back = (ImageView) findViewById(R.id.iv_agreements_back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        mTvTitle = (TextView) findViewById(R.id.tv_agreements_title);
        mTvTitle.setText(mTitle);

        mWebAgreement = (WebView) findViewById(R.id.web_agreements);
        mWebAgreement.loadUrl(mUrl);
        WebSettings settings = mWebAgreement.getSettings();
        settings.setBuiltInZoomControls(false);// 显示缩放按钮(wap网页不支持)
        settings.setUseWideViewPort(false);// 支持双击缩放(wap网页不支持)
        settings.setJavaScriptEnabled(true);
        settings.setDatabaseEnabled(true);
        settings.setAppCacheEnabled(true);
        String absolutePath = getApplicationContext().getCacheDir().getAbsolutePath();
        settings.setAppCachePath(absolutePath);
    }

    private void initData() {
        Intent intent = getIntent();
        mTitle = intent.getStringExtra("title");
        mUrl = intent.getStringExtra("url");
    }
}
