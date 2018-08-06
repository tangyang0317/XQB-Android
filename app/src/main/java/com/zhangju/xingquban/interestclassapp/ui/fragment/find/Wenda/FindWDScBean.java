package com.zhangju.xingquban.interestclassapp.ui.fragment.find.Wenda;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

/**
 * Created by zsl on 2017/8/28.
 */

public class FindWDScBean implements Serializable {

    /**
     * sEcho : 1
     * iTotalRecords : 1
     * iTotalDisplayRecords : 1
     * aaData : [{"questionId":"1","editUserId":"12776","editUserName":"bbb(13411111111)","addUserId":"12776","editUserTime":"2017-08-25 14:18:54","display":1,"published":true,"addUserTime":"2017-08-25 14:18:54","removed":0,"customerId":"12890","digest":0,"Question":{"id":"1","title":"猪蹄","qExplain":"解释说明","clickRate":0,"picStr":null,"pic":null,"answersList":[{"id":"1","title":"担任","questionId":"1","customerId":"12890","questionTitle":null,"pic":null,"picStr":null,"files":[],"isRelease":null,"removed":0,"addUserId":"12776","addUserName":"bbb(13411111111)","addUserTime":"2017-08-25 14:19:35","editUserId":"12776","editUserName":"bbb(13411111111)","editUserTime":"2017-08-25 14:19:35","isAddUserType":"customer","isEditUserType":"customer","published":true,"sorts":0,"display":1,"digest":0,"del":false,"degreeId":null}],"isRelease":1,"label":"呵呵","removed":0,"addUserId":"12776","addUserName":"bbb(13411111111)","addUserTime":"2017-08-24 16:40:44","editUserId":"12776","editUserName":"bbb(13411111111)","editUserTime":"2017-08-24 16:40:44","isAddUserType":"customer","isEditUserType":"customer","published":true,"sorts":0,"display":1,"digest":0,"authorPicture":null,"authorName":null,"new":false,"collection":false,"del":false,"degreeId":null},"id":"4","isAddUserType":"customer","isEditUserType":"customer","sorts":0,"addUserName":"bbb(13411111111)"}]
     * attachData : null
     * total : 1
     * page : 0
     * success : true
     * isLogin : true
     * errMsg : {}
     * isAdmin : false
     * cId : 12776
     * cname : 13411111111
     * time : 2017-08-25 14:29:03:370
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
         * questionId : 1
         * editUserId : 12776
         * editUserName : bbb(13411111111)
         * addUserId : 12776
         * editUserTime : 2017-08-25 14:18:54
         * display : 1
         * published : true
         * addUserTime : 2017-08-25 14:18:54
         * removed : 0
         * customerId : 12890
         * digest : 0
         * Question : {"id":"1","title":"猪蹄","qExplain":"解释说明","clickRate":0,"picStr":null,"pic":null,"answersList":[{"id":"1","title":"担任","questionId":"1","customerId":"12890","questionTitle":null,"pic":null,"picStr":null,"files":[],"isRelease":null,"removed":0,"addUserId":"12776","addUserName":"bbb(13411111111)","addUserTime":"2017-08-25 14:19:35","editUserId":"12776","editUserName":"bbb(13411111111)","editUserTime":"2017-08-25 14:19:35","isAddUserType":"customer","isEditUserType":"customer","published":true,"sorts":0,"display":1,"digest":0,"del":false,"degreeId":null}],"isRelease":1,"label":"呵呵","removed":0,"addUserId":"12776","addUserName":"bbb(13411111111)","addUserTime":"2017-08-24 16:40:44","editUserId":"12776","editUserName":"bbb(13411111111)","editUserTime":"2017-08-24 16:40:44","isAddUserType":"customer","isEditUserType":"customer","published":true,"sorts":0,"display":1,"digest":0,"authorPicture":null,"authorName":null,"new":false,"collection":false,"del":false,"degreeId":null}
         * id : 4
         * isAddUserType : customer
         * isEditUserType : customer
         * sorts : 0
         * addUserName : bbb(13411111111)
         */

        private String questionId;
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


        public static class QuestionBean implements Serializable {
            /**
             * id : 1
             * title : 猪蹄
             * qExplain : 解释说明
             * clickRate : 0
             * picStr : null
             * pic : null
             * answersList : [{"id":"1","title":"担任","questionId":"1","customerId":"12890","questionTitle":null,"pic":null,"picStr":null,"files":[],"isRelease":null,"removed":0,"addUserId":"12776","addUserName":"bbb(13411111111)","addUserTime":"2017-08-25 14:19:35","editUserId":"12776","editUserName":"bbb(13411111111)","editUserTime":"2017-08-25 14:19:35","isAddUserType":"customer","isEditUserType":"customer","published":true,"sorts":0,"display":1,"digest":0,"del":false,"degreeId":null}]
             * isRelease : 1
             * label : 呵呵
             * removed : 0
             * addUserId : 12776
             * addUserName : bbb(13411111111)
             * addUserTime : 2017-08-24 16:40:44
             * editUserId : 12776
             * editUserName : bbb(13411111111)
             * editUserTime : 2017-08-24 16:40:44
             * isAddUserType : customer
             * isEditUserType : customer
             * published : true
             * sorts : 0
             * display : 1
             * digest : 0
             * authorPicture : null
             * authorName : null
             * new : false
             * collection : false
             * del : false
             * degreeId : null
             */

            private String id;
            private String title;
            private String qExplain;
            private int clickRate;
            private Object picStr;
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
            private Object authorPicture;
            private Object authorName;
            @SerializedName("new")
            private boolean newX;
            private boolean collection;
            private boolean del;
            private Object degreeId;
            private List<AnswersListBean> answersList;

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

            public int getClickRate() {
                return clickRate;
            }

            public void setClickRate(int clickRate) {
                this.clickRate = clickRate;
            }

            public Object getPicStr() {
                return picStr;
            }

            public void setPicStr(Object picStr) {
                this.picStr = picStr;
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

            public Object getAuthorPicture() {
                return authorPicture;
            }

            public void setAuthorPicture(Object authorPicture) {
                this.authorPicture = authorPicture;
            }

            public Object getAuthorName() {
                return authorName;
            }

            public void setAuthorName(Object authorName) {
                this.authorName = authorName;
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

            public Object getDegreeId() {
                return degreeId;
            }

            public void setDegreeId(Object degreeId) {
                this.degreeId = degreeId;
            }

            public List<AnswersListBean> getAnswersList() {
                return answersList;
            }

            public void setAnswersList(List<AnswersListBean> answersList) {
                this.answersList = answersList;
            }

            public static class AnswersListBean {
                /**
                 * id : 1
                 * title : 担任
                 * questionId : 1
                 * customerId : 12890
                 * questionTitle : null
                 * pic : null
                 * picStr : null
                 * files : []
                 * isRelease : null
                 * removed : 0
                 * addUserId : 12776
                 * addUserName : bbb(13411111111)
                 * addUserTime : 2017-08-25 14:19:35
                 * editUserId : 12776
                 * editUserName : bbb(13411111111)
                 * editUserTime : 2017-08-25 14:19:35
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
                private String title;
                private String questionId;
                private String customerId;
                private Object questionTitle;
                private Object pic;
                private Object picStr;
                private Object isRelease;
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
                private List<?> files;

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

                public Object getQuestionTitle() {
                    return questionTitle;
                }

                public void setQuestionTitle(Object questionTitle) {
                    this.questionTitle = questionTitle;
                }

                public Object getPic() {
                    return pic;
                }

                public void setPic(Object pic) {
                    this.pic = pic;
                }

                public Object getPicStr() {
                    return picStr;
                }

                public void setPicStr(Object picStr) {
                    this.picStr = picStr;
                }

                public Object getIsRelease() {
                    return isRelease;
                }

                public void setIsRelease(Object isRelease) {
                    this.isRelease = isRelease;
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

                public List<?> getFiles() {
                    return files;
                }

                public void setFiles(List<?> files) {
                    this.files = files;
                }
            }
        }
    }
}
