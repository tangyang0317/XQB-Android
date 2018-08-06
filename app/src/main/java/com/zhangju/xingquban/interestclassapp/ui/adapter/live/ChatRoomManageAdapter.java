package com.zhangju.xingquban.interestclassapp.ui.adapter.live;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.zhangju.xingquban.R;
import com.zhangju.xingquban.interestclassapp.bean.live.ChatRoomManageBean;
import com.zhangju.xingquban.interestclassapp.view.GlideCircleTrans;

import java.util.ArrayList;

/**
 * Created by liush on 2016/12/16 0016.
 *
 * @我的场控
 */
public class ChatRoomManageAdapter extends BaseAdapter {
    private       Context                                  context;
    private       ArrayList<ChatRoomManageBean.AaDataBean> data;
    private final LayoutInflater                           mInflater;
//    private       MyClickListener                          mListener;

    public ChatRoomManageAdapter(Context context, ArrayList<ChatRoomManageBean.AaDataBean> chatRoomManageList
                                    ) {

        this.context = context;
        this.data = chatRoomManageList;
        mInflater = LayoutInflater.from(context);
//        mListener = listener;

    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public Object getItem(int position) {
        return data.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        if (convertView == null) {
            convertView = mInflater.inflate(R.layout.item_live_manage, null);
            holder = new ViewHolder();
            holder.name = (TextView) convertView.findViewById(R.id.tv_live_mymanage_name);
            holder.location = (TextView) convertView.findViewById(R.id.tv_live_mymanage_location);
            holder.cancel = (TextView) convertView.findViewById(R.id.tv_live_mymanage_cancelmanage);
            holder.avatar = (ImageView) convertView.findViewById(R.id.iv_live_mymanage_avatar);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        ChatRoomManageBean.AaDataBean.ChatUserBean chatUser = data.get(position).getChatUser();

        if (chatUser != null) {
            String name = chatUser.getName();
            holder.name.setText(name + "");
            String icon = chatUser.getIcon();
            Glide.with(context)
                    .load(icon)
                    .error(R.drawable.default_icon)
                    .placeholder(R.drawable.default_icon)
                    .transform(new GlideCircleTrans(context))
                    .into(holder.avatar);
        }
        holder.cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cancelClick.onCancelClick(v,position);

            }
        });

        return convertView;
    }

    public class ViewHolder {
        public ImageView avatar;
        public TextView  name;
        public TextView  location;
        public TextView  cancel;
    }
    private CancelClick cancelClick;

    public void setCancelClick(CancelClick cancelClick) {
        this.cancelClick = cancelClick;
    }

    public interface CancelClick{
        void onCancelClick(View view,int position);
    }


    /**
     * 用于回调的抽象类
     */
//    public static abstract class MyClickListener implements View.OnClickListener {
//        /**
//         * 基类的onClick方法
//         */
//        @Override
//        public void onClick(View v) {
//            if (v.getTag() != null) {
//                myOnClick((Integer) v.getTag(), v);
//            }
//        }
//
//        public abstract void myOnClick(int position, View v);
//    }

}
