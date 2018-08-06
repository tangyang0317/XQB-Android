package com.zhangju.xingquban.interestclassapp.ui.fragment.me.Institutions.xcgl;

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

public class MeJiGouXcgl extends BaseActivity {

    @BindView(R.id.me_jg_xcgl)
    PublicHead meJgXcgl;
    @BindView(R.id.meJgxcgl_tablayout)
    TabLayout meJgxcglTablayout;
    @BindView(R.id.meJgxcgl_viewpage)
    ViewPager meJgxcglViewpage;

    private View view;
    private LiveTabLayoutAdapter liveTabLayoutAdapter;
    private List<Fragment> fragmentList;
    private List<String> stringList;
    private MeJiGouXcglSp meJiGouXcglSp;
    private MeJiGouXcglTp meJiGouXcglTp;

    @Override
    public int getLayout() {
        return R.layout.activity_me_ji_gou_xcgl;
    }

    @Override
    public void initView() {
        setMeJgXcglHead();
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

    boolean deleteFlag = false;

    public void setMeJgXcglHead() {
        meJgXcgl.setTitle("更多视频");
        meJgXcgl.setShow(true, false, true);
        meJgXcgl.setRightTitle("编辑");
        meJgXcgl.setRightTextColor(R.color.color_main);
        meJgXcgl.setRightClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                meJiGouXcglSp.chege();
//                MeJiGouXcglSp meJiGouXcglSp = (MeJiGouXcglSp) liveTabLayoutAdapter.getItem(0);
                MeJiGouXcglTp meJiGouXcglTp = (MeJiGouXcglTp) liveTabLayoutAdapter.getItem(1);
//                meJiGouXcglSp.setDelectFlag(deleteFlag = !deleteFlag);
                meJiGouXcglTp.setDelectFlag(deleteFlag = !deleteFlag);
            }
        });
        meJgXcgl.setBackClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void initLayout(View view) {
        meJiGouXcglSp = new MeJiGouXcglSp();
        meJiGouXcglTp = new MeJiGouXcglTp();

        fragmentList = new ArrayList<>();
        fragmentList.add(meJiGouXcglSp);
        fragmentList.add(meJiGouXcglTp);

        stringList = new ArrayList<>();
        stringList.add("视频");
        stringList.add("图片");

//        tabLayout.setTabMode(TabLayout.MODE_SCROLLABLE);

        meJgxcglTablayout.addTab(meJgxcglTablayout.newTab().setText(stringList.get(0)));
        meJgxcglTablayout.addTab(meJgxcglTablayout.newTab().setText(stringList.get(1)));

        liveTabLayoutAdapter = new LiveTabLayoutAdapter(this.getSupportFragmentManager(), fragmentList, stringList);

        meJgxcglViewpage.setAdapter(liveTabLayoutAdapter);

        meJgxcglTablayout.setupWithViewPager(meJgxcglViewpage);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
    }
}
