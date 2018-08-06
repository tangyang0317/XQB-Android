package com.zhangju.xingquban.interestclassapp.ui.fragment.me.MeActivity.wofabude.baomingguanli;

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

public class MeActivityWfbdDataBmgl extends BaseActivity {
    private LiveTabLayoutAdapter liveTabLayoutAdapter;
    private List<Fragment> fragmentList;
    private List<String> stringList;
    private MeActivityWfbdDataBmglAll meActivityWfbdDataBmglAll;
    private MeActivityWfbdDataDcj meActivityWfbdDataDcj;
    private MeActivityWfbdDataBmglYwanc meActivityWfbdDataBmglYwanc;
    private MeActivityWfbdDataBmglDtp meActivityWfbdDataBmglDtp;


    @BindView(R.id.me_activity_wfbd_bmglHead)
    PublicHead meActivityWfbdBmglHead;
    @BindView(R.id.wfbd_bmgl_tablayout)
    TabLayout wfbdBmglTablayout;
    @BindView(R.id.wfbd_bmgl_viewpage)
    ViewPager wfbdBmglViewpage;

    @Override
    public int getLayout() {
        return R.layout.activity_me_wfbd_data_bmgl;
    }

    @Override
    public void initView() {
        setMeActivityWfbdBmglHead();
        meActivityWfbdDataBmglAll = new MeActivityWfbdDataBmglAll();
        meActivityWfbdDataDcj = new MeActivityWfbdDataDcj();
        meActivityWfbdDataBmglYwanc = new MeActivityWfbdDataBmglYwanc();
        meActivityWfbdDataBmglDtp = new MeActivityWfbdDataBmglDtp();


        fragmentList = new ArrayList<>();
        fragmentList.add(meActivityWfbdDataBmglAll);
        fragmentList.add(meActivityWfbdDataDcj);
        fragmentList.add(meActivityWfbdDataBmglYwanc);
        fragmentList.add(meActivityWfbdDataBmglDtp);

        stringList = new ArrayList<>();
        stringList.add("全部");
        stringList.add("待参加");
        stringList.add("已完成");
        stringList.add("待退票");

//        tabLayout.setTabMode(TabLayout.MODE_SCROLLABLE);

        wfbdBmglTablayout.addTab(wfbdBmglTablayout.newTab().setText(stringList.get(0)));
        wfbdBmglTablayout.addTab(wfbdBmglTablayout.newTab().setText(stringList.get(1)));
        wfbdBmglTablayout.addTab(wfbdBmglTablayout.newTab().setText(stringList.get(2)));
        wfbdBmglTablayout.addTab(wfbdBmglTablayout.newTab().setText(stringList.get(3)));

        liveTabLayoutAdapter = new LiveTabLayoutAdapter(this.getSupportFragmentManager(), fragmentList, stringList);

        wfbdBmglViewpage.setAdapter(liveTabLayoutAdapter);

        wfbdBmglTablayout.setupWithViewPager(wfbdBmglViewpage);
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

    public void setMeActivityWfbdBmglHead() {
        meActivityWfbdBmglHead.setTitle("报名管理");
        meActivityWfbdBmglHead.setShow(true, false, false);
        meActivityWfbdBmglHead.setBackClickListener(new View.OnClickListener() {
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
