package com.zhangju.xingquban.interestclassapp.refactor.me.adapter;

import android.content.Context;
import android.text.TextUtils;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.fastlib.adapter.FastAdapter;
import com.fastlib.base.OldViewHolder;
import com.zhangju.xingquban.R;
import com.zhangju.xingquban.interestclassapp.refactor.me.bean.ResponseOrder;

import java.util.Locale;

/**
 * Created by sgfb on 2017/11/13.
 * 订单详情中课程列表
 */
public class OrderCourseAdapter extends FastAdapter<ResponseOrder.Lesson>{

    public OrderCourseAdapter(Context context) {
        super(context,R.layout.item_order_detail_course);
    }

    @Override
    public void binding(int position, ResponseOrder.Lesson data, OldViewHolder holder) {
        holder.setText(R.id.name, data.name);
        holder.setText(R.id.price,String.format(Locale.getDefault(),"单价：￥%s",safeStr(data.price)));
        holder.setText(R.id.expireDate,String.format(Locale.getDefault(),"有效期至%s",safeStr(data.lessonDate)));
        Glide.with(mContext).load(data.picture).into((ImageView)holder.getView(R.id.cover));
    }

    public String safeStr(String s){
        return TextUtils.isEmpty(s)?"":s;
    }
}
