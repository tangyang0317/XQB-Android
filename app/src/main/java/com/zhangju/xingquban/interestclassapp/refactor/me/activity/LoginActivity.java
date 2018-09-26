package com.zhangju.xingquban.interestclassapp.refactor.me.activity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.EditText;

import com.fastlib.annotation.Bind;
import com.fastlib.annotation.ContentView;
import com.fastlib.app.FastActivity;
import com.fastlib.app.task.EmptyAction;
import com.fastlib.app.task.NetAction;
import com.fastlib.app.task.NoParamAction;
import com.fastlib.app.task.NoReturnAction;
import com.fastlib.app.task.Task;
import com.fastlib.net.Request;
import com.fastlib.net.SimpleListener;
import com.fastlib.net.SimpleMockProcessor;
import com.fastlib.utils.Utils;
import com.orhanobut.logger.Logger;
import com.zhangju.xingquban.R;
import com.zhangju.xingquban.interestclassapp.application.MyApp;
import com.zhangju.xingquban.interestclassapp.refactor.common.bean.Response;
import com.zhangju.xingquban.interestclassapp.refactor.me.bean.MeInterface;
import com.zhangju.xingquban.interestclassapp.refactor.user.User;
import com.zhangju.xingquban.interestclassapp.refactor.user.UserManager;
import com.zhangju.xingquban.interestclassapp.refactor.user.UserVerify;
import com.zhangju.xingquban.interestclassapp.ui.main.MainActivity;
import com.zhangju.xingquban.interestclassapp.util.ToastUtil;

import java.io.File;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import cn.jpush.android.api.JPushInterface;
import cn.jpush.android.api.TagAliasCallback;

/**
 * Created by sgfb on 2017/10/27.
 * 账号密码登录界面
 */
@ContentView(R.layout.activity_login)
public class LoginActivity extends FastActivity {
    final int REQ_LOGIN_BY_OTHER = 1;

    @Bind(R.id.phone)
    EditText mPhone;
    @Bind(R.id.password)
    EditText mPassword;
    private boolean mCanGoBack = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = getIntent();
        mCanGoBack = intent.getBooleanExtra("canGoBack", false);
    }

    @Override
    protected void alreadyPrepared() {
        String lastLogin = UserManager.getInstance().getLastLogin();
        if (!TextUtils.isEmpty(lastLogin)) {
            mPhone.setText(UserManager.getInstance().getLastLogin()); //尝试获取最后一次登录账号
            mPassword.requestFocus();
        }
    }

    @Bind(R.id.commit)
    private void commit() {
        final String phone = mPhone.getText().toString();
        final String pass = mPassword.getText().toString();

        if (TextUtils.isEmpty(phone)) {
            mPhone.setError("手机号不能为空");
            mPhone.requestFocus();
            return;
        }
        if (!Utils.isPhoneNumber(phone)) {
            mPhone.setError("手机号格式不正确");
            mPhone.requestFocus();
            return;
        }
        if (TextUtils.isEmpty(pass)) {
            mPassword.setError("密码不能为空");
            mPassword.requestFocus();
            return;
        }
        loading("登录中...");
        startTask(Task.begin(new NoParamAction<Request>() {
            @Override
            protected Request executeAdapt() {
                Request request = Request.obtain(MeInterface.POST_LOGIN);
                request.put("phone", phone);
                request.put("password", pass);
                request.put("Rtn_Token", 1);
                return request;
            }
        }).next(new NetAction<Response<UserVerify>>() {

            @Override
            protected void executeAdapt(Response<UserVerify> userVerifyResponse, Request request) {
                if (userVerifyResponse.success && userVerifyResponse.data != null) {
                    userVerifyResponse.data.loginName = phone;
                    UserManager.getInstance().login(userVerifyResponse.data);
                    asyncRefreshUserData();
                    Intent intent = new Intent();
                    if (mCanGoBack) {
                        setResult(RESULT_OK);
                        finish();
                    } else {
                        MyApp.instance.mActivityStack.clearStack();
                        intent.setClass(LoginActivity.this, MainActivity.class);
                        intent.putExtra(MainActivity.ARG_INT_JUMP_PAGE, 4);
                        startActivity(intent);
                    }
                } else {
                    stopTask();
                }
            }
        }), new NoReturnAction<Throwable>() {
            @Override
            public void executeAdapt(Throwable param) {
                dismissLoading();
            }
        }, new EmptyAction() {
            @Override
            protected void executeAdapt() {
                dismissLoading();
            }
        });
    }

    private void asyncRefreshUserData() {
        Request request = Request.obtain(MeInterface.POST_USER_DATA);
        request.setListener(new SimpleListener<Response<List<User>>>() {

            @Override
            public void onResponseListener(Request r, Response<List<User>> result) {
                if (result.success && result.data != null && !result.data.isEmpty())
                    UserManager.getInstance().refreshUser(result.data.get(0));
            }
        });
        request.start();
    }


    @Bind(R.id.forgetPassword)
    private void openForgotPassword() {
        startActivity(ForgotPasswordActivity.class);
    }

    @Bind(R.id.register)
    private void register() {
        startActivity(RegisterActivity.class);
    }

    @Bind(R.id.login_number)
    private void loginByNumber() {
        startActivityForResult(LoginByOtherActivity.class, REQ_LOGIN_BY_OTHER);
    }

    @Bind(R.id.login_back)
    private void back() {
        finish();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) finish();
    }
}