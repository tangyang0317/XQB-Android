package com.zhangju.xingquban.interestclassapp.ui.fragment.live;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.fastlib.annotation.ContentView;
import com.fastlib.app.FastActivity;
import com.fastlib.net.Request;
import com.fastlib.net.SimpleListener;
import com.zhangju.xingquban.R;
import com.zhangju.xingquban.interestclassapp.adapter.VideoCommentAdapter;
import com.zhangju.xingquban.interestclassapp.bean.VideoCommentBean;
import com.zhangju.xingquban.interestclassapp.refactor.common.bean.Response;
import com.zhangju.xingquban.interestclassapp.refactor.user.UserManager;
import com.zhangju.xingquban.interestclassapp.swiperefrsh.SwipeRefreshAdapterView;
import com.zhangju.xingquban.interestclassapp.swiperefrsh.SwipeRefreshRecyclerView;
import com.zhangju.xingquban.interestclassapp.util.KeybordState;
import com.zhangju.xingquban.interestclassapp.util.ToastUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

//视频评论列表
@ContentView(R.layout.activity_video_comment)
public class VideoCommentActivity extends FastActivity implements SwipeRefreshAdapterView.OnListLoadListener, SwipeRefreshLayout.OnRefreshListener {

    @BindView(R.id.im_back)
    ImageView imBack;

    @BindView(R.id.edit_comment_input)
    EditText editCommentInput;
    @BindView(R.id.tv_send_comment)
    TextView tvSendComment;
    @BindView(R.id.swipe_refresh_comment)
    SwipeRefreshRecyclerView swipeRefreshLayout;

    private List<VideoCommentBean> mCommentList = new ArrayList<>();
    private VideoCommentAdapter videoCommentAdapter;
    String id;
    int pageIndex = 0;
    int total = 0;

    @Override
    protected void alreadyPrepared() {
        id = getIntent().getStringExtra("id");
        setCommentListAdapter();///评论adapter

    }

    private void setCommentListAdapter() {
        swipeRefreshLayout.setOnListLoadListener(this);
        swipeRefreshLayout.setOnRefreshListener(this);
        swipeRefreshLayout.autoRefresh();

        videoCommentAdapter = new VideoCommentAdapter(VideoCommentActivity.this, mCommentList);
        swipeRefreshLayout.setLayoutManager(new LinearLayoutManager(VideoCommentActivity.this));
        swipeRefreshLayout.setAdapter(videoCommentAdapter);

    }

    //获取评论数据
    private void getVideoCommentList(String id) {
        final Request request = Request.obtain(LiveInterface.POST_RECORD_COMMENT);
        String token = UserManager.getInstance().getToken();
        request.put("subjectId", id);
        request.put("comtType", "liveVdo");
        request.put("pageIndex", pageIndex);
        request.put("pageSize", "10");
        request.addHeader("X-CustomToken", token);
        request.setListener(new SimpleListener<Response<List<VideoCommentBean>>>() {

            @Override
            public void onResponseListener(Request r, Response<List<VideoCommentBean>> result) {
                boolean success = result.success;
                if (pageIndex==0){
                    mCommentList.clear();
                }
                if (success) {
                    total = result.total;
                    List<VideoCommentBean> data = result.data;
                    if (data != null && data.size() > 0)

                        mCommentList.addAll(data);
                }
                videoCommentAdapter.notifyDataSetChanged();
                setSwipeEnable();
            }

            @Override
            public void onErrorListener(Request r, String error) {
                super.onErrorListener(r, error);
                setSwipeEnable();
            }
        });
        net(request);
    }

    private void setSwipeEnable() {
        swipeRefreshLayout.setRefreshing(false);
        swipeRefreshLayout.setLoading(false);
        swipeRefreshLayout.setEnabledLoad(true);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.im_back, R.id.tv_send_comment})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.im_back:
                finish();
                break;
            case R.id.tv_send_comment:
                String trim = editCommentInput.getText().toString().trim();
                if (trim.isEmpty()) {
                    ToastUtil.showToast("评论不能为空");
                } else {
                    sendComment(trim);
                }
                break;
        }
    }

    //发送评论数据
    private void sendComment(String content) {
        final Request request = Request.obtain(LiveInterface.POST_RECORD_COMMENT_ADD);
        String token = UserManager.getInstance().getToken();
        request.put("comment", content);
        request.put("subjectId", id);
        request.put("star", "0");
        request.put("comtType", "liveVdo");
        request.addHeader("X-CustomToken", token);
        request.setListener(new SimpleListener<Response>() {

            @Override
            public void onResponseListener(Request r, Response result) {
                boolean success = result.success;
                if (success) {
                    editCommentInput.setText("");
                    KeybordState.closeKeybord(editCommentInput, VideoCommentActivity.this);
                    swipeRefreshLayout.autoRefresh();
                }
            }

            @Override
            public void onErrorListener(Request r, String error) {
                super.onErrorListener(r, error);

            }
        });
        net(request);
    }


    @Override
    public void onListLoad() {
        pageIndex++;
        if (pageIndex + 1 > total) {
            swipeRefreshLayout.setEnabledLoad(false);
            swipeRefreshLayout.setLoading(false);
            return;
        }
        getVideoCommentList(id);

    }

    @Override
    public void onRefresh() {

        pageIndex = 0;
        getVideoCommentList(id);
    }
}
