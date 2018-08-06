package com.zhangju.xingquban.interestclassapp.ui.fragment.me.Collect;

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

public class MyCollect extends BaseActivity {

    @BindView(R.id.me_collect_head)
    PublicHead meCollectHead;
    @BindView(R.id.me_collect_tablayout)
    TabLayout meCollectTablayout;
    @BindView(R.id.me_collect_viewpage)
    ViewPager meCollectViewpage;

    private View view;
    private List<Fragment> fragmentList;
    private List<String> stringList;
    private LiveTabLayoutAdapter liveTabLayoutAdapter;

    private MeCollectAll meCollectAll;
    private MeCollectTeacherAndJG meCollectTeacherAndJG;
    private MeCollectRecrouse meCollectRecrouse;

    @Override
    public int getLayout() {
        return R.layout.activity_my_collect;
    }

    @Override
    public void initView() {
        initLayout(view);
        getMeCollectHead();
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

    public void getMeCollectHead() {
        meCollectHead.setTitle("我的收藏");
        meCollectHead.setShow(true, false, true);
        meCollectHead.setRightTitle("编辑");
        meCollectHead.setRightTextColor(R.color.color_main);
        meCollectHead.setBackClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        meCollectHead.setRightClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

    private void initLayout(View view) {
        meCollectAll = new MeCollectAll();
        meCollectTeacherAndJG = new MeCollectTeacherAndJG();
        meCollectRecrouse = new MeCollectRecrouse();

        fragmentList = new ArrayList<>();
        fragmentList.add(meCollectAll);
        fragmentList.add(meCollectTeacherAndJG);
        fragmentList.add(meCollectRecrouse);

        stringList = new ArrayList<>();
        stringList.add("全部");
        stringList.add("老师/机构");
        stringList.add("资源");

//        tabLayout.setTabMode(TabLayout.MODE_SCROLLABLE);

        meCollectTablayout.addTab(meCollectTablayout.newTab().setText(stringList.get(0)));
        meCollectTablayout.addTab(meCollectTablayout.newTab().setText(stringList.get(1)));
        meCollectTablayout.addTab(meCollectTablayout.newTab().setText(stringList.get(2)));

        liveTabLayoutAdapter = new LiveTabLayoutAdapter(this.getSupportFragmentManager(), fragmentList, stringList);

        meCollectViewpage.setAdapter(liveTabLayoutAdapter);

        meCollectTablayout.setupWithViewPager(meCollectViewpage);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
    }
}
