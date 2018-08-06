package com.zhangju.xingquban.interestclassapp.bean;

import com.google.gson.Gson;

import java.io.Serializable;

/**
 * Created by ydw on 2017/11/3.
 */
//视频评论列表获取bean
public class VideoCommentBean implements Serializable {

    /**
     * comtIcon : http://video.xqban.com/Customer/2017-10-25/1508928904901_321.jpg
     * star : 0
     * session : null
     * comtClsId : 2
     * timeStr : 2天前
     * subjectId : 489
     * comtName : 啦啦啦德玛西亚
     * addUserTime : 2017-11-01 18:39:49
     * customerId : 12777
     * replyId : null
     * comment : 时尚简约
     * id : 29
     * comtCls : {"tab":"com.studying.cm.model.impl.im.LiveVdo","name":"回放","keyName":"liveVdo","id":"2"}
     */

    private String comtIcon;
    private int star;
    private Object session;
    private String comtClsId;
    private String timeStr;
    private String subjectId;
    private String comtName;
    private String addUserTime;
    private String customerId;
    private Object replyId;
    private String comment;
    private String id;
    private ComtClsBean comtCls;

    public static VideoCommentBean objectFromData(String str) {

        return new Gson().fromJson(str, VideoCommentBean.class);
    }

    public String getComtIcon() {
        return comtIcon;
    }

    public void setComtIcon(String comtIcon) {
        this.comtIcon = comtIcon;
    }

    public int getStar() {
        return star;
    }

    public void setStar(int star) {
        this.star = star;
    }

    public Object getSession() {
        return session;
    }

    public void setSession(Object session) {
        this.session = session;
    }

    public String getComtClsId() {
        return comtClsId;
    }

    public void setComtClsId(String comtClsId) {
        this.comtClsId = comtClsId;
    }

    public String getTimeStr() {
        return timeStr;
    }

    public void setTimeStr(String timeStr) {
        this.timeStr = timeStr;
    }

    public String getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(String subjectId) {
        this.subjectId = subjectId;
    }

    public String getComtName() {
        return comtName;
    }

    public void setComtName(String comtName) {
        this.comtName = comtName;
    }

    public String getAddUserTime() {
        return addUserTime;
    }

    public void setAddUserTime(String addUserTime) {
        this.addUserTime = addUserTime;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public Object getReplyId() {
        return replyId;
    }

    public void setReplyId(Object replyId) {
        this.replyId = replyId;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public ComtClsBean getComtCls() {
        return comtCls;
    }

    public void setComtCls(ComtClsBean comtCls) {
        this.comtCls = comtCls;
    }

    public static class ComtClsBean {
        /**
         * tab : com.studying.cm.model.impl.im.LiveVdo
         * name : 回放
         * keyName : liveVdo
         * id : 2
         */

        private String tab;
        private String name;
        private String keyName;
        private String id;

        public static ComtClsBean objectFromData(String str) {

            return new Gson().fromJson(str, ComtClsBean.class);
        }

        public String getTab() {
            return tab;
        }

        public void setTab(String tab) {
            this.tab = tab;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getKeyName() {
            return keyName;
        }

        public void setKeyName(String keyName) {
            this.keyName = keyName;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }
    }
}
