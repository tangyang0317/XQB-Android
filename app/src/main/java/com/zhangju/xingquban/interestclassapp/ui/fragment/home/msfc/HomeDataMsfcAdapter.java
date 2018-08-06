package com.zhangju.xingquban.interestclassapp.ui.fragment.home.msfc;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.zhangju.xingquban.R;
import com.zhangju.xingquban.interestclassapp.ui.fragment.home.HomeDataTeacherBean;

/**
 * Created by zsl on 2017/8/21.
 */

public class HomeDataMsfcAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> implements View.OnClickListener {
    private HomeDataTeacherBean mHomeDataTeacherBean;
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

    public HomeDataMsfcAdapter(Context context, HomeDataTeacherBean homeDataTeacherBean) {
        this.mContext = context;
        this.mHomeDataTeacherBean = homeDataTeacherBean;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_home_data_msfc, parent, false);
        MyViewHolder myViewHolder = new MyViewHolder(view);
        view.setOnClickListener(this);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        MyViewHolder holder1 = (MyViewHolder) holder;

        try {
            Glide.with(mContext).load(mHomeDataTeacherBean.getAaData().get(0).getReses().get(position).getPicture()).into(holder1.home_data_msfc_image);
            holder1.home_data_msfc_name.setText(mHomeDataTeacherBean.getAaData().get(0).getReses().get(position).getName());
            if (mHomeDataTeacherBean.getAaData().get(0).getReses().get(position).getAge()==0){
                holder1.home_data_msfc_schoolAge.setVisibility(View.GONE);
            }
            holder1.home_data_msfc_schoolAge.setText(mHomeDataTeacherBean.getAaData().get(0).getReses().get(position).getAge() + "年教龄");
            holder1.home_data_msfc_sfghTeacher.setText(mHomeDataTeacherBean.getAaData().get(0).getReses().get(position).getCatagoryName()+"老师");
        } catch (Exception e) {
            e.printStackTrace();
        }
        holder.itemView.setTag(position);
    }

    @Override
    public int getItemCount() {
        return mHomeDataTeacherBean.getAaData().get(0).getReses().size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        private ImageView home_data_msfc_image;
        private TextView home_data_msfc_name, home_data_msfc_schoolAge, home_data_msfc_sfghTeacher;

        public MyViewHolder(View itemView) {
            super(itemView);
            home_data_msfc_image = (ImageView) itemView.findViewById(R.id.home_data_msfc_image);
            home_data_msfc_name = (TextView) itemView.findViewById(R.id.home_data_msfc_name);
            home_data_msfc_schoolAge = (TextView) itemView.findViewById(R.id.home_data_msfc_schoolAge);
            home_data_msfc_sfghTeacher = (TextView) itemView.findViewById(R.id.home_data_msfc_sfghTeacher);


        }
    }

}
