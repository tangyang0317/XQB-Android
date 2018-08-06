package com.zhangju.xingquban.interestclassapp.refactor.me.activity;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.fastlib.annotation.Bind;
import com.fastlib.annotation.ContentView;
import com.fastlib.app.FastActivity;
import com.fastlib.widget.TitleBar;
import com.zhangju.xingquban.R;
import com.zhangju.xingquban.interestclassapp.refactor.me.adapter.CommentAdapter;

import java.util.Locale;

/**
 * Created by sgfb on 2017/10/27.
 * 评论列表
 */
@ContentView(R.layout.act_comment)
public class CommentActivity extends FastActivity{
    @Bind(R.id.titleBar)
    TitleBar mTitleBar;
    @Bind(R.id.commentCount)
    TextView mCommentCount;
    @Bind(R.id.list)
    RecyclerView mList;
    CommentAdapter mAdapter;

    @Override
    protected void alreadyPrepared(){
        mTitleBar.setOnLeftClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        mList.setAdapter(mAdapter=new CommentAdapter(this, new CommentAdapter.DataCallback() {
            @Override
            public void callback(int commentCount) {
                mCommentCount.setText(String.format(Locale.getDefault(),"总共%s个评论",commentCount));
            }
        }));
    }
}