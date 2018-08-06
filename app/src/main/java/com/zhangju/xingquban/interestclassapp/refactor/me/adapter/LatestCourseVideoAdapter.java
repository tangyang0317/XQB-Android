package com.zhangju.xingquban.interestclassapp.refactor.me.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.fastlib.adapter.SingleAdapterForRecycler;
import com.fastlib.app.EventObserver;
import com.fastlib.base.CommonViewHolder;
import com.fastlib.net.Request;
import com.fastlib.net.SimpleListener;
import com.fastlib.widget.FastPopupWindow;
import com.zhangju.xingquban.R;
import com.zhangju.xingquban.interestclassapp.refactor.common.bean.EventRefresh;
import com.zhangju.xingquban.interestclassapp.refactor.common.bean.Response;
import com.zhangju.xingquban.interestclassapp.refactor.me.activity.CourseVideoActivity;
import com.zhangju.xingquban.interestclassapp.refactor.me.activity.PublishCourseVideoActivity;
import com.zhangju.xingquban.interestclassapp.refactor.me.bean.MeInterface;
import com.zhangju.xingquban.interestclassapp.refactor.me.bean.ResponseCourseVideo;
import com.zhangju.xingquban.interestclassapp.refactor.user.UserManager;
import com.zhangju.xingquban.interestclassapp.ui.activity.near.ShipinBofangActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by sgfb on 2017/11/21.
 * 最近课程视频适配器
 */
public class LatestCourseVideoAdapter extends SingleAdapterForRecycler<ResponseCourseVideo,Response<List<ResponseCourseVideo>>>{

    public LatestCourseVideoAdapter(Context context) {
        super(context, R.layout.item_course_video_latester_video);
    }

    @Override
    public Request generateRequest(){
        return Request.obtain(MeInterface.POST_COURSE_VIDEO_LATEST).put("customerId", UserManager.getInstance().getUser().id);
    }

    @Override
    public void binding(int position, final ResponseCourseVideo data, CommonViewHolder holder) {
        holder.setText(R.id.date,data.addUserTime);
        holder.setText(R.id.title,data.title);
        Glide.with(mContext)
                .load(data.videoTitlePic)
                .centerCrop()
                .into((ImageView)holder.getView(R.id.cover));
        holder.setOnClickListener(R.id.more, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final FastPopupWindow moreMenu=new FastPopupWindow(mContext);
                List<String> list=new ArrayList<>();
                list.add("删除");
                list.add("编辑");
                moreMenu.setListView(list, new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        if(position==0){
                            requestDeleteVideo(data.id);
                        }
                        else{
                            Intent intent=new Intent(mContext,PublishCourseVideoActivity.class);
                            intent.putExtra(PublishCourseVideoActivity.ARG_SER_COURSE_VIDEO,data);
                            mContext.startActivity(intent);
                        }
                        moreMenu.toggle();
                    }
                });
                moreMenu.setOrientation(FastPopupWindow.Orientation.LEFT);
                moreMenu.toggle(v);
            }
        });
        holder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(mContext, ShipinBofangActivity.class);
                intent.putExtra(ShipinBofangActivity.ARG_STRING_NAME,data.title);
                intent.putExtra(ShipinBofangActivity.ARG_STRING_URL,data.videoStr);
                mContext.startActivity(intent);
            }
        });
    }

    @Override
    public List<ResponseCourseVideo> translate(Response<List<ResponseCourseVideo>> result){
        if(result.success) return result.data;
        return null;
    }

    @Override
    public void getMoreDataRequest(Request request) {
        isMore=false;
    }

    @Override
    public void getRefreshDataRequest(Request request) {
        isMore=false;
    }

    /**
     * 请求删除某条视频
     */
    private void requestDeleteVideo(String id){
        Request request=new Request(MeInterface.POST_VIDEO_DELETE);
        request.put("id",id);
        request.setListener(new SimpleListener<Response>(){

            @Override
            public void onResponseListener(Request r, Response result) {
                if(result.success){
                    EventObserver.getInstance().sendEvent(mContext,new EventRefresh(CourseVideoActivity.class));
                }
            }
        });
        request.start();
    }
}