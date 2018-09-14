package com.zhangju.xingquban.interestclassapp.adapter.near;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.zhangju.xingquban.R;
import com.zhangju.xingquban.interestclassapp.bean.NearDataBean;
import com.zhangju.xingquban.interestclassapp.ui.fragment.home.HomeDataTeacherBean;
import com.zhangju.xingquban.interestclassapp.view.imageView.CustomRoundView;
import com.zhangju.xingquban.interestclassapp.view.imageView.TopOvelImageView;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by zsl on 2017/7/21.
 */

public class LiveVideoNearAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> implements View.OnClickListener {

    private Context mContext;
    private OnItemClickListener mOnItemClickListener = null;
    private HomeDataTeacherBean liveVdoBean;

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

    public LiveVideoNearAdapter(Context context, HomeDataTeacherBean mcomment) {
        this.mContext = context;
        this.liveVdoBean = mcomment;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.attention_live_item, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);

        view.setOnClickListener(this);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ViewHolder holder1 = (ViewHolder) holder;
        Glide.with(mContext).load(liveVdoBean.getAaData().get(0).getLiveVdo().getRoomPic()).placeholder(R.drawable.app_icon).dontTransform().dontAnimate().into(holder1.imgResourceSmall);
        Glide.with(mContext).load(liveVdoBean.getAaData().get(0).getPicture().isEmpty() ? R.drawable.default_icon : liveVdoBean.getAaData().get(0).getPicture()).into(holder1.imgAuthorHead);
        holder1.tvResourceTitle.setText(liveVdoBean.getAaData().get(0).getLiveVdo().getRoomName());
        holder1.tvResourceLooks.setText(liveVdoBean.getAaData().get(0).getLiveVdo().getOnlineUserCount() + "人在看");
        holder1.tvVideoCommentnum.setText(liveVdoBean.getAaData().get(0).getLiveVdo().getComtCount() + "");
        holder1.tvVideoStartnum.setText(liveVdoBean.getAaData().get(0).getLiveVdo().getFollows() + "");
        holder1.tvResourceMoney.setText("¥" + liveVdoBean.getAaData().get(0).getLiveVdo().getAmount() + "");
        holder.itemView.setTag(position);


    }

    @Override
    public int getItemCount() {
        return 1;
    }


    static class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.img_resource_small)
        ImageView imgResourceSmall;
        @BindView(R.id.tv_video_time)
        TextView tvVideoTime;
        @BindView(R.id.tv_resource_type)
        TextView tvResourceType;
        @BindView(R.id.tv_resource_title)
        TextView tvResourceTitle;
        @BindView(R.id.tv_resource_money)
        TextView tvResourceMoney;
        @BindView(R.id.tv_resource_looks)
        TextView tvResourceLooks;
        @BindView(R.id.img_author_head)
        CustomRoundView imgAuthorHead;
        @BindView(R.id.tv_author_name)
        TextView tvAuthorName;
        @BindView(R.id.img_video_comment)
        ImageView imgVideoComment;
        @BindView(R.id.tv_video_commentnum)
        TextView tvVideoCommentnum;
        @BindView(R.id.img_video_start)
        ImageView imgVideoStart;
        @BindView(R.id.tv_video_startnum)
        TextView tvVideoStartnum;

        ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}
