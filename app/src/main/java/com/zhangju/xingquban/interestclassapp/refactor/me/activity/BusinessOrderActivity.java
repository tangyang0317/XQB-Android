package com.zhangju.xingquban.interestclassapp.refactor.me.activity;

import android.support.v4.app.Fragment;
import android.support.v4.util.Pair;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.fastlib.adapter.CommonFragmentViewPagerAdapter;
import com.fastlib.annotation.Bind;
import com.fastlib.annotation.ContentView;
import com.fastlib.annotation.Event;
import com.fastlib.app.FastActivity;
import com.fastlib.widget.TitleBar;
import com.zhangju.xingquban.R;
import com.zhangju.xingquban.interestclassapp.refactor.me.fragment.BusinessOrderFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by sgfb on 2017/11/11.
 * 商户订单
 */
@ContentView(R.layout.act_business_order)
public class BusinessOrderActivity extends FastActivity{
    @Bind(R.id.titleBar)
    TitleBar mTitleBar;
    @Bind(R.id.viewPager)
    ViewPager mViewPager;
    @Bind(R.id.tab1)
    RadioButton mTab1;
    @Bind(R.id.tab2)
    RadioButton mTab2;
    @Bind(R.id.radioGroup)
    RadioGroup mRadioGroup;

    @Override
    protected void alreadyPrepared(){
        List<Pair<String,Fragment>> pages=new ArrayList<>();
        pages.add(Pair.<String, Fragment>create("待验证", BusinessOrderFragment.getInstance(0)));
        pages.add(Pair.<String, Fragment>create("已完成",BusinessOrderFragment.getInstance(1)));
        mViewPager.setAdapter(new CommonFragmentViewPagerAdapter(getSupportFragmentManager(),pages));
        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                mRadioGroup.check(position==0?mTab1.getId():mTab2.getId());
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        mTitleBar.setOnLeftClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        mTitleBar.setOnRightClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(VipVertifyActivity.class);
            }
        });
    }

    @Bind(R.id.tab1)
    private void changeTab1(){
        mViewPager.setCurrentItem(0,true);
    }

    @Bind(R.id.tab2)
    private void changeTab2(){
        mViewPager.setCurrentItem(1,true);
    }
}
