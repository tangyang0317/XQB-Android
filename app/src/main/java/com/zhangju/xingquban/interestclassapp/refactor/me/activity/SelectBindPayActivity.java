package com.zhangju.xingquban.interestclassapp.refactor.me.activity;

import android.content.Intent;
import android.view.View;

import com.fastlib.annotation.Bind;
import com.fastlib.annotation.ContentView;
import com.fastlib.annotation.LocalData;
import com.fastlib.app.FastActivity;
import com.fastlib.widget.TitleBar;
import com.zhangju.xingquban.R;

/**
 * Created by Administrator on 2017/11/25.
 * 选择绑定提现和支付账户
 */
@ContentView(R.layout.act_select_bind_pay)
public class SelectBindPayActivity extends FastActivity{
    public static final int REQ_BIND_PAY=1;

    @Bind(R.id.titleBar)
    TitleBar mTitleBar;

    @Override
    protected void alreadyPrepared() {
        mTitleBar.setOnLeftClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    @Bind(R.id.bindAlipay)
    private void openBindAlipay(){
        Intent intent=new Intent(this,BindPayActivity.class);
        intent.putExtra(BindPayActivity.ARG_INT_TYPE,BindPayActivity.TYPE_ALIPAY);
        startActivityForResult(intent,REQ_BIND_PAY);
    }

    @Bind(R.id.bindWechatPay)
    private void openBindWechatPay(){
        Intent intent=new Intent(this,BindPayActivity.class);
        intent.putExtra(BindPayActivity.ARG_INT_TYPE,BindPayActivity.TYPE_WECHAT_PAY);
        startActivityForResult(intent,REQ_BIND_PAY);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode==RESULT_OK){
            setResult(RESULT_OK);
            finish();
        }
    }
}
