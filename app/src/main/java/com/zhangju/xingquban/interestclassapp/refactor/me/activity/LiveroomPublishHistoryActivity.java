package com.zhangju.xingquban.interestclassapp.refactor.me.activity;

import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.fastlib.annotation.Bind;
import com.fastlib.annotation.ContentView;
import com.fastlib.app.FastActivity;
import com.fastlib.net.Request;
import com.fastlib.net.SimpleListener;
import com.fastlib.utils.N;
import com.fastlib.widget.TitleBar;
import com.zhangju.xingquban.R;
import com.zhangju.xingquban.interestclassapp.refactor.common.bean.Response;
import com.zhangju.xingquban.interestclassapp.refactor.common.utils.ThirdPartyUtils;
import com.zhangju.xingquban.interestclassapp.refactor.me.adapter.LiveroomHistoryAdapter;
import com.zhangju.xingquban.interestclassapp.refactor.me.bean.MeInterface;
import com.zhangju.xingquban.interestclassapp.refactor.me.bean.ResponsePublishHistory;
import com.zhangju.xingquban.interestclassapp.ui.fragment.find.SeekResource.PicDetailActivity;
import com.zhangju.xingquban.interestclassapp.ui.fragment.live.LiveTabLayoutFragment.LiveVideoPlayerActivity;
import com.zhangju.xingquban.interestclassapp.view.dialog.ShareDialog;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by sgfb on 2017/11/8.
 * 我的直播间发布历史
 */
@ContentView(R.layout.act_live_room_history)
public class LiveroomPublishHistoryActivity extends FastActivity {
    @Bind(R.id.titleBar)
    TitleBar mTitleBar;
    @Bind(R.id.list)
    RecyclerView mList;
    LiveroomHistoryAdapter mAdapter;



    private ShareDialog shareDialog;
    private String title = "回放";
    private String pic = "";
    private String shareid = "";
    private String shareUrl = "http://my.xqban.com/admnxzcmr/user/h5/liveVdoShare?liveVdoId=" ;


    ArrayList<String> image = new ArrayList<String>();



    @Override
    protected void alreadyPrepared() {
        mList.setAdapter(mAdapter = new LiveroomHistoryAdapter(this));
        mTitleBar.setOnLeftClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        requestHistory();
        mAdapter.setShareInter(new LiveroomHistoryAdapter.ShareInter() {
            @Override
            public void toShare(int pos, ResponsePublishHistory data) {
                shareid=data.id;
                title=data.liveRecord.roomName;
                pic=data.liveRecord.roomPic;
                shareDialog.show();
            }
        });
        initShare();
    }

    private void initShare() {
        shareDialog=new ShareDialog(LiveroomPublishHistoryActivity.this,R.style.ActionSheetDialogStyle);
        shareDialog.QQZone(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                ThirdPartyUtils.getInstance(LiveroomPublishHistoryActivity.this).shareUrlToZone(LiveroomPublishHistoryActivity.this, shareUrl+shareid, title, "回放", image);

            }
        });

        shareDialog.qq(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                shareDialog.dismiss();
                ThirdPartyUtils.getInstance(LiveroomPublishHistoryActivity.this).shareToQQ(LiveroomPublishHistoryActivity.this, shareUrl+shareid, title, "回放", pic);
            }
        });
        shareDialog.wechat(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                shareDialog.dismiss();
                ThirdPartyUtils.getInstance(LiveroomPublishHistoryActivity.this).shareUrlToWechat(shareUrl+shareid, title, "回放", pic, false,false);
            }
        });
        shareDialog.wechat_zone(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                shareDialog.dismiss();
                ThirdPartyUtils.getInstance(LiveroomPublishHistoryActivity.this).shareUrlToWechat(shareUrl+shareid, title, "回放", pic,false,true);

            }
        });
        shareDialog.weibo(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                shareDialog.dismiss();
                ThirdPartyUtils.getInstance(LiveroomPublishHistoryActivity.this).shareToWeibo(LiveroomPublishHistoryActivity.this, title, pic);

            }
        });
    }

    private void requestHistory() {
        Request request = Request.obtain(MeInterface.POST_LIVE_PUBLISH_HISTORY);
        request.setListener(new SimpleListener<Response<List<ResponsePublishHistory>>>() {

            @Override
            public void onResponseListener(Request r, Response<List<ResponsePublishHistory>> result) {
                if (result.success) mAdapter.setData(result.data);
                else N.showShort(LiveroomPublishHistoryActivity.this, "获取历史记录失败");
            }
        });
        net(request);
    }
}