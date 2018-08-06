package com.zhangju.xingquban.interestclassapp.refactor.me.activity.publish_active;

import android.content.Intent;
import android.support.annotation.IdRes;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.fastlib.annotation.Bind;
import com.fastlib.annotation.ContentView;
import com.fastlib.annotation.LocalData;
import com.fastlib.app.FastActivity;
import com.fastlib.widget.TitleBar;
import com.zhangju.xingquban.R;

/**
 * Created by sgfb on 2017/11/1.
 * 投票设置页面
 */
@ContentView(R.layout.act_publish_active_vote_settings)
public class VoteSettingsActivity extends FastActivity{
    public static final String RES_INT_VOTE_STATUS="voteStatus";
    public static final String RES_INT_VOTE_TYPE="voteType";

    @LocalData(RES_INT_VOTE_STATUS)
    int mVoteStatus;
    @LocalData(RES_INT_VOTE_TYPE)
    int mVoteType;
    @Bind(R.id.titleBar)
    TitleBar mTitleBar;
    @Bind(R.id.voteOpenCheck)
    CheckBox mVoteOpenCheck;
    @Bind(R.id.worksInfoLayout)
    View mWorksInfoLayout;
    @Bind(R.id.radioGroup)
    RadioGroup mRadioGroup;
    @Bind(R.id.personIntroduce)
    RadioButton mPersonIntroduce;
    @Bind(R.id.videoWorks)
    RadioButton mVideoWorks;
    @Bind(R.id.pictureWorks)
    RadioButton mPictureWorks;
    @Bind(R.id.textWorks)
    RadioButton mTextWorks;

    @Override
    protected void alreadyPrepared(){
        mVoteOpenCheck.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                mWorksInfoLayout.setVisibility(isChecked?View.VISIBLE:View.GONE);
            }
        });
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
        mRadioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {
                mTextWorks.setChecked(false);
            }
        });
        mTextWorks.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    int checkId=mRadioGroup.getCheckedRadioButtonId();
                    if(checkId!=-1)
                        ((RadioButton)mRadioGroup.findViewById(checkId)).setChecked(false);
                }
            }
        });
        mVoteOpenCheck.setChecked(mVoteStatus==2);
        switch (mVoteType){
            case 1:mPersonIntroduce.setChecked(true);break;
            case 2:mVideoWorks.setChecked(true);break;
            case 3:mPictureWorks.setChecked(true);break;
            case 4:mTextWorks.setChecked(true);break;
            default:System.out.println("未选定任何投票类型");
        }
    }

    private void commit(){
        Intent intent=new Intent();
        int voteType=-1;

        intent.putExtra(RES_INT_VOTE_STATUS,mVoteOpenCheck.isChecked()?2:0);
        if(mPersonIntroduce.isChecked()) voteType=1;
        else if(mVideoWorks.isChecked()) voteType=2;
        else if(mPictureWorks.isChecked()) voteType=3;
        else if(mTextWorks.isChecked()) voteType=4;
        intent.putExtra(RES_INT_VOTE_TYPE,voteType);
        setResult(RESULT_OK,intent);
        finish();
    }
}
