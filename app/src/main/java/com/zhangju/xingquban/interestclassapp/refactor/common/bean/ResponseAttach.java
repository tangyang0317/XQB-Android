package com.zhangju.xingquban.interestclassapp.refactor.common.bean;

/**
 * Created by sgfb on 2017/11/15.
 * 具有附加数据的返回
 */
public class ResponseAttach<T,T2> extends Response<T>{
    public T2 attachData;
}