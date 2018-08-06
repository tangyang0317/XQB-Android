package com.zhangju.xingquban.interestclassapp.ui.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.zhangju.xingquban.R;


/**
 * 描述：自定义公用导航头
 * 项目名称：兴趣班
 * Created by Dr. Zhu on 2017/3/13.
 */


public class PublicHead extends FrameLayout {
    private TextView tx, mopertion;
    private ImageView back;
    private ImageView search;

    public PublicHead(Context context) {
        super(context);
    }

    public PublicHead(final Context context, AttributeSet attrs) {
        super(context, attrs);
        View view = LayoutInflater.from(context).inflate(R.layout.public_head, this);
        tx = (TextView) view.findViewById(R.id.mytitle);
        mopertion = (TextView) view.findViewById(R.id.opertion);
        back = (ImageView) view.findViewById(R.id.back);
        search = (ImageView) view.findViewById(R.id.im_search);

    }

    /**
     * 设置顶部导航的显示和隐藏，以便更好的定制标题头
     *
     * @param myback
     * @param mysearch
     * @param opertion
     */
    public void setShow(Boolean myback, Boolean mysearch, Boolean opertion) {
        back.setVisibility(myback ? View.VISIBLE : View.GONE);
        search.setVisibility(mysearch ? View.VISIBLE : View.GONE);
        mopertion.setVisibility(opertion ? View.VISIBLE : View.GONE);
    }

    public void setTitle(String title) {
        tx.setText(title);
    }

    public void setRightTitle(String title) {
        mopertion.setText(title);
    }

    public void setRightTextColor(int textColor) {
        mopertion.setTextColor(getContext().getResources().getColor(textColor));
    }

    public void setImgSearch(int backid) {
        search.setImageResource(backid);
    }

    /**
     * 返回按钮事件
     *
     * @param l
     */
    public void setBackClickListener(OnClickListener l) {
        back.setOnClickListener(l);
    }

    /**
     * 添加按钮事件
     *
     * @param l
     */
    public void setAddClickListener(OnClickListener l) {
        search.setOnClickListener(l);
    }

    /**
     * 添加操作文字对应的点击事件接口
     *
     * @param l
     */
    public void setRightClickListener(OnClickListener l) {
        mopertion.setOnClickListener(l);
    }

    /**
     * 添加操作文字对应的点击事件接口
     *
     * @param l
     */
    public void setImgSeachRightClickListener(OnClickListener l) {
        search.setOnClickListener(l);
    }


}
