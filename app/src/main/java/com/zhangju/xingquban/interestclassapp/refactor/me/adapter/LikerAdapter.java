package com.zhangju.xingquban.interestclassapp.refactor.me.adapter;

import android.app.Activity;
import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.fastlib.adapter.FastAdapterForRecycler;
import com.fastlib.base.CommonViewHolder;
import com.zhangju.xingquban.R;

import java.util.List;

/**
 * Created by Administrator on 2017/11/29.
 *
 */
public class LikerAdapter extends FastAdapterForRecycler<String>{

    public LikerAdapter(Context context) {
        super(context, R.layout.item_liker);
    }

    public LikerAdapter(Context context, List<String> data) {
        super(context,R.layout.item_liker, data);
    }

    @Override
    public void binding(int position, String data, CommonViewHolder holder) {
        Glide.with((Activity) mContext).load(data).into((ImageView)holder.getView(R.id.avatar));
    }
}
