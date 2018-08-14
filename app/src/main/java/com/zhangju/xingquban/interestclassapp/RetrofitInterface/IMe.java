package com.zhangju.xingquban.interestclassapp.RetrofitInterface;

import com.zhangju.xingquban.interestclassapp.bean.Data;
import com.zhangju.xingquban.interestclassapp.bean.ResouceUpBean;
import com.zhangju.xingquban.interestclassapp.ui.fragment.me.Collect.MeCollectAllBean;
import com.zhangju.xingquban.interestclassapp.ui.fragment.me.Comment.MeCommentBean;
import com.zhangju.xingquban.interestclassapp.ui.fragment.me.Institutions.kcgl.MeJiGouKcglKctjBean;
import com.zhangju.xingquban.interestclassapp.ui.fragment.me.Institutions.kcgl.MeJiGouKcglKctjCityBean;
import com.zhangju.xingquban.interestclassapp.ui.fragment.me.Institutions.spkc.MeJiGouSpkcSpjBean;
import com.zhangju.xingquban.interestclassapp.ui.fragment.me.Institutions.xcgl.MeJiGouXcglScspSpjBean;
import com.zhangju.xingquban.interestclassapp.ui.fragment.me.Institutions.xcgl.MeJiGouXcglTpBean;
import com.zhangju.xingquban.interestclassapp.ui.fragment.me.Message.MeXXBean;
import com.zhangju.xingquban.interestclassapp.ui.fragment.me.user.MeUserBean;
import com.zhangju.xingquban.refactoring.entity.BaseResponseBean;
import com.zhangju.xingquban.refactoring.entity.LessonsManagerBean;

import java.io.File;
import java.util.Map;

import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;
import rx.Observable;

/**
 * Created by zsl on 2017/7/18.
 */

public interface IMe {

//    //    修改个人资料
//    @POST("admnxzcmr/customer/add.json")
//    Observable<Object> updateMeinfo(
//            @Query("sessionId") String sessionId,
//            @Query("id") String id,
//            @Query("phone") String phone,
//            @Query("sex") String sex,
//            @Query("age") Integer age,
//            @Query("signame") String signame,
//            @Query("name"), String name,
//            @Query("picture") String picture,
//            @Query("summary") String summary,
//            @Query("points") Double points,
//            @Query("balances") Double balances
//
//
//    );

    //开通vip
    @POST("admnxzcmr/members/pay.json")
    Observable<Object> getMeKTVip(
            @Query("payType") String payType,
            @Query("amount") double amount
    );

    //个人信息
    @POST("admnxzcmr/customer/ls.json")
    Observable<MeUserBean> getMeGRMessage();

    //我的消息
    @POST("admnxzcmr/push/ls.json")
    Observable<MeXXBean> getMeMessage(
//            @Query("customerId") String customerId,
//            @Query("resourcesId") String resourcesId
    );

    //我的评论
    @POST("admnxzcmr/teacherComment/ls.json")
    Observable<MeCommentBean> getMeComment(
            @Query("customerId") String customerId,
            @Query("resourcesId") String resourcesId
    );

    //添加机构简介
    @POST("admnxzcmr/organ/add.json")
    Observable<Object> organ(
            @Query("addUserId") String addUserId,
            @Query("intro") String intro
    );

    //添加机构简介图片
    @POST("admnxzcmr/organ/upload.json")
    Observable<Object> organUpload(
            @Query("files") File files
    );

    //课程的添加和修改（改）
    @POST("admnxzcmr/lesson/add.json")
    Observable<MeJiGouKcglKctjBean> lesson(
            @Query("teacherTimeId") String teacherTimeId,
            @Query("name") String name,
            @Query("timelength") String timelength,
            @Query("price") String price,
            @Query("vipPrice") String vipPrice,
            @Query("picture") String picture,
            @Query("resId") String resId,
            @Query("isCantry") boolean isCantry,
            @Query("allows") Integer allows,
            @Query("methodType") Integer methodType,
            @Query("region") String region,
            @Query("classRoom") String classRoom,
            @Query("lessonDate") Data lessonDate,
            @Query("descript") String descript,
            @Query("summary") byte[] summary
    );

    //添加机构简介图片
    @POST("admnxzcmr/provinces/ls.json")
    Observable<MeJiGouKcglKctjCityBean> city(
            @Query("pageIndex") int pageIndex,
            @Query("pageSize") String pageSize
    );

    //添加机构简介图片
    @POST("admnxzcmr/cities/ls.json")
    Observable<MeJiGouKcglKctjCityBean> city2(
            @Query("pid") String pid,
            @Query("pageIndex") int pageIndex,
            @Query("pageSize") String pageSize
    );

    //课程安排的添加（改）
    @POST("admnxzcmr/plan2/add.json")
    Observable<Object> plan2(
            @Query("teacherTimeId") String teacherTimeId,
            @QueryMap(encoded = true) Map<String, String> map
    );

    //查询照片或者视频
    @POST("admnxzcmr/organAlbumFiles/ls.json")
    Observable<MeJiGouSpkcSpjBean> organAlbumls(
            @Query("customerId") String customerId,
            @Query("isPic") Integer isPic,
            @Query("type") String type
    );

    //查询照片或者视频
    @POST("admnxzcmr/organAlbumFiles/ls.json")
    Observable<MeJiGouXcglTpBean> organAlbumlsPhoto(
            @Query("customerId") String customerId,
            @Query("isPic") Integer isPic,
            @Query("type") String type
    );

    //添加视频课程
    @POST("admnxzcmr/organVideo/add.json")
    Observable<Object> organVideo(
            @Query("customerId") String customerId,
            @Query("title") String title,
            @Query("comment") String comment,
            @Query("contactWay") String contactWay,
            @Query("videoStr") String videoStr,
            @Query("isCharge") Integer isCharge,
            @Query("price") Integer price,
            @Query("address") String address,
            @Query("latitude") String latitude,
            @Query("longitude") String longitude,
            @Query("videoTitlePic") String videoTitlePic,
            @Query("videoFilesId") String videoFilesId
    );

    //查询照片或者视频
    @POST("admnxzcmr/videoFiles/ls.json")
    Observable<MeJiGouXcglScspSpjBean> videoFiles(
            @Query("customerId") String customerId
//            @Query("id") Integer id
    );

    //查询我的收藏 老师/机构
    @POST("admnxzcmr/activityCollection/ls.json")
    Observable<MeCollectAllBean> activityCollection(
            @Query("customerId") String customerId,
            @Query("lng") String lng,
            @Query("lat") String lat,
            @Query("collectionTypes") String collectionTypes);

    //资源管理--资源添加    isCharge  /*是否收费 0.不收费（默认），1.收费*/
    @POST("admnxzcmr/myResources/add.json")
    Observable<ResouceUpBean> uploadresourceManager(
            @Query("title") String title,
            @Query("customerId") String customerId,
            @Query("types") String types,
            @Query("titlePicture") String titlePicture,
            @Query("price") int price,
            @Query("isCharge") Integer isCharge,
            @Query("muitlUploadPicture") String muitlUploadPicture,
            @Query("videoUrl") String videoUrl,
            @Query("summary") String summary,
            @Query("address") String address,
            @Query("latitude") String latitude,
            @Query("longitude") String longitude

    );

    //资源管理--资源列表展示    isCharge 0.不收费（默认），1.收费
    @POST("admnxzcmr/myResources/ls.json")
    Observable<Object> searchResList(
            @Query("customerId") String customerId,
            @Query("isCharge") Integer isCharge
    );

    @GET("admnxzcmr/teacher/ls.json")
    Observable<BaseResponseBean<LessonsManagerBean>> getLessonManager(@Query("id") String id);

    @POST("admnxzcmr/lesson/del.json")
    Observable<BaseResponseBean<String>> deleteLesson(@Query("id") String id, @Query("teacherTimeId") String teacherTimeId);
}
