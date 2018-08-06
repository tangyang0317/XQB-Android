package com.zhangju.xingquban.interestclassapp.ui.fragment.find.SeekActivity;

import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.View;

import com.zhangju.xingquban.R;
import com.zhangju.xingquban.interestclassapp.base.BaseActivity;
import com.zhangju.xingquban.interestclassapp.ui.fragment.find.SeekActivity.huwai.FindSeekOutdoors;
import com.zhangju.xingquban.interestclassapp.ui.fragment.find.SeekActivity.jingxuan.FindSeekChoiceness;
import com.zhangju.xingquban.interestclassapp.ui.fragment.find.SeekActivity.paidui.FindSeekParty;
import com.zhangju.xingquban.interestclassapp.ui.fragment.find.SeekActivity.remen.FindSeekHot;
import com.zhangju.xingquban.interestclassapp.ui.fragment.find.SeekActivity.shalong.FindSeekSalon;
import com.zhangju.xingquban.interestclassapp.ui.fragment.live.LiveTabLayoutFragment.LiveTabLayoutAdapter;
import com.zhangju.xingquban.interestclassapp.ui.widget.PublicHead;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class FindActivity extends BaseActivity {
    private Context mContext = this;
    private View view;

    @BindView(R.id.find_seekactivity)
    PublicHead findSeekactivity;
    @BindView(R.id.find_seek_tablayout)
    TabLayout findSeekTablayout;
    @BindView(R.id.find_seek_viewpage)
    ViewPager findSeekViewpage;
    private LiveTabLayoutAdapter liveTabLayoutAdapter;

    private FindSeekChoiceness findSeekChoiceness;
    private FindSeekHot findSeekHot;
    private FindSeekOutdoors findSeekOutdoors;
    private FindSeekParty findSeekParty;
    private FindSeekSalon findSeekSalon;

    private List<String> stringList;
    private List<Fragment> fragmentList;

    @Override
    public int getLayout() {
        return R.layout.activity_find;
    }

    public void setFindSeekActivityHead() {
        findSeekactivity.setTitle("活动");
        findSeekactivity.setRightTitle("我的");
        findSeekactivity.setRightTextColor(R.color.color_main);
        findSeekactivity.setBackClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        findSeekactivity.setRightClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

    @Override
    public void initView() {
        initLayout(view);
        setFindSeekActivityHead();
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

    private void initLayout(View view) {
        findSeekChoiceness = new FindSeekChoiceness();
        findSeekHot = new FindSeekHot();
        findSeekOutdoors = new FindSeekOutdoors();
        findSeekParty = new FindSeekParty();
        findSeekSalon = new FindSeekSalon();

        fragmentList = new ArrayList<>();
        fragmentList.add(findSeekChoiceness);
        fragmentList.add(findSeekHot);
        fragmentList.add(findSeekOutdoors);
        fragmentList.add(findSeekParty);
        fragmentList.add(findSeekSalon);

        stringList = new ArrayList<>();
        stringList.add("精选");
        stringList.add("热门");
        stringList.add("户外");
        stringList.add("派对");
        stringList.add("沙龙");

//        tabLayout.setTabMode(TabLayout.MODE_SCROLLABLE);

        findSeekTablayout.addTab(findSeekTablayout.newTab().setText(stringList.get(0)));
        findSeekTablayout.addTab(findSeekTablayout.newTab().setText(stringList.get(1)));
        findSeekTablayout.addTab(findSeekTablayout.newTab().setText(stringList.get(2)));
        findSeekTablayout.addTab(findSeekTablayout.newTab().setText(stringList.get(3)));
        findSeekTablayout.addTab(findSeekTablayout.newTab().setText(stringList.get(4)));

        liveTabLayoutAdapter = new LiveTabLayoutAdapter(this.getSupportFragmentManager(), fragmentList, stringList);

        findSeekViewpage.setAdapter(liveTabLayoutAdapter);

        findSeekTablayout.setupWithViewPager(findSeekViewpage);
//        findSeekTablayout.post(new Runnable() {
//            @Override
//            public void run() {
//                TabLayoutSetIndicator.setIndicator(findSeekTablayout, 50, 50);
//            }
//        });

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
    }
}
