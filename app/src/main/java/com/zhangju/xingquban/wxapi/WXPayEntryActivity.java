package com.zhangju.xingquban.wxapi;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.fastlib.app.EventObserver;
import com.tencent.mm.sdk.constants.ConstantsAPI;
import com.tencent.mm.sdk.modelbase.BaseReq;
import com.tencent.mm.sdk.modelbase.BaseResp;
import com.tencent.mm.sdk.openapi.IWXAPI;
import com.tencent.mm.sdk.openapi.IWXAPIEventHandler;
import com.zhangju.xingquban.interestclassapp.refactor.common.bean.EventPayResult;
import com.zhangju.xingquban.interestclassapp.refactor.common.utils.ThirdPartyUtils;


public class WXPayEntryActivity extends Activity implements IWXAPIEventHandler {
    private IWXAPI mAPI;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mAPI= ThirdPartyUtils.getInstance(this).mWxAPI;
        mAPI.handleIntent(getIntent(),this);
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        setIntent(intent);
        mAPI.handleIntent(intent,this);
    }

    @Override
    public void onReq(BaseReq req) {

    }

    @Override
    public void onResp(BaseResp resp) {
        if (resp.getType() == ConstantsAPI.COMMAND_PAY_BY_WX) {
            switch (resp.errCode) {
                case 0:EventObserver.getInstance().sendEvent(WXPayEntryActivity.this,new EventPayResult(true,"qqpay"));break;
                case -1:EventObserver.getInstance().sendEvent(WXPayEntryActivity.this,new EventPayResult(false,"qqpay"));break;
                case -2:EventObserver.getInstance().sendEvent(WXPayEntryActivity.this,new EventPayResult(false,"qqpay"));break;
            }
        }
        finish();
    }
}