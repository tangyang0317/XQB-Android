package com.zhangju.xingquban.interestclassapp.ui.fragment.find.lookactivity.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.zhangju.xingquban.R;
import com.zhangju.xingquban.interestclassapp.adapter.baseadpter.BaseRecycleViewAdapter;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by hqf on 2017/11/22.
 * 列表adapter
 */

public class LookListAdapter extends BaseRecycleViewAdapter {

    public LookListAdapter(Context c) {
        super(c);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new LookViewHolder(resIdToView(parent, R.layout.look_list_item));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        LookViewHolder lookViewHolder = (LookViewHolder) holder;
        lookViewHolder.onBind();
    }

    @Override
    public int getItemCount() {
        return 0;
    }

    class LookViewHolder extends RecyclerView.ViewHolder {
        int pos;

        @BindView(R.id.item_look_image)
        ImageView itemLookImage;
        @BindView(R.id.tv_image_title)
        TextView tvImageTitle;
        @BindView(R.id.tv_list_title)
        TextView tvListTitle;
        @BindView(R.id.tv_money)
        TextView tvMoney;
        @BindView(R.id.tv_content)
        TextView tvContent;
        @BindView(R.id.tv_start_time)
        TextView tvStartTime;
        @BindView(R.id.tv_end_time)
        TextView tvEndTime;

        public LookViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
        void onBind(){

        }
    }
}
