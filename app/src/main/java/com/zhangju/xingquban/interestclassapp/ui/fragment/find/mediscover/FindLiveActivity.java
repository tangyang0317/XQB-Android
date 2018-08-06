package com.zhangju.xingquban.interestclassapp.ui.fragment.find.mediscover;

import android.support.v4.app.FragmentManager;

import com.fastlib.annotation.ContentView;
import com.fastlib.app.FastActivity;
import com.zhangju.xingquban.R;
import com.zhangju.xingquban.interestclassapp.ui.fragment.live.LiveFragment;
import com.zhangju.xingquban.interestclassapp.util.SpUtil;

/**
 * 发现模块---找直播
 */
@ContentView(R.layout.activity_find_live)
public class FindLiveActivity extends FastActivity {

    @Override
    protected void alreadyPrepared() {
        SpUtil.putBoolean(FindLiveActivity.this,"isBack",true);

        FragmentManager fm = getSupportFragmentManager();
        LiveFragment liveFragment = new LiveFragment();
        fm.beginTransaction().replace(R.id.find_live_frame, liveFragment).commit();
    }


    @Override
    protected void onPause() {
        super.onPause();
        SpUtil.putBoolean(FindLiveActivity.this,"isBack",false);
    }
}
