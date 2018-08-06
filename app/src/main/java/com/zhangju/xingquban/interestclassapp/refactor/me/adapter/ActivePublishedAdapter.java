package com.zhangju.xingquban.interestclassapp.refactor.me.adapter;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.fastlib.adapter.SingleAdapterForRecycler;
import com.fastlib.base.CommonViewHolder;
import com.fastlib.net.Request;
import com.fastlib.utils.Utils;
import com.zhangju.xingquban.R;
import com.zhangju.xingquban.interestclassapp.refactor.common.bean.Response;
import com.zhangju.xingquban.interestclassapp.refactor.me.bean.MeInterface;
import com.zhangju.xingquban.interestclassapp.refactor.me.bean.ResponseActivePublished;
import com.zhangju.xingquban.interestclassapp.refactor.user.UserManager;

import java.text.DecimalFormat;
import java.util.List;
import java.util.Locale;

/**
 * Created by sgfb on 2017/11/15.
 * 活动发布列表适配器
 */
public class ActivePublishedAdapter extends SingleAdapterForRecycler<ResponseActivePublished,Response<List<ResponseActivePublished>>>{
    private DecimalFormat mDecimalFormat=new DecimalFormat("##.##");

    public ActivePublishedAdapter(Context context) {
        super(context, R.layout.item_active_published);
    }

    @Override
    public Request generateRequest(){
        Request request=new Request(MeInterface.POST_ACTIVE_PUBLISHED);
        request.put("customerId", UserManager.getInstance().getUser().id);
//        request.put("pageIndex",0);
//        request.put("pageSize",10);
        return request;
    }

    @Override
    public void binding(int position, ResponseActivePublished data, CommonViewHolder holder){
        TextView status=holder.getView(R.id.status);
        String applyCount=String.format(Locale.getDefault(),"%d 报名",data.applyCount);
        String focusCount=String.format(Locale.getDefault(),"%d 关注",data.collectionCount);

        holder.setText(R.id.title,data.title);
        holder.setText(R.id.location,data.location);
        holder.setText(R.id.date,data.date);
        holder.setText(R.id.price,String.format(Locale.getDefault(),"￥ %s起",mDecimalFormat.format(data.price)));
        holder.setText(R.id.applyCount, Utils.getTextSomeOtherColor(0,applyCount.length()-3,applyCount,mContext.getResources().getColor(R.color.EF4E4C)));
        holder.setText(R.id.commentCount,Utils.getTextSomeOtherColor(0,focusCount.length()-3,focusCount,mContext.getResources().getColor(R.color.EF4E4C)));
        switch (data.status){
            case 1:{
                status.setText("进行中");
                status.setTextColor(Color.BLACK);
                status.setBackgroundColor(Color.parseColor("#FFDB10"));
            }break;
            case 0:{
                status.setText("已结束");
                status.setTextColor(Color.parseColor("#77777F"));
                status.setBackgroundColor(Color.parseColor("#F8F8F8"));
            }break;
            case 3:{
                status.setText("暂停报名");
                status.setTextColor(Color.parseColor("#77777F"));
                status.setBackgroundColor(Color.parseColor("#F8F8F8"));
            }
            default:System.out.println("一个不存在的活动状态");break;
        }
        Glide.with((Activity)mContext).load(data.cover).into((ImageView)holder.getView(R.id.cover));
    }

    @Override
    public List<ResponseActivePublished> translate(Response<List<ResponseActivePublished>> result) {
        if(result.success) return result.data;
        return null;
    }

    @Override
    public void getMoreDataRequest(Request request) {
//        request.increment("pageIndex",1);
        isMore=false;
    }

    @Override
    public void getRefreshDataRequest(Request request) {
//        request.put("pageIndex",1);
        isMore=false;
    }
}
