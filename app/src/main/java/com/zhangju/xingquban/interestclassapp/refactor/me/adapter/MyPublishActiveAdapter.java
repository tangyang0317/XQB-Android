package com.zhangju.xingquban.interestclassapp.refactor.me.adapter;

import android.content.Context;
import android.graphics.Color;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.fastlib.adapter.SingleAdapterForRecycler;
import com.fastlib.base.CommonViewHolder;
import com.fastlib.net.Request;
import com.fastlib.utils.Utils;
import com.zhangju.xingquban.R;
import com.zhangju.xingquban.interestclassapp.refactor.common.bean.Response;
import com.zhangju.xingquban.interestclassapp.refactor.me.bean.MeInterface;
import com.zhangju.xingquban.interestclassapp.refactor.me.bean.ResponsePublish;
import com.zhangju.xingquban.interestclassapp.refactor.user.UserManager;

import java.util.List;
import java.util.Locale;

/**
 * Created by sgfb on 2017/10/31.
 * 我发布的列表适配器
 */
public class MyPublishActiveAdapter extends SingleAdapterForRecycler<ResponsePublish,Response<List<ResponsePublish>>>{

    public MyPublishActiveAdapter(Context context) {
        super(context, R.layout.item_active_publish);
    }

    @Override
    public Request generateRequest(){
        Request request=Request.obtain(MeInterface.POST_ACTIVE_PUBLISH_LIST);
        request.put("customerId", UserManager.getInstance().getUser().id);
        request.put("pageIndex",1);
        request.put("pageSize",10);
        return request;
    }

    @Override
    public void binding(int position, ResponsePublish data, CommonViewHolder holder){
        String ticketCount=String.valueOf(data.buyNumber);
        String collectionCount=String.valueOf(data.collectionNum);

        holder.setText(R.id.title,data.title);
        holder.setText(R.id.location,data.place);
        holder.setText(R.id.date,data.date);
        holder.setText(R.id.price,String.format(Locale.getDefault(),"￥%s",data.price));
        holder.setText(R.id.ticketCount, Utils.getTextSomeOtherColor(0,ticketCount.length(),ticketCount+" 报名", Color.parseColor("#DF594F")));
        holder.setText(R.id.commentCount,Utils.getTextSomeOtherColor(0,collectionCount.length(),collectionCount+" 关注",Color.parseColor("#DF594F")));
        Glide.with(mContext).load(data.titlePic).into((ImageView)holder.getView(R.id.cover));
    }

    @Override
    public List<ResponsePublish> translate(Response<List<ResponsePublish>> result){
        if(result.success) return result.data;
        return null;
    }

    @Override
    public void getMoreDataRequest(Request request) {
        request.increment("pageIndex",1);
    }

    @Override
    public void getRefreshDataRequest(Request request) {
        request.put("pageIndex",1);
    }
}
