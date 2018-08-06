package com.zhangju.xingquban.interestclassapp.refactor.common.widget;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by sgfb on 17/3/28.
 * 可以控制滚动能力的ViewPager，默认禁止滚动
 */
public class ScrollableViewPager extends ViewPager {
    private boolean mScrollEnable=false;

    public ScrollableViewPager(Context context) {
        super(context);
    }

    public ScrollableViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev){
        if(mScrollEnable)
            return super.onTouchEvent(ev);
        return false;
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        if(mScrollEnable)
            return super.onInterceptTouchEvent(ev);
        return false;
    }
}
