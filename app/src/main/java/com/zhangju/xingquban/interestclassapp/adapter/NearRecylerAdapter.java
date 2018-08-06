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
import com.zhangju.xingquban.interestclassapp.bean.NearDataBean;



/**
 * Created by Administrator on 2017/7/2.
 */

public class NearRecylerAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> implements View.OnClickListener {
    private LayoutInflater layoutInflater;
    private NearDataBean data;
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

    public NearRecylerAdapter(Context context,  NearDataBean mdata) {
        this.mContext = context;
        this.data = mdata;
    }

    public NearRecylerAdapter(Context context) {
        this.mContext = context;

    }
    public NearDataBean getdata(){
        return this.data;
    }

    public void addData(NearDataBean mdata){

        this.data = mdata;
        notifyDataSetChanged();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.home_recyler_item, null, false);
        NearRecylerAdapter.MyViewHolder homeData = new NearRecylerAdapter.MyViewHolder(view);
        view.setOnClickListener(this);
        return homeData;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        MyViewHolder holder1 = (MyViewHolder) holder;

        Glide.with(mContext)
                .load(data.getAaData().get(position).getPicture())
                .placeholder(R.drawable.rec_seize_bitmao)
                .error(R.drawable.rec_seize_bitmao)
                .into(holder1.home_recyclerview_item_image);
        if(data.getAaData().get(position).getDegreeId().equals("1")) //1老师 2机构
            holder1.home_recyclerview_item_title.setText(data.getAaData().get(position).getUsername());
        else
            holder1.home_recyclerview_item_title.setText(data.getAaData().get(position).getName());
        holder1.home_recyclerview_item_coursename.setText(data.getAaData().get(position).getCatagoryName());
        if (data.getAaData().get(position).getQcAuth() == 2) {

            holder1.zhizhirenzheng.setVisibility(View.VISIBLE);
        } else {
            holder1.zhizhirenzheng.setVisibility(View.GONE);
        }
        if (data.getAaData().get(position).getMinVipPrice()==0.0){
            holder1.home_recyclerview_item_money.setVisibility(View.GONE);
        }
        else {
            holder1.home_recyclerview_item_money.setVisibility(View.VISIBLE);
            holder1.home_recyclerview_item_money.setText("￥"+data.getAaData().get(position).getMinVipPrice() + "");
        }

        if (data.getAaData().get(position).getCommentCount()<=0){
            holder1.near_number.setVisibility(View.GONE);
        }

        else{
            holder1.near_number.setVisibility(View.VISIBLE);
            holder1.near_number.setText(data.getAaData().get(position).getCommentCount() + "条");
        }
        holder1.home_recyclerview_item_city.setText(data.getAaData().get(position).getAreasName());

        //米转公里
        int range = data.getAaData().get(position).getRange();
        double dis = 0;
        dis = Math.round(range / 100d) / 10d;
        if (range<1000)
            holder1.home_recyclerview_item_cityNumber.setText(range + "m");
        else
          holder1.home_recyclerview_item_cityNumber.setText(dis + "km");

        if (data.getAaData().get(position).getVideoLesson().size() <= 0) {
            holder1.home_recyclerview_linlayout_shipin.setVisibility(View.GONE);
        } else {
            holder1.home_recyclerview_linlayout_shipin.setVisibility(View.VISIBLE);
        }
        if (data.getAaData().get(position).getLessons().size() <=0) {
            holder1.home_recyclerview_linlayout_kecheng.setVisibility(View.GONE);
        } else {
            holder1.home_recyclerview_linlayout_kecheng.setVisibility(View.VISIBLE);
            holder1.home_recyclerview_item_course.setText(data.getAaData().get(position).getLessons().get(0).getName());
        }

        holder.itemView.setTag(position);
        switch ((int) data.getAaData().get(position).getAvgComment()) {
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
        try {
            return data.getAaData().size();
        }catch (Exception e){
            return 0;
        }

    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        private ImageView home_recyclerview_item_image, start1, start2, start3, start4, start5,zhizhirenzheng;
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
            zhizhirenzheng= (ImageView) itemView.findViewById(R.id.home_recyclerview_item_huiyuan);
            start1 = (ImageView) itemView.findViewById(R.id.start1);
            start2 = (ImageView) itemView.findViewById(R.id.start2);
            start3 = (ImageView) itemView.findViewById(R.id.start3);
            start4 = (ImageView) itemView.findViewById(R.id.start4);
            start5 = (ImageView) itemView.findViewById(R.id.start5);
        }
    }

}
