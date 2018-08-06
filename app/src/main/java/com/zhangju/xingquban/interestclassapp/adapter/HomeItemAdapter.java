package com.zhangju.xingquban.interestclassapp.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.zhangju.xingquban.R;
import com.zhangju.xingquban.interestclassapp.bean.NearSubjectBean;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/7/2.
 */

public class HomeItemAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private List<NearSubjectBean.AaDataBean> mList = new ArrayList<>();
    private LayoutInflater layoutInflater;
    private OnRecylerViewItemClickListener mListener;
    private Context mContext;

    public HomeItemAdapter(Context context, List<NearSubjectBean.AaDataBean> list) {
        mList = list;
        mContext = context;
        layoutInflater = LayoutInflater.from(context);
    }

    public void setRecylerViewClickListener(OnRecylerViewItemClickListener mListener) {
        this.mListener = mListener;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new MyViewHelder(layoutInflater.inflate(R.layout.home_page_item, parent, false));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        MyViewHelder a = (MyViewHelder) holder;
        Glide.with(mContext).load(mList.get(position).getCatagoriesPicture()).into(a.imageView);
        a.textView.setText(mList.get(position).getName());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mListener != null) {
                    mListener.onItemClickListener(v, position);
                }
            }
        });

    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public interface OnRecylerViewItemClickListener {
        public void onItemClickListener(View view, int postine);
    }

    class MyViewHelder extends RecyclerView.ViewHolder {
        public ImageView imageView;
        public TextView textView;

        public MyViewHelder(View itemView) {
            super(itemView);
            imageView = (ImageView) itemView.findViewById(R.id.home_page_itemimgae);
            textView = (TextView) itemView.findViewById(R.id.home_page_itemtext);
        }
    }
}
