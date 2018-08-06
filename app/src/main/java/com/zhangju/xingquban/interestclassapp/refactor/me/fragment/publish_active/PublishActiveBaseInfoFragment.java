package com.zhangju.xingquban.interestclassapp.refactor.me.fragment.publish_active;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.text.TextUtils;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.fastlib.annotation.Bind;
import com.fastlib.annotation.ContentView;
import com.fastlib.annotation.Event;
import com.fastlib.app.EventObserver;
import com.fastlib.app.FastDialog;
import com.fastlib.app.FastFragment;
import com.fastlib.app.PhotoResultListener;
import com.fastlib.utils.ImageUtil;
import com.fastlib.utils.N;
import com.fastlib.utils.TimeUtil;
import com.zhangju.xingquban.R;
import com.zhangju.xingquban.interestclassapp.refactor.me.activity.publish_active.ActiveCategoryActivity;
import com.zhangju.xingquban.interestclassapp.refactor.me.activity.publish_active.SignupSettingsActivity;
import com.zhangju.xingquban.interestclassapp.refactor.me.activity.publish_active.VoteSettingsActivity;
import com.zhangju.xingquban.interestclassapp.refactor.me.bean.EventPublishDataEdited;
import com.zhangju.xingquban.interestclassapp.refactor.me.bean.EventPublishProgressNext;
import com.zhangju.xingquban.interestclassapp.refactor.me.bean.PublishActiveInfo;
import com.zhangju.xingquban.interestclassapp.ui.fragment.me.MyRecrouse.LocationActive;

import java.util.Calendar;

/**
 * Created by sgfb on 2017/11/1.
 * 发布活动基本信息
 */
@ContentView(R.layout.frag_publish_active_base_info)
public class PublishActiveBaseInfoFragment extends FastFragment{
    final int REQ_SIGNUP_SETTINGS=1;
    final int REQ_LOCATION=2;
    final int REQ_VOTE_STATUS=3;
    final int REQ_CATEGORY = 4;

    @Bind(R.id.poster)
    ImageView mPoster;
    @Bind(R.id.activeName)
    EditText mActiveName;
    @Bind(R.id.hostName)
    EditText mHostName;
    @Bind(R.id.location)
    TextView mLocation;
    @Bind(R.id.startDate)
    TextView mStartDate;
    @Bind(R.id.endDate)
    TextView mEndDate;
    @Bind(R.id.signUp)
    TextView mSignUpType;
    @Bind(R.id.voteStatus)
    TextView mVoteStatus;
    @Bind(R.id.category)
    TextView mCategory;
    Calendar mStartCalendar=Calendar.getInstance();
    Calendar mEndCalendar=Calendar.getInstance();
    PublishActiveInfo.BaseInfo mInfo;

    @Override
    protected void alreadyPrepared(){
        mInfo=new PublishActiveInfo.BaseInfo();
    }

    @Bind(R.id.changePoster)
    private void changePoster(){
        FastDialog.showListDialog(new String[]{"拍照","从相册中选择"}).show(getChildFragmentManager(), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if(which==0)
                    openCamera(new PhotoResultListener() {
                        @Override
                        public void onPhotoResult(String path){
                            mPoster.setImageBitmap(BitmapFactory.decodeFile(path));
                            mInfo.titlePic=path;
                        }
                    });
                else openAlbum(new PhotoResultListener() {
                    @Override
                    public void onPhotoResult(String path) {
                        mPoster.setImageBitmap(BitmapFactory.decodeFile(path));
                        mInfo.titlePic=path;
                    }
                });
            }
        });
    }

    @Bind(R.id.startDate)
    private void changeStartDate(){
        DatePickerDialog dateDialog=new DatePickerDialog(getContext(), new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                mStartCalendar.set(year,month,dayOfMonth);
                mStartDate.setText(TimeUtil.dateToString(mStartCalendar.getTime(),"yyyy-MM-dd"));
            }
        },mStartCalendar.get(Calendar.YEAR),mStartCalendar.get(Calendar.MONTH),mStartCalendar.get(Calendar.DAY_OF_MONTH));
        dateDialog.show();
    }

    @Bind(R.id.endDate)
    private void changeEndDate(){
        DatePickerDialog dateDialog=new DatePickerDialog(getContext(), new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                mEndCalendar.set(year,month,dayOfMonth);
                mEndDate.setText(TimeUtil.dateToString(mEndCalendar.getTime(),"yyyy-MM-dd"));
            }
        },mEndCalendar.get(Calendar.YEAR),mEndCalendar.get(Calendar.MONTH),mEndCalendar.get(Calendar.DAY_OF_MONTH));
        dateDialog.show();
    }

    @Bind(R.id.signUpLayout)
    private void selectSignUpType(){
        Intent intent=new Intent(getContext(), SignupSettingsActivity.class);
        intent.putExtra(SignupSettingsActivity.ARG_RES_SER_BASE_INFO,mInfo.signupSettings);
        startActivityForResult(intent,REQ_SIGNUP_SETTINGS);
    }

    @Bind(R.id.voteLayout)
    private void selectVoteStatus(){
        Intent intent=new Intent(getContext(),VoteSettingsActivity.class);
        intent.putExtra(VoteSettingsActivity.RES_INT_VOTE_TYPE,mInfo.voteType);
        intent.putExtra(VoteSettingsActivity.RES_INT_VOTE_STATUS,mInfo.voteStatus);
        startActivityForResult(intent,REQ_VOTE_STATUS);
    }

    @Bind(R.id.location)
    private void selectLocation(){
        Intent intent=new Intent(getContext(),LocationActive.class);
        startActivityForResult(intent,REQ_LOCATION);
    }

    @Bind(R.id.categoryLayout)
    private void selectCategory(){
        startActivityForResult(new Intent(getContext(), ActiveCategoryActivity.class),REQ_CATEGORY);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode!= Activity.RESULT_OK) return;
        if(requestCode==REQ_SIGNUP_SETTINGS){
            mInfo.signupSettings= (PublishActiveInfo.BaseInfo.SignupSettings) data.getSerializableExtra(SignupSettingsActivity.ARG_RES_SER_BASE_INFO);
            mSignUpType.setText(mInfo.signupSettings.signUpType==0?"无需报名":"在线报名");
        }
        else if(requestCode==REQ_VOTE_STATUS){
            mInfo.voteStatus=data.getIntExtra(VoteSettingsActivity.RES_INT_VOTE_STATUS,0);
            mInfo.voteType=data.getIntExtra(VoteSettingsActivity.RES_INT_VOTE_TYPE,0);
            mVoteStatus.setText(mInfo.voteStatus==0?"关闭":"开启");
        }
        else if(requestCode==REQ_LOCATION){
            mInfo.latitude=data.getStringExtra("lat");
            mInfo.longitude=data.getStringExtra("lng");
            mInfo.place=data.getStringExtra("address");
            mLocation.setText(mInfo.place);
        }
        else if(requestCode==REQ_CATEGORY){
            mInfo.categoryId=data.getStringExtra(ActiveCategoryActivity.RES_STR_CATEGORY_ID);
            mInfo.categoryName=data.getStringExtra(ActiveCategoryActivity.RES_STR_CATEGORY_NAME);
            mCategory.setText(mInfo.categoryName);
        }
    }

    @Event
    private void eEventNext(EventPublishProgressNext event){
        if(event.getmCla()==getClass()){
            String title=mActiveName.getText().toString();
            String host=mHostName.getText().toString();
            String place=mLocation.getText().toString();
            String startDate=mStartDate.getText().toString();
            String endDate=mEndDate.getText().toString();

            if(TextUtils.isEmpty(title)){
                mActiveName.setError("请填写标题");
                mActiveName.requestFocus();
                return;
            }
            if(TextUtils.isEmpty(host)){
                mHostName.setError("请填写主办方");
                mHostName.requestFocus();
                return;
            }
            if(TextUtils.isEmpty(place)){
                mLocation.setError("请选择活动地点");
                mLocation.requestFocus();
                return;
            }
            if(TextUtils.isEmpty(startDate)){
                N.showSnackbarLong(mStartDate,"请填写活动开始时间");
                return;
            }
            if(TextUtils.isEmpty(endDate)){
                N.showSnackbarLong(mEndDate,"请填写活动结束时间");
                return;
            }
            if(TextUtils.isEmpty(mInfo.titlePic)){
                N.showSnackbarLong(mPoster,"请上传活动海报");
                return;
            }
            if(TextUtils.isEmpty(mInfo.categoryName)){
                N.showSnackbarShort(mCategory,"请选择类别");
                return;
            }
            mInfo.title=title;
            mInfo.host=host;
            mInfo.place=place;
            mInfo.startDate=startDate;
            mInfo.endDate=endDate;
            PublishActiveInfo activeInfo=new PublishActiveInfo();
            activeInfo.baseInfo=mInfo;
            EventObserver.getInstance().sendEvent(getContext(),new EventPublishDataEdited(activeInfo));
        }
    }
}
