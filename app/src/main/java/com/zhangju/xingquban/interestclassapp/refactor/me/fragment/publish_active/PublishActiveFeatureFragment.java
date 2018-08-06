package com.zhangju.xingquban.interestclassapp.refactor.me.fragment.publish_active;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.fastlib.annotation.Bind;
import com.fastlib.annotation.ContentView;
import com.fastlib.annotation.Event;
import com.fastlib.app.EventObserver;
import com.fastlib.app.FastFragment;
import com.fastlib.app.PhotoResultListener;
import com.fastlib.utils.N;
import com.zhangju.xingquban.R;
import com.zhangju.xingquban.interestclassapp.refactor.me.activity.publish_active.AddFeatureTextActivity;
import com.zhangju.xingquban.interestclassapp.refactor.me.adapter.PublishActiveFeatureAdapter;
import com.zhangju.xingquban.interestclassapp.refactor.me.bean.EventPublishDataEdited;
import com.zhangju.xingquban.interestclassapp.refactor.me.bean.EventPublishProgressNext;
import com.zhangju.xingquban.interestclassapp.refactor.me.bean.PublishActiveFeature;
import com.zhangju.xingquban.interestclassapp.refactor.me.bean.PublishActiveInfo;

/**
 * Created by sgfb on 2017/11/1.
 * 发布活动特色界面
 */
@ContentView(R.layout.frag_publish_active_features)
public class PublishActiveFeatureFragment extends FastFragment{

    @Bind(R.id.emptyView)
    View mEmptyView;
    @Bind(R.id.list)
    RecyclerView mList;
    PublishActiveFeatureAdapter mAdapter;

    @Override
    protected void alreadyPrepared() {
        mList.setAdapter(mAdapter=new PublishActiveFeatureAdapter(this));
        mAdapter.registerAdapterDataObserver(new RecyclerView.AdapterDataObserver() {
            @Override
            public void onChanged() {
                super.onChanged();
                if(mAdapter.getData()==null||mAdapter.getData().isEmpty())
                    mEmptyView.setVisibility(View.VISIBLE);
                else mEmptyView.setVisibility(View.GONE);
            }
        });
    }

    @Bind(R.id.add)
    private void addFeature(){
        AddFeatureDialog.getInstance((AppCompatActivity) getActivity()).show(0,this, new PhotoResultListener() {
            @Override
            public void onPhotoResult(String path) {
                PublishActiveFeature feature=new PublishActiveFeature();
                feature.type=PublishActiveFeature.TYPE_IMAGE;
                feature.content=path;
                mAdapter.addData(feature);
            }
        });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode!= Activity.RESULT_OK) return;
        if(AddFeatureDialog.REQ_ADD_FEATURE_TEXT==requestCode){
            PublishActiveFeature feature=new PublishActiveFeature();
            feature.type=PublishActiveFeature.TYPE_TEXT;
            feature.content=data.getStringExtra(AddFeatureTextActivity.ARG_RES_STR_TEXT);
            mAdapter.addData(feature);
        }
    }

    @Event
    private void eCommit(EventPublishProgressNext event){
        if(event.getmCla()==getClass()){
            if(mAdapter.getData()==null||mAdapter.getData().isEmpty()){
                N.showShort(getContext(),"最少填写一个活动特色");
                return;
            }
            PublishActiveInfo info=new PublishActiveInfo();
            info.featured=new PublishActiveInfo.ActiveFeatured();
            info.featured.featured=mAdapter.getData();
            EventObserver.getInstance().sendEvent(getContext(),new EventPublishDataEdited(info));
        }
    }
}