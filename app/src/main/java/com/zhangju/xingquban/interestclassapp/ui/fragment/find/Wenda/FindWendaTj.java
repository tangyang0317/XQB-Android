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
//我的问答--推荐
public class FindWendaTj extends BaseFragment {
    public static final String TAG = "FindWendaTj";


    @BindView(R.id.find_wendaTj_recylerview)
    RecyclerView findWendaTjRecylerview;//列表
    @BindView(R.id.findTj_pulltorefresh)
    PullToRefreshScrollView findTjPulltorefresh;
    Unbinder unbinder;

    private LinearLayoutManager linearLayoutManager;
    private boolean lood = true;
    private int PageNumber = 0;
    private int PageSize = 10;
    private List<FindWDBean.AaDataBean> wdBeanList = new ArrayList<>();
    private FindWDAdapter findWDAdapter;
    private Subscription subscription;
    private List<FindWDBean.AaDataBean> list = new ArrayList<>();
    boolean isrefresh;

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
           isrefresh=false;
                if (lood) {
                    findWDAdapter = new FindWDAdapter(getContext(), findWDBean);
                    findWendaTjRecylerview.setAdapter(findWDAdapter);
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
                findTjPulltorefresh.onRefreshComplete();

            } else {
                ToastUtil.showToast("网络加载错误!");
            }

        }
    };



    @Override
    public void onResume() {
        super.onResume();
        if (!isrefresh){
            getPulltoRefish();
        }
    }


    @Override
    public void initData() {
        linearLayoutManager = new LinearLayoutManager(getActivity()) {
            @Override
            public boolean canScrollVertically() {
                return false;
            }
        };
        findWendaTjRecylerview.setLayoutManager(linearLayoutManager);
        getPulltoRefish();
    }

    @Override
    public int getMyLayout() {
        return R.layout.activity_find_wenda_tj;
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

    //界面加载
    public void getPulltoRefish() {
        findTjPulltorefresh.setMode(PullToRefreshBase.Mode.BOTH);
        findTjPulltorefresh.autoRefresh();
        findTjPulltorefresh.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener2<ScrollView>() {
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
        isrefresh=true;
        subscription = NetWork.getMeTiwen().myQuestionAll(1, 1 )
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
