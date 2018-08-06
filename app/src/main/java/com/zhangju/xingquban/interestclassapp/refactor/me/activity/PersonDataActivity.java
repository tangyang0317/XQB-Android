package com.zhangju.xingquban.interestclassapp.refactor.me.activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Environment;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.fastlib.annotation.Bind;
import com.fastlib.annotation.ContentView;
import com.fastlib.annotation.Event;
import com.fastlib.app.FastActivity;
import com.fastlib.app.FastDialog;
import com.fastlib.app.PhotoResultListener;
import com.fastlib.net.Request;
import com.fastlib.net.SimpleListener;
import com.fastlib.utils.ImageUtil;
import com.fastlib.utils.N;
import com.fastlib.widget.TitleBar;
import com.zhangju.xingquban.R;
import com.zhangju.xingquban.interestclassapp.refactor.common.bean.CommonInterface;
import com.zhangju.xingquban.interestclassapp.refactor.common.bean.Response;
import com.zhangju.xingquban.interestclassapp.refactor.common.utils.FileHelper;
import com.zhangju.xingquban.interestclassapp.refactor.me.bean.MeInterface;
import com.zhangju.xingquban.interestclassapp.refactor.me.bean.ResponseTeachInfo;
import com.zhangju.xingquban.interestclassapp.refactor.me.bean.ResponseUploadImage;
import com.zhangju.xingquban.interestclassapp.refactor.user.EventUserDataChanged;
import com.zhangju.xingquban.interestclassapp.refactor.user.User;
import com.zhangju.xingquban.interestclassapp.refactor.user.UserManager;
import com.zhangju.xingquban.interestclassapp.ui.fragment.home.CityActivity;
import com.zhangju.xingquban.interestclassapp.ui.fragment.me.MyRecrouse.LocationActive;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

/**
 * Created by sgfb on 2017/10/26.
 * 个人资料设置
 */
@ContentView(R.layout.act_person)
public class PersonDataActivity extends FastActivity{
    final int REQ_LOCATION=1;
    final int REQ_LOCATION_DETAIL =2;
    final int REQ_CHANGE_TEACH_AGE=3;
    final int REQ_CHANGE_NICKNAME=4;
    final int REQ_CHANGE_SEX=5;
    final int REQ_QUALIFY=6;
    final int REQ_CONTACT_PHONE=7;
    @Bind(R.id.titleBar)
    TitleBar mTitleBar;
    @Bind(R.id.avatar)
    ImageView mAvatar;
    @Bind(R.id.username)
    TextView mUsername;

    @Override
    protected void alreadyPrepared(){
        mTitleBar.setOnLeftClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        init();
        requestUserData(); //用本地数据初始化后，异步刷新用户数据，广播返回
    }

    private void init(){
        User user= UserManager.getInstance().getUser();
        User.Degree degree=user.degree;

        initCommon(user);
        if(degree!=null){
            if(degree.isOrganization){
                View root=findViewById(R.id.org);
                initOrg(root);
            }
            else if(degree.isTeacher){
                View root=findViewById(R.id.teacher);
                initTeacher(root);
            }
            else{
                View root=findViewById(R.id.normal);
                initNormal(root);
            }
        }
        Glide.with(this)
                .load(user.picture)
                .placeholder(R.mipmap.me_touxiang)
                .error(R.mipmap.me_touxiang)
                .dontAnimate()
                .dontTransform()
                .into(mAvatar);
    }

    private void initCommon(User user){
        mUsername.setText("aaa****bbbb".replace("aaa",safeSubstring(user.phone,0,3)).replace("bbbb",safeSubstring(user.phone,7,11)));
    }

    /**
     * 修改头像
     */
    @Bind(R.id.avatarLayout)
    private void changeAvatar(){
        FastDialog.showListDialog(new String[]{"拍照","从相册中选择"}).show(getSupportFragmentManager(), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if(which==0){
                    openCamera(new PhotoResultListener() {
                        @Override
                        public void onPhotoResult(String path){
                            changeAvatar(path);
                        }
                    });
                }
                else{
                    openAlbum(new PhotoResultListener() {
                        @Override
                        public void onPhotoResult(String path) {
                            changeAvatar(path);
                        }
                    });
                }
            }
        });
    }

    /**
     * 上传并修改用户头像
     * @param path 选中的图像路径
     */
    private void changeAvatar(String path){
        Request request=Request.obtain(CommonInterface.POST_UPLOAD_IMAGE);
        try {
            request.put("files", ImageUtil.getThumbImageFile(1000,90,path, FileHelper.getCacheFolder(this,Environment.DIRECTORY_PICTURES).getAbsolutePath()));
            request.putHeader("X-CustomToken", UserManager.getInstance().getToken());
            request.setListener(new SimpleListener<Response<List<ResponseUploadImage>>>(){

                @Override
                public void onResponseListener(Request r, Response<List<ResponseUploadImage>> result) {
                    if(result.success){
                        if(result.data!=null&&!result.data.isEmpty()){
                            String avatarUrl=result.data.get(0).fileName;
                            updatePersonAvatar(avatarUrl);
                        }
                        else N.showShort(PersonDataActivity.this,"接口异常，上传头像失败");
                    }
                }
            });
            net(request);
        } catch (IOException e) {
            e.printStackTrace();
            N.showShort(this,"图像解析异常");
        }
    }

    /**
     * 更新用户头像
     * @param url 头像url
     */
    private void updatePersonAvatar(final String url){
        Request request=Request.obtain(MeInterface.POST_CHANGE_USER_DATA);
        request.put("id",UserManager.getInstance().getUser().id);
        request.put("picture",url);
        loading();
        request.setListener(new SimpleListener<Response>(){

            @Override
            public void onResponseListener(Request r, Response result) {
                dismissLoading();
                if(result.success){
                    User user=UserManager.getInstance().getUser();
                    user.picture=url;
                    UserManager.getInstance().refreshUser(user);
                }
                else N.showShort(PersonDataActivity.this,"修改头像失败");
            }

            @Override
            public void onErrorListener(Request r, String error) {
                super.onErrorListener(r, error);
                dismissLoading();
            }
        });
        net(request);
    }

    /**
     * 打开身份验证
     */
    @Bind(R.id.teaIdQualityLayout)
    private void openIdQualityLayout(){
        if(UserManager.getInstance().getUser().realnameAuth==1){
            N.showShort(this,"待认证,请耐心等待");
        }
        else{
            Intent intent=new Intent(this,QualifyActivity.class);
            intent.putExtra(QualifyActivity.ARG_INT_TYPE,1);
            startActivityForResult(intent,REQ_QUALIFY);
        }
    }

    /**
     * 打开资格认证
     */
    @Bind({R.id.qualificationLayout,R.id.teaQualifyLayout})
    private void openQualification(){
        if(UserManager.getInstance().getUser().qcAuth==1){
            N.showShort(this,"待认证,请耐心等待");
        }
        else{
            Intent intent=new Intent(this,QualifyActivity.class);
            intent.putExtra(QualifyActivity.ARG_INT_TYPE,2);
            startActivityForResult(intent,REQ_QUALIFY);
        }
    }

    /**
     * 打开平台认证
     */
//    @Bind(R.id.platformQualificationLayout)
//    private void openPlatformQualification(){
//        if(UserManager.getInstance().getUser().contractAuth==1){
//            N.showShort(this,"待认证,请耐心等待");
//        }
//        else{
//            Intent intent=new Intent(this,QualifyActivity.class);
//            intent.putExtra(QualifyActivity.ARG_INT_TYPE,3);
//            startActivityForResult(intent,REQ_QUALIFY);
//        }
//    }

    @Bind(R.id.usernameSettings)
    private void changeUsername(){
        startActivity(RebindPhoneActivity.class);
    }

    @Bind(R.id.passwordSettings)
    private void changePassword(){
        startActivity(ChangePasswordActivity.class);
    }

    @Event
    private void  eUserDataChanged(EventUserDataChanged event){
        init();
    }

    ////////////////以上是通用模块功能，以下根据角色不同改变///////////////////////////////////////////
    /**
     * 教师类型
     */
    @Bind(R.id.teaName)
    TextView mTeaName;
    @Bind(R.id.teaSex)
    TextView mTeaSex;
    @Bind(R.id.teaTeachAge)
    TextView mTeachAge;
    @Bind(R.id.teaSchool)
    TextView mSchoolName;
    @Bind(R.id.teaTeachAddress)
    TextView mTeaTeachAddress;
    @Bind(R.id.teaIdQuality)
    TextView mTeaIdQuality;
    @Bind(R.id.teaQualify)
    TextView mTeaQualify;

    private void initTeacher(View root){
        root.setVisibility(View.VISIBLE);
        inflaterTeacher();
    }

    private void inflaterTeacher(){
        User user=UserManager.getInstance().getUser();
        User.UserMaps map=user.map;

        mTeaName.setText(user.username);
        mTeaIdQuality.setText(getQualityFormat(user.realnameAuth));
        mTeaQualify.setText(getQualityFormat(user.qcAuth));
        if(map!=null){
            mTeaSex.setText(TextUtils.isEmpty(map.sex)?"":user.sex.toUpperCase().equals("M")?"男":"女");
            mTeachAge.setText(String.format(Locale.getDefault(),"%d年",map.teachAge));
            mSchoolName.setText(map.school);
            mTeaTeachAddress.setText(map.classRoom);
            mTeaIdQuality.setText(map.getRealnameAuth());
            mTeaQualify.setText(map.getQcAuth());
        }
    }

    private String getQualityFormat(int type){
        if(type==1) return "待审核";
        else if(type==2) return "已认证";
        else if(type==-1) return "拒绝";
        else return "未认证";
    }

    /**
     * 修改教龄
     */
    @Bind(R.id.teaTeachAgeLayout)
    private void changeTeachAge(){
        startActivityForResult(ChangeTeachAgeActivity.class,REQ_CHANGE_TEACH_AGE);
    }

    /**
     * 修改授课地点
     */
    @Bind({R.id.teaTeachAddressLayout,R.id.teacherAddressLayout})
    private void changeTeachAddress(){
        startActivityForResult(LocationActive.class,REQ_LOCATION_DETAIL);
    }

    /**
     * 机构类型
     */
    @Bind(R.id.educationName)
    TextView mEducationName;
    @Bind(R.id.setupDate)
    TextView mSetupeDate;
    @Bind(R.id.legalPerson)
    TextView mLegalPersonName;
    @Bind(R.id.contactPhone)
    TextView mContactPhone;
    @Bind(R.id.teachAddress)
    TextView mTeachAddress;
    @Bind(R.id.qualification)
    TextView mQualification;

    private void initOrg(View root){
        root.setVisibility(View.VISIBLE);
        requestTeacherOrgInfo();
    }

    private void requestTeacherOrgInfo(){
        Request request=Request.obtain(MeInterface.POST_TEACH_INFO);
        request.put("id",UserManager.getInstance().getUser().teacherTimeId);
        request.setListener(new SimpleListener<Response<List<ResponseTeachInfo>>>(){

            @Override
            public void onResponseListener(Request r, Response<List<ResponseTeachInfo>> result) {
                if(result.success){
                    if(result.data!=null&&!result.data.isEmpty()){
                        ResponseTeachInfo info=result.data.get(0);
                        User user=UserManager.getInstance().getUser();
                        String setupTime=user.addUserTime;
                        //机构
                        mEducationName.setText(info.name);
                        mSetupeDate.setText((TextUtils.isEmpty(setupTime)|| !setupTime.contains(" "))?null:setupTime.substring(0,setupTime.indexOf(" ")));
                        mContactPhone.setText(info.contactTel);
                        mTeachAddress.setText(user.address);
                        mQualification.setText(getQualityFormat(user.qcAuth));
                        mLegalPersonName.setText(UserManager.getInstance().getUser().username);
                    }
                }
            }
        });
        net(request);
    }

    @Bind(R.id.contactPhoneLayout)
    private void changeContactPhone(){
        startActivityForResult(ChangeContactPhoneActivity.class,REQ_CONTACT_PHONE);
    }

    /**
     * 普通用户显示和操作的模块
     */
    @Bind(R.id.nickname)
    TextView mNickname;
    @Bind(R.id.sex)
    TextView mSex;
    @Bind(R.id.location)
    TextView mLocation;


    private void initNormal(View root){
        root.setVisibility(View.VISIBLE);
        inflaterNormal();
    }

    private void inflaterNormal(){
        User user=UserManager.getInstance().getUser();
        mNickname.setText(user.signame);
        mSex.setText(TextUtils.isEmpty(user.sex)?"":("M".equals(user.sex.toUpperCase())?"男":"女"));
        mLocation.setText(user.cityName);
    }

    private String safeSubstring(String s,int start,int end){
        StringBuilder sb=new StringBuilder();
        if(s==null||s.length()==0)
            for(int i=0;i<end;i++) sb.append("*");
        else if(s.length()<end){
            sb.append(s.substring(start,end-s.length()));
            for(int i=0;i<end-s.length();i++) sb.append("*");
        }
        else{
            sb.append(s.substring(start,end));
        }
        return sb.toString();
    }

    @Bind({R.id.nicknameLine,R.id.teaNameLayout,R.id.education})
    private void changeNickname(View view){
        Intent intent=new Intent(this,ChangeNicknameActivity.class);
        int type=-1;
        switch (view.getId()){
            case R.id.nicknameLine:type=1;break;
            case R.id.teaNameLayout:type=2;break;
            case R.id.education:type=3;break;
            default:System.out.println("一个错误的点击事件跳转");break;
        }
        intent.putExtra(ChangeNicknameActivity.ARG_INT_CHANGE_TYPE,type);
        startActivityForResult(intent,REQ_CHANGE_NICKNAME);
    }

    @Bind({R.id.sexLine,R.id.teaSexLayout})
    private void changeSex(){
        startActivityForResult(ChangeSexActivity.class,REQ_CHANGE_SEX);
    }

    @Bind({R.id.locationLine})
    private void changeLocation(){
        startActivityForResult(CityActivity.class,REQ_LOCATION);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode!=RESULT_OK) return;
        requestUserData();
        if(requestCode==REQ_LOCATION){
            String city=data.getStringExtra("city");
            String cityId=data.getStringExtra("cityId");
            changeBindCity(city,cityId);
        }
        else if(requestCode==REQ_LOCATION_DETAIL){
            String lat=data.getStringExtra("lat");
            String lng=data.getStringExtra("lng");
            String provinceId=data.getStringExtra("provinceCode");
            String provinceName=data.getStringExtra("provinceName");
            String address=data.getStringExtra("address");
            String areaId=data.getStringExtra("areaCode");
            String areaName=data.getStringExtra("areaName");
            String cityCode=data.getStringExtra("cityId");
            changeBindTeachAddress(address,areaId,areaName,cityCode,lat,lng,provinceId,provinceName);
        }
    }

    private void changeBindTeachAddress(final String address, String areaId, String areaName, String cityCode, String lat, String lng, String provinceId, String provinceName){
        Request request=Request.obtain(MeInterface.POST_CHANGE_USER_DATA);
        request.put("address",areaName+address)
                .put("areasId",areaId)
                .put("areasName",areaName)
                .put("citycode",cityCode)
                .put("lat",lat)
                .put("lng",lng)
                .put("provinceId",provinceId)
                .put("provinceName",provinceName)
                .put("id",UserManager.getInstance().getUser().id);
        request.setListener(new SimpleListener<Response>(){

            @Override
            public void onResponseListener(Request r, Response result) {
                if(result.success){
                    mTeachAddress.setText(address);
                    N.showShort(PersonDataActivity.this,"修改授课地址成功");
                }
            }
        });
        net(request);
    }

    /**
     * 修改绑定的城市
     * @param city 城市名
     * @param cityId 城市id
     */
    private void changeBindCity(final String city, String cityId){
        loading();
        Request request=Request.obtain(MeInterface.POST_CHANGE_USER_DATA);
        request.put("id",UserManager.getInstance().getUser().id);
        request.put("cityId",cityId);
        request.setListener(new SimpleListener<Response>(){

            @Override
            public void onResponseListener(Request r, Response result) {
                dismissLoading();
                if(result.success) {
                    mLocation.setText(city);
                    N.showShort(PersonDataActivity.this,"修改城市成功");
                }
                else N.showShort(PersonDataActivity.this,"修改城市失败");
            }

            @Override
            public void onErrorListener(Request r, String error) {
                super.onErrorListener(r, error);
                dismissLoading();
            }
        });
        net(request);
    }

    /**
     * 请求用户数据
     */
    private void requestUserData(){
        Request request=Request.obtain(MeInterface.POST_USER_DATA);
        request.setListener(new SimpleListener<Response<List<User>>>(){
            @Override
            public void onResponseListener(Request r, Response<List<User>> result){
                if(result.success){
                    if(result.data!=null&&!result.data.isEmpty()) {
                        UserManager.getInstance().refreshUser(result.data.get(0));
                    }
                }
            }
        });
        net(request);
    }
}