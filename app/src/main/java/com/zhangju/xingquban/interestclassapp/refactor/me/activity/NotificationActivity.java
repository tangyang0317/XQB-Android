package com.zhangju.xingquban.interestclassapp.refactor.me.activity;

import android.content.Intent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.fastlib.annotation.Bind;
import com.fastlib.annotation.ContentView;
import com.fastlib.app.FastActivity;
import com.fastlib.widget.TitleBar;
import com.zhangju.xingquban.R;

/**
 * Created by sgfb on 2017/10/26.
 * 消息中心
 */
@ContentView(R.layout.act_notification)
public class NotificationActivity extends FastActivity{
    @Bind(R.id.titleBar)
    TitleBar mTitleBar;
    @Bind(R.id.dayTitle)
    TextView mTitle;
    @Bind(R.id.dayContent)
    TextView mContent;
    @Bind(R.id.systemTitle)
    TextView mSystemTitle;
    @Bind(R.id.systemContent)
    TextView mSystemContent;
    @Bind(R.id.dayUnreadCount)
    TextView mUnreadCount;
    @Bind(R.id.systemUnread)
    TextView mSystemMessageUnread;
    @Bind(R.id.icon)
    ImageView mIcon;

    @Override
    protected void alreadyPrepared() {
        mTitleBar.setOnLeftClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        mSystemTitle.setText("系统消息");
        mTitle.setText("每日动态");
    }

    @Bind(R.id.dayMessage)
    private void openDayMessage(){
        Intent intent=new Intent(this,NotificationListActivity.class);
        intent.putExtra(NotificationListActivity.ARG_INT_MESSAGE_TYPE,1);
        startActivity(intent);
    }

    @Bind(R.id.systemMessage)
    private void openSystemMessage(){
        Intent intent=new Intent(this,NotificationListActivity.class);
        intent.putExtra(NotificationListActivity.ARG_INT_MESSAGE_TYPE,0);
        startActivity(intent);
    }
}