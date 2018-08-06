package com.zhangju.xingquban.interestclassapp.ui.fragment.home.spkc;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.zhangju.xingquban.R;
import com.zhangju.xingquban.interestclassapp.base.BaseFragment;
import com.zhangju.xingquban.interestclassapp.ui.fragment.home.wydp.HomeDataWdplXq;
import com.zhangju.xingquban.interestclassapp.ui.fragment.me.Comment.MeCommentBean;
import com.zhangju.xingquban.interestclassapp.ui.fragment.me.Comment.MeCommentdapter;
import com.zhangju.xingquban.interestclassapp.util.RecyclerItemDecoration.DensityUtils;
import com.zhangju.xingquban.interestclassapp.util.RecyclerItemDecoration.SpaceItemDecoration;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class HomeDataSpkcXqPl extends BaseFragment {
    @BindView(R.id.home_data_wydp_number)
    TextView homeDataWydpNumber;
    @BindView(R.id.home_data_pj_wydp)
    RelativeLayout homeDataPjWydp;
    @BindView(R.id.home_data_dp_recycler)
    RecyclerView homeDataDpRecycler;
    @BindView(R.id.home_data_pj_wydp_all)
    RelativeLayout homeDataPjWydpAll;
    Unbinder unbinder;
    private List<MeCommentBean> list = new ArrayList();
    private MeCommentdapter meCommentdapter;

    @Override
    public void initData() {
        plData();
    }

    @Override
    public int getMyLayout() {
        return R.layout.activity_home_data_spkc_xq_pl;
    }

    //评论
    public void plData() {
        list.clear();
        MeCommentBean meCommentBean = new MeCommentBean();
        list.add(meCommentBean);

        for (int i = 0; i < 1; i++) {
            list.add(list.get(0));
        }
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false) {
            @Override
            public boolean canScrollVertically() {
                return false;
            }
        };
        homeDataDpRecycler.setLayoutManager(linearLayoutManager);
//        meCommentdapter = new MeCommentdapter(getContext(), list);
        homeDataDpRecycler.setAdapter(meCommentdapter);
        homeDataDpRecycler.addItemDecoration(new SpaceItemDecoration(DensityUtils.dp2px(getActivity(), 10)));
        meCommentdapter.setOnItemClickListener(new MeCommentdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Intent intent = new Intent(getActivity(), HomeDataWdplXq.class);
                intent.putExtra("tag", position);
                startActivity(intent);
            }
        });
    }

    @Override
    public void initView() {

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
