package com.zhangju.xingquban.interestclassapp.util.test;

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
 * Created by ydw on 2017/12/29.
 */

public class TestTTopAdapter extends BaseRecycleViewAdapter {


    private List<String> mlist;

    public TestTTopAdapter(Context c, List<String> mlist) {
        super(c);
        this.mlist = mlist;

    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new TestTopViewHolder(resIdToView(parent, R.layout.city_item));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        TestTopViewHolder testTopViewHolder= (TestTopViewHolder) holder;
        testTopViewHolder.onBind();
    }

    @Override
    public int getItemCount() {
        return mlist.size();
    }

    class TestTopViewHolder extends RecyclerView.ViewHolder {
        int pos;
        @BindView(R.id.cityName)
        TextView cityName;
        public TestTopViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);

        }

        void onBind() {
            pos = getLayoutPosition();
            cityName.setText(mlist.get(pos));

        }
    }
}
