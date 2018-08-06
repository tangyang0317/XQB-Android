package com.zhangju.xingquban.interestclassapp.ui.fragment.me.MeActivity.wodepiaoquan;

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

public class MeActivityWdpj extends BaseActivity {


    @BindView(R.id.me_activity_wdpjHead)
    PublicHead meActivityWdpjHead;
    @BindView(R.id.wdpj_tablayout)
    TabLayout wdpjTablayout;
    @BindView(R.id.wdpj_viewpage)
    ViewPager wdpjViewpage;

    private LiveTabLayoutAdapter liveTabLayoutAdapter;
    private List<Fragment> fragmentList;
    private List<String> stringList;
    private MeActivityWdpqAll meActivityWdpqAll;
    private MeActivityWdpqDaicj meActivityWdpqDaicj;
    private MeActivityWdpqYiwanc meActivityWdpqYiwanc;
    private MeActivityWdpqTuip meActivityWdpqTuip;


    @Override
    public int getLayout() {
        return R.layout.activity_me_wdpj;
    }

    @Override
    public void initView() {
        setMeActivityWdpjHead();
        meActivityWdpqAll = new MeActivityWdpqAll();
        meActivityWdpqDaicj = new MeActivityWdpqDaicj();
        meActivityWdpqYiwanc = new MeActivityWdpqYiwanc();
        meActivityWdpqTuip = new MeActivityWdpqTuip();

        fragmentList = new ArrayList<>();
        fragmentList.add(meActivityWdpqAll);
        fragmentList.add(meActivityWdpqDaicj);
        fragmentList.add(meActivityWdpqYiwanc);
        fragmentList.add(meActivityWdpqTuip);

        stringList = new ArrayList<>();
        stringList.add("关注");
        stringList.add("热门");
        stringList.add("最新");
        stringList.add("书法");

//        tabLayout.setTabMode(TabLayout.MODE_SCROLLABLE);

        wdpjTablayout.addTab(wdpjTablayout.newTab().setText(stringList.get(0)));
        wdpjTablayout.addTab(wdpjTablayout.newTab().setText(stringList.get(1)));
        wdpjTablayout.addTab(wdpjTablayout.newTab().setText(stringList.get(2)));
        wdpjTablayout.addTab(wdpjTablayout.newTab().setText(stringList.get(3)));

        liveTabLayoutAdapter = new LiveTabLayoutAdapter(this.getSupportFragmentManager(), fragmentList, stringList);

        wdpjViewpage.setAdapter(liveTabLayoutAdapter);

        wdpjTablayout.setupWithViewPager(wdpjViewpage);
    }

    @Override
    public void initData() {

    }

    @Override
    public void initListener() {

    }

    @OnClick({})
    public void onClick(View v) {

    }

    public void setMeActivityWdpjHead() {
        meActivityWdpjHead.setTitle("我的票券");
        meActivityWdpjHead.setShow(true, false, false);
        meActivityWdpjHead.setBackClickListener(new View.OnClickListener() {
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
