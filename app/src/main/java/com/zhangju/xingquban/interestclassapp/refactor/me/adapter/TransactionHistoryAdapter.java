package com.zhangju.xingquban.interestclassapp.refactor.me.adapter;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.text.TextUtils;

import com.fastlib.adapter.SingleAdapterForRecycler;
import com.fastlib.base.CommonViewHolder;
import com.fastlib.net.Request;
import com.zhangju.xingquban.R;
import com.zhangju.xingquban.interestclassapp.refactor.common.bean.Response;
import com.zhangju.xingquban.interestclassapp.refactor.me.bean.MeInterface;
import com.zhangju.xingquban.interestclassapp.refactor.me.bean.ResponseTransactionHistory;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/11/25.
 * 交易记录适配器
 */
public class TransactionHistoryAdapter extends SingleAdapterForRecycler<ResponseTransactionHistory.Ledger,Response<List<ResponseTransactionHistory>>>{

    public TransactionHistoryAdapter(Context context,String coinId){
        super(context,R.layout.item_transaction_history,false);
        if(!TextUtils.isEmpty(coinId))
            mRequest.put("coinId",coinId);
        refresh();
    }

    @Override
    public Request generateRequest(){
        Request request=new Request(MeInterface.POST_TRANSACTION_HISTORY_LIST);
        request.put("pageIndex",0);
        request.put("pageSize",10);
        return request;
    }

    @Override
    public void binding(int position, ResponseTransactionHistory.Ledger data, CommonViewHolder holder) {
        holder.setText(R.id.title,data.cname);
        holder.setText(R.id.amount,data.amount);
        holder.setText(R.id.type,data.name);
        holder.setText(R.id.date,data.time);
    }

    @Override
    public List<ResponseTransactionHistory.Ledger> translate(Response<List<ResponseTransactionHistory>> result){
        if(result.success&&result.data!=null){
            List<ResponseTransactionHistory.Ledger> list=new ArrayList<>();
            for(ResponseTransactionHistory history:result.data)
                if(history.ledger!=null&&!history.ledger.isEmpty())
                    list.addAll(history.ledger);
            return list;
        }
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