package com.zhangju.xingquban.interestclassapp.ui.fragment.home;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.alibaba.fastjson.JSONObject;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.fastlib.annotation.Bind;
import com.fastlib.annotation.ContentView;
import com.fastlib.app.FastActivity;
import com.fastlib.widget.TitleBar;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.RequestParams;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest;
import com.youth.banner.Banner;
import com.zhangju.xingquban.R;
import com.zhangju.xingquban.interestclassapp.bean.EditBean;
import com.zhangju.xingquban.interestclassapp.bean.NearDataBean;
import com.zhangju.xingquban.interestclassapp.bean.near.CurriculumBean;
import com.zhangju.xingquban.interestclassapp.refactor.location.Location;
import com.zhangju.xingquban.interestclassapp.refactor.location.LocationManager;
import com.zhangju.xingquban.interestclassapp.refactor.user.UserManager;
import com.zhangju.xingquban.interestclassapp.ui.activity.near.CurriculumOrderActivity;
import com.zhangju.xingquban.interestclassapp.util.UrlUtils;
import com.zhangju.xingquban.interestclassapp.view.BannerHelper;
import com.zhangju.xingquban.refactoring.adapter.InterestNewExperienceAdapter;
import com.zhangju.xingquban.refactoring.view.XQBLoadMoreView;

import java.util.List;

/**
 * Created by Administrator on 2018/2/2.
 * 兴趣新体验
 */
@ContentView(R.layout.act_experience)
public class ExperienceActivity extends FastActivity implements SwipeRefreshLayout.OnRefreshListener, BaseQuickAdapter.RequestLoadMoreListener {
    @Bind(R.id.titleBar)
    TitleBar mTitleBar;
    @Bind(R.id.refresh)
    SwipeRefreshLayout mRefersh;
    @Bind(R.id.list)
    RecyclerView mList;
    @Bind(R.id.home_banner)
    Banner mHomeBanner;
    private BannerHelper mBannerHelper;
    private InterestNewExperienceAdapter interestNewExperienceAdapter;
    private int pageIndex = 0;

    @Override
    protected void alreadyPrepared() {
        mTitleBar.setOnLeftClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        mRefersh.setOnRefreshListener(this);
        mRefersh.setColorSchemeColors(getResources().getColor(R.color.colorPrimary));
        interestNewExperienceAdapter = new InterestNewExperienceAdapter();
        interestNewExperienceAdapter.setLoadMoreView(new XQBLoadMoreView());
        interestNewExperienceAdapter.setOnLoadMoreListener(this, mList);
        mList.setAdapter(interestNewExperienceAdapter);
        interestNewExperienceAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                switch (view.getId()) {
                    case R.id.textView12:
                        CurriculumBean.AaDataBean dataBean = (CurriculumBean.AaDataBean) adapter.getItem(position);
                        Intent intent = new Intent(mContext, CurriculumOrderActivity.class);
                        Bundle bundle = new Bundle();
                        bundle.putSerializable(CurriculumOrderActivity.ARG_BEAN_DATA, dataBean);
                        intent.putExtras(bundle);
                        mContext.startActivity(intent);
                        break;
                }
            }
        });


        initListData(pageIndex);
        initBanner();
    }

    /***
     * 拉去列表数据
     */
    private void initListData(final int pageIndex) {
        RequestParams params = new RequestParams();
        Location location = LocationManager.getInstance().getLocation();
        params.addQueryStringParameter("lng", location.longitude);
        params.addQueryStringParameter("lat", location.latitude);
        params.addQueryStringParameter("cityId", location.cityId);
        params.addQueryStringParameter("sortJson", "[{\"field\":\"vipPrice\",\"isAsc\":\"true\"}]");
        params.addQueryStringParameter("pageIndex", String.valueOf(pageIndex));
        params.addQueryStringParameter("pageSize", "10");
        params.addHeader("X-CustomToken", UserManager.getInstance().getToken());
        new HttpUtils().send(HttpRequest.HttpMethod.POST, UrlUtils.URL_LESSON, params, new RequestCallBack<String>() {
            @Override
            public void onSuccess(ResponseInfo<String> responseInfo) {
                String data = responseInfo.result;
                CurriculumBean mBean = JSONObject.parseObject(data, CurriculumBean.class);
                List<CurriculumBean.AaDataBean> dataBeanList = mBean.getAaData();
                if (pageIndex == 0) {
                    mRefersh.setRefreshing(false);
                    if (dataBeanList != null && dataBeanList.size() > 0) {
                        if (dataBeanList.size() < 10) {
                            interestNewExperienceAdapter.loadMoreComplete();
                            interestNewExperienceAdapter.loadMoreEnd();
                            interestNewExperienceAdapter.setNewData(dataBeanList);
                        } else {
                            interestNewExperienceAdapter.setEnableLoadMore(true);
                            interestNewExperienceAdapter.setNewData(dataBeanList);
                        }
                    } else {
                        interestNewExperienceAdapter.loadMoreEnd();
                    }
                } else {
                    if (dataBeanList != null && dataBeanList.size() > 0) {
                        interestNewExperienceAdapter.loadMoreComplete();
                        interestNewExperienceAdapter.addData(dataBeanList);
                    } else {
                        interestNewExperienceAdapter.loadMoreComplete();
                        interestNewExperienceAdapter.loadMoreEnd();
                        interestNewExperienceAdapter.addData(dataBeanList);
                    }
                }
            }

            @Override
            public void onFailure(HttpException e, String s) {

            }
        });

    }

    private void initBanner() {
        mBannerHelper = new BannerHelper(mContext);
        mBannerHelper.init(mHomeBanner).loadBannerDate("1");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mBannerHelper != null) {
            mBannerHelper.stopBanner();
        }
    }

    @Override
    public void onRefresh() {
        pageIndex = 0;
        initListData(pageIndex);
    }

    @Override
    public void onLoadMoreRequested() {
        pageIndex++;
        initListData(pageIndex);
    }
}
