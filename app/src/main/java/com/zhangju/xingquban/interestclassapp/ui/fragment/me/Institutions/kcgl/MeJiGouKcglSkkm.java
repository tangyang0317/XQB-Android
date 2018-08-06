package com.zhangju.xingquban.interestclassapp.ui.fragment.me.Institutions.kcgl;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import com.zhangju.xingquban.R;
import com.zhangju.xingquban.interestclassapp.RetrofitInterface.NetWork;
import com.zhangju.xingquban.interestclassapp.base.BaseActivity;
import com.zhangju.xingquban.interestclassapp.bean.HomeViewPage;
import com.zhangju.xingquban.interestclassapp.ui.widget.PublicHead;

import butterknife.BindView;
import butterknife.ButterKnife;
import rx.Observer;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class MeJiGouKcglSkkm extends BaseActivity {
    public static final String TAG = "MeJiGouKcglSkkm";

    @BindView(R.id.MeJiGouKcglSkkm_Head)
    PublicHead MeJiGouKcglSkkmHead;
    @BindView(R.id.MeJiGouKcglSkkm_Recycler)
    RecyclerView MeJiGouKcglSkkmRecycler;
    private MeJiGouKcglSkkmAdapter mejigouAdapter;

    private Subscription suscription;
    Observer<HomeViewPage> observer = new Observer<HomeViewPage>() {
        @Override
        public void onCompleted() {
        }

        @Override
        public void onError(Throwable e) {

        }

        @Override
        public void onNext(HomeViewPage homeViewPage) {
            Log.i(TAG, "onNext: " + homeViewPage.toString());

            if (homeViewPage.isSuccess()) {
                LinearLayoutManager linlayout = new LinearLayoutManager(mContext);
                MeJiGouKcglSkkmRecycler.setLayoutManager(linlayout);
                mejigouAdapter = new MeJiGouKcglSkkmAdapter(mContext, homeViewPage);
                MeJiGouKcglSkkmRecycler.setAdapter(mejigouAdapter);
            } else {

            }

        }
    };

    @Override
    public int getLayout() {
        return R.layout.activity_me_ji_gou_kcgl_skkm;
    }

    @Override
    public void initView() {
        setMeJiGouKcglSkkmHead();
        getTiWenData();
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

    public void getTiWenData() {
        suscription = NetWork.getHomeViewPage().getHomeViewPage()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);
    }

    public void setMeJiGouKcglSkkmHead() {
        MeJiGouKcglSkkmHead.setTitle("科目选择");
        MeJiGouKcglSkkmHead.setShow(true, false, false);
        MeJiGouKcglSkkmHead.setBackClickListener(new View.OnClickListener() {
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
