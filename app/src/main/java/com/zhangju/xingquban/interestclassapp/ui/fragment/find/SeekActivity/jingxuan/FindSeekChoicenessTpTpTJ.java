package com.zhangju.xingquban.interestclassapp.ui.fragment.find.SeekActivity.jingxuan;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;

import com.zhangju.xingquban.R;
import com.zhangju.xingquban.interestclassapp.base.BaseFragment;
import com.zhangju.xingquban.interestclassapp.ui.fragment.me.MeVip.BottomDialog2;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

public class FindSeekChoicenessTpTpTJ extends BaseFragment {


    @BindView(R.id.fid_seek_chicoeness_tptp_button)
    Button fidSeekChicoenessTptpButton;
    @BindView(R.id.me_activity_fbhdTwo_tianjia)
    LinearLayout meActivityFbhdTwoTianjia;
    Unbinder unbinder;

    private BottomDialog2 bottomDialog2;

    @Override
    public void initData() {

    }

    @Override
    public int getMyLayout() {
        return R.layout.activity_find_seek_choiceness_tp_tp_tj;
    }

    @Override
    public void initView() {

    }

    @Override
    public void initListener() {

    }

    @OnClick({R.id.fid_seek_chicoeness_tptp_button})
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.fid_seek_chicoeness_tptp_button:
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
                                startActivity(new Intent(getActivity(), FindSeekChoicenessXqBmxx.class));
                            }
                        });


                    }
                });
                bottomDialog2.show(getFragmentManager());
                break;
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        unbinder = ButterKnife.bind(this, rootView);
        return rootView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
