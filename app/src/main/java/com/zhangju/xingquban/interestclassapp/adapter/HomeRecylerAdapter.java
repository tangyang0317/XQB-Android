package com.zhangju.xingquban.interestclassapp.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.zhangju.xingquban.R;
import com.zhangju.xingquban.interestclassapp.bean.HomeRecylerBean;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/7/2.
 */

public class HomeRecylerAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> implements View.OnClickListener {
    private LayoutInflater layoutInflater;


    private List<HomeRecylerBean.AaDataBean> list = new ArrayList<>();





    private HomeRecylerBean mHomeRecylerBean;
    private Context mContext;
    private OnItemClickListener mOnItemClickListener = null;

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

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.mOnItemClickListener = listener;
    }

    public HomeRecylerAdapter(Context context, HomeRecylerBean homeRecylerBean) {
        this.mContext = context;
        this.mHomeRecylerBean = homeRecylerBean;
    }

    public void setHomeRecyler(HomeRecylerBean mList) {
        this.mHomeRecylerBean = mList;
        notifyDataSetChanged();

    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.home_recyler_item, parent, false);
        HomeRecylerAdapter.MyViewHolder homeData = new HomeRecylerAdapter.MyViewHolder(view);
        view.setOnClickListener(this);
        return homeData;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        MyViewHolder holder1 = (MyViewHolder) holder;

        Glide.with(mContext).load(mHomeRecylerBean.getAaData().get(position).getPicture()).into(holder1.home_recyclerview_item_image);
        holder1.home_recyclerview_item_title.setText(mHomeRecylerBean.getAaData().get(position).getSigname());
        holder1.home_recyclerview_item_coursename.setText(mHomeRecylerBean.getAaData().get(position).getCatagoryName());
        holder1.home_recyclerview_item_money.setText(mHomeRecylerBean.getAaData().get(position).getAvgPrice() + "");
        holder1.near_number.setText(mHomeRecylerBean.getAaData().get(position).getCommentCount() + "");
        holder1.home_recyclerview_item_city.setText(mHomeRecylerBean.getAaData().get(position).getAreasName());

        //米转公里
        int range = mHomeRecylerBean.getAaData().get(position).getRange();
        double dis = 0;
        dis = Math.round(range / 100d) / 10d;
        holder1.home_recyclerview_item_cityNumber.setText(dis + "km");

        if (mHomeRecylerBean.getAaData().get(position).getVideoLesson().size() == 0) {
            holder1.home_recyclerview_linlayout_shipin.setVisibility(View.GONE);
        } else {
            holder1.home_recyclerview_linlayout_shipin.setVisibility(View.VISIBLE);
        }
        if (mHomeRecylerBean.getAaData().get(position).getLessons().size() == 0) {
            holder1.home_recyclerview_linlayout_kecheng.setVisibility(View.GONE);
        } else {
            holder1.home_recyclerview_linlayout_kecheng.setVisibility(View.VISIBLE);
            holder1.home_recyclerview_item_course.setText(mHomeRecylerBean.getAaData().get(position).getLessons().get(0).getName());
        }


        holder.itemView.setTag(position);
        switch (mHomeRecylerBean.getAaData().get(position).getStar()) {
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
        return mHomeRecylerBean.getAaData().size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        private ImageView home_recyclerview_item_image, start1, start2, start3, start4, start5;
        private TextView home_recyclerview_item_title;
        private TextView home_recyclerview_item_course;
        private TextView home_recyclerview_item_coursename;
        private TextView home_recyclerview_item_money, near_number, home_recyclerview_item_city, home_recyclerview_item_cityNumber;
        private LinearLayout home_recyclerview_linlayout_shipin, home_recyclerview_linlayout_kecheng;

        public MyViewHolder(View itemView) {
            super(itemView);
            home_recyclerview_item_image = (ImageView) itemView.findViewById(R.id.home_recyclerview_item_image);
            home_recyclerview_item_title = (TextView) itemView.findViewById(R.id.home_recyclerview_item_title);
            home_recyclerview_item_course = (TextView) itemView.findViewById(R.id.home_recyclerview_item_course);
            home_recyclerview_item_coursename = (TextView) itemView.findViewById(R.id.home_recyclerview_item_coursename);
            home_recyclerview_item_money = (TextView) itemView.findViewById(R.id.home_recyclerview_item_money);
            home_recyclerview_linlayout_shipin = (LinearLayout) itemView.findViewById(R.id.home_recyclerview_linlayout_shipin);
            home_recyclerview_linlayout_kecheng = (LinearLayout) itemView.findViewById(R.id.home_recyclerview_linlayout_kecheng);
            near_number = (TextView) itemView.findViewById(R.id.near_number);
            home_recyclerview_item_city = (TextView) itemView.findViewById(R.id.home_recyclerview_item_city);
            home_recyclerview_item_cityNumber = (TextView) itemView.findViewById(R.id.home_recyclerview_item_cityNumber);

            start1 = (ImageView) itemView.findViewById(R.id.start1);
            start2 = (ImageView) itemView.findViewById(R.id.start2);
            start3 = (ImageView) itemView.findViewById(R.id.start3);
            start4 = (ImageView) itemView.findViewById(R.id.start4);
            start5 = (ImageView) itemView.findViewById(R.id.start5);
        }
    }

}
