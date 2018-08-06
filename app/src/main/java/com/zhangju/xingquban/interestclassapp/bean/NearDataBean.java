package com.zhangju.xingquban.interestclassapp.bean;

import com.zhangju.xingquban.interestclassapp.base.BaseBean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by zsl on 2017/7/8.
 */

public class NearDataBean extends BaseBean implements Serializable{


    private int sEcho;
    private int iTotalRecords;
    private int iTotalDisplayRecords;
    private Object attachData;
    private int total;
    private int page;
//    private boolean success;
    private boolean isLogin;
//    private ErrMsgBean errMsg;
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

//    public boolean isSuccess() {
//        return success;
//    }

//    public void setSuccess(boolean success) {
//        this.success = success;
//    }

    public boolean isIsLogin() {
        return isLogin;
    }

    public void setIsLogin(boolean isLogin) {
        this.isLogin = isLogin;
    }

    /*public ErrMsgBean getErrMsg() {
        return errMsg;
    }*/

    /*public void setErrMsg(ErrMsgBean errMsg) {
        this.errMsg = errMsg;
    }*/

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

    public static class ErrMsgBean implements Serializable{
    }

    public static class AaDataBean implements Serializable{
        private int lessonNum;
        private String legalTel;
        private String editUserId;
        private String degreeId;
        private Object strength;
        private boolean platformAuth;
        private Object tqcAuthId;
        private String cityId;
        private String contactTel;
        private Object score;
        private int showCount;
        private Object school;
        private boolean enable;
        private String classRoom;
        private String id;
        private Object minimumPrice;
        private boolean studioOrNot;
        private int sorts;
        private int exeCount;
        private double lat;
        private Object joinOrganization;
        private String editUserName;
        private String method;
        private String signame;
        private double lng;
        private Object IDCard;
        private String addUserId;
        private String adcode;
        private String chgDegreeCheckTime;
        private int envCount;
        private float minVipPrice;
        private DegreeBean degree;

        private String degreeName;
        private boolean published;
        private Object degreeAuthId;
        private String provinceId;
        private String catagoriesId;
        private int commentCount;
        private String phone;
        private String name;
        private String areasName;
        private Object contractAuth;
        private String provinceName;
        private int qcAuth;
        private String isEditUserType;
        private Object addUserName;
        private String accountsId;
        private Object fixDate;
        private String formatted_address;
        private String agentId;
        private String areasId;
        private int degreeAuth;
        private int realnameAuth;
        private double avgPrice;
        private boolean isCollection;
        private Object scale;
        private int range;
        private int teachAge;
        private boolean isDirApp;
        private Object qCertificate;
        private String cityName;
        private String customerId;
        private int digest;
        private Object joinOrganizationName;
        private String isAddUserType;
        private String summary;
        private Object protocolOrContract;
        private int videoLessonNum;
        private int star;
        private float avgComment;
        private Object studSkill;
        private String chgDegreeId;
        private Object chgDegreeCheckId;
        private String editUserTime;
        private int display;
        private Object sex;
        private Object realnameAuthId;
        private Object qcAuthId;
        private Object workTime;
        private String picture;
        private Object colDegree;
        private String addUserTime;
        private int resCount;
        private int removed;
        private Object subName;
        private String catagoryName;
        private int tqcAuth;
        private String location;
        private Object contactUser;
        private String username;
        private CustomerBean customer;
        private List<OrganAlbumFilesBean> organAlbumFiles;
        private List<LessonsBean> lessons;
        private List<?> resources;
        private List<VideoLessonBean> videoLesson;


        private LiveVdoBean liveVdo;

        public LiveVdoBean getLiveVdo() {
            return liveVdo;
        }

        public void setLiveVdo(LiveVdoBean liveVdo) {
            this.liveVdo = liveVdo;
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

        public String getContactTel() {
            return contactTel;
        }

        public void setContactTel(String contactTel) {
            this.contactTel = contactTel;
        }

        public Object getScore() {
            return score;
        }

        public void setScore(Object score) {
            this.score = score;
        }

        public int getShowCount() {
            return showCount;
        }

        public void setShowCount(int showCount) {
            this.showCount = showCount;
        }

        public Object getSchool() {
            return school;
        }

        public void setSchool(Object school) {
            this.school = school;
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

        public float getAvgComment() {
            return avgComment;
        }

        public void setAvgComment(float avgComment) {
            this.avgComment = avgComment;
        }
        public String getEditUserName() {
            return editUserName;
        }

        public void setEditUserName(String editUserName) {
            this.editUserName = editUserName;
        }

        public String getMethod() {
            return method;
        }

        public void setMethod(String method) {
            this.method = method;
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

        public Object getIDCard() {
            return IDCard;
        }

        public void setIDCard(Object IDCard) {
            this.IDCard = IDCard;
        }

        public String getAddUserId() {
            return addUserId;
        }

        public void setAddUserId(String addUserId) {
            this.addUserId = addUserId;
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

        public int getEnvCount() {
            return envCount;
        }

        public void setEnvCount(int envCount) {
            this.envCount = envCount;
        }

        public float getMinVipPrice() {
            return minVipPrice;
        }

        public void setMinVipPrice(float minVipPrice) {
            this.minVipPrice = minVipPrice;
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

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getAreasName() {
            return areasName;
        }

        public void setAreasName(String areasName) {
            this.areasName = areasName;
        }

        public Object isContractAuth() {
            return contractAuth;
        }

        public void setContractAuth(Object contractAuth) {
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

        public int getDegreeAuth() {
            return degreeAuth;
        }

        public void setDegreeAuth(int degreeAuth) {
            this.degreeAuth = degreeAuth;
        }

        public int getRealnameAuth() {
            return realnameAuth;
        }

        public void setRealnameAuth(int realnameAuth) {
            this.realnameAuth = realnameAuth;
        }

        public double getAvgPrice() {
            return avgPrice;
        }

        public void setAvgPrice(double avgPrice) {
            this.avgPrice = avgPrice;
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

        public int getRange() {
            return range;
        }

        public void setRange(int range) {
            this.range = range;
        }

        public int getTeachAge() {
            return teachAge;
        }

        public void setTeachAge(int teachAge) {
            this.teachAge = teachAge;
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

        public String getCityName() {
            return cityName;
        }

        public void setCityName(String cityName) {
            this.cityName = cityName;
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

        public String getAddUserTime() {
            return addUserTime;
        }

        public void setAddUserTime(String addUserTime) {
            this.addUserTime = addUserTime;
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

        public Object getContactUser() {
            return contactUser;
        }

        public void setContactUser(Object contactUser) {
            this.contactUser = contactUser;
        }

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
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

        public List<LessonsBean> getLessons() {
            return lessons;
        }

        public void setLessons(List<LessonsBean> lessons) {
            this.lessons = lessons;
        }

        public List<?> getResources() {
            return resources;
        }

        public void setResources(List<?> resources) {
            this.resources = resources;
        }

        public List<VideoLessonBean> getVideoLesson() {
            return videoLesson;
        }

        public void setVideoLesson(List<VideoLessonBean> videoLesson) {
            this.videoLesson = videoLesson;
        }

        public static class LiveVdoBean implements Serializable{
            /**
             * id : 1439
             * roomsId : 73
             * chatUserId : 744
             * startTime : 2017-11-14 15:23:16
             * endTime : null
             * roomName : 安卓直播
             * roomPic : http://video.xqban.com/Customer/2017-10-27/1509075506202_425.jpg
             * amount : 0
             * currencyId : null
             * summary : null
             * comtCount : 0
             * likeCount : 0
             * onlineUserCount : 3
             * follows : 0
             * gets : 0
             * fans : 0
             * gives : 0
             * stdCoin : 0
             * catagoriesId : null
             * catagoryName : null
             * lng : null
             * lat : null
             * location : null
             * formatted_address : null
             * adcode : null
             * address : null
             * areasId : null
             * provinceId : null
             * cityId : null
             * cityName : null
             * areasName : null
             * provinceName : null
             * status : 1
             * syncVdoStatus : 0
             * chatUser : null
             * channels : null
             * rooms : null
             * chatroomId : null
             * removed : 0
             * addUserId : 12777
             * addUserName : 刘北风(13422222222)
             * addUserTime : 2017-11-14 15:23:16
             * editUserId : 12777
             * editUserName : 刘北风(13422222222)
             * editUserTime : 2017-11-14 15:41:17
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
            private String roomsId;
            private String chatUserId;
            private String startTime;
            private Object endTime;
            private String roomName;
            private String roomPic;
            private int amount;
            private Object currencyId;
            private Object summary;
            private int comtCount;
            private int likeCount;
            private int onlineUserCount;
            private int follows;
            private int gets;
            private int fans;
            private int gives;
            private int stdCoin;
            private Object catagoriesId;
            private Object catagoryName;
            private Object lng;
            private Object lat;
            private Object location;
            private Object formatted_address;
            private Object adcode;
            private Object address;
            private Object areasId;
            private Object provinceId;
            private Object cityId;
            private Object cityName;
            private Object areasName;
            private Object provinceName;
            private int status;
            private int syncVdoStatus;
            private Object chatUser;
            private Object channels;
            private Object rooms;
            private Object chatroomId;
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

            public String getRoomsId() {
                return roomsId;
            }

            public void setRoomsId(String roomsId) {
                this.roomsId = roomsId;
            }

            public String getChatUserId() {
                return chatUserId;
            }

            public void setChatUserId(String chatUserId) {
                this.chatUserId = chatUserId;
            }

            public String getStartTime() {
                return startTime;
            }

            public void setStartTime(String startTime) {
                this.startTime = startTime;
            }

            public Object getEndTime() {
                return endTime;
            }

            public void setEndTime(Object endTime) {
                this.endTime = endTime;
            }

            public String getRoomName() {
                return roomName;
            }

            public void setRoomName(String roomName) {
                this.roomName = roomName;
            }

            public String getRoomPic() {
                return roomPic;
            }

            public void setRoomPic(String roomPic) {
                this.roomPic = roomPic;
            }

            public int getAmount() {
                return amount;
            }

            public void setAmount(int amount) {
                this.amount = amount;
            }

            public Object getCurrencyId() {
                return currencyId;
            }

            public void setCurrencyId(Object currencyId) {
                this.currencyId = currencyId;
            }

            public Object getSummary() {
                return summary;
            }

            public void setSummary(Object summary) {
                this.summary = summary;
            }

            public int getComtCount() {
                return comtCount;
            }

            public void setComtCount(int comtCount) {
                this.comtCount = comtCount;
            }

            public int getLikeCount() {
                return likeCount;
            }

            public void setLikeCount(int likeCount) {
                this.likeCount = likeCount;
            }

            public int getOnlineUserCount() {
                return onlineUserCount;
            }

            public void setOnlineUserCount(int onlineUserCount) {
                this.onlineUserCount = onlineUserCount;
            }

            public int getFollows() {
                return follows;
            }

            public void setFollows(int follows) {
                this.follows = follows;
            }

            public int getGets() {
                return gets;
            }

            public void setGets(int gets) {
                this.gets = gets;
            }

            public int getFans() {
                return fans;
            }

            public void setFans(int fans) {
                this.fans = fans;
            }

            public int getGives() {
                return gives;
            }

            public void setGives(int gives) {
                this.gives = gives;
            }

            public int getStdCoin() {
                return stdCoin;
            }

            public void setStdCoin(int stdCoin) {
                this.stdCoin = stdCoin;
            }

            public Object getCatagoriesId() {
                return catagoriesId;
            }

            public void setCatagoriesId(Object catagoriesId) {
                this.catagoriesId = catagoriesId;
            }

            public Object getCatagoryName() {
                return catagoryName;
            }

            public void setCatagoryName(Object catagoryName) {
                this.catagoryName = catagoryName;
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

            public Object getFormatted_address() {
                return formatted_address;
            }

            public void setFormatted_address(Object formatted_address) {
                this.formatted_address = formatted_address;
            }

            public Object getAdcode() {
                return adcode;
            }

            public void setAdcode(Object adcode) {
                this.adcode = adcode;
            }

            public Object getAddress() {
                return address;
            }

            public void setAddress(Object address) {
                this.address = address;
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

            public int getStatus() {
                return status;
            }

            public void setStatus(int status) {
                this.status = status;
            }

            public int getSyncVdoStatus() {
                return syncVdoStatus;
            }

            public void setSyncVdoStatus(int syncVdoStatus) {
                this.syncVdoStatus = syncVdoStatus;
            }

            public Object getChatUser() {
                return chatUser;
            }

            public void setChatUser(Object chatUser) {
                this.chatUser = chatUser;
            }

            public Object getChannels() {
                return channels;
            }

            public void setChannels(Object channels) {
                this.channels = channels;
            }

            public Object getRooms() {
                return rooms;
            }

            public void setRooms(Object rooms) {
                this.rooms = rooms;
            }

            public Object getChatroomId() {
                return chatroomId;
            }

            public void setChatroomId(Object chatroomId) {
                this.chatroomId = chatroomId;
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
             * points : 0
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
             * provinceName : 上海市
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
             * balances : 0
             * cityName : 上海市市辖区
             * shareGetGift : null
             * notvirgin : null
             * digest : 0
             * continueSumSignDay : null
             * isAddUserType : accounts
             * summary : null
             * address : 地址：朱泾镇秀州街328号209-210室

             * studSkill : null
             * chgDegreeId : 4122
             * sex : null
             * editUserTime : 2016-10-08 16:42:08
             * display : 1
             * realnameAuthId : null
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
            private int points;
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
            private String provinceName;
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
            private int balances;
            private String cityName;
            private Object shareGetGift;
            private Object notvirgin;
            private int digest;
            private Object continueSumSignDay;
            private String isAddUserType;
            private Object summary;
            private String address;
            private Object studSkill;
            private String chgDegreeId;
            private Object sex;
            private String editUserTime;
            private int display;
            private Object realnameAuthId;
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

            public int getPoints() {
                return points;
            }

            public void setPoints(int points) {
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

            public int getBalances() {
                return balances;
            }

            public void setBalances(int balances) {
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
             * id : 4
             * organAlbumId : 12
             * videoFilesId : null
             * picVideo : http://video.xqban.com/Customer/2017-09-05/1504598983060_237.jpg
             * isPic : 0
             * videoTitlePic : null
             * customerId : 6488
             * title : null
             * type : 1
             * organAlbumFilesList : null
             * removed : 0
             * addUserId : 12776
             * addUserName : bbb(13411111111)
             * addUserTime : 2017-09-11 17:54:35
             * editUserId : 12776
             * editUserName : bbb(13411111111)
             * editUserTime : 2017-09-11 17:54:35
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
            private Object videoTitlePic;
            private String customerId;
            private Object title;
            private String type;
            private Object organAlbumFilesList;
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

            public Object getVideoTitlePic() {
                return videoTitlePic;
            }

            public void setVideoTitlePic(Object videoTitlePic) {
                this.videoTitlePic = videoTitlePic;
            }

            public String getCustomerId() {
                return customerId;
            }

            public void setCustomerId(String customerId) {
                this.customerId = customerId;
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

            public Object getOrganAlbumFilesList() {
                return organAlbumFilesList;
            }

            public void setOrganAlbumFilesList(Object organAlbumFilesList) {
                this.organAlbumFilesList = organAlbumFilesList;
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

        public static class LessonsBean implements Serializable{
            /**
             * radius : null
             * districtType : null
             * range : -1
             * id : 9526
             * teacherTimeId : 6426
             * customerId : 6488
             * degreeId : 2
             * name : 动漫手绘板大师班
             * picture : http://video.xqban.com/Lesson/2017-06-20/1497930660433_185.jpg
             * categoriesId : 432
             * price : 120
             * vipPrice : null
             * resId : 马婧
             * method : 学生上门
             * methodType : 1
             * isCantry : false
             * counts : 0
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
             * commentCount : null
             * score : null
             * json : null
             * adcode : null
             * formatted_address : null
             * del : false
             */

            private Object radius;
            private Object districtType;
            private int range;
            private String id;
            private String teacherTimeId;
            private String customerId;
            private String degreeId;
            private String name;
            private String picture;
            private String categoriesId;
            private float price;
            private Object vipPrice;
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

            public float getPrice() {
                return price;
            }

            public void setPrice(float price) {
                this.price = price;
            }

            public Object getVipPrice() {
                return vipPrice;
            }

            public void setVipPrice(Object vipPrice) {
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

        public static class VideoLessonBean implements Serializable{
            /**
             * id : 3
             * organAlbumId : 11
             * videoFilesId : null
             * picVideo : http://video.xqban.com/Customer/2017-09-05/1504593460831_345.jpg
             * isPic : 0
             * videoTitlePic : null
             * customerId : 6488
             * title : null
             * type : 2
             * organAlbumFilesList : null
             * removed : 0
             * addUserId : 12776
             * addUserName : bbb(13411111111)
             * addUserTime : 2017-09-11 17:54:36
             * editUserId : 12776
             * editUserName : bbb(13411111111)
             * editUserTime : 2017-09-11 17:54:36
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
            private Object videoTitlePic;
            private String customerId;
            private String title;
            private String type;
            private Object organAlbumFilesList;
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

            public Object getVideoTitlePic() {
                return videoTitlePic;
            }

            public void setVideoTitlePic(Object videoTitlePic) {
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

            public Object getOrganAlbumFilesList() {
                return organAlbumFilesList;
            }

            public void setOrganAlbumFilesList(Object organAlbumFilesList) {
                this.organAlbumFilesList = organAlbumFilesList;
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
