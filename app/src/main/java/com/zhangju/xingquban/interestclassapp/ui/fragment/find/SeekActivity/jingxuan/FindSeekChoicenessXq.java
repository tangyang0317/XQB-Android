package com.zhangju.xingquban.interestclassapp.ui.fragment.find.SeekActivity.jingxuan;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.zhangju.xingquban.R;
import com.zhangju.xingquban.interestclassapp.base.BaseActivity;
import com.zhangju.xingquban.interestclassapp.ui.fragment.me.MeVip.BottomDialog2;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class FindSeekChoicenessXq extends BaseActivity {


    @BindView(R.id.seek_choiceness_xq_return)
    ImageView seekChoicenessXqReturn;
    @BindView(R.id.seek_choiceness_xq_fenx)
    ImageView seekChoicenessXqFenx;
    @BindView(R.id.seek_choiceness_xq_city)
    LinearLayout seekChoicenessXqCity;
    @BindView(R.id.seek_choiceness_xq_ckgd)
    TextView seekChoicenessXqCkgd;
    @BindView(R.id.seek_choiceness_xq_button)
    Button seekChoicenessXqButton;

    private BottomDialog2 bottomDialog2;

    @Override
    public int getLayout() {
        return R.layout.activity_find_seek_choiceness_xq;
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

    @Override
    public void onClick(View v) {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.seek_choiceness_xq_return, R.id.seek_choiceness_xq_fenx, R.id.seek_choiceness_xq_city, R.id.seek_choiceness_xq_ckgd, R.id.seek_choiceness_xq_button})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.seek_choiceness_xq_return:
                finish();
                break;
            case R.id.seek_choiceness_xq_fenx:
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
            case R.id.seek_choiceness_xq_city:

                break;

            //查看更多
            case R.id.seek_choiceness_xq_ckgd:

                break;


            case R.id.seek_choiceness_xq_button:

                bottomDialog2 = BottomDialog2.getInstance(R.layout.bottomdalog_find_hdxq, R.anim.in_bottom_to_top, getResources().getColor(R.color.translucent), new BottomDialog2.Callback() {
                    @Override
                    public void bind(View v) {
                        v.findViewById(R.id.me_ktvip_quxiao).setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                bottomDialog2.setRemove();
                            }
                        });

                        v.findViewById(R.id.seek_choiceness_pq_button).setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                startActivity(new Intent(FindSeekChoicenessXq.this, FindSeekChoicenessXqBmxx.class));
                            }
                        });


                    }
                });
                bottomDialog2.show(getSupportFragmentManager());
                break;
        }
    }
}
