package com.zhangju.xingquban.interestclassapp.bean.UserBean;

/**
 * Created by zgl on 2017/11/13.
 */

public class SecondCommend {

    /**
     * sEcho : 0
     * iTotalRecords : 0
     * iTotalDisplayRecords : 0
     * aaData : {"id":"53","teacherCommentId":"643","levels":0,"customerId":"12776","customerName":"李老师","customerPicture":"http://video.xqban.com/Customer/2017-10-19/1508405601491_435.jpg","picString":null,"summary":"一个","removed":0,"addUserId":"12776","addUserName":"李老师(13411111111)","addUserTime":"2017-11-13 19:49:36","editUserId":"12776","editUserName":"李老师(13411111111)","editUserTime":"2017-11-13 19:49:36","isAddUserType":"customer","isEditUserType":"customer","published":true,"sorts":0,"display":1,"digest":0,"del":false,"degreeId":""}
     * attachData : null
     * total : null
     * page : null
     * success : true
     * isLogin : true
     * errMsg : {}
     * isAdmin : false
     * cId : 12776
     * cname : 13411111111
     * time : 2017-11-13 19:49:36:908
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
    private String cId;
    private String cname;
    private String time;

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

    public static class AaDataBean {
    }

    public static class ErrMsgBean {
    }
}

