package com.zhangju.xingquban.interestclassapp.ui.fragment.me.MeActivity.wofabude.baomingguanli;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.zhangju.xingquban.R;
import com.zhangju.xingquban.interestclassapp.adapter.HomeRecylerAdapter;
import com.zhangju.xingquban.interestclassapp.base.BaseFragment;
import com.zhangju.xingquban.interestclassapp.util.RecyclerItemDecoration.DensityUtils;
import com.zhangju.xingquban.interestclassapp.util.RecyclerItemDecoration.SpaceItemDecoration;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class MeActivityWfbdDataBmglAll extends BaseFragment {

    private LinearLayoutManager linearLayoutManager;
    private MeWfbdDataBmglAllAdapter meWfbdDataBmglAllAdapter;

    private List<MeWfbdDataBmglAllBean> list = new ArrayList<>();

    @BindView(R.id.me_activity_wfbdData_bmglAll)
    RecyclerView meActivityWfbdDataBmglAll;
    Unbinder unbinder;

    @Override
    public void initData() {

    }

    @Override
    public int getMyLayout() {
        return R.layout.activity_me_wfbd_data_bmgl_all;
    }

    @Override
    public void initView() {

        MeWfbdDataBmglAllBean meWfbdDataBmglAllBean = new MeWfbdDataBmglAllBean();
        list.add(meWfbdDataBmglAllBean);

        for (int i = 0; i < 2; i++) {
            list.add(list.get(0));
        }

        linearLayoutManager = new LinearLayoutManager(getContext());
        meActivityWfbdDataBmglAll.setLayoutManager(linearLayoutManager);
        meWfbdDataBmglAllAdapter = new MeWfbdDataBmglAllAdapter(getContext(), list);
        meActivityWfbdDataBmglAll.setAdapter(meWfbdDataBmglAllAdapter);
        meActivityWfbdDataBmglAll.addItemDecoration(new SpaceItemDecoration(DensityUtils.dp2px(getActivity(), 10)));

        meWfbdDataBmglAllAdapter.setOnItemClickListener(new HomeRecylerAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Intent intent = new Intent(getActivity(), MeActivityWfbdDataBmglAllXq.class);
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
