package com.zhangju.xingquban.interestclassapp.ui.fragment.me.Institutions.xcgl;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import com.zhangju.xingquban.R;
import com.zhangju.xingquban.interestclassapp.RetrofitInterface.NetWork;
import com.zhangju.xingquban.interestclassapp.base.BaseActivity;
import com.zhangju.xingquban.interestclassapp.refactor.user.UserManager;
import com.zhangju.xingquban.interestclassapp.ui.widget.PublicHead;
import com.zhangju.xingquban.interestclassapp.util.ToastUtil;

import butterknife.BindView;
import butterknife.ButterKnife;
import rx.Observer;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class MeJiGouXcglScspSpj extends BaseActivity {

    @BindView(R.id.spj_Head)
    PublicHead spjHead;
    @BindView(R.id.spj_Recycler)
    RecyclerView spjRecycler;
    private Subscription suscription;
    private MeJiGouXcglScspSpjAdapter meJiGouXcglScspSpjAdapter;
    Observer<MeJiGouXcglScspSpjBean> observer = new Observer<MeJiGouXcglScspSpjBean>() {

        public static final String TAG = "";

        @Override
        public void onCompleted() {

        }

        @Override
        public void onError(Throwable e) {
            Log.i(TAG, "onError: " + e);
        }

        @Override
        public void onNext(final MeJiGouXcglScspSpjBean meJiGouXcglScspSpjBean) {
            Log.i(TAG, "meUserBean: " + meJiGouXcglScspSpjBean.toString());
            if (meJiGouXcglScspSpjBean.isSuccess()) {
                LinearLayoutManager linearLayoutManager = new LinearLayoutManager(mContext);
                spjRecycler.setLayoutManager(linearLayoutManager);
                meJiGouXcglScspSpjAdapter = new MeJiGouXcglScspSpjAdapter(mContext, meJiGouXcglScspSpjBean);
                spjRecycler.setAdapter(meJiGouXcglScspSpjAdapter);
                meJiGouXcglScspSpjAdapter.setOnItemClickListener(new MeJiGouXcglScspSpjAdapter.OnItemClickListener() {
                    @Override
                    public void onItemClick(View view, int position) {
                        String files = meJiGouXcglScspSpjBean.getAaData().get(position).getFiles();
                        Intent intent = new Intent(MeJiGouXcglScspSpj.this, MeJiGouXcglScsp.class);
                        intent.putExtra("files", files);
                        startActivityForResult(intent, 1);
                        finish();
                    }
                });
            } else {
                ToastUtil.showToast("错误");
            }

        }
    };

    @Override
    public int getLayout() {
        return R.layout.activity_me_ji_gou_xcgl_scsp_spj;
    }

    @Override
    public void initView() {
        getSpjData();
        setSpjHead();
    }

    @Override
    public void initData() {

    }

    @Override
    public void initListener() {

    }

    public void getSpjData() {
        suscription = NetWork.getMe().videoFiles(UserManager.getInstance().getUser().id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);
    }

    @Override
    public void onClick(View v) {

    }

    public void setSpjHead() {
        spjHead.setTitle("视频夹");
        spjHead.setShow(true, false, false);
        spjHead.setBackClickListener(new View.OnClickListener() {
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
