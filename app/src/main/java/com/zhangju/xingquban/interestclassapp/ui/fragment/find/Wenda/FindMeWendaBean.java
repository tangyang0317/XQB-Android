package com.zhangju.xingquban.interestclassapp.ui.fragment.find.Wenda;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

/**
 * Created by zsl on 2017/8/31.
 */

public class FindMeWendaBean implements Serializable {

    /**
     * sEcho : 1
     * iTotalRecords : 4
     * iTotalDisplayRecords : 4
     * aaData : [{"signName":null,"questionId":"58","editUserId":"12776","editUserName":"bbb(13411111111)","questionTitle":{"id":"58","title":"HK哦摸摸","qExplain":null,"customerId":"11773","clickRate":0,"pic":null,"answersList":[],"isRelease":1,"label":"美术","removed":0,"addUserId":"11773","addUserName":"(15618277777)","addUserTime":"2017-08-30 23:57:32","editUserId":"11773","editUserName":"(15618277777)","editUserTime":"2017-08-30 23:57:32","isAddUserType":"customer","isEditUserType":"customer","published":true,"sorts":0,"display":1,"digest":0,"new":false,"collection":false,"del":false,"degreeId":""},"addUserId":"12776","editUserTime":"2017-08-31 15:19:57","display":1,"pic":null,"published":true,"customerPic":"http://video.xqban.com/Customer/2017-07-25/1500981055219_563.jpg","title":"哈哈哈哈","DateTime":"1小时前","isRelease":1,"addUserTime":"2017-08-31 15:19:57","picStr":[],"removed":0,"customerId":"12776","digest":0,"id":"24","isAddUserType":"customer","isEditUserType":"customer","sorts":0,"addUserName":"bbb(13411111111)"},{"signName":null,"questionId":"63","editUserId":"12776","editUserName":"bbb(13411111111)","questionTitle":{"id":"63","title":"最近在练习手绘，线条老是画的不干脆，你们一般都是怎么练习的？|","qExplain":"这里是问题描述文案，这里是问题描述文案，这里是问题描述文案，这里是问题描述文案，这里是问题描述文案，这里是问题描述文案，","customerId":"(null)","clickRate":0,"pic":null,"answersList":[],"isRelease":1,"label":"美术","removed":0,"addUserId":"12775","addUserName":"(13400000000)","addUserTime":"2017-08-31 00:07:16","editUserId":"12775","editUserName":"(13400000000)","editUserTime":"2017-08-31 00:07:16","isAddUserType":"customer","isEditUserType":"customer","published":true,"sorts":0,"display":1,"digest":0,"new":false,"collection":false,"del":false,"degreeId":""},"addUserId":"12776","editUserTime":"2017-08-31 15:17:40","display":1,"pic":null,"published":true,"customerPic":"http://video.xqban.com/Customer/2017-07-25/1500981055219_563.jpg","title":"这个答案很明确","DateTime":"1小时前","isRelease":1,"addUserTime":"2017-08-31 15:17:40","picStr":[],"removed":0,"customerId":"12776","digest":0,"id":"23","isAddUserType":"customer","isEditUserType":"customer","sorts":0,"addUserName":"bbb(13411111111)"},{"signName":null,"questionId":"55","editUserId":"12776","editUserName":"bbb(13411111111)","questionTitle":{"id":"55","title":"第一个问题","qExplain":"这里是问题，会好好解决的","customerId":"(null)","clickRate":0,"pic":null,"answersList":[],"isRelease":1,"label":"手绘","removed":0,"addUserId":"12775","addUserName":"(13400000000)","addUserTime":"2017-08-30 19:10:48","editUserId":"12775","editUserName":"(13400000000)","editUserTime":"2017-08-30 19:10:48","isAddUserType":"customer","isEditUserType":"customer","published":true,"sorts":0,"display":1,"digest":0,"new":false,"collection":false,"del":false,"degreeId":""},"addUserId":"12776","editUserTime":"2017-08-31 12:20:07","display":1,"pic":null,"published":true,"customerPic":"http://video.xqban.com/Customer/2017-07-25/1500981055219_563.jpg","title":"我知道你得问题，我会尽快帮你解决的","DateTime":"4小时前","isRelease":1,"addUserTime":"2017-08-31 12:20:07","picStr":[],"removed":0,"customerId":"12776","digest":0,"id":"19","isAddUserType":"customer","isEditUserType":"customer","sorts":0,"addUserName":"bbb(13411111111)"},{"signName":null,"questionId":"55","editUserId":"12776","editUserName":"bbb(13411111111)","questionTitle":{"id":"55","title":"第一个问题","qExplain":"这里是问题，会好好解决的","customerId":"(null)","clickRate":0,"pic":null,"answersList":[],"isRelease":1,"label":"手绘","removed":0,"addUserId":"12775","addUserName":"(13400000000)","addUserTime":"2017-08-30 19:10:48","editUserId":"12775","editUserName":"(13400000000)","editUserTime":"2017-08-30 19:10:48","isAddUserType":"customer","isEditUserType":"customer","published":true,"sorts":0,"display":1,"digest":0,"new":false,"collection":false,"del":false,"degreeId":""},"addUserId":"12776","editUserTime":"2017-08-31 12:14:41","display":1,"pic":null,"published":true,"customerPic":null,"title":"我知道你得问题，我会尽快帮你解决的","DateTime":"4小时前","isRelease":1,"addUserTime":"2017-08-31 12:14:41","picStr":[],"removed":0,"customerId":"12776","digest":0,"id":"18","isAddUserType":"customer","isEditUserType":"customer","sorts":0,"addUserName":"bbb(13411111111)"}]
     * attachData : null
     * total : 1
     * page : 0
     * success : true
     * isLogin : true
     * errMsg : {}
     * isAdmin : false
     * cId : 12776
     * cname : 13411111111
     * time : 2017-08-31 16:14:35:577
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
         * signName : null
         * questionId : 58
         * editUserId : 12776
         * editUserName : bbb(13411111111)
         * questionTitle : {"id":"58","title":"HK哦摸摸","qExplain":null,"customerId":"11773","clickRate":0,"pic":null,"answersList":[],"isRelease":1,"label":"美术","removed":0,"addUserId":"11773","addUserName":"(15618277777)","addUserTime":"2017-08-30 23:57:32","editUserId":"11773","editUserName":"(15618277777)","editUserTime":"2017-08-30 23:57:32","isAddUserType":"customer","isEditUserType":"customer","published":true,"sorts":0,"display":1,"digest":0,"new":false,"collection":false,"del":false,"degreeId":""}
         * addUserId : 12776
         * editUserTime : 2017-08-31 15:19:57
         * display : 1
         * pic : null
         * published : true
         * customerPic : http://video.xqban.com/Customer/2017-07-25/1500981055219_563.jpg
         * title : 哈哈哈哈
         * DateTime : 1小时前
         * isRelease : 1
         * addUserTime : 2017-08-31 15:19:57
         * picStr : []
         * removed : 0
         * customerId : 12776
         * digest : 0
         * id : 24
         * isAddUserType : customer
         * isEditUserType : customer
         * sorts : 0
         * addUserName : bbb(13411111111)
         */

        private Object signName;
        private String questionId;
        private String editUserId;
        private String editUserName;
        private QuestionTitleBean questionTitle;
        private String addUserId;
        private String editUserTime;
        private int display;
        private Object pic;
        private boolean published;
        private String customerPic;
        private String title;
        private String DateTime;
        private int isRelease;
        private String addUserTime;
        private int removed;
        private String customerId;
        private int digest;
        private String id;
        private String isAddUserType;
        private String isEditUserType;
        private int sorts;
        private String addUserName;
        private List<?> picStr;

        public Object getSignName() {
            return signName;
        }

        public void setSignName(Object signName) {
            this.signName = signName;
        }

        public String getQuestionId() {
            return questionId;
        }

        public void setQuestionId(String questionId) {
            this.questionId = questionId;
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

        public QuestionTitleBean getQuestionTitle() {
            return questionTitle;
        }

        public void setQuestionTitle(QuestionTitleBean questionTitle) {
            this.questionTitle = questionTitle;
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

        public Object getPic() {
            return pic;
        }

        public void setPic(Object pic) {
            this.pic = pic;
        }

        public boolean isPublished() {
            return published;
        }

        public void setPublished(boolean published) {
            this.published = published;
        }

        public String getCustomerPic() {
            return customerPic;
        }

        public void setCustomerPic(String customerPic) {
            this.customerPic = customerPic;
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

        public List<?> getPicStr() {
            return picStr;
        }

        public void setPicStr(List<?> picStr) {
            this.picStr = picStr;
        }

        public static class QuestionTitleBean implements Serializable{
            /**
             * id : 58
             * title : HK哦摸摸
             * qExplain : null
             * customerId : 11773
             * clickRate : 0
             * pic : null
             * answersList : []
             * isRelease : 1
             * label : 美术
             * removed : 0
             * addUserId : 11773
             * addUserName : (15618277777)
             * addUserTime : 2017-08-30 23:57:32
             * editUserId : 11773
             * editUserName : (15618277777)
             * editUserTime : 2017-08-30 23:57:32
             * isAddUserType : customer
             * isEditUserType : customer
             * published : true
             * sorts : 0
             * display : 1
             * digest : 0
             * new : false
             * collection : false
             * del : false
             * degreeId :
             */

            private String id;
            private String title;
            private Object qExplain;
            private String customerId;
            private int clickRate;
            private Object pic;
            private int isRelease;
            private String label;
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
            @SerializedName("new")
            private boolean newX;
            private boolean collection;
            private boolean del;
            private String degreeId;
            private List<?> answersList;

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public Object getQExplain() {
                return qExplain;
            }

            public void setQExplain(Object qExplain) {
                this.qExplain = qExplain;
            }

            public String getCustomerId() {
                return customerId;
            }

            public void setCustomerId(String customerId) {
                this.customerId = customerId;
            }

            public int getClickRate() {
                return clickRate;
            }

            public void setClickRate(int clickRate) {
                this.clickRate = clickRate;
            }

            public Object getPic() {
                return pic;
            }

            public void setPic(Object pic) {
                this.pic = pic;
            }

            public int getIsRelease() {
                return isRelease;
            }

            public void setIsRelease(int isRelease) {
                this.isRelease = isRelease;
            }

            public String getLabel() {
                return label;
            }

            public void setLabel(String label) {
                this.label = label;
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

            public boolean isNewX() {
                return newX;
            }

            public void setNewX(boolean newX) {
                this.newX = newX;
            }

            public boolean isCollection() {
                return collection;
            }

            public void setCollection(boolean collection) {
                this.collection = collection;
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

            public List<?> getAnswersList() {
                return answersList;
            }

            public void setAnswersList(List<?> answersList) {
                this.answersList = answersList;
            }
        }
    }
}
