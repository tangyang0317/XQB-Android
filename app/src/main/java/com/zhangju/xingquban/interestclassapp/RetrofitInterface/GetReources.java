package com.zhangju.xingquban.interestclassapp.RetrofitInterface;

import com.zhangju.xingquban.interestclassapp.bean.ResouecesAll;


import retrofit2.http.POST;
import retrofit2.http.Query;
import rx.Observable;

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
