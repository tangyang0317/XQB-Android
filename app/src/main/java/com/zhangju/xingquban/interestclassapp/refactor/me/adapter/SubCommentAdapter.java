package com.zhangju.xingquban.interestclassapp.refactor.me.adapter;

import android.app.Activity;
import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.fastlib.adapter.FastAdapterForRecycler;
import com.fastlib.base.CommonViewHolder;
import com.zhangju.xingquban.R;
import com.zhangju.xingquban.interestclassapp.refactor.me.bean.ResponseComment;

import java.util.List;

/**
 * Created by Administrator on 2017/11/29.
 * 评论中的子评论适配器
 */
public class SubCommentAdapter extends FastAdapterForRecycler<ResponseComment.SubComment>{

    public SubCommentAdapter(Context context, List<ResponseComment.SubComment> data) {
        super(context, R.layout.item_subcomment,data);
    }

    @Override
    public void binding(int position, ResponseComment.SubComment data, CommonViewHolder holder) {
        holder.setText(R.id.name,data.customerName);
        holder.setText(R.id.comment,data.summary);
        Glide.with((Activity)mContext).load(data.customerPicture).into((ImageView)holder.getView(R.id.avatar));
    }
}