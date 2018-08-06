package com.zhangju.xingquban.interestclassapp.bean.me.vip;

import com.alibaba.fastjson.annotation.JSONField;
import com.zhangju.xingquban.interestclassapp.base.BaseBean;

/**
 * Created by liush on 2016/12/27 0027.
 *
 * @描述
 */
public class VipPayBean extends BaseBean {

    /**
     * sEcho : 0
     * iTotalRecords : 0
     * iTotalDisplayRecords : 0
     * aaData : {"package":"Sign=WXPay","packageValue":"Sign=WXPay","appid":"wx792fbfb68e82c491",
     * "sign":"D36F5DD8CCE5994391A2DB08D04AC0B3","partnerid":"1329896801",
     * "prepayid":"wx201612271533086bf686b3fb0492738181","noncestr":"8169e05e2a0debcb15458f2cc1eff0ea",
     * "timestamp":"1482823987"}
     * attachData : null
     * total : null
     * page : null
     * success : true
     * isLogin : true
     * isAdmin : false
     * cId : 48
     * cname : 18854254524
     * time : 2016-12-27 15:33:07:634
     */

    private int        sEcho;
    private int        iTotalRecords;
    private int        iTotalDisplayRecords;
    /**
     * package : Sign=WXPay
     * packageValue : Sign=WXPay
     * appid : wx792fbfb68e82c491
     * sign : D36F5DD8CCE5994391A2DB08D04AC0B3
     * partnerid : 1329896801
     * prepayid : wx201612271533086bf686b3fb0492738181
     * noncestr : 8169e05e2a0debcb15458f2cc1eff0ea
     * timestamp : 1482823987
     */

    private AaDataBean aaData;
    private Object     attachData;
    private Object     total;
    private Object     page;
    private boolean    success;
    private boolean    isLogin;
    private boolean    isAdmin;
    private String     cId;
    private String     cname;
    private String     time;

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
        @JSONField(name = "package")
        private String  packageX;
        private String  packageValue;
        private String  appid;
        private String  sign;
        private String  sno;
        private String  partnerid;
        private String  prepayid;
        private String  noncestr;
        private String  timestamp;
        private boolean status;
        private boolean ispay;

        public boolean ispay() {
            return ispay;
        }

        public void setIspay(boolean ispay) {
            this.ispay = ispay;
        }

        public String getSno() {
            return sno;
        }

        public void setSno(String sno) {
            this.sno = sno;
        }

        public boolean isStatus() {
            return status;
        }

        public void setStatus(boolean status) {
            this.status = status;
        }

        public String getPackageX() {
            return packageX;
        }

        public void setPackageX(String packageX) {
            this.packageX = packageX;
        }

        public String getPackageValue() {
            return packageValue;
        }

        public void setPackageValue(String packageValue) {
            this.packageValue = packageValue;
        }

        public String getAppid() {
            return appid;
        }

        public void setAppid(String appid) {
            this.appid = appid;
        }

        public String getSign() {
            return sign;
        }

        public void setSign(String sign) {
            this.sign = sign;
        }

        public String getPartnerid() {
            return partnerid;
        }

        public void setPartnerid(String partnerid) {
            this.partnerid = partnerid;
        }

        public String getPrepayid() {
            return prepayid;
        }

        public void setPrepayid(String prepayid) {
            this.prepayid = prepayid;
        }

        public String getNoncestr() {
            return noncestr;
        }

        public void setNoncestr(String noncestr) {
            this.noncestr = noncestr;
        }

        public String getTimestamp() {
            return timestamp;
        }

        public void setTimestamp(String timestamp) {
            this.timestamp = timestamp;
        }
    }
}
