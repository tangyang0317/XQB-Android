package com.zhangju.xingquban.interestclassapp.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.zhangju.xingquban.R;
import com.zhangju.xingquban.interestclassapp.adapter.baseadpter.BaseRecycleViewAdapter;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by ydw on 2017/11/30.
 */

public class SingleTextAdapter extends BaseRecycleViewAdapter {


    private List<String> mlist;

    public SingleTextAdapter(Context c, List<String> mlist) {
        super(c);
        this.mlist = mlist;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new SingleTextViewHolder(resIdToView(parent, R.layout.item_single_text));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        SingleTextViewHolder singleTextViewHolde = (SingleTextViewHolder) holder;
        singleTextViewHolde.onBind();
    }

    @Override
    public int getItemCount() {
        return mlist.size();
    }

    class SingleTextViewHolder extends RecyclerView.ViewHolder {
        int pos;
        @BindView(R.id.single_text)
        TextView singleText;

        public SingleTextViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        void onBind() {
            pos = getLayoutPosition();

            registerOnItemClickListener(pos, itemView);
            String s = mlist.get(pos);
            singleText.setText(s);
        }
    }
}
