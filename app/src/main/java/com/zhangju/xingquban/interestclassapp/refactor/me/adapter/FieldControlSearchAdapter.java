package com.zhangju.xingquban.interestclassapp.refactor.me.adapter;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.fastlib.adapter.FastAdapterForRecycler;
import com.fastlib.base.CommonViewHolder;
import com.fastlib.net.Request;
import com.fastlib.net.SimpleListener;
import com.fastlib.utils.N;
import com.zhangju.xingquban.R;
import com.zhangju.xingquban.interestclassapp.refactor.common.bean.Response;
import com.zhangju.xingquban.interestclassapp.refactor.me.bean.ChatUser;
import com.zhangju.xingquban.interestclassapp.refactor.me.bean.MeInterface;

/**
 * Created by sgfb on 2017/11/8.
 * 增加场控
 */
public class FieldControlSearchAdapter extends FastAdapterForRecycler<ChatUser>{

    public FieldControlSearchAdapter(Context context) {
        super(context, R.layout.item_live_room_field_control);
    }

    @Override
    public void binding(final int position, final ChatUser data, CommonViewHolder holder) {
        holder.setText(R.id.name,data.name);
        holder.setText(R.id.changeFieldControl,"增加场控");
        Glide.with(mContext).load(data.icon).into((ImageView)holder.getView(R.id.avatar));
        holder.setOnClickListener(R.id.changeFieldControl, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                requestAddControl(position,data.accid);
            }
        });
    }

    private void requestAddControl(final int position,String id){
        Request request=new Request(MeInterface.POST_CHANGE_FIELD_CONTROL);
        request.put("targetAccid",id);
        request.put("command","manager");
        request.setListener(new SimpleListener<Response>(){

            @Override
            public void onResponseListener(Request r, Response result) {
                if(result.success)
                    remove(position);
                else N.showShort(mContext,"增加失败");
            }
        });
        request.start();
    }
}