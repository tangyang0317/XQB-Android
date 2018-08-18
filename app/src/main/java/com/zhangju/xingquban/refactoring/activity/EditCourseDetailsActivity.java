package com.zhangju.xingquban.refactoring.activity;

import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.EditText;

import com.fastlib.annotation.Bind;
import com.fastlib.annotation.ContentView;
import com.fastlib.app.FastActivity;
import com.fastlib.app.PhotoResultListener;
import com.fastlib.widget.TitleBar;
import com.zhangju.xingquban.R;
import com.zhangju.xingquban.interestclassapp.refactor.me.activity.publish_active.AddFeatureTextActivity;
import com.zhangju.xingquban.interestclassapp.refactor.me.adapter.OrgProfileDisplayAdapter;
import com.zhangju.xingquban.interestclassapp.refactor.me.adapter.PublishActiveFeatureAdapter;
import com.zhangju.xingquban.interestclassapp.refactor.me.bean.PublishActiveFeature;
import com.zhangju.xingquban.interestclassapp.refactor.me.bean.ResponseOrgProfile;
import com.zhangju.xingquban.interestclassapp.refactor.me.fragment.publish_active.AddFeatureDialog;

import java.util.List;

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

    //发布机构简介点击事件
    private View.OnClickListener mPublishProfileListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
        }
    };
    //编辑机构简介点击事件
    private View.OnClickListener mEditListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
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
                    titleBar.getRightText().setText("发布");
                    titleBar.setOnRightClickListener(mPublishProfileListener);
                } else {
                    titleBar.getRightText().setText("编辑");
                    titleBar.setOnRightClickListener(mEditListener);
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
                    titleBar.getRightText().setText("编辑");
                    titleBar.setOnRightClickListener(mEditListener);
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

        mEmpty.setVisibility(View.VISIBLE);

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
