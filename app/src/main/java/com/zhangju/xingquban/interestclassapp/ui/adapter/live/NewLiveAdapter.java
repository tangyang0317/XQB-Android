package com.zhangju.xingquban.interestclassapp.ui.adapter.live;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.zhangju.xingquban.R;
import com.zhangju.xingquban.interestclassapp.adapter.baseadpter.BaseRecycleViewAdapter;
import com.zhangju.xingquban.interestclassapp.bean.live.LiveNewListBean;
import com.zhangju.xingquban.interestclassapp.view.imageView.ZQImageViewRoundOval;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by hqf 2017//1/6
 *
 * @最新直播列表
 */
public class NewLiveAdapter extends BaseRecycleViewAdapter {

	private List<LiveNewListBean.AaDataBean> mNewsList;

	public NewLiveAdapter(Context c, List<LiveNewListBean.AaDataBean> mapList) {
		super(c);
		this.mNewsList = mapList;
	}

	@Override
	public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
		return new NewsViewholder(resIdToView(parent, R.layout.live_new_item));
	}

	@Override
	public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
		NewsViewholder newsViewholder = (NewsViewholder) holder;
		newsViewholder.onBind();
	}

	@Override
	public int getItemCount() {
		return mNewsList.size();
	}

	class NewsViewholder extends RecyclerView.ViewHolder {

		int pos;

		@BindView(R.id.img_resource_small)
		ImageView imgResourceSmall;
		@BindView(R.id.tv_video_time1)
		TextView tvVideoTime1;
		@BindView(R.id.tv_video_class)
		TextView tvVideoClass;
		@BindView(R.id.tv_resource_title)
		TextView tvResourceTitle;
		@BindView(R.id.tv_resource_looks)
		TextView tvResourceLooks;

		public NewsViewholder(View itemView) {
			super(itemView);
			ButterKnife.bind(this,itemView);
		}

		void onBind() {
			pos = getLayoutPosition();
			LiveNewListBean.AaDataBean item = mNewsList.get(pos);
			registerOnItemClickListener(pos,itemView);

			String roomName = item.getRoomName()==null?"":item.getRoomName();//直播名称
			String roomPic = item.getRoomPic()==null?"":item.getRoomPic();//直播背景图
			String liveStatus = getStatus(item.getStatus());// 直播类型


			String signStr=item.getSummary()==null?"":item.getSummary().toString();

			LiveNewListBean.AaDataBean.CatagoriesBean catagories = item.getCatagories();
			String classtype="";
			if (catagories!=null){
				classtype = catagories.getName()==null?"":catagories.getName();//课程类型
			}

			Glide.with(c).load(roomPic).into(imgResourceSmall);
			tvResourceTitle.setText(roomName);
			tvVideoTime1.setText(liveStatus);
			tvVideoClass.setText(classtype);
			tvResourceLooks.setText(signStr);
		}
	}

	//状态判断
	//   status: '直播间状态_int_0：空闲 1：直播  2：禁用  3：直播录制'
	private String getStatus(int status) {
		if (status == 0) {
			return "空闲";
		} else if (status == 1) {
			return "直播中";
		} else if (status == 2) {
			return "禁用";
		} else if (status == 3) {
			return "直播录制";
		} else {
			return "";
		}
	}
}
