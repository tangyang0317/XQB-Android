package com.zhangju.xingquban.interestclassapp.refactor.me.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.View;

import com.fastlib.adapter.MultiTypeAdapter;
import com.fastlib.base.CommonViewHolder;
import com.zhangju.xingquban.R;

import java.util.List;

/**
 * Created by Administrator on 2017/11/30.
 * 活动发布进度提示条适配器
 */
public class PublishActiveProgressAdapter2 extends MultiTypeAdapter{
    private int mCurrentPosition =0;
    FirstGroup mFirstGroup;
    LastGroup mLastGroup;
    NormalGroup mNormalGroup;

    public PublishActiveProgressAdapter2(Context context) {
        super(context);
        mFirstGroup=new FirstGroup();
        mLastGroup=new LastGroup();
        mNormalGroup=new NormalGroup();
        addGroup(mFirstGroup);
        addGroup(mNormalGroup);
        addGroup(mLastGroup);
    }

    public void setData(List<String> title,int position){
        this.mCurrentPosition =position;
        clear();
        mFirstGroup.addData(title.get(0));
        for(int i=1;i<title.size()-1;i++)
            mNormalGroup.addData(title.get(i));
        mLastGroup.addData(title.get(title.size()-1));
    }

    public class FirstGroup extends MultiTypeAdapter.RecyclerGroup<String>{

        @Override
        protected void binding(int positionOfRecyclerView, int positionOfGroup,String data, CommonViewHolder holder){
            View line=holder.getView(R.id.progressLine);
            holder.setText(R.id.statusText,data);
            line.setBackgroundColor(Color.parseColor(mCurrentPosition <=0?"#E0E0E0":"#EF4E4C"));
        }

        @Override
        protected int getLayoutId() {
            return R.layout.item_publish_active_first_progress;
        }
    }

    public class LastGroup extends MultiTypeAdapter.RecyclerGroup<String>{

        @Override
        protected void binding(int positionOfRecyclerView, int positionOfGroup, String data, CommonViewHolder holder) {
            View line=holder.getView(R.id.progressLine);
            View circle=holder.getView(R.id.statusCircle);
            holder.setText(R.id.statusText,data);
            line.setBackgroundColor(Color.parseColor(mCurrentPosition >=getItemCount()-1?"#EF4E4C":"#E0E0E0"));
            circle.setBackgroundResource(mCurrentPosition >=getItemCount()-1?R.drawable.shape_circle_red_stoke_white_solid:R.drawable.shape_circle_grey_stroke_white_solid);
        }

        @Override
        protected int getLayoutId() {
            return R.layout.item_publish_active_last_progress;
        }
    }

    public class NormalGroup extends MultiTypeAdapter.RecyclerGroup<String>{

        @Override
        protected void binding(int positionOfRecyclerView, int positionOfGroup, String data, CommonViewHolder holder){
            View leftLine=holder.getView(R.id.leftProgressLine);
            View rightLine=holder.getView(R.id.progressLine);
            View circle=holder.getView(R.id.statusCircle);

            holder.setText(R.id.statusText,data);
            if(mCurrentPosition <positionOfRecyclerView){
                leftLine.setBackgroundColor(Color.parseColor("#E0E0E0"));
                rightLine.setBackgroundColor(Color.parseColor("#E0E0E0"));
                circle.setBackgroundResource(R.drawable.shape_circle_grey_stroke_white_solid);
            }
            else if(mCurrentPosition==positionOfRecyclerView){
                leftLine.setBackgroundColor(Color.parseColor("#EF4E4C"));
                rightLine.setBackgroundColor(Color.parseColor("#E0E0E0"));
                circle.setBackgroundResource(R.drawable.shape_circle_red_stoke_white_solid);
            }
            else{
                leftLine.setBackgroundColor(Color.parseColor("#EF4E4C"));
                rightLine.setBackgroundColor(Color.parseColor("#EF4E4C"));
                circle.setBackgroundResource(R.drawable.shape_circle_red_stoke_white_solid);
            }
        }

        @Override
        protected int getLayoutId() {
            return R.layout.item_publish_active_progress;
        }
    }
}
