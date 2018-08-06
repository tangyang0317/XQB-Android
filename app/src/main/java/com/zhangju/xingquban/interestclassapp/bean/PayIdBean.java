package com.zhangju.xingquban.interestclassapp.bean;

import com.google.gson.Gson;

import java.io.Serializable;
import java.util.List;

/**
 * Created by ydw on 2017/11/28.
 */

public class PayIdBean implements Serializable {

    /**
     * id : 924
     * sno : 935393339052658688
     * customerId : 12777
     * teacherTimeId : 11014
     * teacherCustomerId : 12776
     * pdtNum : 1
     * amount : 1
     * payAmount : 1
     * resume : null
     * orderType : 4
     * sellerNum : 1
     * memberPrice : 1
     * status : 0
     * applyName : 刘北风
     * bondPrice : 0
     * payed : false
     * ordersPayId : null
     * isCommented : 0
     * paytypeId : null
     * paytypeName : null
     * payTime : null
     * confirmDate : null
     * genQcode : false
     * qcodeNum : 0
     * scanQcodeNum : 0
     * scanQcodeStatus : 0
     * firstScanTime : null
     * lastScanTime : null
     * useCoupon : false
     * useCouponid : null
     * phone : 13422222222
     * currencyId : null
     * expireStaus : 0
     * expireNum : 0
     * activityId : 41894
     * ordersPay : null
     * couponNum : 0
     * removed : 0
     * addUserId : 12777
     * addUserName : 刘北风(13422222222)
     * addUserTime : 2017-11-28 14:22:05
     * editUserId : 12777
     * editUserName : 刘北风(13422222222)
     * editUserTime : 2017-11-28 14:22:05
     * isAddUserType : customer
     * isEditUserType : customer
     * published : true
     * sorts : 0
     * display : 1
     * digest : 0
     * dlist : [{"id":"919","ordersId":"924","customerId":"12777","teacherTimeId":"11014","teacherCustomerId":"12776","lessonName":"女寝","lessonImg":"http://video.xqban.com/Customer/2017-11-27/1511786537626_436.jpg","lessonId":null,"activityTitle":null,"activityImg":null,"activityId":null,"resourceId":"41894","organVideoId":null,"useVipPrice":false,"price":1,"counts":1,"amount":1,"isCommented":false,"commentCount":0,"qcodeNum":0,"scanQcodeNum":0,"scanQcodeStatus":0,"firstScanTime":null,"lastScanTime":null,"expireStaus":0,"expireNum":0,"teacherTime":null,"contactUser":null,"contactTel":null,"activity":null,"organVideo":null,"resources":null,"lesson":{"radius":null,"districtType":null,"range":-1,"id":"919","teacherTimeId":"452","customerId":"426","degreeId":"1","name":"黑板报","picture":"#picture","categoriesId":"71","price":400,"vipPrice":400,"resId":null,"method":"学生上门","methodType":1,"isCantry":null,"counts":0,"allows":0,"descript":"师生关系融洽，不但能活跃课堂气氛，而且能调动起学生的极大热情去参与教学。因此要保持师生关系的和谐，就要建立良好的师生关系。首先，教师要尊重学生、信任学生。特别是对那些学困生，更要尊重和信任他们，平等相处，诚恳相待，以情动人，用情感的钥匙打开学生的心扉","star":0,"lessonDate":null,"timelength":"0","region":"","areasId":"310104","provinceId":"310000","cityId":"310000","cityName":null,"areasName":null,"provinceName":null,"lng":121.422462,"lat":31.191501,"location":null,"courses":0,"summary":"暂时无法提供课程详细情况希望与老师面议","catagoryName":"声乐","orgName":null,"teacherName":"张老师","removed":0,"addUserId":null,"addUserName":null,"addUserTime":null,"editUserId":null,"editUserName":null,"editUserTime":null,"isAddUserType":null,"isEditUserType":null,"published":true,"sorts":0,"display":1,"digest":0,"classRoom":"","classTeacherName":null,"teacherTime":null,"customer":null,"res":null,"canBeginClass":false,"canOverClass":false,"clist":null,"ordersDetail":null,"commentCount":0,"score":0,"json":null,"adcode":null,"formatted_address":null,"del":false},"orders":null,"toCustomer":null,"customer":null,"removed":0,"addUserId":"12777","addUserName":"刘北风(13422222222)","addUserTime":"2017-11-28 14:22:05","editUserId":"12777","editUserName":"刘北风(13422222222)","editUserTime":"2017-11-28 14:22:05","isAddUserType":"customer","isEditUserType":"customer","published":true,"sorts":0,"display":1,"digest":0,"del":false,"degreeId":""}]
     * customer : null
     * ordersDetail : null
     * del : false
     * degreeId :
     */

    private String id;
    private String sno;
    private String customerId;
    private String teacherTimeId;
    private String teacherCustomerId;
    private int pdtNum;


    private Object resume;
    private String orderType;
    private int sellerNum;
    private double memberPrice;
    private int status;
    private String applyName;
    private int bondPrice;
    private boolean payed;
    private Object ordersPayId;
    private int isCommented;
    private Object paytypeId;
    private Object paytypeName;
    private Object payTime;
    private Object confirmDate;
    private boolean genQcode;
    private int qcodeNum;
    private int scanQcodeNum;
    private int scanQcodeStatus;
    private Object firstScanTime;
    private Object lastScanTime;
    private boolean useCoupon;
    private Object useCouponid;
    private String phone;
    private Object currencyId;
    private int expireStaus;
    private int expireNum;
    private String activityId;
    private Object ordersPay;
    private int couponNum;
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
    private Object customer;
    private Object ordersDetail;
    private boolean del;
    private String degreeId;


    public static PayIdBean objectFromData(String str) {

        return new Gson().fromJson(str, PayIdBean.class);
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

    public String getTeacherCustomerId() {
        return teacherCustomerId;
    }

    public void setTeacherCustomerId(String teacherCustomerId) {
        this.teacherCustomerId = teacherCustomerId;
    }

    public int getPdtNum() {
        return pdtNum;
    }

    public void setPdtNum(int pdtNum) {
        this.pdtNum = pdtNum;
    }





    public Object getResume() {
        return resume;
    }

    public void setResume(Object resume) {
        this.resume = resume;
    }

    public String getOrderType() {
        return orderType;
    }

    public void setOrderType(String orderType) {
        this.orderType = orderType;
    }

    public int getSellerNum() {
        return sellerNum;
    }

    public void setSellerNum(int sellerNum) {
        this.sellerNum = sellerNum;
    }

    public double getMemberPrice() {
        return memberPrice;
    }

    public void setMemberPrice(double memberPrice) {
        this.memberPrice = memberPrice;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getApplyName() {
        return applyName;
    }

    public void setApplyName(String applyName) {
        this.applyName = applyName;
    }

    public int getBondPrice() {
        return bondPrice;
    }

    public void setBondPrice(int bondPrice) {
        this.bondPrice = bondPrice;
    }

    public boolean isPayed() {
        return payed;
    }

    public void setPayed(boolean payed) {
        this.payed = payed;
    }

    public Object getOrdersPayId() {
        return ordersPayId;
    }

    public void setOrdersPayId(Object ordersPayId) {
        this.ordersPayId = ordersPayId;
    }

    public int getIsCommented() {
        return isCommented;
    }

    public void setIsCommented(int isCommented) {
        this.isCommented = isCommented;
    }

    public Object getPaytypeId() {
        return paytypeId;
    }

    public void setPaytypeId(Object paytypeId) {
        this.paytypeId = paytypeId;
    }

    public Object getPaytypeName() {
        return paytypeName;
    }

    public void setPaytypeName(Object paytypeName) {
        this.paytypeName = paytypeName;
    }

    public Object getPayTime() {
        return payTime;
    }

    public void setPayTime(Object payTime) {
        this.payTime = payTime;
    }

    public Object getConfirmDate() {
        return confirmDate;
    }

    public void setConfirmDate(Object confirmDate) {
        this.confirmDate = confirmDate;
    }

    public boolean isGenQcode() {
        return genQcode;
    }

    public void setGenQcode(boolean genQcode) {
        this.genQcode = genQcode;
    }

    public int getQcodeNum() {
        return qcodeNum;
    }

    public void setQcodeNum(int qcodeNum) {
        this.qcodeNum = qcodeNum;
    }

    public int getScanQcodeNum() {
        return scanQcodeNum;
    }

    public void setScanQcodeNum(int scanQcodeNum) {
        this.scanQcodeNum = scanQcodeNum;
    }

    public int getScanQcodeStatus() {
        return scanQcodeStatus;
    }

    public void setScanQcodeStatus(int scanQcodeStatus) {
        this.scanQcodeStatus = scanQcodeStatus;
    }

    public Object getFirstScanTime() {
        return firstScanTime;
    }

    public void setFirstScanTime(Object firstScanTime) {
        this.firstScanTime = firstScanTime;
    }

    public Object getLastScanTime() {
        return lastScanTime;
    }

    public void setLastScanTime(Object lastScanTime) {
        this.lastScanTime = lastScanTime;
    }

    public boolean isUseCoupon() {
        return useCoupon;
    }

    public void setUseCoupon(boolean useCoupon) {
        this.useCoupon = useCoupon;
    }

    public Object getUseCouponid() {
        return useCouponid;
    }

    public void setUseCouponid(Object useCouponid) {
        this.useCouponid = useCouponid;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Object getCurrencyId() {
        return currencyId;
    }

    public void setCurrencyId(Object currencyId) {
        this.currencyId = currencyId;
    }

    public int getExpireStaus() {
        return expireStaus;
    }

    public void setExpireStaus(int expireStaus) {
        this.expireStaus = expireStaus;
    }

    public int getExpireNum() {
        return expireNum;
    }

    public void setExpireNum(int expireNum) {
        this.expireNum = expireNum;
    }

    public String getActivityId() {
        return activityId;
    }

    public void setActivityId(String activityId) {
        this.activityId = activityId;
    }

    public Object getOrdersPay() {
        return ordersPay;
    }

    public void setOrdersPay(Object ordersPay) {
        this.ordersPay = ordersPay;
    }

    public int getCouponNum() {
        return couponNum;
    }

    public void setCouponNum(int couponNum) {
        this.couponNum = couponNum;
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

    public Object getCustomer() {
        return customer;
    }

    public void setCustomer(Object customer) {
        this.customer = customer;
    }

    public Object getOrdersDetail() {
        return ordersDetail;
    }

    public void setOrdersDetail(Object ordersDetail) {
        this.ordersDetail = ordersDetail;
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





    public static class DlistBean {
        /**
         * id : 919
         * ordersId : 924
         * customerId : 12777
         * teacherTimeId : 11014
         * teacherCustomerId : 12776
         * lessonName : 女寝
         * lessonImg : http://video.xqban.com/Customer/2017-11-27/1511786537626_436.jpg
         * lessonId : null
         * activityTitle : null
         * activityImg : null
         * activityId : null
         * resourceId : 41894
         * organVideoId : null
         * useVipPrice : false
         * price : 1
         * counts : 1
         * amount : 1
         * isCommented : false
         * commentCount : 0
         * qcodeNum : 0
         * scanQcodeNum : 0
         * scanQcodeStatus : 0
         * firstScanTime : null
         * lastScanTime : null
         * expireStaus : 0
         * expireNum : 0
         * teacherTime : null
         * contactUser : null
         * contactTel : null
         * activity : null
         * organVideo : null
         * resources : null
         * lesson : {"radius":null,"districtType":null,"range":-1,"id":"919","teacherTimeId":"452","customerId":"426","degreeId":"1","name":"黑板报","picture":"#picture","categoriesId":"71","price":400,"vipPrice":400,"resId":null,"method":"学生上门","methodType":1,"isCantry":null,"counts":0,"allows":0,"descript":"师生关系融洽，不但能活跃课堂气氛，而且能调动起学生的极大热情去参与教学。因此要保持师生关系的和谐，就要建立良好的师生关系。首先，教师要尊重学生、信任学生。特别是对那些学困生，更要尊重和信任他们，平等相处，诚恳相待，以情动人，用情感的钥匙打开学生的心扉","star":0,"lessonDate":null,"timelength":"0","region":"","areasId":"310104","provinceId":"310000","cityId":"310000","cityName":null,"areasName":null,"provinceName":null,"lng":121.422462,"lat":31.191501,"location":null,"courses":0,"summary":"暂时无法提供课程详细情况希望与老师面议","catagoryName":"声乐","orgName":null,"teacherName":"张老师","removed":0,"addUserId":null,"addUserName":null,"addUserTime":null,"editUserId":null,"editUserName":null,"editUserTime":null,"isAddUserType":null,"isEditUserType":null,"published":true,"sorts":0,"display":1,"digest":0,"classRoom":"","classTeacherName":null,"teacherTime":null,"customer":null,"res":null,"canBeginClass":false,"canOverClass":false,"clist":null,"ordersDetail":null,"commentCount":0,"score":0,"json":null,"adcode":null,"formatted_address":null,"del":false}
         * orders : null
         * toCustomer : null
         * customer : null
         * removed : 0
         * addUserId : 12777
         * addUserName : 刘北风(13422222222)
         * addUserTime : 2017-11-28 14:22:05
         * editUserId : 12777
         * editUserName : 刘北风(13422222222)
         * editUserTime : 2017-11-28 14:22:05
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
        private String ordersId;
        private String customerId;
        private String teacherTimeId;
        private String teacherCustomerId;
        private String lessonName;
        private String lessonImg;
        private Object lessonId;
        private Object activityTitle;
        private Object activityImg;
        private Object activityId;
        private String resourceId;
        private Object organVideoId;
        private boolean useVipPrice;
        private int price;
        private int counts;
        private int amount;
        private boolean isCommented;
        private int commentCount;
        private int qcodeNum;
        private int scanQcodeNum;
        private int scanQcodeStatus;
        private Object firstScanTime;
        private Object lastScanTime;
        private int expireStaus;
        private int expireNum;
        private Object teacherTime;
        private Object contactUser;
        private Object contactTel;
        private Object activity;
        private Object organVideo;
        private Object resources;
        private LessonBean lesson;
        private Object orders;
        private Object toCustomer;
        private Object customer;
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

        public static DlistBean objectFromData(String str) {

            return new Gson().fromJson(str, DlistBean.class);
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getOrdersId() {
            return ordersId;
        }

        public void setOrdersId(String ordersId) {
            this.ordersId = ordersId;
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

        public String getTeacherCustomerId() {
            return teacherCustomerId;
        }

        public void setTeacherCustomerId(String teacherCustomerId) {
            this.teacherCustomerId = teacherCustomerId;
        }

        public String getLessonName() {
            return lessonName;
        }

        public void setLessonName(String lessonName) {
            this.lessonName = lessonName;
        }

        public String getLessonImg() {
            return lessonImg;
        }

        public void setLessonImg(String lessonImg) {
            this.lessonImg = lessonImg;
        }

        public Object getLessonId() {
            return lessonId;
        }

        public void setLessonId(Object lessonId) {
            this.lessonId = lessonId;
        }

        public Object getActivityTitle() {
            return activityTitle;
        }

        public void setActivityTitle(Object activityTitle) {
            this.activityTitle = activityTitle;
        }

        public Object getActivityImg() {
            return activityImg;
        }

        public void setActivityImg(Object activityImg) {
            this.activityImg = activityImg;
        }

        public Object getActivityId() {
            return activityId;
        }

        public void setActivityId(Object activityId) {
            this.activityId = activityId;
        }

        public String getResourceId() {
            return resourceId;
        }

        public void setResourceId(String resourceId) {
            this.resourceId = resourceId;
        }

        public Object getOrganVideoId() {
            return organVideoId;
        }

        public void setOrganVideoId(Object organVideoId) {
            this.organVideoId = organVideoId;
        }

        public boolean isUseVipPrice() {
            return useVipPrice;
        }

        public void setUseVipPrice(boolean useVipPrice) {
            this.useVipPrice = useVipPrice;
        }

        public int getPrice() {
            return price;
        }

        public void setPrice(int price) {
            this.price = price;
        }

        public int getCounts() {
            return counts;
        }

        public void setCounts(int counts) {
            this.counts = counts;
        }

        public int getAmount() {
            return amount;
        }

        public void setAmount(int amount) {
            this.amount = amount;
        }

        public boolean isIsCommented() {
            return isCommented;
        }

        public void setIsCommented(boolean isCommented) {
            this.isCommented = isCommented;
        }

        public int getCommentCount() {
            return commentCount;
        }

        public void setCommentCount(int commentCount) {
            this.commentCount = commentCount;
        }

        public int getQcodeNum() {
            return qcodeNum;
        }

        public void setQcodeNum(int qcodeNum) {
            this.qcodeNum = qcodeNum;
        }

        public int getScanQcodeNum() {
            return scanQcodeNum;
        }

        public void setScanQcodeNum(int scanQcodeNum) {
            this.scanQcodeNum = scanQcodeNum;
        }

        public int getScanQcodeStatus() {
            return scanQcodeStatus;
        }

        public void setScanQcodeStatus(int scanQcodeStatus) {
            this.scanQcodeStatus = scanQcodeStatus;
        }

        public Object getFirstScanTime() {
            return firstScanTime;
        }

        public void setFirstScanTime(Object firstScanTime) {
            this.firstScanTime = firstScanTime;
        }

        public Object getLastScanTime() {
            return lastScanTime;
        }

        public void setLastScanTime(Object lastScanTime) {
            this.lastScanTime = lastScanTime;
        }

        public int getExpireStaus() {
            return expireStaus;
        }

        public void setExpireStaus(int expireStaus) {
            this.expireStaus = expireStaus;
        }

        public int getExpireNum() {
            return expireNum;
        }

        public void setExpireNum(int expireNum) {
            this.expireNum = expireNum;
        }

        public Object getTeacherTime() {
            return teacherTime;
        }

        public void setTeacherTime(Object teacherTime) {
            this.teacherTime = teacherTime;
        }

        public Object getContactUser() {
            return contactUser;
        }

        public void setContactUser(Object contactUser) {
            this.contactUser = contactUser;
        }

        public Object getContactTel() {
            return contactTel;
        }

        public void setContactTel(Object contactTel) {
            this.contactTel = contactTel;
        }

        public Object getActivity() {
            return activity;
        }

        public void setActivity(Object activity) {
            this.activity = activity;
        }

        public Object getOrganVideo() {
            return organVideo;
        }

        public void setOrganVideo(Object organVideo) {
            this.organVideo = organVideo;
        }

        public Object getResources() {
            return resources;
        }

        public void setResources(Object resources) {
            this.resources = resources;
        }

        public LessonBean getLesson() {
            return lesson;
        }

        public void setLesson(LessonBean lesson) {
            this.lesson = lesson;
        }

        public Object getOrders() {
            return orders;
        }

        public void setOrders(Object orders) {
            this.orders = orders;
        }

        public Object getToCustomer() {
            return toCustomer;
        }

        public void setToCustomer(Object toCustomer) {
            this.toCustomer = toCustomer;
        }

        public Object getCustomer() {
            return customer;
        }

        public void setCustomer(Object customer) {
            this.customer = customer;
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

        public static class LessonBean {
            /**
             * radius : null
             * districtType : null
             * range : -1
             * id : 919
             * teacherTimeId : 452
             * customerId : 426
             * degreeId : 1
             * name : 黑板报
             * picture : #picture
             * categoriesId : 71
             * price : 400
             * vipPrice : 400
             * resId : null
             * method : 学生上门
             * methodType : 1
             * isCantry : null
             * counts : 0
             * allows : 0
             * descript : 师生关系融洽，不但能活跃课堂气氛，而且能调动起学生的极大热情去参与教学。因此要保持师生关系的和谐，就要建立良好的师生关系。首先，教师要尊重学生、信任学生。特别是对那些学困生，更要尊重和信任他们，平等相处，诚恳相待，以情动人，用情感的钥匙打开学生的心扉
             * star : 0
             * lessonDate : null
             * timelength : 0
             * region :
             * areasId : 310104
             * provinceId : 310000
             * cityId : 310000
             * cityName : null
             * areasName : null
             * provinceName : null
             * lng : 121.422462
             * lat : 31.191501
             * location : null
             * courses : 0
             * summary : 暂时无法提供课程详细情况希望与老师面议
             * catagoryName : 声乐
             * orgName : null
             * teacherName : 张老师
             * removed : 0
             * addUserId : null
             * addUserName : null
             * addUserTime : null
             * editUserId : null
             * editUserName : null
             * editUserTime : null
             * isAddUserType : null
             * isEditUserType : null
             * published : true
             * sorts : 0
             * display : 1
             * digest : 0
             * classRoom :
             * classTeacherName : null
             * teacherTime : null
             * customer : null
             * res : null
             * canBeginClass : false
             * canOverClass : false
             * clist : null
             * ordersDetail : null
             * commentCount : 0
             * score : 0
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
            private int price;
            private int vipPrice;
            private Object resId;
            private String method;
            private int methodType;
            private Object isCantry;
            private int counts;
            private int allows;
            private String descript;
            private int star;
            private Object lessonDate;
            private String timelength;
            private String region;
            private String areasId;
            private String provinceId;
            private String cityId;
            private Object cityName;
            private Object areasName;
            private Object provinceName;
            private double lng;
            private double lat;
            private Object location;
            private int courses;
            private String summary;
            private String catagoryName;
            private Object orgName;
            private String teacherName;
            private int removed;
            private Object addUserId;
            private Object addUserName;
            private Object addUserTime;
            private Object editUserId;
            private Object editUserName;
            private Object editUserTime;
            private Object isAddUserType;
            private Object isEditUserType;
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
            private int commentCount;
            private int score;
            private Object json;
            private Object adcode;
            private Object formatted_address;
            private boolean del;

            public static LessonBean objectFromData(String str) {

                return new Gson().fromJson(str, LessonBean.class);
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

            public int getPrice() {
                return price;
            }

            public void setPrice(int price) {
                this.price = price;
            }

            public int getVipPrice() {
                return vipPrice;
            }

            public void setVipPrice(int vipPrice) {
                this.vipPrice = vipPrice;
            }

            public Object getResId() {
                return resId;
            }

            public void setResId(Object resId) {
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

            public Object getIsCantry() {
                return isCantry;
            }

            public void setIsCantry(Object isCantry) {
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

            public int getStar() {
                return star;
            }

            public void setStar(int star) {
                this.star = star;
            }

            public Object getLessonDate() {
                return lessonDate;
            }

            public void setLessonDate(Object lessonDate) {
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

            public String getTeacherName() {
                return teacherName;
            }

            public void setTeacherName(String teacherName) {
                this.teacherName = teacherName;
            }

            public int getRemoved() {
                return removed;
            }

            public void setRemoved(int removed) {
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

            public int getCommentCount() {
                return commentCount;
            }

            public void setCommentCount(int commentCount) {
                this.commentCount = commentCount;
            }

            public int getScore() {
                return score;
            }

            public void setScore(int score) {
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
    }
}
