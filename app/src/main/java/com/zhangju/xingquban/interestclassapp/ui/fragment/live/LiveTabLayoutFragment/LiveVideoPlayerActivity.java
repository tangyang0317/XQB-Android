package com.zhangju.xingquban.interestclassapp.ui.fragment.live.LiveTabLayoutFragment;

import android.app.Dialog;
import android.content.Intent;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.TextView;

import com.alibaba.fastjson.JSONObject;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.RequestParams;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest;
import com.netease.neliveplayer.NELivePlayer;
import com.orhanobut.logger.Logger;
import com.tencent.connect.common.Constants;
import com.zhangju.xingquban.R;
import com.zhangju.xingquban.interestclassapp.adapter.baseadpter.OnListItemClickListener;
import com.zhangju.xingquban.interestclassapp.application.MyApp;
import com.zhangju.xingquban.interestclassapp.base.BaseActivity;
import com.zhangju.xingquban.interestclassapp.bean.LiveVideoLoveBean;
import com.zhangju.xingquban.interestclassapp.refactor.common.ShareUiListener;
import com.zhangju.xingquban.interestclassapp.refactor.common.utils.ThirdPartyUtils;
import com.zhangju.xingquban.interestclassapp.refactor.user.UserManager;
import com.zhangju.xingquban.interestclassapp.ui.fragment.find.SeekResource.AudioDetailActivity;
import com.zhangju.xingquban.interestclassapp.ui.fragment.home.HomeRecyclerViewData;
import com.zhangju.xingquban.interestclassapp.ui.fragment.live.VideoCommentActivity;
import com.zhangju.xingquban.interestclassapp.util.ToastUtil;
import com.zhangju.xingquban.interestclassapp.util.UrlUtils;
import com.zhangju.xingquban.interestclassapp.util.click.NoDoubleClick;
import com.zhangju.xingquban.interestclassapp.view.dialog.ShareDialog;
import com.zhangju.xingquban.interestclassapp.view.livestream.NEMediaController;
import com.zhangju.xingquban.interestclassapp.view.livestream.NEVideoView;
import com.zhangju.xingquban.interestclassapp.view.me.AlertDialogIOS;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

//视频播放器主页
public class LiveVideoPlayerActivity extends BaseActivity implements NEMediaController.VideoControllerCallBack {

    @BindView(R.id.view_watch_video)
    NEVideoView viewWatchVideo;
    @BindView(R.id.iv_video_play_black)
    ImageView mIvVideoPlayBlack;
    @BindView(R.id.iv_video_play_like)
    ImageView mIvVideoPlayLike;
    @BindView(R.id.iv_video_play_share)
    ImageView mIvVideoPlayShare;
    @BindView(R.id.rl_live_video_top)
    RelativeLayout rlLiveVideoTop;
    @BindView(R.id.buffering_prompt)
    LinearLayout bufferingPrompt;
    @BindView(R.id.frame)
    FrameLayout frame;

    private NEVideoView mVideoView;
    private NEMediaController mMediaController;
    private boolean mPauseInBackground = true;
    private SeekBar mProgress;
    private View mLoadingView;



    private String mId;
    private boolean mIsAddOp;
    private String mVideoUrl="";

    private ShareDialog shareDialog;
    private String title="回放";
    private String pic="";
    private String shareid="";
    private String shareUrl="http://my.xqban.com/admnxzcmr/user/h5/liveVdoShare?liveVdoId=";


    ArrayList<String> image=new ArrayList<String>();
//    http://my.xqban.com/admnxzcmr/user/h5/liveVdoShare?liveVdoId=489

    @Override
    public int getLayout() {
        return R.layout.activity_video_player;
    }

    @Override
    public void initView() {

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        ThirdPartyUtils.getInstance(LiveVideoPlayerActivity.this).mTencent.onActivityResultData(requestCode,resultCode,data,new ShareUiListener());

    }

    @Override
    public void initData() {
        mVideoUrl = getIntent().getStringExtra("url");
        mId = getIntent().getStringExtra("id");
        pic = getIntent().getStringExtra("pic");
        title = getIntent().getStringExtra("title");
        mIsAddOp = getIntent().getBooleanExtra("isfocus", false);
        shareid=mId;


        image.add(pic);
        initVideoView(mVideoUrl);
        initListener();

        if (mIsAddOp) { // 点过赞
            mIvVideoPlayLike.setImageResource(R.mipmap.icon_love_2);
        } else {
            mIvVideoPlayLike.setImageResource(R.mipmap.icon_love_white);
        }
        shareDialog=new ShareDialog(LiveVideoPlayerActivity.this,R.style.ActionSheetDialogStyle);

        shareDialog.QQZone(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                ThirdPartyUtils.getInstance(LiveVideoPlayerActivity.this).shareUrlToZone(LiveVideoPlayerActivity.this,shareUrl+shareid, title,"回放",image);

            }
        });

        shareDialog.qq(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                shareDialog.dismiss();
                ThirdPartyUtils.getInstance(LiveVideoPlayerActivity.this).shareToQQ(LiveVideoPlayerActivity.this,shareUrl+shareid,title,"回放",pic);
            }
        });
        shareDialog.wechat(new View.OnClickListener() {
            @Override
            public void onClick(View v) {  shareDialog.dismiss();
                ThirdPartyUtils.getInstance(LiveVideoPlayerActivity.this).shareUrlToWechat( shareUrl+shareid,title,"回放",pic,false,true);
            }
        });
        shareDialog.wechat_zone(new View.OnClickListener() {
            @Override
            public void onClick(View v) {  shareDialog.dismiss();
                ThirdPartyUtils.getInstance(LiveVideoPlayerActivity.this).shareUrlToWechat( shareUrl+shareid,title,"回放",pic,false,false);

            }
        });
        shareDialog.weibo(new View.OnClickListener() {
            @Override
            public void onClick(View v) {  shareDialog.dismiss();
                ThirdPartyUtils.getInstance(LiveVideoPlayerActivity.this).shareToWeibo( LiveVideoPlayerActivity.this,title,pic);

            }
        });
    }

    private void initVideoView(String url) {

        //  创建NEVideoView 和 NEMediaController 实例
        mVideoView = (NEVideoView) findViewById(R.id.view_watch_video);
        if (mVideoView != null) {
            mVideoView.setPlayerMode(true);
        }
        mMediaController = new NEMediaController(this);
        mMediaController.setControllerCallBack(this);
        //  将 NEMediaController 与 NEVideoView 进行绑定
        mVideoView.setMediaController(mMediaController);
        // 设置缓冲动画
        mLoadingView = findViewById(R.id.buffering_prompt);
        mVideoView.setBufferingIndicator(mLoadingView);

        //  设置缓冲策略低        延时和抗抖动两种模式，0为低延时，1为抗抖动
        //        mVideoView.setBufferStrategy(0);
        mVideoView.setBufferStrategy(NELivePlayer.NELPANTIJITTER); //点播抗抖动
        mVideoView.setHardwareDecoder(false); //是否开启硬件解码
        mVideoView.setPauseInBackground(mPauseInBackground);
        mVideoView.setMediaType("videoondemand");
        mVideoView.setVideoPath(url); // 拉流
        //        mVideoView.setVideoPath("http://video.xqban.com/Resources/2017-04-06/1491468839052_561.mp4"); // 拉流
        mVideoView.requestFocus();
        mVideoView.start();
        mVideoView.setOnPreparedListener(new NELivePlayer.OnPreparedListener() {
            @Override
            public void onPrepared(NELivePlayer neLivePlayer) {
                mLoadingView.setVisibility(View.GONE);
            }
        });
    }
    @Override
    public void onPause() {
        if (mPauseInBackground) {
            mVideoView.pause(); //锁屏时暂停}
        }
        super.onPause();
    }

    @Override
    protected void onDestroy() {
        mVideoView.release_resource();
        super.onDestroy();
    }

    @Override
    protected void onResume() {
        if (mPauseInBackground && !mVideoView.isPaused()) {
            mVideoView.start(); //锁屏打开后恢复播放
        }
        super.onResume();
    }
    public void initListener() {
        mIvVideoPlayBlack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        mIvVideoPlayLike.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                   addFocus();

            }
        });
        mIvVideoPlayShare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               shareDialog.show();
            }
        });

    }


    @Override
    public void onClick(View v) {
    }


    NEMediaController.OnShownListener mOnShowListener = new NEMediaController.OnShownListener() {

        @Override
        public void onShown() {
            mVideoView.invalidate();
        }
    };


    @Override
    public void onBackPressed() {
      showExitDialog();
    }


    //添加收藏
    private void addFocus() {
        HttpUtils httpUtils = new HttpUtils();
        String token = UserManager.getInstance().getToken();
        RequestParams params = new RequestParams();
        params.addBodyParameter("subjectId", mId);
        params.addBodyParameter("comtType", "liveVdo");
        params.addHeader("X-CustomToken", token);


        String url = UrlUtils.POST_RECORD_LIKE;
        httpUtils.send(HttpRequest.HttpMethod.POST, url, params,
                new RequestCallBack<String>() {
                    @Override
                    public void onSuccess(ResponseInfo<String> responseInfo) {
                        String json = responseInfo.result;
                        LiveVideoLoveBean bean = JSONObject.parseObject(json, LiveVideoLoveBean.class);
                        if (bean.isSuccess()){

                            mIsAddOp = !mIsAddOp;
                            if (mIsAddOp) { // 点过赞
                                mIvVideoPlayLike.setImageResource(R.mipmap.icon_love_2);
                            } else {
                                mIvVideoPlayLike.setImageResource(R.mipmap.icon_love_white);
                            }
                        }
                    }

                    @Override
                    public void onFailure(HttpException e, String s) {
                        ToastUtil.showToast(MyApp.instance, "请求服务器异常,请检查您的网络连接");
                    }
                });
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
    }




    //退出提示框
    private void showExitDialog() {
        new AlertDialogIOS(LiveVideoPlayerActivity.this).builder().setTitle("提示")
                .setMsg("确认退出观看?")
                .setPositiveButton("确认", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        mVideoView.release_resource();
                        finish();

                    }
                }).setNegativeButton("取消", new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        }).show();

    }


    @Override
    public void onCommentClick() {
        if (mPauseInBackground) {
            mVideoView.pause(); //锁屏时暂停}
        }
        mMediaController.hide();

        Intent intent = new Intent(LiveVideoPlayerActivity.this, VideoCommentActivity.class);
        intent.putExtra("id", mId);
        startActivity(intent);

    }
}
