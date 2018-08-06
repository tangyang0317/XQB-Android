package com.zhangju.xingquban.interestclassapp.refactor.me.activity.publish_active;

import android.content.Intent;
import android.view.View;
import android.widget.EditText;

import com.fastlib.annotation.Bind;
import com.fastlib.annotation.ContentView;
import com.fastlib.annotation.LocalData;
import com.fastlib.app.FastActivity;
import com.fastlib.widget.TitleBar;
import com.zhangju.xingquban.R;

/**
 * Created by sgfb on 2017/11/1.
 * 增加活动行程
 */
@ContentView(R.layout.act_publish_active_add_trip)
public class AddTripActivity extends FastActivity{
    public static final String ARG_RES_TITLE ="title";
    public static final String ARG_RES_CONTENT="content";

    @LocalData(ARG_RES_TITLE)
    String mOldPlace;
    @LocalData(ARG_RES_CONTENT)
    String mOldContent;
    @Bind(R.id.titleBar)
    TitleBar mTitleBar;
    @Bind(R.id.title)
    EditText mActiveTitle;
    @Bind(R.id.content)
    EditText mContent;

    @Override
    protected void alreadyPrepared(){
        mTitleBar.setOnLeftClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        mTitleBar.setOnRightClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                commit();
            }
        });
        mActiveTitle.setText(mOldPlace);
        mContent.setText(mOldContent);
    }

    private void commit(){
        Intent intent=new Intent();
        intent.putExtra(ARG_RES_TITLE, mActiveTitle.getText().toString());
        intent.putExtra(ARG_RES_CONTENT,mContent.getText().toString());
        setResult(RESULT_OK,intent);
        finish();
    }
}