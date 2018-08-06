package com.zhangju.xingquban.interestclassapp.http;

import android.util.Log;

import com.zhangju.xingquban.interestclassapp.base.BaseBean;
import com.zhangju.xingquban.interestclassapp.util.ToastUtil;

import rx.Subscriber;

/**
 * Created by Wxy on 2017/10/27.
 */

public abstract class BaseSubscriber<T extends BaseBean> extends Subscriber<T> {

    public abstract void onSuccess(T t);

    @Override
    public void onNext(T t) {
        if (t.isSuccess()) {
            onSuccess(t);
        } else {
            onFailure(t);
        }

    }

    @Override
    public void onCompleted() {

    }

    @Override
    public void onError(Throwable e) {
        ToastUtil.showToast("网络异常");
        Log.e("BaseSubscriber", e.toString());
    }

    public void onFailure(T t) {
        if (t.getErrMsg() != null) {
            ToastUtil.showToast(t.getErrMsg().toString());
        }
    }
}
