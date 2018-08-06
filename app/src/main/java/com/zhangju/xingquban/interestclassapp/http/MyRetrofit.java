package com.zhangju.xingquban.interestclassapp.http;


import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.zhangju.xingquban.BuildConfig;
import com.zhangju.xingquban.interestclassapp.application.MyApp;
import com.zhangju.xingquban.interestclassapp.refactor.user.UserManager;
import com.zhangju.xingquban.interestclassapp.util.BaseUrl;

import java.io.IOException;
import java.io.Serializable;
import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * 网络请求对象
 * <p>
 * Created by Wxy
 *
 * @version 1.0
 */
public class MyRetrofit implements Serializable {

    private static MyRetrofit sInstance;
    private static Retrofit.Builder sBuilder;
    private static MyNetManager.NetApi sNetApi;

    private static OkHttpClient.Builder sOkHttpBuilder;
    private static OkHttpClient sClient;


    private MyRetrofit() {

    }

    public static MyRetrofit getInstance() {
        if (null == sInstance) {
            sInstance = new MyRetrofit();
        }
        return sInstance;
    }

    public static OkHttpClient getOkHttpClient() {
        return sClient;
    }


    static {
        // OkHttp3
        sOkHttpBuilder = new OkHttpClient().newBuilder();
//        sOkHttpBuilder.sslSocketFactory(SSLClientVerify.getSSLSocketFactory());
//        sOkHttpBuilder.hostnameVerifier(new HostnameVerifier() {
//            @Override
//            public boolean verify(String hostname, SSLSession session) {
//                return true;
//            }
//        });
        if (BuildConfig.DEBUG) {
            sOkHttpBuilder.addNetworkInterceptor(new LoggingInterceptor());
        }

        sOkHttpBuilder.retryOnConnectionFailure(false);
        //默认连接超时和读写超时  为10s
//        sOkHttpBuilder.readTimeout(readTimeout_150, TimeUnit.SECONDS);
//        sOkHttpBuilder.connectTimeout(connectTimeout_150, TimeUnit.SECONDS);
        sClient = sOkHttpBuilder.build();

        sBuilder = new Retrofit.Builder();
    }

    /**
     * 设置连接超时时间
     *
     * @param timeOut
     */
    public void setConnectTimeout(long timeOut) {
        sOkHttpBuilder.connectTimeout(timeOut, TimeUnit.SECONDS);
    }

    public void setReadTimeout(long timeOut) {
        sOkHttpBuilder.readTimeout(timeOut, TimeUnit.SECONDS);
    }


    /**
     * Get NetApi instance
     *
     * @param serverUrl
     * @return
     */
    public static MyNetManager.NetApi getNetApiInstance(String serverUrl) {
        if (sNetApi == null) {
            synchronized (MyRetrofit.class) {
                if (sNetApi == null) {
                    Retrofit retrofit = sBuilder
                            .client(sClient)
                            .baseUrl(serverUrl)
                            .addConverterFactory(GsonConverterFactory.create())
                            .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                            .build();

                    sNetApi = retrofit.create(MyNetManager.NetApi.class);
                }
            }
        }
        return sNetApi;
    }

    public static MyNetManager.NetApi getNetApiInstance() {
        return getNetApiInstance(BaseUrl.url);
    }

}



