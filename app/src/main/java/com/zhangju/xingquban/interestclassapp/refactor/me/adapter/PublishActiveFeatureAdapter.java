package com.zhangju.xingquban.interestclassapp.refactor.me.adapter;

import android.content.Context;
import android.content.DialogInterface;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.fastlib.adapter.FastAdapterForRecycler;
import com.fastlib.app.FastDialog;
import com.fastlib.app.PhotoResultListener;
import com.fastlib.base.CommonViewHolder;
import com.zhangju.xingquban.R;
import com.zhangju.xingquban.interestclassapp.refactor.me.bean.PublishActiveFeature;
import com.zhangju.xingquban.interestclassapp.refactor.me.fragment.publish_active.AddFeatureDialog;

import java.io.File;

/**
 * Created by sgfb on 2017/11/1.
 * 发布活动中活动特色适配器
 */
public class PublishActiveFeatureAdapter extends FastAdapterForRecycler<PublishActiveFeature>{
    private Object mHost;
    private int mAddNewFeatherPosition=0;
    private boolean isEditable=true;

    public PublishActiveFeatureAdapter(Context context){
        super(context,R.layout.item_publish_active_feature);
        mHost=context;
    }

    public PublishActiveFeatureAdapter(Fragment fragment){
        this(fragment.getContext());
        mHost=fragment;
    }

    @Override
    public void binding(final int position, final PublishActiveFeature data, CommonViewHolder holder){
        holder.setVisibility(R.id.contentLayout,data.isReplace? View.GONE:View.VISIBLE);
        holder.setVisibility(R.id.addBelow,position==getItemCount()-1?View.VISIBLE:View.GONE);
        holder.setEnabled(R.id.text,isEditable);
        if(!data.isReplace){
            if(data.type==PublishActiveFeature.TYPE_TEXT){
                holder.setEnabled(R.id.text,true);
                holder.setText(R.id.text, data.content);
                ((ImageView)holder.getView(R.id.image)).setImageDrawable(null);
            }
            else {
                holder.setEnabled(R.id.text,false);
                holder.setText(R.id.text,"");
                Glide.with(mContext)
                        .load(data.type==PublishActiveFeature.TYPE_IMAGE?new File(data.content):data.content)
                        .into((ImageView)holder.getView(R.id.image));
            }
            holder.setVisibility(R.id.up,View.VISIBLE);
            holder.setVisibility(R.id.down,View.VISIBLE);
            if(position==0)
                holder.setVisibility(R.id.up,View.INVISIBLE);
            if(position==getItemCount()-1)
                holder.setVisibility(R.id.down,View.INVISIBLE);
            holder.setOnClickListener(R.id.add,new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mAddNewFeatherPosition=position;
                    AppCompatActivity activity= (AppCompatActivity) mContext;
                    AddFeatureDialog.getInstance(activity).show(position,mHost,new PhotoResultListener() {
                        @Override
                        public void onPhotoResult(String path) {
                            PublishActiveFeature feature=new PublishActiveFeature();
                            feature.type=PublishActiveFeature.TYPE_IMAGE;
                            feature.content=path;
                            addData(feature);
                        }
                    });
                }
            });
            holder.setOnClickListener(R.id.addBelow, new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mAddNewFeatherPosition=position+1;
                    AppCompatActivity activity= (AppCompatActivity) mContext;
                    AddFeatureDialog.getInstance(activity).show(position,mHost,new PhotoResultListener() {
                        @Override
                        public void onPhotoResult(String path) {
                            PublishActiveFeature feature=new PublishActiveFeature();
                            feature.type=PublishActiveFeature.TYPE_IMAGE;
                            feature.content=path;
                            addData(feature);
                        }
                    });
                }
            });
            holder.setOnClickListener(R.id.up, new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    getData().remove(position);
                    getData().add(position-1,data);
                    notifyDataSetChanged();
                }
            });
            holder.setOnClickListener(R.id.down, new View.OnClickListener() {
                @Override
                public void onClick(View v){
                    getData().remove(position);
                    getData().add(position+1,data);
                    notifyDataSetChanged();
                }
            });
            holder.setOnClickListener(R.id.delete, new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    FastDialog.showMessageDialog("确定删除该模块？",true).show(((AppCompatActivity) mContext).getSupportFragmentManager(), new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            getData().remove(position);
                            notifyDataSetChanged();
                        }
                    });
                }
            });
        }
    }

    @Override
    public void addData(PublishActiveFeature data){
        getData().add(mAddNewFeatherPosition,data);
        notifyDataSetChanged();
    }

    public void setEditable(boolean flag){
        isEditable=flag;
        notifyDataSetChanged();
    }
}
