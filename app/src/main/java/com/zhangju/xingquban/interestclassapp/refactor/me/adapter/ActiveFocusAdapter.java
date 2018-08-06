package com.zhangju.xingquban.interestclassapp.refactor.me.adapter;

import android.content.Context;
import android.graphics.Color;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.fastlib.adapter.SingleAdapterForRecycler;
import com.fastlib.base.CommonViewHolder;
import com.fastlib.net.Request;
import com.zhangju.xingquban.R;
import com.zhangju.xingquban.interestclassapp.refactor.common.bean.Response;
import com.zhangju.xingquban.interestclassapp.refactor.me.bean.MeInterface;
import com.zhangju.xingquban.interestclassapp.refactor.me.bean.ResponseActiveFocus;
import com.zhangju.xingquban.interestclassapp.refactor.user.UserManager;

import java.text.DecimalFormat;
import java.util.List;

/**
 * Created by sgfb on 2017/11/15.
 * 活动模块关注
 */
public class ActiveFocusAdapter extends SingleAdapterForRecycler<ResponseActiveFocus,Response<List<ResponseActiveFocus>>>{
    private DecimalFormat mDecimalFormat=new DecimalFormat("##.##");

    public ActiveFocusAdapter(Context context) {
        super(context, R.layout.item_active_focus);
    }

    @Override
    public Request generateRequest(){
        Request request=new Request(MeInterface.POST_ACTIVE_MY_FOCUS);
        request.put("customerId", UserManager.getInstance().getUser().id);
        request.put("collectionTypes",1);
        request.put("pageIndex",0);
        request.put("pageSize",10);
        return request;
    }

    @Override
    public void binding(int position, ResponseActiveFocus data, CommonViewHolder holder){
        TextView status=holder.getView(R.id.status);
        holder.setText(R.id.title,data.title);
        holder.setText(R.id.location,data.location);
        holder.setText(R.id.date,data.date);
        holder.setText(R.id.price,mDecimalFormat.format(data.price));
        Glide.with(mContext).load(data.cover).into((ImageView)holder.getView(R.id.cover));
        if(data.status==1){
            status.setText("进行中");
            status.setTextColor(Color.BLACK);
            status.setBackgroundColor(Color.parseColor("#FFDB10"));
        }
        else if(data.status==2){
            status.setText("暂停报名");
            status.setTextColor(Color.parseColor("#77777F"));
            status.setBackgroundColor(Color.parseColor("#F8F8F8"));
        }
        else{
            status.setText("已结束");
            status.setTextColor(Color.parseColor("#77777F"));
            status.setBackgroundColor(Color.parseColor("#F8F8F8"));
        }
    }

    @Override
    public List<ResponseActiveFocus> translate(Response<List<ResponseActiveFocus>> result){
        if(result.success) return result.data;
        return null;
    }

    @Override
    public void getMoreDataRequest(Request request) {
        request.increment("pageIndex",1);
    }

    @Override
    public void getRefreshDataRequest(Request request) {
        request.put("pageIndex",0);
    }
}