package com.zhangju.xingquban.interestclassapp.ui.fragment.me.Institutions.spkc;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.MediaController;
import android.widget.VideoView;

import com.zhangju.xingquban.R;
import com.zhangju.xingquban.interestclassapp.base.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import fm.jiecao.jcvideoplayer_lib.JCVideoPlayer;
import fm.jiecao.jcvideoplayer_lib.JCVideoPlayerStandard;

public class LiveVideo extends BaseActivity {

    @BindView(R.id.videoView)
    VideoView videoView;
    @BindView(R.id.mediaController)
    MediaController mediaController;
    @BindView(R.id.videoplayer)
    JCVideoPlayerStandard videoplayer;

    @Override
    public int getLayout() {
        return R.layout.activity_live_video;
    }

    @Override
    public void initView() {
        Intent intent = getIntent();
        String video = intent.getStringExtra("video");
        videoplayer.setUp(video, JCVideoPlayerStandard.SCREEN_LAYOUT_NORMAL);
        videoplayer.backButton.setVisibility(View.GONE);
//        videoplayer.batteryTimeLayout.setVisibility(View.GONE);
//        videoplayer.thumbImageView
//        videoView.setMediaController(mediaController);
//        videoView.setVideoURI(Uri.parse(video));
//        videoView.start();
    }

    @Override
    public void initData() {

    }

    @Override
    public void initListener() {
    }

    @Override
    public void onClick(View v) {

    }

    @Override
    public void onBackPressed() {
        if (JCVideoPlayer.backPress()) {
            return;
        }
        super.onBackPressed();
    }

    @Override
    protected void onPause() {
        super.onPause();
        JCVideoPlayer.releaseAllVideos();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
    }
}
