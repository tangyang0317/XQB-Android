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
import com.zhangju.xingquban.interestclassapp.refactor.me.bean.ResponseFans;

/**
 * Created by sgfb on 2017/11/8.
 * 粉丝适配器
 */
public class FansAdapter extends FastAdapterForRecycler<ResponseFans>{

    public FansAdapter(Context context) {
        super(context, R.layout.item_fans);
    }

    @Override
    public void binding(int position, final ResponseFans data, CommonViewHolder holder) {
        TextView follow=holder.getView(R.id.focus);
        if(data.targetChatUser!=null){
            holder.setText(R.id.name, TextUtils.isEmpty(data.targetChatUser.name)?"未知":data.targetChatUser.name);
            Glide.with(mContext).load(data.targetChatUser.icon).into((ImageView)holder.getView(R.id.avatar));
        }
        if(data.followFans){
            follow.setTextColor(mContext.getResources().getColor(R.color.mainTextColor));
            follow.setBackgroundResource(R.drawable.shape_round_main_text);
            follow.setText("取消关注");
        }
        else{
            follow.setTextColor(mContext.getResources().getColor(R.color.EF4E4C));
            follow.setBackgroundResource(R.drawable.shape_round_ef4e4c);
            follow.setText("关注Ta");
        }
        follow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                followOrUnfollow(data);
            }
        });
    }

    /**
     * 关注或者解除关注
     * @param data 对应item数据
     */
    private void followOrUnfollow(final ResponseFans data){
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
                    data.followFans=!data.followFans;
                    notifyDataSetChanged();
                    EventObserver.getInstance().sendEvent(mContext,new EventRefresh(MyLiveRoomActivity.class));
                }
                else N.showShort(mContext,"操作失败");
            }
        });
        request.start();
    }
}