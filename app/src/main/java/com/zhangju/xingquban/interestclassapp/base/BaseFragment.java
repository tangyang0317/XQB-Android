package com.zhangju.xingquban.interestclassapp.base;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.MenuRes;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.text.format.DateUtils;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.ListView;

import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshGridView;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.zhangju.xingquban.R;

import butterknife.ButterKnife;
import butterknife.Unbinder;
import rx.Subscription;
import rx.subscriptions.CompositeSubscription;

/**
 * Created by Administrator on 2016/10/27.
 */
public abstract class BaseFragment
        extends Fragment
        implements View.OnClickListener {
    public View view;
    private Toolbar toolbar;
    protected Unbinder mUnbinder;
    protected CompositeSubscription mSubscribers;//管理所有的订阅


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(getMyLayout(), container, false);
        mUnbinder = ButterKnife.bind(this, view);
        initView();
        initData();
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initListener();
    }

    @Override
    public void onDestroyView() {
        if (mSubscribers != null && !mSubscribers.isUnsubscribed())
            mSubscribers.unsubscribe();
        super.onDestroyView();
        if (mUnbinder != null) mUnbinder.unbind();

    }

    /**
     * 在oncreate方法中初始化数据
     */
    public abstract void initData();

    /**
     * 取得需要加载的布局
     */
    public abstract int getMyLayout();

    /**
     * 在onCreateView中操作数据和控件
     */
    public abstract void initView();

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
                getActivity().finish();
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
     * 描述：抽象出所有ListView的上拉和下拉刷新事件，进行统一逻辑处理
     *
     * @param plistview   可刷新的listview
     * @param myContext   上下文
     * @param num         分页数目
     * @param size        条目数
     * @param refshLister 刷新事件的监听
     */
    public void refshLister(final PullToRefreshListView plistview,
                            final Context myContext, final int num,
                            final String size, final String searchCont,
                            final RefshLister refshLister) {
        plistview.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener2<ListView>() {
            int pagenum = num;
            String str = DateUtils.formatDateTime(myContext, System.currentTimeMillis(), DateUtils.FORMAT_SHOW_TIME | DateUtils
                    .FORMAT_SHOW_DATE | DateUtils.FORMAT_ABBREV_ALL);

            @Override
            public void onPullDownToRefresh(PullToRefreshBase refreshView) {
                plistview.getLoadingLayoutProxy().setRefreshingLabel("正在刷新");
                plistview.getLoadingLayoutProxy().setPullLabel("下拉刷新");
                plistview.getLoadingLayoutProxy().setReleaseLabel("释放开始刷新");
                refreshView.getLoadingLayoutProxy().setLastUpdatedLabel("最后更新时间:" + str);
                //回调该方法，加载第一页的新数据
                refshLister.getDada("0", size, searchCont);
                refshLister.isFirstPage();
                pagenum = 0;
            }

            @Override
            public void onPullUpToRefresh(PullToRefreshBase refreshView) {
                pagenum++;
                plistview.getLoadingLayoutProxy().setRefreshingLabel("正在加载");
                plistview.getLoadingLayoutProxy().setPullLabel("上拉加载更多");
                plistview.getLoadingLayoutProxy().setReleaseLabel("释放开始加载");
                refreshView.getLoadingLayoutProxy().setLastUpdatedLabel("最后加载时间:" + str);
                //回调该方法，进行分页加载
                refshLister.getDada(String.valueOf(pagenum), size, searchCont);

            }
        });
    }

    public void refshListerGridview(final PullToRefreshGridView plistview,
                                    final Context myContext, final int num,
                                    final String size, final String searchCont,
                                    final RefshLister refshLister) {
        plistview.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener2<GridView>() {
            int pagenum = num;
            String str = DateUtils.formatDateTime(myContext, System.currentTimeMillis(), DateUtils.FORMAT_SHOW_TIME | DateUtils
                    .FORMAT_SHOW_DATE | DateUtils.FORMAT_ABBREV_ALL);

            @Override
            public void onPullDownToRefresh(PullToRefreshBase<GridView> refreshView) {
                plistview.getLoadingLayoutProxy().setRefreshingLabel("正在刷新");
                plistview.getLoadingLayoutProxy().setPullLabel("下拉刷新");
                plistview.getLoadingLayoutProxy().setReleaseLabel("释放开始刷新");
                refreshView.getLoadingLayoutProxy().setLastUpdatedLabel("最后更新时间:" + str);
                //回调该方法，加载第一页的新数据
                refshLister.getDada("0", size, searchCont);
                refshLister.isFirstPage();
                pagenum = 0;
            }

            @Override
            public void onPullUpToRefresh(PullToRefreshBase<GridView> refreshView) {
                pagenum++;
                plistview.getLoadingLayoutProxy().setRefreshingLabel("正在加载");
                plistview.getLoadingLayoutProxy().setPullLabel("上拉加载更多");
                plistview.getLoadingLayoutProxy().setReleaseLabel("释放开始加载");
                refreshView.getLoadingLayoutProxy().setLastUpdatedLabel("最后加载时间:" + str);
                //回调该方法，进行分页加载
                refshLister.getDada(String.valueOf(pagenum), size, searchCont);

            }
        });
    }

    /**
     * 定义回调接口，重构子类getdata方法
     */
    public interface RefshLister {
        void getDada(String num, String size, String search_content);

        void isFirstPage();
    }

    /**
     * 订阅管理
     *
     * @param subscription
     */
    protected void addSubscriber(Subscription subscription) {
        if (mSubscribers == null) {
            mSubscribers = new CompositeSubscription();
        }
        mSubscribers.add(subscription);
    }
}

