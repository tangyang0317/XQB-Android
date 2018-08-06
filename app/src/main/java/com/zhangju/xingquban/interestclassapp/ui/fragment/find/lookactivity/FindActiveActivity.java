package com.zhangju.xingquban.interestclassapp.ui.fragment.find.lookactivity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.util.Pair;
import android.support.v4.view.ViewPager;
import android.view.View;

import com.fastlib.adapter.CommonFragmentViewPagerAdapter;
import com.fastlib.annotation.ContentView;
import com.fastlib.app.FastActivity;
import com.fastlib.net.Request;
import com.fastlib.net.SimpleListener;
import com.fastlib.widget.TitleBar;
import com.zhangju.xingquban.R;
import com.zhangju.xingquban.interestclassapp.refactor.discover.fragment.ActiveListFragment;
import com.zhangju.xingquban.interestclassapp.refactor.me.activity.ActiveActivity;
import com.zhangju.xingquban.interestclassapp.refactor.user.UserManager;
import com.zhangju.xingquban.interestclassapp.ui.fragment.find.lookactivity.bean.LooktabListBean;
import com.zhangju.xingquban.interestclassapp.ui.fragment.live.LiveTabLayoutFragment.LiveTabLayoutAdapter;
import com.zhangju.xingquban.interestclassapp.util.LoadingDialog;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


/**
 * create by hqf 2017/11/22
 * 发现模块--找活动主页
 */
@ContentView(R.layout.activity_look_main)
public class FindActiveActivity extends FastActivity {
    @BindView(R.id.titleBar)
    TitleBar mTitleBar;
    @BindView(R.id.look_tablayout)
    TabLayout lookTablayout;
    @BindView(R.id.look_viewpage)
    ViewPager lookViewpage;

//    private List<Fragment> mLookListFrag = new ArrayList<>();
//    private List<String> mListTitle = new ArrayList<>();

    private LiveTabLayoutAdapter adapter;
    private LoadingDialog loadingDialog;


    @Override
    protected void alreadyPrepared() {
        loadingDialog = new LoadingDialog(FindActiveActivity.this);
        loadingDialog.setLoading("");

        setPubHead();
        getTabTabTitle();
    }

    //获取tablayout标题数据
    private void getTabTabTitle() {
        loadingDialog.show();
        final Request request = Request.obtain(LookActiviInterface.POST_LOOK_LIST_TAB);
        String token = UserManager.getInstance().getToken();
        request.addHeader("X-CustomToken", token);
        request.setListener(new SimpleListener<LooktabListBean>() {
            @Override
            public void onResponseListener(Request r, LooktabListBean result) {
                boolean success = result.isSuccess();
                loadingDialog.dismiss();
                try {
                    if (success) {
                        List<LooktabListBean.AaDataBean> data = result.getAaData();
                        if (data != null) {
                            setTableList(data);
                        }
                    }
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }

            @Override
            public void onErrorListener(Request r, String error) {
                super.onErrorListener(r, error);
                loadingDialog.dismiss();
            }
        });
        net(request);

    }

    //设置标题选项卡
    private void setTableList(List<LooktabListBean.AaDataBean> dataBeanList){
        if(dataBeanList==null||dataBeanList.isEmpty()) return;
        List<Pair<String,Fragment>> pages=new ArrayList<>();
        pages.add(Pair.<String, Fragment>create("精选", ActiveListFragment.getInstance("")));
        pages.add(Pair.<String, Fragment>create("热门",ActiveListFragment.getInstance("-1")));
        for(LooktabListBean.AaDataBean bean:dataBeanList)
            pages.add(Pair.<String, Fragment>create(bean.getName(),ActiveListFragment.getInstance(bean.getId())));
        lookViewpage.setAdapter(new CommonFragmentViewPagerAdapter(getSupportFragmentManager(),pages));
        lookViewpage.setCurrentItem(1);
        lookTablayout.setupWithViewPager(lookViewpage);
    }

    private void setPubHead() {
        mTitleBar.setOnLeftClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        mTitleBar.setOnRightClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(FindActiveActivity.this, ActiveActivity.class)); //我的活动
            }
        });
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
    }
}
