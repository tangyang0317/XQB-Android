package com.zhangju.xingquban.interestclassapp.bean;

import com.zhangju.xingquban.interestclassapp.base.BaseBean;

import java.util.List;

/**
 * @Created by  liush on 2018/3/20.
 * @describe ${广告banner}
 */

public class AdBannerBean
        extends BaseBean {


    private boolean    isAdmin;
    private int        sEcho;
    private int        iTotalRecords;
    private int        iTotalDisplayRecords;
    private AaDataBean aaData;

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

    public AaDataBean getAaData() {
        return aaData;
    }

    public void setAaData(AaDataBean aaData) {
        this.aaData = aaData;
    }

    public static class AaDataBean {
        private int            total;
        private List<ListBean> list;

        public int getTotal() {
            return total;
        }

        public void setTotal(int total) {
            this.total = total;
        }

        public List<ListBean> getList() {
            return list;
        }

        public void setList(List<ListBean> list) {
            this.list = list;
        }

        public static class ListBean {

            private int     advertId;
            private String  businessName;
            private String  businessContact;
            private String  businessPhone;
            private int     businessTotal;
            private int     businessOverage;
            private String  advertTitle;
            private int     advertClassification;
            private int     advertType;
            private String  advertKeyWords;
            private int     advertPosition;
            private int     advertChannel;
            private int     advertSubject;
            private String  advertIntroduction;
            private String  advertUrl;
            private int     advertPutInType;
            private int     advertPutInProvince;
            private int     advertPutInCity;
            private String  advertPutInRemark;
            private int     advertReleaseType;
            private String  advertGoToType;
            private String  advertGoToUrl;
            private int     clickTimes;
            private int     isPut;
            private int     status;
            private String  createTime;
            private String  updateTime;
            private int     updateUser;
            private int     iDisplayStart;
            private int     iDisplayLength;
            private int     sEcho;
            private int     curpage;
            private int     rows;
            private boolean isAdmin;
            private String advertGoToSourceId;

            public String getAdvertGoToSourceId() {
                return advertGoToSourceId;
            }

            public void setAdvertGoToSourceId(String advertGoToSourceId) {
                this.advertGoToSourceId = advertGoToSourceId;
            }

            public String getAdvertGoToUrl() {
                return advertGoToUrl;
            }

            public void setAdvertGoToUrl(String advertGoToUrl) {
                this.advertGoToUrl = advertGoToUrl;
            }

            public int getAdvertId() {
                return advertId;
            }

            public void setAdvertId(int advertId) {
                this.advertId = advertId;
            }

            public String getBusinessName() {
                return businessName;
            }

            public void setBusinessName(String businessName) {
                this.businessName = businessName;
            }

            public String getBusinessContact() {
                return businessContact;
            }

            public void setBusinessContact(String businessContact) {
                this.businessContact = businessContact;
            }

            public String getBusinessPhone() {
                return businessPhone;
            }

            public void setBusinessPhone(String businessPhone) {
                this.businessPhone = businessPhone;
            }

            public int getBusinessTotal() {
                return businessTotal;
            }

            public void setBusinessTotal(int businessTotal) {
                this.businessTotal = businessTotal;
            }

            public int getBusinessOverage() {
                return businessOverage;
            }

            public void setBusinessOverage(int businessOverage) {
                this.businessOverage = businessOverage;
            }

            public String getAdvertTitle() {
                return advertTitle;
            }

            public void setAdvertTitle(String advertTitle) {
                this.advertTitle = advertTitle;
            }

            public int getAdvertClassification() {
                return advertClassification;
            }

            public void setAdvertClassification(int advertClassification) {
                this.advertClassification = advertClassification;
            }

            public int getAdvertType() {
                return advertType;
            }

            public void setAdvertType(int advertType) {
                this.advertType = advertType;
            }

            public String getAdvertKeyWords() {
                return advertKeyWords;
            }

            public void setAdvertKeyWords(String advertKeyWords) {
                this.advertKeyWords = advertKeyWords;
            }

            public int getAdvertPosition() {
                return advertPosition;
            }

            public void setAdvertPosition(int advertPosition) {
                this.advertPosition = advertPosition;
            }

            public int getAdvertChannel() {
                return advertChannel;
            }

            public void setAdvertChannel(int advertChannel) {
                this.advertChannel = advertChannel;
            }

            public int getAdvertSubject() {
                return advertSubject;
            }

            public void setAdvertSubject(int advertSubject) {
                this.advertSubject = advertSubject;
            }

            public String getAdvertIntroduction() {
                return advertIntroduction;
            }

            public void setAdvertIntroduction(String advertIntroduction) {
                this.advertIntroduction = advertIntroduction;
            }

            public String getAdvertUrl() {
                return advertUrl;
            }

            public void setAdvertUrl(String advertUrl) {
                this.advertUrl = advertUrl;
            }

            public int getAdvertPutInType() {
                return advertPutInType;
            }

            public void setAdvertPutInType(int advertPutInType) {
                this.advertPutInType = advertPutInType;
            }

            public int getAdvertPutInProvince() {
                return advertPutInProvince;
            }

            public void setAdvertPutInProvince(int advertPutInProvince) {
                this.advertPutInProvince = advertPutInProvince;
            }

            public int getAdvertPutInCity() {
                return advertPutInCity;
            }

            public void setAdvertPutInCity(int advertPutInCity) {
                this.advertPutInCity = advertPutInCity;
            }

            public String getAdvertPutInRemark() {
                return advertPutInRemark;
            }

            public void setAdvertPutInRemark(String advertPutInRemark) {
                this.advertPutInRemark = advertPutInRemark;
            }

            public int getAdvertReleaseType() {
                return advertReleaseType;
            }

            public void setAdvertReleaseType(int advertReleaseType) {
                this.advertReleaseType = advertReleaseType;
            }

            public String  getAdvertGoToType() {
                return advertGoToType;
            }

            public void setAdvertGoToType(String  advertGoToType) {
                this.advertGoToType = advertGoToType;
            }

            public int getClickTimes() {
                return clickTimes;
            }

            public void setClickTimes(int clickTimes) {
                this.clickTimes = clickTimes;
            }

            public int getIsPut() {
                return isPut;
            }

            public void setIsPut(int isPut) {
                this.isPut = isPut;
            }

            public int getStatus() {
                return status;
            }

            public void setStatus(int status) {
                this.status = status;
            }

            public String getCreateTime() {
                return createTime;
            }

            public void setCreateTime(String createTime) {
                this.createTime = createTime;
            }

            public String getUpdateTime() {
                return updateTime;
            }

            public void setUpdateTime(String updateTime) {
                this.updateTime = updateTime;
            }

            public int getUpdateUser() {
                return updateUser;
            }

            public void setUpdateUser(int updateUser) {
                this.updateUser = updateUser;
            }

            public int getIDisplayStart() {
                return iDisplayStart;
            }

            public void setIDisplayStart(int iDisplayStart) {
                this.iDisplayStart = iDisplayStart;
            }

            public int getIDisplayLength() {
                return iDisplayLength;
            }

            public void setIDisplayLength(int iDisplayLength) {
                this.iDisplayLength = iDisplayLength;
            }

            public int getSEcho() {
                return sEcho;
            }

            public void setSEcho(int sEcho) {
                this.sEcho = sEcho;
            }

            public int getCurpage() {
                return curpage;
            }

            public void setCurpage(int curpage) {
                this.curpage = curpage;
            }

            public int getRows() {
                return rows;
            }

            public void setRows(int rows) {
                this.rows = rows;
            }

            public boolean isIsAdmin() {
                return isAdmin;
            }

            public void setIsAdmin(boolean isAdmin) {
                this.isAdmin = isAdmin;
            }
        }
    }
}
