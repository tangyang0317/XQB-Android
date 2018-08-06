package com.zhangju.xingquban.interestclassapp.refactor.me.adapter;

import android.content.Context;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.fastlib.adapter.FastAdapterForRecycler;
import com.fastlib.app.EventObserver;
import com.fastlib.base.CommonViewHolder;
import com.fastlib.net.Request;
import com.fastlib.net.SimpleListener;
import com.fastlib.utils.N;
import com.zhangju.xingquban.R;
import com.zhangju.xingquban.interestclassapp.refactor.common.bean.EventRefresh;
import com.zhangju.xingquban.interestclassapp.refactor.common.bean.Response;
import com.zhangju.xingquban.interestclassapp.refactor.me.activity.MyLiveRoomActivity;
import com.zhangju.xingquban.interestclassapp.refactor.me.bean.MeInterface;
import com.zhangju.xingquban.interestclassapp.refactor.me.bean.ResponseFocusUser;

/**
 * Created by sgfb on 2017/11/8.
 * 关注列表
 */
public class FocusUserAdapter extends FastAdapterForRecycler<ResponseFocusUser>{

    public FocusUserAdapter(Context context) {
        super(context, R.layout.item_fans); //粉丝item界面复用
    }

    @Override
    public void binding(final int position, final ResponseFocusUser data, CommonViewHolder holder) {
        TextView follow=holder.getView(R.id.focus);
        if(data.targetChatUser!=null){
            holder.setText(R.id.name, TextUtils.isEmpty(data.targetChatUser.name)?"未知":data.targetChatUser.name);
            Glide.with(mContext).load(data.targetChatUser.icon).into((ImageView)holder.getView(R.id.avatar));
        }
        follow.setTextColor(mContext.getResources().getColor(R.color.mainTextColor));
        follow.setBackgroundResource(R.drawable.shape_round_main_text);
        follow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                requestUnfollow(position,data);
            }
        });
    }

    private void requestUnfollow(final int position, final ResponseFocusUser data){
        if(data.targetChatUser==null){
            N.showShort(mContext,"用户数据异常，无法操作");
            return;
        }
        Request request=new Request(MeInterface.POST_FOCUS_AND_DELETE);
        request.put("targetAccid",data.targetChatUser.accid);
        request.setListener(new SimpleListener<Response>(){

            @Override
            public void onResponseListener(Request r, Response result) {
                if(result.success) {
                    remove(position);
                    EventObserver.getInstance().sendEvent(mContext,new EventRefresh(MyLiveRoomActivity.class));
                }
                else N.showShort(mContext,"操作失败");
            }
        });
        request.start();
    }
}
