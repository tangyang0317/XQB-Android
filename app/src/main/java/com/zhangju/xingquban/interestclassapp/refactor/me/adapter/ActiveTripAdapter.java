package com.zhangju.xingquban.interestclassapp.refactor.me.adapter;

import android.content.Context;

import com.fastlib.adapter.FastAdapter;
import com.fastlib.adapter.FastAdapterForRecycler;
import com.fastlib.base.CommonViewHolder;
import com.fastlib.base.OldViewHolder;
import com.zhangju.xingquban.R;
import com.zhangju.xingquban.interestclassapp.refactor.me.bean.PublishActiveTrip;

import java.util.List;
import java.util.Locale;

/**
 * Created by Administrator on 2017/12/1.
 * 活动行程列表适配器
 */
public class ActiveTripAdapter extends FastAdapter<PublishActiveTrip> {

    public ActiveTripAdapter(Context context) {
        super(context, R.layout.item_active_trip);
    }

    public ActiveTripAdapter(Context context, List<PublishActiveTrip> data) {
        super(context,R.layout.item_active_trip, data);
    }

    @Override
    public void binding(int position, PublishActiveTrip data, OldViewHolder holder) {
        holder.setText(R.id.time,data.time);
        holder.setText(R.id.titleAndContent,String.format(Locale.getDefault(),"%s\n%s",data.title,data.content));
    }
}