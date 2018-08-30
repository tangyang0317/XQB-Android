package com.zhangju.xingquban.refactoring.activity;

import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.EditText;

import com.fastlib.annotation.Bind;
import com.fastlib.annotation.ContentView;
import com.fastlib.app.FastActivity;
import com.fastlib.app.PhotoResultListener;
import com.fastlib.app.task.Action;
import com.fastlib.app.task.EmptyAction;
import com.fastlib.app.task.NetAction;
import com.fastlib.app.task.NoReturnAction;
import com.fastlib.app.task.Task;
import com.fastlib.app.task.ThreadType;
import com.fastlib.net.Request;
import com.fastlib.utils.N;
import com.fastlib.widget.TitleBar;
import com.zhangju.xingquban.R;
import com.zhangju.xingquban.interestclassapp.RetrofitInterface.NetWork;
import com.zhangju.xingquban.interestclassapp.refactor.common.bean.CommonInterface;
import com.zhangju.xingquban.interestclassapp.refactor.common.bean.Response;
import com.zhangju.xingquban.interestclassapp.refactor.me.activity.EditCourseActivity;
import com.zhangju.xingquban.interestclassapp.refactor.me.activity.OrgProfileActivity;
import com.zhangju.xingquban.interestclassapp.refactor.me.activity.publish_active.AddFeatureTextActivity;
import com.zhangju.xingquban.interestclassapp.refactor.me.adapter.OrgProfileDisplayAdapter;
import com.zhangju.xingquban.interestclassapp.refactor.me.adapter.PublishActiveFeatureAdapter;
import com.zhangju.xingquban.interestclassapp.refactor.me.bean.MeInterface;
import com.zhangju.xingquban.interestclassapp.refactor.me.bean.PublishActiveFeature;
import com.zhangju.xingquban.interestclassapp.refactor.me.bean.ResponseOrgProfile;
import com.zhangju.xingquban.interestclassapp.refactor.me.bean.ResponseUploadImage;
import com.zhangju.xingquban.interestclassapp.refactor.me.fragment.publish_active.AddFeatureDialog;
import com.zhangju.xingquban.interestclassapp.refactor.user.UserManager;
import com.zhangju.xingquban.interestclassapp.util.ToastUtil;
import com.zhangju.xingquban.refactoring.entity.BaseResponseBean;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * @packageName com.zhangju.xingquban.refactoring.activity
 * @FileName EditCourseDetailsActivity
 * @Author tangyang
 * @DATE 2018/8/18
 **/
@ContentView(R.layout.activity_edit_course_details)
public class EditCourseDetailsActivity extends FastActivity {

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
        loading();
        startTask(Task.beginCycle(mAdapter.getData())
                .filter(new Action<PublishActiveFeature, Boolean>() {  //过滤出是本地图像的item，上传并将返回的地址赋值到PublishActiveFeature.imageUrl中
                    @Override
                    protected Boolean execute(PublishActiveFeature param) throws Throwable {
                        return param != null && param.type == PublishActiveFeature.TYPE_IMAGE;
                    }
                })
                .next(new Action<PublishActiveFeature, Request>() {

                    @Override
                    protected Request execute(PublishActiveFeature param) throws Throwable {
                        Request request = Request.obtain(CommonInterface.POST_UPLOAD_IMAGE).put("files", new File(param.content));
                        request.putHeader("X-CustomToken", UserManager.getInstance().getToken());
                        request.setTag(param);
                        return request;
                    }
                })
                .next(new NetAction<Response<List<ResponseUploadImage>>>() {

                    @Override
                    protected void executeAdapt(Response<List<ResponseUploadImage>> listResponse, Request request) {
                        if (listResponse.success && listResponse.data != null && !listResponse.data.isEmpty())
                            ((PublishActiveFeature) request.getTag()).imageUrl = listResponse.data.get(0).fileName;
                        else stopTask();
                    }
                })
                .again(new Action<List<Response<List<ResponseUploadImage>>>, Request>() { //拼接图像和文本发布新机构简介

                    @Override
                    protected Request execute(List<Response<List<ResponseUploadImage>>> param) throws Throwable {
                        Request request = Request.obtain(MeInterface.POST_ADD_COURSE);
                        request.putHeader("X-CustomToken", UserManager.getInstance().getToken());
                        request.put("areasId", getLessonParam().get("areasId"));
                        request.put("allows", getLessonParam().get("allows"));
                        request.put("cityCode", getLessonParam().get("cityCode"));
                        request.put("provinceId", getLessonParam().get("provinceId"));
                        request.put("areasName", getLessonParam().get("areasName"));
                        request.put("cityName", getLessonParam().get("cityName"));
                        request.put("courses", getLessonParam().get("courses"));
                        request.put("customerId", getLessonParam().get("customerId"));
                        request.put("isCantry", getLessonParam().get("isCantry"));
                        request.put("lat", getLessonParam().get("lat"));
                        request.put("lng", getLessonParam().get("lng"));
                        request.put("methodType", getLessonParam().get("methodType"));
                        request.put("name", getLessonParam().get("name"));
                        request.put("picture", getLessonParam().get("picture"));
                        request.put("price", getLessonParam().get("price"));
                        request.put("provinceName", getLessonParam().get("provinceName"));
                        request.put("region", getLessonParam().get("region"));
                        request.put("resId", getLessonParam().get("resId"));
                        request.put("teacherTimeId", getLessonParam().get("teacherTimeId"));
                        request.put("timelength", getLessonParam().get("timelength"));
                        request.put("vipPrice", getLessonParam().get("vipPrice"));
                        request.put("lessonDate", getLessonParam().get("lessonDate"));
                        request.put("categoriesId", getLessonParam().get("categoriesId"));
                        request.put("catagoryName", getLessonParam().get("catagoryName"));
                        request.put("descript", getLessonParam().get("descript"));
                        request.put("summary", concatProfile());
                        if (!TextUtils.isEmpty(getLessonParam().get("id")) && !"-1".equals(getLessonParam().get("id"))) {
                            request.put("id", getLessonParam().get("id"));
                        }
                        return request;
                    }
                })
                .next(new NetAction<Response<ResponseOrgProfile>>() {

                    @Override
                    protected void executeAdapt(Response<ResponseOrgProfile> response, Request request) {
                        if (response.success) {
                            EditCourseActivity.instance.finish();
                            EditCourseDetailsActivity.this.finish();
                        } else {
                            N.showShort(EditCourseDetailsActivity.this, "课程发布失败");
                        }
                    }
                }, ThreadType.MAIN), new NoReturnAction<Throwable>() {
            @Override
            public void executeAdapt(Throwable param) {
                param.printStackTrace();
                dismissLoading();
            }
        }, new EmptyAction() {
            @Override
            protected void executeAdapt() {
                dismissLoading();
            }
        });
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
        AddFeatureDialog.getInstance(this).show(0, this, new PhotoResultListener() {
            @Override
            public void onPhotoResult(String path) {
                PublishActiveFeature feature = new PublishActiveFeature();
                feature.type = PublishActiveFeature.TYPE_IMAGE;
                feature.content = path;
                mAdapter.addData(feature);
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
        }
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
            if (profile.type == PublishActiveFeature.TYPE_TEXT || profile.type == PublishActiveFeature.TYPE_IMAGE_URL)
                sb.append(profile.content);
            else if (profile.type == PublishActiveFeature.TYPE_IMAGE) sb.append(profile.imageUrl);
            sb.append("#");
        }
        if (sb.length() > 0) sb.deleteCharAt(sb.length() - 1);
        return sb.toString();
    }
}
