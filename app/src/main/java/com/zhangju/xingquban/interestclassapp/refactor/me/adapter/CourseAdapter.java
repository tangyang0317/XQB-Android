package com.zhangju.xingquban.interestclassapp.refactor.me.adapter;

import android.content.Context;
import android.text.TextUtils;
import android.view.View;

import com.fastlib.adapter.FastAdapterForRecycler;
import com.fastlib.base.CommonViewHolder;
import com.zhangju.xingquban.R;
import com.zhangju.xingquban.interestclassapp.refactor.me.bean.Course;

import java.util.List;

/**
 * Created by sgfb on 2017/11/2.
 * 课程表安排适配器
 */
public class CourseAdapter extends FastAdapterForRecycler<Course> {

    public CourseAdapter(Context context) {
        super(context, R.layout.item_course);
    }

    public CourseAdapter(Context context, List<Course> data) {
        super(context, R.layout.item_course, data);
    }

    @Override
    public void binding(final int position, final Course data, CommonViewHolder holder) {
        holder.setVisibility(R.id.text,data.isTitle? View.VISIBLE:View.INVISIBLE);
        holder.setVisibility(R.id.courseTag,data.isTitle?View.INVISIBLE: View.VISIBLE);
        holder.setText(R.id.text,data.title);
        holder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(TextUtils.isEmpty(data.title)){
                    data.isTitle=!data.isTitle;
                    notifyDataSetChanged();
                }
            }
        });
    }
}
