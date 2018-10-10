package com.zhangju.xingquban.interestclassapp.RetrofitInterface;

import com.zhangju.xingquban.interestclassapp.bean.ResDeatailTopBean;
import com.zhangju.xingquban.interestclassapp.bean.ResDetailBean;
import com.zhangju.xingquban.interestclassapp.bean.ResouecesAll;
import com.zhangju.xingquban.refactoring.entity.BaseResponseBean;


import java.util.List;

import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;
import rx.Observable;
import rx.Observer;

/**
 * Created by zsl on 2017/6/22.
 */

public interface GetReources {
    @POST("admnxzcmr/resources/ls.json")
    Observable<ResouecesAll> getReourceslist(@Query("pageIndex") String pageIndex,
                                             @Query("pageSize") String pageSize,
                                             @Query("types") String types,
                                             @Query("allStringQuery") String search
    );

    @GET("admnxzcmr/resources/ls.json")
    Observable<BaseResponseBean<List<ResDeatailTopBean.AaDataBean>>> getResourceDetailsTopData(@Query("id") String id,
                                                                                               @Query("pageIndex") String pageIndex,
                                                                                               @Query("pageSize") String pageSize);

    @GET("admnxzcmr/comment/ls.json")
    Observable<BaseResponseBean<List<ResDetailBean.AaDataBean>>> getResourceDetailsCommentData(@Query("resourcesId") String resourcesId,
                                                                                               @Query("pageIndex") String pageIndex,
                                                                                               @Query("pageSize") String pageSize);

    @POST("admnxzcmr/links/add.json")
    Observable<BaseResponseBean<Object>> resourcePraised(@Query("resourcesId") String resourcesId);

    @POST("admnxzcmr/collection/add.json")
    Observable<BaseResponseBean<Object>> userCollection(@Query("customerId") String customerId,
                                                        @Query("resourcesId") String resourcesId,
                                                        @Query("types") String types,
                                                        @Query("collectionTypes") String collectionTypes
    );

    @POST("admnxzcmr/comment/add.json")
    Observable<BaseResponseBean<Object>> resourceComment(@Query("resourcesId") String resourcesId,
                                                         @Query("summary") String summary
    );


    @POST("admnxzcmr/resources/ls.json")
    Observable<ResouecesAll> getAudioReourceslist(@Query("pageIndex") String pageIndex,
                                                  @Query("pageSize") String pageSize,
                                                  @Query("types") String types
    );

    @POST("admnxzcmr/resources/ls.json")
    Observable<ResouecesAll> getArticleReourceslist(@Query("pageIndex") String pageIndex,
                                                    @Query("pageSize") String pageSize,
                                                    @Query("types") String types,
                                                    @Query("allStringQuery") String search
    );

}
