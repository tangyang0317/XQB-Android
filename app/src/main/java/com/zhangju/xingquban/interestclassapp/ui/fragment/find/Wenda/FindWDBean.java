package com.zhangju.xingquban.interestclassapp.ui.fragment.find.Wenda;

import java.io.Serializable;
import java.util.List;

/**
 * Created by zsl on 2017/8/4.
 */

public class FindWDBean implements Serializable {

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

    public static class ErrMsgBean implements Serializable {
    }

    public static class AaDataBean implements Serializable {
        /**
         * editUserId : 12776
         * answers : 0
         * isCollection : false
         * title : 我是辉辉辉
         * DateTime : 1天前
         * isRelease : 1
         * qExplain : null
         * picStr : [{"id":"8","picture":"http://video.xqban.com/Customer/2017-08-29/1503997860886_607.jpg","questionId":"52","answerId":null,"removed":0,"addUserId":"12776","addUserName":"bbb(13411111111)","addUserTime":"2017-08-29 17:11:08","editUserId":"12776","editUserName":"bbb(13411111111)","editUserTime":"2017-08-29 17:11:08","isAddUserType":"customer","isEditUserType":"customer","published":true,"sorts":0,"display":1,"digest":0,"del":false,"degreeId":null},{"id":"7","picture":"http://video.xqban.com/Customer/2017-08-29/1503997853073_556.jpg","questionId":"52","answerId":null,"removed":0,"addUserId":"12776","addUserName":"bbb(13411111111)","addUserTime":"2017-08-29 17:11:08","editUserId":"12776","editUserName":"bbb(13411111111)","editUserTime":"2017-08-29 17:11:08","isAddUserType":"customer","isEditUserType":"customer","published":true,"sorts":0,"display":1,"digest":0,"del":false,"degreeId":null},{"id":"6","picture":"http://video.xqban.com/Customer/2017-08-29/1503997848571_577.jpg","questionId":"52","answerId":null,"removed":0,"addUserId":"12776","addUserName":"bbb(13411111111)","addUserTime":"2017-08-29 17:11:08","editUserId":"12776","editUserName":"bbb(13411111111)","editUserTime":"2017-08-29 17:11:08","isAddUserType":"customer","isEditUserType":"customer","published":true,"sorts":0,"display":1,"digest":0,"del":false,"degreeId":null},{"id":"5","picture":"http://video.xqban.com/Customer/2017-08-29/1503997844181_458.jpg","questionId":"52","answerId":null,"removed":0,"addUserId":"12776","addUserName":"bbb(13411111111)","addUserTime":"2017-08-29 17:11:08","editUserId":"12776","editUserName":"bbb(13411111111)","editUserTime":"2017-08-29 17:11:08","isAddUserType":"customer","isEditUserType":"customer","published":true,"sorts":0,"display":1,"digest":0,"del":false,"degreeId":null}]
         * clickRate : 0
         * customerId : 12776
         * digest : 0
         * id : 53
         * isAddUserType : customer
         * sorts : 0
         * editUserName : bbb(13411111111)
         * addUserId : 12776
         * editUserTime : 2017-08-29 18:06:46
         * display : 1
         * label : 美术
         * published : true
         * addUserTime : 2017-08-29 18:06:46
         * removed : 0
         * authorName : aaa
         * authorPicture : http://video.xqban.com/Customer/2017-07-25/1500981055219_563.jpg
         * isEditUserType : customer
         * answersDetail : []
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
        private List<PicStrBean> picStr;
        private List<?> answersDetail;

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

        public List<PicStrBean> getPicStr() {
            return picStr;
        }

        public void setPicStr(List<PicStrBean> picStr) {
            this.picStr = picStr;
        }

        public List<?> getAnswersDetail() {
            return answersDetail;
        }

        public void setAnswersDetail(List<?> answersDetail) {
            this.answersDetail = answersDetail;
        }

        public static class PicStrBean implements Serializable {
            /**
             * id : 8
             * picture : http://video.xqban.com/Customer/2017-08-29/1503997860886_607.jpg
             * questionId : 52
             * answerId : null
             * removed : 0
             * addUserId : 12776
             * addUserName : bbb(13411111111)
             * addUserTime : 2017-08-29 17:11:08
             * editUserId : 12776
             * editUserName : bbb(13411111111)
             * editUserTime : 2017-08-29 17:11:08
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
            private String picture;
            private String questionId;
            private Object answerId;
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
            private Object degreeId;

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

            public Object getAnswerId() {
                return answerId;
            }

            public void setAnswerId(Object answerId) {
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

            public Object getDegreeId() {
                return degreeId;
            }

            public void setDegreeId(Object degreeId) {
                this.degreeId = degreeId;
            }
        }
    }

}
