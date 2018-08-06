package com.zhangju.xingquban.interestclassapp.RetrofitInterface;

import com.zhangju.xingquban.interestclassapp.bean.HomeBannerBean;
import com.zhangju.xingquban.interestclassapp.bean.HomeCityIdBaen;
import com.zhangju.xingquban.interestclassapp.ui.fragment.home.HomeDataTeacherBean;

import retrofit2.http.POST;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by zsl on 2017/7/5.
 */

public interface IHomeFragment {

    @POST("admnxzcmr/chgpic/ls.json")
    Observable<HomeBannerBean> getHomeBanner(@Query("CityId") String cityid,
                                             @Query("types") String types);

    @POST("admnxzcmr/cities/ls.json")
    Observable<HomeCityIdBaen> getHomeCityId(@Query("name") String name);


    @POST("admnxzcmr/teacher/ls.json")
    Observable<HomeDataTeacherBean> getHomeDataTeacher(
            @Query("cityId") String cityId,
            @Query("lng") String lng,
            @Query("lat") String lat,
            @Query("id") String id

    );

    @POST("admnxzcmr/organCollection/add.json")
    Observable<Object> getHomeDataSc(
            @Query("customerId") String customerId,
            @Query("resourcesId") String resourcesId,
            @Query("collectionTypes") String collectionTypes,
            @Query("teacherCustomerId") String teacherCustomerId
    );
}
