package com.zhangju.xingquban.interestclassapp.util;

import android.content.Context;
import android.util.AttributeSet;
import android.view.Gravity;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.zhangju.xingquban.R;

/**
 * Created by ydw on 2017/10/13.
 */

public class LoadingView  extends LinearLayout {

    private TextView text;
    private ProgressBar laoding;

    public LoadingView(Context context) {
        this(context, null);
    }

    public LoadingView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public LoadingView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context) {
        inflate(context, R.layout.recycler_load_more_layout, this);
        setGravity(Gravity.CENTER);

        text = (TextView) super.findViewById(R.id.foot_view_item_tv);
        laoding = (ProgressBar) super.findViewById(R.id.progressBar);
        text.setText("亲,正在加载数据中...");
    }

    public void setText(String str) {
        text.setText(str);
    }

    public void hiddenLoading() {
        laoding.setVisibility(GONE);
    }

}
