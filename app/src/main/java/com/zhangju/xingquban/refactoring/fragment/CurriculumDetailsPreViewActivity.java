package com.zhangju.xingquban.refactoring.fragment;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.RequestParams;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest;
import com.zhangju.xingquban.R;
import com.zhangju.xingquban.interestclassapp.base.BaseActivity;
import com.zhangju.xingquban.interestclassapp.refactor.me.activity.EditCourseActivity;
import com.zhangju.xingquban.interestclassapp.refactor.me.bean.ResponseUploadImage;
import com.zhangju.xingquban.interestclassapp.refactor.user.UserManager;
import com.zhangju.xingquban.interestclassapp.util.ToastUtil;
import com.zhangju.xingquban.interestclassapp.util.UrlUtils;
import com.zhangju.xingquban.refactoring.activity.EditCourseDetailsActivity;
import com.zhangju.xingquban.refactoring.adapter.LessonDetailsPreViewPagerAdapter;
import com.zhangju.xingquban.refactoring.entity.BaseResponseBean;
import com.zhangju.xingquban.refactoring.utils.DimentUtils;

import java.io.File;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.List;

import butterknife.ButterKnife;

public class CurriculumDetailsPreViewActivity extends BaseActivity {

    private ImageView lessonBackImg, bannerImg;
    private TextView lessonNameTxt, orderNum, lessonSubTitleTxt;
    private TabLayout lessonDetailsTablayout;
    private ViewPager lessonDetailsViewPager;
    private Button publishRightNow;

    public static void lanuchActivity(Activity activity, HashMap<String, String> lessonsBea) {
        Intent intent = new Intent(activity, CurriculumDetailsPreViewActivity.class);
        intent.putExtra("lessonsBea", lessonsBea);
        activity.startActivity(intent);
    }

    /***
     * 获取课程详情数据参数
     * @return
     */
    private HashMap<String, String> getLessonParam() {
        if (getIntent() != null) {
            return (HashMap<String, String>) getIntent().getSerializableExtra("lessonsBea");
        }
        return null;
    }


    @Override
    public int getLayout() {
        return R.layout.act_curriculum_details_preview;
    }

    @Override
    public void initView() {
        lessonBackImg = findViewById(R.id.lessonBackImg);
        bannerImg = findViewById(R.id.img_kcbanner);
        lessonNameTxt = findViewById(R.id.tv_kcname);
        orderNum = findViewById(R.id.home_data_sjkcXq_sellnumber);
        lessonSubTitleTxt = findViewById(R.id.tv_kcsubname);
        lessonDetailsTablayout = findViewById(R.id.lessonDetailsTablayout);
        lessonDetailsViewPager = findViewById(R.id.lessonDetailsViewPager);
        publishRightNow = findViewById(R.id.publish_right_now);
        int screenWidth = DimentUtils.getScreenWidth(this);
        ViewGroup.LayoutParams bannerImgLayoutParams = bannerImg.getLayoutParams();
        bannerImgLayoutParams.width = screenWidth;
        bannerImgLayoutParams.height = screenWidth;
        bannerImg.setLayoutParams(bannerImgLayoutParams);
        initData();
    }

    @Override
    public void initData() {
        Glide.with(CurriculumDetailsPreViewActivity.this).load(getLessonParam().get("picture")).into(bannerImg);
        orderNum.setText("");
        lessonNameTxt.setText(getLessonParam().get("name"));
        lessonSubTitleTxt.setText(getLessonParam().get("descript"));
        lessonDetailsViewPager.setAdapter(new LessonDetailsPreViewPagerAdapter(getSupportFragmentManager(), new String[]{"课程详情", "课程属性"}, getLessonParam()));
        lessonDetailsTablayout.setupWithViewPager(lessonDetailsViewPager);
    }

    @Override
    public void initListener() {
        lessonBackImg.setOnClickListener(this);
        publishRightNow.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.lessonBackImg:
                CurriculumDetailsPreViewActivity.this.finish();
                break;
            case R.id.publish_right_now:
                if (getLessonParam().get("picture").startsWith("http") || getLessonParam().get("picture").equals("https")) {
                    publishLesson(getLessonParam().get("picture"));
                } else {
                    upLoadImg();
                }
                break;
        }
    }

    /***
     * 发布课程信息
     */
    private void publishLesson(String imgUrl) {
        RequestParams params = new RequestParams();
        params.addHeader("X-CustomToken", UserManager.getInstance().getToken());
        params.addBodyParameter("areasId", getLessonParam().get("areasId"));
        params.addBodyParameter("allows", getLessonParam().get("allows"));
        params.addBodyParameter("cityCode", getLessonParam().get("cityCode"));
        params.addBodyParameter("provinceId", getLessonParam().get("provinceId"));
        params.addBodyParameter("areasName", getLessonParam().get("areasName"));
        params.addBodyParameter("cityName", getLessonParam().get("cityName"));
        params.addBodyParameter("courses", getLessonParam().get("courses"));
        params.addBodyParameter("customerId", getLessonParam().get("customerId"));
        params.addBodyParameter("isCantry", getLessonParam().get("isCantry"));
        params.addBodyParameter("lat", getLessonParam().get("lat"));
        params.addBodyParameter("lng", getLessonParam().get("lng"));
        params.addBodyParameter("methodType", getLessonParam().get("methodType"));
        params.addBodyParameter("name", getLessonParam().get("name"));
        params.addBodyParameter("picture", imgUrl);
        params.addBodyParameter("price", getLessonParam().get("price"));
        params.addBodyParameter("provinceName", getLessonParam().get("provinceName"));
        params.addBodyParameter("region", getLessonParam().get("region"));
        params.addBodyParameter("resId", getLessonParam().get("resId"));
        params.addBodyParameter("teacherTimeId", getLessonParam().get("teacherTimeId"));
        params.addBodyParameter("timelength", getLessonParam().get("timelength"));
        params.addBodyParameter("vipPrice", getLessonParam().get("vipPrice"));
        params.addBodyParameter("lessonDate", getLessonParam().get("lessonDate"));
        params.addBodyParameter("categoriesId", getLessonParam().get("categoriesId"));
        params.addBodyParameter("catagoryName", getLessonParam().get("catagoryName"));
        params.addBodyParameter("descript", getLessonParam().get("descript"));
        params.addBodyParameter("summary", getLessonParam().get("summary"));
        if (!TextUtils.isEmpty(getLessonParam().get("id")) && !"-1".equals(getLessonParam().get("id"))) {
            params.addBodyParameter("id", getLessonParam().get("id"));
        }
        new HttpUtils().send(HttpRequest.HttpMethod.POST, UrlUtils.URL_ADD_LESSON, params, new RequestCallBack<String>() {

            @Override
            public void onSuccess(ResponseInfo<String> responseInfo) {
                Type type = new TypeToken<BaseResponseBean<Object>>() {
                }.getType();
                BaseResponseBean<Object> baseResponseBean = new Gson().fromJson(responseInfo.result, type);
                if (baseResponseBean.isSuccess()) {
                    EditCourseDetailsActivity.instance.finish();
                    EditCourseActivity.instance.finish();
                    CurriculumDetailsPreViewActivity.this.finish();
                } else {
                    ToastUtil.showToast(baseResponseBean.getErrMsg().toString());
                }
            }

            @Override
            public void onFailure(HttpException e, String s) {
                ToastUtil.showToast(s);
            }
        });
    }

    /***
     * 课程发布
     */
    private void upLoadImg() {
        RequestParams params = new RequestParams();
        params.addHeader("X-CustomToken", UserManager.getInstance().getToken());
        params.addBodyParameter("files", new File(getLessonParam().get("picture")));
        new HttpUtils().send(HttpRequest.HttpMethod.POST, UrlUtils.URL_UPLOAD_IMG, params, new RequestCallBack<String>() {

            @Override
            public void onSuccess(ResponseInfo<String> responseInfo) {
                Type type = new TypeToken<BaseResponseBean<List<ResponseUploadImage>>>() {
                }.getType();
                BaseResponseBean<List<ResponseUploadImage>> listBaseResponseBean = new Gson().fromJson(responseInfo.result, type);
                if (listBaseResponseBean.isSuccess()) {
                    if (listBaseResponseBean.getAaData() != null && listBaseResponseBean.getAaData().size() > 0) {
                        String imgUrl = listBaseResponseBean.getAaData().get(0).fileName;
                        publishLesson(imgUrl);
                    }
                }
            }

            @Override
            public void onFailure(HttpException e, String s) {
                ToastUtil.showToast(s);
            }
        });

    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
    }
}
