package com.zhangju.xingquban.interestclassapp.refactor.common.widget;

import android.animation.ObjectAnimator;
import android.support.v7.widget.RecyclerView;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

/**
 * Created by Administrator on 2017/12/6.
 * 侧滑辅助监听
 */
public class SlideTouchHelper implements RecyclerView.OnItemTouchListener{
    private int START_INTERCEPT=35;
    private float mOldX=0,mOldY=0;
    private boolean isStartSwip=false,isHoldView=false;
    private View mTranslateCurrView;

    public SlideTouchHelper(RecyclerView.Adapter adapter){
        adapter.registerAdapterDataObserver(new RecyclerView.AdapterDataObserver() {
            @Override
            public void onChanged() {
                super.onChanged();
                if(mTranslateCurrView!=null){
                    View child=((ViewGroup)mTranslateCurrView).getChildAt(((ViewGroup) mTranslateCurrView).getChildCount()-1);
                    child.setTranslationX(0);
                }
            }

            @Override
            public void onItemRangeRemoved(int positionStart, int itemCount) {
                super.onItemRangeRemoved(positionStart, itemCount);
                if(mTranslateCurrView!=null){
                    View child=((ViewGroup)mTranslateCurrView).getChildAt(((ViewGroup) mTranslateCurrView).getChildCount()-1);
                    child.setTranslationX(0);
                }
            }
        });
    }

    @Override
    public boolean onInterceptTouchEvent(RecyclerView rv, MotionEvent e){
        switch(e.getAction()){
            case MotionEvent.ACTION_DOWN:
                mOldX=e.getX();
                mOldY=e.getY();
                break;
            case MotionEvent.ACTION_MOVE:
                if(isStartSwip) return true;
                float distanceX= Math.abs(mOldX-e.getX());
                float distanceY= Math.abs(mOldY-e.getY());
                if(distanceX>START_INTERCEPT&&distanceY<START_INTERCEPT)
                    isStartSwip=true;
                else{
                    if(mTranslateCurrView!=null){
                        View child=((ViewGroup)mTranslateCurrView).getChildAt(((ViewGroup) mTranslateCurrView).getChildCount()-1);
                        child.setTranslationX(0);
                    }
                }
                break;
            case MotionEvent.ACTION_UP:
                isStartSwip=false;
                break;
            default:break;
        }
        return isStartSwip;
    }

    @Override
    public void onTouchEvent(RecyclerView rv, MotionEvent e){
        switch (e.getAction()){
            case MotionEvent.ACTION_UP:
            case MotionEvent.ACTION_POINTER_UP:
            case MotionEvent.ACTION_CANCEL:{
                isStartSwip=false;
                isHoldView=false;
                if(mTranslateCurrView!=null){
                    View lastChild=((ViewGroup)mTranslateCurrView).getChildAt(((ViewGroup) mTranslateCurrView).getChildCount()-1);
                    View firstChild=((ViewGroup)mTranslateCurrView).getChildAt(0);
                    ObjectAnimator.ofFloat(lastChild,"translationX",lastChild.getTranslationX(), Math.abs(lastChild.getTranslationX())>firstChild.getWidth()?firstChild.getWidth()*-1:0).setDuration(120).start();
                }
            }
        }
        if(isStartSwip){
            if(!isHoldView) {
                mTranslateCurrView = rv.findChildViewUnder(e.getX(), e.getY());
                isHoldView=true;
            }

            //仅滑动FrameLayout中的第一个View
            if(mTranslateCurrView instanceof FrameLayout){
                FrameLayout group= (FrameLayout) mTranslateCurrView;
                if(group.getChildCount()>1){
                    View lastChild=group.getChildAt(group.getChildCount()-1);
                    float translateX=e.getX()-mOldX;
                    if(translateX>0)
                        translateX=0;
                    lastChild.setTranslationX(translateX);
                }
            }
            else mTranslateCurrView=null;
        }
    }

    @Override
    public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {

    }
}
