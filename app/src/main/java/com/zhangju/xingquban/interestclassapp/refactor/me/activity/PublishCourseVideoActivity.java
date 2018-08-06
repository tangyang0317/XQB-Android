package com.zhangju.xingquban.interestclassapp.refactor.me.activity;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.provider.MediaStore;
import android.support.v4.util.Pair;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.fastlib.annotation.Bind;
import com.fastlib.annotation.ContentView;
import com.fastlib.annotation.Event;
import com.fastlib.annotation.LocalData;
import com.fastlib.app.EventObserver;
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
import com.fastlib.net.SimpleListener;
import com.fastlib.utils.ImageUtil;
import com.fastlib.utils.N;
import com.fastlib.widget.TitleBar;
import com.zhangju.xingquban.R;
import com.zhangju.xingquban.interestclassapp.refactor.common.bean.CommonInterface;
import com.zhangju.xingquban.interestclassapp.refactor.common.bean.EventRefresh;
import com.zhangju.xingquban.interestclassapp.refactor.common.bean.Response;
import com.zhangju.xingquban.interestclassapp.refactor.me.bean.MeInterface;
import com.zhangju.xingquban.interestclassapp.refactor.me.bean.ResponseCourseVideo;
import com.zhangju.xingquban.interestclassapp.refactor.me.bean.ResponseUploadImage;
import com.zhangju.xingquban.interestclassapp.refactor.me.bean.ResponseVideoFolderWithVideo;
import com.zhangju.xingquban.interestclassapp.refactor.user.UserManager;
import com.zhangju.xingquban.interestclassapp.ui.fragment.me.MyRecrouse.ChargeActivity;
import com.zhangju.xingquban.interestclassapp.ui.fragment.me.MyRecrouse.LocationActive;

import java.io.File;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

/**
 * Created by sgfb on 2017/11/21.
 * 发布课程视频
 */
@ContentView(R.layout.act_publish_course_video)
public class PublishCourseVideoActivity extends FastActivity {
    public static final String ARG_SER_COURSE_VIDEO = "video"; //如果id存在表示是修改，不存在则是新发布视频
    final int REQ_LOCATION = 1;
    final int REQ_CHANGE = 2;
    final int REQ_VIDEO = 3;
    final int REQ_VIDEO_ALBUM = 4;
    final int REQ_FOLDER = 5;

    @LocalData(ARG_SER_COURSE_VIDEO)
    ResponseCourseVideo mOldVideoData;
    @Bind(R.id.titleBar)
    TitleBar mTitleBar;
    @Bind(R.id.title)
    EditText mTitle;
    @Bind(R.id.content)
    EditText mContent;
    @Bind(R.id.contact)
    EditText mContact;
    @Bind(R.id.address)
    TextView mAddress;
    @Bind(R.id.price)
    TextView mPrice;
    @Bind(R.id.toFolder)
    TextView mFolder;
    //    @Bind(R.id.videos)
//    GridView mVideos;
    @Bind(R.id.video)
    ImageView mVideo;
    //    AddVideoAdapter mAdapter;
    String mVideoPath;
    String mLng = "0", mLat = "0";
    String mFolderId;
    int mPriceInt = 0;
    Pair<String, String> mCoverWithVideoPairs; //视频封面url和视频对

    @Override
    protected void alreadyPrepared() {
        mVideo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FastDialog.showListDialog(new String[]{"从视频库选择", "录像"}).show(getSupportFragmentManager(), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if (which == 0) {
                            mPermissionHelper.requestPermission(PublishCourseVideoActivity.this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, new Runnable() {
                                @Override
                                public void run() {
                                    Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
                                    intent.setType("video/*");
                                    startActivityForResult(intent, REQ_VIDEO_ALBUM);
                                }
                            }, new Runnable() {
                                @Override
                                public void run() {
                                    N.showLong(PublishCourseVideoActivity.this, "请给与读取视频库的权限");
                                }
                            });
                        } else {
                            mPermissionHelper.requestPermission(PublishCourseVideoActivity.this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.CAMERA}, new Runnable() {
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
                                    N.showLong(PublishCourseVideoActivity.this, "请给与开启照相机和写录像数据到存储卡权限");
                                }
                            });
                        }
                    }
                });
            }
        });
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
        if (mOldVideoData != null) {
            DecimalFormat df = new DecimalFormat("##.##");
            mTitle.setText(mOldVideoData.title);
            mContent.setText(mOldVideoData.comment);
            mContact.setText(mOldVideoData.contactWay);
            mFolder.setText(mOldVideoData.fromFolder);
            mPrice.setText(mOldVideoData.price <= 0 ? "免费" : df.format(mOldVideoData.price));
            mLat = TextUtils.isEmpty(mOldVideoData.latitude) ? "0" : mOldVideoData.latitude;
            mLng = TextUtils.isEmpty(mOldVideoData.longitude) ? "0" : mOldVideoData.longitude;
            mFolderId = mOldVideoData.videoFilesId;
            mCoverWithVideoPairs = Pair.create(mOldVideoData.videoTitlePic, mOldVideoData.videoStr);
            mAddress.setText(mOldVideoData.address);
            Glide.with(this).load(mOldVideoData.videoTitlePic).into(mVideo);
        }
        requestVideoFolder();
    }

    private void requestVideoFolder() {
        Request request = Request.obtain(MeInterface.POST_VIDEO_FOLDER);
        request.put("customerId", UserManager.getInstance().getUser().id);
        request.setListener(new SimpleListener<Response<List<ResponseVideoFolderWithVideo>>>() {

            @Override
            public void onResponseListener(Request r, Response<List<ResponseVideoFolderWithVideo>> result) {
                if (result.success) {
                    if (result.data != null && result.data.size() > 0) {
                        mFolderId = result.data.get(0).id;
                        mFolder.setText(result.data.get(0).name);
                    }
                }
            }
        });
        net(request);
    }

    private void commit() {
        final String title = mTitle.getText().toString();
        final String content = mContent.getText().toString();
        final String contact = mContact.getText().toString();
        final String address = mAddress.getText().toString();
//        List<String> videoPath=mAdapter.getData();

        if (TextUtils.isEmpty(title)) {
            mTitle.setError("标题不能为空");
            return;
        }
        if (TextUtils.isEmpty(content)) {
            mContent.setError("介绍不能为空");
            return;
        }
        if (TextUtils.isEmpty(mVideoPath) && mOldVideoData == null) {
            N.showShort(this, "上传的视频不能为空");
            return;
        }
        if (TextUtils.isEmpty(mFolderId)) {
            N.showShort(this, "请选择上传的视频文件夹");
            return;
        }
        loading();
        if (mOldVideoData != null && TextUtils.isEmpty(mVideoPath)) {
            Request request = new Request(MeInterface.POST_ADD_COURSE_VIDEO);
            request.put("id", mOldVideoData.id);
            request.put("customerId", UserManager.getInstance().getUser().id);
            request.put("title", title);
            request.put("comment", content);
            request.put("contactWay", contact);
            request.put("isCharge", mPriceInt <= 0 ? 0 : 1);
            request.put("price", mPriceInt);
            request.put("address", address);
            request.put("latitude", mLat);
            request.put("longitude", mLng);
            request.put("videoTitlePic", mCoverWithVideoPairs.first);
            request.put("videoStr", mCoverWithVideoPairs.second);
            request.put("videoFilesId", mFolderId);
            request.setListener(new SimpleListener<Response>() {

                @Override
                public void onResponseListener(Request r, Response result) {
                    dismissLoading();
                    if (result.success) {
                        N.showShort(PublishCourseVideoActivity.this, "修改视频成功");
                        EventObserver.getInstance().sendEvent(PublishCourseVideoActivity.this, new EventRefresh(CourseVideoActivity.class));
                        finish();
                    }
                }

                @Override
                public void onErrorListener(Request r, String error) {
                    super.onErrorListener(r, error);
                    dismissLoading();
                }
            });
            net(request);
        } else {
            startTask(Task.begin(mVideoPath)
                    .next(new Action<String, Request>() {

                        @Override
                        protected Request execute(String param) throws Throwable {
                            String coverName = System.currentTimeMillis() + ".jpg";
                            ImageUtil.saveVideoFrame(PublishCourseVideoActivity.this, param, coverName);
                            File file = new File(getFilesDir(), coverName);
                            return Request.obtain(CommonInterface.POST_UPLOAD_IMAGE)
                                    .add("files", file)
                                    .add("files", new File(param))
                                    .setSendEventInterval(200)
                                    .putHeader("X-CustomToken", UserManager.getInstance().getToken());
                        }
                    })
                    .next(new NetAction<Response<List<ResponseUploadImage>>>() {

                        @Override
                        protected void executeAdapt(Response<List<ResponseUploadImage>> listResponse, Request request) {
                            if (listResponse.success && listResponse.data != null && listResponse.data.size() >= 2)
                                mCoverWithVideoPairs = Pair.create(listResponse.data.get(0).fileName, listResponse.data.get(1).fileName);
                            else stopTask();
                        }
                    })
                    .again(new Action<List<Response<List<ResponseUploadImage>>>, Request>() {

                        @Override
                        protected Request execute(List<Response<List<ResponseUploadImage>>> param) throws Throwable {
                            Request request = new Request(MeInterface.POST_ADD_COURSE_VIDEO);
                            request.putHeader("X-CustomToken", UserManager.getInstance().getToken());
                            request.put("customerId", UserManager.getInstance().getUser().id);
                            request.put("title", title);
                            request.put("comment", content);
                            request.put("contactWay", contact);
                            request.put("isCharge", mPriceInt <= 0 ? 0 : 1);
                            request.put("price", mPriceInt);
                            request.put("address", address);
                            request.put("latitude", mLat);
                            request.put("longitude", mLng);
                            request.put("videoTitlePic", mCoverWithVideoPairs.first);
                            request.put("videoStr", mCoverWithVideoPairs.second);
                            request.put("videoFilesId", mFolderId);
                            if (mOldVideoData != null)
                                request.put("id", mOldVideoData.id);
                            return request;
                        }
                    })
                    .next(new NetAction<Response>() {

                        @Override
                        protected void executeAdapt(Response response, Request request) {
                            if (response.success) {
                                N.showShort(PublishCourseVideoActivity.this, "视频上传成功");
                                EventObserver.getInstance().sendEvent(PublishCourseVideoActivity.this, new EventRefresh(CourseVideoActivity.class));
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
    }

    @Event
    private void eUploadFile(EventUploading event) {
        long size = new File(event.getPath()).length();
        loading(String.format(Locale.getDefault(), "%s上传 %s%", TextUtils.equals(event.getPath(), mVideoPath) ? "视频" : "封面", event.getSendByte() * 100 / size));
    }

    @Bind(R.id.priceLayout)
    private void changePrice() {
        Intent intent = new Intent(this, ChargeActivity.class);
        intent.putExtra(ChargeActivity.ARG_RES_INT_PRICE, mPriceInt);
        startActivityForResult(intent, REQ_CHANGE);
    }

    @Bind(R.id.locationLayout)
    private void selectLocation() {
        startActivityForResult(LocationActive.class, REQ_LOCATION);
    }

    @Bind(R.id.folderLayout)
    private void selectToFolder() {
        startActivityForResult(SelectVideoFolderActivity.class, REQ_FOLDER);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode != RESULT_OK) return;
        if (requestCode == REQ_LOCATION) {
            mLat = data.getStringExtra("mLat");
            mLng = data.getStringExtra("mLng");
            mAddress.setText(data.getStringExtra("address"));
        } else if (requestCode == REQ_CHANGE) {
            mPriceInt = data.getIntExtra(ChargeActivity.ARG_RES_INT_PRICE, 0);
            mPrice.setText(mPriceInt > 0 ? String.format(Locale.getDefault(), "%d￥", mPriceInt) : "免费");
        } else if (requestCode == REQ_VIDEO || requestCode == REQ_VIDEO_ALBUM) {
            mVideoPath = ImageUtil.getVideoPath(this, data.getData());
            if (mVideoPath == null) return;
            mVideo.setImageBitmap(ImageUtil.getVideoFirstFrame(mVideoPath));
        } else if (requestCode == REQ_FOLDER) {
            mFolderId = data.getStringExtra(SelectVideoFolderActivity.RES_STR_FOLDER_ID);
            mFolder.setText(data.getStringExtra(SelectVideoFolderActivity.RES_STR_FOLDER_NAME));
        }
    }
}