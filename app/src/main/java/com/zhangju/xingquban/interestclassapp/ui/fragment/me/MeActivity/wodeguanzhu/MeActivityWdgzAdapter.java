package com.zhangju.xingquban.interestclassapp.ui.fragment.me.MeActivity.wodeguanzhu;

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
 * Created by zsl on 2017/8/16.
 */

public class MeActivityWdgzAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Context mContext;
    private List<MeActivityWdgzBean> mList = new ArrayList<>();

    public MeActivityWdgzAdapter(Context context, List<MeActivityWdgzBean> list) {
        this.mContext = context;
        this.mList = list;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_meactivity_wdgz, parent, false);
        MyViewHolder myViewHolder = new MyViewHolder(view);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        MyViewHolder holder1 = (MyViewHolder) holder;
        holder1.me_activity_wfbdItem_title.setText("六一|“城市生存之战”之魔都生存挑战");
//        holder1.me_activity_wfbdItem_city.setText("");
//        holder1.me_activity_wfbdItem_time.setText("");
//        holder1.me_activity_wfbdItem_money.setText("");
//        holder1.me_activity_wfbdItem_baoming.setText("");
//        holder1.me_activity_wfbdItem_pinglun.setText("");

    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        private ImageView me_activity_wfbdItem_image;
        private TextView me_activity_wfbdItem_title, me_activity_wfbdItem_city, me_activity_wfbdItem_time, me_activity_wfbdItem_money,
                me_activity_wfbdItem_baoming, me_activity_wfbdItem_pinglun;

        public MyViewHolder(View itemView) {
            super(itemView);
            me_activity_wfbdItem_title = (TextView) itemView.findViewById(R.id.me_activity_wfbdItem_title);
            me_activity_wfbdItem_city = (TextView) itemView.findViewById(R.id.me_activity_wfbdItem_city);
            me_activity_wfbdItem_time = (TextView) itemView.findViewById(R.id.me_activity_wfbdItem_time);
            me_activity_wfbdItem_money = (TextView) itemView.findViewById(R.id.me_activity_wfbdItem_money);
//            me_activity_wfbdItem_baoming = (TextView) itemView.findViewById(R.id.me_activity_wfbdItem_baoming);
//            me_activity_wfbdItem_pinglun = (TextView) itemView.findViewById(R.id.me_activity_wfbdItem_pinglun);

        }
    }
}
