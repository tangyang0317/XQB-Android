package com.zhangju.xingquban.interestclassapp.refactor.me.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.widget.ImageView;

import com.fastlib.adapter.FastAdapterForRecycler;
import com.fastlib.base.CommonViewHolder;
import com.fastlib.net.Request;
import com.fastlib.net.SimpleListener;
import com.zhangju.xingquban.R;
import com.zhangju.xingquban.interestclassapp.refactor.common.bean.Response;
import com.zhangju.xingquban.interestclassapp.refactor.me.bean.MeInterface;
import com.zhangju.xingquban.interestclassapp.refactor.me.bean.ResponseBoundAccount;

import java.util.Locale;

/**
 * Created by Administrator on 2017/11/25.
 * 绑定的支付方式列表
 */
public class BoundPayAdapter extends FastAdapterForRecycler<ResponseBoundAccount>{
    private OnClickListener mListener;

    public BoundPayAdapter(Context context,OnClickListener listener){
        super(context, R.layout.item_bound_pay);
        mListener=listener;
    }

    @Override
    public void binding(final int position, final ResponseBoundAccount data, CommonViewHolder holder) {
        if(data.paytype!=null&&"qqpay".equals(data.paytype.keyname)){
            holder.setText(R.id.name,"微信");
            ((ImageView)holder.getView(R.id.payIcon)).setImageResource(R.mipmap.me_wallet_bind_weixin);
        }
        else if(data.paytype!=null&&"aipay".equals(data.paytype.keyname)){
            holder.setText(R.id.name,"支付宝");
            ((ImageView)holder.getView(R.id.payIcon)).setImageResource(R.mipmap.me_wallet_bind_zhifubao);
        }
        else{
            System.out.println("一个未知的绑定支付类型");
        }
        holder.setText(R.id.num,String.format(Locale.getDefault(),"%s %s",data.username,data.account));
        holder.setOnClickListener(R.id.unbind, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                requestUnbind(position,data.id);
            }
        });
        holder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mListener!=null) mListener.onClick(data);
            }
        });
    }

    /**
     * 解除支付绑定
     */
    private void requestUnbind(final int position, String id){
        Request request=new Request(MeInterface.POST_UNBIND_ACCOUNT);
        request.put("id",id);
        request.setListener(new SimpleListener<Response>(){

            @Override
            public void onResponseListener(Request r, Response result) {
                if(result.success){
                    ((Activity)mContext).setResult(Activity.RESULT_OK);
                    getData().remove(position);
                    notifyDataSetChanged();
                }
            }
        });
        request.start();
    }

    public interface OnClickListener{
        void onClick(ResponseBoundAccount data);
    }
}