package com.zhangju.xingquban.interestclassapp.ui.fragment.me.Institutions.xcgl;

import java.util.List;

public class MeJiGouXcglTpBean {

    /**
     * sEcho : 1
     * iTotalRecords : 3
     * iTotalDisplayRecords : 3
     * aaData : [{"editUserId":"12776","editUserName":"bbb(13411111111)","addUserId":"12776","editUserTime":"2017-09-05 16:14:21","display":1,"published":true,"videoTitlePic":null,"title":null,"type":"1","organAlbumId":"14","addUserTime":"2017-09-05 16:14:21","videoFilesId":null,"removed":0,"customerId":"12776","digest":0,"id":"8","isAddUserType":"customer","isEditUserType":"customer","isPic":0,"sorts":0,"picVideo":"http://video.xqban.com/Customer/2017-09-05/1504599253489_971.jpg","addUserName":"bbb(13411111111)"},{"editUserId":"12776","editUserName":"bbb(13411111111)","addUserId":"12776","editUserTime":"2017-09-05 16:10:56","display":1,"published":true,"videoTitlePic":null,"title":null,"type":"1","organAlbumId":"13","addUserTime":"2017-09-05 16:10:56","videoFilesId":null,"removed":0,"customerId":"12776","digest":0,"id":"6","isAddUserType":"customer","isEditUserType":"customer","isPic":0,"sorts":0,"picVideo":"http://video.xqban.com/Customer/2017-09-05/1504599038473_357.jpg","addUserName":"bbb(13411111111)"},{"editUserId":"12776","editUserName":"bbb(13411111111)","addUserId":"12776","editUserTime":"2017-09-05 16:10:56","display":1,"published":true,"videoTitlePic":null,"title":null,"type":"1","organAlbumId":"13","addUserTime":"2017-09-05 16:10:56","videoFilesId":null,"removed":0,"customerId":"12776","digest":0,"id":"5","isAddUserType":"customer","isEditUserType":"customer","isPic":0,"sorts":0,"picVideo":"http://video.xqban.com/Customer/2017-09-05/1504599032135_665.jpg","addUserName":"bbb(13411111111)"}]
     * attachData : null
     * total : 1
     * page : 0
     * success : true
     * isLogin : true
     * errMsg : {}
     * isAdmin : false
     * cId : 12776
     * cname : 13411111111
     * time : 2017-09-12 11:39:07:424
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

    public static class ErrMsgBean {
    }

    public static class AaDataBean {
        /**
         * editUserId : 12776
         * editUserName : bbb(13411111111)
         * addUserId : 12776
         * editUserTime : 2017-09-05 16:14:21
         * display : 1
         * published : true
         * videoTitlePic : null
         * title : null
         * type : 1
         * organAlbumId : 14
         * addUserTime : 2017-09-05 16:14:21
         * videoFilesId : null
         * removed : 0
         * customerId : 12776
         * digest : 0
         * id : 8
         * isAddUserType : customer
         * isEditUserType : customer
         * isPic : 0
         * sorts : 0
         * picVideo : http://video.xqban.com/Customer/2017-09-05/1504599253489_971.jpg
         * addUserName : bbb(13411111111)
         */

        private String editUserId;
        private String editUserName;
        private String addUserId;
        private String editUserTime;
        private int display;
        private boolean published;
        private Object videoTitlePic;
        private Object title;
        private String type;
        private String organAlbumId;
        private String addUserTime;
        private Object videoFilesId;
        private int removed;
        private String customerId;
        private int digest;
        private String id;
        private String isAddUserType;
        private String isEditUserType;
        private int isPic;
        private int sorts;
        private String picVideo;
        private String addUserName;

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

        public Object getVideoTitlePic() {
            return videoTitlePic;
        }

        public void setVideoTitlePic(Object videoTitlePic) {
            this.videoTitlePic = videoTitlePic;
        }

        public Object getTitle() {
            return title;
        }

        public void setTitle(Object title) {
            this.title = title;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getOrganAlbumId() {
            return organAlbumId;
        }

        public void setOrganAlbumId(String organAlbumId) {
            this.organAlbumId = organAlbumId;
        }

        public String getAddUserTime() {
            return addUserTime;
        }

        public void setAddUserTime(String addUserTime) {
            this.addUserTime = addUserTime;
        }

        public Object getVideoFilesId() {
            return videoFilesId;
        }

        public void setVideoFilesId(Object videoFilesId) {
            this.videoFilesId = videoFilesId;
        }

        public int getRemoved() {
            return removed;
        }

        public void setRemoved(int removed) {
            this.removed = removed;
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

        public String getIsEditUserType() {
            return isEditUserType;
        }

        public void setIsEditUserType(String isEditUserType) {
            this.isEditUserType = isEditUserType;
        }

        public int getIsPic() {
            return isPic;
        }

        public void setIsPic(int isPic) {
            this.isPic = isPic;
        }

        public int getSorts() {
            return sorts;
        }

        public void setSorts(int sorts) {
            this.sorts = sorts;
        }

        public String getPicVideo() {
            return picVideo;
        }

        public void setPicVideo(String picVideo) {
            this.picVideo = picVideo;
        }

        public String getAddUserName() {
            return addUserName;
        }

        public void setAddUserName(String addUserName) {
            this.addUserName = addUserName;
        }
    }
}
