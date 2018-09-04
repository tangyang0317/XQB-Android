package com.zhangju.xingquban.interestclassapp.adapter.near;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.zhangju.xingquban.R;
import com.zhangju.xingquban.interestclassapp.bean.near.CurriculumBean;
import com.zhangju.xingquban.interestclassapp.ui.activity.near.CurriculumOrderActivity;

import java.text.DecimalFormat;

/**
 * Created by zsl on 2017/8/21.
 */

public class CurriculumAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> implements View.OnClickListener {
    private CurriculumBean curriculumBean;
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

    public CurriculumAdapter(Context context) {
        this.mContext = context;
    }

    public void addData(CurriculumBean mcurriculumBean) {
        this.curriculumBean = mcurriculumBean;
        notifyDataSetChanged();

    }

    public CurriculumBean getData() {
        return this.curriculumBean;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_curriculum_data_sjkc, parent, false);
        MyViewHolder myViewHolder = new MyViewHolder(view);
        view.setOnClickListener(this);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        MyViewHolder holder1 = (MyViewHolder) holder;

        Glide.with(mContext).load(curriculumBean.getAaData().get(position).getPicture()).error(R.drawable.rec_seize_bitmao).into(holder1.home_data_sjkc_image);

        holder1.home_data_sjkc_title.setText(curriculumBean.getAaData().get(position).getName());

        DecimalFormat decimalFormat = new DecimalFormat("##.##");
        String p = decimalFormat.format(curriculumBean.getAaData().get(position).getVipPrice());//format 返回的是字符串
        holder1.home_data_sjkc_money.setText(p);

     /*   if (UserManager.getInstance().getUser().isMember){
            DecimalFormat decimalFormat=new DecimalFormat("##.##");
            String p=decimalFormat.format(curriculumBean.getAaData().get(position).getVipPrice());//format 返回的是字符串
            holder1.home_data_sjkc_money.setText(p);
        }else {
            DecimalFormat decimalFormat=new DecimalFormat("##.##");
            String p=decimalFormat.format(curriculumBean.getAaData().get(position).getVipPrice());//format 返回的是字符串
            holder1.home_data_sjkc_money.setText(p);
        }*/


        holder1.home_data_sjkc_text1.setText(curriculumBean.getAaData().get(position).getCatagoryName());
        if (curriculumBean.getAaData().get(position).getMethod() == null) {
            holder1.home_data_sjkc_text2.setText("协商地点");
        } else {
            holder1.home_data_sjkc_text2.setText(curriculumBean.getAaData().get(position).getMethod());
        }
        holder1.home_data_sjkc_text3.setText(curriculumBean.getAaData().get(position).getAllows() + "人");
        //米转公里
        int range = curriculumBean.getAaData().get(position).getRange();
        double dis = 0;
        dis = Math.round(range / 100d) / 10d;
        if (curriculumBean.getAaData().get(position).getAreasName() == null)
            holder1.tvjuli.setText(dis + "km");
        else
            holder1.tvjuli.setText(curriculumBean.getAaData().get(position).getAreasName() + dis + "km");
        holder1.lijiqiagke.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CurriculumOrderActivity.lanuchActivity(mContext, curriculumBean.getAaData().get(position).getId());
            }
        });
        holder.itemView.setTag(position);
    }

    @Override
    public int getItemCount() {
        try {
            return curriculumBean.getAaData().size();
        } catch (Exception e) {
            return 0;
        }
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        private ImageView home_data_sjkc_image;
        private TextView home_data_sjkc_title, home_data_sjkc_money, home_data_sjkc_text1, home_data_sjkc_text2, home_data_sjkc_text3, tvjuli, lijiqiagke;

        public MyViewHolder(View itemView) {
            super(itemView);
            home_data_sjkc_image = (ImageView) itemView.findViewById(R.id.home_data_sjkc_image);
            lijiqiagke = (TextView) itemView.findViewById(R.id.textView12);

            home_data_sjkc_title = (TextView) itemView.findViewById(R.id.home_data_sjkc_title);
            home_data_sjkc_money = (TextView) itemView.findViewById(R.id.home_data_sjkc_money);
            home_data_sjkc_text1 = (TextView) itemView.findViewById(R.id.home_data_sjkc_text1);
            home_data_sjkc_text2 = (TextView) itemView.findViewById(R.id.home_data_sjkc_text2);
            home_data_sjkc_text3 = (TextView) itemView.findViewById(R.id.home_data_sjkc_text3);
            tvjuli = (TextView) itemView.findViewById(R.id.tv_juli);

        }
    }

}
