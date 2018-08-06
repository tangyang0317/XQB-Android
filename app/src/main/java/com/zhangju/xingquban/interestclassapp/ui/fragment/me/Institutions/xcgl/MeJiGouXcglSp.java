package com.zhangju.xingquban.interestclassapp.ui.fragment.me.Institutions.xcgl;

import android.content.Intent;
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
import com.zhangju.xingquban.interestclassapp.ui.fragment.me.Institutions.spkc.GridImageAdapter;
import com.zhangju.xingquban.interestclassapp.ui.fragment.me.Institutions.spkc.LiveVideo;
import com.zhangju.xingquban.interestclassapp.ui.fragment.me.Institutions.spkc.MeJiGouSpkcSpjBean;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import rx.Observer;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class MeJiGouXcglSp extends BaseFragment {
    public static final String TAG = "MeJiGouXcglSp";

    @BindView(R.id.me_jg_xcgl)
    GridView meJgXcgl;
    @BindView(R.id.me_jg_xcgl_shangchuansp)
    LinearLayout meJgXcglShangchuansp;
    Unbinder unbinder;
    @BindView(R.id.me_jg_xcgl_Recycler)
    RecyclerView meJgXcglRecycler;
    private GridViewImageView gridViewImageView;

    private GridImageAdapter canDelete;
    private Subscription suscription;
    private String picVideo;
    private List<MeJiGouSpkcSpjBean> list = new ArrayList<>();
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

            if (mejigouBean.isSuccess()) {
                meJgXcglRecycler.setLayoutManager(new GridLayoutManager(getContext(), 4));
                MeJiGouXcglSpAdapter meJiGouXcglSpAdapter = new MeJiGouXcglSpAdapter(getContext(), mejigouBean);
                meJgXcglRecycler.setAdapter(meJiGouXcglSpAdapter);
                meJiGouXcglSpAdapter.setOnItemClickListener(new MeJiGouXcglSpAdapter.OnItemClickListener() {
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

            }

//            list.add(mejigouBean);
//            gridViewImageView = new GridViewImageView(mContext, list, GridViewImageView.ImageType.VIDEO);
//            meJgXcgl.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//                @Override
//                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                    Intent intent = new Intent(getActivity(), LiveVideo.class);
//                    intent.putExtra("video", picVideo);
//                    startActivity(intent);
//                }
//            });
//            for (MeJiGouSpkcSpjBean.AaDataBean bean : mejigouBean.getAaData()) {
//                list.add(bean.getVideoTitlePic());
//            }
//            canDelete.addDataPhoto(list);
        }
    };

    @Override
    public void initView() {
        getGRMessage();
//        gridViewImageView = new GridViewImageView(mContext, GridViewImageView.ImageType.VIDEO);
//        meJgXcgl.setAdapter(gridViewImageView);
//        List<String> list = new ArrayList<>();
//        for (int i = 0; i < 15; i++) {
//            list.add("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1504893398569&di=0927ad4c7d2205da8bf13a573e020e4e&imgtype=0&src=http%3A%2F%2Fpic7.nipic.com%2F20100514%2F2158700_153225558098_2.jpg");
//        }
//        gridViewImageView.setData(list);
//        meJgXcgl.setAdapter(canDelete = new GridImageAdapter(mContext));
//        meJgXcgl.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                Intent intent = new Intent(getActivity(), LiveVideo.class);
//                intent.putExtra("video", picVideo);
//                startActivity(intent);
//            }
//        });
    }

    public void getGRMessage() {
        suscription = NetWork.getMe().organAlbumls(UserManager.getInstance().getUser().id, 2, "1")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer1);
    }

    public void setDelectFlag(boolean b) {
        gridViewImageView.canDelete(b);
    }

    @Override
    public void initData() {

    }

    @Override
    public int getMyLayout() {
        return R.layout.activity_me_ji_gou_xcgl_sp;
    }

    @Override
    public void initListener() {

    }

    @OnClick({R.id.me_jg_xcgl_shangchuansp})
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.me_jg_xcgl_shangchuansp:
//                gridViewImageView.deleteSelectImage();
                startActivity(new Intent(getActivity(), MeJiGouXcglScsp.class));
                break;
        }
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

    public void chege() {

    }
}
