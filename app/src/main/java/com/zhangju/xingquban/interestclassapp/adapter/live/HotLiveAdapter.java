package com.zhangju.xingquban.interestclassapp.adapter.live;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.zhangju.xingquban.R;
import com.zhangju.xingquban.interestclassapp.adapter.baseadpter.BaseRecycleViewAdapter;
import com.zhangju.xingquban.interestclassapp.bean.HotNewsLiveBean;
import com.zhangju.xingquban.interestclassapp.view.imageView.CustomRoundView;
import com.zhangju.xingquban.interestclassapp.view.imageView.TopOvelImageView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * create by hqf 2017/11/6
 */
public class HotLiveAdapter extends BaseRecycleViewAdapter {

    private List<HotNewsLiveBean.AaDataBean> mHotList = new ArrayList<>();

    public HotLiveAdapter(Context c, List<HotNewsLiveBean.AaDataBean> mHotList) {
        super(c);
        this.mHotList = mHotList;

    }



    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new HotViewHolder(resIdToView(parent, R.layout.attention_live_item));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        HotViewHolder hotViewHolder = (HotViewHolder) holder;
        hotViewHolder.onBind();

    }

    @Override
    public int getItemCount() {
        return mHotList.size();
    }

    class HotViewHolder extends RecyclerView.ViewHolder {
        int pos;
        @BindView(R.id.img_resource_small)
        ImageView imgResourceSmall;
        @BindView(R.id.tv_video_time)
        TextView tvVideoTime;
        @BindView(R.id.tv_resource_type)
        TextView tvResourceType;
        @BindView(R.id.tv_resource_title)
        TextView tvResourceTitle;
        @BindView(R.id.tv_resource_money)
        TextView tvResourceMoney;
        @BindView(R.id.tv_resource_looks)
        TextView tvResourceLooks;
        @BindView(R.id.img_author_head)
        CustomRoundView imgAuthorHead;
        @BindView(R.id.tv_author_name)
        TextView tvAuthorName;
        @BindView(R.id.img_video_comment)
        ImageView imgVideoComment;
        @BindView(R.id.tv_video_commentnum)
        TextView tvVideoCommentnum;
        @BindView(R.id.img_video_start)
        ImageView imgVideoStart;
        @BindView(R.id.tv_video_startnum)
        TextView tvVideoStartnum;


        public HotViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        void onBind() {
            pos = getLayoutPosition();
            HotNewsLiveBean.AaDataBean item = mHotList.get(pos);
            registerOnItemClickListener(pos,itemView);


            if (item != null) {
                int rstype = item.getRstype();//1直播 2回播
                if (rstype == 1) {
                    HotNewsLiveBean.AaDataBean.ModelsBean liveModels = item.getModels();
                    String roomName = liveModels.getRoomName() == null ? "" : liveModels.getRoomName();//直播名称
                    String roomPic = liveModels.getRoomPic() == null ? "" : liveModels.getRoomPic();//直播背景图
                    int onlineUserCount = liveModels.getOnlineUserCount();//在线人数
                    double amount = liveModels.getAmount();//价格
                    int follows = liveModels.getFollows();//关注数
                    int comtCount = liveModels.getComtCount();//评论人数
                    if (liveModels.getChatUser() != null) {
                        String name = liveModels.getChatUser().getName()==null?"":liveModels.getChatUser().getName();
                        String icon = liveModels.getChatUser().getIcon()==null?"":liveModels.getChatUser().getIcon();
                        Glide.with(c).load(icon.isEmpty() ? R.drawable.default_icon : icon).into(imgAuthorHead);
                        tvAuthorName.setText(name);
                    }
                    tvResourceTitle.setText(roomName);
                    Glide.with(c).load(roomPic).placeholder(R.mipmap.item_find_wd_bg).dontTransform().dontAnimate().into(imgResourceSmall);
                    tvResourceLooks.setText(onlineUserCount + "人在看");
                    tvVideoCommentnum.setText(String.valueOf(comtCount));
                    tvVideoStartnum.setText(String.valueOf(follows));
                    tvResourceMoney.setText("¥" + amount);
                } else if (rstype == 2) {
                    String roomName = item.getRoomName() == null ? "" : item.getRoomName();//名称
                    String roomPic = item.getRoomPic() == null ? "" : item.getRoomPic();//背景图
                    int count = item.getModels().getSeeCount();//观看人数
                    tvResourceTitle.setText(roomName);

                    Glide.with(c).load(roomPic).placeholder(R.drawable.app_icon).dontTransform().dontAnimate().into(imgResourceSmall);
                    tvResourceLooks.setText(count + "人在看");
                    if (item.getModels() != null) {
                        String comtCount = String.valueOf(item.getModels().getComtCount());//消息数量
                        String likesCount = String.valueOf(item.getModels().getLikesCount());//关注数
                        tvVideoCommentnum.setText(comtCount);
                        tvVideoStartnum.setText(likesCount);
                        if (item.getModels().getChatUser() != null) {
                            HotNewsLiveBean.AaDataBean.ModelsBean.ChatUserBean chatUser = item.getModels().getChatUser();
                            String icon = chatUser.getIcon() == null ? "" : chatUser.getIcon();//头像
                            String name = chatUser.getName() == null ? "" : chatUser.getName();//昵称
                            Glide.with(c).load(icon.isEmpty() ? R.drawable.default_icon : icon).into(imgAuthorHead);
                            tvAuthorName.setText(name);
                        }
                    }
                }
            }
        }


        //状态判断
        //        status: '直播间状态_int_0：空闲 1：直播  2：禁用  3：直播录制'
        private String getStatus(int status) {
            if (status == 0) {
                return "空闲";
            } else if (status == 1) {
                return "直播";
            } else if (status == 2) {
                return "禁用";
            } else if (status == 3) {
                return "直播录制";
            } else {
                return "";
            }
        }
    }
}

