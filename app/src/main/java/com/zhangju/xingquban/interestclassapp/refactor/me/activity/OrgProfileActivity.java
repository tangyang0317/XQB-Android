package com.zhangju.xingquban.interestclassapp.refactor.me.activity;

import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;

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
import com.fastlib.net.SimpleListener;
import com.fastlib.utils.N;
import com.fastlib.widget.TitleBar;
import com.zhangju.xingquban.R;
import com.zhangju.xingquban.interestclassapp.refactor.common.bean.CommonInterface;
import com.zhangju.xingquban.interestclassapp.refactor.common.bean.Response;
import com.zhangju.xingquban.interestclassapp.refactor.me.activity.publish_active.AddFeatureTextActivity;
import com.zhangju.xingquban.interestclassapp.refactor.me.adapter.OrgProfileDisplayAdapter;
import com.zhangju.xingquban.interestclassapp.refactor.me.adapter.PublishActiveFeatureAdapter;
import com.zhangju.xingquban.interestclassapp.refactor.me.bean.MeInterface;
import com.zhangju.xingquban.interestclassapp.refactor.me.bean.PublishActiveFeature;
import com.zhangju.xingquban.interestclassapp.refactor.me.bean.ResponseOrgProfile;
import com.zhangju.xingquban.interestclassapp.refactor.me.bean.ResponseUploadImage;
import com.zhangju.xingquban.interestclassapp.refactor.me.fragment.publish_active.AddFeatureDialog;
import com.zhangju.xingquban.interestclassapp.refactor.user.UserManager;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by sgfb on 2017/11/3.
 * 机构简介
 */
@ContentView(R.layout.act_org_profile)
public class OrgProfileActivity extends FastActivity {
    @Bind(R.id.titleBar)
    TitleBar mTitleBar;
    @Bind(R.id.list)
    RecyclerView mList;
    @Bind(R.id.emptyView)
    View mEmpty;
    ResponseOrgProfile mOrgProfile;
    OrgProfileDisplayAdapter mDisplayAdapter;
    PublishActiveFeatureAdapter mAdapter; //活动特色适配器复用.

    //发布机构简介点击事件
    private View.OnClickListener mPublishProfileListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            requestPublishProfile();
        }
    };
    //编辑机构简介点击事件
    private View.OnClickListener mEditListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            convertProfileToEditable();
        }
    };

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
                    mTitleBar.getRightText().setText("发布");
                    mTitleBar.setOnRightClickListener(mPublishProfileListener);
                } else {
                    mTitleBar.getRightText().setText("编辑");
                    mTitleBar.setOnRightClickListener(mEditListener);
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
                    mTitleBar.getRightText().setText("编辑");
                    mTitleBar.setOnRightClickListener(mEditListener);
                    mEmpty.setVisibility(View.GONE);
                    mList.setVisibility(View.VISIBLE);
                }
            }
        });
        mTitleBar.setOnLeftClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        requestOrgProfile();
    }

    /**
     * 请求机构简介
     */
    private void requestOrgProfile() {
        Request request = Request.obtain(MeInterface.POST_ORG_PROFILE);
        request.put("customerId", UserManager.getInstance().getUser().id);
        request.setListener(new SimpleListener<Response<List<ResponseOrgProfile>>>() {

            @Override
            public void onResponseListener(Request r, Response<List<ResponseOrgProfile>> result) {
                if (result.success) {
                    if (result.data != null && !result.data.isEmpty()) {
                        mOrgProfile = result.data.get(0);
                        parseOrgProfile();
                    } else mEmpty.setVisibility(View.VISIBLE);
                } else mEmpty.setVisibility(View.VISIBLE);
            }
        });
        net(request);
    }

    /**
     * 解析机构简介
     */
    private void parseOrgProfile() {
        mDisplayAdapter.setData(mOrgProfile);
        mList.setAdapter(mDisplayAdapter);
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

    /**
     * 调接口发布机构简介
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
                        Request request = Request.obtain(MeInterface.POST_ORG_CHANGE_PROFILE);
                        request.putHeader("X-CustomToken", UserManager.getInstance().getToken());
                        if (mOrgProfile != null) request.put("id", mOrgProfile.id);
                        request.put("customerId", UserManager.getInstance().getUser().id);
                        request.put("intro", concatProfile());
                        return request;
                    }
                })
                .next(new NetAction<Response<ResponseOrgProfile>>() {

                    @Override
                    protected void executeAdapt(Response<ResponseOrgProfile> response, Request request) {
                        if (response.success) {
                            mOrgProfile = response.data;
                            requestOrgProfile();
                        } else N.showShort(OrgProfileActivity.this, "发布机构简介失败");
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