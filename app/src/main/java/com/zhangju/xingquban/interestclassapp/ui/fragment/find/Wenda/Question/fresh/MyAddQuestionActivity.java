package com.zhangju.xingquban.interestclassapp.ui.fragment.find.Wenda.Question.fresh;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
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
import com.zhangju.xingquban.interestclassapp.adapter.baseadpter.OnListItemClickListener;
import com.zhangju.xingquban.interestclassapp.hplper.ScrollGridManager;
import com.zhangju.xingquban.interestclassapp.refactor.common.bean.CommonInterface;
import com.zhangju.xingquban.interestclassapp.refactor.common.bean.Response;
import com.zhangju.xingquban.interestclassapp.refactor.me.bean.ResponseUploadImage;
import com.zhangju.xingquban.interestclassapp.refactor.user.UserManager;
import com.zhangju.xingquban.interestclassapp.ui.fragment.find.Wenda.Question.fresh.adapter.ImageAdapter;
import com.zhangju.xingquban.interestclassapp.ui.fragment.find.Wenda.Question.fresh.bean.QuestionMainBean;
import com.zhangju.xingquban.interestclassapp.ui.fragment.find.Wenda.Question.fresh.interfacequestion.MyQuestion;
import com.zhangju.xingquban.interestclassapp.ui.fragment.find.Wenda.wdcgtiwen.FindWdLabelAdapter;
import com.zhangju.xingquban.interestclassapp.ui.fragment.me.Settings.MyDialog;
import com.zhangju.xingquban.interestclassapp.ui.widget.PublicHead;
import com.zhangju.xingquban.interestclassapp.util.LoadingDialog;
import com.zhangju.xingquban.interestclassapp.util.ToastUtil;
import com.zhangju.xingquban.interestclassapp.view.me.AlertDialogIOS;

import java.io.File;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


/**
 * create by hqf 2017//11 /16
 * 我的问答--提问
 */
@ContentView(R.layout.activity_my_add_question)
public class MyAddQuestionActivity extends FastActivity {

    @BindView(R.id.pub_head)
    PublicHead pubHead;
    @BindView(R.id.ask_question_title)
    EditText askQuestionTitle;
    @BindView(R.id.find_tab_text_xt)
    EditText findTabTextXt;
    @BindView(R.id.add_recyclerview_question)
    RecyclerView addRecyclerviewQuestion;
    @BindView(R.id.tv_ask_picselect)
    ImageView tvAskPicselect;
    @BindView(R.id.image_select_label)
    ImageView imageSelectLabel;
    @BindView(R.id.tv_select_label)
    TextView tvSelectLabel;

    private StringBuilder picBuilder = new StringBuilder();//图片地址
    private List<String> loadList = new ArrayList<String>();//上传返回集合

    private List<String> picList = new ArrayList<>();//图片集合
    private List<String> draftList = new ArrayList<>();//草稿图片

    private ImageAdapter imageAdapter;
    private LoadingDialog loadingDialog;
    private int isRelease = 1;

    List<String> list = Arrays.asList("手绘", "美术", "健身", "舞蹈", "棋类", "户外", "球类", "乐器", "亲子");
    private View view, view1;
    private MyDialog ab, ab1;
    FindWdLabelAdapter findWdLabelAdapter;
    private String labelText = "";//用户选择的标签
    private String id;
    int type;

    @Override
    protected void alreadyPrepared() {
        loadingDialog = new LoadingDialog(MyAddQuestionActivity.this);
        setpubHead();
        setPicListAdapter();
        showLabelList();
        QuestionMainBean.AaDataBean data = (QuestionMainBean.AaDataBean) getIntent().getSerializableExtra("data");
        type = getIntent().getIntExtra("type", 0);
        if (type == 2) {
            if (data != null) {
                String title = data.getTitle() == null ? "" : data.getTitle();
                labelText = data.getLabel() == null ? "" : data.getLabel();
                id = data.getId();
                String content = data.getqExplain() == null ? "" : data.getqExplain();
                List<QuestionMainBean.AaDataBean.PicStrBeans> picStr = data.getPicStr();
                for (QuestionMainBean.AaDataBean.PicStrBeans beans : picStr) {
                    picList.add(beans.getPicture());
                    draftList.add(beans.getPicture());
                }
                imageAdapter.notifyDataSetChanged();
                askQuestionTitle.setText(title);
                findTabTextXt.setText(content);
                if (!labelText.isEmpty()) {
                    tvSelectLabel.setText(labelText);
                    imageSelectLabel.setVisibility(View.GONE);
                    tvSelectLabel.setVisibility(View.VISIBLE);

                }
            }
        }

    }

    private void showLabelList() {
        view1 = getLayoutInflater().inflate(R.layout.question_label, null);
        ab1 = new MyDialog(MyAddQuestionActivity.this, view1, R.style.dialog);
        RecyclerView labelList = (RecyclerView) view1.findViewById(R.id.recycler_label);//列表
        ImageView label_x = (ImageView) view1.findViewById(R.id.label_x);//关闭框


        findWdLabelAdapter = new FindWdLabelAdapter(MyAddQuestionActivity.this, list);
        labelList.setLayoutManager(new GridLayoutManager(MyAddQuestionActivity.this, 3));
        labelList.setAdapter(findWdLabelAdapter);

        findWdLabelAdapter.setOnListItemClickListener(new OnListItemClickListener() {
            @Override
            public void onItemClickListener(int position, View v) {
                findWdLabelAdapter.setSelected(position);
                labelText = list.get(position);
                tvSelectLabel.setVisibility(View.VISIBLE);
                tvSelectLabel.setText(labelText);
                imageSelectLabel.setVisibility(View.GONE);
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

    private void setPicListAdapter() {
        //图片
        imageAdapter = new ImageAdapter(MyAddQuestionActivity.this, picList);
        ScrollGridManager manager = new ScrollGridManager(MyAddQuestionActivity.this, 3);
        manager.setScrollEnabled(false);
        addRecyclerviewQuestion.setLayoutManager(manager);
        addRecyclerviewQuestion.setAdapter(imageAdapter);
    }

    private void setpubHead() {
        pubHead.setTitle("提问");
        pubHead.setRightTextColor(R.color.color_main);
        pubHead.setRightTitle("发布");
        pubHead.setBackClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isRelease = 2;
                loadingDialog.setLoading("保存中...");

                String title = askQuestionTitle.getText().toString().trim();
                String content = findTabTextXt.getText().toString().trim();
                if (title.isEmpty() && content.isEmpty() && picList.size() == 0 && labelText.isEmpty()) {
                    finish();
                } else {
                    if (askQuestionTitle.getText().toString().isEmpty()) {
                        ToastUtil.showToast("标题不能为空");
                    } else {
                        showExitDialog();
                    }
                }


            }
        });

        pubHead.setRightClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isRelease = 1;
                loadingDialog.setLoading("提交中...");

                String title = askQuestionTitle.getText().toString().trim();
                String content = findTabTextXt.getText().toString().trim();

                if (title.isEmpty()) {
                    ToastUtil.showToast("标题不能为空");
                } else if (labelText.isEmpty()) {
                    ToastUtil.showToast("标签不能为空");
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

    @OnClick({R.id.tv_ask_picselect, R.id.image_select_label, R.id.tv_select_label})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_ask_picselect:
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
                break;
            case R.id.image_select_label:
                ab1.show();
                break;
            case R.id.tv_select_label:
                ab1.show();
                break;
        }
    }


    //退出提示框
    private void showExitDialog() {
        new AlertDialogIOS(MyAddQuestionActivity.this).builder().setTitle("提示")
                .setMsg(isRelease == 2 ? "是否保存本次编辑?" : "是否确认发布?")
                .setPositiveButton("确定", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        loadingDialog.show();
                        if (picList.size() > 0) {
                            loadList.clear();
                            loadList.addAll(draftList);

                            List<String> mlist = new ArrayList<String>();

                            for (String s : picList) {
                                if (!s.contains("http")) {
                                    mlist.add(s);
                                }
                            }
                            if (mlist.size() == 0) {
                                commitEditContent(askQuestionTitle.getText().toString(), findTabTextXt.getText().toString());

                            } else {


                                for (String s : mlist) {

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


                                                    commitEditContent(askQuestionTitle.getText().toString(), findTabTextXt.getText().toString());
                                                }
                                            } else {
                                                stopTask();
                                            }
                                        }
                                    }));
                                }
                            }
                        } else {
                            commitEditContent(askQuestionTitle.getText().toString(), findTabTextXt.getText().toString());
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


    //提交编辑数据
    private void commitEditContent(String title, String content) {
        final Request request = Request.obtain(MyQuestion.POST_QUESTION_ADD_COMMIT);
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
        request.put("label", labelText);
        request.put("title", title);
        if (type == 2) {
            request.put("id", id);
        }

        request.put("qExplain", content);
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
}
