package com.zhangju.xingquban.interestclassapp.refactor.me.activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.fastlib.annotation.Bind;
import com.fastlib.annotation.ContentView;
import com.fastlib.annotation.LocalData;
import com.fastlib.app.FastActivity;
import com.fastlib.app.FastDialog;
import com.fastlib.app.PhotoResultListener;
import com.fastlib.app.task.Action;
import com.fastlib.app.task.EmptyAction;
import com.fastlib.app.task.NetAction;
import com.fastlib.app.task.NoReturnAction;
import com.fastlib.app.task.Task;
import com.fastlib.app.task.ThreadType;
import com.fastlib.net.Request;
import com.fastlib.net.SimpleListener;
import com.fastlib.utils.N;
import com.fastlib.utils.Utils;
import com.fastlib.widget.TitleBar;
import com.zhangju.xingquban.R;
import com.zhangju.xingquban.interestclassapp.refactor.common.bean.CommonInterface;
import com.zhangju.xingquban.interestclassapp.refactor.common.bean.Response;
import com.zhangju.xingquban.interestclassapp.refactor.me.bean.MeInterface;
import com.zhangju.xingquban.interestclassapp.refactor.me.bean.ResponseCourse;
import com.zhangju.xingquban.interestclassapp.refactor.me.bean.ResponseUploadImage;
import com.zhangju.xingquban.interestclassapp.refactor.user.UserManager;
import com.zhangju.xingquban.interestclassapp.ui.activity.find.liveradio.CourseChoiceActivity;
import com.zhangju.xingquban.interestclassapp.ui.fragment.me.MyRecrouse.LocationActive;

import java.io.File;
import java.util.Calendar;
import java.util.List;

/**
 * Created by sgfb on 2017/11/6.
 * 课程添加或修改
 */
@ContentView(R.layout.act_add_course)
public class EditCourseActivity extends FastActivity{
    public static final String ARG_STR_ID ="id"; //可选课程，如果存在则是修改课程否则添加
    final int REQ_AUDITION_TYPE=1;
    final int REQ_TEACH_TYPE=2;
    final int REQ_ADDRESS=3;
    final int REQ_TEACH_COURSE=4;

    @LocalData(ARG_STR_ID)
    String mId;
    @Bind(R.id.hint)
    TextView mHint;
    @Bind(R.id.titleBar)
    TitleBar mTitleBar;
    @Bind(R.id.courseName)
    EditText mCourseName;
    @Bind(R.id.courseCount)
    EditText mCourseCount;
    @Bind(R.id.courseLength)
    EditText mCourseLength;
    @Bind(R.id.coursePrice)
    EditText mCoursePrice;
    @Bind(R.id.vipPrice)
    EditText mVipPrice;
    @Bind(R.id.teacherName)
    EditText mTeacherName;
    //2018/01/29修改无限订单时间和报名人数
//    @Bind(R.id.openCourseNeedMember)
//    EditText mOpenCourseNeedMember;
//    @Bind(R.id.teachTime)
//    TextView mTeachTime;
    @Bind(R.id.courseProfile)
    EditText mCourseProfile;
    @Bind(R.id.courseDescription)
    EditText mCourseDescription;
    @Bind(R.id.auditionInfo)
    TextView mAuditionInfo;
    @Bind(R.id.teachAddress)
    TextView mTeachAddress;
    @Bind(R.id.teachMethod)
    TextView mTeachMethod;
    @Bind(R.id.teachAddressTitle)
    TextView mTeachAddressTitle;
    @Bind(R.id.cover)
    ImageView mCover;
    @Bind(R.id.teachCourse)
    TextView mTeachCourse;
    Calendar mCalendar=Calendar.getInstance();
    int mAuditionType=-1;
    int mTeachMethodType=-1;
    String mAddress;
    String mLng;
    String mLat;
    String mCoverPath;
    String mAreaName;
    String mAreaCode;
    String mProvinceName;
    String mProvinceCode;
    String mCityId;
    String mCityName;
    String mCourseId;

    @Override
    protected void alreadyPrepared() {
        mTitleBar.setOnLeftClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        String hint=mHint.getText().toString();
        mHint.setText(Utils.getTextSomeOtherColor(2,3,hint,getResources().getColor(R.color.EF4E4C)));
        if(!TextUtils.isEmpty(mId)&&!"-1".equals(mId)){
            mTitleBar.getTitle().setText("修改课程");
            requestCourse();
        }
    }

    /**
     * 如果是修改课程，请求课程详情
     */
    private void requestCourse(){
        Request request=Request.obtain(MeInterface.POST_COURSE_DETAIL);
        request.put("id",mId);
        request.setListener(new SimpleListener<Response<List<ResponseCourse>>>(){

            @Override
            public void onResponseListener(Request r, Response<List<ResponseCourse>> result) {
                if(result.success){
                    ResponseCourse data=result.data.get(0);
                    mCourseName.append(TextUtils.isEmpty(data.name)?"":data.name);
                    mCourseCount.setText(data.courses);
                    mCourseLength.setText(data.timelength);
                    mCoursePrice.setText(data.price);
                    mAuditionInfo.setText(data.isCantry?"可试听":"不可试听");
//                    mOpenCourseNeedMember.setText(data.allows);
                    mTeachMethod.setText(data.method);
//                    mTeachTime.setText(data.lessonDate);
                    mCourseDescription.setText(data.descript);
                    mCourseProfile.setText(data.summary);
                    mTeacherName.setText(data.teacherName);
                    mTeachAddress.setText(data.region);
                    mVipPrice.setText(data.vipPrice);
                    mTeachCourse.setText(data.catagoryName);
                    mCourseId=data.categoriesId;
                    mAuditionType=data.isCantry?1:0;
                    mAddress=data.region;
                    mAreaCode=data.areasId;
                    mProvinceCode=data.provinceId;
                    mCityId=data.cityId;
                    mAreaName=data.areasName;
                    mProvinceName=data.provinceName;
                    mCityName=data.cityName;
                    mLat=data.lat;
                    mLng=data.lng;
                    mCoverPath=data.picture;
                    if("学生上门".equals(data.method))
                        mTeachMethodType=1;
                    else if("家教上门".equals(data.method))
                        mTeachMethodType=2;
                    else mTeachMethodType=3;
                    Glide.with(EditCourseActivity.this).load(data.picture).into(mCover);
                }
            }
        });
        net(request);
    }

    @Bind(R.id.coverLayout)
    private void selectCover(){
        FastDialog.showListDialog(new String[]{"拍照","从相册中选择"}).show(getSupportFragmentManager(), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if(which==0) openCamera(new PhotoResultListener() {
                    @Override
                    public void onPhotoResult(String path) {
                        Glide.with(EditCourseActivity.this).load(new File(path)).into(mCover);
                        mCoverPath=path;
                    }
                });
                else openAlbum(new PhotoResultListener() {
                    @Override
                    public void onPhotoResult(String path) {
                        Glide.with(EditCourseActivity.this).load(new File(path)).into(mCover);
                        mCoverPath=path;
                    }
                });
            }
        });
    }

    @Bind(R.id.auditionLayout)
    private void selectAudition(){
        startActivityForResult(AuditionTypeActivity.class,REQ_AUDITION_TYPE);
    }

    @Bind(R.id.teachAddressLayout)
    private void selectTeachAddress(){
        startActivityForResult(LocationActive.class,REQ_ADDRESS);
    }

    @Bind(R.id.teachMethodLayout)
    private void selectTeachMethod(){
        startActivityForResult(TeachTypeActivity.class,REQ_TEACH_TYPE);
    }

//    @Bind(R.id.teachTimeLayout)
//    private void selectTeachTime(){
//        DatePickerDialog dateDialog=new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
//            @Override
//            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
//                mCalendar.set(year,month,dayOfMonth);
//                mTeachTime.setText(TimeUtil.dateToString(mCalendar.getTime(),"yyyy-MM-dd"));
//            }
//        },mCalendar.get(Calendar.YEAR),mCalendar.get(Calendar.MONTH),mCalendar.get(Calendar.DAY_OF_MONTH));
//        dateDialog.show();
//    }

    @Bind(R.id.commit)
    private void commit(){
        final String courseName=mCourseName.getText().toString();
        final String courseCount=mCourseCount.getText().toString();
        final String courseLength=mCourseLength.getText().toString();
        final String coursePrice=mCoursePrice.getText().toString();
        final String teacherName=mTeacherName.getText().toString();
//        final String openNeedMember=mOpenCourseNeedMember.getText().toString();
        final String courseDescription=mCourseDescription.getText().toString();
        final String courseProfile=mCourseProfile.getText().toString();
//        final String time=mTeachTime.getText().toString();
        final String vipPrice=mVipPrice.getText().toString();

        if(TextUtils.isEmpty(courseName)){
            mCourseName.setError("请输入课程名称");
            mCourseName.requestFocus();
            return;
        }
        if(TextUtils.isEmpty(courseCount)){
            mCourseCount.setError("请输入课程数量");
            mCourseCount.requestFocus();
            return;
        }
        if(TextUtils.isEmpty(courseLength)){
            mCourseLength.setError("请输入课时长度");
            mCourseLength.requestFocus();
            return;
        }
        if(TextUtils.isEmpty(coursePrice)){
            mCoursePrice.setError("请输入课程价格");
            mCoursePrice.requestFocus();
            return;
        }
        if(TextUtils.isEmpty(vipPrice)){
            mVipPrice.setError("请输入优惠后的价格");
            return;
        }
        if(TextUtils.isEmpty(teacherName)){
            mTeacherName.setError("请输入教师姓名");
            mTeacherName.requestFocus();
            return;
        }
//        if(TextUtils.isEmpty(openNeedMember)){
//            mOpenCourseNeedMember.setError("请输入开班人数");
//            mOpenCourseNeedMember.requestFocus();
//            return;
//        }
        if(TextUtils.isEmpty(courseDescription)){
            mCourseDescription.setError("请输入课程描述");
            mCourseDescription.requestFocus();
            return;
        }
        if(TextUtils.isEmpty(courseProfile)){
            mCourseProfile.setError("请输入课程简介");
            mCourseProfile.requestFocus();
            return;
        }
//        if(TextUtils.isEmpty(time)){
//            N.showSnackbarShort(mTeachTime,"请选择时间");
//            return;
//        }
        if(TextUtils.isEmpty(mTeachAddress.getText().toString())){
            N.showSnackbarShort(mTeachAddress,"请输入授课地点");
            return;
        }
        if(mAuditionType==-1){
            N.showSnackbarShort(mAuditionInfo,"请选择是否可试听");
            return;
        }
        if(mTeachMethodType==-1){
            N.showSnackbarShort(mTeachMethod,"请选择教学类型");
            return;
        }
        if(TextUtils.isEmpty(mCoverPath)){
            N.showSnackbarShort(mCover,"请上传课程封面");
            return;
        }
        if(TextUtils.isEmpty(mCourseId)){
            N.showSnackbarShort(mTeachCourse,"请选择科目");
            return;
        }
        float price=Float.parseFloat(coursePrice);
        float discountPrice=Float.parseFloat(vipPrice);
        if(price<=0){
            N.showShort(this,"价格必须大于0元");
            return;
        }
        if(price<=discountPrice){
            N.showShort(this,"优惠价必须小于原价");
            return;
        }
        loading();
        if(!TextUtils.isEmpty(mId)&&!"-1".equals(mId)){
            Request request = Request.obtain(MeInterface.POST_ADD_COURSE);
            request.put("areasId",mAreaCode)
//                    .put("allows", openNeedMember)
                    .put("allows",0)
                    .put("cityCode",mCityId)
                    .put("provinceId",mProvinceCode)
                    .put("areasName", mAreaName)
                    .put("cityName", mCityName)
                    .put("courses", courseCount)
                    .put("customerId", UserManager.getInstance().getUser().id)
                    .put("descript", courseDescription)
                    .put("isCantry", mAuditionType)
                    .put("lat", mLat)
                    .put("lng", mLng)
                    .put("methodType", mTeachMethodType)
                    .put("name", courseName)
                    .put("picture",mCoverPath)
                    .put("price", coursePrice)
                    .put("provinceName", mProvinceName)
                    .put("region", mAddress)
                    .put("resId", teacherName)
                    .put("summary", courseProfile)
                    .put("teacherTimeId", UserManager.getInstance().getUser().teacherTimeId)
                    .put("timelength", courseLength)
                    .put("vipPrice", vipPrice)
//                    .put("lessonDate",time)
                    .put("lessonDate","2050-01-01")
                    .put("categoriesId",mCourseId)
                    .put("catagoryName",mTeachCourse.getText().toString())
                    .put("id",mId);
            request.setListener(new SimpleListener<Response>(){

                @Override
                public void onResponseListener(Request r, Response result) {
                    N.showShort(EditCourseActivity.this,"修改课程成功");
                    finish();
                }
            });
            net(request);
        }
        else{
            startTask(Task.begin(mCoverPath)
                    .next(new Action<String, Request>() { //上传封面
                        @Override
                        protected Request execute(String param) throws Throwable {
                            return Request.obtain(CommonInterface.POST_UPLOAD_IMAGE).put("files", new File(param))
                                    .putHeader("X-CustomToken", UserManager.getInstance().getToken());

                        }
                    })
                    .next(new NetAction<Response<List<ResponseUploadImage>>>() {

                        @Override
                        protected void executeAdapt(Response<List<ResponseUploadImage>> listResponse, Request request) {
                            if (!listResponse.success || listResponse.data == null || listResponse.data.isEmpty())
                                stopTask();
                        }
                    })
                    .next(new Action<Response<List<ResponseUploadImage>>, Request>() { //开始调增加课程接口

                        @Override
                        protected Request execute(Response<List<ResponseUploadImage>> param) throws Throwable {
                            Request request = Request.obtain(MeInterface.POST_ADD_COURSE);
                            request.put("areasId",mAreaCode)
//                                    .put("allows", openNeedMember)
                                    .put("allows",0)
                                    .put("cityCode",mCityId)
                                    .put("provinceId",mProvinceCode)
                                    .put("areasName", mAreaName)
                                    .put("cityName", mCityName)
                                    .put("courses", courseCount)
                                    .put("customerId", UserManager.getInstance().getUser().id)
                                    .put("descript", courseDescription)
                                    .put("isCantry", mAuditionType)
                                    .put("lat", mLat)
                                    .put("lng", mLng)
                                    .put("methodType", mTeachMethodType)
                                    .put("name", courseName)
                                    .put("picture", param.data.get(0).fileName)
                                    .put("price", coursePrice)
                                    .put("provinceName", mProvinceName)
                                    .put("region", mAddress)
                                    .put("resId", teacherName)
                                    .put("summary", courseProfile)
                                    .put("teacherTimeId", UserManager.getInstance().getUser().teacherTimeId)
                                    .put("timelength", courseLength)
//                                    .put("lessonDate",time)
                                    .put("lessonDate","2050-01-01")
                                    .put("categoriesId",mCourseId)
                                    .put("catagoryName",mTeachCourse.getText().toString())
                                    .put("vipPrice", vipPrice);
                            return request;
                        }
                    })
                    .next(new NetAction<Response>() {

                        @Override
                        protected void executeAdapt(Response response, Request request) {
                            if (response.success) {
                                N.showShort(EditCourseActivity.this, "增加课程成功");
                                setResult(RESULT_OK);
                                finish();
                            }
                        }
                    },ThreadType.MAIN), new NoReturnAction<Throwable>() {
                @Override
                public void executeAdapt(Throwable param) {
                    param.printStackTrace();
                    dismissLoading();
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            N.showShort(EditCourseActivity.this,"增加课程失败");
                        }
                    });
                }
            }, new EmptyAction() {
                @Override
                protected void executeAdapt() {
                    dismissLoading();
                }
            });
        }
    }

    @Bind(R.id.teachCourseLayout)
    private void selectTeachCourse(){
        Intent intent=new Intent(this, CourseChoiceActivity.class);
        intent.putExtra("type",false);
        startActivityForResult(intent,REQ_TEACH_COURSE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode!=RESULT_OK) return;
        if(requestCode==REQ_AUDITION_TYPE){
            mAuditionType=data.getIntExtra(AuditionTypeActivity.RES_INT_CAN_AUDITION,-1);
            if(mAuditionType==0)
                mAuditionInfo.setText("不可以试听");
            else mAuditionInfo.setText("可以试听");
        }
        else if(requestCode==REQ_TEACH_TYPE){
            mTeachMethodType=data.getIntExtra(TeachTypeActivity.RES_INT_TEACH_METHOD_TYPE,-1);
            switch (mTeachMethodType){
                case 1:mTeachMethod.setText("学生上门");
                    mTeachAddressTitle.setText("授课地点");
                    break;
                case 2:mTeachMethod.setText("家教上门");
                    mTeachAddressTitle.setText("所在地点");
                    break;
                case 3:mTeachMethod.setText("协商地点");
                    mTeachAddressTitle.setText("所在地点");
                    break;
                default:mTeachMethod.setText("请选择");
                    mTeachAddressTitle.setText("授课地点");
                    break;
            }
        }
        else if(requestCode==REQ_ADDRESS){
            mAddress=data.getStringExtra("address");
            mLat=data.getStringExtra("lat");
            mLng=data.getStringExtra("lng");
            mAreaName=data.getStringExtra("areaName");
            mAreaCode=data.getStringExtra("areaCode");
            mProvinceName=data.getStringExtra("provinceName");
            mProvinceCode=data.getStringExtra("provinceCode");
            mCityId=data.getStringExtra("cityId");
            mCityName=data.getStringExtra("cityName");
            mTeachAddress.setText(mAddress);
        }
        else if(requestCode==REQ_TEACH_COURSE){
            mCourseId=data.getStringExtra("subId");
            mTeachCourse.setText(data.getStringExtra("subName"));
        }
    }
}
