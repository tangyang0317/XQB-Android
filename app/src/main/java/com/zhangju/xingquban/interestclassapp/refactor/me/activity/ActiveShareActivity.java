package com.zhangju.xingquban.interestclassapp.refactor.me.activity;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.fastlib.annotation.Bind;
import com.fastlib.annotation.ContentView;
import com.fastlib.annotation.LocalData;
import com.fastlib.app.FastActivity;
import com.fastlib.widget.TitleBar;
import com.zhangju.xingquban.R;
import com.zhangju.xingquban.interestclassapp.refactor.me.bean.ResponseActivePublished;

/**
 * Created by Administrator on 2017/12/15.
 * 活动邀请函页
 */
@ContentView(R.layout.act_active_share)
public class ActiveShareActivity extends FastActivity{
    public static final String ARG_SER_ACTIVE="active";

    @LocalData(ARG_SER_ACTIVE)
    ResponseActivePublished mData;
    @Bind(R.id.titleBar)
    TitleBar mTitleBar;
    @Bind(R.id.cover)
    ImageView mCover;
    @Bind(R.id.qrCode)
    ImageView mQrcode;
    @Bind(R.id.activeName)
    TextView mActiveName;
    @Bind(R.id.host)
    TextView mHost;
    @Bind(R.id.location)
    TextView mLocation;
    @Bind(R.id.startDate)
    TextView mStartDate;
    @Bind(R.id.endDate)
    TextView mEndDate;

    @Override
    protected void alreadyPrepared(){
        mTitleBar.setOnLeftClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        mActiveName.setText(mData.title);
        mHost.setText(mData.title);
        mLocation.setText(mData.location);
        mStartDate.setText(mData.date);
    }
}
