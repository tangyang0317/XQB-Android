package com.zhangju.xingquban.interestclassapp.ui.fragment.me.Institutions.xcgl;

import java.util.List;

/**
 * Created by zsl on 2017/9/11.
 */

public class MeJiGouXcglScspSpjBean {

    /**
     * sEcho : 1
     * iTotalRecords : 4
     * iTotalDisplayRecords : 4
     * aaData : [{"organAlbumFilesNum":0,"editUserId":"12776","editUserName":"bbb(13411111111)","addUserId":"12776","editUserTime":"2017-09-09 22:30:13","display":1,"published":true,"addUserTime":"2017-09-09 22:30:13","organAlbumFilesList":[],"removed":0,"customerId":"12776","digest":0,"files":null,"id":"21","isAddUserType":"customer","isEditUserType":"customer","sorts":0,"addUserName":"bbb(13411111111)"},{"organAlbumFilesNum":0,"editUserId":"12776","editUserName":"bbb(13411111111)","addUserId":"12776","editUserTime":"2017-09-09 22:29:19","display":1,"published":true,"addUserTime":"2017-09-09 22:29:19","organAlbumFilesList":[],"removed":0,"customerId":"12776","digest":0,"files":null,"id":"20","isAddUserType":"customer","isEditUserType":"customer","sorts":0,"addUserName":"bbb(13411111111)"},{"organAlbumFilesNum":0,"editUserId":"12776","editUserName":"bbb(13411111111)","addUserId":"12776","editUserTime":"2017-09-09 22:28:47","display":1,"published":true,"addUserTime":"2017-09-09 22:28:47","organAlbumFilesList":[],"removed":0,"customerId":"12776","digest":0,"files":null,"id":"19","isAddUserType":"customer","isEditUserType":"customer","sorts":0,"addUserName":"bbb(13411111111)"},{"organAlbumFilesNum":1,"editUserId":"12776","editUserName":"bbb(13411111111)","addUserId":"12776","editUserTime":"2017-09-09 22:31:20","display":1,"published":true,"addUserTime":"2017-09-09 22:31:20","organAlbumFilesList":[{"id":"18","organAlbumId":"3","videoFilesId":"18","picVideo":"http://video.xqban.com/OrganVideo/2017-09-09/1504959063885_374.mp4","isPic":2,"videoTitlePic":"http://video.xqban.com/OrganVideo/2017-09-09/1504959165202_470.png","customerId":"7262","title":"测试测试","type":"2","organAlbumFilesList":null,"removed":0,"addUserId":"12776","addUserName":"bbb(13411111111)","addUserTime":"2017-09-11 17:11:18","editUserId":"12776","editUserName":"bbb(13411111111)","editUserTime":"2017-09-11 17:11:18","isAddUserType":"customer","isEditUserType":"customer","published":true,"sorts":0,"display":1,"digest":0,"del":false,"degreeId":""}],"removed":0,"customerId":"12776","digest":0,"files":"段虹玉","id":"18","isAddUserType":"customer","isEditUserType":"customer","sorts":0,"addUserName":"bbb(13411111111)"}]
     * attachData : null
     * total : 1
     * page : 0
     * success : true
     * isLogin : true
     * errMsg : {}
     * isAdmin : false
     * cId : 12776
     * cname : 13411111111
     * time : 2017-09-11 19:20:21:704
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
         * organAlbumFilesNum : 0
         * editUserId : 12776
         * editUserName : bbb(13411111111)
         * addUserId : 12776
         * editUserTime : 2017-09-09 22:30:13
         * display : 1
         * published : true
         * addUserTime : 2017-09-09 22:30:13
         * organAlbumFilesList : []
         * removed : 0
         * customerId : 12776
         * digest : 0
         * files : null
         * id : 21
         * isAddUserType : customer
         * isEditUserType : customer
         * sorts : 0
         * addUserName : bbb(13411111111)
         */

        private int organAlbumFilesNum;
        private String editUserId;
        private String editUserName;
        private String addUserId;
        private String editUserTime;
        private int display;
        private boolean published;
        private String addUserTime;
        private int removed;
        private String customerId;
        private int digest;
        private String files;
        private String id;
        private String isAddUserType;
        private String isEditUserType;
        private int sorts;
        private String addUserName;
        private List<?> organAlbumFilesList;

        public int getOrganAlbumFilesNum() {
            return organAlbumFilesNum;
        }

        public void setOrganAlbumFilesNum(int organAlbumFilesNum) {
            this.organAlbumFilesNum = organAlbumFilesNum;
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

        public String getFiles() {
            return files;
        }

        public void setFiles(String files) {
            this.files = files;
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

        public int getSorts() {
            return sorts;
        }

        public void setSorts(int sorts) {
            this.sorts = sorts;
        }

        public String getAddUserName() {
            return addUserName;
        }

        public void setAddUserName(String addUserName) {
            this.addUserName = addUserName;
        }

        public List<?> getOrganAlbumFilesList() {
            return organAlbumFilesList;
        }

        public void setOrganAlbumFilesList(List<?> organAlbumFilesList) {
            this.organAlbumFilesList = organAlbumFilesList;
        }
    }
}
