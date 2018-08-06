package com.zhangju.xingquban.interestclassapp.ui.fragment.me.Message;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import com.zhangju.xingquban.R;
import com.zhangju.xingquban.interestclassapp.RetrofitInterface.NetWork;
import com.zhangju.xingquban.interestclassapp.base.BaseActivity;
import com.zhangju.xingquban.interestclassapp.ui.widget.PublicHead;
import com.zhangju.xingquban.interestclassapp.util.RecyclerItemDecoration.SpaceItemDecoration;
import com.zhangju.xingquban.interestclassapp.util.ToastUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import rx.Observer;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class MeMessageDay extends BaseActivity {
    public static final String TAG = "MeMessageDay";
    private List<MeXXBean> list = new ArrayList<>();
    private Context context = this;

    @BindView(R.id.me_message_day)
    PublicHead meMessageDay;
    @BindView(R.id.me_message_dayrecycler)
    RecyclerView meMessageDayrecycler;

    private LinearLayoutManager linearLayoutManager;
    private MeXXDayAdpter meXXDayAdpter;
    private Subscription suscription;

    Observer<MeXXBean> observer1 = new Observer<MeXXBean>() {

        @Override
        public void onCompleted() {

        }

        @Override
        public void onError(Throwable e) {
            Log.i(TAG, "onError: " + e);
        }

        @Override
        public void onNext(MeXXBean meXXBean) {
            Log.i(TAG, "meUserBean: " + meXXBean.toString());

            if (meXXBean.isSuccess()) {
                linearLayoutManager = new LinearLayoutManager(mContext);
                meMessageDayrecycler.setLayoutManager(linearLayoutManager);
                meXXDayAdpter = new MeXXDayAdpter(mContext, meXXBean);
                meMessageDayrecycler.setAdapter(meXXDayAdpter);
                meMessageDayrecycler.addItemDecoration(new SpaceItemDecoration(10));
            } else {
                ToastUtil.showToast("获取失败");
            }

        }
    };

    @Override
    public int getLayout() {
        return R.layout.activity_me_message_day;
    }

    @Override
    public void initView() {
        getMeWalletMessageDayHead();
        getGRMessage();
    }

    public void getGRMessage() {
        suscription = NetWork.getMe().getMeMessage()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer1);
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

    public void getMeWalletMessageDayHead() {
        meMessageDay.setTitle("每日动态");
        meMessageDay.setShow(true, false, false);
        meMessageDay.setBackClickListener(new View.OnClickListener() {
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
