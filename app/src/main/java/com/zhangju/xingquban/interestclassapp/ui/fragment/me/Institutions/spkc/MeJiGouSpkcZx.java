package com.zhangju.xingquban.interestclassapp.ui.fragment.me.Institutions.spkc;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.zhangju.xingquban.R;
import com.zhangju.xingquban.interestclassapp.RetrofitInterface.NetWork;
import com.zhangju.xingquban.interestclassapp.base.BaseFragment;
import com.zhangju.xingquban.interestclassapp.refactor.user.UserManager;
import com.zhangju.xingquban.interestclassapp.util.ToastUtil;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import rx.Observer;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class MeJiGouSpkcZx extends BaseFragment {
    public static final String TAG = "MeJiGouSpkcZx";

    @BindView(R.id.linlayout_video)
    LinearLayout linlayoutVideo;
    @BindView(R.id.me_jg_sokc_zxRecycler)
    RecyclerView meJgSokcZxRecycler;
    Unbinder unbinder;
    private String picVideo;

    Observer<MeJiGouSpkcSpjBean> observer1 = new Observer<MeJiGouSpkcSpjBean>() {

        @Override
        public void onCompleted() {

        }

        @Override
        public void onError(Throwable e) {
            Log.i(TAG, "onError: " + e);
        }

        @Override
        public void onNext(final MeJiGouSpkcSpjBean mejigouBean) {
            Log.i(TAG, "meUserBean: " + mejigouBean.toString());

            if (mejigouBean.getAaData().size() == 0) {
                linlayoutVideo.setVisibility(View.VISIBLE);
                meJgSokcZxRecycler.setVisibility(View.GONE);
            } else {
                meJgSokcZxRecycler.setVisibility(View.VISIBLE);
                linlayoutVideo.setVisibility(View.GONE);
            }

            if (mejigouBean.isSuccess()) {
                LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
                meJgSokcZxRecycler.setLayoutManager(linearLayoutManager);
                MeJiGouXcglSpkcAdapter meJiGouXcglSpAdapter = new MeJiGouXcglSpkcAdapter(getContext(), mejigouBean);
                meJgSokcZxRecycler.setAdapter(meJiGouXcglSpAdapter);
                meJiGouXcglSpAdapter.setOnItemClickListener(new MeJiGouXcglSpkcAdapter.OnItemClickListener() {
                    @Override
                    public void onItemClick(View view, int position) {
                        Intent intent = new Intent(getActivity(), LiveVideo.class);
                        picVideo = mejigouBean.getAaData().get(position).getPicVideo();
                        intent.putExtra("video", picVideo);
                        intent.putExtra("tag", position);
                        startActivity(intent);
                    }
                });
            } else {
                ToastUtil.showToast("请求失败");
            }

        }
    };

    @Override
    public void initData() {
        getGRMessage();
    }

    @Override
    public int getMyLayout() {
        return R.layout.activity_me_ji_gou_spkc_zx;
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

    public void getGRMessage() {
        Subscription suscription = NetWork.getMe().organAlbumls(UserManager.getInstance().getUser().id, 2, "2")
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
