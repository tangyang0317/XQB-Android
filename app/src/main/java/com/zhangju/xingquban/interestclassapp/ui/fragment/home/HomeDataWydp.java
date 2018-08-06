package com.zhangju.xingquban.interestclassapp.ui.fragment.home;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.zhangju.xingquban.R;
import com.zhangju.xingquban.interestclassapp.base.BaseActivity;
import com.zhangju.xingquban.interestclassapp.ui.fragment.me.Comment.MeCommentBean;
import com.zhangju.xingquban.interestclassapp.ui.fragment.me.Comment.MeCommentdapter;
import com.zhangju.xingquban.interestclassapp.ui.widget.PublicHead;
import com.zhangju.xingquban.interestclassapp.util.RecyclerItemDecoration.DensityUtils;
import com.zhangju.xingquban.interestclassapp.util.RecyclerItemDecoration.SpaceItemDecoration;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/*
* 机构详情 我要点评
* */
public class HomeDataWydp extends BaseActivity {


    @BindView(R.id.home_data_wydpHead)
    PublicHead homeDataWydpHead;
    @BindView(R.id.home_data_wddpNumber)
    TextView homeDataWddpNumber;
    @BindView(R.id.home_data_wddpRecycler)
    RecyclerView homeDataWddpRecycler;

    private List<MeCommentBean> list = new ArrayList();
    private MeCommentdapter meCommentdapter;
    private LinearLayoutManager linearLayoutManager;

    @Override
    public int getLayout() {
        return R.layout.activity_home_data_wydp;
    }

    @Override
    public void initView() {
        setHomeDataWydpHead();
        plData();
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

    public void plData() {
        list.clear();
        MeCommentBean meCommentBean = new MeCommentBean();
        list.add(meCommentBean);

        for (int i = 0; i < 10; i++) {
            list.add(list.get(0));
        }

        linearLayoutManager = new LinearLayoutManager(this);
        homeDataWddpRecycler.setLayoutManager(linearLayoutManager);
//        meCommentdapter = new MeCommentdapter(this, list);
        homeDataWddpRecycler.setAdapter(meCommentdapter);
        homeDataWddpRecycler.addItemDecoration(new SpaceItemDecoration(DensityUtils.dp2px(this, 10)));
      /*  meCommentdapter.setOnItemClickListener(new MeCommentdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {

            }
        });*/
    }

    public void setHomeDataWydpHead() {
        homeDataWydpHead.setTitle("全部评论");
        homeDataWydpHead.setShow(true, false, false);
        homeDataWydpHead.setBackClickListener(new View.OnClickListener() {
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
