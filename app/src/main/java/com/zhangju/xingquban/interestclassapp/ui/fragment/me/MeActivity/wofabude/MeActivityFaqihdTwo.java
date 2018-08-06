package com.zhangju.xingquban.interestclassapp.ui.fragment.me.MeActivity.wofabude;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;

import com.zhangju.xingquban.R;
import com.zhangju.xingquban.interestclassapp.base.BaseActivity;
import com.zhangju.xingquban.interestclassapp.ui.fragment.me.MeVip.BottomDialog2;
import com.zhangju.xingquban.interestclassapp.ui.widget.PublicHead;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MeActivityFaqihdTwo extends BaseActivity {

    @BindView(R.id.me_activity_wfbdFqhdTwoHead)
    PublicHead meActivityWfbdFqhdTwoHead;
    @BindView(R.id.image_faqihuodong)
    ImageView imageFaqihuodong;
    @BindView(R.id.me_activity_fbhdTwo_tianjia)
    LinearLayout meActivityFbhdTwoTianjia;
    @BindView(R.id.me_activity_fbhdTwo_xianshi)
    ScrollView meActivityFbhdTwoXianshi;
    @BindView(R.id.me_activity_fbhdTwo_button)
    Button meActivityFbhdTwoButton;

    private BottomDialog2 bottomDialog2;

    @Override
    public int getLayout() {
        return R.layout.activity_me_faqihd_two;
    }

    @Override
    public void initView() {
        setMeActivityWfbdFqhdTwoHead();
    }

    @Override
    public void initData() {

    }

    @Override
    public void initListener() {

    }

    @OnClick({R.id.me_activity_fbhdTwo_button})
    public void onClick(View v) {
        switch (v.getId()) {

            case R.id.me_activity_fbhdTwo_button:
//
//                bottomDialog2 = BottomDialog2.getInstance(R.layout.item_me_jg_jjtj, R.anim.in_bottom_to_top, getResources().getColor(R.color.translucent), new BottomDialog2.Callback() {
//
//                    @Override
//                    public void bind(View v) {
//                        v.findViewById(R.id.me_jg_spkc_tp).setOnClickListener(new View.OnClickListener() {
//                            @Override
//                            public void onComplete(View v) {
////                                selectpic();
//                                startActivity(new Intent(MeActivityFaqihdTwo.this, MeJiGouJgjjTj.class));
//                            }
//                        });
//                        v.findViewById(R.id.me_jg_spkc_wb).setOnClickListener(new View.OnClickListener() {
//                            @Override
//                            public void onComplete(View v) {
//                                startActivity(new Intent(MeActivityFaqihdTwo.this, MeJiGouJgjjWb.class));
//                            }
//                        });
//                        v.findViewById(R.id.me_jg_spkc_quxiao).setOnClickListener(new View.OnClickListener() {
//                            @Override
//                            public void onComplete(View v) {
//                                bottomDialog2.setRemove();
//                            }
//                        });
//                        v.findViewById(R.id.me_jg_spkc_quxiaox).setOnClickListener(new View.OnClickListener() {
//                            @Override
//                            public void onComplete(View v) {
//                                bottomDialog2.setRemove();
//                            }
//                        });
//
//                    }
//                });
//                bottomDialog2.show(getSupportFragmentManager());

                meActivityFbhdTwoTianjia.setVisibility(View.GONE);
                meActivityFbhdTwoXianshi.setVisibility(View.VISIBLE);
                break;

        }
    }

    public void setMeActivityWfbdFqhdTwoHead() {
        meActivityWfbdFqhdTwoHead.setTitle("发布活动");
        meActivityWfbdFqhdTwoHead.setShow(true, false, true);
        meActivityWfbdFqhdTwoHead.setRightTitle("下一步");
        meActivityWfbdFqhdTwoHead.setRightTextColor(R.color.color_main);
        meActivityWfbdFqhdTwoHead.setBackClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        meActivityWfbdFqhdTwoHead.setRightClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MeActivityFaqihdTwo.this, MeActivityFaqihdThree.class));
            }
        });
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
    }
}
