package com.zhangju.xingquban.interestclassapp.ui.fragment.home.sjkc;


import android.app.Activity;
import android.content.Intent;
import android.graphics.Paint;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.fastlib.net.Request;
import com.fastlib.net.SimpleListener;
import com.zhangju.xingquban.R;
import com.zhangju.xingquban.interestclassapp.RetrofitInterface.INear;
import com.zhangju.xingquban.interestclassapp.base.BaseActivity;
import com.zhangju.xingquban.interestclassapp.bean.near.LessonXqBean;
import com.zhangju.xingquban.interestclassapp.refactor.common.utils.ThirdPartyUtils;
import com.zhangju.xingquban.interestclassapp.refactor.location.LocationManager;
import com.zhangju.xingquban.interestclassapp.ui.activity.near.CurriculumOrderActivity;
import com.zhangju.xingquban.interestclassapp.view.dialog.ShareDialog;
import com.zhangju.xingquban.refactoring.adapter.LessonDetailsPagerAdapter;
import com.zhangju.xingquban.refactoring.utils.DimentUtils;

import java.util.ArrayList;

import butterknife.ButterKnife;

public class CurriculumXqActivity extends BaseActivity {
    public static final String ARG_BEAN_DATA = "lessons";
    private ImageView lessonBackImg, lessonSharedImg, bannerImg;
    private TextView lessonNameTxt, orderNum, lessonSubTitleTxt;
    private TextView vipPrice, lessPrice;
    private Button signUpBtn;
    private TabLayout lessonDetailsTablayout;
    private ViewPager lessonDetailsViewPager;
    private ShareDialog shareDialog;
    private String SHARE_ICON_URL = "http://m.xqban.com/rs/app/images/down_logo.png";
    private LessonXqBean.AaDataBean lessonsBea = null;


    public static void lanuchActivity(Activity activity, String lessonId) {
        Intent intent = new Intent(activity, CurriculumXqActivity.class);
        intent.putExtra("lessonId", lessonId);
        activity.startActivity(intent);
    }

    private String getlessonId() {
        if (getIntent() != null) {
            return getIntent().getStringExtra("lessonId");
        }
        return "";
    }

    @Override
    public int getLayout() {
        return R.layout.activity_lesson_details;
    }

    @Override
    public void initView() {
        lessonBackImg = findViewById(R.id.lessonBackImg);
        lessonSharedImg = findViewById(R.id.lessonSharedImg);
        bannerImg = findViewById(R.id.img_kcbanner);
        lessonNameTxt = findViewById(R.id.tv_kcname);
        orderNum = findViewById(R.id.home_data_sjkcXq_sellnumber);
        lessonSubTitleTxt = findViewById(R.id.tv_kcsubname);
        lessonDetailsTablayout = findViewById(R.id.lessonDetailsTablayout);
        lessonDetailsViewPager = findViewById(R.id.lessonDetailsViewPager);
        signUpBtn = findViewById(R.id.seek_choiceness_xq_button);
        vipPrice = findViewById(R.id.tv_less_vip_price);
        lessPrice = findViewById(R.id.tv_lessprice);
        shareDialog = new ShareDialog(this, R.style.ActionSheetDialogStyle);
        int screenWidth = DimentUtils.getScreenWidth(this);
        ViewGroup.LayoutParams bannerImgLayoutParams = bannerImg.getLayoutParams();
        bannerImgLayoutParams.width = screenWidth;
        bannerImgLayoutParams.height = screenWidth;
        bannerImg.setLayoutParams(bannerImgLayoutParams);

        final Request request = Request.obtain(INear.POST_LESSONS_XQ);
        request.put("id", getlessonId());
        request.put("lng", LocationManager.getInstance().getLocation().longitude);
        request.put("lat", LocationManager.getInstance().getLocation().latitude);
        request.setListener(new SimpleListener<LessonXqBean>() {

            @Override
            public void onResponseListener(Request r, LessonXqBean result) {
                if (request != null) {
                    lessonsBea = result.getAaData().get(0);
                    initData(result.getAaData().get(0));
                }
            }

            @Override
            public void onErrorListener(Request r, String error) {
                super.onErrorListener(r, error);
            }
        });
        request.start();

    }

    @Override
    public void initData() {

    }

    public void initData(LessonXqBean.AaDataBean lessonsBea) {
        Glide.with(CurriculumXqActivity.this).load(lessonsBea.getPicture()).into(bannerImg);
        orderNum.setText(lessonsBea.getLessonOrders() + "");
        lessonNameTxt.setText(lessonsBea.getName());
        lessonSubTitleTxt.setText(lessonsBea.getDescript());
        lessPrice.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG);
        lessPrice.setText(lessonsBea.getPrice() + "");
        vipPrice.setText("¥" + lessonsBea.getVipPrice() + "");
        lessonDetailsViewPager.setAdapter(new LessonDetailsPagerAdapter(getSupportFragmentManager(), new String[]{"课程详情", "课程属性"}, lessonsBea));
        lessonDetailsTablayout.setupWithViewPager(lessonDetailsViewPager);
    }

    @Override
    public void initListener() {
        lessonBackImg.setOnClickListener(this);
        lessonSharedImg.setOnClickListener(this);
        signUpBtn.setOnClickListener(this);
        shareDialog.QQZone(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ArrayList<String> imgs = new ArrayList<String>();
                imgs.add(lessonsBea.getPicture());
                ThirdPartyUtils.getInstance(CurriculumXqActivity.this).shareUrlToZone(CurriculumXqActivity.this, SHARE_ICON_URL, lessonsBea.getName(), "", imgs);
            }
        });
        shareDialog.qq(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ThirdPartyUtils.getInstance(CurriculumXqActivity.this).shareToQQ(CurriculumXqActivity.this, SHARE_ICON_URL, lessonsBea.getName(), "", lessonsBea.getPicture());
            }
        });
        shareDialog.wechat(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ThirdPartyUtils.getInstance(CurriculumXqActivity.this).shareUrlToWechat(SHARE_ICON_URL, lessonsBea.getName(), "", lessonsBea.getPicture(), false, false);
            }
        });
        shareDialog.wechat_zone(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ThirdPartyUtils.getInstance(CurriculumXqActivity.this).shareUrlToWechat(SHARE_ICON_URL, lessonsBea.getName(), "", lessonsBea.getPicture(), false, true);
            }
        });
        shareDialog.weibo(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ThirdPartyUtils.getInstance(CurriculumXqActivity.this).shareToWeibo(CurriculumXqActivity.this, lessonsBea.getName(), lessonsBea.getPicture());
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.seek_choiceness_xq_button:
                CurriculumOrderActivity.lanuchActivity(CurriculumXqActivity.this, lessonsBea.getId());
                break;
            case R.id.lessonBackImg:
                CurriculumXqActivity.this.finish();
                break;
            case R.id.lessonSharedImg:
                shareDialog.show();
                break;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
    }
}
