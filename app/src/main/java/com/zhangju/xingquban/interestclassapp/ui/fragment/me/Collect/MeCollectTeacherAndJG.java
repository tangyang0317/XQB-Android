package com.zhangju.xingquban.interestclassapp.ui.fragment.me.Collect;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.zhangju.xingquban.R;
import com.zhangju.xingquban.interestclassapp.RetrofitInterface.NetWork;
import com.zhangju.xingquban.interestclassapp.base.BaseFragment;
import com.zhangju.xingquban.interestclassapp.refactor.user.UserManager;
import com.zhangju.xingquban.interestclassapp.util.RecyclerItemDecoration.SpaceItemDecoration;
import com.zhangju.xingquban.interestclassapp.util.ToastUtil;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import rx.Observer;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class MeCollectTeacherAndJG extends BaseFragment {
    private MeCollectAllAdapter meCollectAllAdapter;

    @BindView(R.id.me_teacher_recycler)
    RecyclerView meTeacherRecycler;
    Unbinder unbinder;

    private Subscription suscription;
    private String teacherTimeId;

    Observer<MeCollectAllBean> observer1 = new Observer<MeCollectAllBean>() {

        @Override
        public void onCompleted() {

        }

        @Override
        public void onError(Throwable e) {
            ToastUtil.showToast(e.toString());
        }

        @Override
        public void onNext(final MeCollectAllBean meCollectAllBean) {

            if (meCollectAllBean.isSuccess()) {
                LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
                meTeacherRecycler.setLayoutManager(linearLayoutManager);
                meCollectAllAdapter = new MeCollectAllAdapter(getContext(), meCollectAllBean);
                meTeacherRecycler.setAdapter(meCollectAllAdapter);
                meTeacherRecycler.addItemDecoration(new SpaceItemDecoration(10));
                meCollectAllAdapter.setOnItemClickListener(new MeCollectAllAdapter.OnItemClickListener() {
                    @Override
                    public void onItemClick(View view, int position) {
//                        Intent intent = new Intent(getActivity(), HomeRecyclerViewData.class);
//                        Bundle bundle = new Bundle();
//                        bundle.putSerializable("homeXq", meCollectAllBean.getAaData().get(position));
//                        intent.putExtra("tag", position);
//                        intent.putExtras(bundle);
//                        startActivity(intent);
                    }
                });

            } else {
                ToastUtil.showToast("获取失败");
            }

        }
    };

    public void getGRMessage() {
        suscription = NetWork.getMe().activityCollection(UserManager.getInstance().getUser().id, "120.039457", "30.289106", "3")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer1);
    }

    @Override
    public void initView() {
        getGRMessage();
    }

    @Override
    public void initData() {

    }

    @Override
    public int getMyLayout() {
        return R.layout.activity_me_collect_teacher_and_jg;
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
