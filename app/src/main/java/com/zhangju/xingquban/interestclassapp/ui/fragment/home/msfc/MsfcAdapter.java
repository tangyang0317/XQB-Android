package com.zhangju.xingquban.interestclassapp.ui.fragment.home.msfc;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.zhangju.xingquban.R;
import com.zhangju.xingquban.interestclassapp.ui.fragment.home.HomeDataTeacherBean;
import com.zhangju.xingquban.interestclassapp.view.imageView.CustomRoundView;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by zsl on 2017/8/21.
 */

public class MsfcAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> implements View.OnClickListener {
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

    public MsfcAdapter(Context context, HomeDataTeacherBean homeDataTeacherBean) {
        this.mContext = context;
        this.mHomeDataTeacherBean = homeDataTeacherBean;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_msfc_all, parent, false);
        MyViewHolder myViewHolder = new MyViewHolder(view);
        view.setOnClickListener(this);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        MyViewHolder holder1 = (MyViewHolder) holder;

        try {
            Glide.with(mContext).load(mHomeDataTeacherBean.getAaData().get(0).getReses().get(position).getPicture()).into(holder1.headicon);
            holder1.teacName.setText(mHomeDataTeacherBean.getAaData().get(0).getReses().get(position).getName());
            if(mHomeDataTeacherBean.getAaData().get(0).getReses().get(position).getAge()<=0){
                holder1.tecAge.setVisibility(View.GONE);
                holder1.line.setVisibility(View.GONE);
            }
            holder1.tecAge.setText(mHomeDataTeacherBean.getAaData().get(0).getReses().get(position).getAge() + "");
            holder1.teacShuxing.setText("资深"+mHomeDataTeacherBean.getAaData().get(0).getReses().get(position).getCatagoryName()+"教师");
            holder1.tvSchoole.setText(mHomeDataTeacherBean.getAaData().get(0).getReses().get(position).getCatagoryName());
        } catch (Exception e) {
            e.printStackTrace();
        }
        holder.itemView.setTag(position);
    }

    @Override
    public int getItemCount() {
        return mHomeDataTeacherBean.getAaData().get(0).getReses().size();
    }


    static class MyViewHolder extends RecyclerView.ViewHolder{
        @BindView(R.id.headicon)
        CustomRoundView headicon;
        @BindView(R.id.teac_name)
        TextView teacName;
        @BindView(R.id.teac_shuxing)
        TextView teacShuxing;
        @BindView(R.id.tec_age)
        TextView tecAge;
        @BindView(R.id.tv_schoole)
        TextView tvSchoole;
        @BindView(R.id.line_nl)
        View line;

        MyViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}
