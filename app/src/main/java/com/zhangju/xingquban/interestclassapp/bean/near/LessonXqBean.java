package com.zhangju.xingquban.interestclassapp.bean.near;

import java.io.Serializable;
import java.util.List;

/**
 * Created by zgl on 2017/12/15.
 */

public class LessonXqBean implements Serializable {

    /**
     * sEcho : 1
     * iTotalRecords : 1
     * iTotalDisplayRecords : 1
     * aaData : [{"lessonOrders":1,"editUserId":"12777","degreeId":"2","counts":0,"classTeacherName":null,"cityId":"330100","score":0,"isCantry":false,"price":100,"classRoom":"","id":"9937","sorts":0,"lat":30.290998,"lessonDate":"2017-12-15","courses":133,"editUserName":"刘北风(13422222222)","teacherTime":null,"method":"学生上门","lng":120.213116,"signame":null,"addUserId":"12777","published":true,"resId":"王博","provinceId":"330000","commentCount":0,"teacherTimeId":"11015","name":"魅族课程","areasName":"江干区","provinceName":"浙江省","descript":"描述","region":"杭州东站","isEditUserType":"customer","addUserName":"刘北风(13422222222)","areasId":"330104","range":16470,"methodType":1,"cityName":"杭州市","customerId":"12777","vipPrice":1,"digest":0,"categoriesId":null,"isAddUserType":"customer","allows":10,"summary":"简介","star":null,"teacherName":null,"editUserTime":"2017-12-15 09:53:04","display":1,"picture":"http://video.xqban.com/Customer/2017-12-15/1513302783558_89.jpg","timelength":"10","addUserTime":"2017-12-15 09:53:04","removed":0,"catagoryName":"速写","location":null,"customer":null}]
     * attachData : null
     * total : 1
     * page : 0
     * success : true
     * isLogin : true
     * errMsg : {}
     * isAdmin : false
     * cId : 12776
     * cname : 13411111111
     * time : 2017-12-15 10:36:03:477
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

    public static class ErrMsgBean  implements Serializable{
    }

    public static class AaDataBean  implements Serializable{
        /**
         * lessonOrders : 1
         * editUserId : 12777
         * degreeId : 2
         * counts : 0
         * classTeacherName : null
         * cityId : 330100
         * score : 0.0
         * isCantry : false
         * price : 100.0
         * classRoom :
         * id : 9937
         * sorts : 0
         * lat : 30.290998
         * lessonDate : 2017-12-15
         * courses : 133
         * editUserName : 刘北风(13422222222)
         * teacherTime : null
         * method : 学生上门
         * lng : 120.213116
         * signame : null
         * addUserId : 12777
         * published : true
         * resId : 王博
         * provinceId : 330000
         * commentCount : 0
         * teacherTimeId : 11015
         * name : 魅族课程
         * areasName : 江干区
         * provinceName : 浙江省
         * descript : 描述
         * region : 杭州东站
         * isEditUserType : customer
         * addUserName : 刘北风(13422222222)
         * areasId : 330104
         * range : 16470.0
         * methodType : 1
         * cityName : 杭州市
         * customerId : 12777
         * vipPrice : 1.0
         * digest : 0
         * categoriesId : null
         * isAddUserType : customer
         * allows : 10
         * summary : 简介
         * star : null
         * teacherName : null
         * editUserTime : 2017-12-15 09:53:04
         * display : 1
         * picture : http://video.xqban.com/Customer/2017-12-15/1513302783558_89.jpg
         * timelength : 10
         * addUserTime : 2017-12-15 09:53:04
         * removed : 0
         * catagoryName : 速写
         * location : null
         * customer : null
         */

        private int lessonOrders;
        private String editUserId;
        private String degreeId;
        private int counts;
        private Object classTeacherName;
        private String cityId;
        private double score;
        private boolean isCantry;
        private double price;
        private String classRoom;
        private String id;
        private int sorts;
        private double lat;
        private String lessonDate;
        private int courses;
        private String editUserName;
        private Object teacherTime;
        private String method;
        private double lng;
        private Object signame;
        private String addUserId;
        private boolean published;
        private String resId;
        private String provinceId;
        private int commentCount;
        private String teacherTimeId;
        private String name;
        private String areasName;
        private String provinceName;
        private String descript;
        private String region;
        private String isEditUserType;
        private String addUserName;
        private String areasId;
        private double range;
        private int methodType;
        private String cityName;
        private String customerId;
        private double vipPrice;
        private int digest;
        private Object categoriesId;
        private String isAddUserType;
        private int allows;
        private String summary;
        private Object star;
        private Object teacherName;
        private String editUserTime;
        private int display;
        private String picture;
        private String timelength;
        private String addUserTime;
        private int removed;
        private String catagoryName;
        private Object location;
        private Object customer;

        public int getLessonOrders() {
            return lessonOrders;
        }

        public void setLessonOrders(int lessonOrders) {
            this.lessonOrders = lessonOrders;
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

        public int getCounts() {
            return counts;
        }

        public void setCounts(int counts) {
            this.counts = counts;
        }

        public Object getClassTeacherName() {
            return classTeacherName;
        }

        public void setClassTeacherName(Object classTeacherName) {
            this.classTeacherName = classTeacherName;
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

        public boolean isIsCantry() {
            return isCantry;
        }

        public void setIsCantry(boolean isCantry) {
            this.isCantry = isCantry;
        }

        public double getPrice() {
            return price;
        }

        public void setPrice(double price) {
            this.price = price;
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

        public String getLessonDate() {
            return lessonDate;
        }

        public void setLessonDate(String lessonDate) {
            this.lessonDate = lessonDate;
        }

        public int getCourses() {
            return courses;
        }

        public void setCourses(int courses) {
            this.courses = courses;
        }

        public String getEditUserName() {
            return editUserName;
        }

        public void setEditUserName(String editUserName) {
            this.editUserName = editUserName;
        }

        public Object getTeacherTime() {
            return teacherTime;
        }

        public void setTeacherTime(Object teacherTime) {
            this.teacherTime = teacherTime;
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

        public Object getSigname() {
            return signame;
        }

        public void setSigname(Object signame) {
            this.signame = signame;
        }

        public String getAddUserId() {
            return addUserId;
        }

        public void setAddUserId(String addUserId) {
            this.addUserId = addUserId;
        }

        public boolean isPublished() {
            return published;
        }

        public void setPublished(boolean published) {
            this.published = published;
        }

        public String getResId() {
            return resId;
        }

        public void setResId(String resId) {
            this.resId = resId;
        }

        public String getProvinceId() {
            return provinceId;
        }

        public void setProvinceId(String provinceId) {
            this.provinceId = provinceId;
        }

        public int getCommentCount() {
            return commentCount;
        }

        public void setCommentCount(int commentCount) {
            this.commentCount = commentCount;
        }

        public String getTeacherTimeId() {
            return teacherTimeId;
        }

        public void setTeacherTimeId(String teacherTimeId) {
            this.teacherTimeId = teacherTimeId;
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

        public String getProvinceName() {
            return provinceName;
        }

        public void setProvinceName(String provinceName) {
            this.provinceName = provinceName;
        }

        public String getDescript() {
            return descript;
        }

        public void setDescript(String descript) {
            this.descript = descript;
        }

        public String getRegion() {
            return region;
        }

        public void setRegion(String region) {
            this.region = region;
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

        public String getAreasId() {
            return areasId;
        }

        public void setAreasId(String areasId) {
            this.areasId = areasId;
        }

        public double getRange() {
            return range;
        }

        public void setRange(double range) {
            this.range = range;
        }

        public int getMethodType() {
            return methodType;
        }

        public void setMethodType(int methodType) {
            this.methodType = methodType;
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

        public double getVipPrice() {
            return vipPrice;
        }

        public void setVipPrice(double vipPrice) {
            this.vipPrice = vipPrice;
        }

        public int getDigest() {
            return digest;
        }

        public void setDigest(int digest) {
            this.digest = digest;
        }

        public Object getCategoriesId() {
            return categoriesId;
        }

        public void setCategoriesId(Object categoriesId) {
            this.categoriesId = categoriesId;
        }

        public String getIsAddUserType() {
            return isAddUserType;
        }

        public void setIsAddUserType(String isAddUserType) {
            this.isAddUserType = isAddUserType;
        }

        public int getAllows() {
            return allows;
        }

        public void setAllows(int allows) {
            this.allows = allows;
        }

        public String getSummary() {
            return summary;
        }

        public void setSummary(String summary) {
            this.summary = summary;
        }

        public Object getStar() {
            return star;
        }

        public void setStar(Object star) {
            this.star = star;
        }

        public Object getTeacherName() {
            return teacherName;
        }

        public void setTeacherName(Object teacherName) {
            this.teacherName = teacherName;
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

        public String getPicture() {
            return picture;
        }

        public void setPicture(String picture) {
            this.picture = picture;
        }

        public String getTimelength() {
            return timelength;
        }

        public void setTimelength(String timelength) {
            this.timelength = timelength;
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

        public String getCatagoryName() {
            return catagoryName;
        }

        public void setCatagoryName(String catagoryName) {
            this.catagoryName = catagoryName;
        }

        public Object getLocation() {
            return location;
        }

        public void setLocation(Object location) {
            this.location = location;
        }

        public Object getCustomer() {
            return customer;
        }

        public void setCustomer(Object customer) {
            this.customer = customer;
        }
    }
}
