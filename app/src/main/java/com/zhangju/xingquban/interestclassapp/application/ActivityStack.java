package com.zhangju.xingquban.interestclassapp.application;

import android.app.Activity;
import android.app.Application;
import android.os.Bundle;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by Administrator on 2018/1/29.
 * Activity栈，用来监控和清空当前App的Activity
 */
public class ActivityStack{
    private List<Activity> mStack=new LinkedList<>();

    public ActivityStack(Application application){
        application.registerActivityLifecycleCallbacks(new ActivityLifecycleAdapter(){
            @Override
            public void onActivityCreated(Activity activity, Bundle savedInstanceState) {
                super.onActivityCreated(activity, savedInstanceState);
                mStack.add(activity);
            }

            @Override
            public void onActivityDestroyed(Activity activity) {
                super.onActivityDestroyed(activity);
                mStack.remove(activity);
            }
        });
    }

    public void clearStack(){
        for(Activity activity:mStack)
            activity.finish();
    }

    public class ActivityLifecycleAdapter implements Application.ActivityLifecycleCallbacks{

        @Override
        public void onActivityCreated(Activity activity, Bundle savedInstanceState) {
            //适配
        }

        @Override
        public void onActivityStarted(Activity activity) {
            //适配
        }

        @Override
        public void onActivityResumed(Activity activity) {
            //适配
        }

        @Override
        public void onActivityPaused(Activity activity) {
            //适配
        }

        @Override
        public void onActivityStopped(Activity activity) {
            //适配
        }

        @Override
        public void onActivitySaveInstanceState(Activity activity, Bundle outState) {
            //适配
        }

        @Override
        public void onActivityDestroyed(Activity activity) {
            //适配
        }
    }
}