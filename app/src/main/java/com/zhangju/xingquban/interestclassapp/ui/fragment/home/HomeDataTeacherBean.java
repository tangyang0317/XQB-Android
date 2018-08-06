package com.zhangju.xingquban.interestclassapp.ui.fragment.home;

import com.zhangju.xingquban.interestclassapp.refactor.me.bean.ResponseComment;

import java.io.Serializable;
import java.util.List;

/**
 * Created by zsl on 2017/9/19.
 */

public class HomeDataTeacherBean implements Serializable{


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

    public static class ErrMsgBean implements Serializable{
    }

    public static class AaDataBean implements Serializable{

        private String editUserId;
        private String degreeId;
        private int collectionNum;
        private Object tqcAuthId;
        private String cityId;
        private double score;
        private int showCount;
        private boolean enable;
        private String classRoom;
        private String id;
        private Object minimumPrice;
        private boolean studioOrNot;
        private int sorts;
        private int exeCount;
        private String method;
        private double lng;
        private String addUserId;
        private int envCount;
        private double minVipPrice;
        private Object degreeAuthId;
        private String catagoriesId;
        private int commentCount;
        private String phone;
        private String areasName;
        private String provinceName;
        private String isEditUserType;
        private int degreeAuth;
        private int resesNum;
        private double avgPrice;
        private double range;
        private int teachAge;
        private Object qCertificate;
        private int clickRate;
        private String customerId;
        private int digest;
        private Object contractAuthStr;
        private Object protocolOrContract;
        private int videoLessonNum;
        private int star;
        private String chgDegreeId;
        private int display;
        private Object sex;
        private Object realnameAuthId;
        private Object qcAuthId;
        private Object workTime;
        private String picture;
        private Object colDegree;
        private int resCount;
        private int removed;
        private Object subName;
        private Object contactUser;
        private AccountsBean accounts;
        private boolean isCollections;
        private int lessonNum;
        private String legalTel;
        private Object strength;
        private boolean platformAuth;
        private String contactTel;
        private Object school;
        private double lat;
        private Object joinOrganization;
        private String editUserName;
        private String signame;
        private Object IDCard;
        private String adcode;
        private String chgDegreeCheckTime;
        private DegreeBean degree;
        private String degreeName;
        private boolean published;
        private String provinceId;
        private int taughtCourse;
        private String name;
        private int contractAuth;
        private int qcAuth;
        private Object addUserName;
        private String accountsId;
        private Object fixDate;
        private String formatted_address;
        private String agentId;
        private String areasId;
        private int realnameAuth;
        private boolean isCollection;
        private Object scale;
        private boolean isDirApp;
        private String cityName;
        private String deathDate;
        private Object joinOrganizationName;
        private String isAddUserType;
        private String summary;
        private Object studSkill;
        private Object chgDegreeCheckId;
        private String editUserTime;
        private String addUserTime;
        private String catagoryName;
        private int tqcAuth;
        private String location;
        private double avgComment;
        private Object username;
        private CustomerBean customer;
        private List<OrganAlbumFilesBean> organAlbumFiles;
        private List<?> plans;
        private List<ResesBean> reses;
        private List<LessonsBean> lessons;
        private List<ChangepicBeanX> changepic;
        private List<ResponseComment> commentsList;
        private List<VideoLessonBean> videoLesson;

        public int getClickRate() {
            return clickRate;
        }

        public void setClickRate(int clickRate) {
            this.clickRate = clickRate;
        }
        public String getEditUserId() {
            return editUserId;
        }

        public void setEditUserId(String editUserId) {
            this.editUserId = editUserId;
        }

        public String getDegreeId() {
            return degreeId;
        }

        public void setDegreeId(String degreeId) {
            this.degreeId = degreeId;
        }

        public int getCollectionNum() {
            return collectionNum;
        }

        public void setCollectionNum(int collectionNum) {
            this.collectionNum = collectionNum;
        }

        public Object getTqcAuthId() {
            return tqcAuthId;
        }

        public void setTqcAuthId(Object tqcAuthId) {
            this.tqcAuthId = tqcAuthId;
        }

        public String getCityId() {
            return cityId;
        }

        public void setCityId(String cityId) {
            this.cityId = cityId;
        }

        public double getScore() {
            return score;
        }

        public void setScore(double score) {
            this.score = score;
        }

        public int getShowCount() {
            return showCount;
        }

        public void setShowCount(int showCount) {
            this.showCount = showCount;
        }

        public boolean isEnable() {
            return enable;
        }

        public void setEnable(boolean enable) {
            this.enable = enable;
        }

        public String getClassRoom() {
            return classRoom;
        }

        public void setClassRoom(String classRoom) {
            this.classRoom = classRoom;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public Object getMinimumPrice() {
            return minimumPrice;
        }

        public void setMinimumPrice(Object minimumPrice) {
            this.minimumPrice = minimumPrice;
        }

        public boolean isStudioOrNot() {
            return studioOrNot;
        }

        public void setStudioOrNot(boolean studioOrNot) {
            this.studioOrNot = studioOrNot;
        }

        public int getSorts() {
            return sorts;
        }

        public void setSorts(int sorts) {
            this.sorts = sorts;
        }

        public int getExeCount() {
            return exeCount;
        }

        public void setExeCount(int exeCount) {
            this.exeCount = exeCount;
        }

        public String getMethod() {
            return method;
        }

        public void setMethod(String method) {
            this.method = method;
        }

        public double getLng() {
            return lng;
        }

        public void setLng(double lng) {
            this.lng = lng;
        }

        public String getAddUserId() {
            return addUserId;
        }

        public void setAddUserId(String addUserId) {
            this.addUserId = addUserId;
        }

        public int getEnvCount() {
            return envCount;
        }

        public void setEnvCount(int envCount) {
            this.envCount = envCount;
        }

        public double getMinVipPrice() {
            return minVipPrice;
        }

        public void setMinVipPrice(double minVipPrice) {
            this.minVipPrice = minVipPrice;
        }

        public Object getDegreeAuthId() {
            return degreeAuthId;
        }

        public void setDegreeAuthId(Object degreeAuthId) {
            this.degreeAuthId = degreeAuthId;
        }

        public String getCatagoriesId() {
            return catagoriesId;
        }

        public void setCatagoriesId(String catagoriesId) {
            this.catagoriesId = catagoriesId;
        }

        public int getCommentCount() {
            return commentCount;
        }

        public void setCommentCount(int commentCount) {
            this.commentCount = commentCount;
        }

        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
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

        public String getIsEditUserType() {
            return isEditUserType;
        }

        public void setIsEditUserType(String isEditUserType) {
            this.isEditUserType = isEditUserType;
        }

        public int getDegreeAuth() {
            return degreeAuth;
        }

        public void setDegreeAuth(int degreeAuth) {
            this.degreeAuth = degreeAuth;
        }

        public int getResesNum() {
            return resesNum;
        }

        public void setResesNum(int resesNum) {
            this.resesNum = resesNum;
        }

        public double getAvgPrice() {
            return avgPrice;
        }

        public void setAvgPrice(double avgPrice) {
            this.avgPrice = avgPrice;
        }

        public double getRange() {
            return range;
        }

        public void setRange(double range) {
            this.range = range;
        }

        public int getTeachAge() {
            return teachAge;
        }

        public void setTeachAge(int teachAge) {
            this.teachAge = teachAge;
        }

        public Object getQCertificate() {
            return qCertificate;
        }

        public void setQCertificate(Object qCertificate) {
            this.qCertificate = qCertificate;
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

        public Object getContractAuthStr() {
            return contractAuthStr;
        }

        public void setContractAuthStr(Object contractAuthStr) {
            this.contractAuthStr = contractAuthStr;
        }

        public Object getProtocolOrContract() {
            return protocolOrContract;
        }

        public void setProtocolOrContract(Object protocolOrContract) {
            this.protocolOrContract = protocolOrContract;
        }

        public int getVideoLessonNum() {
            return videoLessonNum;
        }

        public void setVideoLessonNum(int videoLessonNum) {
            this.videoLessonNum = videoLessonNum;
        }

        public int getStar() {
            return star;
        }

        public void setStar(int star) {
            this.star = star;
        }

        public String getChgDegreeId() {
            return chgDegreeId;
        }

        public void setChgDegreeId(String chgDegreeId) {
            this.chgDegreeId = chgDegreeId;
        }

        public int getDisplay() {
            return display;
        }

        public void setDisplay(int display) {
            this.display = display;
        }

        public Object getSex() {
            return sex;
        }

        public void setSex(Object sex) {
            this.sex = sex;
        }

        public Object getRealnameAuthId() {
            return realnameAuthId;
        }

        public void setRealnameAuthId(Object realnameAuthId) {
            this.realnameAuthId = realnameAuthId;
        }

        public Object getQcAuthId() {
            return qcAuthId;
        }

        public void setQcAuthId(Object qcAuthId) {
            this.qcAuthId = qcAuthId;
        }

        public Object getWorkTime() {
            return workTime;
        }

        public void setWorkTime(Object workTime) {
            this.workTime = workTime;
        }

        public String getPicture() {
            return picture;
        }

        public void setPicture(String picture) {
            this.picture = picture;
        }

        public Object getColDegree() {
            return colDegree;
        }

        public void setColDegree(Object colDegree) {
            this.colDegree = colDegree;
        }

        public int getResCount() {
            return resCount;
        }

        public void setResCount(int resCount) {
            this.resCount = resCount;
        }

        public int getRemoved() {
            return removed;
        }

        public void setRemoved(int removed) {
            this.removed = removed;
        }

        public Object getSubName() {
            return subName;
        }

        public void setSubName(Object subName) {
            this.subName = subName;
        }

        public Object getContactUser() {
            return contactUser;
        }

        public void setContactUser(Object contactUser) {
            this.contactUser = contactUser;
        }

        public AccountsBean getAccounts() {
            return accounts;
        }

        public void setAccounts(AccountsBean accounts) {
            this.accounts = accounts;
        }

        public boolean isIsCollections() {
            return isCollections;
        }

        public void setIsCollections(boolean isCollections) {
            this.isCollections = isCollections;
        }

        public int getLessonNum() {
            return lessonNum;
        }

        public void setLessonNum(int lessonNum) {
            this.lessonNum = lessonNum;
        }

        public String getLegalTel() {
            return legalTel;
        }

        public void setLegalTel(String legalTel) {
            this.legalTel = legalTel;
        }

        public Object getStrength() {
            return strength;
        }

        public void setStrength(Object strength) {
            this.strength = strength;
        }

        public boolean isPlatformAuth() {
            return platformAuth;
        }

        public void setPlatformAuth(boolean platformAuth) {
            this.platformAuth = platformAuth;
        }

        public String getContactTel() {
            return contactTel;
        }

        public void setContactTel(String contactTel) {
            this.contactTel = contactTel;
        }

        public Object getSchool() {
            return school;
        }

        public void setSchool(Object school) {
            this.school = school;
        }

        public double getLat() {
            return lat;
        }

        public void setLat(double lat) {
            this.lat = lat;
        }

        public Object getJoinOrganization() {
            return joinOrganization;
        }

        public void setJoinOrganization(Object joinOrganization) {
            this.joinOrganization = joinOrganization;
        }

        public String getEditUserName() {
            return editUserName;
        }

        public void setEditUserName(String editUserName) {
            this.editUserName = editUserName;
        }

        public String getSigname() {
            return signame;
        }

        public void setSigname(String signame) {
            this.signame = signame;
        }

        public Object getIDCard() {
            return IDCard;
        }

        public void setIDCard(Object IDCard) {
            this.IDCard = IDCard;
        }

        public String getAdcode() {
            return adcode;
        }

        public void setAdcode(String adcode) {
            this.adcode = adcode;
        }

        public String getChgDegreeCheckTime() {
            return chgDegreeCheckTime;
        }

        public void setChgDegreeCheckTime(String chgDegreeCheckTime) {
            this.chgDegreeCheckTime = chgDegreeCheckTime;
        }

        public DegreeBean getDegree() {
            return degree;
        }

        public void setDegree(DegreeBean degree) {
            this.degree = degree;
        }

        public String getDegreeName() {
            return degreeName;
        }

        public void setDegreeName(String degreeName) {
            this.degreeName = degreeName;
        }

        public boolean isPublished() {
            return published;
        }

        public void setPublished(boolean published) {
            this.published = published;
        }

        public String getProvinceId() {
            return provinceId;
        }

        public void setProvinceId(String provinceId) {
            this.provinceId = provinceId;
        }

        public int getTaughtCourse() {
            return taughtCourse;
        }

        public void setTaughtCourse(int taughtCourse) {
            this.taughtCourse = taughtCourse;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getContractAuth() {
            return contractAuth;
        }

        public void setContractAuth(int contractAuth) {
            this.contractAuth = contractAuth;
        }

        public int getQcAuth() {
            return qcAuth;
        }

        public void setQcAuth(int qcAuth) {
            this.qcAuth = qcAuth;
        }

        public Object getAddUserName() {
            return addUserName;
        }

        public void setAddUserName(Object addUserName) {
            this.addUserName = addUserName;
        }

        public String getAccountsId() {
            return accountsId;
        }

        public void setAccountsId(String accountsId) {
            this.accountsId = accountsId;
        }

        public Object getFixDate() {
            return fixDate;
        }

        public void setFixDate(Object fixDate) {
            this.fixDate = fixDate;
        }

        public String getFormatted_address() {
            return formatted_address;
        }

        public void setFormatted_address(String formatted_address) {
            this.formatted_address = formatted_address;
        }

        public String getAgentId() {
            return agentId;
        }

        public void setAgentId(String agentId) {
            this.agentId = agentId;
        }

        public String getAreasId() {
            return areasId;
        }

        public void setAreasId(String areasId) {
            this.areasId = areasId;
        }

        public int getRealnameAuth() {
            return realnameAuth;
        }

        public void setRealnameAuth(int realnameAuth) {
            this.realnameAuth = realnameAuth;
        }

        public boolean isIsCollection() {
            return isCollection;
        }

        public void setIsCollection(boolean isCollection) {
            this.isCollection = isCollection;
        }

        public Object getScale() {
            return scale;
        }

        public void setScale(Object scale) {
            this.scale = scale;
        }

        public boolean isIsDirApp() {
            return isDirApp;
        }

        public void setIsDirApp(boolean isDirApp) {
            this.isDirApp = isDirApp;
        }

        public String getCityName() {
            return cityName;
        }

        public void setCityName(String cityName) {
            this.cityName = cityName;
        }

        public String getDeathDate() {
            return deathDate;
        }

        public void setDeathDate(String deathDate) {
            this.deathDate = deathDate;
        }

        public Object getJoinOrganizationName() {
            return joinOrganizationName;
        }

        public void setJoinOrganizationName(Object joinOrganizationName) {
            this.joinOrganizationName = joinOrganizationName;
        }

        public String getIsAddUserType() {
            return isAddUserType;
        }

        public void setIsAddUserType(String isAddUserType) {
            this.isAddUserType = isAddUserType;
        }

        public String getSummary() {
            return summary;
        }

        public void setSummary(String summary) {
            this.summary = summary;
        }

        public Object getStudSkill() {
            return studSkill;
        }

        public void setStudSkill(Object studSkill) {
            this.studSkill = studSkill;
        }

        public Object getChgDegreeCheckId() {
            return chgDegreeCheckId;
        }

        public void setChgDegreeCheckId(Object chgDegreeCheckId) {
            this.chgDegreeCheckId = chgDegreeCheckId;
        }

        public String getEditUserTime() {
            return editUserTime;
        }

        public void setEditUserTime(String editUserTime) {
            this.editUserTime = editUserTime;
        }

        public String getAddUserTime() {
            return addUserTime;
        }

        public void setAddUserTime(String addUserTime) {
            this.addUserTime = addUserTime;
        }

        public String getCatagoryName() {
            return catagoryName;
        }

        public void setCatagoryName(String catagoryName) {
            this.catagoryName = catagoryName;
        }

        public int getTqcAuth() {
            return tqcAuth;
        }

        public void setTqcAuth(int tqcAuth) {
            this.tqcAuth = tqcAuth;
        }

        public String getLocation() {
            return location;
        }

        public void setLocation(String location) {
            this.location = location;
        }

        public double getAvgComment() {
            return avgComment;
        }

        public void setAvgComment(double avgComment) {
            this.avgComment = avgComment;
        }

        public Object getUsername() {
            return username;
        }

        public void setUsername(Object username) {
            this.username = username;
        }

        public CustomerBean getCustomer() {
            return customer;
        }

        public void setCustomer(CustomerBean customer) {
            this.customer = customer;
        }

        public List<OrganAlbumFilesBean> getOrganAlbumFiles() {
            return organAlbumFiles;
        }

        public void setOrganAlbumFiles(List<OrganAlbumFilesBean> organAlbumFiles) {
            this.organAlbumFiles = organAlbumFiles;
        }

        public List<?> getPlans() {
            return plans;
        }

        public void setPlans(List<?> plans) {
            this.plans = plans;
        }

        public List<ResesBean> getReses() {
            return reses;
        }

        public void setReses(List<ResesBean> reses) {
            this.reses = reses;
        }

        public List<LessonsBean> getLessons() {
            return lessons;
        }

        public void setLessons(List<LessonsBean> lessons) {
            this.lessons = lessons;
        }

        public List<ChangepicBeanX> getChangepic() {
            return changepic;
        }

        public void setChangepic(List<ChangepicBeanX> changepic) {
            this.changepic = changepic;
        }

        public List<ResponseComment> getCommentsList() {
            return commentsList;
        }

        public void setCommentsList(List<ResponseComment> commentsList) {
            this.commentsList = commentsList;
        }

        public List<VideoLessonBean> getVideoLesson() {
            return videoLesson;
        }

        public void setVideoLesson(List<VideoLessonBean> videoLesson) {
            this.videoLesson = videoLesson;
        }

        public static class VideoLessonBean implements Serializable{
            private String id;
            private String title;
            private String comment;
            private String videoFilesId;
            private String contactWay;
            private String videoStr;
            private int isCharge;
            private int price;
            private Object address;
            private Object latitude;
            private Object longitude;
            private String customerId;
            private String videoTitlePic;
            private int clickRate;
            private Object albumFiles;
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

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public String getComment() {
                return comment;
            }

            public void setComment(String comment) {
                this.comment = comment;
            }

            public String getVideoFilesId() {
                return videoFilesId;
            }

            public void setVideoFilesId(String videoFilesId) {
                this.videoFilesId = videoFilesId;
            }

            public String getContactWay() {
                return contactWay;
            }

            public void setContactWay(String contactWay) {
                this.contactWay = contactWay;
            }

            public String getVideoStr() {
                return videoStr;
            }

            public void setVideoStr(String videoStr) {
                this.videoStr = videoStr;
            }

            public int getIsCharge() {
                return isCharge;
            }

            public void setIsCharge(int isCharge) {
                this.isCharge = isCharge;
            }

            public int getPrice() {
                return price;
            }

            public void setPrice(int price) {
                this.price = price;
            }

            public Object getAddress() {
                return address;
            }

            public void setAddress(Object address) {
                this.address = address;
            }

            public Object getLatitude() {
                return latitude;
            }

            public void setLatitude(Object latitude) {
                this.latitude = latitude;
            }

            public Object getLongitude() {
                return longitude;
            }

            public void setLongitude(Object longitude) {
                this.longitude = longitude;
            }

            public String getCustomerId() {
                return customerId;
            }

            public void setCustomerId(String customerId) {
                this.customerId = customerId;
            }

            public String getVideoTitlePic() {
                return videoTitlePic;
            }

            public void setVideoTitlePic(String videoTitlePic) {
                this.videoTitlePic = videoTitlePic;
            }

            public int getClickRate() {
                return clickRate;
            }

            public void setClickRate(int clickRate) {
                this.clickRate = clickRate;
            }

            public Object getAlbumFiles() {
                return albumFiles;
            }

            public void setAlbumFiles(Object albumFiles) {
                this.albumFiles = albumFiles;
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
        public static class AccountsBean implements Serializable{
            /**
             * id : 8
             * username : zhangju_zhz
             * password : 8B6463BDAEAE73015AEDD32005FC93E2
             * realname : 张涵之
             * phone : 15921924973
             * agentId : 1
             * pid : 1
             * pids : ,1,
             * childrenIds : null
             * roleId : null
             * roleName : null
             * removed : 0
             * addUserId : 1
             * addUserName : null
             * addUserTime : 2016-07-22 10:47:54
             * editUserId : 1
             * editUserName : shanghai_xqb_cery
             * editUserTime : 2016-07-22 10:47:54
             * isAddUserType : accounts
             * isEditUserType : accounts
             * published : true
             * sorts : 0
             * display : 1
             * digest : 0
             * agent : null
             * del : false
             * degreeId :
             */

            private String id;
            private String username;
            private String password;
            private String realname;
            private String phone;
            private String agentId;
            private String pid;
            private String pids;
            private Object childrenIds;
            private Object roleId;
            private Object roleName;
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
            private Object agent;
            private boolean del;
            private String degreeId;

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getUsername() {
                return username;
            }

            public void setUsername(String username) {
                this.username = username;
            }

            public String getPassword() {
                return password;
            }

            public void setPassword(String password) {
                this.password = password;
            }

            public String getRealname() {
                return realname;
            }

            public void setRealname(String realname) {
                this.realname = realname;
            }

            public String getPhone() {
                return phone;
            }

            public void setPhone(String phone) {
                this.phone = phone;
            }

            public String getAgentId() {
                return agentId;
            }

            public void setAgentId(String agentId) {
                this.agentId = agentId;
            }

            public String getPid() {
                return pid;
            }

            public void setPid(String pid) {
                this.pid = pid;
            }

            public String getPids() {
                return pids;
            }

            public void setPids(String pids) {
                this.pids = pids;
            }

            public Object getChildrenIds() {
                return childrenIds;
            }

            public void setChildrenIds(Object childrenIds) {
                this.childrenIds = childrenIds;
            }

            public Object getRoleId() {
                return roleId;
            }

            public void setRoleId(Object roleId) {
                this.roleId = roleId;
            }

            public Object getRoleName() {
                return roleName;
            }

            public void setRoleName(Object roleName) {
                this.roleName = roleName;
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

            public Object getAgent() {
                return agent;
            }

            public void setAgent(Object agent) {
                this.agent = agent;
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

        public static class DegreeBean implements Serializable{
            /**
             * isOrgaliza : true
             * nameCn : 机构
             * isCommon : false
             * id : 2
             * nameEn : orgaliza
             * isTeacher : false
             */

            private boolean isOrgaliza;
            private String nameCn;
            private boolean isCommon;
            private String id;
            private String nameEn;
            private boolean isTeacher;

            public boolean isIsOrgaliza() {
                return isOrgaliza;
            }

            public void setIsOrgaliza(boolean isOrgaliza) {
                this.isOrgaliza = isOrgaliza;
            }

            public String getNameCn() {
                return nameCn;
            }

            public void setNameCn(String nameCn) {
                this.nameCn = nameCn;
            }

            public boolean isIsCommon() {
                return isCommon;
            }

            public void setIsCommon(boolean isCommon) {
                this.isCommon = isCommon;
            }

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getNameEn() {
                return nameEn;
            }

            public void setNameEn(String nameEn) {
                this.nameEn = nameEn;
            }

            public boolean isIsTeacher() {
                return isTeacher;
            }

            public void setIsTeacher(boolean isTeacher) {
                this.isTeacher = isTeacher;
            }
        }

        public static class CustomerBean implements Serializable{
            /**
             * editUserId : 1
             * degreeId : 2
             * uidCard : null
             * cityId : 310100
             * points : 0.0
             * password : 220C2FF5C8B1A8C44FADCE2E5AA509B9
             * dnyAddress : null
             * chgDgrCheckId : null
             * school : null
             * dnyLat : null
             * id : 6488
             * dnyLng : null
             * studioOrNot : false
             * sorts : 0
             * lat : 30.902754
             * editUserName : admin
             * signame : 华彩琴行(-朱泾店)
             * lng : 121.174087
             * adcode : 310116
             * addUserId : 8
             * openid : null
             * published : true
             * degreeAuthId : null
             * provinceId : 310000
             * teacherTimeId : 6426
             * phone : 15818299470
             * areasName : 金山区
             * contractAuth : 0
             * provinceName : 上海市
             * qcAuth : 0
             * isEditUserType : staff
             * status : 0
             * accountsId : 8
             * addUserName : null
             * birthday : null
             * formatted_address : 上海市金山区秀州街|328号
             * agentId : 1
             * degreeAuth : 0
             * areasId : 310116
             * iAmMembers : false
             * realnameAuth : 0
             * countView : null
             * dnyGisUpdateTime : null
             * invalidtimeAboutMember : null
             * isDirApp : true
             * balances : 0.0
             * cityName : 上海市市辖区
             * shareGetGift : null
             * notvirgin : null
             * digest : 0
             * continueSumSignDay : null
             * isAddUserType : accounts
             * contractAuthStr : null
             * summary : null
             * address : 地址：朱泾镇秀州街328号209-210室

             * studSkill : null
             * chgDegreeId : 4122
             * sex : null
             * editUserTime : 2016-10-08 16:42:08
             * display : 1
             * realnameAuthId : null
             * qcAuthId : null
             * homeProvinceName : 上海市
             * picture : http://video.xqban.com/RegTeacher/2016-08-19/1471598408923_qq截图20160819171822.png
             * colDegree : null
             * addUserTime : 2016-08-19 17:20:37
             * removed : 0
             * sno : 20160819000006488
             * location : 121.174087,30.902754
             * oauthType : null
             * username : null
             */

            private String editUserId;
            private String degreeId;
            private Object uidCard;
            private String cityId;
            private double points;
            private String password;
            private Object dnyAddress;
            private Object chgDgrCheckId;
            private Object school;
            private Object dnyLat;
            private String id;
            private Object dnyLng;
            private boolean studioOrNot;
            private int sorts;
            private double lat;
            private String editUserName;
            private String signame;
            private double lng;
            private String adcode;
            private String addUserId;
            private Object openid;
            private boolean published;
            private Object degreeAuthId;
            private String provinceId;
            private String teacherTimeId;
            private String phone;
            private String areasName;
            private int contractAuth;
            private String provinceName;
            private int qcAuth;
            private String isEditUserType;
            private int status;
            private String accountsId;
            private Object addUserName;
            private Object birthday;
            private String formatted_address;
            private String agentId;
            private int degreeAuth;
            private String areasId;
            private boolean iAmMembers;
            private int realnameAuth;
            private Object countView;
            private Object dnyGisUpdateTime;
            private Object invalidtimeAboutMember;
            private boolean isDirApp;
            private double balances;
            private String cityName;
            private Object shareGetGift;
            private Object notvirgin;
            private int digest;
            private Object continueSumSignDay;
            private String isAddUserType;
            private Object contractAuthStr;
            private Object summary;
            private String address;
            private Object studSkill;
            private String chgDegreeId;
            private Object sex;
            private String editUserTime;
            private int display;
            private Object realnameAuthId;
            private Object qcAuthId;
            private String homeProvinceName;
            private String picture;
            private Object colDegree;
            private String addUserTime;
            private int removed;
            private String sno;
            private String location;
            private Object oauthType;
            private Object username;

            public String getEditUserId() {
                return editUserId;
            }

            public void setEditUserId(String editUserId) {
                this.editUserId = editUserId;
            }

            public String getDegreeId() {
                return degreeId;
            }

            public void setDegreeId(String degreeId) {
                this.degreeId = degreeId;
            }

            public Object getUidCard() {
                return uidCard;
            }

            public void setUidCard(Object uidCard) {
                this.uidCard = uidCard;
            }

            public String getCityId() {
                return cityId;
            }

            public void setCityId(String cityId) {
                this.cityId = cityId;
            }

            public double getPoints() {
                return points;
            }

            public void setPoints(double points) {
                this.points = points;
            }

            public String getPassword() {
                return password;
            }

            public void setPassword(String password) {
                this.password = password;
            }

            public Object getDnyAddress() {
                return dnyAddress;
            }

            public void setDnyAddress(Object dnyAddress) {
                this.dnyAddress = dnyAddress;
            }

            public Object getChgDgrCheckId() {
                return chgDgrCheckId;
            }

            public void setChgDgrCheckId(Object chgDgrCheckId) {
                this.chgDgrCheckId = chgDgrCheckId;
            }

            public Object getSchool() {
                return school;
            }

            public void setSchool(Object school) {
                this.school = school;
            }

            public Object getDnyLat() {
                return dnyLat;
            }

            public void setDnyLat(Object dnyLat) {
                this.dnyLat = dnyLat;
            }

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public Object getDnyLng() {
                return dnyLng;
            }

            public void setDnyLng(Object dnyLng) {
                this.dnyLng = dnyLng;
            }

            public boolean isStudioOrNot() {
                return studioOrNot;
            }

            public void setStudioOrNot(boolean studioOrNot) {
                this.studioOrNot = studioOrNot;
            }

            public int getSorts() {
                return sorts;
            }

            public void setSorts(int sorts) {
                this.sorts = sorts;
            }

            public double getLat() {
                return lat;
            }

            public void setLat(double lat) {
                this.lat = lat;
            }

            public String getEditUserName() {
                return editUserName;
            }

            public void setEditUserName(String editUserName) {
                this.editUserName = editUserName;
            }

            public String getSigname() {
                return signame;
            }

            public void setSigname(String signame) {
                this.signame = signame;
            }

            public double getLng() {
                return lng;
            }

            public void setLng(double lng) {
                this.lng = lng;
            }

            public String getAdcode() {
                return adcode;
            }

            public void setAdcode(String adcode) {
                this.adcode = adcode;
            }

            public String getAddUserId() {
                return addUserId;
            }

            public void setAddUserId(String addUserId) {
                this.addUserId = addUserId;
            }

            public Object getOpenid() {
                return openid;
            }

            public void setOpenid(Object openid) {
                this.openid = openid;
            }

            public boolean isPublished() {
                return published;
            }

            public void setPublished(boolean published) {
                this.published = published;
            }

            public Object getDegreeAuthId() {
                return degreeAuthId;
            }

            public void setDegreeAuthId(Object degreeAuthId) {
                this.degreeAuthId = degreeAuthId;
            }

            public String getProvinceId() {
                return provinceId;
            }

            public void setProvinceId(String provinceId) {
                this.provinceId = provinceId;
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

            public String getAreasName() {
                return areasName;
            }

            public void setAreasName(String areasName) {
                this.areasName = areasName;
            }

            public int getContractAuth() {
                return contractAuth;
            }

            public void setContractAuth(int contractAuth) {
                this.contractAuth = contractAuth;
            }

            public String getProvinceName() {
                return provinceName;
            }

            public void setProvinceName(String provinceName) {
                this.provinceName = provinceName;
            }

            public int getQcAuth() {
                return qcAuth;
            }

            public void setQcAuth(int qcAuth) {
                this.qcAuth = qcAuth;
            }

            public String getIsEditUserType() {
                return isEditUserType;
            }

            public void setIsEditUserType(String isEditUserType) {
                this.isEditUserType = isEditUserType;
            }

            public int getStatus() {
                return status;
            }

            public void setStatus(int status) {
                this.status = status;
            }

            public String getAccountsId() {
                return accountsId;
            }

            public void setAccountsId(String accountsId) {
                this.accountsId = accountsId;
            }

            public Object getAddUserName() {
                return addUserName;
            }

            public void setAddUserName(Object addUserName) {
                this.addUserName = addUserName;
            }

            public Object getBirthday() {
                return birthday;
            }

            public void setBirthday(Object birthday) {
                this.birthday = birthday;
            }

            public String getFormatted_address() {
                return formatted_address;
            }

            public void setFormatted_address(String formatted_address) {
                this.formatted_address = formatted_address;
            }

            public String getAgentId() {
                return agentId;
            }

            public void setAgentId(String agentId) {
                this.agentId = agentId;
            }

            public int getDegreeAuth() {
                return degreeAuth;
            }

            public void setDegreeAuth(int degreeAuth) {
                this.degreeAuth = degreeAuth;
            }

            public String getAreasId() {
                return areasId;
            }

            public void setAreasId(String areasId) {
                this.areasId = areasId;
            }

            public boolean isIAmMembers() {
                return iAmMembers;
            }

            public void setIAmMembers(boolean iAmMembers) {
                this.iAmMembers = iAmMembers;
            }

            public int getRealnameAuth() {
                return realnameAuth;
            }

            public void setRealnameAuth(int realnameAuth) {
                this.realnameAuth = realnameAuth;
            }

            public Object getCountView() {
                return countView;
            }

            public void setCountView(Object countView) {
                this.countView = countView;
            }

            public Object getDnyGisUpdateTime() {
                return dnyGisUpdateTime;
            }

            public void setDnyGisUpdateTime(Object dnyGisUpdateTime) {
                this.dnyGisUpdateTime = dnyGisUpdateTime;
            }

            public Object getInvalidtimeAboutMember() {
                return invalidtimeAboutMember;
            }

            public void setInvalidtimeAboutMember(Object invalidtimeAboutMember) {
                this.invalidtimeAboutMember = invalidtimeAboutMember;
            }

            public boolean isIsDirApp() {
                return isDirApp;
            }

            public void setIsDirApp(boolean isDirApp) {
                this.isDirApp = isDirApp;
            }

            public double getBalances() {
                return balances;
            }

            public void setBalances(double balances) {
                this.balances = balances;
            }

            public String getCityName() {
                return cityName;
            }

            public void setCityName(String cityName) {
                this.cityName = cityName;
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

            public int getDigest() {
                return digest;
            }

            public void setDigest(int digest) {
                this.digest = digest;
            }

            public Object getContinueSumSignDay() {
                return continueSumSignDay;
            }

            public void setContinueSumSignDay(Object continueSumSignDay) {
                this.continueSumSignDay = continueSumSignDay;
            }

            public String getIsAddUserType() {
                return isAddUserType;
            }

            public void setIsAddUserType(String isAddUserType) {
                this.isAddUserType = isAddUserType;
            }

            public Object getContractAuthStr() {
                return contractAuthStr;
            }

            public void setContractAuthStr(Object contractAuthStr) {
                this.contractAuthStr = contractAuthStr;
            }

            public Object getSummary() {
                return summary;
            }

            public void setSummary(Object summary) {
                this.summary = summary;
            }

            public String getAddress() {
                return address;
            }

            public void setAddress(String address) {
                this.address = address;
            }

            public Object getStudSkill() {
                return studSkill;
            }

            public void setStudSkill(Object studSkill) {
                this.studSkill = studSkill;
            }

            public String getChgDegreeId() {
                return chgDegreeId;
            }

            public void setChgDegreeId(String chgDegreeId) {
                this.chgDegreeId = chgDegreeId;
            }

            public Object getSex() {
                return sex;
            }

            public void setSex(Object sex) {
                this.sex = sex;
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

            public Object getRealnameAuthId() {
                return realnameAuthId;
            }

            public void setRealnameAuthId(Object realnameAuthId) {
                this.realnameAuthId = realnameAuthId;
            }

            public Object getQcAuthId() {
                return qcAuthId;
            }

            public void setQcAuthId(Object qcAuthId) {
                this.qcAuthId = qcAuthId;
            }

            public String getHomeProvinceName() {
                return homeProvinceName;
            }

            public void setHomeProvinceName(String homeProvinceName) {
                this.homeProvinceName = homeProvinceName;
            }

            public String getPicture() {
                return picture;
            }

            public void setPicture(String picture) {
                this.picture = picture;
            }

            public Object getColDegree() {
                return colDegree;
            }

            public void setColDegree(Object colDegree) {
                this.colDegree = colDegree;
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

            public String getSno() {
                return sno;
            }

            public void setSno(String sno) {
                this.sno = sno;
            }

            public String getLocation() {
                return location;
            }

            public void setLocation(String location) {
                this.location = location;
            }

            public Object getOauthType() {
                return oauthType;
            }

            public void setOauthType(Object oauthType) {
                this.oauthType = oauthType;
            }

            public Object getUsername() {
                return username;
            }

            public void setUsername(Object username) {
                this.username = username;
            }
        }

        public static class OrganAlbumFilesBean implements Serializable{
            /**
             * id : 15
             * organAlbumId : 25
             * videoFilesId : null
             * picVideo : http://video.xqban.com/Customer/2017-09-09/1504929561774_176.mp4
             * isPic : 2
             * videoTitlePic : http://video.xqban.com/Customer/2017-09-09/1504929554177_375.jpg
             * customerId : 6488
             * title : 你好
             * type : 1
             * clickRate : 0
             * organAlbumFilesList : null
             * ids : null
             * timeGap : null
             * removed : 0
             * addUserId : 12776
             * addUserName : bbb(13411111111)
             * addUserTime : 2017-09-11 17:54:30
             * editUserId : 12776
             * editUserName : bbb(13411111111)
             * editUserTime : 2017-09-11 17:54:30
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
            private String organAlbumId;
            private Object videoFilesId;
            private String picVideo;
            private int isPic;
            private String videoTitlePic;
            private String customerId;
            private String title;
            private String type;
            private int clickRate;
            private Object organAlbumFilesList;
            private Object ids;
            private Object timeGap;
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

            public String getOrganAlbumId() {
                return organAlbumId;
            }

            public void setOrganAlbumId(String organAlbumId) {
                this.organAlbumId = organAlbumId;
            }

            public Object getVideoFilesId() {
                return videoFilesId;
            }

            public void setVideoFilesId(Object videoFilesId) {
                this.videoFilesId = videoFilesId;
            }

            public String getPicVideo() {
                return picVideo;
            }

            public void setPicVideo(String picVideo) {
                this.picVideo = picVideo;
            }

            public int getIsPic() {
                return isPic;
            }

            public void setIsPic(int isPic) {
                this.isPic = isPic;
            }

            public String getVideoTitlePic() {
                return videoTitlePic;
            }

            public void setVideoTitlePic(String videoTitlePic) {
                this.videoTitlePic = videoTitlePic;
            }

            public String getCustomerId() {
                return customerId;
            }

            public void setCustomerId(String customerId) {
                this.customerId = customerId;
            }

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public String getType() {
                return type;
            }

            public void setType(String type) {
                this.type = type;
            }

            public int getClickRate() {
                return clickRate;
            }

            public void setClickRate(int clickRate) {
                this.clickRate = clickRate;
            }

            public Object getOrganAlbumFilesList() {
                return organAlbumFilesList;
            }

            public void setOrganAlbumFilesList(Object organAlbumFilesList) {
                this.organAlbumFilesList = organAlbumFilesList;
            }

            public Object getIds() {
                return ids;
            }

            public void setIds(Object ids) {
                this.ids = ids;
            }

            public Object getTimeGap() {
                return timeGap;
            }

            public void setTimeGap(Object timeGap) {
                this.timeGap = timeGap;
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

        public static class ResesBean implements Serializable{
            /**
             * id : 26582
             * name : 李英波
             * signame : 李
             * picture : http://video.xqban.com/Res/2017-06-09/1496974971111_868.jpg
             * summary : 李英波，济宁市乐缶音乐艺术学校校长。1998年毕业于济宁师专艺术系音乐教育专业，先后进于中央音乐学院，上海音乐学院杨茹文、罗天琪、张旭儒教授学习打击乐理论。师从上海打击乐协会陈少伦会长，专业涉及西洋流行打击乐及古典打击乐的技能技巧。通过努力获得以下荣誉：
             中国音协打击乐学会会员；
             中国社会艺术考级考官
             国家西洋打击乐高级演奏员
             中国行进打击乐联合会会员；
             上海打击乐协会理事
             济宁市打击乐协会会长
             山东省音乐家协会会员。
             山东省社会组织联合会会员
             山东省打击乐协会 秘书长

             * customerId : 6488
             * teacherTimeId : 6426
             * age : 42
             * sex : m
             * school : 济宁学院
             * teacherAge : 17
             * catagoryId : 265
             * catagoryName : 架子鼓
             * region : 济宁市
             * method : 小组课、一对一
             * removed : 0
             * addUserId : 32
             * addUserName : jining_xqb_6628
             * addUserTime : 2017-06-09 10:26:42
             * editUserId : 32
             * editUserName : jining_xqb_6628
             * editUserTime : 2017-06-09 10:26:42
             * isAddUserType : accounts
             * isEditUserType : accounts
             * published : true
             * sorts : 0
             * display : 1
             * digest : 0
             * changepic : [{"types":5,"pictureurl":"http://video.xqban.com/ReleaseVersion/2016-08-16/1471344561882_teacherpower.jpg","id":"2","changeId":null}]
             * teacherTime : null
             * exeTypes : null
             * teacherName : null
             * del : false
             * degreeId :
             */

            private String id;
            private String name;
            private String signame;
            private String picture;
            private String summary;
            private String customerId;
            private String teacherTimeId;
            private int age;
            private String sex;
            private String school;
            private int teacherAge;
            private String catagoriesId;
            private String catagoryName;
            private String region;
            private String method;
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
            private Object teacherTime;
            private Object exeTypes;
            private Object teacherName;
            private boolean del;
            private String degreeId;
            private List<ChangepicBean> changepic;

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getSigname() {
                return signame;
            }

            public void setSigname(String signame) {
                this.signame = signame;
            }

            public String getPicture() {
                return picture;
            }

            public void setPicture(String picture) {
                this.picture = picture;
            }

            public String getSummary() {
                return summary;
            }

            public void setSummary(String summary) {
                this.summary = summary;
            }

            public String getCustomerId() {
                return customerId;
            }

            public void setCustomerId(String customerId) {
                this.customerId = customerId;
            }

            public String getTeacherTimeId() {
                return teacherTimeId;
            }

            public void setTeacherTimeId(String teacherTimeId) {
                this.teacherTimeId = teacherTimeId;
            }

            public int getAge() {
                return age;
            }

            public void setAge(int age) {
                this.age = age;
            }

            public String getSex() {
                return sex;
            }

            public void setSex(String sex) {
                this.sex = sex;
            }

            public String getSchool() {
                return school;
            }

            public void setSchool(String school) {
                this.school = school;
            }

            public int getTeacherAge() {
                return teacherAge;
            }

            public void setTeacherAge(int teacherAge) {
                this.teacherAge = teacherAge;
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

            public String getRegion() {
                return region;
            }

            public void setRegion(String region) {
                this.region = region;
            }

            public String getMethod() {
                return method;
            }

            public void setMethod(String method) {
                this.method = method;
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

            public Object getTeacherTime() {
                return teacherTime;
            }

            public void setTeacherTime(Object teacherTime) {
                this.teacherTime = teacherTime;
            }

            public Object getExeTypes() {
                return exeTypes;
            }

            public void setExeTypes(Object exeTypes) {
                this.exeTypes = exeTypes;
            }

            public Object getTeacherName() {
                return teacherName;
            }

            public void setTeacherName(Object teacherName) {
                this.teacherName = teacherName;
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

            public List<ChangepicBean> getChangepic() {
                return changepic;
            }

            public void setChangepic(List<ChangepicBean> changepic) {
                this.changepic = changepic;
            }

            public static class ChangepicBean implements Serializable{
                /**
                 * types : 5
                 * pictureurl : http://video.xqban.com/ReleaseVersion/2016-08-16/1471344561882_teacherpower.jpg
                 * id : 2
                 * changeId : null
                 */

                private int types;
                private String pictureurl;
                private String id;
                private Object changeId;

                public int getTypes() {
                    return types;
                }

                public void setTypes(int types) {
                    this.types = types;
                }

                public String getPictureurl() {
                    return pictureurl;
                }

                public void setPictureurl(String pictureurl) {
                    this.pictureurl = pictureurl;
                }

                public String getId() {
                    return id;
                }

                public void setId(String id) {
                    this.id = id;
                }

                public Object getChangeId() {
                    return changeId;
                }

                public void setChangeId(Object changeId) {
                    this.changeId = changeId;
                }
            }
        }

        public static class LessonsBean implements Serializable{

            /**
             * radius : null
             * districtType : null
             * range : -1.0
             * id : 9526
             * teacherTimeId : 6426
             * customerId : 6488
             * degreeId : 2
             * name : 动漫手绘板大师班
             * picture : http://video.xqban.com/Lesson/2017-06-20/1497930660433_185.jpg
             * categoriesId : 432
             * price : 9526.0
             * vipPrice : 9525.99
             * resId : 马婧
             * method : 学生上门
             * methodType : 1
             * isCantry : false
             * counts : 1
             * allows : 10
             * descript : 动漫手绘大师班开课啦
             * star : null
             * lessonDate : 2017-06-23
             * timelength : 120
             * region : 乌鲁木齐市沙依巴克区
             * areasId : null
             * provinceId : null
             * cityId : null
             * cityName : null
             * areasName : null
             * provinceName : null
             * lng : null
             * lat : null
             * location : null
             * courses : 10
             * summary :
             动漫手绘板大师班（全程手绘板授课）
             上课时间：6月23—7月2日。
             学费标准：10天课程现报名只需1200元。
             优惠方案：以上所有课程学生可多选，报两科以上立减200元。前十名报名者可获得由朝阳画室提供价值200元的画画工具箱。
             * catagoryName : 少儿编程
             * orgName : null
             * teacherName : null
             * removed : 0
             * addUserId : 2145
             * addUserName : 邹霞(13999977728)
             * addUserTime : 2017-06-20 11:54:14
             * editUserId : 2145
             * editUserName : 邹霞(13999977728)
             * editUserTime : 2017-06-20 11:54:14
             * isAddUserType : customer
             * isEditUserType : customer
             * published : true
             * sorts : 0
             * display : 1
             * digest : 0
             * classRoom : 新疆师范大学美术学院
             * classTeacherName : null
             * teacherTime : null
             * customer : null
             * res : null
             * canBeginClass : false
             * canOverClass : false
             * clist : null
             * ordersDetail : null
             * collectionCount : null
             * score : null
             * json : null
             * adcode : null
             * formatted_address : null
             * del : false
             */
            private int lessonOrders;
            private Object radius;
            private Object districtType;
            private double range;
            private String id;
            private String teacherTimeId;
            private String customerId;
            private String degreeId;
            private String name;
            private String picture;
            private String categoriesId;
            private double price;
            private double vipPrice;
            private String resId;
            private String method;
            private int methodType;
            private boolean isCantry;
            private int counts;
            private int allows;
            private String descript;
            private Object star;
            private String lessonDate;
            private String timelength;
            private String region;
            private Object areasId;
            private Object provinceId;
            private Object cityId;
            private Object cityName;
            private Object areasName;
            private Object provinceName;
            private Object lng;
            private Object lat;
            private Object location;
            private int courses;
            private String summary;
            private String catagoryName;
            private Object orgName;
            private Object teacherName;
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
            private String classRoom;
            private Object classTeacherName;
            private Object teacherTime;
            private Object customer;
            private Object res;
            private boolean canBeginClass;
            private boolean canOverClass;
            private Object clist;
            private Object ordersDetail;
            private Object commentCount;
            private Object score;
            private Object json;
            private Object adcode;
            private Object formatted_address;
            private boolean del;

            public int getLessonOrders() {
                return lessonOrders;
            }

            public void setLessonOrders(int lessonOrders) {
                this.lessonOrders = lessonOrders;
            }

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

            public double getRange() {
                return range;
            }

            public void setRange(double range) {
                this.range = range;
            }

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getTeacherTimeId() {
                return teacherTimeId;
            }

            public void setTeacherTimeId(String teacherTimeId) {
                this.teacherTimeId = teacherTimeId;
            }

            public String getCustomerId() {
                return customerId;
            }

            public void setCustomerId(String customerId) {
                this.customerId = customerId;
            }

            public String getDegreeId() {
                return degreeId;
            }

            public void setDegreeId(String degreeId) {
                this.degreeId = degreeId;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getPicture() {
                return picture;
            }

            public void setPicture(String picture) {
                this.picture = picture;
            }

            public String getCategoriesId() {
                return categoriesId;
            }

            public void setCategoriesId(String categoriesId) {
                this.categoriesId = categoriesId;
            }

            public double getPrice() {
                return price;
            }

            public void setPrice(double price) {
                this.price = price;
            }

            public double getVipPrice() {
                return vipPrice;
            }

            public void setVipPrice(double vipPrice) {
                this.vipPrice = vipPrice;
            }

            public String getResId() {
                return resId;
            }

            public void setResId(String resId) {
                this.resId = resId;
            }

            public String getMethod() {
                return method;
            }

            public void setMethod(String method) {
                this.method = method;
            }

            public int getMethodType() {
                return methodType;
            }

            public void setMethodType(int methodType) {
                this.methodType = methodType;
            }

            public boolean isIsCantry() {
                return isCantry;
            }

            public void setIsCantry(boolean isCantry) {
                this.isCantry = isCantry;
            }

            public int getCounts() {
                return counts;
            }

            public void setCounts(int counts) {
                this.counts = counts;
            }

            public int getAllows() {
                return allows;
            }

            public void setAllows(int allows) {
                this.allows = allows;
            }

            public String getDescript() {
                return descript;
            }

            public void setDescript(String descript) {
                this.descript = descript;
            }

            public Object getStar() {
                return star;
            }

            public void setStar(Object star) {
                this.star = star;
            }

            public String getLessonDate() {
                return lessonDate;
            }

            public void setLessonDate(String lessonDate) {
                this.lessonDate = lessonDate;
            }

            public String getTimelength() {
                return timelength;
            }

            public void setTimelength(String timelength) {
                this.timelength = timelength;
            }

            public String getRegion() {
                return region;
            }

            public void setRegion(String region) {
                this.region = region;
            }

            public Object getAreasId() {
                return areasId;
            }

            public void setAreasId(Object areasId) {
                this.areasId = areasId;
            }

            public Object getProvinceId() {
                return provinceId;
            }

            public void setProvinceId(Object provinceId) {
                this.provinceId = provinceId;
            }

            public Object getCityId() {
                return cityId;
            }

            public void setCityId(Object cityId) {
                this.cityId = cityId;
            }

            public Object getCityName() {
                return cityName;
            }

            public void setCityName(Object cityName) {
                this.cityName = cityName;
            }

            public Object getAreasName() {
                return areasName;
            }

            public void setAreasName(Object areasName) {
                this.areasName = areasName;
            }

            public Object getProvinceName() {
                return provinceName;
            }

            public void setProvinceName(Object provinceName) {
                this.provinceName = provinceName;
            }

            public Object getLng() {
                return lng;
            }

            public void setLng(Object lng) {
                this.lng = lng;
            }

            public Object getLat() {
                return lat;
            }

            public void setLat(Object lat) {
                this.lat = lat;
            }

            public Object getLocation() {
                return location;
            }

            public void setLocation(Object location) {
                this.location = location;
            }

            public int getCourses() {
                return courses;
            }

            public void setCourses(int courses) {
                this.courses = courses;
            }

            public String getSummary() {
                return summary;
            }

            public void setSummary(String summary) {
                this.summary = summary;
            }

            public String getCatagoryName() {
                return catagoryName;
            }

            public void setCatagoryName(String catagoryName) {
                this.catagoryName = catagoryName;
            }

            public Object getOrgName() {
                return orgName;
            }

            public void setOrgName(Object orgName) {
                this.orgName = orgName;
            }

            public Object getTeacherName() {
                return teacherName;
            }

            public void setTeacherName(Object teacherName) {
                this.teacherName = teacherName;
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

            public String getClassRoom() {
                return classRoom;
            }

            public void setClassRoom(String classRoom) {
                this.classRoom = classRoom;
            }

            public Object getClassTeacherName() {
                return classTeacherName;
            }

            public void setClassTeacherName(Object classTeacherName) {
                this.classTeacherName = classTeacherName;
            }

            public Object getTeacherTime() {
                return teacherTime;
            }

            public void setTeacherTime(Object teacherTime) {
                this.teacherTime = teacherTime;
            }

            public Object getCustomer() {
                return customer;
            }

            public void setCustomer(Object customer) {
                this.customer = customer;
            }

            public Object getRes() {
                return res;
            }

            public void setRes(Object res) {
                this.res = res;
            }

            public boolean isCanBeginClass() {
                return canBeginClass;
            }

            public void setCanBeginClass(boolean canBeginClass) {
                this.canBeginClass = canBeginClass;
            }

            public boolean isCanOverClass() {
                return canOverClass;
            }

            public void setCanOverClass(boolean canOverClass) {
                this.canOverClass = canOverClass;
            }

            public Object getClist() {
                return clist;
            }

            public void setClist(Object clist) {
                this.clist = clist;
            }

            public Object getOrdersDetail() {
                return ordersDetail;
            }

            public void setOrdersDetail(Object ordersDetail) {
                this.ordersDetail = ordersDetail;
            }

            public Object getCommentCount() {
                return commentCount;
            }

            public void setCommentCount(Object commentCount) {
                this.commentCount = commentCount;
            }

            public Object getScore() {
                return score;
            }

            public void setScore(Object score) {
                this.score = score;
            }

            public Object getJson() {
                return json;
            }

            public void setJson(Object json) {
                this.json = json;
            }

            public Object getAdcode() {
                return adcode;
            }

            public void setAdcode(Object adcode) {
                this.adcode = adcode;
            }

            public Object getFormatted_address() {
                return formatted_address;
            }

            public void setFormatted_address(Object formatted_address) {
                this.formatted_address = formatted_address;
            }

            public boolean isDel() {
                return del;
            }

            public void setDel(boolean del) {
                this.del = del;
            }
        }

        public static class ChangepicBeanX implements Serializable{
            /**
             * types : 3
             * pictureurl : http://video.xqban.com/ReleaseVersion/2017-03-04/1488599346029_336.jpg
             * id : 43
             * changeId : null
             */

            private int types;
            private String pictureurl;
            private String id;
            private Object changeId;

            public int getTypes() {
                return types;
            }

            public void setTypes(int types) {
                this.types = types;
            }

            public String getPictureurl() {
                return pictureurl;
            }

            public void setPictureurl(String pictureurl) {
                this.pictureurl = pictureurl;
            }

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public Object getChangeId() {
                return changeId;
            }

            public void setChangeId(Object changeId) {
                this.changeId = changeId;
            }
        }

        public static class CommentsListBean implements Serializable{
            /**
             * radius : null
             * districtType : null
             * range : -1.0
             * isLeaf : null
             * expanded : null
             * childs : []
             * id : 523
             * parentsId : null
             * name : null
             * summary : 哦婆婆我
             * resourcesId : 6426
             * resourceTitle : null
             * levels : 3
             * customerId : 12777
             * customerName : 超神学院
             * customerPicture : http://video.xqban.com/Customer/2017-11-03/1509702315274_329.jpg
             * rsCount : 0
             * collectionCount : 0
             * picString : http://video.xqban.com/Customer/2017-11-01/1509538885800_459.jpg
             * picList : ["http://video.xqban.com/Customer/2017-11-01/1509538885800_459.jpg"]
             * removed : 0
             * addUserId : 12777
             * addUserName : 啦啦啦德玛西亚(13422222222)
             * addUserTime : 2017-11-01 20:21:27
             * editUserId : 12777
             * editUserName : 啦啦啦德玛西亚(13422222222)
             * editUserTime : 2017-11-01 20:21:27
             * isAddUserType : customer
             * isEditUserType : customer
             * published : true
             * sorts : 0
             * display : 1
             * digest : 0
             * teacherTime : null
             * location : null
             * json : null
             * adcode : null
             * lng : null
             * lat : null
             * formatted_address : null
             * areasId : null
             * areasName : null
             * provinceId : null
             * provinceName : null
             * cityId : null
             * cityName : null
             * del : false
             * degreeId :
             */

            private Object radius;
            private Object districtType;
            private double range;
            private Object isLeaf;
            private Object expanded;
            private String id;
            private Object parentsId;
            private String name;
            private String summary;
            private String resourcesId;
            private Object resourceTitle;
            private int levels;
            private String customerId;
            private String customerName;
            private String customerPicture;
            private int rsCount;
            private int commentCount;
            private String picString;
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
            private Object teacherTime;
            private Object location;
            private Object json;
            private Object adcode;
            private Object lng;
            private Object lat;
            private Object formatted_address;
            private Object areasId;
            private Object areasName;
            private Object provinceId;
            private Object provinceName;
            private Object cityId;
            private Object cityName;
            private boolean del;
            private String degreeId;
            private List<?> childs;
            private List<String> picList;

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

            public double getRange() {
                return range;
            }

            public void setRange(double range) {
                this.range = range;
            }

            public Object getIsLeaf() {
                return isLeaf;
            }

            public void setIsLeaf(Object isLeaf) {
                this.isLeaf = isLeaf;
            }

            public Object getExpanded() {
                return expanded;
            }

            public void setExpanded(Object expanded) {
                this.expanded = expanded;
            }

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public Object getParentsId() {
                return parentsId;
            }

            public void setParentsId(Object parentsId) {
                this.parentsId = parentsId;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getSummary() {
                return summary;
            }

            public void setSummary(String summary) {
                this.summary = summary;
            }

            public String getResourcesId() {
                return resourcesId;
            }

            public void setResourcesId(String resourcesId) {
                this.resourcesId = resourcesId;
            }

            public Object getResourceTitle() {
                return resourceTitle;
            }

            public void setResourceTitle(Object resourceTitle) {
                this.resourceTitle = resourceTitle;
            }

            public int getLevels() {
                return levels;
            }

            public void setLevels(int levels) {
                this.levels = levels;
            }

            public String getCustomerId() {
                return customerId;
            }

            public void setCustomerId(String customerId) {
                this.customerId = customerId;
            }

            public String getCustomerName() {
                return customerName;
            }

            public void setCustomerName(String customerName) {
                this.customerName = customerName;
            }

            public String getCustomerPicture() {
                return customerPicture;
            }

            public void setCustomerPicture(String customerPicture) {
                this.customerPicture = customerPicture;
            }

            public int getRsCount() {
                return rsCount;
            }

            public void setRsCount(int rsCount) {
                this.rsCount = rsCount;
            }

            public int getCommentCount() {
                return commentCount;
            }

            public void setCommentCount(int commentCount) {
                this.commentCount = commentCount;
            }

            public String getPicString() {
                return picString;
            }

            public void setPicString(String picString) {
                this.picString = picString;
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

            public Object getTeacherTime() {
                return teacherTime;
            }

            public void setTeacherTime(Object teacherTime) {
                this.teacherTime = teacherTime;
            }

            public Object getLocation() {
                return location;
            }

            public void setLocation(Object location) {
                this.location = location;
            }

            public Object getJson() {
                return json;
            }

            public void setJson(Object json) {
                this.json = json;
            }

            public Object getAdcode() {
                return adcode;
            }

            public void setAdcode(Object adcode) {
                this.adcode = adcode;
            }

            public Object getLng() {
                return lng;
            }

            public void setLng(Object lng) {
                this.lng = lng;
            }

            public Object getLat() {
                return lat;
            }

            public void setLat(Object lat) {
                this.lat = lat;
            }

            public Object getFormatted_address() {
                return formatted_address;
            }

            public void setFormatted_address(Object formatted_address) {
                this.formatted_address = formatted_address;
            }

            public Object getAreasId() {
                return areasId;
            }

            public void setAreasId(Object areasId) {
                this.areasId = areasId;
            }

            public Object getAreasName() {
                return areasName;
            }

            public void setAreasName(Object areasName) {
                this.areasName = areasName;
            }

            public Object getProvinceId() {
                return provinceId;
            }

            public void setProvinceId(Object provinceId) {
                this.provinceId = provinceId;
            }

            public Object getProvinceName() {
                return provinceName;
            }

            public void setProvinceName(Object provinceName) {
                this.provinceName = provinceName;
            }

            public Object getCityId() {
                return cityId;
            }

            public void setCityId(Object cityId) {
                this.cityId = cityId;
            }

            public Object getCityName() {
                return cityName;
            }

            public void setCityName(Object cityName) {
                this.cityName = cityName;
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

            public List<?> getChilds() {
                return childs;
            }

            public void setChilds(List<?> childs) {
                this.childs = childs;
            }

            public List<String> getPicList() {
                return picList;
            }

            public void setPicList(List<String> picList) {
                this.picList = picList;
            }
        }
    }
}
