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
import com.zhangju.xingquban.R;
import com.zhangju.xingquban.interestclassapp.base.BaseActivity;
import com.zhangju.xingquban.interestclassapp.bean.near.CurriculumBean;
import com.zhangju.xingquban.interestclassapp.refactor.common.utils.ThirdPartyUtils;
import com.zhangju.xingquban.interestclassapp.ui.activity.near.CurriculumOrderActivity;
import com.zhangju.xingquban.interestclassapp.ui.fragment.me.Comment.MeCommentBean;
import com.zhangju.xingquban.interestclassapp.ui.fragment.me.Comment.MeCommentdapter;
import com.zhangju.xingquban.interestclassapp.ui.widget.PublicHead;
import com.zhangju.xingquban.interestclassapp.util.RecyclerItemDecoration.DensityUtils;
import com.zhangju.xingquban.interestclassapp.util.RecyclerItemDecoration.SpaceItemDecoration;
import com.zhangju.xingquban.interestclassapp.view.dialog.ShareDialog;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class CurriculumXqActivity extends BaseActivity {
    public static final  String ARG_BEAN_DATA ="lessons";
    @BindView(R.id.home_data_sjkcXq_head)
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
    @BindView(R.id.courseCount)
    TextView mCourseCount;
    @BindView(R.id.courseLength)
    TextView mCourseLength;
    @BindView(R.id.seek_choiceness_xq_button)
    Button seekChoicenessXqButton;
    private List<MeCommentBean> list = new ArrayList();
    private MeCommentdapter meCommentdapter;
    private CurriculumBean.AaDataBean lessonsBea;
    private ShareDialog shareDialog;
    private String SHARE_ICON_URL = "http://m.xqban.com/rs/app/images/down_logo.png";
    @Override
    public int getLayout() {
        return R.layout.activity_home_data_sjkc_xq;
    }

    @Override
    public void initView() {
        lessonsBea = (CurriculumBean.AaDataBean) getIntent().getExtras().getSerializable("lessons");
        setHomeDataSjkcXqHead();
        bindDatatoView();
        shareDialog=new ShareDialog(this,R.style.ActionSheetDialogStyle);
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
        Glide.with(CurriculumXqActivity.this).load(lessonsBea.getPicture()).into(imgKcbanner);
        homeDataSjkcXqSellnumber.setText(lessonsBea.getLessonOrders()+"");
        tvKcname.setText(lessonsBea.getName());
        tvKcsubname.setText(lessonsBea.getDescript());
        isCantryBoolean.setText(lessonsBea.isIsCantry() ? "可以试听" : "不可以试听");
        /*授课方式*/
        switch (lessonsBea.getMethodType()){
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

        tvScdd.setText(lessonsBea.getMethodType()==3?"协商地点":lessonsBea.getRegion());
        tvKcjj.setText(lessonsBea.getDescript());
        tvLessprice.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG);
        tvLessprice.setText(lessonsBea.getPrice()+"");
    //    tvLessprice.setVisibility(UserManager.getInstance().getUser().isMember?View.VISIBLE:View.GONE);
        tvLessVipPrice.setText(lessonsBea.getVipPrice()+"");
        mCourseCount.setText(String.format(Locale.getDefault(),"%d节",lessonsBea.getCourses()));
        mCourseLength.setText(String.format(Locale.getDefault(),"%s分钟",lessonsBea.getTimelength()));
    }

    @Override
    public void initListener() {
        shareDialog.QQZone(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ArrayList<String> imgs=new ArrayList<String>();
                imgs.add(lessonsBea.getPicture());
                ThirdPartyUtils.getInstance(CurriculumXqActivity.this).shareUrlToZone(CurriculumXqActivity.this,SHARE_ICON_URL,lessonsBea.getName(),"",imgs);
            }
        });
        shareDialog.qq(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ThirdPartyUtils.getInstance(CurriculumXqActivity.this).shareToQQ(CurriculumXqActivity.this,SHARE_ICON_URL,lessonsBea.getName(),"",lessonsBea.getPicture());
            }
        });
        shareDialog.wechat(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ThirdPartyUtils.getInstance(CurriculumXqActivity.this).shareUrlToWechat(SHARE_ICON_URL,lessonsBea.getName(),"",lessonsBea.getPicture(),false,false);
            }
        });
        shareDialog.wechat_zone(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ThirdPartyUtils.getInstance(CurriculumXqActivity.this).shareUrlToWechat(SHARE_ICON_URL,lessonsBea.getName(),"",lessonsBea.getPicture(),false,true);
            }
        });
        shareDialog.weibo(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ThirdPartyUtils.getInstance(CurriculumXqActivity.this).shareToWeibo(CurriculumXqActivity.this,lessonsBea.getName(),lessonsBea.getPicture());
            }
        });
    }
    @OnClick({R.id.seek_choiceness_xq_button})
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.seek_choiceness_xq_button:
                double money= lessonsBea.getPrice();
                Intent intent=new Intent(this, CurriculumOrderActivity.class);
                Bundle bundle=new Bundle();
                bundle.putSerializable(CurriculumOrderActivity.ARG_BEAN_DATA,lessonsBea);
                intent.putExtras(bundle);
                startActivity(intent);
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
                shareDialog.show();
            }
        });
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
    }
}
