package com.zhangju.xingquban.interestclassapp.ui.fragment.me.Collect;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.zhangju.xingquban.R;
import com.zhangju.xingquban.interestclassapp.base.BaseFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class MeCollectAll extends BaseFragment {
    private List<MeCollectAllBean> list = new ArrayList();
    private Context context = getContext();
    private LinearLayoutManager linearLayoutManager;
    private MeCollectAllAdapter meCollectAllAdapter;

    @BindView(R.id.me_collect_recycler)
    RecyclerView meCollectRecycler;
    Unbinder unbinder;

    @Override
    public void initView() {
        list.clear();
        MeCollectAllBean meCollectAllBean = new MeCollectAllBean();
        list.add(meCollectAllBean);

        for (int i = 0; i < 2; i++) {
            list.add(list.get(0));
        }

        linearLayoutManager = new LinearLayoutManager(getContext());
        meCollectRecycler.setLayoutManager(linearLayoutManager);
//        meCollectAllAdapter = new MeCollectAllAdapter(mContext, list);
        meCollectRecycler.setAdapter(meCollectAllAdapter);
    }

    @Override
    public void initData() {

    }

    @Override
    public int getMyLayout() {
        return R.layout.activity_me_collect_all;
    }

    @Override
    public void initListener() {

    }

    @Override
    public void onClick(View v) {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        unbinder = ButterKnife.bind(this, rootView);
        return rootView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
