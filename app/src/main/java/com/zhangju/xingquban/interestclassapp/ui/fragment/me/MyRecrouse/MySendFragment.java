package com.zhangju.xingquban.interestclassapp.ui.fragment.me.MyRecrouse;

import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.zhangju.xingquban.R;
import com.zhangju.xingquban.interestclassapp.adapter.ResManagerAdapter;
import com.zhangju.xingquban.interestclassapp.base.BaseFragment;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by ydw on 2017/10/25.
 */
//资源管理--我发布的

public class MySendFragment extends BaseFragment {
    @BindView(R.id.recycler_resource)
    RecyclerView recyclerResource;
    Unbinder unbinder;

    private ResManagerAdapter managerAdapter;

    @Override
    public void initData() {

    }

    @Override
    public int getMyLayout() {
        return R.layout.fragment_recent;
    }

    @Override
    public void initView() {
        setMySendAdapter();

    }

    private void setMySendAdapter() {

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
