package com.zhangju.xingquban.interestclassapp.refactor.me.activity;

import android.content.Intent;
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
import com.zhangju.xingquban.zxing.activity.CaptureActivity;

/**
 * Created by sgfb on 2017/11/15.
 * 活动手动验票
 */
@ContentView(R.layout.act_active_check_ticket_manual_input)
public class CheckTicketManualActivity extends FastActivity{

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

    @Bind(R.id.commit)
    private void commit(){
        String code=mCode.getText().toString();

        if(TextUtils.isEmpty(code)){
            mCode.setError("劵码不能为空");
            return;
        }
        Intent intent=new Intent();
        intent.putExtra(CaptureActivity.RES_STR_CODE,code);
        setResult(RESULT_OK,intent);
        finish();
    }
}