package com.zhangju.xingquban.interestclassapp.ui.activity.find.liveradio;

import android.os.Bundle;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.fastlib.annotation.ContentView;
import com.fastlib.app.FastActivity;
import com.fastlib.widget.RoundImageView;
import com.zhangju.xingquban.R;
import com.zhangju.xingquban.interestclassapp.refactor.user.User;
import com.zhangju.xingquban.interestclassapp.refactor.user.UserManager;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

@ContentView(R.layout.activity_live_look_exit)
public class LiveLookExitActivity extends FastActivity {

    @BindView(R.id.image_head)
    RoundImageView imageHead;
    @BindView(R.id._tv_name)
    TextView TvName;
    @BindView(R.id._tv_people_num)
    TextView TvPeopleNum;
    @BindView(R.id.tv_close)
    TextView tvClose;

    @Override
    protected void alreadyPrepared() {
        String onlineUserCount = getIntent().getStringExtra("onlineUserCount");
        String icon = getIntent().getStringExtra("icon");
        String roomName = getIntent().getStringExtra("roomName");


        Glide.with(LiveLookExitActivity.this).load(icon).placeholder(R.drawable.app_icon).dontAnimate().dontTransform().into(imageHead);
        TvName.setText(roomName);
        TvPeopleNum.setText("观看人数：" + onlineUserCount);


    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.tv_close)
    public void onViewClicked() {
        finish();
    }
}
