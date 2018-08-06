package com.zhangju.xingquban.interestclassapp.app;

import com.fastlib.db.FastDatabase;
import com.fastlib.net.NetManager;
import com.zhangju.xingquban.interestclassapp.application.MyApp;

/**
 * Created by Administrator on 2017/12/22.
 * 测试时使用的Application.除了自身单例引用没做任何初始化
 */
public class TestApplication extends MyApp{

    @Override
    public void onCreate() {
        instance=this;
        isInited=true;
        NetManager.getInstance().setRootAddress(URL+"/admnxzcmr");
        FastDatabase.getConfig().setVersion(DB_VERSION);
        super.onCreate();
    }
}
