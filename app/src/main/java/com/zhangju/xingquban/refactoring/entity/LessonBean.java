package com.zhangju.xingquban.refactoring.entity;

import java.io.Serializable;

/**
 * 课程列表实体对象
 *
 * @packageName com.zhangju.xingquban.refactoring.Entity
 * @FileName LessonBean
 * @Author tangyang
 * @DATE 2018/8/11
 **/
public class LessonBean implements Serializable {

    private String id;
    private String name;
    private String lessonDate;
    private String descript;
    private String price;
    private String method; //授课地点
    private String picture;


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

    public String getLessonDate() {
        return lessonDate;
    }

    public void setLessonDate(String lessonDate) {
        this.lessonDate = lessonDate;
    }

    public String getDescript() {
        return descript;
    }

    public void setDescript(String descript) {
        this.descript = descript;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }
}
