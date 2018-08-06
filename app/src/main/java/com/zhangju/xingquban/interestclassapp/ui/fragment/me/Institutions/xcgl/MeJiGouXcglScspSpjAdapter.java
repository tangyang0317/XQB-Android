package com.zhangju.xingquban.interestclassapp.ui.fragment.me.Institutions.xcgl;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.zhangju.xingquban.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by zsl on 2017/9/11.
 */

public class MeJiGouXcglScspSpjAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> implements View.OnClickListener {
    private MeJiGouXcglScspSpjBean mMeJiGouXcglScspSpjBean;
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

    public MeJiGouXcglScspSpjAdapter(Context context, MeJiGouXcglScspSpjBean meJiGouXcglScspSpjBean) {
        this.mContext = context;
        this.mMeJiGouXcglScspSpjBean = meJiGouXcglScspSpjBean;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_scsp_spj, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        view.setOnClickListener(this);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ViewHolder holder1 = (ViewHolder) holder;
        holder1.spjText.setText(mMeJiGouXcglScspSpjBean.getAaData().get(position).getFiles());
        holder.itemView.setTag(position);
    }

    @Override
    public int getItemCount() {
        return mMeJiGouXcglScspSpjBean.getAaData().size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.spj_text)
        TextView spjText;

        ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}
