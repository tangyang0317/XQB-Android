package com.zhangju.xingquban.interestclassapp.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.zhangju.xingquban.R;
import com.zhangju.xingquban.interestclassapp.adapter.baseadpter.BaseRecycleViewAdapter;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by ydw on 2017/10/19.
 */

public class IndicatorAdapter extends BaseRecycleViewAdapter {
    int position=0;
    int size;


    public void setPosition(int position) {
        this.position = position;
        notifyDataSetChanged();
    }

    public IndicatorAdapter(Context c, int size) {
        super(c);
        this.size = size;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new IndicatorViewholder(resIdToView(parent, R.layout.indicator_item));
    }


    @Override

    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        IndicatorViewholder indicatorViewholder= (IndicatorViewholder) holder;
        indicatorViewholder.onBind();

    }

    @Override
    public int getItemCount() {
        return size;
    }

    class IndicatorViewholder extends RecyclerView.ViewHolder {

        int pos;
        @BindView(R.id.tv_indicator)
        TextView indicator_red;

        void onBind() {
            pos = getLayoutPosition();
            if (position == pos) {
                indicator_red.setBackgroundResource(R.drawable.indicator_red);
            }else {
                indicator_red.setBackgroundResource(R.drawable.indicator_gray);
            }
        }

        public IndicatorViewholder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }
}
