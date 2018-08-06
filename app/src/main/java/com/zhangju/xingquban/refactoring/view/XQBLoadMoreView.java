package com.zhangju.xingquban.refactoring.view;


import com.chad.library.adapter.base.loadmore.LoadMoreView;
import com.zhangju.xingquban.R;

/**
 * 加载更多的View
 *
 * @packageName xkh.hzp.xkh.com.base.view
 * @FileName XkhLoadMoreView
 * @Author tangyang
 * @DATE 2018/5/23
 **/
public class XQBLoadMoreView extends LoadMoreView {

    @Override
    public int getLayoutId() {
        return R.layout.view_load_more;
    }

    @Override
    protected int getLoadingViewId() {
        return R.id.load_more_loading_view;
    }

    @Override
    protected int getLoadFailViewId() {
        return R.id.load_more_load_fail_view;
    }

    @Override
    protected int getLoadEndViewId() {
        return R.id.load_more_load_end_view;
    }
}
