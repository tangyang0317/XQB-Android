package com.zhangju.xingquban.interestclassapp.adapter;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.zhangju.xingquban.R;
import com.zhangju.xingquban.interestclassapp.adapter.baseadpter.BaseRecycleViewAdapter;
import com.zhangju.xingquban.interestclassapp.bean.CityNameBean;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by ydw on 2017/10/26.
 */

//主页城市选择热门和历史Adapter

public class HotCityAdapter extends BaseRecycleViewAdapter {

    private List<CityNameBean.AaDataBean> cityList;


    public HotCityAdapter(Context c, List<CityNameBean.AaDataBean> cityData) {
        super(c);
        this.cityList = cityData;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new HotViewHolder(resIdToView(parent, R.layout.find_item_city));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        HotViewHolder hotViewHolder = (HotViewHolder) holder;
        hotViewHolder.onBind();
    }

    @Override
    public int getItemCount() {
        return cityList.size();
    }

    class HotViewHolder extends RecyclerView.ViewHolder {

        int pos;

        @BindView(R.id.tv_item_city)
        TextView tvItemCity;

        public HotViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }

        void onBind() {

            pos = getLayoutPosition();
            registerOnItemClickListener(pos,itemView);
            String name = cityList.get(pos).getName() == null ? "" : cityList.get(pos).getName();
            tvItemCity.setText(name);
            tvItemCity.setTextColor(Color.BLACK);

        }
    }
}
