package com.zhangju.xingquban.interestclassapp.ui.fragment.home.msfc;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.fastlib.annotation.ContentView;
import com.fastlib.annotation.LocalData;
import com.fastlib.app.FastActivity;
import com.zhangju.xingquban.R;
import com.zhangju.xingquban.interestclassapp.ui.fragment.home.HomeDataTeacherBean;
import com.zhangju.xingquban.interestclassapp.ui.widget.PublicHead;

import butterknife.BindView;
import butterknife.ButterKnife;


@ContentView(R.layout.activity_home_data_msfc_all)
public class HomeDataMsfcAll extends FastActivity {
    public static final String ARG_TEACHER_DATA="teachers";
    @LocalData(ARG_TEACHER_DATA)
    HomeDataTeacherBean teacherBean;

    @BindView(R.id.home_data_msfcall_head)
    PublicHead homeDataMsfcallHead;
    @BindView(R.id.home_data_msfc_all_recycler)
    RecyclerView homeDataMsfcAllRecycler;
    private MsfcAdapter msfcAdapter;
    @Override
    protected void alreadyPrepared() {
        homeDataMsfcallHead.setTitle("老师列表");
        homeDataMsfcallHead.setShow(true, false, false);
        homeDataMsfcallHead.setBackClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        homeDataMsfcAllRecycler.setLayoutManager(linearLayoutManager);
        msfcAdapter=new MsfcAdapter(HomeDataMsfcAll.this,teacherBean);
        homeDataMsfcAllRecycler.setAdapter(msfcAdapter);
        msfcAdapter.setOnItemClickListener(new MsfcAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
             Intent   intent = new Intent(HomeDataMsfcAll.this, HomeDataMsfcXq.class);
              Bundle  bundle = new Bundle();
                bundle.putSerializable("teacher", teacherBean.getAaData().get(0).getReses().get(position));
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
    }
}
