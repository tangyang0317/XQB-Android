package com.zhangju.xingquban.interestclassapp.ui.fragment.find.Wenda;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
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

//推荐详情查看
public class FindTiWenXq extends BaseActivity {
    public static final String TAG = "FindTiWenXq";

    private final int FIND_TWXQ_SCF = 1;
    private final int FIND_TWXQ_SCT = 2;
    @BindView(R.id.find_wenda_itemXq_recycler)
    RecyclerView findWendaItemXqRecycler;
    @BindView(R.id.find_wdXq_return)
    LinearLayout findWdXqReturn;
    @BindView(R.id.find_wdXq_startFalse)
    ImageView findWdXqStartFalse;
    @BindView(R.id.find_wdXq_startTrue)
    ImageView findWdXqStartTrue;
    @BindView(R.id.find_wdXq_start)
    LinearLayout findWdXqStart;
    @BindView(R.id.find_wenda_item_title)
    TextView findWendaItemTitle;
    @BindView(R.id.find_wenda_shunfa)
    TextView findWendaShunfa;
    @BindView(R.id.find_wenda_item_day)
    TextView findWendaItemDay;
    @BindView(R.id.find_wenda_item_plnumber)
    TextView findWendaItemPlnumber;
    @BindView(R.id.find_wenda_item_name)
    TextView findWendaItemName;
    @BindView(R.id.find_tiwen_bj)
    ImageView findTiwenBj;

    @BindView(R.id.image_find_collect)
    ImageView image_find_collect;
    @BindView(R.id.find_wenda_itemPl_recycler)
    RecyclerView findWendaItemPlRecycler;

    private Subscription subscription;
    private String id, title;
    private FindTiWenXqPlAdapter findTiWenXqPlAdapter;
    private FindItemRecyclerAdapter findItemRecyclerAdapter;
    boolean isCollect = false;


    Observer<FindTiWenXqSCBean> observer1 = new Observer<FindTiWenXqSCBean>() {

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
            Glide.with(FindTiWenXq.this).load(isCollect == true ? R.mipmap.find_wdxqstart_true : R.mipmap.find_wdxqstart_false).into(image_find_collect);
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
        return R.layout.activity_find_ti_wen_xq;
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public void initView() {
        getBundle();
        setFindWdXqData();
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
        setFindWdXqData();
    }

    //添加收藏
    public void setFindWdSc() {
        subscription = NetWork.getMeTiwen().questionCollection(UserManager.getInstance().getUser().id, id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer1);
    }

    //查询我的回答
    public void setFindWdXqData() {
        subscription = NetWork.getMeTiwen().myAnswerls(1, id,1000)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer2);
    }


    @OnClick({R.id.find_tiwen_bj, R.id.image_find_collect, R.id.find_wdXq_start, R.id.find_wdXq_return, R.id.find_wdXq_startFalse, R.id.find_wdXq_startTrue})
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.find_tiwen_bj:
                Intent intent = new Intent(this, FindTiWenXqHd.class);
                intent.putExtra("title", findWendaItemTitle.getText().toString());
                intent.putExtra("id", id);
                startActivityForResult(intent, 1);
                break;

            case R.id.find_wdXq_start:
//                findWdXqStartFalse.setVisibility(View.GONE);
//                findWdXqStartTrue.setVisibility(View.VISIBLE);
//                ToastUtil.showToast("收藏成功");

                break;

            case R.id.find_wdXq_startFalse://未收藏

                break;
            case R.id.find_wdXq_startTrue://已收藏

                break;

            case R.id.find_wdXq_return:
                finish();
                break;


            case R.id.image_find_collect:

                setFindWdSc();
                break;
        }
    }

    public void getBundle() {
        Bundle bundle = getIntent().getExtras();
        //定义 Adapter传值 d Adapter对应item 图片的格式
        //Recycler setAdapter()
        try {
            FindWDBean.AaDataBean dataBean = (FindWDBean.AaDataBean) bundle.getSerializable("xq");
            findWendaItemXqRecycler.setLayoutManager(new GridLayoutManager(mContext, 3));
            findItemRecyclerAdapter = new FindItemRecyclerAdapter(mContext, dataBean);
            findWendaItemXqRecycler.setAdapter(findItemRecyclerAdapter);
            id = dataBean.getId();
            findWendaItemTitle.setText(dataBean.getTitle());
            findWendaShunfa.setText(dataBean.getLabel());
            findWendaItemDay.setText(dataBean.getDateTime());
            findWendaItemPlnumber.setText(dataBean.getAnswers() + "");
            findWendaItemName.setText(dataBean.getAuthorName());
            isCollect = dataBean.isIsCollection();
            Glide.with(FindTiWenXq.this).load(isCollect == true ? R.mipmap.find_wdxqstart_true : R.mipmap.find_wdxqstart_false).into(image_find_collect);


        } catch (Exception ex) {
            ex.printStackTrace();
        }


    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
    }
}
