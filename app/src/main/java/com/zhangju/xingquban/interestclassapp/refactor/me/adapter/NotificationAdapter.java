package com.zhangju.xingquban.interestclassapp.refactor.me.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.ImageView;

import com.fastlib.adapter.SingleAdapterForRecycler;
import com.fastlib.base.CommonViewHolder;
import com.fastlib.net.Request;
import com.zhangju.xingquban.R;
import com.zhangju.xingquban.interestclassapp.refactor.common.bean.Response;
import com.zhangju.xingquban.interestclassapp.refactor.me.bean.MeInterface;
import com.zhangju.xingquban.interestclassapp.refactor.me.bean.ResponseNotification;
import com.zhangju.xingquban.interestclassapp.ui.fragment.find.SeekResource.AudioDetailActivity;

import java.util.List;

/**
 * Created by sgfb on 2017/10/26.
 * 消息中心列表适配器
 */
public class NotificationAdapter extends SingleAdapterForRecycler<ResponseNotification,Response<List<ResponseNotification>>>{
    private int mType;

    public NotificationAdapter(Context context,int type){
        super(context, R.layout.item_notification,false);
        mType=type;
        mRequest.put("msgtype",type);
        refresh();
    }

    @Override
    public Request generateRequest(){
        Request request=Request.obtain("get",MeInterface.GET_NOTIFY_LIST);
        request.put("iDisplayStart",0);
        request.put("iDisplayLength",10);
        return request;
    }

    @Override
    public void binding(int position, final ResponseNotification data, CommonViewHolder holder) {
        ImageView cover=holder.getView(R.id.cover);
        holder.setText(R.id.title,data.title);
        holder.setText(R.id.content,data.alert);
        cover.setImageResource(mType==1?R.mipmap.me_message_day:R.mipmap.me_message_xit);
        if(mType==1){
            holder.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(mContext, AudioDetailActivity.class);
                    intent.putExtra("types",data.resourceTypes);
                    intent.putExtra("resId",data.typeId);
                    mContext.startActivity(intent);
                }
            });
        }
    }

    @Override
    public List<ResponseNotification> translate(Response<List<ResponseNotification>> result){
        if(result.success) return result.data;
        return null;
    }

    @Override
    public void getMoreDataRequest(Request request) {
        request.increment("iDisplayStart",10);
    }

    @Override
    public void getRefreshDataRequest(Request request) {
        request.put("iDisplayStart",0);
    }
}
