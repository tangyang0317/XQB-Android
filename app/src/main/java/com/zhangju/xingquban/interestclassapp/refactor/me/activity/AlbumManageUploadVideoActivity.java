package com.zhangju.xingquban.interestclassapp.refactor.me.activity;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.provider.MediaStore;
import android.support.v4.util.Pair;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.fastlib.annotation.Bind;
import com.fastlib.annotation.ContentView;
import com.fastlib.annotation.Event;
import com.fastlib.app.FastActivity;
import com.fastlib.app.FastDialog;
import com.fastlib.app.task.Action;
import com.fastlib.app.task.EmptyAction;
import com.fastlib.app.task.NetAction;
import com.fastlib.app.task.NoReturnAction;
import com.fastlib.app.task.Task;
import com.fastlib.app.task.ThreadType;
import com.fastlib.bean.EventUploading;
import com.fastlib.net.Request;
import com.fastlib.utils.ImageUtil;
import com.fastlib.utils.N;
import com.fastlib.widget.TitleBar;
import com.zhangju.xingquban.R;
import com.zhangju.xingquban.interestclassapp.refactor.common.bean.CommonInterface;
import com.zhangju.xingquban.interestclassapp.refactor.common.bean.Response;
import com.zhangju.xingquban.interestclassapp.refactor.me.adapter.AddVideoAdapter;
import com.zhangju.xingquban.interestclassapp.refactor.me.bean.MeInterface;
import com.zhangju.xingquban.interestclassapp.refactor.me.bean.ResponseUploadImage;
import com.zhangju.xingquban.interestclassapp.refactor.user.UserManager;
import com.zhangju.xingquban.interestclassapp.ui.fragment.me.MyRecrouse.LocationActive;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

/**
 * Created by sgfb on 2017/11/21.
 * 相册管理发布视频
 */
@ContentView(R.layout.act_add_album_video)
public class AlbumManageUploadVideoActivity extends FastActivity{
    final int REQ_VIDEO=1;
    final int REQ_VIDEO_ALBUM=2;
    final int REQ_LOCATION=3;
    @Bind(R.id.titleBar)
    TitleBar mTitleBar;
    @Bind(R.id.location)
    TextView mLocation;
    @Bind(R.id.title)
    EditText mTitle;
    @Bind(R.id.content)
    EditText mContent;
    @Bind(R.id.contact)
    EditText mContact;
    @Bind(R.id.photos)
    GridView mPhotos;
    AddVideoAdapter mAdapter;
    String mLat,mLng;

    @Override
    protected void alreadyPrepared() {
        mTitleBar.setOnLeftClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        mTitleBar.setOnRightClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                commit();
            }
        });
        mPhotos.setAdapter(mAdapter=new AddVideoAdapter(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FastDialog.showListDialog(new String[]{"从视频库选择","录像"}).show(getSupportFragmentManager(), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if(which==0){
                            mPermissionHelper.requestPermission(AlbumManageUploadVideoActivity.this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, new Runnable() {
                                @Override
                                public void run() {
                                    Intent intent=new Intent(Intent.ACTION_GET_CONTENT);
                                    intent.setType("video/*");
                                    startActivityForResult(intent,REQ_VIDEO_ALBUM);
                                }
                            }, new Runnable() {
                                @Override
                                public void run() {
                                    N.showLong(AlbumManageUploadVideoActivity.this,"请给与读取视频库的权限");
                                }
                            });
                        }
                        else{
                            mPermissionHelper.requestPermission(AlbumManageUploadVideoActivity.this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.CAMERA}, new Runnable() {
                                @Override
                                public void run() {
                                    Intent intent=new Intent(MediaStore.ACTION_VIDEO_CAPTURE);
                                    intent.putExtra(MediaStore.EXTRA_VIDEO_QUALITY,0);
                                    intent.putExtra(MediaStore.EXTRA_DURATION_LIMIT,20);
                                    startActivityForResult(intent,REQ_VIDEO);
                                }
                            }, new Runnable() {
                                @Override
                                public void run() {
                                    N.showLong(AlbumManageUploadVideoActivity.this,"请给与开启照相机和写录像数据到存储卡权限");
                                }
                            });
                        }
                    }
                });
            }
        }));
    }

    @Bind(R.id.locationLayout)
    private void selectLocation(){
        startActivityForResult(LocationActive.class,REQ_LOCATION);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode!=RESULT_OK) return;
        if(requestCode==REQ_VIDEO||requestCode==REQ_VIDEO_ALBUM){
            String path= ImageUtil.getVideoPath(this,data.getData());
            if(path==null) return;
            mAdapter.addData(path);
        }
        else if(requestCode==REQ_LOCATION){
            mLat=data.getStringExtra("mLat");
            mLng=data.getStringExtra("mLng");
            mLocation.setText(data.getStringExtra("address"));
        }
    }

    List<Pair<String,String>> mCoverVideoPairList=new ArrayList<>(); //视频封面和视频对列表 左封面右视频

    private void commit(){
        final String title=mTitle.getText().toString();
        final String content=mContent.getText().toString();
        if(TextUtils.isEmpty(title)){
            mTitle.setError("标题不能为空");
            return;
        }
        if(TextUtils.isEmpty(content)){
            mContent.setError("视频介绍不能为空");
            return;
        }
        if(mAdapter.getData()==null||mAdapter.getData().isEmpty()){
            N.showShort(this,"请上传视频");
            return;

        }
        loading();
        startTask(Task.beginCycle(mAdapter.getData())
                .next(new Action<String, Request>() { //上传视频和封面

                    @Override
                    protected Request execute(String param) throws Throwable {
                        String name=System.currentTimeMillis()+".jpg";
                        ImageUtil.saveVideoFrame(AlbumManageUploadVideoActivity.this, param,name);
                        File file=new File(getFilesDir(),name);
                        return Request.obtain(CommonInterface.POST_UPLOAD_IMAGE)
                                .add("files", file)
                                .add("files", new File(param))
                                .setSendEventInterval(500)
                                .setHost(AlbumManageUploadVideoActivity.this)
                                .putHeader("X-CustomToken", UserManager.getInstance().getToken());

                    }
                })
                .next(new NetAction<Response<List<ResponseUploadImage>>>() { //封面和视频成对储存

                    @Override
                    protected void executeAdapt(Response<List<ResponseUploadImage>> listResponse, Request request) {
                        if (listResponse.success && listResponse.data != null && listResponse.data.size() >= 2) {
                            mCoverVideoPairList.add(Pair.create(listResponse.data.get(0).fileName, listResponse.data.get(1).fileName));
                        } else stopTask();
                    }
                })
                .again(new Action<List<Response<List<ResponseUploadImage>>>,Request>(){

                    @Override
                    protected Request execute(List<Response<List<ResponseUploadImage>>> param) throws Throwable {
                        Request request = new Request(MeInterface.POST_MEDIA_ADD);
                        Pair<String, String> coverAndVideoPair = videoAndCoverConcat();
                        request.put("comment", content);
                        request.put("title", title);
                        request.put("latitude", mLat);
                        request.put("longitude", mLng);
                        request.put("type", 1);
                        request.put("isPic", 2);
                        request.put("customerId", UserManager.getInstance().getUser().id);
                        request.put("picVideo", coverAndVideoPair.second);
                        request.put("videoTitlePic", coverAndVideoPair.first);

                        return request;
                    }
                })
                .next(new NetAction<Response>() {

                    @Override
                    protected void executeAdapt(Response response, Request request) {
                        if (response.success) {
                            N.showShort(AlbumManageUploadVideoActivity.this, "发布成功");
                            setResult(RESULT_OK);
                            finish();
                        }
                    }
                }, ThreadType.MAIN), new NoReturnAction<Throwable>() {
            @Override
            public void executeAdapt(Throwable param) {
                param.printStackTrace();
            }
        }, new EmptyAction() {
            @Override
            protected void executeAdapt() {
                dismissLoading();
            }
        });
    }

    /**
     * 用#号来拼接视频和视频封面
     * @return 左封面url#号拼接后字符串，右视频url#号拼接后字符串
     */
    private Pair<String,String> videoAndCoverConcat(){
        StringBuilder coverSb=new StringBuilder();
        StringBuilder videoSb=new StringBuilder();
        for(Pair<String,String> pair:mCoverVideoPairList){
            coverSb.append(pair.first).append("#");
            videoSb.append(pair.second).append("#");
        }
        if(coverSb.length()>0){
            coverSb.deleteCharAt(coverSb.length()-1);
            videoSb.deleteCharAt(videoSb.length()-1);
        }
        return Pair.create(coverSb.toString(),videoSb.toString());
    }

    @Event()
    private void eUpload(EventUploading event){
        int videoIndex=mAdapter.getData().indexOf(event.getPath()); //上传文件广播事件.如果不是-1说明是视频上传，值为视频在列表中的索引位置
        if(videoIndex!=-1){
            int percent= (int) (event.getSendByte()*100/new File(event.getPath()).length());
            loading(String.format(Locale.getDefault(),"上传第%d个视频 ",videoIndex+1)+percent+"%");
        }
    }
}
