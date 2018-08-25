package com.zhangju.xingquban.refactoring.entity;

import com.zhangju.xingquban.interestclassapp.bean.NearSubjectBean;

import java.util.List;

/**
 * @packageName com.zhangju.xingquban.refactoring.Entity
 * @FileName BaseResponseBean
 * @Author tangyang
 * @DATE 2018/8/9
 **/
public class BaseResponseBean<T> {

    private int sEcho;
    private int iTotalRecords;
    private int iTotalDisplayRecords;
    private Object attachData;
    private Object total;
    private Object page;
    private boolean success;
    private boolean isLogin;
    private NearSubjectBean.ErrMsgBean errMsg;
    private boolean isAdmin;
    private Object cId;
    private Object cname;
    private String time;
    private T aaData;


    public int getsEcho() {
        return sEcho;
    }

    public void setsEcho(int sEcho) {
        this.sEcho = sEcho;
    }

    public int getiTotalRecords() {
        return iTotalRecords;
    }

    public void setiTotalRecords(int iTotalRecords) {
        this.iTotalRecords = iTotalRecords;
    }

    public int getiTotalDisplayRecords() {
        return iTotalDisplayRecords;
    }

    public void setiTotalDisplayRecords(int iTotalDisplayRecords) {
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

    public boolean isLogin() {
        return isLogin;
    }

    public void setLogin(boolean login) {
        isLogin = login;
    }

    public NearSubjectBean.ErrMsgBean getErrMsg() {
        return errMsg;
    }

    public void setErrMsg(NearSubjectBean.ErrMsgBean errMsg) {
        this.errMsg = errMsg;
    }

    public boolean isAdmin() {
        return isAdmin;
    }

    public void setAdmin(boolean admin) {
        isAdmin = admin;
    }

    public Object getcId() {
        return cId;
    }

    public void setcId(Object cId) {
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

    public T getAaData() {
        return aaData;
    }

    public void setAaData(T aaData) {
        this.aaData = aaData;
    }
}
