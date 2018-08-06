package com.zhangju.xingquban.interestclassapp.refactor.me.activity;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.fastlib.annotation.Bind;
import com.fastlib.annotation.ContentView;
import com.fastlib.annotation.Event;
import com.fastlib.app.EventObserver;
import com.fastlib.app.FastActivity;
import com.fastlib.app.FastDialog;
import com.fastlib.app.PhotoResultListener;
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
import com.zhangju.xingquban.interestclassapp.refactor.common.bean.EventRefresh;
import com.zhangju.xingquban.interestclassapp.refactor.common.bean.Response;
import com.zhangju.xingquban.interestclassapp.refactor.me.bean.MeInterface;
import com.zhangju.xingquban.interestclassapp.refactor.me.bean.ResponseUploadImage;
import com.zhangju.xingquban.interestclassapp.refactor.me.fragment.ResourceManageFragment;
import com.zhangju.xingquban.interestclassapp.refactor.user.UserManager;
import com.zhangju.xingquban.interestclassapp.ui.fragment.me.MyRecrouse.ChargeActivity;
import com.zhangju.xingquban.interestclassapp.ui.fragment.me.MyRecrouse.LocationActive;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Locale;

/**
 * Created by sgfb on 2017/11/7.
 * 发布视频类型资源
 */
@ContentView(R.layout.act_send_vidoe)
public class SendVideoActivity
        extends FastActivity {
    final int REQ_CHARGE      = 1;
    final int REQ_LOCATION    = 2;
    final int REQ_VIDEO       = 3;
    final int REQ_VIDEO_ALBUM = 4;

    @Bind(R.id.titleBar)
    TitleBar  mTitleBar;
    @Bind(R.id.videoCover)
    ImageView mVideoCover;
    @Bind(R.id.title)
    EditText  mTitle;
    @Bind(R.id.intro)
    EditText  mIntro;
    @Bind(R.id.charge)
    TextView  mCharge;
    @Bind(R.id.address)
    TextView  mAddress;
    @Bind(R.id.image_vedio_cover)
    ImageView mIvTitle;
    String mCoverPath;
    String mVideoCoverPath;
    String mVideoPath;
    String mCoverUrl;
    String mLng, mLat;
    int mPrice = 0;

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
    }

    private void commit() {
        final String title = mTitle.getText().toString();
        final String intro = mIntro.getText().toString();
        final String address = mAddress.getText().toString();

        if (TextUtils.isEmpty(title)) {
            mTitle.setError("标题不能为空");
            return;
        }
        if (TextUtils.isEmpty(mCoverPath)) {
            N.showShort(this, "请上传封面");
            return;
        }
        if (TextUtils.isEmpty(mVideoPath)) {
            N.showShort(this, "请上传视频");
            return;
        }
        loading("上传封面...");
        startTask(Task.begin(mCoverPath)
                .next(new Action<String, Request>() {
                    @Override
                    protected Request execute(String param) throws Throwable {
                        return Request.obtain(CommonInterface.POST_UPLOAD_IMAGE).put("files", new File(param))
                                .putHeader("X-CustomToken", UserManager.getInstance().getToken());
                    }
                })
                .next(new NetAction<Response<List<ResponseUploadImage>>>() {

                    @Override
                    protected void executeAdapt(Response<List<ResponseUploadImage>> listResponse, Request request) {
                        if (listResponse.success && listResponse.data != null && !listResponse.data.isEmpty())
                            mCoverUrl = listResponse.data.get(0).fileName;
                        else stopTask();
                    }
                })
                .next(new Action<Response<List<ResponseUploadImage>>, Request>() {

                    @Override
                    protected Request execute(Response<List<ResponseUploadImage>> param) throws Throwable {
                        loading("上传视频");
                        return Request.obtain(CommonInterface.POST_UPLOAD_IMAGE).put("files", new File(mVideoPath))
                                .setSendEventInterval(100).setHost(SendVideoActivity.this)
                                .putHeader("X-CustomToken", UserManager.getInstance().getToken());
                    }
                }, ThreadType.MAIN)
                .next(new NetAction<Response<List<ResponseUploadImage>>>() {

                    @Override
                    protected void executeAdapt(Response<List<ResponseUploadImage>> listResponse, Request request) {
                        if (listResponse.success && listResponse.data != null && !listResponse.data.isEmpty())
                            mVideoUrl = listResponse.data.get(0).fileName;
                        else stopTask();
                    }
                })
                .next(new Action<Response<List<ResponseUploadImage>>, Request>() {

                    @Override
                    protected Request execute(Response<List<ResponseUploadImage>> param) throws Throwable {
                        loading("发布视频资源");
                        Request request = new Request(MeInterface.POST_ADD_RESOURCE);
                        request.putHeader("X-CustomToken", UserManager.getInstance().getToken());
                        request.put("title", title);
                        request.put("summary", intro);
                        request.put("types", "video");
                        request.put("customerId", UserManager.getInstance().getUser().id);
                        request.put("titlePicture", mCoverUrl);
                        request.put("price", mPrice);
                        request.put("isCharge", mPrice <= 0 ? 0 : 1);
                        request.put("muitlUploadPicture", "");
                        request.put("videoUrl", mVideoUrl);
                        request.put("address", address);
                        request.put("latitude", mLat);
                        request.put("longitude", mLng);
                        return request;
                    }
                }, ThreadType.MAIN)
                .next(new NetAction<Response>() {

                    @Override
                    protected void executeAdapt(Response response, Request request) {
                        if (response.success) {
                            N.showShort(SendVideoActivity.this, "上传成功");
                            EventObserver.getInstance().sendEvent(SendVideoActivity.this, new EventRefresh
                                    (ResourceManageFragment.class));
                            finish();
                        } else N.showShort(SendVideoActivity.this, "上传失败");
                    }
                }, ThreadType.MAIN), new NoReturnAction<Throwable>() {
            @Override
            public void executeAdapt(Throwable param) {
                dismissLoading();
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        N.showShort(SendVideoActivity.this, "上传失败");
                    }
                });
            }
        }, new EmptyAction() {
            @Override
            protected void executeAdapt() {
                dismissLoading();
            }
        });
    }

    String mVideoUrl;

    @Event
    private void eUploadVideoProgress(EventUploading event) {
        if (TextUtils.equals(event.getPath(), mVideoPath)) {
            File video = new File(event.getPath());
            long fileLength = video.length();
            int percent = (int) (100 * event.getSendByte() / fileLength);
            loading("上传视频 " + percent + "%");
        }
    }

    @Bind(R.id.videoCover)
    private void changeVideo() {
        FastDialog.showListDialog(new String[]{"从视频库选择", "录像"}).show(getSupportFragmentManager(), new DialogInterface
                .OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if (which == 0) {
                    mPermissionHelper.requestPermission(SendVideoActivity.this, new String[]{Manifest.permission
                            .READ_EXTERNAL_STORAGE}, new Runnable() {
                        @Override
                        public void run() {
                            Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
                            intent.setType("video/*");
                            startActivityForResult(intent, REQ_VIDEO_ALBUM);
                        }
                    }, new Runnable() {
                        @Override
                        public void run() {
                            N.showLong(SendVideoActivity.this, "请给与读取视频库的权限");
                        }
                    });
                } else {
                    mPermissionHelper.requestPermission(SendVideoActivity.this, new String[]{Manifest.permission
                            .WRITE_EXTERNAL_STORAGE, Manifest.permission.CAMERA}, new Runnable() {
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
                            N.showLong(SendVideoActivity.this, "请给与开启照相机和写录像数据到存储卡权限");
                        }
                    });
                }
            }
        });
    }

    @Bind(R.id.changeCover)
    private void changeCover() {
        FastDialog.showListDialog(new String[]{"拍照", "从手机相册选择"}).show(getSupportFragmentManager(), new DialogInterface
                .OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if (which == 0) openCamera(new PhotoResultListener() {
                    @Override
                    public void onPhotoResult(String path) {
                        //                        mCoverBg.setBackground(new BitmapDrawable(getResources(), BitmapFactory
                        // .decodeFile(path)));
                        mCoverPath = path;
                        Glide.with(mContext)
                                .load(path)
                                .into(mIvTitle);
                    }
                });
                else {
                    openAlbum(new PhotoResultListener() {
                        @Override
                        public void onPhotoResult(String path) {
                            mCoverPath = path;
                            Glide.with(mContext)
                                    .load(path)
                                    .into(mIvTitle);
                        }
                    });
                }
            }
        });
    }

    @Bind(R.id.addressLayout)
    private void selectAddress() {
        startActivityForResult(LocationActive.class, REQ_LOCATION);
    }

    @Bind(R.id.fare)
    private void changeCharge() {
        Intent intent = new Intent(this, ChargeActivity.class);
        intent.putExtra(ChargeActivity.ARG_RES_INT_PRICE, mPrice);
        startActivityForResult(intent, REQ_CHARGE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode != RESULT_OK) return;
        if (requestCode == REQ_LOCATION) {
            mAddress.setText(data.getStringExtra("address"));
            mLat = data.getStringExtra("mLat");
            mLng = data.getStringExtra("mLng");
        } else if (requestCode == REQ_CHARGE) {
            mPrice = data.getIntExtra(ChargeActivity.ARG_RES_INT_PRICE, 0);
            mCharge.setText(mPrice <= 0 ? "免费" : String.format(Locale.getDefault(), "%d￥", mPrice));
        } else if (requestCode == REQ_VIDEO || requestCode == REQ_VIDEO_ALBUM) {
            String path = ImageUtil.getVideoPath(this, data.getData());
            if (path == null) return;
            mVideoPath = path;
            try {
                ImageUtil.saveVideoFrame(this, mVideoPath, "tempVideoCover.jpg");
            } catch (IOException e) {
                e.printStackTrace();
            }
            mVideoCoverPath = new File(getFilesDir(), "tempVideoCover.jpg").getAbsolutePath();
            Glide.with(this)
                    .load(new File(mVideoCoverPath))
                    .into(mVideoCover);
        }
    }
}