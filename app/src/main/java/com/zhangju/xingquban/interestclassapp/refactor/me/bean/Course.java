package com.zhangju.xingquban.interestclassapp.refactor.me.bean;

/**
 * Created by sgfb on 2017/11/3.
 * 课程安排显示item的实体
 */
public class Course {
    public boolean isTitle;
    public String title;

    public Course(){}

    public Course(boolean isTitle, String title) {
        this.isTitle = isTitle;
        this.title = title;
    }
}