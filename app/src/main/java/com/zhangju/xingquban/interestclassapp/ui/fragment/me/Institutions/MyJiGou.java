package com.zhangju.xingquban.interestclassapp.ui.fragment.me.Institutions;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.zhangju.xingquban.R;
import com.zhangju.xingquban.interestclassapp.base.BaseActivity;
import com.zhangju.xingquban.interestclassapp.refactor.user.UserManager;
import com.zhangju.xingquban.interestclassapp.ui.fragment.me.Institutions.jgjj.MeJiGouJgjj;
import com.zhangju.xingquban.interestclassapp.ui.fragment.me.Institutions.kcap.MeJiGouKcap;
import com.zhangju.xingquban.interestclassapp.ui.fragment.me.Institutions.kcgl.MeJiGouKcgl;
import com.zhangju.xingquban.interestclassapp.ui.fragment.me.Institutions.spkc.MeJiGouSpkc;
import com.zhangju.xingquban.interestclassapp.ui.fragment.me.Institutions.xcgl.MeJiGouXcgl;
import com.zhangju.xingquban.interestclassapp.ui.widget.PublicHead;
import com.zhangju.xingquban.interestclassapp.view.imageView.CustomRoundView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/*
    机构管理
 */
public class MyJiGou extends BaseActivity {

    @BindView(R.id.me_laoshijigou_head)
    PublicHead meLaoshijigouHead;
    @BindView(R.id.me_addxqb_work)
    LinearLayout meAddxqbWork;
    @BindView(R.id.me_addxqb_teacher)
    LinearLayout meAddxqbTeacher;
    LinearLayout workRoom;
    @BindView(R.id.image_jigou_bg)
    ImageView imageJigouBg;
    @BindView(R.id.image_work_bg)
    ImageView imageWorkBg;
    @BindView(R.id.me_image_work_false)
    ImageView meImageWorkFalse;
    @BindView(R.id.me_text_work_false)
    TextView meTextWorkFalse;
    @BindView(R.id.me_image_jigou_false)
    ImageView meImageJigouFalse;
    @BindView(R.id.image_teacher_bg)
    ImageView imageTeacherBg;
    @BindView(R.id.me_image_jigou_text)
    TextView meImageJigouText;
    @BindView(R.id.me_image_teacher_false)
    ImageView meImageTeacherFalse;
    @BindView(R.id.me_image_teacher_text)
    TextView meImageTeacherText;
    @BindView(R.id.ruzhu_jigou)
    LinearLayout ruzhuJigou;
    @BindView(R.id.ruzhu_work)
    LinearLayout ruzhuWork;
    @BindView(R.id.ruzhu_teacher)
    LinearLayout ruzhuTeacher;
    @BindView(R.id.mTime_of_organization_set_up)
    TextView mTimeOfOrganizationSetUp;
    @BindView(R.id.me_jg_kcgl)
    LinearLayout meJgKcgl;
    @BindView(R.id.me_jg_kcap)
    LinearLayout meJgKcap;
    @BindView(R.id.me_jg_xcgl)
    LinearLayout meJgXcgl;
    @BindView(R.id.me_jg_spkc)
    LinearLayout meJgSpkc;
    @BindView(R.id.me_jg_jgjj)
    LinearLayout meJgJgjj;
    @BindView(R.id.me_jg_icon)
    CustomRoundView meJgIcon;
    @BindView(R.id.me_jg_name)
    TextView meJgName;
    @BindView(R.id.me_jg_activity)
    LinearLayout meJgActivity;

    @Override
    public int getLayout() {
        return R.layout.activity_my_ji_gou;
    }

    public void getMeTeacherHead() {
        meLaoshijigouHead.setTitle("入驻兴趣班");
        meLaoshijigouHead.setShow(true, false, false);
        meLaoshijigouHead.setBackClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    @Override
    public void initView() {
        getMeTeacherHead();
        try {
            Glide.with(MyJiGou.this).load(UserManager.getInstance().getUser().picture).error(R.mipmap.me_touxiang).into(meJgIcon);
            meJgName.setText(UserManager.getInstance().getUser().username);
        } catch (Exception e) {
            meJgIcon.setImageResource(R.mipmap.me_touxiang);
        }
    }

    @Override
    public void initData() {
        final String time_set_up = mTimeOfOrganizationSetUp.getText().toString();//成立时间
    }

    @Override
    public void initListener() {

    }

    public void setJiGou() {
        imageJigouBg.setImageResource(R.drawable.me_teacher_true);
        meImageJigouFalse.setImageResource(R.mipmap.me_teacher_true);
        meImageJigouText.setTextColor(mContext.getResources().getColor(R.color.color_main));

        imageWorkBg.setImageResource(R.drawable.me_teacher_falser);
        meImageWorkFalse.setImageResource(R.mipmap.me_work_false);
        meTextWorkFalse.setTextColor(mContext.getResources().getColor(R.color.f9b9b9b));

        imageTeacherBg.setImageResource(R.drawable.me_teacher_falser);
        meImageTeacherFalse.setImageResource(R.mipmap.me_family_false);
        meImageTeacherText.setTextColor(mContext.getResources().getColor(R.color.f9b9b9b));

        ruzhuJigou.setVisibility(View.VISIBLE);
        ruzhuWork.setVisibility(View.GONE);
        ruzhuTeacher.setVisibility(View.GONE);
    }

    public void setWork() {
        imageWorkBg.setImageResource(R.drawable.me_teacher_true);
        meImageWorkFalse.setImageResource(R.mipmap.me_work_true);
        meTextWorkFalse.setTextColor(mContext.getResources().getColor(R.color.color_main));

        imageJigouBg.setImageResource(R.drawable.me_teacher_falser);
        meImageJigouFalse.setImageResource(R.mipmap.me_teacher_false);
        meImageJigouText.setTextColor(mContext.getResources().getColor(R.color.f9b9b9b));

        imageTeacherBg.setImageResource(R.drawable.me_teacher_falser);
        meImageTeacherFalse.setImageResource(R.mipmap.me_family_false);
        meImageTeacherText.setTextColor(mContext.getResources().getColor(R.color.f9b9b9b));

        ruzhuWork.setVisibility(View.VISIBLE);
        ruzhuJigou.setVisibility(View.GONE);
        ruzhuTeacher.setVisibility(View.GONE);
    }

    public void setTeacher() {
        imageTeacherBg.setImageResource(R.drawable.me_teacher_true);
        meImageTeacherFalse.setImageResource(R.mipmap.me_family_true);
        meImageTeacherText.setTextColor(mContext.getResources().getColor(R.color.color_main));

        imageJigouBg.setImageResource(R.drawable.me_teacher_falser);
        meImageJigouFalse.setImageResource(R.mipmap.me_teacher_false);
        meImageJigouText.setTextColor(mContext.getResources().getColor(R.color.f9b9b9b));

        imageWorkBg.setImageResource(R.drawable.me_teacher_falser);
        meImageWorkFalse.setImageResource(R.mipmap.me_work_false);
        meTextWorkFalse.setTextColor(mContext.getResources().getColor(R.color.f9b9b9b));

        ruzhuTeacher.setVisibility(View.VISIBLE);
        ruzhuWork.setVisibility(View.GONE);
        ruzhuJigou.setVisibility(View.GONE);
    }

    @OnClick({R.id.image_jigou_bg, R.id.image_work_bg, R.id.image_teacher_bg, R.id.mTime_of_organization_set_up,
            R.id.me_jg_kcgl, R.id.me_jg_kcap, R.id.me_jg_xcgl, R.id.me_jg_spkc, R.id.me_jg_jgjj})
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.image_jigou_bg:
                setJiGou();
                break;
            case R.id.image_work_bg:
                setWork();
                break;
            case R.id.image_teacher_bg:
                setTeacher();
                break;
            case R.id.mTime_of_organization_set_up:

                break;

            //课程管理
            case R.id.me_jg_kcgl:
                startActivity(new Intent(MyJiGou.this, MeJiGouKcgl.class));
                break;

            //课程安排
            case R.id.me_jg_kcap:
                startActivity(new Intent(MyJiGou.this, MeJiGouKcap.class));
                break;

            //相册管理
            case R.id.me_jg_xcgl:
                startActivity(new Intent(MyJiGou.this, MeJiGouXcgl.class));
                break;

            //视频课程
            case R.id.me_jg_spkc:
                startActivity(new Intent(MyJiGou.this, MeJiGouSpkc.class));
                break;

            //机构简介
            case R.id.me_jg_jgjj:
                startActivity(new Intent(MyJiGou.this, MeJiGouJgjj.class));
                break;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
    }
}
