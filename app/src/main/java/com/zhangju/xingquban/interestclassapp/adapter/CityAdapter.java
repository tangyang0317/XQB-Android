package com.zhangju.xingquban.interestclassapp.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.zhangju.xingquban.R;
import com.zhangju.xingquban.interestclassapp.bean.CityNameBean;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zsl on 2017/7/6.
 */

public class CityAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<CityNameBean.AaDataBean> cityDatas = new ArrayList<>();
    private Context mContext;

    public CityAdapter(Context context, CityNameBean cityNameBean) {
        this.mContext = context;
        this.cityDatas = cityNameBean.getAaData();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.city_item, parent, false);
        MyViewHolder myViewHolder = new MyViewHolder(view);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        MyViewHolder holder1 = (MyViewHolder) holder;
        holder1.cityName.setText(cityDatas.get(position).getName());
    }

    @Override
    public int getItemCount() {
        return cityDatas.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView cityName;

        public MyViewHolder(View itemView) {
            super(itemView);
            cityName = (TextView) itemView.findViewById(R.id.cityName);
        }
    }

}
