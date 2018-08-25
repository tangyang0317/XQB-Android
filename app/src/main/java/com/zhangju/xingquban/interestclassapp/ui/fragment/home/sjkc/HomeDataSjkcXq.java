package com.zhangju.xingquban.interestclassapp.ui.fragment.home.sjkc;


import android.content.Intent;
import android.graphics.Paint;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.fastlib.net.Request;
import com.fastlib.net.SimpleListener;
import com.zhangju.xingquban.R;
import com.zhangju.xingquban.interestclassapp.RetrofitInterface.INear;
import com.zhangju.xingquban.interestclassapp.application.MyApp;
import com.zhangju.xingquban.interestclassapp.base.BaseActivity;
import com.zhangju.xingquban.interestclassapp.bean.near.LessonXqBean;
import com.zhangju.xingquban.interestclassapp.refactor.location.LocationManager;
import com.zhangju.xingquban.interestclassapp.ui.activity.near.CurriculumHomeOrderActivity;
import com.zhangju.xingquban.interestclassapp.ui.activity.near.CurriculumOrderActivity;
import com.zhangju.xingquban.interestclassapp.ui.fragment.home.HomeDataTeacherBean;
import com.zhangju.xingquban.interestclassapp.ui.fragment.me.Comment.MeCommentBean;
import com.zhangju.xingquban.interestclassapp.ui.fragment.me.Comment.MeCommentdapter;
import com.zhangju.xingquban.interestclassapp.ui.widget.PublicHead;
import com.zhangju.xingquban.interestclassapp.util.RecyclerItemDecoration.DensityUtils;
import com.zhangju.xingquban.interestclassapp.util.RecyclerItemDecoration.SpaceItemDecoration;
import com.zhangju.xingquban.interestclassapp.view.dialog.MyShareDialog;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class HomeDataSjkcXq extends BaseActivity {


    @BindView(R.id.home_data_msfcXq_Head)
    PublicHead homeDataSjkcXqHead;
    @BindView(R.id.img_kcbanner)
    ImageView imgKcbanner;
    @BindView(R.id.tv_kcname)
    TextView tvKcname;
    @BindView(R.id.home_data_sjkcXq_sellnumber)
    TextView homeDataSjkcXqSellnumber;
    @BindView(R.id.tv_kcsubname)
    TextView tvKcsubname;
    @BindView(R.id.isCantry_Boolean)
    TextView isCantryBoolean;
    @BindView(R.id.methodType_scfs)
    TextView methodTypeScfs;
    @BindView(R.id.tv_scdd)
    TextView tvScdd;
    @BindView(R.id.tv_kcjj)
    TextView tvKcjj;
    @BindView(R.id.tv_less_comment_num)
    TextView tvLessCommentNum;
    @BindView(R.id.home_data_sjkcXq_recycler)
    RecyclerView homeDataSjkcXqRecycler;
    @BindView(R.id.tv_lessprice)
    TextView tvLessprice;
    @BindView(R.id.tv_less_vip_price)
    TextView tvLessVipPrice;
    @BindView(R.id.seek_choiceness_xq_button)
    Button seekChoicenessXqButton;
    @BindView(R.id.courseCount)
    TextView courseCount;
    @BindView(R.id.courseLength)
    TextView courseLength;

    private LessonXqBean lessonXqBean;
    private List<MeCommentBean> list = new ArrayList();
    private MeCommentdapter meCommentdapter;
    private HomeDataTeacherBean.AaDataBean.LessonsBean lessonsBea;
    private String SHARE_ICON_URL = "http://m.xqban.com/rs/app/images/down_logo.png";
    private String mId;
    private MyShareDialog mMyShareDialog;

    @Override
    public int getLayout() {
        return R.layout.activity_home_data_sjkc_xq;
    }

    @Override
    public void initView() {
        lessonsBea = (HomeDataTeacherBean.AaDataBean.LessonsBean) getIntent().getExtras().getSerializable("lessons");
        Request request = Request.obtain(INear.POST_LESSONS_XQ);
        mId = lessonsBea.getId();
        request.put("id", mId);
        request.put("lng", LocationManager.getInstance().getLocation().longitude);
        request.put("lat", LocationManager.getInstance().getLocation().latitude);
        request.setListener(new SimpleListener<LessonXqBean>() {

            @Override
            public void onResponseListener(Request r, LessonXqBean result) {
                lessonXqBean = result;
                setHomeDataSjkcXqHead();
                bindDatatoView();

            }

            @Override
            public void onErrorListener(Request r, String error) {
                super.onErrorListener(r, error);
            }
        });
        request.start();
        mMyShareDialog = new MyShareDialog(mContext);
        String shareContent = lessonsBea.getDescript();
        String shareTitle = lessonsBea.getName();
        String shareImg = lessonsBea.getPicture();
        String shareUrl = MyApp.URL + "/share/#/class/detail?id=" + mId;
        mMyShareDialog.initShare(shareImg, shareUrl, shareContent, shareTitle);
    }

    @Override
    public void initData() {
        list.clear();
        MeCommentBean meCommentBean = new MeCommentBean();
        list.add(meCommentBean);

        for (int i = 0; i < 1; i++) {
            list.add(list.get(0));
        }
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false) {
            @Override
            public boolean canScrollVertically() {
                return false;
            }
        };
        homeDataSjkcXqRecycler.setLayoutManager(linearLayoutManager);
        //        meCommentdapter = new MeCommentdapter(this, list);
        homeDataSjkcXqRecycler.setAdapter(meCommentdapter);
        homeDataSjkcXqRecycler.addItemDecoration(new SpaceItemDecoration(DensityUtils.dp2px(this, 10)));
    }

    private void bindDatatoView() {
        Glide.with(HomeDataSjkcXq.this).load(lessonXqBean.getAaData().get(0).getPicture()).into(imgKcbanner);
        homeDataSjkcXqSellnumber.setText(lessonXqBean.getAaData().get(0).getLessonOrders() + "");
        tvKcname.setText(lessonXqBean.getAaData().get(0).getName());

        tvKcsubname.setText(lessonXqBean.getAaData().get(0).getDescript());
        isCantryBoolean.setText(lessonXqBean.getAaData().get(0).isIsCantry() ? "可以试听" : "不可以试听");
        /*授课方式*/
        switch (lessonsBea.getMethodType()) {
            case 1:
                methodTypeScfs.setText("学生上门");
                break;
            case 2:
                methodTypeScfs.setText("家教上门");
                break;
            case 3:
                methodTypeScfs.setText("协商地点");
                break;
        }

        tvScdd.setText(lessonXqBean.getAaData().get(0).getMethodType() == 3 ? "协商地点" : lessonXqBean.getAaData().get(0)
                .getRegion());
        tvKcjj.setText(lessonXqBean.getAaData().get(0).getSummary());
        tvLessprice.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG);
        tvLessprice.setText(lessonXqBean.getAaData().get(0).getPrice() + "");
        tvLessVipPrice.setText(lessonXqBean.getAaData().get(0).getVipPrice() + "");
        courseCount.setText(String.format(Locale.getDefault(), "%d节", lessonXqBean.getAaData().get(0).getCourses()));
        courseLength.setText(String.format(Locale.getDefault(), "%s分钟", lessonXqBean.getAaData().get(0).getTimelength()));
    }

    @Override
    public void initListener() {

    }

    @OnClick({R.id.seek_choiceness_xq_button})
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.seek_choiceness_xq_button:
                Intent intent = new Intent(this, CurriculumHomeOrderActivity.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable(CurriculumOrderActivity.ARG_BEAN_DATA, lessonsBea);
                intent.putExtras(bundle);
                startActivity(intent);
                double money = lessonsBea.getPrice();
                break;
        }
    }

    public void setHomeDataSjkcXqHead() {
        homeDataSjkcXqHead.setTitle("课程详情");
        homeDataSjkcXqHead.setShow(true, true, false);
        homeDataSjkcXqHead.setImgSearch(R.drawable.nav_btn_share_red);
        homeDataSjkcXqHead.setBackClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        homeDataSjkcXqHead.setImgSeachRightClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mMyShareDialog.show();
            }
        });
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
    }
}
