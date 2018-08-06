package com.zhangju.xingquban.interestclassapp.ui.fragment.me.Lesson;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.LinearLayout;

import com.zhangju.xingquban.R;
import com.zhangju.xingquban.interestclassapp.base.BaseActivity;
import com.zhangju.xingquban.interestclassapp.ui.fragment.live.LiveTabLayoutFragment.LiveTabLayoutAdapter;
import com.zhangju.xingquban.interestclassapp.ui.widget.PublicHead;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MyLesson extends BaseActivity {

    @BindView(R.id.me_lesson_head)
    PublicHead meLessonHead;
    @BindView(R.id.me_lesson_tablayout)
    TabLayout meLessonTablayout;
    @BindView(R.id.me_lesson_viewpage)
    ViewPager meLessonViewpage;

    private View view;
    private List<Fragment> fragmentList = new ArrayList<>();
    private List<String> stringList = new ArrayList<>();
    private LiveTabLayoutAdapter liveTabLayoutAdapter;

    private MeActivityDaiyz meActivityDaiyz;
    private MeActivityYiwanc meActivityYiwanc;

    @Override
    public int getLayout() {
        return R.layout.activity_my_messon;
    }

    @Override
    public void initView() {
        setMeLessonHead();
        initLayout(view);
        initTabLayout();
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

    //tablayout
    private void initLayout(View view) {
        meActivityDaiyz = new MeActivityDaiyz();
        meActivityYiwanc = new MeActivityYiwanc();

        fragmentList = new ArrayList<>();
        fragmentList.add(meActivityDaiyz);
        fragmentList.add(meActivityYiwanc);

        stringList = new ArrayList<>();
        stringList.add("待验证课程");
        stringList.add("已完成订单");

//        tabLayout.setTabMode(TabLayout.MODE_SCROLLABLE);

        meLessonTablayout.addTab(meLessonTablayout.newTab().setText(stringList.get(0)));
        meLessonTablayout.addTab(meLessonTablayout.newTab().setText(stringList.get(1)));

        liveTabLayoutAdapter = new LiveTabLayoutAdapter(this.getSupportFragmentManager(), fragmentList, stringList);

        meLessonViewpage.setAdapter(liveTabLayoutAdapter);

        meLessonTablayout.setupWithViewPager(meLessonViewpage);
    }

    // 给tablayout添加分割线
    private void initTabLayout() {
        // 给tablayout添加分割线
        LinearLayout linearLayout = (LinearLayout) meLessonTablayout.getChildAt(0);
        linearLayout.setShowDividers(LinearLayout.SHOW_DIVIDER_MIDDLE);
        linearLayout.setDividerDrawable(ContextCompat.getDrawable(this,
                R.drawable.layout_divider_vertical));
        linearLayout.setDividerPadding(10); // 设置分割线的padding值
        meLessonTablayout.setSelectedTabIndicatorHeight(0); // 设置下划线不可见
        meLessonTablayout.setTabTextColors(0xFF000000, 0xFFF65447);
    }

    //head
    public void setMeLessonHead() {
        meLessonHead.setTitle("商户订单");
        meLessonHead.setShow(true, false, false);
        meLessonHead.setBackClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
