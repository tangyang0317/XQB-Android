package com.zhangju.xingquban.interestclassapp.RetrofitInterface;

import com.zhangju.xingquban.interestclassapp.bean.HomeViewPage;

import retrofit2.http.POST;
import rx.Observable;

/**
 * Created by Administrator on 2017/7/4.
 */

public interface InterfaceHomeViewPage {
    @POST("admnxzcmr/catagories/loadnode.json")
    Observable<HomeViewPage> getHomeViewPage();
}
