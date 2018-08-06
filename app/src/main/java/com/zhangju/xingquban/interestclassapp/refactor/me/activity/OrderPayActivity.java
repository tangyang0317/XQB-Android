package com.zhangju.xingquban.interestclassapp.refactor.me.activity;

import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;

import com.bumptech.glide.Glide;
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
import com.zhangju.xingquban.interestclassapp.refactor.common.utils.ThirdPartyUtils;
import com.zhangju.xingquban.interestclassapp.refactor.me.bean.MeInterface;
import com.zhangju.xingquban.interestclassapp.refactor.me.bean.ResponseExchange;
import com.zhangju.xingquban.interestclassapp.refactor.me.bean.ResponseOrder;
import com.zhangju.xingquban.interestclassapp.refactor.me.bean.ResponsePayType;
import com.zhangju.xingquban.interestclassapp.refactor.me.bean.ResponseWallet;

import java.util.List;
import java.util.Locale;

/**
 * Created by sgfb on 2017/10/30.
 * 支付订单
 */
@ContentView(R.layout.act_order_pay)
public class OrderPayActivity extends FastActivity{
    public static final String ARG_SER_ORDER="order";

    @LocalData(ARG_SER_ORDER)
    ResponseOrder mOrder;
    @Bind(R.id.titleBar)
    TitleBar mTitleBar;
    @Bind(R.id.title)
    TextView mTitle;
    @Bind(R.id.host)
    TextView mDescription;
    @Bind(R.id.price)
    TextView mPrice;
    @Bind(R.id.cover)
    ImageView mCover;
    @Bind(R.id.walletRemainCheck)
    RadioButton mWalletRemainCheck;
    @Bind(R.id.experienceVolumeCheck)
    RadioButton mExperienceVolumeCheck;
    @Bind(R.id.payByWechatCheck)
    RadioButton mPayByWechatCheck;
    @Bind(R.id.payByAlipayCheck)
    RadioButton mPayByAlipayCheck;
    @Bind(R.id.walletRemain)
    TextView mTvWalletRemain;
    @Bind(R.id.experienceVolume)
    TextView mExperienceVolume;
    @Bind(R.id.commit)
    Button mCommit;
    float mWalletRemain=0;

    @Override
    protected void alreadyPrepared() {
        if(mOrder.dlist!=null&&!mOrder.dlist.isEmpty()){
            ResponseOrder.OrderData od=mOrder.dlist.get(0);
            mTitle.setText(od.lessonName);
        }
        mPrice.setText(String.format(Locale.getDefault(),"￥%s",mOrder.amount));
        if(mOrder!=null&&!mOrder.dlist.isEmpty())
            Glide.with(this).load(mOrder.dlist.get(0).lessonImg).into(mCover);
        if(mOrder.dlist!=null&&!mOrder.dlist.isEmpty()&&mOrder.dlist.get(0).lesson!=null)
            mDescription.setText(mOrder.dlist.get(0).lesson.descript);
        mTitleBar.setOnLeftClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        mWalletRemainCheck.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    if(mOrder.amount>mWalletRemain){
                        N.showShort(OrderPayActivity.this,"余额不足");
                        buttonView.setChecked(false);
                    }
                    else{
                        mExperienceVolumeCheck.setChecked(false);
                        mPayByAlipayCheck.setChecked(false);
                        mPayByWechatCheck.setChecked(false);
                    }
                }
            }
        });
        mExperienceVolumeCheck.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    mWalletRemainCheck.setChecked(false);
                    mPayByAlipayCheck.setChecked(false);
                    mPayByWechatCheck.setChecked(false);
                }
            }
        });
        mPayByAlipayCheck.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    mPayByWechatCheck.setChecked(false);
                    mWalletRemainCheck.setChecked(false);
                    mExperienceVolumeCheck.setChecked(false);
                }
            }
        });
        mPayByWechatCheck.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    mPayByAlipayCheck.setChecked(false);
                    mWalletRemainCheck.setChecked(false);
                    mExperienceVolumeCheck.setChecked(false);
                }
            }
        });
        mCommit.setText(String.format(Locale.getDefault(),"确认支付￥%s",mOrder.amount));
        requestPayType();
    }

    /**
     * 请求支付方式
     */
    private void requestPayType(){
        Request request= Request.obtain(MeInterface.POST_PAY_TYPE);
        request.setListener(new SimpleListener<Response<List<ResponsePayType>>>(){

            @Override
            public void onResponseListener(Request r, Response<List<ResponsePayType>> result) {
                if(result.success) {
                    if(result.data!=null){
                        for(ResponsePayType payType:result.data){
                            if("balances".equals(payType.keyname)){
                                mWalletRemain=payType.coinNum;
                                mTvWalletRemain.setText(String.format(Locale.getDefault(),"可用账号余额%s",mWalletRemain));
                            }
                            else if("ordersCoupon".equals(payType.keyname)){
                                mExperienceVolume.setText(String.format(Locale.getDefault(),"%d张，限线下课程单价200元以内使用",mExperienceVolumeCount= (int) payType.coinNum));
                            }
                        }
                    }
                }
            }
        });
        net(request);
    }

    int mExperienceVolumeCount=0;

    @Bind(R.id.walletRemainLayout)
    private void selectWalletRemain(){
        if(mOrder.amount<mWalletRemain)
            mWalletRemainCheck.setChecked(true);
        else N.showShort(this,"余额不足");
    }

    @Bind(R.id.experienceVolumeLayout)
    private void selectExperienceVolume(){
        if(mExperienceVolumeCount>0) mExperienceVolumeCheck.setChecked(true);
        else N.showShort(this,"不可用");
    }

    @Bind(R.id.payByWechat)
    private void selectWechat(){
        mPayByWechatCheck.setChecked(true);
    }

    @Bind(R.id.payByAlipay)
    private void selectAlipay(){
        mPayByAlipayCheck.setChecked(true);
    }

    @Bind(R.id.commit)
    private void commit(){
        if(!mWalletRemainCheck.isChecked()&&!mExperienceVolumeCheck.isChecked()
                &&!mPayByWechatCheck.isChecked()&&!mPayByAlipayCheck.isChecked()){
            N.showLong(this,"请选择支付方式");
            return;
        }
        Request request=Request.obtain(MeInterface.POST_ORDER_PAY);
        request.put("id",mOrder.id);
        if(mWalletRemainCheck.isChecked()) request.put("payType","balances");
        else if(mPayByAlipayCheck.isChecked()) request.put("payType","aipay");
        else if(mPayByWechatCheck.isChecked()) request.put("payType","qqpay");
        else if(mExperienceVolumeCheck.isChecked()) request.put("payType","ordersCoupon");
        request.setListener(new SimpleListener<Response<ResponseExchange>>(){

            @Override
            public void onResponseListener(Request r,Response<ResponseExchange> result) {
                if(result.success){
                    ResponseExchange payResult=result.data;
                    if(mPayByWechatCheck.isChecked())
                        ThirdPartyUtils.getInstance(OrderPayActivity.this).payByWechat(payResult.appid,payResult.partnerid,payResult.prepayid,payResult.timestamp,payResult.packageValue,payResult.noncestr,payResult.sign);
                    else if(mPayByAlipayCheck.isChecked())
                        ThirdPartyUtils.getInstance(OrderPayActivity.this).payByAli(OrderPayActivity.this,payResult.sign);
                    else{
                        N.showLong(OrderPayActivity.this,"支付成功");
                        finish();
                    }
                }
            }
        });
        net(request);
    }
}
