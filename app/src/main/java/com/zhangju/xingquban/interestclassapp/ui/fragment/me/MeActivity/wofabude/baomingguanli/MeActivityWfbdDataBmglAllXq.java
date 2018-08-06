package com.zhangju.xingquban.interestclassapp.ui.fragment.me.MeActivity.wofabude.baomingguanli;

import android.os.Bundle;
import android.view.View;

import com.zhangju.xingquban.R;
import com.zhangju.xingquban.interestclassapp.base.BaseActivity;
import com.zhangju.xingquban.interestclassapp.ui.widget.PublicHead;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MeActivityWfbdDataBmglAllXq extends BaseActivity {


    @BindView(R.id.me_activity_wfbdData_bmglAllXq)
    PublicHead meActivityWfbdDataBmglAllXq;

    @Override
    public int getLayout() {
        return R.layout.activity_me_wfbd_data_bmgl_all_xq;
    }

    @Override
    public void initView() {
        setMeActivityWfbdDataBmglAllXq();
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

    public void setMeActivityWfbdDataBmglAllXq() {
        meActivityWfbdDataBmglAllXq.setTitle("票券详情");
        meActivityWfbdDataBmglAllXq.setShow(true, false, false);
        meActivityWfbdDataBmglAllXq.setBackClickListener(new View.OnClickListener() {
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
