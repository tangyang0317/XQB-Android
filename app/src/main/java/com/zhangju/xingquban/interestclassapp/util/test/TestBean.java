package com.zhangju.xingquban.interestclassapp.util.test;

import java.io.Serializable;
import java.util.List;

/**
 * Created by ydw on 2017/11/27.
 */

public class TestBean implements Serializable {
    private String name;
    private String number;

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public TestBean(String name, String number) {
        this.name = name;
        this.number = number;
    }

    public TestBean(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
