package com.zhangju.xingquban.interestclassapp.bean.live;

import java.util.List;

/**
 * Created by liush on 2016/11/30 0030.
 *
 * @描述
 */
public class LivePayTypeBean {

    /**
     * sEcho : 1
     * iTotalRecords : 3
     * iTotalDisplayRecords : 3
     * aaData : [{"sysPaytype":2,"editUserId":"1","editUserName":"admin","addUserId":"1","editUserTime":"2016-10-25
     * 11:32:31","display":1,"icon":null,"published":true,"payClass":null,"addUserTime":"2016-10-20 16:23:26",
     * "removed":0,"enable":true,"domain":null,"name":"余额","digest":0,"keyname":"balances","id":"4",
     * "isAddUserType":"staff","isEditUserType":"staff","sorts":1,"addUserName":"admin"},{"sysPaytype":1,
     * "editUserId":"1","editUserName":"admin","addUserId":"1","editUserTime":"2016-11-04 16:57:31","display":1,
     * "icon":null,"published":true,"payClass":"com.studying.cm.service.impl.pay2.QQPayService2",
     * "addUserTime":"2016-10-20 16:23:06","removed":0,"enable":true,"domain":"http://x14850701c.iask.in",
     * "name":"微信","digest":0,"keyname":"qqpay","id":"3","isAddUserType":"staff","isEditUserType":"staff","sorts":0,
     * "addUserName":"admin"},{"sysPaytype":1,"editUserId":"1","editUserName":"admin","addUserId":"1",
     * "editUserTime":"2016-10-26 18:26:33","display":1,"icon":null,"published":true,"payClass":"com.studying.cm
     * .service.impl.pay2.AIPayService2","addUserTime":"2016-10-20 16:22:11","removed":0,"enable":true,
     * "domain":"http://x14850701c.iask.in","name":"支付宝","digest":0,"keyname":"aipay","id":"1",
     * "isAddUserType":"staff","isEditUserType":"staff","sorts":0,"addUserName":"admin"}]
     * attachData : null
     * total : 1
     * page : 0
     * success : true
     * isLogin : true
     * errMsg : {}
     * isAdmin : false
     * cId : 2654
     * cname : 18666650022
     * time : 2016-11-30 17:55:44:552
     */

    private int sEcho;
    private int        iTotalRecords;
    private int        iTotalDisplayRecords;
    private Object     attachData;
    private int        total;
    private int        page;
    private boolean    success;
    private boolean    isLogin;
    private ErrMsgBean errMsg;
    private boolean    isAdmin;
    private String     cId;
    private String     cname;
    private String     time;
    /**
     * sysPaytype : 2
     * editUserId : 1
     * editUserName : admin
     * addUserId : 1
     * editUserTime : 2016-10-25 11:32:31
     * display : 1
     * icon : null
     * published : true
     * payClass : null
     * addUserTime : 2016-10-20 16:23:26
     * removed : 0
     * enable : true
     * domain : null
     * name : 余额
     * digest : 0
     * keyname : balances
     * id : 4
     * isAddUserType : staff
     * isEditUserType : staff
     * sorts : 1
     * addUserName : admin
     */

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

    public static class ErrMsgBean {
    }

    public static class AaDataBean {
        private int     sysPaytype;
        private String  editUserId;
        private String  editUserName;
        private String  addUserId;
        private String  editUserTime;
        private int     display;
        private Object  icon;
        private boolean published;
        private Object  payClass;
        private String  addUserTime;
        private int     removed;
        private boolean enable;
        private Object  domain;
        private String  name;
        private int     digest;
        private String  keyname;
        private String  id;
        private String  isAddUserType;
        private String  isEditUserType;
        private int     sorts;
        private String  addUserName;

        public int getSysPaytype() {
            return sysPaytype;
        }

        public void setSysPaytype(int sysPaytype) {
            this.sysPaytype = sysPaytype;
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

        public Object getIcon() {
            return icon;
        }

        public void setIcon(Object icon) {
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
