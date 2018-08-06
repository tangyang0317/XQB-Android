package com.zhangju.xingquban.interestclassapp.util;

import android.content.Context;
import android.content.SharedPreferences;

import com.zhangju.xingquban.interestclassapp.bean.LoginBeanSuccess;

import rx.Observer;
import rx.Subscription;

/**
 * Created by Administrator on 2017/6/25.
 */

public class TokenUtils {
    private final String TAG = "";
    private static Subscription suscription;
    public static String getTokenaddHeader(Context context, final String mToken) {
        return mToken;
    }

    public static String getSharedPreferences(Context context, String key) {
        SharedPreferences sharedPreferences = context.getSharedPreferences("dhy", Context.MODE_PRIVATE);
        String token = sharedPreferences.getString(key, "false");

        return token;
    }

    public static void getToken(Context context, Observer<LoginBeanSuccess> loginObserver) {
        SharedPreferences sharedPreferences = context.getSharedPreferences("dhy", Context.MODE_PRIVATE);
        String username = sharedPreferences.getString("username", "1");
        String password = sharedPreferences.getString("password", "2");
        String token = sharedPreferences.getString("mToken", "3");

     /*   Retrofit retrofit = new Retrofit.Builder().baseUrl(BaseUrl.url).addConverterFactory(GsonConverterFactory.create()).build();
        InterfaceLogin login = retrofit.create(InterfaceLogin.class);
        suscription = NetWork.mylogin().getLogin(username, password,"1")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(loginObserver);*/
      /*  Call<LoginBeanSuccess> call = login.getLogin(username, password, "1");
        call.enqueue(callback);*/

    }

}
