package com.zhangju.xingquban.interestclassapp.refactor.common;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.animation.LinearInterpolator;
import android.widget.AdapterView;

import com.fastlib.annotation.Bind;
import com.fastlib.annotation.ContentView;
import com.fastlib.annotation.LocalData;
import com.fastlib.app.FastFragment;
import com.zhangju.xingquban.R;
import com.zhangju.xingquban.interestclassapp.refactor.common.adapter.PopupList2Adapter;
import com.zhangju.xingquban.interestclassapp.refactor.common.adapter.PopupListAdapter;

import java.util.List;

/**
 * Created by Administrator on 2017/12/4.
 * 弹出式双列表
 */
@ContentView(R.layout.frag_popup_list)
public class PopupListFragment extends FastFragment{
    public static final String ARG_BOOL_ISFUJIN="isfujin";
    public static final String ARG_BOOL_SINGLE_LIST="isSingleList"; //true为单列表，false双列表
    public static final String ARG_LIST_STR="listStr"; //第一条列表的字符串组
    public static final String ARG_LIST_SECOND_STR="secondList";
    public static final String ARG_INT_SELECTED_POSITION ="selectPosition"; //已选中位置
    public static final String ARG_INT_SELECTED_SECOND_POSITION="secondSelectPosition"; //第二列表已选中位置
    final int ANIM_DURATION=150;

    @LocalData(ARG_BOOL_ISFUJIN)
    boolean isfujin;
    @LocalData(ARG_BOOL_SINGLE_LIST)
    boolean isSingleList;
    @LocalData(ARG_INT_SELECTED_POSITION)
    int mSelectedPosition;
    @LocalData(ARG_INT_SELECTED_SECOND_POSITION)
    int mSecondSelectedPosition;
    @LocalData(ARG_LIST_STR)
    List<String> mMainListData;
    @LocalData(ARG_LIST_SECOND_STR)
    List<List<String>> mSecondListData;
    @Bind(R.id.leftList)
    RecyclerView mLeftList;
    @Bind(R.id.rightList)
    RecyclerView mRightList;
    PopupListAdapter mRightListAdapter;
    PopupList2Adapter mLeftListAdapter;
    @Bind(R.id.background)
    View mBackground;
    @Bind(R.id.listLayout)
    View mListLayout;
    ObjectAnimator mBackgroundAlphaAnima;
    ObjectAnimator mListSuspendAnima;
    OnListItemClickListener mListener;

    public int mainPostion=0;
    public void setOnPopListClickListener(OnListItemClickListener listener){
        mListener=listener;
    }

    @Override
    protected void alreadyPrepared(){
        mBackgroundAlphaAnima=ObjectAnimator.ofFloat(mBackground,"alpha",0,1).setDuration(ANIM_DURATION);
        mListSuspendAnima=ObjectAnimator.ofFloat(mListLayout,"scaleY",0,1).setDuration(ANIM_DURATION);
        mLeftList.setAdapter(mLeftListAdapter=new PopupList2Adapter(getContext()));
        mRightList.setAdapter(mRightListAdapter=new PopupListAdapter(getContext()));

        if(isSingleList) {
            mLeftList.setVisibility(View.GONE);
            mRightListAdapter.setData(mMainListData);
            mRightListAdapter.setSelectPosition(mSelectedPosition==-1?0:mSelectedPosition);
        }
        else {
            mLeftListAdapter.setData(mMainListData);
            mRightListAdapter.setData(mSecondListData.get(mSecondSelectedPosition==-1?0:mSecondSelectedPosition));
            mLeftListAdapter.setListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                    //是附近的筛选
                    if (isfujin){
                        if (position==0){
                            mainPostion=position;
                            mRightListAdapter.setData(mSecondListData.get(position));
                        }else {
                            if(mListener!=null) mListener.onItemClicked(true,position,position,"");
                        }
                    }
                    else { //不是附近的筛选
                        mainPostion=position;
                        mRightListAdapter.setData(mSecondListData.get(position));
                    }
                }
            });
            mLeftListAdapter.setSelectedPosition(mSelectedPosition==-1?0:mSelectedPosition);
            mRightListAdapter.setSelectPosition(mSecondSelectedPosition==-1?0:mSecondSelectedPosition);
        }
        mRightListAdapter.setListener(new OnListItemClickListener() {
            @Override
            public void onItemClicked(boolean firstList, int mainpostion, int position, String text) {
                if(mListener!=null) mListener.onItemClicked(true,mainPostion,position,text);
            }
        });
        mBackgroundAlphaAnima.setInterpolator(new LinearInterpolator());
        mListSuspendAnima.setInterpolator(new LinearInterpolator());
        mBackgroundAlphaAnima.start();
        mListSuspendAnima.start();
        mBackground.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mListener!=null) mListener.onItemClicked(false,-1,-1,null);
            }
        });
    }

    @Override
    public void finish(){
        mListSuspendAnima.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                PopupListFragment.super.finish();
            }
        });
        mBackgroundAlphaAnima.reverse();
        mListSuspendAnima.reverse();
    }

    /**
     * 列表的item点击回调事件,包括了空白点击
     */
    public interface OnListItemClickListener {

        /**
         * 列表item点击回调事件
         * @param firstList true为第一条list的回调，false第二条
         * @param position 点击item的位置
         * @param text item的text
         */
        void onItemClicked(boolean firstList,int mainpostion,int position,String text);
    }
}
