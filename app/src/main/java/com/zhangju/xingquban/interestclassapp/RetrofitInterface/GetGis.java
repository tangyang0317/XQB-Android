package com.zhangju.xingquban.interestclassapp.RetrofitInterface;

import retrofit2.http.POST;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by zsl on 2017/7/6.
 */

public interface GetGis {

    @POST("admnxzcmr/gis/ls.json")
    Observable<Object> getGislocation(  @Query("iDisplayLength") String dispalyLength,
                                        @Query("lng") String lng,
                                        @Query("lat") String lat,
                                        @Query("level") String level);

}
