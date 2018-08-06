package com.zhangju.xingquban.interestclassapp.ui.fragment.home.msfc;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.zhangju.xingquban.R;
import com.zhangju.xingquban.interestclassapp.base.BaseActivity;
import com.zhangju.xingquban.interestclassapp.ui.fragment.home.HomeDataTeacherBean;
import com.zhangju.xingquban.interestclassapp.ui.widget.PublicHead;
import com.zhangju.xingquban.interestclassapp.view.imageView.CustomRoundView;

import butterknife.BindView;
import butterknife.ButterKnife;

public class HomeDataMsfcXq extends BaseActivity {


    @BindView(R.id.home_data_msfcXq_Head)
    PublicHead homeDataMsfcXqHead;
    @BindView(R.id.headicon)
    CustomRoundView headicon;
    @BindView(R.id.teac_name)
    TextView teacName;
    @BindView(R.id.teac_shuxing)
    TextView teacShuxing;
    @BindView(R.id.tec_age)
    TextView tecAge;
    @BindView(R.id.tv_schoole)
    TextView tvSchoole;

    @BindView(R.id.headpic_2)
    CustomRoundView headpic2;
    @BindView(R.id.teac_name2)
    TextView teacName2;
    @BindView(R.id.minghao)
    TextView minghao;
    @BindView(R.id.miaoshu)
    TextView miaoshu;
    @BindView(R.id.line_nl)
    View linenl;

    private HomeDataTeacherBean.AaDataBean.ResesBean teacher;
    @Override
    public int getLayout() {
        return R.layout.activity_home_data_msfc_xq;
    }

    @Override
    public void initView() {
        teacher= (HomeDataTeacherBean.AaDataBean.ResesBean) getIntent().getExtras().getSerializable("teacher");
        setHomeDataMsfcXqHead();
        bindDataToView();
    }
    private void bindDataToView(){
        Glide.with(HomeDataMsfcXq.this).load(teacher.getPicture()).into(headicon);
        Glide.with(HomeDataMsfcXq.this).load(teacher.getPicture()).into(headpic2);
        teacName.setText(teacher.getName());
        teacName2.setText(teacher.getName());
        teacShuxing.setText("资深"+teacher.getCatagoryName()+"教师");
        if (teacher.getAge()==0){
            tecAge.setVisibility(View.GONE);
        }
        tecAge.setText(teacher.getAge()+"年教龄");
        if (teacher.getSchool()==null){
            linenl.setVisibility(View.GONE);
            tvSchoole.setVisibility(View.GONE);
        }
        tvSchoole.setText("毕业于"+teacher.getSchool());
        minghao.setText("中国高级"+teacher.getCatagoryName()+"英皇考级培训导师");
        miaoshu.setText(teacher.getSummary());
    }
    @Override
    public void initData() {

    }

    @Override
    public void initListener() {

    }

    @Override
    public void onClick(View v) {

    }

    public void setHomeDataMsfcXqHead() {
        homeDataMsfcXqHead.setTitle("老师详情");
        homeDataMsfcXqHead.setShow(true, false, false);
        homeDataMsfcXqHead.setBackClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
    }
}
