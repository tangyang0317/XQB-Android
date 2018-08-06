package com.zhangju.xingquban.interestclassapp.ui.fragment.me.Institutions.kcgl;

import java.util.List;

/**
 * Created by zsl on 2017/9/6.
 */

public class MeJiGouKcglKctjCity2Bean {

    /**
     * sEcho : 1
     * iTotalRecords : 13
     * iTotalDisplayRecords : 13
     * aaData : [{"addrShortPinyin":"hebs","specialAreas":true,"name":"哈尔滨市","pid":"230000","id":"230100"},{"addrShortPinyin":"qqhes","specialAreas":false,"name":"齐齐哈尔市","pid":"230000","id":"230200"},{"addrShortPinyin":"jxs","specialAreas":false,"name":"鸡西市","pid":"230000","id":"230300"},{"addrShortPinyin":"hgs","specialAreas":false,"name":"鹤岗市","pid":"230000","id":"230400"},{"addrShortPinyin":"syss","specialAreas":false,"name":"双鸭山市","pid":"230000","id":"230500"},{"addrShortPinyin":"dqs","specialAreas":false,"name":"大庆市","pid":"230000","id":"230600"},{"addrShortPinyin":"ycs","specialAreas":false,"name":"伊春市","pid":"230000","id":"230700"},{"addrShortPinyin":"jmss","specialAreas":false,"name":"佳木斯市","pid":"230000","id":"230800"},{"addrShortPinyin":"qths","specialAreas":false,"name":"七台河市","pid":"230000","id":"230900"},{"addrShortPinyin":"mdjs","specialAreas":false,"name":"牡丹江市","pid":"230000","id":"231000"}]
     * attachData : {"defaultServiceCityId":"310000","defaultServiceCityName":"上海市"}
     * total : 2
     * page : 0
     * success : true
     * isLogin : true
     * errMsg : {}
     * isAdmin : false
     * cId : 12776
     * cname : 13411111111
     * time : 2017-09-06 14:52:51:66
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
         * addrShortPinyin : hebs
         * specialAreas : true
         * name : 哈尔滨市
         * pid : 230000
         * id : 230100
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
