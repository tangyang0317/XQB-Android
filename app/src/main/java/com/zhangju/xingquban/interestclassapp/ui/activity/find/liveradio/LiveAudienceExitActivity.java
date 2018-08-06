package com.zhangju.xingquban.interestclassapp.ui.activity.find.liveradio;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.zhangju.xingquban.R;

import butterknife.BindView;
import butterknife.ButterKnife;


/**
 * @Created by  liush on 2017/3/17.
 * @describe ${直播结束页面}
 */

public class LiveAudienceExitActivity extends Activity {
	@BindView(R.id.textView_title)
	TextView textViewTitle;
	@BindView(R.id.iv_live_exit_avatar)
	ImageView ivLiveExitAvatar;
	@BindView(R.id.tv_live_exit_time)
	TextView tvLiveExitTime;
	@BindView(R.id.tv_live_exit_audience_num)
	TextView tvLiveExitAudienceNum;
	@BindView(R.id.linearlayout_live_exit)
	LinearLayout linearlayoutLiveExit;
	@BindView(R.id.tv_live_exit_share)
	TextView tvLiveExitShare;
	@BindView(R.id.rl_live_exit_share)
	RelativeLayout rlLiveExitShare;
	@BindView(R.id.cb_live_share_SinaWeibo)
	CheckBox cbLiveShareSinaWeibo;
	@BindView(R.id.cb_live_share_WechatMoments)
	CheckBox cbLiveShareWechatMoments;
	@BindView(R.id.cb_live_share_Wechat)
	CheckBox cbLiveShareWechat;
	@BindView(R.id.cb_live_share_QQ)
	CheckBox cbLiveShareQQ;
	@BindView(R.id.cb_live_share_QZone)
	CheckBox cbLiveShareQZone;
	@BindView(R.id.ll_live_exit_share)
	LinearLayout llLiveExitShare;
	@BindView(R.id.tv_live_exit_back)
	TextView tvLiveExitBack;
	@Override
	protected void onCreate(@Nullable Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_live_exit_audience);
		ButterKnife.bind(this);

	}
}
