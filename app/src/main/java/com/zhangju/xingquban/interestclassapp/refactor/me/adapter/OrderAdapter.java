package com.zhangju.xingquban.interestclassapp.refactor.me.adapter;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.fastlib.adapter.SingleAdapterForRecycler;
import com.fastlib.app.FastDialog;
import com.fastlib.base.CommonViewHolder;
import com.fastlib.net.Request;
import com.fastlib.net.SimpleListener;
import com.fastlib.utils.N;
import com.zhangju.xingquban.R;
import com.zhangju.xingquban.interestclassapp.refactor.common.bean.Response;
import com.zhangju.xingquban.interestclassapp.refactor.me.activity.CheckCourseCodeActivity;
import com.zhangju.xingquban.interestclassapp.refactor.me.activity.OrderDetailActivity;
import com.zhangju.xingquban.interestclassapp.refactor.me.activity.OrderPayActivity;
import com.zhangju.xingquban.interestclassapp.refactor.me.bean.MeInterface;
import com.zhangju.xingquban.interestclassapp.refactor.me.bean.ResponseOrder;
import com.zhangju.xingquban.interestclassapp.refactor.me.fragment.OrderMoreDialog;

import java.util.List;

/**
 * Created by sgfb on 2017/10/28.
 * 订单适配器
 */
public class OrderAdapter extends SingleAdapterForRecycler<ResponseOrder,Response<List<ResponseOrder>>>{

    public OrderAdapter(Context context,int status,int isBusiness){
        super(context, R.layout.item_my_order,false);
        if(status!=ResponseOrder.STATUS_NONE)
            mRequest.put("status",status);
        mRequest.put("isBussiness",isBusiness);
        refresh();
    }

    @Override
    public Request generateRequest(){
        Request request=Request.obtain(MeInterface.POST_ORDER_LIST);
        request.put("pageIndex",0);
        request.put("pageSize",10);
        return request;
    }

    @Override
    public void binding(int position, final ResponseOrder data, CommonViewHolder holder){
        boolean isDeleteHint;
        if(data.dlist!=null&&!data.dlist.isEmpty()){
            ResponseOrder.OrderData od=data.dlist.get(0);
            holder.setText(R.id.title,od.lessonName);
        }
        holder.setText(R.id.price,Float.toString(data.amount));
        switch (data.status){
            case ResponseOrder.STATUS_CANCEL:
                holder.setText(R.id.status,"交易关闭");
                holder.setVisibility(R.id.commit,View.GONE);
                holder.setVisibility(R.id.more,View.GONE);
                isDeleteHint=true;
                break;
            case ResponseOrder.STATUS_UNPAY:holder.setText(R.id.status,"待支付");
                holder.setText(R.id.commit,"立即支付");
                holder.setVisibility(R.id.commit,View.VISIBLE);
                holder.setVisibility(R.id.more,View.VISIBLE);
                holder.setOnClickListener(R.id.commit, new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        //立即支付
                        Intent intent=new Intent(mContext, OrderPayActivity.class);
                        intent.putExtra(OrderPayActivity.ARG_SER_ORDER,data);
                        mContext.startActivity(intent);
                    }
                });
                isDeleteHint=false;
                break;
            case ResponseOrder.STATUS_VALID:holder.setText(R.id.status,"未使用");
                holder.setText(R.id.commit,"查看课程码");
                holder.setVisibility(R.id.commit,View.VISIBLE);
                holder.setVisibility(R.id.more,View.GONE);
                holder.setOnClickListener(R.id.commit, new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        //查看课程码
                        Intent intent=new Intent(mContext, CheckCourseCodeActivity.class);
                        intent.putExtra(CheckCourseCodeActivity.ARG_STR_ID,data.id);
                        mContext.startActivity(intent);
                    }
                });
                isDeleteHint=false;
                break;
            case ResponseOrder.STATUS_USED:holder.setText(R.id.status,"已消费");
                holder.setVisibility(R.id.commit,View.GONE);
                isDeleteHint=true;
                break;
            default:holder.setText(R.id.status,"未知状态");
                holder.setVisibility(R.id.commit,View.GONE);
                isDeleteHint=true;
                break;
        }
        final boolean fIsDeleteHint=isDeleteHint;
        holder.setOnClickListener(R.id.more, new View.OnClickListener() {
            @Override
            public void onClick(View v){
                final OrderMoreDialog dialog=OrderMoreDialog.getInstance(mContext,fIsDeleteHint?"删除订单":"取消订单");
                dialog.setListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                        //只有一个选项，不做判断
                        Request request=Request.obtain(MeInterface.POST_ORDER_CANCEL);
                        request.put("sid",data.id);
                        request.put("isBussiness",false);
                        request.setListener(new SimpleListener<Response>(){

                            @Override
                            public void onResponseListener(Request r, Response result) {
                                if(result.success){
                                    refresh();
                                }
                                else N.showShort(mContext,"删除订单失败");
                            }
                        });
                        request.start();
                    }
                });
                ((AppCompatActivity)mContext).getSupportFragmentManager().beginTransaction().replace(android.R.id.content,dialog).commit();
//                showCancelOrderHint(fIsDeleteHint,data.id);
            }
        });
        //订单有效期改为无限期
        if(data.dlist!=null&&!data.dlist.isEmpty()&&data.dlist.get(0).lesson!=null) {
//            holder.setText(R.id.expirationDate, data.dlist.get(0).lesson.lessonDate);
            holder.setText(R.id.expirationDate,"长期有效");
        }
        else holder.setText(R.id.expirationDate,"未知");
        if(data.dlist!=null&&!data.dlist.isEmpty())
        Glide.with(mContext).load(data.dlist.get(0).lessonImg).into((ImageView)holder.getView(R.id.cover));
        holder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(mContext, OrderDetailActivity.class);
                intent.putExtra(OrderDetailActivity.ARG_BOOL_ORDER_IS_BUSINESS,false);
                intent.putExtra(OrderDetailActivity.ARG_STR_ORDER_ID,data.id);
                mContext.startActivity(intent);
            }
        });
    }

    @Override
    public List<ResponseOrder> translate(Response<List<ResponseOrder>> result){
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

    /**
     * 显示取消订单界面
     * @param isDeleteHint 是否是删除提示
     * @param id 订单id
     */
    private void showCancelOrderHint(boolean isDeleteHint,final String id){
        FastDialog.showListDialog(new String[]{isDeleteHint?"删除订单":"取消订单"}).show(((AppCompatActivity) mContext).getSupportFragmentManager(), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                //只有一个选项，不做判断
                Request request=Request.obtain(MeInterface.POST_ORDER_CANCEL);
                request.put("sid",id);
                request.put("isBussiness",false);
                request.setListener(new SimpleListener<Response>(){

                    @Override
                    public void onResponseListener(Request r, Response result) {
                        if(result.success){
                            refresh();
                        }
                        else N.showShort(mContext,"删除订单失败");
                    }
                });
                request.start();
            }
        });
    }
}
