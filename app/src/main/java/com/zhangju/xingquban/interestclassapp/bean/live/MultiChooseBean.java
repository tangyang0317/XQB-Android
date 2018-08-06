package com.zhangju.xingquban.interestclassapp.bean.live;

import java.io.Serializable;

/**
 * Created by ydw on 2017/11/13.
 */
//科目选择的bean
public class MultiChooseBean implements Serializable{
    private String id;
    private String name;

    public MultiChooseBean(String id, String name) {
        this.id = id;
        this.name = name;
    }

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
}
