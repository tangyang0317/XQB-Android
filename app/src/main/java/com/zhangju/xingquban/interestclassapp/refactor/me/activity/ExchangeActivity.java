package com.zhangju.xingquban.interestclassapp.refactor.me.activity;

import android.text.TextUtils;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RadioButton;

import com.fastlib.annotation.Bind;
import com.fastlib.annotation.ContentView;
import com.fastlib.annotation.Event;
import com.fastlib.app.EventObserver;
import com.fastlib.app.FastActivity;
import com.fastlib.net.Request;
import com.fastlib.net.SimpleListener;
import com.fastlib.utils.KeyBoardUtils;
import com.fastlib.utils.N;
import com.fastlib.widget.TitleBar;
import com.zhangju.xingquban.R;
import com.zhangju.xingquban.interestclassapp.refactor.common.bean.EventPayResult;
import com.zhangju.xingquban.interestclassapp.refactor.common.bean.Response;
import com.zhangju.xingquban.interestclassapp.refactor.common.utils.ThirdPartyUtils;
import com.zhangju.xingquban.interestclassapp.refactor.me.bean.EventWalletChanged;
import com.zhangju.xingquban.interestclassapp.refactor.me.bean.MeInterface;
import com.zhangju.xingquban.interestclassapp.refactor.me.bean.ResponseExchange;

/**
 * Created by Administrator on 2017/11/27.
 * 充值
 */
@ContentView(R.layout.act_exchange)
public class ExchangeActivity extends FastActivity{
    @Bind(R.id.titleBar)
    TitleBar mTitleBar;
    @Bind(R.id.exchangeCount)
    EditText mExchangeCount;
    @Bind(R.id.wechatCheck)
    RadioButton mWechatCheck;
    @Bind(R.id.alipayCheck)
    RadioButton mAlipayCheck;

    CompoundButton.OnCheckedChangeListener mWechatCheckListener=new CompoundButton.OnCheckedChangeListener() {
        @Override
        public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
            mAlipayCheck.setOnCheckedChangeListener(null);
            mAlipayCheck.setChecked(false);
            mAlipayCheck.setOnCheckedChangeListener(mAliCheckListener);
        }
    };
    CompoundButton.OnCheckedChangeListener mAliCheckListener=new CompoundButton.OnCheckedChangeListener() {
        @Override
        public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
            mWechatCheck.setOnCheckedChangeListener(null);
            mWechatCheck.setChecked(false);
            mWechatCheck.setOnCheckedChangeListener(mWechatCheckListener);
        }
    };

    @Override
    protected void alreadyPrepared() {
        mTitleBar.setOnLeftClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        mWechatCheck.setOnCheckedChangeListener(mWechatCheckListener);
        mAlipayCheck.setOnCheckedChangeListener(mAliCheckListener);
    }

    @Bind(R.id.commit)
    private void commit(View v){
        KeyBoardUtils.closeKeybord(this);
        String exchangeCountStr=mExchangeCount.getText().toString();
        if(TextUtils.isEmpty(exchangeCountStr)){
            mExchangeCount.setError("请输入充值金额");
            return;
        }
        if(!mAlipayCheck.isChecked()&&!mWechatCheck.isChecked()){
            N.showSnackbarShort(v,"请选择支付方式");
            return;
        }
        loading();
        Request request=Request.obtain("get", MeInterface.GET_EXCHANGE);
        request.put("amount",exchangeCountStr);
        request.put("paytype",mWechatCheck.isChecked()?"qqpay":"aipay");
        request.setListener(new SimpleListener<Response<ResponseExchange>>(){

            @Override
            public void onResponseListener(Request r, Response<ResponseExchange> result) {
                dismissLoading();
                if(result.success){
                    ThirdPartyUtils thirdPartyUtils=ThirdPartyUtils.getInstance(ExchangeActivity.this);
                    if(mWechatCheck.isChecked()) thirdPartyUtils.payByWechat(result.data.appid,result.data.partnerid,result.data.prepayid,
                            result.data.timestamp,result.data.packageValue,result.data.noncestr,result.data.sign);
                    else thirdPartyUtils.payByAli(ExchangeActivity.this,result.data.sign);
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

    @Event
    private void  PayResult(EventPayResult payResult){
        if(payResult.isSuccess()){
            N.showLong(ExchangeActivity.this,"充值成功");
            EventObserver.getInstance().sendEvent(ExchangeActivity.this,new EventWalletChanged());
            finish();
        }
    }

    @Bind(R.id.wechatPayLayout)
    private void selectWechatPay(){
        mWechatCheck.setChecked(true);
    }

    @Bind(R.id.aliPayLayout)
    private void selectAlipay(){
        mAlipayCheck.setChecked(true);
    }
}
