package com.zhangju.xingquban.interestclassapp.RetrofitInterface;

import com.zhangju.xingquban.interestclassapp.bean.UserBean.LoginUserResponse;

import retrofit2.http.POST;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by zsl on 2017/6/22.
 */

public interface InterfaceLogin {
    @POST("admnxzcmr/customer/login.json")
    Observable<LoginUserResponse> getLogin(@Query("phone") String username,
                                           @Query("password") String password,
                                           @Query("Rtn_Token") String token);
}
