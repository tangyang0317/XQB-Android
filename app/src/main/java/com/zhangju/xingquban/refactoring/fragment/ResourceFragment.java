package com.zhangju.xingquban.refactoring.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.fastlib.app.EventObserver;
import com.fastlib.utils.KeyBoardUtils;
import com.youth.banner.Banner;
import com.zhangju.xingquban.R;
import com.zhangju.xingquban.interestclassapp.base.BaseFragment;
import com.zhangju.xingquban.interestclassapp.ui.fragment.find.EventResourceFilterContent;
import com.zhangju.xingquban.interestclassapp.ui.fragment.find.SeekResource.AllResourceFragment;
import com.zhangju.xingquban.interestclassapp.ui.fragment.find.SeekResource.AudioResFragment;
import com.zhangju.xingquban.interestclassapp.ui.fragment.find.SeekResource.FindResource;
import com.zhangju.xingquban.interestclassapp.ui.fragment.find.SeekResource.InformationFragment;
import com.zhangju.xingquban.interestclassapp.ui.fragment.find.SeekResource.PicResFragment;
import com.zhangju.xingquban.interestclassapp.ui.fragment.find.SeekResource.VideoResFragment;
import com.zhangju.xingquban.interestclassapp.ui.fragment.live.LiveTabLayoutFragment.LiveTabLayoutAdapter;
import com.zhangju.xingquban.interestclassapp.view.BannerHelper;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * <pre>
 *     author : tangyang
 *     e-mail : ty766998873@163.com
 *     time   : 2018/11/10
 *     desc   :
 *     version: 2.6.0
 * </pre>
 */
public class ResourceFragment extends BaseFragment {

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
    Banner mHomeBanner;

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
    public void initData() {
        mBannerHelper = new BannerHelper(getActivity());
        mBannerHelper.init(mHomeBanner).loadBannerDate("9");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        ButterKnife.bind(this, rootView);
        initView(view);
        return rootView;
    }

    @Override
    public int getMyLayout() {
        return R.layout.activity_find_resources;
    }

    @Override
    public void initView() {

    }

    public void initView(View view) {
        mIvBack.setVisibility(View.INVISIBLE);
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


        liveTabLayoutAdapter = new LiveTabLayoutAdapter(this.getChildFragmentManager(), fragmentList, tabstrList);
        resourceViewpage.setAdapter(liveTabLayoutAdapter);
        tabLayout.setupWithViewPager(resourceViewpage);
        resourceViewpage.setOffscreenPageLimit(2);
    }

    @Override
    public void initListener() {

        mSearchContent.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                String filterStr = v.getText().toString();
                if ((actionId == EditorInfo.IME_ACTION_SEARCH || actionId == EditorInfo.IME_ACTION_UNSPECIFIED) && event != null) {
                    if (event.getAction() == KeyEvent.ACTION_UP) {
                        KeyBoardUtils.closeKeybord(mSearchContent);
                        EventObserver.getInstance().sendEvent(getActivity(), new EventResourceFilterContent(filterStr));
                    }
                    return true;
                }
                return false;
            }
        });
    }

    @Override
    public void onClick(View view) {

    }
}
