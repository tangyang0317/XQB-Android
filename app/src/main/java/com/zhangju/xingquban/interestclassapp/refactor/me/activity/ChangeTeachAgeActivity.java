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
import com.zhangju.xingquban.interestclassapp.refactor.user.User;
import com.zhangju.xingquban.interestclassapp.refactor.user.UserManager;

/**
 * Created by sgfb on 2017/11/16.
 * 修改教龄
 */
@ContentView(R.layout.act_change_teach_age)
public class ChangeTeachAgeActivity extends FastActivity{
    @Bind(R.id.titleBar)
    TitleBar mTitleBar;
    @Bind(R.id.teachAge)
    EditText mTeachAge;

    @Override
    protected void alreadyPrepared() {
        mTitleBar.setOnLeftClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        User user=UserManager.getInstance().getUser();
        if(user.map!=null)
            mTeachAge.append(Integer.toString(user.map.teachAge));
    }

    @Bind(R.id.commit)
    private void commit(){
        final String teachAge=mTeachAge.getText().toString();

        if(TextUtils.isEmpty(teachAge)){
            N.showShort(this,"教龄不能输入空");
            return;
        }
        Request request=Request.obtain(MeInterface.POST_CHANGE_TEACH_DATA);
        request.put("id", UserManager.getInstance().getUser().teacherTimeId);
        request.put("teachAge",teachAge);
        request.setListener(new SimpleListener<Response>(){

            @Override
            public void onResponseListener(Request r, Response result) {
                if(result.success){
                    N.showLong(ChangeTeachAgeActivity.this,"修改教龄成功");
                    setResult(RESULT_OK);
                    finish();
                }
            }
        });
        net(request);
    }
}
