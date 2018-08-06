package com.zhangju.xingquban.interestclassapp.refactor.me.activity;

import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;

import com.fastlib.annotation.Bind;
import com.fastlib.annotation.ContentView;
import com.fastlib.app.FastActivity;
import com.fastlib.net.Request;
import com.fastlib.net.SimpleListener;
import com.fastlib.utils.N;
import com.fastlib.widget.TitleBar;
import com.zhangju.xingquban.R;
import com.zhangju.xingquban.interestclassapp.refactor.common.bean.Response;
import com.zhangju.xingquban.interestclassapp.refactor.me.bean.MeInterface;

/**
 * Created by sgfb on 2017/10/28.
 * 修改密码
 */
@ContentView(R.layout.act_change_pass)
public class ChangePasswordActivity extends FastActivity{
    @Bind(R.id.titleBar)
    TitleBar mTitleBar;
    @Bind(R.id.oldPass)
    EditText mOldPass;
    @Bind(R.id.newPass)
    EditText mNewPass;

    @Override
    protected void alreadyPrepared() {
        mTitleBar.setOnLeftClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    @Bind(R.id.commit)
    private void commit(){
        String oldPass=mOldPass.getText().toString().trim();
        String newPass=mNewPass.getText().toString().trim();

        if(TextUtils.isEmpty(oldPass)){
            N.showSnackbarShort(mOldPass,"旧密码不能为空");
            return;
        }
        if(TextUtils.isEmpty(newPass)||newPass.length()<5){
            N.showSnackbarShort(mNewPass,"新密码不能小于5位");
            return;
        }
        loading();
        Request request=Request.obtain(MeInterface.POST_CHANGE_PASS);
        request.put("oldPwd",oldPass);
        request.put("newPwd",newPass);
        request.put("cfmNewPwd",newPass);
        request.setListener(new SimpleListener<Response>(){

            @Override
            public void onResponseListener(Request r, Response result) {
                dismissLoading();
                if(result.success){
                    N.showShort(ChangePasswordActivity.this,"修改密码成功");
                    finish();
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
}
