package com.zhangju.xingquban.interestclassapp.ui.fragment.home;

import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.fastlib.annotation.Bind;
import com.fastlib.annotation.ContentView;
import com.fastlib.app.FastActivity;
import com.fastlib.widget.TitleBar;
import com.youth.banner.Banner;
import com.zhangju.xingquban.R;
import com.zhangju.xingquban.interestclassapp.view.BannerHelper;

/**
 * Created by Administrator on 2018/2/2.
 * 兴趣新体验
 */
@ContentView(R.layout.act_experience)
public class ExperienceActivity
        extends FastActivity {
    @Bind(R.id.titleBar)
    TitleBar           mTitleBar;
    @Bind(R.id.refresh)
    SwipeRefreshLayout mRefersh;
    @Bind(R.id.list)
    RecyclerView       mList;
    @Bind(R.id.home_banner)
    Banner             mHomeBanner;
    private BannerHelper mBannerHelper;

    @Override
    protected void alreadyPrepared() {
        mTitleBar.setOnLeftClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        mList.setNestedScrollingEnabled(false);
        final ExperienceAdapter adapter = new ExperienceAdapter(this);
        mList.setAdapter(adapter);
        mRefersh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                adapter.refresh();
            }
        });
        adapter.setRefreshLayout(mRefersh);
        initBanner();
    }

    private void initBanner() {
        mBannerHelper = new BannerHelper(mContext);
        mBannerHelper.init(mHomeBanner).loadBannerDate("1");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mBannerHelper != null) {
            mBannerHelper.stopBanner();
        }
    }
}
