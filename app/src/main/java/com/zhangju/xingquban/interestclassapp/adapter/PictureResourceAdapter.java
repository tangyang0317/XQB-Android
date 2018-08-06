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
import com.zhangju.xingquban.interestclassapp.bean.ResouecesAll;

import java.util.List;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * create hqf 2017/11/9
 */
public class PictureResourceAdapter extends BaseRecycleViewAdapter {


    private List<ResouecesAll.AaDataBean> pictureList;


    public PictureResourceAdapter(Context c, List<ResouecesAll.AaDataBean> pictureList) {
        super(c);
        this.pictureList = pictureList;

    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new PicViewholder(resIdToView(parent, R.layout.pciture_resource_item));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        PicViewholder picViewholder = (PicViewholder) holder;
        picViewholder.onBind();

    }

    @Override
    public int getItemCount() {
        return pictureList.size();
    }

    class PicViewholder extends RecyclerView.ViewHolder {

        int pos;
        @BindView(R.id.img_image_back)
        ImageView imgImageBack;
        @BindView(R.id.tv_image_size)
        TextView tvImageSize;
        @BindView(R.id.tv_image_title)
        TextView tvImageTitle;
        @BindView(R.id.tv_image_type)
        TextView tvImageType;
        @BindView(R.id.tv_image_comment_num)
        TextView tvImageCommentNum;
        @BindView(R.id.tv_image_love_num)
        TextView tvImageLoveNum;
        @BindView(R.id.thumbCount)
        TextView mThumbCount;

        public PicViewholder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        void onBind() {
            pos = getLayoutPosition();

            registerOnItemClickListener(pos, itemView);

            final ResouecesAll.AaDataBean dataBean = pictureList.get(pos);
            if (dataBean != null) {
                String title = dataBean.getTitle() == null ? "" : dataBean.getTitle();//标题

                List<ResouecesAll.AaDataBean.PictureListBean> pictureList = dataBean.getPictureList();
                int commentCounts = dataBean.getCommentCounts();//评论数
                int collectionCounts = dataBean.getCollectionCounts();//喜欢数
                int thumb = dataBean.getResourceExit();
//                DrawableUtils.setDrawableLeft(c, thumb == 0 ? R.drawable.icon_like_12_12 : R.drawable.red_love, tvImageLoveNum);
                String names = dataBean.getCatagoriesNames() == null ? "" : dataBean.getCatagoriesNames();//类型

                tvImageTitle.setText(title);
                tvImageType.setText(names);
                tvImageCommentNum.setText(commentCounts + "");
                tvImageLoveNum.setText(collectionCounts + "");
                mThumbCount.setText(String.format(Locale.getDefault(),"点赞%d次",dataBean.getLikes()));
                if (pictureList != null) {
                    tvImageSize.setText(pictureList.size() + "张");
                    if (pictureList.size() > 0) {
                        String fileUrl = pictureList.get(0).getFileUrl();
                        Glide.with(c).load(fileUrl).placeholder(R.mipmap.default_image).dontAnimate().dontTransform().into(imgImageBack);

                    }

                }

                tvImageCommentNum.setOnClickListener(new View.OnClickListener() {
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
