package com.zhangju.xingquban.interestclassapp.ui.fragment.me.Comment;

import java.util.List;

/**
 * Created by zsl on 2017/8/4.
 */

public class MeCommentBean {

    /**
     * sEcho : 1
     * iTotalRecords : 2
     * iTotalDisplayRecords : 2
     * aaData : [{"radius":null,"districtType":null,"range":-1,"isLeaf":null,"expanded":null,"childs":[],"id":"415","parentsId":null,"name":null,"summary":"请输入资源介绍马力是个坏人吗","resourcesId":"6426","resourceTitle":null,"levels":2,"customerId":"12776","customerName":"bbb","customerPicture":"http://video.xqban.com/Customer/2017-09-04/1504493718698_608.jpg","rsCount":0,"commentCount":3,"picString":"http://video.xqban.com/Customer/2017-09-20/1505876690393_227.jpg#http://video.xqban.com/Customer/2017-09-20/1505876695382_688.jpg","picList":["http://video.xqban.com/Customer/2017-09-20/1505876690393_227.jpg","http://video.xqban.com/Customer/2017-09-20/1505876695382_688.jpg"],"removed":0,"addUserId":"12776","addUserName":"bbb(13411111111)","addUserTime":"2017-09-20 11:04:57","editUserId":"12776","editUserName":"bbb(13411111111)","editUserTime":"2017-09-20 11:04:57","isAddUserType":"customer","isEditUserType":"customer","published":true,"sorts":0,"display":1,"digest":0,"teacherTime":null,"location":null,"json":null,"adcode":null,"lng":null,"lat":null,"formatted_address":null,"areasId":null,"areasName":null,"provinceId":null,"provinceName":null,"cityId":null,"cityName":null,"del":false,"degreeId":""},{"radius":null,"districtType":null,"range":-1,"isLeaf":null,"expanded":null,"childs":[],"id":"412","parentsId":null,"name":null,"summary":"请输入资源介绍你好","resourcesId":"6426","resourceTitle":null,"levels":2,"customerId":"12776","customerName":"bbb","customerPicture":"http://video.xqban.com/Customer/2017-09-04/1504493718698_608.jpg","rsCount":0,"commentCount":3,"picString":"http://video.xqban.com/Customer/2017-09-20/1505872378468_586.jpg","picList":["http://video.xqban.com/Customer/2017-09-20/1505872378468_586.jpg"],"removed":0,"addUserId":"12776","addUserName":"bbb(17051062142)","addUserTime":"2017-09-20 09:53:01","editUserId":"12776","editUserName":"bbb(17051062142)","editUserTime":"2017-09-20 09:53:01","isAddUserType":"customer","isEditUserType":"customer","published":true,"sorts":0,"display":1,"digest":0,"teacherTime":null,"location":null,"json":null,"adcode":null,"lng":null,"lat":null,"formatted_address":null,"areasId":null,"areasName":null,"provinceId":null,"provinceName":null,"cityId":null,"cityName":null,"del":false,"degreeId":""}]
     * attachData : null
     * total : 1
     * page : 0
     * success : true
     * isLogin : false
     * errMsg : {}
     * isAdmin : false
     * cId : null
     * cname : null
     * time : 2017-09-21 09:56:45:580
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

    public static class ErrMsgBean {
    }

    public static class AaDataBean {
        /**
         * radius : null
         * districtType : null
         * range : -1
         * isLeaf : null
         * expanded : null
         * childs : []
         * id : 415
         * parentsId : null
         * name : null
         * summary : 请输入资源介绍马力是个坏人吗
         * resourcesId : 6426
         * resourceTitle : null
         * levels : 2
         * customerId : 12776
         * customerName : bbb
         * customerPicture : http://video.xqban.com/Customer/2017-09-04/1504493718698_608.jpg
         * rsCount : 0
         * commentCount : 3
         * picString : http://video.xqban.com/Customer/2017-09-20/1505876690393_227.jpg#http://video.xqban.com/Customer/2017-09-20/1505876695382_688.jpg
         * picList : ["http://video.xqban.com/Customer/2017-09-20/1505876690393_227.jpg","http://video.xqban.com/Customer/2017-09-20/1505876695382_688.jpg"]
         * removed : 0
         * addUserId : 12776
         * addUserName : bbb(13411111111)
         * addUserTime : 2017-09-20 11:04:57
         * editUserId : 12776
         * editUserName : bbb(13411111111)
         * editUserTime : 2017-09-20 11:04:57
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
        private int range;
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

        public int getRange() {
            return range;
        }

        public void setRange(int range) {
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
