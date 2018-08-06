package com.zhangju.xingquban.interestclassapp.util.test;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;

import com.zhangju.xingquban.R;
import com.zhangju.xingquban.interestclassapp.adapter.baseadpter.OnListItemClickListener;
import com.zhangju.xingquban.interestclassapp.hplper.ScrollLinearLayoutManager;
import com.zhangju.xingquban.interestclassapp.swiperefrsh.SwipeRefreshAdapterView;
import com.zhangju.xingquban.interestclassapp.swiperefrsh.SwipeRefreshRecyclerView;
import com.zhangju.xingquban.interestclassapp.util.ToastUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class TestMyActivity extends AppCompatActivity implements SwipeRefreshAdapterView.OnListLoadListener, SwipeRefreshLayout.OnRefreshListener {


    @BindView(R.id.recyclerView)
    SwipeRefreshRecyclerView recyclerView;
    private TestMyAdapter adapter;
    private List<String> mlist = new ArrayList<>();
    private List<String> mlist2 = new ArrayList<>();
    View view;

    RecyclerView recyclerview_top_test;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_my);
        ButterKnife.bind(this);

        view = LayoutInflater.from(this).inflate(R.layout.test_top, null);
        recyclerview_top_test= (RecyclerView) view.findViewById(R.id.recyclerview_top_test);

        ScrollLinearLayoutManager scrollLinearLayoutManager=new ScrollLinearLayoutManager(this);
        scrollLinearLayoutManager.setScrollEnabled(false);
        recyclerview_top_test.setLayoutManager(scrollLinearLayoutManager);
        recyclerview_top_test.setAdapter(new TestTTopAdapter(this,mlist2));

        mlist.add("1111111111");
        mlist.add("2222222222");
        mlist.add("33333333333");
        mlist.add("44444444444");
        mlist.add("55555555555");
        mlist.add("66666666666");
        mlist.add("77777777777");
        mlist.add("88888888888");
        mlist.add("999999999999");
        mlist2.add("aaaaaaaaaaaa");
        mlist2.add("bbbbbbbbbbbb");
        mlist2.add("cccccccccccc");
        mlist2.add("dddddddddddd");



        adapter = new TestMyAdapter(TestMyActivity.this, mlist, view);
        recyclerView.setLayoutManager(new LinearLayoutManager(TestMyActivity.this));
        recyclerView.setAdapter(adapter);

        recyclerView.setOnListLoadListener(this);
        recyclerView.setOnRefreshListener(this);
        adapter.setOnListItemClickListener(new OnListItemClickListener() {
            @Override
            public void onItemClickListener(int position, View v) {


            }
        });
        adapter.notifyDataSetChanged();

    }

    @Override
    public void onListLoad() {
        recyclerView.setLoading(false);
        recyclerView.setRefreshing(false);
        recyclerView.setEnabledLoad(true);
    }

    @Override
    public void onRefresh() {




        recyclerView.setLoading(false);
        recyclerView.setRefreshing(false);
        recyclerView.setEnabledLoad(true);
        adapter.notifyDataSetChanged();
    }
}
