package com.zhangju.xingquban.interestclassapp.bean.near;

import java.io.Serializable;
import java.util.List;

/**
 * Created by zgl on 2017/12/14.
 */

public class VideoLessonsBean implements Serializable {

    /**
     * sEcho : 1
     * iTotalRecords : 1
     * iTotalDisplayRecords : 1
     * aaData : [{"editUserId":"12777","buyNum":1,"latitude":0,"isCollection":false,"title":"收费视频","videoTitlePic":"http://video.xqban.com/Customer/2017-12-14/1513219386244_45.jpg","videoStr":"http://video.xqban.com/Customer/2017-12-14/1513219386371_773.mp4","price":1,"clickRate":8,"customerId":"12776","digest":0,"id":"95","isAddUserType":"customer","sorts":0,"longitude":0,"address":"选择地点","editUserName":"刘北风(13422222222)","addUserId":"12776","editUserTime":"2017-12-14 11:00:09","display":1,"published":true,"userName":"张老师2","commentNum":0,"addUserTime":"2017-12-14 10:43:07","videoFilesId":"79","removed":0,"customerPicList":["http://video.xqban.com/Customer/2017-10-27/1509075506202_425.jpg"],"contactWay":"3853856","comment":"测试收费","isCharge":1,"isEditUserType":"customer","isPayed":true,"addUserName":"张老师2(13411111111)"}]
     * attachData : null
     * total : 1
     * page : 0
     * success : true
     * isLogin : true
     * errMsg : {}
     * isAdmin : false
     * cId : 12777
     * cname : 13422222222
     * time : 2017-12-14 11:00:09:480
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

    public static class ErrMsgBean implements Serializable{
    }

    public static class AaDataBean implements Serializable{
        /**
         * editUserId : 12777
         * buyNum : 1
         * latitude : 0
         * isCollection : false
         * title : 收费视频
         * videoTitlePic : http://video.xqban.com/Customer/2017-12-14/1513219386244_45.jpg
         * videoStr : http://video.xqban.com/Customer/2017-12-14/1513219386371_773.mp4
         * price : 1
         * clickRate : 8
         * customerId : 12776
         * digest : 0
         * id : 95
         * isAddUserType : customer
         * sorts : 0
         * longitude : 0
         * address : 选择地点
         * editUserName : 刘北风(13422222222)
         * addUserId : 12776
         * editUserTime : 2017-12-14 11:00:09
         * display : 1
         * published : true
         * userName : 张老师2
         * commentNum : 0
         * addUserTime : 2017-12-14 10:43:07
         * videoFilesId : 79
         * removed : 0
         * customerPicList : ["http://video.xqban.com/Customer/2017-10-27/1509075506202_425.jpg"]
         * contactWay : 3853856
         * comment : 测试收费
         * isCharge : 1
         * isEditUserType : customer
         * isPayed : true
         * addUserName : 张老师2(13411111111)
         */

        private String editUserId;
        private int buyNum;
        private int latitude;
        private boolean isCollection;
        private String title;
        private String videoTitlePic;
        private String videoStr;
        private int price;
        private int clickRate;
        private String customerId;
        private int digest;
        private String id;
        private String isAddUserType;
        private int sorts;
        private int longitude;
        private String address;
        private String editUserName;
        private String addUserId;
        private String editUserTime;
        private int display;
        private boolean published;
        private String userName;
        private int commentNum;
        private String addUserTime;
        private String videoFilesId;
        private int removed;
        private String contactWay;
        private String comment;
        private int isCharge;
        private String isEditUserType;
        private boolean isPayed;
        private String addUserName;
        private List<String> customerPicList;

        public String getEditUserId() {
            return editUserId;
        }

        public void setEditUserId(String editUserId) {
            this.editUserId = editUserId;
        }

        public int getBuyNum() {
            return buyNum;
        }

        public void setBuyNum(int buyNum) {
            this.buyNum = buyNum;
        }

        public int getLatitude() {
            return latitude;
        }

        public void setLatitude(int latitude) {
            this.latitude = latitude;
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

        public String getVideoTitlePic() {
            return videoTitlePic;
        }

        public void setVideoTitlePic(String videoTitlePic) {
            this.videoTitlePic = videoTitlePic;
        }

        public String getVideoStr() {
            return videoStr;
        }

        public void setVideoStr(String videoStr) {
            this.videoStr = videoStr;
        }

        public int getPrice() {
            return price;
        }

        public void setPrice(int price) {
            this.price = price;
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

        public int getLongitude() {
            return longitude;
        }

        public void setLongitude(int longitude) {
            this.longitude = longitude;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
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

        public boolean isPublished() {
            return published;
        }

        public void setPublished(boolean published) {
            this.published = published;
        }

        public String getUserName() {
            return userName;
        }

        public void setUserName(String userName) {
            this.userName = userName;
        }

        public int getCommentNum() {
            return commentNum;
        }

        public void setCommentNum(int commentNum) {
            this.commentNum = commentNum;
        }

        public String getAddUserTime() {
            return addUserTime;
        }

        public void setAddUserTime(String addUserTime) {
            this.addUserTime = addUserTime;
        }

        public String getVideoFilesId() {
            return videoFilesId;
        }

        public void setVideoFilesId(String videoFilesId) {
            this.videoFilesId = videoFilesId;
        }

        public int getRemoved() {
            return removed;
        }

        public void setRemoved(int removed) {
            this.removed = removed;
        }

        public String getContactWay() {
            return contactWay;
        }

        public void setContactWay(String contactWay) {
            this.contactWay = contactWay;
        }

        public String getComment() {
            return comment;
        }

        public void setComment(String comment) {
            this.comment = comment;
        }

        public int getIsCharge() {
            return isCharge;
        }

        public void setIsCharge(int isCharge) {
            this.isCharge = isCharge;
        }

        public String getIsEditUserType() {
            return isEditUserType;
        }

        public void setIsEditUserType(String isEditUserType) {
            this.isEditUserType = isEditUserType;
        }

        public boolean isIsPayed() {
            return isPayed;
        }

        public void setIsPayed(boolean isPayed) {
            this.isPayed = isPayed;
        }

        public String getAddUserName() {
            return addUserName;
        }

        public void setAddUserName(String addUserName) {
            this.addUserName = addUserName;
        }

        public List<String> getCustomerPicList() {
            return customerPicList;
        }

        public void setCustomerPicList(List<String> customerPicList) {
            this.customerPicList = customerPicList;
        }
    }
}
