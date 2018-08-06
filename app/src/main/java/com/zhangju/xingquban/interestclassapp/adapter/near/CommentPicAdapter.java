package com.zhangju.xingquban.interestclassapp.adapter.near;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.zhangju.xingquban.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by zsl on 2017/7/21.
 */

public class CommentPicAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> implements View.OnClickListener {
    private List<String> piclist = new ArrayList<>();
    private Context mContext;
    private OnItemClickListener mOnItemClickListener = null;

    //define interfacequestion
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

    public CommentPicAdapter(Context context, List<String> mpiclist) {
        this.mContext = context;
        this.piclist = mpiclist;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.comment_pic_item, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);

        view.setOnClickListener(this);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ViewHolder holder1 = (ViewHolder) holder;
        Glide.with(mContext).load(piclist.get(position)).error(R.drawable.rec_seize_bitmao).into(holder1.commentPic);


        holder.itemView.setTag(position);


    }

    @Override
    public int getItemCount() {
        return piclist.size();
    }


    static class ViewHolder extends RecyclerView.ViewHolder{
        @BindView(R.id.comment_pic)
        ImageView commentPic;

       public ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}
