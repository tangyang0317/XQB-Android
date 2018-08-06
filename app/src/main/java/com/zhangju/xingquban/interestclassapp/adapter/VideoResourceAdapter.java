package com.zhangju.xingquban.interestclassapp.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.fastlib.widget.RoundImageView;
import com.zhangju.xingquban.R;
import com.zhangju.xingquban.interestclassapp.adapter.baseadpter.BaseRecycleViewAdapter;
import com.zhangju.xingquban.interestclassapp.bean.ResouecesAll;

import java.util.List;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by ydw on 2017/11/9.
 */

public class VideoResourceAdapter
        extends BaseRecycleViewAdapter {

    private List<ResouecesAll.AaDataBean> mVideoList;

    public VideoResourceAdapter(Context c, List<ResouecesAll.AaDataBean> mVideoList) {
        super(c);
        this.mVideoList = mVideoList;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new VideoViewHolder(resIdToView(parent, R.layout.video_resource_item));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        VideoViewHolder videoViewHolder = (VideoViewHolder) holder;
        videoViewHolder.onBind();
    }

    @Override
    public int getItemCount() {
        return mVideoList.size();
    }

    class VideoViewHolder
            extends RecyclerView.ViewHolder {

        int pos;
        @BindView(R.id.img_back_pic)
        ImageView      imgBackPic;
        @BindView(R.id.tv_video_ischarge)
        TextView       tvVideoIscharge;
        @BindView(R.id.tv_video_time)
        TextView       tvVideoTime;
        @BindView(R.id.tv_video_title)
        TextView       tvVideoTitle;
        @BindView(R.id.tv_video_price)
        TextView       tvVideoPrice;
        @BindView(R.id.tv_video_count)
        TextView       tvVideoCount;
        @BindView(R.id.img_author_head)
        RoundImageView imgAuthorHead;
        @BindView(R.id.tv_author_name)
        TextView       tvAuthorName;
        @BindView(R.id.tv_video_comment_num)
        TextView       tvVideoCommentNum;
        @BindView(R.id.tv_video_love_num)
        TextView       tvVideoLoveNum;
        @BindView(R.id.thumbCount)
        TextView       mThumbCount;

        public VideoViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        void onBind() {
            pos = getLayoutPosition();
            registerOnItemClickListener(pos, itemView);
            final ResouecesAll.AaDataBean dataBean = mVideoList.get(pos);
            if (dataBean != null) {
                String title = dataBean.getTitle() == null ? "" : dataBean.getTitle();//标题
                String titlePicture = dataBean.getTitlePicture() == null ? "" : dataBean.getTitlePicture();//背景图
                int likes = dataBean.getCollectionCounts();//喜欢
                int commentCounts = dataBean.getCommentCounts();//评论数
                String author = dataBean.getCustomerName() == null ? "" : dataBean.getCustomerName();//昵称
                String authorPicture = dataBean.getAuthorPicture() == null ? "" : dataBean.getAuthorPicture();//用户头像
                int clickRate = dataBean.getClickRate();//播放量
                int isCharge = dataBean.getIsCharge();
                int thumb = dataBean.getResourceExit();

//                DrawableUtils.setDrawableLeft(c, thumb == 0 ? R.drawable.icon_like_12_12 : R.drawable.red_love, tvVideoLoveNum);
                Object price = dataBean.getPrice() == null ? "" : dataBean.getPrice();//价格
                if (isCharge == 0) {
                    tvVideoIscharge.setText("免费");
                    tvVideoIscharge.setBackgroundResource(R.drawable.back_transparent);
                    tvVideoPrice.setText("免费");
                } else {
                    tvVideoIscharge.setText("付费");
                    tvVideoIscharge.setBackgroundResource(R.drawable.back_translate_red);
                    tvVideoPrice.setText("¥" + price.toString());

                }

                mThumbCount.setText(String.format(Locale.getDefault(), "点赞%d次", dataBean.getLikes()));
                tvVideoCount.setText(clickRate + "次播放");
                tvVideoTitle.setText(title);
                tvVideoLoveNum.setText(likes + "");
                tvVideoCommentNum.setText(commentCounts + "");
                tvAuthorName.setText(author);
                Glide.with(c).load(authorPicture).placeholder(R.drawable.default_icon).dontAnimate().dontTransform().into
                        (imgAuthorHead);
                Glide.with(c).load(titlePicture).placeholder(R.mipmap.default_image).dontAnimate().dontTransform().into
                        (imgBackPic);

                tvVideoCommentNum.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (mMyOnClickListener != null) {
                            mMyOnClickListener.onChildClickListener(pos, dataBean.getId());
                        }
                    }
                });
            }
        }
    }

    private AllTypeResourceAdapter.MyOnClickListener mMyOnClickListener;

    public void setMyOnClickListener(AllTypeResourceAdapter.MyOnClickListener myOnClickListener) {
        mMyOnClickListener = myOnClickListener;
    }

    public interface MyOnClickListener {
        void onChildClickListener(int pos, String id);
    }
}
