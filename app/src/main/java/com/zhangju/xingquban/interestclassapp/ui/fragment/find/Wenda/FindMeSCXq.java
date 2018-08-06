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
import com.zhangju.xingquban.interestclassapp.util.RecyclerItemDecoration.DensityUtils;
import com.zhangju.xingquban.interestclassapp.util.RecyclerItemDecoration.SpaceItemDecoration2;
import com.zhangju.xingquban.interestclassapp.util.ToastUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import rx.Observer;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

//我的收藏查看
public class FindMeSCXq extends BaseActivity {
    public static final String TAG = "FindMeSCXq";

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
    private String id1, title;
    private FindTiWenXqPlAdapter findTiWenXqPlAdapter;
    private FindItemRecyclerAdapter2 findItemRecyclerAdapter2;
    private SingleImageAdapter singleImageAdapter;
    private String id;
    boolean isCollect = false;

    Observer<FindMeWendaXqBean> observer1 = new Observer<FindMeWendaXqBean>() {

        @Override
        public void onCompleted() {

        }

        @Override
        public void onError(Throwable e) {

        }

        @Override
        public void onNext(FindMeWendaXqBean findMeWendaXqBean) {
            Log.i(TAG, "=====homeRecylerBean=====" + findMeWendaXqBean.getAaData().toString());
            title = findMeWendaXqBean.getAaData().get(0).getTitle();
            findWendaItemTitle.setText(title);
            findWendaShunfa.setText(findMeWendaXqBean.getAaData().get(0).getLabel());
            findWendaItemDay.setText(findMeWendaXqBean.getAaData().get(0).getDateTime());
            findWendaItemPlnumber.setText(findMeWendaXqBean.getAaData().get(0).getAnswers());
            findWendaItemName.setText(findMeWendaXqBean.getAaData().get(0).getAuthorName());

            findWendaItemXqRecycler.setLayoutManager(new GridLayoutManager(mContext, 3));
            findWendaItemXqRecycler.addItemDecoration(new SpaceItemDecoration2(DensityUtils.dp2px(mContext, 10)));
            findItemRecyclerAdapter2 = new FindItemRecyclerAdapter2(mContext, findMeWendaXqBean);
            findWendaItemXqRecycler.setAdapter(findItemRecyclerAdapter2);
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
        return R.layout.activity_find_me_scxq;
    }

    @Override
    public void initView() {

        Bundle bundle = getIntent().getExtras();
        FindSCBean.AaDataBean dataBean = (FindSCBean.AaDataBean) bundle.getSerializable("data");

        id = dataBean.getQuestionId().toString();

        setFindWdSc();
        setFindWdXqData();
//
        try {
            isCollect=dataBean.getQuestion().isCollection();
            Object title = dataBean.getQuestion().getTitle() == null ? "" : dataBean.getQuestion().getTitle();
            Object editUserTime = dataBean.getQuestion().getEditUserTime() == null ? "" : dataBean.getQuestion().getEditUserTime();
            Object label = dataBean.getQuestion().getLabel() == null ? "" : dataBean.getQuestion().getLabel();//标签
            Object addUserName = dataBean.getQuestion().getAddUserName() == null ? "" : dataBean.getQuestion().getAddUserName();
            List<FindSCBean.AaDataBean.QuestionBean.AnswerBean> answersList = dataBean.getQuestion().getAnswersList();
            List<FindSCBean.AaDataBean.PicItem> picList = dataBean.getPicList();


            List<String> mlist = new ArrayList<>();
            mlist.clear();

            for (int i = 0; i < picList.size(); i++) {
                mlist.add(picList.get(i).getPicture());
            }


            findWendaItemTitle.setText(title.toString());
            findWendaShunfa.setText(label.toString());
            findWendaItemDay.setText(editUserTime.toString());
            findWendaItemName.setText(addUserName.toString());
            findWendaItemPlnumber.setText(answersList.size() + "");

//
            findWendaItemXqRecycler.setLayoutManager(new GridLayoutManager(mContext, 3));
//            findWendaItemXqRecycler.addItemDecoration(new SpaceItemDecoration2(DensityUtils.dp2px(mContext, 10)));
            singleImageAdapter = new SingleImageAdapter(FindMeSCXq.this, mlist);
            findWendaItemXqRecycler.setAdapter(singleImageAdapter);
        } catch (Exception ex) {
            ex.printStackTrace();
        }


    }

    @Override
    public void initData() {

    }

    @Override
    public void initListener() {
    }

    public void setFindWdSc() {

        subscription = NetWork.getMeTiwen().myQuestionWd(1, false, id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer1);
    }

    public void setFindWdXqData() {

        subscription = NetWork.getMeTiwen().myAnswerls(1, id, 1000)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer2);
    }


    @OnClick({R.id.find_tiwen_bj, R.id.image_find_collect, R.id.find_wdXq_start, R.id.find_wdXq_return, R.id.find_wdXq_startFalse, R.id.find_wdXq_startTrue})
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.find_tiwen_bj:
                Intent intent = new Intent(this, FindTiWenXqHd.class);
                intent.putExtra("title", title);
                intent.putExtra("id", id1);
                startActivityForResult(intent, 1);
                break;

            case R.id.find_wdXq_start:
                findWdXqStartFalse.setVisibility(View.GONE);
                findWdXqStartTrue.setVisibility(View.VISIBLE);
                ToastUtil.showToast("收藏成功");
                setFindWdSc();
                break;

            case R.id.find_wdXq_startFalse:

                break;
            case R.id.find_wdXq_startTrue:

                break;

            case R.id.find_wdXq_return:
                finish();
                break;


            case R.id.image_find_collect:

                addCollectTion();
                break;
        }
    }

    private void addCollectTion() {
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
            Glide.with(FindMeSCXq.this).load(isCollect == true ? R.mipmap.find_wdxqstart_true : R.mipmap.find_wdxqstart_false).into(image_find_collect);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
    }
}
