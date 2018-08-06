package com.zhangju.xingquban.interestclassapp.ui.fragment.find.Wenda;

import java.io.Serializable;
import java.util.List;

/**
 * Created by zsl on 2017/8/31.
 */

public class FindWDCgTiWenBean implements Serializable {

    /**
     * sEcho : 1
     * iTotalRecords : 1
     * iTotalDisplayRecords : 1
     * aaData : [{"editUserId":"12776","answers":0,"isCollection":false,"title":"zhegeshiwode caogao","DateTime":"2秒前","isRelease":2,"qExplain":null,"picStr":[],"clickRate":0,"customerId":"12776","digest":0,"id":"70","isAddUserType":"customer","sorts":0,"editUserName":"bbb(13411111111)","addUserId":"12776","editUserTime":"2017-08-31 16:49:25","display":1,"label":"美术","published":true,"addUserTime":"2017-08-31 16:49:25","removed":0,"authorName":"aaa","authorPicture":"http://video.xqban.com/Customer/2017-07-25/1500981055219_563.jpg","isEditUserType":"customer","addUserName":"bbb(13411111111)"}]
     * attachData : null
     * total : 1
     * page : 0
     * success : true
     * isLogin : true
     * errMsg : {}
     * isAdmin : false
     * cId : 12776
     * cname : 13411111111
     * time : 2017-08-31 16:49:27:830
     */

    private int sEcho;
    private int iTotalRecords;
    private int iTotalDisplayRecords;
    private Object attachData;
    private int total;
    private int page;
    private boolean success;
    private boolean isLogin;
    private ErrMsgBean errMsg;
    private boolean isAdmin;
    private String cId;
    private String cname;
    private String time;
    private List<AaDataBean> aaData;

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

    public Object getAttachData() {
        return attachData;
    }

    public void setAttachData(Object attachData) {
        this.attachData = attachData;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public boolean isIsLogin() {
        return isLogin;
    }

    public void setIsLogin(boolean isLogin) {
        this.isLogin = isLogin;
    }

    public ErrMsgBean getErrMsg() {
        return errMsg;
    }

    public void setErrMsg(ErrMsgBean errMsg) {
        this.errMsg = errMsg;
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

    public List<AaDataBean> getAaData() {
        return aaData;
    }

    public void setAaData(List<AaDataBean> aaData) {
        this.aaData = aaData;
    }

    public static class ErrMsgBean implements Serializable {
    }

    public static class AaDataBean implements Serializable {
        /**
         * editUserId : 12776
         * answers : 0
         * isCollection : false
         * title : zhegeshiwode caogao
         * DateTime : 2秒前
         * isRelease : 2
         * qExplain : null
         * picStr : []
         * clickRate : 0
         * customerId : 12776
         * digest : 0
         * id : 70
         * isAddUserType : customer
         * sorts : 0
         * editUserName : bbb(13411111111)
         * addUserId : 12776
         * editUserTime : 2017-08-31 16:49:25
         * display : 1
         * label : 美术
         * published : true
         * addUserTime : 2017-08-31 16:49:25
         * removed : 0
         * authorName : aaa
         * authorPicture : http://video.xqban.com/Customer/2017-07-25/1500981055219_563.jpg
         * isEditUserType : customer
         * addUserName : bbb(13411111111)
         */

        private String editUserId;
        private int answers;
        private boolean isCollection;
        private String title;
        private String DateTime;
        private int isRelease;
        private Object qExplain;
        private int clickRate;
        private String customerId;
        private int digest;
        private String id;
        private String isAddUserType;
        private int sorts;
        private String editUserName;
        private String addUserId;
        private String editUserTime;
        private int display;
        private String label;
        private boolean published;
        private String addUserTime;
        private int removed;
        private String authorName;
        private String authorPicture;
        private String isEditUserType;
        private String addUserName;
        private List<String> picStr;

        public String getEditUserId() {
            return editUserId;
        }

        public void setEditUserId(String editUserId) {
            this.editUserId = editUserId;
        }

        public int getAnswers() {
            return answers;
        }

        public void setAnswers(int answers) {
            this.answers = answers;
        }

        public boolean isIsCollection() {
            return isCollection;
        }

        public void setIsCollection(boolean isCollection) {
            this.isCollection = isCollection;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getDateTime() {
            return DateTime;
        }

        public void setDateTime(String DateTime) {
            this.DateTime = DateTime;
        }

        public int getIsRelease() {
            return isRelease;
        }

        public void setIsRelease(int isRelease) {
            this.isRelease = isRelease;
        }

        public Object getQExplain() {
            return qExplain;
        }

        public void setQExplain(Object qExplain) {
            this.qExplain = qExplain;
        }

        public int getClickRate() {
            return clickRate;
        }

        public void setClickRate(int clickRate) {
            this.clickRate = clickRate;
        }

        public String getCustomerId() {
            return customerId;
        }

        public void setCustomerId(String customerId) {
            this.customerId = customerId;
        }

        public int getDigest() {
            return digest;
        }

        public void setDigest(int digest) {
            this.digest = digest;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getIsAddUserType() {
            return isAddUserType;
        }

        public void setIsAddUserType(String isAddUserType) {
            this.isAddUserType = isAddUserType;
        }

        public int getSorts() {
            return sorts;
        }

        public void setSorts(int sorts) {
            this.sorts = sorts;
        }

        public String getEditUserName() {
            return editUserName;
        }

        public void setEditUserName(String editUserName) {
            this.editUserName = editUserName;
        }

        public String getAddUserId() {
            return addUserId;
        }

        public void setAddUserId(String addUserId) {
            this.addUserId = addUserId;
        }

        public String getEditUserTime() {
            return editUserTime;
        }

        public void setEditUserTime(String editUserTime) {
            this.editUserTime = editUserTime;
        }

        public int getDisplay() {
            return display;
        }

        public void setDisplay(int display) {
            this.display = display;
        }

        public String getLabel() {
            return label;
        }

        public void setLabel(String label) {
            this.label = label;
        }

        public boolean isPublished() {
            return published;
        }

        public void setPublished(boolean published) {
            this.published = published;
        }

        public String getAddUserTime() {
            return addUserTime;
        }

        public void setAddUserTime(String addUserTime) {
            this.addUserTime = addUserTime;
        }

        public int getRemoved() {
            return removed;
        }

        public void setRemoved(int removed) {
            this.removed = removed;
        }

        public String getAuthorName() {
            return authorName;
        }

        public void setAuthorName(String authorName) {
            this.authorName = authorName;
        }

        public String getAuthorPicture() {
            return authorPicture;
        }

        public void setAuthorPicture(String authorPicture) {
            this.authorPicture = authorPicture;
        }

        public String getIsEditUserType() {
            return isEditUserType;
        }

        public void setIsEditUserType(String isEditUserType) {
            this.isEditUserType = isEditUserType;
        }

        public String getAddUserName() {
            return addUserName;
        }

        public void setAddUserName(String addUserName) {
            this.addUserName = addUserName;
        }

        public List<String> getPicStr() {
            return picStr;
        }

        public void setPicStr(List<String> picStr) {
            this.picStr = picStr;
        }
    }
}
