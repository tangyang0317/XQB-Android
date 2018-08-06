package com.zhangju.xingquban.interestclassapp.refactor.me.adapter;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.fastlib.annotation.Bind;
import com.fastlib.utils.ImageUtil;
import com.zhangju.xingquban.R;
import com.zhangju.xingquban.interestclassapp.ui.activity.near.ShipinBofangActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by sgfb on 2017/11/21.
 * 相册管理中上传新视频
 */
public class AddVideoAdapter extends BaseAdapter{
    private List<String> mVideos=new ArrayList<>();
    private View.OnClickListener mAddListener; //添加视频按键回调

    public AddVideoAdapter(View.OnClickListener listener){
        mAddListener=listener;
    }

    @Override
    public int getCount() {
        return (mVideos==null?0:mVideos.size())+1;
    }

    @Override
    public String getItem(int position){
        if(position==getCount()-1) return null;
        return mVideos.get(position);
    }

    @Override
    public long getItemId(int position){
        return 0;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent){
        if(convertView==null) convertView= LayoutInflater.from(parent.getContext()).inflate(R.layout.item_add_image,null);
        ViewHolder holder= (ViewHolder) convertView.getTag();
        if(holder==null){
            holder=new ViewHolder();
            holder.image= (ImageView) convertView.findViewById(R.id.image);
            holder.delete=convertView.findViewById(R.id.delete);
            convertView.setTag(holder);
        }
        if(position==getCount()-1){
            holder.delete.setVisibility(View.GONE);
            holder.image.setImageResource(R.mipmap.me_recrouse_ypbg);
            holder.image.setOnClickListener(mAddListener);
        }
        else{
            convertView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent=new Intent(v.getContext(), ShipinBofangActivity.class);
                    intent.putExtra(ShipinBofangActivity.ARG_STRING_NAME,"本地视频");
                    intent.putExtra(ShipinBofangActivity.ARG_STRING_URL,getItem(position));
                    v.getContext().startActivity(intent);
                }
            });
            holder.delete.setVisibility(View.VISIBLE);
            holder.image.setOnClickListener(null);
            holder.image.setImageBitmap(ImageUtil.getVideoFirstFrame(getItem(position)));
            holder.delete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mVideos.remove(position);
                    notifyDataSetChanged();
                }
            });
        }
        return convertView;
    }

    public void addData(String video){
        mVideos.add(video);
        notifyDataSetChanged();
    }

    public List<String> getData(){
        return mVideos;
    }

    public class ViewHolder{
        public ImageView image;
        public View delete;
    }
}
