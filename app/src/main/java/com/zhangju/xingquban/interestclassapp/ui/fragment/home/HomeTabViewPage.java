package com.zhangju.xingquban.interestclassapp.ui.fragment.home;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import com.zhangju.xingquban.R;
import com.zhangju.xingquban.interestclassapp.base.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class HomeTabViewPage extends BaseActivity {

    @BindView(R.id.return_image)
    LinearLayout returnImage;

    @Override
    public int getLayout() {
        return R.layout.activity_home_tab_view_page;
    }

    @Override
    public void initView() {
        Intent intent = getIntent();
        String name = intent.getStringExtra("name");

    }

    @Override
    public void initData() {

    }

    @Override
    public void initListener() {

    }

    @OnClick({R.id.return_image})
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.return_image:
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
