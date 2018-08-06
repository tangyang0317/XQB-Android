package com.zhangju.xingquban.interestclassapp.ui.fragment.find.Wenda;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

/**
 * Created by ydw on 2017/10/16.
 */
//收藏列表bean
public class FindSCBean implements Serializable {


    /**
     * sEcho : 1
     * iTotalRecords : 24
     * iTotalDisplayRecords : 24
     * aaData : [{"questionId":null,"editUserId":"12776","editUserName":"hh哥(13411111111)","addUserId":"12776","editUserTime":"2017-10-16 11:36:24","display":1,"published":true,"addUserTime":"2017-10-16 11:36:24","removed":0,"customerId":"12776","digest":0,"Question":{"id":null,"title":null,"qExplain":null,"customerId":null,"clickRate":null,"pic":null,"answersList":[],"isRelease":null,"label":null,"removed":null,"addUserId":null,"addUserName":null,"addUserTime":null,"editUserId":null,"editUserName":null,"editUserTime":null,"isAddUserType":null,"isEditUserType":null,"published":null,"sorts":null,"display":null,"digest":null,"new":false,"collection":false,"del":false,"degreeId":""},"id":"165","isAddUserType":"customer","isEditUserType":"customer","sorts":0,"addUserName":"hh哥(13411111111)"},{"questionId":null,"editUserId":"12776","editUserName":"hh哥(13411111111)","addUserId":"12776","editUserTime":"2017-10-16 11:36:24","display":1,"published":true,"addUserTime":"2017-10-16 11:36:24","removed":0,"customerId":"12776","digest":0,"Question":{"id":null,"title":null,"qExplain":null,"customerId":null,"clickRate":null,"pic":null,"answersList":[],"isRelease":null,"label":null,"removed":null,"addUserId":null,"addUserName":null,"addUserTime":null,"editUserId":null,"editUserName":null,"editUserTime":null,"isAddUserType":null,"isEditUserType":null,"published":null,"sorts":null,"display":null,"digest":null,"new":false,"collection":false,"del":false,"degreeId":""},"id":"164","isAddUserType":"customer","isEditUserType":"customer","sorts":0,"addUserName":"hh哥(13411111111)"},{"questionId":null,"editUserId":"12776","editUserName":"hh哥(13411111111)","addUserId":"12776","editUserTime":"2017-10-16 11:36:23","display":1,"published":true,"addUserTime":"2017-10-16 11:36:23","removed":0,"customerId":"12776","digest":0,"Question":{"id":null,"title":null,"qExplain":null,"customerId":null,"clickRate":null,"pic":null,"answersList":[],"isRelease":null,"label":null,"removed":null,"addUserId":null,"addUserName":null,"addUserTime":null,"editUserId":null,"editUserName":null,"editUserTime":null,"isAddUserType":null,"isEditUserType":null,"published":null,"sorts":null,"display":null,"digest":null,"new":false,"collection":false,"del":false,"degreeId":""},"id":"163","isAddUserType":"customer","isEditUserType":"customer","sorts":0,"addUserName":"hh哥(13411111111)"},{"questionId":null,"editUserId":"12776","editUserName":"hh哥(13411111111)","addUserId":"12776","editUserTime":"2017-10-16 11:33:11","display":1,"published":true,"addUserTime":"2017-10-16 11:33:11","removed":0,"customerId":"12776","digest":0,"Question":{"id":null,"title":null,"qExplain":null,"customerId":null,"clickRate":null,"pic":null,"answersList":[],"isRelease":null,"label":null,"removed":null,"addUserId":null,"addUserName":null,"addUserTime":null,"editUserId":null,"editUserName":null,"editUserTime":null,"isAddUserType":null,"isEditUserType":null,"published":null,"sorts":null,"display":null,"digest":null,"new":false,"collection":false,"del":false,"degreeId":""},"id":"162","isAddUserType":"customer","isEditUserType":"customer","sorts":0,"addUserName":"hh哥(13411111111)"},{"questionId":null,"editUserId":"12776","editUserName":"hh哥(13411111111)","addUserId":"12776","editUserTime":"2017-10-16 11:33:11","display":1,"published":true,"addUserTime":"2017-10-16 11:33:11","removed":0,"customerId":"12776","digest":0,"Question":{"id":null,"title":null,"qExplain":null,"customerId":null,"clickRate":null,"pic":null,"answersList":[],"isRelease":null,"label":null,"removed":null,"addUserId":null,"addUserName":null,"addUserTime":null,"editUserId":null,"editUserName":null,"editUserTime":null,"isAddUserType":null,"isEditUserType":null,"published":null,"sorts":null,"display":null,"digest":null,"new":false,"collection":false,"del":false,"degreeId":""},"id":"161","isAddUserType":"customer","isEditUserType":"customer","sorts":0,"addUserName":"hh哥(13411111111)"},{"questionId":null,"editUserId":"12776","editUserName":"hh哥(13411111111)","addUserId":"12776","editUserTime":"2017-10-16 11:33:10","display":1,"published":true,"addUserTime":"2017-10-16 11:33:10","removed":0,"customerId":"12776","digest":0,"Question":{"id":null,"title":null,"qExplain":null,"customerId":null,"clickRate":null,"pic":null,"answersList":[],"isRelease":null,"label":null,"removed":null,"addUserId":null,"addUserName":null,"addUserTime":null,"editUserId":null,"editUserName":null,"editUserTime":null,"isAddUserType":null,"isEditUserType":null,"published":null,"sorts":null,"display":null,"digest":null,"new":false,"collection":false,"del":false,"degreeId":""},"id":"160","isAddUserType":"customer","isEditUserType":"customer","sorts":0,"addUserName":"hh哥(13411111111)"},{"questionId":null,"editUserId":"12776","editUserName":"hh哥(13411111111)","addUserId":"12776","editUserTime":"2017-10-16 11:33:10","display":1,"published":true,"addUserTime":"2017-10-16 11:33:10","removed":0,"customerId":"12776","digest":0,"Question":{"id":null,"title":null,"qExplain":null,"customerId":null,"clickRate":null,"pic":null,"answersList":[],"isRelease":null,"label":null,"removed":null,"addUserId":null,"addUserName":null,"addUserTime":null,"editUserId":null,"editUserName":null,"editUserTime":null,"isAddUserType":null,"isEditUserType":null,"published":null,"sorts":null,"display":null,"digest":null,"new":false,"collection":false,"del":false,"degreeId":""},"id":"159","isAddUserType":"customer","isEditUserType":"customer","sorts":0,"addUserName":"hh哥(13411111111)"},{"questionId":null,"editUserId":"12776","editUserName":"hh哥(13411111111)","addUserId":"12776","editUserTime":"2017-10-16 11:33:10","display":1,"published":true,"addUserTime":"2017-10-16 11:33:10","removed":0,"customerId":"12776","digest":0,"Question":{"id":null,"title":null,"qExplain":null,"customerId":null,"clickRate":null,"pic":null,"answersList":[],"isRelease":null,"label":null,"removed":null,"addUserId":null,"addUserName":null,"addUserTime":null,"editUserId":null,"editUserName":null,"editUserTime":null,"isAddUserType":null,"isEditUserType":null,"published":null,"sorts":null,"display":null,"digest":null,"new":false,"collection":false,"del":false,"degreeId":""},"id":"158","isAddUserType":"customer","isEditUserType":"customer","sorts":0,"addUserName":"hh哥(13411111111)"},{"questionId":null,"editUserId":"12776","editUserName":"hh哥(13411111111)","addUserId":"12776","editUserTime":"2017-10-16 11:33:09","display":1,"published":true,"addUserTime":"2017-10-16 11:33:09","removed":0,"customerId":"12776","digest":0,"Question":{"id":null,"title":null,"qExplain":null,"customerId":null,"clickRate":null,"pic":null,"answersList":[],"isRelease":null,"label":null,"removed":null,"addUserId":null,"addUserName":null,"addUserTime":null,"editUserId":null,"editUserName":null,"editUserTime":null,"isAddUserType":null,"isEditUserType":null,"published":null,"sorts":null,"display":null,"digest":null,"new":false,"collection":false,"del":false,"degreeId":""},"id":"157","isAddUserType":"customer","isEditUserType":"customer","sorts":0,"addUserName":"hh哥(13411111111)"},{"questionId":null,"editUserId":"12776","editUserName":"hh哥(13411111111)","addUserId":"12776","editUserTime":"2017-10-16 11:33:09","display":1,"published":true,"addUserTime":"2017-10-16 11:33:09","removed":0,"customerId":"12776","digest":0,"Question":{"id":null,"title":null,"qExplain":null,"customerId":null,"clickRate":null,"pic":null,"answersList":[],"isRelease":null,"label":null,"removed":null,"addUserId":null,"addUserName":null,"addUserTime":null,"editUserId":null,"editUserName":null,"editUserTime":null,"isAddUserType":null,"isEditUserType":null,"published":null,"sorts":null,"display":null,"digest":null,"new":false,"collection":false,"del":false,"degreeId":""},"id":"156","isAddUserType":"customer","isEditUserType":"customer","sorts":0,"addUserName":"hh哥(13411111111)"}]
     * attachData : null
     * total : 3
     * page : 0
     * success : true
     * isLogin : true
     * errMsg : {}
     * isAdmin : false
     * cId : 12776
     * cname : 13411111111
     * time : 2017-10-16 20:56:21:592
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

    public static class AaDataBean implements Serializable{
        /**
         * questionId : null
         * editUserId : 12776
         * editUserName : hh哥(13411111111)
         * addUserId : 12776
         * editUserTime : 2017-10-16 11:36:24
         * display : 1
         * published : true
         * addUserTime : 2017-10-16 11:36:24
         * removed : 0
         * customerId : 12776
         * digest : 0
         * Question : {"id":null,"title":null,"qExplain":null,"customerId":null,"clickRate":null,"pic":null,"answersList":[],"isRelease":null,"label":null,"removed":null,"addUserId":null,"addUserName":null,"addUserTime":null,"editUserId":null,"editUserName":null,"editUserTime":null,"isAddUserType":null,"isEditUserType":null,"published":null,"sorts":null,"display":null,"digest":null,"new":false,"collection":false,"del":false,"degreeId":""}
         * id : 165
         * isAddUserType : customer
         * isEditUserType : customer
         * sorts : 0
         * addUserName : hh哥(13411111111)
         */

        private Object questionId;
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
        private QuestionBean Question;
        private String id;
        private String isAddUserType;
        private String isEditUserType;
        private int sorts;
        private String addUserName;
        private List<PicItem> picList;

        public List<PicItem> getPicList() {
            return picList;
        }

        public void setPicList(List<PicItem> picList) {
            this.picList = picList;
        }



        public Object getQuestionId() {
            return questionId;
        }

        public void setQuestionId(Object questionId) {
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

        public QuestionBean getQuestion() {
            return Question;
        }

        public void setQuestion(QuestionBean Question) {
            this.Question = Question;
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

        public static class PicItem implements Serializable{
            private String picture;

            public String getPicture() {
                return picture;
            }

            public void setPicture(String picture) {
                this.picture = picture;
            }
        }

        public static class QuestionBean implements Serializable {
            /**
             * id : null
             * title : null
             * qExplain : null
             * customerId : null
             * clickRate : null
             * pic : null
             * answersList : []
             * isRelease : null
             * label : null
             * removed : null
             * addUserId : null
             * addUserName : null
             * addUserTime : null
             * editUserId : null
             * editUserName : null
             * editUserTime : null
             * isAddUserType : null
             * isEditUserType : null
             * published : null
             * sorts : null
             * display : null
             * digest : null
             * new : false
             * collection : false
             * del : false
             * degreeId :
             */

            private Object id;
            private Object title;
            private Object qExplain;
            private Object customerId;
            private Object clickRate;
            private Object pic;
            private Object isRelease;
            private Object label;
            private Object removed;
            private Object addUserId;
            private Object addUserName;
            private Object addUserTime;
            private Object editUserId;
            private Object editUserName;
            private Object editUserTime;
            private Object isAddUserType;
            private Object isEditUserType;
            private Object published;
            private Object sorts;
            private Object display;
            private Object digest;
            @SerializedName("new")
            private boolean newX;
            private boolean collection;
            private boolean del;
            private String degreeId;
//            private Object answersList;
            private List<AnswerBean> answersList;

            public Object getId() {
                return id;
            }

            public void setId(Object id) {
                this.id = id;
            }

            public Object getTitle() {
                return title;
            }

            public void setTitle(Object title) {
                this.title = title;
            }

            public Object getQExplain() {
                return qExplain;
            }

            public void setQExplain(Object qExplain) {
                this.qExplain = qExplain;
            }

            public Object getCustomerId() {
                return customerId;
            }

            public void setCustomerId(Object customerId) {
                this.customerId = customerId;
            }

            public Object getClickRate() {
                return clickRate;
            }

            public void setClickRate(Object clickRate) {
                this.clickRate = clickRate;
            }

            public Object getPic() {
                return pic;
            }

            public void setPic(Object pic) {
                this.pic = pic;
            }

            public Object getIsRelease() {
                return isRelease;
            }

            public void setIsRelease(Object isRelease) {
                this.isRelease = isRelease;
            }

            public Object getLabel() {
                return label;
            }

            public void setLabel(Object label) {
                this.label = label;
            }

            public Object getRemoved() {
                return removed;
            }

            public void setRemoved(Object removed) {
                this.removed = removed;
            }

            public Object getAddUserId() {
                return addUserId;
            }

            public void setAddUserId(Object addUserId) {
                this.addUserId = addUserId;
            }

            public Object getAddUserName() {
                return addUserName;
            }

            public void setAddUserName(Object addUserName) {
                this.addUserName = addUserName;
            }

            public Object getAddUserTime() {
                return addUserTime;
            }

            public void setAddUserTime(Object addUserTime) {
                this.addUserTime = addUserTime;
            }

            public Object getEditUserId() {
                return editUserId;
            }

            public void setEditUserId(Object editUserId) {
                this.editUserId = editUserId;
            }

            public Object getEditUserName() {
                return editUserName;
            }

            public void setEditUserName(Object editUserName) {
                this.editUserName = editUserName;
            }

            public Object getEditUserTime() {
                return editUserTime;
            }

            public void setEditUserTime(Object editUserTime) {
                this.editUserTime = editUserTime;
            }

            public Object getIsAddUserType() {
                return isAddUserType;
            }

            public void setIsAddUserType(Object isAddUserType) {
                this.isAddUserType = isAddUserType;
            }

            public Object getIsEditUserType() {
                return isEditUserType;
            }

            public void setIsEditUserType(Object isEditUserType) {
                this.isEditUserType = isEditUserType;
            }

            public Object getPublished() {
                return published;
            }

            public void setPublished(Object published) {
                this.published = published;
            }

            public Object getSorts() {
                return sorts;
            }

            public void setSorts(Object sorts) {
                this.sorts = sorts;
            }

            public Object getDisplay() {
                return display;
            }

            public void setDisplay(Object display) {
                this.display = display;
            }

            public Object getDigest() {
                return digest;
            }

            public void setDigest(Object digest) {
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

            public List<AnswerBean> getAnswersList() {
                return answersList;
            }

            public void setAnswersList(List<AnswerBean> answersList) {
                this.answersList = answersList;
            }
            public static class AnswerBean implements Serializable{
                public String id;
                public String signName;
                public String customerPic;
                public String title;
                public String questionId;
                public String customerId;
                public String questionTitle;
                public String pic;
                public List<Object> files;
                public String isRelease;
                public String removed;
                public String addUserId;
                public String addUserName;

                public String getId() {
                    return id;
                }

                public void setId(String id) {
                    this.id = id;
                }

                public String getSignName() {
                    return signName;
                }

                public void setSignName(String signName) {
                    this.signName = signName;
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

                public String getQuestionId() {
                    return questionId;
                }

                public void setQuestionId(String questionId) {
                    this.questionId = questionId;
                }

                public String getCustomerId() {
                    return customerId;
                }

                public void setCustomerId(String customerId) {
                    this.customerId = customerId;
                }

                public String getQuestionTitle() {
                    return questionTitle;
                }

                public void setQuestionTitle(String questionTitle) {
                    this.questionTitle = questionTitle;
                }

                public String getPic() {
                    return pic;
                }

                public void setPic(String pic) {
                    this.pic = pic;
                }

                public List<Object> getFiles() {
                    return files;
                }

                public void setFiles(List<Object> files) {
                    this.files = files;
                }

                public String getIsRelease() {
                    return isRelease;
                }

                public void setIsRelease(String isRelease) {
                    this.isRelease = isRelease;
                }

                public String getRemoved() {
                    return removed;
                }

                public void setRemoved(String removed) {
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
            }
        }
    }
}
