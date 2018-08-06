package com.zhangju.xingquban.interestclassapp.ui.fragment.me.Order.Obligation;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.zhangju.xingquban.R;
import com.zhangju.xingquban.interestclassapp.base.BaseFragment;
import com.zhangju.xingquban.interestclassapp.ui.fragment.me.Order.AllOrders.AllOrderAdapter;
import com.zhangju.xingquban.interestclassapp.ui.fragment.me.Order.AllOrders.MyAllOrderBean;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class MyOrderObligation extends BaseFragment {
    private AllOrderAdapter allOrderAdapter;
    private Context context = getContext();
    private List<MyAllOrderBean> list = new ArrayList<>();
    private LinearLayoutManager linearLayoutManager;

    @BindView(R.id.recycler_obligation)
    RecyclerView recyclerObligation;
    Unbinder unbinder;

    @Override
    public void initData() {

    }

    @Override
    public int getMyLayout() {
        return R.layout.activity_my_order_obligation;
    }

    @Override
    public void initView() {
        MyAllOrderBean myallBean = new MyAllOrderBean();
        list.add(myallBean);

        linearLayoutManager = new LinearLayoutManager(context);
        recyclerObligation.setLayoutManager(linearLayoutManager);

        for (int i = 0; i < 10; i++) {
            list.add(list.get(0));
        }

        allOrderAdapter = new AllOrderAdapter(context, list);
        recyclerObligation.setAdapter(allOrderAdapter);

//        recyclerAllOrder.addItemDecoration(new SpaceItemDecoration(20));
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
