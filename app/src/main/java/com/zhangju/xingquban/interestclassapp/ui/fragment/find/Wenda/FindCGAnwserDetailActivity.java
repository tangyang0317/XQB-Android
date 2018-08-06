package com.zhangju.xingquban.interestclassapp.ui.fragment.find.Wenda;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.alibaba.fastjson.JSONObject;
import com.imnjh.imagepicker.SImagePicker;
import com.imnjh.imagepicker.activity.PhotoPickerActivity;
import com.zhangju.xingquban.R;
import com.zhangju.xingquban.interestclassapp.RetrofitInterface.NetWork;
import com.zhangju.xingquban.interestclassapp.base.BaseActivity;
import com.zhangju.xingquban.interestclassapp.bean.UploadResponse;
import com.zhangju.xingquban.interestclassapp.refactor.user.UserManager;
import com.zhangju.xingquban.interestclassapp.ui.fragment.me.Settings.MyDialog;
import com.zhangju.xingquban.interestclassapp.util.LoadingDialog;
import com.zhangju.xingquban.interestclassapp.util.ToastUtil;
import com.zhangju.xingquban.interestclassapp.util.UrlUtils;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.RequestParams;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest;

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

//我的草稿回答详情
public class FindCGAnwserDetailActivity extends BaseActivity {


    @BindView(R.id.tv_question_cancel)
    TextView tvQuestionCancel;
    @BindView(R.id.tv_tiwen)
    TextView tvTiwen;
    @BindView(R.id.tv_question_ok)
    TextView tvQuestionOk;
    @BindView(R.id.top_wenda)
    LinearLayout topWenda;
    @BindView(R.id.find_tiwen_xq_text)
    TextView findTiwenXqText;
    @BindView(R.id.find_tiwen_xq_edit)
    EditText findTiwenXqEdit;
    @BindView(R.id.find_wenda_girdview)
    GridView findWendaGirdview;
    @BindView(R.id.find_tiwen_xq_image)
    ImageView findTiwenXqImage;

    private CanDeleteImageAdapter photoAdapter;

    private String questionId;//问题id
    private Subscription subscription;
    private List<File> fileList = new ArrayList<>();

    List<String> pictureList = new ArrayList<>();//图片上传返回集合
    StringBuilder pictureBuilder = new StringBuilder();//图片接收


    private LoadingDialog loadingDialog;
    int type = 1;
    private MyDialog ab;
    private View view;


    Observer<FindTiWenXqHdBean> observer2 = new Observer<FindTiWenXqHdBean>() {

        @Override
        public void onCompleted() {

        }

        @Override
        public void onError(Throwable e) {
            loadingDialog.dismiss();
            ToastUtil.showToast(e.toString());
        }

        @Override
        public void onNext(FindTiWenXqHdBean object) {
            loadingDialog.dismiss();
            boolean success = object.isSuccess();
            if (type == 1) {
                if (success) {
                    startActivity(new Intent(FindCGAnwserDetailActivity.this, FindWenda.class));
                    finish();
                }
            } else {
                if (success) {
                    ToastUtil.showToast("发布成功");
                    finish();
                }
            }
        }
    };


    @Override
    public int getLayout() {
        return R.layout.activity_find_ti_wen_xq_hd;
    }

    @Override
    public void initView() {
        loadingDialog = new LoadingDialog(FindCGAnwserDetailActivity.this);
        loadingDialog.setLoading("提交中...");

        tvTiwen.setText("我的草稿");
        photoAdapter = new CanDeleteImageAdapter(this);
        findWendaGirdview.setAdapter(photoAdapter);
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
        questionId = getIntent().getStringExtra("id");
        String title = getIntent().getStringExtra("title");
        String explain = getIntent().getStringExtra("explain");
        ArrayList<String> picList = getIntent().getStringArrayListExtra("picList");

        findTiwenXqText.setText(title);
        findTiwenXqEdit.setText(explain);
        photoAdapter.addDataPhoto(picList);

        WdReturn();
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

    @OnClick({R.id.tv_question_cancel, R.id.tv_question_ok, R.id.find_tiwen_xq_image})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_question_cancel:
                type = 2;
                if (!findTiwenXqEdit.getText().toString().trim().isEmpty()) {
                    ab.show();
                } else {
                    finish();
                }
                break;
            case R.id.tv_question_ok:
                loadingDialog.show();
                type = 1;
                if (!findTiwenXqEdit.getText().toString().trim().isEmpty()) {
                   getTiWen();
                } else {
                    ToastUtil.showToast("答案不能为空");
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
                            .forResult(104);
                }
                break;
        }
    }

    //返回按钮弹出  是否要保存草稿
    public void WdReturn() {
        type = 2;
        view = getLayoutInflater().inflate(R.layout.find_tw_view_dialog, null);
        ab = new MyDialog(FindCGAnwserDetailActivity.this, view, R.style.dialog);
        view.findViewById(R.id.find_button1).setOnClickListener(new View.OnClickListener() {//点击否的时候取消dialog
            @Override
            public void onClick(View v) {
                ab.cancel();
            }
        });

        view.findViewById(R.id.find_button2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadingDialog.setLoading("保存中...");
                loadingDialog.show();
                getTiWen();
            }
        });
    }

    //提交或保存
    private void getTiWen() {
        try {
            if (fileList.size() > 0) {
                for (int i = 0; i < fileList.size(); i++) {
                    String file = fileList.get(i).toString();
                    if (file.contains("http")) {
                        pictureList.add(file);
                    } else {
                        upload(fileList.get(i));
                    }

                }
                if (pictureList.size() == fileList.size()) {
                    goRequest();
                }

            } else {
                goRequest();
            }

        } catch (Exception e) {
            ToastUtil.showToast(e.toString());
        }


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
                            goRequest();
                        }

                    }
                });
    }

    private void goRequest() {
        String title = findTiwenXqEdit.getText().toString().trim();//标题
        String username = UserManager.getInstance().getUser().username;
        String picture = UserManager.getInstance().getUser().picture;
        String customerId =UserManager.getInstance().getUser().id;


        for (int i = 0; i < pictureList.size(); i++) {//图片集合
            if (i == pictureList.size() - 1) {
                pictureBuilder.append(pictureList.get(i));
            } else {
                pictureBuilder.append(pictureList.get(i) + "#");
            }
        }

        subscription = NetWork.getMeTiwen().myAnswer(username, picture, title, questionId, customerId, pictureBuilder.toString(), type)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer2);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            if (requestCode == 104) {
                List<String> piclist = data.getStringArrayListExtra(PhotoPickerActivity.EXTRA_RESULT_SELECTION);
                photoAdapter.addDataPhoto(piclist);

            }
        }
    }
}
