/*
 * 官网地站:http://www.mob.com
 * 技术支持QQ: 4006852216
 * 官方微信:ShareSDK   （如果发布新版本的话，我们将会第一时间通过微信将版本更新内容推送给您。如果使用过程中有任何问题，也可以通过微信与我们取得联系，我们将会在24小时内给予回复）
 *
 * Copyright (c) 2013年 mob.com. All rights reserved.
 */

package com.zhangju.xingquban.wxapi;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Toast;

import com.fastlib.app.EventObserver;
import com.fastlib.net.Request;
import com.fastlib.net.SimpleListener;
import com.fastlib.utils.N;
import com.tencent.mm.sdk.modelbase.BaseReq;
import com.tencent.mm.sdk.modelbase.BaseResp;
import com.tencent.mm.sdk.modelmsg.SendAuth;
import com.tencent.mm.sdk.openapi.IWXAPIEventHandler;
import com.zhangju.xingquban.interestclassapp.refactor.common.bean.EventWechatLogin;
import com.zhangju.xingquban.interestclassapp.refactor.common.bean.ResponseWechatLogin;
import com.zhangju.xingquban.interestclassapp.refactor.common.utils.ThirdPartyUtils;

import cn.sharesdk.wechat.utils.WXAppExtendObject;
import cn.sharesdk.wechat.utils.WXMediaMessage;
import cn.sharesdk.wechat.utils.WechatHandlerActivity;

/* 微信客户端回调activity示例*/

public class WXEntryActivity extends WechatHandlerActivity implements IWXAPIEventHandler{
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		ThirdPartyUtils.getInstance(this).mWxAPI.handleIntent(getIntent(),this);
	}

	/*
	 * 处理微信发出的向第三方应用请求app message
	 * <p>
	 * 在微信客户端中的聊天页面有“添加工具”，可以将本应用的图标添加到其中
	 * 此后点击图标，下面的代码会被执行。Demo仅仅只是打开自己而已，但你可
	 * 做点其他的事情，包括根本不打开任何页面
*/
	public void onGetMessageFromWXReq(WXMediaMessage msg) {
		Intent iLaunchMyself = getPackageManager().getLaunchIntentForPackage(getPackageName());
		startActivity(iLaunchMyself);
	}

/*
	 * 处理微信向第三方应用发起的消息
	 * <p>
	 * 此处用来接收从微信发送过来的消息，比方说本demo在wechatpage里面分享
	 * 应用时可以不分享应用文件，而分享一段应用的自定义信息。接受方的微信
	 * 客户端会通过这个方法，将这个信息发送回接收方手机上的本demo中，当作
	 * 回调。
	 * <p>
	 * 本Demo只是将信息展示出来，但你可做点其他的事情，而不仅仅只是Toast
*/

	public void onShowMessageFromWXReq(WXMediaMessage msg){
		if (msg != null && msg.mediaObject != null
				&& (msg.mediaObject instanceof WXAppExtendObject)) {
			WXAppExtendObject obj = (WXAppExtendObject) msg.mediaObject;
			System.out.println(msg.description);
			Toast.makeText(this, obj.extInfo, Toast.LENGTH_SHORT).show();
		}
	}


	@Override
	public void onReq(BaseReq baseReq) {

	}

	@Override
	public void onResp(BaseResp baseResp) {
		System.out.println("location");
		if(baseResp instanceof SendAuth.Resp){
			SendAuth.Resp response= (SendAuth.Resp) baseResp;
			if(response.errCode==0)
				requestAccessToken(response.code);
			else if(response.errCode==-4){
				N.showShort(this,"拒绝了微信授权");
			}
			else if(response.errCode==-2){
				N.showShort(this,"取消微信登录");
			}
			else
				N.showShort(this,"一个未知的微信登录错误");
		}
	}

	/**
	 * 请求AccessToken
	 */
	private void requestAccessToken(String code){
		Request request=new Request("get","https://api.weixin.qq.com/sns/oauth2/access_token");
		request.setHadRootAddress(true);
		request.put("appid",ThirdPartyUtils.WX_APPID);
		request.put("secret",ThirdPartyUtils.WX_SECRET);
		request.put("code",code);
		request.put("grant_type","authorization_code");
		request.setListener(new SimpleListener<ResponseWechatLogin>(){

			@Override
			public void onResponseListener(Request r, ResponseWechatLogin result) {
				if(TextUtils.isEmpty(result.errmsg))
					EventObserver.getInstance().sendEvent(WXEntryActivity.this,new EventWechatLogin(result.openid,result.access_token));
				else N.showShort(WXEntryActivity.this,result.errmsg);
			}
		});
		request.start();
	}
}
