package com.zhangju.xingquban.interestclassapp.bean.UserBean;

import com.zhangju.xingquban.interestclassapp.base.BaseBean;

import java.io.Serializable;
import java.util.List;

/**
 * 项目名称：InterestClassApp
 * Created by Dr. Zhu on 2017/7/12.
 */

public class UserInfoBean extends BaseBean implements Serializable {


    /**
     * sEcho : 1
     * iTotalRecords : 1
     * iTotalDisplayRecords : 1
     * aaData : [{"editUserId":"12776","degreeId":"1","uidCard":"614545854585245","cityId":"310100","isMember":false,"points":null,"password":"E10ADC3949BA59ABBE56E057F20F883E","dnyAddress":"温州市瑞安市蔡宅中路70号","chgDgrCheckId":"795","school":"上海大学","dnyLat":30.290714,"id":"12776","dnyLng":120.04162,"studioOrNot":null,"sorts":0,"lat":31.31156,"editUserName":"hh哥(13411111111)","signame":"累啊","lng":121.395158,"adcode":"310113","addUserId":"1","openid":null,"degree":{"removed":0,"isOrgaliza":false,"nameCn":"教师","isCommon":false,"id":"1","isTeacher":true},"published":true,"degreeAuthId":null,"provinceId":"310000","teacherTimeId":"11014","phone":"13411111111","areasName":"这是地区","contractAuth":0,"provinceName":"上海市","qcAuth":-1,"isEditUserType":"customer","status":2,"accountsId":"","addUserName":"admin","birthday":null,"formatted_address":"上海市金山区","agentId":",","degreeAuth":0,"areasId":"310113","iAmMembers":false,"realnameAuth":0,"countView":700,"dnyGisUpdateTime":"2017-07-01 15:35:49","invalidtimeAboutMember":null,"isDirApp":false,"balances":null,"cityName":"上海市市辖区","shareGetGift":null,"notvirgin":true,"digest":0,"continueSumSignDay":1,"isAddUserType":"staff","contractAuthStr":null,"map":{"formatted_address":"上海市金山区","agentId":",","areasId":"310113","degreeAuth":0,"realnameAuth":0,"cityId":"310100","teachAge":20,"cityName":"上海市市辖区","school":"上海大学","classRoom":"河滩北路243号","id":"11014","lat":31.31156,"lng":121.395158,"IDCard":"614545854585245","signame":"累啊","adcode":"310113","studSkill":null,"sex":"m","realnameAuthId":"57","degreeAuthId":null,"provinceId":"310000","picture":"http://video.xqban.com/Customer/2017-09-18/1505725632340_6.jpg","colDegree":"本科","phone":"13411111111","areasName":"这是地区","location":"121.395158,31.311560","contractAuth":2,"provinceName":"上海市","qcAuth":-1,"username":"hh哥","accountsId":""},"summary":null,"address":"河滩北路243号","studSkill":null,"chgDegreeId":"802","sex":"m","editUserTime":"2017-10-13 19:05:06","display":1,"realnameAuthId":"57","qcAuthId":"60","homeProvinceName":"上海市","picture":"http://video.xqban.com/Customer/2017-09-18/1505725632340_6.jpg","colDegree":"本科","addUserTime":"2017-06-14 18:03:48","removed":0,"sno":"customer_20170614000012776","lastday":0,"location":"121.395158,31.311560","oauthType":null,"username":"hh哥"}]
     * attachData : null
     * total : 1
     * page : 0
     * isLogin : true
     * isAdmin : false
     * cId : 12776
     * cname : 13411111111
     * time : 2017-10-14 16:09:14:977
     */

    private int sEcho;
    private int iTotalRecords;
    private int iTotalDisplayRecords;
    private Object attachData;
    private int total;
    private int page;
    private boolean isLogin;
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

    public boolean isIsLogin() {
        return isLogin;
    }

    public void setIsLogin(boolean isLogin) {
        this.isLogin = isLogin;
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

    public static class AaDataBean {
        /**
         * editUserId : 12776
         * degreeId : 1
         * uidCard : 614545854585245
         * cityId : 310100
         * isMember : false
         * points : null
         * password : E10ADC3949BA59ABBE56E057F20F883E
         * dnyAddress : 温州市瑞安市蔡宅中路70号
         * chgDgrCheckId : 795
         * school : 上海大学
         * dnyLat : 30.290714
         * id : 12776
         * dnyLng : 120.04162
         * studioOrNot : null
         * sorts : 0
         * lat : 31.31156
         * editUserName : hh哥(13411111111)
         * signame : 累啊
         * lng : 121.395158
         * adcode : 310113
         * addUserId : 1
         * openid : null
         * degree : {"removed":0,"isOrgaliza":false,"nameCn":"教师","isCommon":false,"id":"1","isTeacher":true}
         * published : true
         * degreeAuthId : null
         * provinceId : 310000
         * teacherTimeId : 11014
         * phone : 13411111111
         * areasName : 这是地区
         * contractAuth : 0
         * provinceName : 上海市
         * qcAuth : -1
         * isEditUserType : customer
         * status : 2
         * accountsId :
         * addUserName : admin
         * birthday : null
         * formatted_address : 上海市金山区
         * agentId : ,
         * degreeAuth : 0
         * areasId : 310113
         * iAmMembers : false
         * realnameAuth : 0
         * countView : 700
         * dnyGisUpdateTime : 2017-07-01 15:35:49
         * invalidtimeAboutMember : null
         * isDirApp : false
         * balances : null
         * cityName : 上海市市辖区
         * shareGetGift : null
         * notvirgin : true
         * digest : 0
         * continueSumSignDay : 1
         * isAddUserType : staff
         * contractAuthStr : null
         * map : {"formatted_address":"上海市金山区","agentId":",","areasId":"310113","degreeAuth":0,"realnameAuth":0,"cityId":"310100","teachAge":20,"cityName":"上海市市辖区","school":"上海大学","classRoom":"河滩北路243号","id":"11014","lat":31.31156,"lng":121.395158,"IDCard":"614545854585245","signame":"累啊","adcode":"310113","studSkill":null,"sex":"m","realnameAuthId":"57","degreeAuthId":null,"provinceId":"310000","picture":"http://video.xqban.com/Customer/2017-09-18/1505725632340_6.jpg","colDegree":"本科","phone":"13411111111","areasName":"这是地区","location":"121.395158,31.311560","contractAuth":2,"provinceName":"上海市","qcAuth":-1,"username":"hh哥","accountsId":""}
         * summary : null
         * address : 河滩北路243号
         * studSkill : null
         * chgDegreeId : 802
         * sex : m
         * editUserTime : 2017-10-13 19:05:06
         * display : 1
         * realnameAuthId : 57
         * qcAuthId : 60
         * homeProvinceName : 上海市
         * picture : http://video.xqban.com/Customer/2017-09-18/1505725632340_6.jpg
         * colDegree : 本科
         * addUserTime : 2017-06-14 18:03:48
         * removed : 0
         * sno : customer_20170614000012776
         * lastday : 0
         * location : 121.395158,31.311560
         * oauthType : null
         * username : hh哥
         */

        private String editUserId;
        private String degreeId;
        private String uidCard;
        private String cityId;
        private boolean isMember;
        private Object points;
        private String password;
        private String dnyAddress;
        private String chgDgrCheckId;
        private String school;
        private double dnyLat;
        private String id;
        private double dnyLng;
        private Object studioOrNot;
        private int sorts;
        private double lat;
        private String editUserName;
        private String signame;
        private double lng;
        private String adcode;
        private String addUserId;
        private Object openid;
        private DegreeBean degree;
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
        private String addUserName;
        private Object birthday;
        private String formatted_address;
        private String agentId;
        private int degreeAuth;
        private String areasId;
        private boolean iAmMembers;
        private int realnameAuth;
        private int countView;
        private String dnyGisUpdateTime;
        private Object invalidtimeAboutMember;
        private boolean isDirApp;
        private Object balances;
        private String cityName;
        private Object shareGetGift;
        private boolean notvirgin;
        private int digest;
        private int continueSumSignDay;
        private String isAddUserType;
        private Object contractAuthStr;
        private MapBean map;
        private Object summary;
        private String address;
        private Object studSkill;
        private String chgDegreeId;
        private String sex;
        private String editUserTime;
        private int display;
        private String realnameAuthId;
        private String qcAuthId;
        private String homeProvinceName;
        private String picture;
        private String colDegree;
        private String addUserTime;
        private int removed;
        private String sno;
        private int lastday;
        private String location;
        private Object oauthType;
        private String username;

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

        public String getUidCard() {
            return uidCard;
        }

        public void setUidCard(String uidCard) {
            this.uidCard = uidCard;
        }

        public String getCityId() {
            return cityId;
        }

        public void setCityId(String cityId) {
            this.cityId = cityId;
        }

        public boolean isIsMember() {
            return isMember;
        }

        public void setIsMember(boolean isMember) {
            this.isMember = isMember;
        }

        public Object getPoints() {
            return points;
        }

        public void setPoints(Object points) {
            this.points = points;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }

        public String getDnyAddress() {
            return dnyAddress;
        }

        public void setDnyAddress(String dnyAddress) {
            this.dnyAddress = dnyAddress;
        }

        public String getChgDgrCheckId() {
            return chgDgrCheckId;
        }

        public void setChgDgrCheckId(String chgDgrCheckId) {
            this.chgDgrCheckId = chgDgrCheckId;
        }

        public String getSchool() {
            return school;
        }

        public void setSchool(String school) {
            this.school = school;
        }

        public double getDnyLat() {
            return dnyLat;
        }

        public void setDnyLat(double dnyLat) {
            this.dnyLat = dnyLat;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public double getDnyLng() {
            return dnyLng;
        }

        public void setDnyLng(double dnyLng) {
            this.dnyLng = dnyLng;
        }

        public Object getStudioOrNot() {
            return studioOrNot;
        }

        public void setStudioOrNot(Object studioOrNot) {
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

        public DegreeBean getDegree() {
            return degree;
        }

        public void setDegree(DegreeBean degree) {
            this.degree = degree;
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

        public String getAddUserName() {
            return addUserName;
        }

        public void setAddUserName(String addUserName) {
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

        public int getCountView() {
            return countView;
        }

        public void setCountView(int countView) {
            this.countView = countView;
        }

        public String getDnyGisUpdateTime() {
            return dnyGisUpdateTime;
        }

        public void setDnyGisUpdateTime(String dnyGisUpdateTime) {
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

        public Object getBalances() {
            return balances;
        }

        public void setBalances(Object balances) {
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

        public boolean isNotvirgin() {
            return notvirgin;
        }

        public void setNotvirgin(boolean notvirgin) {
            this.notvirgin = notvirgin;
        }

        public int getDigest() {
            return digest;
        }

        public void setDigest(int digest) {
            this.digest = digest;
        }

        public int getContinueSumSignDay() {
            return continueSumSignDay;
        }

        public void setContinueSumSignDay(int continueSumSignDay) {
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

        public MapBean getMap() {
            return map;
        }

        public void setMap(MapBean map) {
            this.map = map;
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

        public String getSex() {
            return sex;
        }

        public void setSex(String sex) {
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

        public String getRealnameAuthId() {
            return realnameAuthId;
        }

        public void setRealnameAuthId(String realnameAuthId) {
            this.realnameAuthId = realnameAuthId;
        }

        public String getQcAuthId() {
            return qcAuthId;
        }

        public void setQcAuthId(String qcAuthId) {
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

        public String getColDegree() {
            return colDegree;
        }

        public void setColDegree(String colDegree) {
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

        public int getLastday() {
            return lastday;
        }

        public void setLastday(int lastday) {
            this.lastday = lastday;
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

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public static class DegreeBean {
            /**
             * removed : 0
             * isOrgaliza : false
             * nameCn : 教师
             * isCommon : false
             * id : 1
             * isTeacher : true
             */

            private int removed;
            private boolean isOrgaliza;
            private String nameCn;
            private boolean isCommon;
            private String id;
            private boolean isTeacher;

            public int getRemoved() {
                return removed;
            }

            public void setRemoved(int removed) {
                this.removed = removed;
            }

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

            public boolean isIsTeacher() {
                return isTeacher;
            }

            public void setIsTeacher(boolean isTeacher) {
                this.isTeacher = isTeacher;
            }
        }

        public static class MapBean {
            /**
             * formatted_address : 上海市金山区
             * agentId : ,
             * areasId : 310113
             * degreeAuth : 0
             * realnameAuth : 0
             * cityId : 310100
             * teachAge : 20
             * cityName : 上海市市辖区
             * school : 上海大学
             * classRoom : 河滩北路243号
             * id : 11014
             * lat : 31.31156
             * lng : 121.395158
             * IDCard : 614545854585245
             * signame : 累啊
             * adcode : 310113
             * studSkill : null
             * sex : m
             * realnameAuthId : 57
             * degreeAuthId : null
             * provinceId : 310000
             * picture : http://video.xqban.com/Customer/2017-09-18/1505725632340_6.jpg
             * colDegree : 本科
             * phone : 13411111111
             * areasName : 这是地区
             * location : 121.395158,31.311560
             * contractAuth : 2
             * provinceName : 上海市
             * qcAuth : -1
             * username : hh哥
             * accountsId :
             */

            private String formatted_address;
            private String agentId;
            private String areasId;
            private int degreeAuth;
            private int realnameAuth;
            private String cityId;
            private int teachAge;
            private String cityName;
            private String school;
            private String classRoom;
            private String id;
            private double lat;
            private double lng;
            private String IDCard;
            private String signame;
            private String adcode;
            private Object studSkill;
            private String sex;
            private String realnameAuthId;
            private Object degreeAuthId;
            private String provinceId;
            private String picture;
            private String colDegree;
            private String phone;
            private String areasName;
            private String location;
            private int contractAuth;
            private String provinceName;
            private int qcAuth;
            private String username;
            private String accountsId;

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

            public String getCityId() {
                return cityId;
            }

            public void setCityId(String cityId) {
                this.cityId = cityId;
            }

            public int getTeachAge() {
                return teachAge;
            }

            public void setTeachAge(int teachAge) {
                this.teachAge = teachAge;
            }

            public String getCityName() {
                return cityName;
            }

            public void setCityName(String cityName) {
                this.cityName = cityName;
            }

            public String getSchool() {
                return school;
            }

            public void setSchool(String school) {
                this.school = school;
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

            public double getLat() {
                return lat;
            }

            public void setLat(double lat) {
                this.lat = lat;
            }

            public double getLng() {
                return lng;
            }

            public void setLng(double lng) {
                this.lng = lng;
            }

            public String getIDCard() {
                return IDCard;
            }

            public void setIDCard(String IDCard) {
                this.IDCard = IDCard;
            }

            public String getSigname() {
                return signame;
            }

            public void setSigname(String signame) {
                this.signame = signame;
            }

            public String getAdcode() {
                return adcode;
            }

            public void setAdcode(String adcode) {
                this.adcode = adcode;
            }

            public Object getStudSkill() {
                return studSkill;
            }

            public void setStudSkill(Object studSkill) {
                this.studSkill = studSkill;
            }

            public String getSex() {
                return sex;
            }

            public void setSex(String sex) {
                this.sex = sex;
            }

            public String getRealnameAuthId() {
                return realnameAuthId;
            }

            public void setRealnameAuthId(String realnameAuthId) {
                this.realnameAuthId = realnameAuthId;
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

            public String getPicture() {
                return picture;
            }

            public void setPicture(String picture) {
                this.picture = picture;
            }

            public String getColDegree() {
                return colDegree;
            }

            public void setColDegree(String colDegree) {
                this.colDegree = colDegree;
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

            public String getLocation() {
                return location;
            }

            public void setLocation(String location) {
                this.location = location;
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

            public String getUsername() {
                return username;
            }

            public void setUsername(String username) {
                this.username = username;
            }

            public String getAccountsId() {
                return accountsId;
            }

            public void setAccountsId(String accountsId) {
                this.accountsId = accountsId;
            }
        }
    }
}
