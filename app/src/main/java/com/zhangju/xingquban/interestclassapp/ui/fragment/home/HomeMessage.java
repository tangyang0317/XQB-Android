package com.zhangju.xingquban.interestclassapp.ui.fragment.home;

import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import com.zhangju.xingquban.R;
import com.zhangju.xingquban.interestclassapp.base.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class HomeMessage extends BaseActivity {

    @BindView(R.id.home_message_return)
    LinearLayout homeMessageReturn;

    @Override
    public int getLayout() {
        return R.layout.activity_home_message;
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

    @OnClick({R.id.home_message_return})
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.home_message_return:
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
