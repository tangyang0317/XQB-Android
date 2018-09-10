package com.zhangju.xingquban.interestclassapp.http;

import com.zhangju.xingquban.interestclassapp.bean.NearDataBean;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import retrofit2.http.POST;
import retrofit2.http.QueryMap;
import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public final class MyNetManager implements Serializable {
    private static MyNetManager sInstance;
    private static NetApi sApi;


    public interface NetApi {
        // 获取老师详情
        @POST("admnxzcmr/teacher/ls.json")
        Observable<NearDataBean> teacherLs(@QueryMap Map<String, String> map);
    }

    public void teacherLs(HashMap<String, String> map, BaseSubscriber<NearDataBean> subscriber) {
        sApi.teacherLs(map)
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(subscriber);

    }


    public static MyNetManager getInstance() {
        if (sInstance == null) {
            sInstance = new MyNetManager();
        }
        sApi = MyRetrofit.getNetApiInstance();
        return sInstance;
    }

}
