package com.zhangju.xingquban.interestclassapp.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.zhangju.xingquban.R;
import com.zhangju.xingquban.interestclassapp.bean.NearDataBean;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zsl on 2017/7/8.
 */

public class NearDataAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> implements View.OnClickListener {
    private static final String TAG = "NearDataAdapter";
    private LayoutInflater layoutInflater;
    private List<NearDataBean.AaDataBean> list = new ArrayList<>();
    private Context mContext;
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

    public NearDataAdapter(Context context, List<NearDataBean.AaDataBean> mList) {
        this.mContext = context;
        this.list = mList;
    }

    public void setTeacherData(List<NearDataBean.AaDataBean> mList) {
        this.list = mList;
        notifyDataSetChanged();
    }

    public void setInstitutionData(List<NearDataBean.AaDataBean> mList) {
        this.list = mList;
        notifyDataSetChanged();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.home_recyler_item2, parent, false);
        NearDataAdapter.MyViewHolder nearData = new NearDataAdapter.MyViewHolder(view);
        view.setOnClickListener(this);
        return nearData;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        MyViewHolder holder1 = (MyViewHolder) holder;

        Glide.with(mContext).load(list.get(position).getPicture()).into(holder1.near_recyclerview_item_image);

        holder1.near_recyclerview_item_title.setText(list.get(position).getSigname());
//        holder1.near_recyclerview_item_course.setText(list.get(position).getCatagoryName());
        holder1.near_recyclerview_item_coursename.setText(list.get(position).getCatagoryName());
        holder1.near_recyclerview_item_money.setText(list.get(position).getAvgPrice() + "");
//        holder1.near_recyclerview_item_money_false.setText(list.get(position).());
        holder.itemView.setTag(position);
        switch (list.get(position).getStar()) {
            case 0:
                holder1.start1.setImageResource(R.drawable.home_recyler_item_pingjiatwo);
                holder1.start2.setImageResource(R.drawable.home_recyler_item_pingjiatwo);
                holder1.start3.setImageResource(R.drawable.home_recyler_item_pingjiatwo);
                holder1.start4.setImageResource(R.drawable.home_recyler_item_pingjiatwo);
                holder1.start5.setImageResource(R.drawable.home_recyler_item_pingjiatwo);
                break;
            case 1:
                holder1.start1.setImageResource(R.drawable.home_recyler_item_pingjia);
                holder1.start2.setImageResource(R.drawable.home_recyler_item_pingjiatwo);
                holder1.start3.setImageResource(R.drawable.home_recyler_item_pingjiatwo);
                holder1.start4.setImageResource(R.drawable.home_recyler_item_pingjiatwo);
                holder1.start5.setImageResource(R.drawable.home_recyler_item_pingjiatwo);
                break;
            case 2:
                holder1.start1.setImageResource(R.drawable.home_recyler_item_pingjia);
                holder1.start2.setImageResource(R.drawable.home_recyler_item_pingjia);
                holder1.start3.setImageResource(R.drawable.home_recyler_item_pingjiatwo);
                holder1.start4.setImageResource(R.drawable.home_recyler_item_pingjiatwo);
                holder1.start5.setImageResource(R.drawable.home_recyler_item_pingjiatwo);
                break;
            case 3:
                holder1.start1.setImageResource(R.drawable.home_recyler_item_pingjia);
                holder1.start2.setImageResource(R.drawable.home_recyler_item_pingjia);
                holder1.start3.setImageResource(R.drawable.home_recyler_item_pingjia);
                holder1.start4.setImageResource(R.drawable.home_recyler_item_pingjiatwo);
                holder1.start5.setImageResource(R.drawable.home_recyler_item_pingjiatwo);
                break;
            case 4:
                holder1.start1.setImageResource(R.drawable.home_recyler_item_pingjia);
                holder1.start2.setImageResource(R.drawable.home_recyler_item_pingjia);
                holder1.start3.setImageResource(R.drawable.home_recyler_item_pingjia);
                holder1.start4.setImageResource(R.drawable.home_recyler_item_pingjia);
                holder1.start5.setImageResource(R.drawable.home_recyler_item_pingjiatwo);
                break;
            case 5:
                holder1.start1.setImageResource(R.drawable.home_recyler_item_pingjia);
                holder1.start2.setImageResource(R.drawable.home_recyler_item_pingjia);
                holder1.start3.setImageResource(R.drawable.home_recyler_item_pingjia);
                holder1.start4.setImageResource(R.drawable.home_recyler_item_pingjia);
                holder1.start5.setImageResource(R.drawable.home_recyler_item_pingjia);
                break;
        }

    }

    @Override
    public int getItemCount() {
        Log.i(TAG, "getItemCount: " + list.size());
        return list.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        private ImageView near_recyclerview_item_image, start1, start2, start3, start4, start5;
        private TextView near_recyclerview_item_title, near_recyclerview_item_course,
                near_recyclerview_item_coursename, near_recyclerview_item_money,
                near_recyclerview_item_money_false;

        public MyViewHolder(View itemView) {
            super(itemView);
            near_recyclerview_item_image = (ImageView) itemView.findViewById(R.id.near_recyclerview_item_image);
            near_recyclerview_item_title = (TextView) itemView.findViewById(R.id.near_recyclerview_item_title);
            near_recyclerview_item_course = (TextView) itemView.findViewById(R.id.near_recyclerview_item_course);
            near_recyclerview_item_coursename = (TextView) itemView.findViewById(R.id.near_recyclerview_item_coursename);
            near_recyclerview_item_money = (TextView) itemView.findViewById(R.id.near_recyclerview_item_money);
            near_recyclerview_item_money_false = (TextView) itemView.findViewById(R.id.near_recyclerview_item_money_false);

            start1 = (ImageView) itemView.findViewById(R.id.start1);
            start2 = (ImageView) itemView.findViewById(R.id.start2);
            start3 = (ImageView) itemView.findViewById(R.id.start3);
            start4 = (ImageView) itemView.findViewById(R.id.start4);
            start5 = (ImageView) itemView.findViewById(R.id.start5);

        }
    }
}
