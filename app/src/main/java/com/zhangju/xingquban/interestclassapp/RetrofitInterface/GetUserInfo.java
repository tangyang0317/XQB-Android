package com.zhangju.xingquban.interestclassapp.RetrofitInterface;

import com.zhangju.xingquban.interestclassapp.bean.CityConvertBean;
import com.zhangju.xingquban.interestclassapp.bean.UserBean.UserInfoBean;

import retrofit2.http.POST;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by zsl on 2017/6/22.
 */

public interface GetUserInfo {
//

    @POST("admnxzcmr/customer/ls.json")
    Observable<UserInfoBean> getUserData(@Query("sessionId") String sessionId);

    @POST("admnxzcmr/gis/ls.json")
    Observable<CityConvertBean> getConvertLocation(@Query("lat") String lat,
                                                   @Query("lng") String lng,
                                                   @Query("level") String city);


}
