package com.zhangju.xingquban.interestclassapp.ui.fragment.me.Institutions.xcgl;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.zhangju.xingquban.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zsl on 2017/9/4.
 */

public class GridViewImageView extends BaseAdapter {
    private Context mContext;
    private List<Bean> list = new ArrayList<>();
    private boolean deleteFlag = false;
    private ImageType mType;

    public GridViewImageView(Context context, ImageType type) {
        mContext = context;
        mType = type;
    }

    @Override
    public int getCount() {
        return list == null ? 0 : list.size();
    }

    @Override
    public Bean getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final Bean bean = list.get(position);
        final ViewHolder holder;
        if (convertView == null) {
            convertView = LayoutInflater.from(mContext).inflate(R.layout.item_me_jg_gridview, null);
            holder = new ViewHolder();
            holder.image = (ImageView) convertView.findViewById(R.id.home_data_spkcall_image);
            holder.delete = (ImageView) convertView.findViewById(R.id.me_jjgl_xcgl_false);
            holder.play = (ImageView) convertView.findViewById(R.id.me_jjgl_xcgl_play);
            convertView.setTag(holder);
        } else holder = (ViewHolder) convertView.getTag();

        holder.play.setVisibility(mType == ImageType.IMAGE ? View.GONE : View.VISIBLE);
        holder.delete.setVisibility(deleteFlag ? View.VISIBLE : View.GONE);
        Glide.with(mContext).load(bean.image).into(holder.image);
        holder.delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bean.delete = !bean.delete;
                holder.delete.setImageResource(bean.delete ? R.mipmap.me_jjgl_xcgl_true : R.mipmap.me_jjgl_xcgl_false);
            }
        });
        return convertView;
    }

    public void deleteSelectImage() {
        List<Bean> needDelete = new ArrayList<>();
        for (Bean b : list)
            if (b.delete)
                needDelete.add(b);
        for (Bean b : needDelete)
            list.remove(b);
        notifyDataSetChanged();
    }

    public void canDelete(boolean deleteFlag) {
        this.deleteFlag = deleteFlag;
        if (!deleteFlag && list != null) {
            for (Bean b : list)
                b.delete = false;
        }
        notifyDataSetChanged();
    }

    public void setData(List<String> list) {
        for (String image : list) {
            Bean bean = new Bean();
            bean.image = image;
            this.list.add(bean);
        }
        notifyDataSetChanged();
    }

    public static class ViewHolder {
        ImageView image;
        ImageView delete;
        ImageView play;
    }

    public static class Bean {
        boolean delete;
        String image;
    }

    public enum ImageType {
        IMAGE,
        VIDEO
    }
}

