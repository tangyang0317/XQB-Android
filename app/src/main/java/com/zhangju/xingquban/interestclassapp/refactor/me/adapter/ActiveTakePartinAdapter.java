package com.zhangju.xingquban.interestclassapp.refactor.me.adapter;

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
import com.zhangju.xingquban.interestclassapp.refactor.me.bean.ResponseMyTakePartin;
import com.zhangju.xingquban.interestclassapp.refactor.user.UserManager;

import java.util.List;
import java.util.Locale;

/**
 * Created by sgfb on 2017/11/15.
 * 活动模块我的参与适配器
 */
public class ActiveTakePartinAdapter extends SingleAdapterForRecycler<ResponseMyTakePartin,Response<List<ResponseMyTakePartin>>>{

    public ActiveTakePartinAdapter(Context context){
        super(context, R.layout.item_active_take_part_in);
    }

    @Override
    public Request generateRequest(){
        Request request=new Request(MeInterface.POST_ACTIVE_MY_TAKE_PART_IN);
        request.put("customerId", UserManager.getInstance().getUser().id);
        request.put("pageIndex",0);
        request.put("pageSize",10);
        return request;
    }

    @Override
    public void binding(int position, ResponseMyTakePartin data, CommonViewHolder holder){
        String voteCount=String.format(Locale.getDefault(),"%d 票",data.voteCount);
        String commentCount=String.format(Locale.getDefault(),"%d 评论",data.commentCount);
        TextView status=holder.getView(R.id.status);
        holder.setText(R.id.title,data.title);
        holder.setText(R.id.name, data.name);
        holder.setText(R.id.date,data.date);
        holder.setText(R.id.voteCount, Utils.getTextSomeOtherColor(0,voteCount.length()-2,voteCount,mContext.getResources().getColor(R.color.EF4E4C)));
        holder.setText(R.id.commentCount,Utils.getTextSomeOtherColor(0,commentCount.length()-2,commentCount,mContext.getResources().getColor(R.color.EF4E4C)));
        Glide.with(mContext).load(data.cover).into((ImageView)holder.getView(R.id.cover));
        if(data.status==1){
            status.setText("进行中");
            status.setTextColor(Color.BLACK);
            status.setBackgroundColor(Color.parseColor("#FFDB10"));
        }
        else{
            status.setText("暂停投票");
            status.setTextColor(Color.parseColor("#77777F"));
            status.setBackgroundColor(Color.parseColor("#F8F8F8"));
        }
    }

    @Override
    public List<ResponseMyTakePartin> translate(Response<List<ResponseMyTakePartin>> result){
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
