package com.zhangju.xingquban.interestclassapp.bean;

import com.google.gson.Gson;

import java.io.Serializable;

/**
 * Created by ydw on 2017/11/3.
 */
//播放喜欢关注
public class LiveVideoLoveBean implements Serializable {

    /**
     * sEcho : 0
     * iTotalRecords : 0
     * iTotalDisplayRecords : 0
     * aaData : {"sum_b":9,"isAddOp":true,"id":"53","comtClsId":"2","subjectId":"489","customerId":null,"comtName":null,"comtIcon":null,"counts":0,"removed":0,"addUserId":null,"addUserName":null,"addUserTime":"2017-11-03 14:19:32","editUserId":null,"editUserName":null,"editUserTime":"2017-11-03 14:19:32","isAddUserType":"sys","isEditUserType":"sys","published":true,"sorts":0,"display":1,"digest":0,"session":"19B284CBA1F60A68DB679FE5644A4C7E","del":false,"degreeId":""}
     * attachData : null
     * total : null
     * page : null
     * success : true
     * isLogin : false
     * errMsg : {}
     * isAdmin : false
     * cId : null
     * cname : null
     * time : 2017-11-03 14:19:32:988
     */

    private int sEcho;
    private int iTotalRecords;
    private int iTotalDisplayRecords;
    private AaDataBean aaData;
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

    public static LiveVideoLoveBean objectFromData(String str) {

        return new Gson().fromJson(str, LiveVideoLoveBean.class);
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

    public AaDataBean getAaData() {
        return aaData;
    }

    public void setAaData(AaDataBean aaData) {
        this.aaData = aaData;
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

    public static class AaDataBean {

        private int sum_b;
        private Object isAddOp;
        private String id;
        private String comtClsId;

        public Object getIsAddOp() {
            return isAddOp;
        }

        public void setIsAddOp(Object isAddOp) {
            this.isAddOp = isAddOp;
        }

        public int getSum_b() {
            return sum_b;
        }

        public void setSum_b(int sum_b) {
            this.sum_b = sum_b;
        }





        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getComtClsId() {
            return comtClsId;
        }

        public void setComtClsId(String comtClsId) {
            this.comtClsId = comtClsId;
        }
    }

    public static class ErrMsgBean {
        public static ErrMsgBean objectFromData(String str) {

            return new Gson().fromJson(str, ErrMsgBean.class);
        }
    }
}
