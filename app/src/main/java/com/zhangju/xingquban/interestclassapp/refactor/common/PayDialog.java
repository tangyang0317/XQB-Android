package com.zhangju.xingquban.interestclassapp.refactor.common;

import android.content.Context;
import android.os.Bundle;
import android.view.View;

import com.fastlib.app.BottomDialog;
import com.fastlib.app.FastActivity;
import com.fastlib.net.Request;
import com.fastlib.net.SimpleListener;
import com.zhangju.xingquban.R;
import com.zhangju.xingquban.interestclassapp.refactor.common.bean.Response;
import com.zhangju.xingquban.interestclassapp.refactor.common.utils.ThirdPartyUtils;
import com.zhangju.xingquban.interestclassapp.refactor.me.bean.MeInterface;
import com.zhangju.xingquban.interestclassapp.refactor.me.bean.ResponseExchange;

/**
 * Created by sgfb on 2017/11/23.
 * 底部弹出支付
 */
public class PayDialog extends BottomDialog{
    public static String ARG_STR_ID ="id";

    public static PayDialog getInstance(Context context,String id){
        Bundle bundle=new Bundle();
        PayDialog payDialog=new PayDialog();

        bundle.putString(ARG_STR_ID,id);
        bundle.putInt(ARG_INT_LAYOUT_ID,R.layout.dialog_bottom_pay);
        bundle.putInt(ARG_INT_COLOR,context.getResources().getColor(R.color.translucent_dialog));
        payDialog.setArguments(bundle);
        return payDialog;
    }

    @Override
    protected void bindView(View v) {
        v.findViewById(R.id.cancel).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        v.findViewById(R.id.payByWechat).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                Request request=new Request(MeInterface.POST_EXCHANGE_FUNBEAN);
                request.put("keyname","qqpay");
                request.put("currencyRateId",getArguments().getString(ARG_STR_ID));
                request.setListener(new SimpleListener<Response<ResponseExchange>>(){

                    @Override
                    public void onResponseListener(Request r, Response<ResponseExchange> result) {
                        if(result.success){
                            ResponseExchange data=result.data;
                            ThirdPartyUtils.getInstance(v.getContext()).payByWechat(data.appid,data.partnerid,data.prepayid,data.timestamp,data.packageValue,data.noncestr,data.sign);
                        }
                        finish();
                    }

                    @Override
                    public void onErrorListener(Request r, String error) {
                        super.onErrorListener(r, error);
                        finish();
                    }
                });
                request.start();
            }
        });
        v.findViewById(R.id.payByAlipay).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                Request request=new Request(MeInterface.POST_EXCHANGE_FUNBEAN);
                request.put("keyname","aipay");
                request.put("currencyRateId",getArguments().getString(ARG_STR_ID));
                request.setListener(new SimpleListener<Response<ResponseExchange>>(){

                    @Override
                    public void onResponseListener(Request r, Response<ResponseExchange> result) {
                        if(result.success){
                            ResponseExchange data=result.data;
                            ThirdPartyUtils.getInstance(v.getContext()).payByAli((FastActivity)getActivity(),data.sign);
                        }
                        finish();
                    }

                    @Override
                    public void onErrorListener(Request r, String error) {
                        super.onErrorListener(r, error);
                        finish();
                    }
                });
                request.start();
            }
        });
    }

    private void finish(){
        getFragmentManager()
                .beginTransaction()
                .remove(this)
                .commit();
    }
}
