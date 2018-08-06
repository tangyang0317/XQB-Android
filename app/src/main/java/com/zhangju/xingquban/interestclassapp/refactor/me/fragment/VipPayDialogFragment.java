package com.zhangju.xingquban.interestclassapp.refactor.me.fragment;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioButton;
import android.widget.TextView;

import com.fastlib.app.BottomDialog;
import com.fastlib.app.FastActivity;
import com.fastlib.net.Request;
import com.fastlib.net.SimpleListener;
import com.fastlib.utils.N;
import com.zhangju.xingquban.R;
import com.zhangju.xingquban.interestclassapp.refactor.common.bean.Response;
import com.zhangju.xingquban.interestclassapp.refactor.common.utils.ThirdPartyUtils;
import com.zhangju.xingquban.interestclassapp.refactor.me.bean.MeInterface;
import com.zhangju.xingquban.interestclassapp.refactor.me.bean.ResponseExchange;

/**
 * Created by sgfb on 2017/11/11.
 * 开通会员底部弹窗选择支付方式
 */
public class VipPayDialogFragment extends BottomDialog{
    public static final String ARG_INT_USER_TYPE="userType";

    /**
     * 获取开通会员选择支付方式实例
     * @param context 上下文
     * @param userType 用户类型 1普通 2商户
     * @return 弹窗实例
     */
    public static VipPayDialogFragment getInstance(Context context,int userType){
        Bundle bundle=new Bundle();
        VipPayDialogFragment fragment=new VipPayDialogFragment();
        bundle.putInt(ARG_INT_USER_TYPE,userType);
        bundle.putInt(VipPayDialogFragment.ARG_INT_LAYOUT_ID, R.layout.dialog_open_vip);
        bundle.putInt(VipPayDialogFragment.ARG_INT_COLOR,context.getResources().getColor(com.fastlib.R.color.translucent_dialog));
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    protected void bindView(View v) {
        int userType=getArguments().getInt(ARG_INT_USER_TYPE);
        TextView title= (TextView) v.findViewById(R.id.title);
        TextView vipType= (TextView) v.findViewById(R.id.vipType);
        final RadioButton wechatPayCheck= (RadioButton) v.findViewById(R.id.wechatPayCheck);
        final RadioButton aliPayCheck= (RadioButton) v.findViewById(R.id.alipayCheck);

        if(userType==1){
            title.setText("普通会员");
            vipType.setText("(普通会员)");
        }
        else{
            title.setText("商户会员");
            vipType.setText("(商户会员)");
        }
        v.findViewById(R.id.wechatPayLayout).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                wechatPayCheck.setChecked(true);
                aliPayCheck.setChecked(false);
            }
        });
        v.findViewById(R.id.aliPayLayout).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                wechatPayCheck.setChecked(false);
                aliPayCheck.setChecked(true);
            }
        });
        v.findViewById(R.id.commit).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!wechatPayCheck.isChecked()&&!aliPayCheck.isChecked()){
                    N.showShort(getContext(),"请选择支付方式");
                    return;
                }
                v.setEnabled(false);
                final boolean wechatPay=wechatPayCheck.isChecked();
                Request request=new Request(MeInterface.POST_OPEN_VIP);
                request.put("amount",365);
                request.put("payType",wechatPay?"qqpay":"aipay");
                request.setListener(new SimpleListener<Response<ResponseExchange>>(){

                    @Override
                    public void onResponseListener(Request r, Response<ResponseExchange> result) {
                        if(result.success){
                            ResponseExchange data=result.data;
                            if(wechatPay) ThirdPartyUtils.getInstance(getContext()).payByWechat(data.appid,data.partnerid,data.prepayid,data.timestamp,data.packageValue,data.noncestr,data.sign);
                            else ThirdPartyUtils.getInstance(getContext()).payByAli((FastActivity) getActivity(),data.sign);
                        }
                        else N.showShort(getContext(),"网络异常");
                        dismiss();
                    }

                    @Override
                    public void onErrorListener(Request r, String error) {
                        super.onErrorListener(r, error);
                        dismiss();
                    }
                });
                request.start();
            }
        });
        v.findViewById(R.id.close).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });
    }
}