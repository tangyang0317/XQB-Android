package com.zhangju.xingquban.interestclassapp.ui.fragment.me.MeActivity.wodepiaoquan;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.zhangju.xingquban.R;
import com.zhangju.xingquban.interestclassapp.base.BaseFragment;
import com.zhangju.xingquban.interestclassapp.util.RecyclerItemDecoration.DensityUtils;
import com.zhangju.xingquban.interestclassapp.util.RecyclerItemDecoration.SpaceItemDecoration;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class MeActivityWdpqAll extends BaseFragment {

    @BindView(R.id.me_activity_wdpj_recycler)
    RecyclerView meActivityWdpjRecycler;
    Unbinder unbinder;

    private MeActivityWdpaAllAdapter meActivityWdpaAllAdapter;
    private List<MeActivityWdpqAllBean> list = new ArrayList<>();
    private LinearLayoutManager linearLayoutManager;


    @Override
    public void initData() {

    }

    @Override
    public int getMyLayout() {
        return R.layout.activity_me_wdpq_all;
    }

    @Override
    public void initView() {

        MeActivityWdpqAllBean meActivityWdpqAllBean = new MeActivityWdpqAllBean();
        list.add(meActivityWdpqAllBean);

        for (int i = 0; i < 1; i++) {
            list.add(list.get(0));
        }

        linearLayoutManager = new LinearLayoutManager(getContext());
        meActivityWdpjRecycler.setLayoutManager(linearLayoutManager);
        meActivityWdpaAllAdapter = new MeActivityWdpaAllAdapter(getContext(), list);
        meActivityWdpjRecycler.setAdapter(meActivityWdpaAllAdapter);
        meActivityWdpjRecycler.addItemDecoration(new SpaceItemDecoration(DensityUtils.dp2px(getActivity(), 10)));

        meActivityWdpaAllAdapter.setOnItemClickListener(new MeActivityWdpaAllAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Intent intent = new Intent(getActivity(), MeActivityWdpqAllXq.class);
                intent.putExtra("tag", position);
                startActivity(intent);
            }
        });
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
