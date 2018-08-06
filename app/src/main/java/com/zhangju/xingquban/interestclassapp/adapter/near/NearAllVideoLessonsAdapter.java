package com.zhangju.xingquban.interestclassapp.adapter.near;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.zhangju.xingquban.R;
import com.zhangju.xingquban.interestclassapp.bean.HomeRecylerBean;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by zsl on 2017/8/21.
 */

public class NearAllVideoLessonsAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> implements View.OnClickListener {
    private HomeRecylerBean.AaDataBean mHomeRecyclerbean;
    private Context mContext;
    private OnItemClickListener mOnItemClickListener = null;

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

    public NearAllVideoLessonsAdapter(Context context, HomeRecylerBean.AaDataBean lessonsBean) {
        this.mContext = context;
        this.mHomeRecyclerbean = lessonsBean;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_near_spkc, parent, false);
        MyViewHolder myViewHolder = new MyViewHolder(view);
        view.setOnClickListener(this);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        MyViewHolder holder1 = (MyViewHolder) holder;
        Glide.with(mContext).load(mHomeRecyclerbean.getVideoLesson().get(position).getVideoTitlePic()).into(holder1.imgBackimg);
        holder1.tvVideoName.setText(mHomeRecyclerbean.getVideoLesson().get(position).getTitle());
        holder1.tvtime.setText(mHomeRecyclerbean.getVideoLesson().get(position).getEditUserTime());
        holder.itemView.setTag(position);
    }

    @Override
    public int getItemCount() {
        return mHomeRecyclerbean.getVideoLesson().size();
    }


    static class MyViewHolder extends RecyclerView.ViewHolder{
        @BindView(R.id.red_point)
        ImageView redPoint;
        @BindView(R.id.tv_vide_time)
        TextView tvtime;
        @BindView(R.id.rl_head)
        RelativeLayout rlHead;
        @BindView(R.id.img_backimg)
        ImageView imgBackimg;
        @BindView(R.id.tv_video_name)
        TextView tvVideoName;

        MyViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}
