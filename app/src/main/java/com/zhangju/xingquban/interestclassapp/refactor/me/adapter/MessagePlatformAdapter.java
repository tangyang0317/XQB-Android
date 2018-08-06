package com.zhangju.xingquban.interestclassapp.refactor.me.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.zhangju.xingquban.R;
import com.zhangju.xingquban.interestclassapp.adapter.baseadpter.BaseRecycleViewAdapter;
import com.zhangju.xingquban.interestclassapp.refactor.me.bean.VipPlatform;
import com.zhangju.xingquban.interestclassapp.view.imageView.CustomRoundView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by hqf on 2017/11/25.
 * vip商户 信息平台Adapter
 */

public class MessagePlatformAdapter extends BaseRecycleViewAdapter {

    private List<VipPlatform> platformList;
    private int mSelectedPos = -1;

    public void setPosition(int position) {
        this.mSelectedPos = position;
        notifyDataSetChanged();
    }

    public MessagePlatformAdapter(Context c, List<VipPlatform> platformList) {
        super(c);
        this.platformList = platformList;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new VipPlatFormViewHolder(resIdToView(parent, R.layout.item_meessge_info));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        VipPlatFormViewHolder vipPlatFormViewHolder = (VipPlatFormViewHolder) holder;
        vipPlatFormViewHolder.onBind();
    }

    @Override
    public int getItemCount() {
        return platformList.size();
    }

    class VipPlatFormViewHolder extends RecyclerView.ViewHolder {
        int pos;
        @BindView(R.id.find_message_title)
        TextView findMessageTitle;
        @BindView(R.id.find_message_describle)
        TextView findMessageDescrible;
        @BindView(R.id.find_message_sign)
        TextView findMessageSign;
        @BindView(R.id.find_message_time)
        TextView findMessageTime;
        @BindView(R.id.platform_bottom)
        RelativeLayout platform_bottom;
        @BindView(R.id.view_line)
        View view_line;


        public VipPlatFormViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        void onBind() {
            pos = getLayoutPosition();
            VipPlatform dataBean = platformList.get(pos);
            String title = dataBean.getTitle() == null ? "" : dataBean.getTitle();
            String userTime = dataBean.getAddUserTime() == null ? "" : dataBean.getAddUserTime();
            String teacherName = dataBean.getTeacherName() == null ? "" : dataBean.getTeacherName();
            String infoContent = dataBean.getInfoContent() == null ? "" : dataBean.getInfoContent();
            String time = userTime.substring(0, userTime.indexOf(" "));

            findMessageTitle.setText(title);
            findMessageDescrible.setText(infoContent);
            findMessageSign.setText(teacherName);
            findMessageTime.setText(time);


            platform_bottom.setVisibility(View.GONE);
            view_line.setVisibility(View.GONE);

        }
    }


}
