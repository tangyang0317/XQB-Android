package com.zhangju.xingquban.interestclassapp.bean.near;

import java.util.List;

/**
 * Created by zsl on 2017/7/12.
 */

public class NearDistrictBean {

    /**
     * sEcho : 1
     * iTotalRecords : 17
     * iTotalDisplayRecords : 17
     * aaData : [{"addrShortPinyin":"hpq","specialAreas":true,"name":"黄浦区","pid":"310100","id":"310101"},{"addrShortPinyin":"xhq","specialAreas":true,"name":"徐汇区","pid":"310100","id":"310104"},{"addrShortPinyin":"cnq","specialAreas":true,"name":"长宁区","pid":"310100","id":"310105"},{"addrShortPinyin":"jaq","specialAreas":true,"name":"静安区","pid":"310100","id":"310106"},{"addrShortPinyin":"ptq","specialAreas":true,"name":"普陀区","pid":"310100","id":"310107"},{"addrShortPinyin":"zbq","specialAreas":true,"name":"闸北区","pid":"310100","id":"310108"},{"addrShortPinyin":"hkq","specialAreas":true,"name":"虹口区","pid":"310100","id":"310109"},{"addrShortPinyin":"ypq","specialAreas":true,"name":"杨浦区","pid":"310100","id":"310110"},{"addrShortPinyin":"mxq","specialAreas":true,"name":"闵行区","pid":"310100","id":"310112"},{"addrShortPinyin":"bsq","specialAreas":true,"name":"宝山区","pid":"310100","id":"310113"}]
     * attachData : {"defaultServiceCityId":"310000","defaultServiceCityName":"上海市"}
     * total : 2
     * page : 0
     * success : true
     * isLogin : false
     * errMsg : {}
     * isAdmin : false
     * cId : null
     * cname : null
     * time : 2017-07-12 10:15:33:59
     */

    private int sEcho;
    private int iTotalRecords;
    private int iTotalDisplayRecords;
    private AttachDataBean attachData;
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

    public AttachDataBean getAttachData() {
        return attachData;
    }

    public void setAttachData(AttachDataBean attachData) {
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

    public static class AttachDataBean {
    }

    public static class ErrMsgBean {
    }

    public static class AaDataBean {

        @Override
        public String toString() {
            return "AaDataBean{" +
                    "addrShortPinyin='" + addrShortPinyin + '\'' +
                    ", specialAreas=" + specialAreas +
                    ", name='" + name + '\'' +
                    ", pid='" + pid + '\'' +
                    ", id='" + id + '\'' +
                    '}';
        }

        /**
         * addrShortPinyin : hpq
         * specialAreas : true
         * name : 黄浦区
         * pid : 310100
         * id : 310101
         */

        private String addrShortPinyin;
        private boolean specialAreas;
        private String name;
        private String pid;
        private String id;

        public String getAddrShortPinyin() {
            return addrShortPinyin;
        }

        public void setAddrShortPinyin(String addrShortPinyin) {
            this.addrShortPinyin = addrShortPinyin;
        }

        public boolean isSpecialAreas() {
            return specialAreas;
        }

        public void setSpecialAreas(boolean specialAreas) {
            this.specialAreas = specialAreas;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getPid() {
            return pid;
        }

        public void setPid(String pid) {
            this.pid = pid;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }
    }
}
