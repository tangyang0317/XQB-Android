package com.zhangju.xingquban.interestclassapp.bean;

import com.google.gson.Gson;

import java.io.Serializable;

/**
 * Created by ydw on 2017/11/27.
 */

public class PayTypeBean implements Serializable {

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
     * coinNum : -765.09
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
    private double coinNum;
    private String keyname;
    private String id;
    private String isAddUserType;
    private String isEditUserType;
    private int sorts;
    private String addUserName;

    public static PayTypeBean objectFromData(String str) {

        return new Gson().fromJson(str, PayTypeBean.class);
    }

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

    public double getCoinNum() {
        return coinNum;
    }

    public void setCoinNum(double coinNum) {
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
