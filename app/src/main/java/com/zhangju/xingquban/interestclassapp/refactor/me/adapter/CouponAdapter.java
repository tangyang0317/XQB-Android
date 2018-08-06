package com.zhangju.xingquban.interestclassapp.refactor.me.adapter;

import android.content.Context;

import com.fastlib.adapter.FastAdapterForRecycler;
import com.fastlib.base.CommonViewHolder;
import com.zhangju.xingquban.R;
import com.zhangju.xingquban.interestclassapp.refactor.me.bean.ResponseCoupon;

import java.util.List;
import java.util.Locale;

/**
 * Created by Administrator on 2017/11/25.
 * 优惠劵适配器
 */
public class CouponAdapter extends FastAdapterForRecycler<ResponseCoupon>{

    public CouponAdapter(Context context) {
        super(context, R.layout.item_wallet_ticket);
    }

    public CouponAdapter(Context context, List<ResponseCoupon> data) {
        super(context,R.layout.item_wallet_ticket, data);
    }

    @Override
    public void binding(int position, ResponseCoupon data, CommonViewHolder holder) {
        holder.setText(R.id.context,data.context);
        holder.setText(R.id.useCondition,data.useconditions);
        holder.setText(R.id.validDate,String.format(Locale.getDefault(),"有效期：%s至%s",data.couponsvalidtime,data.couponsinvalidtime));
    }
}