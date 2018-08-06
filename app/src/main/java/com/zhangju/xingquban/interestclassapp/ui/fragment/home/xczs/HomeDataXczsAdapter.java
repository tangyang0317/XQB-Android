package com.zhangju.xingquban.interestclassapp.ui.fragment.home.xczs;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.zhangju.xingquban.R;
import com.zhangju.xingquban.interestclassapp.ui.fragment.home.HomeDataTeacherBean;

/**
 * Created by zsl on 2017/8/24.
 */

public class HomeDataXczsAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>implements View.OnClickListener {
    private HomeDataTeacherBean dataTeacherBean;
    private Context mContent;
    private OnItemClickListener mOnItemClickListener = null;

    public HomeDataXczsAdapter(Context context, HomeDataTeacherBean homeDataTeacherBean) {
        this.mContent = context;
        this.dataTeacherBean = homeDataTeacherBean;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContent).inflate(R.layout.item_home_data_xczs, parent, false);
        MyViewHolder myViewHolder = new MyViewHolder(view);
        view.setOnClickListener(this);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        MyViewHolder holder1 = (MyViewHolder) holder;
        if (dataTeacherBean.getAaData().get(0).getOrganAlbumFiles().get(position).getIsPic()==2){
            Glide.with(mContent).load(dataTeacherBean.getAaData().get(0).getOrganAlbumFiles().get(position).getVideoTitlePic()).into(holder1.pic1);
        }
        else{
            Glide.with(mContent).load(dataTeacherBean.getAaData().get(0).getOrganAlbumFiles().get(position).getPicVideo()).into(holder1.pic1);
            holder1.videoicon.setVisibility(View.GONE);
        }



        holder.itemView.setTag(position);
    }

    @Override
    public int getItemCount() {
        return dataTeacherBean.getAaData().get(0).getOrganAlbumFiles().size();
    }

    @Override
    public void onClick(View v) {
        if (mOnItemClickListener != null) {
            //注意这里使用getTag方法获取position
            mOnItemClickListener.onItemClick(v, (int) v.getTag());
        }
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.mOnItemClickListener = listener;
    }


     class MyViewHolder extends RecyclerView.ViewHolder{

         private  ImageView pic1,videoicon;

        public  MyViewHolder(View view) {
            super(view);
            pic1= (ImageView) view.findViewById(R.id.pic1);
            videoicon= (ImageView) view.findViewById(R.id.video_icon);

        }
    }

    public static interface OnItemClickListener {
        void onItemClick(View view, int position);
    }

}
