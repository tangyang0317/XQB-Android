package com.zhangju.xingquban.interestclassapp.adapter.near;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.zhangju.xingquban.R;
import com.zhangju.xingquban.interestclassapp.bean.NearDataBean;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by zsl on 2017/7/21.
 */

public class HotSearchAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> implements View.OnClickListener {

    private Context mContext;
    private OnItemClickListener mOnItemClickListener = null;
    private NearDataBean nearDataBean;

    //define interface
    public static interface OnItemClickListener {
        void onItemClick(View view, int position);
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

    public HotSearchAdapter(Context context, NearDataBean mcomment) {
        this.mContext = context;
        this.nearDataBean = mcomment;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_near_hotsearch, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        view.setOnClickListener(this);
        return viewHolder;

    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ViewHolder holder1 = (ViewHolder) holder;

        holder1.jigouName.setText(nearDataBean.getAaData().get(position).getSigname());
        holder.itemView.setTag(position);

    }

    @Override
    public int getItemCount() {
        return nearDataBean.getAaData().size();
    }


    static class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.jigou_name)
        TextView jigouName;

        ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}
