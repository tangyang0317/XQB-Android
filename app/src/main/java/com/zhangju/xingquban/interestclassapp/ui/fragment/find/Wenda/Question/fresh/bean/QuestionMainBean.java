package com.zhangju.xingquban.interestclassapp.ui.fragment.find.Wenda.Question.fresh.bean;

import com.google.gson.Gson;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

/**
 * Created by hqf on 2017/11/16.
 * 推荐和最新bean
 */

public class QuestionMainBean implements Serializable {


    /**
     * sEcho : 1
     * iTotalRecords : 19
     * iTotalDisplayRecords : 19
     * aaData : [{"editUserId":"12777","answers":0,"isCollection":false,"title":"嗯","DateTime":"2小时前","isRelease":1,"qExplain":"测","picStr":[],"clickRate":0,"customerId":"12777","digest":0,"id":"264","isAddUserType":"customer","sorts":0,"editUserName":"刘北风(13422222222)","addUserId":"12777","editUserTime":"2017-11-16 09:55:50","display":1,"label":"美术","published":true,"addUserTime":"2017-11-16 09:55:50","removed":0,"authorName":"刘北风","authorPicture":"http://video.xqban.com/Customer/2017-10-27/1509075506202_425.jpg","isEditUserType":"customer","addUserName":"刘北风(13422222222)"},{"editUserId":"12777","answers":0,"isCollection":false,"title":"阿德","DateTime":"1天前","isRelease":1,"qExplain":"啊啊啊","picStr":[],"clickRate":0,"customerId":"12777","digest":0,"id":"262","isAddUserType":"customer","sorts":0,"editUserName":"刘北风(13422222222)","addUserId":"12777","editUserTime":"2017-11-15 18:21:58","display":1,"label":"美术","published":true,"addUserTime":"2017-11-15 18:21:58","removed":0,"authorName":"刘北风","authorPicture":"http://video.xqban.com/Customer/2017-10-27/1509075506202_425.jpg","isEditUserType":"customer","addUserName":"刘北风(13422222222)"},{"editUserId":"15897","answers":0,"isCollection":false,"title":"你好","DateTime":"1天前","isRelease":1,"qExplain":"你好","picStr":[],"clickRate":0,"customerId":"15897","digest":0,"id":"261","isAddUserType":"customer","sorts":0,"editUserName":"(13353353534)","addUserId":"15897","editUserTime":"2017-11-15 18:19:59","display":1,"label":"舞蹈","published":true,"addUserTime":"2017-11-15 18:19:59","removed":0,"authorName":"sgfb","authorPicture":"http://video.xqban.com/Customer/2017-11-01/1509523136719_518.jpg","isEditUserType":"customer","addUserName":"(13353353534)"},{"editUserId":"15897","answers":0,"isCollection":false,"title":"测试","DateTime":"1天前","isRelease":1,"qExplain":"你好","picStr":[],"clickRate":0,"customerId":"15897","digest":0,"id":"260","isAddUserType":"customer","sorts":0,"editUserName":"(13353353534)","addUserId":"15897","editUserTime":"2017-11-15 18:18:53","display":1,"label":"美术","published":true,"addUserTime":"2017-11-15 18:18:53","removed":0,"authorName":"sgfb","authorPicture":"http://video.xqban.com/Customer/2017-11-01/1509523136719_518.jpg","isEditUserType":"customer","addUserName":"(13353353534)"},{"editUserId":"15897","answers":0,"isCollection":false,"title":"分","DateTime":"1天前","isRelease":1,"qExplain":null,"picStr":[],"clickRate":0,"customerId":"15897","digest":0,"id":"259","isAddUserType":"customer","sorts":0,"editUserName":"(13353353534)","addUserId":"15897","editUserTime":"2017-11-15 18:17:08","display":1,"label":"手绘","published":true,"addUserTime":"2017-11-15 18:17:08","removed":0,"authorName":"sgfb","authorPicture":"http://video.xqban.com/Customer/2017-11-01/1509523136719_518.jpg","isEditUserType":"customer","addUserName":"(13353353534)"},{"editUserId":"15897","answers":0,"isCollection":false,"title":"你好的人","DateTime":"1天前","isRelease":1,"qExplain":null,"picStr":[],"clickRate":0,"customerId":"15897","digest":0,"id":"258","isAddUserType":"customer","sorts":0,"editUserName":"(13353353534)","addUserId":"15897","editUserTime":"2017-11-15 17:58:27","display":1,"label":"美术","published":true,"addUserTime":"2017-11-15 17:58:27","removed":0,"authorName":"sgfb","authorPicture":"http://video.xqban.com/Customer/2017-11-01/1509523136719_518.jpg","isEditUserType":"customer","addUserName":"(13353353534)"},{"editUserId":"16102","answers":0,"isCollection":false,"title":"哈布斯堡好的霍華德被逮捕好像不想不想變成並不無小補必須補習班補習補習吧","DateTime":"1天前","isRelease":1,"qExplain":"哈哈哈哈哈哈哈哈哈哈白白白白白白畢竟寶寶男男女女並不能男男女女白白白白白白白白你爸爸不能把寶寶不男男女女吧","picStr":[{"id":"304","picture":"http://video.xqban.com/Customer/2017-11-15/1510739860117_492.jpg","questionId":"257","answerId":"","removed":0,"addUserId":"16102","addUserName":"8888(18666650022)","addUserTime":"2017-11-15 17:57:45","editUserId":"16102","editUserName":"8888(18666650022)","editUserTime":"2017-11-15 17:57:45","isAddUserType":"customer","isEditUserType":"customer","published":true,"sorts":0,"display":1,"digest":0,"del":false,"degreeId":""}],"clickRate":2,"customerId":"16102","digest":0,"id":"257","isAddUserType":"customer","sorts":0,"editUserName":"8888(18666650022)","addUserId":"16102","editUserTime":"2017-11-15 17:57:59","display":1,"label":"美术","published":true,"addUserTime":"2017-11-15 17:57:45","removed":0,"authorName":"8888","authorPicture":"http://video.xqban.com/Customer/2017-11-14/1510630085548_773.jpg","isEditUserType":"customer","addUserName":"8888(18666650022)"},{"editUserId":"15897","answers":0,"isCollection":false,"title":"测试","DateTime":"1天前","isRelease":1,"qExplain":null,"picStr":[],"clickRate":0,"customerId":"15897","digest":0,"id":"256","isAddUserType":"customer","sorts":0,"editUserName":"(13353353534)","addUserId":"15897","editUserTime":"2017-11-15 17:50:16","display":1,"label":"棋类","published":true,"addUserTime":"2017-11-15 17:50:16","removed":0,"authorName":"sgfb","authorPicture":"http://video.xqban.com/Customer/2017-11-01/1509523136719_518.jpg","isEditUserType":"customer","addUserName":"(13353353534)"},{"editUserId":"16102","answers":0,"isCollection":false,"title":"测试结果","DateTime":"1天前","isRelease":1,"qExplain":"你好的人","picStr":[{"id":"303","picture":"http://video.xqban.com/Customer/2017-11-15/1510739092139_195.jpg","questionId":"255","answerId":"","removed":0,"addUserId":"15897","addUserName":"(13353353534)","addUserTime":"2017-11-15 17:45:07","editUserId":"15897","editUserName":"(13353353534)","editUserTime":"2017-11-15 17:45:07","isAddUserType":"customer","isEditUserType":"customer","published":true,"sorts":0,"display":1,"digest":0,"del":false,"degreeId":""}],"clickRate":1,"customerId":"15897","digest":0,"id":"255","isAddUserType":"customer","sorts":0,"editUserName":"8888(18666650022)","addUserId":"15897","editUserTime":"2017-11-15 17:57:09","display":1,"label":"棋类","published":true,"addUserTime":"2017-11-15 17:45:07","removed":0,"authorName":"sgfb","authorPicture":"http://video.xqban.com/Customer/2017-11-01/1509523136719_518.jpg","isEditUserType":"customer","addUserName":"(13353353534)"},{"editUserId":"16102","answers":0,"isCollection":false,"title":"你好的","DateTime":"1天前","isRelease":1,"qExplain":null,"picStr":[{"id":"302","picture":"http://video.xqban.com/Customer/2017-11-15/1510736873559_74.jpg","questionId":"254","answerId":"","removed":0,"addUserId":"12776","addUserName":"李老师(13411111111)","addUserTime":"2017-11-15 17:07:58","editUserId":"12776","editUserName":"李老师(13411111111)","editUserTime":"2017-11-15 17:07:58","isAddUserType":"customer","isEditUserType":"customer","published":true,"sorts":0,"display":1,"digest":0,"del":false,"degreeId":""}],"clickRate":1,"customerId":"12776","digest":0,"id":"254","isAddUserType":"customer","sorts":0,"editUserName":"8888(18666650022)","addUserId":"12776","editUserTime":"2017-11-15 17:57:12","display":1,"label":"美术","published":true,"addUserTime":"2017-11-15 17:07:58","removed":0,"authorName":"李老师","authorPicture":"http://video.xqban.com/Customer/2017-11-15/1510714975182_822.jpg","isEditUserType":"customer","addUserName":"李老师(13411111111)"}]
     * attachData : null
     * total : 2
     * page : 0
     * success : true
     * isLogin : true
     * errMsg : {}
     * isAdmin : false
     * cId : 12776
     * cname : 13411111111
     * time : 2017-11-16 11:42:09:139
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

    public static QuestionMainBean objectFromData(String str) {

        return new Gson().fromJson(str, QuestionMainBean.class);
    }

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
        public static ErrMsgBean objectFromData(String str) {

            return new Gson().fromJson(str, ErrMsgBean.class);
        }
    }

    public static class AaDataBean implements Serializable {
        /**
         * editUserId : 12777
         * answers : 0
         * isCollection : false
         * title : 嗯
         * DateTime : 2小时前
         * isRelease : 1
         * qExplain : 测
         * picStr : []
         * clickRate : 0
         * customerId : 12777
         * digest : 0
         * id : 264
         * isAddUserType : customer
         * sorts : 0
         * editUserName : 刘北风(13422222222)
         * addUserId : 12777
         * editUserTime : 2017-11-16 09:55:50
         * display : 1
         * label : 美术
         * published : true
         * addUserTime : 2017-11-16 09:55:50
         * removed : 0
         * authorName : 刘北风
         * authorPicture : http://video.xqban.com/Customer/2017-10-27/1509075506202_425.jpg
         * isEditUserType : customer
         * addUserName : 刘北风(13422222222)
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
        private String authorPicture;
        private String customerPic;
        private String isEditUserType;
        private String addUserName;
        private String signName;
        private List<PicStrBeans> picStr;
        private QuestionTitle questionTitle;
        private Question Question;

        public AaDataBean.Question getQuestion() {
            return Question;
        }

        public void setQuestion(AaDataBean.Question question) {
            Question = question;
        }

        public QuestionTitle getQuestionTitle() {
            return questionTitle;
        }

        public void setQuestionTitle(QuestionTitle questionTitle) {
            this.questionTitle = questionTitle;
        }

        public String getSignName() {
            return signName;
        }

        public void setSignName(String signName) {
            this.signName = signName;
        }

        public boolean isCollection() {
            return isCollection;
        }

        public void setCollection(boolean collection) {
            isCollection = collection;
        }

        public String getqExplain() {
            return qExplain;
        }

        public void setqExplain(String qExplain) {
            this.qExplain = qExplain;
        }

        public String getCustomerPic() {
            return customerPic;
        }

        public void setCustomerPic(String customerPic) {
            this.customerPic = customerPic;
        }

        public static AaDataBean objectFromData(String str) {

            return new Gson().fromJson(str, AaDataBean.class);
        }

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

        public List<PicStrBeans> getPicStr() {
            return picStr;
        }

        public void setPicStr(List<PicStrBeans> picStr) {
            this.picStr = picStr;
        }

        public static class Question implements Serializable {
            private String title;
            private String qExplain;
            private String label;
            private String id;

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public String getqExplain() {
                return qExplain;
            }

            public void setqExplain(String qExplain) {
                this.qExplain = qExplain;
            }

            public String getLabel() {
                return label;
            }

            public void setLabel(String label) {
                this.label = label;
            }

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }
        }
        public static class QuestionTitle implements Serializable {

            /**
             * id : 264
             * title : 嗯
             * qExplain : 测
             * customerId : 12777
             * clickRate : 11
             * pic : null
             * answersList : []
             * isRelease : 1
             * label : 美术
             * removed : 0
             * addUserId : 12777
             * addUserName : 刘北风(13422222222)
             * addUserTime : 2017-11-16 09:55:50
             * editUserId : 12777
             * editUserName : 刘北风(13422222222)
             * editUserTime : 2017-11-17 13:49:26
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
            private String qExplain;
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

            public static QuestionTitle objectFromData(String str) {

                return new Gson().fromJson(str, QuestionTitle.class);
            }

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

            public String getQExplain() {
                return qExplain;
            }

            public void setQExplain(String qExplain) {
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
        public static class PicStrBeans implements Serializable {
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
