package com.zhangju.xingquban.interestclassapp.ui.fragment.home;

import android.os.Bundle;
import android.view.View;

import com.zhangju.xingquban.R;
import com.zhangju.xingquban.interestclassapp.base.BaseActivity;
import com.zhangju.xingquban.interestclassapp.ui.widget.PublicHead;

import butterknife.BindView;
import butterknife.ButterKnife;

public class HomeDataCity extends BaseActivity {

    @BindView(R.id.home_data_city)
    PublicHead homeDataCity;

    @Override
    public int getLayout() {
        return R.layout.activity_home_data_city;
    }

    @Override
    public void initView() {
        setHomeDataCity();
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

    public void setHomeDataCity() {
        homeDataCity.setTitle("立即导航");
        homeDataCity.setShow(true, false, false);
        homeDataCity.setBackClickListener(new View.OnClickListener() {
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
