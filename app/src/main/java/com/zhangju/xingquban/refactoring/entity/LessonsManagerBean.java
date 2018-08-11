package com.zhangju.xingquban.refactoring.entity;

import java.io.Serializable;
import java.util.List;

/**
 * @packageName com.zhangju.xingquban.refactoring.entity
 * @FileName CategoryManagerBean
 * @Author tangyang
 * @DATE 2018/8/11
 **/
public class LessonsManagerBean implements Serializable {

    private String catagoryName;
    private List<LessonBean> lessons;

    public String getCatagoryName() {
        return catagoryName;
    }

    public void setCatagoryName(String catagoryName) {
        this.catagoryName = catagoryName;
    }

    public List<LessonBean> getLessons() {
        return lessons;
    }

    public void setLessons(List<LessonBean> lessons) {
        this.lessons = lessons;
    }
}
