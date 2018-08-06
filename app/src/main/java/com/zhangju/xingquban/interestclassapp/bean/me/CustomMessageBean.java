package com.zhangju.xingquban.interestclassapp.bean.me;

import java.util.List;

/**
 * @Created by  liush on 2017/2/24.
 * @describe ${聊天室自定义消息}
 */

public class CustomMessageBean {

    /**
     * to : {"chatUserId":"136","icon":"http://video.xqban.com/Customer/2017-01-18/1484732367535_979.jpg",
     * "accid":"b0b8def80ae8a943d9d91caa92f1a897","name":"把"}
     * op : add
     * chatroomId : 6838055
     * msgtype : 10001
     * roomName : 直播测试
     * attach : [{"summary":"棒棒糖","amount":2,"stdCoin":114634,"icon":"http://video.xqban
     * .com/Gift/2016-11-18/1479462570112_627.png","name":"棒棒糖","giftId":"9","counts":1,"flhtype":"1000"}]
     * roomId : 23
     * from : {"chatUserId":"3","icon":"http://q.qlogo.cn/qqapp/1104938073/66C7BE685394EE6060F554DD20801FDE/40",
     * "accid":"b0b8def80ae8a943b7aaa505a6d10b5e","name":"倪雷工作室"}
     */

    private ToBean           to;
    private String           op;
    private String           chatroomId;
    private int              msgtype;
    private String           roomName;
    private String           roomId;
    private FromBean         from;
    private List<AttachBean> attach;

    public ToBean getTo() {
        return to;
    }

    public void setTo(ToBean to) {
        this.to = to;
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

    public List<AttachBean> getAttach() {
        return attach;
    }

    public void setAttach(List<AttachBean> attach) {
        this.attach = attach;
    }

    public static class ToBean {
        /**
         * chatUserId : 136
         * icon : http://video.xqban.com/Customer/2017-01-18/1484732367535_979.jpg
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

    public static class FromBean {
        /**
         * chatUserId : 3
         * icon : http://q.qlogo.cn/qqapp/1104938073/66C7BE685394EE6060F554DD20801FDE/40
         * accid : b0b8def80ae8a943b7aaa505a6d10b5e
         * name : 倪雷工作室
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
         * summary : 棒棒糖
         * amount : 2
         * stdCoin : 114634
         * icon : http://video.xqban.com/Gift/2016-11-18/1479462570112_627.png
         * name : 棒棒糖
         * giftId : 9
         * counts : 1
         * flhtype : 1000
         */

        private String summary;
        private int    amount;
        private String    stdCoin;
        private String icon;
        private String name;
        private String giftId;
        private int    counts;
        private String flhtype;

        public String getSummary() {
            return summary;
        }

        public void setSummary(String summary) {
            this.summary = summary;
        }

        public int getAmount() {
            return amount;
        }

        public void setAmount(int amount) {
            this.amount = amount;
        }

        public String getStdCoin() {
            return stdCoin;
        }

        public void setStdCoin(String stdCoin) {
            this.stdCoin = stdCoin;
        }

        public String getIcon() {
            return icon;
        }

        public void setIcon(String icon) {
            this.icon = icon;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getGiftId() {
            return giftId;
        }

        public void setGiftId(String giftId) {
            this.giftId = giftId;
        }

        public int getCounts() {
            return counts;
        }

        public void setCounts(int counts) {
            this.counts = counts;
        }

        public String getFlhtype() {
            return flhtype;
        }

        public void setFlhtype(String flhtype) {
            this.flhtype = flhtype;
        }
    }
}
