package com.zhangju.xingquban.interestclassapp.refactor.me.activity;

import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;

import com.fastlib.annotation.Bind;
import com.fastlib.annotation.ContentView;
import com.fastlib.app.FastActivity;
import com.fastlib.utils.N;
import com.fastlib.widget.TitleBar;
import com.zhangju.xingquban.R;
import com.zhangju.xingquban.interestclassapp.refactor.me.adapter.SelectDegreeAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by sgfb on 2017/11/22.
 * 学历选择
 */
@ContentView(R.layout.act_select_degree)
public class SelectDegreeActivity extends FastActivity{
    public static final String RES_STR_DEGREE="degree";
    @Bind(R.id.titleBar)
    TitleBar mTitleBar;
    @Bind(R.id.list)
    RecyclerView mList;
    SelectDegreeAdapter mAdapter;

    @Override
    protected void alreadyPrepared() {
        List<String> list=new ArrayList<>();
        list.add("初中");
        list.add("高中");
        list.add("中专");
        list.add("大专");
        list.add("本科");
        list.add("硕士");
        list.add("MBA");
        list.add("EMBA");
        list.add("博士");
        mList.setAdapter(mAdapter=new SelectDegreeAdapter(this,list));
        mTitleBar.setOnLeftClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        mTitleBar.setOnRightClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position=mAdapter.getmSelectPosition();
                if(position<0){
                    N.showShort(SelectDegreeActivity.this,"请选择提交项");
                    return;
                }
                Intent intent=new Intent();
                intent.putExtra(RES_STR_DEGREE,mAdapter.getItemAtPosition(position));
                setResult(RESULT_OK,intent);
                finish();
            }
        });
    }
}
