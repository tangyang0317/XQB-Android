package com.zhangju.xingquban.interestclassapp.ui.activity.find.liveradio;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.fastlib.widget.RoundImageView;
import com.zhangju.xingquban.R;
import com.zhangju.xingquban.interestclassapp.refactor.common.utils.ThirdPartyUtils;
import com.zhangju.xingquban.interestclassapp.refactor.user.User;
import com.zhangju.xingquban.interestclassapp.refactor.user.UserManager;
import com.zhangju.xingquban.interestclassapp.util.SpUtil;
import com.zhangju.xingquban.interestclassapp.util.UrlUtils;
import com.zhangju.xingquban.interestclassapp.util.click.NoDoubleClick;

import java.util.ArrayList;

import butterknife.ButterKnife;

/**
 * Created by lsh on 2016/11/8.
 *
 * @直播结束页面
 */
public class LiveExitActivity extends Activity implements View.OnClickListener {

    TextView mTvBack;     // 返回大厅
    TextView tv_live_exit_time;    // 直播时长
    TextView tv_live_exit_xqb;    // 兴趣币
    TextView tv_live_exit_audience_num;    // 观看人数
    RoundImageView imageViewHead;   // 头像

    CheckBox cb_live_share_SinaWeibo;
    CheckBox cb_live_share_WechatMoments;
    CheckBox cb_live_share_Wechat;
    CheckBox cb_live_share_QQ;
    CheckBox cb_live_share_QZone;

    private String mRoomsId;
    private String mCaseTimeStr;
    private int follows;
    private int mStdCoin;
    private Context mContext;


    /**
     * 分享操作
     */

    private String sharetitle = "为兴趣而生，为梦想而来";
    private String shareid = "";
    private String shareUrl = "http://my.xqban.com/admnxzcmr/rooms/share?id=" ;
    private String image = "";
    private ArrayList<String> mlist = new ArrayList<>();
    private String shareContent = "";
    private int shareType = 0;//1微信 2微信朋友圈 3qq 4空间  5微博

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_live_exit);
        ButterKnife.bind(this);
        mContext = this;
        initView();
        initData();

    }

    private void initData() {

        image = getIntent().getStringExtra("image");//分享图
        shareContent = getIntent().getStringExtra("shareContent");//分享内容
        mRoomsId = getIntent().getStringExtra("roomsId");//房间号
        mCaseTimeStr = getIntent().getStringExtra("caseTimeStr");//时长
        follows = getIntent().getIntExtra("follows", 0);//在线人数
        mStdCoin = getIntent().getIntExtra("stdCoin", 0);//兴趣币
        shareid=mRoomsId;

        tv_live_exit_time.setText("直播时长：" + mCaseTimeStr);
        tv_live_exit_xqb.setText(mStdCoin + "");
        tv_live_exit_audience_num.setText(follows + "");

    }

    private void initView() {
        mTvBack = (TextView) findViewById(R.id.tv_live_exit_back);

        cb_live_share_QZone = (CheckBox) findViewById(R.id.cb_live_share_QZone);
        cb_live_share_QQ = (CheckBox) findViewById(R.id.cb_live_share_QQ);
        cb_live_share_Wechat = (CheckBox) findViewById(R.id.cb_live_share_Wechat);
        cb_live_share_WechatMoments = (CheckBox) findViewById(R.id.cb_live_share_WechatMoments);
        cb_live_share_SinaWeibo = (CheckBox) findViewById(R.id.cb_live_share_SinaWeibo);


        tv_live_exit_time = (TextView) findViewById(R.id.tv_live_exit_time);
        tv_live_exit_xqb = (TextView) findViewById(R.id.tv_live_exit_xqb);
        tv_live_exit_audience_num = (TextView) findViewById(R.id.tv_live_exit_audience_num);
        imageViewHead = (RoundImageView) findViewById(R.id.iv_live_exit_avatar);

        mTvBack.setOnClickListener(this);
        cb_live_share_QZone.setOnClickListener(this);
        cb_live_share_QQ.setOnClickListener(this);
        cb_live_share_Wechat.setOnClickListener(this);
        cb_live_share_WechatMoments.setOnClickListener(this);
        cb_live_share_SinaWeibo.setOnClickListener(this);

        User user = UserManager.getInstance().getUser();
        String picture = user.picture;
        Glide.with(mContext).load(picture).placeholder(R.drawable.app_icon).dontAnimate().dontTransform().into(imageViewHead);


    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_live_exit_back:
               if (shareType==0){
                   finish();
               }else {
                   toShare();
               }
            case R.id.cb_live_share_SinaWeibo:
                shareType=5;
                break;
            case R.id.cb_live_share_WechatMoments:
                shareType=2;
                break;
            case R.id.cb_live_share_Wechat:
                shareType=1;
                break;
            case R.id.cb_live_share_QQ:
                shareType=3;
                break;
            case R.id.cb_live_share_QZone:
                shareType=4;
                break;
        }
    }

    /**
     * 分享处理
     */
    boolean isStartLive = false;

    //1微信 2微信朋友圈 3qq 4空间  5微博
    private void toShare() {
        isStartLive = true;
        switch (shareType) {

            case 1:
                ThirdPartyUtils.getInstance(LiveExitActivity.this).shareUrlToWechat(shareUrl+shareid, sharetitle, shareContent, image,false,false);

                break;
            case 2:
                ThirdPartyUtils.getInstance(LiveExitActivity.this).shareUrlToWechat(shareUrl+shareid, sharetitle, shareContent, image,false,true);

                break;
            case 3:
                ThirdPartyUtils.getInstance(LiveExitActivity.this).shareToQQ(LiveExitActivity.this, shareUrl+shareid, sharetitle, shareContent, image);

                break;
            case 4:
                ThirdPartyUtils.getInstance(LiveExitActivity.this).shareUrlToZone(LiveExitActivity.this, shareUrl+shareid, sharetitle, shareContent, mlist);

                break;
            case 5:
                ThirdPartyUtils.getInstance(LiveExitActivity.this).shareToWeibo(LiveExitActivity.this, sharetitle, image);

                break;
        }
    }


    @Override
    protected void onResume() {
        super.onResume();
        if (isStartLive) {
            finish();
        }
    }
}
