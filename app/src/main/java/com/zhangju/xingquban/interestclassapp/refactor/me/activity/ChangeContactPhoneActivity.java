package com.zhangju.xingquban.interestclassapp.refactor.me.activity;

import android.support.design.widget.TextInputEditText;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;

import com.fastlib.annotation.Bind;
import com.fastlib.annotation.ContentView;
import com.fastlib.annotation.LocalData;
import com.fastlib.app.FastActivity;
import com.fastlib.net.Request;
import com.fastlib.net.SimpleListener;
import com.fastlib.utils.N;
import com.fastlib.widget.TitleBar;
import com.zhangju.xingquban.R;
import com.zhangju.xingquban.interestclassapp.refactor.common.bean.Response;
import com.zhangju.xingquban.interestclassapp.refactor.me.bean.MeInterface;
import com.zhangju.xingquban.interestclassapp.refactor.user.UserManager;

/**
 * Created by Administrator on 2017/11/29.
 * 修改联系方式
 */
@ContentView(R.layout.act_change_nickname) //复用修改昵称视图
public class ChangeContactPhoneActivity extends FastActivity{
    public static final String ARG_STR_PHONE="phone";

    @LocalData(ARG_STR_PHONE)
    String mOldPhone;
    @Bind(R.id.titleBar)
    TitleBar mTitleBar;
    @Bind(R.id.nickname)
    TextInputEditText mPhone;

    @Override
    protected void alreadyPrepared(){
        mTitleBar.getTitle().setText("修改联系方式");
        mPhone.setHint("请输入新的联系方式");
        mPhone.setText(mOldPhone);
        mTitleBar.setOnLeftClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    @Bind(R.id.commit)
    private void commit(){
        String phone=mPhone.getText().toString();
        if(TextUtils.isEmpty(phone)){
            N.showShort(this,"新的联系方式不能为空");
            return;
        }
        Request request=Request.obtain(MeInterface.POST_CHANGE_TEACH_DATA);
        request.put("id", UserManager.getInstance().getUser().teacherTimeId);
        request.put("contactTel",phone);
        request.setListener(new SimpleListener<Response>(){

            @Override
            public void onResponseListener(Request r, Response result) {
                if(result.success){
                    N.showShort(ChangeContactPhoneActivity.this,"修改成功");
                    setResult(RESULT_OK);
                    finish();
                }
            }
        });
        net(request);
    }
}
