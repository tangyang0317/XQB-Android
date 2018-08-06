package com.zhangju.xingquban.interestclassapp.ui.fragment.near;

import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

import com.zhangju.xingquban.R;
import com.zhangju.xingquban.interestclassapp.base.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

//课程列表
public class DistrictActivity_Copy extends BaseActivity implements View.OnClickListener {

    @BindView(R.id.district_return)
    LinearLayout districtReturn;
    @BindView(R.id.near_edit_share)
    LinearLayout nearEditShare;
    @BindView(R.id.fragment_near)
    FrameLayout fragmentNear;

    private NearbySecondFragment nearbySecondFragment;
    private Integer degreeid = null;
    private Bundle bundle;
    private boolean mavgScore;
    private String allStringQuery = null;
    private boolean isSearch = true;

    @Override
    public int getLayout() {
        return R.layout.activity_district_copy;
    }

    @Override
    public void initView() {
        bundle = new Bundle();
        nearbySecondFragment = new NearbySecondFragment();
        if (getIntent().getExtras().containsKey("degreeid")) {
            degreeid = getIntent().getExtras().getInt("degreeid");
            bundle.putString("degreeid", degreeid + "");
        }
        if (getIntent().getExtras().containsKey("avgScore")) {
            mavgScore = getIntent().getExtras().getBoolean("avgScore");
            bundle.putBoolean("avgScore", mavgScore);
        }
        if (getIntent().getExtras().containsKey("allStringQuery")) {
            allStringQuery = getIntent().getExtras().getString("allStringQuery");
            bundle.putString("allStringQuery", allStringQuery);
        }
        if (getIntent().getExtras().containsKey("bannerType")) {
            String banenrType = getIntent().getExtras().getString("bannerType");
            bundle.putString("bannerType", banenrType);
        }
        bundle.putBoolean("isSearch", isSearch);
        nearbySecondFragment.setArguments(bundle);
        getSupportFragmentManager().beginTransaction().add(R.id.fragment_near, nearbySecondFragment).commit();
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
