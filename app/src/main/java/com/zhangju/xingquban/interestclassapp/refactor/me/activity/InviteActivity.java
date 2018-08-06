package com.zhangju.xingquban.interestclassapp.refactor.me.activity;

import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.fastlib.annotation.Bind;
import com.fastlib.annotation.ContentView;
import com.fastlib.app.FastActivity;
import com.fastlib.net.DefaultDownload;
import com.fastlib.net.Request;
import com.fastlib.net.SimpleListener;
import com.fastlib.utils.N;
import com.fastlib.widget.TitleBar;
import com.zhangju.xingquban.R;
import com.zhangju.xingquban.interestclassapp.application.MyApp;
import com.zhangju.xingquban.interestclassapp.refactor.common.utils.ThirdPartyUtils;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by sgfb on 2017/10/26.
 * 邀请有奖界面
 */
@ContentView(R.layout.act_invite)
public class InviteActivity extends FastActivity{
    final String SHARE_URL = MyApp.URL+"/admnxzcmr/appversion/ls";
    final String SHARE_ICON_URL = MyApp.URL+"/rs/app/images/down_logo.png";
    final String SHARE_TITLE = "【专注兴趣特长班】找好老师好机构就靠他了";
    final String SHARE_CONTENT = "《兴趣班》，全国最大的兴趣教育公共服务平台，100多种科目，1000多类课程，线下、直播、网课，立体学习，让兴趣更加出彩！";
    File mShareLocalImage;
    @Bind(R.id.titleBar)
    TitleBar mTitleBar;
    @Bind(R.id.bg)
    ImageView mBg;
    @Bind(R.id.qrCode)
    ImageView mQrCode;

    @Override
    protected void alreadyPrepared() {
        mShareLocalImage=new File(getExternalCacheDir(),"shareImage.png");
        mTitleBar.setOnLeftClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        mQrCode.post(new Runnable() {
            @Override
            public void run() {
                float y=mQrCode.getY();
                int height=mQrCode.getHeight();
                FrameLayout.LayoutParams lp= (FrameLayout.LayoutParams) mBg.getLayoutParams();

                if(lp==null) lp=new FrameLayout.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT, (int) (y+height/2));
                lp.height= (int) (y+height/2);
                mBg.setLayoutParams(lp);
            }
        });
        try {
            mShareLocalImage.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Bind(R.id.wechat)
    private void shareToWeChat(){
        checkShareIcon(1);
    }

    @Bind(R.id.moments)
    private void shareToMoments(){
        checkShareIcon(2);
    }

    @Bind(R.id.weibo)
    private void shareToWeibo(){
        checkShareIcon(3);
    }

    @Bind(R.id.qq)
    private void shareToQQ(){
        ThirdPartyUtils.getInstance(InviteActivity.this).shareToQQ(InviteActivity.this,SHARE_URL,SHARE_TITLE,SHARE_CONTENT,SHARE_ICON_URL);
    }

    @Bind(R.id.qqZone)
    private void shareToQQZone(){
        checkShareIcon(4);
    }

    private void checkShareIcon(final int type){
        Request request=new Request("get",SHARE_ICON_URL);
        request.setHadRootAddress(true);
        request.setDownloadable(new DefaultDownload(mShareLocalImage));
        request.setListener(new SimpleListener<String>(){

            @Override
            public void onResponseListener(Request r, String result) {
                switch (type){
                    case 1:
                        ThirdPartyUtils.getInstance(InviteActivity.this).shareUrlToWechat(SHARE_URL,SHARE_TITLE,SHARE_CONTENT,mShareLocalImage.getAbsolutePath(),true);break;
                    case 2:
                        ThirdPartyUtils.getInstance(InviteActivity.this).shareUrlToWechat(SHARE_URL,SHARE_TITLE,SHARE_CONTENT,mShareLocalImage.getAbsolutePath(),false);break;
                    case 3:
                        ThirdPartyUtils.getInstance(InviteActivity.this).shareToWeibo(InviteActivity.this,SHARE_CONTENT,mShareLocalImage.getAbsolutePath());break;
                    case 4:
                        ArrayList<String> list=new ArrayList<>();
                        list.add(mShareLocalImage.getAbsolutePath());
                        ThirdPartyUtils.getInstance(InviteActivity.this).shareUrlToZone(InviteActivity.this,SHARE_URL,SHARE_TITLE,SHARE_CONTENT,list);
                        break;
                    default:break;
                }
            }

            @Override
            public void onErrorListener(Request r, String error) {
                super.onErrorListener(r, error);
                N.showShort(InviteActivity.this,"分享封面异常，分享失败");
            }
        });
        net(request);
    }
}