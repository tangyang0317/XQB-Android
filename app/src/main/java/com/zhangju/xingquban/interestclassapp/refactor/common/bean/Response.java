package com.zhangju.xingquban.interestclassapp.refactor.common.bean;

import com.google.gson.annotations.SerializedName;
import com.google.gson.internal.LinkedHashTreeMap;

/**
 * Created by sgfb on 2017/10/26.
 * 接口返回实体模板.部分字段被省略，具体请查看文档http://test.xqban.com/api.doc
 */
public class Response<T>{
    public boolean success;
    public boolean isLogin;
    public boolean isAdmin;
    public int iTotalRecords; //符合条件总数
    public String cId; //操作接口的用户的ID
    public String cname; //操作接口的用户的姓名
    public String time; //接口请求的发生时间
    public LinkedHashTreeMap<String,String> errMsg;
    public int total;//分页总数
    @SerializedName("aaData")
    public T data;
}