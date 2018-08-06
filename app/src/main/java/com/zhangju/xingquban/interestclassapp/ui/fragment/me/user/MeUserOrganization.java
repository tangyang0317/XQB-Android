package com.zhangju.xingquban.interestclassapp.ui.fragment.me.user;

import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import com.zhangju.xingquban.R;
import com.zhangju.xingquban.interestclassapp.base.BaseActivity;
import com.zhangju.xingquban.interestclassapp.ui.widget.PublicHead;

import butterknife.BindView;
import butterknife.ButterKnife;

//机构认证
public class MeUserOrganization extends BaseActivity {

    @BindView(R.id.me_user_organization)
    PublicHead meUserOrganization;
    @BindView(R.id.me_user_organizationAdd)
    LinearLayout meUserOrganizationAdd;

    @Override
    public int getLayout() {
        return R.layout.activity_me_user_organization;
    }

    @Override
    public void initView() {
        setMeUserOrganization();
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

    public void setMeUserOrganization() {
        meUserOrganization.setTitle("机构认证");
        meUserOrganization.setShow(true, false, true);
        meUserOrganization.setRightTitle("上传");
        meUserOrganization.setRightTextColor(R.color.color_main);
        meUserOrganization.setBackClickListener(new View.OnClickListener() {
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
