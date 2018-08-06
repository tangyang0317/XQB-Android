package com.zhangju.xingquban.interestclassapp.adapter.near;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.fastlib.widget.RoundImageView;
import com.zhangju.xingquban.R;
import com.zhangju.xingquban.interestclassapp.bean.near.NeatVideoComment;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by zsl on 2017/7/21.
 */

public class VideoCommentAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> implements View.OnClickListener {

    private Context mContext;
    private OnItemClickListener mOnItemClickListener = null;
    private NeatVideoComment videoComment;
    //define interface
    public static interface OnItemClickListener {
        void onItemClick(View view, int position);
    }

    @Override
    public void onClick(View v) {
        if (mOnItemClickListener != null) {
            //注意这里使用getTag方法获取position
            mOnItemClickListener.onItemClick(v, (int) v.getTag());
        }
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.mOnItemClickListener = listener;
    }

    public VideoCommentAdapter(Context context, NeatVideoComment mcomment) {
        this.mContext = context;
        this.videoComment = mcomment;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.comment_second_item, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);

        view.setOnClickListener(this);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ViewHolder holder1 = (ViewHolder) holder;
        Glide.with(mContext).load(videoComment.getAaData().get(position).getCustomerPicture()).into(holder1.avatar);
        holder1.name.setText(videoComment.getAaData().get(position).getCustomerName());
        holder1.commnet.setText(videoComment.getAaData().get(position).getTitle());

        holder.itemView.setTag(position);


    }

    @Override
    public int getItemCount() {
        return videoComment.getAaData().size();
    }


    static class ViewHolder extends  RecyclerView.ViewHolder{
        @BindView(R.id.avatar)
        RoundImageView avatar;
        @BindView(R.id.name)
        TextView name;
        @BindView(R.id.commnet)
        TextView commnet;

        ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}
