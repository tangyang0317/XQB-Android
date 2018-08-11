package com.zhangju.xingquban.interestclassapp.RetrofitInterface;

import android.text.TextUtils;

import com.zhangju.xingquban.interestclassapp.RetrofitInterface.live.AttentionLive;
import com.zhangju.xingquban.interestclassapp.refactor.user.UserManager;
import com.zhangju.xingquban.interestclassapp.util.BaseUrl;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.CallAdapter;
import retrofit2.Converter;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * 项目名称：InterestClassApp
 * Created by Dr. Zhu on 2017/7/3.
 */

public class NetWork {
    private static OkHttpClient.Builder builder = new OkHttpClient.Builder()
            .connectTimeout(150, TimeUnit.SECONDS)
            .writeTimeout(120, TimeUnit.SECONDS)
            .readTimeout(120, TimeUnit.SECONDS);
    private static OkHttpClient okHttpClient = builder.build();
    private static Converter.Factory gsonConverterFactory = GsonConverterFactory.create();
    private static CallAdapter.Factory rxJavaCallAdapterFactory = RxJavaCallAdapterFactory.create();

    private static InterfaceLogin interfaceLogin;
    private static RegisterUserBean registerUserBean;
    private static GetReources getReources;
    private static InterfaceHomeViewPage interfaceHomeViewPage;
    private static IHomeFragment iHomeFragment;
    private static ICityName iCityName;
    private static INear iNear;
    private static IMe iMe;
    private static IFind iFind;
    private static InterfaceLiveStart1 interfaceLiveStart1;
    private static GetUserInfo getUserInfo;
    private static AttentionLive attentionLive;

    /*Demo*/
    public static InterfaceLogin mylogin() {

        if (interfaceLogin == null) {

            Retrofit retrofit = new Retrofit.Builder()
                    .client(okHttpClient)
                    .baseUrl(BaseUrl.url)
                    .addConverterFactory(gsonConverterFactory)
                    .addCallAdapterFactory(rxJavaCallAdapterFactory)
                    .build();
            interfaceLogin = retrofit.create(InterfaceLogin.class);
        }

        return interfaceLogin;

    }

    /**
     * 带有token请求头的Demo
     *
     * @return
     */
    public static GetUserInfo getuserData() {
        /*添加请求头*/
        OkHttpClient addfavclirentkey = new OkHttpClient.Builder()
                .addInterceptor(new Interceptor() {
                    @Override//pass the headers
                    public Response intercept(Chain chain) throws IOException {
                        Request request = chain.request()
                                .newBuilder()
                                .addHeader("X-CustomToken",UserManager.getInstance().getToken())
                                .build();
                        return chain.proceed(request);
                    }

                })
                .build();
        if (getUserInfo == null) {
            Retrofit retrofit = new Retrofit.Builder()
                    .client(UserManager.getInstance().getToken()== null ? okHttpClient : addfavclirentkey)
                    .baseUrl(BaseUrl.url)
                    .addConverterFactory(gsonConverterFactory)
                    .addCallAdapterFactory(rxJavaCallAdapterFactory)
                    .build();
            getUserInfo = retrofit.create(GetUserInfo.class);
        }

        return getUserInfo;

    }

    /*Demo*/
    public static GetReources getReources() {

        OkHttpClient addfavclirentkey = new OkHttpClient.Builder()
                .addInterceptor(new Interceptor() {
                    @Override//pass the headers
                    public Response intercept(Chain chain) throws IOException {
                        Request request = chain.request()
                                .newBuilder()
                                .addHeader("X-CustomToken",UserManager.getInstance().getToken())
                                .build();
                        return chain.proceed(request);
                    }

                })
                .build();

        if (getReources == null) {

            Retrofit retrofit = new Retrofit.Builder()
                    .client(UserManager.getInstance().getToken()== null ? okHttpClient : addfavclirentkey)
                    .baseUrl(BaseUrl.url)
                    .addConverterFactory(gsonConverterFactory)
                    .addCallAdapterFactory(rxJavaCallAdapterFactory)
                    .build();
            getReources = retrofit.create(GetReources.class);
        }

        return getReources;

    }

    //首页课程选择
    public static InterfaceHomeViewPage getHomeViewPage() {
        if (interfaceHomeViewPage == null) {
            Retrofit retrofit = new Retrofit.Builder()
                    .client(okHttpClient)
                    .baseUrl(BaseUrl.url)
                    .addConverterFactory(gsonConverterFactory)
                    .addCallAdapterFactory(rxJavaCallAdapterFactory)
                    .build();
            interfaceHomeViewPage = retrofit.create(InterfaceHomeViewPage.class);
        }
        return interfaceHomeViewPage;
    }

    //首页轮播
    public static IHomeFragment getHomeBanner() {
           /*添加请求头*/
        OkHttpClient addfavclirentkey = new OkHttpClient.Builder()
                .addInterceptor(new Interceptor() {
                    @Override//pass the headers
                    public Response intercept(Chain chain) throws IOException {
                        Request request = chain.request()
                                .newBuilder()
                                .addHeader("X-CustomToken", TextUtils.isEmpty(UserManager.getInstance().getToken())?"":UserManager.getInstance().getToken())
                                .build();
                        return chain.proceed(request);
                    }

                })
                .build();
        if (iHomeFragment == null) {
            Retrofit retrofit = new Retrofit.Builder()
                    .client(UserManager.getInstance().getToken()== null ? okHttpClient : addfavclirentkey)
                    .client(okHttpClient)
                    .baseUrl(BaseUrl.url)
                    .addConverterFactory(gsonConverterFactory)
                    .addCallAdapterFactory(rxJavaCallAdapterFactory)
                    .build();
            iHomeFragment = retrofit.create(IHomeFragment.class);
        }
        return iHomeFragment;
    }

    //城市列表
    public static ICityName getICityName() {
        if (iCityName == null) {
            Retrofit retrofit = new Retrofit.Builder()
                    .client(okHttpClient)
                    .baseUrl(BaseUrl.url)
                    .addConverterFactory(gsonConverterFactory)
                    .addCallAdapterFactory(rxJavaCallAdapterFactory)
                    .build();
            iCityName = retrofit.create(ICityName.class);
        }
        return iCityName;
    }

    //附近列表查询
    public static INear getNearData() {
        if (iNear == null) {
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(BaseUrl.url)
                    .addConverterFactory(gsonConverterFactory)
                    .addCallAdapterFactory(rxJavaCallAdapterFactory)
                    .build();
            iNear = retrofit.create(INear.class);
        }
        return iNear;
    }

    //附近列表数据
    public static INear getNear() {
        if (iNear == null) {
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(BaseUrl.url)
                    .addConverterFactory(gsonConverterFactory)
                    .addCallAdapterFactory(rxJavaCallAdapterFactory)
                    .build();
            iNear = retrofit.create(INear.class);

        }
        return iNear;

    }

    //附近老师列表数据
    public static INear getNearTeacher() {
        if (iNear == null) {
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(BaseUrl.url)
                    .addConverterFactory(gsonConverterFactory)
                    .addCallAdapterFactory(rxJavaCallAdapterFactory)
                    .build();
            iNear = retrofit.create(INear.class);
        }
        return iNear;

    }

    //附近机构列表数据
    public static INear getNearInstitution() {
        if (iNear == null) {
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(BaseUrl.url)
                    .addConverterFactory(gsonConverterFactory)
                    .addCallAdapterFactory(rxJavaCallAdapterFactory)
                    .build();
            iNear = retrofit.create(INear.class);

        }
        return iNear;

    }

    //附近机构列表数据分页
    public static INear getNearDatapsf() {
        if (iNear == null) {
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(BaseUrl.url)
                    .addConverterFactory(gsonConverterFactory)
                    .addCallAdapterFactory(rxJavaCallAdapterFactory)
                    .build();
            iNear = retrofit.create(INear.class);

        }
        return iNear;

    }

    //附近科目列表数据
    public static INear getNearSubject() {
        if (iNear == null) {
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(BaseUrl.url)
                    .addConverterFactory(gsonConverterFactory)
                    .addCallAdapterFactory(rxJavaCallAdapterFactory)
                    .build();
            iNear = retrofit.create(INear.class);

        }
        return iNear;

    }




    //首页城市返回id
    public static IHomeFragment getHomeCityId() {
           /*添加请求头*/
        OkHttpClient addfavclirentkey = new OkHttpClient.Builder()
                .addInterceptor(new Interceptor() {
                    @Override//pass the headers
                    public Response intercept(Chain chain) throws IOException {
                        Request request = chain.request()
                                .newBuilder()
                                .addHeader("X-CustomToken",UserManager.getInstance().getToken())
                                .build();
                        return chain.proceed(request);
                    }

                })
                .build();
        if (iHomeFragment == null) {
            Retrofit retrofit = new Retrofit.Builder()
                    .client(UserManager.getInstance().getToken()== null ? okHttpClient : addfavclirentkey)
                    .baseUrl(BaseUrl.url)
                    .addConverterFactory(gsonConverterFactory)
                    .addCallAdapterFactory(rxJavaCallAdapterFactory)
                    .build();
            iHomeFragment = retrofit.create(IHomeFragment.class);
        }
        return iHomeFragment;
    }

    //首页城市数据
    public static IHomeFragment getHomeData() {
           /*添加请求头*/
        OkHttpClient addfavclirentkey = new OkHttpClient.Builder()
                .addInterceptor(new Interceptor() {
                    @Override//pass the headers
                    public Response intercept(Chain chain) throws IOException {
                        Request request = chain.request()
                                .newBuilder()
                                .addHeader("X-CustomToken",UserManager.getInstance().getToken())
                                .build();
                        return chain.proceed(request);
                    }

                })
                .build();
        if (iHomeFragment == null) {
            Retrofit retrofit = new Retrofit.Builder()
                    .client(UserManager.getInstance().getToken()== null ? okHttpClient : addfavclirentkey)
                    .baseUrl(BaseUrl.url)
                    .addConverterFactory(gsonConverterFactory)
                    .addCallAdapterFactory(rxJavaCallAdapterFactory)
                    .build();
            iHomeFragment = retrofit.create(IHomeFragment.class);

        }
        return iHomeFragment;
    }

    //获取附近美术右数据
    public static INear getNearMSRight() {
        if (iNear == null) {

            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(BaseUrl.url)
                    .addConverterFactory(gsonConverterFactory)
                    .addCallAdapterFactory(rxJavaCallAdapterFactory)
                    .build();

            iNear = retrofit.create(INear.class);
        }
        return iNear;
    }

    //获取附近区域名称
    public static INear getNearDistrict() {
        if (iNear == null) {

            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(BaseUrl.url)
                    .addConverterFactory(gsonConverterFactory)
                    .addCallAdapterFactory(rxJavaCallAdapterFactory)
                    .build();

            iNear = retrofit.create(INear.class);
        }
        return iNear;
    }

    //获取附近区域名称
    public static INear getNearHomeLise() {
        if (iNear == null) {

            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(BaseUrl.url)
                    .addConverterFactory(gsonConverterFactory)
                    .addCallAdapterFactory(rxJavaCallAdapterFactory)
                    .build();

            iNear = retrofit.create(INear.class);
        }
        return iNear;
    }

    //获取推流地址
    public static InterfaceLiveStart1 getLiveUpUrl() {
        /*添加请求头*/
        OkHttpClient addfavclirentkey = new OkHttpClient.Builder()
                .addInterceptor(new Interceptor() {
                    @Override//pass the headers
                    public Response intercept(Chain chain) throws IOException {
                        Request request = chain.request()
                                .newBuilder()
                                .addHeader("X-CustomToken",UserManager.getInstance().getToken())
                                .build();
                        return chain.proceed(request);
                    }

                })
                .build();
        if (interfaceLiveStart1 == null) {
            Retrofit retrofit = new Retrofit.Builder()
                    .client(!UserManager.getInstance().isLogin() ? okHttpClient : addfavclirentkey)
                    .baseUrl(BaseUrl.url)
                    .addConverterFactory(gsonConverterFactory)
                    .addCallAdapterFactory(rxJavaCallAdapterFactory)
                    .build();

            interfaceLiveStart1 = retrofit.create(InterfaceLiveStart1.class);
        }
        return interfaceLiveStart1;
    }

    /*获取关注的直播间列表*/
    public static AttentionLive getAttentionLiveList() {
          /*添加请求头*/
       final String token = UserManager.getInstance().getToken();
        OkHttpClient addfavclirentkey = new OkHttpClient.Builder()
                .addInterceptor(new Interceptor() {
                    @Override//pass the headers
                    public Response intercept(Chain chain) throws IOException {
                        Request request = chain.request()
                                .newBuilder()
                                .addHeader("X-CustomToken", token)
                                .build();
                        return chain.proceed(request);
                    }

                })
                .build();
        if (attentionLive == null) {
            Retrofit retrofit = new Retrofit.Builder()
                    .client(!UserManager.getInstance().isLogin()? okHttpClient : addfavclirentkey)
                    .baseUrl(BaseUrl.url)
                    .addConverterFactory(gsonConverterFactory)
                    .addCallAdapterFactory(rxJavaCallAdapterFactory)
                    .build();

            attentionLive = retrofit.create(AttentionLive.class);
        }
        return attentionLive;
    }

    //获取推流地址
    public static InterfaceLiveStart1 getMeIconUpload() {
        /*添加请求头*/
        OkHttpClient addfavclirentkey = new OkHttpClient.Builder()
                .addInterceptor(new Interceptor() {
                    @Override//pass the headers
                    public Response intercept(Chain chain) throws IOException {
                        Request request = chain.request()
                                .newBuilder()
                                .addHeader("X-CustomToken",UserManager.getInstance().getToken())
                                .build();
                        return chain.proceed(request);
                    }

                })
                .build();
        if (interfaceLiveStart1 == null) {
            Retrofit retrofit = new Retrofit.Builder()
                    .client(!UserManager.getInstance().isLogin() ? okHttpClient : addfavclirentkey)
                    .baseUrl(BaseUrl.url)
                    .addConverterFactory(gsonConverterFactory)
                    .addCallAdapterFactory(rxJavaCallAdapterFactory)
                    .build();

            interfaceLiveStart1 = retrofit.create(InterfaceLiveStart1.class);
        }
        return interfaceLiveStart1;
    }


    //我的提问
    public static IFind getMeTiwen() {
        /*添加请求头*/
      final   String token = UserManager.getInstance().getToken();

        OkHttpClient addfavclirentkey = new OkHttpClient.Builder()
                .addInterceptor(new Interceptor() {
                    @Override//pass the headers
                    public Response intercept(Chain chain) throws IOException {
                        Request request = chain.request()
                                .newBuilder()
                                .addHeader("X-CustomToken", token)
                                .build();
                        return chain.proceed(request);
                    }

                })
                .build();

        if (iFind == null) {
            Retrofit retrofit = new Retrofit.Builder()
                    .client(!UserManager.getInstance().isLogin()? okHttpClient : addfavclirentkey)
                    .baseUrl(BaseUrl.url)
                    .addConverterFactory(gsonConverterFactory)
                    .addCallAdapterFactory(rxJavaCallAdapterFactory)
                    .build();

            iFind = retrofit.create(IFind.class);
        }
        return iFind;
    }

    //提问数据加载
    public static IFind getFindTiWenData() {
        if (iFind == null) {

            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(BaseUrl.url)
                    .addConverterFactory(gsonConverterFactory)
                    .addCallAdapterFactory(rxJavaCallAdapterFactory)
                    .build();

            iFind = retrofit.create(IFind.class);
        }
        return iFind;
    }

    //开通vip
    public static IMe getMeKTVip() {
        /*添加请求头*/
        OkHttpClient addfavclirentkey = new OkHttpClient.Builder()
                .addInterceptor(new Interceptor() {
                    @Override//pass the headers
                    public Response intercept(Chain chain) throws IOException {
                        Request request = chain.request()
                                .newBuilder()
                                .addHeader("X-CustomToken",UserManager.getInstance().getToken())
                                .build();
                        return chain.proceed(request);
                    }

                })
                .build();
        if (iMe == null) {
            Retrofit retrofit = new Retrofit.Builder()
                    .client(!UserManager.getInstance().isLogin() ? okHttpClient : addfavclirentkey)
                    .baseUrl(BaseUrl.url)
                    .addConverterFactory(gsonConverterFactory)
                    .addCallAdapterFactory(rxJavaCallAdapterFactory)
                    .build();

            iMe = retrofit.create(IMe.class);
        }
        return iMe;
    }

    //我
    public static IMe getMe() {
        /*添加请求头*/
        OkHttpClient addfavclirentkey = new OkHttpClient.Builder()
                .addInterceptor(new Interceptor() {
                    @Override//pass the headers
                    public Response intercept(Chain chain) throws IOException {
                        Request request = chain.request()
                                .newBuilder()
                                .addHeader("X-CustomToken",UserManager.getInstance().getToken())
                                .build();
                        return chain.proceed(request);
                    }

                })
                .build();
        if (iMe == null) {
            Retrofit retrofit = new Retrofit.Builder()
                    .client(UserManager.getInstance().getToken()== null ? okHttpClient : addfavclirentkey)
                    .baseUrl(BaseUrl.url)
                    .addConverterFactory(gsonConverterFactory)
                    .addCallAdapterFactory(rxJavaCallAdapterFactory)
                    .build();

            iMe = retrofit.create(IMe.class);
        }
        return iMe;
    }

}
