package com.zhangju.xingquban.interestclassapp.refactor.me.activity;

import android.text.TextUtils;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;

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
import com.zhangju.xingquban.interestclassapp.refactor.user.User;
import com.zhangju.xingquban.interestclassapp.refactor.user.UserManager;

/**
 * Created by sgfb on 2017/10/28.
 * 修改性别
 */
@ContentView(R.layout.act_change_sex)
public class ChangeSexActivity extends FastActivity{
    @Bind(R.id.titleBar)
    TitleBar mTitleBar;
    @Bind(R.id.manCheck)
    CheckBox mManCheck;
    @Bind(R.id.womanCheck)
    CheckBox mWomanCheck;

    @Override
    protected void alreadyPrepared(){
        if(!TextUtils.isEmpty(UserManager.getInstance().getUser().sex)){
            if("M".equals(UserManager.getInstance().getUser().sex.toUpperCase()))
                mManCheck.setChecked(true);
            else mWomanCheck.setChecked(true);
        }
        mManCheck.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked)
                    mWomanCheck.setChecked(false);
            }
        });
        mWomanCheck.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked)
                    mManCheck.setChecked(false);
            }
        });
        mTitleBar.setOnLeftClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    @Bind(R.id.manLine)
    private void selectMan(){
        mManCheck.setChecked(true);
    }

    @Bind(R.id.womanLine)
    private void selectWoman(){
        mWomanCheck.setChecked(true);
    }

    @Bind(R.id.commit)
    private void commit(){
        loading();
        Request request=Request.obtain(MeInterface.POST_CHANGE_USER_DATA);
        final String sex=mManCheck.isChecked()?"m":"f";
        request.put("id",UserManager.getInstance().getUser().id);
        request.put("sex",sex);
        request.setListener(new SimpleListener<Response>(){

            @Override
            public void onResponseListener(Request r, Response result) {
                dismissLoading();
                if(result.success){
                    N.showShort(ChangeSexActivity.this,"修改性别成功");
                    setResult(RESULT_OK);
                    finish();
                }
                else N.showShort(ChangeSexActivity.this,"修改性别失败");
            }

            @Override
            public void onErrorListener(Request r, String error) {
                super.onErrorListener(r, error);
                finish();
            }
        });
        net(request);
    }
}