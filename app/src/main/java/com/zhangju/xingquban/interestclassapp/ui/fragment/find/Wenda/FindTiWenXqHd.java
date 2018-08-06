package com.zhangju.xingquban.interestclassapp.ui.fragment.find.Wenda;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import com.alibaba.fastjson.JSONObject;
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
import com.zhangju.xingquban.interestclassapp.base.BaseActivity;
import com.zhangju.xingquban.interestclassapp.bean.UploadResponse;
import com.zhangju.xingquban.interestclassapp.refactor.user.UserManager;
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
//我的问答---回答

public class FindTiWenXqHd extends BaseActivity {


    @BindView(R.id.find_tiwen_xq_text)
    TextView findTiwenXqText;

    @BindView(R.id.tv_question_cancel)
    TextView tv_question_cancel;

    @BindView(R.id.tv_question_ok)
    TextView tv_question_ok;

    @BindView(R.id.find_tiwen_xq_edit)
    EditText findTiwenXqEdit;
    @BindView(R.id.find_tiwen_xq_image)
    ImageView findTiwenXqImage;

    @BindView(R.id.find_wenda_girdview)
    GridView find_wenda_girdview;

    private String id;

    private Subscription subscription;
    private CanDeleteImageAdapter photoAdapter;
    private List<File> fileList = new ArrayList<>();

    List<String> pictureList = new ArrayList<>();//图片上传返回集合
    StringBuilder pictureBuilder = new StringBuilder();//图片接收
    private LoadingDialog loadingDialog;


    Observer<FindTiWenXqHdBean> observer1 = new Observer<FindTiWenXqHdBean>() {

        @Override
        public void onCompleted() {

        }

        @Override
        public void onError(Throwable e) {
          loadingDialog.dismiss();
        }

        @Override
        public void onNext(FindTiWenXqHdBean findTiWenXqHdBean) {
            boolean success = findTiWenXqHdBean.isSuccess();
            if (success){

                finish();
            }
            loadingDialog.dismiss();

        }
    };

    @Override
    public int getLayout() {
        return R.layout.activity_find_ti_wen_xq_hd;
    }

    @Override
    public void initView() {
        loadingDialog=new LoadingDialog(FindTiWenXqHd.this);
        loadingDialog.setLoading("提交中...");

        photoAdapter = new CanDeleteImageAdapter(this);
        find_wenda_girdview.setAdapter(photoAdapter);
        photoAdapter.setShowPicutreList(new CanDeleteImageAdapter.showPicutreList() {
            @Override
            public void getPictureList(List<String> list) {
                File file;
                fileList.clear();
                for (int i = 0; i < list.size(); i++) {
                    file = new File(list.get(i));
                    fileList.add(file);
                }
            }
        });
    }

    @Override
    public void initData() {
        Intent intent = getIntent();
        String title = intent.getStringExtra("title");
        id = intent.getStringExtra("id");
        findTiwenXqText.setText(title);
    }

    @Override
    public void initListener() {

    }

    @Override
    public void onClick(View v) {

    }

    public void setFindTiwenXqData() {
        String str = findTiwenXqEdit.getText().toString().trim();
        String username = UserManager.getInstance().getUser().username;
        String picture = UserManager.getInstance().getUser().picture;
        String customerId = UserManager.getInstance().getUser().id;

        for (int i = 0; i < pictureList.size(); i++) {//图片集合
            if (i == pictureList.size() - 1) {
                pictureBuilder.append(pictureList.get(i));
            } else {
                pictureBuilder.append(pictureList.get(i) + "#");
            }
        }

        subscription = NetWork.getMeTiwen().myAnswer(username, picture, str, id, customerId, pictureBuilder.toString(), 1)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer1);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
    }


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
                            setFindTiwenXqData();
                        }

                    }
                });
    }

    @OnClick({R.id.tv_question_cancel, R.id.tv_question_ok, R.id.find_tiwen_xq_image})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_question_cancel:
                finish();
                break;
            case R.id.tv_question_ok:
                String s = findTiwenXqEdit.getText().toString().trim();
                if (s.isEmpty()) {
                    ToastUtil.showToast("回答不能为空");
                } else {
                    loadingDialog.show();
                    try {
                        if (fileList.size() > 0) {
                            for (int i = 0; i < fileList.size(); i++) {
                                upload(fileList.get(i));
                            }
                        } else {
                            setFindTiwenXqData();
                        }

                    } catch (Exception e) {
                        ToastUtil.showToast(e.toString());
                    }


                }
                break;
            case R.id.find_tiwen_xq_image:
                if (fileList.size() == 9) {
                    ToastUtil.showToast("最多选9张图片");
                } else {
                    SImagePicker
                            .from(this)
                            .maxCount(fileList == null ? 9 : 9 - fileList.size())
                            .rowCount(3)
                            .showCamera(true)
                            .pickMode(SImagePicker.MODE_IMAGE)
                            .forResult(102);
                }
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            if (requestCode == 102) {
                List<String> piclist = data.getStringArrayListExtra(PhotoPickerActivity.EXTRA_RESULT_SELECTION);
                photoAdapter.addDataPhoto(piclist);

            }
        }
    }
}
