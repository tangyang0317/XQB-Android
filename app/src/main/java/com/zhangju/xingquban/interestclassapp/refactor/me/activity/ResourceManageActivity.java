package com.zhangju.xingquban.interestclassapp.refactor.me.activity;

import android.graphics.Bitmap;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.util.Pair;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.LinearLayout;

import com.fastlib.adapter.CommonFragmentViewPagerAdapter;
import com.fastlib.annotation.Bind;
import com.fastlib.annotation.ContentView;
import com.fastlib.app.FastActivity;
import com.fastlib.widget.TitleBar;
import com.zhangju.xingquban.R;
import com.zhangju.xingquban.interestclassapp.refactor.me.fragment.AddResourceSelectFragment;
import com.zhangju.xingquban.interestclassapp.refactor.me.fragment.ResourceManageFragment;
import com.zhangju.xingquban.interestclassapp.util.BlurKit;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by sgfb on 2017/11/6.
 * 资源管理
 */
@ContentView(R.layout.act_resource_manage)
public class ResourceManageActivity extends FastActivity {
    @Bind(R.id.titleBar)
    TitleBar mTitleBar;
    @Bind(R.id.tabLayout)
    TabLayout mTabLayout;
    @Bind(R.id.viewPager)
    ViewPager mViewPager;
    @Bind(R.id.line_gaosi)
    LinearLayout line_gaosi;

    @Override
    protected void alreadyPrepared() {
        List<Pair<String, Fragment>> pages = new ArrayList<>();

        pages.add(Pair.<String, Fragment>create("免费资源", ResourceManageFragment.getInstance(false)));
        pages.add(Pair.<String, Fragment>create("收费资源", ResourceManageFragment.getInstance(true)));
        mViewPager.setAdapter(new CommonFragmentViewPagerAdapter(getSupportFragmentManager(), pages));
        mTabLayout.setupWithViewPager(mViewPager);
        mViewPager.setCurrentItem(1);
        mTitleBar.setOnLeftClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        BlurKit.init(ResourceManageActivity.this);
        mTitleBar.setOnRightClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bitmap bitmap = BlurKit.getInstance().fastBlur(line_gaosi, 0, 0.1f);
                BlurKit.getInstance().setBitmap(bitmap);
                showSelectAdd();
            }
        });
    }

    public void showSelectAdd() {
        getSupportFragmentManager()
                .beginTransaction()
                .replace(android.R.id.content, AddResourceSelectFragment.getInstance())
                .commit();
    }
}