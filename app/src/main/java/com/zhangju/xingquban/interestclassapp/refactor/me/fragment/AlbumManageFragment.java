package com.zhangju.xingquban.interestclassapp.refactor.me.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.TextView;

import com.fastlib.annotation.Bind;
import com.fastlib.annotation.ContentView;
import com.fastlib.annotation.Event;
import com.fastlib.annotation.LocalData;
import com.fastlib.app.EventObserver;
import com.fastlib.app.FastFragment;
import com.zhangju.xingquban.R;
import com.zhangju.xingquban.interestclassapp.refactor.common.activity.PreviewImageActivity;
import com.zhangju.xingquban.interestclassapp.refactor.me.activity.AlbumManageUploadVideoActivity;
import com.zhangju.xingquban.interestclassapp.refactor.me.activity.AlbumMangeUploadImageActivity;
import com.zhangju.xingquban.interestclassapp.refactor.me.adapter.AlbumManageAdapter;
import com.zhangju.xingquban.interestclassapp.refactor.me.bean.EventAlbumMangeShowDelete;
import com.zhangju.xingquban.interestclassapp.refactor.me.bean.ResponseVideoAlbum;
import com.zhangju.xingquban.interestclassapp.ui.activity.near.ShipinBofangActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by sgfb on 2017/11/6.
 * 机构管理中的相册管理内容模块
 */
@ContentView(R.layout.frag_album_manage)
public class AlbumManageFragment extends FastFragment{
    public static final String ARG_INT_MANAGER_TYPE="managerType"; //管理模块类型 0-图片 2-视频
    final int REQ_UPLOAD_ALBUM=1;
    final int REQ_UPLOAD_VIDEO=2;

    @LocalData(ARG_INT_MANAGER_TYPE)
    int mType;
    @Bind(R.id.grid)
    GridView mGrid;
    @Bind(R.id.upload)
    TextView mUpload;
    @Bind(R.id.delete)
    TextView mDelete;
    AlbumManageAdapter mAdapter;
    List<ResponseVideoAlbum> mDeleteFlagResponseVideoAlbum =new ArrayList<>();

    public static AlbumManageFragment getInstance(int type){
        Bundle bundle=new Bundle();
        AlbumManageFragment fragment=new AlbumManageFragment();
        bundle.putInt(ARG_INT_MANAGER_TYPE,type);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    protected void alreadyPrepared(){
        if(mType==2) {
            mUpload.setText("上传视频");
        }
        mGrid.setAdapter(mAdapter=new AlbumManageAdapter(getContext(),mType));
        mGrid.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                List<ResponseVideoAlbum> data=mAdapter.getData();
                ArrayList<String> images=new ArrayList<>();

                for(ResponseVideoAlbum videoAlbum:data) {
                    images.add(mType == 0 ? videoAlbum.picVideo : videoAlbum.videoTitlePic);
                }
                Intent intent;
                if(mType==0){
                    intent=new Intent(getContext(), PreviewImageActivity.class);
                    intent.putExtra(PreviewImageActivity.ARG_BOOL_IS_LOCAL,false);
                    intent.putExtra(PreviewImageActivity.ARG_INT_INDEX,position);
                    intent.putExtra(PreviewImageActivity.ARG_LIST_STR_IMAGES,images);
                }
                else{
                    intent=new Intent(getContext(), ShipinBofangActivity.class);
                    intent.putExtra(ShipinBofangActivity.ARG_STRING_NAME,data.get(position).title);
                    intent.putExtra(ShipinBofangActivity.ARG_STRING_URL,data.get(position).picVideo);
                }
                startActivity(intent);
            }
        });
        mAdapter.setmCallback(new AlbumManageAdapter.DeleteFlagChangeCallback() {
            @Override
            public void callback(ResponseVideoAlbum videoAlbum, boolean isChecked) {
                if(isChecked) {
                    mDeleteFlagResponseVideoAlbum.add(videoAlbum);
                } else {
                    mDeleteFlagResponseVideoAlbum.remove(videoAlbum);
                }
                mDelete.setVisibility(mDeleteFlagResponseVideoAlbum.isEmpty()?View.GONE:View.VISIBLE);
            }
        });
    }

    @Event
    private void eShowDelete(EventAlbumMangeShowDelete event){
        if(event.isDeleteFlag())
            mUpload.setVisibility(View.GONE);
        else{
            mDelete.setVisibility(View.GONE);
            mUpload.setVisibility(View.VISIBLE);
        }
        mAdapter.setShowDelete(event.isDeleteFlag());
    }

    @Bind(R.id.upload)
    private void upload(){
        if(mType==0) {
            startActivityForResult(new Intent(getContext(), AlbumMangeUploadImageActivity.class), REQ_UPLOAD_ALBUM);
        } else if(mType==2) {
            startActivityForResult(new Intent(getContext(), AlbumManageUploadVideoActivity.class), REQ_UPLOAD_VIDEO);
        } else {
            System.out.println("一个未知的相册管理类型");
        }
    }

    @Bind(R.id.delete)
    private void delete(){
        mAdapter.deleteSelectImage();
        EventObserver.getInstance().sendEvent(getContext(),new EventAlbumMangeShowDelete(false));
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        mAdapter.refresh();
    }
}