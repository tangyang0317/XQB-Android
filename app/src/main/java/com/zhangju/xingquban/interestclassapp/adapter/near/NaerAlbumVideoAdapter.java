package com.zhangju.xingquban.interestclassapp.adapter.near;

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

import butterknife.BindView;
import butterknife.ButterKnife;


/**
 * Created by zsl on 2017/10/14.
 */

public class NaerAlbumVideoAdapter extends BaseAdapter {
    private Context context;
    private LayoutInflater mlayoutInflater;
    private int flag;
    private List<String> data=new ArrayList<>();

    public NaerAlbumVideoAdapter(Context mcontext, List<String> mdata) {
        this.context = mcontext;
        this.data = mdata;
        mlayoutInflater = LayoutInflater.from(mcontext);
    }

    public void selectnum(int mflag) {
        this.flag = mflag;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public Object getItem(int position) {
        return data.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView == null) {
            convertView = mlayoutInflater.inflate(R.layout.item_image, null);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        viewHolder.videoIcon.setVisibility(View.VISIBLE);
        Glide.with(context).load(data.get(position)).into(viewHolder.image);
        return convertView;
    }


    static class ViewHolder {
        @BindView(R.id.image)
        ImageView image;
        @BindView(R.id.videoIcon)
        ImageView videoIcon;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
