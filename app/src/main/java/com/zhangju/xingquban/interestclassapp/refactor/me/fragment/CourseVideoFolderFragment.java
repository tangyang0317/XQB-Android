package com.zhangju.xingquban.interestclassapp.refactor.me.fragment;

import android.content.DialogInterface;
import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;

import com.fastlib.annotation.Bind;
import com.fastlib.annotation.ContentView;
import com.fastlib.annotation.Event;
import com.fastlib.app.FastDialog;
import com.fastlib.app.FastFragment;
import com.fastlib.net.Request;
import com.fastlib.net.SimpleListener;
import com.zhangju.xingquban.R;
import com.zhangju.xingquban.interestclassapp.refactor.common.bean.EventRefresh;
import com.zhangju.xingquban.interestclassapp.refactor.common.bean.Response;
import com.zhangju.xingquban.interestclassapp.refactor.me.activity.CourseVideoActivity;
import com.zhangju.xingquban.interestclassapp.refactor.me.activity.CourseVideoListActivity;
import com.zhangju.xingquban.interestclassapp.refactor.me.adapter.VideoFolderAdapter;
import com.zhangju.xingquban.interestclassapp.refactor.me.bean.EventCreateVideoFolder;
import com.zhangju.xingquban.interestclassapp.refactor.me.bean.MeInterface;
import com.zhangju.xingquban.interestclassapp.refactor.me.bean.ResponseVideoFolderWithVideo;
import com.zhangju.xingquban.interestclassapp.refactor.user.UserManager;

import java.util.List;

/**
 * Created by sgfb on 2017/11/21.
 * 我的视频中视频文件夹页面
 */
@ContentView(R.layout.frag_course_video_folder)
public class CourseVideoFolderFragment extends FastFragment{
    @Bind(R.id.folder)
    GridView mVideos;
    VideoFolderAdapter mAdapter;

    @Override
    protected void alreadyPrepared() {
        mVideos.setAdapter(mAdapter=new VideoFolderAdapter(getContext()));
        mVideos.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent=new Intent(getContext(), CourseVideoListActivity.class);
                intent.putExtra(CourseVideoListActivity.ARG_SER_COURSE_VIDEO,mAdapter.getItem(position).organAlbumFilesList);
                startActivity(intent);
            }
        });
        mVideos.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, final int position, long id){
                FastDialog.showMessageDialog("是否删除文件夹",true).show(getChildFragmentManager(), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        requestDeleteFolder(mAdapter.getItem(position).id);
                    }
                });
                return true;
            }
        });
        requestFolders();
    }

    /**
     * 请求文件夹列表
     */
    private void requestFolders(){
        Request request=Request.obtain(MeInterface.POST_VIDEO_FOLDER);
        request.put("customerId", UserManager.getInstance().getUser().id);
        request.setListener(new SimpleListener<Response<List<ResponseVideoFolderWithVideo>>>(){

            @Override
            public void onResponseListener(Request r, Response<List<ResponseVideoFolderWithVideo>> result){
                if(result.success)
                    mAdapter.setData(result.data);
            }
        });
        net(request);
    }

    /**
     * 请求删除指定文件夹
     * @param id 指定文件夹id
     */
    private void requestDeleteFolder(String id){
        Request request=Request.obtain(MeInterface.POST_DEL_VIDEO_FOLDER);
        request.put("id",id);
        request.setListener(new SimpleListener<Response>(){

            @Override
            public void onResponseListener(Request r, Response result) {
                if(result.success) requestFolders();
            }
        });
        net(request);
    }

    @Bind(R.id.addFolder)
    private void addVideoFolder(){
        new CreateVideoFloderDialog().show(getFragmentManager(),"createVideoFolder");
    }

    @Event
    private void eFolderAdded(EventCreateVideoFolder event){
        requestFolders();
    }

    @Event
    private void eRefreshList(EventRefresh event){
        if(event.getmTargetClass()==CourseVideoActivity.class)
            requestFolders();
    }
}