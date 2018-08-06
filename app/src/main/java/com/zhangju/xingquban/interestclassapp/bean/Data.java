package com.zhangju.xingquban.interestclassapp.bean;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;


public class Data
        implements Parcelable {

    /**
     *
     */
//    private String  sessionId;
    private String  token;
    private Data    addressComponent;
    private Data    districtes;
    private String  formatted_address;
    private boolean signin;
    private int     signnum;
    private double  points;
    private double  balances;

    private String homeProvinceName;
    private String pname;

    private String          catagoriesId;
    private String          title;
    private String          subtitle;
    private String          author;
    private int             likes;
    private String          summary;
    private String          username;
    private String          nameCn;
    private int             star;
    private ArrayList<Data> pictureList;
    private ArrayList<Data> videoList;
    private ArrayList<Data> audioList;
    private boolean         isTeacher;
    private boolean         isOrgaliza;
    private String          fileName;
    private String          titlePicture;
    private String          authorPicture;
    private String          addUserTime;
    private String          customerId;
    private String          customerName;
    private String          customerPicture;
    private int             commentCounts;
    private ArrayList<Data> childs;
    private Data            degree;
    private String          method;
    private String          school;
    private int             teachAge;
    private String          classRoom;
    private String          signame;
    private String          sex;
    private int             age;
    private double          range;
    private String          gisAddress;
    private String          phone;
    private int             status;
    private String          teacherTimeId;
    private int             realnameAuth;
    private int             qcAuth;
    private int             tqcAuth;
    private int             degreeAuth;
    private double          avgPrice;
    private double          minimumPrice;
    private String          fixDate;
    private String          colDegree;
    private boolean         contractAuth;

    private ArrayList<Data> exes;
    private ArrayList<Data> enves;
    private ArrayList<Data> shows;
    private ArrayList<Data> reses;
    private ArrayList<Data> lessons;


    private double price;
    private String area;
    private String organization;

    private String catagoriesNames;
    private String descript;

    private String contactTel;

    /*
     * 老师是否可约
     */
    private ArrayList<Data> plans;
    private int             rowes;
    private int             weeks;
    /*
     * 订单列表
	 */

    private ArrayList<OrderData> dlist;
    private String               lessonName;
    private double               amount;
    private int                  counts;
    private String               lessonImg;
    private String               sno;
    private Data                 lesson;
    private Data                 customer;

    /*
     * 课程详情
     */
    private Data            teacherTime;
    private double          vipPrice;
    private boolean         isCantry;
    private int             allows;
    private Data            res;
    private String          region;
    private String          lessonDate;
    private String          teacherName;
    private int             courses;
    private String          timelength;
    private int             methodType;
    private String          catagoriesPicture;
    /*
     * 评论
     */
    private int             levels;
    private int             commentCount;
    private ArrayList<Data> comments;
    private int             isCommented;

    /*
     * 收藏
     */
    private int             collectionCounts;
    private String          resourcesId;
    private Data            resources;
    private ArrayList<Data> pictures;
    /*
     * 我的评论
     */
    private String          pasttime;

    /*
     * 活动
     */
    private String          titlePic;
    private String          place;
    private String          atime;
    private String          featured;
    private String          schedules;
    private String          notes;
    private String          statement;
    private double          viprice;
    private ArrayList<Data> photos;
    /**
     * 支付
     */
    private String          sign;
    private String          alert;
    private String          sendTime;

    private ArrayList<Data> cities;
    private ArrayList<Data> areas;
    private String          taughtCourse;
    private Data            map;
    private String          classTeacherName;
    private boolean         studioOrNot;

    /*
     * 实现 序列化
     */
    private String id;
    private String name;
    private String types;
    private String fileUrl;
    private String picture;
    private String srcUrl;
    private String catagoryName;
    private String typeId;
    private String pushInfotype;


    public Data() {
    }

    public Data(String id, String name, String types, String fileUrl, String picture, String catagoryName) {
        super();
        this.id = id;
        this.name = name;
        this.types = types;
        this.fileUrl = fileUrl;
        this.picture = picture;
        this.catagoryName = catagoryName;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(id);
        dest.writeString(name);
        dest.writeString(types);
        dest.writeString(fileUrl);
        dest.writeString(picture);
        dest.writeString(catagoryName);
    }

    public static final Creator<Data> CREATOR = new Creator<Data>() {

        @Override
        public Data createFromParcel(Parcel source) {
            String id = source.readString();
            String name = source.readString();
            String types = source.readString();
            String fileUrl = source.readString();
            String picture = source.readString();
            String catagoryName = source.readString();
            return new Data(id, name, types, fileUrl, picture, catagoryName);
        }

        @Override
        public Data[] newArray(int size) {
            return new Data[size];
        }
    };

    private Boolean notvirgin;    // 优惠券是否被查看
    private boolean isMember;     // 是否是会员

    private Data            organizationData;
    private String          versionNumber;
    private String          uploadUrl;
    private String          cityName;
    private String          areasName;
    private String          pid;
    private int             thumb;
    private int             resourceExit;
    private boolean         specialAreas;
    private Data            garbageCity;
    private int             ordersCount;
    private double          ordersAmount;
    private String          defaultServiceCityId;
    private String          defaultServiceCityName;
    private String          oauthType;
    private String          contactUser;
    private boolean         canBeginClass;
    private boolean         canOverClass;
    private boolean         bindingphone;
    private String          password;
    private int             workState;
    private int             teacherAge;
    private String          pictureurl;
    private ArrayList<Data> changepic;
    private String          url;
    private int             bannerJumpTypes;
    private String          cityId;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public int getBannerJumpTypes() {
        return bannerJumpTypes;
    }

    public void setBannerJumpTypes(int bannerJumpTypes) {
        this.bannerJumpTypes = bannerJumpTypes;
    }

    public String getCityId() {
        return cityId;
    }

    public void setCityId(String cityId) {
        this.cityId = cityId;
    }

    public String getChangeId() {
        return changeId;
    }

    public void setChangeId(String changeId) {
        this.changeId = changeId;
    }

    private String changeId;

    public ArrayList<Data> getChangepic() {
        return changepic;
    }

    public void setChangepic(ArrayList<Data> changepic) {
        this.changepic = changepic;
    }

    public boolean isMember() {
        return isMember;
    }

    public void setMember(boolean member) {
        isMember = member;
    }

    public String getPictureurl() {
        return pictureurl;
    }

    public void setPictureurl(String pictureurl) {
        this.pictureurl = pictureurl;
    }

    public int getTeacherAge() {
        return teacherAge;
    }

    public void setTeacherAge(int teacherAge) {
        this.teacherAge = teacherAge;
    }

    public int getWorkState() {
        return workState;
    }

    public void setWorkState(int workState) {
        this.workState = workState;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isBindingphone() {
        return bindingphone;
    }

    public void setBindingphone(boolean bindingphone) {
        this.bindingphone = bindingphone;
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

    public String getContactUser() {
        return contactUser;
    }

    public void setContactUser(String contactUser) {
        this.contactUser = contactUser;
    }

    public String getOauthType() {
        return oauthType;
    }

    public void setOauthType(String oauthType) {
        this.oauthType = oauthType;
    }

    public String getDefaultServiceCityId() {
        return defaultServiceCityId;
    }

    public void setDefaultServiceCityId(String defaultServiceCityId) {
        this.defaultServiceCityId = defaultServiceCityId;
    }

    public String getDefaultServiceCityName() {
        return defaultServiceCityName;
    }

    public void setDefaultServiceCityName(String defaultServiceCityName) {
        this.defaultServiceCityName = defaultServiceCityName;
    }

    public int getOrdersCount() {
        return ordersCount;
    }

    public void setOrdersCount(int ordersCount) {
        this.ordersCount = ordersCount;
    }

    public double getOrdersAmount() {
        return ordersAmount;
    }

    public void setOrdersAmount(double ordersAmount) {
        this.ordersAmount = ordersAmount;
    }

    public boolean isSpecialAreas() {
        return specialAreas;
    }

    public void setSpecialAreas(boolean specialAreas) {
        this.specialAreas = specialAreas;
    }

    public Data getGarbageCity() {
        return garbageCity;
    }

    public void setGarbageCity(Data garbageCity) {
        this.garbageCity = garbageCity;
    }

    public int getResourceExit() {
        return resourceExit;
    }

    public void setResourceExit(int resourceExit) {
        this.resourceExit = resourceExit;
    }

    public int getThumb() {
        return thumb;
    }

    public void setThumb(int thumb) {
        this.thumb = thumb;
    }

    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid;
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

    public String getVersionNumber() {
        return versionNumber;
    }

    public void setVersionNumber(String versionNumber) {
        this.versionNumber = versionNumber;
    }

    public String getUploadUrl() {
        return uploadUrl;
    }

    public void setUploadUrl(String uploadUrl) {
        this.uploadUrl = uploadUrl;
    }

    public String getCatagoriesPicture() {
        return catagoriesPicture;
    }

    public void setCatagoriesPicture(String catagoriesPicture) {
        this.catagoriesPicture = catagoriesPicture;
    }

    public Data getOrganizationData() {
        return organizationData;
    }

    public void setOrganizationData(Data organizationData) {
        this.organizationData = organizationData;
    }

    public String getFileUrl() {
        return fileUrl;
    }

    public void setFileUrl(String fileUrl) {
        this.fileUrl = fileUrl;
    }

    public String getSrcUrl() {
        return srcUrl;
    }

    public void setSrcUrl(String srcUrl) {
        this.srcUrl = srcUrl;
    }

    public Data getMap() {
        return map;
    }

    public void setMap(Data map) {
        this.map = map;
    }

    public String getTaughtCourse() {
        return taughtCourse;
    }

    public void setTaughtCourse(String taughtCourse) {
        this.taughtCourse = taughtCourse;
    }

    public boolean isContractAuth() {
        return contractAuth;
    }

    public void setContractAuth(boolean contractAuth) {
        this.contractAuth = contractAuth;
    }

    public String getColDegree() {
        return colDegree;
    }

    public void setColDegree(String colDegree) {
        this.colDegree = colDegree;
    }

    public String getFixDate() {
        return fixDate;
    }

    public void setFixDate(String fixDate) {
        this.fixDate = fixDate;
    }

    public ArrayList<Data> getAreas() {
        return areas;
    }

    public void setAreas(ArrayList<Data> areas) {
        this.areas = areas;
    }

    public ArrayList<Data> getCities() {
        return cities;
    }

    public void setCities(ArrayList<Data> cities) {
        this.cities = cities;
    }

    public double getAvgPrice() {
        return avgPrice;
    }

    public void setAvgPrice(double avgPrice) {
        this.avgPrice = avgPrice;
    }

    public String getSendTime() {
        return sendTime;
    }

    public void setSendTime(String sendTime) {
        this.sendTime = sendTime;
    }

    public String getAlert() {
        return alert;
    }

    public void setAlert(String alert) {
        this.alert = alert;
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }

    public ArrayList<Data> getPhotos() {
        return photos;
    }

    public void setPhotos(ArrayList<Data> photos) {
        this.photos = photos;
    }

    public double getViprice() {
        return viprice;
    }

    public void setViprice(double viprice) {
        this.viprice = viprice;
    }

    public String getFeatured() {
        return featured;
    }

    public void setFeatured(String featured) {
        this.featured = featured;
    }

    public String getSchedules() {
        return schedules;
    }

    public void setSchedules(String schedules) {
        this.schedules = schedules;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public String getStatement() {
        return statement;
    }

    public void setStatement(String statement) {
        this.statement = statement;
    }

    public String getAtime() {
        return atime;
    }

    public void setAtime(String atime) {
        this.atime = atime;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public String getTitlePic() {
        return titlePic;
    }

    public void setTitlePic(String titlePic) {
        this.titlePic = titlePic;
    }

    public String getHomeProvinceName() {
        return homeProvinceName;
    }

    public void setHomeProvinceName(String homeProvinceName) {
        this.homeProvinceName = homeProvinceName;
    }

//    public String getSessionId() {
//        return sessionId;
//    }
//
//    public void setSessionId(String sessionId) {
//        this.sessionId = sessionId;
//    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Data getAddressComponent() {
        return addressComponent;
    }

    public void setAddressComponent(Data addressComponent) {
        this.addressComponent = addressComponent;
    }

    public Data getDistrictes() {
        return districtes;
    }

    public void setDistrictes(Data districtes) {
        this.districtes = districtes;
    }

    public String getFormatted_address() {
        return formatted_address;
    }

    public void setFormatted_address(String formatted_address) {
        this.formatted_address = formatted_address;
    }

    public boolean isSignin() {
        return signin;
    }

    public void setSignin(boolean signin) {
        this.signin = signin;
    }

    public int getSignnum() {
        return signnum;
    }

    public void setSignnum(int signnum) {
        this.signnum = signnum;
    }

    public double getPoints() {
        return points;
    }

    public void setPoints(double points) {
        this.points = points;
    }

    public double getBalances() {
        return balances;
    }

    public void setBalances(double balances) {
        this.balances = balances;
    }

    public String getPasttime() {
        return pasttime;
    }

    public void setPasttime(String pasttime) {
        this.pasttime = pasttime;
    }

    public ArrayList<Data> getPictures() {
        return pictures;
    }

    public void setPictures(ArrayList<Data> pictures) {
        this.pictures = pictures;
    }

    public Data getResources() {
        return resources;
    }

    public void setResources(Data resources) {
        this.resources = resources;
    }

    public int getCollectionCounts() {
        return collectionCounts;
    }

    public void setCollectionCounts(int collectionCounts) {
        this.collectionCounts = collectionCounts;
    }

    public String getResourcesId() {
        return resourcesId;
    }

    public void setResourcesId(String resourcesId) {
        this.resourcesId = resourcesId;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getCourses() {
        return courses;
    }

    public void setCourses(int courses) {
        this.courses = courses;
    }

    public String getTeacherName() {
        return teacherName;
    }

    public void setTeacherName(String teacherName) {
        this.teacherName = teacherName;
    }

    public Data getRes() {
        return res;
    }

    public int getIsCommented() {
        return isCommented;
    }

    public void setIsCommented(int isCommented) {
        this.isCommented = isCommented;
    }

    public ArrayList<Data> getComments() {
        return comments;
    }

    public void setComments(ArrayList<Data> comments) {
        this.comments = comments;
    }

    public int getCommentCount() {
        return commentCount;
    }

    public void setCommentCount(int commentCount) {
        this.commentCount = commentCount;
    }

    public int getLevels() {
        return levels;
    }

    public void setLevels(int levels) {
        this.levels = levels;
    }

    public String getContactTel() {
        return contactTel;
    }

    public void setContactTel(String contactTel) {
        this.contactTel = contactTel;
    }

    public Data getCustomer() {
        return customer;
    }

    public void setCustomer(Data customer) {
        this.customer = customer;
    }

    public Data getLesson() {
        return lesson;
    }

    public void setLesson(Data lesson) {
        this.lesson = lesson;
    }

    public String getSno() {
        return sno;
    }

    public void setSno(String sno) {
        this.sno = sno;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getLessonDate() {
        return lessonDate;
    }

    public void setLessonDate(String lessonDate) {
        this.lessonDate = lessonDate;
    }

    public void setRes(Data res) {
        this.res = res;
    }

    public int getAllows() {
        return allows;
    }

    public void setAllows(int allows) {
        this.allows = allows;
    }

    public boolean isIsCantry() {
        return isCantry;
    }

    public void setIsCantry(boolean isCantry) {
        this.isCantry = isCantry;
    }

    public double getVipPrice() {
        return vipPrice;
    }

    public void setVipPrice(double vipPrice) {
        this.vipPrice = vipPrice;
    }

    public Data getTeacherTime() {
        return teacherTime;
    }

    public void setTeacherTime(Data teacherTime) {
        this.teacherTime = teacherTime;
    }

    public String getLessonImg() {
        return lessonImg;
    }

    public void setLessonImg(String lessonImg) {
        this.lessonImg = lessonImg;
    }

    public int getCounts() {
        return counts;
    }

    public void setCounts(int counts) {
        this.counts = counts;
    }

    public String getLessonName() {
        return lessonName;
    }

    public void setLessonName(String lessonName) {
        this.lessonName = lessonName;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public ArrayList<OrderData> getDlist() {
        return dlist;
    }

    public void setDlist(ArrayList<OrderData> dlist) {
        this.dlist = dlist;
    }

    public ArrayList<Data> getPlans() {
        return plans;
    }

    public void setPlans(ArrayList<Data> plans) {
        this.plans = plans;
    }

    public int getRowes() {
        return rowes;
    }

    public void setRowes(int rowes) {
        this.rowes = rowes;
    }

    public int getWeeks() {
        return weeks;
    }

    public void setWeeks(int weeks) {
        this.weeks = weeks;
    }

    public String getDescript() {
        return descript;
    }

    public void setDescript(String descript) {
        this.descript = descript;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public ArrayList<Data> getLessons() {
        return lessons;
    }

    public void setLessons(ArrayList<Data> lessons) {
        this.lessons = lessons;
    }

    public String getCatagoriesNames() {
        return catagoriesNames;
    }

    public void setCatagoriesNames(String catagoriesNames) {
        this.catagoriesNames = catagoriesNames;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getOrganization() {
        return organization;
    }

    public void setOrganization(String organization) {
        this.organization = organization;
    }


    public int getRealnameAuth() {
        return realnameAuth;
    }

    public void setRealnameAuth(int realnameAuth) {
        this.realnameAuth = realnameAuth;
    }

    public int getQcAuth() {
        return qcAuth;
    }

    public void setQcAuth(int qcAuth) {
        this.qcAuth = qcAuth;
    }

    public int getTqcAuth() {
        return tqcAuth;
    }

    public void setTqcAuth(int tqcAuth) {
        this.tqcAuth = tqcAuth;
    }

    public int getDegreeAuth() {
        return degreeAuth;
    }

    public void setDegreeAuth(int degreeAuth) {
        this.degreeAuth = degreeAuth;
    }

    public String getTeacherTimeId() {
        return teacherTimeId;
    }

    public void setTeacherTimeId(String teacherTimeId) {
        this.teacherTimeId = teacherTimeId;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getGisAddress() {
        return gisAddress;
    }

    public void setGisAddress(String gisAddress) {
        this.gisAddress = gisAddress;
    }

    public String getSigname() {
        return signame;
    }

    public void setSigname(String signame) {
        this.signame = signame;
    }

    public String getClassRoom() {
        return classRoom;
    }

    public void setClassRoom(String classRoom) {
        this.classRoom = classRoom;
    }

    public int getTeachAge() {
        return teachAge;
    }

    public void setTeachAge(int teachAge) {
        this.teachAge = teachAge;
    }

    public String getSchool() {
        return school;
    }

    public void setSchool(String school) {
        this.school = school;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public String getCatagoryName() {
        return catagoryName;
    }

    public void setCatagoryName(String catagoryName) {
        this.catagoryName = catagoryName;
    }

    public Data getDegree() {
        return degree;
    }

    public void setDegree(Data degree) {
        this.degree = degree;
    }

    public int getCommentCounts() {
        return commentCounts;
    }

    public void setCommentCounts(int commentCounts) {
        this.commentCounts = commentCounts;
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

    public String getAddUserTime() {
        return addUserTime;
    }

    public void setAddUserTime(String addUserTime) {
        this.addUserTime = addUserTime;
    }

    public String getAuthorPicture() {
        return authorPicture;
    }

    public void setAuthorPicture(String authorPicture) {
        this.authorPicture = authorPicture;
    }

    public String getTitlePicture() {
        return titlePicture;
    }

    public void setTitlePicture(String titlePicture) {
        this.titlePicture = titlePicture;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCatagoriesId() {
        return catagoriesId;
    }

    public void setCatagoriesId(String catagoriesId) {
        this.catagoriesId = catagoriesId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSubtitle() {
        return subtitle;
    }

    public void setSubtitle(String subtitle) {
        this.subtitle = subtitle;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getTypes() {
        return types;
    }

    public void setTypes(String types) {
        this.types = types;
    }

    public int getLikes() {
        return likes;
    }

    public void setLikes(int likes) {
        this.likes = likes;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNameCn() {
        return nameCn;
    }

    public void setNameCn(String nameCn) {
        this.nameCn = nameCn;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public boolean isIsTeacher() {
        return isTeacher;
    }

    public void setIsTeacher(boolean isTeacher) {
        this.isTeacher = isTeacher;
    }

    public boolean isIsOrgaliza() {
        return isOrgaliza;
    }

    public void setIsOrgaliza(boolean isOrgaliza) {
        this.isOrgaliza = isOrgaliza;
    }

    public int getStar() {
        return star;
    }

    public void setStar(int star) {
        this.star = star;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public ArrayList<Data> getPictureList() {
        return pictureList;
    }

    public void setPictureList(ArrayList<Data> pictureList) {
        this.pictureList = pictureList;
    }

    public ArrayList<Data> getVideoList() {
        return videoList;
    }

    public void setVideoList(ArrayList<Data> videoList) {
        this.videoList = videoList;
    }

    public ArrayList<Data> getAudioList() {
        return audioList;
    }

    public void setAudioList(ArrayList<Data> audioList) {
        this.audioList = audioList;
    }

    public ArrayList<Data> getChilds() {
        return childs;
    }

    public void setChilds(ArrayList<Data> childs) {
        this.childs = childs;
    }

    public ArrayList<Data> getExes() {
        return exes;
    }

    public void setExes(ArrayList<Data> exes) {
        this.exes = exes;
    }

    public ArrayList<Data> getEnves() {
        return enves;
    }

    public void setEnves(ArrayList<Data> enves) {
        this.enves = enves;
    }

    public ArrayList<Data> getShows() {
        return shows;
    }

    public void setShows(ArrayList<Data> shows) {
        this.shows = shows;
    }

    public ArrayList<Data> getReses() {
        return reses;
    }

    public void setReses(ArrayList<Data> reses) {
        this.reses = reses;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public double getRange() {
        return range;
    }

    public void setRange(double range) {
        this.range = range;
    }

    public double getMinimumPrice() {
        return minimumPrice;
    }

    public void setMinimumPrice(double minimumPrice) {
        this.minimumPrice = minimumPrice;
    }

    public String getPname() {
        return pname;
    }

    public void setPname(String pname) {
        this.pname = pname;
    }

    public String getClassTeacherName() {
        return classTeacherName;
    }

    public void setClassTeacherName(String classTeacherName) {
        this.classTeacherName = classTeacherName;
    }

    public boolean isStudioOrNot() {
        return studioOrNot;
    }

    public void setStudioOrNot(boolean studioOrNot) {
        this.studioOrNot = studioOrNot;
    }

    public String getTimelength() {
        return timelength;
    }

    public void setTimelength(String timelength) {
        this.timelength = timelength;
    }

    public int getMethodType() {
        return methodType;
    }

    public void setMethodType(int methodType) {
        this.methodType = methodType;
    }

    public void setTypeId(String typeId) {
        this.typeId = typeId;
    }

    public String getTypeId() {
        return typeId;
    }

    public String getPushInfotype() {
        return pushInfotype;
    }

    public void setPushInfotype(String pushInfotype) {
        this.pushInfotype = pushInfotype;
    }

    public Boolean getNotvirgin() {
        return notvirgin;
    }

    public void setNotvirgin(Boolean notvirgin) {
        this.notvirgin = notvirgin;
    }
}
