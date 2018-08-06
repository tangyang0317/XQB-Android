package com.zhangju.xingquban.interestclassapp.bean.live;


import com.zhangju.xingquban.interestclassapp.base.BaseBean;

/**
 * @Created by  liush on 2017/2/27.
 */
public class LiveGiftSendBean
        extends BaseBean {

    /**
     * sEcho : 0
     * iTotalRecords : 0
     * iTotalDisplayRecords : 0
     * aaData : {"id":"18910","fromChatUserId":"136","fromAccid":"b0b8def80ae8a943d9d91caa92f1a897","toChatUserId":"128",
     * "toAccid":"b0b8def80ae8a943ce1d222010b8121e","roomId":"19","chatroomId":"6503287","liveRecordId":"776",
     * "giftId":"9","giftName":"棒棒糖","amount":2,"counts":1,"total_fee":2,"flhtype":"1000","rtnJson":null,
     * "fromChatUser":null,"rooms":null,"gift":null,"removed":0,"addUserId":"10071","addUserName":"把(13056077535)",
     * "addUserTime":"2017-02-27 10:22:49","editUserId":"10071","editUserName":"把(13056077535)","editUserTime":"2017-02-27
     * 10:22:49","isAddUserType":"customer","isEditUserType":"customer","published":true,"sorts":0,"display":1,"digest":0,
     * "del":false,"degreeId":null}
     * attachData : {"stdbeanAmount":9227}
     * total : null
     * page : null
     * success : true
     * isLogin : true
     * isAdmin : false
     * cId : 10071
     * cname : 13056077535
     * time : 2017-02-27 10:22:49:272
     */

    private int sEcho;
    private int            iTotalRecords;
    private int            iTotalDisplayRecords;
    private AaDataBean     aaData;
    private AttachDataBean attachData;
    private Object         total;
    private Object         page;
    private boolean        isLogin;
    private boolean        isAdmin;
    private String         cId;
    private String         cname;
    private String         time;

    public int getSEcho() {
        return sEcho;
    }

    public void setSEcho(int sEcho) {
        this.sEcho = sEcho;
    }

    public int getITotalRecords() {
        return iTotalRecords;
    }

    public void setITotalRecords(int iTotalRecords) {
        this.iTotalRecords = iTotalRecords;
    }

    public int getITotalDisplayRecords() {
        return iTotalDisplayRecords;
    }

    public void setITotalDisplayRecords(int iTotalDisplayRecords) {
        this.iTotalDisplayRecords = iTotalDisplayRecords;
    }

    public AaDataBean getAaData() {
        return aaData;
    }

    public void setAaData(AaDataBean aaData) {
        this.aaData = aaData;
    }

    public AttachDataBean getAttachData() {
        return attachData;
    }

    public void setAttachData(AttachDataBean attachData) {
        this.attachData = attachData;
    }

    public Object getTotal() {
        return total;
    }

    public void setTotal(Object total) {
        this.total = total;
    }

    public Object getPage() {
        return page;
    }

    public void setPage(Object page) {
        this.page = page;
    }

    public boolean isIsLogin() {
        return isLogin;
    }

    public void setIsLogin(boolean isLogin) {
        this.isLogin = isLogin;
    }

    public boolean isIsAdmin() {
        return isAdmin;
    }

    public void setIsAdmin(boolean isAdmin) {
        this.isAdmin = isAdmin;
    }

    public String getCId() {
        return cId;
    }

    public void setCId(String cId) {
        this.cId = cId;
    }

    public String getCname() {
        return cname;
    }

    public void setCname(String cname) {
        this.cname = cname;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public static class AaDataBean {
        /**
         * id : 18910
         * fromChatUserId : 136
         * fromAccid : b0b8def80ae8a943d9d91caa92f1a897
         * toChatUserId : 128
         * toAccid : b0b8def80ae8a943ce1d222010b8121e
         * roomId : 19
         * chatroomId : 6503287
         * liveRecordId : 776
         * giftId : 9
         * giftName : 棒棒糖
         * amount : 2.0
         * counts : 1
         * total_fee : 2.0
         * flhtype : 1000
         * rtnJson : null
         * fromChatUser : null
         * rooms : null
         * gift : null
         * removed : 0
         * addUserId : 10071
         * addUserName : 把(13056077535)
         * addUserTime : 2017-02-27 10:22:49
         * editUserId : 10071
         * editUserName : 把(13056077535)
         * editUserTime : 2017-02-27 10:22:49
         * isAddUserType : customer
         * isEditUserType : customer
         * published : true
         * sorts : 0
         * display : 1
         * digest : 0
         * del : false
         * degreeId : null
         */

        private String id;
        private String  fromChatUserId;
        private String  fromAccid;
        private String  toChatUserId;
        private String  toAccid;
        private String  roomId;
        private String  chatroomId;
        private String  liveRecordId;
        private String  giftId;
        private String  giftName;
        private double  amount;
        private int     counts;
        private double  total_fee;
        private String  flhtype;
        private Object  rtnJson;
        private Object  fromChatUser;
        private Object  rooms;
        private Object  gift;
        private int     removed;
        private String  addUserId;
        private String  addUserName;
        private String  addUserTime;
        private String  editUserId;
        private String  editUserName;
        private String  editUserTime;
        private String  isAddUserType;
        private String  isEditUserType;
        private boolean published;
        private int     sorts;
        private int     display;
        private int     digest;
        private boolean del;
        private Object  degreeId;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getFromChatUserId() {
            return fromChatUserId;
        }

        public void setFromChatUserId(String fromChatUserId) {
            this.fromChatUserId = fromChatUserId;
        }

        public String getFromAccid() {
            return fromAccid;
        }

        public void setFromAccid(String fromAccid) {
            this.fromAccid = fromAccid;
        }

        public String getToChatUserId() {
            return toChatUserId;
        }

        public void setToChatUserId(String toChatUserId) {
            this.toChatUserId = toChatUserId;
        }

        public String getToAccid() {
            return toAccid;
        }

        public void setToAccid(String toAccid) {
            this.toAccid = toAccid;
        }

        public String getRoomId() {
            return roomId;
        }

        public void setRoomId(String roomId) {
            this.roomId = roomId;
        }

        public String getChatroomId() {
            return chatroomId;
        }

        public void setChatroomId(String chatroomId) {
            this.chatroomId = chatroomId;
        }

        public String getLiveRecordId() {
            return liveRecordId;
        }

        public void setLiveRecordId(String liveRecordId) {
            this.liveRecordId = liveRecordId;
        }

        public String getGiftId() {
            return giftId;
        }

        public void setGiftId(String giftId) {
            this.giftId = giftId;
        }

        public String getGiftName() {
            return giftName;
        }

        public void setGiftName(String giftName) {
            this.giftName = giftName;
        }

        public double getAmount() {
            return amount;
        }

        public void setAmount(double amount) {
            this.amount = amount;
        }

        public int getCounts() {
            return counts;
        }

        public void setCounts(int counts) {
            this.counts = counts;
        }

        public double getTotal_fee() {
            return total_fee;
        }

        public void setTotal_fee(double total_fee) {
            this.total_fee = total_fee;
        }

        public String getFlhtype() {
            return flhtype;
        }

        public void setFlhtype(String flhtype) {
            this.flhtype = flhtype;
        }

        public Object getRtnJson() {
            return rtnJson;
        }

        public void setRtnJson(Object rtnJson) {
            this.rtnJson = rtnJson;
        }

        public Object getFromChatUser() {
            return fromChatUser;
        }

        public void setFromChatUser(Object fromChatUser) {
            this.fromChatUser = fromChatUser;
        }

        public Object getRooms() {
            return rooms;
        }

        public void setRooms(Object rooms) {
            this.rooms = rooms;
        }

        public Object getGift() {
            return gift;
        }

        public void setGift(Object gift) {
            this.gift = gift;
        }

        public int getRemoved() {
            return removed;
        }

        public void setRemoved(int removed) {
            this.removed = removed;
        }

        public String getAddUserId() {
            return addUserId;
        }

        public void setAddUserId(String addUserId) {
            this.addUserId = addUserId;
        }

        public String getAddUserName() {
            return addUserName;
        }

        public void setAddUserName(String addUserName) {
            this.addUserName = addUserName;
        }

        public String getAddUserTime() {
            return addUserTime;
        }

        public void setAddUserTime(String addUserTime) {
            this.addUserTime = addUserTime;
        }

        public String getEditUserId() {
            return editUserId;
        }

        public void setEditUserId(String editUserId) {
            this.editUserId = editUserId;
        }

        public String getEditUserName() {
            return editUserName;
        }

        public void setEditUserName(String editUserName) {
            this.editUserName = editUserName;
        }

        public String getEditUserTime() {
            return editUserTime;
        }

        public void setEditUserTime(String editUserTime) {
            this.editUserTime = editUserTime;
        }

        public String getIsAddUserType() {
            return isAddUserType;
        }

        public void setIsAddUserType(String isAddUserType) {
            this.isAddUserType = isAddUserType;
        }

        public String getIsEditUserType() {
            return isEditUserType;
        }

        public void setIsEditUserType(String isEditUserType) {
            this.isEditUserType = isEditUserType;
        }

        public boolean isPublished() {
            return published;
        }

        public void setPublished(boolean published) {
            this.published = published;
        }

        public int getSorts() {
            return sorts;
        }

        public void setSorts(int sorts) {
            this.sorts = sorts;
        }

        public int getDisplay() {
            return display;
        }

        public void setDisplay(int display) {
            this.display = display;
        }

        public int getDigest() {
            return digest;
        }

        public void setDigest(int digest) {
            this.digest = digest;
        }

        public boolean isDel() {
            return del;
        }

        public void setDel(boolean del) {
            this.del = del;
        }

        public Object getDegreeId() {
            return degreeId;
        }

        public void setDegreeId(Object degreeId) {
            this.degreeId = degreeId;
        }
    }

    public static class AttachDataBean {
        /**
         * stdbeanAmount : 9227.0
         */

        private double stdbeanAmount;

        public double getStdbeanAmount() {
            return stdbeanAmount;
        }

        public void setStdbeanAmount(double stdbeanAmount) {
            this.stdbeanAmount = stdbeanAmount;
        }
    }
}
