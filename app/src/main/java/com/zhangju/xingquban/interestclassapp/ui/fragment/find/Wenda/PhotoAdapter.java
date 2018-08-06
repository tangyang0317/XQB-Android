package com.zhangju.xingquban.interestclassapp.ui.fragment.find.Wenda;

import android.app.Activity;
import android.content.Context;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.imnjh.imagepicker.SImagePicker;
import com.zhangju.xingquban.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/8/17.
 */

public class PhotoAdapter extends BaseAdapter {
    List<String> mList = new ArrayList<>();
    private Context mContent;

    public PhotoAdapter(Context context) {
        this.mContent = context;
    }

    @Override
    public int getCount() {
        return mList.size() + 1;
    }

    @Override
    public String getItem(int position) {
        if (position == getCount() - 1) {
            return null;
        }

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
        if (position == getCount() - 1) {
            image.setImageResource(R.mipmap.find_wenda_tiwen);
            image.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    SImagePicker
                            .from((Activity) mContent)
                            .maxCount(9)
                            .rowCount(3)
                            .showCamera(true)
                            .pickMode(SImagePicker.MODE_IMAGE)
                            .forResult(101);
                }
            });
        } else {
            image.setImageBitmap(BitmapFactory.decodeFile(getItem(position)));
        }
        return inflate;
    }

    public void addDataPhoto(List<String> list) {
        if (list != null && !list.isEmpty()) {
            mList.addAll(list);
            notifyDataSetChanged();
        }
    }
}
