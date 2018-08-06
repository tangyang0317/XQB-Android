package com.zhangju.xingquban.interestclassapp.bean;

import java.util.List;

/**
 * Created by zsl on 2017/7/5.
 */

public class HomeBannerBean {

    @Override
    public String toString() {
        return "HomeBannerBean{" +
                "sEcho=" + sEcho +
                ", iTotalRecords=" + iTotalRecords +
                ", iTotalDisplayRecords=" + iTotalDisplayRecords +
                ", attachData=" + attachData +
                ", total=" + total +
                ", page=" + page +
                ", success=" + success +
                ", isLogin=" + isLogin +
                ", errMsg=" + errMsg +
                ", isAdmin=" + isAdmin +
                ", cId=" + cId +
                ", cname=" + cname +
                ", time='" + time + '\'' +
                ", aaData=" + aaData +
                '}';
    }

    /**
     * sEcho : 1
     * iTotalRecords : 6
     * iTotalDisplayRecords : 6
     * aaData : [{"types":1,"pictureurl":"http://video.xqban.com/ReleaseVersion/2017-06-13/1497343826125_21.jpg","bannerJumpTypes":1,"changeId":null,"id":"65","cityId":"310000","url":"http://m.xqban.com/admnxzcmr/topic/entity?id=8"},{"types":1,"pictureurl":"http://video.xqban.com/ReleaseVersion/2017-06-02/1496370681502_167.jpg","bannerJumpTypes":1,"changeId":null,"id":"63","cityId":"310000","url":"http://m.xqban.com/admnxzcmr/topic/entity?id=3"},{"types":0,"pictureurl":"http://video.xqban.com/ReleaseVersion/2017-04-20/1492688267801_924.jpg","bannerJumpTypes":1,"changeId":null,"id":"53","cityId":"111111","url":"http://mob.xqban.com/zt/jm/"},{"types":0,"pictureurl":"http://video.xqban.com/ReleaseVersion/2017-02-28/1488275993736_320.png","bannerJumpTypes":3,"changeId":null,"id":"38","cityId":"111111","url":null},{"types":0,"pictureurl":"http://video.xqban.com/ReleaseVersion/2017-02-28/1488275965464_733.png","bannerJumpTypes":9,"changeId":null,"id":"37","cityId":"111111","url":null},{"types":0,"pictureurl":"http://video.xqban.com/ReleaseVersion/2017-02-28/1488275950015_182.png","bannerJumpTypes":3,"changeId":null,"id":"36","cityId":"111111","url":"https://jinshuju.net/f/sQkAde"}]
     * attachData : null
     * total : 1
     * page : 0
     * success : true
     * isLogin : false
     * errMsg : {}
     * isAdmin : false
     * cId : null
     * cname : null
     * time : 2017-07-10 19:44:22:764
     */

    private int sEcho;
    private int iTotalRecords;
    private int iTotalDisplayRecords;
    private Object attachData;
    private int total;
    private int page;
    private boolean success;
    private boolean isLogin;
    private ErrMsgBean errMsg;
    private boolean isAdmin;
    private Object cId;
    private Object cname;
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

    public Object getAttachData() {
        return attachData;
    }

    public void setAttachData(Object attachData) {
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

    public List<AaDataBean> getAaData() {
        return aaData;
    }

    public void setAaData(List<AaDataBean> aaData) {
        this.aaData = aaData;
    }

    public static class ErrMsgBean {
    }

    public static class AaDataBean {

        @Override
        public String toString() {
            return "AaDataBean{" +
                    "types=" + types +
                    ", pictureurl='" + pictureurl + '\'' +
                    ", bannerJumpTypes=" + bannerJumpTypes +
                    ", changeId=" + changeId +
                    ", id='" + id + '\'' +
                    ", cityId='" + cityId + '\'' +
                    ", url='" + url + '\'' +
                    '}';
        }

        /**
         * types : 1
         * pictureurl : http://video.xqban.com/ReleaseVersion/2017-06-13/1497343826125_21.jpg
         * bannerJumpTypes : 1
         * changeId : null
         * id : 65
         * cityId : 310000
         * url : http://m.xqban.com/admnxzcmr/topic/entity?id=8
         */

        private int types;
        private String pictureurl;
        private int bannerJumpTypes;
        private Object changeId;
        private String id;
        private String cityId;
        private String url;

        public int getTypes() {
            return types;
        }

        public void setTypes(int types) {
            this.types = types;
        }

        public String getPictureurl() {
            return pictureurl;
        }

        public void setPictureurl(String pictureurl) {
            this.pictureurl = pictureurl;
        }

        public int getBannerJumpTypes() {
            return bannerJumpTypes;
        }

        public void setBannerJumpTypes(int bannerJumpTypes) {
            this.bannerJumpTypes = bannerJumpTypes;
        }

        public Object getChangeId() {
            return changeId;
        }

        public void setChangeId(Object changeId) {
            this.changeId = changeId;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getCityId() {
            return cityId;
        }

        public void setCityId(String cityId) {
            this.cityId = cityId;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }
    }
}
