package com.zhangju.xingquban.interestclassapp.ui.fragment.me.Institutions.kcgl;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.zhangju.xingquban.R;
import com.zhangju.xingquban.interestclassapp.base.BaseActivity;
import com.zhangju.xingquban.interestclassapp.ui.widget.PublicHead;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MeJiGouKcglKctjStxx extends BaseActivity {

    @BindView(R.id.me_jg_kctjStxx)
    PublicHead meJgKctjStxx;
    @BindView(R.id.St_false)
    RelativeLayout StFalse;
    @BindView(R.id.St_True)
    RelativeLayout StTrue;
    @BindView(R.id.text_false)
    TextView textFalse;
    @BindView(R.id.text_true)
    TextView textTrue;
    private Intent intent;

    @Override
    public int getLayout() {
        return R.layout.activity_me_ji_gou_kcgl_kctj_stxx;
    }

    @Override
    public void initView() {
        setMeJgKctjStxx();
    }

    @Override
    public void initData() {

    }

    @Override
    public void initListener() {

    }

    @OnClick({R.id.St_false, R.id.St_True})
    public void onClick(View v) {
        switch (v.getId()) {

            case R.id.St_false:
                intent = new Intent();
                Bundle bundle = new Bundle();
                String s = textFalse.getText().toString();
                bundle.putString("s", s);
                intent.putExtras(bundle);
                setResult(RESULT_OK, intent);
                finish();
                break;

            case R.id.St_True:
                intent = new Intent();
                Bundle bundle1 = new Bundle();
                String s1 = textTrue.getText().toString();
                bundle1.putString("s", s1);
                intent.putExtras(bundle1);
                setResult(RESULT_OK, intent);
                finish();
                break;
        }
    }

    public void setMeJgKctjStxx() {
        meJgKctjStxx.setTitle("是否允许试听");
        meJgKctjStxx.setShow(true, false, false);
        meJgKctjStxx.setBackClickListener(new View.OnClickListener() {
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
