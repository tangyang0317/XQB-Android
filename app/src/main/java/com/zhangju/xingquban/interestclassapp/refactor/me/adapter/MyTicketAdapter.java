package com.zhangju.xingquban.interestclassapp.refactor.me.adapter;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.LayoutRes;
import android.view.View;
import android.widget.TextView;

import com.fastlib.adapter.SingleAdapterForRecycler;
import com.fastlib.base.CommonViewHolder;
import com.fastlib.net.Request;
import com.zhangju.xingquban.R;
import com.zhangju.xingquban.interestclassapp.refactor.common.bean.Response;
import com.zhangju.xingquban.interestclassapp.refactor.me.bean.MeInterface;
import com.zhangju.xingquban.interestclassapp.refactor.me.bean.ResponseTicket;
import com.zhangju.xingquban.interestclassapp.refactor.user.UserManager;

import java.text.DecimalFormat;
import java.util.List;
import java.util.Locale;

/**
 * Created by sgfb on 2017/11/15.
 * 我的票劵适配器
 */
public class MyTicketAdapter extends SingleAdapterForRecycler<ResponseTicket,Response<List<ResponseTicket>>>{
    private int mStatus;

    public MyTicketAdapter(Context context,int status){
        super(context, R.layout.item_my_ticket,false);
        mStatus=status;
        if(status!=0)
            mRequest.put("status",status);
        refresh();
    }

    @Override
    public Request generateRequest(){
        Request request=new Request(MeInterface.POST_MY_TICKET_LIST);
        request.put("customerId", UserManager.getInstance().getUser().id);
        request.put("pageIndex",0);
        request.put("pageSize",10);
        return request;
    }

    @Override
    public void binding(int position, ResponseTicket data, CommonViewHolder holder){
        DecimalFormat df=new DecimalFormat();
        TextView status=holder.getView(R.id.status);
        holder.setText(R.id.title,data.title);
        holder.setText(R.id.location,data.location);
        holder.setText(R.id.time,data.time);
        holder.setText(R.id.price,String.format(Locale.getDefault(),"￥%s",df.format(data.price)));
        if(data.status==-3||data.status==3){
            status.setBackgroundResource(R.drawable.shape_round_solid_f8f8f8);
            status.setText(data.status==-3?"已退票":"已完成");
            status.setTextColor(Color.BLACK);
        }
        else{
            status.setBackgroundResource(R.drawable.shape_round_solid_7cc370);
            status.setText("有效");
            status.setTextColor(Color.WHITE);
        }
    }

    @Override
    public List<ResponseTicket> translate(Response<List<ResponseTicket>> result){
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
