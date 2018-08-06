package com.zhangju.xingquban.interestclassapp.refactor.me.bean;

import java.util.List;

/**
 * Created by sgfb on 2017/10/27.
 * 接口返回收藏实体
 */
public class ResponseCollection{
    public boolean isDeleteChecked=false; //非接口字段
    public float LessonsPrice;
    public int collectionTypes;
    public String id;
    public String resourcesAuthorPicture;
    public CollectionResource resources;
    public CollectionStore teacherTime;
    public CollectionCustomer customer;
    public List<PictureRes> resourcesPictureList;

    /**
     * 收藏的资源实体
     */
    public class CollectionResource{
        public int commentCounts;
        public int collectionCounts;
        public int clickRate;
        public float price;
        public String id;
        public String title;
        public String subtitle;
        public String titlePicture;
        public String author;
        public String authorPicture;
        public String types;
        public String addUserTime;
    }

    public class CollectionCustomer {
        public String picture;
        public String areasName;
    }

    /**
     * 收藏的店铺类型
     */
    public class CollectionStore{
        public int star;
        public String id;
        public String name;
        public String catagoryName;
    }

    public class PictureRes{
        public String fileUrl;
    }
}