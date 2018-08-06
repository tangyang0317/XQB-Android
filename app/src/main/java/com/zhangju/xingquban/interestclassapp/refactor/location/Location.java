package com.zhangju.xingquban.interestclassapp.refactor.location;

import com.fastlib.annotation.Database;

/**
 * Created by sgfb on 2017/10/30.
 * 定位信息
 */
public class Location
        implements Cloneable {
    @Database(keyPrimary = true)
    private final int id = 1;

    public String latitude     = "30.292678";
    public String longitude    = "120.036981";
    public String locationCity = "默认城市";
    public String cityPid      = "100000";
    public String cityId       = "310000";


    @Override
    protected Location clone() throws CloneNotSupportedException {
        return (Location) super.clone();
    }
}