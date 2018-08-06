package com.zhangju.xingquban.interestclassapp.ui.fragment.find.lookactivity.bean;

import com.google.gson.Gson;

import java.io.Serializable;
import java.util.List;

/**
 * Created by hqf on 2017/11/22.
 *  找活动--数据列表bean
 */

public class LooklistDataBean implements Serializable{

    /**
     * sEcho : 1
     * iTotalRecords : 3
     * iTotalDisplayRecords : 3
     * aaData : [{"featured":"http://video.xqban.com/Customer/2017-11-16/1510799951813_780.jpg#hbnn你好鱼","notes":"你好我哦","editUserId":"12777","degreeId":"1","collectionNum":0,"counts":0,"cityId":null,"voteChannelType":1,"isApply":false,"price":0,"id":"52","titlePic":"http://video.xqban.com/Customer/2017-11-16/1510799923874_576.jpg","sorts":0,"longitude":120.1487,"atime":"2017-11-16","editUserName":"刘北风(13422222222)","activityNum":0,"addUserId":"12776","published":true,"judgeTime":"已结束","provinceId":null,"catagoriesId":"247","partAge":null,"commentNum":0,"teacherTimeId":"11014","areasName":null,"provinceName":null,"region":null,"isEditUserType":"customer","issueWorks":false,"addUserName":"李老师(13411111111)","sponsor":"竭尽所能","activityExit":false,"areasId":null,"buyNumber":0,"bondPrice":0,"remainNumber":0,"latitude":30.22181,"title":"jns","activitySchedules":[{"time":"2017-09-09 20:15:47","title":"年 my","content":"哦 hi 兔子"}],"partName":2,"partSex":null,"partIn":0,"cityName":null,"clickRate":8,"schedules":"[\n  {\n    \"title\" : \"年 my\",\n    \"time\" : \"2017-09-09 20:15:47\",\n    \"content\" : \"哦 hi 兔子\"\n  }\n]","customerId":"12776","statement":null,"digest":0,"bondNumber":0,"place":"她说精品客栈","isAddUserType":"customer","viprice":0,"FinalTime":"2017-11-17","allows":0,"voteChannel":0,"editUserTime":"2017-11-22 14:48:22","display":1,"partPhone":2,"priceType":0,"activitytype":null,"addUserTime":"2017-11-16 10:40:04","isOverdue":true,"removed":0,"catagoryName":"弦乐类","collectionCounts":0},{"featured":"文本#http://video.xqban.com/Customer/2017-11-15/1510729861105_131.jpg","notes":"一般须知","editUserId":"12777","degreeId":"2","collectionNum":1,"counts":0,"cityId":null,"voteChannelType":1,"isApply":false,"price":0,"id":"51","titlePic":"http://video.xqban.com/Customer/2017-11-15/1510729734298_326.jpg","sorts":0,"longitude":120.2131,"atime":"2017-11-13","editUserName":"刘北风(13422222222)","activityNum":0,"addUserId":"12777","published":true,"judgeTime":"活动进行中","provinceId":null,"catagoriesId":null,"partAge":null,"commentNum":0,"teacherTimeId":"11015","areasName":null,"provinceName":null,"region":null,"isEditUserType":"customer","issueWorks":false,"addUserName":"刘北风(13422222222)","sponsor":"测试方","activityExit":false,"areasId":null,"buyNumber":0,"bondPrice":0,"remainNumber":0,"latitude":30.291,"title":"新活动","activitySchedules":[{"time":"2017-09-09 20:15:47","title":"行程标题","content":"行程内容"}],"partName":2,"partSex":null,"partIn":0,"cityName":null,"clickRate":7,"schedules":"[\n  {\n    \"title\" : \"行程标题\",\n    \"time\" : \"2017-09-09 20:15:47\",\n    \"content\" : \"行程内容\"\n  }\n]","customerId":"12777","statement":null,"digest":0,"bondNumber":0,"place":"杭州东站","isAddUserType":"customer","viprice":0,"FinalTime":"2017-12-08","allows":0,"voteChannel":0,"editUserTime":"2017-11-22 14:48:30","display":1,"partPhone":2,"priceType":0,"activitytype":null,"addUserTime":"2017-11-15 15:11:56","isOverdue":false,"removed":0,"catagoryName":null,"collectionCounts":0},{"featured":"http://video.xqban.com/Customer/2017-11-13/1510576165348_330.jpg#翻云覆雨愚夫愚妇风言风语的一份#http://video.xqban.com/Customer/2017-11-13/1510576178764_369.jpg","notes":"v vv 不男男女女分发给何处合成愁","editUserId":"16097","degreeId":"2","collectionNum":1,"counts":10,"cityId":null,"voteChannelType":2,"isApply":false,"price":100,"id":"50","titlePic":"http://video.xqban.com/Customer/2017-11-13/1510575961393_395.jpg","sorts":0,"longitude":121.4832,"atime":"2017-11-13","editUserName":"(13400000000)","activityNum":0,"addUserId":"16102","published":true,"judgeTime":"已结束","provinceId":null,"catagoriesId":"27","partAge":null,"commentNum":0,"teacherTimeId":"12436","areasName":null,"provinceName":null,"region":null,"isEditUserType":"customer","issueWorks":false,"addUserName":"8888(18666650022)","sponsor":"兴趣班","activityExit":false,"areasId":null,"buyNumber":1,"bondPrice":10,"remainNumber":0,"latitude":31.28943,"title":"跑步","activitySchedules":[],"partName":2,"partSex":null,"partIn":2,"cityName":null,"clickRate":14,"schedules":"[\n  {\n    \"title\" : \"出发\",\n    \"time\" : \"2017-11-15\",\n    \"content\" : \"给 v 好怀念你们看看看看看看看完发广告\"\n  },\n  {\n    \"title\" : \"改改改改改改\",\n    \"time\" : \"2017-09-09 20:15:47\",\n    \"content\" : \"许 v f 给广告 v\"\n  },\n  {\n    \"title\" : \"结束\",\n    \"time\" : \"2017-09-09 20:15:47\",\n    \"content\" : \"回程\"\n  }\n]","customerId":"16102","statement":null,"digest":0,"bondNumber":10,"place":"大柏树(地铁站)","isAddUserType":"customer","viprice":0,"FinalTime":"2017-11-15","allows":1000,"voteChannel":2,"editUserTime":"2017-11-22 10:26:19","display":1,"partPhone":2,"priceType":2,"activitytype":null,"addUserTime":"2017-11-13 20:31:28","isOverdue":true,"removed":0,"catagoryName":"舞蹈","collectionCounts":0}]
     * attachData : null
     * total : 1
     * page : 0
     * success : true
     * isLogin : true
     * errMsg : {}
     * isAdmin : false
     * cId : 12777
     * cname : 13422222222
     * time : 2017-11-22 14:57:34:565
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

    public static LooklistDataBean objectFromData(String str) {

        return new Gson().fromJson(str, LooklistDataBean.class);
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

    public static class AaDataBean implements Serializable{
        /**
         * featured : http://video.xqban.com/Customer/2017-11-16/1510799951813_780.jpg#hbnn你好鱼
         * notes : 你好我哦
         * editUserId : 12777
         * degreeId : 1
         * collectionNum : 0
         * counts : 0
         * cityId : null
         * voteChannelType : 1
         * isApply : false
         * price : 0
         * id : 52
         * titlePic : http://video.xqban.com/Customer/2017-11-16/1510799923874_576.jpg
         * sorts : 0
         * longitude : 120.1487
         * atime : 2017-11-16
         * editUserName : 刘北风(13422222222)
         * activityNum : 0
         * addUserId : 12776
         * published : true
         * judgeTime : 已结束
         * provinceId : null
         * catagoriesId : 247
         * partAge : null
         * commentNum : 0
         * teacherTimeId : 11014
         * areasName : null
         * provinceName : null
         * region : null
         * isEditUserType : customer
         * issueWorks : false
         * addUserName : 李老师(13411111111)
         * sponsor : 竭尽所能
         * activityExit : false
         * areasId : null
         * buyNumber : 0
         * bondPrice : 0
         * remainNumber : 0
         * latitude : 30.22181
         * title : jns
         * activitySchedules : [{"time":"2017-09-09 20:15:47","title":"年 my","content":"哦 hi 兔子"}]
         * partName : 2
         * partSex : null
         * partIn : 0
         * cityName : null
         * clickRate : 8
         * schedules : [
         {
         "title" : "年 my",
         "time" : "2017-09-09 20:15:47",
         "content" : "哦 hi 兔子"
         }
         ]
         * customerId : 12776
         * statement : null
         * digest : 0
         * bondNumber : 0
         * place : 她说精品客栈
         * isAddUserType : customer
         * viprice : 0
         * FinalTime : 2017-11-17
         * allows : 0
         * voteChannel : 0
         * editUserTime : 2017-11-22 14:48:22
         * display : 1
         * partPhone : 2
         * priceType : 0
         * activitytype : null
         * addUserTime : 2017-11-16 10:40:04
         * isOverdue : true
         * removed : 0
         * catagoryName : 弦乐类
         * collectionCounts : 0
         */

        private String featured;
        private String notes;
        private String editUserId;
        private String degreeId;
        private int collectionNum;
        private int counts;
        private Object cityId;
        private int voteChannelType;
        private boolean isApply;
        private int price;
        private String id;
        private String titlePic;
        private int sorts;
        private double longitude;
        private String atime;
        private String editUserName;
        private int activityNum;
        private String addUserId;
        private boolean published;
        private String judgeTime;
        private Object provinceId;
        private String catagoriesId;
        private Object partAge;
        private int commentNum;
        private String teacherTimeId;
        private Object areasName;
        private Object provinceName;
        private Object region;
        private String isEditUserType;
        private boolean issueWorks;
        private String addUserName;
        private String sponsor;
        private boolean activityExit;
        private Object areasId;
        private int buyNumber;
        private int bondPrice;
        private int remainNumber;
        private double latitude;
        private String title;
        private int partName;
        private Object partSex;
        private int partIn;
        private Object cityName;
        private int clickRate;
        private String schedules;
        private String customerId;
        private Object statement;
        private int digest;
        private int bondNumber;
        private String place;
        private String isAddUserType;
        private int viprice;
        private String FinalTime;
        private int allows;
        private int voteChannel;
        private String editUserTime;
        private int display;
        private int partPhone;
        private int priceType;
        private Object activitytype;
        private String addUserTime;
        private boolean isOverdue;
        private int removed;
        private String catagoryName;
        private int collectionCounts;
        private List<ActivitySchedulesBean> activitySchedules;

        public static AaDataBean objectFromData(String str) {

            return new Gson().fromJson(str, AaDataBean.class);
        }

        public String getFeatured() {
            return featured;
        }

        public void setFeatured(String featured) {
            this.featured = featured;
        }

        public String getNotes() {
            return notes;
        }

        public void setNotes(String notes) {
            this.notes = notes;
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

        public int getCounts() {
            return counts;
        }

        public void setCounts(int counts) {
            this.counts = counts;
        }

        public Object getCityId() {
            return cityId;
        }

        public void setCityId(Object cityId) {
            this.cityId = cityId;
        }

        public int getVoteChannelType() {
            return voteChannelType;
        }

        public void setVoteChannelType(int voteChannelType) {
            this.voteChannelType = voteChannelType;
        }

        public boolean isIsApply() {
            return isApply;
        }

        public void setIsApply(boolean isApply) {
            this.isApply = isApply;
        }

        public int getPrice() {
            return price;
        }

        public void setPrice(int price) {
            this.price = price;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getTitlePic() {
            return titlePic;
        }

        public void setTitlePic(String titlePic) {
            this.titlePic = titlePic;
        }

        public int getSorts() {
            return sorts;
        }

        public void setSorts(int sorts) {
            this.sorts = sorts;
        }

        public double getLongitude() {
            return longitude;
        }

        public void setLongitude(double longitude) {
            this.longitude = longitude;
        }

        public String getAtime() {
            return atime;
        }

        public void setAtime(String atime) {
            this.atime = atime;
        }

        public String getEditUserName() {
            return editUserName;
        }

        public void setEditUserName(String editUserName) {
            this.editUserName = editUserName;
        }

        public int getActivityNum() {
            return activityNum;
        }

        public void setActivityNum(int activityNum) {
            this.activityNum = activityNum;
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

        public String getJudgeTime() {
            return judgeTime;
        }

        public void setJudgeTime(String judgeTime) {
            this.judgeTime = judgeTime;
        }

        public Object getProvinceId() {
            return provinceId;
        }

        public void setProvinceId(Object provinceId) {
            this.provinceId = provinceId;
        }

        public String getCatagoriesId() {
            return catagoriesId;
        }

        public void setCatagoriesId(String catagoriesId) {
            this.catagoriesId = catagoriesId;
        }

        public Object getPartAge() {
            return partAge;
        }

        public void setPartAge(Object partAge) {
            this.partAge = partAge;
        }

        public int getCommentNum() {
            return commentNum;
        }

        public void setCommentNum(int commentNum) {
            this.commentNum = commentNum;
        }

        public String getTeacherTimeId() {
            return teacherTimeId;
        }

        public void setTeacherTimeId(String teacherTimeId) {
            this.teacherTimeId = teacherTimeId;
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

        public Object getRegion() {
            return region;
        }

        public void setRegion(Object region) {
            this.region = region;
        }

        public String getIsEditUserType() {
            return isEditUserType;
        }

        public void setIsEditUserType(String isEditUserType) {
            this.isEditUserType = isEditUserType;
        }

        public boolean isIssueWorks() {
            return issueWorks;
        }

        public void setIssueWorks(boolean issueWorks) {
            this.issueWorks = issueWorks;
        }

        public String getAddUserName() {
            return addUserName;
        }

        public void setAddUserName(String addUserName) {
            this.addUserName = addUserName;
        }

        public String getSponsor() {
            return sponsor;
        }

        public void setSponsor(String sponsor) {
            this.sponsor = sponsor;
        }

        public boolean isActivityExit() {
            return activityExit;
        }

        public void setActivityExit(boolean activityExit) {
            this.activityExit = activityExit;
        }

        public Object getAreasId() {
            return areasId;
        }

        public void setAreasId(Object areasId) {
            this.areasId = areasId;
        }

        public int getBuyNumber() {
            return buyNumber;
        }

        public void setBuyNumber(int buyNumber) {
            this.buyNumber = buyNumber;
        }

        public int getBondPrice() {
            return bondPrice;
        }

        public void setBondPrice(int bondPrice) {
            this.bondPrice = bondPrice;
        }

        public int getRemainNumber() {
            return remainNumber;
        }

        public void setRemainNumber(int remainNumber) {
            this.remainNumber = remainNumber;
        }

        public double getLatitude() {
            return latitude;
        }

        public void setLatitude(double latitude) {
            this.latitude = latitude;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public int getPartName() {
            return partName;
        }

        public void setPartName(int partName) {
            this.partName = partName;
        }

        public Object getPartSex() {
            return partSex;
        }

        public void setPartSex(Object partSex) {
            this.partSex = partSex;
        }

        public int getPartIn() {
            return partIn;
        }

        public void setPartIn(int partIn) {
            this.partIn = partIn;
        }

        public Object getCityName() {
            return cityName;
        }

        public void setCityName(Object cityName) {
            this.cityName = cityName;
        }

        public int getClickRate() {
            return clickRate;
        }

        public void setClickRate(int clickRate) {
            this.clickRate = clickRate;
        }

        public String getSchedules() {
            return schedules;
        }

        public void setSchedules(String schedules) {
            this.schedules = schedules;
        }

        public String getCustomerId() {
            return customerId;
        }

        public void setCustomerId(String customerId) {
            this.customerId = customerId;
        }

        public Object getStatement() {
            return statement;
        }

        public void setStatement(Object statement) {
            this.statement = statement;
        }

        public int getDigest() {
            return digest;
        }

        public void setDigest(int digest) {
            this.digest = digest;
        }

        public int getBondNumber() {
            return bondNumber;
        }

        public void setBondNumber(int bondNumber) {
            this.bondNumber = bondNumber;
        }

        public String getPlace() {
            return place;
        }

        public void setPlace(String place) {
            this.place = place;
        }

        public String getIsAddUserType() {
            return isAddUserType;
        }

        public void setIsAddUserType(String isAddUserType) {
            this.isAddUserType = isAddUserType;
        }

        public int getViprice() {
            return viprice;
        }

        public void setViprice(int viprice) {
            this.viprice = viprice;
        }

        public String getFinalTime() {
            return FinalTime;
        }

        public void setFinalTime(String FinalTime) {
            this.FinalTime = FinalTime;
        }

        public int getAllows() {
            return allows;
        }

        public void setAllows(int allows) {
            this.allows = allows;
        }

        public int getVoteChannel() {
            return voteChannel;
        }

        public void setVoteChannel(int voteChannel) {
            this.voteChannel = voteChannel;
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

        public int getPartPhone() {
            return partPhone;
        }

        public void setPartPhone(int partPhone) {
            this.partPhone = partPhone;
        }

        public int getPriceType() {
            return priceType;
        }

        public void setPriceType(int priceType) {
            this.priceType = priceType;
        }

        public Object getActivitytype() {
            return activitytype;
        }

        public void setActivitytype(Object activitytype) {
            this.activitytype = activitytype;
        }

        public String getAddUserTime() {
            return addUserTime;
        }

        public void setAddUserTime(String addUserTime) {
            this.addUserTime = addUserTime;
        }

        public boolean isIsOverdue() {
            return isOverdue;
        }

        public void setIsOverdue(boolean isOverdue) {
            this.isOverdue = isOverdue;
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

        public int getCollectionCounts() {
            return collectionCounts;
        }

        public void setCollectionCounts(int collectionCounts) {
            this.collectionCounts = collectionCounts;
        }

        public List<ActivitySchedulesBean> getActivitySchedules() {
            return activitySchedules;
        }

        public void setActivitySchedules(List<ActivitySchedulesBean> activitySchedules) {
            this.activitySchedules = activitySchedules;
        }

        public static class ActivitySchedulesBean {
            /**
             * time : 2017-09-09 20:15:47
             * title : 年 my
             * content : 哦 hi 兔子
             */

            private String time;
            private String title;
            private String content;

            public static ActivitySchedulesBean objectFromData(String str) {

                return new Gson().fromJson(str, ActivitySchedulesBean.class);
            }

            public String getTime() {
                return time;
            }

            public void setTime(String time) {
                this.time = time;
            }

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public String getContent() {
                return content;
            }

            public void setContent(String content) {
                this.content = content;
            }
        }
    }
}
