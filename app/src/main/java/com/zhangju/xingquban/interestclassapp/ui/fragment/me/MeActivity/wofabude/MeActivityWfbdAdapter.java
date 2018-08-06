package com.zhangju.xingquban.interestclassapp.ui.fragment.me.MeActivity.wofabude;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.zhangju.xingquban.R;
import com.zhangju.xingquban.interestclassapp.adapter.HomeRecylerAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zsl on 2017/8/14.
 */

public class MeActivityWfbdAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> implements View.OnClickListener {
    private Context mContext;
    private List<MeActivityWfbdBean> mList = new ArrayList<>();
    private HomeRecylerAdapter.OnItemClickListener mOnItemClickListener = null;

    //define interfacequestion
    public static interface OnItemClickListener {
        void onItemClick(View view, int position);
    }

    @Override
    public void onClick(View v) {
        if (mOnItemClickListener != null) {
            //注意这里使用getTag方法获取position
            mOnItemClickListener.onItemClick(v, (int) v.getTag());
        }
    }

    public void setOnItemClickListener(HomeRecylerAdapter.OnItemClickListener listener) {
        this.mOnItemClickListener = listener;
    }


    public MeActivityWfbdAdapter(Context context, List<MeActivityWfbdBean> list) {
        this.mContext = context;
        this.mList = list;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_meactivity_wfbd, parent, false);
        MyViewHolder myViewHolder = new MyViewHolder(view);
        view.setOnClickListener(this);
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
        holder.itemView.setTag(position);
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
            me_activity_wfbdItem_baoming = (TextView) itemView.findViewById(R.id.me_activity_wfbdItem_baoming);
            me_activity_wfbdItem_pinglun = (TextView) itemView.findViewById(R.id.me_activity_wfbdItem_pinglun);

        }
    }

}
