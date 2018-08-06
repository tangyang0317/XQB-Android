package com.zhangju.xingquban.interestclassapp.refactor.common.bean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by sgfb on 2017/11/14.
 * 市镇列表返回实体
 */
public class ResponseCity implements Serializable{
    public boolean showChildren; //本地字段，是否显示子区域
    public boolean specialAreas;
    public String addrShortPinyin;
    public String id;
    public String name;
    public String pid;
    public List<Area> areas;

    public class Area implements Serializable{
        public boolean specialAreas;
        public String id;
        public String pid;
        public String name;
        public ResponseCity fromCity; //快速索引字段
    }
}