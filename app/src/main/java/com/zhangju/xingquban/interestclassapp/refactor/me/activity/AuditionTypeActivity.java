package com.zhangju.xingquban.interestclassapp.refactor.me.activity;

import android.content.Intent;
import android.view.View;

import com.fastlib.annotation.Bind;
import com.fastlib.annotation.ContentView;
import com.fastlib.app.FastActivity;
import com.fastlib.widget.TitleBar;
import com.zhangju.xingquban.R;

/**
 * Created by sgfb on 2017/11/6.
 * 是否允许试听
 */
@ContentView(R.layout.act_audition_type)
public class AuditionTypeActivity extends FastActivity{
    public static final String RES_INT_CAN_AUDITION="canAudition"; //是否允许试听返回 0不允许 1允许

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

    @Bind(R.id.canAudition)
    private void canAuditionResult(){
        Intent intent=new Intent();
        intent.putExtra(RES_INT_CAN_AUDITION,1);
        setResult(RESULT_OK,intent);
        finish();
    }

    @Bind(R.id.cantAudition)
    private void cantAuditionResult(){
        Intent intent=new Intent();
        intent.putExtra(RES_INT_CAN_AUDITION,0);
        setResult(RESULT_OK,intent);
        finish();
    }
}
