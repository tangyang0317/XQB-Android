package com.zhangju.xingquban.interestclassapp.ui.fragment.me.Institutions.kcgl;

import java.util.List;

/**
 * Created by zsl on 2017/9/6.
 */

public class MeJiGouKcglKctjCityBean {

    /**
     * sEcho : 1
     * iTotalRecords : 34
     * iTotalDisplayRecords : 34
     * aaData : [{"addrShortPinyin":"bjs","specialAreas":true,"name":"北京市","pid":"100000","id":"110000"},{"addrShortPinyin":"tjs","specialAreas":true,"name":"天津市","pid":"100000","id":"120000"},{"addrShortPinyin":"hbs","specialAreas":false,"name":"河北省","pid":"100000","id":"130000"},{"addrShortPinyin":"sxs","specialAreas":false,"name":"山西省","pid":"100000","id":"140000"},{"addrShortPinyin":"nmgzzq","specialAreas":false,"name":"内蒙古自治区","pid":"100000","id":"150000"},{"addrShortPinyin":"lns","specialAreas":false,"name":"辽宁省","pid":"100000","id":"210000"},{"addrShortPinyin":"jls","specialAreas":false,"name":"吉林省","pid":"100000","id":"220000"},{"addrShortPinyin":"hljs","specialAreas":false,"name":"黑龙江省","pid":"100000","id":"230000"},{"addrShortPinyin":"shs","specialAreas":true,"name":"上海市","pid":"100000","id":"310000"},{"addrShortPinyin":"jss","specialAreas":false,"name":"江苏省","pid":"100000","id":"320000"}]
     * attachData : {"defaultServiceCityId":"310000","defaultServiceCityName":"上海市"}
     * total : 4
     * page : 0
     * success : true
     * isLogin : true
     * errMsg : {}
     * isAdmin : false
     * cId : 12776
     * cname : 13411111111
     * time : 2017-09-06 13:43:16:175
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

    public static class AttachDataBean {
    }

    public static class ErrMsgBean {
    }

    public static class AaDataBean {
        /**
         * addrShortPinyin : bjs
         * specialAreas : true
         * name : 北京市
         * pid : 100000
         * id : 110000
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
