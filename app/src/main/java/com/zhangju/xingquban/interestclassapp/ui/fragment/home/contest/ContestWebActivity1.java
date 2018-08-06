package com.zhangju.xingquban.interestclassapp.ui.fragment.home.contest;

import android.Manifest;
import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.webkit.JavascriptInterface;
import android.webkit.ValueCallback;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;

import com.fastlib.widget.TitleBar;
import com.zhangju.xingquban.BuildConfig;
import com.zhangju.xingquban.R;
import com.zhangju.xingquban.interestclassapp.refactor.me.activity.LoginActivity;
import com.zhangju.xingquban.interestclassapp.refactor.user.UserManager;

import java.io.File;

public class ContestWebActivity1 extends AppCompatActivity {

    private ValueCallback<Uri> uploadMessage;
    private ValueCallback<Uri[]> uploadMessageAboveL;

    private static final int REQUEST_CODE_ALBUM = 0x01;
    private static final int REQUEST_CODE_CAMERA = 0x02;
    private static final int REQUEST_CODE_PERMISSION_CAMERA = 0x03;

    private String mCurrentPhotoPath;
    private String mLastPhotoPath;

    public static final String ARG_URL = "URL";
    public static final String ARG_TITLE = "title";
    public static final String ARG_DATA = "data"; //本地html数据
    private static final String TAG = "WebViewActivity";

    private static final int LOGIN_REQUEST_CODE = 1001;
    protected ProgressBar mProgress;
    protected String mUrl;
    private TitleBar mTitleBar;
    private WebView mWebView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_webview);
        mTitleBar = (TitleBar) findViewById(R.id.titleBar);
        mWebView = (WebView) findViewById(R.id.webview);
        mProgress = (ProgressBar) findViewById(R.id.progressbar);
        mUrl = getIntent().getStringExtra(ARG_URL);
        String data = getIntent().getStringExtra(ARG_DATA);
        String title = getIntent().getStringExtra(ARG_TITLE);
        mTitleBar.setOnLeftClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        assert mWebView != null;
        WebSettings settings = mWebView.getSettings();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            settings.setMixedContentMode(WebSettings.MIXED_CONTENT_ALWAYS_ALLOW);
        }
        settings.setBlockNetworkImage(false);
        settings.setUseWideViewPort(true);
        settings.setCacheMode(WebSettings.LOAD_NO_CACHE);
        settings.setAppCacheEnabled(false);
        settings.setLoadWithOverviewMode(true);
        settings.setJavaScriptEnabled(true);
        settings.setLoadsImagesAutomatically(true);
        settings.setAllowFileAccess(true);


        mWebView.addJavascriptInterface(this, "androidWebView");
        mWebView.setWebViewClient(new WebClient());
        mWebView.setWebChromeClient(new ChromeClient());
        if (TextUtils.isEmpty(data)) {
            mWebView.loadUrl(mUrl);
        } else {
            mWebView.loadData(data, "text/html;charset=UTF-8", null);
        }

    }

    public void webTitle(String title) {
        mTitleBar.getTitle().setText(title);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == REQUEST_CODE_ALBUM || requestCode == REQUEST_CODE_CAMERA) {
            if (uploadMessage == null && uploadMessageAboveL == null) {
                return;
            }

            //取消拍照或者图片选择时
            if (resultCode != RESULT_OK) {
                //一定要返回null,否则<input file> 就是没有反应
                returnNullValue();
            }

            //拍照成功和选取照片时
            if (resultCode == RESULT_OK) {
                Uri imageUri = null;

                switch (requestCode) {
                    case REQUEST_CODE_ALBUM:

                        if (data != null) {
                            imageUri = data.getData();
                        }

                        break;
                    case REQUEST_CODE_CAMERA:

                        if (!TextUtils.isEmpty(mCurrentPhotoPath)) {
                            File file = new File(mCurrentPhotoPath);
                            Uri localUri = Uri.fromFile(file);
                            Intent localIntent = new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE, localUri);
                            sendBroadcast(localIntent);
                            imageUri = Uri.fromFile(file);
                            mLastPhotoPath = mCurrentPhotoPath;
                        }
                        break;
                    default:
                }


                //上传文件
                if (uploadMessage != null) {
                    uploadMessage.onReceiveValue(imageUri);
                    uploadMessage = null;
                }
                if (uploadMessageAboveL != null) {
                    uploadMessageAboveL.onReceiveValue(new Uri[]{imageUri});
                    uploadMessageAboveL = null;

                }
            }
        } else if (resultCode == Activity.RESULT_OK && requestCode == LOGIN_REQUEST_CODE) {
            String url = mWebView.getUrl();
            if (url.contains("token=null")) {
                String replace = url.replace("token=null", "token=" + UserManager.getInstance().getToken());
                mWebView.loadUrl("about:blank");
                mWebView.loadUrl(replace);
            }
        }
    }


    /**
     * 选择相机或者相册
     */
    public void uploadPicture() {
        String[] items = new String[]{"相机", "相册"};
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("请选择图片上传方式");
        builder.setItems(items, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                switch (which) {
                    case 0:
                        if (!TextUtils.isEmpty(mLastPhotoPath)) {
                            File file = new File(mLastPhotoPath);
                            if (file != null) {
                                file.delete();
                            }
                        }
                        //请求拍照权限
                        if (ActivityCompat.checkSelfPermission(ContestWebActivity1.this, Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED) {
                            takePhoto();
                        } else {
                            ActivityCompat.requestPermissions(ContestWebActivity1.this, new String[]{Manifest.permission.CAMERA}, REQUEST_CODE_PERMISSION_CAMERA);
                        }

                        break;
                    case 1:
                        chooseAlbumPic();
                        break;
                    default:
                }
            }
        });

        //取消对话框时候置空
        builder.setOnCancelListener(new DialogInterface.OnCancelListener() {
            @Override
            public void onCancel(DialogInterface dialog) {

                //一定要返回null,否则<input type='file'>
                returnNullValue();
            }
        });


        builder.show();

    }

    /**
     * 解决再次点击无响应问题
     */
    private void returnNullValue() {
        if (uploadMessage != null) {
            uploadMessage.onReceiveValue(null);
            uploadMessage = null;
        }
        if (uploadMessageAboveL != null) {
            uploadMessageAboveL.onReceiveValue(null);
            uploadMessageAboveL = null;

        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        switch (requestCode) {
            case REQUEST_CODE_PERMISSION_CAMERA:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    takePhoto();
                } else {
                    // Permission Denied
                    new AlertDialog.Builder(this)
                            .setTitle("无法拍照")
                            .setMessage("您未授予拍照权限")
                            .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    returnNullValue();
                                }
                            })
                            .setPositiveButton("去设置", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    Intent localIntent = new Intent();
                                    localIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                                    localIntent.setAction("android.settings.APPLICATION_DETAILS_SETTINGS");
                                    localIntent.setData(Uri.fromParts("package", getPackageName(), null));
                                    startActivity(localIntent);
                                }
                            }).show();
                }
                break;
            default:
        }

    }

    /**
     * 拍照
     */
    private void takePhoto() {

        StringBuilder fileName = new StringBuilder();
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        Uri takeUri = null;

        fileName.append(System.currentTimeMillis()).append("_temp.png");
        File tempFile = new File(getExternalFilesDir(Environment.DIRECTORY_PICTURES), fileName.toString());
        //适配7.0文件访问,通过此方式拿到文件地址
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            intent.setFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
            takeUri = FileProvider.getUriForFile(this, BuildConfig.APPLICATION_ID + ".fileprovider", tempFile);
        } else {
            takeUri = Uri.fromFile(tempFile);
        }

        intent.putExtra(MediaStore.EXTRA_OUTPUT, takeUri);

        mCurrentPhotoPath = tempFile.getAbsolutePath();
        startActivityForResult(intent, REQUEST_CODE_CAMERA);

    }

    /**
     * 选择相册照片
     */
    private void chooseAlbumPic() {
        Intent i = new Intent(Intent.ACTION_GET_CONTENT);
        i.addCategory(Intent.CATEGORY_OPENABLE);
        i.setType("image/*");
        startActivityForResult(Intent.createChooser(i, "Image Chooser"), REQUEST_CODE_ALBUM);
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
        // For Android < 3.0
        public void openFileChooser(ValueCallback<Uri> valueCallback) {
            uploadMessage = valueCallback;
            uploadPicture();
        }

        // For Android  >= 3.0
        public void openFileChooser(ValueCallback valueCallback, String acceptType) {
            uploadMessage = valueCallback;
            uploadPicture();


        }

        //For Android  >= 4.1
        public void openFileChooser(ValueCallback<Uri> valueCallback, String acceptType, String capture) {
            uploadMessage = valueCallback;
            uploadPicture();

        }

        // For Android >= 5.0
        @Override
        public boolean onShowFileChooser(WebView webView, ValueCallback<Uri[]> filePathCallback, WebChromeClient.FileChooserParams fileChooserParams) {
            uploadMessageAboveL = filePathCallback;
            uploadPicture();
            return true;
        }
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
                Intent intent = new Intent(ContestWebActivity1.this, LoginActivity.class);
                intent.putExtra("canGoBack", true);
                startActivityForResult(intent, LOGIN_REQUEST_CODE);
                mWebView.clearCache(true);
                mWebView.clearHistory();
            }
        });
    }

    @Override
    public void finish() {
        mWebView.loadUrl("about:blank");
        super.finish();
    }

    @Override
    public void onBackPressed() {
        finish();
    }
}