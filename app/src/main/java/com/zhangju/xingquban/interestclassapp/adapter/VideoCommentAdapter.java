package com.zhangju.xingquban.interestclassapp.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.zhangju.xingquban.R;
import com.zhangju.xingquban.interestclassapp.adapter.baseadpter.BaseRecycleViewAdapter;
import com.zhangju.xingquban.interestclassapp.bean.VideoCommentBean;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by ydw on 2017/11/1.
 */
//视频评论列表adapter
public class VideoCommentAdapter extends BaseRecycleViewAdapter {

    private List<VideoCommentBean> mCommentList;


    public VideoCommentAdapter(Context c, List<VideoCommentBean> mCommentList) {
        super(c);
        this.mCommentList = mCommentList;
    }

    public void addCommentList(List<VideoCommentBean> mList) {
        mCommentList.addAll(mCommentList);
        notifyDataSetChanged();

    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        return new CommentViewHolder(resIdToView(parent, R.layout.item_video_comment));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        CommentViewHolder commentViewHolder = (CommentViewHolder) holder;
        commentViewHolder.onBind();

    }

    @Override
    public int getItemCount() {
        return mCommentList.size();
    }

    class CommentViewHolder extends RecyclerView.ViewHolder {

        int pos;

        @BindView(R.id.image_comment_head)
        ImageView imageCommentHead;
        @BindView(R.id.tv_comment_title)
        TextView tvCommentTitle;
        @BindView(R.id.tv_comment_time)
        TextView tvCommentTime;
        @BindView(R.id.tv_comment_content)
        TextView tvCommentContent;

        public CommentViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);

        }

        void onBind() {
            pos = getLayoutPosition();
            String comtIcon = mCommentList.get(pos).getComtIcon() == null ? "" : mCommentList.get(pos).getComtIcon();//头像
            String comment = mCommentList.get(pos).getComment() == null ? "" : mCommentList.get(pos).getComment();//评论
            String comtName = mCommentList.get(pos).getComtName() == null ? "" : mCommentList.get(pos).getComtName();//评论用户
            String timeStr = mCommentList.get(pos).getTimeStr() == null ? "" : mCommentList.get(pos).getTimeStr();//时间


            Glide.with(c).load(comtIcon).placeholder(R.drawable.app_icon).dontAnimate().into(imageCommentHead);
            tvCommentContent.setText(comment);
            tvCommentTitle.setText(comtName);
            tvCommentTime.setText(timeStr);


        }
    }
}
