package com.zhangju.xingquban.interestclassapp.refactor;

import android.os.Bundle;
import android.support.v4.view.GestureDetectorCompat;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.zhangju.xingquban.R;
import com.zhangju.xingquban.interestclassapp.util.ScreenUtils;

/**
 * Created by Administrator on 2017/11/28.
 * 引导页
 */
public class GuideActivity extends AppCompatActivity{
    int[] mGuideImageId={R.drawable.guide1,R.drawable.guide2,R.drawable.guide3};
    GestureDetectorCompat mGestureDetector;

    @Override
    protected void onCreate(Bundle saveInstanceState){
        super.onCreate(saveInstanceState);
        mGestureDetector=new GestureDetectorCompat(this,new GestureDetector.SimpleOnGestureListener(){
            @Override
            public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY){
                if(distanceX>15) {
                    finish();
                    return true;
                }
                return false;
            }
        });
        ViewPager viewPager=new ViewPager(this);
        viewPager.setOffscreenPageLimit(3);
        viewPager.setAdapter(new PagerAdapter() {
            @Override
            public int getCount() {
                return mGuideImageId.length;
            }

            @Override
            public boolean isViewFromObject(View view, Object object) {
                return view==object;
            }

            @Override
            public Object instantiateItem(ViewGroup container, final int position){
                ImageView imageView=new ImageView(GuideActivity.this);
                imageView.setScaleType(ImageView.ScaleType.FIT_XY);
                Glide.with(GuideActivity.this)
                        .load(mGuideImageId[position])
                        .dontAnimate()
                        .dontTransform()
                        .override(ScreenUtils.getScreenWidth(GuideActivity.this),ScreenUtils.getScreenHeight(GuideActivity.this))
                        .into(imageView);
                imageView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v){
                        if(position==mGuideImageId.length-1)
                            finish();
                    }
                });
                if(position==mGuideImageId.length-1){
                    imageView.setOnTouchListener(new View.OnTouchListener() {
                        @Override
                        public boolean onTouch(View v, MotionEvent event){
                            return mGestureDetector.onTouchEvent(event);
                        }
                    });
                }
                container.addView(imageView);
                return imageView;
            }

            @Override
            public void destroyItem(ViewGroup container, int position, Object object) {
                container.removeViewAt(position);
            }
        });
        setContentView(viewPager);
    }
}