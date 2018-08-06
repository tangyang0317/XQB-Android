package com.zhangju.xingquban.interestclassapp.refactor.me.activity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.util.Pair;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.fastlib.annotation.Bind;
import com.fastlib.annotation.ContentView;
import com.fastlib.annotation.LocalData;
import com.fastlib.app.EventObserver;
import com.fastlib.app.FastActivity;
import com.fastlib.app.FastDialog;
import com.fastlib.db.KVDatabase;
import com.fastlib.net.Request;
import com.fastlib.net.SimpleListener;
import com.fastlib.utils.N;
import com.fastlib.widget.TitleBar;
import com.zhangju.xingquban.R;
import com.zhangju.xingquban.interestclassapp.refactor.common.bean.Response;
import com.zhangju.xingquban.interestclassapp.refactor.me.bean.EventWalletChanged;
import com.zhangju.xingquban.interestclassapp.refactor.me.bean.MeInterface;
import com.zhangju.xingquban.interestclassapp.refactor.me.fragment.WithdrawCashPwdDialog;

/**
 * Created by Administrator on 2017/11/25.
 * 绑定支付和提现账户
 */
@ContentView(R.layout.act_bind_pay)
public class BindPayActivity extends FastActivity{
    public static final String ARG_INT_TYPE="类型"; //1支付宝 2微信
    public static final int TYPE_ALIPAY=1;
    public static final int TYPE_WECHAT_PAY=2;

    @LocalData(ARG_INT_TYPE)
    int mType;
    @Bind(R.id.titleBar)
    TitleBar mTitleBar;
    @Bind(R.id.icon)
    ImageView mIcon;
    @Bind(R.id.hint)
    TextView mHint;
    @Bind(R.id.accountTitle)
    TextView mAccountTitle;
    @Bind(R.id.account)
    EditText mAccount;
    @Bind(R.id.name)
    EditText mName;

    @Override
    protected void alreadyPrepared() {
        if(mType==TYPE_ALIPAY){
            mIcon.setImageResource(R.mipmap.me_wallet_bind_zhifubao);
            mHint.setText("绑定的支付宝账号将成为你的提现账户");
            mAccountTitle.setText("支付宝账号");
            mAccount.setHint("请输入支付宝账号");
        }
        else{
            mIcon.setImageResource(R.mipmap.me_wallet_bind_weixin);
            mHint.setText("绑定的微信账号将成为你的提现账户");
            mAccountTitle.setText("微信账号");
            mAccount.setHint("请输入微信账号");
        }
        mTitleBar.setOnLeftClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    @Bind(R.id.commit)
    private void commit(){
        final String account=mAccount.getText().toString();
        final String name=mName.getText().toString();
        KVDatabase kvDb=new KVDatabase(this);

        if(TextUtils.isEmpty(account)){
            mAccount.setError("请输入您的账号");
            return;
        }
        if(TextUtils.isEmpty(name)){
            mName.setError("请输入您的真实姓名");
            return;
        }
        if("Y".equals(kvDb.getStr(WalletActivity.SAVE_STR_HAD_WITHDRAWCASH_PASS))){
            requestBind(account,name);
        }
        else {
            Bundle bundle=new Bundle();
            WithdrawCashPwdDialog dialog=new WithdrawCashPwdDialog();

            bundle.putString(WithdrawCashPwdDialog.ARG_STR_TITLE,"此密码为提现专用支付密码");
            bundle.putString(WithdrawCashPwdDialog.ARG_STR_MESSAGE,"");
            dialog.setArguments(bundle);
            dialog.show(getSupportFragmentManager(), new WithdrawCashPwdDialog.OnClickListener() {
                @Override
                public void onComplete(final String pass) {
                    if(pass.length()>5){
                        FastDialog.showMessageDialog("您输入的密码为："+pass,true).setTitle("请确认").show(getSupportFragmentManager(), new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which){
                                requestSetTradePassword(pass,account,name);
                            }
                        });
                    }
                }
            });
        }
    }

    /**
     * 设置提现密码,如果成功再绑定钱包账户
     * @param pass 提现密码
     * @param account 绑定的用户账户
     * @param name 用户名
     */
    private void requestSetTradePassword(String pass, final String account, final String name){
        Request request=Request.obtain(MeInterface.POST_SET_TRADE_PASSWORD);
        request.put("tradePwd",pass);
        request.setListener(new SimpleListener<Response>(){

            @Override
            public void onResponseListener(Request r, Response result) {
                if(result.success) {
                    KVDatabase kvDatabase=new KVDatabase(BindPayActivity.this);

                    kvDatabase.setStr(Pair.create(WalletActivity.SAVE_STR_HAD_WITHDRAWCASH_PASS,"Y"));
                    EventObserver.getInstance().sendEvent(BindPayActivity.this,new EventWalletChanged());
                    requestBind(account,name);
                }
            }
        });
        net(request);
    }

    /**
     * 请求绑定钱包账户
     * @param account 用户账号
     * @param name 用户名
     */
    private void requestBind(String account,String name){
        Request request=Request.obtain(MeInterface.POST_BIND_PAY);
        request.put("account",account);
        request.put("username",name);
        request.put("keyname",mType==1?"aipay":"qqpay");
        request.setListener(new SimpleListener<Response>(){

            @Override
            public void onResponseListener(Request r, Response result) {
                if(result.success){
                    N.showShort(BindPayActivity.this,"绑定成功");
                    setResult(RESULT_OK);
                    finish();
                }
            }
        });
        net(request);
    }
}
