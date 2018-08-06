package com.zhangju.xingquban.interestclassapp.ui.fragment.me.Order.AllOrders;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.zhangju.xingquban.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zsl on 2017/8/4.
 */

public class AllOrderAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private Context mContext;

    private List<MyAllOrderBean> myAllOrderBeen = new ArrayList<>();

    public AllOrderAdapter(Context context, List<MyAllOrderBean> list) {
        this.mContext = context;
        this.myAllOrderBeen = list;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_my_order, parent, false);
        MyViewHolder myViewHolder = new MyViewHolder(view);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        MyViewHolder holder1 = (MyViewHolder) holder;
        holder1.meOrderTitle.setText("卢晓燕工作室科目课程");
//        holder1.meOrderYctykc.setText(myAllOrderBeen.get(position).getMeOrderYctykc());
//        holder1.meOrderMoney.setText(myAllOrderBeen.get(position).getMeOrderMoney());
//        holder1.meOrderDay.setText(myAllOrderBeen.get(position).getMeOrderDay());
//        holder1.meOrderKcfl.setText(myAllOrderBeen.get(position).getMeOrderKcfl());
    }


    @Override
    public int getItemCount() {
        return myAllOrderBeen.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        //        @BindView(R.id.me_order_title)
//        TextView meOrderTitle;
//        @BindView(R.id.me_order_image)
//        ImageView meOrderImage;
//        @BindView(R.id.me_order_yctykc)
//        TextView meOrderYctykc;
//        @BindView(R.id.me_order_money)
//        TextView meOrderMoney;
//        @BindView(R.id.me_order_day)
//        TextView meOrderDay;
//        @BindView(R.id.me_order_kcfl)
//        TextView meOrderKcfl;
//        @BindView(R.id.me_order_ljzf)
//        RelativeLayout meOrderLjzf;
//        @BindView(R.id.me_order_gd)
//        RelativeLayout meOrderGd;
        private TextView meOrderTitle;

        public MyViewHolder(View itemView) {
            super(itemView);
            meOrderTitle = (TextView) itemView.findViewById(R.id.title);
        }
    }
}
