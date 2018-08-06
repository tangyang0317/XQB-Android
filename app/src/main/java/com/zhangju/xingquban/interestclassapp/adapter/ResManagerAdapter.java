package com.zhangju.xingquban.interestclassapp.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import com.zhangju.xingquban.R;
import com.zhangju.xingquban.interestclassapp.adapter.baseadpter.BaseRecycleViewAdapter;

import java.util.List;

/**
 * Created by ydw on 2017/10/25.
 */
//资源管理列表展示Adapter
public class ResManagerAdapter extends BaseRecycleViewAdapter{

    private List<Object> mResList;


    public ResManagerAdapter(Context c,List<Object> mResList) {
        super(c);
        this.mResList=mResList;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ResourceViewholder(resIdToView(parent, R.layout.res_manager_item));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ResourceViewholder viewholder= (ResourceViewholder) holder;
        viewholder.onBind();

    }

    @Override
    public int getItemCount() {
        return mResList.size();
    }
    class ResourceViewholder extends RecyclerView.ViewHolder{
        int pos;


        public ResourceViewholder(View itemView) {
            super(itemView);
        }



        void onBind(){
            pos=getLayoutPosition();
            Object o = mResList.get(pos);
        }
    }
}
