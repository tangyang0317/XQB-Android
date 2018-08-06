package com.zhangju.xingquban.interestclassapp.ui.fragment.find.Wenda;

import java.util.List;

/**
 * Created by zsl on 2017/9/1.
 */

public class FindMeWendaXqBean {

    /**
     * sEcho : 1
     * iTotalRecords : 1
     * iTotalDisplayRecords : 1
     * aaData : [{"editUserId":"12775","answers":5,"isCollection":false,"title":"第一个问题","DateTime":"1个月前","isRelease":1,"qExplain":"这里是问题，会好好解决的","picStr":[{"id":"16","picture":"http://video.xqban.com/Customer/2017-08-30/1504091399979_912.jpg","questionId":"55","answerId":"","removed":0,"addUserId":"12775","addUserName":"(13400000000)","addUserTime":"2017-08-30 19:10:48","editUserId":"12775","editUserName":"(13400000000)","editUserTime":"2017-08-30 19:10:48","isAddUserType":"customer","isEditUserType":"customer","published":true,"sorts":0,"display":1,"digest":0,"del":false,"degreeId":""},{"id":"15","picture":"http://video.xqban.com/Customer/2017-08-30/1504091392405_421.jpg","questionId":"55","answerId":"","removed":0,"addUserId":"12775","addUserName":"(13400000000)","addUserTime":"2017-08-30 19:10:48","editUserId":"12775","editUserName":"(13400000000)","editUserTime":"2017-08-30 19:10:48","isAddUserType":"customer","isEditUserType":"customer","published":true,"sorts":0,"display":1,"digest":0,"del":false,"degreeId":""}],"clickRate":0,"customerId":"(null)","digest":0,"id":"55","isAddUserType":"customer","sorts":0,"editUserName":"(13400000000)","addUserId":"12775","editUserTime":"2017-08-30 19:10:48","display":1,"label":"手绘","published":true,"addUserTime":"2017-08-30 19:10:48","removed":0,"authorName":null,"authorPicture":null,"isEditUserType":"customer","addUserName":"(13400000000)"}]
     * attachData : null
     * total : 1
     * page : 0
     * success : true
     * isLogin : false
     * errMsg : {}
     * isAdmin : false
     * cId : null
     * cname : null
     * time : 2017-09-01 18:53:28:464
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
    private Object cId;
    private Object cname;
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

    public Object getCId() {
        return cId;
    }

    public void setCId(Object cId) {
        this.cId = cId;
    }

    public Object getCname() {
        return cname;
    }

    public void setCname(Object cname) {
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
         * editUserId : 12775
         * answers : 5
         * isCollection : false
         * title : 第一个问题
         * DateTime : 1个月前
         * isRelease : 1
         * qExplain : 这里是问题，会好好解决的
         * picStr : [{"id":"16","picture":"http://video.xqban.com/Customer/2017-08-30/1504091399979_912.jpg","questionId":"55","answerId":"","removed":0,"addUserId":"12775","addUserName":"(13400000000)","addUserTime":"2017-08-30 19:10:48","editUserId":"12775","editUserName":"(13400000000)","editUserTime":"2017-08-30 19:10:48","isAddUserType":"customer","isEditUserType":"customer","published":true,"sorts":0,"display":1,"digest":0,"del":false,"degreeId":""},{"id":"15","picture":"http://video.xqban.com/Customer/2017-08-30/1504091392405_421.jpg","questionId":"55","answerId":"","removed":0,"addUserId":"12775","addUserName":"(13400000000)","addUserTime":"2017-08-30 19:10:48","editUserId":"12775","editUserName":"(13400000000)","editUserTime":"2017-08-30 19:10:48","isAddUserType":"customer","isEditUserType":"customer","published":true,"sorts":0,"display":1,"digest":0,"del":false,"degreeId":""}]
         * clickRate : 0
         * customerId : (null)
         * digest : 0
         * id : 55
         * isAddUserType : customer
         * sorts : 0
         * editUserName : (13400000000)
         * addUserId : 12775
         * editUserTime : 2017-08-30 19:10:48
         * display : 1
         * label : 手绘
         * published : true
         * addUserTime : 2017-08-30 19:10:48
         * removed : 0
         * authorName : null
         * authorPicture : null
         * isEditUserType : customer
         * addUserName : (13400000000)
         */

        private String editUserId;
        private int answers;
        private boolean isCollection;
        private String title;
        private String DateTime;
        private int isRelease;
        private String qExplain;
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
        private Object authorPicture;
        private String isEditUserType;
        private String addUserName;
        private List<PicStrBean> picStr;

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

        public String getQExplain() {
            return qExplain;
        }

        public void setQExplain(String qExplain) {
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

        public Object getAuthorPicture() {
            return authorPicture;
        }

        public void setAuthorPicture(Object authorPicture) {
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

        public List<PicStrBean> getPicStr() {
            return picStr;
        }

        public void setPicStr(List<PicStrBean> picStr) {
            this.picStr = picStr;
        }

        public static class PicStrBean {
            /**
             * id : 16
             * picture : http://video.xqban.com/Customer/2017-08-30/1504091399979_912.jpg
             * questionId : 55
             * answerId :
             * removed : 0
             * addUserId : 12775
             * addUserName : (13400000000)
             * addUserTime : 2017-08-30 19:10:48
             * editUserId : 12775
             * editUserName : (13400000000)
             * editUserTime : 2017-08-30 19:10:48
             * isAddUserType : customer
             * isEditUserType : customer
             * published : true
             * sorts : 0
             * display : 1
             * digest : 0
             * del : false
             * degreeId :
             */

            private String id;
            private String picture;
            private String questionId;
            private String answerId;
            private int removed;
            private String addUserId;
            private String addUserName;
            private String addUserTime;
            private String editUserId;
            private String editUserName;
            private String editUserTime;
            private String isAddUserType;
            private String isEditUserType;
            private boolean published;
            private int sorts;
            private int display;
            private int digest;
            private boolean del;
            private String degreeId;

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getPicture() {
                return picture;
            }

            public void setPicture(String picture) {
                this.picture = picture;
            }

            public String getQuestionId() {
                return questionId;
            }

            public void setQuestionId(String questionId) {
                this.questionId = questionId;
            }

            public String getAnswerId() {
                return answerId;
            }

            public void setAnswerId(String answerId) {
                this.answerId = answerId;
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

            public String getDegreeId() {
                return degreeId;
            }

            public void setDegreeId(String degreeId) {
                this.degreeId = degreeId;
            }
        }
    }
}
