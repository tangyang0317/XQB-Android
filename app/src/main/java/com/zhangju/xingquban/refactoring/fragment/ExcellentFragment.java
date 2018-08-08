package com.zhangju.xingquban.refactoring.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.alibaba.fastjson.JSONObject;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.RequestParams;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest;
import com.zhangju.xingquban.R;
import com.zhangju.xingquban.interestclassapp.base.BaseFragment;
import com.zhangju.xingquban.interestclassapp.refactor.user.UserManager;
import com.zhangju.xingquban.interestclassapp.ui.fragment.find.Wenda.Question.fresh.MyAnswerActivity;
import com.zhangju.xingquban.interestclassapp.ui.fragment.find.Wenda.Question.fresh.QuestionDetailActivity;
import com.zhangju.xingquban.interestclassapp.ui.fragment.find.Wenda.Question.fresh.bean.QuestionMainBean;
import com.zhangju.xingquban.interestclassapp.util.UrlUtils;
import com.zhangju.xingquban.refactoring.adapter.MineQuestionAdapter;
import com.zhangju.xingquban.refactoring.view.XQBLoadMoreView;

import java.util.List;

/**
 * 优秀问答Fragment
 *
 * @packageName com.zhangju.xingquban.refactoring.fragment
 * @FileName ExcellentFragment
 * @Author tangyang
 * @DATE 2018/8/7
 **/
public class ExcellentFragment extends BaseFragment implements SwipeRefreshLayout.OnRefreshListener, BaseQuickAdapter.RequestLoadMoreListener {

    private SwipeRefreshLayout excellentSwipeRefreshLayout;
    private RecyclerView excellentRecycleView;
    private MineQuestionAdapter mineQuestionAdapter;
    private int pageIndex = 0;

    public static Fragment getInstance(int type) {
        ExcellentFragment excellentFragment = new ExcellentFragment();
        Bundle bundle = new Bundle();
        bundle.putInt("type", type);
        excellentFragment.setArguments(bundle);
        return excellentFragment;
    }

    private int getType() {
        if (getArguments() != null) {
            return getArguments().getInt("type", 0);
        }
        return 0;
    }

    @Override
    public void initData() {
        pageIndex = 0;
        loadData(pageIndex);
    }


    /***
     *
     * @param pageIndex
     */
    private void loadData(final int pageIndex) {
        RequestParams params = new RequestParams();
        params.addQueryStringParameter("isRelease", "1");
        params.addQueryStringParameter("isNew", String.valueOf(getType()));
        params.addQueryStringParameter("pageIndex", String.valueOf(pageIndex));
        params.addQueryStringParameter("pageSize", "10");
        params.addHeader("X-CustomToken", UserManager.getInstance().getToken());
        new HttpUtils().send(HttpRequest.HttpMethod.POST, UrlUtils.URL_MYQUESTION, params, new RequestCallBack<String>() {
            @Override
            public void onSuccess(ResponseInfo<String> responseInfo) {
                String data = responseInfo.result;
                QuestionMainBean mBean = JSONObject.parseObject(data, QuestionMainBean.class);
                List<QuestionMainBean.AaDataBean> dataBeanList = mBean.getAaData();
                if (pageIndex == 0) {
                    excellentSwipeRefreshLayout.setRefreshing(false);
                    if (dataBeanList != null && dataBeanList.size() > 0) {
                        if (dataBeanList.size() < 10) {
                            mineQuestionAdapter.loadMoreComplete();
                            mineQuestionAdapter.loadMoreEnd();
                            mineQuestionAdapter.setNewData(dataBeanList);
                        } else {
                            mineQuestionAdapter.setEnableLoadMore(true);
                            mineQuestionAdapter.setNewData(dataBeanList);
                        }
                    } else {
                        mineQuestionAdapter.loadMoreEnd();
                    }
                } else {
                    if (dataBeanList != null && dataBeanList.size() > 0) {
                        mineQuestionAdapter.loadMoreComplete();
                        mineQuestionAdapter.addData(dataBeanList);
                    } else {
                        mineQuestionAdapter.loadMoreComplete();
                        mineQuestionAdapter.loadMoreEnd();
                        mineQuestionAdapter.addData(dataBeanList);
                    }
                }
            }

            @Override
            public void onFailure(HttpException e, String s) {

            }
        });

    }

    @Override
    public int getMyLayout() {
        return R.layout.fragment_excelltion;
    }

    @Override
    public void initView() {
        excellentSwipeRefreshLayout = view.findViewById(R.id.excellentSwipeRefreshLayout);
        excellentRecycleView = view.findViewById(R.id.excellentRecycleView);
        excellentSwipeRefreshLayout.setOnRefreshListener(this);
        excellentSwipeRefreshLayout.setColorSchemeColors(getResources().getColor(R.color.colorPrimary));
        mineQuestionAdapter = new MineQuestionAdapter();
        mineQuestionAdapter.setLoadMoreView(new XQBLoadMoreView());
        mineQuestionAdapter.setOnLoadMoreListener(this, excellentRecycleView);
        excellentRecycleView.setAdapter(mineQuestionAdapter);

    }

    @Override
    public void initListener() {

        mineQuestionAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                Intent intent = new Intent(getActivity(), MyAnswerActivity.class);
                intent.putExtra("data", (QuestionMainBean.AaDataBean) adapter.getItem(position));
                intent.putExtra("type", String.valueOf(getType()));
                getActivity().startActivity(intent);
            }
        });

        mineQuestionAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                QuestionMainBean.AaDataBean dataBean = (QuestionMainBean.AaDataBean) adapter.getItem(position);
                Intent intent = new Intent(getActivity(), QuestionDetailActivity.class);
                intent.putExtra("data", dataBean);
                startActivity(intent);
            }
        });
    }

    @Override
    public void onClick(View view) {

    }

    @Override
    public void onRefresh() {
        pageIndex = 0;
        loadData(pageIndex);
    }

    @Override
    public void onLoadMoreRequested() {
        pageIndex++;
        loadData(pageIndex);
    }
}
