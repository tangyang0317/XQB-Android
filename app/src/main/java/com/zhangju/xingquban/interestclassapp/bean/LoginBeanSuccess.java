package com.zhangju.xingquban.interestclassapp.bean;

/**
 * Created by Administrator on 2017/6/22.
 */

public class LoginBeanSuccess {

    @Override
    public String toString() {
        return "LoginBeanSuccess{" +
                "success=" + success +
                ", isLogin=" + isLogin +
                ", errMsg=" + errMsg +
                ", isAdmin=" + isAdmin +
                ", cId='" + cId + '\'' +
                ", cname='" + cname + '\'' +
                ", sEcho=" + sEcho +
                ", iTotalRecords=" + iTotalRecords +
                ", iTotalDisplayRecords=" + iTotalDisplayRecords +
                ", aaData=" + aaData +
                '}';
    }

    /**
     * success : true
     * isLogin : true
     * errMsg : {}
     * isAdmin : false
     * cId : 12887
     * cname : 13716113314
     * sEcho : 0
     * iTotalRecords : 0
     * iTotalDisplayRecords : 0
     * aaData : {"bindingphone":true,"customerId":"12887","token":"eyJhbGciOiJIUzI1NiJ9.eyJ0YWIiOiJjb20uc3R1ZHlpbmcuY20ubW9kZWwuaW1wbC5jdXN0b21lci5DdXN0b21lciIsIm1vZGVsIjoie1wiaWRcIjpcIjEyODg3XCIsXCJzbm9cIjpcImN1c3RvbWVyXzIwMTcwNjIyMDAwMDEyODg3XCIsXCJkZWdyZWVJZFwiOlwiNFwiLFwiaXNEaXJBcHBcIjpmYWxzZSxcInBob25lXCI6XCIxMzcxNjExMzMxNFwiLFwicGFzc3dvcmRcIjpcIkUxMEFEQzM5NDlCQTU5QUJCRTU2RTA1N0YyMEY4ODNFXCIsXCJyZWFsbmFtZUF1dGhcIjowLFwiZGVncmVlQXV0aFwiOjAsXCJzdGF0dXNcIjowLFwiaUFtTWVtYmVyc1wiOmZhbHNlLFwibm90dmlyZ2luXCI6ZmFsc2UsXCJyZW1vdmVkXCI6MCxcImFkZFVzZXJUaW1lXCI6XCIyMDE3LTA2LTIyIDIyOjM4OjU5XCIsXCJlZGl0VXNlclRpbWVcIjpcIjIwMTctMDYtMjIgMjI6Mzg6NTlcIixcImlzQWRkVXNlclR5cGVcIjpcInN5c1wiLFwiaXNFZGl0VXNlclR5cGVcIjpcInN5c1wiLFwicHVibGlzaGVkXCI6dHJ1ZSxcInNvcnRzXCI6MCxcImRpc3BsYXlcIjoxLFwiZGlnZXN0XCI6MCxcInJhbmdlXCI6LTEuMCxcImlEaXNwbGF5U3RhcnRcIjowLFwiaURpc3BsYXlMZW5ndGhcIjoxMCxcInNFY2hvXCI6MSxcImN1cnBhZ2VcIjowLFwicm93c1wiOjEwLFwiaXNBZG1pblwiOmZhbHNlfSIsImlkIjoiMTI4ODciLCJleHAiOjE0OTkxODA1MDh9.SCqHFzsgr2PD8HFyItarIPzw8ceKB0ccNCtn0TDTsPQ"}
     */

    private boolean success;
    private boolean isLogin;
    private ErrMsgBean errMsg;
    private boolean isAdmin;
    private String cId;
    private String cname;
    private int sEcho;
    private int iTotalRecords;
    private int iTotalDisplayRecords;
    private AaDataBean aaData;

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

    public static class ErrMsgBean {
    }

    public static class AaDataBean {
        /**
         * bindingphone : true
         * customerId : 12887
         * token : eyJhbGciOiJIUzI1NiJ9.eyJ0YWIiOiJjb20uc3R1ZHlpbmcuY20ubW9kZWwuaW1wbC5jdXN0b21lci5DdXN0b21lciIsIm1vZGVsIjoie1wiaWRcIjpcIjEyODg3XCIsXCJzbm9cIjpcImN1c3RvbWVyXzIwMTcwNjIyMDAwMDEyODg3XCIsXCJkZWdyZWVJZFwiOlwiNFwiLFwiaXNEaXJBcHBcIjpmYWxzZSxcInBob25lXCI6XCIxMzcxNjExMzMxNFwiLFwicGFzc3dvcmRcIjpcIkUxMEFEQzM5NDlCQTU5QUJCRTU2RTA1N0YyMEY4ODNFXCIsXCJyZWFsbmFtZUF1dGhcIjowLFwiZGVncmVlQXV0aFwiOjAsXCJzdGF0dXNcIjowLFwiaUFtTWVtYmVyc1wiOmZhbHNlLFwibm90dmlyZ2luXCI6ZmFsc2UsXCJyZW1vdmVkXCI6MCxcImFkZFVzZXJUaW1lXCI6XCIyMDE3LTA2LTIyIDIyOjM4OjU5XCIsXCJlZGl0VXNlclRpbWVcIjpcIjIwMTctMDYtMjIgMjI6Mzg6NTlcIixcImlzQWRkVXNlclR5cGVcIjpcInN5c1wiLFwiaXNFZGl0VXNlclR5cGVcIjpcInN5c1wiLFwicHVibGlzaGVkXCI6dHJ1ZSxcInNvcnRzXCI6MCxcImRpc3BsYXlcIjoxLFwiZGlnZXN0XCI6MCxcInJhbmdlXCI6LTEuMCxcImlEaXNwbGF5U3RhcnRcIjowLFwiaURpc3BsYXlMZW5ndGhcIjoxMCxcInNFY2hvXCI6MSxcImN1cnBhZ2VcIjowLFwicm93c1wiOjEwLFwiaXNBZG1pblwiOmZhbHNlfSIsImlkIjoiMTI4ODciLCJleHAiOjE0OTkxODA1MDh9.SCqHFzsgr2PD8HFyItarIPzw8ceKB0ccNCtn0TDTsPQ
         */

        private boolean bindingphone;
        private String customerId;
        private String token;

        public boolean isBindingphone() {
            return bindingphone;
        }

        public void setBindingphone(boolean bindingphone) {
            this.bindingphone = bindingphone;
        }

        public String getCustomerId() {
            return customerId;
        }

        public void setCustomerId(String customerId) {
            this.customerId = customerId;
        }

        public String getToken() {
            return token;
        }

        public void setToken(String token) {
            this.token = token;
        }
    }
}
