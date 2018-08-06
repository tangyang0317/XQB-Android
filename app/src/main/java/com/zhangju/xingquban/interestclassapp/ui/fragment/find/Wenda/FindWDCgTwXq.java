package com.zhangju.xingquban.interestclassapp.ui.fragment.find.Wenda;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.zhangju.xingquban.R;
import com.zhangju.xingquban.interestclassapp.RetrofitInterface.NetWork;
import com.zhangju.xingquban.interestclassapp.base.BaseActivity;
import com.zhangju.xingquban.interestclassapp.refactor.user.UserManager;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import rx.Observer;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

//提问草稿详情
public class FindWDCgTwXq extends BaseActivity {
    public static final String TAG = "FindWDCgTwXq";

    @BindView(R.id.find_wdXq_return)
    LinearLayout findWdXqReturn;
    @BindView(R.id.find_wdXq_startFalse)
    ImageView findWdXqStartFalse;
    @BindView(R.id.find_wdXq_startTrue)
    ImageView findWdXqStartTrue;
    @BindView(R.id.find_wdXq_start)
    LinearLayout findWdXqStart;
    @BindView(R.id.head)
    RelativeLayout head;
    @BindView(R.id.find_wenda_item_title)
    TextView findWendaItemTitle;
    @BindView(R.id.find_wenda_itemXq_recycler)
    RecyclerView findWendaItemXqRecycler;
    @BindView(R.id.find_wenda_shunfa)
    TextView findWendaShunfa;
    @BindView(R.id.find_wenda_item_day)
    TextView findWendaItemDay;
    @BindView(R.id.find_wenda_item_plnumber)
    TextView findWendaItemPlnumber;
    @BindView(R.id.find_wenda_item_name)
    TextView findWendaItemName;
    @BindView(R.id.linearLayout1)
    LinearLayout linearLayout1;
    @BindView(R.id.find_wenda_itemPl_recycler)
    RecyclerView findWendaItemPlRecycler;
    @BindView(R.id.find_tiwen_bj)
    ImageView findTiwenBj;

    @BindView(R.id.image_find_collect)
    ImageView image_find_collect;

    private Subscription subscription;
    private String id, title;
    private FindTiWenXqPlAdapter findTiWenXqPlAdapter;
    private FindItemRecyclerAdapter3 findItemRecyclerAdapter2;

    Observer<FindMeCgTwXqBean> observer1 = new Observer<FindMeCgTwXqBean>() {

        @Override
        public void onCompleted() {

        }

        @Override
        public void onError(Throwable e) {

        }

        @Override
        public void onNext(FindMeCgTwXqBean findMeCgTwXqBean) {
            Log.i(TAG, "=====homeRecylerBean=====" + findMeCgTwXqBean.getAaData().toString());
            try {
                title = findMeCgTwXqBean.getAaData().get(0).getTitle();
                findWendaItemTitle.setText(title);
                findWendaShunfa.setText(findMeCgTwXqBean.getAaData().get(0).getLabel());
                findWendaItemDay.setText(findMeCgTwXqBean.getAaData().get(0).getDateTime());
                findWendaItemPlnumber.setText(String.valueOf(findMeCgTwXqBean.getAaData().get(0).getAnswers()));
                findWendaItemName.setText(findMeCgTwXqBean.getAaData().get(0).getAuthorName());

                findWendaItemXqRecycler.setLayoutManager(new GridLayoutManager(mContext, 3));
//            findItemRecyclerAdapter2 = new FindItemRecyclerAdapter3(mContext, findMeCgTwXqBean);
                findWendaItemXqRecycler.setAdapter(findItemRecyclerAdapter2);

                isCollect = findMeCgTwXqBean.getAaData().get(0).isIsCollection();
                Glide.with(FindWDCgTwXq.this).load(isCollect == true ? R.mipmap.find_wdxqstart_true : R.mipmap.find_wdxqstart_false).into(image_find_collect);

            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    };

    Observer<FindTiWenXqPlBean> observer2 = new Observer<FindTiWenXqPlBean>() {

        @Override
        public void onCompleted() {

        }

        @Override
        public void onError(Throwable e) {

        }

        @Override
        public void onNext(FindTiWenXqPlBean findTiWenXqPlBean) {
            Log.i(TAG, "=====findTiWenXqPlBean=====" + findTiWenXqPlBean.getAaData());
            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(mContext);
            findWendaItemPlRecycler.setLayoutManager(linearLayoutManager);
            findTiWenXqPlAdapter = new FindTiWenXqPlAdapter(mContext, findTiWenXqPlBean);
            findWendaItemPlRecycler.setAdapter(findTiWenXqPlAdapter);
        }
    };

    @Override
    public int getLayout() {
        return R.layout.activity_find_wdcg_tw_xq;
    }

    @Override
    public void initView() {
        Intent intent = getIntent();
        id = intent.getStringExtra("id");
        setFindWdXqData();
        setCxWdCg();
    }

    @Override
    public void initData() {

    }

    @Override
    public void initListener() {
    }

    @Override
    protected void onResume() {
        super.onResume();
        setCxWdCg();
        setFindWdXqData();
    }

    //查询我的草稿
    public void setCxWdCg() {
        subscription = NetWork.getMeTiwen().myQuestionCg(2, id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer1);
    }


    public void setFindWdXqData() {
        subscription = NetWork.getMeTiwen().myAnswerls(2, id, 1000)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer2);
    }


    @OnClick({R.id.find_tiwen_bj,R.id.image_find_collect, R.id.find_wdXq_start, R.id.find_wdXq_return, R.id.find_wdXq_startFalse, R.id.find_wdXq_startTrue})
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.find_tiwen_bj:
                Intent intent = new Intent(this, FindTiWenXqHd.class);
                intent.putExtra("title", title);
                intent.putExtra("id", id);
                startActivityForResult(intent, 1);
                break;

            case R.id.find_wdXq_start:
                findWdXqStartFalse.setVisibility(View.GONE);
                findWdXqStartTrue.setVisibility(View.VISIBLE);
//                ToastUtil.showToast("收藏成功");
//                setFindWdSc();
                break;

            case R.id.find_wdXq_startFalse:

                break;
            case R.id.find_wdXq_startTrue:

                break;

            case R.id.find_wdXq_return:
                finish();
                break;
            case R.id.image_find_collect:
                setFindWdSc();
                break;
        }
    }

    //收藏调用
    private void setFindWdSc() {
        subscription = NetWork.getMeTiwen().questionCollection(UserManager.getInstance().getUser().id, id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer3);
    }

    Observer<FindTiWenXqSCBean> observer3 = new Observer<FindTiWenXqSCBean>() {

        @Override
        public void onCompleted() {

        }

        @Override
        public void onError(Throwable e) {

        }

        @Override
        public void onNext(FindTiWenXqSCBean findTiWenXqSCBean) {
            boolean flag = findTiWenXqSCBean.isSuccess();
            if (flag) {
                isCollect = isCollect == true ? false : true;
            }
            Glide.with(FindWDCgTwXq.this).load(isCollect == true ? R.mipmap.find_wdxqstart_true : R.mipmap.find_wdxqstart_false).into(image_find_collect);
        }
    };
    boolean isCollect = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
    }
}
