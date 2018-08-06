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
import com.zhangju.xingquban.interestclassapp.refactor.me.bean.ResponseScanCode;

import java.util.List;

/**
 * Created by sgfb on 2017/11/11.
 * 课程验证
 */
@ContentView(R.layout.act_course_verify)
public class CourseVerifyActivity extends FastActivity{
    @Bind(R.id.titleBar)
    TitleBar mTitleBar;
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

    /**
     * 开启扫码
     */
    @Bind(R.id.startSwipeCode)
    private void startSwipeCode(){

    }

    @Bind(R.id.commit)
    private void commit(){
        String code=mCode.getText().toString();

        if(TextUtils.isEmpty(code)){
            N.showShort(this,"劵码不能为空");
            return;
        }
        Request request=Request.obtain(MeInterface.POST_SCAN_CODE);
        request.put("qcode",code);
        request.setListener(new SimpleListener<Response<List<ResponseScanCode>>>(){

            @Override
            public void onResponseListener(Request r, Response<List<ResponseScanCode>> result) {
                if(result.success){
                    //do something
                }
            }
        });
        net(request);
    }
}
