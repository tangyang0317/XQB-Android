package com.zhangju.xingquban.interestclassapp.base;

import android.content.Context;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.annotation.MenuRes;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;

import com.trello.rxlifecycle.components.support.RxAppCompatActivity;
import com.zhangju.xingquban.R;
import com.zhangju.xingquban.interestclassapp.http.BaseSubscriber;

import butterknife.ButterKnife;
import rx.Subscription;
import rx.subscriptions.CompositeSubscription;

/**
 * activity的基类，实现butterKnife的绑定和布局的加载
 * Created by Administrator on 2016/10/27.
 */
public abstract class BaseActivity extends RxAppCompatActivity implements View.OnClickListener {
    public Context mContext = this;
    private Toolbar toolbar = null;
    protected CompositeSubscription mSubscribers;//管理所有的订阅


        @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        setContentView(getLayout());
        this.mSubscribers = new CompositeSubscription();
        ButterKnife.bind(this);
        initView();
        initData();
        initListener();

    }

    /**
     * 获取布局
     */
    public abstract int getLayout();

    /**
     * 初始化控件
     */
    public abstract void initView();

    /**
     * 初始化数据
     */
    public abstract void initData();

    /**
     * 设置监听
     */
    public abstract void initListener();

    /**
     * 设置toobar的返回键
     */
    public boolean setBackKey(Toolbar toolbar) {
        if (toolbar == null) {
            return false;
        } else {
            this.toolbar = toolbar;
            toolbar.setNavigationIcon(R.mipmap.ic_launcher);
            setBackEvent();
            return true;
        }
    }

    /**
     * 设置返回事件
     */
    private void setBackEvent() {
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    /**
     * 设置菜单
     */
    public boolean setMenuKey(Toolbar toolbar, @MenuRes int menuLayout, final OnClickListener clickListener) {
        if (toolbar == null) {
            return false;
        } else {
            this.toolbar = toolbar;
            toolbar.inflateMenu(menuLayout);
            setMenuEvent(clickListener);
            return true;
        }
    }

    /**
     * 设置菜单点击事件
     */
    private void setMenuEvent(final OnClickListener clickListener) {
        this.toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                clickListener.onClick();
                return true;
            }
        });
    }

    /**
     * 设置点击接口
     */
    public interface OnClickListener {
        public void onClick();
    }

    /**
     * 销毁处理
     */
    @Override
    protected void onDestroy() {
        if (mSubscribers != null && !mSubscribers.isUnsubscribed()) {
            mSubscribers.unsubscribe();
        }
        super.onDestroy();
    }

    @Override
    public void onBackPressed() {
        finish();

    }

    protected  <E extends BaseBean> BaseSubscriber<E> addSubscriber(BaseSubscriber<E> subscriber){
        mSubscribers.add(subscriber);
        return subscriber;
    }
    protected  void addSubscriber(Subscription subscriber){
        mSubscribers.add(subscriber);
    }
}
