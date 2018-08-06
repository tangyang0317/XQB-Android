package com.zhangju.xingquban.interestclassapp.refactor.me.activity.publish_active;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.util.Pair;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.fastlib.adapter.CommonFragmentViewPagerAdapter;
import com.fastlib.annotation.Bind;
import com.fastlib.annotation.ContentView;
import com.fastlib.annotation.Event;
import com.fastlib.app.EventObserver;
import com.fastlib.app.FastActivity;
import com.fastlib.widget.TitleBar;
import com.zhangju.xingquban.R;
import com.zhangju.xingquban.interestclassapp.refactor.me.adapter.PublishActiveProgressAdapter;
import com.zhangju.xingquban.interestclassapp.refactor.me.adapter.PublishActiveProgressAdapter2;
import com.zhangju.xingquban.interestclassapp.refactor.me.bean.EventPublishDataEdited;
import com.zhangju.xingquban.interestclassapp.refactor.me.bean.EventPublishProgressNext;
import com.zhangju.xingquban.interestclassapp.refactor.me.bean.PublishActiveInfo;
import com.zhangju.xingquban.interestclassapp.refactor.me.fragment.publish_active.PublishActiveBaseInfoFragment;
import com.zhangju.xingquban.interestclassapp.refactor.me.fragment.publish_active.PublishActiveFeatureFragment;
import com.zhangju.xingquban.interestclassapp.refactor.me.fragment.publish_active.PublishActiveNoteFragment;
import com.zhangju.xingquban.interestclassapp.refactor.me.fragment.publish_active.PublishActiveTripFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by sgfb on 2017/11/2.
 * 发布活动
 */
@ContentView(R.layout.act_publish_active)
public class PublishActiveActivity extends FastActivity{
    final int REQ_ACTIVE_INFO=20;
    final String[] ACTIVE_PROGRESS={"基本信息","活动特色","活动行程","活动须知"};
    @Bind(R.id.titleBar)
    TitleBar mTitleBar;
    @Bind(R.id.horizontalList)
    RecyclerView mHorizontalList;
    @Bind(R.id.viewPager)
    ViewPager mViewPager;
    PublishActiveProgressAdapter2 mAdapter;
    PublishActiveInfo mPublishActiveInfo=new PublishActiveInfo();
    List<String> progressTitles=new ArrayList<>();

    @Override
    protected void alreadyPrepared(){
        for(String progressTitle:ACTIVE_PROGRESS)
            progressTitles.add(progressTitle);
        LinearLayoutManager llm=new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false){
            @Override
            public boolean canScrollHorizontally() {
                return false;
            }
        };
        mHorizontalList.setLayoutManager(llm);
        mHorizontalList.setAdapter(mAdapter=new PublishActiveProgressAdapter2(this));
        mAdapter.setData(progressTitles,0);

        List<Pair<String,Fragment>> pages=new ArrayList<>();
        pages.add(Pair.<String, Fragment>create(ACTIVE_PROGRESS[0],new PublishActiveBaseInfoFragment()));
        pages.add(Pair.<String, Fragment>create(ACTIVE_PROGRESS[1],new PublishActiveFeatureFragment()));
        pages.add(Pair.<String, Fragment>create(ACTIVE_PROGRESS[2],new PublishActiveTripFragment()));
        pages.add(Pair.<String, Fragment>create(ACTIVE_PROGRESS[3],new PublishActiveNoteFragment()));
        mViewPager.setOffscreenPageLimit(4);
        mViewPager.setAdapter(new CommonFragmentViewPagerAdapter(getSupportFragmentManager(),pages));
        mTitleBar.setOnLeftClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        mTitleBar.setOnRightClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (mViewPager.getCurrentItem()){
                    case 0:
                        EventObserver.getInstance().sendEvent(PublishActiveActivity.this,new EventPublishProgressNext(PublishActiveBaseInfoFragment.class));
                        break;
                    case 1:
                        EventObserver.getInstance().sendEvent(PublishActiveActivity.this,new EventPublishProgressNext(PublishActiveFeatureFragment.class));
                        break;
                    case 2:
                        EventObserver.getInstance().sendEvent(PublishActiveActivity.this,new EventPublishProgressNext(PublishActiveTripFragment.class));
                        break;
                    case 3:
                        EventObserver.getInstance().sendEvent(PublishActiveActivity.this,new EventPublishProgressNext(PublishActiveNoteFragment.class));
                        break;
                    default:
                        startActivity(PublishActiveDoneActivity.class);
                        break;
                }
            }
        });
    }

    @Event
    private void ePublishDataEdited(EventPublishDataEdited event){
        PublishActiveInfo info=event.getmInfo();
        if(info.baseInfo!=null) mPublishActiveInfo.baseInfo=info.baseInfo;
        if(info.featured!=null) mPublishActiveInfo.featured=info.featured;
        if(info.schedules!=null) mPublishActiveInfo.schedules=info.schedules;
        if(info.notes!=null) mPublishActiveInfo.notes=info.notes;
        nextStep();
    }

    /**
     * 下一步
     */
    private void nextStep(){
        if(mViewPager.getCurrentItem()==mViewPager.getChildCount()-1){
            Intent intent=new Intent(this,ActiveLastActivity.class);
            intent.putExtra(ActiveLastActivity.ARG_SER_ACTIVE_INFO,mPublishActiveInfo);
            startActivityForResult(intent,REQ_ACTIVE_INFO);
            return;
        }
        mViewPager.setCurrentItem(mViewPager.getCurrentItem()+1,false);
        mAdapter.setData(progressTitles,mViewPager.getCurrentItem());
        mHorizontalList.scrollToPosition(mViewPager.getCurrentItem());
    }

    @Override
    public void onBackPressed() {
        if(mViewPager.getCurrentItem()==0) super.onBackPressed();
        else {
            mViewPager.setCurrentItem(mViewPager.getCurrentItem()-1,false);
            mAdapter.setData(progressTitles,mViewPager.getCurrentItem());
            mHorizontalList.scrollToPosition(mViewPager.getCurrentItem()-1);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode==RESULT_OK&&requestCode==REQ_ACTIVE_INFO) finish();
    }
}