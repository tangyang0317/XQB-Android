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

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import rx.Observer;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class FindTiWen extends BaseActivity {
    public static final String TAG = "FindTiWen";
    @BindView(R.id.find_wodetiwen)
    PublicHead findWodetiwen;
    @BindView(R.id.find_tiwen_recycler)
    RecyclerView findTiwenRecycler;
    @BindView(R.id.findTW_pulltorefresh)
    PullToRefreshScrollView findTWPulltorefresh;

    private boolean lood = true;
    private int PageNumber = 0;
    private int PageSize = 10;
    private List<FindWDBean.AaDataBean> list = new ArrayList<>();
    private FindTWAdapter findTWAdapter;
    private LinearLayoutManager linearLayoutManager;

    private Subscription subscription;
    Observer<FindWDBean> observer1 = new Observer<FindWDBean>() {

        @Override
        public void onCompleted() {

        }

        @Override
        public void onError(Throwable e) {
            Log.i(TAG, "onError: " + e);
            ToastUtil.showToast(e.toString());
        }

        @Override
        public void onNext(final FindWDBean findWDBean) {
            String s = findWDBean.getAaData().toString();
            Log.i(TAG, "onNext: " + s);

            if (findWDBean.isSuccess()) {

                if (lood) {
                    linearLayoutManager = new LinearLayoutManager(mContext);
                    findTiwenRecycler.setLayoutManager(linearLayoutManager);
                    findTWAdapter = new FindTWAdapter(mContext, findWDBean);
                    findTiwenRecycler.setAdapter(findTWAdapter);
                    findTWAdapter.setOnItemClickListener(new FindTWAdapter.OnItemClickListener() {
                        @Override
                        public void onItemClick(View view, int position) {
                            Intent intent = new Intent(FindTiWen.this, FindTiWenXq.class);
                            Bundle bundle = new Bundle();
                            bundle.putSerializable("xq", findWDBean.getAaData().get(position));
                            intent.putExtra("tag", position);
                            intent.putExtras(bundle);
                            startActivity(intent);
                        }
                    });
                    lood = false;
                } else {
                    findTWAdapter.addData(findWDBean.getAaData());
                }
                findTWPulltorefresh.onRefreshComplete();

            } else {
                ToastUtil.showToast("网络加载错误!");
            }

        }
    };

    @Override
    public int getLayout() {
        return R.layout.activity_find_ti_wen;
    }

    public void setFindHead() {
        findWodetiwen.setTitle("我的提问");
        findWodetiwen.setShow(true, false, false);
        findWodetiwen.setBackClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    @Override
    public void initView() {
        setFindHead();
        getPulltoRefish();
        getTiWenData();
    }

    public void getTiWenData() {
        subscription = NetWork.getMeTiwen().myQuestion(UserManager.getInstance().getUser().id, 1, false, PageNumber, PageSize + "")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer1);
    }

    @Override
    public void initData() {

    }

    //界面加载
    public void getPulltoRefish() {
        findTWPulltorefresh.setMode(PullToRefreshBase.Mode.BOTH);
        findTWPulltorefresh.autoRefresh();
        findTWPulltorefresh.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener2<ScrollView>() {
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
    public void initListener() {

    }

    @Override
    public void onClick(View v) {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
    }
}
