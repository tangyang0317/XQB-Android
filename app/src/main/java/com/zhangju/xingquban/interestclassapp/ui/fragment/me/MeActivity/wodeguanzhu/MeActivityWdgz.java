package com.zhangju.xingquban.interestclassapp.ui.fragment.me.MeActivity.wodeguanzhu;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.zhangju.xingquban.R;
import com.zhangju.xingquban.interestclassapp.base.BaseActivity;
import com.zhangju.xingquban.interestclassapp.ui.widget.PublicHead;
import com.zhangju.xingquban.interestclassapp.util.RecyclerItemDecoration.DensityUtils;
import com.zhangju.xingquban.interestclassapp.util.RecyclerItemDecoration.SpaceItemDecoration;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MeActivityWdgz extends BaseActivity {


    @BindView(R.id.me_activity_wdgzHead)
    PublicHead meActivityWdgzHead;
    @BindView(R.id.me_activity_wdgz_recycler)
    RecyclerView meActivityWdgzRecycler;

    private LinearLayoutManager linearLayoutManager;
    private MeActivityWdgzAdapter meActivityWdgzAdapter;
    private List<MeActivityWdgzBean> list = new ArrayList<>();

    @Override
    public int getLayout() {
        return R.layout.activity_me_wdgz;
    }

    @Override
    public void initView() {
        setMeActivityWdgzHead();

        MeActivityWdgzBean meActivityWdgzBean = new MeActivityWdgzBean();
        list.add(meActivityWdgzBean);

        for (int i = 0; i < 2; i++) {
            list.add(list.get(0));
        }

        linearLayoutManager = new LinearLayoutManager(this);
        meActivityWdgzRecycler.setLayoutManager(linearLayoutManager);
        meActivityWdgzAdapter = new MeActivityWdgzAdapter(this, list);
        meActivityWdgzRecycler.setAdapter(meActivityWdgzAdapter);
        meActivityWdgzRecycler.addItemDecoration(new SpaceItemDecoration(DensityUtils.dp2px(this, 10)));


    }

    @Override
    public void initData() {

    }

    @Override
    public void initListener() {

    }

    @Override
    public void onClick(View v) {

    }

    public void setMeActivityWdgzHead() {
        meActivityWdgzHead.setTitle("我关注的");
        meActivityWdgzHead.setShow(true, false, false);
        meActivityWdgzHead.setBackClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
    }
}
