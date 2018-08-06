package com.zhangju.xingquban.interestclassapp.refactor.common.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.widget.TextView;

import com.fastlib.adapter.FastAdapterForRecycler;
import com.fastlib.base.CommonViewHolder;
import com.zhangju.xingquban.R;
import com.zhangju.xingquban.interestclassapp.refactor.common.PopupListFragment;

import java.util.List;

/**
 * Created by Administrator on 2017/12/4.
 * 弹出列表适配器
 */
public class PopupListAdapter extends FastAdapterForRecycler<String>{
    private PopupListFragment.OnListItemClickListener mListener;
    private int mSelectPosition=0;

    public PopupListAdapter(Context context){
        super(context,R.layout.item_filter_popup);
    }

    public PopupListAdapter(Context context, List<String> data){
        super(context,R.layout.item_filter_popup,data);
    }

    @Override
    public void binding(final int position, final String data, CommonViewHolder holder) {
        TextView text=holder.getView(R.id.text);
        View line=holder.getView(R.id.line);

        text.setText(data);
        if(mSelectPosition==position){
            text.setTextColor(Color.parseColor("#EF4E4C"));
            line.setBackgroundColor(Color.parseColor("#EF4E4C"));
        }
        else{
            text.setTextColor(Color.parseColor("#444444"));
            line.setBackgroundColor(Color.parseColor("#eeeeee"));
        }
        holder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                mSelectPosition=position;
                if(mListener!=null) mListener.onItemClicked(false,-1,position,data);
                notifyDataSetChanged();
            }
        });
    }

    public void setListener(PopupListFragment.OnListItemClickListener listener){
        mListener=listener;
    }

    public void setSelectPosition(int position){
        mSelectPosition=position;
        notifyDataSetChanged();
    }
}
