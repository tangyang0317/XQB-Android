package com.zhangju.xingquban.interestclassapp.refactor.me.adapter;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.fastlib.adapter.FastAdapterForRecycler;
import com.fastlib.base.CommonViewHolder;
import com.zhangju.xingquban.R;
import com.zhangju.xingquban.interestclassapp.refactor.common.bean.ResponseProvince;

/**
 * Created by sgfb on 2017/11/22.
 * 省份列表适配器
 */
public class ApplyToMemberLocationAdapter extends FastAdapterForRecycler<ResponseProvince>{
    private OnClickListener mListener;

    public ApplyToMemberLocationAdapter(Context context,OnClickListener listener){
        super(context, R.layout.item_apply_member_location);
        mListener=listener;
    }

    @Override
    public void binding(int position, final ResponseProvince data, CommonViewHolder holder) {
        holder.setText(R.id.name,data.name);
        holder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mListener!=null) mListener.onClick(data);
            }
        });
    }

    public interface OnClickListener{
        void onClick(ResponseProvince data);
    }
}