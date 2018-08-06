package com.zhangju.xingquban.interestclassapp.ui.fragment.find.Wenda;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;

import com.zhangju.xingquban.R;
import com.zhangju.xingquban.interestclassapp.base.BaseActivity;
import com.zhangju.xingquban.interestclassapp.hplper.TabLayoutSetIndicator;
import com.zhangju.xingquban.interestclassapp.ui.fragment.live.LiveTabLayoutFragment.LiveTabLayoutAdapter;
import com.zhangju.xingquban.interestclassapp.ui.widget.PublicHead;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
//我的草稿

public class FindWendaCaog extends BaseActivity {


    @BindView(R.id.find_wenda_cgHead)
    PublicHead findWendaCgHead;
    @BindView(R.id.find_wendaCg_tablayout)
    TabLayout findWendaCgTablayout;
    @BindView(R.id.find_wendaCg_viewpage)
    ViewPager findWendaCgViewpage;

    private LiveTabLayoutAdapter liveTabLayoutAdapter;
    private List<Fragment> fragmentList;
    private List<String> stringList;
    private FindWDCgFragmentTiWen findWDCgFragmentTiWen;
    private FindWDCgFragmentWenda findWDCgFragmentWenda;
//    private List<FindTWBean> list = new ArrayList<>();
    private FindTWAdapter findTWAdapter;
    private LinearLayoutManager linearLayoutManager;

    @Override
    public int getLayout() {
        return R.layout.activity_find_wenda_caog;
    }

    @Override
    public void initView() {
        setFindWendaCgHead();

    }

    @Override
    public void initData() {
        findWDCgFragmentTiWen = new FindWDCgFragmentTiWen();
        findWDCgFragmentWenda = new FindWDCgFragmentWenda();

        fragmentList = new ArrayList<>();
        fragmentList.add(findWDCgFragmentTiWen);
        fragmentList.add(findWDCgFragmentWenda);

        stringList = new ArrayList<>();
        stringList.add("提问草稿");
        stringList.add("问答草稿");

//        tabLayout.setTabMode(TabLayout.MODE_SCROLLABLE);

        findWendaCgTablayout.addTab(findWendaCgTablayout.newTab().setText(stringList.get(0)));
        findWendaCgTablayout.addTab(findWendaCgTablayout.newTab().setText(stringList.get(1)));

        liveTabLayoutAdapter = new LiveTabLayoutAdapter(this.getSupportFragmentManager(), fragmentList, stringList);

        findWendaCgViewpage.setAdapter(liveTabLayoutAdapter);

        findWendaCgTablayout.setupWithViewPager(findWendaCgViewpage);
        findWendaCgTablayout.post(new Runnable() {
            @Override
            public void run() {
                TabLayoutSetIndicator.setIndicator(findWendaCgTablayout, 60, 60);
            }
        });
    }

    @Override
    public void initListener() {

    }

    @Override
    public void onClick(View v) {

    }

    public void setFindWendaCgHead() {
        findWendaCgHead.setTitle("我的草稿");
        findWendaCgHead.setShow(true, false, false);
        findWendaCgHead.setBackClickListener(new View.OnClickListener() {
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
