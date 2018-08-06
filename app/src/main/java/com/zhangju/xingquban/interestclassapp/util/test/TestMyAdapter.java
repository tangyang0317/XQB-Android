package com.zhangju.xingquban.interestclassapp.util.test;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.zhangju.xingquban.R;
import com.zhangju.xingquban.interestclassapp.adapter.baseadpter.BaseRecycleViewAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by ydw on 2017/11/27.
 */

public class TestMyAdapter extends BaseRecycleViewAdapter {

    private List<String> mlist = new ArrayList<>();
    private View headView;

    private int TYPE_HEAD = 0x1;
    private int TYPE_CONTENT = 0x2;


    public TestMyAdapter(Context c, List<String> mlist, View headView) {
        super(c);
        this.mlist = mlist;
        this.headView = headView;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == TYPE_HEAD) {
            return new TopViewholder(headView);


        }
        return new TestViewHolder(resIdToView(parent, R.layout.item_exchange_funbean));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder !=null){

            if (holder instanceof TestViewHolder){

                TestViewHolder testViewHolder = (TestViewHolder) holder;
                testViewHolder.onBind();
            }else if (holder instanceof TopViewholder){
                TopViewholder topViewholder= (TopViewholder) holder;
                topViewholder.onBind();
            }

        }

    }

    @Override
    public int getItemCount() {

        return mlist.size() + 1;
    }

    @Override
    public int getItemViewType(int position) {
        if (position == 0) {
            return TYPE_HEAD;
        }
        return TYPE_CONTENT;

    }

    class TestViewHolder extends RecyclerView.ViewHolder {
        int pos;
        @BindView(R.id.beanCount)
        TextView tvName;


        public TestViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        void onBind() {
            pos = getLayoutPosition();
            registerOnItemClickListener(pos, itemView);
            String name = mlist.get(pos);
            tvName.setText(name);
        }
    }

    class TopViewholder extends RecyclerView.ViewHolder {
        int pos;


        public TopViewholder(View itemView) {
            super(itemView);
        }

        void onBind() {
            pos = getLayoutPosition();
        }
    }

}
