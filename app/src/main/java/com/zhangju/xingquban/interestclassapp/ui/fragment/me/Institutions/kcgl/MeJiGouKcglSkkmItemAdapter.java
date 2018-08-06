package com.zhangju.xingquban.interestclassapp.ui.fragment.me.Institutions.kcgl;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.zhangju.xingquban.R;
import com.zhangju.xingquban.interestclassapp.bean.NearMSRight;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by zsl on 2017/9/7.
 */

public class MeJiGouKcglSkkmItemAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private NearMSRight mNearMSRight;
    private Context mContext;

    public MeJiGouKcglSkkmItemAdapter(Context context, NearMSRight nearMSRight) {
        this.mContext = context;
        this.mNearMSRight = nearMSRight;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_jg_kcgl_skkm, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ViewHolder holder1 = (ViewHolder) holder;
        holder1.itemJgKcglSkkmText.setText(mNearMSRight.getAaData().get(position).getName());
    }

    @Override
    public int getItemCount() {
        return mNearMSRight.getAaData().size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.item_jg_kcgl_skkmText)
        TextView itemJgKcglSkkmText;

        ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}
