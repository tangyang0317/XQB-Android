package com.zhangju.xingquban.interestclassapp.RetrofitInterface;

import com.zhangju.xingquban.interestclassapp.bean.RefisterBean;

import retrofit2.Call;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * Created by zsl on 2017/6/22.
 */

public interface RegisterUserBean {
    @POST("admnxzcmr/customer/register.json")
    Call<RefisterBean> getRegister(@Query("phone") String username1,
                                   @Query("confirmPassword") String conPassword,
                                   @Query("varCode") String captcha,
                                   @Query("password") String password
    );

}
