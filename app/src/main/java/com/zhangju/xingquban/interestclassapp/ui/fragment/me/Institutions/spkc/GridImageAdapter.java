package com.zhangju.xingquban.interestclassapp.ui.fragment.me.Institutions.spkc;

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
 * Created by Administrator on 2017/9/8.
 */

public class GridImageAdapter extends BaseAdapter {
    List<String> mList = new ArrayList<>();
    private Context mContent;

    public GridImageAdapter(Context context) {
        this.mContent = context;
    }

    @Override
    public int getCount() {
        return mList == null ? 0 : mList.size();
    }

    @Override
    public String getItem(int position) {
        return mList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        View inflate = LayoutInflater.from(mContent).inflate(R.layout.item_image, null);
        ImageView image = (ImageView) inflate.findViewById(R.id.image);
        Glide.with(mContent).load(mList.get(position)).into(image);
        return inflate;
    }

    public void addDataPhoto(List<String> list) {
        if (list != null && !list.isEmpty()) {
            mList.addAll(list);
            notifyDataSetChanged();
        }
    }
}
