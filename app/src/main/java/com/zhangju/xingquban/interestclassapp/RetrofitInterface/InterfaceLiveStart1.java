package com.zhangju.xingquban.interestclassapp.RetrofitInterface;

import com.zhangju.xingquban.interestclassapp.bean.live.LiveStartBean;

import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Query;
import rx.Observable;


/**
 * Created by Administrator on 2017/6/25.
 */

public interface InterfaceLiveStart1 {
	/**
	 * 获取推流地址
	 * @param roomName
	 * @param roomPic
	 * @param amcout
	 * @return
	 */
    @POST("admnxzcmr/rooms/add.json")
	Observable<LiveStartBean> getLiveStartUrl(
		 	@Header("X-CustomToken") String mToken,
			@Query("roomName") String roomName,
			@Query("roomPic") String roomPic,
			@Query("amount") String amcout

	);
}
