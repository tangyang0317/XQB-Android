package com.zhangju.xingquban.interestclassapp.ui.fragment.me.Collect;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.zhangju.xingquban.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by zsl on 2017/7/21.
 */

public class MeCollectAllAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> implements View.OnClickListener {
    private MeCollectAllBean mMeCollectAllBean;
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

    public MeCollectAllAdapter(Context context, MeCollectAllBean meCollectAllBean) {
        this.mContext = context;
        this.mMeCollectAllBean = meCollectAllBean;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_me_collect, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        view.setOnClickListener(this);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ViewHolder holder1 = (ViewHolder) holder;

        Glide.with(mContext).load(mMeCollectAllBean.getAaData().get(position).getCustomer().getPicture()).into(holder1.meCollectItemImage);

        holder1.meCollectItemTitle.setText(mMeCollectAllBean.getAaData().get(position).getTeacherTime().getName());
        holder1.meCollectItemNumber.setText(mMeCollectAllBean.getAaData().get(position).getTeacherTime().getCommentCount());
        holder1.meCollectItemBiaoqian.setText(mMeCollectAllBean.getAaData().get(position).getTeacherTime().getCatagoryName());
//        holder1.me_collect_item_leixing.setText("架子鼓");
        holder1.meCollectItemMoney.setText(mMeCollectAllBean.getAaData().get(position).getTeacherTime().getAvgPrice() + "");
        holder1.meCollectItemCity.setText(mMeCollectAllBean.getAaData().get(position).getCustomer().getAreasName());

        //米转公里
        int range = mMeCollectAllBean.getAaData().get(position).getOrganRange();
        double dis = 0;
        dis = Math.round(range / 100d) / 10d;
        holder1.meCollectItemCityNumber.setText(dis + "km");
        holder.itemView.setTag(position);
        switch (mMeCollectAllBean.getAaData().get(position).getTeacherTime().getStar()) {
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
        return mMeCollectAllBean.getAaData().size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.me_collect_item_image)
        ImageView meCollectItemImage;
        @BindView(R.id.me_collect_item_title)
        TextView meCollectItemTitle;
        @BindView(R.id.home_recyclerview_linlayout_title)
        LinearLayout homeRecyclerviewLinlayoutTitle;
        @BindView(R.id.start1)
        ImageView start1;
        @BindView(R.id.start2)
        ImageView start2;
        @BindView(R.id.start3)
        ImageView start3;
        @BindView(R.id.start4)
        ImageView start4;
        @BindView(R.id.start5)
        ImageView start5;
        @BindView(R.id.me_collect_item_number)
        TextView meCollectItemNumber;
        @BindView(R.id.home_recyclerview_linlayoutpj)
        LinearLayout homeRecyclerviewLinlayoutpj;
        @BindView(R.id.me_collect_item_biaoqian)
        TextView meCollectItemBiaoqian;
        @BindView(R.id.home_recyclerview_linlayout_tab)
        LinearLayout homeRecyclerviewLinlayoutTab;
        @BindView(R.id.me_collect_item_money)
        TextView meCollectItemMoney;
        @BindView(R.id.me_collect_item_city)
        TextView meCollectItemCity;
        @BindView(R.id.me_collect_item_cityNumber)
        TextView meCollectItemCityNumber;
        @BindView(R.id.home_recyclerview_linlayout_jiage)
        RelativeLayout homeRecyclerviewLinlayoutJiage;

        ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}
