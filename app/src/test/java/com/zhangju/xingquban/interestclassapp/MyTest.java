package com.zhangju.xingquban.interestclassapp;

import android.widget.TextView;

import com.fastlib.net.Request;
import com.fastlib.net.SimpleListener;
import com.zhangju.xingquban.R;
import com.zhangju.xingquban.interestclassapp.app.AppRobolectricTestRunner;
import com.zhangju.xingquban.interestclassapp.refactor.me.activity.LoginActivity;
import com.zhangju.xingquban.interestclassapp.refactor.me.activity.PersonDataActivity;
import com.zhangju.xingquban.interestclassapp.shadow.NetManagerShadow;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.shadows.ShadowLog;

/**
 * Created by Administrator on 2017/12/26.
 */
@RunWith(AppRobolectricTestRunner.class)
public class MyTest {

    @Before
    public void setUp() throws Exception {
        ShadowLog.stream=System.out;
    }

    @Test
    public void test(){
        Request request=new Request("get","http://www.baidu.com");
        request.setHadRootAddress(true);
        request.setListener(new SimpleListener<String>(){

            @Override
            public void onResponseListener(Request r, String result) {
                System.out.println("result:"+result);
            }

            @Override
            public void onErrorListener(Request r, String error) {
                super.onErrorListener(r, error);
                System.out.println("error:"+error);
            }
        });
        request.start();
        synchronized(NetManagerShadow.mLock){
            try {
                NetManagerShadow.mLock.wait();
            } catch (InterruptedException e){
                e.printStackTrace();
            }
        }
    }
}