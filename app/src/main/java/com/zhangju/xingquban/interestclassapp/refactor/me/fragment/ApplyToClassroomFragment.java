package com.zhangju.xingquban.interestclassapp.refactor.me.fragment;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.text.TextUtils;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;

import com.fastlib.annotation.Bind;
import com.fastlib.annotation.ContentView;
import com.fastlib.annotation.Event;
import com.fastlib.app.FastFragment;
import com.fastlib.net.Request;
import com.fastlib.net.SimpleListener;
import com.fastlib.utils.N;
import com.fastlib.utils.TimeUtil;
import com.zhangju.xingquban.R;
import com.zhangju.xingquban.interestclassapp.bean.live.MultiChooseBean;
import com.zhangju.xingquban.interestclassapp.refactor.common.bean.Response;
import com.zhangju.xingquban.interestclassapp.refactor.common.bean.ResponseCity;
import com.zhangju.xingquban.interestclassapp.refactor.common.bean.ResponseProvince;
import com.zhangju.xingquban.interestclassapp.refactor.me.activity.SelectLocationActivity;
import com.zhangju.xingquban.interestclassapp.refactor.me.bean.EventCommit;
import com.zhangju.xingquban.interestclassapp.refactor.me.bean.MeInterface;
import com.zhangju.xingquban.interestclassapp.refactor.user.User;
import com.zhangju.xingquban.interestclassapp.refactor.user.UserManager;
import com.zhangju.xingquban.interestclassapp.ui.activity.find.liveradio.CourseChoiceActivity;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

/**
 * Created by sgfb on 2017/11/15.
 * 申请开办工作室
 */
@ContentView(R.layout.page_apply_to_org)
public class ApplyToClassroomFragment extends FastFragment{
    final int REQ_COURSE=1;
    final int REQ_LOCATION=2;
    Calendar mCalendar=Calendar.getInstance();
    @Bind(R.id.licenseLayout)
    View mLicenseLayout;
    @Bind(R.id.classroomName)
    EditText mClassName;
    @Bind(R.id.legalName)
    EditText mLegalName;
    @Bind(R.id.phone)
    EditText mPhone;
    @Bind(R.id.address)
    EditText mAddress;
    @Bind(R.id.setUpDate)
    TextView mSetupDate;
    @Bind(R.id.courses)
    TextView mCourse;
    @Bind(R.id.location)
    TextView mLocation;
    @Bind(R.id.legalTitle)
    TextView mLegalTitle;
    String mCourseIds;
    String mProvinceId;
    String mCityId;
    String mAreaId;

    @Override
    protected void alreadyPrepared() {
        mLicenseLayout.setVisibility(View.GONE);
        mLegalTitle.setText("联系人");
        mLegalName.setHint("请输入联系人姓名");
    }

    @Bind(R.id.setupDateLayout)
    private void changeSetupDate(){
        DatePickerDialog dialog=new DatePickerDialog(getContext(), new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                mCalendar.set(year,month,dayOfMonth);
                mSetupDate.setText(TimeUtil.dateToString(mCalendar.getTime(),"yyyy-MM-dd"));
            }
        },mCalendar.get(Calendar.YEAR),mCalendar.get(Calendar.MONTH),mCalendar.get(Calendar.DAY_OF_MONTH));
        dialog.show();
    }

    @Bind(R.id.courseLayout)
    private void selectTeachCourse(){
        Intent intent=new Intent(getContext(),CourseChoiceActivity.class);
        intent.putExtra("type",true);
        startActivityForResult(intent,REQ_COURSE);
    }

    @Bind(R.id.locationLayout)
    private void changeLocation(){
        startActivityForResult(new Intent(getContext(), SelectLocationActivity.class),REQ_LOCATION);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode!= Activity.RESULT_OK) return;
        if(requestCode==REQ_COURSE){
            ArrayList<MultiChooseBean> list= (ArrayList<MultiChooseBean>) data.getSerializableExtra("subject");
            if(list!=null){
                StringBuilder sb=new StringBuilder();
                StringBuilder nameSb=new StringBuilder();
                for(MultiChooseBean bean:list){
                    sb.append(bean.getId()).append(",");
                    nameSb.append(bean.getName()).append(",");
                }
                if(sb.length()>0) sb.deleteCharAt(sb.length()-1);
                if(nameSb.length()>0){
                    nameSb.deleteCharAt(nameSb.length()-1);
                    mCourse.setText(nameSb.toString());
                }
                mCourseIds =sb.toString();
            }
        }
        else if(requestCode==REQ_LOCATION){
            ResponseProvince province= (ResponseProvince) data.getSerializableExtra(SelectLocationActivity.RES_SER_PROVINCE);
            ResponseCity city= (ResponseCity) data.getSerializableExtra(SelectLocationActivity.RES_SER_CITY);
            ResponseCity.Area area= (ResponseCity.Area) data.getSerializableExtra(SelectLocationActivity.RES_SER_AREA);

            mProvinceId=province.id;
            mCityId=city.id;
            mAreaId=area.id;
            mLocation.setText(String.format(Locale.getDefault(),"%s-%s-%s",province.name,city.name,area.name));
        }
    }

    @Event
    private void eCommit(EventCommit event){
        String name=mClassName.getText().toString();
        String setupTime=mSetupDate.getText().toString();
        String legal=mLegalName.getText().toString();
        String phone=mPhone.getText().toString();
        String address=mAddress.getText().toString();

        if(TextUtils.isEmpty(name)){
            N.showShort(getContext(),"工作室名称不能为空");
            return;
        }
        if(TextUtils.isEmpty(setupTime)){
            N.showShort(getContext(),"成立时间不能为空");
            return;
        }
        if(TextUtils.isEmpty(phone)){
            N.showShort(getContext(),"联系人电话不能为空");
            return;
        }
        if(TextUtils.isEmpty(mCourseIds)){
            N.showShort(getContext(),"请选择授课科目");
            return;
        }
        if(TextUtils.isEmpty(mLocation.getText().toString())){
            N.showShort(getContext(),"请选择授课地点");
            return;
        }
        if(TextUtils.isEmpty(address)){
            N.showShort(getContext(),"详细地址不能为空");
            return;
        }
        Request request=Request.obtain(MeInterface.POST_APPLY_IN_TEACHER)
                .put("customerId", UserManager.getInstance().getUser().id)
                .put("degreeTypeString","2")
                .put("studioOrNot","true")
                .put("companyName",name)
                .put("institutionBuiltTime",setupTime)
                .put("contactTel",phone)
                .put("catagoriesId",mCourseIds)
                .put("provinceId",mProvinceId)
                .put("cityId",mCityId)
                .put("areasId",mAreaId)
                .put("name",legal)
                .put("classRoom",address);
        request.setListener(new SimpleListener<Response>(){

            @Override
            public void onResponseListener(Request r, Response result) {
                if(result.success){
                    N.showShort(getContext(),"申请成功，请耐心等待审核结果");
                    User user=UserManager.getInstance().getUser();
                    user.status=1;
                    UserManager.getInstance().refreshUser(user);
                    getActivity().finish();
                }
            }
        });
        net(request);
    }
}
