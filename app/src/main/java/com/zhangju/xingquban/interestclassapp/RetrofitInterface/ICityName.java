package com.zhangju.xingquban.interestclassapp.RetrofitInterface;

import com.zhangju.xingquban.interestclassapp.bean.CityNameBean;

import retrofit2.http.POST;
import rx.Observable;

/**
 * Created by zsl on 2017/7/6.
 */

public interface ICityName {

    @POST("admnxzcmr/provinces/pinyinlist.json")
    Observable<CityNameBean> getCityName();


    @POST("admnxzcmr/hotcity/ls.json")
    Observable<CityNameBean> getHotCity();




}
