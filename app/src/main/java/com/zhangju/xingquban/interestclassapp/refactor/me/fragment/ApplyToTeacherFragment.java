package com.zhangju.xingquban.interestclassapp.refactor.me.fragment;

import android.app.Activity;
import android.content.Intent;
import android.text.TextUtils;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.fastlib.annotation.Bind;
import com.fastlib.annotation.ContentView;
import com.fastlib.annotation.Event;
import com.fastlib.app.FastFragment;
import com.fastlib.net.Request;
import com.fastlib.net.SimpleListener;
import com.fastlib.utils.N;
import com.zhangju.xingquban.R;
import com.zhangju.xingquban.interestclassapp.bean.live.MultiChooseBean;
import com.zhangju.xingquban.interestclassapp.refactor.common.bean.Response;
import com.zhangju.xingquban.interestclassapp.refactor.common.bean.ResponseCity;
import com.zhangju.xingquban.interestclassapp.refactor.common.bean.ResponseProvince;
import com.zhangju.xingquban.interestclassapp.refactor.me.activity.SelectDegreeActivity;
import com.zhangju.xingquban.interestclassapp.refactor.me.activity.SelectLocationActivity;
import com.zhangju.xingquban.interestclassapp.refactor.me.bean.EventApplyInMember;
import com.zhangju.xingquban.interestclassapp.refactor.me.bean.EventCommit;
import com.zhangju.xingquban.interestclassapp.refactor.me.bean.MeInterface;
import com.zhangju.xingquban.interestclassapp.refactor.user.User;
import com.zhangju.xingquban.interestclassapp.refactor.user.UserManager;
import com.zhangju.xingquban.interestclassapp.ui.activity.find.liveradio.CourseChoiceActivity;

import java.util.ArrayList;
import java.util.Locale;

/**
 * Created by sgfb on 2017/11/13.
 * 申请入驻兴趣班 申请家教页
 */
@ContentView(R.layout.page_apply_to_teacher)
public class ApplyToTeacherFragment extends FastFragment{
    final int REQ_EDUCATION_BG=1;
    final int REQ_LOCATION=2;
    final int REQ_TEACH_COURSE=3;

    @Bind(R.id.name)
    EditText mName;
    @Bind(R.id.sexGroup)
    RadioGroup mSexGroup;
    @Bind(R.id.educationBg)
    TextView mEducationBg;
    @Bind(R.id.schoolName)
    EditText mSchoolName;
    @Bind(R.id.idNumber)
    EditText mIdNumber;
    @Bind(R.id.qualification)
    EditText mQualification;
    @Bind(R.id.teachAge)
    EditText mTeachAge;
    @Bind(R.id.phone)
    EditText mPhone;
    @Bind(R.id.location)
    TextView mLocation;
    @Bind(R.id.address)
    EditText mAddress;
    @Bind(R.id.courses)
    TextView mCourses;
    String mCityId;
    String mAreasId;
    String mProvinceId;
    String mTeachCourseIds;

    @Override
    protected void alreadyPrepared(){

    }

    @Bind(R.id.educationLayout)
    private void selectEducationBg(){
        startActivityForResult(new Intent(getContext(),SelectDegreeActivity.class),REQ_EDUCATION_BG);
    }

    @Bind(R.id.locationLayout)
    private void locationLayout(){
        startActivityForResult(new Intent(getContext(),SelectLocationActivity.class),REQ_LOCATION);
    }

    @Bind(R.id.courseLayout)
    private void selectTeachCourse(){
        Intent intent=new Intent(getContext(),CourseChoiceActivity.class);
        intent.putExtra("type",true);
        startActivityForResult(intent,REQ_TEACH_COURSE);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode!= Activity.RESULT_OK) return;
        if(requestCode==REQ_EDUCATION_BG)
            mEducationBg.setText(data.getStringExtra(SelectDegreeActivity.RES_STR_DEGREE));
        else if(requestCode==REQ_LOCATION){
            ResponseProvince province= (ResponseProvince) data.getSerializableExtra(SelectLocationActivity.RES_SER_PROVINCE);
            ResponseCity city= (ResponseCity) data.getSerializableExtra(SelectLocationActivity.RES_SER_CITY);
            ResponseCity.Area area= (ResponseCity.Area) data.getSerializableExtra(SelectLocationActivity.RES_SER_AREA);
            mProvinceId=province.id;
            mCityId=city.id;
            mAreasId=area.id;
            mLocation.setText(String.format(Locale.getDefault(),"%s-%s-%s",province.name,city.name,area.name));
        }
        else if(requestCode==REQ_TEACH_COURSE){
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
                    mCourses.setText(nameSb.toString());
                }
                mTeachCourseIds =sb.toString();
            }
        }
    }

    @Event
    private void eCommit(EventCommit event){
        if(event.getmCla()==ApplyToTeacherFragment.class){
            String name=mName.getText().toString();
            String sex=mSexGroup.getCheckedRadioButtonId()==R.id.man?"m":"f";
            String qualifications=mEducationBg.getText().toString();
            String idNum=mIdNumber.getText().toString();
            String qualification=mQualification.getText().toString();
            String teachAge=mTeachAge.getText().toString();
            String phone=mPhone.getText().toString();
            String address=mAddress.getText().toString();
            String schoolName=mSchoolName.getText().toString();

            if(TextUtils.isEmpty(name)){
                N.showShort(getContext(),"姓名不能为空");
                return;
            }
            if(TextUtils.isEmpty(qualifications)){
                N.showShort(getContext(),"请填写您的学历");
                return;
            }
            if(TextUtils.isEmpty(schoolName)){
                N.showShort(getContext(),"请填写您的毕业院校");
                return;
            }
            if(TextUtils.isEmpty(idNum)){
                N.showShort(getContext(),"身份证号码不能为空");
                return;
            }
            if(TextUtils.isEmpty(teachAge)){
                N.showShort(getContext(),"教龄不能为空");
                return;
            }
            if(TextUtils.isEmpty(phone)){
                N.showShort(getContext(),"手机号码不能为空");
                return;
            }
            if(TextUtils.isEmpty(mLocation.getText().toString())){
                N.showShort(getContext(),"请输入上课地点");
                return;
            }
            if(TextUtils.isEmpty(address)){
                N.showShort(getContext(),"详细地址不能为空");
                return;
            }
            if(TextUtils.isEmpty(mTeachCourseIds)){
                N.showShort(getContext(),"请选择教授课程");
                return;
            }
            Request request=Request.obtain(MeInterface.POST_APPLY_IN_TEACHER);
            request.put("customerId", UserManager.getInstance().getUser().id);
            request.put("name",name);
            request.put("sex",sex);
            request.put("contactTel",phone);
            request.put("teacherAge",teachAge);
            request.put("IDCard",idNum);
            request.put("classRoom",address);
            request.put("school",schoolName);
            request.put("catagoriesId", mTeachCourseIds);
            request.put("qualifications",qualifications);
            request.put("qCertificate",qualification);
            request.put("degreeTypeString",1);
            request.put("provinceId",mProvinceId);
            request.put("cityId",mCityId);
            request.put("areasId",mAreasId);
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
}