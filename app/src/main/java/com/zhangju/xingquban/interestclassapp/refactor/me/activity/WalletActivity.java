package com.zhangju.xingquban.interestclassapp.refactor.me.activity;

import android.content.Intent;
import android.support.v4.util.Pair;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.fastlib.annotation.Bind;
import com.fastlib.annotation.ContentView;
import com.fastlib.annotation.Event;
import com.fastlib.app.FastActivity;
import com.fastlib.db.KVDatabase;
import com.fastlib.net.Request;
import com.fastlib.net.SimpleListener;
import com.zhangju.xingquban.R;
import com.zhangju.xingquban.interestclassapp.refactor.common.bean.Response;
import com.zhangju.xingquban.interestclassapp.refactor.me.bean.EventWalletChanged;
import com.zhangju.xingquban.interestclassapp.refactor.me.bean.MeInterface;
import com.zhangju.xingquban.interestclassapp.refactor.me.bean.ResponseWallet;
import com.zhangju.xingquban.interestclassapp.refactor.user.User;
import com.zhangju.xingquban.interestclassapp.refactor.user.UserManager;

import java.text.DecimalFormat;

/**
 * Created by sgfb on 2017/10/26.
 * 我的钱包
 */
@ContentView(R.layout.activity_my_wallet)
public class WalletActivity extends FastActivity{
    public static final String SAVE_STR_HAD_WITHDRAWCASH_PASS="hadWithdrawcashPass";

    @Bind(R.id.avatar)
    ImageView mAvatar;
    @Bind(R.id.name)
    TextView mName;
    @Bind(R.id.bind)
    TextView mBind;
    @Bind(R.id.remain)
    TextView mRemain;
    @Bind(R.id.beanCount)
    TextView mBeanCount;
    @Bind(R.id.coinCount)
    TextView mCoinCount;
    @Bind(R.id.ticketCount)
    TextView mTicketCount;
    @Bind(R.id.ticketIncomeCount)
    TextView mTicketIncome;
    ResponseWallet mWallet;

    @Override
    protected void alreadyPrepared(){
        User user= UserManager.getInstance().getUser();

        mName.setText(user.signame);
        Glide.with(this).load(user.picture).into(mAvatar);
        requestWallet();
    }

    /**
     * 请求钱包信息
     */
    private void requestWallet(){
        Request request=Request.obtain(MeInterface.POST_WALLET);
        request.setListener(new SimpleListener<Response<ResponseWallet>>(){

            @Override
            public void onResponseListener(Request r, Response<ResponseWallet> result){
                if(result.success){
                    DecimalFormat df=new DecimalFormat("##.##");
                    mRemain.setText(df.format(result.data.money.coinNum));
                    if(result.data.currency!=null){
                        for(ResponseWallet.WalletRemain walletRemain:result.data.currency){
                            if("兴趣豆".equals(walletRemain.name)){
                                mBeanCount.setText(df.format(walletRemain.coinNum));
                            }
                            else if("兴趣币".equals(walletRemain.name)){
                                mCoinCount.setText(df.format(walletRemain.coinNum));
                            }
                            else if("门票收益".equals(walletRemain.name)){
                                mTicketIncome.setText(df.format(walletRemain.coinNum));
                            }
                            else if("优惠劵".equals(walletRemain.name))
                                mTicketCount.setText(df.format(walletRemain.coinNum));
                        }
                    }
                    KVDatabase kvDb=new KVDatabase(WalletActivity.this);
                    mWallet=result.data;
                    kvDb.setStr(Pair.create(SAVE_STR_HAD_WITHDRAWCASH_PASS,mWallet.isSetTradePwd?"Y":"N"));
                }
            }
        });
        net(request);
    }

    @Bind(R.id.back)
    private void back(){
        finish();
    }

    @Bind(R.id.recharge)
    private void openRecharge(){
        startActivity(ExchangeActivity.class);
    }

    @Bind(R.id.withdraw)
    private void openWithdraw(){
        Intent intent=new Intent(this,WithdrawActivity.class);
        intent.putExtra(WithdrawActivity.ARG_STR_REMAIN,mRemain.getText().toString());
        startActivity(intent);
    }

    @Bind(R.id.bind)
    private void openBind(){
        if(mWallet==null) return;
        startActivity(BoundPayActivity.class);
    }

    @Bind(R.id.funBean)
    private void openFunBean(){
        if(mWallet==null) return;
        Intent intent=new Intent(this,ExchangeFunBeanActivity.class);
        intent.putExtra(ExchangeFunBeanActivity.ARG_SER_WALLET,mWallet);
        startActivity(intent);
    }

    @Bind(R.id.funCoin)
    private void openFunCoin(){
        if(mWallet==null) return;
        Intent intent=new Intent(this,ExchangeFunCoinActivity.class);
        intent.putExtra(ExchangeFunCoinActivity.ARG_SER_WALLET,mWallet);
        startActivity(intent);
    }

    @Bind(R.id.ticketIncome)
    private void openTicketIncome(){
        if(mWallet==null) return;
        Intent intent=new Intent(this,ExchangeTicketIncomeActivity.class);
        intent.putExtra(ExchangeTicketIncomeActivity.ARG_SER_WALLET,mWallet);
        startActivity(intent);
    }

    @Bind(R.id.discountTicket)
    private void openDiscountTicket(){
        startActivity(WalletCouponActivity.class);
    }

    /**
     * 交易记录
     */
    @Bind(R.id.transactionHistory)
    private void openTransactionHistory(){
        Intent intent=new Intent(this,WalletTransactionHistoryActivity.class);
        intent.putExtra(WalletTransactionHistoryActivity.ARG_STR_COIN_ID,"");
        startActivity(intent);
    }

    @Event
    private void eWalletChanged(EventWalletChanged event){
        requestWallet();
    }
}