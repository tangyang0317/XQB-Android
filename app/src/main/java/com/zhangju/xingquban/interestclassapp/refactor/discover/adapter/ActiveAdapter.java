package com.zhangju.xingquban.interestclassapp.refactor.discover.adapter;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.text.TextUtils;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.fastlib.adapter.SingleAdapterForRecycler;
import com.fastlib.base.CommonViewHolder;
import com.fastlib.net.Request;
import com.zhangju.xingquban.R;
import com.zhangju.xingquban.interestclassapp.refactor.common.bean.Response;
import com.zhangju.xingquban.interestclassapp.refactor.discover.bean.ResponseActive;

import java.text.DecimalFormat;
import java.util.List;
import java.util.Locale;

/**
 * Created by Administrator on 2017/11/30.
 * 活动适配器
 */
public class ActiveAdapter extends SingleAdapterForRecycler<ResponseActive,Response<List<ResponseActive>>>{
    private DecimalFormat mDecimalFormat=new DecimalFormat("##.##");

    public ActiveAdapter(Context context,String url,String categoryId){
        super(context, R.layout.item_find_active,false);
        mRequest.setUrl(url);
        if(!TextUtils.isEmpty(categoryId)&&!"-1".equals(categoryId))
            mRequest.put("catagoriesId",categoryId);
        refresh();
    }

    @Override
    public Request generateRequest(){
        return new Request();
    }

    @Override
    public void binding(int position,ResponseActive data, CommonViewHolder holder){
        TextView flag=holder.getView(R.id.flag);
        TextView place=holder.getView(R.id.location);
        holder.setText(R.id.title,data.title);
        holder.setText(R.id.price,data.price<=0?"免费":String.format(Locale.getDefault(),"%s兴趣豆",mDecimalFormat.format(data.price)));
        holder.setText(R.id.hostName,data.sponsor);
        holder.setText(R.id.startDate,data.atime+" 开始");
        if(data.voteChannelType==1){
            flag.setText("活动");
            flag.setTextColor(Color.BLACK);
            flag.setBackgroundColor(Color.parseColor("#FFDB10"));
            place.setText(data.place);
            ((ImageView)holder.getView(R.id.icon)).setImageResource(R.mipmap.seek_sl_wz);
        }
        else{
            flag.setText("投票");
            flag.setTextColor(Color.WHITE);
            flag.setBackgroundColor(Color.parseColor("#4A90E2"));
            place.setText(String.format(Locale.getDefault(),"%s 截止",data.endTime));
            ((ImageView)holder.getView(R.id.icon)).setImageResource(R.mipmap.seek_sl_jzsj);
        }
        Glide.with((Activity)mContext).load(data.titlePic).into((ImageView)holder.getView(R.id.cover));
    }

    @Override
    public List<ResponseActive> translate(Response<List<ResponseActive>> result) {
        if(result.success) return result.data;
        return null;
    }

    @Override
    public void getMoreDataRequest(Request request) {
        isMore=false;
    }

    @Override
    public void getRefreshDataRequest(Request request) {
        isMore=false;
    }
}