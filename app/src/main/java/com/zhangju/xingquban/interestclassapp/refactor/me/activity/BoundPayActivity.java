package com.zhangju.xingquban.interestclassapp.refactor.me.activity;

import android.content.Intent;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.fastlib.annotation.Bind;
import com.fastlib.annotation.ContentView;
import com.fastlib.annotation.LocalData;
import com.fastlib.app.FastActivity;
import com.fastlib.net.Request;
import com.fastlib.net.SimpleListener;
import com.fastlib.widget.TitleBar;
import com.zhangju.xingquban.R;
import com.zhangju.xingquban.interestclassapp.refactor.common.bean.Response;
import com.zhangju.xingquban.interestclassapp.refactor.me.adapter.BoundPayAdapter;
import com.zhangju.xingquban.interestclassapp.refactor.me.bean.MeInterface;
import com.zhangju.xingquban.interestclassapp.refactor.me.bean.ResponseBoundAccount;

import java.util.List;

/**
 * Created by Administrator on 2017/11/25.
 * 已绑定的钱包账户
 */
@ContentView(R.layout.act_bound_pay)
public class BoundPayActivity extends FastActivity{
    public static final String ARG_BOOL_CAN_SELECT="canSelect";
    public static final String RES_SER_BOUND_ACCOUNT="boundAccount";

    @LocalData(ARG_BOOL_CAN_SELECT)
    boolean isCanSelect;
    @Bind(R.id.titleBar)
    TitleBar mTitleBar;
    @Bind(R.id.refresh)
    SwipeRefreshLayout mRefresh;
    @Bind(R.id.list)
    RecyclerView mList;
    BoundPayAdapter mAdapter;

    @Override
    protected void alreadyPrepared() {
        mList.setAdapter(mAdapter=new BoundPayAdapter(this, new BoundPayAdapter.OnClickListener() {
            @Override
            public void onClick(ResponseBoundAccount data) {
                if(isCanSelect){
                    Intent intent=new Intent();
                    intent.putExtra(RES_SER_BOUND_ACCOUNT,data);
                    setResult(RESULT_OK,intent);
                    finish();
                }
            }
        }));
        mRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                requestBoundPayList();
            }
        });
        mTitleBar.setOnLeftClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        mTitleBar.setOnRightClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivityForResult(SelectBindPayActivity.class,1);
            }
        });
        requestBoundPayList();
    }

    /**
     * 请求已绑定支付方式列表
     */
    private void requestBoundPayList(){
        Request request=Request.obtain(MeInterface.POST_BOUND_ACCOUNT_LIST);
        request.setListener(new SimpleListener<Response<List<ResponseBoundAccount>>>(){

            @Override
            public void onResponseListener(Request r, Response<List<ResponseBoundAccount>> result) {
                mRefresh.setRefreshing(false);
                if(result.success)
                    mAdapter.setData(result.data);
            }

            @Override
            public void onErrorListener(Request r, String error) {
                super.onErrorListener(r, error);
                mRefresh.setRefreshing(false);
            }
        });
        net(request);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode==RESULT_OK){
            setResult(RESULT_OK);
            mRefresh.setRefreshing(true);
            mRefresh.postDelayed(new Runnable() {
                @Override
                public void run() {
                    requestBoundPayList();
                }
            },1000);
        }
    }
}