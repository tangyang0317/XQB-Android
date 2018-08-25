package com.zhangju.xingquban.interestclassapp.refactor.me.bean;

/**
 * Created by sgfb on 2017/11/3.
 * 机构简介返回实体
 */
public class ResponseOrgProfile {
    public String id;
    public String intro; //使用#符号分割内容和图片

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getIntro() {
        return intro;
    }

    public void setIntro(String intro) {
        this.intro = intro;
    }
}