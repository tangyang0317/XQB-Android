package com.zhangju.xingquban.interestclassapp.ui.fragment.me.MeActivity.wofabude;

import android.os.Bundle;
import android.view.View;

import com.zhangju.xingquban.R;
import com.zhangju.xingquban.interestclassapp.base.BaseActivity;
import com.zhangju.xingquban.interestclassapp.ui.fragment.me.MeVip.BottomDialog2;
import com.zhangju.xingquban.interestclassapp.ui.widget.PublicHead;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MeActivityWfbdHdfx extends BaseActivity {


    @BindView(R.id.me_yaoqinghan_head)
    PublicHead meYaoqinghanHead;
    private BottomDialog2 bottomDialog2;

    @Override
    public int getLayout() {
        return R.layout.activity_me_wfbd_hdfx;
    }

    @Override
    public void initView() {
        setMeYaoqinghanHead();
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

    public void setMeYaoqinghanHead() {
        meYaoqinghanHead.setTitle("邀请函");
        meYaoqinghanHead.setShow(true, false, true);
        meYaoqinghanHead.setImgSearch(R.mipmap.me_yaoqinghan_ll_fenx);
        meYaoqinghanHead.setBackClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        meYaoqinghanHead.setImgSeachRightClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
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
            }
        });
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
    }
}
