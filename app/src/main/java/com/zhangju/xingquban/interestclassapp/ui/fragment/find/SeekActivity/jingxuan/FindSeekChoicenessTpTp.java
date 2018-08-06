package com.zhangju.xingquban.interestclassapp.ui.fragment.find.SeekActivity.jingxuan;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.zhangju.xingquban.R;
import com.zhangju.xingquban.interestclassapp.base.BaseFragment;
import com.zhangju.xingquban.interestclassapp.hplper.TabLayoutSetIndicator;
import com.zhangju.xingquban.interestclassapp.ui.fragment.live.LiveTabLayoutFragment.LiveTabLayoutAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class FindSeekChoicenessTpTp extends BaseFragment {
    private LiveTabLayoutAdapter liveTabLayoutAdapter;
    private List<Fragment> fragmentList;
    private List<String> stringList;

    private FindSeekChoicenessTpTpTJ findSeekChoicenessTpTpTJ;
    private FindSeekChoicenessTpTpZx findSeekChoicenessTpTpZx;
    @BindView(R.id.tp_tp_tablayout)
    TabLayout tpTpTablayout;
    @BindView(R.id.tp_tp_viewpage)
    ViewPager tpTpViewpage;
    Unbinder unbinder;

    @Override
    public void initData() {

    }

    @Override
    public int getMyLayout() {
        return R.layout.activity_find_seek_choiceness_tp_tp;
    }

    @Override
    public void initView() {
        findSeekChoicenessTpTpTJ = new FindSeekChoicenessTpTpTJ();
        findSeekChoicenessTpTpZx = new FindSeekChoicenessTpTpZx();

        fragmentList = new ArrayList<>();
        fragmentList.add(findSeekChoicenessTpTpTJ);
        fragmentList.add(findSeekChoicenessTpTpZx);

        stringList = new ArrayList<>();
        stringList.add("推荐");
        stringList.add("最新");

//        tabLayout.setTabMode(TabLayout.MODE_SCROLLABLE);

        tpTpTablayout.addTab(tpTpTablayout.newTab().setText(stringList.get(0)));
        tpTpTablayout.addTab(tpTpTablayout.newTab().setText(stringList.get(1)));

        liveTabLayoutAdapter = new LiveTabLayoutAdapter(getActivity().getSupportFragmentManager(), fragmentList, stringList);

        tpTpViewpage.setAdapter(liveTabLayoutAdapter);
        tpTpTablayout.setupWithViewPager(tpTpViewpage);

        tpTpTablayout.post(new Runnable() {
            @Override
            public void run() {
                TabLayoutSetIndicator.setIndicator(tpTpTablayout, 60, 60);
            }
        });

    }

    @Override
    public void initListener() {

    }

    @Override
    public void onClick(View v) {

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
