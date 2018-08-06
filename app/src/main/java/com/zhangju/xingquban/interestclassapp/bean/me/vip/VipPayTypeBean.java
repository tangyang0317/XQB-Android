package com.zhangju.xingquban.interestclassapp.bean.me.vip;


import com.zhangju.xingquban.interestclassapp.base.BaseBean;

import java.util.List;

/**
 * Created by liush on 2016/12/23 0023.
 * <p>
 * 获取支付方式及商品列表
 */
public class VipPayTypeBean extends BaseBean {

    /**
     * sEcho : 0
     * iTotalRecords : 0
     * iTotalDisplayRecords : 0
     * aaData : {"paytype":[{"id":"5","name":"内购","icon":"http://video.xqban
     * .com/Currency/2016-12-23/1482470959349_569.png","keyname":"appbuy","payClass":null,"domain":null,
     * "enable":true,"sysPaytype":4,"canTackcash":false,"coinNum":null,"shouldShowMoneyAboutPaytype":null,
     * "removed":0,"addUserId":"1","addUserName":"admin","addUserTime":"2016-11-30 13:26:40","editUserId":"1",
     * "editUserName":"admin","editUserTime":"2016-12-23 13:29:23","isAddUserType":"staff","isEditUserType":"staff",
     * "published":true,"sorts":2,"display":1,"digest":0,"del":false,"degreeId":null},{"id":"3","name":"微信",
     * "icon":"http://video.xqban.com/Currency/2016-12-16/1481889545278_198.png","keyname":"qqpay","payClass":"com
     * .studying.cm.service.impl.pay2.QQPayService2","domain":"http://x14850701c.iask.in","enable":true,
     * "sysPaytype":1,"canTackcash":true,"coinNum":null,"shouldShowMoneyAboutPaytype":null,"removed":0,
     * "addUserId":"1","addUserName":"admin","addUserTime":"2016-10-20 16:23:06","editUserId":"1",
     * "editUserName":"admin","editUserTime":"2016-12-16 19:59:07","isAddUserType":"staff","isEditUserType":"staff",
     * "published":true,"sorts":0,"display":1,"digest":0,"del":false,"degreeId":null},{"id":"1","name":"支付宝",
     * "icon":"http://video.xqban.com/Currency/2016-12-16/1481889484110_372.png","keyname":"aipay","payClass":"com
     * .studying.cm.service.impl.pay2.AIPayService2","domain":"http://x14850701c.iask.in","enable":true,
     * "sysPaytype":1,"canTackcash":true,"coinNum":null,"shouldShowMoneyAboutPaytype":null,"removed":0,
     * "addUserId":"1","addUserName":"admin","addUserTime":"2016-10-20 16:22:11","editUserId":"1",
     * "editUserName":"admin","editUserTime":"2016-12-16 19:58:06","isAddUserType":"staff","isEditUserType":"staff",
     * "published":true,"sorts":0,"display":1,"digest":0,"del":false,"degreeId":null}],"productlist":[{"id":"2",
     * "productId":"zhangju.XQBanVip002","productContext":"2","removed":0,"addUserName":"admin",
     * "addUserTime":"2016-12-22 10:36:12","editUserId":"1","editUserName":"admin","editUserTime":"2016-12-22
     * 10:36:12","addUserId":"1","isAddUserType":"staff","isEditUserType":"staff","sorts":0,"digest":0,
     * "published":true,"display":1,"shouldShowMoneyAboutPaytype":388,"del":false,"degreeId":null}]}
     * attachData : null
     * total : null
     * page : null
     * success : true
     * isLogin : false
     * errMsg : {}
     * isAdmin : false
     * cId : null
     * cname : null
     * time : 2016-12-23 18:04:03:319
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
    private boolean isAdmin;
    private Object cId;
    private Object cname;
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

    public static class AaDataBean {
        /**
         * id : 5
         * name : 内购
         * icon : http://video.xqban.com/Currency/2016-12-23/1482470959349_569.png
         * keyname : appbuy
         * payClass : null
         * domain : null
         * enable : true
         * sysPaytype : 4
         * canTackcash : false
         * coinNum : null
         * shouldShowMoneyAboutPaytype : null
         * removed : 0
         * addUserId : 1
         * addUserName : admin
         * addUserTime : 2016-11-30 13:26:40
         * editUserId : 1
         * editUserName : admin
         * editUserTime : 2016-12-23 13:29:23
         * isAddUserType : staff
         * isEditUserType : staff
         * published : true
         * sorts : 2
         * display : 1
         * digest : 0
         * del : false
         * degreeId : null
         */

        private List<PaytypeBean> paytype;
        /**
         * id : 2
         * productId : zhangju.XQBanVip002
         * productContext : 2
         * removed : 0
         * addUserName : admin
         * addUserTime : 2016-12-22 10:36:12
         * editUserId : 1
         * editUserName : admin
         * editUserTime : 2016-12-22 10:36:12
         * addUserId : 1
         * isAddUserType : staff
         * isEditUserType : staff
         * sorts : 0
         * digest : 0
         * published : true
         * display : 1
         * shouldShowMoneyAboutPaytype : 388
         * del : false
         * degreeId : null
         */

        private List<ProductlistBean> productlist;

        public List<PaytypeBean> getPaytype() {
            return paytype;
        }

        public void setPaytype(List<PaytypeBean> paytype) {
            this.paytype = paytype;
        }

        public List<ProductlistBean> getProductlist() {
            return productlist;
        }

        public void setProductlist(List<ProductlistBean> productlist) {
            this.productlist = productlist;
        }

        public static class PaytypeBean {
            private String id;
            private String name;
            private String icon;
            private String keyname;
            private Object payClass;
            private Object domain;
            private boolean enable;
            private int sysPaytype;
            private boolean canTackcash;
            private Object coinNum;
            private Object shouldShowMoneyAboutPaytype;
            private int removed;
            private String addUserId;
            private String addUserName;
            private String addUserTime;
            private String editUserId;
            private String editUserName;
            private String editUserTime;
            private String isAddUserType;
            private String isEditUserType;
            private boolean published;
            private int sorts;
            private int display;
            private int digest;
            private boolean del;
            private Object degreeId;

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getIcon() {
                return icon;
            }

            public void setIcon(String icon) {
                this.icon = icon;
            }

            public String getKeyname() {
                return keyname;
            }

            public void setKeyname(String keyname) {
                this.keyname = keyname;
            }

            public Object getPayClass() {
                return payClass;
            }

            public void setPayClass(Object payClass) {
                this.payClass = payClass;
            }

            public Object getDomain() {
                return domain;
            }

            public void setDomain(Object domain) {
                this.domain = domain;
            }

            public boolean isEnable() {
                return enable;
            }

            public void setEnable(boolean enable) {
                this.enable = enable;
            }

            public int getSysPaytype() {
                return sysPaytype;
            }

            public void setSysPaytype(int sysPaytype) {
                this.sysPaytype = sysPaytype;
            }

            public boolean isCanTackcash() {
                return canTackcash;
            }

            public void setCanTackcash(boolean canTackcash) {
                this.canTackcash = canTackcash;
            }

            public Object getCoinNum() {
                return coinNum;
            }

            public void setCoinNum(Object coinNum) {
                this.coinNum = coinNum;
            }

            public Object getShouldShowMoneyAboutPaytype() {
                return shouldShowMoneyAboutPaytype;
            }

            public void setShouldShowMoneyAboutPaytype(Object shouldShowMoneyAboutPaytype) {
                this.shouldShowMoneyAboutPaytype = shouldShowMoneyAboutPaytype;
            }

            public int getRemoved() {
                return removed;
            }

            public void setRemoved(int removed) {
                this.removed = removed;
            }

            public String getAddUserId() {
                return addUserId;
            }

            public void setAddUserId(String addUserId) {
                this.addUserId = addUserId;
            }

            public String getAddUserName() {
                return addUserName;
            }

            public void setAddUserName(String addUserName) {
                this.addUserName = addUserName;
            }

            public String getAddUserTime() {
                return addUserTime;
            }

            public void setAddUserTime(String addUserTime) {
                this.addUserTime = addUserTime;
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

            public String getEditUserTime() {
                return editUserTime;
            }

            public void setEditUserTime(String editUserTime) {
                this.editUserTime = editUserTime;
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

            public boolean isPublished() {
                return published;
            }

            public void setPublished(boolean published) {
                this.published = published;
            }

            public int getSorts() {
                return sorts;
            }

            public void setSorts(int sorts) {
                this.sorts = sorts;
            }

            public int getDisplay() {
                return display;
            }

            public void setDisplay(int display) {
                this.display = display;
            }

            public int getDigest() {
                return digest;
            }

            public void setDigest(int digest) {
                this.digest = digest;
            }

            public boolean isDel() {
                return del;
            }

            public void setDel(boolean del) {
                this.del = del;
            }

            public Object getDegreeId() {
                return degreeId;
            }

            public void setDegreeId(Object degreeId) {
                this.degreeId = degreeId;
            }
        }

        public static class ProductlistBean {
            private String id;
            private String productId;
            private String productContext;
            private int removed;
            private String addUserName;
            private String addUserTime;
            private String editUserId;
            private String editUserName;
            private String editUserTime;
            private String addUserId;
            private String isAddUserType;
            private String isEditUserType;
            private int sorts;
            private int digest;
            private boolean published;
            private int display;
            private int shouldShowMoneyAboutPaytype;
            private boolean del;
            private Object degreeId;

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getProductId() {
                return productId;
            }

            public void setProductId(String productId) {
                this.productId = productId;
            }

            public String getProductContext() {
                return productContext;
            }

            public void setProductContext(String productContext) {
                this.productContext = productContext;
            }

            public int getRemoved() {
                return removed;
            }

            public void setRemoved(int removed) {
                this.removed = removed;
            }

            public String getAddUserName() {
                return addUserName;
            }

            public void setAddUserName(String addUserName) {
                this.addUserName = addUserName;
            }

            public String getAddUserTime() {
                return addUserTime;
            }

            public void setAddUserTime(String addUserTime) {
                this.addUserTime = addUserTime;
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

            public String getEditUserTime() {
                return editUserTime;
            }

            public void setEditUserTime(String editUserTime) {
                this.editUserTime = editUserTime;
            }

            public String getAddUserId() {
                return addUserId;
            }

            public void setAddUserId(String addUserId) {
                this.addUserId = addUserId;
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

            public int getDigest() {
                return digest;
            }

            public void setDigest(int digest) {
                this.digest = digest;
            }

            public boolean isPublished() {
                return published;
            }

            public void setPublished(boolean published) {
                this.published = published;
            }

            public int getDisplay() {
                return display;
            }

            public void setDisplay(int display) {
                this.display = display;
            }

            public int getShouldShowMoneyAboutPaytype() {
                return shouldShowMoneyAboutPaytype;
            }

            public void setShouldShowMoneyAboutPaytype(int shouldShowMoneyAboutPaytype) {
                this.shouldShowMoneyAboutPaytype = shouldShowMoneyAboutPaytype;
            }

            public boolean isDel() {
                return del;
            }

            public void setDel(boolean del) {
                this.del = del;
            }

            public Object getDegreeId() {
                return degreeId;
            }

            public void setDegreeId(Object degreeId) {
                this.degreeId = degreeId;
            }
        }
    }
}
