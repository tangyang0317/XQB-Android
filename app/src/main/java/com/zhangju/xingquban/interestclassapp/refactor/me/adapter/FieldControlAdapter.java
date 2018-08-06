package com.zhangju.xingquban.interestclassapp.refactor.me.adapter;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.fastlib.adapter.FastAdapterForRecycler;
import com.fastlib.base.CommonViewHolder;
import com.fastlib.net.Request;
import com.fastlib.net.SimpleListener;
import com.zhangju.xingquban.R;
import com.zhangju.xingquban.interestclassapp.refactor.common.bean.Response;
import com.zhangju.xingquban.interestclassapp.refactor.me.bean.ChatUser;
import com.zhangju.xingquban.interestclassapp.refactor.me.bean.MeInterface;
import com.zhangju.xingquban.interestclassapp.refactor.me.bean.ResponseLiveroomControl;

/**
 * Created by sgfb on 2017/11/8.
 * 场控适配器
 */
public class FieldControlAdapter extends FastAdapterForRecycler<ChatUser>{

    public FieldControlAdapter(Context context) {
        super(context, R.layout.item_live_room_field_control);
    }

    @Override
    public void binding(final int position, final ChatUser data, CommonViewHolder holder) {
        if(data==null) return;
        holder.setText(R.id.name,data.name);
        Glide.with(mContext).load(data.icon).error(R.drawable.default_icon).into((ImageView)holder.getView(R.id.avatar));
        holder.setOnClickListener(R.id.changeFieldControl, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                requestRemoveControl(position,data.accid);
            }
        });
    }

    private void requestRemoveControl(final int position, String id){
        Request request=new Request(MeInterface.POST_CHANGE_FIELD_CONTROL);
        request.put("targetAccid",id);
        request.put("command","common");
        request.setListener(new SimpleListener<Response>(){

            @Override
            public void onResponseListener(Request r, Response result) {
                if(result.success)
                    remove(position);
            }
        });
        request.start();
    }
}