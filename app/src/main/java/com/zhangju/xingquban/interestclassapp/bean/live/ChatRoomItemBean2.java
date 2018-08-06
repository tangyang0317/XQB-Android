package com.zhangju.xingquban.interestclassapp.bean.live;

import com.netease.nimlib.sdk.chatroom.model.ChatRoomMessage;

/**
 * Created by liush on 2016/11/21 0021.
 *
 * 聊天室 普通聊天消息
 */
public class ChatRoomItemBean2 extends ChatRoomBaseItem{
    private String content;
    private ChatRoomMessage message;

    public ChatRoomItemBean2(int item_type, ChatRoomMessage message) {
        super(item_type);
        this.message = message;
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

    public int getItemType(){
        return super.getItem_type();
    }

    public void setItem_type(int item_type) {
        super.setItem_type(item_type);
    }
}
