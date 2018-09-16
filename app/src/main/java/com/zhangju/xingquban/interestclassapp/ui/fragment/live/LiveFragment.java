package com.zhangju.xingquban.interestclassapp.ui.fragment.live;

import android.Manifest;
import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.youth.banner.Banner;
import com.zhangju.xingquban.R;
import com.zhangju.xingquban.interestclassapp.base.BaseFragment;
import com.zhangju.xingquban.interestclassapp.ui.fragment.live.LiveTabLayoutFragment.LiveTabAttention;
import com.zhangju.xingquban.interestclassapp.ui.fragment.live.LiveTabLayoutFragment.LiveTabHot;
import com.zhangju.xingquban.interestclassapp.ui.fragment.live.LiveTabLayoutFragment.LiveTabLayoutAdapter;
import com.zhangju.xingquban.interestclassapp.ui.fragment.live.LiveTabLayoutFragment.LiveTabNewEst;
import com.zhangju.xingquban.interestclassapp.util.SpUtil;
import com.zhangju.xingquban.interestclassapp.view.BannerHelper;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


/**
 * Created by zsl on 2017/6/14.
 */
//直播模块
public class LiveFragment extends BaseFragment implements View.OnClickListener {

    private final String TAG = "LiveFragment直播模块列表";
    /**
     * 6.0权限处理
     **/
    private final int WRITE_PERMISSION_REQ_CODE = 100;
    @BindView(R.id.live_main_back)
    ImageView liveMainBack;
    @BindView(R.id.home_banner)
    Banner mHomeBanner;
    private ImageView back;
    private TabLayout tabLayout;

    private String url;
    private ViewPager viewPager;
    private LiveTabLayoutAdapter liveTabLayoutAdapter;
    private List<Fragment> fragmentList;
    private List<String> tabstrList;
    private LiveTabAttention liveTabAttention;
    private LiveTabNewEst liveTabNewEst;
    private LiveTabHot liveTabHot;
    private SwitchBroadCastReceiver receiver;
    private BannerHelper mBannerHelper;

    //初始化
    @Override
    public void initData() {
        mBannerHelper = new BannerHelper(getActivity());
        mBannerHelper.init(mHomeBanner).loadBannerDate("8");
        initBroadcast();
    }

    private void initBroadcast() {
        receiver = new SwitchBroadCastReceiver();
        IntentFilter intentFilter = new IntentFilter("cuttab");
        getActivity().registerReceiver(receiver, intentFilter);
    }


    @Override
    public int getMyLayout() {
        return R.layout.live_fragment;
    }

    @Override
    public void initView() {

    }

    @Override
    public void onResume() {
        super.onResume();
        //判断返回键是否需要展示
        boolean isBack = SpUtil.getBoolean(getActivity(), "isBack");
        if (isBack) {
            liveMainBack.setVisibility(View.VISIBLE);
        } else {
            liveMainBack.setVisibility(View.INVISIBLE);
        }

    }

    @Override
    public void initListener() {

    }

    @Override
    public void onClick(View v) {

    }

    private boolean checkPublishPermission() {
        if (Build.VERSION.SDK_INT >= 23) {
            List<String> permissions = new ArrayList<>();
            if (PackageManager.PERMISSION_GRANTED != ActivityCompat.checkSelfPermission(getContext(), Manifest
                    .permission.WRITE_EXTERNAL_STORAGE)) {
                permissions.add(Manifest.permission.WRITE_EXTERNAL_STORAGE);
            }
            if (PackageManager.PERMISSION_GRANTED != ActivityCompat.checkSelfPermission(getContext(), Manifest
                    .permission.CAMERA)) {
                permissions.add(Manifest.permission.CAMERA);
            }
            if (PackageManager.PERMISSION_GRANTED != ActivityCompat.checkSelfPermission(getContext(), Manifest
                    .permission.RECORD_AUDIO)) {
                permissions.add(Manifest.permission.RECORD_AUDIO);
            }
            if (PackageManager.PERMISSION_GRANTED != ActivityCompat.checkSelfPermission(getContext(), Manifest
                    .permission.READ_PHONE_STATE)) {
                permissions.add(Manifest.permission.READ_PHONE_STATE);
            }
            if (permissions.size() != 0) {
                ActivityCompat.requestPermissions((Activity) getContext(),
                        (String[]) permissions.toArray(new String[0]),
                        WRITE_PERMISSION_REQ_CODE);
                return false;
            }
        }
        return true;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        ButterKnife.bind(this, rootView);

        initLayout(view);
        return rootView;
    }

    private void initLayout(View view) {
        tabLayout = (TabLayout) view.findViewById(R.id.live_tablayout);
        viewPager = (ViewPager) view.findViewById(R.id.live_viewpage);

        liveTabAttention = new LiveTabAttention();
        liveTabHot = new LiveTabHot();
        liveTabNewEst = new LiveTabNewEst();

        fragmentList = new ArrayList<>();
        fragmentList.add(liveTabAttention);//关注
        fragmentList.add(liveTabHot);//热门
        fragmentList.add(liveTabNewEst);//最新


        tabstrList = new ArrayList<>();
        tabstrList.add("关注");
        tabstrList.add("热门");
        tabstrList.add("最新");


        tabLayout.addTab(tabLayout.newTab().setText(tabstrList.get(0)));
        tabLayout.addTab(tabLayout.newTab().setText(tabstrList.get(1)));
        tabLayout.addTab(tabLayout.newTab().setText(tabstrList.get(2)));


        liveTabLayoutAdapter = new LiveTabLayoutAdapter(getActivity().getSupportFragmentManager(), fragmentList, tabstrList);
        viewPager.setAdapter(liveTabLayoutAdapter);
        viewPager.setOffscreenPageLimit(3);
        viewPager.setCurrentItem(1);
        tabLayout.setupWithViewPager(viewPager);


    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mBannerHelper.stopBanner();
        getActivity().unregisterReceiver(receiver);
    }

    @OnClick(R.id.live_main_back)
    public void onViewClicked() {
        getActivity().finish();
    }

    //接收关注页面切换tab
    class SwitchBroadCastReceiver
            extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            int index = intent.getIntExtra("index", 0);
            viewPager.setCurrentItem(index);

        }
    }
}
