package com.zhangju.xingquban.interestclassapp.refactor.me.activity;

import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.fastlib.annotation.Bind;
import com.fastlib.annotation.ContentView;
import com.fastlib.annotation.LocalData;
import com.fastlib.app.FastActivity;
import com.fastlib.widget.TitleBar;
import com.zhangju.xingquban.R;
import com.zhangju.xingquban.interestclassapp.refactor.me.adapter.NotificationAdapter;

/**
 * Created by sgfb on 2017/10/26.
 * 某类型消息列表
 */
@ContentView(R.layout.act_notification_list)
public class NotificationListActivity extends FastActivity{
    public static final String ARG_INT_MESSAGE_TYPE="messageType"; //1为今日消息，0为系统消息

    @LocalData(ARG_INT_MESSAGE_TYPE)
    int mMessageType;
    @Bind(R.id.titleBar)
    TitleBar mTitleBar;
    @Bind(R.id.refresh)
    SwipeRefreshLayout mRefresh;
    @Bind(R.id.list)
    RecyclerView mList;
    NotificationAdapter mAdapter;

    @Override
    protected void alreadyPrepared(){
        mList.setAdapter(mAdapter=new NotificationAdapter(this,mMessageType));
        mTitleBar.getTitle().setText(mMessageType==0?"系统消息":"每日动态");
        mTitleBar.setOnLeftClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        mRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                mAdapter.refresh();
            }
        });

        mAdapter.setRefreshLayout(mRefresh);
    }
}