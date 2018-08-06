package com.zhangju.xingquban.interestclassapp.refactor.me.activity;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.text.TextUtils;
import android.util.Base64;
import android.view.View;
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
import com.fastlib.widget.TitleBar;
import com.zhangju.xingquban.R;
import com.zhangju.xingquban.interestclassapp.refactor.common.bean.Response;
import com.zhangju.xingquban.interestclassapp.refactor.common.utils.DESUtil;
import com.zhangju.xingquban.interestclassapp.refactor.me.bean.MeInterface;

import java.util.Locale;

/**
 * Created by sgfb on 2017/11/10.
 * 绑定第三方id和手机号
 */
@ContentView(R.layout.act_binding_phone)
public class BindingPhoneActivity extends FastActivity{
    @Bind(R.id.titleBar)
    TitleBar mTitleBar;
    @Bind(R.id.phone)
    EditText mPhone;
    @Bind(R.id.code)
    EditText mCode;

    @Override
    protected void alreadyPrepared() {
        mTitleBar.setOnLeftClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    @Bind(R.id.getVerifyCode)
    private void getVerifyCode(final View v){
        String phone=mPhone.getText().toString();

        if(TextUtils.isEmpty(phone)){
            N.showShort(this,"手机号不能为空");
            return;
        }
        if(!Utils.isPhoneNumber(phone)){
            N.showShort(this,"请输入标准的手机号");
            return;
        }
        v.setEnabled(false);
        try {
            String verifyCode= Base64.encodeToString(com.zhangju.xingquban.interestclassapp.refactor.common.utils.Utils.aesEncryption("CBC","PKCS5padding","bicikeji@zhangju","zhangju@bicikeji",phone),Base64.DEFAULT).trim();
            String checkCode=Base64.encodeToString(DESUtil.encode(String.valueOf(System.currentTimeMillis())),Base64.DEFAULT).trim();
            verifyCode=verifyCode.replace("+","%2B").replace("\\","%5C").replace(" ","%20").replace("/","%2F");
            checkCode=checkCode.replace("+","%2B").replace("\\","%5C").replace(" ","%20").replace("/","%2F");
            Request request=Request.obtain("get",MeInterface.GET_GET_VERIFY_CODE);
            request.put("types","bindingphone");
            request.put("phone",phone);
            request.put("verifyCode",verifyCode);
            request.put("checkCode",checkCode);
            request.setListener(new SimpleListener<Response>(){

                @Override
                public void onResponseListener(Request r, Response result) {
                    if(result.success){
                        startCountdownTime((TextView) v);
                    }
                    else{
                        v.setEnabled(true);
                    }
                }
            });
            net(request);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Bind(R.id.commit)
    private void commit(){
        String phone=mPhone.getText().toString();
        String code=mCode.getText().toString();

        if(TextUtils.isEmpty(phone)){
            N.showShort(this,"手机号不能为空");
            return;
        }
        if(!Utils.isPhoneNumber(phone)){
            N.showShort(this,"请输入标准的手机号");
            return;
        }
        if(TextUtils.isEmpty(code)){
            N.showShort(this,"验证码不能为空");
            return;
        }
        Request request=Request.obtain(MeInterface.POST_BINDING_PHONE);
        request.put("phone",phone);
        request.put("varCode",code);
        request.setListener(new SimpleListener<Response>(){

            @Override
            public void onResponseListener(Request r, Response result) {
                if(result.success){
                    N.showShort(BindingPhoneActivity.this,"绑定成功");
                    setResult(RESULT_OK);
                    finish();
                }
                else N.showShort(BindingPhoneActivity.this,"绑定失败");
            }
        });
        net(request);
    }

    /**
     * 开始60秒倒计时
     * @param tv
     */
    private void startCountdownTime(final TextView tv){
        ValueAnimator animator=ValueAnimator.ofInt(60,0);
        animator.setDuration(60000);
        animator.setInterpolator(new LinearInterpolator());
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                tv.setText(String.format(Locale.getDefault(),"%s秒后重新获取",animation.getAnimatedValue().toString()));
            }
        });
        animator.addListener(new AnimatorListenerAdapter() {

            @Override
            public void onAnimationStart(Animator animation) {
                super.onAnimationStart(animation);
                tv.setEnabled(false);
            }

            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                tv.setText("获取验证码");
                tv.setEnabled(true);
            }
        });
        animator.start();
    }
}
