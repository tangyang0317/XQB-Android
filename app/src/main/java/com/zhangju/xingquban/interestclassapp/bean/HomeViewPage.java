package com.zhangju.xingquban.interestclassapp.bean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Administrator on 2017/7/4.
 */

public class HomeViewPage implements Serializable {

    @Override
    public String toString() {
        return "HomeViewPage{" +
                "sEcho=" + sEcho +
                ", iTotalRecords=" + iTotalRecords +
                ", iTotalDisplayRecords=" + iTotalDisplayRecords +
                ", attachData=" + attachData +
                ", total=" + total +
                ", page=" + page +
                ", success=" + success +
                ", isLogin=" + isLogin +
                ", errMsg=" + errMsg +
                ", isAdmin=" + isAdmin +
                ", cId=" + cId +
                ", cname=" + cname +
                ", time='" + time + '\'' +
                ", aaData=" + aaData +
                '}';
    }

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

    public static class ErrMsgBean implements Serializable {
    }

    public static class AaDataBean implements Serializable {

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

        public List<?> getChilds() {
            return childs;
        }

        public void setChilds(List<?> childs) {
            this.childs = childs;
        }
    }
}
