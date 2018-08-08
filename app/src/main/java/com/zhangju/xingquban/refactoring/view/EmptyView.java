package com.zhangju.xingquban.refactoring.view;

import android.content.Context;
import android.os.Build;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.zhangju.xingquban.R;

/**
 * @packageName com.zhangju.xingquban.refactoring.view
 * @FileName EmptyView
 * @Author tangyang
 * @DATE 2018/5/23
 **/
public class EmptyView extends LinearLayout {

    private ImageView noDataImg;
    private TextView noDataTxt;

    public EmptyView(Context context) {
        super(context);
        initView(context);
    }

    public EmptyView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initView(context);
    }

    public EmptyView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public EmptyView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        initView(context);
    }

    private void initView(Context context) {
        View view = LayoutInflater.from(context).inflate(R.layout.view_no_data, null);
        noDataImg = (ImageView) view.findViewById(R.id.fragment_dormitory_empty_iv);
        noDataTxt = (TextView) view.findViewById(R.id.fragment_dormitory_empty_txt);
        LayoutParams layoutParams = new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        this.setLayoutParams(layoutParams);
        setOrientation(VERTICAL);
        this.addView(view);
    }

    public void setNodataTitle(String title) {
        noDataTxt.setText(title);
    }


    public void setNodataImageSource(int ResourceID) {
        noDataImg.setImageResource(ResourceID);
    }

}
