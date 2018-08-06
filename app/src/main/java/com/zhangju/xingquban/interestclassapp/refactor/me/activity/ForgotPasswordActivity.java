package com.zhangju.xingquban.interestclassapp.refactor.me.activity;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.text.TextUtils;
import android.util.Base64;
import android.view.animation.LinearInterpolator;
import android.widget.EditText;
import android.widget.TextView;

import com.fastlib.annotation.Bind;
import com.fastlib.annotation.ContentView;
import com.fastlib.app.FastActivity;
import com.fastlib.net.Request;
import com.fastlib.net.SimpleListener;
import com.fastlib.utils.N;
import com.fastlib.utils.Utils;
import com.zhangju.xingquban.R;
import com.zhangju.xingquban.interestclassapp.refactor.common.bean.Response;
import com.zhangju.xingquban.interestclassapp.refactor.common.utils.DESUtil;
import com.zhangju.xingquban.interestclassapp.refactor.me.bean.MeInterface;

/**
 * Created by sgfb on 2017/10/27.
 * 忘记密码
 */
@ContentView(R.layout.activity_forget)
public class ForgotPasswordActivity extends FastActivity{
    @Bind(R.id.phoneNumber)
    EditText mPhone;
    @Bind(R.id.password)
    EditText mPass;
    @Bind(R.id.passwordAgain)
    EditText mRePass;
    @Bind(R.id.Captcha)
    EditText mCaptcha;

    @Override
    protected void alreadyPrepared() {

    }

    @Bind(R.id.gainCaptcha)
    private void requestCaptcha(final TextView tv){
        String phone=mPhone.getText().toString();
        if(TextUtils.isEmpty(phone)||!Utils.isPhoneNumber(phone)){
            mPhone.setError("请输入正确的手机号");
            mPhone.requestFocus();
            return;
        }
        loading();
        try{
            String verifyCode= Base64.encodeToString(com.zhangju.xingquban.interestclassapp.refactor.common.utils.Utils.aesEncryption("CBC","PKCS5padding","bicikeji@zhangju","zhangju@bicikeji",phone),Base64.DEFAULT).trim();
            String checkCode=Base64.encodeToString(DESUtil.encode(String.valueOf(System.currentTimeMillis())),Base64.DEFAULT).trim();
            verifyCode=verifyCode.replace("+","%2B").replace("\\","%5C").replace(" ","%20").replace("/","%2F");
            checkCode=checkCode.replace("+","%2B").replace("\\","%5C").replace(" ","%20").replace("/","%2F");
            final Request request=Request.obtain("get",MeInterface.GET_GET_VERIFY_CODE);
            request.put("types","forgetPwd");
            request.put("phone",phone);
            request.put("verifyCode",verifyCode);
            request.put("checkCode",checkCode);
            request.setListener(new SimpleListener<Response>(){

                @Override
                public void onResponseListener(Request r, Response result) {
                    dismissLoading();
                    if(result.success){
                        N.showShort(ForgotPasswordActivity.this, "验证码发送成功,请注意查收");
                        tv.setEnabled(false);
                        countdownTime(tv);
                    }
                    else N.showShort(ForgotPasswordActivity.this,"验证码发送失败");
                }

                @Override
                public void onErrorListener(Request r, String error) {
                    super.onErrorListener(r, error);
                    dismissLoading();
                }
            });
            net(request);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    private void countdownTime(final TextView tv){
        ValueAnimator countdownTimeAnim=ValueAnimator.ofInt(60,0);
        countdownTimeAnim.setInterpolator(new LinearInterpolator());
        countdownTimeAnim.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                tv.setText("获取验证码");
                tv.setEnabled(true);
            }
        });
        countdownTimeAnim.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                tv.setText("验证码("+animation.getAnimatedValue().toString()+")秒");
            }
        });
        countdownTimeAnim.setDuration(60000);
        countdownTimeAnim.start();
    }

    @Bind(R.id.confirmButton)
    private void commit(){
        String phone=mPhone.getText().toString();
        String code=mCaptcha.getText().toString();
        String pass=mPass.getText().toString();
        String repass=mRePass.getText().toString();

        if(TextUtils.isEmpty(phone)||!Utils.isPhoneNumber(phone)){
            mPhone.setError("请输入正确的手机号");
            mPhone.requestFocus();
            return;
        }
        if(TextUtils.isEmpty(code)){
            mCaptcha.setError("验证码不能为空");
            mCaptcha.requestFocus();
            return;
        }
        if(TextUtils.isEmpty(pass)){
            mPass.setError("密码不能为空");
            mPass.requestFocus();
            return;
        }
        if(!TextUtils.equals(pass,repass)){
            N.showShort(this,"两次输入的密码不一致");
            return;
        }
        loading();
        Request request=Request.obtain(MeInterface.POST_FORGOT_PASS);
        request.put("phone",phone);
        request.put("newPwd",pass);
        request.put("cfmNewPwd",repass);
        request.put("varCode",code);
        request.setListener(new SimpleListener<Response>(){

            @Override
            public void onResponseListener(Request r, Response result) {
                dismissLoading();
                if(result.success){
                    N.showShort(ForgotPasswordActivity.this,"密码找回成功");
                    finish();
                }
                else N.showShort(ForgotPasswordActivity.this,"密码找回失败");
            }

            @Override
            public void onErrorListener(Request r, String error) {
                super.onErrorListener(r, error);
                dismissLoading();
            }
        });
        net(request);
    }

    @Bind(R.id.back)
    private void back(){
        finish();
    }
}
