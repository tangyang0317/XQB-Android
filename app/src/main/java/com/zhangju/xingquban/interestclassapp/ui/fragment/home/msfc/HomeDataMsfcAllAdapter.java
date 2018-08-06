package com.zhangju.xingquban.interestclassapp.ui.fragment.home.msfc;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.zhangju.xingquban.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zsl on 2017/8/24.
 */

public class HomeDataMsfcAllAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private List<HomeDataMsfcAllBean> mList = new ArrayList<>();
    private Context mContext;

    public HomeDataMsfcAllAdapter(Context context, List<HomeDataMsfcAllBean> list) {
        this.mContext = context;
        this.mList = list;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_home_data_msfcall, parent, false);
        MyViewHolder myViewHolder = new MyViewHolder(view);

        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        MyViewHolder holder1 = (MyViewHolder) holder;
        holder1.home_data_msfcall_title.setText("王腾飞");
//        holder1.home_data_msfcall_tab.setText();
//        holder1.home_data_msfcall_jl.setText();
//        holder1.home_data_msfcall_school.setText();
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        private TextView home_data_msfcall_title, home_data_msfcall_tab, home_data_msfcall_jl, home_data_msfcall_school;

        public MyViewHolder(View itemView) {
            super(itemView);
            home_data_msfcall_title = (TextView) itemView.findViewById(R.id.home_data_msfcall_title);
            home_data_msfcall_tab = (TextView) itemView.findViewById(R.id.home_data_msfcall_tab);
            home_data_msfcall_jl = (TextView) itemView.findViewById(R.id.home_data_msfcall_jl);
            home_data_msfcall_school = (TextView) itemView.findViewById(R.id.home_data_msfcall_school);

        }
    }
}
