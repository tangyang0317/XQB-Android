package com.zhangju.xingquban.interestclassapp.refactor.me.activity;

import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.fastlib.annotation.Bind;
import com.fastlib.annotation.ContentView;
import com.fastlib.annotation.Event;
import com.fastlib.app.FastActivity;
import com.fastlib.net.Request;
import com.fastlib.net.SimpleListener;
import com.fastlib.utils.N;
import com.fastlib.utils.Utils;
import com.fastlib.widget.TitleBar;
import com.google.gson.Gson;
import com.zhangju.xingquban.R;
import com.zhangju.xingquban.interestclassapp.refactor.common.bean.EventRefresh;
import com.zhangju.xingquban.interestclassapp.refactor.common.bean.Response;
import com.zhangju.xingquban.interestclassapp.refactor.me.bean.MeInterface;
import com.zhangju.xingquban.interestclassapp.refactor.me.bean.ResponseMyLiveRoom;

import java.util.Locale;

/**
 * Created by sgfb on 2017/11/8.
 * 我的直播间
 */
@ContentView(R.layout.act_my_live_room)
public class MyLiveRoomActivity extends FastActivity{
    @Bind(R.id.titleBar)
    TitleBar mTitleBar;
    @Bind(R.id.avatar)
    ImageView mAvatar;
    @Bind(R.id.name)
    TextView mName;
    @Bind(R.id.id)
    TextView mId;
    @Bind(R.id.focusCount)
    TextView mFocusCount;
    @Bind(R.id.thumbCount)
    TextView mThumbCount;
    @Bind(R.id.fansCount)
    TextView mFansCount;

    @Override
    protected void alreadyPrepared(){
        mTitleBar.getTitle().setTextColor(getResources().getColor(android.R.color.white));
        mTitleBar.getLeftIcon().setImageDrawable(Utils.tintDrawable(getResources().getDrawable(R.drawable.home_city_back),getResources().getColor(android.R.color.white)));
        mTitleBar.setOnLeftClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        requestLiveRoomInfo();
    }

    /**
     * 获取直播间信息
     */
    private void requestLiveRoomInfo(){
        Request request=Request.obtain(MeInterface.POST_MY_LIVE_ROOM);
        request.setListener(new SimpleListener<Response<ResponseMyLiveRoom>>(){

            @Override
            public void onResponseListener(Request r, Response<ResponseMyLiveRoom> result) {
                if(result.success){
                    ResponseMyLiveRoom data=result.data;
                    mName.setText(data.name);
                    mName.setCompoundDrawablesRelativeWithIntrinsicBounds(0,0,data.gender==1?R.drawable.home_teacher_gender_male:R.drawable.home_teacher_gender_female,0);
                    Glide.with(MyLiveRoomActivity.this).load(data.icon).into(mAvatar);
                    if(!TextUtils.isEmpty(result.data.ex)){
                        ResponseMyLiveRoom.Extra extra=new Gson().fromJson(data.ex,ResponseMyLiveRoom.Extra.class);
                        mId.setText(String.format(Locale.getDefault(),"ID:%s",extra.id));
                        mFocusCount.setText(String.format(Locale.getDefault(),"%d\n关注",extra.fllows));
                        mFansCount.setText(String.format(Locale.getDefault(),"%d\n粉丝",extra.fans));
                        mThumbCount.setText(String.format(Locale.getDefault(),"%d\n点赞",extra.likes));
                    }
                }
                else N.showShort(MyLiveRoomActivity.this,"获取信息失败");
            }
        });
        net(request);
    }

    /**
     * 粉丝列表
     */
    @Bind(R.id.fansCount)
    private void openFansList(){
        startActivity(MyFansActivity.class);
    }

    /**
     * 关注列表
     */
    @Bind(R.id.focusCount)
    private void openFocusUserList(){
        startActivity(FocusUserActivity.class);
    }

    /**
     * 粉丝贡献列表
     */
    @Bind(R.id.fansReward)
    private void fansReward(){
        startActivity(FansRankActivity.class);
    }

    /**
     * 发布列表
     */
    @Bind(R.id.publishHistory)
    private void publishHistory(){
        startActivity(LiveroomPublishHistoryActivity.class);
    }

    @Bind(R.id.level)
    private void level(){
        N.showShort(this,"敬请期待");
    }

    /**
     * 我的直播间设置
     */
    @Bind(R.id.livingroomSettings)
    private void openLivingroomSettings(){
        startActivity(LiveroomSettingsActivity.class);
    }

    @Event
    private void eRefresh(EventRefresh event){
        if(event.getmTargetClass()==getClass())
            requestLiveRoomInfo();
    }
}