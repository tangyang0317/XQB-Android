package com.zhangju.xingquban.interestclassapp.ui.fragment.home.homeviewpage;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import com.zhangju.xingquban.R;
import com.zhangju.xingquban.interestclassapp.adapter.HomeItemAdapter;
import com.zhangju.xingquban.interestclassapp.base.BaseFragment;
import com.zhangju.xingquban.interestclassapp.bean.NearSubjectBean;
import com.zhangju.xingquban.interestclassapp.ui.activity.near.HomeDistrictActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

import static com.bumptech.glide.gifdecoder.GifHeaderParser.TAG;

/**
 * Created by Administrator on 2017/7/2.
 */
public class HomeClassPage1 extends BaseFragment {
    @BindView(R.id.home_page_itemrecylerview)
    RecyclerView homePageItemrecylerview;
    private HomeItemAdapter adapter;
    private List<NearSubjectBean.AaDataBean> totalList = new ArrayList<>();

    public static HomeClassPage1 newInstance(NearSubjectBean data) {
        HomeClassPage1 fragment = new HomeClassPage1();

        Bundle bundle = new Bundle();
        bundle.putSerializable("datas", data);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void initData() {
        Log.i(TAG, "initData: ======aaaaaaaaaaa");

        if (totalList == null || totalList.size() == 0) {
            Bundle arguments = getArguments();
            NearSubjectBean datas = (NearSubjectBean) arguments.getSerializable("datas");
            if (datas != null && datas.getAaData() != null) {
                if (datas.getAaData().size() >= 10) {
                    totalList.addAll(datas.getAaData().subList(0, 10));
                } else {
                    totalList.addAll(datas.getAaData());
                }
            }

        }


    }

    @Override
    public int getMyLayout() {
        return R.layout.home_page1;
    }

    @Override
    public void initView() {
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
        homePageItemrecylerview.setLayoutManager(new GridLayoutManager(getContext(), 5));
        homePageItemrecylerview.setAdapter(adapter);

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
