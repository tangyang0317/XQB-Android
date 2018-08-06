package com.zhangju.xingquban.interestclassapp.ui.fragment.me.Login;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.fastjson.JSONObject;
import com.zhangju.xingquban.R;
import com.zhangju.xingquban.interestclassapp.application.MyApp;
import com.zhangju.xingquban.interestclassapp.bean.EditBean;
import com.zhangju.xingquban.interestclassapp.util.UrlUtils;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.RequestParams;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest.HttpMethod;

public class ForgetActivity extends Activity {

    private String tag = "ForgetActivity";
    private TextView gainCaptcha;
    private int second = 60;
    private EditText mUserText;
    private EditText mPasswordText;
    private EditText mConPasswordText;
    private EditText mCaptcha;
    private Button confirmButton;

    private static final int CANNOT = 1;
    private static final int CAN = 2;
    private static final int FINISH = 3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forget);
        findViewById(R.id.back).setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                finish();
            }
        });

        initGainCaptcha();// 验证码
        initReset();// 初始化注册
    }

    private void initReset() {
        mPasswordText = (EditText) findViewById(R.id.password);
        mConPasswordText = (EditText) findViewById(R.id.passwordAgain);
        confirmButton = (Button) findViewById(R.id.confirmButton);
        mCaptcha = (EditText) findViewById(R.id.Captcha);
        confirmButton.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                final String username = mUserText.getText().toString();
                final String password = mPasswordText.getText().toString();
                final String conPassword = mConPasswordText.getText()
                        .toString();
                final String captcha = mCaptcha.getText().toString();
                if ((username == null || username.equalsIgnoreCase(""))) {
                    Toast.makeText(ForgetActivity.this, "用户名不能为空",
                            Toast.LENGTH_SHORT).show();
                } else if (password == null
                        || password.equalsIgnoreCase("")) {
                    Toast.makeText(ForgetActivity.this, "密码不能为空",
                            Toast.LENGTH_SHORT).show();
                } else if (conPassword == null
                        || conPassword.equalsIgnoreCase("")) {
                    Toast.makeText(ForgetActivity.this, "请再次输入密码",
                            Toast.LENGTH_SHORT).show();
                } else if (captcha == null || captcha.equalsIgnoreCase("")) {
                    Toast.makeText(ForgetActivity.this, "验证码不能为空",
                            Toast.LENGTH_SHORT).show();
                } else if (!password.equals(conPassword)) {
                    Toast.makeText(ForgetActivity.this, "两次输入密码不同",
                            Toast.LENGTH_SHORT).show();
                } else {
                    HttpUtils httpUtils = MyApp.httpUtils;
                    RequestParams params = new RequestParams();
                    params.addBodyParameter("phone", username);
                    params.addBodyParameter("newPwd", password);
                    params.addBodyParameter("cfmNewPwd", conPassword);
                    params.addBodyParameter("varCode", captcha);
                    httpUtils.send(HttpMethod.POST,
                            UrlUtils.URL_FORTPASSWORD,
                            params,
                            new RequestCallBack<String>() {

                                @Override
                                public void onFailure(HttpException arg0,
                                                      String arg1) {

                                }

                                @Override
                                public void onSuccess(
                                        ResponseInfo<String> arg0) {
                                    String data = arg0.result;
                                    EditBean registerBean = JSONObject.parseObject(data, EditBean.class);
                                    if (registerBean.isSuccess()) {
                                        Log.i(tag, data);
                                        Toast.makeText(
                                                ForgetActivity.this,
                                                "修改密码成功", Toast.LENGTH_SHORT)
                                                .show();
                                        mHandler.sendEmptyMessageDelayed(FINISH, 1000);
                                    } else {
                                        Log.i(tag, data);
                                        Log.i(tag, username);
                                        Log.i(tag, password);
                                        Log.i(tag, conPassword);
                                        Log.i(tag, captcha);
                                        Toast.makeText(
                                                ForgetActivity.this,
                                                registerBean.getErrMsg()
                                                        .toString(),
                                                Toast.LENGTH_SHORT).show();
                                    }
                                }
                            });
                }
            }
        });
    }

    private void initGainCaptcha() {
        gainCaptcha = (TextView) findViewById(R.id.gainCaptcha);
        mUserText = (EditText) findViewById(R.id.phoneNumber);
        gainCaptcha.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                String username = mUserText.getText().toString();
                HttpUtils httpUtils = MyApp.httpUtils;
                RequestParams params = new RequestParams();
                params.addBodyParameter("types", "forgetPwd");
                params.addBodyParameter("phone", username);
                httpUtils.send(HttpMethod.POST, UrlUtils.URL_CAPTCHA, params,
                        new RequestCallBack<String>() {

                            @Override
                            public void onFailure(HttpException arg0,
                                                  String arg1) {
                                Toast.makeText(ForgetActivity.this, "网络异常", Toast.LENGTH_SHORT).show();
                            }

                            @Override
                            public void onSuccess(ResponseInfo<String> arg0) {
                                String data = arg0.result;
                                Log.i(tag, data);
                                EditBean registerBean = JSONObject
                                        .parseObject(data, EditBean.class);
                                if (registerBean.isSuccess()) {
                                    gainCaptcha.setEnabled(false);
                                    new Thread() {
                                        @Override
                                        public void run() {
                                            while (second > 0) {
                                                Log.i(tag, second + "");
                                                try {
                                                    second -= 1;
                                                    Message msg = Message
                                                            .obtain();
                                                    msg.what = CANNOT;
                                                    msg.arg1 = second;
                                                    mHandler.sendMessage(msg);
                                                    sleep(1000);
                                                } catch (InterruptedException e) {
                                                    // block
                                                    e.printStackTrace();
                                                }
                                            }
                                            mHandler.sendEmptyMessage(CAN);
                                        }
                                    }.start();
                                } else {
                                    Log.i(tag, data);
                                    Toast.makeText(
                                            ForgetActivity.this,
                                            registerBean.getErrMsg().toString(),
                                            Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        second = 0;
    }

    Handler mHandler = new Handler() {

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case CANNOT:
                    gainCaptcha.setText("验证码" + "(" + second + ")" + "秒");
                    break;
                case CAN:
                    gainCaptcha.setText("获取验证码");
                    gainCaptcha.setEnabled(true);
                    break;
                case FINISH:
                    ForgetActivity.this.finish();
                    break;
                default:
                    break;
            }
        }

    };

    public void onResume() {
        super.onResume();
//		MobclickAgent.onResume(this);
    }

    public void onPause() {
        super.onPause();
//		MobclickAgent.onPause(this);
    }
}
