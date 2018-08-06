package com.zhangju.xingquban.interestclassapp.refactor.me.activity;

import android.view.View;

import com.fastlib.annotation.Bind;
import com.fastlib.annotation.ContentView;
import com.fastlib.app.FastActivity;
import com.fastlib.widget.TitleBar;
import com.zhangju.xingquban.R;

/**
 * Created by sgfb on 2017/11/15.
 * 活动验票(机构/老师端)
 */
@ContentView(R.layout.act_active_check_ticket)
public class CheckTicketActivity extends FastActivity{
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

    @Bind(R.id.manualInput)
    private void toManualInput(){
        startActivity(CheckTicketManualActivity.class);
    }
}
