package com.zhangju.xingquban.refactoring.activity;

import android.content.Context;
import android.content.Intent;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.fastlib.annotation.Bind;
import com.fastlib.annotation.ContentView;
import com.fastlib.app.FastActivity;
import com.fastlib.widget.TitleBar;
import com.zhangju.xingquban.R;
import com.zhangju.xingquban.refactoring.adapter.CommentAdapter;
import com.zhangju.xingquban.refactoring.bean.CommentBean;
import com.zhangju.xingquban.refactoring.view.XQBLoadMoreView;

import java.util.ArrayList;
import java.util.List;

/**
 * @packageName com.zhangju.xingquban.refactoring.activity
 * @FileName ResourceDetailsActivity
 * @Author tangyang
 * @DATE 2018/9/28
 **/
@ContentView(R.layout.act_resource_details)
public class ResourceDetailsActivity extends FastActivity implements SwipeRefreshLayout.OnRefreshListener, BaseQuickAdapter.RequestLoadMoreListener {

    @Bind(R.id.titleBar)
    TitleBar titleBar;
    @Bind(R.id.resourceSwipeRefreshLayout)
    SwipeRefreshLayout resourceSwipeRefreshLayout;
    @Bind(R.id.resourceRecycleView)
    RecyclerView resourceRecycleView;
    @Bind(R.id.commentLayout)
    LinearLayout commentLayout;
    @Bind(R.id.praisedLayout)
    private LinearLayout praisedLayout;
    @Bind(R.id.collectionLayout)
    private LinearLayout collectionLayout;

    private CommentAdapter commentAdapter;

    private View headView;

    public static void launcherThis(Context context, String resourceId) {
        Intent intent = new Intent(context, ResourceDetailsActivity.class);
        intent.putExtra("id", resourceId);
        context.startActivity(intent);
    }

    @Override
    protected void alreadyPrepared() {
        titleBar.setOnLeftClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ResourceDetailsActivity.this.finish();
            }
        });

        titleBar.setOnRightClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        headView = LayoutInflater.from(this).inflate(R.layout.view_resource_details_header, null);
        commentAdapter = new CommentAdapter();
        resourceRecycleView.setLayoutManager(new LinearLayoutManager(this));
        commentAdapter.addHeaderView(headView);
        resourceRecycleView.setAdapter(commentAdapter);
        commentAdapter.setOnLoadMoreListener(this, resourceRecycleView);
        commentAdapter.setLoadMoreView(new XQBLoadMoreView());
        resourceSwipeRefreshLayout.setColorSchemeResources(R.color.colorPrimary);
        resourceSwipeRefreshLayout.setOnRefreshListener(this);

        initData();
    }

    /***
     *
     */
    private void initData() {
        List<CommentBean> commentBeanList = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            CommentBean commentBean = new CommentBean();
            commentBeanList.add(commentBean);
        }
        commentAdapter.setNewData(commentBeanList);
        commentAdapter.notifyDataSetChanged();
    }

    @Override
    public void onRefresh() {

    }

    @Override
    public void onLoadMoreRequested() {

    }
}
