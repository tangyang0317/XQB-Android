package com.zhangju.xingquban.interestclassapp.refactor.me.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.View;

import com.fastlib.adapter.FastAdapterForRecycler;
import com.fastlib.base.CommonViewHolder;
import com.fastlib.net.Request;
import com.fastlib.net.SimpleListener;
import com.zhangju.xingquban.R;
import com.zhangju.xingquban.interestclassapp.refactor.common.bean.Response;
import com.zhangju.xingquban.interestclassapp.refactor.me.activity.EditCourseActivity;
import com.zhangju.xingquban.interestclassapp.refactor.me.bean.MeInterface;
import com.zhangju.xingquban.interestclassapp.refactor.me.bean.ResponseOrder;
import com.zhangju.xingquban.interestclassapp.refactor.user.UserManager;

import java.util.List;
import java.util.Locale;

/**
 * Created by Administrator on 2017/12/11.
 */
public class CustomerCourseFixCountAdapterForRecycler extends FastAdapterForRecycler<ResponseOrder.Lesson>{
    private DelLessonCallback mCallback;

    public CustomerCourseFixCountAdapterForRecycler(Context context){
        super(context, R.layout.item_custom_course);
    }

    public CustomerCourseFixCountAdapterForRecycler(Context context, List<ResponseOrder.Lesson> data) {
        super(context, R.layout.item_custom_course, data);
    }

    @Override
    public void binding(int position, final ResponseOrder.Lesson data, CommonViewHolder holder) {
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
        holder.setOnClickListener(R.id.delete, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                requestDelLesson(data.id);
            }
        });
    }

    @Override
    public int getItemCount() {
        return super.getItemCount()>3?3:super.getItemCount();
    }

    /**
     * 请求删除课程
     * @param id
     */
    private void requestDelLesson(String id){
        Request request=new Request(MeInterface.POST_LESSON_DEL);
        request.put("id",id);
        request.put("teacherTimeId", UserManager.getInstance().getUser().teacherTimeId);
        request.setListener(new SimpleListener<Response>(){

            @Override
            public void onResponseListener(Request r, Response result) {
                if(result.success)
                    if(mCallback!=null) mCallback.successDel();
            }
        });
        request.start();
    }

    public void setDeleteLessonCallback(DelLessonCallback callback){
        mCallback=callback;
    }

    public interface DelLessonCallback{
        void successDel();
    }
}
