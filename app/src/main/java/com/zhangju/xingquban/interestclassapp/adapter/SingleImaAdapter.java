package com.zhangju.xingquban.interestclassapp.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.fastlib.utils.N;
import com.zhangju.xingquban.R;
import com.zhangju.xingquban.interestclassapp.adapter.baseadpter.BaseRecycleViewAdapter;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by ydw on 2017/11/10.
 */
//单张图片Adapter
public class SingleImaAdapter extends BaseRecycleViewAdapter {


    private List<String> mImageList;

    public SingleImaAdapter(Context c, List<String> mImageList) {
        super(c);
        this.mImageList = mImageList;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ImageViewHolder(resIdToView(parent, R.layout.item_single_image));
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
        @BindView(R.id.image_back)
        ImageView imageBack;

        public ImageViewHolder(View itemView) {
            super(itemView);

            ButterKnife.bind(this, itemView);
        }

        void onBind() {
            pos = getLayoutPosition();
            registerOnItemClickListener(pos,itemView);
            String pic = mImageList.get(pos) == null ? "" : mImageList.get(pos);
            Glide.with(c).load(pic).placeholder(R.mipmap.default_image).dontAnimate().dontTransform().into(imageBack);

        }
    }
}
