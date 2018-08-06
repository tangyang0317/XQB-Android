package com.zhangju.xingquban.interestclassapp.adapter.near;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.zhangju.xingquban.R;
import com.zhangju.xingquban.interestclassapp.bean.Comment_all;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by zsl on 2017/7/21.
 */

public class NearJigouCommentAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> implements View.OnClickListener {
    private Comment_all comment_all;
    private Context mContext;
    private OnItemClickListener mOnItemClickListener = null;
    private CommentPicAdapter commentpicAdapter;

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

    public NearJigouCommentAdapter(Context context, Comment_all mcomment_all) {
        this.mContext = context;
        this.comment_all = mcomment_all;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_me_comment, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        view.setOnClickListener(this);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ViewHolder holder1 = (ViewHolder) holder;

        List<String> piclist=new ArrayList<>();
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(mContext, LinearLayoutManager.HORIZONTAL, false);
        holder1.commentpic_recycle.setLayoutManager(linearLayoutManager);

        for (int i = 0; i <comment_all.getPicList().size() ; i++) {
            piclist.add(comment_all.getPicList().get(i));
        }
        commentpicAdapter  =new CommentPicAdapter(mContext,piclist);
        holder1.commentpic_recycle.setAdapter(commentpicAdapter);
        if(piclist.size()<=0){
            holder1.commentpic_recycle.setVisibility(View.GONE);
        }
        Glide.with(mContext).load(comment_all.getCustomerPicture()).into(holder1.itemMeMessageBg);
        holder1.itemMeMessageName.setText(comment_all.getCustomerName());
        holder1.itemMeMessageContent.setText(comment_all.getSummary());
        holder1.itemMeMessageDay.setText(comment_all.getAddUserTime());
        switch (comment_all.getLevels()) {
                case 0:
                    holder1.start1.setImageResource(R.drawable.home_recyler_item_pingjiatwo);
                    holder1.start2.setImageResource(R.drawable.home_recyler_item_pingjiatwo);
                    holder1.start3.setImageResource(R.drawable.home_recyler_item_pingjiatwo);
                    holder1.start4.setImageResource(R.drawable.home_recyler_item_pingjiatwo);
                    holder1.start5.setImageResource(R.drawable.home_recyler_item_pingjiatwo);
                    break;
                case 1:
                    holder1.start1.setImageResource(R.drawable.home_recyler_item_pingjia);
                    holder1.start2.setImageResource(R.drawable.home_recyler_item_pingjiatwo);
                    holder1.start3.setImageResource(R.drawable.home_recyler_item_pingjiatwo);
                    holder1.start4.setImageResource(R.drawable.home_recyler_item_pingjiatwo);
                    holder1.start5.setImageResource(R.drawable.home_recyler_item_pingjiatwo);
                    break;
                case 2:
                    holder1.start1.setImageResource(R.drawable.home_recyler_item_pingjia);
                    holder1.start2.setImageResource(R.drawable.home_recyler_item_pingjia);
                    holder1.start3.setImageResource(R.drawable.home_recyler_item_pingjiatwo);
                    holder1.start4.setImageResource(R.drawable.home_recyler_item_pingjiatwo);
                    holder1.start5.setImageResource(R.drawable.home_recyler_item_pingjiatwo);
                    break;
                case 3:
                    holder1.start1.setImageResource(R.drawable.home_recyler_item_pingjia);
                    holder1.start2.setImageResource(R.drawable.home_recyler_item_pingjia);
                    holder1.start3.setImageResource(R.drawable.home_recyler_item_pingjia);
                    holder1.start4.setImageResource(R.drawable.home_recyler_item_pingjiatwo);
                    holder1.start5.setImageResource(R.drawable.home_recyler_item_pingjiatwo);
                    break;
                case 4:
                    holder1.start1.setImageResource(R.drawable.home_recyler_item_pingjia);
                    holder1.start2.setImageResource(R.drawable.home_recyler_item_pingjia);
                    holder1.start3.setImageResource(R.drawable.home_recyler_item_pingjia);
                    holder1.start4.setImageResource(R.drawable.home_recyler_item_pingjia);
                    holder1.start5.setImageResource(R.drawable.home_recyler_item_pingjiatwo);
                    break;
                case 5:
                    holder1.start1.setImageResource(R.drawable.home_recyler_item_pingjia);
                    holder1.start2.setImageResource(R.drawable.home_recyler_item_pingjia);
                    holder1.start3.setImageResource(R.drawable.home_recyler_item_pingjia);
                    holder1.start4.setImageResource(R.drawable.home_recyler_item_pingjia);
                    holder1.start5.setImageResource(R.drawable.home_recyler_item_pingjia);
                    break;
            }

        holder.itemView.setTag(position);


    }

    @Override
    public int getItemCount() {
        return 1;
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.item_me_message_bg)
        ImageView itemMeMessageBg;
        @BindView(R.id.item_me_message_name)
        TextView itemMeMessageName;
        @BindView(R.id.text_dafen1)
        TextView textDafen1;
        @BindView(R.id.start1)
        ImageView start1;
        @BindView(R.id.start2)
        ImageView start2;
        @BindView(R.id.start3)
        ImageView start3;
        @BindView(R.id.start4)
        ImageView start4;
        @BindView(R.id.start5)
        ImageView start5;
        @BindView(R.id.item_me_message_content)
        TextView itemMeMessageContent;
        @BindView(R.id.item_me_message_day)
        TextView itemMeMessageDay;
        @BindView(R.id.rv_commentpic)
        RecyclerView commentpic_recycle;

        ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}
