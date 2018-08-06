package com.zhangju.xingquban.interestclassapp.ui.adapter.live;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.fastlib.widget.RoundImageView;
import com.zhangju.xingquban.R;
import com.zhangju.xingquban.interestclassapp.adapter.baseadpter.BaseRecycleViewAdapter;
import com.zhangju.xingquban.interestclassapp.bean.live.LiveAttentionListBean;
import com.zhangju.xingquban.interestclassapp.view.imageView.TopOvelImageView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * create by hqf 2017/11/6
 * 直播关注列表
 */

public class AttentionAdapter extends BaseRecycleViewAdapter {


    private List<LiveAttentionListBean.AaDataBean> mAttentionList;

    public AttentionAdapter(Context c, List<LiveAttentionListBean.AaDataBean> mAttentionList) {
        super(c);
        this.mAttentionList = mAttentionList;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new AttentionViewHolder(resIdToView(parent, R.layout.item_live_attention_play));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        AttentionViewHolder attentionholder = (AttentionViewHolder) holder;
        attentionholder.onBind();

    }

    @Override
    public int getItemCount() {
        return mAttentionList.size();
    }

    class AttentionViewHolder extends RecyclerView.ViewHolder {

        int pos;
        @BindView(R.id.image_pic)
        RoundImageView imagePic;
        @BindView(R.id.tv_name)
        TextView tvName;
        @BindView(R.id.tv_city)
        TextView tvCity;
        @BindView(R.id.tv_people_num)
        TextView tvPeopleNum;
        @BindView(R.id.tv_state)
        TextView tvState;
        @BindView(R.id.img_resource_small)
        TopOvelImageView imgResourceSmall;
        @BindView(R.id.tv_video_time)
        TextView tvVideoTime;

        @BindView(R.id.tv_title)
        TextView tv_title;

        public AttentionViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        void onBind() {
            pos = getLayoutPosition();
            registerOnItemClickListener(pos, itemView);
            LiveAttentionListBean.AaDataBean item = mAttentionList.get(pos);
            if (item != null) {
                LiveAttentionListBean.AaDataBean.ChatUserBean chatUser = item.getChatUser();
                LiveAttentionListBean.AaDataBean.LiveRecord liveRecord = item.getLiveRecord();

                if (chatUser!=null){
                    String name = chatUser.getName()==null?"":chatUser.getName();
                    tvName.setText(name);
                }
                if (liveRecord!=null) {
                    String roomName = liveRecord.getRoomName()==null?"":liveRecord.getRoomName();
                    Integer onlineUserCount = liveRecord.getOnlineUserCount();
                    String cityName = liveRecord.getCityName()==null?"":liveRecord.getCityName();
                    String roomPic = liveRecord.getRoomPic()==null?"":liveRecord.getRoomPic();
                    tv_title.setText(roomName);
                    tvPeopleNum.setText(onlineUserCount+"人");
                    Glide.with(c).load(roomPic).placeholder(R.drawable.app_icon).dontAnimate().dontTransform().into(imagePic);
                    Glide.with(c).load(roomPic).placeholder(R.drawable.app_icon).dontAnimate().dontTransform().into(imgResourceSmall);
                    if (cityName.isEmpty()){
                        tvCity.setText("未知城市");
                    }else {
                        tvCity.setText(cityName);
                    }
                }
            }
        }
    }

    //视频类型区分
    private String getPlayStatus(int status, float price) {
        if (status == 0) {
            return "空闲";
        } else if (status == 1) {
            if (price <= 0) {
                return "直播中";
            } else {
                return "付费";
            }

        } else if (status == 2) {
            return "禁用";
        } else if (status == 3) {
            return "直播录制";
        }
        return "";
    }
}