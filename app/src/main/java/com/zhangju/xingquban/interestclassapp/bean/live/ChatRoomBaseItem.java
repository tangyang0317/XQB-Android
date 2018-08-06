package com.zhangju.xingquban.interestclassapp.bean.live;

/**
 * Created by liush on 2016/11/21 0021.
 *
 * @聊天室item
 */
public class ChatRoomBaseItem {
    private int item_type = 0;

    public ChatRoomBaseItem(int item_type) {
        this.item_type = item_type;
    }

    public int getItem_type() {
        return item_type;
    }

    public void setItem_type(int item_type) {
        this.item_type = item_type;
    }
}
