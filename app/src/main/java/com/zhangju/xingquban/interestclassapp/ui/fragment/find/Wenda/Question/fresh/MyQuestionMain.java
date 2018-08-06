package com.zhangju.xingquban.interestclassapp.ui.fragment.find.Wenda.Question.fresh;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.fastlib.annotation.ContentView;
import com.fastlib.app.FastActivity;
import com.youth.banner.Banner;
import com.zhangju.xingquban.R;
import com.zhangju.xingquban.interestclassapp.adapter.SingleTextAdapter;
import com.zhangju.xingquban.interestclassapp.adapter.baseadpter.OnListItemClickListener;
import com.zhangju.xingquban.interestclassapp.ui.fragment.live.LiveTabLayoutFragment.LiveTabLayoutAdapter;
import com.zhangju.xingquban.interestclassapp.view.BannerHelper;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by hqf on 2017/11/16.
 * 我的问答主页Activity
 */

@ContentView(R.layout.activity_my_question_main)
public class MyQuestionMain extends FastActivity {

    @BindView(R.id.find_return)
    RelativeLayout findReturn;
    @BindView(R.id.image_over)
    ImageView imageOver;
    @BindView(R.id.linear_question)
    LinearLayout linearQuestion;
    @BindView(R.id.quesion_add)
    RelativeLayout quesionAdd;
    @BindView(R.id.findWd_tablayout)
    TabLayout findWdTablayout;
    @BindView(R.id.question_viewpage)
    ViewPager questionViewpage;
    @BindView(R.id.tv_my_question)
    TextView tv_my_question;
    @BindView(R.id.home_banner)
    Banner mHomeBanner;

    private LiveTabLayoutAdapter liveTabLayoutAdapter;


    private NewestFragment newestFragment;//最新fragment
    private RecommendFragment recommendFragment;//推荐fragment

    List<Fragment> mFragments = new ArrayList<>();
    List<String> mTitles = Arrays.asList("推荐", "最新");

    private PopupWindow mPopupWindow;
    private List<String> mSelectTitle = Arrays.asList("我的提问", "我的回答", "我的草稿", "我的收藏");
    private BannerHelper mBannerHelper;


    @Override
    protected void alreadyPrepared() {
        initData();
        initBanner();

    }

    private void initBanner() {
        mBannerHelper = new BannerHelper(mContext);
        mBannerHelper.init(mHomeBanner).loadBannerDate("16");
    }

    private void initData() {
        newestFragment = new NewestFragment();
        recommendFragment = new RecommendFragment();

        mFragments.add(newestFragment);
        mFragments.add(recommendFragment);

        findWdTablayout.addTab(findWdTablayout.newTab().setText(mTitles.get(0)));
        findWdTablayout.addTab(findWdTablayout.newTab().setText(mTitles.get(1)));

        liveTabLayoutAdapter = new LiveTabLayoutAdapter(getSupportFragmentManager(), mFragments, mTitles);
        questionViewpage.setAdapter(liveTabLayoutAdapter);
        questionViewpage.setOffscreenPageLimit(2);
        findWdTablayout.setupWithViewPager(questionViewpage);


    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.find_return, R.id.linear_question, R.id.quesion_add})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            //返回操作
            case R.id.find_return:
                finish();
                break;
            //popwindow
            case R.id.linear_question:
                showselect();

                break;
            //提问
            case R.id.quesion_add:
                startActivity(new Intent(MyQuestionMain.this, MyAddQuestionActivity.class));
                break;
        }
    }

    /**
     * show选择pop
     */
    RecyclerView listView;

    private void showselect() {
        LayoutInflater inflater = LayoutInflater.from(MyQuestionMain.this);
        View inflate = inflater.inflate(
                R.layout.popup_text_item, null, false);

        listView = (RecyclerView) inflate.findViewById(R.id.question_listview);
        SingleTextAdapter singleTextAdapter = new SingleTextAdapter(MyQuestionMain.this, mSelectTitle);
        listView.setLayoutManager(new LinearLayoutManager(MyQuestionMain.this));
        listView.setAdapter(singleTextAdapter);



        mPopupWindow = new PopupWindow(inflate, linearQuestion.getWidth(),
                ViewGroup.LayoutParams.WRAP_CONTENT, true);

        Drawable drawable = ContextCompat.getDrawable(this, R.drawable.white_rectangle);
        mPopupWindow.setBackgroundDrawable(drawable);

        mPopupWindow.showAsDropDown(tv_my_question, -10, 10);// 在控件下方显示popwindow
        mPopupWindow.update();
        singleTextAdapter.setOnListItemClickListener(new OnListItemClickListener() {
            @Override
            public void onItemClickListener(int position, View v) {
                mPopupWindow.dismiss();
                switch (position) {
                    //我的提问
                    case 0:
                        startActivity(new Intent(MyQuestionMain.this, AskQuestionActivity.class));
                        break;
                    //我的回答
                    case 1:
                        startActivity(new Intent(MyQuestionMain.this, AnswerQuestionActivity.class));
                        break;
                    //我的草稿
                    case 2:
                        startActivity(new Intent(MyQuestionMain.this, DraftQuestionActivity.class));
                        break;
                    //我的收藏
                    case 3:
                        startActivity(new Intent(MyQuestionMain.this, CollectQuestionActivity.class));
                        break;

                }
            }
        });

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mBannerHelper.stopBanner();
    }
}
