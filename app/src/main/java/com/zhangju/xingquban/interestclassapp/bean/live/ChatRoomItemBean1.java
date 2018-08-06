package com.zhangju.xingquban.interestclassapp.bean.live;

import com.netease.nimlib.sdk.chatroom.model.ChatRoomMember;
import com.netease.nimlib.sdk.chatroom.model.ChatRoomMessage;

/**
 * Created by liush on 2016/11/21 0021.
 * 聊天室 系统消息
 */
public class ChatRoomItemBean1
        extends ChatRoomBaseItem {
    private String          name;
    private ChatRoomMember member;
    private ChatRoomMessage message;
    private String          content;

    public ChatRoomItemBean1(int item_type, ChatRoomMessage message, String content) {

        super(item_type);
        this.message = message;
        this.content = content;
    }

    public ChatRoomItemBean1(int item_type, ChatRoomMember member, String content) {

        super(item_type);
        if (member != null) {
            this.member = member;
        }
        this.content = content;
    }

    public ChatRoomItemBean1(int item_type, String content) {
        super(item_type);
        this.member = null;
        this.content = content;
    }

    public ChatRoomItemBean1(int item_type, String name, String content) {
        super(item_type);
        this.name = name;
        this.member = null;
        this.content = content;
    }


    public ChatRoomMember getMember() {
        return member;
    }

    public void setMember(ChatRoomMember member) {
        this.member = member;
    }

    public ChatRoomMessage getMessage() {
        return message;
    }

    public void setMessage(ChatRoomMessage message) {
        this.message = message;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getItemType() {
        return super.getItem_type();
    }

    public void setItem_type(int item_type) {
        super.setItem_type(item_type);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
