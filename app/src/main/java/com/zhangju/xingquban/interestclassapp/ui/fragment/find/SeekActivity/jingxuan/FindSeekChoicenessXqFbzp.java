package com.zhangju.xingquban.interestclassapp.ui.fragment.find.SeekActivity.jingxuan;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.zhangju.xingquban.R;
import com.zhangju.xingquban.interestclassapp.base.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class FindSeekChoicenessXqFbzp extends BaseActivity {

    @BindView(R.id.find_seek_xq_fbzp_return)
    ImageView findSeekXqFbzpReturn;
    @BindView(R.id.find_seek_xq_fbzp_xiayb)
    TextView findSeekXqFbzpXiayb;
    @BindView(R.id.find_seek_xq_fbzp_yl)
    TextView findSeekXqFbzpYl;
    @BindView(R.id.find_seek_xq_fbzpOne)
    LinearLayout findSeekXqFbzpOne;
    @BindView(R.id.find_seek_xq_fbzp_button)
    Button findSeekXqFbzpButton;
    @BindView(R.id.find_seek_xq_fbzpTwo)
    LinearLayout findSeekXqFbzpTwo;
    @BindView(R.id.me_activity_fbhdTwo_xianshi)
    ScrollView meActivityFbhdTwoXianshi;

    @Override
    public int getLayout() {
        return R.layout.activity_find_seek_choiceness_xq_fbzp;
    }

    @Override
    public void initView() {

    }

    @Override
    public void initData() {

    }

    @Override
    public void initListener() {

    }

    @OnClick({R.id.find_seek_xq_fbzp_return, R.id.find_seek_xq_fbzp_xiayb, R.id.find_seek_xq_fbzp_yl, R.id.find_seek_xq_fbzp_button})
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.find_seek_xq_fbzp_return:
                finish();
                break;
            case R.id.find_seek_xq_fbzp_xiayb:
                findSeekXqFbzpOne.setVisibility(View.GONE);
                findSeekXqFbzpTwo.setVisibility(View.VISIBLE);
                break;
            case R.id.find_seek_xq_fbzp_button:
                meActivityFbhdTwoXianshi.setVisibility(View.VISIBLE);
                findSeekXqFbzpOne.setVisibility(View.GONE);
                findSeekXqFbzpTwo.setVisibility(View.GONE);
                findSeekXqFbzpXiayb.setVisibility(View.GONE);
                findSeekXqFbzpYl.setVisibility(View.VISIBLE);
                break;
            case R.id.find_seek_xq_fbzp_yl:
                startActivity(new Intent(this, FindSeekChoicenessXqFbzpYulan.class));
                break;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
    }
}
