package com.zhangju.xingquban.interestclassapp.ui.fragment.me.Institutions.spkc;

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
import com.zhangju.xingquban.interestclassapp.ui.fragment.me.Institutions.xcgl.MeJiGouXcglScspSpjBean;
import com.zhangju.xingquban.interestclassapp.util.ToastUtil;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import rx.Observer;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class MeJiGouSpkcSpj extends BaseFragment {
    public static final String TAG = "MeJiGouSpkcSpj";

    @BindView(R.id.me_jg_wdsp_spj)
    GridView meJgWdspSpj;
    @BindView(R.id.me_jg_xcgl_shangchuansp)
    LinearLayout meJgXcglShangchuansp;
    Unbinder unbinder;
    @BindView(R.id.me_jg_wdsp_spjRecycler)
    RecyclerView meJgWdspSpjRecycler;
    private GridImageAdapter canDelete;
    private Subscription suscription;
    private String picVideo;

    Observer<MeJiGouXcglScspSpjBean> observer1 = new Observer<MeJiGouXcglScspSpjBean>() {

        @Override
        public void onCompleted() {

        }

        @Override
        public void onError(Throwable e) {
            Log.i(TAG, "onError: " + e);
        }

        @Override
        public void onNext(MeJiGouXcglScspSpjBean meJiGouXcglScspSpjBean) {
            Log.i(TAG, "meUserBean: " + meJiGouXcglScspSpjBean.toString());

            if (meJiGouXcglScspSpjBean.isSuccess()) {
                meJgWdspSpjRecycler.setLayoutManager(new GridLayoutManager(getContext(), 2));
                MeJiGouXcglSpjAdapter2 meJiGouXcglSpjAdapter2 = new MeJiGouXcglSpjAdapter2(getContext(), meJiGouXcglScspSpjBean);
                meJgWdspSpjRecycler.setAdapter(meJiGouXcglSpjAdapter2);
            } else {
                ToastUtil.showToast("请求失败");
            }

//            List<String> list = new ArrayList<>();
//            picVideo = mejigouBean.getAaData().get(0).getPicVideo();
//            for (MeJiGouSpkcSpjBean.AaDataBean bean : mejigouBean.getAaData()) {
//                list.add(bean.getVideoTitlePic());
//            }
//            canDelete.addDataPhoto(list);
        }
    };

    @Override
    public void initData() {

    }

    @Override
    public int getMyLayout() {
        return R.layout.activity_me_ji_gou_spkc_spj;
    }

    @Override
    public void initView() {
        getGRMessage();
//        meJgWdspSpj.setAdapter(canDelete = new GridImageAdapter(mContext));
//        meJgWdspSpj.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                Intent intent = new Intent(getActivity(), LiveVideo.class);
//                intent.putExtra("video", picVideo);
//                startActivity(intent);
//            }
//        });
    }

    @Override
    public void initListener() {

    }

    @OnClick({R.id.me_jg_xcgl_shangchuansp})
    public void onClick(View v) {
        switch (v.getId()) {

            case R.id.me_jg_xcgl_shangchuansp:

                break;
        }
    }

    public void getGRMessage() {
        suscription = NetWork.getMe().videoFiles(UserManager.getInstance().getUser().id)
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
