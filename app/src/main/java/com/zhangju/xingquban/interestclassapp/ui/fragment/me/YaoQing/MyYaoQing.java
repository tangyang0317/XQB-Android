package com.zhangju.xingquban.interestclassapp.ui.fragment.me.YaoQing;

import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import com.zhangju.xingquban.R;
import com.zhangju.xingquban.interestclassapp.base.BaseActivity;
import com.zhangju.xingquban.interestclassapp.ui.widget.PublicHead;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MyYaoQing extends BaseActivity {

    @BindView(R.id.me_yaoqing_head)
    PublicHead meYaoqingHead;

    @BindView(R.id.me_yaoqing_weixin)
    LinearLayout meYaoqingWeixin;
    @BindView(R.id.me_yaoqing_pengyouquan)
    LinearLayout meYaoqingPengyouquan;
    @BindView(R.id.me_yaoqing_weibo)
    LinearLayout meYaoqingWeibo;
    @BindView(R.id.me_yaoqing_qq)
    LinearLayout meYaoqingQq;
    @BindView(R.id.share_icon_qqkong)
    LinearLayout shareIconQqkong;

    @Override
    public int getLayout() {
        return R.layout.activity_my_yao_qing;
    }

    @Override
    public void initView() {
        getMeYaoQHead();
    }

    @Override
    public void initData() {

    }

    @Override
    public void initListener() {

    }

    @OnClick({R.id.me_yaoqing_weixin, R.id.me_yaoqing_pengyouquan, R.id.me_yaoqing_weibo, R.id.me_yaoqing_qq, R.id.share_icon_qqkong})
    public void onClick(View v) {
        switch (v.getId()) {

            //分享微信
            case R.id.me_yaoqing_weixin:

                break;

            //分享朋友圈
            case R.id.me_yaoqing_pengyouquan:

                break;

            //分享微博
            case R.id.me_yaoqing_weibo:

                break;

            //分享QQ
            case R.id.me_yaoqing_qq:

                break;

            //分享QQ空间
            case R.id.share_icon_qqkong:

                break;
        }
    }

    public void getMeYaoQHead() {
        meYaoqingHead.setTitle("邀请有奖");
        meYaoqingHead.setShow(true, false, false);
        meYaoqingHead.setBackClickListener(new View.OnClickListener() {
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
