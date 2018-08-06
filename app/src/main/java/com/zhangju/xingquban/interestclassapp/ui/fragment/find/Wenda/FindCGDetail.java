package com.zhangju.xingquban.interestclassapp.ui.fragment.find.Wenda;


import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.alibaba.fastjson.JSONObject;
import com.imnjh.imagepicker.SImagePicker;
import com.imnjh.imagepicker.activity.PhotoPickerActivity;
import com.zhangju.xingquban.R;
import com.zhangju.xingquban.interestclassapp.RetrofitInterface.NetWork;
import com.zhangju.xingquban.interestclassapp.adapter.baseadpter.OnListItemClickListener;
import com.zhangju.xingquban.interestclassapp.base.BaseActivity;
import com.zhangju.xingquban.interestclassapp.bean.UploadResponse;
import com.zhangju.xingquban.interestclassapp.bean.find.FindQuestionBean;
import com.zhangju.xingquban.interestclassapp.hplper.MyGridView;
import com.zhangju.xingquban.interestclassapp.refactor.user.UserManager;
import com.zhangju.xingquban.interestclassapp.ui.fragment.find.Wenda.wdcgtiwen.FindWdLabelAdapter;
import com.zhangju.xingquban.interestclassapp.ui.fragment.me.Settings.MyDialog;
import com.zhangju.xingquban.interestclassapp.ui.widget.PublicHead;
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
import java.util.Arrays;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import rx.Observer;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by ydw on 2017/10/16.
 */
//我的提问草稿详情
public class FindCGDetail extends BaseActivity {

    @BindView(R.id.find_tab_tiwen)
    PublicHead find_tab_tiwen;
    @BindView(R.id.tv_question_cancel)
    TextView tvQuestionCancel;
    @BindView(R.id.tv_question_ok)
    TextView tvQuestionOk;
    @BindView(R.id.find_tab_text)
    EditText find_tab_text;
    @BindView(R.id.find_tab_textNumber)
    TextView findTabTextNumber;
    @BindView(R.id.find_tab_text_xt)
    EditText findTabTextXt;
    @BindView(R.id.find_wenda_photo)
    ImageView findWendaPhoto;
    @BindView(R.id.find_wenda_lable)
    ImageView findWendaLable;
    @BindView(R.id.find_wenda_girdview)
    MyGridView findWendaGirdview;
    @BindView(R.id.top_wenda)
    LinearLayout topWenda;
    @BindView(R.id.tv_tiwen)
    TextView tvTiwen;


    private Subscription subscription;
    private String id;

    private CanDeleteImageAdapter photoAdapter;
    FindWdLabelAdapter findWdLabelAdapter;

    private View view, view1;
    private MyDialog ab, ab1;
    private CharSequence temp;


    private List<File> fileList = new ArrayList<>();
    List<String> list = Arrays.asList("手绘", "美术", "健身", "舞蹈", "棋类", "户外", "球类", "乐器", "亲子");

    List<String> pictureList = new ArrayList<>();//图片上传返回集合
    StringBuilder pictureBuilder = new StringBuilder();//图片接收

    private String labelText = "";//用户选择的标签
    private LoadingDialog loadingDialog;
    int type = 1;


    //获取草稿信息
    Observer<FindMeWendaXqBean> observer1 = new Observer<FindMeWendaXqBean>() {

        @Override
        public void onCompleted() {

        }

        @Override
        public void onError(Throwable e) {
            ToastUtil.showToast(e.toString());
        }

        @Override
        public void onNext(FindMeWendaXqBean object) {
            boolean success = object.isSuccess();
            if (success && object.getAaData().size() > 0) {
                String title = object.getAaData().get(0).getTitle() == null ? "" : object.getAaData().get(0).getTitle();//标题
                String qExplain = object.getAaData().get(0).getQExplain() == null ? "" : object.getAaData().get(0).getQExplain();//补充
                List<FindMeWendaXqBean.AaDataBean.PicStrBean> picStr = object.getAaData().get(0).getPicStr();

                List<String> mlistPicture = new ArrayList<>();
                mlistPicture.clear();

                for (FindMeWendaXqBean.AaDataBean.PicStrBean picStrBean : picStr) {
                    String picture = picStrBean.getPicture();
                    mlistPicture.add(picture);
                }
                File file;
                fileList.clear();
                for (int i = 0; i < mlistPicture.size(); i++) {
                    file = new File(mlistPicture.get(i));
                    fileList.add(file);
                }
                find_tab_text.setText(title);
                findTabTextXt.setText(qExplain);
                photoAdapter.addDataPhoto(mlistPicture);

            }
        }
    };


    Observer<FindQuestionBean> observer2 = new Observer<FindQuestionBean>() {

        @Override
        public void onCompleted() {

        }

        @Override
        public void onError(Throwable e) {
            ToastUtil.showToast(e.toString());
        }

        @Override
        public void onNext(FindQuestionBean object) {

            boolean success = object.isSuccess();
            if (type == 1) {
                if (success) {
                    startActivity(new Intent(FindCGDetail.this, FindWenda.class));
                    finish();
                }
            } else {
                if (success) {
                    loadingDialog.dismiss();
                    ToastUtil.showToast("发布成功");
                    finish();
                }
            }
        }
    };


    @Override
    public int getLayout() {
        return R.layout.activity_find_tab_ti_wen;
    }

    @Override
    public void initView() {
        find_tab_tiwen.setVisibility(View.GONE);
        topWenda.setVisibility(View.VISIBLE);
        tvQuestionOk.setText("发布");
        tvTiwen.setText("提问");

        loadingDialog = new LoadingDialog(FindCGDetail.this);
        loadingDialog.setLoading("发布中...");

        find_tab_text.addTextChangedListener(mTextWatcher);
        WdReturn();
        showLabelList();
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
        id = getIntent().getStringExtra("id");
        getDraftinfo();//获取草稿详情

    }

    private void getDraftinfo() {
        subscription = NetWork.getMeTiwen().getDraftinfo(UserManager.getInstance().getUser().id, 2, id).
                subscribeOn(Schedulers.io()).
                observeOn(AndroidSchedulers.mainThread()).
                subscribe(observer1);

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

    @OnClick({R.id.tv_question_cancel, R.id.tv_question_ok, R.id.find_wenda_photo, R.id.find_wenda_lable})
    public void onViewClicked(View view) {
        switch (view.getId()) {

            case R.id.tv_question_cancel:
                type=2;
                if (find_tab_text.length() > 0) {
                    ab.show();
                } else if (findTabTextXt.length() > 0) {
                    ab.show();
                } else {
                    finish();
                }
                break;
            //发布
            case R.id.tv_question_ok:
                type=1;
                if (!find_tab_text.getText().toString().isEmpty()) {
                    loadingDialog.show();
                    getTiWen();
                } else {
                    ToastUtil.showToast("请输入发布内容");
                }
                break;
            // 图片获取
            case R.id.find_wenda_photo:
                if (fileList.size() == 9) {
                    ToastUtil.showToast("最多选9张图片");
                } else {
                    SImagePicker
                            .from(this)
                            .maxCount(fileList == null ? 9 : 9 - fileList.size())
                            .rowCount(3)
                            .showCamera(true)
                            .pickMode(SImagePicker.MODE_IMAGE)
                            .forResult(103);
                }
                break;
            //标签
            case R.id.find_wenda_lable:
                ab1.show();
                break;
        }
    }

    //发布
    private void getTiWen() {
        try {
            if (fileList.size() > 0) {
                for (int i = 0; i < fileList.size(); i++) {
                    String file = fileList.get(i).toString();
                    if (file.contains("http")){
                        pictureList.add(file);
                    }else {
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
        String title = find_tab_text.getText().toString().trim();//标题
        String addContent = findTabTextXt.getText().toString().trim();//补充说明
        for (int i = 0; i < pictureList.size(); i++) {//图片集合
            if (i == pictureList.size() - 1) {
                pictureBuilder.append(pictureList.get(i));
            } else {
                pictureBuilder.append(pictureList.get(i) + "#");
            }
        }

        subscription = NetWork.getMeTiwen().getMeNewTiwen(UserManager.getInstance().getUser().id, title, addContent, pictureBuilder.toString(), type, labelText)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer2);
    }

    //判断EditText输入多少字
    TextWatcher mTextWatcher = new TextWatcher() {

        private int editStart;
        private int editEnd;

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            temp = s;
        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            findTabTextNumber.setText(s);
        }

        @Override
        public void afterTextChanged(Editable s) {
            editStart = find_tab_text.getSelectionStart();
            editEnd = find_tab_text.getSelectionEnd();
            findTabTextNumber.setText(temp.length() + "");
            if (temp.length() > 40) {
                ToastUtil.showToast("你输入的字数已经超过了限制！");
                s.delete(editStart - 1, editEnd);
                int tempSelection = editStart;
                find_tab_text.removeTextChangedListener(this);
                find_tab_text.setText(s);
                find_tab_text.addTextChangedListener(this);
                find_tab_text.setSelection(tempSelection);
            }

        }
    };

    //返回按钮弹出  是否要保存草稿
    public void WdReturn() {
        type = 2;

        view = getLayoutInflater().inflate(R.layout.find_tw_view_dialog, null);
        ab = new MyDialog(FindCGDetail.this, view, R.style.dialog);
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

    //标签弹框
    public void showLabelList() {
        view1 = getLayoutInflater().inflate(R.layout.question_label, null);
        ab1 = new MyDialog(FindCGDetail.this, view1, R.style.dialog);
        RecyclerView labelList = (RecyclerView) view1.findViewById(R.id.recycler_label);//列表
        ImageView label_x = (ImageView) view1.findViewById(R.id.label_x);//关闭框


        findWdLabelAdapter = new FindWdLabelAdapter(FindCGDetail.this, list);
        labelList.setLayoutManager(new GridLayoutManager(FindCGDetail.this, 3));
        labelList.setAdapter(findWdLabelAdapter);

        findWdLabelAdapter.setOnListItemClickListener(new OnListItemClickListener() {
            @Override
            public void onItemClickListener(int position, View v) {
                findWdLabelAdapter.setSelected(position);
                labelText = list.get(position);
                ab1.dismiss();
            }
        });
        label_x.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ab1.dismiss();
            }
        });
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            if (requestCode == 103) {
                List<String> piclist = data.getStringArrayListExtra(PhotoPickerActivity.EXTRA_RESULT_SELECTION);
                photoAdapter.addDataPhoto(piclist);

            }
        }
    }
}
