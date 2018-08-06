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
import com.zhangju.xingquban.interestclassapp.refactor.me.adapter.FocusUserAdapter;
import com.zhangju.xingquban.interestclassapp.refactor.me.bean.MeInterface;
import com.zhangju.xingquban.interestclassapp.refactor.me.bean.ResponseFocusUser;
import com.zhangju.xingquban.interestclassapp.refactor.user.UserManager;

import java.util.List;

/**
 * Created by sgfb on 2017/11/8.
 * 我关注的用户列表
 */
@ContentView(R.layout.act_live_room_history) //我的发布界面复用
public class FocusUserActivity extends FastActivity{
    @Bind(R.id.titleBar)
    TitleBar mTitleBar;
    @Bind(R.id.list)
    RecyclerView mList;
    FocusUserAdapter mAdapter;

    @Override
    protected void alreadyPrepared(){
        mList.setAdapter(mAdapter=new FocusUserAdapter(this));
        mTitleBar.getTitle().setText("关注");
        mTitleBar.setOnLeftClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        requestFocusUserList();
    }

    /**
     * 请求关注用户列表
     */
    private void requestFocusUserList(){
        Request request=Request.obtain(MeInterface.POST_FOCUS_LIST);
        request.put("chatUserId", UserManager.getInstance().getUser().id);
        request.setListener(new SimpleListener<Response<List<ResponseFocusUser>>>(){

            @Override
            public void onResponseListener(Request r, Response<List<ResponseFocusUser>> result) {
                if(result.success) mAdapter.setData(result.data);
                else N.showShort(FocusUserActivity.this,"获取关注列表失败");
            }
        });
        net(request);
    }
}
