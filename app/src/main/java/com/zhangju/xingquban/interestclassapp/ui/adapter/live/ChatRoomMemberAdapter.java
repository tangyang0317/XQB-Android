package com.zhangju.xingquban.interestclassapp.ui.adapter.live;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.zhangju.xingquban.R;
import com.zhangju.xingquban.interestclassapp.view.GlideCircleTrans;
import com.netease.nimlib.sdk.chatroom.model.ChatRoomMember;

import java.util.List;

/**
 * Created by liush on 2016/11/23 0023.
 * <p>
 * 聊天室成员信息
 */
public class ChatRoomMemberAdapter extends ArrayAdapter<ChatRoomMember> {

    private Context mContext;
    private List<ChatRoomMember> mData;
    private final LayoutInflater mInflater;


    public ChatRoomMemberAdapter(Context context, List<ChatRoomMember> result) {
        super(context, R.layout.item_live_chatroom_member,result);
        this.mContext = context;
        this.mData = result;
        mInflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    public void addItem(ChatRoomMember member) {
        mData.add(member);
        this.notifyDataSetChanged();
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ChatRoomMemberHolder holder;
        if (convertView == null) {
            holder = new ChatRoomMemberHolder();
            convertView = mInflater.inflate(R.layout.item_live_chatroom_member, null);
            holder.civAvatar = (ImageView) convertView.findViewById(R.id.iv_chatroom_avatar);
            convertView.setTag(holder);
        } else {
            holder = (ChatRoomMemberHolder) convertView.getTag();
        }
        if (mData != null) {
            String avatar = mData.get(position).getAvatar();
            if (avatar != null) {
                Glide.with(mContext)
                        .load(avatar)
                        .error(R.drawable.default_icon)
                        .placeholder(R.drawable.default_icon)
                        .transform(new GlideCircleTrans(mContext))
                        .into(holder.civAvatar);
            }
        }
        return convertView;
    }
}
