package com.zhangju.xingquban.interestclassapp.adapter;

import android.content.Context;
import android.graphics.drawable.Drawable;
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
 * create by hqf
 */


public class AudioResourceAdapter extends BaseRecycleViewAdapter {


    private List<ResouecesAll.AaDataBean> mAudioList;

    public AudioResourceAdapter(Context c, List<ResouecesAll.AaDataBean> mAudioList) {
        super(c);
        this.mAudioList = mAudioList;

    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new AudioViewHodler(resIdToView(parent, R.layout.audio_resource_item));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        AudioViewHodler audioViewHodler = (AudioViewHodler) holder;
        audioViewHodler.onBind();

    }

    @Override
    public int getItemCount() {
        return mAudioList.size();
    }

    class AudioViewHodler extends RecyclerView.ViewHolder {

        int pos;
        @BindView(R.id.img_back_pic)
        ImageView imgBackPic;
        @BindView(R.id.tv_audio_ischarge)
        TextView tvAudioIscharge;
        @BindView(R.id.tv_audio_time)
        TextView tvAudioTime;
        @BindView(R.id.tv_audio_title)
        TextView tvAudioTitle;
        @BindView(R.id.tv_audio_price)
        TextView tvAudioPrice;
        @BindView(R.id.tv_audio_count)
        TextView tvAudioCount;
        @BindView(R.id.img_author_head)
        RoundImageView imgAuthorHead;
        @BindView(R.id.tv_author_name)
        TextView tvAuthorName;
        @BindView(R.id.tv_audio_comment_num)
        TextView tvAudioCommentNum;
        @BindView(R.id.tv_audio_love_num)
        TextView tvAudioLoveNum;
        @BindView(R.id.thumbCount)
        TextView mThumbCount;

        public AudioViewHodler(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        void onBind() {
            pos = getLayoutPosition();
            registerOnItemClickListener(pos, itemView);
            final ResouecesAll.AaDataBean dataBean = mAudioList.get(pos);
            if (dataBean != null) {
                String title = dataBean.getTitle() == null ? "" : dataBean.getTitle();//标题
                String titlePicture = dataBean.getTitlePicture() == null ? "" : dataBean.getTitlePicture();//背景图
                int collectionCounts = dataBean.getCollectionCounts();//收藏数量
                int commentCounts = dataBean.getCommentCounts();//评论数
                String author = dataBean.getCustomerName() == null ? "" : dataBean.getCustomerName();//作者
                String authorPicture = dataBean.getAuthorPicture() == null ? "" : dataBean.getAuthorPicture();//用户头像
                int isCharge = dataBean.getIsCharge();//0  免费
                int clickRate = dataBean.getClickRate();//播放量
                int thumb = dataBean.getResourceExit();//0 无  1点赞
                Object price = dataBean.getPrice() == null ? "" : dataBean.getPrice();//价格
//                setDrawableLeft(c,thumb==0?R.drawable.icon_like_12_12:R.drawable.red_love,tvAudioLoveNum);
                mThumbCount.setText(String.format(Locale.getDefault(),"点赞%d次",dataBean.getLikes()));

                if (isCharge == 0) {
                    tvAudioIscharge.setBackgroundResource(R.drawable.back_transparent);
                    tvAudioIscharge.setText("免费");
                    tvAudioPrice.setText("免费");

                } else {
                    tvAudioIscharge.setBackgroundResource(R.drawable.back_translate_red);
                    tvAudioIscharge.setText("付费");
                    tvAudioPrice.setText("¥" + price.toString());
                }
                tvAudioCount.setText(clickRate + "次播放");
                tvAudioTitle.setText(title);
                tvAudioLoveNum.setText(collectionCounts + "");
                tvAudioCommentNum.setText(commentCounts + "");
                if (author.isEmpty()) {
                    tvAuthorName.setText("未知");
                } else {
                    tvAuthorName.setText(author);
                }
                Glide.with(c).load(authorPicture).placeholder(R.drawable.app_icon).dontAnimate().dontTransform().into(imgAuthorHead);
                Glide.with(c).load(titlePicture).placeholder(R.mipmap.default_image).dontAnimate().dontTransform().into(imgBackPic);

                tvAudioCommentNum.setOnClickListener(new View.OnClickListener() {
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
    //setDrawble
    public void setDrawableLeft(Context context, int resId, TextView textView) {
        Drawable drawable = context.getResources().getDrawable(resId);
        drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
        textView.setCompoundDrawables(drawable, null, null, null);
    }

    private AllTypeResourceAdapter.MyOnClickListener mMyOnClickListener;

    public void setMyOnClickListener(AllTypeResourceAdapter.MyOnClickListener myOnClickListener) {
        mMyOnClickListener = myOnClickListener;
    }

    public interface MyOnClickListener {
        void onChildClickListener(int pos, String id);
    }
}
