package com.zhangju.xingquban.interestclassapp.refactor.me.activity;

import android.content.Intent;
import android.graphics.Color;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.fastlib.annotation.Bind;
import com.fastlib.annotation.ContentView;
import com.fastlib.annotation.LocalData;
import com.fastlib.app.FastActivity;
import com.fastlib.utils.Utils;
import com.fastlib.widget.TitleBar;
import com.zhangju.xingquban.R;
import com.zhangju.xingquban.interestclassapp.refactor.me.bean.ResponseActivePublished;

import java.text.DecimalFormat;
import java.util.Locale;

/**
 * Created by Administrator on 2017/12/13.
 * 活动管理页面
 */
@ContentView(R.layout.act_active_manage)
public class ActiveManagerActivity extends FastActivity{
    public static final String ARG_SER_PUBLISHED_ACTIVE ="publishedActive";

    @LocalData(ARG_SER_PUBLISHED_ACTIVE)
    ResponseActivePublished mData;
    @Bind(R.id.titleBar)
    TitleBar mTitleBar;
    @Bind(R.id.cover)
    ImageView mCover;
    @Bind(R.id.status)
    TextView mStatus;
    @Bind(R.id.title)
    TextView mTitle;
    @Bind(R.id.location)
    TextView mLocation;
    @Bind(R.id.date)
    TextView mDate;
    @Bind(R.id.price)
    TextView mPrice;
    @Bind(R.id.signCount)
    TextView mSignCount;
    @Bind(R.id.hotCount)
    TextView mHotCount;
    @Bind(R.id.focusCount)
    TextView mFocusCount;

    @Override
    protected void alreadyPrepared(){
        String signCount=String.format(Locale.getDefault(),"%d 报名",mData.applyCount);
        String hotCount=String.format(Locale.getDefault(),"%d 关注",mData.collectionCount);
        mSignCount.setText(Utils.getTextSomeOtherColor(signCount.length()-2,signCount.length()-1,signCount,Color.parseColor("#030303")));
        mFocusCount.setText(Utils.getTextSomeOtherColor(hotCount.length()-2,signCount.length()-1,signCount,Color.parseColor("#030303")));
        mTitle.setText(mData.title);
        mHotCount.setText(Utils.getTextSomeOtherColor(2,3,"0 热度",Color.parseColor("#030303")));
        mLocation.setText(mData.location);
        mDate.setText(mData.date);
        mPrice.setText(String.format(Locale.getDefault(),"￥ %s起",new DecimalFormat("##.##").format(mData.price)));
        switch (mData.status){
            case 1:{
                mStatus.setText("进行中");
                mStatus.setTextColor(Color.BLACK);
                mStatus.setBackgroundColor(Color.parseColor("#FFDB10"));
            }break;
            case 0:{
                mStatus.setText("已结束");
                mStatus.setTextColor(Color.parseColor("#77777F"));
                mStatus.setBackgroundColor(Color.parseColor("#F8F8F8"));
            }break;
            case 3:{
                mStatus.setText("暂停报名");
                mStatus.setTextColor(Color.parseColor("#77777F"));
                mStatus.setBackgroundColor(Color.parseColor("#F8F8F8"));
            }
            default:System.out.println("一个不存在的活动状态");break;
        }
        Glide.with(this).load(mData.cover).into(mCover);
        mTitleBar.setOnLeftClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    @Bind(R.id.signupManager)
    private void signUpManager(){

    }

    @Bind(R.id.editActive)
    private void editActive(){

    }

    @Bind(R.id.startCheckTicket)
    private void startCheckTicket(){

    }

    @Bind(R.id.activeIncome)
    private void activeIncome(){
        Intent intent=new Intent(this,ActiveIncomeActivity.class);
        intent.putExtra(ActiveIncomeActivity.ARG_STR_ACTIVE_ID,mData.id);
        startActivity(intent);
    }

    @Bind(R.id.shareActive)
    private void shareActive(){

    }
}