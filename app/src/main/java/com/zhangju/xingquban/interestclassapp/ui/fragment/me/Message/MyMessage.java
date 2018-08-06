package com.zhangju.xingquban.interestclassapp.ui.fragment.me.Message;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import com.zhangju.xingquban.R;
import com.zhangju.xingquban.interestclassapp.base.BaseActivity;
import com.zhangju.xingquban.interestclassapp.ui.widget.PublicHead;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MyMessage extends BaseActivity {

    @BindView(R.id.me_message_head)
    PublicHead meMessageHead;
    @BindView(R.id.me_message_mrdt)
    LinearLayout meMessageMrdt;
    @BindView(R.id.me_message_xtxx)
    LinearLayout meMessageXtxx;

    @Override
    public int getLayout() {
        return R.layout.activity_my_message;
    }

    @Override
    public void initView() {
        getMeWalletMessageHead();
    }

    @Override
    public void initData() {

    }

    @Override
    public void initListener() {

    }

    @OnClick({R.id.me_message_mrdt, R.id.me_message_xtxx})
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.me_message_mrdt:
                startActivity(new Intent(MyMessage.this, MeMessageDay.class));
                break;
            case R.id.me_message_xtxx:
                startActivity(new Intent(MyMessage.this, MeMessageDay.class));
                break;
        }
    }

    public void getMeWalletMessageHead() {
        meMessageHead.setTitle("消息中心");
        meMessageHead.setShow(true, false, false);
        meMessageHead.setBackClickListener(new View.OnClickListener() {
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
