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
import com.zhangju.xingquban.interestclassapp.adapter.baseadpter.OnListItemClickListener;
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

import static com.amap.api.col.sl3.dj.s;


//我的收藏列表
public class
FindMeShoucang extends BaseActivity {
    public static final String TAG = "FindMeShoucang";

    @BindView(R.id.find_wdSc_Head)
    PublicHead findWdScHead;
    @BindView(R.id.find_tiwenSc_recycler)
    RecyclerView findTiwenScRecycler;
    @BindView(R.id.findSc_pulltorefresh)
    PullToRefreshScrollView findScPulltorefresh;

    private boolean lood = true;
    private int PageNumber = 0;
    private int PageSize = 10;
    private List<FindWDBean.AaDataBean> list = new ArrayList<>();
    private CollectionAdapter findWdScAdapter;
    private LinearLayoutManager linearLayoutManager;

    private Subscription subscription;
    List<FindSCBean.AaDataBean> aaDataBeanList = new ArrayList<>();
    Observer<FindSCBean> observer1 = new Observer<FindSCBean>() {


        @Override
        public void onCompleted() {

        }

        @Override
        public void onError(Throwable e) {
            Log.i(TAG, "onError: " + e);
            ToastUtil.showToast(e.toString());
        }

        @Override
        public void onNext(final FindSCBean findwdSc) {
//            String s = findwdSc.getAaData().toString();
            Log.i(TAG, "onNext: " + s);

//
            if (findwdSc.isSuccess()) {

                if (lood) {
                    aaDataBeanList.clear();
                    aaDataBeanList.addAll(findwdSc.getAaData());
                    lood = false;
                } else {
                    findWdScAdapter.addData(findwdSc);
                }
                findWdScAdapter.notifyDataSetChanged();
                findScPulltorefresh.onRefreshComplete();
            } else {
                ToastUtil.showToast("网络加载错误!");
            }

        }
    };


    @Override
    public int getLayout() {
        return R.layout.activity_find_me_shoucang;
    }

    @Override
    public void initView() {
        setFindWdScHead();
        getPulltoRefish();

        linearLayoutManager = new LinearLayoutManager(mContext);
        findTiwenScRecycler.setLayoutManager(linearLayoutManager);
        findWdScAdapter = new CollectionAdapter(mContext, aaDataBeanList);
        findTiwenScRecycler.setAdapter(findWdScAdapter);
        findWdScAdapter.setOnListItemClickListener(new OnListItemClickListener() {
            @Override
            public void onItemClickListener(int position, View v) {
                FindSCBean.AaDataBean aaDataBean = aaDataBeanList.get(position);
                Intent intent = new Intent(FindMeShoucang.this, FindMeSCXq.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("data", aaDataBean);
                intent.putExtras(bundle);
                startActivity(intent);
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

    public void getTiWenData() {
        subscription = NetWork.getMeTiwen().questionCollectionls(UserManager.getInstance().getUser().id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer1);
    }

    //界面加载
    public void getPulltoRefish() {
        findScPulltorefresh.setMode(PullToRefreshBase.Mode.BOTH);
        findScPulltorefresh.autoRefresh();
        findScPulltorefresh.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener2<ScrollView>() {
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

    public void setFindWdScHead() {
        findWdScHead.setTitle("我的收藏");
        findWdScHead.setShow(true, false, false);
        findWdScHead.setBackClickListener(new View.OnClickListener() {
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
