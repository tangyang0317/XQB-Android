package com.zhangju.xingquban.interestclassapp.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.zhangju.xingquban.R;
import com.zhangju.xingquban.interestclassapp.adapter.baseadpter.BaseRecycleViewAdapter;
import com.zhangju.xingquban.interestclassapp.adapter.baseadpter.OnListItemClickListener;
import com.zhangju.xingquban.interestclassapp.bean.ResouecesAll;
import com.zhangju.xingquban.interestclassapp.hplper.ScrollGridManager;
import com.zhangju.xingquban.interestclassapp.ui.fragment.find.SeekResource.NewsDetailActivity;
import com.zhangju.xingquban.interestclassapp.util.DrawableUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * create by hqf 2017/11/9
 */

public class ArticleResourceAdapter extends BaseRecycleViewAdapter {

    private List<ResouecesAll.AaDataBean > newsList;

    private int TYPE_SINGLE = 0x001;
    private int TYPE_MULTI = 0x002;

    public ArticleResourceAdapter(Context c, List<ResouecesAll.AaDataBean > newsList) {
        super(c);
        this.newsList = newsList;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == TYPE_SINGLE) {
            return new SingleViewHolder(resIdToView(parent, R.layout.article_resource_item));
        } else if (viewType == TYPE_MULTI) {
            return new MultiViewHolder(resIdToView(parent, R.layout.article_resource_itemtype2));
        }
        return null;

    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder!=null){
            if (holder instanceof SingleViewHolder) {
                SingleViewHolder singleViewHolder = (SingleViewHolder) holder;
                singleViewHolder.onBind();
            } else if (holder instanceof MultiViewHolder) {
                MultiViewHolder multiViewHolder = (MultiViewHolder) holder;
                multiViewHolder.onBind();
            }
        }
    }

    @Override
    public int getItemCount() {
        return newsList.size();
    }

    @Override
    public int getItemViewType(int position) {
        ResouecesAll.AaDataBean  dataBean = newsList.get(position);

        if (dataBean!=null&&dataBean.getPictureList()!=null&&dataBean.getPictureList().size()>=2){
            return TYPE_MULTI;
        }
        return TYPE_SINGLE;
    }

    class SingleViewHolder extends RecyclerView.ViewHolder{
        int pos;
        @BindView(R.id.tv_title)
        TextView tvTitle;
        @BindView(R.id.tv_news_num)
        TextView tvNewsNum;
        @BindView(R.id.tv_news_love_num)
        TextView tvNewsLoveNum;
        @BindView(R.id.tv_news_time)
        TextView tvNewsTime;
        @BindView(R.id.img_news_back)
        ImageView imgNewsBack;
        @BindView(R.id.thumbCount)
        TextView mThumbCount;

        public SingleViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        void onBind() {
            pos = getLayoutPosition();
            registerOnItemClickListener(pos, itemView);
            ResouecesAll.AaDataBean  dataBean = newsList.get(pos);
            if (dataBean != null) {
                String titlePicture="" ;//背景图
                if (dataBean.getPictureList().size()>0){
                    titlePicture=dataBean.getPictureList().get(0).getFileUrl();
                }

                String title = dataBean.getTitle() == null ? "" : dataBean.getTitle();
                int likes = dataBean.getLikes();//喜欢数
                int commentCounts = dataBean.getCommentCounts();//评论数
                String editUserTime = dataBean.getAddUserName() == null ? "" : dataBean.getAddUserTime();//时间
                int thumb = dataBean.getResourceExit();

//                DrawableUtils.setDrawableLeft(c, thumb == 0 ? R.drawable.icon_like_12_12 : R.drawable.red_love, tvNewsLoveNum);
                tvTitle.setText(title);
                tvNewsNum.setText(commentCounts + "");
                tvNewsLoveNum.setText(likes + "");
                tvNewsTime.setText(editUserTime);
                mThumbCount.setText(String.format(Locale.getDefault(),"点赞%d次",dataBean.getLikes()));
                if (!editUserTime.isEmpty()) {
                    String time = editUserTime.substring(0, editUserTime.indexOf(" "));
                    tvNewsTime.setText(time);
                }
                Glide.with(c).load(titlePicture).placeholder(R.mipmap.default_image).dontAnimate().dontTransform().into(imgNewsBack);


            }

        }
    }


    class MultiViewHolder extends RecyclerView.ViewHolder {

        int pos;
        @BindView(R.id.img_title)
        TextView tvTitle;
        @BindView(R.id.recycler_image)
        RecyclerView recyclerImage;
        @BindView(R.id.tv_news_num)
        TextView tvNewsNum;
        @BindView(R.id.tv_news_love_num)
        TextView tvNewsLoveNum;
        @BindView(R.id.tv_news_time)
        TextView tvNewsTime;
        @BindView(R.id.thumbCount)
        TextView mThumbCount;

        public MultiViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        void onBind() {
            pos = getLayoutPosition();
            registerOnItemClickListener(pos, itemView);
            final ResouecesAll.AaDataBean  dataBean = newsList.get(pos);

            if (dataBean != null) {
                String title = dataBean.getTitle() == null ? "" : dataBean.getTitle();
                int likes = dataBean.getCollectionCounts();//收藏数
                int commentCounts = dataBean.getCommentCounts();//评论数
                String editUserTime = dataBean.getAddUserName() == null ? "" : dataBean.getAddUserTime();//时间
                int thumb = dataBean.getResourceExit();
//                DrawableUtils.setDrawableLeft(c, thumb == 0 ? R.drawable.icon_like_12_12 : R.drawable.red_love, tvNewsLoveNum);

                tvTitle.setText(title);
                tvNewsNum.setText(commentCounts + "");
                tvNewsLoveNum.setText(likes + "");
                tvNewsTime.setText(editUserTime);
                mThumbCount.setText(String.format(Locale.getDefault(),"点赞%d次",dataBean.getLikes()));
                if (!editUserTime.isEmpty()) {
                    String time = editUserTime.substring(0, editUserTime.indexOf(" "));
                    tvNewsTime.setText(time);
                }
                List<ResouecesAll.AaDataBean .PictureListBean> pictureList = dataBean.getPictureList();
                if (pictureList!=null&&pictureList.size()>=2){
                    List<String> mList=new ArrayList<>();

                    int size=pictureList.size()>3?3:pictureList.size();

                    for (int i = 0; i <  size ; i++) {
                        mList.add(pictureList.get(i).getFileUrl());
                    }

                    SingleImaAdapter singleImaAdapter=new SingleImaAdapter(c,mList);
                    ScrollGridManager scrollGridManager=new ScrollGridManager(c,3);
                    scrollGridManager.setScrollEnabled(false);
                    recyclerImage.setLayoutManager(scrollGridManager);
                    recyclerImage.setAdapter(singleImaAdapter);
                    singleImaAdapter.setOnListItemClickListener(new OnListItemClickListener() {
                        @Override
                        public void onItemClickListener(int position, View v) {
                            String id = newsList.get(pos).getId();

                            Intent intent=new Intent(c,NewsDetailActivity.class);
                            intent.putExtra("id",id);
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
    private AllTypeResourceAdapter.MyOnClickListener mMyOnClickListener;

    public void setMyOnClickListener(AllTypeResourceAdapter.MyOnClickListener myOnClickListener) {
        mMyOnClickListener = myOnClickListener;
    }

    public interface MyOnClickListener {
        void onChildClickListener(int pos, String id);
    }
}
