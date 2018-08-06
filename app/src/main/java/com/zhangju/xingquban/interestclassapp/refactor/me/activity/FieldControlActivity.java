package com.zhangju.xingquban.interestclassapp.refactor.me.activity;

import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.fastlib.annotation.Bind;
import com.fastlib.annotation.ContentView;
import com.fastlib.app.FastActivity;
import com.fastlib.net.Request;
import com.fastlib.net.SimpleListener;
import com.fastlib.utils.N;
import com.fastlib.widget.TitleBar;
import com.zhangju.xingquban.R;
import com.zhangju.xingquban.interestclassapp.refactor.common.bean.Response;
import com.zhangju.xingquban.interestclassapp.refactor.me.adapter.FieldControlAdapter;
import com.zhangju.xingquban.interestclassapp.refactor.me.bean.ChatUser;
import com.zhangju.xingquban.interestclassapp.refactor.me.bean.MeInterface;
import com.zhangju.xingquban.interestclassapp.refactor.me.bean.ResponseLiveroomControl;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by sgfb on 2017/11/8.
 * 我的场控
 */
@ContentView(R.layout.act_field_control)
public class FieldControlActivity extends FastActivity{
    @Bind(R.id.titleBar)
    TitleBar mTitleBar;
    @Bind(R.id.list)
    RecyclerView mList;
    @Bind(R.id.refresh)
    SwipeRefreshLayout mRefresh;
    FieldControlAdapter mAdapter;

    @Override
    protected void alreadyPrepared() {
        mList.setAdapter(mAdapter=new FieldControlAdapter(this));
        mTitleBar.setOnLeftClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        mTitleBar.setOnRightClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(AddFieldControlActivity.class);
            }
        });
        mRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                requestFieldControl();
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        if(mRefresh!=null)
        mRefresh.setRefreshing(true);
        requestFieldControl();
    }

    private void requestFieldControl(){
        Request request=Request.obtain(MeInterface.POST_LIVE_ROOM_FIELD_CONTROL);
        request.put("types","manager");
        request.setListener(new SimpleListener<Response<List<ResponseLiveroomControl>>>(){

            @Override
            public void onResponseListener(Request r, Response<List<ResponseLiveroomControl>> result) {
                mRefresh.setRefreshing(false);
                if(result.success){
                    List<ChatUser> userList=new ArrayList<>();
                    if(result.data!=null&&result.data.size()>=2){
                        ResponseLiveroomControl liveroomControl=result.data.get(1);
                        if(liveroomControl.targetChatUser!=null){
                            for(ChatUser chatUser:liveroomControl.targetChatUser)
                                userList.add(chatUser);
                        }
                    }
                    mAdapter.setData(userList);
                }
                else N.showShort(FieldControlActivity.this,"获取场控失败");
            }

            @Override
            public void onErrorListener(Request r, String error) {
                super.onErrorListener(r, error);
                mRefresh.setRefreshing(false);
            }
        });
        net(request);
    }
}
