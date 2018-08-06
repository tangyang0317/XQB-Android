package com.zhangju.xingquban.interestclassapp.refactor.common.utils;

import org.xutils.common.Callback.ProgressCallback;

public abstract class MyProgressCallBack<ResultType> implements ProgressCallback<ResultType> {

    @Override
    public void onWaiting() {

    }

    @Override
    public void onLoading(long l, long l1, boolean b) {

    }

    @Override
    public void onCancelled(CancelledException e) {

    }

    @Override
    public void onFinished() {

    }
}
