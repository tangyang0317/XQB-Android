package com.zhangju.xingquban.interestclassapp.refactor.me.adapter;

import android.content.Context;
import android.support.v4.util.Pair;
import android.view.View;
import android.widget.TextView;

import com.fastlib.adapter.FastAdapterForRecycler;
import com.fastlib.base.CommonViewHolder;
import com.zhangju.xingquban.R;
import com.zhangju.xingquban.interestclassapp.adapter.HomeRecylerAdapter;

import java.util.List;

/**
 * Created by sgfb on 2017/11/3.
 * 机构管理功能块适配器
 */
public class OrgFunctionAdapter extends FastAdapterForRecycler<Pair<Integer,String>>{
    private HomeRecylerAdapter.OnItemClickListener mListener;

    public OrgFunctionAdapter(Context context) {
        super(context, R.layout.item_org_manager);
    }

    public OrgFunctionAdapter(Context context, List<Pair<Integer, String>> data) {
        super(context, R.layout.item_org_manager, data);
    }

    @Override
    public void binding(final int position, Pair<Integer, String> data, CommonViewHolder holder) {
        TextView textView= (TextView) holder.getConvertView();
        textView.setCompoundDrawablesWithIntrinsicBounds(0,data.first,0,0);
        textView.setText(data.second);
        if(mListener!=null)
            holder.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mListener.onItemClick(v,position);
                }
            });
    }

    public void setmListener(HomeRecylerAdapter.OnItemClickListener mListener) {
        this.mListener = mListener;
    }
}