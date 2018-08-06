package com.zhangju.xingquban.interestclassapp.RetrofitInterface;

import com.zhangju.xingquban.interestclassapp.bean.HomeRecylerBean;
import com.zhangju.xingquban.interestclassapp.bean.NearDataBean;
import com.zhangju.xingquban.interestclassapp.bean.NearMSRight;
import com.zhangju.xingquban.interestclassapp.bean.NearSubjectBean;
import com.zhangju.xingquban.interestclassapp.bean.near.CurriculumBean;
import com.zhangju.xingquban.interestclassapp.bean.near.NearDistrictBean;

import java.util.Map;

import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;
import rx.Observable;

/**
 * Created by zsl on 2017/7/6.
 */

public interface INear {

/*
    @POST("admnxzcmr/teacher/ls.json")
    Observable<NearBean> getNearData();
*/
    /**
     * 机构管理中添加或修改视频和相册
     * in:title,comment,isPic,picVideo,latitude,longitude,videoTitlePic,intro,customerId,type
     * out:
     */
    String POST_COMMENT_ADD="/teacherComment/add.json";
    String POST_COMMNET_ALL="/teacherComment/ls.json";
    String POST_COMMEMT_COMM="/commentcomment/add.json";
    String POST_COMMENT_ZAN="/thumbComment/add.json";
    String POST_VIDEO_COMMENT="/OrganVideoComment/ls.json";
    String POST_VIDEO_ADDCOMMNENT="/OrganVideoComment/add.json";
    String POST_JIGOUXIANGQING="/organ/ls.json";
    String POST_ORDER_CURRICULUM="/orders/add.json";
    String POST_ORDERS_PAY="/orders/pay.json";
    String POST_ORDERS_YUE="/paytype/ls.json";
    String POST_VIDEOLESS="/videoLesson/ls.json";
    String POST_LESSONS_XQ="/lesson/ls.json";
    /**
     * 轮播
     * in:types
     */
    String POST_BANNER="/chgpic/ls.json";
    //附近默认数据

    @POST("admnxzcmr/teacher/ls.json")
    Observable<NearDataBean> getNearDaat(@Query("lng") String lng,
                                         @Query("lat") String lat,
                                         @Query("cityId") String CityId,
                                         @Query("pageIndex") int pageIndex,
                                         @Query("pageSize") String pageSize);

    //附近老师默认数据
    @FormUrlEncoded
    @POST("admnxzcmr/teacher/ls.json")
    Observable<NearDataBean> getNearData(@Field("lng") String lng,
                                         @Field ("lat") String lat,
                                         @Field ("pageIndex") Integer pageIndex,
                                         @Field ("pageSize") String pageSize,
                                         @Field ("degreeId") String degreeId,//级别id 老师1  机构2
                                         @Field ("catagoriesIdes") Integer catagoriesId,//科目id
                                         @Field ("cityId") Integer areasId,//地区ID
                                         @Field ("radius")Integer radius,//半径
                                         @Field("sortJson")String sortJson,//排序json
                                         @Field("avgScore")Boolean avgScore,//精选
                                         @Field("allStringQuery")String allStringQuery//精选



    );
    //获取课程数据
    @FormUrlEncoded
    @POST("admnxzcmr/lesson/ls.json")
    Observable<CurriculumBean> getCurriculumData(
                                        @Field("id")String id,//课程ID
                                        @Field("lng") String lng,
                                         @Field ("lat") String lat,
                                         @Field ("pageIndex") Integer pageIndex,
                                         @Field ("pageSize") String pageSize,
                                         @Field ("categoriesId") Integer catagoriesId,//科目id
                                         @Field ("cityId") Integer areasId,//地区ID
                                         @Field ("radius")Integer radius,//半径
                                         @Field("sortJson")String sortJson//排序json
    );

    //附近老师数据
    @POST("admnxzcmr/teacher/ls.json")
    Observable<NearDataBean> getNearTeacher(@Query("lng") String lng, @Query("lat") String lat, @Query("cityId") String CityId,
                                            @Query("degreeId") String degreeId);

    //附近机构数据
    @POST("admnxzcmr/teacher/ls.json")
    Observable<NearDataBean> getNearInstitution(@Query("lng") String lng, @Query("lat") String lat, @Query("cityId") String CityId,
                                                @Query("degreeId") String degreeId);

    //附近科目列表'左'数据
    @POST("admnxzcmr/catagories/loadnode.json")
    Observable<NearSubjectBean> getNearSubject();

    //附近科目列表'右'数据
    @POST("admnxzcmr/catagories/ls.json")
    Observable<NearMSRight> getNearMSRight();

    /*获取全部科目数据*/
    @POST("admnxzcmr/catagories/ls.json")
    Observable<NearSubjectBean> getKemuAllData();

    //附近科目列表'右'数据
    @POST("admnxzcmr/catagories/ls.json")
    Observable<NearMSRight> getNearMSRight1(@Query("id") String id);

    //附近区域列表数据
    @POST("admnxzcmr/areas/ls.json")
    Observable<NearDistrictBean> getNearDistrict(@Query("pid") String pid);

    //半径范围内搜索
    @POST("/admnxzcmr/customer/search.json")
    Observable<NearDistrictBean> getNearradius(@Query("lng") String lng, @Query("lat") String lat,
                                               @Query("radius") String radius
    );



    //分页
    @POST("admnxzcmr/teacher/ls.json")
    Observable<NearDataBean> getNearDaatPtf(@Query("pageIndex") int pageIndex, @Query("pageSize") String pageSize);

    //首页分页
    @POST("admnxzcmr/teacher/ls.json")
    Observable<HomeRecylerBean> getHomeDaatPtf(@Query("pageIndex") int pageIndex, @Query("pageSize") String pageSize);

    /*
   * 有条件进行查询，获取附近页面数据
   * */
    @POST("admnxzcmr/teacher/ls.json")
    Observable<NearDataBean> getNearDatasCondition(@QueryMap Map<String, String> map);
}
