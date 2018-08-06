package com.zhangju.xingquban.interestclassapp.ui.fragment.me.MeActivity.wocanyude;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.zhangju.xingquban.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zsl on 2017/8/15.
 */

public class MeActivityWcydAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private Context mContext;
    private List<MeActivityWcydBean> mList = new ArrayList<>();

    public MeActivityWcydAdapter(Context context, List<MeActivityWcydBean> list) {
        this.mContext = context;
        this.mList = list;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_meactivity_wcyd, parent, false);
        MyViewHolder myViewHolder = new MyViewHolder(view);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        MyViewHolder holder1 = (MyViewHolder) holder;
        holder1.me_activity_wcyd_title.setText("塞达尔光源");
//        holder1.me_activity_wcyd_name.setText("");
//        holder1.me_activity_wcyd_time.setText("");
//        holder1.me_activity_wcyd_piao.setText("");
//        holder1.me_activity_wcyd_pingl.setText("");

    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        private ImageView me_activity_wcyd_image;
        private TextView me_activity_wcyd_title, me_activity_wcyd_name, me_activity_wcyd_time, me_activity_wcyd_piao,
                me_activity_wcyd_pingl;

        public MyViewHolder(View itemView) {
            super(itemView);
            me_activity_wcyd_image = (ImageView) itemView.findViewById(R.id.me_activity_wcyd_image);

            me_activity_wcyd_title = (TextView) itemView.findViewById(R.id.me_activity_wcyd_title);
            me_activity_wcyd_name = (TextView) itemView.findViewById(R.id.me_activity_wcyd_name);
            me_activity_wcyd_time = (TextView) itemView.findViewById(R.id.me_activity_wcyd_time);
            me_activity_wcyd_piao = (TextView) itemView.findViewById(R.id.me_activity_wcyd_piao);
            me_activity_wcyd_pingl = (TextView) itemView.findViewById(R.id.me_activity_wcyd_pingl);
        }
    }
}
