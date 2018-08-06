package com.zhangju.xingquban.interestclassapp.refactor.common.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.fastlib.adapter.FastAdapterForRecycler;
import com.fastlib.base.CommonViewHolder;
import com.zhangju.xingquban.R;

import java.util.List;

/**
 * Created by Administrator on 2017/12/4.
 * 筛选下拉左边列表适配器
 */
public class PopupList2Adapter extends FastAdapterForRecycler<String>{
    private int mSelectedPosition=-1;
    private ListView.OnItemClickListener mListener;

    public PopupList2Adapter(Context context) {
        super(context, R.layout.item_filter_popup2);
    }

    public PopupList2Adapter(Context context, List<String> data) {
        super(context, R.layout.item_filter_popup2, data);
    }

    @Override
    public void binding(final int position, String data, CommonViewHolder holder) {
        holder.setText(R.id.text,data);
        holder.getConvertView().setBackgroundColor(mSelectedPosition==position? Color.WHITE:Color.parseColor("#EFEFEF"));
        holder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mListener!=null) mListener.onItemClick(null,v,position,0);
                mSelectedPosition=position;
                notifyDataSetChanged();
            }
        });
    }

    public void setSelectedPosition(int position){
        mSelectedPosition=position;
        notifyDataSetChanged();
    }

    public void setListener(AdapterView.OnItemClickListener listener){
        mListener=listener;
    }
}