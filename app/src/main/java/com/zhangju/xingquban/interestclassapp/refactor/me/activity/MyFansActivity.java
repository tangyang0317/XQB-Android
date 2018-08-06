package com.zhangju.xingquban.interestclassapp.refactor.me.activity;

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
import com.zhangju.xingquban.interestclassapp.refactor.me.adapter.FansAdapter;
import com.zhangju.xingquban.interestclassapp.refactor.me.bean.MeInterface;
import com.zhangju.xingquban.interestclassapp.refactor.me.bean.ResponseFans;
import com.zhangju.xingquban.interestclassapp.refactor.user.UserManager;

import java.util.List;

/**
 * Created by sgfb on 2017/11/8.
 * 粉丝列表
 */
@ContentView(R.layout.act_live_room_history) //复用发布界面
public class MyFansActivity extends FastActivity{
    @Bind(R.id.titleBar)
    TitleBar mTitleBar;
    @Bind(R.id.list)
    RecyclerView mList;
    FansAdapter mAdapter;

    @Override
    protected void alreadyPrepared() {
        mList.setAdapter(mAdapter=new FansAdapter(this));
        mTitleBar.getTitle().setText("粉丝");
        mTitleBar.setOnLeftClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        requestFansList();
    }

    /**
     * 请求粉丝列表
     */
    private void requestFansList(){
        Request request=Request.obtain(MeInterface.POST_FANS_LIST);
        request.put("chatUserId", UserManager.getInstance().getUser().id);
        request.setListener(new SimpleListener<Response<List<ResponseFans>>>(){

            @Override
            public void onResponseListener(Request r, Response<List<ResponseFans>> result) {
                if(result.success){
                    mAdapter.setData(result.data);
                }
                else N.showShort(MyFansActivity.this,"请求粉丝列表异常");
            }
        });
        net(request);
    }
}