package com.zhangju.xingquban.interestclassapp.refactor.common.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.fastlib.adapter.FastAdapter;
import com.fastlib.base.OldViewHolder;
import com.zhangju.xingquban.R;
import com.zhangju.xingquban.interestclassapp.refactor.common.activity.PreviewImageActivity;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by sgfb on 2017/10/26.
 * Grid图像适配器
 */
public class ImageAdapter extends FastAdapter<String> {
    private boolean isLocal;
    private View.OnClickListener mListener;

    public ImageAdapter(Context context) {
        this(context, new ArrayList<String>(), true);
    }

    public ImageAdapter(Context context, List<String> data, boolean isLocal) {
        super(context, R.layout.item_images, data);
        this.isLocal = isLocal;
    }

    @Override
    public void binding(final int position, String data, OldViewHolder holder) {
        Glide.with(mContext).load(isLocal ? new File(data) : data).into((ImageView) holder.getView(R.id.image));
        if (mListener != null)
            holder.setOnClickListener(mListener);
        else
            holder.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(mContext, PreviewImageActivity.class);
                    intent.putExtra(PreviewImageActivity.ARG_BOOL_IS_LOCAL, isLocal);
                    intent.putStringArrayListExtra(PreviewImageActivity.ARG_LIST_STR_IMAGES, (ArrayList<String>) mData);
                    intent.putExtra(PreviewImageActivity.ARG_INT_INDEX, position);
                    mContext.startActivity(intent);
                }
            });
    }

    public void setOnItemClickListener(View.OnClickListener listener) {
        mListener = listener;
    }
}