package com.zhangju.xingquban.interestclassapp.refactor.me.adapter;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.fastlib.app.FastDialog;
import com.fastlib.app.PhotoResultListener;
import com.fastlib.utils.N;
import com.jph.takephoto.model.TImage;
import com.jph.takephoto.model.TResult;
import com.zhangju.xingquban.R;
import com.zhangju.xingquban.interestclassapp.base.TakePhotoFastActivity;
import com.zhangju.xingquban.interestclassapp.refactor.common.activity.PreviewImageActivity;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by sgfb on 2017/11/6.
 * 相册管理中上传新图片
 */
public class AddImageAdapter extends BaseAdapter{
    private ArrayList<String> mData=new ArrayList<>();
    //    private int mMaxLimit=Integer.MAX_VALUE;
    private int mMaxLimit = 9;

    @Override
    public int getCount() {
        return (mData==null?0:mData.size())+1;
    }

    @Override
    public String getItem(int position) {
        if(position==getCount()-1) {
            return null;
        }
        return mData.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(final int position, View convertView, final ViewGroup parent){
        if(convertView==null) {
            convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_add_image, null);
        }
        ViewHolder holder= (ViewHolder) convertView.getTag();
        if(holder==null){
            holder=new ViewHolder();
            holder.image= (ImageView) convertView.findViewById(R.id.image);
            holder.delete=convertView.findViewById(R.id.delete);
            convertView.setTag(holder);
        }
        if(position==getCount()-1){
            Glide.with(parent.getContext()).load(R.mipmap.me_bangzhu_image).into(holder.image);
            holder.delete.setVisibility(View.GONE);
            holder.image.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(mData.size()>=mMaxLimit){
                        N.showShort(parent.getContext(),"超出最大上传限制");
                        return;
                    }
                    FastDialog.showListDialog(new String[]{"拍照","从手机相册选择"}).show(((AppCompatActivity) parent.getContext()).getSupportFragmentManager(), new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which){
                            TakePhotoFastActivity activity= (TakePhotoFastActivity) parent.getContext();
                            if(which==0) {
                                activity.openCamera(new PhotoResultListener() {
                                    @Override
                                    public void onPhotoResult(String path) {
                                        mData.add(path);
                                        notifyDataSetChanged();
                                    }
                                });
                            } else {
                               /* activity.openAlbum(new PhotoResultListener() {
                                    @Override
                                    public void onPhotoResult(String path) {
                                        mData.add(path);
                                        notifyDataSetChanged();
                                    }
                                });*/
                                activity.getTakePhoto().onPickMultiple(mMaxLimit - mData.size());
                            }
                        }
                    });
                }
            });
        }
        else{
            holder.delete.setVisibility(View.VISIBLE);
            Glide.with(parent.getContext()).load(new File(getItem(position))).into(holder.image);
            holder.image.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent=new Intent(parent.getContext(), PreviewImageActivity.class);
                    intent.putExtra(PreviewImageActivity.ARG_BOOL_IS_LOCAL,true);
                    intent.putExtra(PreviewImageActivity.ARG_INT_INDEX,position);
                    intent.putExtra(PreviewImageActivity.ARG_LIST_STR_IMAGES,mData);
                    parent.getContext().startActivity(intent);
                }
            });
        }
        holder.delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mData.remove(position);
                notifyDataSetChanged();
            }
        });
        return convertView;
    }

    public List<String> getData(){
        return mData;
    }

    public void setMaxLimit(int limit){
        mMaxLimit=limit;
    }

    public void onTakeSuccess(TResult result) {
        ArrayList<TImage> images = result.getImages();
        for (TImage image : images) {
            String originalPath = image.getOriginalPath();
            mData.add(originalPath);
        }
        notifyDataSetChanged();
    }

    public class ViewHolder{
        public ImageView image;
        public View delete;
    }
}
