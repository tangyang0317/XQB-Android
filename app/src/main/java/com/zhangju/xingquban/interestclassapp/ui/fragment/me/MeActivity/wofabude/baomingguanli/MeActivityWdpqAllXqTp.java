package com.zhangju.xingquban.interestclassapp.ui.fragment.me.MeActivity.wofabude.baomingguanli;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.zhangju.xingquban.R;
import com.zhangju.xingquban.interestclassapp.base.BaseActivity;
import com.zhangju.xingquban.interestclassapp.ui.widget.PublicHead;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MeActivityWdpqAllXqTp extends BaseActivity {


    @BindView(R.id.me_activity_wfbdAll_tpHead)
    PublicHead meActivityWfbdAllTpHead;
    @BindView(R.id.me_activity_wfbdAll_tpCheck1)
    CheckBox meActivityWfbdAllTpCheck1;
    @BindView(R.id.me_activity_wfbdAll_tpCheck2)
    CheckBox meActivityWfbdAllTpCheck2;
    @BindView(R.id.me_activity_wfbdAll_tpCheck3)
    CheckBox meActivityWfbdAllTpCheck3;
    @BindView(R.id.textView9)
    TextView textView9;
    @BindView(R.id.me_activity_wfbdAll_tpCheck4)
    CheckBox meActivityWfbdAllTpCheck4;
    @BindView(R.id.me_activity_wfbdAll_tpEdit)
    EditText meActivityWfbdAllTpEdit;
    @BindView(R.id.me_activity_wfbdAll_button)
    Button meActivityWfbdAllButton;
    @BindView(R.id.me_activity_wdpq_allXqTp_Rl)
    RelativeLayout meActivityWdpqAllXqTpRl;
    @BindView(R.id.me_activity_wdpq_allXqTp_ll)
    LinearLayout meActivityWdpqAllXqTpLl;

    @Override
    public int getLayout() {
        return R.layout.activity_me_wdpq_all_xq_tp;
    }

    @Override
    public void initView() {
        setMeActivityWfbdAllTpHead();
    }

    @Override
    public void initData() {

    }

    @Override
    public void initListener() {

    }

    @OnClick({R.id.me_activity_wfbdAll_tpCheck1, R.id.me_activity_wfbdAll_tpCheck2, R.id.me_activity_wfbdAll_tpCheck3,
            R.id.me_activity_wfbdAll_tpCheck4, R.id.me_activity_wfbdAll_button
    })
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.me_activity_wfbdAll_tpCheck1:
                break;
            case R.id.me_activity_wfbdAll_tpCheck2:
                break;
            case R.id.me_activity_wfbdAll_tpCheck3:
                break;
            case R.id.me_activity_wfbdAll_tpCheck4:
                break;
            case R.id.me_activity_wfbdAll_button:
                meActivityWdpqAllXqTpRl.setVisibility(View.GONE);
                meActivityWdpqAllXqTpLl.setVisibility(View.VISIBLE);
                break;

        }
    }

    public void setMeActivityWfbdAllTpHead() {
        meActivityWfbdAllTpHead.setTitle("退票");
        meActivityWfbdAllTpHead.setShow(true, false, false);
        meActivityWfbdAllTpHead.setBackClickListener(new View.OnClickListener() {
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
