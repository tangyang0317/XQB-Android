package com.zhangju.xingquban.interestclassapp.refactor.me.activity;

import android.content.Intent;
import android.os.Bundle;
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
import com.fastlib.net.Request;
import com.fastlib.net.SimpleListener;
import com.fastlib.utils.N;
import com.fastlib.widget.TitleBar;
import com.zhangju.xingquban.R;
import com.zhangju.xingquban.interestclassapp.refactor.common.bean.Response;
import com.zhangju.xingquban.interestclassapp.refactor.me.bean.EventWalletChanged;
import com.zhangju.xingquban.interestclassapp.refactor.me.bean.MeInterface;
import com.zhangju.xingquban.interestclassapp.refactor.me.bean.ResponseBoundAccount;
import com.zhangju.xingquban.interestclassapp.refactor.me.fragment.WithdrawCashPwdDialog;

import java.util.List;

/**
 * Created by Administrator on 2017/11/27.
 * 提现
 */
@ContentView(R.layout.act_withdraw)
public class WithdrawActivity extends FastActivity{
    public static final String ARG_STR_REMAIN="remain";

    @LocalData(ARG_STR_REMAIN)
    String mRemain;
    @Bind(R.id.titleBar)
    TitleBar mTitleBar;
    @Bind(R.id.remainCount)
    TextView mRemainCount;
    @Bind(R.id.name)
    TextView mName;
    @Bind(R.id.account)
    TextView mAccount;
    @Bind(R.id.withdrawCount)
    EditText mWithdrawCount;
    @Bind(R.id.accountIcon)
    ImageView mAccountIcon;
    @Bind(R.id.withdrawAccountLayout)
    View mWithdrawAccountLayout;
    @Bind(R.id.bindHintLayout)
    View mBindHintLayout;
    String mCoinCardId="-1";

    @Override
    protected void alreadyPrepared(){
        mRemainCount.setText(mRemain);
        mTitleBar.setOnLeftClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        requestAccountList();
    }

    private void requestAccountList(){
        Request request=Request.obtain(MeInterface.POST_BOUND_ACCOUNT_LIST);
        request.setListener(new SimpleListener<Response<List<ResponseBoundAccount>>>(){

            @Override
            public void onResponseListener(Request r, Response<List<ResponseBoundAccount>> result) {
                if(result.success){
                    if(result.data==null||result.data.isEmpty())
                        inflaterAccount(null);
                    else inflaterAccount(result.data.get(0));
                }
            }
        });
        net(request);
    }

    @Bind(R.id.commit)
    private void commit(){
        final String withdrawCount=mWithdrawCount.getText().toString();
        if(mWithdrawAccountLayout.getVisibility()!=View.VISIBLE){
            N.showShort(this,"请选择要提现的账户");
            return;
        }
        if(TextUtils.isEmpty(withdrawCount)){
            mWithdrawCount.setError("请输入提现金额");
            return;
        }
        final WithdrawCashPwdDialog dialog=new WithdrawCashPwdDialog();
        Bundle bundle=new Bundle();
        bundle.putString(WithdrawCashPwdDialog.ARG_STR_TITLE,"提现金额（元）");
        bundle.putString(WithdrawCashPwdDialog.ARG_STR_MESSAGE,withdrawCount);
        dialog.setArguments(bundle);
        dialog.show(getSupportFragmentManager(), new WithdrawCashPwdDialog.OnClickListener() {
            @Override
            public void onComplete(String pass){
                if(pass.length()>5){
                    Request request=Request.obtain(MeInterface.POST_WITHDRAW);
                    request.put("amount",withdrawCount);
                    request.put("coinCardId",mCoinCardId);
                    request.put("currencyId",1);
                    request.put("tradePwd",pass);
                    request.setListener(new SimpleListener<Response>(){

                        @Override
                        public void onResponseListener(Request r, Response result) {
                            if(result.success) {
                                N.showLong(WithdrawActivity.this,"提现成功");
                                EventObserver.getInstance().sendEvent(WithdrawActivity.this,new EventWalletChanged());
                                finish();
                            }
                        }
                    });
                    net(request);
                    getSupportFragmentManager()
                            .beginTransaction()
                            .remove(dialog)
                            .commit();
                }
            }
        });
    }

    @Bind({R.id.bindHintLayout,R.id.withdrawAccountLayout})
    private void bindNewAccount(){
        Intent intent=new Intent(this,BoundPayActivity.class);
        intent.putExtra(BoundPayActivity.ARG_BOOL_CAN_SELECT,true);
        startActivityForResult(intent,1);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode==RESULT_OK){
            if(data!=null&&data.hasExtra(BoundPayActivity.RES_SER_BOUND_ACCOUNT)){
                ResponseBoundAccount account= (ResponseBoundAccount) data.getSerializableExtra(BoundPayActivity.RES_SER_BOUND_ACCOUNT);
                inflaterAccount(account);
            }
            else inflaterAccount(null);
        }
    }

    private void inflaterAccount(ResponseBoundAccount account){
        if(account==null) {
            mWithdrawAccountLayout.setVisibility(View.GONE);
            mBindHintLayout.setVisibility(View.VISIBLE);
        }
        else{
            mBindHintLayout.setVisibility(View.GONE);
            mWithdrawAccountLayout.setVisibility(View.VISIBLE);
            mCoinCardId=account.id;
            mName.setText(account.username);
            mAccount.setText(account.account);
            mAccountIcon.setImageResource("aipay".equals(account.paytype.keyname)?R.mipmap.me_wallet_bind_zhifubao:R.mipmap.me_wallet_bind_weixin);
        }
    }
}
