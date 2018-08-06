package com.zhangju.xingquban.interestclassapp.refactor.me.activity;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.content.Intent;
import android.text.TextUtils;
import android.util.Base64;
import android.view.animation.LinearInterpolator;
import android.widget.EditText;
import android.widget.TextView;

import com.fastlib.annotation.Bind;
import com.fastlib.annotation.ContentView;
import com.fastlib.annotation.Event;
import com.fastlib.app.FastActivity;
import com.fastlib.net.Request;
import com.fastlib.net.SimpleListener;
import com.fastlib.utils.N;
import com.fastlib.utils.Utils;
import com.tencent.tauth.IUiListener;
import com.tencent.tauth.UiError;
import com.zhangju.xingquban.R;
import com.zhangju.xingquban.interestclassapp.refactor.common.bean.EventWechatLogin;
import com.zhangju.xingquban.interestclassapp.refactor.common.bean.Response;
import com.zhangju.xingquban.interestclassapp.refactor.common.utils.DESUtil;
import com.zhangju.xingquban.interestclassapp.refactor.common.utils.ThirdPartyUtils;
import com.zhangju.xingquban.interestclassapp.refactor.me.bean.MeInterface;
import com.zhangju.xingquban.interestclassapp.refactor.me.bean.ResponseThirdPartyLogin;
import com.zhangju.xingquban.interestclassapp.refactor.user.User;
import com.zhangju.xingquban.interestclassapp.refactor.user.UserManager;
import com.zhangju.xingquban.interestclassapp.refactor.user.UserVerify;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.List;

import cn.sharesdk.framework.Platform;
import cn.sharesdk.framework.PlatformActionListener;
import cn.sharesdk.framework.ShareSDK;
import cn.sharesdk.sina.weibo.SinaWeibo;

/**
 * Created by sgfb on 2017/10/27.
 * 其他登录方式界面（手机号，第三方例如QQ，微信，微博）
 */
@ContentView(R.layout.activity_login_tel_number)
public class LoginByOtherActivity
        extends FastActivity {
    final int REQ_BINDING_PHONE = 1;
    @Bind(R.id.phone)
    EditText mPhone;
    @Bind(R.id.password)
    EditText mVerifyCode;

    @Override
    protected void alreadyPrepared() {
                ThirdPartyUtils.getInstance(this); //先调一下初始化微博
        //        mSsoHandler=new SsoHandler(this);
    }

    @Bind(R.id.getVerifyCode)
    private void getCode(final TextView v) {
        String phone = mPhone.getText().toString();

        if (TextUtils.isEmpty(phone) || !Utils.isPhoneNumber(phone)) {
            mPhone.setError("请输入正确的手机号");
            mPhone.requestFocus();
            return;
        }
        loading();
        try {
            String verifyCode = Base64.encodeToString(com.zhangju.xingquban.interestclassapp.refactor.common.utils.Utils
                    .aesEncryption("CBC", "PKCS5padding", "bicikeji@zhangju", "zhangju@bicikeji", phone), Base64.DEFAULT).trim();
            String checkCode = Base64.encodeToString(DESUtil.encode(String.valueOf(System.currentTimeMillis())),
                    Base64.DEFAULT).trim();
            verifyCode = verifyCode.replace("+", "%2B").replace("\\", "%5C").replace(" ", "%20").replace("/", "%2F");
            checkCode = checkCode.replace("+", "%2B").replace("\\", "%5C").replace(" ", "%20").replace("/", "%2F");
            Request request = Request.obtain("get", MeInterface.GET_GET_VERIFY_CODE);
            request.put("types", "QuickLogin");
            request.put("phone", phone);
            request.put("verifyCode", verifyCode);
            request.put("checkCode", checkCode);
            request.setListener(new SimpleListener<Response>() {

                @Override
                public void onResponseListener(Request r, Response result) {
                    if (result.success) {
                        v.setEnabled(false);
                        countdownTime(v);
                        N.showLong(LoginByOtherActivity.this, "验证码请求成功，请注意查收");
                    }
                    dismissLoading();
                }

                @Override
                public void onErrorListener(Request r, String error) {
                    super.onErrorListener(r, error);
                    dismissLoading();
                }
            });
            net(request);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void countdownTime(final TextView v) {
        ValueAnimator countdownAnima = ValueAnimator.ofInt(60, 0);
        countdownAnima.setInterpolator(new LinearInterpolator());
        countdownAnima.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                v.setText("获取验证码");
                v.setEnabled(true);
            }
        });
        countdownAnima.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                v.setText("验证码(" + animation.getAnimatedValue().toString() + ")秒");
            }
        });
        countdownAnima.setDuration(60000);
        countdownAnima.start();
    }

    @Bind(R.id.commit)
    private void commit() {
        String phone = mPhone.getText().toString();
        String code = mVerifyCode.getText().toString();

        if (TextUtils.isEmpty(phone) || !Utils.isPhoneNumber(phone)) {
            mPhone.setError("请输入正确的手机号");
            mPhone.requestFocus();
            return;
        }
        if (TextUtils.isEmpty(code)) {
            mVerifyCode.setError("验证码不能为空");
            mVerifyCode.requestFocus();
            return;
        }
        loading();
        final Request request = Request.obtain(MeInterface.POST_QUICK_LOGIN);
        request.put("phone", phone);
        request.put("varCode", code);
        request.put("Rtn_Token", 1);
        request.setListener(new SimpleListener<Response<UserVerify>>() {

            @Override
            public void onResponseListener(Request r, Response<UserVerify> result) {
                if (result.success) {
                    UserManager.getInstance().login(result.data);
                    requestUserData();
                } else {
                    dismissLoading();
                    N.showLong(LoginByOtherActivity.this, "登录失败");
                }
            }

            @Override
            public void onErrorListener(Request r, String error) {
                super.onErrorListener(r, error);
                dismissLoading();
            }
        });
        net(request);
    }

    /**
     * 请求刷新用户数据
     */
    private void requestUserData() {
        setResult(RESULT_OK); //请求刷新用户数据代表已成功登陆
        Request request = Request.obtain(MeInterface.POST_USER_DATA);
        request.putHeader("X-CustomToken", UserManager.getInstance().getToken());
        request.setListener(new SimpleListener<Response<List<User>>>() {

            @Override
            public void onResponseListener(Request r, Response<List<User>> result) {
                dismissLoading();
                if (result.success && result.data != null && !result.data.isEmpty())
                    UserManager.getInstance().refreshUser(result.data.get(0));
                finish();
            }

            @Override
            public void onErrorListener(Request r, String error) {
                super.onErrorListener(r, error);
                dismissLoading();
                finish();
            }
        });
        net(request);
    }

    @Bind(R.id.register)
    private void openRegister() {
        startActivity(RegisterActivity.class);
    }

    @Bind(R.id.cb_live_share_QQ)
    private void loginByQQ() {
        ThirdPartyUtils.getInstance(this).mTencent.login(this, "all", new IUiListener() {
            @Override
            public void onComplete(Object o) {
                if (o instanceof JSONObject) {
                    JSONObject jo = (JSONObject) o;
                    try {
                        requestThirdPartyLogin("qq", jo.getString("openid"), jo.getString("access_token"));
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onError(UiError uiError) {
                System.out.println(uiError);
            }

            @Override
            public void onCancel() {
                N.showShort(LoginByOtherActivity.this, "取消QQ关联登录");
            }
        });
    }

    @Bind(R.id.cb_live_share_Wechat)
    private void loginByWeChat() {
        ThirdPartyUtils.getInstance(this).loginByWechat();
    }

    //    SsoHandler mSsoHandler;

    @Bind(R.id.SinaWeibo)
    private void loginByWeibo() {
        Platform weibo = ShareSDK.getPlatform(SinaWeibo.NAME);
        weibo.SSOSetting(false);  //设置false表示使用SSO授权方式
        weibo.setPlatformActionListener(new PlatformActionListener() {
            @Override
            public void onComplete(Platform platform, int i, HashMap<String, Object> hashMap) {
                requestThirdPartyLogin("weibo", platform.getDb().getUserId(), platform.getDb().getToken());
            }

            @Override
            public void onError(Platform platform, int i, Throwable throwable) {

            }

            @Override
            public void onCancel(Platform platform, int i) {
                N.showShort(LoginByOtherActivity.this, "取消与微博关联");
            }
        }); // 设置分享事件回调

        weibo.authorize();//单独授权
        weibo.showUser(null);//授权并获取用户信息
        /*mSsoHandler.authorize(new WbAuthListener() {
            @Override
            public void onSuccess(final Oauth2AccessToken oauth2AccessToken) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        requestThirdPartyLogin("weibo", oauth2AccessToken.getUid(), oauth2AccessToken.getToken());
                        if (oauth2AccessToken.isSessionValid()) {
                            AccessTokenKeeper.writeAccessToken(LoginByOtherActivity.this, oauth2AccessToken);
                        }
                    }
                });
            }

            @Override
            public void cancel() {
                N.showShort(LoginByOtherActivity.this, "取消与微博关联");
            }

            @Override
            public void onFailure(WbConnectErrorMessage wbConnectErrorMessage) {

            }
        });*/
    }

    @Bind(R.id.login_number)
    private void openStandradLogin() {
        finish();
    }

    @Bind(R.id.login_back)
    private void back() {
        finish();
    }

    @Event
    private void eWechatLoginSuccess(EventWechatLogin event) {
        requestThirdPartyLogin("weixin", event.getmOpenid(), event.getAccess_token());
    }

    /**
     * 开始发起第三方绑定请求
     * @param type
     * @param openId
     * @param access_token
     */
    private void requestThirdPartyLogin(String type, String openId, String access_token) {
        Request request = Request.obtain(MeInterface.POST_THIRD_LOGIN);
        request.put("oauthType", type);
        request.put("openid", openId);
        request.put("access_token", access_token);
        request.setListener(new SimpleListener<Response<ResponseThirdPartyLogin>>() {
            @Override
            public void onResponseListener(Request r, Response<ResponseThirdPartyLogin> result) {
                if (result.success) {
                    if (!result.data.bindingphone) { //如果未绑定手机号，跳转到手机号绑定
                        startActivityForResult(BindingPhoneActivity.class, REQ_BINDING_PHONE);
                    } else {
                        UserVerify userVerify = new UserVerify();
                        userVerify.id = Long.parseLong(result.data.customerId);
                        userVerify.token = result.data.token;
                        UserManager.getInstance().login(userVerify);
                        requestUserData();
                    }
                } else N.showLong(LoginByOtherActivity.this, "第三方登录调起失败");
            }
        });
        net(request);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode != RESULT_OK) return;
//        mSsoHandler.authorizeCallBack(requestCode, resultCode, data);
        ThirdPartyUtils.getInstance(this).mTencent.onActivityResultData(requestCode, resultCode, data, new IUiListener() {
            @Override
            public void onComplete(Object o) {
                N.showLong(LoginByOtherActivity.this, o.toString());
            }

            @Override
            public void onError(UiError uiError) {
                N.showLong(LoginByOtherActivity.this, "error:" + uiError);
            }

            @Override
            public void onCancel() {
                N.showLong(LoginByOtherActivity.this, "cancel");
            }
        });
        if (requestCode == REQ_BINDING_PHONE && resultCode == RESULT_OK) { //绑定手机成功返回
            requestUserData();
        }
    }
}
