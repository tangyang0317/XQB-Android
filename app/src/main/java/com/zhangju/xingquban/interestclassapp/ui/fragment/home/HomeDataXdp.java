package com.zhangju.xingquban.interestclassapp.ui.fragment.home;

import android.os.Environment;
import android.text.TextUtils;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.fastlib.annotation.Bind;
import com.fastlib.annotation.ContentView;
import com.fastlib.annotation.LocalData;
import com.fastlib.app.EventObserver;
import com.fastlib.app.task.Action;
import com.fastlib.app.task.EmptyAction;
import com.fastlib.app.task.NetAction;
import com.fastlib.app.task.NoReturnAction;
import com.fastlib.app.task.Task;
import com.fastlib.app.task.ThreadType;
import com.fastlib.net.Request;
import com.fastlib.net.SimpleListener;
import com.fastlib.utils.ImageUtil;
import com.fastlib.utils.N;
import com.jph.takephoto.model.TResult;
import com.zhangju.xingquban.R;
import com.zhangju.xingquban.interestclassapp.RetrofitInterface.INear;
import com.zhangju.xingquban.interestclassapp.base.TakePhotoFastActivity;
import com.zhangju.xingquban.interestclassapp.refactor.common.bean.CommonInterface;
import com.zhangju.xingquban.interestclassapp.refactor.common.bean.Response;
import com.zhangju.xingquban.interestclassapp.refactor.common.widget.NoScrollGridView;
import com.zhangju.xingquban.interestclassapp.refactor.me.adapter.AddImageAdapter;
import com.zhangju.xingquban.interestclassapp.refactor.me.bean.ResponseUploadImage;
import com.zhangju.xingquban.interestclassapp.refactor.user.UserManager;
import com.zhangju.xingquban.interestclassapp.ui.fragment.home.zyzs.RefreshEvent;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

@ContentView(R.layout.activity_home_data_xdp)
public class HomeDataXdp extends TakePhotoFastActivity {
    public static final  String ARG_STRING_ID="mid";
    @LocalData(ARG_STRING_ID)
    String resourcesId;
    @Bind(R.id.start1)
    ImageView start1;
    @Bind(R.id.start2)
    ImageView start2;
    @Bind(R.id.start3)
    ImageView start3;
    @Bind(R.id.start4)
    ImageView start4;
    @Bind(R.id.start5)
    ImageView start5;
    @Bind(R.id.commit)
    TextView commit;
    @Bind(R.id.back)
    ImageView back;

    @Bind(R.id.textView10)
    TextView textView10;
    @Bind(R.id.edt_summary)
    EditText edtSummary;
    @Bind(R.id.photos)
    NoScrollGridView mPhotos;

    private int levels=3;
    AddImageAdapter mAdapter;
    String mLat, mLng;
    int mUploadIndex;
    @Override
    protected void alreadyPrepared() {

        mPhotos.setAdapter(mAdapter = new AddImageAdapter());

    }

    @Bind(R.id.commit)
    private void commitFile() {

        List<String> photos = mAdapter.getData();
        final List<File> thumbPhotos = new ArrayList<>();
        if(TextUtils.isEmpty(edtSummary.getText().toString())){
            edtSummary.setError("请输入内容");
            edtSummary.requestFocus();
            return;
        }
        if(photos.isEmpty()){
         /*   N.showShort(this,"上传的图片为空");
            return;*/
        }
        loading("解析图像");
        for (String photo : photos) {
            try {
                thumbPhotos.add(ImageUtil.getThumbImageFile(800, 90, photo, Environment.getExternalStorageDirectory().getAbsolutePath()));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        if(photos.size()>0){
            loading(String.format(Locale.getDefault(), "上传图像 %d/%d", mUploadIndex = 0, thumbPhotos.size()));
            startTask(Task.beginCycle(thumbPhotos)
                    .next(new Action<File, Request>() {
                        @Override
                        protected Request execute(File param) throws Throwable {
                            return Request.obtain(CommonInterface.POST_UPLOAD_IMAGE).put("files", param)
                            .putHeader("X-CustomToken", UserManager.getInstance().getToken());

                        }
                    }).next(new NetAction<Response<List<ResponseUploadImage>>>() {

                        @Override
                        protected void executeAdapt(Response<List<ResponseUploadImage>> listResponse, Request request) {
                            loading(String.format(Locale.getDefault(), "上传图像 %d/%d", ++mUploadIndex, thumbPhotos.size()));
                            if (!listResponse.success || listResponse.data == null || listResponse.data.isEmpty())
                                stopTask();
                        }
                    }).next(new Action<Response<List<ResponseUploadImage>>, String>() {

                        @Override
                        protected String execute(Response<List<ResponseUploadImage>> param) throws Throwable {
                            return param.data.get(0).fileName;
                        }
                    }).again(new Action<List<String>, String>() {

                        @Override
                        protected String execute(List<String> param) throws Throwable {
                            StringBuilder sb = new StringBuilder();
                            for (String s : param)
                                sb.append(s).append("#");
                            sb.deleteCharAt(sb.length() - 1);
                            return sb.toString();
                        }
                    }).next(new Action<String, Request>() {

                        @Override
                        protected Request execute(String param) throws Throwable {
                            Request request = Request.obtain(INear.POST_COMMENT_ADD);
                            request.put("customerId", UserManager.getInstance().getUser().id);
                            request.put("customerName", UserManager.getInstance().getUser().username);
                            request.put("customerPicture", UserManager.getInstance().getUser().picture);
                            request.put("levels", levels);
                            request.put("picString", param);
                            request.put("resourcesId", resourcesId);//资源id
                            request.put("summary",edtSummary.getText().toString());//评论内容
                            return request;
                        }
                    }).next(new NetAction<Response>() {

                        @Override
                        protected void executeAdapt(Response response, Request request) {
                            if (response.success) {
                                N.showShort(HomeDataXdp.this, "上传成功");
                                finish();
                                EventObserver.getInstance().sendEvent(HomeDataXdp.this,new RefreshEvent());
                            } else N.showShort(HomeDataXdp.this, "上传失败");
                        }
                    }, ThreadType.MAIN), new NoReturnAction<Throwable>() {
                @Override
                public void executeAdapt(Throwable param) {
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            N.showShort(HomeDataXdp.this, "上传失败");
                        }
                    });
                }
            }, new EmptyAction() {
                @Override
                protected void executeAdapt() {
                    dismissLoading();
                }
            });
        }else {
            Request request = Request.obtain(INear.POST_COMMENT_ADD);
            request.put("customerId", UserManager.getInstance().getUser().id);
            request.put("customerName", UserManager.getInstance().getUser().username);
            request.put("customerPicture", UserManager.getInstance().getUser().picture);
            request.put("levels", levels);
            request.put("resourcesId", resourcesId);//资源id
            request.put("summary",edtSummary.getText().toString());//评论内容
            request.setListener(new SimpleListener<Response>() {
                @Override
                public void onResponseListener(Request r, Response result) {
                    if (result.success) {
                        N.showShort(HomeDataXdp.this, "上传成功");
                        finish();
                        EventObserver.getInstance().sendEvent(HomeDataXdp.this,new RefreshEvent());
                    } else N.showShort(HomeDataXdp.this, "上传失败");
                }
            });
            net(request);
        }

    }
    @Bind(R.id.back)
    private void back(){
        finish();
    }
    @Bind(R.id.start1)
    private void setStart1() {
        Glide.with(HomeDataXdp.this).load(R.drawable.home_recyler_item_pingjia).into(start1);
        Glide.with(HomeDataXdp.this).load(R.drawable.home_recyler_item_pingjiatwo).into(start2);
        Glide.with(HomeDataXdp.this).load(R.drawable.home_recyler_item_pingjiatwo).into(start3);
        Glide.with(HomeDataXdp.this).load(R.drawable.home_recyler_item_pingjiatwo).into(start4);
        Glide.with(HomeDataXdp.this).load(R.drawable.home_recyler_item_pingjiatwo).into(start5);
        levels = 1;
    }

    @Bind(R.id.start2)
    private void setStart2() {
        Glide.with(HomeDataXdp.this).load(R.drawable.home_recyler_item_pingjia).into(start1);
        Glide.with(HomeDataXdp.this).load(R.drawable.home_recyler_item_pingjia).into(start2);
        Glide.with(HomeDataXdp.this).load(R.drawable.home_recyler_item_pingjiatwo).into(start3);
        Glide.with(HomeDataXdp.this).load(R.drawable.home_recyler_item_pingjiatwo).into(start4);
        Glide.with(HomeDataXdp.this).load(R.drawable.home_recyler_item_pingjiatwo).into(start5);
        levels = 2;
    }

    @Bind(R.id.start3)
    private void setStart3() {
        Glide.with(HomeDataXdp.this).load(R.drawable.home_recyler_item_pingjia).into(start1);
        Glide.with(HomeDataXdp.this).load(R.drawable.home_recyler_item_pingjia).into(start2);
        Glide.with(HomeDataXdp.this).load(R.drawable.home_recyler_item_pingjia).into(start3);
        Glide.with(HomeDataXdp.this).load(R.drawable.home_recyler_item_pingjiatwo).into(start4);
        Glide.with(HomeDataXdp.this).load(R.drawable.home_recyler_item_pingjiatwo).into(start5);
        levels = 3;
    }

    @Bind(R.id.start4)
    private void setStart4() {
        Glide.with(HomeDataXdp.this).load(R.drawable.home_recyler_item_pingjia).into(start1);
        Glide.with(HomeDataXdp.this).load(R.drawable.home_recyler_item_pingjia).into(start2);
        Glide.with(HomeDataXdp.this).load(R.drawable.home_recyler_item_pingjia).into(start3);
        Glide.with(HomeDataXdp.this).load(R.drawable.home_recyler_item_pingjia).into(start4);
        Glide.with(HomeDataXdp.this).load(R.drawable.home_recyler_item_pingjiatwo).into(start5);
        levels = 4;
    }

    @Bind(R.id.start5)
    private void setStart5() {
        Glide.with(HomeDataXdp.this).load(R.drawable.home_recyler_item_pingjia).into(start1);
        Glide.with(HomeDataXdp.this).load(R.drawable.home_recyler_item_pingjia).into(start2);
        Glide.with(HomeDataXdp.this).load(R.drawable.home_recyler_item_pingjia).into(start3);
        Glide.with(HomeDataXdp.this).load(R.drawable.home_recyler_item_pingjia).into(start4);
        Glide.with(HomeDataXdp.this).load(R.drawable.home_recyler_item_pingjia).into(start5);
        levels = 5;
    }

    @Override
    public void takeSuccess(TResult result) {
        super.takeSuccess(result);
        mAdapter.onTakeSuccess(result);
    }
}
