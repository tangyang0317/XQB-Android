package com.zhangju.xingquban.interestclassapp.ui.fragment.me.MeActivity.wofabude;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.zhangju.xingquban.R;
import com.zhangju.xingquban.interestclassapp.base.BaseActivity;
import com.zhangju.xingquban.interestclassapp.ui.widget.PublicHead;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MeActivityFbhdBmsz extends BaseActivity {


    @BindView(R.id.find_message_radio_zhuanr)
    RadioButton findMessageRadioZhuanr;
    @BindView(R.id.find_message_radio_qit)
    RadioButton findMessageRadioQit;
    @BindView(R.id.find_message_radiogroup2)
    RadioGroup findMessageRadiogroup2;
    @BindView(R.id.editText3)
    EditText editText3;
    @BindView(R.id.me_activity_wfbdFbhd_Head)
    PublicHead meActivityWfbdFbhdHead;

    @Override
    public int getLayout() {
        return R.layout.activity_me_fbhd_bmsz;
    }

    @Override
    public void initView() {
        setMeactvityFbhbBmszHead();
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

    public void setMeactvityFbhbBmszHead() {
        meActivityWfbdFbhdHead.setTitle("报名设置");
        meActivityWfbdFbhdHead.setShow(true, false, true);
        meActivityWfbdFbhdHead.setRightTitle("完成");
        meActivityWfbdFbhdHead.setRightTextColor(R.color.color_main);
        meActivityWfbdFbhdHead.setBackClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        meActivityWfbdFbhdHead.setRightClickListener(new View.OnClickListener() {
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
