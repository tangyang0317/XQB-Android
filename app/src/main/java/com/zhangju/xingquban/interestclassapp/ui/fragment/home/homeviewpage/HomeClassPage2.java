package com.zhangju.xingquban.interestclassapp.ui.fragment.home.homeviewpage;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.zhangju.xingquban.R;
import com.zhangju.xingquban.interestclassapp.adapter.HomeItemAdapter;
import com.zhangju.xingquban.interestclassapp.base.BaseFragment;
import com.zhangju.xingquban.interestclassapp.bean.NearSubjectBean;
import com.zhangju.xingquban.interestclassapp.ui.activity.near.HomeDistrictActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by Administrator on 2017/7/2.
 */

public class HomeClassPage2 extends BaseFragment {
    @BindView(R.id.homePageItemrecylerview2)
    RecyclerView homePageItemrecylerview2;
    private HomeItemAdapter adapter;
    private List<NearSubjectBean.AaDataBean> totalList = new ArrayList<>();
    public static HomeClassPage2 newInstance(NearSubjectBean data) {
        HomeClassPage2 fragment = new HomeClassPage2();
        Bundle bundle = new Bundle();
        bundle.putSerializable("datas", data);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void initData() {

        if (totalList == null || totalList.size() == 0) {
            Bundle arguments = getArguments();
            NearSubjectBean datas = (NearSubjectBean) arguments.getSerializable("datas");
            if (datas != null && datas.getAaData() != null) {
                if (datas.getAaData().size() >= 20) {
                    totalList.addAll(datas.getAaData().subList(10, 20));
                } else if (datas.getAaData().size() >= 10) {
                    totalList.addAll(datas.getAaData().subList(10, datas.getAaData().size()));
                }
            }
        }
    }

    @Override
    public int getMyLayout() {
        return R.layout.home_page2;
    }

    @Override
    public void initView() {
//        mList.add(new HomeItemBean("语文", R.drawable.home_meishu));
//        mList.add(new HomeItemBean("美术", R.drawable.home_yuyanbiaoyan));
//        mList.add(new HomeItemBean("美术", R.drawable.home_gongfu));

        adapter = new HomeItemAdapter(getContext(), totalList);
        adapter.setRecylerViewClickListener(new HomeItemAdapter.OnRecylerViewItemClickListener() {
            @Override
            public void onItemClickListener(View view, int postine) {
                switch (postine) {
                    case 0:
                        break;
                }
            }
        });

        homePageItemrecylerview2.setLayoutManager(new GridLayoutManager(getContext(), 5));
        homePageItemrecylerview2.setAdapter(adapter);
    }

    @Override
    public void initListener() {
        adapter.setRecylerViewClickListener(new HomeItemAdapter.OnRecylerViewItemClickListener() {
            @Override
            public void onItemClickListener(View view, int postine) {
                totalList.get(postine).getName();
                Intent intent = new Intent(getActivity(), HomeDistrictActivity.class);
                Bundle bundle2=new Bundle();
                bundle2.putSerializable("data", totalList.get(postine));
                intent.putExtras(bundle2);
                startActivity(intent);
            }
        });
    }

    @Override
    public void onClick(View v) {

    }
}
