package com.zhangju.xingquban.interestclassapp.ui.fragment.find.SeekMessage;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.zhangju.xingquban.R;
import com.zhangju.xingquban.interestclassapp.adapter.baseadpter.BaseRecycleViewAdapter;
import com.zhangju.xingquban.interestclassapp.view.imageView.CustomRoundView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * create by hqf
 */
public class FindMessageAdapter extends BaseRecycleViewAdapter {


    private int TYPE_IMAGE = 0x11;//图片
    private int TYPE_CONTENT = 0x22;//主体信息

    private List<FindMessageBean.AaDataBean> mMessageList;

    public FindMessageAdapter(Context c, List<FindMessageBean.AaDataBean> mMessageList) {
        super(c);
        this.mMessageList = mMessageList;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
      /*  if (viewType == TYPE_IMAGE) {
            return new ImageViewHolder(resIdToView(parent, R.layout.message_head_image));
        }*/
        return new MessageViewHolder(resIdToView(parent, R.layout.item_meessge_info));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
     /*   if (holder instanceof ImageViewHolder) {
            ImageViewHolder imageViewHolder = (ImageViewHolder) holder;
            imageViewHolder.onBind();
        } else if (holder instanceof MessageViewHolder) {*/
            MessageViewHolder messageViewHolder = (MessageViewHolder) holder;
            messageViewHolder.onBind();

//        }

    }

    @Override
    public int getItemCount() {
        return mMessageList.size();
    }

    @Override
    public int getItemViewType(int position) {
      /*  if (position == 0) {
            return TYPE_IMAGE;
        }*/
        return TYPE_CONTENT;
    }

    //顶部图片
    class ImageViewHolder extends RecyclerView.ViewHolder {

        int pos;

        public ImageViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        void onBind() {
            pos = getLayoutPosition();

        }
    }

    //主体信息
    class MessageViewHolder extends RecyclerView.ViewHolder {
        int pos;

        @BindView(R.id.find_message_title)
        TextView findMessageTitle;
        @BindView(R.id.find_message_describle)
        TextView findMessageDescrible;
        @BindView(R.id.find_message_sign)
        TextView findMessageSign;
        @BindView(R.id.find_message_time)
        TextView findMessageTime;
        @BindView(R.id.find_message_headpic)
        CustomRoundView findMessageHeadpic;
        @BindView(R.id.find_message_name)
        TextView findMessageName;
        @BindView(R.id.find_message_see_count)
        TextView findMessageSeeCount;
        @BindView(R.id.find_message_city)
        TextView findMessageCity;

        public MessageViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        void onBind() {
            pos = getLayoutPosition();
            registerOnItemClickListener(pos, itemView);

            if (mMessageList != null && mMessageList.size() > 0) {
                FindMessageBean.AaDataBean message = mMessageList.get(pos);

                if (message != null) {
                    int countView = message.getCountView();//观看数
                    Object pictureUrl = message.getPictureUrl() == null ? "" : message.getPictureUrl();//头像
                    String title = message.getTitle() == null ? "" : message.getTitle();
                    String infoContent = message.getInfoContent() == null ? "" : message.getInfoContent();//描述
                    String teacherName = message.getTeacherName() == null ? "" : message.getTeacherName();//昵称
                    String cityName = message.getCityName() == null ? "" : message.getCityName();//城市
                    String addUserTime = message.getAddUserTime() == null ? "" : message.getAddUserTime();//时间
                    int infoType = message.getInfoType();// 1  (招生)  2  (招聘) 3  （租赁） 4  （转租）


                    findMessageTitle.setText(title);
                    findMessageCity.setText(cityName);
                    findMessageDescrible.setText(infoContent);
                    findMessageName.setText(teacherName);
                    findMessageSeeCount.setText(countView + "");
                    findMessageSign.setText(getType(infoType));
                    if (!addUserTime.isEmpty()) {
                        String time = addUserTime.substring(0, addUserTime.indexOf(" "));
                        findMessageTime.setText(time);
                    }
                    if (!pictureUrl.toString().isEmpty()) {
                        Glide.with(c).load(pictureUrl.toString()).placeholder(R.drawable.app_icon).dontAnimate().dontTransform().into(findMessageHeadpic);
                    }

                }

            }


        }
    }

    //获取类型
    private String getType(int type) {
        if (type==1){
            return "招生";
        }else if (type==2){
            return "招聘";
        }else if (type==3){
            return "租赁";
        }else if (type==4){
            return "转租";
        }
        return "";
    }
}
