package com.zhangju.xingquban.interestclassapp.ui.fragment.find.Wenda;

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
 * Created by Administrator on 2017/9/3.
 */

public class CanDeleteImageAdapter extends BaseAdapter {
    List<String> mList = new ArrayList<>();
    private Context mContent;

    public CanDeleteImageAdapter(Context context) {
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
        View inflate = LayoutInflater.from(mContent).inflate(R.layout.item_photo_list, null);
        ImageView image = (ImageView) inflate.findViewById(R.id.photo_image);

        inflate.findViewById(R.id.delete).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mList.remove(position);
                notifyDataSetChanged();
            }
        });
        Glide.with(mContent).load(getItem(position)).into(image);
//        image.setImageBitmap(BitmapFactory.decodeFile(getItem(position)));//出现OOM
        if (null!= mList){
            showPicutreList.getPictureList(mList);
        }

        return inflate;
    }


    public void addDataPhoto(List<String> list) {
        if (list != null && !list.isEmpty()) {
            mList.addAll(list);
            notifyDataSetChanged();
        }
    }

    //图片数据回调
    showPicutreList showPicutreList;

    interface showPicutreList{
        void getPictureList( List<String> list);

    }

    public void setShowPicutreList(showPicutreList showPicutreList) {
        this.showPicutreList = showPicutreList;
    }

}
