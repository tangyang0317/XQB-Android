package com.zhangju.xingquban.interestclassapp.ui.fragment.find.SeekActivity.jingxuan;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.zhangju.xingquban.R;
import com.zhangju.xingquban.interestclassapp.base.BaseFragment;
import com.zhangju.xingquban.interestclassapp.util.RecyclerItemDecoration.DensityUtils;
import com.zhangju.xingquban.interestclassapp.util.RecyclerItemDecoration.SpaceItemDecoration;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * 找活动 精选
 */

public class FindSeekChoiceness extends BaseFragment {
    @BindView(R.id.find_ll_huodong)
    LinearLayout findLlHuodong;
    @BindView(R.id.find_ll_toupiao)
    LinearLayout findLlToupiao;
    @BindView(R.id.find_ll_toupiao1)
    LinearLayout findLlToupiao1;
    private Context mContext = getContext();
    private List<FindSeekChoicenessBean> findSeekChoicenessBeanList = new ArrayList<>();
    private FindSeekChoicenessAdapter findSeekChoicenessAdapter;
    private LinearLayoutManager linearLayoutManager;

    @BindView(R.id.find_seek_choiceness_recycler)
    RecyclerView findSeekChoicenessRecycler;
    Unbinder unbinder;

    @Override
    public void initData() {
        findSeekChoicenessBeanList.clear();
        FindSeekChoicenessBean findSeekChoicenessBean = new FindSeekChoicenessBean();
        findSeekChoicenessBeanList.add(findSeekChoicenessBean);

        for (int i = 0; i <= 1; i++) {
            findSeekChoicenessBeanList.add(findSeekChoicenessBeanList.get(0));
        }

        linearLayoutManager = new LinearLayoutManager(mContext);
        findSeekChoicenessRecycler.setLayoutManager(linearLayoutManager);
        findSeekChoicenessAdapter = new FindSeekChoicenessAdapter(mContext, findSeekChoicenessBeanList);
        findSeekChoicenessRecycler.setAdapter(findSeekChoicenessAdapter);
        findSeekChoicenessRecycler.addItemDecoration(new SpaceItemDecoration(DensityUtils.dp2px(getContext(), 10)));
        findSeekChoicenessAdapter.setRecyclerViewItemClickListener(new FindSeekChoicenessAdapter.onRecyclerViewClickItemListener() {
            @Override
            public void onItemClickListener(View view, int position) {
                startActivity(new Intent(getActivity(), FindSeekChoicenessXq.class));
            }
        });
    }

    @Override
    public int getMyLayout() {
        return R.layout.activity_find_seek_choiceness;
    }

    @Override
    public void initView() {

    }

    @Override
    public void initListener() {

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


    @Override
    public void onClick(View v) {

    }

    @OnClick({R.id.find_ll_huodong, R.id.find_ll_toupiao, R.id.find_ll_toupiao1})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.find_ll_huodong:
                startActivity(new Intent(getActivity(), FindSeekChoicenessXq.class));
                break;
            case R.id.find_ll_toupiao:
                startActivity(new Intent(getActivity(), FindSeekChoicenessTp.class));
                break;
            case R.id.find_ll_toupiao1:
                startActivity(new Intent(getActivity(), FindSeekChoicenessTp.class));
                break;
        }
    }
}
