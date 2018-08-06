package com.zhangju.xingquban.interestclassapp.RetrofitInterface;

import com.zhangju.xingquban.interestclassapp.bean.NumberText;

import retrofit2.Call;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * Created by zsl on 2017/6/21.
 */

public interface RequestSerives {

    @POST("admnxzcmr/customer/sendRegCode.json")
    Call<NumberText> getString(@Query("types") String register,
                               @Query("phone") String username
    );

}
