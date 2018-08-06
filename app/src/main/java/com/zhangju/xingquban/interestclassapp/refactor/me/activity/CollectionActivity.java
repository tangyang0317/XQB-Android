package com.zhangju.xingquban.interestclassapp.refactor.me.activity;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.util.Pair;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.Button;

import com.fastlib.adapter.CommonFragmentViewPagerAdapter;
import com.fastlib.annotation.Bind;
import com.fastlib.annotation.ContentView;
import com.fastlib.app.EventObserver;
import com.fastlib.app.FastActivity;
import com.fastlib.widget.TitleBar;
import com.zhangju.xingquban.R;
import com.zhangju.xingquban.interestclassapp.refactor.me.bean.EventCollectionDeleteStatusChanged;
import com.zhangju.xingquban.interestclassapp.refactor.me.bean.EventDeleteCollection;
import com.zhangju.xingquban.interestclassapp.refactor.me.fragment.AllCollectionFragment;
import com.zhangju.xingquban.interestclassapp.refactor.me.fragment.ResCollectionListFragment;
import com.zhangju.xingquban.interestclassapp.refactor.me.fragment.StoreCollectionListFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by sgfb on 2017/10/30.
 * 收藏列表
 */
@ContentView(R.layout.act_collection)
public class CollectionActivity extends FastActivity{
    @Bind(R.id.titleBar)
    TitleBar mTitleBar;
    @Bind(R.id.tabLayout)
    TabLayout mTabLayout;
    @Bind(R.id.viewPager)
    ViewPager mViewPager;
    @Bind(R.id.delete)
    Button mDelete;
    boolean isDeleteShow=false;

    @Override
    protected void alreadyPrepared() {
        List<Pair<String,Fragment>> pages=new ArrayList<>();
        pages.add(Pair.<String, Fragment>create("全部",new AllCollectionFragment()));
        pages.add(Pair.<String, Fragment>create("老师/机构",new StoreCollectionListFragment()));
        pages.add(Pair.<String, Fragment>create("资源",new ResCollectionListFragment()));

        mViewPager.setOffscreenPageLimit(3);
        mViewPager.setAdapter(new CommonFragmentViewPagerAdapter(getSupportFragmentManager(),pages));
        mViewPager.setCurrentItem(1,false);
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
                if(isDeleteShow){
                    mTitleBar.getRightText().setText("编辑");
                    isDeleteShow=false;
                    mDelete.setVisibility(View.GONE);
                }
                else {
                    mTitleBar.getRightText().setText("完成");
                    isDeleteShow=true;
                    mDelete.setVisibility(View.VISIBLE);
                }
                EventObserver.getInstance().sendEvent(CollectionActivity.this,new EventCollectionDeleteStatusChanged(isDeleteShow));
            }
        });
    }

    @Bind(R.id.delete)
    private void delete(){
        EventObserver.getInstance().sendEvent(this,new EventDeleteCollection());
    }
}
