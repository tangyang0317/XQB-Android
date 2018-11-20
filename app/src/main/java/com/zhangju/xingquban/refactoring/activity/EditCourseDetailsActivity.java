package com.zhangju.xingquban.refactoring.activity;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.EditText;

import com.fastlib.annotation.Bind;
import com.fastlib.annotation.ContentView;
import com.fastlib.app.FastActivity;
import com.fastlib.widget.TitleBar;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.RequestParams;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest;
import com.zhangju.xingquban.R;
import com.zhangju.xingquban.interestclassapp.refactor.me.activity.publish_active.AddFeatureTextActivity;
import com.zhangju.xingquban.interestclassapp.refactor.me.adapter.OrgProfileDisplayAdapter;
import com.zhangju.xingquban.interestclassapp.refactor.me.adapter.PublishActiveFeatureAdapter;
import com.zhangju.xingquban.interestclassapp.refactor.me.bean.PublishActiveFeature;
import com.zhangju.xingquban.interestclassapp.refactor.me.bean.ResponseOrgProfile;
import com.zhangju.xingquban.interestclassapp.refactor.me.bean.ResponseUploadImage;
import com.zhangju.xingquban.interestclassapp.refactor.me.fragment.publish_active.AddFeatureDialog;
import com.zhangju.xingquban.interestclassapp.refactor.user.UserManager;
import com.zhangju.xingquban.interestclassapp.util.ToastUtil;
import com.zhangju.xingquban.interestclassapp.util.UrlUtils;
import com.zhangju.xingquban.refactoring.entity.BaseResponseBean;
import com.zhangju.xingquban.refactoring.fragment.CurriculumDetailsPreViewActivity;
import com.zhangju.xingquban.refactoring.interfacs.BottomSheetDialogListener;
import com.zhangju.xingquban.refactoring.view.AddRichTextDialog;
import com.zhihu.matisse.Matisse;
import com.zhihu.matisse.MimeType;
import com.zhihu.matisse.engine.impl.GlideEngine;
import com.zhihu.matisse.internal.entity.CaptureStrategy;

import java.io.File;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * @packageName com.zhangju.xingquban.refactoring.activity
 * @FileName EditCourseDetailsActivity
 * @Author tangyang
 * @DATE 2018/8/18
 **/
@ContentView(R.layout.activity_edit_course_details)
public class EditCourseDetailsActivity extends FastActivity {

    public static Activity instance;
    @Bind(R.id.titleBar)
    TitleBar titleBar;
    @Bind(R.id.lessonDescripEdt)
    EditText lessonDescripEdt;
    @Bind(R.id.list)
    RecyclerView mList;
    @Bind(R.id.emptyView)
    View mEmpty;
    ResponseOrgProfile mOrgProfile;
    OrgProfileDisplayAdapter mDisplayAdapter;
    PublishActiveFeatureAdapter mAdapter; //活动特色适配器复用.
    final int REQUEST_CODE_CHOOSE_IMG = 5;

    /***
     * 获取课程详情数据参数
     * @return
     */
    private HashMap<String, String> getLessonParam() {
        if (getIntent() != null) {
            return (HashMap<String, String>) getIntent().getSerializableExtra("lessonMap");
        }
        return null;
    }

    @Override
    protected void alreadyPrepared() {
        instance = this;
        lessonDescripEdt.clearFocus();
        mDisplayAdapter = new OrgProfileDisplayAdapter(this);
        mList.setAdapter(mAdapter = new PublishActiveFeatureAdapter(this));
        mAdapter.setEditable(false);
        mAdapter.registerAdapterDataObserver(new RecyclerView.AdapterDataObserver() { //如果编辑适配器不为空说明正在编辑，否则是介绍界面
            @Override
            public void onChanged() {
                super.onChanged();
                if (mAdapter.getItemCount() > 0) {
                    mEmpty.setVisibility(View.GONE);
                    mList.setVisibility(View.VISIBLE);
                } else {
                    mEmpty.setVisibility(View.VISIBLE);
                    mList.setVisibility(View.GONE);
                }
            }
        });

        mDisplayAdapter.registerAdapterDataObserver(new RecyclerView.AdapterDataObserver() {
            @Override
            public void onChanged() {
                super.onChanged();
                if (mDisplayAdapter.getItemCount() > 0) {
                    mEmpty.setVisibility(View.GONE);
                    mList.setVisibility(View.VISIBLE);
                }
            }
        });
        titleBar.setOnLeftClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


        titleBar.setOnRightClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /*****调用发布或者修改方法*****/
                requestPublishProfile();
            }
        });

        if (TextUtils.isEmpty(getLessonParam().get("descript")) && TextUtils.isEmpty(getLessonParam().get("summary"))) {
            mEmpty.setVisibility(View.VISIBLE);
            mList.setVisibility(View.GONE);
        } else {
            mEmpty.setVisibility(View.GONE);
            mList.setVisibility(View.VISIBLE);
            lessonDescripEdt.setText(getLessonParam().get("descript"));
            mOrgProfile = new ResponseOrgProfile();
            mOrgProfile.setId("1");
            mOrgProfile.setIntro(getLessonParam().get("summary"));
            parseOrgProfile();
        }

    }


    /**
     * 课程发布和修改
     */
    private void requestPublishProfile() {

        HashMap<String, String> params = getLessonParam();
        final String lessonDescripStr = lessonDescripEdt.getText().toString();
        params.put("descript", lessonDescripStr);
        params.put("summary", concatProfile());

        if (lessonDescripStr.isEmpty()) {
            ToastUtil.showToast("请填写课程描述课程描述！");
            return;
        }
        if (concatProfile().isEmpty()) {
            ToastUtil.showToast("请填写课程描述课程描述！");
            return;
        }
        CurriculumDetailsPreViewActivity.lanuchActivity(EditCourseDetailsActivity.this, params);
    }


    /**
     * 解析机构简介
     */
    private void parseOrgProfile() {
        mDisplayAdapter.setData(mOrgProfile);
        mList.setAdapter(mDisplayAdapter);
        convertProfileToEditable();
    }


    /**
     * 转换机构简介为可编辑状态
     */
    private void convertProfileToEditable() {
        if (mOrgProfile == null || TextUtils.isEmpty(mOrgProfile.intro)) {
            add();
            return;
        }
        String[] ss = mOrgProfile.intro.split("#");
        List<PublishActiveFeature> features = new ArrayList<>();
        for (String s : ss) {
            PublishActiveFeature feature = new PublishActiveFeature();
            feature.content = s;
            feature.type = Patterns.WEB_URL.matcher(s).matches() ? PublishActiveFeature.TYPE_IMAGE_URL : PublishActiveFeature.TYPE_TEXT;
            features.add(feature);
        }
        mAdapter.setData(features);
        mList.setAdapter(mAdapter);
    }

    @Bind(R.id.add)
    private void add() {

        AddRichTextDialog.getInstance(this).show(0, this, new BottomSheetDialogListener() {
            @Override
            public void onImageClickListenner() {
                Matisse.from(EditCourseDetailsActivity.this)
                        .choose(MimeType.ofImage(), true)
                        .capture(true)
                        .maxSelectable(1)
                        .captureStrategy(new CaptureStrategy(true, "com.zhangju.xingquban.fileprovider"))
                        .countable(true)
                        .showSingleMediaType(true)
                        .gridExpectedSize(getResources().getDimensionPixelSize(R.dimen.grid_expected_size))
                        .restrictOrientation(ActivityInfo.SCREEN_ORIENTATION_UNSPECIFIED)
                        .thumbnailScale(0.85f)
                        .imageEngine(new GlideEngine())
                        .forResult(REQUEST_CODE_CHOOSE_IMG);
            }

            @Override
            public void onTextClickListenner() {
                Intent intent = new Intent(EditCourseDetailsActivity.this, AddFeatureTextActivity.class);
                intent.putExtra(AddFeatureTextActivity.ARG_RES_INT_POSITION, 0);
                EditCourseDetailsActivity.this.startActivityForResult(intent, AddFeatureDialog.REQ_ADD_FEATURE_TEXT);
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode != RESULT_OK) return;
        if (requestCode == AddFeatureDialog.REQ_ADD_FEATURE_TEXT) {
            PublishActiveFeature feature = new PublishActiveFeature();
            feature.type = PublishActiveFeature.TYPE_TEXT;
            feature.content = data.getStringExtra(AddFeatureTextActivity.ARG_RES_STR_TEXT);
            mAdapter.addData(feature);
        } else if (requestCode == REQUEST_CODE_CHOOSE_IMG) {
            List<String> pathList = Matisse.obtainPathResult(data);
            if (pathList != null && pathList.size() > 0) {
                upLoadImg(pathList.get(0));
            }
        }
    }


    /***
     * 课程发布
     */
    private void upLoadImg(String filePath) {
        RequestParams params = new RequestParams();
        params.addHeader("X-CustomToken", UserManager.getInstance().getToken());
        params.addBodyParameter("files", new File(filePath));
        new HttpUtils().send(HttpRequest.HttpMethod.POST, UrlUtils.URL_UPLOAD_IMG, params, new RequestCallBack<String>() {

            @Override
            public void onSuccess(ResponseInfo<String> responseInfo) {
                Type type = new TypeToken<BaseResponseBean<List<ResponseUploadImage>>>() {
                }.getType();
                BaseResponseBean<List<ResponseUploadImage>> listBaseResponseBean = new Gson().fromJson(responseInfo.result, type);
                if (listBaseResponseBean.isSuccess()) {
                    if (listBaseResponseBean.getAaData() != null && listBaseResponseBean.getAaData().size() > 0) {
                        String imgPath = listBaseResponseBean.getAaData().get(0).fileName;
                        PublishActiveFeature feature = new PublishActiveFeature();
                        feature.type = PublishActiveFeature.TYPE_IMAGE_URL;
                        feature.content = imgPath;
                        mAdapter.addData(feature);
                    }
                }
            }

            @Override
            public void onFailure(HttpException e, String s) {
                ToastUtil.showToast(s);
            }
        });

    }


    /**
     * 拼接文本和图片信息发布机构简介
     *
     * @return 拼接好的机构简介
     */
    private String concatProfile() {
        StringBuilder sb = new StringBuilder();
        List<PublishActiveFeature> list = mAdapter.getData();
        for (PublishActiveFeature profile : list) {
            if (profile.type == PublishActiveFeature.TYPE_TEXT || profile.type == PublishActiveFeature.TYPE_IMAGE_URL) {
                sb.append(profile.content);
                sb.append("#");
            } else if (profile.type == PublishActiveFeature.TYPE_IMAGE) {
                sb.append(profile.imageUrl);
                sb.append("#");
            }
        }
        if (sb.length() > 0 && sb.toString().endsWith("#")) {
            sb.deleteCharAt(sb.length() - 1);
        }
        return sb.toString();
    }
}
