package com.zhangju.xingquban.interestclassapp.app;

import com.zhangju.xingquban.BuildConfig;
import com.zhangju.xingquban.interestclassapp.shadow.NetManagerShadow;

import org.junit.runners.model.InitializationError;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;

/**
 * Created by Administrator on 2017/12/22.
 * 为兴趣班指定的Robolectrict单元测试Runner
 */
public class AppRobolectricTestRunner extends RobolectricTestRunner{

    /**
     * Creates a runner to run {@code testClass}. Looks in your working directory for your AndroidManifest.xml file
     * and res directory by default. Use the {@link Config} annotation to configure.
     *
     * @param testClass the test class to be run
     * @throws InitializationError if junit says so
     */
    public AppRobolectricTestRunner(Class<?> testClass) throws InitializationError {
        super(testClass);
    }

    @Override
    protected Config buildGlobalConfig(){
        Config.Builder config=new Config.Builder(super.buildGlobalConfig());
        return config.setConstants(BuildConfig.class)
                .setApplication(TestApplication.class)
                .setShadows(new Class[]{NetManagerShadow.class})
                .build();
    }
}
