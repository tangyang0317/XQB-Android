package com.zhangju.xingquban.interestclassapp.refactor.me.bean;

import java.io.Serializable;

/**
 * Created by sgfb on 2017/11/1.
 * 活动特色item
 */
public class PublishActiveFeature implements Serializable {
    public static final int TYPE_TEXT=1;
    public static final int TYPE_IMAGE=2;
    public static final int TYPE_IMAGE_URL=3; //图片来自服务器

    public boolean isReplace;
    public int type; //1为文本 2为图像
    public String content; //文本或者图像路径
    public String imageUrl; //本地字段,用来储存本地图像上传服务器返回后的url地址
}