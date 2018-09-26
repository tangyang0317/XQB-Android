package com.zhangju.xingquban.interestclassapp.refactor.me.activity;

import android.content.Intent;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.TextView;

import com.fastlib.annotation.Bind;
import com.fastlib.annotation.ContentView;
import com.fastlib.app.task.Action;
import com.fastlib.app.task.EmptyAction;
import com.fastlib.app.task.NetAction;
import com.fastlib.app.task.NoReturnAction;
import com.fastlib.app.task.Task;
import com.fastlib.app.task.ThreadType;
import com.fastlib.net.Request;
import com.fastlib.utils.ImageUtil;
import com.fastlib.utils.N;
import com.fastlib.widget.TitleBar;
import com.jph.takephoto.model.TResult;
import com.zhangju.xingquban.R;
import com.zhangju.xingquban.interestclassapp.base.TakePhotoFastActivity;
import com.zhangju.xingquban.interestclassapp.refactor.common.bean.CommonInterface;
import com.zhangju.xingquban.interestclassapp.refactor.common.bean.Response;
import com.zhangju.xingquban.interestclassapp.refactor.common.utils.FileHelper;
import com.zhangju.xingquban.interestclassapp.refactor.me.adapter.AddImageAdapter;
import com.zhangju.xingquban.interestclassapp.refactor.me.bean.MeInterface;
import com.zhangju.xingquban.interestclassapp.refactor.me.bean.ResponseUploadImage;
import com.zhangju.xingquban.interestclassapp.refactor.user.UserManager;
import com.zhangju.xingquban.interestclassapp.ui.fragment.me.MyRecrouse.LocationActive;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import static android.app.Activity.RESULT_OK;

/**
 * Created by sgfb on 2017/11/6.
 * 机构管理中相册管理中上传图片
 */
@ContentView(R.layout.act_add_album_image)
public class AlbumMangeUploadImageActivity extends TakePhotoFastActivity {
    @Bind(R.id.titleBar)
    TitleBar mTitleBar;
    @Bind(R.id.location)
    TextView mLocation;
    @Bind(R.id.title)
    EditText mTitle;
    @Bind(R.id.content)
    EditText mContent;
    @Bind(R.id.photos)
    GridView mPhotos;
    AddImageAdapter mAdapter;
    String mLat, mLng;
    int mUploadIndex;

    @Override
    protected void alreadyPrepared() {
        mPhotos.setAdapter(mAdapter = new AddImageAdapter());
        mAdapter.setMaxLimit(9);
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
        final String content = mContent.getText().toString();
        List<String> photos = mAdapter.getData();

        if (TextUtils.isEmpty(title)) {
            mTitle.setError("请输入标题");
            mTitle.requestFocus();
            return;
        }
        if (TextUtils.isEmpty(content)) {
            mContent.setError("请输入内容");
            mContent.requestFocus();
            return;
        }
        if (photos.isEmpty()) {
            N.showShort(this, "上传的图片为空");
            return;
        }
        final List<File> thumbPhotos = new ArrayList<>();
        loading("解析图像");
        for (String photo : photos) {
            try {
                thumbPhotos.add(ImageUtil.getThumbImageFile(800, 90, photo, FileHelper.getImageCacheFolder(this).getAbsolutePath()));
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
                        request.put("title", title);
                        request.put("comment", content);
                        request.put("customerId", UserManager.getInstance().getUser().id);
                        request.put("isPic", 0);
                        request.put("latitude", mLat);
                        request.put("longitude", mLng);
                        request.put("picVideo", param);
                        return request;
                    }
                })
                .next(new NetAction<Response>() {

                    @Override
                    protected void executeAdapt(Response response, Request request) {
                        if (response.success) {
                            N.showShort(AlbumMangeUploadImageActivity.this, "上传成功");
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

    @Bind(R.id.locationLayout)
    private void selectLocation() {
        startActivityForResult(LocationActive.class, 1);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode != RESULT_OK) return;
        if (requestCode == 1) {
            mLat = data.getStringExtra("mLat");
            mLng = data.getStringExtra("mLng");
            mLocation.setText(data.getStringExtra("address"));
        }
    }

    @Override
    public void takeSuccess(TResult result) {
        super.takeSuccess(result);
        mAdapter.onTakeSuccess(result);
    }
}