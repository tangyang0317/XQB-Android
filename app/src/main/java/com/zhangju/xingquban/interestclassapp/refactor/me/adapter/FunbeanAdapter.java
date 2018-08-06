package com.zhangju.xingquban.interestclassapp.refactor.me.adapter;

import android.content.Context;
import android.graphics.Color;
import android.support.v4.util.Pair;
import android.view.View;
import android.widget.TextView;

import com.fastlib.adapter.FastAdapterForRecycler;
import com.fastlib.base.CommonViewHolder;
import com.zhangju.xingquban.R;
import com.zhangju.xingquban.interestclassapp.refactor.me.bean.ResponseWallet;

import java.text.DecimalFormat;
import java.util.List;
import java.util.Locale;

/**
 * Created by sgfb on 2017/11/23.
 * 兴趣豆兑换汇率表适配器
 */
public class FunbeanAdapter extends FastAdapterForRecycler<ResponseWallet.Rate>{
    private int mSelectItem=0;
    private DecimalFormat mDecimalFormat=new DecimalFormat();

    public FunbeanAdapter(Context context) {
        super(context, R.layout.item_exchange_funbean);
    }

    public FunbeanAdapter(Context context,List<ResponseWallet.Rate> data){
        super(context,R.layout.item_exchange_funbean,data);
    }

    @Override
    public void binding(final int position,ResponseWallet.Rate data, CommonViewHolder holder){
        holder.setText(R.id.beanCount,String.format(Locale.getDefault(),"%d豆",(int)data.toAmount));
        holder.setText(R.id.price,String.format(Locale.getDefault(),"￥%s",mDecimalFormat.format(data.fromAmount)));
        if(mSelectItem==position){
            holder.getConvertView().setBackgroundResource(R.drawable.shape_block_ef4e4c);
            ((TextView)holder.getView(R.id.beanCount)).setTextColor(mContext.getResources().getColor(R.color.EF4E4C));
            ((TextView)holder.getView(R.id.price)).setTextColor(mContext.getResources().getColor(R.color.EF4E4C));
        }
        else{
            holder.getConvertView().setBackgroundResource(R.drawable.shape_block_ffb911);
            ((TextView)holder.getView(R.id.beanCount)).setTextColor(Color.parseColor("#ffb911"));
            ((TextView)holder.getView(R.id.price)).setTextColor(Color.parseColor("#ffb911"));
        }
        holder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mSelectItem=position;
                notifyDataSetChanged();
            }
        });
    }

    public int getmSelectItem() {
        return mSelectItem;
    }
}