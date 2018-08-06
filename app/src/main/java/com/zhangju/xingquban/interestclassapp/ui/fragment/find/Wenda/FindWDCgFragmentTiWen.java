package com.zhangju.xingquban.interestclassapp.ui.fragment.find.Wenda;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ScrollView;

import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshScrollView;
import com.zhangju.xingquban.R;
import com.zhangju.xingquban.interestclassapp.RetrofitInterface.NetWork;
import com.zhangju.xingquban.interestclassapp.base.BaseFragment;
import com.zhangju.xingquban.interestclassapp.refactor.user.UserManager;
import com.zhangju.xingquban.interestclassapp.ui.fragment.find.Wenda.wdcgtiwen.FindWdCgWDAdapter;
import com.zhangju.xingquban.interestclassapp.util.ToastUtil;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import rx.Observer;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
//提问草稿列表
public class FindWDCgFragmentTiWen extends BaseFragment {
    public static final String TAG = "FindWDCgFragmentTiWen";
    @BindView(R.id.findWdCg_pulltorefresh)
    PullToRefreshScrollView findWdCgPulltorefresh;

    private FindWdCgWDAdapter findWdCgWDAdapter;
    private LinearLayoutManager linearLayoutManager;
    @BindView(R.id.findwdcg_tiwen_recycler)
    RecyclerView findwdcgTiwenRecycler;
    Unbinder unbinder;
    private Subscription subscription;
    private boolean lood = true;
    private int PageNumber = 0;
    private int PageSize = 10;

    Observer<FindWDCgWendaBean> observer1 = new Observer<FindWDCgWendaBean>() {

        @Override
        public void onCompleted() {

        }

        @Override
        public void onError(Throwable e) {
            Log.i(TAG, "onError: " + e);
            ToastUtil.showToast(e.toString());
        }

        @Override
        public void onNext(final FindWDCgWendaBean findWDCgWendaBean) {
            String s = findWDCgWendaBean.getAaData().toString();
            Log.i(TAG, "onNext: " + s);

            if (findWDCgWendaBean.isSuccess()) {

                if (lood) {
                    linearLayoutManager = new LinearLayoutManager(getActivity());
                    findwdcgTiwenRecycler.setLayoutManager(linearLayoutManager);
                    findWdCgWDAdapter = new FindWdCgWDAdapter(getContext(), findWDCgWendaBean);
                    findwdcgTiwenRecycler.setAdapter(findWdCgWDAdapter);
                    findWdCgWDAdapter.setOnItemClickListener(new FindWdCgWDAdapter.OnItemClickListener() {
                        @Override
                        public void onItemClick(View view, int position) {
                            Intent intent = new Intent(getActivity(), FindCGDetail.class);
                            intent.putExtra("id", findWDCgWendaBean.getAaData().get(position).getId());
                            startActivity(intent);
                        }
                    });
                    lood = false;
                } else {
                    findWdCgWDAdapter.addData(findWDCgWendaBean);
                }
                findWdCgPulltorefresh.onRefreshComplete();

            } else {
                ToastUtil.showToast("网络加载错误!");
            }

        }
    };

    @Override
    public void initData() {

    }

    @Override
    public int getMyLayout() {
        return R.layout.activity_find_wdcg_fragment_ti_wen;
    }

    @Override
    public void initView() {
        getTiWenData();
        getPulltoRefish();
    }

    @Override
    public void onResume() {
        super.onResume();
        PageNumber = 0;
        lood = true;
        getTiWenData();
    }

    @Override
    public void initListener() {

    }

    @Override
    public void onClick(View v) {

    }

    //界面加载
    public void getPulltoRefish() {
        findWdCgPulltorefresh.setMode(PullToRefreshBase.Mode.BOTH);
        findWdCgPulltorefresh.autoRefresh();
        findWdCgPulltorefresh.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener2<ScrollView>() {
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

    public void getTiWenData() {
        subscription = NetWork.getMeTiwen().myQuestionWdCg(UserManager.getInstance().getUser().id, 2, PageNumber, PageSize + "")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer1);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        unbinder = ButterKnife.bind(this, rootView);
        return rootView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
