package com.zhangju.xingquban.interestclassapp.refactor.me.activity;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.text.TextUtils;
import android.util.Base64;
import android.view.View;
import android.view.animation.LinearInterpolator;
import android.widget.CheckBox;
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
import com.zhangju.xingquban.interestclassapp.ui.fragment.me.Login.AgreementActivity;

/**
 * Created by sgfb on 2017/10/27.
 * 注册界面
 */
@ContentView(R.layout.activity_register)
public class RegisterActivity extends FastActivity{
    @Bind(R.id.phoneNumber)
    EditText mPhone;
    @Bind(R.id.Captcha)
    EditText mCaptcha;
    @Bind(R.id.password)
    EditText mPass;
    @Bind(R.id.confirmPassword)
    EditText mRePass;
    @Bind(R.id.agreed)
    CheckBox mAgreed;

    @Override
    protected void alreadyPrepared(){

    }

    @Bind(R.id.commit)
    private void commit(){
        String phone=mPhone.getText().toString().trim();
        String pass=mPass.getText().toString().trim();
        String repass=mRePass.getText().toString().trim();
        String captcha=mCaptcha.getText().toString().trim();

        if(!mAgreed.isChecked()){
            N.showShort(this,"请阅读并同意用户协议");
            return;
        }
        if(TextUtils.isEmpty(phone)||!Utils.isPhoneNumber(phone)){
            mPhone.setError("请输入正确的手机号码");
            mPhone.requestFocus();
            return;
        }
        if(TextUtils.isEmpty(pass)||pass.length()<5){
            mPass.setError("请输入5位及以上的密码");
            mPass.requestFocus();
            return;
        }
        if(!TextUtils.equals(pass,repass)){
            N.showShort(this,"两次输入的密码不一致");
            return;
        }
        if(TextUtils.isEmpty(captcha)){
            mCaptcha.setError("验证码不能为空");
            mCaptcha.requestFocus();
            return;
        }
        loading();
        Request request=Request.obtain(MeInterface.POST_REGISTER);
        request.put("phone",phone);
        request.put("password",pass);
        request.put("confirmPassword",repass);
        request.put("varCode",captcha);
        request.setListener(new SimpleListener<Response>(){

            @Override
            public void onResponseListener(Request r, Response result) {
                dismissLoading();
                if(result.success){
                    N.showShort(RegisterActivity.this,"注册成功");
                    finish();
                }
                else N.showShort(RegisterActivity.this,"注册失败");
            }

            @Override
            public void onErrorListener(Request r, String error) {
                super.onErrorListener(r, error);
                dismissLoading();
            }
        });
        net(request);
    }

    @Bind(R.id.textView2)
    private void openAgreement(){
        startActivity(AgreementActivity.class);
    }

    @Bind(R.id.gainCaptcha)
    private void getCaptcha(final View view){
        String phone=mPhone.getText().toString();
        if(TextUtils.isEmpty(phone)|| !Utils.isPhoneNumber(phone)){
            mPhone.setError("请输入正确的手机号码");
            mPhone.requestFocus();
            return;
        }
        view.setEnabled(false);
        try{
            String verifyCode= Base64.encodeToString(com.zhangju.xingquban.interestclassapp.refactor.common.utils.Utils.aesEncryption("CBC","PKCS5padding","bicikeji@zhangju","zhangju@bicikeji",phone),Base64.DEFAULT).trim();
            String checkCode=Base64.encodeToString(DESUtil.encode(String.valueOf(System.currentTimeMillis())),Base64.DEFAULT).trim();
            verifyCode=verifyCode.replace("+","%2B").replace("\\","%5C").replace(" ","%20").replace("/","%2F");
            checkCode=checkCode.replace("+","%2B").replace("\\","%5C").replace(" ","%20").replace("/","%2F");
            Request request=Request.obtain("get",MeInterface.GET_GET_VERIFY_CODE);
            request.put("types","register");
            request.put("phone",phone);
            request.put("verifyCode",verifyCode);
            request.put("checkCode",checkCode);
            request.setListener(new SimpleListener<Response>(){
                @Override
                public void onResponseListener(Request r, Response result){
                    if(result.success){
                        N.showShort(RegisterActivity.this,"获取验证码成功");
                        startCountdownTime(view);
                    }
                    else view.setEnabled(true);
                }

                @Override
                public void onErrorListener(Request r, String error) {
                    super.onErrorListener(r, error);
                    view.setEnabled(true);
                }
            });
            net(request);
        }catch (Exception e){
            e.printStackTrace();
            view.setEnabled(true);
        }
    }

    @Bind(R.id.back)
    private void back(){
        finish();
    }

    /**
     * 开始重新获取验证码倒计时
     * @param view
     */
    private void startCountdownTime(final View view){
        ValueAnimator countdownAnim=ValueAnimator.ofInt(60,0);
        countdownAnim.setInterpolator(new LinearInterpolator());
        countdownAnim.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                ((TextView)view).setText("获取验证码");
                view.setEnabled(true);
            }
        });
        countdownAnim.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation){
                ((TextView)view).setText("验证码("+animation.getAnimatedValue().toString()+")秒");
            }
        });
        countdownAnim.setDuration(60000);
        countdownAnim.start();
    }
}