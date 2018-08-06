package com.zhangju.xingquban.interestclassapp.ui.fragment.me.MeActivity.wofabude;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.zhangju.xingquban.R;
import com.zhangju.xingquban.interestclassapp.adapter.HomeRecylerAdapter;
import com.zhangju.xingquban.interestclassapp.base.BaseActivity;
import com.zhangju.xingquban.interestclassapp.ui.widget.PublicHead;
import com.zhangju.xingquban.interestclassapp.util.RecyclerItemDecoration.DensityUtils;
import com.zhangju.xingquban.interestclassapp.util.RecyclerItemDecoration.SpaceItemDecoration;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
/*
* 我发布的
* */

public class MeActivityWfbd extends BaseActivity {

    @BindView(R.id.me_activity_wfbd_head)
    PublicHead meActivityWfbdHead;
    @BindView(R.id.me_activity_wfbd_Recycler)
    RecyclerView meActivityWfbdRecycler;

    private LinearLayoutManager linearLayoutManager;
    private MeActivityWfbdAdapter meActivityWfbdAdapter;
    private List<MeActivityWfbdBean> list = new ArrayList<>();

    @Override
    public int getLayout() {
        return R.layout.activity_me_wfbd;
    }

    @Override
    public void initView() {
        setMeActivityWfbdHead();

        MeActivityWfbdBean meActivityWfbdBean = new MeActivityWfbdBean();
        list.add(meActivityWfbdBean);

        for (int i = 0; i < 2; i++) {
            list.add(list.get(0));
        }

        linearLayoutManager = new LinearLayoutManager(this);
        meActivityWfbdRecycler.setLayoutManager(linearLayoutManager);
        meActivityWfbdAdapter = new MeActivityWfbdAdapter(this, list);
        meActivityWfbdRecycler.setAdapter(meActivityWfbdAdapter);
        meActivityWfbdRecycler.addItemDecoration(new SpaceItemDecoration(DensityUtils.dp2px(this, 10)));
        meActivityWfbdAdapter.setOnItemClickListener(new HomeRecylerAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Intent intent = new Intent(MeActivityWfbd.this, MeActivityWfbdData.class);
                intent.putExtra("tag", position);
                startActivity(intent);
            }
        });
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

    public void setMeActivityWfbdHead() {
        meActivityWfbdHead.setTitle("我发布的");
        meActivityWfbdHead.setShow(true, false, true);
        meActivityWfbdHead.setRightTitle("发起活动");
        meActivityWfbdHead.setRightTextColor(R.color.color_main);
        meActivityWfbdHead.setBackClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        meActivityWfbdHead.setRightClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MeActivityWfbd.this, MeActivityFaqihd.class));
            }
        });
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
    }
}
