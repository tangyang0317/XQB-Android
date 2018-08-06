package com.zhangju.xingquban.interestclassapp.ui.fragment.me.MeActivity.wodepiaoquan;

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
 * Created by zsl on 2017/8/15.
 */

public class MeActivityWdpaAllAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> implements View.OnClickListener {
    private Context mContext;
    private List<MeActivityWdpqAllBean> mList = new ArrayList<>();
    private OnItemClickListener mOnItemClickListener = null;

    public MeActivityWdpaAllAdapter(Context context, List<MeActivityWdpqAllBean> list) {
        this.mContext = context;
        this.mList = list;
    }

    //define interfacequestion
    public static interface OnItemClickListener {
        void onItemClick(View view, int position);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.mOnItemClickListener = listener;
    }

    @Override
    public void onClick(View v) {
        if (mOnItemClickListener != null) {
            //注意这里使用getTag方法获取position
            mOnItemClickListener.onItemClick(v, (int) v.getTag());
        }
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_meactivity_wdpj, parent, false);
        MyViewHolder myViewHolder = new MyViewHolder(view);
        view.setOnClickListener(this);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        MyViewHolder holder1 = (MyViewHolder) holder;
        holder1.item_me_activity_wdpj_title.setText("城市生存之魔都生存挑战");
//        holder1.item_me_activity_wdpj_city.setText("");
//        holder1.item_me_activity_wdpj_time.setText("");
//        holder1.item_me_activity_wdpj_huiyuanp.setText("");
//        holder1.item_me_activity_wdpj_money.setText("");
//        holder1.item_me_activity_wdpj_youx.setText("");
        holder.itemView.setTag(position);
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        private TextView item_me_activity_wdpj_title, item_me_activity_wdpj_city, item_me_activity_wdpj_time, item_me_activity_wdpj_huiyuanp,
                item_me_activity_wdpj_money, item_me_activity_wdpj_youx;

        public MyViewHolder(View itemView) {
            super(itemView);
            item_me_activity_wdpj_title = (TextView) itemView.findViewById(R.id.item_me_activity_wdpj_title);
            item_me_activity_wdpj_city = (TextView) itemView.findViewById(R.id.item_me_activity_wdpj_city);
            item_me_activity_wdpj_time = (TextView) itemView.findViewById(R.id.item_me_activity_wdpj_time);
            item_me_activity_wdpj_huiyuanp = (TextView) itemView.findViewById(R.id.item_me_activity_wdpj_huiyuanp);
            item_me_activity_wdpj_money = (TextView) itemView.findViewById(R.id.item_me_activity_wdpj_money);
            item_me_activity_wdpj_youx = (TextView) itemView.findViewById(R.id.item_me_activity_wdpj_youx);
        }
    }
}
