package com.zhangju.xingquban.interestclassapp.refactor.me.activity;

import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.fastlib.annotation.Bind;
import com.fastlib.annotation.ContentView;
import com.fastlib.app.FastActivity;
import com.fastlib.net.Request;
import com.fastlib.net.SimpleListener;
import com.fastlib.utils.N;
import com.fastlib.widget.TitleBar;
import com.zhangju.xingquban.R;
import com.zhangju.xingquban.interestclassapp.refactor.common.bean.Response;
import com.zhangju.xingquban.interestclassapp.refactor.me.adapter.FieldControlSearchAdapter;
import com.zhangju.xingquban.interestclassapp.refactor.me.bean.ChatUser;
import com.zhangju.xingquban.interestclassapp.refactor.me.bean.MeInterface;

import java.util.List;

/**
 * Created by sgfb on 2017/11/8.
 * 搜索添加场控
 */
@ContentView(R.layout.act_add_field_control)
public class AddFieldControlActivity extends FastActivity{
    @Bind(R.id.titleBar)
    TitleBar mTitleBar;
    @Bind(R.id.searchContent)
    EditText mContent;
    @Bind(R.id.list)
    RecyclerView mList;
    FieldControlSearchAdapter mAdapter;

    @Override
    protected void alreadyPrepared(){
        mList.setAdapter(mAdapter =new FieldControlSearchAdapter(this));
        mTitleBar.setOnLeftClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        mContent.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event){
                if((actionId==0||actionId==3)&&event!=null){
                    startSearch();
                    return true;
                }
                return false;
            }
        });
    }

    @Bind(R.id.search)
    private void startSearch(){
        String search=mContent.getText().toString();

        if(TextUtils.isEmpty(search))
            return;
        Request request=new Request(MeInterface.POST_LIVE_ROOM_SEARCH);
        request.put("isFilterRoomRole",true);
        request.put("types","manager");
        request.put("allStringQuery",search);
        request.setListener(new SimpleListener<Response<List<ChatUser>>>(){

            @Override
            public void onResponseListener(Request r, Response<List<ChatUser>> result) {
                if(result.success) mAdapter.setData(result.data);
                else N.showShort(AddFieldControlActivity.this,"搜索失败");
            }
        });
        net(request);
    }

    @Bind(R.id.delete)
    private void delete(){
        mContent.setText(null);
    }
}
