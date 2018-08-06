package com.zhangju.xingquban.interestclassapp.ui.fragment.me.Collect;

import java.io.Serializable;
import java.util.List;

/**
 * Created by zsl on 2017/8/4.
 */

public class MeCollectAllBean implements Serializable {

    /**
     * sEcho : 1
     * iTotalRecords : 1
     * iTotalDisplayRecords : 1
     * aaData : [{"organRange":128397,"types":"picture","editUserId":"12776","editUserName":"bbb(13411111111)","teacherTime":{"id":"6426","degreeId":"2","degreeName":"机构","chgDegreeId":"4122","chgDegreeCheckTime":"2016-08-19 17:20:30","chgDegreeCheckId":null,"isDirApp":true,"qCertificate":null,"summary":"    上海华彩音乐器材有限公司成立于1997年，以中、西方乐器生产、批发和零售为主要经营项目，同时兼顾培训音乐人才和发展音乐产业。公司自创办以来，以改善国内音乐条件、推动国内音乐发展、提高国人音乐素质为己任。乐器制作、销售、代理和音乐培训一体化，继承优秀的民族文化传统，注入鲜活的时尚现代因素，竭诚为海内、外音乐人服务。公司现辖上海华彩音乐器材有限公司、华彩乐器厂、华彩全国连锁琴行、华彩音乐艺术学校、华彩音乐餐厅、华彩会务公司、斯坦利钢琴公司、上海金音娱乐有限公司、上海金音文化传媒有限公司，以及全球音乐网。\r\n","subName":null,"star":2,"catagoryId":",242,248,251,252,253,","catagoryName":",钢琴,小提琴,吉他,尤克里里,贝斯,","method":"1","teachAge":0,"name":"华彩琴行(-朱泾店)","legalTel":"15818299470","contactUser":null,"contactTel":"02157324116","workTime":null,"strength":null,"fixDate":null,"scale":null,"customerId":"6488","enable":true,"qcAuth":0,"qcAuthId":null,"tqcAuth":0,"tqcAuthId":null,"contractAuth":0,"contractAuthStr":null,"joinOrganization":null,"joinOrganizationName":null,"removed":0,"addUserId":"8","addUserName":null,"addUserTime":"2016-08-19 17:20:37","editUserId":"1","editUserName":"admin","editUserTime":"2017-03-23 19:05:47","isAddUserType":"accounts","isEditUserType":"sys","published":true,"sorts":0,"display":1,"digest":0,"chgDegree":null,"degree":null,"teacher":null,"customer":null,"comments":null,"collectionCount":null,"avgPrice":0,"minimumPrice":null,"minVipPrice":0,"score":null,"studioOrNot":false,"protocolOrContract":null,"envCount":4,"exeCount":8,"showCount":0,"resCount":0,"del":false},"addUserId":"12776","collectionTypes":"3","editUserTime":"2017-09-21 14:23:32","display":1,"published":true,"addUserTime":"2017-09-21 14:23:32","teacherCustomerId":"6488","removed":0,"resourcesId":"6426","customerId":"12776","digest":0,"id":"309","resourceExit":1,"isAddUserType":"customer","isEditUserType":"customer","sorts":0,"postingsId":null,"addUserName":"bbb(13411111111)","customer":{"radius":null,"districtType":null,"range":-1,"id":"6488","sno":"20160819000006488","degreeId":"2","chgDegreeId":"4122","chgDgrCheckId":null,"isDirApp":true,"teacherTimeId":"6426","phone":"15818299470","password":"220C2FF5C8B1A8C44FADCE2E5AA509B9","address":"地址：朱泾镇秀州街328号209-210室\r\n","sex":null,"birthday":null,"signame":"华彩琴行(-朱泾店)","username":null,"picture":"http://video.xqban.com/RegTeacher/2016-08-19/1471598408923_qq截图20160819171822.png","studSkill":null,"school":null,"colDegree":null,"uidCard":null,"realnameAuth":0,"realnameAuthId":null,"degreeAuth":0,"degreeAuthId":null,"summary":null,"continueSumSignDay":null,"countView":null,"status":0,"chgDegree":null,"degree":null,"lng":121.174087,"lat":30.902754,"location":"121.174087,30.902754","formatted_address":"上海市金山区秀州街|328号","adcode":"310116","dnyLng":null,"dnyLat":null,"dnyAddress":null,"dnyGisUpdateTime":null,"areasId":"310116","provinceId":"310000","cityId":"310100","cityName":"上海市市辖区","areasName":"金山区","provinceName":"上海市","homeProvinceName":"上海市","studioOrNot":false,"openid":null,"oauthType":null,"accountsId":"8","agentId":"1","iAmMembers":false,"invalidtimeAboutMember":null,"shareGetGift":null,"notvirgin":null,"removed":0,"addUserId":"8","addUserName":null,"addUserTime":"2016-08-19 17:20:37","editUserId":"1","editUserName":"admin","editUserTime":"2016-10-08 16:42:08","isAddUserType":"accounts","isEditUserType":"staff","published":true,"sorts":0,"display":1,"digest":0,"balances":0,"points":0,"json":null,"customerId":"6488","del":false}}]
     * attachData : null
     * total : 1
     * page : 0
     * success : true
     * isLogin : true
     * errMsg : {}
     * isAdmin : false
     * cId : 12776
     * cname : 13411111111
     * time : 2017-09-21 14:31:09:917
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
         * organRange : 128397
         * types : picture
         * editUserId : 12776
         * editUserName : bbb(13411111111)
         * teacherTime : {"id":"6426","degreeId":"2","degreeName":"机构","chgDegreeId":"4122","chgDegreeCheckTime":"2016-08-19 17:20:30","chgDegreeCheckId":null,"isDirApp":true,"qCertificate":null,"summary":"    上海华彩音乐器材有限公司成立于1997年，以中、西方乐器生产、批发和零售为主要经营项目，同时兼顾培训音乐人才和发展音乐产业。公司自创办以来，以改善国内音乐条件、推动国内音乐发展、提高国人音乐素质为己任。乐器制作、销售、代理和音乐培训一体化，继承优秀的民族文化传统，注入鲜活的时尚现代因素，竭诚为海内、外音乐人服务。公司现辖上海华彩音乐器材有限公司、华彩乐器厂、华彩全国连锁琴行、华彩音乐艺术学校、华彩音乐餐厅、华彩会务公司、斯坦利钢琴公司、上海金音娱乐有限公司、上海金音文化传媒有限公司，以及全球音乐网。\r\n","subName":null,"star":2,"catagoryId":",242,248,251,252,253,","catagoryName":",钢琴,小提琴,吉他,尤克里里,贝斯,","method":"1","teachAge":0,"name":"华彩琴行(-朱泾店)","legalTel":"15818299470","contactUser":null,"contactTel":"02157324116","workTime":null,"strength":null,"fixDate":null,"scale":null,"customerId":"6488","enable":true,"qcAuth":0,"qcAuthId":null,"tqcAuth":0,"tqcAuthId":null,"contractAuth":0,"contractAuthStr":null,"joinOrganization":null,"joinOrganizationName":null,"removed":0,"addUserId":"8","addUserName":null,"addUserTime":"2016-08-19 17:20:37","editUserId":"1","editUserName":"admin","editUserTime":"2017-03-23 19:05:47","isAddUserType":"accounts","isEditUserType":"sys","published":true,"sorts":0,"display":1,"digest":0,"chgDegree":null,"degree":null,"teacher":null,"customer":null,"comments":null,"collectionCount":null,"avgPrice":0,"minimumPrice":null,"minVipPrice":0,"score":null,"studioOrNot":false,"protocolOrContract":null,"envCount":4,"exeCount":8,"showCount":0,"resCount":0,"del":false}
         * addUserId : 12776
         * collectionTypes : 3
         * editUserTime : 2017-09-21 14:23:32
         * display : 1
         * published : true
         * addUserTime : 2017-09-21 14:23:32
         * teacherCustomerId : 6488
         * removed : 0
         * resourcesId : 6426
         * customerId : 12776
         * digest : 0
         * id : 309
         * resourceExit : 1
         * isAddUserType : customer
         * isEditUserType : customer
         * sorts : 0
         * postingsId : null
         * addUserName : bbb(13411111111)
         * customer : {"radius":null,"districtType":null,"range":-1,"id":"6488","sno":"20160819000006488","degreeId":"2","chgDegreeId":"4122","chgDgrCheckId":null,"isDirApp":true,"teacherTimeId":"6426","phone":"15818299470","password":"220C2FF5C8B1A8C44FADCE2E5AA509B9","address":"地址：朱泾镇秀州街328号209-210室\r\n","sex":null,"birthday":null,"signame":"华彩琴行(-朱泾店)","username":null,"picture":"http://video.xqban.com/RegTeacher/2016-08-19/1471598408923_qq截图20160819171822.png","studSkill":null,"school":null,"colDegree":null,"uidCard":null,"realnameAuth":0,"realnameAuthId":null,"degreeAuth":0,"degreeAuthId":null,"summary":null,"continueSumSignDay":null,"countView":null,"status":0,"chgDegree":null,"degree":null,"lng":121.174087,"lat":30.902754,"location":"121.174087,30.902754","formatted_address":"上海市金山区秀州街|328号","adcode":"310116","dnyLng":null,"dnyLat":null,"dnyAddress":null,"dnyGisUpdateTime":null,"areasId":"310116","provinceId":"310000","cityId":"310100","cityName":"上海市市辖区","areasName":"金山区","provinceName":"上海市","homeProvinceName":"上海市","studioOrNot":false,"openid":null,"oauthType":null,"accountsId":"8","agentId":"1","iAmMembers":false,"invalidtimeAboutMember":null,"shareGetGift":null,"notvirgin":null,"removed":0,"addUserId":"8","addUserName":null,"addUserTime":"2016-08-19 17:20:37","editUserId":"1","editUserName":"admin","editUserTime":"2016-10-08 16:42:08","isAddUserType":"accounts","isEditUserType":"staff","published":true,"sorts":0,"display":1,"digest":0,"balances":0,"points":0,"json":null,"customerId":"6488","del":false}
         */

        private int organRange;
        private String types;
        private String editUserId;
        private String editUserName;
        private TeacherTimeBean teacherTime;
        private String addUserId;
        private String collectionTypes;
        private String editUserTime;
        private int display;
        private boolean published;
        private String addUserTime;
        private String teacherCustomerId;
        private int removed;
        private String resourcesId;
        private String customerId;
        private int digest;
        private String id;
        private int resourceExit;
        private String isAddUserType;
        private String isEditUserType;
        private int sorts;
        private Object postingsId;
        private String addUserName;
        private CustomerBean customer;

        public int getOrganRange() {
            return organRange;
        }

        public void setOrganRange(int organRange) {
            this.organRange = organRange;
        }

        public String getTypes() {
            return types;
        }

        public void setTypes(String types) {
            this.types = types;
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

        public TeacherTimeBean getTeacherTime() {
            return teacherTime;
        }

        public void setTeacherTime(TeacherTimeBean teacherTime) {
            this.teacherTime = teacherTime;
        }

        public String getAddUserId() {
            return addUserId;
        }

        public void setAddUserId(String addUserId) {
            this.addUserId = addUserId;
        }

        public String getCollectionTypes() {
            return collectionTypes;
        }

        public void setCollectionTypes(String collectionTypes) {
            this.collectionTypes = collectionTypes;
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

        public String getTeacherCustomerId() {
            return teacherCustomerId;
        }

        public void setTeacherCustomerId(String teacherCustomerId) {
            this.teacherCustomerId = teacherCustomerId;
        }

        public int getRemoved() {
            return removed;
        }

        public void setRemoved(int removed) {
            this.removed = removed;
        }

        public String getResourcesId() {
            return resourcesId;
        }

        public void setResourcesId(String resourcesId) {
            this.resourcesId = resourcesId;
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

        public int getResourceExit() {
            return resourceExit;
        }

        public void setResourceExit(int resourceExit) {
            this.resourceExit = resourceExit;
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

        public Object getPostingsId() {
            return postingsId;
        }

        public void setPostingsId(Object postingsId) {
            this.postingsId = postingsId;
        }

        public String getAddUserName() {
            return addUserName;
        }

        public void setAddUserName(String addUserName) {
            this.addUserName = addUserName;
        }

        public CustomerBean getCustomer() {
            return customer;
        }

        public void setCustomer(CustomerBean customer) {
            this.customer = customer;
        }

        public static class TeacherTimeBean implements Serializable {
            /**
             * id : 6426
             * degreeId : 2
             * degreeName : 机构
             * chgDegreeId : 4122
             * chgDegreeCheckTime : 2016-08-19 17:20:30
             * chgDegreeCheckId : null
             * isDirApp : true
             * qCertificate : null
             * summary :     上海华彩音乐器材有限公司成立于1997年，以中、西方乐器生产、批发和零售为主要经营项目，同时兼顾培训音乐人才和发展音乐产业。公司自创办以来，以改善国内音乐条件、推动国内音乐发展、提高国人音乐素质为己任。乐器制作、销售、代理和音乐培训一体化，继承优秀的民族文化传统，注入鲜活的时尚现代因素，竭诚为海内、外音乐人服务。公司现辖上海华彩音乐器材有限公司、华彩乐器厂、华彩全国连锁琴行、华彩音乐艺术学校、华彩音乐餐厅、华彩会务公司、斯坦利钢琴公司、上海金音娱乐有限公司、上海金音文化传媒有限公司，以及全球音乐网。
             * <p>
             * subName : null
             * star : 2
             * catagoryId : ,242,248,251,252,253,
             * catagoryName : ,钢琴,小提琴,吉他,尤克里里,贝斯,
             * method : 1
             * teachAge : 0
             * name : 华彩琴行(-朱泾店)
             * legalTel : 15818299470
             * contactUser : null
             * contactTel : 02157324116
             * workTime : null
             * strength : null
             * fixDate : null
             * scale : null
             * customerId : 6488
             * enable : true
             * qcAuth : 0
             * qcAuthId : null
             * tqcAuth : 0
             * tqcAuthId : null
             * contractAuth : 0
             * contractAuthStr : null
             * joinOrganization : null
             * joinOrganizationName : null
             * removed : 0
             * addUserId : 8
             * addUserName : null
             * addUserTime : 2016-08-19 17:20:37
             * editUserId : 1
             * editUserName : admin
             * editUserTime : 2017-03-23 19:05:47
             * isAddUserType : accounts
             * isEditUserType : sys
             * published : true
             * sorts : 0
             * display : 1
             * digest : 0
             * chgDegree : null
             * degree : null
             * teacher : null
             * customer : null
             * comments : null
             * collectionCount : null
             * avgPrice : 0
             * minimumPrice : null
             * minVipPrice : 0
             * score : null
             * studioOrNot : false
             * protocolOrContract : null
             * envCount : 4
             * exeCount : 8
             * showCount : 0
             * resCount : 0
             * del : false
             */

            private String id;
            private String degreeId;
            private String degreeName;
            private String chgDegreeId;
            private String chgDegreeCheckTime;
            private Object chgDegreeCheckId;
            private boolean isDirApp;
            private Object qCertificate;
            private String summary;
            private Object subName;
            private int star;
            private String catagoriesId;
            private String catagoryName;
            private String method;
            private int teachAge;
            private String name;
            private String legalTel;
            private Object contactUser;
            private String contactTel;
            private Object workTime;
            private Object strength;
            private Object fixDate;
            private Object scale;
            private String customerId;
            private boolean enable;
            private int qcAuth;
            private Object qcAuthId;
            private int tqcAuth;
            private Object tqcAuthId;
            private int contractAuth;
            private Object contractAuthStr;
            private Object joinOrganization;
            private Object joinOrganizationName;
            private int removed;
            private String addUserId;
            private Object addUserName;
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
            private Object chgDegree;
            private Object degree;
            private Object teacher;
            private Object customer;
            private Object comments;
            private String commentCount;
            private int avgPrice;
            private Object minimumPrice;
            private int minVipPrice;
            private Object score;
            private boolean studioOrNot;
            private Object protocolOrContract;
            private int envCount;
            private int exeCount;
            private int showCount;
            private int resCount;
            private boolean del;

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getDegreeId() {
                return degreeId;
            }

            public void setDegreeId(String degreeId) {
                this.degreeId = degreeId;
            }

            public String getDegreeName() {
                return degreeName;
            }

            public void setDegreeName(String degreeName) {
                this.degreeName = degreeName;
            }

            public String getChgDegreeId() {
                return chgDegreeId;
            }

            public void setChgDegreeId(String chgDegreeId) {
                this.chgDegreeId = chgDegreeId;
            }

            public String getChgDegreeCheckTime() {
                return chgDegreeCheckTime;
            }

            public void setChgDegreeCheckTime(String chgDegreeCheckTime) {
                this.chgDegreeCheckTime = chgDegreeCheckTime;
            }

            public Object getChgDegreeCheckId() {
                return chgDegreeCheckId;
            }

            public void setChgDegreeCheckId(Object chgDegreeCheckId) {
                this.chgDegreeCheckId = chgDegreeCheckId;
            }

            public boolean isIsDirApp() {
                return isDirApp;
            }

            public void setIsDirApp(boolean isDirApp) {
                this.isDirApp = isDirApp;
            }

            public Object getQCertificate() {
                return qCertificate;
            }

            public void setQCertificate(Object qCertificate) {
                this.qCertificate = qCertificate;
            }

            public String getSummary() {
                return summary;
            }

            public void setSummary(String summary) {
                this.summary = summary;
            }

            public Object getSubName() {
                return subName;
            }

            public void setSubName(Object subName) {
                this.subName = subName;
            }

            public int getStar() {
                return star;
            }

            public void setStar(int star) {
                this.star = star;
            }

            public String getCatagoriesId() {
                return catagoriesId;
            }

            public void setCatagoriesId(String catagoriesId) {
                this.catagoriesId = catagoriesId;
            }

            public String getCatagoryName() {
                return catagoryName;
            }

            public void setCatagoryName(String catagoryName) {
                this.catagoryName = catagoryName;
            }

            public String getMethod() {
                return method;
            }

            public void setMethod(String method) {
                this.method = method;
            }

            public int getTeachAge() {
                return teachAge;
            }

            public void setTeachAge(int teachAge) {
                this.teachAge = teachAge;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getLegalTel() {
                return legalTel;
            }

            public void setLegalTel(String legalTel) {
                this.legalTel = legalTel;
            }

            public Object getContactUser() {
                return contactUser;
            }

            public void setContactUser(Object contactUser) {
                this.contactUser = contactUser;
            }

            public String getContactTel() {
                return contactTel;
            }

            public void setContactTel(String contactTel) {
                this.contactTel = contactTel;
            }

            public Object getWorkTime() {
                return workTime;
            }

            public void setWorkTime(Object workTime) {
                this.workTime = workTime;
            }

            public Object getStrength() {
                return strength;
            }

            public void setStrength(Object strength) {
                this.strength = strength;
            }

            public Object getFixDate() {
                return fixDate;
            }

            public void setFixDate(Object fixDate) {
                this.fixDate = fixDate;
            }

            public Object getScale() {
                return scale;
            }

            public void setScale(Object scale) {
                this.scale = scale;
            }

            public String getCustomerId() {
                return customerId;
            }

            public void setCustomerId(String customerId) {
                this.customerId = customerId;
            }

            public boolean isEnable() {
                return enable;
            }

            public void setEnable(boolean enable) {
                this.enable = enable;
            }

            public int getQcAuth() {
                return qcAuth;
            }

            public void setQcAuth(int qcAuth) {
                this.qcAuth = qcAuth;
            }

            public Object getQcAuthId() {
                return qcAuthId;
            }

            public void setQcAuthId(Object qcAuthId) {
                this.qcAuthId = qcAuthId;
            }

            public int getTqcAuth() {
                return tqcAuth;
            }

            public void setTqcAuth(int tqcAuth) {
                this.tqcAuth = tqcAuth;
            }

            public Object getTqcAuthId() {
                return tqcAuthId;
            }

            public void setTqcAuthId(Object tqcAuthId) {
                this.tqcAuthId = tqcAuthId;
            }

            public int getContractAuth() {
                return contractAuth;
            }

            public void setContractAuth(int contractAuth) {
                this.contractAuth = contractAuth;
            }

            public Object getContractAuthStr() {
                return contractAuthStr;
            }

            public void setContractAuthStr(Object contractAuthStr) {
                this.contractAuthStr = contractAuthStr;
            }

            public Object getJoinOrganization() {
                return joinOrganization;
            }

            public void setJoinOrganization(Object joinOrganization) {
                this.joinOrganization = joinOrganization;
            }

            public Object getJoinOrganizationName() {
                return joinOrganizationName;
            }

            public void setJoinOrganizationName(Object joinOrganizationName) {
                this.joinOrganizationName = joinOrganizationName;
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

            public Object getAddUserName() {
                return addUserName;
            }

            public void setAddUserName(Object addUserName) {
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

            public Object getChgDegree() {
                return chgDegree;
            }

            public void setChgDegree(Object chgDegree) {
                this.chgDegree = chgDegree;
            }

            public Object getDegree() {
                return degree;
            }

            public void setDegree(Object degree) {
                this.degree = degree;
            }

            public Object getTeacher() {
                return teacher;
            }

            public void setTeacher(Object teacher) {
                this.teacher = teacher;
            }

            public Object getCustomer() {
                return customer;
            }

            public void setCustomer(Object customer) {
                this.customer = customer;
            }

            public Object getComments() {
                return comments;
            }

            public void setComments(Object comments) {
                this.comments = comments;
            }

            public String getCommentCount() {
                return commentCount;
            }

            public void setCommentCount(String commentCount) {
                this.commentCount = commentCount;
            }

            public int getAvgPrice() {
                return avgPrice;
            }

            public void setAvgPrice(int avgPrice) {
                this.avgPrice = avgPrice;
            }

            public Object getMinimumPrice() {
                return minimumPrice;
            }

            public void setMinimumPrice(Object minimumPrice) {
                this.minimumPrice = minimumPrice;
            }

            public int getMinVipPrice() {
                return minVipPrice;
            }

            public void setMinVipPrice(int minVipPrice) {
                this.minVipPrice = minVipPrice;
            }

            public Object getScore() {
                return score;
            }

            public void setScore(Object score) {
                this.score = score;
            }

            public boolean isStudioOrNot() {
                return studioOrNot;
            }

            public void setStudioOrNot(boolean studioOrNot) {
                this.studioOrNot = studioOrNot;
            }

            public Object getProtocolOrContract() {
                return protocolOrContract;
            }

            public void setProtocolOrContract(Object protocolOrContract) {
                this.protocolOrContract = protocolOrContract;
            }

            public int getEnvCount() {
                return envCount;
            }

            public void setEnvCount(int envCount) {
                this.envCount = envCount;
            }

            public int getExeCount() {
                return exeCount;
            }

            public void setExeCount(int exeCount) {
                this.exeCount = exeCount;
            }

            public int getShowCount() {
                return showCount;
            }

            public void setShowCount(int showCount) {
                this.showCount = showCount;
            }

            public int getResCount() {
                return resCount;
            }

            public void setResCount(int resCount) {
                this.resCount = resCount;
            }

            public boolean isDel() {
                return del;
            }

            public void setDel(boolean del) {
                this.del = del;
            }
        }

        public static class CustomerBean implements Serializable{
            /**
             * radius : null
             * districtType : null
             * range : -1
             * id : 6488
             * sno : 20160819000006488
             * degreeId : 2
             * chgDegreeId : 4122
             * chgDgrCheckId : null
             * isDirApp : true
             * teacherTimeId : 6426
             * phone : 15818299470
             * password : 220C2FF5C8B1A8C44FADCE2E5AA509B9
             * address : 地址：朱泾镇秀州街328号209-210室
             * <p>
             * sex : null
             * birthday : null
             * signame : 华彩琴行(-朱泾店)
             * username : null
             * picture : http://video.xqban.com/RegTeacher/2016-08-19/1471598408923_qq截图20160819171822.png
             * studSkill : null
             * school : null
             * colDegree : null
             * uidCard : null
             * realnameAuth : 0
             * realnameAuthId : null
             * degreeAuth : 0
             * degreeAuthId : null
             * summary : null
             * continueSumSignDay : null
             * countView : null
             * status : 0
             * chgDegree : null
             * degree : null
             * lng : 121.174087
             * lat : 30.902754
             * location : 121.174087,30.902754
             * formatted_address : 上海市金山区秀州街|328号
             * adcode : 310116
             * dnyLng : null
             * dnyLat : null
             * dnyAddress : null
             * dnyGisUpdateTime : null
             * areasId : 310116
             * provinceId : 310000
             * cityId : 310100
             * cityName : 上海市市辖区
             * areasName : 金山区
             * provinceName : 上海市
             * homeProvinceName : 上海市
             * studioOrNot : false
             * openid : null
             * oauthType : null
             * accountsId : 8
             * agentId : 1
             * iAmMembers : false
             * invalidtimeAboutMember : null
             * shareGetGift : null
             * notvirgin : null
             * removed : 0
             * addUserId : 8
             * addUserName : null
             * addUserTime : 2016-08-19 17:20:37
             * editUserId : 1
             * editUserName : admin
             * editUserTime : 2016-10-08 16:42:08
             * isAddUserType : accounts
             * isEditUserType : staff
             * published : true
             * sorts : 0
             * display : 1
             * digest : 0
             * balances : 0
             * points : 0
             * json : null
             * customerId : 6488
             * del : false
             */

            private Object radius;
            private Object districtType;
            private int range;
            private String id;
            private String sno;
            private String degreeId;
            private String chgDegreeId;
            private Object chgDgrCheckId;
            private boolean isDirApp;
            private String teacherTimeId;
            private String phone;
            private String password;
            private String address;
            private Object sex;
            private Object birthday;
            private String signame;
            private Object username;
            private String picture;
            private Object studSkill;
            private Object school;
            private Object colDegree;
            private Object uidCard;
            private int realnameAuth;
            private Object realnameAuthId;
            private int degreeAuth;
            private Object degreeAuthId;
            private Object summary;
            private Object continueSumSignDay;
            private Object countView;
            private int status;
            private Object chgDegree;
            private Object degree;
            private double lng;
            private double lat;
            private String location;
            private String formatted_address;
            private String adcode;
            private Object dnyLng;
            private Object dnyLat;
            private Object dnyAddress;
            private Object dnyGisUpdateTime;
            private String areasId;
            private String provinceId;
            private String cityId;
            private String cityName;
            private String areasName;
            private String provinceName;
            private String homeProvinceName;
            private boolean studioOrNot;
            private Object openid;
            private Object oauthType;
            private String accountsId;
            private String agentId;
            private boolean iAmMembers;
            private Object invalidtimeAboutMember;
            private Object shareGetGift;
            private Object notvirgin;
            private int removed;
            private String addUserId;
            private Object addUserName;
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
            private int balances;
            private int points;
            private Object json;
            private String customerId;
            private boolean del;

            public Object getRadius() {
                return radius;
            }

            public void setRadius(Object radius) {
                this.radius = radius;
            }

            public Object getDistrictType() {
                return districtType;
            }

            public void setDistrictType(Object districtType) {
                this.districtType = districtType;
            }

            public int getRange() {
                return range;
            }

            public void setRange(int range) {
                this.range = range;
            }

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getSno() {
                return sno;
            }

            public void setSno(String sno) {
                this.sno = sno;
            }

            public String getDegreeId() {
                return degreeId;
            }

            public void setDegreeId(String degreeId) {
                this.degreeId = degreeId;
            }

            public String getChgDegreeId() {
                return chgDegreeId;
            }

            public void setChgDegreeId(String chgDegreeId) {
                this.chgDegreeId = chgDegreeId;
            }

            public Object getChgDgrCheckId() {
                return chgDgrCheckId;
            }

            public void setChgDgrCheckId(Object chgDgrCheckId) {
                this.chgDgrCheckId = chgDgrCheckId;
            }

            public boolean isIsDirApp() {
                return isDirApp;
            }

            public void setIsDirApp(boolean isDirApp) {
                this.isDirApp = isDirApp;
            }

            public String getTeacherTimeId() {
                return teacherTimeId;
            }

            public void setTeacherTimeId(String teacherTimeId) {
                this.teacherTimeId = teacherTimeId;
            }

            public String getPhone() {
                return phone;
            }

            public void setPhone(String phone) {
                this.phone = phone;
            }

            public String getPassword() {
                return password;
            }

            public void setPassword(String password) {
                this.password = password;
            }

            public String getAddress() {
                return address;
            }

            public void setAddress(String address) {
                this.address = address;
            }

            public Object getSex() {
                return sex;
            }

            public void setSex(Object sex) {
                this.sex = sex;
            }

            public Object getBirthday() {
                return birthday;
            }

            public void setBirthday(Object birthday) {
                this.birthday = birthday;
            }

            public String getSigname() {
                return signame;
            }

            public void setSigname(String signame) {
                this.signame = signame;
            }

            public Object getUsername() {
                return username;
            }

            public void setUsername(Object username) {
                this.username = username;
            }

            public String getPicture() {
                return picture;
            }

            public void setPicture(String picture) {
                this.picture = picture;
            }

            public Object getStudSkill() {
                return studSkill;
            }

            public void setStudSkill(Object studSkill) {
                this.studSkill = studSkill;
            }

            public Object getSchool() {
                return school;
            }

            public void setSchool(Object school) {
                this.school = school;
            }

            public Object getColDegree() {
                return colDegree;
            }

            public void setColDegree(Object colDegree) {
                this.colDegree = colDegree;
            }

            public Object getUidCard() {
                return uidCard;
            }

            public void setUidCard(Object uidCard) {
                this.uidCard = uidCard;
            }

            public int getRealnameAuth() {
                return realnameAuth;
            }

            public void setRealnameAuth(int realnameAuth) {
                this.realnameAuth = realnameAuth;
            }

            public Object getRealnameAuthId() {
                return realnameAuthId;
            }

            public void setRealnameAuthId(Object realnameAuthId) {
                this.realnameAuthId = realnameAuthId;
            }

            public int getDegreeAuth() {
                return degreeAuth;
            }

            public void setDegreeAuth(int degreeAuth) {
                this.degreeAuth = degreeAuth;
            }

            public Object getDegreeAuthId() {
                return degreeAuthId;
            }

            public void setDegreeAuthId(Object degreeAuthId) {
                this.degreeAuthId = degreeAuthId;
            }

            public Object getSummary() {
                return summary;
            }

            public void setSummary(Object summary) {
                this.summary = summary;
            }

            public Object getContinueSumSignDay() {
                return continueSumSignDay;
            }

            public void setContinueSumSignDay(Object continueSumSignDay) {
                this.continueSumSignDay = continueSumSignDay;
            }

            public Object getCountView() {
                return countView;
            }

            public void setCountView(Object countView) {
                this.countView = countView;
            }

            public int getStatus() {
                return status;
            }

            public void setStatus(int status) {
                this.status = status;
            }

            public Object getChgDegree() {
                return chgDegree;
            }

            public void setChgDegree(Object chgDegree) {
                this.chgDegree = chgDegree;
            }

            public Object getDegree() {
                return degree;
            }

            public void setDegree(Object degree) {
                this.degree = degree;
            }

            public double getLng() {
                return lng;
            }

            public void setLng(double lng) {
                this.lng = lng;
            }

            public double getLat() {
                return lat;
            }

            public void setLat(double lat) {
                this.lat = lat;
            }

            public String getLocation() {
                return location;
            }

            public void setLocation(String location) {
                this.location = location;
            }

            public String getFormatted_address() {
                return formatted_address;
            }

            public void setFormatted_address(String formatted_address) {
                this.formatted_address = formatted_address;
            }

            public String getAdcode() {
                return adcode;
            }

            public void setAdcode(String adcode) {
                this.adcode = adcode;
            }

            public Object getDnyLng() {
                return dnyLng;
            }

            public void setDnyLng(Object dnyLng) {
                this.dnyLng = dnyLng;
            }

            public Object getDnyLat() {
                return dnyLat;
            }

            public void setDnyLat(Object dnyLat) {
                this.dnyLat = dnyLat;
            }

            public Object getDnyAddress() {
                return dnyAddress;
            }

            public void setDnyAddress(Object dnyAddress) {
                this.dnyAddress = dnyAddress;
            }

            public Object getDnyGisUpdateTime() {
                return dnyGisUpdateTime;
            }

            public void setDnyGisUpdateTime(Object dnyGisUpdateTime) {
                this.dnyGisUpdateTime = dnyGisUpdateTime;
            }

            public String getAreasId() {
                return areasId;
            }

            public void setAreasId(String areasId) {
                this.areasId = areasId;
            }

            public String getProvinceId() {
                return provinceId;
            }

            public void setProvinceId(String provinceId) {
                this.provinceId = provinceId;
            }

            public String getCityId() {
                return cityId;
            }

            public void setCityId(String cityId) {
                this.cityId = cityId;
            }

            public String getCityName() {
                return cityName;
            }

            public void setCityName(String cityName) {
                this.cityName = cityName;
            }

            public String getAreasName() {
                return areasName;
            }

            public void setAreasName(String areasName) {
                this.areasName = areasName;
            }

            public String getProvinceName() {
                return provinceName;
            }

            public void setProvinceName(String provinceName) {
                this.provinceName = provinceName;
            }

            public String getHomeProvinceName() {
                return homeProvinceName;
            }

            public void setHomeProvinceName(String homeProvinceName) {
                this.homeProvinceName = homeProvinceName;
            }

            public boolean isStudioOrNot() {
                return studioOrNot;
            }

            public void setStudioOrNot(boolean studioOrNot) {
                this.studioOrNot = studioOrNot;
            }

            public Object getOpenid() {
                return openid;
            }

            public void setOpenid(Object openid) {
                this.openid = openid;
            }

            public Object getOauthType() {
                return oauthType;
            }

            public void setOauthType(Object oauthType) {
                this.oauthType = oauthType;
            }

            public String getAccountsId() {
                return accountsId;
            }

            public void setAccountsId(String accountsId) {
                this.accountsId = accountsId;
            }

            public String getAgentId() {
                return agentId;
            }

            public void setAgentId(String agentId) {
                this.agentId = agentId;
            }

            public boolean isIAmMembers() {
                return iAmMembers;
            }

            public void setIAmMembers(boolean iAmMembers) {
                this.iAmMembers = iAmMembers;
            }

            public Object getInvalidtimeAboutMember() {
                return invalidtimeAboutMember;
            }

            public void setInvalidtimeAboutMember(Object invalidtimeAboutMember) {
                this.invalidtimeAboutMember = invalidtimeAboutMember;
            }

            public Object getShareGetGift() {
                return shareGetGift;
            }

            public void setShareGetGift(Object shareGetGift) {
                this.shareGetGift = shareGetGift;
            }

            public Object getNotvirgin() {
                return notvirgin;
            }

            public void setNotvirgin(Object notvirgin) {
                this.notvirgin = notvirgin;
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

            public Object getAddUserName() {
                return addUserName;
            }

            public void setAddUserName(Object addUserName) {
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

            public int getBalances() {
                return balances;
            }

            public void setBalances(int balances) {
                this.balances = balances;
            }

            public int getPoints() {
                return points;
            }

            public void setPoints(int points) {
                this.points = points;
            }

            public Object getJson() {
                return json;
            }

            public void setJson(Object json) {
                this.json = json;
            }

            public String getCustomerId() {
                return customerId;
            }

            public void setCustomerId(String customerId) {
                this.customerId = customerId;
            }

            public boolean isDel() {
                return del;
            }

            public void setDel(boolean del) {
                this.del = del;
            }
        }
    }
}
