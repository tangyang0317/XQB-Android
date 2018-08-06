package com.zhangju.xingquban.interestclassapp.refactor.me.activity;

import android.content.Intent;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.fastlib.annotation.Bind;
import com.fastlib.annotation.ContentView;
import com.fastlib.app.FastActivity;
import com.fastlib.net.Request;
import com.fastlib.net.SimpleListener;
import com.fastlib.widget.TitleBar;
import com.zhangju.xingquban.R;
import com.zhangju.xingquban.interestclassapp.refactor.common.bean.Response;
import com.zhangju.xingquban.interestclassapp.refactor.me.adapter.SelectFolderAdapter;
import com.zhangju.xingquban.interestclassapp.refactor.me.bean.MeInterface;
import com.zhangju.xingquban.interestclassapp.refactor.me.bean.ResponseVideoFolderWithVideo;
import com.zhangju.xingquban.interestclassapp.refactor.user.UserManager;

import java.util.List;

/**
 * Created by sgfb on 2017/11/21.
 * 选择视频文件夹
 */
@ContentView(R.layout.act_select_video_folder)
public class SelectVideoFolderActivity extends FastActivity{
    public static final String RES_STR_FOLDER_NAME="folderName";
    public static final String RES_STR_FOLDER_ID="folderId";
    @Bind(R.id.titleBar)
    TitleBar mTitleBar;
    @Bind(R.id.refresh)
    SwipeRefreshLayout mRefresh;
    @Bind(R.id.list)
    RecyclerView mList;
    SelectFolderAdapter mAdapter;

    @Override
    protected void alreadyPrepared(){
        mList.setAdapter(mAdapter=new SelectFolderAdapter(this, new SelectFolderAdapter.OnClickListener() {
            @Override
            public void onClick(ResponseVideoFolderWithVideo responseVideoFolderWithVideo) {
                Intent intent=new Intent();
                intent.putExtra(RES_STR_FOLDER_ID,responseVideoFolderWithVideo.id);
                intent.putExtra(RES_STR_FOLDER_NAME,responseVideoFolderWithVideo.name);
                setResult(RESULT_OK,intent);
                finish();
            }
        }));
        mRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                requestVideoFolder();
            }
        });
        mTitleBar.setOnLeftClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        requestVideoFolder();
    }

    /**
     * 请求视频文件夹
     */
    private void requestVideoFolder(){
        Request request=Request.obtain(MeInterface.POST_VIDEO_FOLDER);
        request.put("customerId", UserManager.getInstance().getUser().id);
        request.setListener(new SimpleListener<Response<List<ResponseVideoFolderWithVideo>>>(){

            @Override
            public void onResponseListener(Request r, Response<List<ResponseVideoFolderWithVideo>> result) {
                if(result.success)
                    mAdapter.setData(result.data);
            }
        });
        net(request);
    }
}
