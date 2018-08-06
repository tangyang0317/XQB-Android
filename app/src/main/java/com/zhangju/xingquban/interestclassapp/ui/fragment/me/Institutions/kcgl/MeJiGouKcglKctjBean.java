package com.zhangju.xingquban.interestclassapp.ui.fragment.me.Institutions.kcgl;

/**
 * Created by zsl on 2017/9/6.
 */

public class MeJiGouKcglKctjBean {

    /**
     * sEcho : 0
     * iTotalRecords : 0
     * iTotalDisplayRecords : 0
     * aaData : {"id":"9546","teacherTimeId":"4","customerId":null,"degreeId":null,"name":"段老师","picture":"http://video.xqban.com/Customer/2017-09-04/1504503366991_149.jpg","categoriesId":null,"price":null,"vipPrice":null,"resId":null,"method":"家教上门","methodType":2,"isCantry":true,"counts":0,"allows":10,"descript":"这个是课程课程课程","star":null,"lessonDate":"2017-09-06","timelength":"100分钟","region":null,"courses":0,"summary":"课程简介","catagoryName":null,"orgName":null,"teacherName":null,"removed":0,"addUserId":"12776","addUserName":"bbb(13411111111)","addUserTime":"2017-09-06 10:16:21","editUserId":"12776","editUserName":"bbb(13411111111)","editUserTime":"2017-09-06 10:16:21","isAddUserType":"customer","isEditUserType":"customer","published":true,"sorts":0,"display":1,"digest":0,"classRoom":null,"classTeacherName":null,"teacherTime":null,"customer":null,"res":null,"canBeginClass":false,"canOverClass":false,"clist":null,"ordersDetail":null,"del":false}
     * attachData : null
     * total : null
     * page : null
     * success : true
     * isLogin : true
     * errMsg : {}
     * isAdmin : false
     * cId : 12776
     * cname : 13411111111
     * time : 2017-09-06 10:16:21:181
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
