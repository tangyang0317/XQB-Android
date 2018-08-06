package com.zhangju.xingquban.interestclassapp.refactor.me.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.widget.TextView;

import com.fastlib.adapter.FastAdapterForRecycler;
import com.fastlib.base.CommonViewHolder;
import com.zhangju.xingquban.R;

import java.util.List;

/**
 * Created by sgfb on 2017/11/22.
 * 学历列表适配器
 */
public class SelectDegreeAdapter extends FastAdapterForRecycler<String>{
    private int mSelectPosition=-1;

    public SelectDegreeAdapter(Context context){
        super(context, R.layout.item_select_degree);
    }

    public SelectDegreeAdapter(Context context, List<String> data) {
        super(context,R.layout.item_select_degree, data);
    }

    @Override
    public void binding(final int position, String data, CommonViewHolder holder) {
        holder.setText(R.id.degree,data);
        ((TextView)holder.getView(R.id.degree)).setTextColor(mSelectPosition==position?mContext.getResources().getColor(R.color.EF4E4C): Color.BLACK);
        holder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mSelectPosition=position;
                notifyDataSetChanged();
            }
        });
    }

    public int getmSelectPosition() {
        return mSelectPosition;
    }
}