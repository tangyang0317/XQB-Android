package com.zhangju.xingquban.interestclassapp.ui.fragment.find.SeekActivity.jingxuan;

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

public class FindSeekChoicenessTpTpZx extends BaseFragment {

    private List<FindSeekTpTpZxBean> list = new ArrayList<>();
    private FindSeekTpTpZxAdapter findSeekTpTpZxAdapter;
    private LinearLayoutManager linearLayoutManager;

    @BindView(R.id.find_tptpZxRecycler)
    RecyclerView findTptpZxRecycler;
    Unbinder unbinder;

    @Override
    public void initData() {

    }

    @Override
    public int getMyLayout() {
        return R.layout.activity_find_seek_choiceness_tp_tp_zx;
    }

    @Override
    public void initView() {
        linearLayoutManager = new LinearLayoutManager(getContext());
        findTptpZxRecycler.setLayoutManager(linearLayoutManager);
        findSeekTpTpZxAdapter = new FindSeekTpTpZxAdapter(getActivity(), list);
        findTptpZxRecycler.setAdapter(findSeekTpTpZxAdapter);
        findTptpZxRecycler.addItemDecoration(new SpaceItemDecoration(DensityUtils.dp2px(getActivity(), 10)));
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
