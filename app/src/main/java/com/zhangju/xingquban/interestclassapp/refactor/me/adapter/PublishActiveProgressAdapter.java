package com.zhangju.xingquban.interestclassapp.refactor.me.adapter;

import android.content.Context;
import android.support.v4.util.Pair;
import android.view.View;
import android.widget.FrameLayout;

import com.fastlib.adapter.FastAdapterForRecycler;
import com.fastlib.base.CommonViewHolder;
import com.fastlib.utils.DensityUtils;
import com.zhangju.xingquban.R;

/**
 * Created by sgfb on 2017/10/31.
 * 发布活动进度适配器
 */
public class PublishActiveProgressAdapter extends FastAdapterForRecycler<Pair<Boolean,String>> {

    public PublishActiveProgressAdapter(Context context) {
        super(context, R.layout.item_publish_active_progress);
    }

    @Override
    public void binding(int position, Pair<Boolean, String> data, CommonViewHolder holder) {
        View statusLayout=holder.getView(R.id.statusLayout);
        View bgLine=holder.getView(R.id.progressLine);
        View circle=holder.getView(R.id.statusCircle);

        holder.setText(R.id.statusText,data.second);
        circle.setBackgroundResource(data.first?R.drawable.shape_circle_red_stoke_white_solid:R.drawable.shape_circle_grey_stroke_white_solid);

        FrameLayout.LayoutParams statusLayoutLp= (FrameLayout.LayoutParams) statusLayout.getLayoutParams();
        FrameLayout.LayoutParams bgLineLp= (FrameLayout.LayoutParams) bgLine.getLayoutParams();

        if(statusLayoutLp==null) statusLayoutLp=new FrameLayout.LayoutParams(FrameLayout.LayoutParams.WRAP_CONTENT,FrameLayout.LayoutParams.WRAP_CONTENT);
        if(bgLineLp==null) bgLineLp=new FrameLayout.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT, DensityUtils.dp2px(mContext,4));
        bgLineLp.width= DensityUtils.dp2px(mContext,130);

        if(position==0){ //如果是第一个，加左边距
            statusLayoutLp.leftMargin= DensityUtils.dp2px(mContext,40);
            statusLayoutLp.rightMargin=0;
            bgLineLp.leftMargin= DensityUtils.dp2px(mContext,60);
        }
        else if(position==getItemCount()-1){ //如果是最后一个，去掉背景线，加右边距
            statusLayoutLp.leftMargin=0;
            statusLayoutLp.rightMargin= DensityUtils.dp2px(mContext,40);
            bgLineLp.leftMargin=0;
            bgLineLp.width=circle.getWidth();
        }
        else{ //中间,如果已经到后面的状态，背景线变色
            statusLayoutLp.leftMargin=statusLayoutLp.rightMargin=0;
            bgLineLp.leftMargin=0;
        }
        bgLine.setLayoutParams(bgLineLp);
        statusLayout.setLayoutParams(statusLayoutLp);
    }
}