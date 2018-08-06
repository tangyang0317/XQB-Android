package com.zhangju.xingquban.interestclassapp.ui.fragment.live.LiveTabLayoutFragment;

import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;

import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.zhangju.xingquban.R;
import com.zhangju.xingquban.interestclassapp.base.BaseFragment;
import com.zhangju.xingquban.interestclassapp.bean.live.LiveAttentionListBean;
import com.zhangju.xingquban.interestclassapp.ui.activity.find.liveradio.StartLiveActivity;
import com.zhangju.xingquban.interestclassapp.ui.adapter.live.AttentionAdapter;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.OnClick;

public class LiveTabArt extends BaseFragment  implements AdapterView.OnItemClickListener{


	@BindView(R.id.btn_live_jump)
	ImageView btnLiveJump;
	@BindView(R.id.all_follow)
	PullToRefreshListView pListView;
	private AttentionAdapter mAdapter;
	private ArrayList<LiveAttentionListBean.AaDataBean> mAaDataList = new ArrayList<>();
	private String token;
	private String mRoomId;
	private String mChatroomId;
	private int pager_num = 0;
	public boolean load = true;
	@Override
	public void initData() {
	}

	@Override
	public int getMyLayout() {
		return R.layout.activity_live_tab_art;
	}

	@Override
	public void initView() {

	}

	@Override
	public void initListener() {

	}
	@OnClick({R.id.btn_live_jump})
	@Override
	public void onClick(View v) {
		switch (v.getId()) {
			case R.id.btn_live_jump:
				jumpLive();
				break;
		}
	}

	private void jumpLive() {
		Intent intent = new Intent(getActivity(), StartLiveActivity.class);
		startActivity(intent);
	}


	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
		switch (parent.getId()) {
			case R.id.all_follow:
				// 直播间id
				mRoomId = mAaDataList.get(position).getId();
				mChatroomId = mAaDataList.get(position).getChatroomId();
				// 进入直播间
				//initRoom(mRoomId);
				//
				break;
		}
	}
	public void sendTestRequest(String  num,String size) {

	}
}
