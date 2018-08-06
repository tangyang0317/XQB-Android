package com.zhangju.xingquban.interestclassapp.ui.fragment.find.SeekMessage;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.zhangju.xingquban.R;
import com.zhangju.xingquban.interestclassapp.adapter.baseadpter.OnListItemClickListener;
import com.zhangju.xingquban.interestclassapp.base.BaseActivity;
import com.zhangju.xingquban.interestclassapp.ui.widget.PublicHead;

import java.util.Arrays;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

//发布城市选择
public class FindMessageFabuCity extends BaseActivity {


    @BindView(R.id.find_message_fabuCity_head)
    PublicHead findMessageFabuCityHead;
    @BindView(R.id.recycler_city)
    RecyclerView recyclerCity;
    private String city = "不限";
    private List<String> mCityList = Arrays.asList("不限", "上海市", "北京市", "天津市","青岛市","济南市","沈阳市","惠州市","珠海市", "南京市", "莆田市", "聊城", "南昌市", "九江市", "广州市", "哈密市", "绍兴市", "济宁市", "宁波市", "乌鲁木齐");//城市数据
    private FindCityAdapter cityAdapter;
    int position;

    @Override
    public int getLayout() {
        return R.layout.activity_find_message_fabu_city;
    }

    @Override
    public void initView() {
        setFindMessageFabuCityHead();
        setCityAdapter();
    }

    private void setCityAdapter() {
        position = getIntent().getIntExtra("position", 0);
        cityAdapter = new FindCityAdapter(FindMessageFabuCity.this, mCityList);
        recyclerCity.setLayoutManager(new GridLayoutManager(FindMessageFabuCity.this, 4));
        recyclerCity.setAdapter(cityAdapter);
        cityAdapter.setPosition(position);
        city = mCityList.get(0);
        cityAdapter.setOnListItemClickListener(new OnListItemClickListener() {
            @Override
            public void onItemClickListener(int position, View v) {
                cityAdapter.setPosition(position);
                city = mCityList.get(position);

                //携带数据返回
                Intent intent = new Intent();
                intent.putExtra("city", mCityList.get(position) + "," + position);
                setResult(RESULT_OK, intent);
                finish();
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

    public void setFindMessageFabuCityHead() {
        findMessageFabuCityHead.setTitle("选择城市");
        findMessageFabuCityHead.setShow(true, false, false);
        findMessageFabuCityHead.setBackClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.putExtra("city", city);
                setResult(RESULT_OK, intent);
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
