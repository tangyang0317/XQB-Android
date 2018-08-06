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
import com.zhangju.xingquban.interestclassapp.bean.ResDetailBean;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @author hqf
 *         Created by ydw on 2017/11/11.
 *         资源音频详情adapter
 */

public class AudioDetailAdapter extends BaseRecycleViewAdapter {

    private View mViewHead;
    private List<ResDetailBean.AaDataBean> mCommentList;

    private int TYPE_TOP = 0x01;
    private int TYPE_COMMENT = 0x02;


    public AudioDetailAdapter(Context c, List<ResDetailBean.AaDataBean> mCommentList, View mViewHead) {
        super(c);
        this.mCommentList = mCommentList;
        this.mViewHead = mViewHead;

    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == TYPE_TOP) {
            return new TopViewHolder(mViewHead);
        }
        return new CommentViewHolder(resIdToView(parent, R.layout.item_video_comment));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder != null) {
            if (holder instanceof TopViewHolder) {
                TopViewHolder topViewHolder = (TopViewHolder) holder;
                topViewHolder.onBind();
            } else if (holder instanceof CommentViewHolder) {
                CommentViewHolder commentViewHolder = (CommentViewHolder) holder;
                commentViewHolder.onBind();
            }
        }

    }

    @Override
    public int getItemCount() {
        return mCommentList.size() + 1;
    }

    @Override
    public int getItemViewType(int position) {
        if (position == 0)
            return TYPE_TOP;
        return TYPE_COMMENT;
    }

    class TopViewHolder extends RecyclerView.ViewHolder {
        int pos;

        public TopViewHolder(View itemView) {
            super(itemView);
        }

        void onBind() {
            pos = getLayoutPosition();
        }
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
            pos = getLayoutPosition() - 1;
            ResDetailBean.AaDataBean dataBean = mCommentList.get(pos);
            String customerPicture = dataBean.getCustomerPicture() == null ? "" : dataBean.getCustomerPicture();//头像
            String customerName = dataBean.getCustomerName() == null ? "" : dataBean.getCustomerName();//用户
            String summary = dataBean.getSummary() == null ? "" : dataBean.getSummary();//内容
            String addUserTime = dataBean.getAddUserTime() == null ? "" : dataBean.getAddUserTime();//时间

            Glide.with(c).load(customerPicture).placeholder(R.drawable.app_icon).dontAnimate().into(imageCommentHead);
            tvCommentContent.setText(summary);
            tvCommentTitle.setText(customerName);
            tvCommentTime.setText(addUserTime);


        }
    }


}
