package com.zhangju.xingquban.interestclassapp.ui.activity.near;

import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

import com.zhangju.xingquban.R;
import com.zhangju.xingquban.interestclassapp.base.BaseActivity;
import com.zhangju.xingquban.interestclassapp.ui.fragment.near.NearPointbyFragment;

import butterknife.BindView;
import butterknife.ButterKnife;

//课程列表
public class HomeDistrictActivity extends BaseActivity implements View.OnClickListener {

    @BindView(R.id.district_return)
    LinearLayout districtReturn;
    @BindView(R.id.near_edit_share)
    LinearLayout nearEditShare;
    @BindView(R.id.fragment_near)
    FrameLayout fragmentNear;

    private NearPointbyFragment nearPointbyFragment;
    private Integer degreeid = null;
    private Bundle bundle;
    private int categoryId;

    @Override
    public int getLayout() {
        return R.layout.activity_district_copy;
    }

    @Override
    public void initView() {
        nearPointbyFragment = new NearPointbyFragment();
        categoryId = getIntent().getExtras().getInt("categoryId");
        bundle = new Bundle();
        bundle.putInt("categoryId", categoryId);
        nearPointbyFragment.setArguments(bundle);
        getSupportFragmentManager().beginTransaction().add(R.id.fragment_near, nearPointbyFragment).commit();
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
    }
}
