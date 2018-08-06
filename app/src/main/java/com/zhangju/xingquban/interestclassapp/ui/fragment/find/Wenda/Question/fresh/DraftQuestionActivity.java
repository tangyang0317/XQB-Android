package com.zhangju.xingquban.interestclassapp.ui.fragment.find.Wenda.Question.fresh;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.View;

import com.fastlib.annotation.ContentView;
import com.fastlib.app.FastActivity;
import com.zhangju.xingquban.R;
import com.zhangju.xingquban.interestclassapp.hplper.NoScrollViewPager;
import com.zhangju.xingquban.interestclassapp.ui.fragment.live.LiveTabLayoutFragment.LiveTabLayoutAdapter;
import com.zhangju.xingquban.interestclassapp.ui.widget.PublicHead;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by hqf on 2017/11/16.
 * 我的草稿列表
 */
@ContentView(R.layout.quesition_draft)
public class DraftQuestionActivity extends FastActivity {

    @BindView(R.id.pub_head)
    PublicHead pubHead;
    @BindView(R.id.draft_tablayout)
    TabLayout draftTablayout;
    @BindView(R.id.draft_viewpage)
    NoScrollViewPager draftViewpage;

    private DraftQuestionFragment draftQuestionFragment;
    private DraftAskFragment draftAskFragment;
    List<Fragment> mFragment = new ArrayList<>();

    List<String> mTitleList = Arrays.asList("提问草稿", "问答草稿");
    private LiveTabLayoutAdapter adapter;



    @Override
    protected void alreadyPrepared() {
        setDraftHead();
        setTabViewpager();

    }




    private void setTabViewpager() {
        draftAskFragment=new DraftAskFragment();
        draftQuestionFragment=new DraftQuestionFragment();

        mFragment.add(draftQuestionFragment);
        mFragment.add(draftAskFragment);

        draftTablayout.addTab(draftTablayout.newTab().setText(mTitleList.get(0)));
        draftTablayout.addTab(draftTablayout.newTab().setText(mTitleList.get(1)));

        adapter=new LiveTabLayoutAdapter(getSupportFragmentManager(),mFragment,mTitleList);
        draftViewpage.setAdapter(adapter);
        draftTablayout.setupWithViewPager(draftViewpage);
        draftViewpage.setOffscreenPageLimit(2);

    }

    private void setDraftHead() {
        pubHead.setRightTitle("");
        pubHead.setTitle("我的草稿");
        pubHead.setBackClickListener(new View.OnClickListener() {
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
