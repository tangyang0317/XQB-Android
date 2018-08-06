package com.zhangju.xingquban.interestclassapp.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.zhangju.xingquban.R;
import com.zhangju.xingquban.interestclassapp.adapter.baseadpter.BaseRecycleViewAdapter;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by ydw on 2017/11/11.
 * <p>
 * 资源评论详情二级adapter
 */

public class ResDetailCommentAdapter extends BaseRecycleViewAdapter {




    public ResDetailCommentAdapter(Context c) {
        super(c);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ComViewHolder(resIdToView(parent, R.layout.item_video_comment));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    class ComViewHolder extends RecyclerView.ViewHolder {

        int pos;
        @BindView(R.id.image_comment_head)
        ImageView imageCommentHead;
        @BindView(R.id.tv_comment_title)
        TextView tvCommentTitle;
        @BindView(R.id.tv_comment_time)
        TextView tvCommentTime;
        @BindView(R.id.tv_comment_content)
        TextView tvCommentContent;


        public ComViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        void onBind() {
            pos = getLayoutPosition();
        }
    }
}
