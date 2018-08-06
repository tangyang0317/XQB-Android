package com.zhangju.xingquban.interestclassapp.ui.fragment.find.Wenda;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.zhangju.xingquban.R;

/**
 * Created by zsl on 2017/8/4.
 */

public class FindWDScAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> implements View.OnClickListener {
    private FindWDScBean mFindWDScBean;
    private Context mContext;
    private OnItemClickListener mOnItemClickListener = null;

    public void addData(FindWDScBean findWDScBean) {
        mFindWDScBean.getAaData().addAll(findWDScBean.getAaData());
        notifyDataSetChanged();
    }

    public FindWDScBean getData() {
        return this.mFindWDScBean;
    }

    //define interfacequestion
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

    public FindWDScAdapter(Context context, FindWDScBean findTWBean) {
        this.mContext = context;
        this.mFindWDScBean = findTWBean;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_findwdsc_tiwen, parent, false);
        MyViewHolder myViewHolder = new MyViewHolder(view);
        view.setOnClickListener(this);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        MyViewHolder holder1 = (MyViewHolder) holder;
        holder1.title.setText(mFindWDScBean.getAaData().get(position).getQuestion().getTitle());
        holder1.number.setText(mFindWDScBean.getAaData().get(position).getQuestion().getQExplain());
        holder.itemView.setTag(position);
    }

    @Override
    public int getItemCount() {
        return mFindWDScBean.getAaData().size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        private TextView title;
        private TextView number;

        public MyViewHolder(View itemView) {
            super(itemView);
            title = (TextView) itemView.findViewById(R.id.item_find_tiwen_title);
            number = (TextView) itemView.findViewById(R.id.item_find_tiwen_number);
        }
    }
}
