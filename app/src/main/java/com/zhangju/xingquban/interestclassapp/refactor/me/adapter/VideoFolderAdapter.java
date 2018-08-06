package com.zhangju.xingquban.interestclassapp.refactor.me.adapter;

import android.content.Context;
import android.view.View;

import com.fastlib.adapter.FastAdapter;
import com.fastlib.base.OldViewHolder;
import com.zhangju.xingquban.R;
import com.zhangju.xingquban.interestclassapp.refactor.me.bean.ResponseVideoFolderWithVideo;

import java.util.List;
import java.util.Locale;

/**
 * Created by sgfb on 2017/11/21.
 * 视频文件夹适配器
 */
public class VideoFolderAdapter extends FastAdapter<ResponseVideoFolderWithVideo>{

    public VideoFolderAdapter(Context context) {
        super(context,R.layout.item_course_video_folder);
    }

    public VideoFolderAdapter(Context context, List<ResponseVideoFolderWithVideo> data) {
        super(context,R.layout.item_course_video_folder, data);
    }

    @Override
    public void binding(int position, ResponseVideoFolderWithVideo data,OldViewHolder holder) {
        holder.setVisibility(R.id.defaultFolderFlag,position==0? View.VISIBLE:View.GONE);
        holder.setText(R.id.name,data.name);
        holder.setText(R.id.videoCount,String.format(Locale.getDefault(),"%d个视频",data.organAlbumFilesNum));
    }
}