package com.zhangju.xingquban.interestclassapp.ui.fragment.find.Wenda.Question.fresh;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.fastlib.annotation.ContentView;
import com.fastlib.app.FastActivity;
import com.fastlib.app.FastDialog;
import com.fastlib.app.PhotoResultListener;
import com.fastlib.app.task.Action;
import com.fastlib.app.task.NetAction;
import com.fastlib.app.task.Task;
import com.fastlib.net.Request;
import com.fastlib.net.SimpleListener;
import com.zhangju.xingquban.R;
import com.zhangju.xingquban.interestclassapp.hplper.ScrollGridManager;
import com.zhangju.xingquban.interestclassapp.refactor.common.bean.CommonInterface;
import com.zhangju.xingquban.interestclassapp.refactor.common.bean.Response;
import com.zhangju.xingquban.interestclassapp.refactor.me.bean.ResponseUploadImage;
import com.zhangju.xingquban.interestclassapp.refactor.user.UserManager;
import com.zhangju.xingquban.interestclassapp.ui.fragment.find.Wenda.Question.fresh.adapter.ImageAdapter;
import com.zhangju.xingquban.interestclassapp.ui.fragment.find.Wenda.Question.fresh.bean.QuestionMainBean;
import com.zhangju.xingquban.interestclassapp.ui.fragment.find.Wenda.Question.fresh.interfacequestion.MyQuestion;
import com.zhangju.xingquban.interestclassapp.ui.widget.PublicHead;
import com.zhangju.xingquban.interestclassapp.util.LoadingDialog;
import com.zhangju.xingquban.interestclassapp.util.ToastUtil;
import com.zhangju.xingquban.interestclassapp.view.me.AlertDialogIOS;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * create by hqf 2017//11/16
 * 我的问答--我的回答
 */
@ContentView(R.layout.activity_my_answer)
public class MyAnswerActivity extends FastActivity {

    @BindView(R.id.pub_head)
    PublicHead pubHead;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.edit_content)
    EditText editContent;
    @BindView(R.id.image_select)
    ImageView imageSelect;
    @BindView(R.id.answer_recyclerview)
    RecyclerView answer_recyclerview;


    private String questionId;
    private StringBuilder picBuilder = new StringBuilder();//图片地址
    private List<String> loadList = new ArrayList<String>();//上传返回集合
    private List<String> draftList = new ArrayList<String>();//草稿来的图片
    private int isRelease = 1;//1提交  2保存编辑

    private List<String> picList = new ArrayList<>();//图片集合
    private ImageAdapter imageAdapter;
    private LoadingDialog loadingDialog;
    private String id;
    int type;

    @Override
    protected void alreadyPrepared() {
        setPicListAdapter();
        questionId = getIntent().getStringExtra("id");
        type = getIntent().getIntExtra("type", 0);
        QuestionMainBean.AaDataBean data = (QuestionMainBean.AaDataBean) getIntent().getSerializableExtra("data");
        if (data != null) {
            try {
                if (type == 1) {
                    questionId = data.getId();
                    String title = data.getTitle() == null ? "" : data.getTitle();//内容

                    tvTitle.setText(title);

                } else if (type == 3) {
                    questionId = data.getQuestion().getId();
                    String title = data.getQuestion().getTitle() == null ? "" : data.getQuestion().getTitle();//内容

                    tvTitle.setText(title);

                } else {

                    id = data.getId();
                    if (data.getQuestionTitle() != null) {
                        String content = data.getQuestionTitle().getTitle() == null ? "" : data.getQuestionTitle().getTitle();//内容
                        questionId = data.getQuestionTitle().getId();
                        tvTitle.setText(content);
                    }

                    String title = data.getTitle() == null ? "" : data.getTitle();//标题

                    List<QuestionMainBean.AaDataBean.PicStrBeans> picStr = data.getPicStr();
                    for (QuestionMainBean.AaDataBean.PicStrBeans beans : picStr) {
                        draftList.add(beans.getPicture());
                        picList.add(beans.getPicture());

                    }


                    editContent.setText(title);
                    imageAdapter.notifyDataSetChanged();
                }


            } catch (Exception ex) {
                ex.printStackTrace();

            }

        }
        loadingDialog = new LoadingDialog(MyAnswerActivity.this);
        setPubHead();


    }

    private void setPicListAdapter() {
        //图片
        imageAdapter = new ImageAdapter(MyAnswerActivity.this, picList);
        ScrollGridManager manager = new ScrollGridManager(MyAnswerActivity.this, 3);
        manager.setScrollEnabled(false);
        answer_recyclerview.setLayoutManager(manager);
        answer_recyclerview.setAdapter(imageAdapter);
    }

    private void setPubHead() {
        pubHead.setRightTitle("提交");
        pubHead.setRightTextColor(R.color.color_main);
        pubHead.setTitle("回答");

        //返回保存草稿
        pubHead.setBackClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isRelease = 2;
                loadingDialog.setLoading("保存中...");
                String content = editContent.getText().toString();

                if (content.isEmpty() && picList.size() == 0) {
                    finish();
                } else {
                    showExitDialog();
                }
            }
        });

        //提交操作
        pubHead.setRightClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isRelease = 1;
                loadingDialog.setLoading("提交中...");
                String content = editContent.getText().toString();
                if (content.isEmpty()) {
                    ToastUtil.showToast("清输入答案");
                } else {
                    showExitDialog();
                }

            }
        });
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
    }

    //提交编辑数据
    private void commitEditContent(String title) {
        final Request request = Request.obtain(MyQuestion.POST_QUESTION_MYANSWER_COMMIT);
        final String token = UserManager.getInstance().getToken();
        String customerId = UserManager.getInstance().getUser().id;

        for (int i = 0; i < loadList.size(); i++) {
            if (i == loadList.size() - 1) {
                picBuilder.append(loadList.get(i));
            } else {
                picBuilder.append(loadList.get(i));
                picBuilder.append("#");
            }
        }

        request.put("isRelease", isRelease);
        request.put("customerId", customerId);
        String str = picBuilder.toString();
        if (!str.isEmpty()) {
            request.put("pic", str);
        }
        if (type == 2) {
            request.put("id", id);
        }
        request.put("questionId", questionId);
        request.put("title", title);
        request.addHeader("X-CustomToken", token);
        request.setListener(new SimpleListener<Response>() {

            @Override
            public void onResponseListener(Request r, Response result) {
                boolean success = result.success;
                if (success) {
                    finish();
                }
                loadingDialog.dismiss();
            }

            @Override
            public void onErrorListener(Request r, String error) {
                super.onErrorListener(r, error);
                loadingDialog.dismiss();
            }
        });
        net(request);

    }

    @OnClick(R.id.image_select)
    public void onViewClicked() {
        FastDialog.showListDialog(new String[]{"拍照", "从手机相册选择"}).show(getSupportFragmentManager(), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if (which == 0) openCamera(new PhotoResultListener() {
                    @Override
                    public void onPhotoResult(String path) {
                        picList.add(path);
                        imageAdapter.notifyDataSetChanged();
                    }
                });
                else openAlbum(new PhotoResultListener() {
                    @Override
                    public void onPhotoResult(String path) {
                        picList.add(path);
                        imageAdapter.notifyDataSetChanged();
                    }
                });
            }
        });
    }

    //退出提示框
    private void showExitDialog() {
        new AlertDialogIOS(MyAnswerActivity.this).builder().setTitle(isRelease == 2 ? "提示?" : "温馨提示?")
                .setMsg(isRelease == 2 ? "是否保存本次编辑?" : "是否确认发布?")
                .setPositiveButton("确定", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        loadingDialog.show();
                        if (picList.size() > 0) {

                            loadList.clear();
                            loadList.addAll(draftList);

                            List<String> mlsit = new ArrayList<String>();
                            for (String s : picList) {
                                if (!s.contains("http")) {
                                    mlsit.add(s);
                                }
                            }
                            if (mlsit.size() == 0) {
                                commitEditContent(editContent.getText().toString());
                            } else {


                                for (String s : mlsit) {
                                    startTask(Task.begin(s).next(new Action<String, Request>() {
                                        @Override
                                        protected Request execute(String param) throws Throwable {
                                            return Request.obtain(CommonInterface.POST_UPLOAD_IMAGE).put("files", new File(param))
                                                    .putHeader("X-CustomToken", UserManager.getInstance().getToken());
                                        }
                                    }).next(new NetAction<Response<List<ResponseUploadImage>>>() {
                                        @Override
                                        protected void executeAdapt(Response<List<ResponseUploadImage>> listResponse, Request request) {
                                            if (listResponse.success && listResponse.data != null && !listResponse.data.isEmpty()) {
                                                loadList.add(listResponse.data.get(0).fileName);
                                                //如果上传完成，则进行后台提交
                                                if (loadList.size() == picList.size()) {

                                                    commitEditContent(editContent.getText().toString());
                                                }
                                            } else {
                                                stopTask();
                                            }
                                        }
                                    }));

                                }
                            }
                        } else {
                            commitEditContent(editContent.getText().toString());
                        }
                    }
                }).setNegativeButton("取消", new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isRelease == 2) {
                    finish();
                }

            }
        }).show();

    }
}
