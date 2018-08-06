package com.zhangju.xingquban.interestclassapp.bean;

import java.util.List;

/**
 * Created by zsl on 2017/7/10.
 */

public class HomeCityIdBaen {

    @Override
    public String toString() {
        return "HomeCityIdBaen{" +
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

    /**
     * sEcho : 1
     * iTotalRecords : 1
     * iTotalDisplayRecords : 1
     * aaData : [{"addrShortPinyin":"hzs","specialAreas":false,"name":"杭州市","pid":"330000","id":"330100"}]
     * attachData : {"defaultServiceCityId":"310000","defaultServiceCityName":"上海市"}
     * total : 1
     * page : 0
     * success : true
     * isLogin : false
     * errMsg : {}
     * isAdmin : false
     * cId : null
     * cname : null
     * time : 2017-07-12 10:46:29:375
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
         * addrShortPinyin : hzs
         * specialAreas : false
         * name : 杭州市
         * pid : 330000
         * id : 330100
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
