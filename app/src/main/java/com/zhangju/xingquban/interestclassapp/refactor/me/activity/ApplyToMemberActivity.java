package com.zhangju.xingquban.interestclassapp.refactor.me.activity;

import android.content.Intent;
import android.view.View;
import android.widget.CheckBox;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.fastlib.annotation.Bind;
import com.fastlib.annotation.ContentView;
import com.fastlib.app.EventObserver;
import com.fastlib.app.FastActivity;
import com.fastlib.utils.N;
import com.fastlib.utils.Utils;
import com.fastlib.widget.TitleBar;
import com.zhangju.xingquban.R;
import com.zhangju.xingquban.interestclassapp.refactor.common.activity.WebViewActivity;
import com.zhangju.xingquban.interestclassapp.refactor.common.bean.CommonInterface;
import com.zhangju.xingquban.interestclassapp.refactor.location.LocationManager;
import com.zhangju.xingquban.interestclassapp.refactor.me.bean.EventCommit;
import com.zhangju.xingquban.interestclassapp.refactor.me.fragment.ApplyToClassroomFragment;
import com.zhangju.xingquban.interestclassapp.refactor.me.fragment.ApplyToOrgFragment;
import com.zhangju.xingquban.interestclassapp.refactor.me.fragment.ApplyToTeacherFragment;

/**
 * Created by sgfb on 2017/11/15.
 * 申请成为老师/机构
 */
@ContentView(R.layout.act_apply_to_org)
public class ApplyToMemberActivity extends FastActivity{
    @Bind(R.id.titleBar)
    TitleBar mTitleBar;
    @Bind(R.id.hint)
    TextView mHint;
    @Bind(R.id.checkAgreement)
    CheckBox mCheckAgreement;
    @Bind(R.id.radioGroup)
    RadioGroup mRadioGroup;
    ApplyToOrgFragment mToOrgFragment;
    ApplyToClassroomFragment mToClassroomFragment;
    ApplyToTeacherFragment mToTeacherFragment;

    @Override
    protected void alreadyPrepared() {
        mHint.setText(Utils.getTextSomeOtherColor(2,3,mHint.getText().toString(),getResources().getColor(R.color.EF4E4C)));
        mToClassroomFragment=new ApplyToClassroomFragment();
        mToTeacherFragment=new ApplyToTeacherFragment();
        mToOrgFragment=new ApplyToOrgFragment();
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.content,mToOrgFragment)
                .commit();
        mTitleBar.setOnLeftClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    @Bind(R.id.typeOrg)
    private void selectOrgType(){
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.content,mToOrgFragment)
                .commit();
    }

    @Bind(R.id.typeClass)
    private void selectClassType(){
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.content,mToClassroomFragment)
                .commit();
    }

    @Bind(R.id.typeTeacher)
    private void selectTeacher(){
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.content,mToTeacherFragment)
                .commit();
    }

    @Bind(R.id.toAgreementDetail)
    private void toAgreementDetail(){
        Intent intent=new Intent(this,WebViewActivity.class);
        intent.putExtra(WebViewActivity.ARG_URL, CommonInterface.URL_TO_MEMBER_AGREEMENT);
        startActivity(intent);
    }

    @Bind(R.id.commit)
    private void commit(){
        if(mCheckAgreement.isChecked()){
            Class cla=null;
            if(mRadioGroup.getCheckedRadioButtonId()==R.id.typeOrg)
                cla=ApplyToOrgFragment.class;
            else if(mRadioGroup.getCheckedRadioButtonId()==R.id.typeClass)
                cla=ApplyToClassroomFragment.class;
            else if(mRadioGroup.getCheckedRadioButtonId()==R.id.typeTeacher) cla=ApplyToTeacherFragment.class;
            if(cla!=null)
               EventObserver.getInstance().sendEvent(this, new EventCommit(cla));
            else System.out.println("内部位置错误，无法提交入驻兴趣班事务");
        }
        else N.showShort(this,"请勾选同意入驻协议");
    }
}