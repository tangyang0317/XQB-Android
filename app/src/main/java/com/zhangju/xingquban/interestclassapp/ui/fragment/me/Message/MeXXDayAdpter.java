package com.zhangju.xingquban.interestclassapp.ui.fragment.me.Message;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.zhangju.xingquban.R;

/**
 * Created by zsl on 2017/8/4.
 */

public class MeXXDayAdpter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private Context mContext;
    private MeXXBean mMeXXbean;

    public MeXXDayAdpter(Context context, MeXXBean meXXBean) {
        this.mContext = context;
        this.mMeXXbean = meXXBean;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_day_message, parent, false);
        MyViewHolder myViewHolder = new MyViewHolder(view);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        MyViewHolder holder1 = (MyViewHolder) holder;
        holder1.title.setText(mMeXXbean.getAaData().get(position).getTitle());
        holder1.message.setText(mMeXXbean.getAaData().get(position).getAlert());
    }

    @Override
    public int getItemCount() {
        return mMeXXbean.getAaData().size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        private TextView title;
        private TextView message;

        public MyViewHolder(View itemView) {
            super(itemView);
            title = (TextView) itemView.findViewById(R.id.me_message_day_title);
            message = (TextView) itemView.findViewById(R.id.me_message_day_message);
        }
    }
}
