package com.zhangju.xingquban.interestclassapp.refactor.me.bean;

import android.graphics.Bitmap;

import java.util.List;

/**
 * Created by sgfb on 2017/10/30.
 * 查看课程码返回实体
 */
public class ResponseCheckCourse{
    public float amount;
    public int counts;
    public String lessonName;
    public List<QrCode> ordersQCode;

    public static class QrCode{
        public int status; //1未使用 0已使用
        public String orderDetailId;
        public String qcode;
        public Bitmap codeIcon; //非接口字段
    }
}