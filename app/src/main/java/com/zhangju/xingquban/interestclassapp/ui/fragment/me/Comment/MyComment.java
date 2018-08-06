package com.zhangju.xingquban.interestclassapp.ui.fragment.me.Comment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

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

public class MyComment extends BaseActivity {
    public static final String TAG = "MyComment";
    @BindView(R.id.me_comment_plNumber)
    TextView meCommentPlNumber;
    private LinearLayoutManager linearLayoutManager;
    private MeCommentdapter meCommentAdapter;

    @BindView(R.id.me_comment_head)
    PublicHead meCommentHead;
    @BindView(R.id.me_comment_recycler)
    RecyclerView meCommentRecycler;
    private Subscription subscription;

    Observer<MeCommentBean> observer1 = new Observer<MeCommentBean>() {

        @Override
        public void onCompleted() {

        }

        @Override
        public void onError(Throwable e) {
            Log.i(TAG, "onError: " + e);
        }

        @Override
        public void onNext(MeCommentBean meCommentBean) {
            Log.i(TAG, "meUserBean: " + meCommentBean.toString());
            String s = meCommentBean.getAaData().get(0).getDegreeId();

            if (meCommentBean.isSuccess()) {
                linearLayoutManager = new LinearLayoutManager(mContext);
                meCommentRecycler.setLayoutManager(linearLayoutManager);
                meCommentAdapter = new MeCommentdapter(mContext, meCommentBean);
                meCommentRecycler.setAdapter(meCommentAdapter);

                meCommentPlNumber.setText(meCommentBean.getITotalRecords() + "");
            } else {
                ToastUtil.showToast("获取失败");
            }

        }
    };

    @Override
    public int getLayout() {
        return R.layout.activity_my_comment;
    }

    @Override
    public void initView() {
        getMeWalletcommetnHead();
        getGRMessage();
    }

    public void getGRMessage() {
        Intent intent = getIntent();
        String teacherTimeId = intent.getStringExtra("teacherTimeId");
        subscription = NetWork.getMe().getMeComment(UserManager.getInstance().getUser().id, "6426")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer1);
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


    public void getMeWalletcommetnHead() {
        meCommentHead.setTitle("我的评论");
        meCommentHead.setShow(true, false, false);
        meCommentHead.setBackClickListener(new View.OnClickListener() {
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
