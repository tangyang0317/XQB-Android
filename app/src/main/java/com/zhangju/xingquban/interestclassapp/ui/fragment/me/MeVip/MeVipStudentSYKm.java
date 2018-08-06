package com.zhangju.xingquban.interestclassapp.ui.fragment.me.MeVip;

import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;

import com.zhangju.xingquban.R;
import com.zhangju.xingquban.interestclassapp.base.BaseActivity;
import com.zhangju.xingquban.interestclassapp.ui.fragment.find.Wenda.FindTWButtonAdapter;
import com.zhangju.xingquban.interestclassapp.ui.widget.PublicHead;
import com.zhangju.xingquban.interestclassapp.util.RecyclerItemDecoration.RecyclerViewSpacesItemDecoration;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MeVipStudentSYKm extends BaseActivity {

    @BindView(R.id.me_vip_student)
    PublicHead meVipStudent;
    @BindView(R.id.me_vip_student_ms)
    RecyclerView meVipStudentMs;

    private LayoutInflater inflater;
    private FindTWButtonAdapter findTWButtonAdapter;
    private List<String> mList = new ArrayList<>();
    private LinearLayoutManager linearLayoutManager;

    @Override
    public int getLayout() {
        return R.layout.activity_me_vip_student_sykm;
    }

    @Override
    public void initView() {
        setMeVipStudentHead();
    }

    @Override
    public void initData() {
        meVipStudentMs.setLayoutManager(new GridLayoutManager(mContext, 5));
        mList.add("美术");
        mList.add("象棋");
        mList.add("声乐");
        mList.add("舞蹈舞蹈");
        mList.add("舞蹈");
        mList.add("语言");
        mList.add("键盘");
        mList.add("功夫");
        mList.add("健身");
        mList.add("健身");
        findTWButtonAdapter = new FindTWButtonAdapter(mContext, mList);
        meVipStudentMs.setAdapter(findTWButtonAdapter);
//        meVipStudentMs.addItemDecoration(new SpaceItemDecoration(DensityUtils.dp2px(this, 10)));
        HashMap<String, Integer> stringIntegerHashMap = new HashMap<>();
        stringIntegerHashMap.put(RecyclerViewSpacesItemDecoration.TOP_DECORATION, 10);
        stringIntegerHashMap.put(RecyclerViewSpacesItemDecoration.BOTTOM_DECORATION, 10);
        stringIntegerHashMap.put(RecyclerViewSpacesItemDecoration.LEFT_DECORATION, 20);
        stringIntegerHashMap.put(RecyclerViewSpacesItemDecoration.RIGHT_DECORATION, 20);
        meVipStudentMs.addItemDecoration(new RecyclerViewSpacesItemDecoration(stringIntegerHashMap));

        findTWButtonAdapter.setmOnItemClickListener(new FindTWButtonAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {

            }
        });
    }

    @Override
    public void initListener() {

    }

    @Override
    public void onClick(View v) {

    }

    public void setMeVipStudentHead() {
        meVipStudent.setTitle("科目选择");
        meVipStudent.setShow(true, false, true);
        meVipStudent.setRightTitle("提交");
        meVipStudent.setRightTextColor(R.color.color_main);
        meVipStudent.setBackClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        meVipStudent.setRightClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
    }
}
