package com.zhangju.xingquban.interestclassapp.refactor.me.activity;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.util.Pair;
import android.support.v4.view.ViewPager;
import android.view.View;

import com.fastlib.adapter.CommonFragmentViewPagerAdapter;
import com.fastlib.annotation.Bind;
import com.fastlib.annotation.ContentView;
import com.fastlib.annotation.Event;
import com.fastlib.app.EventObserver;
import com.fastlib.app.FastActivity;
import com.fastlib.widget.TitleBar;
import com.zhangju.xingquban.R;
import com.zhangju.xingquban.interestclassapp.refactor.me.bean.EventAlbumMangeShowDelete;
import com.zhangju.xingquban.interestclassapp.refactor.me.fragment.AlbumManageFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by sgfb on 2017/11/6.
 * 相册管理
 */
@ContentView(R.layout.act_album_manager)
public class AlbumManageActivity extends FastActivity {
    @Bind(R.id.titleBar)
    TitleBar mTitleBar;
    @Bind(R.id.tabLayout)
    TabLayout mTabLayout;
    @Bind(R.id.viewPager)
    ViewPager mViewPager;
    boolean deleteFlag = false;


    @Override
    protected void alreadyPrepared() {
        List<Pair<String, Fragment>> pages = new ArrayList<>();

        pages.add(Pair.<String, Fragment>create("视频", AlbumManageFragment.getInstance(2)));
        pages.add(Pair.<String, Fragment>create("图片", AlbumManageFragment.getInstance(0)));
        mViewPager.setAdapter(new CommonFragmentViewPagerAdapter(getSupportFragmentManager(), pages));
        mTabLayout.setupWithViewPager(mViewPager);
        mTitleBar.setOnLeftClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        mTitleBar.setOnRightClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EventObserver.getInstance().sendEvent(AlbumManageActivity.this, new EventAlbumMangeShowDelete(!deleteFlag));
            }
        });
    }

    @Event
    private void eDeleteFlagChanged(EventAlbumMangeShowDelete event) {
        deleteFlag = event.isDeleteFlag();
        mTitleBar.getRightText().setText(event.isDeleteFlag() ? "取消" : "编辑");
    }
}