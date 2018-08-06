package com.zhangju.xingquban.interestclassapp.refactor.common.utils;

import android.graphics.Rect;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.fastlib.utils.DensityUtils;

/**
 * Created by Administrator on 2017/11/25.
 * Grid布局间隔
 */
public class GridDivider extends RecyclerView.ItemDecoration{
    private int mDividerWidth,mDividerHeight;

    public GridDivider(int mDividerWidth, int mDividerHeight) {
        this.mDividerWidth = mDividerWidth;
        this.mDividerHeight = mDividerHeight;
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state){
        if(parent.getLayoutManager() instanceof GridLayoutManager){
            int height=mDividerHeight,width=mDividerWidth;
            int position=parent.getChildAdapterPosition(view);
            GridLayoutManager layoutManager= (GridLayoutManager) parent.getLayoutManager();

            if((position+1)%layoutManager.getSpanCount()==0) width=0;
            if((position+1)>parent.getAdapter().getItemCount()-layoutManager.getSpanCount()) height=0;
            outRect.set(0,0,width,height);
        }
    }
}