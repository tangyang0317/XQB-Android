package com.zhangju.xingquban.interestclassapp.refactor.me.fragment.publish_active;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.fastlib.annotation.Bind;
import com.fastlib.annotation.ContentView;
import com.fastlib.annotation.Event;
import com.fastlib.app.EventObserver;
import com.fastlib.app.FastFragment;
import com.fastlib.utils.N;
import com.zhangju.xingquban.R;
import com.zhangju.xingquban.interestclassapp.refactor.me.activity.publish_active.AddTripActivity;
import com.zhangju.xingquban.interestclassapp.refactor.me.adapter.PublishActiveTripAdapter;
import com.zhangju.xingquban.interestclassapp.refactor.me.bean.EventPublishDataEdited;
import com.zhangju.xingquban.interestclassapp.refactor.me.bean.EventPublishProgressNext;
import com.zhangju.xingquban.interestclassapp.refactor.me.bean.PublishActiveInfo;
import com.zhangju.xingquban.interestclassapp.refactor.me.bean.PublishActiveTrip;

/**
 * Created by sgfb on 2017/11/1.
 * 发布活动行程页
 */
@ContentView(R.layout.frag_publish_active_features)
public class PublishActiveTripFragment extends FastFragment{
    public static final int REQ_ADD_TRIP=1;

    @Bind(R.id.emptyView)
    View mEmptyView;
    @Bind(R.id.image)
    ImageView mImage;
    @Bind(R.id.title)
    TextView mTitle;
    @Bind(R.id.description)
    TextView mDescription;
    @Bind(R.id.list)
    RecyclerView mList;
    PublishActiveTripAdapter mAdapter;

    @Override
    protected void alreadyPrepared() {
        mTitle.setText("编辑活动行程");
        mDescription.setText("为活动添加一段有意思的活动安排");
        mImage.setImageResource(R.mipmap.me_activity_fbhdthree_bg);
        mList.setAdapter(mAdapter=new PublishActiveTripAdapter(this));
        mAdapter.registerAdapterDataObserver(new RecyclerView.AdapterDataObserver() {
            @Override
            public void onChanged() {
                super.onChanged();
                if(mAdapter.getData()==null||mAdapter.getData().isEmpty())
                    mEmptyView.setVisibility(View.VISIBLE);
                else mEmptyView.setVisibility(View.GONE);
            }

            @Override
            public void onItemRangeRemoved(int positionStart, int itemCount) {
                super.onItemRangeRemoved(positionStart, itemCount);
                if(mAdapter.getData()==null||mAdapter.getData().isEmpty())
                    mEmptyView.setVisibility(View.VISIBLE);
            }
        });
    }

    @Bind(R.id.add)
    private void add(){
        startActivityForResult(new Intent(getContext(),AddTripActivity.class),REQ_ADD_TRIP);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode!=Activity.RESULT_OK) return;
        if(requestCode==REQ_ADD_TRIP){
            PublishActiveTrip trip=new PublishActiveTrip();
            trip.title=data.getStringExtra(AddTripActivity.ARG_RES_TITLE);
            trip.content=data.getStringExtra(AddTripActivity.ARG_RES_CONTENT);
            mAdapter.addData(trip);
        }
    }

    @Event
    private void eNextStep(EventPublishProgressNext event){
        if(event.getmCla()==getClass()){
            if(mAdapter.getData()==null||mAdapter.getData().isEmpty()){
                N.showShort(getContext(),"最少填写一个活动行程");
                return;
            }
            for(PublishActiveTrip trip:mAdapter.getData()){
                if(TextUtils.isEmpty(trip.time)){
                    N.showShort(getContext(),"所有行程都必须要填写时间");
                    return;
                }
            }
            PublishActiveInfo info=new PublishActiveInfo();
            info.schedules=new PublishActiveInfo.ActiveSchedules();
            info.schedules.schedules=mAdapter.getData();
            EventObserver.getInstance().sendEvent(getContext(),new EventPublishDataEdited(info));
        }
    }
}
