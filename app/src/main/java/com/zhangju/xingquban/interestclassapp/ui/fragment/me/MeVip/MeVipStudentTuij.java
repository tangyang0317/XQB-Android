package com.zhangju.xingquban.interestclassapp.ui.fragment.me.MeVip;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.zhangju.xingquban.R;
import com.zhangju.xingquban.interestclassapp.base.BaseActivity;
import com.zhangju.xingquban.interestclassapp.ui.fragment.find.SeekMessage.FindMessageFabuCity;
import com.zhangju.xingquban.interestclassapp.util.RecyclerItemDecoration.DensityUtils;
import com.zhangju.xingquban.interestclassapp.util.RecyclerItemDecoration.SpaceItemDecoration;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MeVipStudentTuij extends BaseActivity {

    @BindView(R.id.me_shengyuanpt_return)
    LinearLayout meShengyuanptReturn;
    @BindView(R.id.me_shengyuanpt_shaixuan)
    TextView meShengyuanptShaixuan;
    @BindView(R.id.me_shengyuanpt_quxiao)
    TextView meShengyuanptQuxiao;
    @BindView(R.id.me_shengyuanpt_image)
    ImageView meShengyuanptImage;
    @BindView(R.id.me_shengyuanpt_recrcley)
    RecyclerView meShengyuanptRecrcley;
    @BindView(R.id.me_shengyuan_kemu)
    RelativeLayout meShengyuanKemu;
    @BindView(R.id.me_shengyuan_syCity)
    RelativeLayout meShengyuanSyCity;
    @BindView(R.id.sy_drawer_layout)
    DrawerLayout syDrawerLayout;

    private LinearLayoutManager linearLayoutManager;
    private List<MeShengYuanBean> list = new ArrayList<>();
    private MeShengYuanAdapter meShengYuanAdapter;
    private ActionBarDrawerToggle toggle;

    @Override
    public int getLayout() {
        return R.layout.activity_me_vip_student_tuij;
    }

    @Override
    public void initView() {
        MeShengYuanBean meShengYuanBean = new MeShengYuanBean();
        list.add(meShengYuanBean);

        for (int i = 0; i < 3; i++) {
            list.add(list.get(0));
        }
        meShengYuanAdapter = new MeShengYuanAdapter(this, list);
        linearLayoutManager = new LinearLayoutManager(this);
        meShengyuanptRecrcley.setLayoutManager(linearLayoutManager);
        meShengyuanptRecrcley.setAdapter(meShengYuanAdapter);
        meShengyuanptRecrcley.setHasFixedSize(true);
        meShengyuanptRecrcley.addItemDecoration(new SpaceItemDecoration(DensityUtils.dp2px(this, 10)));
        setDrawerLayout();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        if (syDrawerLayout.isDrawerOpen(GravityCompat.START)) {
            syDrawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    public void setDrawerLayout() {
        syDrawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED);
        //Toolbar上面最左边显示三杠图标监听DrawerLayout
        toggle = new ActionBarDrawerToggle(
                this, syDrawerLayout, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        syDrawerLayout.setDrawerListener(toggle);
        toggle.syncState();
    }

    @Override
    public void initData() {

    }

    @Override
    public void initListener() {
    }

    @OnClick({R.id.me_shengyuanpt_return, R.id.me_shengyuanpt_shaixuan, R.id.me_shengyuanpt_quxiao, R.id.me_shengyuan_kemu, R.id.me_shengyuan_syCity})
    public void onClick(View v) {
        switch (v.getId()) {

            case R.id.me_shengyuanpt_shaixuan:
                meShengyuanptShaixuan.setVisibility(View.GONE);
                meShengyuanptQuxiao.setVisibility(View.VISIBLE);
                syDrawerLayout.openDrawer(Gravity.RIGHT);
                break;

            case R.id.me_shengyuanpt_quxiao:
                meShengyuanptShaixuan.setVisibility(View.VISIBLE);
                meShengyuanptQuxiao.setVisibility(View.GONE);
                syDrawerLayout.closeDrawer(Gravity.RIGHT);
                break;

            case R.id.me_shengyuan_kemu:
                startActivity(new Intent(this, MeVipStudentSYKm.class));
                break;
            case R.id.me_shengyuan_syCity:
                startActivity(new Intent(this, FindMessageFabuCity.class));
                break;

            case R.id.me_shengyuanpt_return:
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
