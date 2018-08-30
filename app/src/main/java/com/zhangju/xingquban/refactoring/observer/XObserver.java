package com.zhangju.xingquban.refactoring.observer;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import rx.Observable;

/**
 * @packageName com.zhangju.xingquban.refactoring.observer
 * @FileName XObserver
 * @Author tangyang
 * @DATE 2018/8/27
 **/
public abstract class XObserver<T> implements Observer<T> {

    protected abstract void success(T t);

    protected abstract void error(String error);

    @Override
    public void onSubscribe(Disposable d) {

    }

    @Override
    public void onNext(T t) {
        success(t);
    }

    @Override
    public void onError(Throwable e) {
        error(e.getMessage());
    }

    @Override
    public void onComplete() {

    }
}
