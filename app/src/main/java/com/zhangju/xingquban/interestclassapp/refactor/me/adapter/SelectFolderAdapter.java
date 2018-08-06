package com.zhangju.xingquban.interestclassapp.refactor.me.adapter;

import android.content.Context;
import android.view.View;

import com.fastlib.adapter.FastAdapter;
import com.fastlib.adapter.FastAdapterForRecycler;
import com.fastlib.base.CommonViewHolder;
import com.fastlib.base.OldViewHolder;
import com.zhangju.xingquban.R;
import com.zhangju.xingquban.interestclassapp.refactor.me.bean.ResponseVideoFolderWithVideo;

/**
 * Created by sgfb on 2017/11/21.
 * 选择视频文件夹适配器
 */
public class SelectFolderAdapter extends FastAdapterForRecycler<ResponseVideoFolderWithVideo> {
    private OnClickListener mListener;

    public SelectFolderAdapter(Context context,OnClickListener listener){
        super(context, R.layout.item_video_folder);
        mListener=listener;
    }

    @Override
    public void binding(int position, final ResponseVideoFolderWithVideo data, CommonViewHolder holder) {
        holder.setText(R.id.name,data.name);
        holder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mListener!=null)
                    mListener.onClick(data);
            }
        });
    }

    public interface OnClickListener{
        void onClick(ResponseVideoFolderWithVideo responseVideoFolderWithVideo);
    }
}
