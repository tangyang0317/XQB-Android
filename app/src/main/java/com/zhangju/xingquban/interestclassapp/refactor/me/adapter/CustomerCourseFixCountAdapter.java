package com.zhangju.xingquban.interestclassapp.refactor.me.adapter;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.fastlib.adapter.FastAdapter;
import com.fastlib.app.FastDialog;
import com.fastlib.base.OldViewHolder;
import com.zhangju.xingquban.R;
import com.zhangju.xingquban.interestclassapp.refactor.me.activity.EditCourseActivity;
import com.zhangju.xingquban.interestclassapp.refactor.me.bean.ResponseOrder;

import java.util.List;
import java.util.Locale;

/**
 * Created by Administrator on 2017/12/6.
 */
public class CustomerCourseFixCountAdapter extends FastAdapter<ResponseOrder.Lesson>{

    public CustomerCourseFixCountAdapter(Context context) {
        super(context,R.layout.item_custom_course);
    }

    public CustomerCourseFixCountAdapter(Context context, List<ResponseOrder.Lesson> data){
        super(context,R.layout.item_custom_course, data);
    }

    @Override
    public void binding(int position, final ResponseOrder.Lesson data, OldViewHolder holder) {
        holder.setText(R.id.name,data.name);
        holder.setText(R.id.price,String.format(Locale.getDefault(),"￥%s",data.price));
        holder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(mContext,EditCourseActivity.class);
                intent.putExtra(EditCourseActivity.ARG_STR_ID,data.id);
                mContext.startActivity(intent);
            }
        });
    }

    @Override
    public int getCount() {
        return super.getCount()>3?3:super.getCount();
    }
}
