package com.zhangju.xingquban.interestclassapp.bean.live;

import java.util.List;

/**
 * @Created by  liush on 2017/3/17.
 * @describe ${收到直播结束的自定义消息}
 */

public class LiveExitDataBean {

    /**
     * roomId : 23
     * from : {"chatUserId":"136","icon":"http://video.xqban.com/Customer/2017-03-10/1489127933244_929.jpg",
     * "accid":"b0b8def80ae8a943d9d91caa92f1a897","name":"把"}
     * op : add
     * chatroomId : 6838055
     * msgtype : 10003
     * roomName : 安卓直播
     * attach : [{"onlineUserCount":1,"icon":"http://video.xqban.com/Customer/2017-03-10/1489127933244_929.jpg",
     * "roomName":"把","caseTimeStr":"00:00"}]
     */

    private String roomId;
    private FromBean         from;
    private String           op;
    private String           chatroomId;
    private int              msgtype;
    private String           roomName;
    private List<AttachBean> attach;

    public String getRoomId() {
        return roomId;
    }

    public void setRoomId(String roomId) {
        this.roomId = roomId;
    }

    public FromBean getFrom() {
        return from;
    }

    public void setFrom(FromBean from) {
        this.from = from;
    }

    public String getOp() {
        return op;
    }

    public void setOp(String op) {
        this.op = op;
    }

    public String getChatroomId() {
        return chatroomId;
    }

    public void setChatroomId(String chatroomId) {
        this.chatroomId = chatroomId;
    }

    public int getMsgtype() {
        return msgtype;
    }

    public void setMsgtype(int msgtype) {
        this.msgtype = msgtype;
    }

    public String getRoomName() {
        return roomName;
    }

    public void setRoomName(String roomName) {
        this.roomName = roomName;
    }

    public List<AttachBean> getAttach() {
        return attach;
    }

    public void setAttach(List<AttachBean> attach) {
        this.attach = attach;
    }

    public static class FromBean {
        /**
         * chatUserId : 136
         * icon : http://video.xqban.com/Customer/2017-03-10/1489127933244_929.jpg
         * accid : b0b8def80ae8a943d9d91caa92f1a897
         * name : 把
         */

        private String chatUserId;
        private String icon;
        private String accid;
        private String name;

        public String getChatUserId() {
            return chatUserId;
        }

        public void setChatUserId(String chatUserId) {
            this.chatUserId = chatUserId;
        }

        public String getIcon() {
            return icon;
        }

        public void setIcon(String icon) {
            this.icon = icon;
        }

        public String getAccid() {
            return accid;
        }

        public void setAccid(String accid) {
            this.accid = accid;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }

    public static class AttachBean {
        /**
         * onlineUserCount : 1
         * icon : http://video.xqban.com/Customer/2017-03-10/1489127933244_929.jpg
         * roomName : 把
         * caseTimeStr : 00:00
         */

        private String onlineUserCount;
        private String icon;
        private String roomName;
        private String caseTimeStr;

        public String getOnlineUserCount() {
            return onlineUserCount;
        }

        public void setOnlineUserCount(String onlineUserCount) {
            this.onlineUserCount = onlineUserCount;
        }

        public String getIcon() {
            return icon;
        }

        public void setIcon(String icon) {
            this.icon = icon;
        }

        public String getRoomName() {
            return roomName;
        }

        public void setRoomName(String roomName) {
            this.roomName = roomName;
        }

        public String getCaseTimeStr() {
            return caseTimeStr;
        }

        public void setCaseTimeStr(String caseTimeStr) {
            this.caseTimeStr = caseTimeStr;
        }
    }
}
