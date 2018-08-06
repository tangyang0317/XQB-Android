package com.zhangju.xingquban.interestclassapp.ui.fragment.me.Order;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.View;

import com.zhangju.xingquban.R;
import com.zhangju.xingquban.interestclassapp.base.BaseActivity;
import com.zhangju.xingquban.interestclassapp.ui.fragment.live.LiveTabLayoutFragment.LiveTabLayoutAdapter;
import com.zhangju.xingquban.interestclassapp.ui.fragment.me.Order.AllOrders.MyAllOrder;
import com.zhangju.xingquban.interestclassapp.ui.fragment.me.Order.InCommission.MyOrderInCommission;
import com.zhangju.xingquban.interestclassapp.ui.fragment.me.Order.Obligation.MyOrderObligation;
import com.zhangju.xingquban.interestclassapp.ui.widget.PublicHead;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 我的订单
 */

public class MyOrder extends BaseActivity {
    private View view;

    @BindView(R.id.me_order_head)
    PublicHead meOrderHead;
    @BindView(R.id.me_order_tablayout)
    TabLayout meOrderTablayout;
    @BindView(R.id.me_order_viewpage)
    ViewPager meOrderViewpage;

    private List<Fragment> fragmentList = new ArrayList<>();
    private List<String> stringList = new ArrayList<>();
    private LiveTabLayoutAdapter liveTabLayoutAdapter;
    private MyAllOrder myAllOrder;
    private MyOrderObligation myOrderObligation;
    private MyOrderInCommission myOrderInCommission;

    @Override
    public int getLayout() {
        return R.layout.activity_my_order;
    }


    @Override
    public void initView() {
        initLayout(view);
        setMeOrderHead();
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
    }

    //tablayout
    private void initLayout(View view) {
        myAllOrder = new MyAllOrder();
        myOrderInCommission = new MyOrderInCommission();
        myOrderObligation = new MyOrderObligation();

        fragmentList = new ArrayList<>();
        fragmentList.add(myAllOrder);
        fragmentList.add(myOrderInCommission);
        fragmentList.add(myOrderObligation);

        stringList = new ArrayList<>();
        stringList.add("全部订单");
        stringList.add("待付款");
        stringList.add("可使用");

//        tabLayout.setTabMode(TabLayout.MODE_SCROLLABLE);

        meOrderTablayout.addTab(meOrderTablayout.newTab().setText(stringList.get(0)));
        meOrderTablayout.addTab(meOrderTablayout.newTab().setText(stringList.get(1)));
        meOrderTablayout.addTab(meOrderTablayout.newTab().setText(stringList.get(2)));

        liveTabLayoutAdapter = new LiveTabLayoutAdapter(this.getSupportFragmentManager(), fragmentList, stringList);

        meOrderViewpage.setAdapter(liveTabLayoutAdapter);

        meOrderTablayout.setupWithViewPager(meOrderViewpage);
    }


    //head
    public void setMeOrderHead() {
        meOrderHead.setTitle("我的订单");
        meOrderHead.setShow(true, false, false);
        meOrderHead.setBackClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

}
