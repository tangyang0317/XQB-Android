package com.zhangju.xingquban.interestclassapp.refactor.me.activity;

import android.content.Intent;
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
import com.zhangju.xingquban.interestclassapp.refactor.common.bean.CommonInterface;
import com.zhangju.xingquban.interestclassapp.refactor.common.bean.Response;
import com.zhangju.xingquban.interestclassapp.refactor.common.bean.ResponseCity;
import com.zhangju.xingquban.interestclassapp.refactor.common.bean.ResponseProvince;
import com.zhangju.xingquban.interestclassapp.refactor.me.adapter.ApplyToMemberLocationAdapter;
import com.zhangju.xingquban.interestclassapp.refactor.me.bean.MeInterface;

import java.util.List;

/**
 * Created by sgfb on 2017/11/22.
 * 授课地点选择 省级
 */
@ContentView(R.layout.act_select_location)
public class SelectLocationActivity extends FastActivity{
    public final static String RES_SER_PROVINCE="province";
    public final static String RES_SER_CITY="city";
    public final static String RES_SER_AREA="area";
    final int REQ_LOCATION_DETAIL=1;

    @Bind(R.id.titleBar)
    TitleBar mTitleBar;
    @Bind(R.id.refresh)
    SwipeRefreshLayout mRefresh;
    @Bind(R.id.list)
    RecyclerView mList;
    ApplyToMemberLocationAdapter mAdapter;
    ResponseProvince mSelectProvince;

    @Override
    protected void alreadyPrepared(){
        mList.setAdapter(mAdapter=new ApplyToMemberLocationAdapter(this, new ApplyToMemberLocationAdapter.OnClickListener() {
            @Override
            public void onClick(ResponseProvince data) {
                Intent intent=new Intent(SelectLocationActivity.this,SelectAreaActivity.class);
                intent.putExtra(SelectAreaActivity.ARG_STR_ID,data.id);
                mSelectProvince=data;
                startActivityForResult(intent,REQ_LOCATION_DETAIL);
            }
        }));
        mRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                requestProvinceLocation();
            }
        });
        mTitleBar.setOnLeftClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        requestProvinceLocation();
    }

    /**
     * 请求省级信息
     */
    private void requestProvinceLocation(){
        Request request=Request.obtain(CommonInterface.POST_PROVICE_LIST);
        request.put("iDisplayLength",10000);
        request.setListener(new SimpleListener<Response<List<ResponseProvince>>>(){

            @Override
            public void onResponseListener(Request r, Response<List<ResponseProvince>> result) {
                if(result.success)
                    mAdapter.setData(result.data);
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

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode!=RESULT_OK) return;
        //只有一个回传值，不做判断
        ResponseCity city= (ResponseCity) data.getSerializableExtra(RES_SER_CITY);
        Intent intent=new Intent();
        intent.putExtra(RES_SER_AREA,data.getSerializableExtra(RES_SER_AREA));
        intent.putExtra(RES_SER_CITY,city);
        intent.putExtra(RES_SER_PROVINCE,mSelectProvince);
        setResult(RESULT_OK,intent);
        finish();
    }
}