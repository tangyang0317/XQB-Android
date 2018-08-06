package com.zhangju.xingquban.interestclassapp.refactor.me.activity;

import android.view.View;
import android.widget.EditText;

import com.fastlib.annotation.Bind;
import com.fastlib.annotation.ContentView;
import com.fastlib.app.FastActivity;
import com.fastlib.widget.TitleBar;
import com.zhangju.xingquban.R;

/**
 * Created by Administrator on 2017/12/14.
 * 订单评论
 */
@ContentView(R.layout.act_order_comment)
public class OrderCommentActivity extends FastActivity{
    @Bind(R.id.titleBar)
    TitleBar mTitleBar;
    @Bind(R.id.comment)
    EditText mComment;

    @Override
    protected void alreadyPrepared() {
        mTitleBar.setOnLeftClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
