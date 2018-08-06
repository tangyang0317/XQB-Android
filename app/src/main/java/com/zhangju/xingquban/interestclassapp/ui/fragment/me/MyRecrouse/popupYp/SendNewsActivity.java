package com.zhangju.xingquban.interestclassapp.ui.fragment.me.MyRecrouse.popupYp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.alibaba.fastjson.JSONObject;
import com.fastlib.app.EventObserver;
import com.imnjh.imagepicker.SImagePicker;
import com.imnjh.imagepicker.activity.PhotoPickerActivity;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.RequestParams;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest;
import com.zhangju.xingquban.R;
import com.zhangju.xingquban.interestclassapp.RetrofitInterface.NetWork;
import com.zhangju.xingquban.interestclassapp.adapter.RectangleAdapter;
import com.zhangju.xingquban.interestclassapp.base.BaseActivity;
import com.zhangju.xingquban.interestclassapp.bean.ResouceUpBean;
import com.zhangju.xingquban.interestclassapp.bean.UploadResponse;
import com.zhangju.xingquban.interestclassapp.hplper.ScrollGridManager;
import com.zhangju.xingquban.interestclassapp.refactor.common.bean.EventRefresh;
import com.zhangju.xingquban.interestclassapp.refactor.me.fragment.ResourceManageFragment;
import com.zhangju.xingquban.interestclassapp.refactor.user.UserManager;
import com.zhangju.xingquban.interestclassapp.ui.fragment.me.MyRecrouse.LocationActive;
import com.zhangju.xingquban.interestclassapp.ui.widget.PublicHead;
import com.zhangju.xingquban.interestclassapp.util.LoadingDialog;
import com.zhangju.xingquban.interestclassapp.util.ToastUtil;
import com.zhangju.xingquban.interestclassapp.util.UrlUtils;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import rx.Observer;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

//发送资讯
public class SendNewsActivity extends BaseActivity {

    @BindView(R.id.me_recrouse_popw_ypHead)
    PublicHead meRecrousePopwYpHead;
    @BindView(R.id.edit_title)
    EditText editTitle;
    @BindView(R.id.recycler_pic)
    RecyclerView recyclerPic;
    @BindView(R.id.tv_resource_address)
    TextView tvResourceAddress;
    @BindView(R.id.me_recrouse_popw_tp)
    LinearLayout meRecrousePopwTp;
    @BindView(R.id.edit_introduce)
    EditText editIntroduce;
    private RectangleAdapter adapter;
    private View viewFoot;

    private Subscription subscription;

    StringBuilder pictureBuilder = new StringBuilder();//图片接收
    List<String> pictureList = new ArrayList<>();//图片上传返回集合
    private List<String> mPicList = new ArrayList<>();//图片集合展示
    private List<File> fileList = new ArrayList<>();
    private String newsTitle;
    private String newsIntroduce;
    private String newsAddress;
    private String lat;
    private String lng;
    private LoadingDialog loadingDialog;

    @Override
    public int getLayout() {
        return R.layout.activity_send_news;
    }

    @Override
    public void initView() {
        loadingDialog = new LoadingDialog(mContext);
        loadingDialog.setLoading("发布中...");


        setMeRecrousePopwYpHead();
        intiViewFoot();
        setNewsAdapter();

    }

    private void setNewsAdapter() {

        adapter = new RectangleAdapter(mContext, mPicList, 2, viewFoot);
        ScrollGridManager scrollGridManager = new ScrollGridManager(SendNewsActivity.this, 4);
        scrollGridManager.setScrollEnabled(false);
        recyclerPic.setLayoutManager(scrollGridManager);
        recyclerPic.setAdapter(adapter);
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

    private void intiViewFoot() {
        viewFoot = LayoutInflater.from(SendNewsActivity.this).inflate(R.layout.picture_item, null);
        viewFoot.findViewById(R.id.pic_delete).setVisibility(View.GONE);
//        viewFoot.findViewById(R.id.photo_image).setBackgroundResource(R.mipmap.me_recrouse_ypbg);
        viewFoot.findViewById(R.id.photo_image).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (fileList.size() == 9) {
                    ToastUtil.showToast("最多选9张图片");
                } else {
                    SImagePicker
                            .from(SendNewsActivity.this)
                            .maxCount(fileList == null ? 9 : 9 - fileList.size())
                            .rowCount(3)
                            .showCamera(true)
                            .pickMode(SImagePicker.MODE_IMAGE)
                            .forResult(101);
                }
            }
        });
    }

    public void setMeRecrousePopwYpHead() {
        meRecrousePopwYpHead.setTitle("发表资讯");
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
                newsTitle = editTitle.getText().toString().trim();
                newsIntroduce = editIntroduce.getText().toString().trim();
                newsAddress = tvResourceAddress.getText().toString().trim();
                if (newsTitle.isEmpty()) {
                    ToastUtil.showToast("请输入资讯标题");
                } else if (newsIntroduce.isEmpty()) {
                    ToastUtil.showToast("请输入资讯介绍");
                }else {
                    upLoadData();
                }

            }
        });
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
                EventObserver.getInstance().sendEvent(SendNewsActivity.this,new EventRefresh(ResourceManageFragment.class));
                finish();
            } else {

                ToastUtil.showToast("发布失败");
            }
            pictureList.clear();
            loadingDialog.dismiss();


        }
    };

    private void goRequest() {
        for (int i = 0; i < pictureList.size(); i++) {//图片集合
            if (i == pictureList.size() - 1) {
                pictureBuilder.append(pictureList.get(i));
            } else {
                pictureBuilder.append(pictureList.get(i) + "#");
            }
        }
        subscription = NetWork.getMe().uploadresourceManager(newsTitle, UserManager.getInstance().getUser().id, "article",null,0, 0, pictureBuilder.toString(),null, newsIntroduce,   newsAddress, lat, lng)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer1);
    }

    //发布资讯数据
    private void upLoadData() {
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

    @OnClick({R.id.me_recrouse_popw_tp})
    public void onViewClicked(View view) {
        switch (view.getId()) {

            case R.id.me_recrouse_popw_tp:
                Intent intent = new Intent(SendNewsActivity.this, LocationActive.class);
                startActivityForResult(intent, 1);
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode!=RESULT_OK) return;
        //图片回调
        if (requestCode == 101) {
            List<String> piclist = data.getStringArrayListExtra(PhotoPickerActivity.EXTRA_RESULT_SELECTION);
            mPicList.addAll(piclist);
            adapter.notifyDataSetChanged();
        }else if(requestCode==1){  //地理位置回调
            lat = data.getStringExtra("lat");
            lng = data.getStringExtra("lng");
            newsAddress = data.getStringExtra("address");
            tvResourceAddress.setText(newsAddress);
        }
    }
}
