package com.zhangju.xingquban.interestclassapp.RetrofitInterface.live;

import com.zhangju.xingquban.interestclassapp.bean.live.LiveAttentionListBean;
import com.zhangju.xingquban.interestclassapp.bean.live.LiveListBean;

import retrofit2.http.POST;
import retrofit2.http.Query;
import rx.Observable;


/**
 * Created by zsl on 2017/6/22.
 */

public interface AttentionLive {
	//直播关注列表数据
	@POST("admnxzcmr/liveRecord/like.json")
	Observable<LiveAttentionListBean> getAttentionLive(@Query("pageIndex") String pageIndex,
													   @Query("pageSize") String pageSize );

	@POST("admnxzcmr/rooms/ls.json")
	Observable<LiveListBean> getHotLive(@Query("pageIndex") String pageIndex,
										@Query("pageSize") String pageSize
	);
}
