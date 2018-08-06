package com.zhangju.xingquban.interestclassapp.ui.fragment.find.Wenda;

import android.content.Context;
import android.support.annotation.AttrRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.widget.FrameLayout;

/**
 * Created by Administrator on 2017/8/17.
 */

public class PhotoSquare extends FrameLayout {

    @Override
    protected void onMeasure(int widht, int height) {
        setMeasuredDimension(getDefaultSize(0, widht), getDefaultSize(0, height));
        int childWidthSize = getMeasuredWidth();
        // 高度和宽度一样
        height = widht = MeasureSpec.makeMeasureSpec(childWidthSize, MeasureSpec.EXACTLY);
        super.onMeasure(widht, height);
    }

    public PhotoSquare(@NonNull Context context) {
        super(context);
    }

    public PhotoSquare(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public PhotoSquare(@NonNull Context context, @Nullable AttributeSet attrs, @AttrRes int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }
}
