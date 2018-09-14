package com.zhangju.xingquban.interestclassapp.ui.fragment.home.spkc;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.zhangju.xingquban.R;
import com.zhangju.xingquban.interestclassapp.bean.HomeRecylerBean;
import com.zhangju.xingquban.interestclassapp.ui.fragment.home.HomeDataTeacherBean;

/**
 * Created by zsl on 2017/8/21.
 */

public class HomeDataSpkcAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> implements View.OnClickListener {
    private Context mConten;
    private HomeDataTeacherBean mHomeRecyclerbean;

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

    public HomeDataSpkcAdapter(Context context, HomeDataTeacherBean homeRecylerBean) {
        this.mConten = context;
        this.mHomeRecyclerbean = homeRecylerBean;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_home_data_spkc, parent, false);
        MyViewHolder myViewHolder = new MyViewHolder(view);
        view.setOnClickListener(this);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        MyViewHolder holder1 = (MyViewHolder) holder;

        Glide.with(mConten).load(mHomeRecyclerbean.getAaData().get(0).getVideoLesson().get(position).getVideoTitlePic()).into(holder1.home_data_spkc_image);
        holder1.home_data_spkc_text.setText(mHomeRecyclerbean.getAaData().get(0).getVideoLesson().get(position).getTitle());
        holder.itemView.setTag(position);
    }

    @Override
    public int getItemCount() {
        return mHomeRecyclerbean.getAaData().get(0).getVideoLesson().size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        private ImageView home_data_spkc_image;
        private TextView home_data_spkc_text;

        public MyViewHolder(View itemView) {
            super(itemView);
            home_data_spkc_image = (ImageView) itemView.findViewById(R.id.home_data_spkc_image);
            home_data_spkc_text = (TextView) itemView.findViewById(R.id.home_data_spkc_text);

        }
    }
}
