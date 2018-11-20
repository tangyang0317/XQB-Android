package com.zhangju.xingquban.interestclassapp.ui.activity.near;

import android.view.View;

import com.fastlib.annotation.Bind;
import com.fastlib.annotation.ContentView;
import com.fastlib.annotation.LocalData;
import com.fastlib.app.FastActivity;
import com.zhangju.xingquban.R;

import fm.jiecao.jcvideoplayer_lib.JCVideoPlayer;
import fm.jiecao.jcvideoplayer_lib.JCVideoPlayerStandard;


/**
 * 项目名称：MeijuApp
 * Created by Dr. Zhu on 2017/8/20.
 */
@ContentView(R.layout.shipinbofang)

public class ShipinBofangActivity extends FastActivity {
    public static final String ARG_STRING_URL = "video_url";
    public static final String ARG_STRING_NAME = "video_name";
    @LocalData(ARG_STRING_NAME)
    String name;
    @LocalData(ARG_STRING_URL)
    String url;
    @Bind(R.id.videoplayer)
    JCVideoPlayerStandard videoplayer;

    private int videoid;


    @Override
    protected void alreadyPrepared() {
//		videoplayer.setUp(url,JCVideoPlayerStandard.SCREEN_LAYOUT_NORMAL,name);
        videoplayer.startFullscreen(ShipinBofangActivity.this, JCVideoPlayerStandard.class, url, name);
        videoplayer.backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    @Override
    public void onBackPressed() {
        if (JCVideoPlayer.backPress()) {
            finish();
        } else {
            finish();
        }
        super.onBackPressed();

    }

    @Override
    protected void onPause() {
        super.onPause();
        JCVideoPlayer.releaseAllVideos();
    }
}
