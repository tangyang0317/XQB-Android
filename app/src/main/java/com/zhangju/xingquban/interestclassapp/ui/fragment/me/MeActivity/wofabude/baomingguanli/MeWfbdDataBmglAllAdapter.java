package com.zhangju.xingquban.interestclassapp.ui.fragment.me.MeActivity.wofabude.baomingguanli;

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
 * Created by zsl on 2017/8/16.
 */

public class MeWfbdDataBmglAllAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> implements View.OnClickListener {
    private Context mContext;
    private List<MeWfbdDataBmglAllBean> mList = new ArrayList<>();
    private HomeRecylerAdapter.OnItemClickListener mOnItemClickListener = null;

    public MeWfbdDataBmglAllAdapter(Context context, List<MeWfbdDataBmglAllBean> list) {
        this.mContext = context;
        this.mList = list;
    }

    //define interfacequestion
    public static interface OnItemClickListener {
        void onItemClick(View view, int position);
    }

    public void setOnItemClickListener(HomeRecylerAdapter.OnItemClickListener listener) {
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
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_meactivity_wfbdbmglall, parent, false);
        MyViewHolder myViewHolder = new MyViewHolder(view);
        view.setOnClickListener(this);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        MyViewHolder holder1 = (MyViewHolder) holder;
//        holder1.item_wfbdbmglall_title.setText("");
//        holder1.item_wfbdbmglall_piaoq.setText("");
//        holder1.item_wfbdbmglall_time.setText("");
        holder1.item_wfbdbmglall_youxiao.setText("有效");
        holder.itemView.setTag(position);

    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        private ImageView item_wfbdbmglall_icon;
        private TextView item_wfbdbmglall_title, item_wfbdbmglall_piaoq, item_wfbdbmglall_time, item_wfbdbmglall_youxiao;

        public MyViewHolder(View itemView) {
            super(itemView);

            item_wfbdbmglall_title = (TextView) itemView.findViewById(R.id.item_wfbdbmglall_title);
            item_wfbdbmglall_piaoq = (TextView) itemView.findViewById(R.id.item_wfbdbmglall_piaoq);
            item_wfbdbmglall_time = (TextView) itemView.findViewById(R.id.item_wfbdbmglall_time);
            item_wfbdbmglall_youxiao = (TextView) itemView.findViewById(R.id.item_wfbdbmglall_youxiao);
        }
    }
}
