package com.zhangju.xingquban.interestclassapp.ui.adapter.live;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.zhangju.xingquban.R;
import com.zhangju.xingquban.interestclassapp.bean.live.ChatRoomBaseItem;
import com.zhangju.xingquban.interestclassapp.bean.live.ChatRoomItemBean1;
import com.zhangju.xingquban.interestclassapp.bean.live.ChatRoomItemBean2;
import com.netease.nimlib.sdk.chatroom.model.ChatRoomMember;
import com.netease.nimlib.sdk.chatroom.model.ChatRoomMessage;
import com.netease.nimlib.sdk.chatroom.model.ChatRoomMessageExtension;
import com.netease.nimlib.sdk.chatroom.model.ChatRoomNotificationAttachment;

import java.util.ArrayList;

/**
 * Created by liush on 2016/11/21 0021.
 *
 * @直播聊天室adapter
 */
public class ChatRoomAdapter
        extends BaseAdapter {

    private Context                     mContext;
    private ArrayList<ChatRoomBaseItem> mMessageData;
    private LayoutInflater              mInflater;
    final int VIEW_TYPE = 3;
    final int TYPE_1    = 0;   // 提示消息
    final int TYPE_2    = 1;   // 系统通知
    final int TYPE_3    = 2;   // 普通消息


    public ChatRoomAdapter(Context context, ArrayList<ChatRoomBaseItem> messageData) {
        this.mMessageData = messageData;
        mInflater = LayoutInflater.from(context);
    }

    //添加一个新的Item，并通知listview进行显示刷新
    public void addItem(ChatRoomBaseItem newItem) {
        this.mMessageData.add(newItem);
        this.notifyDataSetChanged();
    }

    @Override
    public int getItemViewType(int position) {
        return mMessageData.get(position).getItem_type();
    }

    @Override
    public int getViewTypeCount() {

        return 3;
    }

    @Override
    public int getCount() {
        if (mMessageData == null) {
            return 0;
        }
        return mMessageData.size();
    }

    @Override
    public Object getItem(int position) {

        return mMessageData.get(position);
    }

    @Override
    public long getItemId(int position) {

        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        viewHolder1 holder1 = null;
        viewHolder2 holder2 = null;
        viewHolder3 holder3 = null;
        int itemType = getItemViewType(position);
        if (convertView == null) {
            switch (itemType) {
                case TYPE_1:
                    convertView = mInflater.inflate(R.layout.item_live_chatroom_sys, parent, false);
                    holder1 = new viewHolder1();
                    holder1.name = (TextView) convertView.findViewById(R.id.tv_chatroom_sys_name);
                    holder1.content = (TextView) convertView.findViewById(R.id.tv_chatroom_sys_content);
                    convertView.setTag(holder1);
                    break;
                case TYPE_2:
                    convertView = mInflater.inflate(R.layout.item_live_chatroom_sys, parent, false);
                    holder2 = new viewHolder2();
                    holder2.name = (TextView) convertView.findViewById(R.id.tv_chatroom_sys_name);
                    holder2.content = (TextView) convertView.findViewById(R.id.tv_chatroom_sys_content);
                    convertView.setTag(holder2);

                    break;
                case TYPE_3:
                    convertView = mInflater.inflate(R.layout.item_live_chatroom_user, parent, false);
                    holder3 = new viewHolder3();
                    holder3.name = (TextView) convertView.findViewById(R.id.tv_chatroom_user_name);
                    holder3.content = (TextView) convertView.findViewById(R.id.tv_chatroom_user_content);
                    convertView.setTag(holder3);
                    break;
            }
        } else {
            switch (itemType) {
                case TYPE_1:
                    holder1 = (viewHolder1) convertView.getTag();
                    break;
                case TYPE_2:
                    holder2 = (viewHolder2) convertView.getTag();
                    break;
                case TYPE_3:
                    holder3 = (viewHolder3) convertView.getTag();
                    break;
            }
        }
        if (mMessageData != null && mMessageData.size() >= 1) {
            ChatRoomMessage message;
            String name = "";
            String content = "";
            switch (itemType) {
                case TYPE_1:    // 系统提示
                    ChatRoomMember member = ((ChatRoomItemBean1) mMessageData.get(position)).getMember();
                    if (member == null) {
                        content = ((ChatRoomItemBean1) mMessageData.get(position)).getContent();
                    } else {
                        name = member.getNick();
                        content = ((ChatRoomItemBean1) mMessageData.get(position)).getContent();
                    }
                    holder1.content.setText(content + "");
                    holder1.name.setText(name + "");
                    break;
                case TYPE_2:    //  系统通知
                    message = ((ChatRoomItemBean1) mMessageData.get(position)).getMessage();
                    content = ((ChatRoomItemBean1) mMessageData.get(position)).getContent();
                    if (message != null) {
                        ChatRoomNotificationAttachment attachment = (ChatRoomNotificationAttachment) message
                                .getAttachment();
                        if (attachment != null) {
                            name = attachment.getOperatorNick();
                        } else {
                            ChatRoomMessageExtension extension = message.getChatRoomMessageExtension();
                            if (extension != null) {
                                name = extension.getSenderNick();
                            }
                        }
                    } else {
                        name = ((ChatRoomItemBean1) mMessageData.get(position)).getName();
                    }
                    holder2.name.setText(name + "");
                    holder2.content.setText(content + "");
                    break;
                case TYPE_3:    // 普通消息
                    message = ((ChatRoomItemBean2) mMessageData.get(position)).getMessage();
                    if (message != null) {
                        String nick = null;
                        if (message.getChatRoomMessageExtension() != null) {
                            nick = message.getChatRoomMessageExtension().getSenderNick();
                        } else {
                            nick = message.getFromNick();
                        }
                        holder3.name.setText(nick + "");
                        holder3.content.setText(message.getContent() + "");
                    }
                    break;
            }
        }
        return convertView;
    }

    //各个布局的控件资源
    class viewHolder1 {
        TextView name;
        TextView content;
    }

    class viewHolder2 {
        ImageView leve;
        TextView  name;
        TextView  content;
    }

    class viewHolder3 {
        ImageView leve;
        TextView  name;
        TextView  content;
    }
}
