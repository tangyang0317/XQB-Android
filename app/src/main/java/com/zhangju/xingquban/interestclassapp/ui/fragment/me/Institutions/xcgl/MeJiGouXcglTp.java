package com.zhangju.xingquban.interestclassapp.ui.fragment.me.Institutions.xcgl;

import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.LinearLayout;

import com.zhangju.xingquban.R;
import com.zhangju.xingquban.interestclassapp.RetrofitInterface.NetWork;
import com.zhangju.xingquban.interestclassapp.base.BaseFragment;
import com.zhangju.xingquban.interestclassapp.refactor.user.UserManager;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import rx.Observer;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class MeJiGouXcglTp extends BaseFragment {
    public static final String TAG = "MeJiGouXcglTp";

    @BindView(R.id.me_jg_xcgl_photo)
    GridView meJgXcglPhoto;
    Unbinder unbinder;
    @BindView(R.id.me_jg_xcgl_Upphoto)
    LinearLayout meJgXcglUpphoto;
    @BindView(R.id.me_jg_xcgl_Recycler)
    RecyclerView meJgXcglRecycler;
    private GridViewImageView gridViewImageView;

    Observer<MeJiGouXcglTpBean> observer1 = new Observer<MeJiGouXcglTpBean>() {

        @Override
        public void onCompleted() {

        }

        @Override
        public void onError(Throwable e) {
            Log.i(TAG, "onError: " + e);
        }

        @Override
        public void onNext(final MeJiGouXcglTpBean mejigouBean) {
            Log.i(TAG, "meUserBean: " + mejigouBean.toString());

            if (mejigouBean.isSuccess()) {
                meJgXcglRecycler.setLayoutManager(new GridLayoutManager(getContext(), 4));
                MeJiGouXcglTpAdapter mejigouxcgltpAdapter = new MeJiGouXcglTpAdapter(getContext(), mejigouBean);
                meJgXcglRecycler.setAdapter(mejigouxcgltpAdapter);
                mejigouxcgltpAdapter.setOnItemClickListener(new MeJiGouXcglTpAdapter.OnItemClickListener() {
                    @Override
                    public void onItemClick(View view, int position) {

                    }
                });
            } else {

            }
        }
    };
    private Subscription suscription;

    @Override
    public void initData() {

    }

    @Override
    public int getMyLayout() {
        return R.layout.activity_me_ji_gou_xcgl_tp;
    }

    @Override
    public void initView() {
        getGRMessage();
    }

    public void setDelectFlag(boolean b) {
        gridViewImageView.canDelete(b);
    }

    @Override
    public void initListener() {

    }

    @OnClick({R.id.me_jg_xcgl_Upphoto})
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.me_jg_xcgl_Upphoto:
//                gridViewImageView.deleteSelectImage();
                break;
        }
    }

    public void getGRMessage() {
        suscription = NetWork.getMe().organAlbumlsPhoto(UserManager.getInstance().getUser().id, 0, "1")
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
