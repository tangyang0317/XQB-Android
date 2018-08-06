package com.zhangju.xingquban.interestclassapp.ui.fragment.me.Institutions.kcgl;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ScrollView;

import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshScrollView;
import com.zhangju.xingquban.R;
import com.zhangju.xingquban.interestclassapp.RetrofitInterface.NetWork;
import com.zhangju.xingquban.interestclassapp.base.BaseActivity;
import com.zhangju.xingquban.interestclassapp.ui.widget.PublicHead;
import com.zhangju.xingquban.interestclassapp.util.ToastUtil;

import butterknife.BindView;
import butterknife.ButterKnife;
import rx.Observer;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class MeJiGouKcglKctjCity2 extends BaseActivity {
    public static final String TAG = "MeJiGouKcglKctjCity2";
    @BindView(R.id.MeJiGouKcglKctjCity_Head)
    PublicHead MeJiGouKcglKctjCityHead;
    @BindView(R.id.KcglKctjCity_recyclerView)
    RecyclerView KcglKctjCityRecyclerView;
    @BindView(R.id.KcglKctjCity_pulltorefresh)
    PullToRefreshScrollView KcglKctjCityPulltorefresh;

    private Subscription subscription;
    private boolean lood = true;
    private int PageNumber = 0;
    private int PageSize = 20;

    private MeJiGouKcglKctjCityAdapter mejgkcglAdapter;
    Observer<MeJiGouKcglKctjCityBean> observer1 = new Observer<MeJiGouKcglKctjCityBean>() {

        @Override
        public void onCompleted() {

        }

        @Override
        public void onError(Throwable e) {
            Log.i(TAG, "onError: " + e);
        }

        @Override
        public void onNext(final MeJiGouKcglKctjCityBean meJiGouKcglKctjCityBean) {

            if (meJiGouKcglKctjCityBean.isSuccess()) {

                if (lood) {
                    LinearLayoutManager linearLayoutManager = new LinearLayoutManager(mContext);
                    KcglKctjCityRecyclerView.setLayoutManager(linearLayoutManager);
                    mejgkcglAdapter = new MeJiGouKcglKctjCityAdapter(mContext, meJiGouKcglKctjCityBean);
                    KcglKctjCityRecyclerView.setAdapter(mejgkcglAdapter);
                    mejgkcglAdapter.setOnItemClickListener(new MeJiGouKcglKctjCityAdapter.OnItemClickListener() {
                        @Override
                        public void onItemClick(View view, int position) {
                            
                        }
                    });
                    lood = false;
                } else {
                    mejgkcglAdapter.addData(meJiGouKcglKctjCityBean);
                }
                KcglKctjCityPulltorefresh.onRefreshComplete();
            } else {
                ToastUtil.showToast("网络加载错误!");
            }

        }
    };

    @Override
    public int getLayout() {
        return R.layout.activity_me_ji_gou_kcgl_kctj_city2;
    }

    @Override
    public void initView() {
        setMeJiGouKcglKctjCityHead();
        getPulltoRefish();
    }

    public void getCity() {
        Intent intent = getIntent();
        String pid = intent.getStringExtra("pid");
        subscription = NetWork.getMe().city2(pid, PageNumber, PageSize + "")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer1);
    }

    //界面加载
    public void getPulltoRefish() {
        KcglKctjCityPulltorefresh.setMode(PullToRefreshBase.Mode.BOTH);
        KcglKctjCityPulltorefresh.autoRefresh();
        KcglKctjCityPulltorefresh.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener2<ScrollView>() {
            @Override
            public void onPullDownToRefresh(PullToRefreshBase<ScrollView> refreshView) {
                PageNumber = 0;
                lood = true;
                getCity();
            }

            @Override
            public void onPullUpToRefresh(PullToRefreshBase<ScrollView> refreshView) {
                PageNumber++;
                getCity();
            }
        });
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

    public void setMeJiGouKcglKctjCityHead() {
        MeJiGouKcglKctjCityHead.setTitle("选择城市");
        MeJiGouKcglKctjCityHead.setShow(true, false, false);
        MeJiGouKcglKctjCityHead.setBackClickListener(new View.OnClickListener() {
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
