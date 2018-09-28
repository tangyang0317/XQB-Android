package com.zhangju.xingquban.interestclassapp.refactor.me.fragment;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v4.util.Pair;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.TextView;

import com.fastlib.annotation.Bind;
import com.fastlib.annotation.ContentView;
import com.fastlib.annotation.Event;
import com.fastlib.annotation.LocalData;
import com.fastlib.app.EventObserver;
import com.fastlib.app.FastDialog;
import com.fastlib.app.FastFragment;
import com.fastlib.app.PhotoResultListener;
import com.fastlib.app.task.Action;
import com.fastlib.app.task.NetAction;
import com.fastlib.app.task.Task;
import com.fastlib.app.task.ThreadType;
import com.fastlib.net.Request;
import com.fastlib.utils.ImageUtil;
import com.fastlib.utils.N;
import com.zhangju.xingquban.R;
import com.zhangju.xingquban.interestclassapp.refactor.common.activity.PreviewImageActivity;
import com.zhangju.xingquban.interestclassapp.refactor.common.bean.CommonInterface;
import com.zhangju.xingquban.interestclassapp.refactor.common.bean.Response;
import com.zhangju.xingquban.interestclassapp.refactor.common.utils.FileHelper;
import com.zhangju.xingquban.interestclassapp.refactor.me.adapter.AlbumManageAdapter;
import com.zhangju.xingquban.interestclassapp.refactor.me.bean.EventAlbumMangeShowDelete;
import com.zhangju.xingquban.interestclassapp.refactor.me.bean.MeInterface;
import com.zhangju.xingquban.interestclassapp.refactor.me.bean.ResponseUploadImage;
import com.zhangju.xingquban.interestclassapp.refactor.me.bean.ResponseVideoAlbum;
import com.zhangju.xingquban.interestclassapp.refactor.user.UserManager;
import com.zhangju.xingquban.interestclassapp.ui.activity.near.ShipinBofangActivity;
import com.zhihu.matisse.Matisse;
import com.zhihu.matisse.MimeType;
import com.zhihu.matisse.engine.impl.GlideEngine;
import com.zhihu.matisse.internal.entity.CaptureStrategy;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

/**
 * Created by sgfb on 2017/11/6.
 * 机构管理中的相册管理内容模块
 */
@ContentView(R.layout.frag_album_manage)
public class AlbumManageFragment extends FastFragment {

    public static final String ARG_INT_MANAGER_TYPE = "managerType"; //管理模块类型 0-图片 2-视频
    final int REQ_VIDEO = 1;
    final int REQ_VIDEO_ALBUM = 2;
    final int REQ_LOCATION = 3;
    final int REQUEST_CODE_CHOOSE_IMG = 4;
    final int REQUEST_CODE_CHOOSE_VIDEO = 4;
    @LocalData(ARG_INT_MANAGER_TYPE)
    int mType;
    @Bind(R.id.grid)
    GridView mGrid;
    @Bind(R.id.upload)
    TextView mUpload;
    @Bind(R.id.delete)
    TextView mDelete;
    AlbumManageAdapter mAdapter;
    List<ResponseVideoAlbum> mDeleteFlagResponseVideoAlbum = new ArrayList<>();
    int mUploadIndex;
    List<Pair<String, String>> mCoverVideoPairList = new ArrayList<>(); //视频封面和视频对列表 左封面右视频


    public static AlbumManageFragment getInstance(int type) {
        Bundle bundle = new Bundle();
        AlbumManageFragment fragment = new AlbumManageFragment();
        bundle.putInt(ARG_INT_MANAGER_TYPE, type);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    protected void alreadyPrepared() {
        if (mType == 2) {
            mUpload.setText("上传视频");
        }
        mGrid.setAdapter(mAdapter = new AlbumManageAdapter(getContext(), mType));
        mGrid.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                List<ResponseVideoAlbum> data = mAdapter.getData();
                ArrayList<String> images = new ArrayList<>();

                for (ResponseVideoAlbum videoAlbum : data) {
                    images.add(mType == 0 ? videoAlbum.picVideo : videoAlbum.videoTitlePic);
                }
                Intent intent;
                if (mType == 0) {
                    intent = new Intent(getContext(), PreviewImageActivity.class);
                    intent.putExtra(PreviewImageActivity.ARG_BOOL_IS_LOCAL, false);
                    intent.putExtra(PreviewImageActivity.ARG_INT_INDEX, position);
                    intent.putExtra(PreviewImageActivity.ARG_LIST_STR_IMAGES, images);
                } else {
                    intent = new Intent(getContext(), ShipinBofangActivity.class);
                    intent.putExtra(ShipinBofangActivity.ARG_STRING_NAME, data.get(position).title);
                    intent.putExtra(ShipinBofangActivity.ARG_STRING_URL, data.get(position).picVideo);
                }
                startActivity(intent);
            }
        });
        mAdapter.setmCallback(new AlbumManageAdapter.DeleteFlagChangeCallback() {
            @Override
            public void callback(ResponseVideoAlbum videoAlbum, boolean isChecked) {
                if (isChecked) {
                    mDeleteFlagResponseVideoAlbum.add(videoAlbum);
                } else {
                    mDeleteFlagResponseVideoAlbum.remove(videoAlbum);
                }
                mDelete.setVisibility(mDeleteFlagResponseVideoAlbum.isEmpty() ? View.GONE : View.VISIBLE);
            }
        });
    }

    @Event
    private void eShowDelete(EventAlbumMangeShowDelete event) {
        if (event.isDeleteFlag())
            mUpload.setVisibility(View.GONE);
        else {
            mDelete.setVisibility(View.GONE);
            mUpload.setVisibility(View.VISIBLE);
        }
        mAdapter.setShowDelete(event.isDeleteFlag());
    }

    /***
     * 上传图片
     */
    private void dialog() {
        FastDialog.showListDialog(new String[]{"拍照", "从手机相册选择"}).show(getChildFragmentManager(), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if (which == 0) {
                    openCamera(new PhotoResultListener() {
                        @Override
                        public void onPhotoResult(String path) {
                            List<String> photoList = new ArrayList<>();
                            photoList.add(path);
                            uploadImage(photoList);
                        }
                    });
                } else {

                }
            }
        });
    }


    private void uploadImage(List<String> photos) {
        final List<File> thumbPhotos = new ArrayList<>();
        loading("解析图像");
        for (String photo : photos) {
            try {
                thumbPhotos.add(ImageUtil.getThumbImageFile(800, 90, photo, FileHelper.getImageCacheFolder(getActivity()).getAbsolutePath()));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        loading(String.format(Locale.getDefault(), "上传图像 %d/%d", mUploadIndex = 0, thumbPhotos.size()));
        startTask(Task.beginCycle(thumbPhotos)
                .next(new Action<File, Request>() {
                    @Override
                    protected Request execute(File param) throws Throwable {
                        return Request.obtain(CommonInterface.POST_UPLOAD_IMAGE).put("files", param)
                                .putHeader("X-CustomToken", UserManager.getInstance().getToken());
                    }
                })
                .next(new NetAction<Response<List<ResponseUploadImage>>>() {

                    @Override
                    protected void executeAdapt(Response<List<ResponseUploadImage>> listResponse, Request request) {
                        loading(String.format(Locale.getDefault(), "上传图像 %d/%d", ++mUploadIndex, thumbPhotos.size()));
                        if (!listResponse.success || listResponse.data == null || listResponse.data.isEmpty())
                            stopTask();
                    }
                })
                .next(new Action<Response<List<ResponseUploadImage>>, String>() {

                    @Override
                    protected String execute(Response<List<ResponseUploadImage>> param) throws Throwable {
                        return param.data.get(0).fileName;
                    }
                })
                .again(new Action<List<String>, String>() {

                    @Override
                    protected String execute(List<String> param) throws Throwable {
                        StringBuilder sb = new StringBuilder();
                        for (String s : param)
                            sb.append(s).append("#");
                        sb.deleteCharAt(sb.length() - 1);
                        return sb.toString();
                    }
                })
                .next(new Action<String, Request>() {

                    @Override
                    protected Request execute(String param) throws Throwable {
                        Request request = Request.obtain(MeInterface.POST_MEDIA_ADD);
                        request.put("title", "");
                        request.put("comment", "");
                        request.put("customerId", UserManager.getInstance().getUser().id);
                        request.put("isPic", 0);
                        request.put("latitude", "");
                        request.put("longitude", "");
                        request.put("picVideo", param);
                        return request;
                    }
                })
                .next(new NetAction<Response>() {

                    @Override
                    protected void executeAdapt(Response response, Request request) {
                        if (response.success) {
                            dismissLoading();
                            N.showShort(getActivity(), "上传成功");
                        }
                    }

                }, ThreadType.MAIN)

        );
    }


    private void videoDialog() {
        FastDialog.showListDialog(new String[]{"从视频库选择", "录像"}).show(getChildFragmentManager(), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if (which == 0) {
                    Matisse.from(AlbumManageFragment.this)
                            .choose(MimeType.ofVideo())
                            .capture(true)
                            .captureStrategy(new CaptureStrategy(true, "com.zhangju.xingquban.fileprovider"))
                            .countable(true)
                            .maxSelectable(1)
                            .gridExpectedSize(getResources().getDimensionPixelSize(R.dimen.grid_expected_size))
                            .restrictOrientation(ActivityInfo.SCREEN_ORIENTATION_UNSPECIFIED)
                            .forResult(REQUEST_CODE_CHOOSE_VIDEO);

                } else {
                    mPermissionHelper.requestPermission(getActivity(), new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.CAMERA}, new Runnable() {
                        @Override
                        public void run() {
                            Intent intent = new Intent(MediaStore.ACTION_VIDEO_CAPTURE);
                            intent.putExtra(MediaStore.EXTRA_VIDEO_QUALITY, 0);
                            intent.putExtra(MediaStore.EXTRA_DURATION_LIMIT, 20);
                            startActivityForResult(intent, REQ_VIDEO);
                        }
                    }, new Runnable() {
                        @Override
                        public void run() {
                            N.showLong(getActivity(), "请给与开启照相机和写录像数据到存储卡权限");
                        }
                    });
                }
            }
        });
    }


    @Bind(R.id.upload)
    private void upload() {
        if (mType == 0) {
            Matisse.from(AlbumManageFragment.this)
                    .choose(MimeType.ofImage())
                    .capture(true)
                    .captureStrategy(new CaptureStrategy(true, "com.zhangju.xingquban.fileprovider"))
                    .countable(true)
                    .maxSelectable(9)
                    .gridExpectedSize(getResources().getDimensionPixelSize(R.dimen.grid_expected_size))
                    .restrictOrientation(ActivityInfo.SCREEN_ORIENTATION_UNSPECIFIED)
                    .thumbnailScale(0.85f)
                    .imageEngine(new GlideEngine())
                    .forResult(REQUEST_CODE_CHOOSE_IMG);
        } else if (mType == 2) {
            videoDialog();
        } else {
            System.out.println("一个未知的相册管理类型");
        }
    }

    @Bind(R.id.delete)
    private void delete() {
        mAdapter.deleteSelectImage();
        EventObserver.getInstance().sendEvent(getContext(), new EventAlbumMangeShowDelete(false));
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQ_VIDEO || requestCode == REQ_VIDEO_ALBUM) {
            String path = ImageUtil.getVideoPath(getActivity(), data.getData());
            if (path != null) {
                List<String> videoPathList = new ArrayList<>();
                videoPathList.add(path);
                commit(videoPathList);
            }
        } else if (requestCode == REQUEST_CODE_CHOOSE_IMG && resultCode == getActivity().RESULT_OK) {
            List<String> pathResult = Matisse.obtainPathResult(data);
            if (pathResult != null && pathResult.size() > 0) {
                uploadImage(pathResult);
            }
        } else if (requestCode == REQUEST_CODE_CHOOSE_VIDEO && resultCode == getActivity().RESULT_OK) {
            List<String> path = Matisse.obtainPathResult(data);
            commit(path);
        }

    }


    private void commit(List<String> videoPathList) {
        loading();
        startTask(Task.beginCycle(videoPathList)
                .next(new Action<String, Request>() { //上传视频和封面
                    @Override
                    protected Request execute(String param) throws Throwable {
                        String name = System.currentTimeMillis() + ".jpg";
                        ImageUtil.saveVideoFrame(getActivity(), param, name);
                        return Request.obtain(CommonInterface.POST_UPLOAD_IMAGE)
                                .add("files", new File(name))
                                .add("files", new File(param))
                                .setSendEventInterval(500)
                                .setHost(getActivity())
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
                .again(new Action<List<Response<List<ResponseUploadImage>>>, Request>() {

                    @Override
                    protected Request execute(List<Response<List<ResponseUploadImage>>> param) throws Throwable {
                        Request request = new Request(MeInterface.POST_MEDIA_ADD);
                        Pair<String, String> coverAndVideoPair = videoAndCoverConcat();
                        request.put("comment", "");
                        request.put("title", "");
                        request.put("latitude", "");
                        request.put("longitude", "");
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
                            N.showShort(getActivity(), "发布成功");
                            finish();
                        }
                    }
                }, ThreadType.MAIN));
    }


    /**
     * 用#号来拼接视频和视频封面
     *
     * @return 左封面url#号拼接后字符串，右视频url#号拼接后字符串
     */
    private Pair<String, String> videoAndCoverConcat() {
        StringBuilder coverSb = new StringBuilder();
        StringBuilder videoSb = new StringBuilder();
        for (Pair<String, String> pair : mCoverVideoPairList) {
            coverSb.append(pair.first).append("#");
            videoSb.append(pair.second).append("#");
        }
        if (coverSb.length() > 0) {
            coverSb.deleteCharAt(coverSb.length() - 1);
            videoSb.deleteCharAt(videoSb.length() - 1);
        }
        return Pair.create(coverSb.toString(), videoSb.toString());
    }

}