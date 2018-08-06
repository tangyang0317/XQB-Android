package com.zhangju.xingquban.interestclassapp.ui.fragment.me.MeActivity.wodepiaoquan;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.zhangju.xingquban.R;
import com.zhangju.xingquban.interestclassapp.base.BaseActivity;
import com.zhangju.xingquban.interestclassapp.ui.fragment.me.MeActivity.wofabude.baomingguanli.MeActivityWdpqAllXqTp;
import com.zhangju.xingquban.interestclassapp.ui.fragment.me.MeVip.BottomDialog2;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MeActivityWdpqAllXq extends BaseActivity {

    @BindView(R.id.me_wdpq_allxq_return)
    ImageView meWdpqAllxqReturn;
    @BindView(R.id.me_wdpq_allxq_fenx)
    ImageView meWdpqAllxqFenx;
    @BindView(R.id.me_wdpq_allxq_tuipiao)
    TextView meWdpqAllxqTuipiao;
    @BindView(R.id.me_wdpq_allxq_button)
    Button meWdpqAllxqButton;

    private BottomDialog2 bottomDialog2;

    @Override
    public int getLayout() {
        return R.layout.activity_me_wdpq_all_xq;
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

    @OnClick({R.id.me_wdpq_allxq_return, R.id.me_wdpq_allxq_fenx, R.id.me_wdpq_allxq_tuipiao, R.id.me_wdpq_allxq_button})
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.me_wdpq_allxq_return:
                finish();
                break;
            case R.id.me_wdpq_allxq_fenx:
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

            //退票
            case R.id.me_wdpq_allxq_tuipiao:
                startActivity(new Intent(this, MeActivityWdpqAllXqTp.class));
                break;
            case R.id.me_wdpq_allxq_button:

                break;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
    }
}
