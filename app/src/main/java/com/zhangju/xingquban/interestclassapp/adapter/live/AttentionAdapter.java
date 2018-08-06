package com.zhangju.xingquban.interestclassapp.adapter.live;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.zhangju.xingquban.R;
import com.zhangju.xingquban.interestclassapp.adapter.baseadpter.BaseRecycleViewAdapter;
import com.zhangju.xingquban.interestclassapp.bean.live.LiveAttentionListBean;
import com.zhangju.xingquban.interestclassapp.view.imageView.CustomRoundView;
import com.zhangju.xingquban.interestclassapp.view.imageView.TopOvelImageView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * create by hqf 2017/11/6
 * 直播关注列表
 */

public class AttentionAdapter extends BaseRecycleViewAdapter {

	private List<LiveAttentionListBean.AaDataBean> mAttentionList;

	public AttentionAdapter(Context c, List<LiveAttentionListBean.AaDataBean> mAttentionList) {
		super(c);
		this.mAttentionList = mAttentionList;
	}

	@Override
	public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
		return new AttentionViewHolder(resIdToView(parent, R.layout.attention_live_item));
	}

	@Override
	public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
		AttentionViewHolder attentionholder = (AttentionViewHolder) holder;
		attentionholder.onBind();

	}

	@Override
	public int getItemCount() {
		return mAttentionList.size();
	}

	class AttentionViewHolder extends RecyclerView.ViewHolder {

		int pos;
		@BindView(R.id.img_resource_small)
		ImageView imgResourceSmall;
		@BindView(R.id.tv_video_time)
		TextView tvVideoTime;
		@BindView(R.id.tv_resource_type)
		TextView tvResourceType;
		@BindView(R.id.tv_resource_title)
		TextView tvResourceTitle;
		@BindView(R.id.tv_resource_money)
		TextView tvResourceMoney;
		@BindView(R.id.tv_resource_looks)
		TextView tvResourceLooks;
		@BindView(R.id.img_author_head)
		CustomRoundView imgAuthorHead;
		@BindView(R.id.tv_author_name)
		TextView tvAuthorName;
		@BindView(R.id.img_video_comment)
		ImageView imgVideoComment;
		@BindView(R.id.tv_video_commentnum)
		TextView tvVideoCommentnum;
		@BindView(R.id.img_video_start)
		ImageView imgVideoStart;
		@BindView(R.id.tv_video_startnum)
		TextView tvVideoStartnum;

		public AttentionViewHolder(View itemView) {
			super(itemView);
			ButterKnife.bind(this, itemView);
		}

		void onBind() {
			pos = getLayoutPosition();
			registerOnItemClickListener(pos,itemView);
			LiveAttentionListBean.AaDataBean item = mAttentionList.get(pos);
			if (item != null) {
				String status = getPlayStatus(item.getStatus(), item.getAmount());
				String cityName = item.getCityName() == null ? "" : item.getCityName();

				LiveAttentionListBean.AaDataBean.ChatUserBean chatUser = item.getChatUser();
				if (chatUser != null) {
					String name = chatUser.getName() == null ? "" : chatUser.getName();
					String gender = chatUser.getGender() == null ? "" : chatUser.getGender();
					String icon = chatUser.getIcon() == null ? "" : chatUser.getIcon();

					tvResourceTitle.setText(name);
					Glide.with(c).load(icon).placeholder(R.mipmap.ic_launcher).crossFade().centerCrop().into(imgResourceSmall);
					Glide.with(c) .load(icon).placeholder(R.mipmap.ic_launcher).crossFade() .centerCrop().into(imgAuthorHead);
				}
				tvResourceType.setText(cityName);
				if (status.isEmpty()){
					tvResourceMoney.setVisibility(View.GONE);
				}else {
					tvResourceMoney.setVisibility(View.VISIBLE);
					tvResourceMoney.setText(status);
				}

			}


		}
	}

//	@Override
//	public View getView(final int position, View convertView, ViewGroup viewGroup) {
//		ViewHolder holder;
//		if (convertView == null) {
//			convertView = View.inflate(mContext, , null);
//			holder = new ViewHolder(convertView);
//			convertView.setTag(holder);
//		} else {
//			holder = (ViewHolder) convertView.getTag();
//		}
//		// 给holder控件设置内容
//		LiveAttentionListBean.AaDataBean item = getItem(position);
//		String name = item.getChatUser().getName();
//		String cityName = item.getCityName();
//		holder.tvResourceTitle.setText(name + "");
//		holder.tvResourceType.setText(cityName + "");
//		holder.tvVideoCommentnum.setText(item.getChatUser().getGender());
//		String icon = item.getChatUser().getIcon();
//		Glide.with(mContext)
//				.load(icon)
//				.placeholder(R.mipmap.ic_launcher)
//				.crossFade()    // 加载动画
//				.centerCrop()   // 裁剪居中
//				.into(holder.imgResourceSmall);
//		Glide.with(mContext)
//				.load(icon)
//				.placeholder(R.mipmap.ic_launcher)
//				.crossFade()    // 加载动画
//				.centerCrop()   // 裁剪居中
//				.into(holder.imgAuthorHead);
//		holder.imgResourceSmall.setOnClickListener(new View.OnClickListener() {
//			@Override
//			public void onComplete(View v) {
//				ToastUtil.showToast("你点击了第"+position+"header个");
//					 mRoomId = mData.get(position).getId();
//					mChatroomId = mData.get(position).getChatroomId();
//				// 进入直播间
//				initRoom(mRoomId);
//			}
//		});
//		int status = item.getStatus(); // 直播类型
//		String liveStatus = null;
//		switch (status) {
//			case 0:
//				liveStatus = "空闲";
//				break;
//			case 1:
//				if (item.getAmount() <= 0) {
//					liveStatus = "直播中";
//				} else {
//					liveStatus = "付费";
//				}
//
//				break;
//			case 2:
//				liveStatus = "禁用";
//				break;
//			case 3:
//				liveStatus = "直播录制";
//				break;
//			default:
//				liveStatus = null;
//				break;
//		}
//		if (liveStatus != null) {
//			holder.
//			holder.
//		} else {
//			holder.tvResourceMoney.setVisibility(View.GONE);
//		}
//
//		return convertView;
//	}
//
//	static class ViewHolder {
//		@BindView(R.id.img_resource_small)
//		ImageView imgResourceSmall;
//		@BindView(R.id.tv_video_time)
//		TextView tvVideoTime;
//		@BindView(R.id.tv_resource_type)
//		TextView tvResourceType;
//		@BindView(R.id.tv_resource_title)
//		TextView tvResourceTitle;
//		@BindView(R.id.tv_resource_money)
//		TextView tvResourceMoney;
//		@BindView(R.id.tv_resource_looks)
//		TextView tvResourceLooks;
//		@BindView(R.id.img_author_head)
//		CustomRoundView imgAuthorHead;
//		@BindView(R.id.tv_author_name)
//		TextView tvAuthorName;
//		@BindView(R.id.img_video_comment)
//		ImageView imgVideoComment;
//		@BindView(R.id.tv_video_commentnum)
//		TextView tvVideoCommentnum;
//		@BindView(R.id.img_video_start)
//		ImageView imgVideoStart;
//		@BindView(R.id.tv_video_startnum)
//		TextView tvVideoStartnum;
//
//		ViewHolder(View view) {
//			ButterKnife.bind(this, view);
//		}
//	}
//	private void initRoom(String roomId) {
//		HttpUtils httpUtils = new HttpUtils();
//		RequestParams params = new RequestParams();
//		params.addHeader("X-CustomToken", CONTANCE.loginUserResponse.getAaData().getToken());
//		params.addBodyParameter("id", roomId);
//		String url = UrlUtils.URL_INITROOM;
//		httpUtils.send(HttpRequest.HttpMethod.POST,
//				url,
//				params,
//				new RequestCallBack<String>() {
//
//					@Override
//					public void onSuccess(ResponseInfo<String> responseInfo) {
//						String json = responseInfo.result;
//						WatchLiveBean bean = JSONObject.parseObject(json, WatchLiveBean.class);
//						if (bean.getAaData() == null) {
//							ToastUtil.showToast(MyApp.instance, "请求服务器异常,请检查您的登录状态");
//							return;
//						} else if (bean.getAaData().getChannels() == null || bean.getAaData().getChatUser() == null) {
//							ToastUtil.showToast(MyApp.instance, "请求服务器异常,请检查您的登录状态");
//							return;
//						}
//						boolean isPay = bean.getAttachData().isIsPay(); // 是否付费直播
//						String rtmpPullUrl = bean.getAaData().getChannels().getRtmpPullUrl();
//						String appKey = bean.getAttachData().getAppKey();
//						String chatToken = bean.getAaData().getChatUser().getToken();
//						String accid = bean.getAaData().getChatUser().getAccid();
//						String followes = bean.getAaData().getChatUser().getFollowes(); // 当前用户的关注列表
//						Intent intent = null;
//						if (isPay) {
//							intent = new Intent(MyApp.instance, LiveWatchActivity.class);
//						} else {
//						/*	float roomPrice = bean.getAttachData().getRoomPrice();
//							double seeBalances = bean.getAttachData().getSeeBalances(); // 观看者的余额
//							intent = new Intent(MyApp.instance, LivePayActivity.class);
//							intent.putExtra("roomPrice", roomPrice);
//							intent.putExtra("seeBalances", seeBalances);*/
//						}
//						String stdCoin = bean.getAaData().getStdCoin();    //用户兴趣币余额
//						intent.putExtra("stdCoin", stdCoin);
//						intent.putExtra("chatToken", chatToken);
//						intent.putExtra("accid", accid);
//						intent.putExtra("appKey", appKey);
//						intent.putExtra("rtmpPullUrl", rtmpPullUrl);
//						intent.putExtra("chatroomId", mChatroomId);
//						intent.putExtra("mToken", CONTANCE.loginUserResponse.getAaData().getToken());
//						intent.putExtra("myFollowes", followes);
//						intent.putExtra("roomId", mRoomId);
//						mContext.startActivity(intent);
//					}
//
//					@Override
//					public void onFailure(HttpException e, String s) {
//						ToastUtil.showToast(MyApp.instance, "请求1服务器异常,请检查您的网络连接");
//					}
//				});
//	}

	//视频类型区分
	private String getPlayStatus(int status, float price) {
		if (status == 0) {
			return "空闲";
		} else if (status == 1) {
			if (price <= 0) {
				return "直播中";
			} else {
				return "付费";
			}

		} else if (status == 2) {
			return "禁用";
		} else if (status == 3) {
			return "直播录制";
		}
		return "";
	}
}