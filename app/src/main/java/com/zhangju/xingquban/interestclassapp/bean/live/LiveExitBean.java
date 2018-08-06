package com.zhangju.xingquban.interestclassapp.bean.live;


import com.zhangju.xingquban.interestclassapp.base.BaseBean;

/**
 * Created by liush on 2016/11/9 0009.
 */

public class LiveExitBean extends BaseBean {

    /**
     * sEcho : 0
     * iTotalRecords : 0
     * iTotalDisplayRecords : 0
     * aaData : {"onlineUserCount":0,"stdCoin":0,"caseTimeStr":"0:0","chatUserId":"19","name":"王光辉",
     * "icon":"files/2016-01-14/TeacherTime/1452744891895@hhxin72013a20.jpg","follows":0,"roomsId":"32","gets":0,
     * "gives":0,"chatroomId":"4592573"}
     * attachData : null
     * total : null
     * page : null
     * success : true
     * isLogin : true
     * isAdmin : false
     * cId : 48
     * cname : 18854254524
     * time : 2016-11-09 17:50:53:410
     */

    private int sEcho;
    private int        iTotalRecords;
    private int        iTotalDisplayRecords;
    private AaDataBean aaData;
    private Object     attachData;
    private Object     total;
    private Object     page;
    private boolean    isLogin;
    private boolean    isAdmin;
    private String     cId;
    private String     cname;
    private String     time;

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

    public Object getAttachData() {
        return attachData;
    }

    public void setAttachData(Object attachData) {
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
         * onlineUserCount : 0
         * stdCoin : 0
         * caseTimeStr : 0:0
         * chatUserId : 19
         * name : 王光辉
         * icon : files/2016-01-14/TeacherTime/1452744891895@hhxin72013a20.jpg
         * follows : 0
         * roomsId : 32
         * gets : 0
         * gives : 0
         * chatroomId : 4592573
         */

        private int onlineUserCount;
        private int    stdCoin;
        private String caseTimeStr;
        private String chatUserId;
        private String name;
        private String icon;
        private int    follows;
        private String roomsId;
        private int    gets;
        private int    gives;
        private String chatroomId;

        public int getOnlineUserCount() {
            return onlineUserCount;
        }

        public void setOnlineUserCount(int onlineUserCount) {
            this.onlineUserCount = onlineUserCount;
        }

        public int getStdCoin() {
            return stdCoin;
        }

        public void setStdCoin(int stdCoin) {
            this.stdCoin = stdCoin;
        }

        public String getCaseTimeStr() {
            return caseTimeStr;
        }

        public void setCaseTimeStr(String caseTimeStr) {
            this.caseTimeStr = caseTimeStr;
        }

        public String getChatUserId() {
            return chatUserId;
        }

        public void setChatUserId(String chatUserId) {
            this.chatUserId = chatUserId;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getIcon() {
            return icon;
        }

        public void setIcon(String icon) {
            this.icon = icon;
        }

        public int getFollows() {
            return follows;
        }

        public void setFollows(int follows) {
            this.follows = follows;
        }

        public String getRoomsId() {
            return roomsId;
        }

        public void setRoomsId(String roomsId) {
            this.roomsId = roomsId;
        }

        public int getGets() {
            return gets;
        }

        public void setGets(int gets) {
            this.gets = gets;
        }

        public int getGives() {
            return gives;
        }

        public void setGives(int gives) {
            this.gives = gives;
        }

        public String getChatroomId() {
            return chatroomId;
        }

        public void setChatroomId(String chatroomId) {
            this.chatroomId = chatroomId;
        }
    }

}
