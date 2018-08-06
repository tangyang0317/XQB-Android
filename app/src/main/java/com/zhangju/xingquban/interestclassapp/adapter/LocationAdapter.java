package com.zhangju.xingquban.interestclassapp.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.zhangju.xingquban.R;
import com.zhangju.xingquban.interestclassapp.adapter.baseadpter.BaseRecycleViewAdapter;
import com.zhangju.xingquban.interestclassapp.bean.me.LocationBean;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by ydw on 2017/10/23.
 */

public class LocationAdapter extends BaseRecycleViewAdapter {


    private List<LocationBean> mLocationList;

    public LocationAdapter(Context c, List<LocationBean> mLocationList) {
        super(c);
        this.mLocationList = mLocationList;
    }


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new LocationViewHolder(resIdToView(parent, R.layout.item_location));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        LocationViewHolder viewHolder = (LocationViewHolder) holder;
        viewHolder.onBind();
    }

    @Override
    public int getItemCount() {
        return mLocationList.size();
    }

    class LocationViewHolder extends RecyclerView.ViewHolder {
        int pos;
        @BindView(R.id.tv_address)
        TextView tvAddress;

        public LocationViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        void onBind() {
            pos = getLayoutPosition();
            registerOnItemClickListener(pos,itemView);
            String address = mLocationList.get(pos).getAddress() == null ? "" : mLocationList.get(pos).getAddress();
            tvAddress.setText(address);
        }
    }
}
