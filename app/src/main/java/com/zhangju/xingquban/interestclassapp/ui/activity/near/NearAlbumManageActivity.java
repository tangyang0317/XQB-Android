package com.zhangju.xingquban.interestclassapp.ui.activity.near;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.util.Pair;
import android.support.v4.view.ViewPager;
import android.view.View;

import com.fastlib.adapter.CommonFragmentViewPagerAdapter;
import com.fastlib.widget.TitleBar;
import com.zhangju.xingquban.R;
import com.zhangju.xingquban.interestclassapp.base.BaseActivity;
import com.zhangju.xingquban.interestclassapp.ui.fragment.home.HomeDataTeacherBean;
import com.zhangju.xingquban.interestclassapp.ui.fragment.near.NearAlbumManageFragment;
import com.zhangju.xingquban.interestclassapp.ui.fragment.near.NearAlbumVideoManageFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by sgfb on 2017/11/6.
 * 相册管理
 */

public class NearAlbumManageActivity extends BaseActivity {
    @BindView(R.id.titleBar)
    TitleBar titleBar;
    @BindView(R.id.tabLayout)
    TabLayout tabLayout;
    @BindView(R.id.viewPager)
    ViewPager viewPager;
    private HomeDataTeacherBean.AaDataBean blbumFiles;

    @Override
    public int getLayout() {
        return R.layout.near_album_manager;
    }

    @Override
    public void initView() {
        blbumFiles= (HomeDataTeacherBean.AaDataBean) getIntent().getExtras().getSerializable("albumFiles");
        List<Pair<String,Fragment>> pages=new ArrayList<>();

        pages.add(Pair.<String, Fragment>create("视频", NearAlbumVideoManageFragment.getInstance(blbumFiles)));
        pages.add(Pair.<String, Fragment>create("图片", NearAlbumManageFragment.getInstance(blbumFiles)) );
        viewPager.setAdapter(new CommonFragmentViewPagerAdapter(getSupportFragmentManager(),pages));
        tabLayout.setupWithViewPager(viewPager);

    }

    @Override
    public void initData() {

    }

    @Override
    public void initListener() {
        titleBar.setOnLeftClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    @Override
    public void onClick(View v) {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
    }
}
