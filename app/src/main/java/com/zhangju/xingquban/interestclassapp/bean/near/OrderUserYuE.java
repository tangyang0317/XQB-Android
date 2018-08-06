package com.zhangju.xingquban.interestclassapp.bean.near;

import java.io.Serializable;
import java.util.List;

/**
 * Created by zgl on 2017/11/27.
 */

public class OrderUserYuE implements Serializable{

    /**
     * sEcho : 1
     * iTotalRecords : 4
     * iTotalDisplayRecords : 4
     * aaData : [{"sysPaytype":2,"sysVersion":"iosAndAndriod","editUserId":"1","editUserName":"admin","addUserId":"1","editUserTime":"2017-03-02 11:44:24","display":1,"icon":"http://video.xqban.com/Currency/2016-12-16/1481890237340_438.png","published":true,"payClass":null,"addUserTime":"2016-10-20 16:23:26","removed":0,"canTackcash":false,"enable":true,"domain":null,"name":"余额","digest":0,"coinNum":0,"keyname":"balances","id":"4","isAddUserType":"staff","isEditUserType":"staff","sorts":1,"addUserName":"admin"},{"sysPaytype":9,"sysVersion":"iosAndAndriod","editUserId":"1","editUserName":"admin","addUserId":"1","editUserTime":"2017-03-02 10:50:28","display":1,"icon":"http://video.xqban.com/Currency/2017-02-15/1487150223925_515.png","published":true,"payClass":null,"addUserTime":"2017-02-13 17:17:26","removed":0,"canTackcash":false,"enable":true,"domain":null,"name":"课程体验券","digest":0,"coinNum":0,"keyname":"ordersCoupon","id":"8","isAddUserType":"staff","isEditUserType":"staff","sorts":0,"addUserName":"admin"},{"sysPaytype":1,"sysVersion":"iosAndAndriod","editUserId":"1","editUserName":"admin","addUserId":"1","editUserTime":"2017-04-11 15:32:26","display":1,"icon":"http://video.xqban.com/Currency/2016-12-16/1481890192558_467.png","published":true,"payClass":"com.studying.cm.service.impl.pay2.QQPayService2","addUserTime":"2016-10-20 16:23:06","removed":0,"canTackcash":true,"enable":true,"domain":"http://m.xqban.com","name":"微信","digest":0,"coinNum":0,"keyname":"qqpay","id":"3","isAddUserType":"staff","isEditUserType":"staff","sorts":0,"addUserName":"admin"},{"sysPaytype":1,"sysVersion":"iosAndAndriod","editUserId":"1","editUserName":"admin","addUserId":"1","editUserTime":"2017-04-11 15:32:34","display":1,"icon":"http://video.xqban.com/Currency/2016-12-16/1481890202699_601.png","published":true,"payClass":"com.studying.cm.service.impl.pay2.AIPayService2","addUserTime":"2016-10-20 16:22:11","removed":0,"canTackcash":true,"enable":true,"domain":"http://m.xqban.com","name":"支付宝","digest":0,"coinNum":0,"keyname":"aipay","id":"1","isAddUserType":"staff","isEditUserType":"staff","sorts":0,"addUserName":"admin"}]
     * attachData : null
     * total : 1
     * page : 0
     * success : true
     * isLogin : true
     * errMsg : {}
     * isAdmin : false
     * cId : 16097
     * cname : 13400000000
     * time : 2017-11-27 11:06:50:203
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

    public static class ErrMsgBean implements Serializable{
    }

    public static class AaDataBean implements Serializable{
        /**
         * sysPaytype : 2
         * sysVersion : iosAndAndriod
         * editUserId : 1
         * editUserName : admin
         * addUserId : 1
         * editUserTime : 2017-03-02 11:44:24
         * display : 1
         * icon : http://video.xqban.com/Currency/2016-12-16/1481890237340_438.png
         * published : true
         * payClass : null
         * addUserTime : 2016-10-20 16:23:26
         * removed : 0
         * canTackcash : false
         * enable : true
         * domain : null
         * name : 余额
         * digest : 0
         * coinNum : 0
         * keyname : balances
         * id : 4
         * isAddUserType : staff
         * isEditUserType : staff
         * sorts : 1
         * addUserName : admin
         */

        private int sysPaytype;
        private String sysVersion;
        private String editUserId;
        private String editUserName;
        private String addUserId;
        private String editUserTime;
        private int display;
        private String icon;
        private boolean published;
        private Object payClass;
        private String addUserTime;
        private int removed;
        private boolean canTackcash;
        private boolean enable;
        private Object domain;
        private String name;
        private int digest;
        private float coinNum;
        private String keyname;
        private String id;
        private String isAddUserType;
        private String isEditUserType;
        private int sorts;
        private String addUserName;

        public int getSysPaytype() {
            return sysPaytype;
        }

        public void setSysPaytype(int sysPaytype) {
            this.sysPaytype = sysPaytype;
        }

        public String getSysVersion() {
            return sysVersion;
        }

        public void setSysVersion(String sysVersion) {
            this.sysVersion = sysVersion;
        }

        public String getEditUserId() {
            return editUserId;
        }

        public void setEditUserId(String editUserId) {
            this.editUserId = editUserId;
        }

        public String getEditUserName() {
            return editUserName;
        }

        public void setEditUserName(String editUserName) {
            this.editUserName = editUserName;
        }

        public String getAddUserId() {
            return addUserId;
        }

        public void setAddUserId(String addUserId) {
            this.addUserId = addUserId;
        }

        public String getEditUserTime() {
            return editUserTime;
        }

        public void setEditUserTime(String editUserTime) {
            this.editUserTime = editUserTime;
        }

        public int getDisplay() {
            return display;
        }

        public void setDisplay(int display) {
            this.display = display;
        }

        public String getIcon() {
            return icon;
        }

        public void setIcon(String icon) {
            this.icon = icon;
        }

        public boolean isPublished() {
            return published;
        }

        public void setPublished(boolean published) {
            this.published = published;
        }

        public Object getPayClass() {
            return payClass;
        }

        public void setPayClass(Object payClass) {
            this.payClass = payClass;
        }

        public String getAddUserTime() {
            return addUserTime;
        }

        public void setAddUserTime(String addUserTime) {
            this.addUserTime = addUserTime;
        }

        public int getRemoved() {
            return removed;
        }

        public void setRemoved(int removed) {
            this.removed = removed;
        }

        public boolean isCanTackcash() {
            return canTackcash;
        }

        public void setCanTackcash(boolean canTackcash) {
            this.canTackcash = canTackcash;
        }

        public boolean isEnable() {
            return enable;
        }

        public void setEnable(boolean enable) {
            this.enable = enable;
        }

        public Object getDomain() {
            return domain;
        }

        public void setDomain(Object domain) {
            this.domain = domain;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getDigest() {
            return digest;
        }

        public void setDigest(int digest) {
            this.digest = digest;
        }

        public float getCoinNum() {
            return coinNum;
        }

        public void setCoinNum(float coinNum) {
            this.coinNum = coinNum;
        }

        public String getKeyname() {
            return keyname;
        }

        public void setKeyname(String keyname) {
            this.keyname = keyname;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getIsAddUserType() {
            return isAddUserType;
        }

        public void setIsAddUserType(String isAddUserType) {
            this.isAddUserType = isAddUserType;
        }

        public String getIsEditUserType() {
            return isEditUserType;
        }

        public void setIsEditUserType(String isEditUserType) {
            this.isEditUserType = isEditUserType;
        }

        public int getSorts() {
            return sorts;
        }

        public void setSorts(int sorts) {
            this.sorts = sorts;
        }

        public String getAddUserName() {
            return addUserName;
        }

        public void setAddUserName(String addUserName) {
            this.addUserName = addUserName;
        }
    }
}
