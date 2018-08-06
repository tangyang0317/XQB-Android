package com.zhangju.xingquban.interestclassapp.ui.fragment.find.Wenda;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
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
import com.zhangju.xingquban.interestclassapp.ui.fragment.find.Wenda.wdcgtiwen.FindWdCgTWAdapter;
import com.zhangju.xingquban.interestclassapp.util.ToastUtil;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import rx.Observer;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

//问答草稿列表
public class FindWDCgFragmentWenda extends BaseFragment {
    @BindView(R.id.findCg_wenda_recycler)
    RecyclerView findCgWendaRecycler;
    @BindView(R.id.findCg_pulltorefresh)
    PullToRefreshScrollView findCgPulltorefresh;

    //    private List<FindTWBean> list = new ArrayList<>();
    private FindTWAdapter findTWAdapter;
    private LinearLayoutManager linearLayoutManager;
    Unbinder unbinder;

    private FindWdCgTWAdapter findWdCgTWAdapter;
    private Subscription subscription;
    private boolean lood = true;
    private int PageNumber = 0;
    private int PageSize = 10;
    Observer<FindWDCgTiWenBean> observer1 = new Observer<FindWDCgTiWenBean>() {

        @Override
        public void onCompleted() {

        }

        @Override
        public void onError(Throwable e) {
            ToastUtil.showToast(e.toString());
        }

        @Override
        public void onNext(final FindWDCgTiWenBean findWDCgTiWenBean) {
            String s = findWDCgTiWenBean.getAaData().toString();

            if (findWDCgTiWenBean.isSuccess()) {

                if (lood) {
                    linearLayoutManager = new LinearLayoutManager(getActivity());
                    findCgWendaRecycler.setLayoutManager(linearLayoutManager);
                    findWdCgTWAdapter = new FindWdCgTWAdapter(getContext(), findWDCgTiWenBean);
                    findCgWendaRecycler.setAdapter(findWdCgTWAdapter);
                    findWdCgTWAdapter.setOnItemClickListener(new FindWdCgTWAdapter.OnItemClickListener() {
                        @Override
                        public void onItemClick(View view, int position) {
                            String title = findWDCgTiWenBean.getAaData().get(position).getTitle() == null ? "" : findWDCgTiWenBean.getAaData().get(position).getTitle();
                            Object explain = findWDCgTiWenBean.getAaData().get(position).getQExplain() == null ? "" : findWDCgTiWenBean.getAaData().get(position).getQExplain();

                            ArrayList<String> picStr = (ArrayList<String>) findWDCgTiWenBean.getAaData().get(position).getPicStr();

                            Intent intent = new Intent(getActivity(), FindCGAnwserDetailActivity.class);
                            intent.putExtra("id", findWDCgTiWenBean.getAaData().get(position).getId());
                            intent.putExtra("title", title);
                            intent.putExtra("explain", explain.toString());
                            intent.putStringArrayListExtra("picList", picStr);
                            startActivity(intent);
                        }
                    });
                    lood = false;
                } else {
                    findWdCgTWAdapter.addData(findWDCgTiWenBean);
                }
                findCgPulltorefresh.onRefreshComplete();

            } else {
                ToastUtil.showToast("网络加载错误!");
            }
            findCgPulltorefresh.onRefreshComplete();

        }
    };

    @Override
    public void initData() {

    }

    @Override
    public int getMyLayout() {
        return R.layout.activity_find_wdcg_fragment_wenda;
    }

    @Override
    public void initView() {
        getPulltoRefish();

    }

    @Override
    public void onResume() {
        super.onResume();
        getPulltoRefish();
    }

    @Override
    public void initListener() {

    }

    @Override
    public void onClick(View v) {

    }

    //界面加载
    public void getPulltoRefish() {
        findCgPulltorefresh.setMode(PullToRefreshBase.Mode.BOTH);
        findCgPulltorefresh.autoRefresh();
        findCgPulltorefresh.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener2<ScrollView>() {
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
        subscription = NetWork.getMeTiwen().myQuestionWdWd(UserManager.getInstance().getUser().id, 2, PageNumber, PageSize + "")
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
