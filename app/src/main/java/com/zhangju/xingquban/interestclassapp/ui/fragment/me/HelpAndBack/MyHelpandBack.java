package com.zhangju.xingquban.interestclassapp.ui.fragment.me.HelpAndBack;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.zhangju.xingquban.R;
import com.zhangju.xingquban.interestclassapp.base.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MyHelpandBack extends BaseActivity {


    @BindView(R.id.me_helpand_return)
    RelativeLayout meHelpandReturn;
    @BindView(R.id.me_helpand_wanc)
    Button meHelpandWanc;
    @BindView(R.id.me_helpand_fankui)
    EditText meHelpandFankui;
    @BindView(R.id.me_helpand_lxfs)
    EditText meHelpandLxfs;
    @BindView(R.id.me_helpand_photo)
    ImageView meHelpandPhoto;

    @Override
    public int getLayout() {
        return R.layout.activity_my_helpand_back;
    }

    @Override
    public void initView() {

    }

    @Override
    public void initData() {

    }

    @Override
    public void initListener() {

    }

    @OnClick({R.id.me_helpand_wanc, R.id.me_helpand_photo, R.id.me_helpand_return})
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.me_helpand_wanc:

                break;
            case R.id.me_helpand_photo:

                break;
            case R.id.me_helpand_return:

                finish();
                break;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
    }
}
