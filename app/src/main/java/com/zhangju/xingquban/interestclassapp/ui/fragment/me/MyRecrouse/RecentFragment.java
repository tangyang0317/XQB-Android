package com.zhangju.xingquban.interestclassapp.ui.fragment.me.MyRecrouse;

import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.zhangju.xingquban.R;
import com.zhangju.xingquban.interestclassapp.adapter.ResManagerAdapter;
import com.zhangju.xingquban.interestclassapp.base.BaseFragment;
import com.zhangju.xingquban.interestclassapp.util.ToastUtil;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
//资源管理--最近

public class RecentFragment extends BaseFragment {
    @BindView(R.id.recycler_resource)
    RecyclerView recyclerResource;
    Unbinder unbinder;

    private ResManagerAdapter managerAdapter;


    rx.Observer<Object> observer1 = new rx.Observer<Object>() {

        @Override
        public void onCompleted() {

        }

        @Override
        public void onError(Throwable e) {
            ToastUtil.showToast(e.toString());

        }

        @Override
        public void onNext(Object object) {
            String s = object.toString();
            Toast.makeText(getContext(), s, Toast.LENGTH_SHORT).show();

        }
    };

    @Override
    public void initData() {
        getRecentResData();

    }

    private void getRecentResData() {

    }

    @Override
    public int getMyLayout() {
        return R.layout.fragment_recent;
    }

    @Override
    public void initView() {
        setResRecentAdapter();

    }

    private void setResRecentAdapter() {

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
