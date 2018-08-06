package com.zhangju.xingquban.interestclassapp.ui.fragment.find.Wenda;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.zhangju.xingquban.R;

/**
 * Created by zsl on 2017/8/30.
 */

public class FindItemRecyclerAdapter2 extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private FindMeWendaXqBean find;
    private Context mContext;

    public FindItemRecyclerAdapter2(Context context, FindMeWendaXqBean findWDBean) {
        this.mContext = context;
        this.find = findWDBean;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.find_wenda_recyler_image, parent, false);
        MyViewHolder myViewHolder = new MyViewHolder(view);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        MyViewHolder holder1 = (MyViewHolder) holder;
        try {

              if (find.getAaData().size()>0){
                  Glide.with(mContext).load(find.getAaData().get(0).getPicStr().get(position).getPicture()).into(holder1.find_wenda_recylerItem);
              }
        }catch (Exception ex){
            ex.printStackTrace();
        }
    }

    @Override
    public int getItemCount() {
        return find.getAaData().size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        private ImageView find_wenda_recylerItem;

        public MyViewHolder(View itemView) {
            super(itemView);
            find_wenda_recylerItem = (ImageView) itemView.findViewById(R.id.find_wenda_recylerItem);
        }
    }
}
