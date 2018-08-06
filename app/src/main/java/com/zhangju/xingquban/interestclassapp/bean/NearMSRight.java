package com.zhangju.xingquban.interestclassapp.bean;

import java.util.List;

/**
 * Created by Administrator on 2017/7/11.
 */

public class NearMSRight {


    private int sEcho;
    private int iTotalRecords;
    private int iTotalDisplayRecords;
    private Object attachData;
    private Object total;
    private Object page;
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

    public Object getTotal() {
        return total;
    }

    public void setTotal(Object total) {
        this.total = total;
    }

    public Object getPage() {
        return page;
    }

    public void setPage(Object page) {
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
        @Override
        public String toString() {
            return "AaDataBean{" +
                    "radius=" + radius +
                    ", districtType=" + districtType +
                    ", range=" + range +
                    ", isLeaf=" + isLeaf +
                    ", expanded=" + expanded +
                    ", id='" + id + '\'' +
                    ", removed=" + removed +
                    ", addUserId='" + addUserId + '\'' +
                    ", addUserName='" + addUserName + '\'' +
                    ", addUserTime='" + addUserTime + '\'' +
                    ", editUserId='" + editUserId + '\'' +
                    ", editUserName='" + editUserName + '\'' +
                    ", editUserTime='" + editUserTime + '\'' +
                    ", isAddUserType='" + isAddUserType + '\'' +
                    ", isEditUserType='" + isEditUserType + '\'' +
                    ", published=" + published +
                    ", sorts=" + sorts +
                    ", display=" + display +
                    ", digest=" + digest +
                    ", name='" + name + '\'' +
                    ", parentsId=" + parentsId +
                    ", isRoots=" + isRoots +
                    ", summary=" + summary +
                    ", hots=" + hots +
                    ", catagoriesPicture='" + catagoriesPicture + '\'' +
                    ", showHome=" + showHome +
                    ", showRsSearch=" + showRsSearch +
                    ", showRsSearchSort=" + showRsSearchSort +
                    ", location=" + location +
                    ", json=" + json +
                    ", customerId=" + customerId +
                    ", lng=" + lng +
                    ", lat=" + lat +
                    ", provinceId=" + provinceId +
                    ", cityId=" + cityId +
                    ", areasId=" + areasId +
                    ", formatted_address=" + formatted_address +
                    ", adcode=" + adcode +
                    ", provinceName=" + provinceName +
                    ", cityName=" + cityName +
                    ", areasName=" + areasName +
                    ", del=" + del +
                    ", degreeId=" + degreeId +
                    ", childs=" + childs +
                    '}';
        }

        /**
         * radius : null
         * districtType : null
         * range : -1.0
         * isLeaf : false
         * expanded : false
         * childs : [{"radius":null,"districtType":null,"range":-1,"isLeaf":true,"expanded":false,"childs":[],"id":"96","removed":0,"addUserId":"1","addUserName":"admin","addUserTime":"2016-04-30 09:43:38","editUserId":"1","editUserName":"admin","editUserTime":"2016-07-25 16:03:46","isAddUserType":"staff","isEditUserType":"staff","published":true,"sorts":50,"display":1,"digest":0,"name":"幼儿美术","parentsId":"1","isRoots":false,"summary":null,"hots":0,"catagoriesPicture":null,"showHome":0,"showRsSearch":0,"showRsSearchSort":0,"location":null,"json":null,"customerId":null,"lng":null,"lat":null,"provinceId":null,"cityId":null,"areasId":null,"formatted_address":null,"adcode":null,"provinceName":null,"cityName":null,"areasName":null,"del":false,"degreeId":null},{"radius":null,"districtType":null,"range":-1,"isLeaf":true,"expanded":false,"childs":[],"id":"132","removed":0,"addUserId":"1","addUserName":"admin","addUserTime":"2016-04-30 13:23:34","editUserId":"1","editUserName":"admin","editUserTime":"2016-07-25 16:03:47","isAddUserType":"staff","isEditUserType":"staff","published":true,"sorts":49,"display":1,"digest":0,"name":"儿绘","parentsId":"1","isRoots":false,"summary":null,"hots":0,"catagoriesPicture":null,"showHome":0,"showRsSearch":0,"showRsSearchSort":0,"location":null,"json":null,"customerId":null,"lng":null,"lat":null,"provinceId":null,"cityId":null,"areasId":null,"formatted_address":null,"adcode":null,"provinceName":null,"cityName":null,"areasName":null,"del":false,"degreeId":null},{"radius":null,"districtType":null,"range":-1,"isLeaf":true,"expanded":false,"childs":[],"id":"130","removed":0,"addUserId":"1","addUserName":"admin","addUserTime":"2016-04-30 13:21:34","editUserId":"1","editUserName":"admin","editUserTime":"2016-07-25 16:03:46","isAddUserType":"staff","isEditUserType":"staff","published":true,"sorts":48,"display":1,"digest":0,"name":"素描","parentsId":"1","isRoots":false,"summary":null,"hots":0,"catagoriesPicture":null,"showHome":0,"showRsSearch":0,"showRsSearchSort":0,"location":null,"json":null,"customerId":null,"lng":null,"lat":null,"provinceId":null,"cityId":null,"areasId":null,"formatted_address":null,"adcode":null,"provinceName":null,"cityName":null,"areasName":null,"del":false,"degreeId":null},{"radius":null,"districtType":null,"range":-1,"isLeaf":true,"expanded":false,"childs":[],"id":"133","removed":0,"addUserId":"1","addUserName":"admin","addUserTime":"2016-04-30 13:30:05","editUserId":"1","editUserName":"admin","editUserTime":"2016-07-25 16:03:47","isAddUserType":"staff","isEditUserType":"staff","published":true,"sorts":47,"display":1,"digest":0,"name":"速写","parentsId":"1","isRoots":false,"summary":null,"hots":0,"catagoriesPicture":null,"showHome":0,"showRsSearch":0,"showRsSearchSort":0,"location":null,"json":null,"customerId":null,"lng":null,"lat":null,"provinceId":null,"cityId":null,"areasId":null,"formatted_address":null,"adcode":null,"provinceName":null,"cityName":null,"areasName":null,"del":false,"degreeId":null},{"radius":null,"districtType":null,"range":-1,"isLeaf":true,"expanded":false,"childs":[],"id":"134","removed":0,"addUserId":"1","addUserName":"admin","addUserTime":"2016-04-30 13:30:05","editUserId":"1","editUserName":"admin","editUserTime":"2016-07-25 16:03:47","isAddUserType":"staff","isEditUserType":"staff","published":true,"sorts":46,"display":1,"digest":0,"name":"色彩","parentsId":"1","isRoots":false,"summary":null,"hots":0,"catagoriesPicture":null,"showHome":0,"showRsSearch":0,"showRsSearchSort":0,"location":null,"json":null,"customerId":null,"lng":null,"lat":null,"provinceId":null,"cityId":null,"areasId":null,"formatted_address":null,"adcode":null,"provinceName":null,"cityName":null,"areasName":null,"del":false,"degreeId":null},{"radius":null,"districtType":null,"range":-1,"isLeaf":true,"expanded":false,"childs":[],"id":"77","removed":0,"addUserId":"1","addUserName":"admin","addUserTime":"2016-01-22 18:24:32","editUserId":"1","editUserName":"admin","editUserTime":"2016-07-25 16:03:46","isAddUserType":"staff","isEditUserType":"staff","published":true,"sorts":45,"display":1,"digest":0,"name":"简笔画","parentsId":"1","isRoots":false,"summary":null,"hots":0,"catagoriesPicture":null,"showHome":0,"showRsSearch":0,"showRsSearchSort":0,"location":null,"json":null,"customerId":null,"lng":null,"lat":null,"provinceId":null,"cityId":null,"areasId":null,"formatted_address":null,"adcode":null,"provinceName":null,"cityName":null,"areasName":null,"del":false,"degreeId":null},{"radius":null,"districtType":null,"range":-1,"isLeaf":true,"expanded":false,"childs":[],"id":"165","removed":0,"addUserId":"1","addUserName":"admin","addUserTime":"2016-05-06 14:30:42","editUserId":"1","editUserName":"admin","editUserTime":"2016-07-25 16:03:47","isAddUserType":"staff","isEditUserType":"staff","published":true,"sorts":43,"display":1,"digest":0,"name":"漫画","parentsId":"1","isRoots":false,"summary":null,"hots":0,"catagoriesPicture":null,"showHome":0,"showRsSearch":0,"showRsSearchSort":0,"location":null,"json":null,"customerId":null,"lng":null,"lat":null,"provinceId":null,"cityId":null,"areasId":null,"formatted_address":null,"adcode":null,"provinceName":null,"cityName":null,"areasName":null,"del":false,"degreeId":null},{"radius":null,"districtType":null,"range":-1,"isLeaf":true,"expanded":false,"childs":[],"id":"166","removed":0,"addUserId":"1","addUserName":"admin","addUserTime":"2016-05-06 14:30:42","editUserId":"1","editUserName":"admin","editUserTime":"2016-07-25 16:03:47","isAddUserType":"staff","isEditUserType":"staff","published":true,"sorts":42,"display":1,"digest":0,"name":"连环画","parentsId":"1","isRoots":false,"summary":null,"hots":0,"catagoriesPicture":null,"showHome":0,"showRsSearch":0,"showRsSearchSort":0,"location":null,"json":null,"customerId":null,"lng":null,"lat":null,"provinceId":null,"cityId":null,"areasId":null,"formatted_address":null,"adcode":null,"provinceName":null,"cityName":null,"areasName":null,"del":false,"degreeId":null},{"radius":null,"districtType":null,"range":-1,"isLeaf":true,"expanded":false,"childs":[],"id":"167","removed":0,"addUserId":"1","addUserName":"admin","addUserTime":"2016-05-06 14:30:42","editUserId":"1","editUserName":"admin","editUserTime":"2016-07-25 16:03:47","isAddUserType":"staff","isEditUserType":"staff","published":true,"sorts":41,"display":1,"digest":0,"name":"插图创作","parentsId":"1","isRoots":false,"summary":null,"hots":0,"catagoriesPicture":null,"showHome":0,"showRsSearch":0,"showRsSearchSort":0,"location":null,"json":null,"customerId":null,"lng":null,"lat":null,"provinceId":null,"cityId":null,"areasId":null,"formatted_address":null,"adcode":null,"provinceName":null,"cityName":null,"areasName":null,"del":false,"degreeId":null},{"radius":null,"districtType":null,"range":-1,"isLeaf":true,"expanded":false,"childs":[],"id":"344","removed":0,"addUserId":"1","addUserName":"admin","addUserTime":"2016-07-25 16:03:48","editUserId":"1","editUserName":"admin","editUserTime":"2016-07-25 16:03:48","isAddUserType":"staff","isEditUserType":"staff","published":true,"sorts":40,"display":1,"digest":0,"name":"涂鸦","parentsId":"1","isRoots":false,"summary":null,"hots":0,"catagoriesPicture":null,"showHome":0,"showRsSearch":0,"showRsSearchSort":0,"location":null,"json":null,"customerId":null,"lng":null,"lat":null,"provinceId":null,"cityId":null,"areasId":null,"formatted_address":null,"adcode":null,"provinceName":null,"cityName":null,"areasName":null,"del":false,"degreeId":null},{"radius":null,"districtType":null,"range":-1,"isLeaf":true,"expanded":false,"childs":[],"id":"131","removed":0,"addUserId":"1","addUserName":"admin","addUserTime":"2016-04-30 13:21:34","editUserId":"1","editUserName":"admin","editUserTime":"2016-07-25 16:03:46","isAddUserType":"staff","isEditUserType":"staff","published":true,"sorts":40,"display":1,"digest":0,"name":"动画","parentsId":"1","isRoots":false,"summary":null,"hots":0,"catagoriesPicture":null,"showHome":0,"showRsSearch":0,"showRsSearchSort":0,"location":null,"json":null,"customerId":null,"lng":null,"lat":null,"provinceId":null,"cityId":null,"areasId":null,"formatted_address":null,"adcode":null,"provinceName":null,"cityName":null,"areasName":null,"del":false,"degreeId":null},{"radius":null,"districtType":null,"range":-1,"isLeaf":true,"expanded":false,"childs":[],"id":"139","removed":0,"addUserId":"1","addUserName":"admin","addUserTime":"2016-04-30 13:43:57","editUserId":"1","editUserName":"admin","editUserTime":"2016-07-25 16:03:47","isAddUserType":"staff","isEditUserType":"staff","published":true,"sorts":39,"display":1,"digest":0,"name":"国画","parentsId":"1","isRoots":false,"summary":null,"hots":0,"catagoriesPicture":null,"showHome":0,"showRsSearch":0,"showRsSearchSort":0,"location":null,"json":null,"customerId":null,"lng":null,"lat":null,"provinceId":null,"cityId":null,"areasId":null,"formatted_address":null,"adcode":null,"provinceName":null,"cityName":null,"areasName":null,"del":false,"degreeId":null},{"radius":null,"districtType":null,"range":-1,"isLeaf":true,"expanded":false,"childs":[],"id":"164","removed":0,"addUserId":"1","addUserName":"admin","addUserTime":"2016-05-06 14:29:07","editUserId":"1","editUserName":"admin","editUserTime":"2016-07-25 16:03:47","isAddUserType":"staff","isEditUserType":"staff","published":true,"sorts":38,"display":1,"digest":0,"name":"雕塑","parentsId":"1","isRoots":false,"summary":null,"hots":0,"catagoriesPicture":null,"showHome":0,"showRsSearch":0,"showRsSearchSort":0,"location":null,"json":null,"customerId":null,"lng":null,"lat":null,"provinceId":null,"cityId":null,"areasId":null,"formatted_address":null,"adcode":null,"provinceName":null,"cityName":null,"areasName":null,"del":false,"degreeId":null}]
         * id : 1
         * removed : 0
         * addUserId : 1
         * addUserName : admin
         * addUserTime : 2015-12-02 19:17:00
         * editUserId : 1
         * editUserName : admin
         * editUserTime : 2016-01-26 18:56:40
         * isAddUserType : staff
         * isEditUserType : staff
         * published : true
         * sorts : 9999
         * display : 1
         * digest : 0
         * name : 美术
         * parentsId : null
         * isRoots : false
         * summary : null
         * hots : 0
         * catagoriesPicture : http://video.xqban.com/Catagories/2016-05-25/1464159860624_meishu.png
         * showHome : 0
         * showRsSearch : 0
         * showRsSearchSort : 0
         * location : null
         * json : null
         * customerId : null
         * lng : null
         * lat : null
         * provinceId : null
         * cityId : null
         * areasId : null
         * formatted_address : null
         * adcode : null
         * provinceName : null
         * cityName : null
         * areasName : null
         * del : false
         * degreeId : null
         */

        private Object radius;
        private Object districtType;
        private double range;
        private boolean isLeaf;
        private boolean expanded;
        private String id;
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
        private String name;
        private Object parentsId;
        private boolean isRoots;
        private Object summary;
        private int hots;
        private String catagoriesPicture;
        private int showHome;
        private int showRsSearch;
        private int showRsSearchSort;
        private Object location;
        private Object json;
        private Object customerId;
        private Object lng;
        private Object lat;
        private Object provinceId;
        private Object cityId;
        private Object areasId;
        private Object formatted_address;
        private Object adcode;
        private Object provinceName;
        private Object cityName;
        private Object areasName;
        private boolean del;
        private Object degreeId;
        private List<ChildsBean> childs;

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

        public boolean isIsLeaf() {
            return isLeaf;
        }

        public void setIsLeaf(boolean isLeaf) {
            this.isLeaf = isLeaf;
        }

        public boolean isExpanded() {
            return expanded;
        }

        public void setExpanded(boolean expanded) {
            this.expanded = expanded;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
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

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public Object getParentsId() {
            return parentsId;
        }

        public void setParentsId(Object parentsId) {
            this.parentsId = parentsId;
        }

        public boolean isIsRoots() {
            return isRoots;
        }

        public void setIsRoots(boolean isRoots) {
            this.isRoots = isRoots;
        }

        public Object getSummary() {
            return summary;
        }

        public void setSummary(Object summary) {
            this.summary = summary;
        }

        public int getHots() {
            return hots;
        }

        public void setHots(int hots) {
            this.hots = hots;
        }

        public String getCatagoriesPicture() {
            return catagoriesPicture;
        }

        public void setCatagoriesPicture(String catagoriesPicture) {
            this.catagoriesPicture = catagoriesPicture;
        }

        public int getShowHome() {
            return showHome;
        }

        public void setShowHome(int showHome) {
            this.showHome = showHome;
        }

        public int getShowRsSearch() {
            return showRsSearch;
        }

        public void setShowRsSearch(int showRsSearch) {
            this.showRsSearch = showRsSearch;
        }

        public int getShowRsSearchSort() {
            return showRsSearchSort;
        }

        public void setShowRsSearchSort(int showRsSearchSort) {
            this.showRsSearchSort = showRsSearchSort;
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

        public Object getCustomerId() {
            return customerId;
        }

        public void setCustomerId(Object customerId) {
            this.customerId = customerId;
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

        public Object getAreasId() {
            return areasId;
        }

        public void setAreasId(Object areasId) {
            this.areasId = areasId;
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

        public Object getProvinceName() {
            return provinceName;
        }

        public void setProvinceName(Object provinceName) {
            this.provinceName = provinceName;
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

        public List<ChildsBean> getChilds() {
            return childs;
        }

        public void setChilds(List<ChildsBean> childs) {
            this.childs = childs;
        }

        public static class ChildsBean {

            @Override
            public String toString() {
                return "ChildsBean{" +
                        "radius=" + radius +
                        ", districtType=" + districtType +
                        ", range=" + range +
                        ", isLeaf=" + isLeaf +
                        ", expanded=" + expanded +
                        ", id='" + id + '\'' +
                        ", removed=" + removed +
                        ", addUserId='" + addUserId + '\'' +
                        ", addUserName='" + addUserName + '\'' +
                        ", addUserTime='" + addUserTime + '\'' +
                        ", editUserId='" + editUserId + '\'' +
                        ", editUserName='" + editUserName + '\'' +
                        ", editUserTime='" + editUserTime + '\'' +
                        ", isAddUserType='" + isAddUserType + '\'' +
                        ", isEditUserType='" + isEditUserType + '\'' +
                        ", published=" + published +
                        ", sorts=" + sorts +
                        ", display=" + display +
                        ", digest=" + digest +
                        ", name='" + name + '\'' +
                        ", parentsId='" + parentsId + '\'' +
                        ", isRoots=" + isRoots +
                        ", summary=" + summary +
                        ", hots=" + hots +
                        ", catagoriesPicture=" + catagoriesPicture +
                        ", showHome=" + showHome +
                        ", showRsSearch=" + showRsSearch +
                        ", showRsSearchSort=" + showRsSearchSort +
                        ", location=" + location +
                        ", json=" + json +
                        ", customerId=" + customerId +
                        ", lng=" + lng +
                        ", lat=" + lat +
                        ", provinceId=" + provinceId +
                        ", cityId=" + cityId +
                        ", areasId=" + areasId +
                        ", formatted_address=" + formatted_address +
                        ", adcode=" + adcode +
                        ", provinceName=" + provinceName +
                        ", cityName=" + cityName +
                        ", areasName=" + areasName +
                        ", del=" + del +
                        ", degreeId=" + degreeId +
                        ", childs=" + childs +
                        '}';
            }

            /**
             * radius : null
             * districtType : null
             * range : -1.0
             * isLeaf : true
             * expanded : false
             * childs : []
             * id : 96
             * removed : 0
             * addUserId : 1
             * addUserName : admin
             * addUserTime : 2016-04-30 09:43:38
             * editUserId : 1
             * editUserName : admin
             * editUserTime : 2016-07-25 16:03:46
             * isAddUserType : staff
             * isEditUserType : staff
             * published : true
             * sorts : 50
             * display : 1
             * digest : 0
             * name : 幼儿美术
             * parentsId : 1
             * isRoots : false
             * summary : null
             * hots : 0
             * catagoriesPicture : null
             * showHome : 0
             * showRsSearch : 0
             * showRsSearchSort : 0
             * location : null
             * json : null
             * customerId : null
             * lng : null
             * lat : null
             * provinceId : null
             * cityId : null
             * areasId : null
             * formatted_address : null
             * adcode : null
             * provinceName : null
             * cityName : null
             * areasName : null
             * del : false
             * degreeId : null
             */

            private Object radius;
            private Object districtType;
            private double range;
            private boolean isLeaf;
            private boolean expanded;
            private String id;
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
            private String name;
            private String parentsId;
            private boolean isRoots;
            private Object summary;
            private int hots;
            private Object catagoriesPicture;
            private int showHome;
            private int showRsSearch;
            private int showRsSearchSort;
            private Object location;
            private Object json;
            private Object customerId;
            private Object lng;
            private Object lat;
            private Object provinceId;
            private Object cityId;
            private Object areasId;
            private Object formatted_address;
            private Object adcode;
            private Object provinceName;
            private Object cityName;
            private Object areasName;
            private boolean del;
            private Object degreeId;
            private List<?> childs;

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

            public boolean isIsLeaf() {
                return isLeaf;
            }

            public void setIsLeaf(boolean isLeaf) {
                this.isLeaf = isLeaf;
            }

            public boolean isExpanded() {
                return expanded;
            }

            public void setExpanded(boolean expanded) {
                this.expanded = expanded;
            }

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
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

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getParentsId() {
                return parentsId;
            }

            public void setParentsId(String parentsId) {
                this.parentsId = parentsId;
            }

            public boolean isIsRoots() {
                return isRoots;
            }

            public void setIsRoots(boolean isRoots) {
                this.isRoots = isRoots;
            }

            public Object getSummary() {
                return summary;
            }

            public void setSummary(Object summary) {
                this.summary = summary;
            }

            public int getHots() {
                return hots;
            }

            public void setHots(int hots) {
                this.hots = hots;
            }

            public Object getCatagoriesPicture() {
                return catagoriesPicture;
            }

            public void setCatagoriesPicture(Object catagoriesPicture) {
                this.catagoriesPicture = catagoriesPicture;
            }

            public int getShowHome() {
                return showHome;
            }

            public void setShowHome(int showHome) {
                this.showHome = showHome;
            }

            public int getShowRsSearch() {
                return showRsSearch;
            }

            public void setShowRsSearch(int showRsSearch) {
                this.showRsSearch = showRsSearch;
            }

            public int getShowRsSearchSort() {
                return showRsSearchSort;
            }

            public void setShowRsSearchSort(int showRsSearchSort) {
                this.showRsSearchSort = showRsSearchSort;
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

            public Object getCustomerId() {
                return customerId;
            }

            public void setCustomerId(Object customerId) {
                this.customerId = customerId;
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

            public Object getAreasId() {
                return areasId;
            }

            public void setAreasId(Object areasId) {
                this.areasId = areasId;
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

            public Object getProvinceName() {
                return provinceName;
            }

            public void setProvinceName(Object provinceName) {
                this.provinceName = provinceName;
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

            public List<?> getChilds() {
                return childs;
            }

            public void setChilds(List<?> childs) {
                this.childs = childs;
            }
        }
    }
}
