package com.zhangju.xingquban.interestclassapp.refactor.me.adapter;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.fastlib.adapter.FastAdapter;
import com.fastlib.adapter.FastAdapterForRecycler;
import com.fastlib.app.FastDialog;
import com.fastlib.base.CommonViewHolder;
import com.fastlib.base.OldViewHolder;
import com.fastlib.net.Request;
import com.fastlib.net.SimpleListener;
import com.zhangju.xingquban.R;
import com.zhangju.xingquban.interestclassapp.refactor.common.bean.Response;
import com.zhangju.xingquban.interestclassapp.refactor.me.activity.CourseListActivity;
import com.zhangju.xingquban.interestclassapp.refactor.me.activity.EditCourseActivity;
import com.zhangju.xingquban.interestclassapp.refactor.me.bean.MeInterface;
import com.zhangju.xingquban.interestclassapp.refactor.me.bean.ResponseOrder;
import com.zhangju.xingquban.interestclassapp.refactor.user.UserManager;

import java.util.List;
import java.util.Locale;

/**
 * Created by sgfb on 2017/11/17.
 * 自定义课程适配器
 */
public class CustomCourseAdapter extends FastAdapterForRecycler<ResponseOrder.Lesson> {

    public CustomCourseAdapter(Context context) {
        super(context, R.layout.item_custom_course);
    }

    public CustomCourseAdapter(Context context, List<ResponseOrder.Lesson> data){
        super(context,R.layout.item_custom_course, data);
    }

    /**
     * 请求删除课程
     * @param position
     * @param id
     */
    private void requestDelLesson(final int position, String id){
        Request request=new Request(MeInterface.POST_LESSON_DEL);
        request.put("id",id);
        request.put("teacherTimeId", UserManager.getInstance().getUser().teacherTimeId);
        request.setListener(new SimpleListener<Response>(){

            @Override
            public void onResponseListener(Request r, Response result) {
                if(result.success){
                    getData().remove(position);
                    notifyDataSetChanged();
                }
            }
        });
        request.start();
    }

    @Override
    public void binding(final int position, final ResponseOrder.Lesson data, CommonViewHolder holder) {
        holder.setText(R.id.name,data.name);
        holder.setText(R.id.price,String.format(Locale.getDefault(),"￥%s",data.price));

        holder.setOnClickListener(R.id.delete, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FastDialog.showMessageDialog("确定删除课程？",true).show(((AppCompatActivity) mContext).getSupportFragmentManager(), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        requestDelLesson(position,data.id);
                    }
                });
            }
        });
        holder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(mContext,EditCourseActivity.class);
                intent.putExtra(EditCourseActivity.ARG_STR_ID,data.id);
                mContext.startActivity(intent);
            }
        });
    }
}