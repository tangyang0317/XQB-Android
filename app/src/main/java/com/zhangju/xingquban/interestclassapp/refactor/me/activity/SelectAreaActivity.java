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
import com.zhangju.xingquban.interestclassapp.refactor.common.bean.CommonInterface;
import com.zhangju.xingquban.interestclassapp.refactor.common.bean.Response;
import com.zhangju.xingquban.interestclassapp.refactor.common.bean.ResponseCity;
import com.zhangju.xingquban.interestclassapp.refactor.me.adapter.ApplyToMemberLocationDetailAdapter;

import java.util.List;

/**
 * Created by sgfb on 2017/11/22.
 * 选择具体地区
 */
@ContentView(R.layout.act_select_location) //选择省界面复用
public class SelectAreaActivity extends FastActivity{
    public static final String ARG_STR_ID="id";

    @LocalData(ARG_STR_ID)
    String mId;
    @Bind(R.id.titleBar)
    TitleBar mTitleBar;
    @Bind(R.id.refresh)
    SwipeRefreshLayout mRefresh;
    @Bind(R.id.list)
    RecyclerView mList;
    ApplyToMemberLocationDetailAdapter mAdapter;

    @Override
    protected void alreadyPrepared(){
        mList.setAdapter(mAdapter=new ApplyToMemberLocationDetailAdapter(new ApplyToMemberLocationDetailAdapter.OnAreaSelectedCallback() {
            @Override
            public void onSelectedArea(ResponseCity.Area area) {
                Intent intent=new Intent();
                intent.putExtra(SelectLocationActivity.RES_SER_AREA,area);
                intent.putExtra(SelectLocationActivity.RES_SER_CITY,area.fromCity);
                setResult(RESULT_OK,intent);
                finish();
            }
        }));
        mRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                requestArea();
            }
        });
        mTitleBar.setOnLeftClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        requestArea();
    }

    /**
     * 请求具体市县
     */
    private void requestArea(){
        final Request request=Request.obtain(CommonInterface.POST_CITY_LIST);
        request.put("id",mId);
        request.put("iDisplayLength",10000);
        request.setListener(new SimpleListener<Response<List<ResponseCity>>>(){

            @Override
            public void onResponseListener(Request r, Response<List<ResponseCity>> result) {
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
}
