package com.zhangju.xingquban.interestclassapp.ui.fragment.home.wydp;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.zhangju.xingquban.R;
import com.zhangju.xingquban.interestclassapp.adapter.near.CommentPicAdapter;
import com.zhangju.xingquban.interestclassapp.refactor.common.activity.PreviewImageActivity;
import com.zhangju.xingquban.interestclassapp.ui.fragment.home.HomeDataTeacherBean;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by zsl on 2017/7/21.
 */

public class HomeDataCommentAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> implements View.OnClickListener {
    private HomeDataTeacherBean mHomeDataTeacherBean;
    private Context mContext;
    private OnItemClickListener mOnItemClickListener = null;
    private CommentPicAdapter commentpicAdapter;

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

    public HomeDataCommentAdapter(Context context, HomeDataTeacherBean homeDataTeacherBean) {
        this.mContext = context;
        this.mHomeDataTeacherBean = homeDataTeacherBean;
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

        final ArrayList<String> piclist=new ArrayList<>();
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(mContext, LinearLayoutManager.HORIZONTAL, false);
        holder1.commentpic_recycle.setLayoutManager(linearLayoutManager);

        for (int i = 0; i <mHomeDataTeacherBean.getAaData().get(0).getCommentsList().get(position).picList.size() ; i++) {
            piclist.add(mHomeDataTeacherBean.getAaData().get(0).getCommentsList().get(position).picList.get(i));
        }
        commentpicAdapter  =new CommentPicAdapter(mContext,piclist);
        holder1.commentpic_recycle.setAdapter(commentpicAdapter);
        commentpicAdapter.setOnItemClickListener(new CommentPicAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Intent intent=new Intent(mContext, PreviewImageActivity.class);
                intent.putExtra(PreviewImageActivity.ARG_BOOL_IS_LOCAL,false);
                intent.putExtra(PreviewImageActivity.ARG_INT_INDEX,position);
                intent.putExtra(PreviewImageActivity.ARG_LIST_STR_IMAGES,piclist);
                mContext.startActivity(intent);
            }
        });
        if(piclist.size()<=0){
            holder1.commentpic_recycle.setVisibility(View.GONE);
        }
        Glide.with(mContext).load(mHomeDataTeacherBean.getAaData().get(0).getCommentsList().get(position).customerPicture).error(R.drawable.rec_seize_bitmao).into(holder1.itemMeMessageBg);
        holder1.itemMeMessageName.setText(mHomeDataTeacherBean.getAaData().get(0).getCommentsList().get(position).customerName);
        holder1.itemMeMessageContent.setText(mHomeDataTeacherBean.getAaData().get(0).getCommentsList().get(position).summary);
        holder1.itemMeMessageDay.setText(mHomeDataTeacherBean.getAaData().get(0).getCommentsList().get(position).addUserTime);
        switch (mHomeDataTeacherBean.getAaData().get(0).getCommentsList().get(position).levels) {
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
        return mHomeDataTeacherBean.getAaData().get(0).getCommentsList().size();
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
