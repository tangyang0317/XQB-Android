package com.zhangju.xingquban.interestclassapp.ui.fragment.me.MeActivity.wofabude;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.zhangju.xingquban.R;
import com.zhangju.xingquban.interestclassapp.base.BaseActivity;
import com.zhangju.xingquban.interestclassapp.ui.fragment.me.MeVip.BottomDialog2;
import com.zhangju.xingquban.interestclassapp.ui.widget.PublicHead;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MeActivityFbhdSucess extends BaseActivity {

    private BottomDialog2 bottomDialog2;

    @BindView(R.id.me_activity_fbhd_true)
    PublicHead meActivityFbhdTrue;
    @BindView(R.id.me_activity_sucess_button)
    Button meActivitySucessButton;

    @Override
    public int getLayout() {
        return R.layout.activity_me_fbhd_sucess;
    }

    @Override
    public void initView() {
        setMeActivityFbhdTrue();
    }

    @Override
    public void initData() {

    }

    @Override
    public void initListener() {

    }

    @OnClick({R.id.me_activity_sucess_button})
    public void onClick(View v) {
        switch (v.getId()) {

            case R.id.me_activity_sucess_button:
                bottomDialog2 = BottomDialog2.getInstance(R.layout.item_me_activity_fenx, R.anim.in_bottom_to_top, getResources().getColor(R.color.translucent), new BottomDialog2.Callback() {
                    @Override
                    public void bind(View v) {
                        v.findViewById(R.id.button_quxiao).setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                bottomDialog2.setRemove();

                            }
                        });
                    }
                });
                bottomDialog2.show(getSupportFragmentManager());
                break;
        }
    }

    public void setMeActivityFbhdTrue() {
        meActivityFbhdTrue.setTitle("成功发布");
        meActivityFbhdTrue.setShow(true, false, true);
        meActivityFbhdTrue.setRightTitle("关闭");
        meActivityFbhdTrue.setRightTextColor(R.color.color_main);
        meActivityFbhdTrue.setBackClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        meActivityFbhdTrue.setRightClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MeActivityFbhdSucess.this, MeActivityWfbd.class));
            }
        });
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
    }
}
