package com.zhangju.xingquban.interestclassapp.refactor.me.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.LayoutRes;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.fastlib.adapter.SingleAdapterForRecycler;
import com.fastlib.base.CommonViewHolder;
import com.fastlib.net.Request;
import com.zhangju.xingquban.R;
import com.zhangju.xingquban.interestclassapp.refactor.common.bean.Response;
import com.zhangju.xingquban.interestclassapp.refactor.me.activity.OrderDetailActivity;
import com.zhangju.xingquban.interestclassapp.refactor.me.bean.MeInterface;
import com.zhangju.xingquban.interestclassapp.refactor.me.bean.ResponseOrder;

import java.text.DecimalFormat;
import java.util.List;
import java.util.Locale;

/**
 * Created by sgfb on 2017/11/11.
 * 商户工单适配器
 */
public class BusinessOrderAdapter extends SingleAdapterForRecycler<ResponseOrder, Response<List<ResponseOrder>>> {

    public BusinessOrderAdapter(Context context, int status) {
        super(context, R.layout.item_business_order, false);
        if (status != ResponseOrder.STATUS_NONE)
            mRequest.put("scancode", status);
        refresh();
    }

    @Override
    public Request generateRequest() {
        Request request = Request.obtain(MeInterface.POST_ORDER_LIST);
        request.put("pageIndex",0);
        request.put("pageSize", 10);
        request.put("isBussiness", true);
        return request;
    }

    @Override
    public void binding(int position, final ResponseOrder data, CommonViewHolder holder){
        String status;

        DecimalFormat df=new DecimalFormat("##.##");
        holder.setText(R.id.price,String.format(Locale.getDefault(),"总价：￥%s",df.format(data.amount)));
        if(data.dlist!=null&&!data.dlist.isEmpty()){
            ResponseOrder.OrderData orderData=data.dlist.get(0);
            holder.setText(R.id.name,orderData.lessonName);
            //改为长期有效
//            if(orderData.lesson!=null)
//                holder.setText(R.id.expireDate,String.format(Locale.getDefault(),"有效期至%s",orderData.lesson.lessonDate));
            holder.setText(R.id.expireDate,"有效期至：长期有效");
            Glide.with(mContext).load(orderData.lesson.picture).into((ImageView)holder.getView(R.id.cover));
        }
        holder.setText(R.id.orderNum,String.format(Locale.getDefault(),"订单号：%s",data.sno));
        holder.setText(R.id.date,data.createTimeStr);
        switch (data.status){
            case ResponseOrder.STATUS_UNPAY:status="未支付";break;
            case ResponseOrder.STATUS_PAYED:status="已支付";break;
            case ResponseOrder.STATUS_VALID:status="未使用";break;
            case ResponseOrder.STATUS_USED:status="已消费";break;
            case ResponseOrder.STATUS_INVALID:status="已失效";break;
            case ResponseOrder.STATUS_CANCEL:status="已取消";break;
            default:status="未知状态";
        }
        holder.setText(R.id.status,status);
        holder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(mContext, OrderDetailActivity.class);
                intent.putExtra(OrderDetailActivity.ARG_BOOL_ORDER_IS_BUSINESS,true);
                intent.putExtra(OrderDetailActivity.ARG_STR_ORDER_ID,data.id);
                mContext.startActivity(intent);
            }
        });
    }

    @Override
    public List<ResponseOrder> translate(Response<List<ResponseOrder>> result) {
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
