package com.zhangju.xingquban.interestclassapp.refactor.me.activity;

import android.view.View;

import com.fastlib.annotation.Bind;
import com.fastlib.annotation.ContentView;
import com.fastlib.app.FastActivity;
import com.fastlib.widget.TitleBar;
import com.zhangju.xingquban.R;

/**
 * Created by sgfb on 2017/11/8.
 * 我的直播间
 */
@ContentView(R.layout.act_my_liveroom_settings)
public class LiveroomSettingsActivity extends FastActivity{
    @Bind(R.id.titleBar)
    TitleBar mTitleBar;

    @Override
    protected void alreadyPrepared() {
        mTitleBar.setOnLeftClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    @Bind(R.id.fieldControl)
    private void fieldControl(){
        startActivity(FieldControlActivity.class);
    }

    @Bind(R.id.blackList)
    private void blackList(){
        startActivity(BlacklistActivity.class);
    }
}
