package com.zhangju.xingquban.interestclassapp.refactor.me.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.RatingBar;

import com.bumptech.glide.Glide;
import com.fastlib.adapter.SingleAdapterForRecycler;
import com.fastlib.base.CommonViewHolder;
import com.fastlib.net.Request;
import com.fastlib.utils.TimeUtil;
import com.zhangju.xingquban.R;
import com.zhangju.xingquban.interestclassapp.refactor.common.adapter.ImageAdapter;
import com.zhangju.xingquban.interestclassapp.refactor.common.bean.Response;
import com.zhangju.xingquban.interestclassapp.refactor.me.activity.CommentDetailActivity;
import com.zhangju.xingquban.interestclassapp.refactor.me.bean.MeInterface;
import com.zhangju.xingquban.interestclassapp.refactor.me.bean.ResponseComment;
import com.zhangju.xingquban.interestclassapp.refactor.user.UserManager;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Created by sgfb on 2017/10/27.
 * 我的评论适配器
 */
public class CommentAdapter extends SingleAdapterForRecycler<ResponseComment,Response<List<ResponseComment>>>{
    private DataCallback mCallback;

    public CommentAdapter(Context context,DataCallback callback){
        super(context, R.layout.item_comment);
        mCallback=callback;
    }

    @Override
    public Request generateRequest(){
        Request request=Request.obtain(MeInterface.POST_COMMENT_LIST);
        request.put("customerId", UserManager.getInstance().getUser().id);
        request.put("pageIndex",0);
        request.put("pageSize",10);
        return request;
    }

    @Override
    public void binding(int position, final ResponseComment data, CommonViewHolder holder){
        RatingBar levels=holder.getView(R.id.ratingBar);
        GridView photos=holder.getView(R.id.photos);
        ImageAdapter adapter= (ImageAdapter) photos.getTag();
        Calendar calendar= formatStrToCalendar(data.addUserTime);

        holder.setText(R.id.name,data.customerName);
        holder.setText(R.id.date,TimeUtil.dateToString(calendar.getTime(),"MM-dd"));
        holder.setText(R.id.comment,data.summary);
        levels.setRating(data.levels);
        if(adapter==null) adapter=new ImageAdapter(mContext,data.picList,false);
        photos.setAdapter(adapter);
        adapter.setData(data.picList);
        Glide.with(mContext).load(data.customerPicture).into((ImageView) holder.getView(R.id.avatar));
        holder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(mContext, CommentDetailActivity.class);
                intent.putExtra(CommentDetailActivity.ARG_SER_COMMENT,data);
                mContext.startActivity(intent);
            }
        });
    }

    @Override
    public List<ResponseComment> translate(Response<List<ResponseComment>> result){
        if(mCallback!=null)
            mCallback.callback(result.iTotalRecords);
        if(result.success) return result.data;
        return null;
    }

    @Override
    public void getMoreDataRequest(Request request){
        request.increment("pageIndex",1);
    }

    @Override
    public void getRefreshDataRequest(Request request) {
        request.put("pageIndex",0);
    }

    private Calendar formatStrToCalendar(String str){
        Calendar calendar=Calendar.getInstance();
        Date date= TimeUtil.StringToDate(str,"yyyy-MM-dd hh:mm:ss");
        if(date!=null) calendar.setTime(date);
        return calendar;
    }

    public interface DataCallback{
        void callback(int commentCount);
    }
}