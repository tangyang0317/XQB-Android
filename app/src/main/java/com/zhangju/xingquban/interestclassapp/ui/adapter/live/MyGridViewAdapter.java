package com.zhangju.xingquban.interestclassapp.ui.adapter.live;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.zhangju.xingquban.R;
import com.zhangju.xingquban.interestclassapp.bean.live.LiveGiftBean;

import java.util.List;


public class MyGridViewAdapter
        extends BaseAdapter {

    private Context                       mContext;
    private List<LiveGiftBean.AaDataBean> mLists;

    public MyGridViewAdapter(Context pContext, List<LiveGiftBean.AaDataBean> mLists) {
        this.mContext = pContext;
        this.mLists = mLists;

    }

    @Override
    public int getCount() {

        return mLists.size();
    }

    @Override
    public LiveGiftBean.AaDataBean getItem(int position) {

        return mLists.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {

        Holder holder = null;
        if (null == convertView) {
            holder = new Holder();
            LayoutInflater mInflater = LayoutInflater.from(mContext);
            convertView = mInflater.inflate(R.layout.gridview_gift_item, null);
            holder.ivGiftIcon = (ImageView) convertView.findViewById(R.id.iv_giftlist_icon);
            holder.ivGiftIcon.setFocusable(false);
            holder.tvGiftAmount = (TextView) convertView.findViewById(R.id.tv_giftlist_amount);
            holder.llGift_item = (LinearLayout) convertView.findViewById(R.id.ll_giftlist_item);
            convertView.setTag(holder);
        } else {
            holder = (Holder) convertView.getTag();
        }
        //        holder.btn_gv_item.setText(mLists.get(position));
        holder.tvGiftAmount.setText(mLists.get(position).getAmount() + "豆");
        Glide.with(mContext)
                .load(mLists.get(position).getIcon())
                .into(holder.ivGiftIcon);

        return convertView;
    }

    private static class Holder {
        ImageView    ivGiftIcon;
        TextView     tvGiftAmount;
        LinearLayout llGift_item;
    }

}
