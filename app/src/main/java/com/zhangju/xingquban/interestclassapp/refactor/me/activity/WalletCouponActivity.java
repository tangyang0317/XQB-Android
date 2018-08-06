package com.zhangju.xingquban.interestclassapp.refactor.me.activity;

import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.fastlib.annotation.Bind;
import com.fastlib.annotation.ContentView;
import com.fastlib.app.FastActivity;
import com.fastlib.net.Request;
import com.fastlib.net.SimpleListener;
import com.fastlib.widget.TitleBar;
import com.zhangju.xingquban.R;
import com.zhangju.xingquban.interestclassapp.refactor.common.bean.Response;
import com.zhangju.xingquban.interestclassapp.refactor.me.adapter.CouponAdapter;
import com.zhangju.xingquban.interestclassapp.refactor.me.bean.MeInterface;
import com.zhangju.xingquban.interestclassapp.refactor.me.bean.ResponseCoupon;

import java.util.List;

/**
 * Created by Administrator on 2017/11/25.
 * 钱包中优惠劵列表
 */
@ContentView(R.layout.act_wallet_coupon)
public class WalletCouponActivity extends FastActivity{
    @Bind(R.id.titleBar)
    TitleBar mTitleBar;
    @Bind(R.id.refresh)
    SwipeRefreshLayout mRefresh;
    @Bind(R.id.list)
    RecyclerView mList;
    CouponAdapter mAdapter;

    @Override
    protected void alreadyPrepared(){
        mList.setAdapter(mAdapter=new CouponAdapter(this));
        mTitleBar.setOnLeftClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        mRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                requestCouponList();
            }
        });
        requestCouponList();
    }

    /**
     * 请求优惠劵列表
     */
    private void requestCouponList(){
        Request request=Request.obtain(MeInterface.POST_COUPON_LIST);
        request.setListener(new SimpleListener<Response<List<ResponseCoupon>>>(){

            @Override
            public void onResponseListener(Request r, Response<List<ResponseCoupon>> result) {
                if(result.success){
                    mAdapter.setData(result.data);
                }
                mRefresh.setRefreshing(false);
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
