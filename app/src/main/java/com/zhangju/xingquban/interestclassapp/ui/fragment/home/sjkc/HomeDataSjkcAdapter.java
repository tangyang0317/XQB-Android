package com.zhangju.xingquban.interestclassapp.ui.fragment.home.sjkc;

import android.content.Context;
import android.graphics.Paint;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.zhangju.xingquban.R;
import com.zhangju.xingquban.interestclassapp.bean.HomeRecylerBean;

/**
 * Created by zsl on 2017/8/21.
 */

public class HomeDataSjkcAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> implements View.OnClickListener{
    private int mMaxCount=2;
    private HomeRecylerBean.AaDataBean mHomeRecyclerbean;
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

    public HomeDataSjkcAdapter(Context context, HomeRecylerBean.AaDataBean lessonsBean) {
        this.mContext = context;
        this.mHomeRecyclerbean = lessonsBean;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_home_data_sjkc, parent, false);
        MyViewHolder myViewHolder = new MyViewHolder(view);
        view.setOnClickListener(this);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        MyViewHolder holder1 = (MyViewHolder) holder;

        Glide.with(mContext).load(mHomeRecyclerbean.getLessons().get(position).getPicture()).error(R.drawable.rec_seize_bitmao).into(holder1.home_data_sjkc_image);

        holder1.home_data_sjkc_title.setText(mHomeRecyclerbean.getLessons().get(position).getName());
        if (mHomeRecyclerbean.getLessons().get(position).getVipPrice()==null)
            holder1.home_data_sjkc_money.setText("￥"+0+ "");
        else
        holder1.home_data_sjkc_money.setText("￥"+mHomeRecyclerbean.getLessons().get(position).getVipPrice() + "");
        holder1.tvputong.setText("￥"+mHomeRecyclerbean.getLessons().get(position).getPrice() + "");
        holder1.tvputong.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG);

        holder1.home_data_sjkc_text1.setText(mHomeRecyclerbean.getLessons().get(position).getCatagoryName());
        holder1.home_data_sjkc_text2.setText(mHomeRecyclerbean.getLessons().get(position).getMethod());
//        holder1.home_data_sjkc_text3.setText(mHomeRecyclerbean.getLessons().get(position).getAllows()+"人");
        holder.itemView.setTag(position);
    }

    @Override
    public int getItemCount() {
        if(mHomeRecyclerbean.getLessons().size()>mMaxCount)
            return mMaxCount;
        else return mHomeRecyclerbean.getLessons().size();
    }

    public void setMaxCount(int maxCount){
        mMaxCount=maxCount;
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        private ImageView home_data_sjkc_image;
        private TextView home_data_sjkc_title, home_data_sjkc_money, home_data_sjkc_text1, home_data_sjkc_text2, home_data_sjkc_text3,tvputong;

        public MyViewHolder(View itemView) {
            super(itemView);
            home_data_sjkc_image = (ImageView) itemView.findViewById(R.id.home_data_sjkc_image);
            tvputong= (TextView) itemView.findViewById(R.id.tv_putongprice);

            home_data_sjkc_title = (TextView) itemView.findViewById(R.id.home_data_sjkc_title);
            home_data_sjkc_money = (TextView) itemView.findViewById(R.id.home_data_sjkc_money);
            home_data_sjkc_text1 = (TextView) itemView.findViewById(R.id.home_data_sjkc_text1);
            home_data_sjkc_text2 = (TextView) itemView.findViewById(R.id.home_data_sjkc_text2);
            home_data_sjkc_text3 = (TextView) itemView.findViewById(R.id.home_data_sjkc_text3);

        }
    }

}
