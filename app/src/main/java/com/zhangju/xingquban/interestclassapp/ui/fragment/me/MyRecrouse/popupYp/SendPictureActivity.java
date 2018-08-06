package com.zhangju.xingquban.interestclassapp.ui.fragment.me.MyRecrouse.popupYp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.alibaba.fastjson.JSONObject;
import com.fastlib.app.EventObserver;
import com.imnjh.imagepicker.activity.PhotoPickerActivity;
import com.jph.takephoto.model.TImage;
import com.jph.takephoto.model.TResult;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.RequestParams;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest;
import com.zhangju.xingquban.R;
import com.zhangju.xingquban.interestclassapp.RetrofitInterface.NetWork;
import com.zhangju.xingquban.interestclassapp.adapter.RectangleAdapter;
import com.zhangju.xingquban.interestclassapp.base.TakePhotoBaseActivity;
import com.zhangju.xingquban.interestclassapp.bean.ResouceUpBean;
import com.zhangju.xingquban.interestclassapp.bean.UploadResponse;
import com.zhangju.xingquban.interestclassapp.hplper.ScrollGridManager;
import com.zhangju.xingquban.interestclassapp.refactor.common.bean.EventRefresh;
import com.zhangju.xingquban.interestclassapp.refactor.me.fragment.ResourceManageFragment;
import com.zhangju.xingquban.interestclassapp.refactor.user.UserManager;
import com.zhangju.xingquban.interestclassapp.ui.fragment.me.MyRecrouse.ChargeActivity;
import com.zhangju.xingquban.interestclassapp.ui.fragment.me.MyRecrouse.LocationActive;
import com.zhangju.xingquban.interestclassapp.ui.widget.PublicHead;
import com.zhangju.xingquban.interestclassapp.util.LoadingDialog;
import com.zhangju.xingquban.interestclassapp.util.ToastUtil;
import com.zhangju.xingquban.interestclassapp.util.UrlUtils;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import rx.Observer;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

//发表图片
public class SendPictureActivity
        extends TakePhotoBaseActivity {
    @BindView(R.id.me_recrouse_popw_ypHead)
    PublicHead   meRecrousePopwYpHead;
    @BindView(R.id.line_video)
    LinearLayout lineVideo;
    @BindView(R.id.image_vedio_cover)
    ImageView    imageVedioCover;
    @BindView(R.id.tv_resource_title)
    EditText     tvResourceTitle;
    @BindView(R.id.tv_resource_introduce)
    EditText     tvResourceIntroduce;
    @BindView(R.id.me_recrouse_popw_ypsc)
    LinearLayout meRecrousePopwYpsc;
    @BindView(R.id.tv_resource_charge)
    TextView     tvResourceCharge;
    @BindView(R.id.my_lesson)
    LinearLayout myLesson;
    @BindView(R.id.tv_resource_address)
    TextView     tvResourceAddress;
    @BindView(R.id.me_recrouse_popw_tp)
    LinearLayout meRecrousePopwTp;
    @BindView(R.id.recycler_pic)
    RecyclerView recycler_pic;
    private List<String> mlist = new ArrayList<>();

    private RectangleAdapter adapter;
    private View             viewFoot;
    private List<File> fileList = new ArrayList<>();

    private String title;
    private String introduce;
    private int cost = 0;
    StringBuilder pictureBuilder = new StringBuilder();//图片接收
    private String address;
    private String lat;
    private String lng;

    private LoadingDialog loadingDialog;
    List<String> pictureList = new ArrayList<>();//图片上传返回集合
    private Subscription subscription;

    @Override
    public int getLayout() {
        return R.layout.activity_me_recrouse_popu_yp;
    }

    @Override
    public void initView() {
        loadingDialog = new LoadingDialog(mContext);
        loadingDialog.setLoading("发布中...");

        setShowState();
        setMeRecrousePopwYpHead();
        intiViewFoot();
        setPicAdapter();
    }

    private void intiViewFoot() {
        viewFoot = LayoutInflater.from(SendPictureActivity.this).inflate(R.layout.picture_item, null);
        viewFoot.findViewById(R.id.pic_delete).setVisibility(View.GONE);
        viewFoot.findViewById(R.id.photo_image).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (fileList.size() >= 9) {
                    ToastUtil.showToast("最多选9张图片");
                } else {
                    getTakePhoto().onPickMultiple(9 - mlist.size());
                }
            }
        });

    }


    private void setPicAdapter() {
        adapter = new RectangleAdapter(SendPictureActivity.this, mlist, 2, viewFoot);
        ScrollGridManager scrollGridManager = new ScrollGridManager(SendPictureActivity.this, 4);
        scrollGridManager.setScrollEnabled(false);
        recycler_pic.setLayoutManager(scrollGridManager);
        recycler_pic.setAdapter(adapter);
        adapter.setGetPicList(new RectangleAdapter.getPicList() {
            @Override
            public void getPicList(List<String> mlist) {
                File file;
                fileList.clear();
                for (int i = 0; i < mlist.size(); i++) {
                    file = new File(mlist.get(i));
                    fileList.add(file);
                }
            }
        });

    }

    private void setMeRecrousePopwYpHead() {
        meRecrousePopwYpHead.setTitle("发表图片");
        meRecrousePopwYpHead.setShow(true, false, true);
        meRecrousePopwYpHead.setRightTitle("发布");
        meRecrousePopwYpHead.setRightTextColor(R.color.color_main);
        meRecrousePopwYpHead.setBackClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        meRecrousePopwYpHead.setRightClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                title = tvResourceTitle.getText().toString().trim();
                introduce = tvResourceIntroduce.getText().toString().trim();
                String trim = tvResourceCharge.getText().toString().trim();
                address = tvResourceAddress.getText().toString().trim();
                if (title.isEmpty()) {
                    ToastUtil.showToast("请输入标题");
                } else if (introduce.isEmpty()) {
                    ToastUtil.showToast("请输入介绍");
                } else {
                    upLoadPic();
                }

            }
        });
    }

    //判断展示状态
    private void setShowState() {
        lineVideo.setVisibility(View.GONE);
    }

    @Override
    public void initData() {

    }

    @Override
    public void initListener() {

    }

    @Override
    public void onClick(View v) {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.my_lesson, R.id.me_recrouse_popw_tp})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            //收费标准
            case R.id.my_lesson:
                Intent intent = new Intent(mContext, ChargeActivity.class);
                intent.putExtra(ChargeActivity.ARG_RES_INT_PRICE, cost);
                startActivityForResult(intent, 11);
                break;
            //地点选择
            case R.id.me_recrouse_popw_tp:
                Intent intent2 = new Intent(SendPictureActivity.this, LocationActive.class);
                startActivityForResult(intent2, 1);
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode != RESULT_OK) {
            return;
        }
        //图片回调
        if (requestCode == 101) {
            List<String> piclist = data.getStringArrayListExtra(PhotoPickerActivity.EXTRA_RESULT_SELECTION);
            mlist.addAll(piclist);
            adapter.notifyDataSetChanged();
        } else if (requestCode == 11) { //收费回调
            cost = data.getIntExtra(ChargeActivity.ARG_RES_INT_PRICE, 0);
            if (cost <= 0)
                tvResourceCharge.setText("免费");
            else
                tvResourceCharge.setText(String.format(Locale.getDefault(), "%d￥", cost));
        } else if (requestCode == 1) { //地理位置回调
            lat = data.getStringExtra("lat");
            lng = data.getStringExtra("lng");
            address = data.getStringExtra("address");
            tvResourceAddress.setText(address);
        }
    }

    //图片发布
    private void upLoadPic() {
        try {
            loadingDialog.show();
            if (fileList.size() > 0) {
                for (int i = 0; i < fileList.size(); i++) {
                    upload(fileList.get(i));
                }
            } else {
                goRequest();
            }

        } catch (Exception e) {
            ToastUtil.showToast(e.toString());
        }

    }

    //图片资源上传
    private void upload(File file) {
        HttpUtils httpUtils = new HttpUtils();
        RequestParams params = new RequestParams();
        params.addBodyParameter("files", file);
        params.addHeader("X-CustomToken", UserManager.getInstance().getToken());
        httpUtils.send(HttpRequest.HttpMethod.POST,
                UrlUtils.URL_UPLOAD,
                params,
                new RequestCallBack<String>() {

                    @Override
                    public void onFailure(HttpException arg0,
                                          String arg1) {
                        ToastUtil.showToast("图片上传失败");
                    }

                    @Override
                    public void onStart() {
                        super.onStart();

                    }

                    @Override
                    public void onLoading(long total, long current,
                                          boolean isUploading) {
                        super.onLoading(total, current, isUploading);
                    }

                    @Override
                    public void onSuccess(ResponseInfo<String> arg0) {
                        String data = arg0.result;
                        UploadResponse bean = JSONObject.parseObject(data, UploadResponse.class);
                        String picurl = bean.getAaData().get(0).getFileName();
                        pictureList.add(picurl);
                        if (pictureList.size() == fileList.size()) {
                            goRequest();
                        }

                    }
                });
    }

    private void goRequest() {
        for (int i = 0; i < pictureList.size(); i++) {//图片集合
            if (i == pictureList.size() - 1) {
                pictureBuilder.append(pictureList.get(i));
            } else {
                pictureBuilder.append(pictureList.get(i) + "#");
            }
        }
        //        Request request=new Request("/myResources/add.json");
        //        request.put("title",title);
        //        request.put("customerId", UserManager.getInstance().getUser().id);
        //        request.put("types","picture");
        //        request.put("price",cost);
        //        request.put("isCharge",cost<=0?0:1);
        //        request.put("muitlUploadPicture",pictureBuilder.toString());
        //        request.put("summary",introduce);
        //        request.put("address",address);
        //        request.put("latitude",lat);
        //        request.put("longitude",lng);
        //        request.setListener(new SimpleListener<Response>(){
        //
        //            @Override
        //            public void onResponseListener(Request r, Response result) {
        //                if(result.success){
        //                    N.showShort(SendPictureActivity.this,"发表成功");
        //                    finish();
        //                }
        //                loadingDialog.dismiss();
        //            }
        //
        //            @Override
        //            public void onErrorListener(Request r, String error) {
        //                super.onErrorListener(r, error);
        //                loadingDialog.dismiss();
        //            }
        //        });
        //        request.start();
        subscription = NetWork.getMe().uploadresourceManager(title, UserManager.getInstance().getUser().id, "picture", null,
                cost, cost <= 0 ? 0 : 1, pictureBuilder.toString(), null, introduce, address, lat, lng)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer1);
    }

    Observer<ResouceUpBean> observer1 = new Observer<ResouceUpBean>() {

        @Override
        public void onCompleted() {
            loadingDialog.dismiss();
        }

        @Override
        public void onError(Throwable e) {
            ToastUtil.showToast(e.toString());
            loadingDialog.dismiss();
        }

        @Override
        public void onNext(ResouceUpBean object) {
            boolean success = object.isSuccess();
            if (success) {
                ToastUtil.showToast("发布成功");
                EventObserver.getInstance().sendEvent(SendPictureActivity.this, new EventRefresh(ResourceManageFragment.class));
                finish();
            } else {
                ToastUtil.showToast("发布失败");
            }
            pictureList.clear();
            loadingDialog.dismiss();
        }
    };

    @Override
    public void takeSuccess(TResult result) {
        super.takeSuccess(result);
        for (TImage tImage : result.getImages()) {
            mlist.add(tImage.getOriginalPath());
        }
        adapter.notifyDataSetChanged();
    }
}
