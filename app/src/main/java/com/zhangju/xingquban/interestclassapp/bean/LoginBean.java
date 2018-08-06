package com.zhangju.xingquban.interestclassapp.bean;

/**
 * Created by zsl on 2017/6/22.
 */

public class LoginBean {
    private Data aaData;

    /**
     * success : false
     * isLogin : false
     * errMsg : {"sys":"手机号必填项"}
     * isAdmin : false
     * sEcho : 0
     * iTotalRecords : 0
     * iTotalDisplayRecords : 0
     */


    private boolean success;
    private boolean isLogin;
    private ErrMsgBean errMsg;
    private boolean isAdmin;
    private int sEcho;
    private int iTotalRecords;
    private int iTotalDisplayRecords;

    public Data getAaData() {
        return aaData;
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

    public static class ErrMsgBean {
        /**
         * sys : 手机号必填项
         */

        private String sys;

        public String getSys() {
            return sys;
        }

        public void setSys(String sys) {
            this.sys = sys;
        }
    }
}
