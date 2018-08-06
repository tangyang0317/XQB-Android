package com.zhangju.xingquban.interestclassapp.bean;

import com.google.gson.Gson;

import java.io.Serializable;
import java.util.List;

/**
 * Created by ydw on 2017/11/22.
 */

public class CityConvertBean implements Serializable {

    /**
     * sEcho : 0
     * iTotalRecords : 0
     * iTotalDisplayRecords : 0
     * aaData : {"formatted_address":"杭州市余杭区荆长大道西溪金座","garbageCity":{"name":"上海市","id":"310000"},"specialAreas":false,"districtes":{"radius":null,"districtType":null,"range":-1,"isLeaf":null,"expanded":null,"childs":[],"id":"330100","pid":"330000","adcode":"330100","center":"120.153576,30.287459","citycode":"\"0571\"","level":"city","name":"杭州市","lng":120,"lat":30,"pname":"浙江省","addrPinyin":"hang,zhou,shi","addrShortPinyin":"hzs","pAddrShortPinyin":"zjs","rtnData":true,"removed":0,"addUserId":null,"addUserName":null,"specialAreas":false,"defaultServiceCity":"310000","defaultServiceCityName":"上海市","addUserTime":null,"editUserId":"1","editUserName":"admin","editUserTime":"2017-03-24 17:01:19","isAddUserType":null,"isEditUserType":"sys","published":true,"sorts":0,"display":1,"digest":0,"parents":null,"isOnly":true,"location":null,"customerId":null,"json":null,"parentsId":"330000","formatted_address":null,"areasId":null,"areasName":null,"provinceId":null,"provinceName":null,"cityId":null,"cityName":null,"openService":false,"del":false,"degreeId":""},"addressComponent":{"province":"浙江省","city":"杭州市","citycode":"0571","district":"余杭区","township":"五常街道","adcode":"330110","building":{"name":null,"type":null},"streetNumber":{"street":"荆长大道","number":"758号"}}}
     * attachData : null
     * total : null
     * page : null
     * success : true
     * isLogin : true
     * errMsg : {}
     * isAdmin : false
     * cId : 12777
     * cname : 13422222222
     * time : 2017-11-22 17:14:25:395
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


    public static class ErrMsgBean implements Serializable{

    }

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

    public boolean isLogin() {
        return isLogin;
    }

    public void setLogin(boolean login) {
        isLogin = login;
    }

    public boolean isAdmin() {
        return isAdmin;
    }

    public void setAdmin(boolean admin) {
        isAdmin = admin;
    }

    public String getcId() {
        return cId;
    }

    public void setcId(String cId) {
        this.cId = cId;
    }

    public static CityConvertBean objectFromData(String str) {

        return new Gson().fromJson(str, CityConvertBean.class);
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

    public static class AaDataBean implements Serializable {

        private Districtes districtes;

        public Districtes getAddressComponent() {
            return districtes;
        }

        public void setAddressComponent(Districtes addressComponent) {
            this.districtes = addressComponent;
        }

        public static class Districtes implements Serializable{
            private String adcode;

            public String getAdcode() {
                return adcode;
            }

            public void setAdcode(String adcode) {
                this.adcode = adcode;
            }
        }



    }
}
