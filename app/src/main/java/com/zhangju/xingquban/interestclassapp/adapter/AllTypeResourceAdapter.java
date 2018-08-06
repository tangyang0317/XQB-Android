package com.zhangju.xingquban.interestclassapp.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.fastlib.widget.RoundImageView;
import com.zhangju.xingquban.R;
import com.zhangju.xingquban.interestclassapp.adapter.baseadpter.BaseRecycleViewAdapter;
import com.zhangju.xingquban.interestclassapp.adapter.baseadpter.OnListItemClickListener;
import com.zhangju.xingquban.interestclassapp.bean.ResouecesAll;
import com.zhangju.xingquban.interestclassapp.hplper.ScrollGridManager;
import com.zhangju.xingquban.interestclassapp.ui.fragment.find.SeekResource.NewsDetailActivity;
import com.zhangju.xingquban.interestclassapp.ui.fragment.find.SeekResource.PicDetailActivity;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * create by hqf 2017/10/10
 */

public class AllTypeResourceAdapter
        extends BaseRecycleViewAdapter {


    private List<ResouecesAll.AaDataBean> resAllList;
    private int TYPE_AUDIO        = 0x111;
    private int TYPE_VIDEO        = 0x222;
    private int TYPE_IMAGE_SINGLE = 0x333;
    private int TYPE_IMAGE_MULTI  = 0x666;
    private int TYPE_NEWS_SINGLE  = 0x444;
    private int TYPE_NEWS_MULTI   = 0x555;


    public AllTypeResourceAdapter(Context c, List<ResouecesAll.AaDataBean> resAllList) {
        super(c);
        this.resAllList = resAllList;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == TYPE_AUDIO) {
            return new AudioViewHodler(resIdToView(parent, R.layout.audio_resource_item));
        } else if (viewType == TYPE_VIDEO) {
            return new VideoViewHolder(resIdToView(parent, R.layout.video_resource_item));
        } else if (viewType == TYPE_IMAGE_MULTI) {
            return new PicViewHolder(resIdToView(parent, R.layout.article_resource_itemtype2));
        } else if (viewType == TYPE_NEWS_SINGLE) {
            return new SingleViewHolder(resIdToView(parent, R.layout.article_resource_item));
        } else if (viewType == TYPE_NEWS_MULTI) {
            return new MultiViewHolder(resIdToView(parent, R.layout.article_resource_itemtype2));
        } else if (viewType == TYPE_IMAGE_SINGLE) {
            return new SinglePicViewHolder(resIdToView(parent, R.layout.article_resource_item));
        }
        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder != null) {
            if (holder instanceof AudioViewHodler) {
                AudioViewHodler audioViewHodler = (AudioViewHodler) holder;
                audioViewHodler.onBind();
            } else if (holder instanceof VideoViewHolder) {
                VideoViewHolder videoViewHolder = (VideoViewHolder) holder;
                videoViewHolder.onBind();
            } else if (holder instanceof PicViewHolder) {
                PicViewHolder picViewHolder = (PicViewHolder) holder;
                picViewHolder.onBind();
            } else if (holder instanceof SingleViewHolder) {
                SingleViewHolder singleViewHolder = (SingleViewHolder) holder;
                singleViewHolder.onBind();
            } else if (holder instanceof MultiViewHolder) {
                MultiViewHolder multiViewHolder = (MultiViewHolder) holder;
                multiViewHolder.onBind();
            } else if (holder instanceof SinglePicViewHolder) {
                SinglePicViewHolder singlePicViewHolder = (SinglePicViewHolder) holder;
                singlePicViewHolder.onBind();
            }
        }

    }

    @Override
    public int getItemCount() {
        return resAllList.size();
    }

    @Override
    public int getItemViewType(int position) {
        String types = resAllList.get(position).getTypes() == null ? "" : resAllList.get(position).getTypes();
        if (types.equals("audio")) {
            return TYPE_AUDIO;


        } else if (types.equals("article")) {

            ResouecesAll.AaDataBean dataBean = resAllList.get(position);
            if (dataBean != null && dataBean.getPictureList() != null && dataBean.getPictureList().size() >= 2) {
                return TYPE_NEWS_MULTI;
            }
            return TYPE_NEWS_SINGLE;
        } else if (types.equals("picture")) {

            ResouecesAll.AaDataBean dataBean = resAllList.get(position);
            if (dataBean != null && dataBean.getPictureList() != null && dataBean.getPictureList().size() >= 2) {
                return TYPE_IMAGE_MULTI;
            }
            return TYPE_IMAGE_SINGLE;
        }
        return TYPE_VIDEO;
    }

    //音频
    class AudioViewHodler
            extends RecyclerView.ViewHolder {

        int pos;
        @BindView(R.id.img_back_pic)
        ImageView      imgBackPic;
        @BindView(R.id.tv_audio_ischarge)
        TextView       tvAudioIscharge;
        @BindView(R.id.tv_audio_time)
        TextView       tvAudioTime;
        @BindView(R.id.tv_audio_title)
        TextView       tvAudioTitle;
        @BindView(R.id.tv_audio_price)
        TextView       tvAudioPrice;
        @BindView(R.id.tv_audio_count)
        TextView       tvAudioCount;
        @BindView(R.id.img_author_head)
        RoundImageView imgAuthorHead;
        @BindView(R.id.tv_author_name)
        TextView       tvAuthorName;
        @BindView(R.id.tv_audio_comment_num)
        TextView       tvAudioCommentNum;
        @BindView(R.id.tv_audio_love_num)
        TextView       tvAudioLoveNum;
        @BindView(R.id.thumbCount)
        TextView       mThumbCount;

        public AudioViewHodler(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        void onBind() {
            pos = getLayoutPosition();
            registerOnItemClickListener(pos, itemView);
            final ResouecesAll.AaDataBean dataBean = resAllList.get(pos);
            if (dataBean != null) {
                String title = dataBean.getTitle() == null ? "" : dataBean.getTitle();//标题
                String titlePicture = dataBean.getTitlePicture() == null ? "" : dataBean.getTitlePicture();//背景图
                int likes = dataBean.getCollectionCounts();//喜欢数量
                int commentCounts = dataBean.getCommentCounts();//评论数
                String author = dataBean.getCustomerName() == null ? "" : dataBean.getCustomerName();//作者
                String authorPicture = dataBean.getAuthorPicture() == null ? "" : dataBean.getAuthorPicture();//用户头像
                int isCharge = dataBean.getIsCharge();//0  免费
                int clickRate = dataBean.getClickRate();//播放量
                Object price = dataBean.getPrice() == null ? "" : dataBean.getPrice();//价格
                int thumb = dataBean.getResourceExit();
//                DrawableUtils.setDrawableLeft(c, thumb == 0 ? R.drawable.icon_like_12_12 : R.drawable.red_love, tvAudioLoveNum);

                if (isCharge == 0) {
                    tvAudioIscharge.setText("免费");
                    tvAudioIscharge.setBackgroundResource(R.drawable.back_transparent);
                    tvAudioPrice.setText("免费");
                } else {
                    tvAudioIscharge.setText("付费");
                    tvAudioIscharge.setBackgroundResource(R.drawable.back_translate_red);
                    tvAudioPrice.setText("¥" + price.toString());
                }
                mThumbCount.setText(String.format(Locale.getDefault(), "点赞%d次", dataBean.getLikes()));
                tvAudioCount.setText(clickRate + "次播放");
                tvAudioTitle.setText(title);
                tvAudioLoveNum.setText(likes + "");
                tvAudioCommentNum.setText(commentCounts + "");


                if (author.isEmpty()) {
                    tvAuthorName.setText("未知");
                } else {
                    tvAuthorName.setText(author);
                }

                Glide.with(c).load(authorPicture).placeholder(R.drawable.app_icon).dontAnimate().dontTransform().into
                        (imgAuthorHead);
                Glide.with(c).load(titlePicture).placeholder(R.mipmap.default_image).dontAnimate().dontTransform().into
                        (imgBackPic);

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

    //视频
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
            final ResouecesAll.AaDataBean dataBean = resAllList.get(pos);
            if (dataBean != null) {
                String title = dataBean.getTitle() == null ? "" : dataBean.getTitle();//标题
                String titlePicture = dataBean.getTitlePicture() == null ? "" : dataBean.getTitlePicture();//背景图
                int likes = dataBean.getCollectionCounts();//喜欢
                int commentCounts = dataBean.getCommentCounts();//评论数
                String author = dataBean.getCustomerName() == null ? "" : dataBean.getCustomerName();//昵称
                String authorPicture = dataBean.getAuthorPicture() == null ? "" : dataBean.getAuthorPicture();//用户头像
                int clickRate = dataBean.getClickRate();//播放量
                int isCharge = dataBean.getIsCharge();
                Object price = dataBean.getPrice() == null ? "" : dataBean.getPrice();//价格
                int thumb = dataBean.getResourceExit();
//                DrawableUtils.setDrawableLeft(c, thumb == 0 ? R.drawable.icon_like_12_12 : R.drawable.red_love, tvVideoLoveNum);
                if (isCharge == 0) {
                    tvVideoIscharge.setText("免费");
                    tvVideoIscharge.setBackgroundResource(R.drawable.back_transparent);
                    tvVideoPrice.setText("免费");
                } else {
                    tvVideoIscharge.setText("付费");
                    tvVideoIscharge.setBackgroundResource(R.drawable.back_translate_red);
                    tvVideoPrice.setText("¥" + price);
                }

                mThumbCount.setText(String.format(Locale.getDefault(), "点赞%d次", dataBean.getLikes()));
                tvVideoCount.setText(clickRate + "次播放");
                tvVideoTitle.setText(title);
                tvVideoLoveNum.setText(likes + "");
                tvVideoCommentNum.setText(commentCounts + "");
                if (author.isEmpty()) {
                    tvAuthorName.setText("未知");
                } else {
                    tvAuthorName.setText(author);
                }
                Glide.with(c).load(authorPicture).placeholder(R.drawable.app_icon).dontAnimate().dontTransform().into
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

    //图片
    class PicViewHolder
            extends RecyclerView.ViewHolder {

        int pos;

        @BindView(R.id.img_title)
        TextView     tvTitle;
        @BindView(R.id.recycler_image)
        RecyclerView recyclerImage;
        @BindView(R.id.tv_news_num)
        TextView     tvNewsNum;
        @BindView(R.id.tv_news_love_num)
        TextView     tvNewsLoveNum;
        @BindView(R.id.tv_news_time)
        TextView     tvNewsTime;
        @BindView(R.id.thumbCount)
        TextView     mThumbCount;

        public PicViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        void onBind() {
            pos = getLayoutPosition();
            registerOnItemClickListener(pos, itemView);
            final ResouecesAll.AaDataBean dataBean = resAllList.get(pos);

            if (dataBean != null) {
                String title = dataBean.getTitle() == null ? "" : dataBean.getTitle();
                int likes = dataBean.getCollectionCounts();//喜欢数
                int commentCounts = dataBean.getCommentCounts();//评论数
                String editUserTime = dataBean.getAddUserName() == null ? "" : dataBean.getAddUserTime();//时间

                mThumbCount.setText(String.format(Locale.getDefault(), "点赞%d次", dataBean.getLikes()));
                tvTitle.setText(title);
                tvNewsNum.setText(commentCounts + "");
                tvNewsLoveNum.setText(likes + "");
                tvNewsTime.setText(editUserTime);
                if (!editUserTime.isEmpty()) {
                    String time = editUserTime.substring(0, editUserTime.indexOf(" "));
                    tvNewsTime.setText(time);
                }
                List<ResouecesAll.AaDataBean.PictureListBean> pictureList = dataBean.getPictureList();
                int thumb = dataBean.getResourceExit();
//                DrawableUtils.setDrawableLeft(c, thumb == 0 ? R.drawable.icon_like_12_12 : R.drawable.red_love, tvNewsLoveNum);

                if (pictureList != null && pictureList.size() > 0) {
                    List<String> mList = new ArrayList<>();

                    int size = pictureList.size() > 3 ? 3 : pictureList.size();

                    for (int i = 0; i < size; i++) {
                        mList.add(pictureList.get(i).getFileUrl());
                    }

                    SingleImaAdapter singleImaAdapter = new SingleImaAdapter(c, mList);
                    ScrollGridManager scrollGridManager = new ScrollGridManager(c, 3);
                    scrollGridManager.setScrollEnabled(false);
                    recyclerImage.setLayoutManager(scrollGridManager);
                    recyclerImage.setAdapter(singleImaAdapter);
                    singleImaAdapter.setOnListItemClickListener(new OnListItemClickListener() {
                        @Override
                        public void onItemClickListener(int position, View v) {
                            String id = dataBean.getId();

                            Intent intent = new Intent(c, PicDetailActivity.class);
                            intent.putExtra("id", id);
                            c.startActivity(intent);

                        }
                    });
                }
                tvNewsNum.setOnClickListener(new View.OnClickListener() {
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

    //单个图片
    class SinglePicViewHolder
            extends RecyclerView.ViewHolder {
        int pos;

        @BindView(R.id.tv_title)
        TextView  tvTitle;
        @BindView(R.id.tv_news_num)
        TextView  tvNewsNum;
        @BindView(R.id.tv_news_love_num)
        TextView  tvNewsLoveNum;
        @BindView(R.id.tv_news_time)
        TextView  tvNewsTime;
        @BindView(R.id.img_news_back)
        ImageView imgNewsBack;
        @BindView(R.id.thumbCount)
        TextView  mThumbCount;

        public SinglePicViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        void onBind() {
            pos = getLayoutPosition();
            registerOnItemClickListener(pos, itemView);
            final ResouecesAll.AaDataBean dataBean = resAllList.get(pos);
            if (dataBean != null) {
                String titlePicture = "";//背景图
                if (dataBean.getPictureList().size() > 0) {
                    titlePicture = dataBean.getPictureList().get(0).getFileUrl();
                }

                String title = dataBean.getTitle() == null ? "" : dataBean.getTitle();
                int likes = dataBean.getCollectionCounts();//喜欢数
                int commentCounts = dataBean.getCommentCounts();//评论数
                String editUserTime = dataBean.getAddUserName() == null ? "" : dataBean.getAddUserTime();//时间
                int thumb = dataBean.getResourceExit();
//                DrawableUtils.setDrawableLeft(c, thumb == 0 ? R.drawable.icon_like_12_12 : R.drawable.red_love, tvNewsLoveNum);

                mThumbCount.setText(String.format(Locale.getDefault(), "点赞%d次", dataBean.getLikes()));
                tvTitle.setText(title);
                tvNewsNum.setText(commentCounts + "");
                tvNewsLoveNum.setText(likes + "");
                tvNewsTime.setText(editUserTime);
                if (!editUserTime.isEmpty()) {
                    String time = editUserTime.substring(0, editUserTime.indexOf(" "));
                    tvNewsTime.setText(time);
                }
                Glide.with(c).load(titlePicture).placeholder(R.mipmap.default_image).dontAnimate().dontTransform().into
                        (imgNewsBack);
                tvNewsNum.setOnClickListener(new View.OnClickListener() {
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

    //单个资讯
    class SingleViewHolder
            extends RecyclerView.ViewHolder {
        int pos;
        @BindView(R.id.tv_title)
        TextView  tvTitle;
        @BindView(R.id.tv_news_num)
        TextView  tvNewsNum;
        @BindView(R.id.tv_news_love_num)
        TextView  tvNewsLoveNum;
        @BindView(R.id.tv_news_time)
        TextView  tvNewsTime;
        @BindView(R.id.img_news_back)
        ImageView imgNewsBack;
        @BindView(R.id.thumbCount)
        TextView  mThumbCount;

        public SingleViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        void onBind() {
            pos = getLayoutPosition();
            registerOnItemClickListener(pos, itemView);
            final ResouecesAll.AaDataBean dataBean = resAllList.get(pos);
            if (dataBean != null) {
                String titlePicture = "";//背景图
                if (dataBean.getPictureList().size() > 0) {
                    titlePicture = dataBean.getPictureList().get(0).getFileUrl();
                }

                String title = dataBean.getTitle() == null ? "" : dataBean.getTitle();
                int likes = dataBean.getCollectionCounts();//喜欢数
                int commentCounts = dataBean.getCommentCounts();//评论数
                String editUserTime = dataBean.getAddUserName() == null ? "" : dataBean.getAddUserTime();//时间
                int thumb = dataBean.getResourceExit();
//                DrawableUtils.setDrawableLeft(c, thumb == 0 ? R.drawable.icon_like_12_12 : R.drawable.red_love, tvNewsLoveNum);

                mThumbCount.setText(String.format(Locale.getDefault(), "点赞%d次", dataBean.getLikes()));
                tvTitle.setText(title);
                tvNewsNum.setText(commentCounts + "");
                tvNewsLoveNum.setText(likes + "");
                tvNewsTime.setText(editUserTime);
                if (!editUserTime.isEmpty()) {
                    String time = editUserTime.substring(0, editUserTime.indexOf(" "));
                    tvNewsTime.setText(time);
                }
                Glide.with(c).load(titlePicture).placeholder(R.mipmap.default_image).dontAnimate().dontTransform().into
                        (imgNewsBack);
                tvNewsNum.setOnClickListener(new View.OnClickListener() {
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

    //多个资讯
    class MultiViewHolder
            extends RecyclerView.ViewHolder {
        int pos;
        @BindView(R.id.img_title)
        TextView     tvTitle;
        @BindView(R.id.recycler_image)
        RecyclerView recyclerImage;
        @BindView(R.id.tv_news_num)
        TextView     tvNewsNum;
        @BindView(R.id.tv_news_love_num)
        TextView     tvNewsLoveNum;
        @BindView(R.id.tv_news_time)
        TextView     tvNewsTime;
        @BindView(R.id.thumbCount)
        TextView     mThumbCount;

        public MultiViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        void onBind() {
            pos = getLayoutPosition();
            registerOnItemClickListener(pos, itemView);
            final ResouecesAll.AaDataBean dataBean = resAllList.get(pos);

            if (dataBean != null) {
                String title = dataBean.getTitle() == null ? "" : dataBean.getTitle();
                int likes = dataBean.getCollectionCounts();//喜欢数
                int commentCounts = dataBean.getCommentCounts();//评论数
                String editUserTime = dataBean.getAddUserName() == null ? "" : dataBean.getAddUserTime();//时间
                int thumb = dataBean.getResourceExit();
//                DrawableUtils.setDrawableLeft(c, thumb == 0 ? R.drawable.icon_like_12_12 : R.drawable.red_love, tvNewsLoveNum);

                mThumbCount.setText(String.format(Locale.getDefault(), "点赞%d次", dataBean.getLikes()));
                tvTitle.setText(title);
                tvNewsNum.setText(commentCounts + "");
                tvNewsLoveNum.setText(likes + "");
                tvNewsTime.setText(editUserTime);
                if (!editUserTime.isEmpty()) {
                    String time = editUserTime.substring(0, editUserTime.indexOf(" "));
                    tvNewsTime.setText(time);
                }
                List<ResouecesAll.AaDataBean.PictureListBean> pictureList = dataBean.getPictureList();

                if (pictureList != null && pictureList.size() >= 2) {
                    List<String> mList = new ArrayList<>();

                    int size = pictureList.size() > 3 ? 3 : pictureList.size();

                    for (int i = 0; i < size; i++) {
                        mList.add(pictureList.get(i).getFileUrl());
                    }

                    SingleImaAdapter singleImaAdapter = new SingleImaAdapter(c, mList);
                    ScrollGridManager scrollGridManager = new ScrollGridManager(c, 3);
                    scrollGridManager.setScrollEnabled(false);
                    recyclerImage.setLayoutManager(scrollGridManager);
                    recyclerImage.setAdapter(singleImaAdapter);
                    singleImaAdapter.setOnListItemClickListener(new OnListItemClickListener() {
                        @Override
                        public void onItemClickListener(int position, View v) {
                            Intent intent = new Intent(c, NewsDetailActivity.class);
                            intent.putExtra("id", dataBean.getId());
                            c.startActivity(intent);
                        }
                    });
                }
                tvNewsNum.setOnClickListener(new View.OnClickListener() {
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

    private MyOnClickListener mMyOnClickListener;

    public void setMyOnClickListener(MyOnClickListener myOnClickListener) {
        mMyOnClickListener = myOnClickListener;
    }

    public interface MyOnClickListener {
        void onChildClickListener(int pos, String id);
    }
}
