package com.zhangju.xingquban.interestclassapp.hplper;

import android.content.Context;
import android.support.v7.widget.GridLayoutManager;
import android.util.AttributeSet;

/**
 * Created by ydw on 2017/10/20.
 */
//滑动卡顿处理  GridView 模式
public class ScrollGridManager extends GridLayoutManager {
    private boolean isScrollEnabled = true;

    public void setScrollEnabled(boolean scrollEnabled) {
        isScrollEnabled = scrollEnabled;
    }

    public ScrollGridManager(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    public ScrollGridManager(Context context, int spanCount) {
        super(context, spanCount);
    }

    public ScrollGridManager(Context context, int spanCount, int orientation, boolean reverseLayout) {
        super(context, spanCount, orientation, reverseLayout);
    }

    @Override
    public boolean canScrollVertically() {

        return isScrollEnabled && super.canScrollVertically();

    }
}
