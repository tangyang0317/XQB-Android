package com.zhangju.xingquban.interestclassapp.refactor.me.adapter;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.fastlib.app.FastActivity;
import com.fastlib.widget.SquareLayout;
import com.zhangju.xingquban.R;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by sgfb on 2017/10/26.
 * 用户反馈图像适配器
 */
public class FeedbackPhotosAdapter extends BaseAdapter{
    private List<String> mData=new ArrayList<>();
    private View.OnClickListener mSelectPhotoListener;

    public FeedbackPhotosAdapter(View.OnClickListener mSelectPhotoListener) {
        this.mSelectPhotoListener = mSelectPhotoListener;
    }

    @Override
    public int getCount() {
        return mData==null?1:mData.size()+1;
    }

    @Override
    public String getItem(int position){
        if(position==getCount()-1) return null;
        else return mData.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        SquareLayout squareLayout;
        ImageView image;

        if(convertView==null){
            convertView=squareLayout=new SquareLayout(parent.getContext());
            squareLayout.addView(image=new ImageView(parent.getContext()));
            image.setScaleType(ImageView.ScaleType.CENTER_CROP);
        }
        else{
            squareLayout= (SquareLayout) convertView;
            image= (ImageView) squareLayout.getChildAt(0);
        }
        if(position==getCount()-1) {
            Glide.with(parent.getContext()).load(R.mipmap.me_bangzhu_image).into(image);
            image.setOnClickListener(mSelectPhotoListener);
        }
        else {
            Glide.with(parent.getContext()).load(new File(getItem(position))).into(image);
            image.setOnClickListener(null);
        }
        return convertView;
    }

    public void addData(String path){
        mData.add(path);
        notifyDataSetChanged();
    }
}
