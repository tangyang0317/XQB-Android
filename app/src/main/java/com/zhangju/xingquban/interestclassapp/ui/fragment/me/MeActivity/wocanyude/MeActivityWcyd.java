package com.zhangju.xingquban.interestclassapp.ui.fragment.me.MeActivity.wocanyude;

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

public class MeActivityWcyd extends BaseActivity {

    @BindView(R.id.me_activity_wcydHead)
    PublicHead meActivityWcydHead;
    @BindView(R.id.me_activity_wcyd_recycler)
    RecyclerView meActivityWcydRecycler;
    private MeActivityWcydAdapter meActivityWcydAdapter;
    private List<MeActivityWcydBean> list = new ArrayList<>();
    private LinearLayoutManager linearLayoutManager;

    @Override
    public int getLayout() {
        return R.layout.activity_me_wcyd;
    }

    @Override
    public void initView() {
        setMeActivityWcydHead();
        MeActivityWcydBean meActivityWcydBean = new MeActivityWcydBean();
        list.add(meActivityWcydBean);

        for (int i = 0; i < 1; i++) {
            list.add(list.get(0));
        }

        linearLayoutManager = new LinearLayoutManager(this);
        meActivityWcydRecycler.setLayoutManager(linearLayoutManager);
        meActivityWcydAdapter = new MeActivityWcydAdapter(this, list);
        meActivityWcydRecycler.setAdapter(meActivityWcydAdapter);
        meActivityWcydRecycler.addItemDecoration(new SpaceItemDecoration(DensityUtils.dp2px(this, 5)));
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

    public void setMeActivityWcydHead() {
        meActivityWcydHead.setTitle("我的关注");
        meActivityWcydHead.setShow(true, false, false);
        meActivityWcydHead.setBackClickListener(new View.OnClickListener() {
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
