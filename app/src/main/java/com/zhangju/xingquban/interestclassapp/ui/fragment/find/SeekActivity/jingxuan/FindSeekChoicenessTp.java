package com.zhangju.xingquban.interestclassapp.ui.fragment.find.SeekActivity.jingxuan;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.LinearLayout;

import com.zhangju.xingquban.R;
import com.zhangju.xingquban.interestclassapp.base.BaseActivity;
import com.zhangju.xingquban.interestclassapp.hplper.TabLayoutSetIndicator;
import com.zhangju.xingquban.interestclassapp.ui.fragment.live.LiveTabLayoutFragment.LiveTabLayoutAdapter;
import com.zhangju.xingquban.interestclassapp.ui.fragment.me.MeVip.BottomDialog2;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class FindSeekChoicenessTp extends BaseActivity {


    @BindView(R.id.find_seek_choiceness_return)
    LinearLayout findSeekChoicenessReturn;
    @BindView(R.id.choiceness_tablayout)
    TabLayout choicenessTablayout;
    @BindView(R.id.find_seek_choiceness_fenx)
    LinearLayout findSeekChoicenessFenx;
    @BindView(R.id.choiceness_viewpage)
    ViewPager choicenessViewpage;
    private BottomDialog2 bottomDialog2;

    private LiveTabLayoutAdapter liveTabLayoutAdapter;
    private List<Fragment> fragmentList;
    private List<String> stringList;

    private FindSeekChoicenessTpXq findSeekChoicenessTpXq;
    private FindSeekChoicenessTpTp findSeekChoicenessTpTp;

    @Override
    public int getLayout() {
        return R.layout.activity_find_seek_choiceness_tp;
    }

    @Override
    public void initView() {
        findSeekChoicenessTpXq = new FindSeekChoicenessTpXq();
        findSeekChoicenessTpTp = new FindSeekChoicenessTpTp();

        fragmentList = new ArrayList<>();
        fragmentList.add(findSeekChoicenessTpXq);
        fragmentList.add(findSeekChoicenessTpTp);

        stringList = new ArrayList<>();
        stringList.add("详情");
        stringList.add("投票");

//        tabLayout.setTabMode(TabLayout.MODE_SCROLLABLE);

        choicenessTablayout.addTab(choicenessTablayout.newTab().setText(stringList.get(0)));
        choicenessTablayout.addTab(choicenessTablayout.newTab().setText(stringList.get(1)));

        liveTabLayoutAdapter = new LiveTabLayoutAdapter(this.getSupportFragmentManager(), fragmentList, stringList);

        choicenessViewpage.setAdapter(liveTabLayoutAdapter);

        choicenessTablayout.setupWithViewPager(choicenessViewpage);
        choicenessTablayout.post(new Runnable() {
            @Override
            public void run() {
                TabLayoutSetIndicator.setIndicator(choicenessTablayout, 50, 50);
            }
        });
    }

    @Override
    public void initData() {

    }

    @Override
    public void initListener() {

    }

    @OnClick({R.id.find_seek_choiceness_return, R.id.find_seek_choiceness_fenx})
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.find_seek_choiceness_return:
                finish();
                break;
            case R.id.find_seek_choiceness_fenx:
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
    }
}
