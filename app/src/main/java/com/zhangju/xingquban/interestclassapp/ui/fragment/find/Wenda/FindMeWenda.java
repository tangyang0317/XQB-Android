package com.zhangju.xingquban.interestclassapp.ui.fragment.find.Wenda;

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
import com.zhangju.xingquban.interestclassapp.refactor.user.UserManager;
import com.zhangju.xingquban.interestclassapp.ui.widget.PublicHead;
import com.zhangju.xingquban.interestclassapp.util.ToastUtil;

import butterknife.BindView;
import butterknife.ButterKnife;
import rx.Observer;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
//我的回答列表
public class FindMeWenda extends BaseActivity {
    public static final String TAG = "FindMeWenda";

    @BindView(R.id.fing_wdhd_head)
    PublicHead fingWdhdHead;
    @BindView(R.id.find_tiwenHd_recycler)
    RecyclerView findTiwenHdRecycler;
    @BindView(R.id.findHd_pulltorefresh)
    PullToRefreshScrollView findHdPulltorefresh;
    private boolean lood = true;
    private int PageNumber = 0;
    private int PageSize = 10;

    private Subscription subscription;
    private FindWdHdAdapter findWDHdadapter;
    Observer<FindMeWendaBean> observer1 = new Observer<FindMeWendaBean>() {

        @Override
        public void onCompleted() {

        }

        @Override
        public void onError(Throwable e) {
            Log.i(TAG, "onError: " + e);
            ToastUtil.showToast(e.toString());
        }

        @Override
        public void onNext(final FindMeWendaBean findHdBean) {
            String s = findHdBean.getAaData().toString();
            Log.i(TAG, "onNext: " + s);
            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(mContext) {
                @Override
                public boolean canScrollVertically() {
                    return false;
                }
            };
            if (findHdBean.isSuccess()) {

                if (lood) {

                    findTiwenHdRecycler.setLayoutManager(linearLayoutManager);
                    findWDHdadapter = new FindWdHdAdapter(mContext, findHdBean);
                    findTiwenHdRecycler.setAdapter(findWDHdadapter);
                    findWDHdadapter.setOnItemClickListener(new FindWdHdAdapter.OnItemClickListener() {
                        @Override
                        public void onItemClick(View view, int position) {
                            Intent intent = new Intent(FindMeWenda.this, FindMeWendaXq.class);
                            String id = findHdBean.getAaData().get(position).getQuestionId();
                            intent.putExtra("id", id);
                            intent.putExtra("tag", position);
                            startActivity(intent);
                        }
                    });
                    lood = false;
                } else {
                    findWDHdadapter.addData(findHdBean);
                }
                findHdPulltorefresh.onRefreshComplete();
            } else {
                ToastUtil.showToast("网络加载错误!");
            }

        }
    };

    @Override
    public int getLayout() {
        return R.layout.activity_find_me_wenda;
    }

    public void getTiWenData() {
        subscription = NetWork.getMeTiwen().myAnswerHd(UserManager.getInstance().getUser().id, 1, PageNumber, PageSize + "")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer1);
    }

    //界面加载
    public void getPulltoRefish() {
        findHdPulltorefresh.setMode(PullToRefreshBase.Mode.BOTH);
        findHdPulltorefresh.autoRefresh();
        findHdPulltorefresh.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener2<ScrollView>() {
            @Override
            public void onPullDownToRefresh(PullToRefreshBase<ScrollView> refreshView) {
                PageNumber = 0;
                lood = true;
                getTiWenData();
            }

            @Override
            public void onPullUpToRefresh(PullToRefreshBase<ScrollView> refreshView) {
                PageNumber++;
                getTiWenData();
            }
        });
    }

    @Override
    public void initView() {
        setHead();
        getPulltoRefish();
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

    public void setHead() {
        fingWdhdHead.setTitle("我的回答");
        fingWdhdHead.setShow(true, false, false);
        fingWdhdHead.setBackClickListener(new View.OnClickListener() {
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
