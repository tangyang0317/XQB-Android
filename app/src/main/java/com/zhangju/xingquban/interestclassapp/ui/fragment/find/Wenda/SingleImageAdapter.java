package com.zhangju.xingquban.interestclassapp.ui.fragment.find.Wenda;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.zhangju.xingquban.R;
import com.zhangju.xingquban.interestclassapp.adapter.baseadpter.BaseRecycleViewAdapter;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by ydw on 2017/10/17.
 */
//单个图片adapter

public class SingleImageAdapter extends BaseRecycleViewAdapter {

    private List<String> mlist;

    public SingleImageAdapter(Context c, List<String> mlist) {
        super(c);
        this.mlist = mlist;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new SingleViewHolder(resIdToView(parent, R.layout.find_wenda_recyler_image));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        SingleViewHolder viewHolder = (SingleViewHolder) holder;
        viewHolder.onBind();

    }

    @Override
    public int getItemCount() {
        return mlist.size();
    }


    class SingleViewHolder extends RecyclerView.ViewHolder {
        int pos;
        @BindView(R.id.find_wenda_recylerItem)
        ImageView findWendaRecylerItem;


        public SingleViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }

        void onBind() {
            pos = getLayoutPosition();
            String url = mlist.get(pos);
            Glide.with(c).load(url).into(findWendaRecylerItem);
        }
    }
}
