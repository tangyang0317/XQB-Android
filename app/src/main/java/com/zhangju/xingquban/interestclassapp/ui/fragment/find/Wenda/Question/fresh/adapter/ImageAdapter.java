package com.zhangju.xingquban.interestclassapp.ui.fragment.find.Wenda.Question.fresh.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.zhangju.xingquban.R;
import com.zhangju.xingquban.interestclassapp.adapter.baseadpter.BaseRecycleViewAdapter;
import com.zhangju.xingquban.interestclassapp.refactor.common.activity.PreviewImageActivity;
import com.zhangju.xingquban.interestclassapp.ui.fragment.find.SeekResource.PicDetailActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by hqf on 2017/11/16.
 * 我的问答---图片适配adapter
 */

public class ImageAdapter extends BaseRecycleViewAdapter {
    private List<String>  mImageList;

    public ImageAdapter(Context c,List<String>  mImageList) {
        super(c);
        this.mImageList=mImageList;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ImageViewHolder(resIdToView(parent, R.layout.find_wenda_recyler_image));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ImageViewHolder imageViewHolder= (ImageViewHolder) holder;
        imageViewHolder.onBind();
    }

    @Override
    public int getItemCount() {
        return mImageList.size();
    }

    class ImageViewHolder extends RecyclerView.ViewHolder {
        int pos;
        @BindView(R.id.find_wenda_recylerItem)
        ImageView findWendaRecylerItem;
        public ImageViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        void onBind() {
            pos = getLayoutPosition();
            String pic = mImageList.get(pos);
            Glide.with(c).load(pic).placeholder(R.drawable.item_all_order_bg).dontTransform().dontAnimate().into(findWendaRecylerItem);

            /**
             *
             */
            findWendaRecylerItem.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(c, PreviewImageActivity.class);
                    intent.putExtra(PreviewImageActivity.ARG_BOOL_IS_LOCAL, false);
                    intent.putExtra(PreviewImageActivity.ARG_INT_INDEX, pos);
                    intent.putExtra(PreviewImageActivity.ARG_LIST_STR_IMAGES, (ArrayList) mImageList);
                    c.startActivity(intent);
                }
            });
        }
    }
}
