package com.zhangju.xingquban.interestclassapp.ui.fragment.me.Institutions.kcgl;

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
 * Created by zsl on 2017/9/6.
 */

public class MeJiGouKcglKctjCity2Adapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> implements View.OnClickListener {
    private MeJiGouKcglKctjCity2Bean mMeJiGouKcglKctjCityBean;
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

    public void addData(MeJiGouKcglKctjCity2Bean meJiGouKcglKctjCityBean) {
        mMeJiGouKcglKctjCityBean.getAaData().addAll(meJiGouKcglKctjCityBean.getAaData());
        notifyDataSetChanged();
    }

    public MeJiGouKcglKctjCity2Bean getData() {
        return this.mMeJiGouKcglKctjCityBean;
    }

    public MeJiGouKcglKctjCity2Adapter(Context context, MeJiGouKcglKctjCity2Bean meJiGouKcglKctjCityBean) {
        this.mContext = context;
        this.mMeJiGouKcglKctjCityBean = meJiGouKcglKctjCityBean;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_mejgkcgl_city, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        view.setOnClickListener(this);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ViewHolder holder1 = (ViewHolder) holder;
        holder1.cityName.setText(mMeJiGouKcglKctjCityBean.getAaData().get(position).getName());
        holder.itemView.setTag(position);
    }

    @Override
    public int getItemCount() {
        return mMeJiGouKcglKctjCityBean.getAaData().size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.city_name)
        TextView cityName;

        ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}
