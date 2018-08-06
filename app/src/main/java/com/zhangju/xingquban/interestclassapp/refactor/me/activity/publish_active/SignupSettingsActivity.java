package com.zhangju.xingquban.interestclassapp.refactor.me.activity.publish_active;

import android.content.Intent;
import android.text.TextUtils;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RadioButton;

import com.fastlib.annotation.Bind;
import com.fastlib.annotation.ContentView;
import com.fastlib.annotation.LocalData;
import com.fastlib.app.FastActivity;
import com.fastlib.widget.TitleBar;
import com.zhangju.xingquban.R;
import com.zhangju.xingquban.interestclassapp.refactor.me.bean.PublishActiveInfo;

import java.text.DecimalFormat;
import java.util.Locale;

/**
 * Created by sgfb on 2017/11/1.
 * 发布活动报名设置
 */
@ContentView(R.layout.act_publish_active_signup_settings)
public class SignupSettingsActivity extends FastActivity{
    //参数复用返回
    public static final String ARG_RES_SER_BASE_INFO="baseInfo";

    @LocalData(ARG_RES_SER_BASE_INFO)
    PublishActiveInfo.BaseInfo.SignupSettings mSignupInfo;
    @Bind(R.id.titleBar)
    TitleBar mTitleBar;
    @Bind(R.id.signUpNeedInfoLayout)
    View mSignUpNeedInfoLayout;
    @Bind(R.id.signUpOnLine)
    RadioButton mSignUpOnLine;
    @Bind(R.id.noSignUp)
    RadioButton mNoSignUp;
    @Bind(R.id.needName)
    CheckBox mName;
    @Bind(R.id.needSex)
    CheckBox mPhone;
    @Bind(R.id.needSex)
    CheckBox mSex;
    @Bind(R.id.needAge)
    CheckBox mAge;
    @Bind(R.id.freeTicketCount)
    EditText mFreeTicketCount;
    @Bind(R.id.buyFreeLimit)
    EditText mBuyFreeLimit;
    @Bind(R.id.ticketPrice)
    EditText mTicketPrice;
    @Bind(R.id.ticketCount)
    EditText mTicketCount;
    @Bind(R.id.buyTicketLimit)
    EditText mBuyTicketLimit;

    @Override
    protected void alreadyPrepared() {
        mTitleBar.setOnLeftClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        mTitleBar.setOnRightClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                commit();
            }
        });
        mSignUpOnLine.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked)
                    mSignUpNeedInfoLayout.setVisibility(View.VISIBLE);
            }
        });
        mNoSignUp.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked)
                    mSignUpNeedInfoLayout.setVisibility(View.GONE);
            }
        });
        if(mSignupInfo !=null){
            if(mSignupInfo.signUpType==2){ //默认无需报名
                DecimalFormat df=new DecimalFormat("##.##");
                mSignUpOnLine.setChecked(true);
                if(mSignupInfo.needName==2) mName.setChecked(true);
                if(mSignupInfo.needPhone==2) mPhone.setChecked(true);
                if(mSignupInfo.needSex==2) mPhone.setChecked(true);
                if(mSignupInfo.needAge==2) mAge.setChecked(true);
                mTicketCount.setText(String.format(Locale.getDefault(),"%d", mSignupInfo.ticketCount));
                mTicketPrice.setText(String.format(Locale.getDefault(),"%s",df.format(mSignupInfo.price)));
                mBuyFreeLimit.setText("0");
                mBuyTicketLimit.append(String.format(Locale.getDefault(),"%d", mSignupInfo.buyLimit));
            }
        }
    }

    private void commit(){
        Intent intent=new Intent();
        int freeTicketCount= TextUtils.isEmpty(mFreeTicketCount.getText().toString())?0:Integer.parseInt(mFreeTicketCount.getText().toString());
        int freeTicketLimit=TextUtils.isEmpty(mBuyFreeLimit.getText().toString())?0:Integer.parseInt(mBuyFreeLimit.getText().toString());
        int ticketCount=TextUtils.isEmpty(mTicketCount.getText().toString())?0:Integer.parseInt(mTicketCount.getText().toString());
        int buyLimit=TextUtils.isEmpty(mBuyTicketLimit.getText().toString())?0:Integer.parseInt(mBuyTicketLimit.getText().toString());
        float price=TextUtils.isEmpty(mTicketPrice.getText().toString())?0:Float.parseFloat(mTicketPrice.getText().toString());

        mSignupInfo.signUpType=mSignUpOnLine.isChecked()?2:0;
        mSignupInfo.needName=mName.isChecked()?2:0;
        mSignupInfo.needPhone=mPhone.isChecked()?2:0;
        mSignupInfo.needSex=mSex.isChecked()?2:0;
        mSignupInfo.needAge=mAge.isChecked()?2:0;
        mSignupInfo.freeTicketCount=freeTicketCount;
        mSignupInfo.ticketCount=ticketCount;
        mSignupInfo.buyLimit=buyLimit;
        mSignupInfo.price=price;
        intent.putExtra(ARG_RES_SER_BASE_INFO, mSignupInfo);
        setResult(RESULT_OK,intent);
        finish();
    }
}
