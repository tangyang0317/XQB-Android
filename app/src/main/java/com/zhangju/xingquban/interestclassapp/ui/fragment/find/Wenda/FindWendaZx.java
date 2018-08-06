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
import com.zhangju.xingquban.interestclassapp.util.ToastUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import rx.Observer;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

import static com.nostra13.universalimageloader.core.ImageLoader.TAG;

//我的问答--最新
public class FindWendaZx extends BaseFragment {

    @BindView(R.id.find_wendaZx_recylerview)
    RecyclerView findWendaZxRecylerview;
    @BindView(R.id.findZx_pulltorefresh)
    PullToRefreshScrollView findZxPulltorefresh;
    Unbinder unbinder;

    private LinearLayoutManager linearLayoutManager;
    private boolean lood = true;
    private int PageNumber = 1;
    private int PageSize = 10;
    private List<FindWDBean.AaDataBean> wdBeanList = new ArrayList<>();
    private FindWDAdapter findWDAdapter;
    private Subscription subscription;
    private List<FindWDBean.AaDataBean> list = new ArrayList<>();

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
                    findWDAdapter = new FindWDAdapter(getContext(), findWDBean);
                    findWendaZxRecylerview.setAdapter(findWDAdapter);
                    findWDAdapter.setOnItemClickListener(new FindWDAdapter.OnItemClickListener() {
                        @Override
                        public void onItemClick(View view, int position) {
                            Intent intent = new Intent(getActivity(), FindTiWenXq.class);
                            Bundle bundle = new Bundle();
                            bundle.putSerializable("xq", findWDAdapter.getData().getAaData().get(position));
                            intent.putExtra("tag", position);
                            intent.putExtras(bundle);
                            startActivity(intent);

                        }
                    });
                    lood = false;
                } else {

                    findWDAdapter.addData(findWDBean);
                }
                findZxPulltorefresh.onRefreshComplete();


            } else {
                ToastUtil.showToast("网络加载错误!");
            }

        }
    };
    @Override
    public void onResume() {
        super.onResume();
        getPulltoRefish();
    }
    @Override
    public void initData() {
        getTiWenData();
        linearLayoutManager = new LinearLayoutManager(getActivity()) {
            @Override
            public boolean canScrollVertically() {
                return false;
            }
        };
        findWendaZxRecylerview.setLayoutManager(linearLayoutManager);
        getPulltoRefish();
    }

    //界面加载
    public void getPulltoRefish() {
        findZxPulltorefresh.setMode(PullToRefreshBase.Mode.BOTH);
        findZxPulltorefresh.autoRefresh();
        findZxPulltorefresh.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener2<ScrollView>() {
            @Override
            public void onPullDownToRefresh(PullToRefreshBase<ScrollView> refreshView) {
                PageNumber = 1;
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
        subscription = NetWork.getMeTiwen().myQuestionAll(1, 0 )
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer1);
    }

    @Override
    public int getMyLayout() {
        return R.layout.activity_find_wenda_zx;
    }

    @Override
    public void initView() {

    }

    @Override
    public void initListener() {

    }

    @Override
    public void onClick(View v) {

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
