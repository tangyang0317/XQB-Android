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
import com.zhangju.xingquban.interestclassapp.refactor.user.User;
import com.zhangju.xingquban.interestclassapp.refactor.user.UserManager;

import java.util.Locale;

/**
 * Created by sgfb on 2017/11/14.
 * 重新绑定手机号
 */
@ContentView(R.layout.act_rebind_phone)
public class RebindPhoneActivity extends FastActivity{
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

    @Bind(R.id.getCode)
    private void getCode(final View v){
        String phone=mPhone.getText().toString();

        if(TextUtils.isEmpty(phone)||!Utils.isPhoneNumber(phone)){
            mPhone.setError("请输入正确的手机号");
            mPhone.requestFocus();
            return;
        }
        v.setEnabled(false);
        try{
            String verifyCode= Base64.encodeToString(com.zhangju.xingquban.interestclassapp.refactor.common.utils.Utils.aesEncryption("CBC","PKCS5padding","bicikeji@zhangju","zhangju@bicikeji",phone),Base64.DEFAULT).trim();
            String checkCode=Base64.encodeToString(DESUtil.encode(String.valueOf(System.currentTimeMillis())),Base64.DEFAULT).trim();
            verifyCode=verifyCode.replace("+","%2B").replace("\\","%5C").replace(" ","%20").replace("/","%2F");
            checkCode=checkCode.replace("+","%2B").replace("\\","%5C").replace(" ","%20").replace("/","%2F");
            Request request=Request.obtain("get",MeInterface.GET_GET_VERIFY_CODE);
            request.put("types","reviseAccount");
            request.put("phone",phone);
            request.put("verifyCode",verifyCode);
            request.put("checkCode",checkCode);
            request.setListener(new SimpleListener<Response>(){

                @Override
                public void onResponseListener(Request r, Response result) {
                    if(result.success) startCountdown((TextView) v);
                    else v.setEnabled(true);
                }

                @Override
                public void onErrorListener(Request r, String error) {
                    super.onErrorListener(r, error);
                    v.setEnabled(true);
                }
            });
            net(request);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    private void startCountdown(final TextView tv){
        ValueAnimator va=ValueAnimator.ofInt(60,0);
        va.setInterpolator(new LinearInterpolator());
        va.setDuration(60000);
        va.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                tv.setText(String.format(Locale.getDefault(),"%d秒后重新获取",(int)animation.getAnimatedValue()));
            }
        });
        va.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                tv.setEnabled(true);
            }
        });
        va.start();
    }

    @Bind(R.id.commit)
    private void commit(){
        String phone=mPhone.getText().toString();
        String code=mCode.getText().toString();

        if(TextUtils.isEmpty(phone)||!Utils.isPhoneNumber(phone)){
            mPhone.setError("请输入正确的手机号");
            mPhone.requestFocus();
            return;
        }
        if(TextUtils.isEmpty(code)){
            mCode.setError("请输入验证码");
            mCode.requestFocus();
            return;
        }
        Request request=Request.obtain("get",MeInterface.GET_REBIND_PHONE);
        request.put("phone",phone);
        request.put("varCode",code);
        request.setListener(new SimpleListener<Response>(){

            @Override
            public void onResponseListener(Request r, Response result){
                if(result.success){
                    N.showShort(RebindPhoneActivity.this,"绑定成功");
                    User user= UserManager.getInstance().getUser();
                    user.phone=r.getParams().get("phone");
                    UserManager.getInstance().refreshUser(user);
                    finish();
                }
            }
        });
        net(request);
    }
}