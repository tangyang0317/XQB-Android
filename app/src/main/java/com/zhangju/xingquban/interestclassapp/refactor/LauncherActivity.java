package com.zhangju.xingquban.interestclassapp.refactor;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.LinearInterpolator;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.fastlib.db.SaveUtil;
import com.zhangju.xingquban.R;
import com.zhangju.xingquban.interestclassapp.RetrofitInterface.NetWork;
import com.zhangju.xingquban.interestclassapp.bean.CityNameBean;
import com.zhangju.xingquban.interestclassapp.refactor.common.activity.WebViewActivity;
import com.zhangju.xingquban.interestclassapp.refactor.location.LocationManager;
import com.zhangju.xingquban.interestclassapp.ui.main.MainActivity;
import com.zhangju.xingquban.interestclassapp.util.ScreenUtils;
import com.zhangju.xingquban.interestclassapp.util.SortUtils;
import com.zhangju.xingquban.interestclassapp.util.SpUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by sgfb on 2017/11/27.
 * 启动Activity
 */
public class LauncherActivity extends AppCompatActivity {
    final String SAVE_FIRST_LAUNCHER = "firstLauncher";
    boolean isStarted = false;
    private boolean canAutoJump = true;

    private List<CityNameBean.AaDataBean> cityData = new ArrayList<>();//全部城市


    private void getCityData() {
        NetWork.getICityName().getCityName()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer2);
    }

    //全部城市数据
    private Observer<CityNameBean> observer2 = new Observer<CityNameBean>() {
        @Override
        public void onCompleted() {
        }

        @Override
        public void onError(Throwable e) {
        }

        @Override
        public void onNext(CityNameBean cityNameBean) {
            cityData.addAll(SortUtils.filledData(cityNameBean.getAaData().toArray(new CityNameBean.AaDataBean[cityNameBean.getAaData().size()])));
            if (cityData.size() > 0) {
                LocationManager.getInstance().setCityData(cityData);
            }
        }
    };

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_launcher);
        ImageView background = (ImageView) findViewById(R.id.image);
        final TextView skip = (TextView) findViewById(R.id.skip);
        String launcherAdUrl = SpUtil.getString(this, "launcherAdUrl");
        final String launcherAdGotoUrl = SpUtil.getString(this, "launcherAdGotoUrl");
        getCityData();
        Glide.with(this)
                .load(launcherAdUrl)
                .placeholder(R.drawable.launcher)
                .error(R.drawable.launcher)
                .override(ScreenUtils.getScreenWidth(this), ScreenUtils.getScreenHeight(this))
                .into(background);
        skip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isStarted = true;
                complete();
            }
        });
        background.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (launcherAdGotoUrl != null) {
                    Intent intent = new Intent(LauncherActivity.this, WebViewActivity.class);
                    intent.putExtra(WebViewActivity.ARG_URL, launcherAdGotoUrl);
                    intent.putExtra("isLauncher", true);
                    startActivity(intent);
                    canAutoJump = false;
                    finish();
                }
            }
        });

        ValueAnimator countdownAnimator = ValueAnimator.ofInt(3, 0);
        countdownAnimator.setDuration(3000);
        countdownAnimator.setTarget(skip);
        countdownAnimator.setInterpolator(new LinearInterpolator());
        countdownAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                skip.setText(String.format(Locale.getDefault(), "跳过（%d）", (int) animation.getAnimatedValue()));
            }
        });
        countdownAnimator.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                if (!isStarted && canAutoJump) {
                    complete();
                }
            }
        });
        countdownAnimator.start();
    }

    private void complete() {
        if (SaveUtil.getFromSp(this, SAVE_FIRST_LAUNCHER, true)) { //判断是否初次启动
            startActivities(new Intent[]{new Intent(this, MainActivity.class), new Intent(this, GuideActivity.class)});
            SaveUtil.saveToSp(this, SAVE_FIRST_LAUNCHER, false);
        } else {
            startActivity(new Intent(LauncherActivity.this, MainActivity.class));
        }
        finish();
    }
}
