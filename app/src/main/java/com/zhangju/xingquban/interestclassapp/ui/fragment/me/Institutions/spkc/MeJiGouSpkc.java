package com.zhangju.xingquban.interestclassapp.ui.fragment.me.Institutions.spkc;

import android.content.Intent;
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
import butterknife.OnClick;

public class MeJiGouSpkc extends BaseActivity {

    @BindView(R.id.me_jg_spkc_head)
    PublicHead meJgSpkcHead;
    @BindView(R.id.meSpkc_tablayout)
    TabLayout meSpkcTablayout;
    @BindView(R.id.meSpkc_viewpage)
    ViewPager meSpkcViewpage;
    private View view;

    private LiveTabLayoutAdapter liveTabLayoutAdapter;
    private List<Fragment> fragmentList;
    private List<String> stringList;
    private MeJiGouSpkcZx meJiGouSpkcZx;
    private MeJiGouSpkcSpj meJiGouSpkcSpj;

    @Override
    public int getLayout() {
        return R.layout.activity_me_ji_gou_spkc;
    }

    @Override
    public void initView() {
        setMeJgSpkcHead();
        initLayout(view);
    }

    @Override
    public void initData() {

    }

    @Override
    public void initListener() {

    }

    @OnClick({})
    public void onClick(View v) {
        switch (v.getId()) {

        }
    }

    private void initLayout(View view) {
        meJiGouSpkcZx = new MeJiGouSpkcZx();
        meJiGouSpkcSpj = new MeJiGouSpkcSpj();


        fragmentList = new ArrayList<>();
        fragmentList.add(meJiGouSpkcZx);
        fragmentList.add(meJiGouSpkcSpj);

        stringList = new ArrayList<>();
        stringList.add("最近");
        stringList.add("视频夹");

//        tabLayout.setTabMode(TabLayout.MODE_SCROLLABLE);

        meSpkcTablayout.addTab(meSpkcTablayout.newTab().setText(stringList.get(0)));
        meSpkcTablayout.addTab(meSpkcTablayout.newTab().setText(stringList.get(1)));

        liveTabLayoutAdapter = new LiveTabLayoutAdapter(this.getSupportFragmentManager(), fragmentList, stringList);

        meSpkcViewpage.setAdapter(liveTabLayoutAdapter);

        meSpkcTablayout.setupWithViewPager(meSpkcViewpage);
    }

    public void setMeJgSpkcHead() {
        meJgSpkcHead.setTitle("我的视频");
        meJgSpkcHead.setShow(true, false, true);
        meJgSpkcHead.setRightTitle("上传");
        meJgSpkcHead.setRightTextColor(R.color.color_main);
        meJgSpkcHead.setBackClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        meJgSpkcHead.setRightClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivityForResult(new Intent(MeJiGouSpkc.this, MeJiGouSpkcUploading.class), 1);
            }
        });
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
    }
}
