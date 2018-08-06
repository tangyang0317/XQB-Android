package com.zhangju.xingquban.interestclassapp.ui.fragment.find.Wenda;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
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
import com.zhangju.xingquban.interestclassapp.adapter.baseadpter.OnListItemClickListener;
import com.zhangju.xingquban.interestclassapp.application.MyApp;
import com.zhangju.xingquban.interestclassapp.base.BaseActivity;
import com.zhangju.xingquban.interestclassapp.bean.UploadResponse;
import com.zhangju.xingquban.interestclassapp.bean.find.FindQuestionBean;
import com.zhangju.xingquban.interestclassapp.refactor.user.UserManager;
import com.zhangju.xingquban.interestclassapp.ui.fragment.find.Wenda.wdcgtiwen.FindWdLabelAdapter;
import com.zhangju.xingquban.interestclassapp.ui.fragment.me.Settings.MyDialog;
import com.zhangju.xingquban.interestclassapp.ui.widget.PublicHead;
import com.zhangju.xingquban.interestclassapp.util.LoadingDialog;
import com.zhangju.xingquban.interestclassapp.util.ToastUtil;
import com.zhangju.xingquban.interestclassapp.util.UrlUtils;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

//提问提交
public class FindTabTiWen extends BaseActivity {

    @BindView(R.id.find_tab_tiwen)
    PublicHead findTabTiwen;
    @BindView(R.id.find_tab_text)
    EditText findTabText;
    @BindView(R.id.find_tab_textNumber)
    TextView findTabTextNumber;
    @BindView(R.id.find_wenda_girdview)
    GridView gridView;
    @BindView(R.id.find_tab_text_xt)
    EditText findTabTextXt;
    @BindView(R.id.find_wenda_photo)
    ImageView findWendaPhoto;
    @BindView(R.id.find_wenda_lable)
    ImageView findWendaLable;


    private CanDeleteImageAdapter photoAdapter;


    /**
     * 接收选择图片的本地路径
     */

    private View view, view1;
    private MyDialog ab, ab1;
    private CharSequence temp;

    private List<File> fileList = new ArrayList<>();
    private LoadingDialog loadingDialog;


    FindWdLabelAdapter findWdLabelAdapter;
    private String labelText = "";//用户选择的标签
    List<String> pictureList = new ArrayList<>();//图片上传返回集合
    StringBuilder pictureBuilder =  new StringBuilder();//图片接收

    List<String> list = Arrays.asList("手绘", "美术", "健身", "舞蹈", "棋类", "户外", "球类", "乐器", "亲子");
    int type = 1;



    @Override
    public int getLayout() {
        return R.layout.activity_find_tab_ti_wen;
    }

    @Override
    public void initView() {
        setFindTabTiwen();
        WdReturn();
        showLabelList();

        photoAdapter = new CanDeleteImageAdapter(this);
        gridView.setAdapter(photoAdapter);
        //图片数据
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
        loadingDialog = new LoadingDialog(FindTabTiWen.this);
        loadingDialog.setLoading("提交中...");
        findTabText.addTextChangedListener(mTextWatcher);
    }

    public void getTiWen() {
        try {
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
        String title = findTabText.getText().toString().trim();//标题
        String addContent = findTabTextXt.getText().toString().trim();//补充说明
        String customerId = UserManager.getInstance().getUser().id;
        for (int i = 0; i < pictureList.size(); i++) {//图片集合
            if (i == pictureList.size() - 1) {
                pictureBuilder.append(pictureList.get(i));
            } else {
                pictureBuilder.append(pictureList.get(i) + "#");
            }
        }

        HttpUtils httpUtils = new HttpUtils();
        String token = UserManager.getInstance().getToken();
        RequestParams params = new RequestParams();
        params.addBodyParameter("customerId", customerId);
        params.addBodyParameter("title", title);
        params.addBodyParameter("qExplain", addContent);
        params.addBodyParameter("pic", pictureBuilder.toString());
        params.addBodyParameter("isRelease", type+"");
        params.addBodyParameter("label", labelText);
        params.addHeader("X-CustomToken", token);


        String url = UrlUtils.POST_QUESTION_COMMIT;
        httpUtils.send(HttpRequest.HttpMethod.POST, url, params,
                new RequestCallBack<String>() {
                    @Override
                    public void onSuccess(ResponseInfo<String> responseInfo) {
                        String json = responseInfo.result;
                        FindQuestionBean object = JSONObject.parseObject(json, FindQuestionBean.class);
                        boolean success = object.isSuccess();
                        if (type == 1) {
                            if (success) {
                                loadingDialog.dismiss();
                                ToastUtil.showToast("发布成功");
                                finish();
                            }
                        } else {
                            if (success) {
                                startActivity(new Intent(FindTabTiWen.this, FindWendaCaog.class));
                            }
                        }
                    }

                    @Override
                    public void onFailure(HttpException e, String s) {
                        ToastUtil.showToast(MyApp.instance, "请求服务器异常,请检查您的网络连接");
                    }
                });

    }

    public void setFindTabTiwen() {
        findTabTiwen.setTitle("提问");
        findTabTiwen.setRightTitle("发布");
        findTabTiwen.setRightTextColor(R.color.color_main);
        findTabTiwen.setBackClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (findTabText.length() > 0) {
                    ab.show();
                } else if (findTabTextXt.length() > 0) {
                    ab.show();
                } else {
                    finish();
                }
            }
        });
        //发布操作
        findTabTiwen.setRightClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                type = 1;
                if (!findTabText.getText().toString().isEmpty()) {
                    loadingDialog.show();
                    getTiWen();
                } else {
                    ToastUtil.showToast("请输入发布内容");
                }
            }
        });
    }

    @Override
    public void initListener() {
    }

    @OnClick({R.id.find_wenda_lable, R.id.find_wenda_photo})
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.find_wenda_lable:

                ab1.show();
                break;
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
                            .forResult(101);
                }

                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            if (requestCode == 101) {
                List<String> piclist = data.getStringArrayListExtra(PhotoPickerActivity.EXTRA_RESULT_SELECTION);
                photoAdapter.addDataPhoto(piclist);

            }
        }
    }




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
    }

    //标签弹框


    public void showLabelList() {
        view1 = getLayoutInflater().inflate(R.layout.question_label, null);
        ab1 = new MyDialog(FindTabTiWen.this, view1, R.style.dialog);
        RecyclerView labelList = (RecyclerView) view1.findViewById(R.id.recycler_label);//列表
        ImageView label_x = (ImageView) view1.findViewById(R.id.label_x);//关闭框


        findWdLabelAdapter = new FindWdLabelAdapter(FindTabTiWen.this, list);
        labelList.setLayoutManager(new GridLayoutManager(FindTabTiWen.this, 3));
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


    //返回按钮弹出  是否要保存草稿
    public void WdReturn() {
        type = 2;

        view = getLayoutInflater().inflate(R.layout.find_tw_view_dialog, null);
        ab = new MyDialog(FindTabTiWen.this, view, R.style.dialog);
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
            editStart = findTabText.getSelectionStart();
            editEnd = findTabText.getSelectionEnd();
            findTabTextNumber.setText(temp.length() + "");
            if (temp.length() > 40) {
                ToastUtil.showToast("你输入的字数已经超过了限制！");
                s.delete(editStart - 1, editEnd);
                int tempSelection = editStart;
                findTabText.removeTextChangedListener(this);
                findTabText.setText(s);
                findTabText.addTextChangedListener(this);
                findTabText.setSelection(tempSelection);
            }

        }
    };

}
