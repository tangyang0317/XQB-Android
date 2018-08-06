package com.zhangju.xingquban.interestclassapp.RetrofitInterface;


import com.zhangju.xingquban.interestclassapp.bean.live.LiveStartBean;

import retrofit2.Call;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * Created by Administrator on 2017/6/25.
 */

public interface InterfaceLiveStart {

    @POST("admnxzcmr/rooms/add.json")
    Call<LiveStartBean> getLiveStart(
            @Header("X-CustomToken") String mToken,
//            @Query("jsessionid") String mToken,

            @Query("roomName") String roomName,
            @Query("roomPic") String roomPic
    );
}
