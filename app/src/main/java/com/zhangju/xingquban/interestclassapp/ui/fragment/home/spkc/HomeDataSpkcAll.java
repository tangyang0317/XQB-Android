package com.zhangju.xingquban.interestclassapp.ui.fragment.home.spkc;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.View;

import com.zhangju.xingquban.R;
import com.zhangju.xingquban.interestclassapp.base.BaseActivity;
import com.zhangju.xingquban.interestclassapp.ui.fragment.live.LiveTabLayoutFragment.LiveTabLayoutAdapter;
import com.zhangju.xingquban.interestclassapp.ui.widget.PublicHead;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class HomeDataSpkcAll extends BaseActivity {


    @BindView(R.id.home_data_spkc_allHead)
    PublicHead homeDataSpkcAllHead;
    @BindView(R.id.spkcAll_tablayout)
    TabLayout spkcAllTablayout;
    @BindView(R.id.spkcAll_viewpage)
    ViewPager spkcAllViewpage;

    private LiveTabLayoutAdapter liveTabLayoutAdapter;
    private List<Fragment> fragmentList;
    private List<String> stringList;
    private HomeDataSpkcAllSp homeDataSpkcAllSp;
    private HomeDataSpkcAllTp homeDataSpkcAllTp;

    @Override
    public int getLayout() {
        return R.layout.activity_home_data_spkc_all;
    }

    @Override
    public void initView() {
        setHomeDataSpkcAllHead();
        setSpkcAllTablayout();
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

    public void setSpkcAllTablayout() {
        homeDataSpkcAllSp = new HomeDataSpkcAllSp();
        homeDataSpkcAllTp = new HomeDataSpkcAllTp();

        fragmentList = new ArrayList<>();
        fragmentList.add(homeDataSpkcAllSp);
        fragmentList.add(homeDataSpkcAllTp);

        stringList = new ArrayList<>();
        stringList.add("视频");
        stringList.add("图片");

//        tabLayout.setTabMode(TabLayout.MODE_SCROLLABLE);

        spkcAllTablayout.addTab(spkcAllTablayout.newTab().setText(stringList.get(0)));
        spkcAllTablayout.addTab(spkcAllTablayout.newTab().setText(stringList.get(1)));

        liveTabLayoutAdapter = new LiveTabLayoutAdapter(this.getSupportFragmentManager(), fragmentList, stringList);

        spkcAllViewpage.setAdapter(liveTabLayoutAdapter);

        spkcAllTablayout.setupWithViewPager(spkcAllViewpage);
    }

    public void setHomeDataSpkcAllHead() {
        homeDataSpkcAllHead.setTitle("更多视频");
        homeDataSpkcAllHead.setShow(true, false, false);
        homeDataSpkcAllHead.setBackClickListener(new View.OnClickListener() {
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
