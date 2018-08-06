package com.zhangju.xingquban.interestclassapp.ui.fragment.home.contest;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.JavascriptInterface;
import android.webkit.ValueCallback;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;

import com.fastlib.widget.TitleBar;
import com.jph.takephoto.model.TImage;
import com.jph.takephoto.model.TResult;
import com.zhangju.xingquban.R;
import com.zhangju.xingquban.interestclassapp.base.TakePhotoBaseActivity;
import com.zhangju.xingquban.interestclassapp.refactor.me.activity.LoginActivity;
import com.zhangju.xingquban.interestclassapp.refactor.user.UserManager;

public class ContestWebActivity extends TakePhotoBaseActivity {
    public static final String ARG_URL = "URL";
    public static final String ARG_TITLE = "title";
    public static final String ARG_DATA = "data"; //本地html数据
    private static final String TAG = "WebViewActivity";

    protected WebView mWebView;
    protected ProgressBar mProgress;
    protected String mUrl;
    private TitleBar mTitleBar;
    private static final int LOGIN_REQUEST_CODE = 1001;

    private ValueCallback<Uri> mFilePathCallback;
    private ValueCallback<Uri[]> mFilePathCallbackArray;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.act_webview);
    }

    @Override
    public void initView() {
        super.initView();
        mTitleBar = (TitleBar) findViewById(R.id.titleBar);

        mTitleBar.setOnLeftClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        init(R.id.webview, R.id.progressbar);
    }

    @Override
    public int getLayout() {
        return R.layout.act_webview;
    }

    public void webTitle(String title) {
        mTitleBar.getTitle().setText(title);
    }


    protected void init(int webViewId, int progressId) {
        mWebView = (WebView) findViewById(webViewId);
        mProgress = (ProgressBar) findViewById(progressId);
        mUrl = getIntent().getStringExtra(ARG_URL);
        String data = getIntent().getStringExtra(ARG_DATA);
        String title = getIntent().getStringExtra(ARG_TITLE);

        webTitle(title);
        WebSettings settings = mWebView.getSettings();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            settings.setMixedContentMode(WebSettings.MIXED_CONTENT_ALWAYS_ALLOW);
        }
        settings.setBlockNetworkImage(false);
        settings.setJavaScriptEnabled(true);
        settings.setCacheMode(WebSettings.LOAD_NO_CACHE);
        settings.setAllowFileAccess(true);
        settings.setLoadsImagesAutomatically(true);
        settings.setSaveFormData(true);
        settings.setAppCacheEnabled(false);
        settings.setDatabaseEnabled(true);



        mWebView.addJavascriptInterface(this, "androidWebView");
        mWebView.setWebViewClient(new WebClient());
        mWebView.setWebChromeClient(new ChromeClient());

        /*if (TextUtils.isEmpty(data)) {
            mWebView.loadUrl(mUrl);
        } else {
            mWebView.loadData(data, "text/html;charset=UTF-8", null);
        }*/
        mWebView.loadUrl("http://www.layui.com/demo/upload.html");
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && mWebView.canGoBack()) {
            mWebView.goBack();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    protected class ChromeClient extends WebChromeClient {

        @Override
        public void onProgressChanged(WebView view, int newProgress) {
            mProgress.setProgress(newProgress);
            if (newProgress >= 100)
                mProgress.setVisibility(View.GONE);

        }

        @Override
        public void onReceivedTitle(WebView view, String title) {
            webTitle(title);
            mProgress.setVisibility(View.VISIBLE);
            mProgress.setProgress(20);
        }

        // file upload callback (Android 2.2 (API level 8) -- Android 2.3 (API level 10)) (hidden method)
        public void openFileChooser(ValueCallback<Uri> filePathCallback) {
            handle(filePathCallback);
        }

        // file upload callback (Android 3.0 (API level 11) -- Android 4.0 (API level 15)) (hidden method)
        public void openFileChooser(ValueCallback filePathCallback, String acceptType) {
            handle(filePathCallback);
        }

        // file upload callback (Android 4.1 (API level 16) -- Android 4.3 (API level 18)) (hidden method)
        public void openFileChooser(ValueCallback<Uri> filePathCallback, String acceptType, String capture) {
            handle(filePathCallback);
        }

        // for Lollipop
        @Override
        public boolean onShowFileChooser(WebView webView, ValueCallback<Uri[]> filePathCallback, FileChooserParams
                fileChooserParams) {
            // Double check that we don't have any existing callbacks
            if (mFilePathCallbackArray != null) {
                mFilePathCallbackArray.onReceiveValue(null);
            }
            mFilePathCallbackArray = filePathCallback;
            takePhoto();
            return true;
        }

        /**
         * 处理5.0以下系统回调
         *
         * @param filePathCallback
         */
        private void handle(ValueCallback<Uri> filePathCallback) {
            if (mFilePathCallback != null) {
                mFilePathCallback.onReceiveValue(null);
            }
            mFilePathCallback = filePathCallback;
            takePhoto();
        }
    }

    private void takePhoto() {
        getTakePhoto().onPickFromGallery();
    }

    protected class WebClient extends WebViewClient {

        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            if (url.startsWith("tel:")) {
                Intent intent = new Intent(Intent.ACTION_DIAL);
                intent.setData(Uri.parse(url));
                startActivity(intent);
                return true;
            }
            return false;
        }

        @Override
        public void onPageStarted(WebView view, String url, Bitmap favicon) {
            Log.d(TAG, "page start:" + url);
            super.onPageStarted(view, url, favicon);
        }

        @Override
        public void onPageFinished(WebView view, String url) {
            Log.d(TAG, "page end: " + url);
            super.onPageFinished(view, url);
            if (url.contains("static/wechat/competition/#/activity/list")) {
                mTitleBar.setVisibility(View.VISIBLE);
            } else {
                mTitleBar.setVisibility(View.GONE);
            }
        }

    }

    @JavascriptInterface
    public void goLogin() {
        mWebView.post(new Runnable() {
            @Override
            public void run() {
                Log.d(TAG, "goLogin: 跳转登录" + mWebView.getUrl());
                Intent intent = new Intent(ContestWebActivity.this, LoginActivity.class);
                intent.putExtra("canGoBack", true);
                startActivityForResult(intent, LOGIN_REQUEST_CODE);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == Activity.RESULT_OK && requestCode == LOGIN_REQUEST_CODE) {
            String url = mWebView.getUrl();
            if (url.contains("token=null")) {
                String replace = url.replace("token=null", "token=" + UserManager.getInstance().getToken());
                mWebView.loadUrl("about:blank");
                mWebView.loadUrl(replace);
            }
        } else {
            super.onActivityResult(requestCode, resultCode, data);
        }

    }

    @Override
    public void finish() {
        mWebView.loadUrl("about:blank");
        super.finish();
    }

    @Override
    public void takeSuccess(TResult result) {
        super.takeSuccess(result);
        TImage image = result.getImage();
        String path = image.getCompressPath();
        if (path == null) {
            path = image.getOriginalPath();
        }
        if (path == null) {
            return;
        }
        Uri uri = Uri.parse(path);
        handleCallback(uri);
    }

    @Override
    public void takeCancel() {
        super.takeCancel();
        handleCallback(null);
    }

    @Override
    public void takeFail(TResult result, String msg) {
        super.takeFail(result, msg);
        handleCallback(null);
    }

    /**
     * 处理WebView的回调
     *
     * @param uri
     */
    private void handleCallback(Uri uri) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            if (mFilePathCallbackArray != null) {
                if (uri != null) {
                    mFilePathCallbackArray.onReceiveValue(new Uri[]{uri});
                } else {
                    mFilePathCallbackArray.onReceiveValue(null);
                }
                mFilePathCallbackArray = null;
            }
        } else {
            if (mFilePathCallback != null) {
                if (uri != null) {
                    mFilePathCallback.onReceiveValue(uri);
                } else {
                    mFilePathCallback.onReceiveValue(null);
                }
                mFilePathCallback = null;
            }
        }
    }
}
