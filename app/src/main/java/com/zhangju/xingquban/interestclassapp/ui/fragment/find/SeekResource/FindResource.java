package com.zhangju.xingquban.interestclassapp.ui.fragment.find.SeekResource;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.fastlib.app.EventObserver;
import com.fastlib.utils.KeyBoardUtils;
import com.youth.banner.Banner;
import com.zhangju.xingquban.R;
import com.zhangju.xingquban.interestclassapp.base.BaseActivity;
import com.zhangju.xingquban.interestclassapp.ui.fragment.find.EventResourceFilterContent;
import com.zhangju.xingquban.interestclassapp.ui.fragment.live.LiveTabLayoutFragment.LiveTabLayoutAdapter;
import com.zhangju.xingquban.interestclassapp.view.BannerHelper;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 发现模块资源主页
 */
public class FindResource extends BaseActivity {
    /*@BindView(R.id.resource_header)
    PublicHead resource_header;*/
    @BindView(R.id.searchContent)
    EditText mSearchContent;
    @BindView(R.id.back)
    ImageView mIvBack;

    @BindView(R.id.resource_tablayout)
    TabLayout tabLayout;
//    screenOrientation

    @BindView(R.id.resource_viewpage)
    ViewPager resourceViewpage;
    @BindView(R.id.home_banner)
    Banner    mHomeBanner;

    private List<String> tabstrList;
    /*复用直播的轮播控件适配器*/
    private LiveTabLayoutAdapter liveTabLayoutAdapter;
    /*五个Fragment的定义*/

    private AllResourceFragment allResourceFragment;
    private AudioResFragment audioResFragment;
    private InformationFragment informationFragment;
    private PicResFragment picResFragment;
    private VideoResFragment videoResFragment;
    /*Fragment的集合*/
    private List<Fragment> fragmentList;
    private BannerHelper mBannerHelper;

    @Override
    public int getLayout() {
        return R.layout.activity_find_resources;
    }

    @Override
    public void initView() {
        setHead();
        tabstrList = new ArrayList<>();
        tabstrList.add("全部");
        tabstrList.add("资讯");
        tabstrList.add("图片");
        tabstrList.add("视频");
        tabstrList.add("音频");
        /*Fragment部分*/
        fragmentList = new ArrayList<>();
        allResourceFragment = new AllResourceFragment();
        audioResFragment = new AudioResFragment();
        informationFragment = new InformationFragment();
        picResFragment = new PicResFragment();
        videoResFragment = new VideoResFragment();
        fragmentList.add(allResourceFragment);
        fragmentList.add(informationFragment);
        fragmentList.add(picResFragment);
        fragmentList.add(videoResFragment);
        fragmentList.add(audioResFragment);

        tabLayout.addTab(tabLayout.newTab().setText(tabstrList.get(0)));
        tabLayout.addTab(tabLayout.newTab().setText(tabstrList.get(1)));
        tabLayout.addTab(tabLayout.newTab().setText(tabstrList.get(2)));
        tabLayout.addTab(tabLayout.newTab().setText(tabstrList.get(3)));
        tabLayout.addTab(tabLayout.newTab().setText(tabstrList.get(4)));


        liveTabLayoutAdapter = new LiveTabLayoutAdapter(FindResource.this.getSupportFragmentManager(), fragmentList, tabstrList);
        resourceViewpage.setAdapter(liveTabLayoutAdapter);
        tabLayout.setupWithViewPager(resourceViewpage);
        resourceViewpage.setOffscreenPageLimit(2);

    }

    private void setHead() {

    }

    @Override
    public void initData() {
        mBannerHelper = new BannerHelper(mContext);
        mBannerHelper.init(mHomeBanner).loadBannerDate("9");
    }

    @Override
    public void initListener() {
        mIvBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FindResource.this.finish();
            }
        });
        mSearchContent.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event){
                String filterStr=v.getText().toString();
                if((actionId==EditorInfo.IME_ACTION_SEARCH||actionId==EditorInfo.IME_ACTION_UNSPECIFIED)&&event!=null){
                    if(event.getAction()==KeyEvent.ACTION_UP){
                        KeyBoardUtils.closeKeybord(mSearchContent);
                        EventObserver.getInstance().sendEvent(FindResource.this,new EventResourceFilterContent(filterStr));
                    }
                    return true;
                }
                return false;
            }
        });
    }

    @Override
    public void onClick(View v) {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
    }

    @Override
    protected void onPause() {
        super.onPause();
        KeyBoardUtils.closeKeybord(mSearchContent);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mBannerHelper != null) {
            mBannerHelper.stopBanner();
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        if (mBannerHelper != null) {
            mBannerHelper.stopBanner();
        }

    }
}
