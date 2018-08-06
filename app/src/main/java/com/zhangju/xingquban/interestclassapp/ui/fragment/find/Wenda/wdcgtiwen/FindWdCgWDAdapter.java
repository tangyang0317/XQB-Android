package com.zhangju.xingquban.interestclassapp.ui.fragment.find.Wenda.wdcgtiwen;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.zhangju.xingquban.R;
import com.zhangju.xingquban.interestclassapp.ui.fragment.find.Wenda.FindWDCgWendaBean;

/**
 * Created by zsl on 2017/8/26.
 */

public class FindWdCgWDAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> implements View.OnClickListener {
    private FindWDCgWendaBean mFindWDCgWendaBean;
    private Context mContext;
    private OnItemClickListener mOnItemClickListener = null;

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

    public void addData(FindWDCgWendaBean findWDCgWendaBean) {
        mFindWDCgWendaBean.getAaData().addAll(findWDCgWendaBean.getAaData());
        notifyDataSetChanged();
    }

    public FindWDCgWendaBean getData() {
        return this.mFindWDCgWendaBean;
    }

    public FindWdCgWDAdapter(Context context, FindWDCgWendaBean findWDCgWendaBean) {
        this.mContext = context;
        this.mFindWDCgWendaBean = findWDCgWendaBean;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_find_wdcg_tw, parent, false);
        MyViewHolder myViewHolder = new MyViewHolder(view);
        view.setOnClickListener(this);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        MyViewHolder holder1 = (MyViewHolder) holder;
        holder1.item_find_wdcgtw_title.setText(mFindWDCgWendaBean.getAaData().get(position).getTitle());
        String str = mFindWDCgWendaBean.getAaData().get(position).getAddUserTime();
        String result = str.substring(0, str.indexOf(" "));
        holder1.item_find_wdcgtw_number.setText(result);
        holder.itemView.setTag(position);
    }

    @Override
    public int getItemCount() {
        return mFindWDCgWendaBean.getAaData().size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        private TextView item_find_wdcgtw_title, item_find_wdcgtw_number;

        public MyViewHolder(View itemView) {
            super(itemView);
            item_find_wdcgtw_title = (TextView) itemView.findViewById(R.id.item_find_wdcgtw_title);
            item_find_wdcgtw_number = (TextView) itemView.findViewById(R.id.item_find_wdcgtw_number);
        }
    }

}
