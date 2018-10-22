package com.zhangju.xingquban.refactoring.adapter;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.zhangju.xingquban.interestclassapp.bean.near.LessonXqBean;
import com.zhangju.xingquban.refactoring.fragment.LessonAttrFragment;
import com.zhangju.xingquban.refactoring.fragment.LessonAttrPreViewFragment;
import com.zhangju.xingquban.refactoring.fragment.LessonDetailsFragment;
import com.zhangju.xingquban.refactoring.fragment.LessonDetailsPreViewFragment;

import java.util.HashMap;

/**
 * @packageName com.zhangju.xingquban.refactoring.adapter
 * @FileName LessonDetailsPagerAdapter
 * @Author tangyang
 * @DATE 2018/8/24
 **/
public class LessonDetailsPreViewPagerAdapter extends FragmentPagerAdapter {

    String[] title;
    HashMap<String, String> ccurriculumBeanData;

    public LessonDetailsPreViewPagerAdapter(FragmentManager fm, String[] title, HashMap<String, String> aaDataBean) {
        super(fm);
        this.title = title;
        this.ccurriculumBeanData = aaDataBean;
    }

    @Override
    public Fragment getItem(int position) {
        if (position == 0) {
            return LessonDetailsPreViewFragment.getInstance(ccurriculumBeanData);
        } else if (position == 1) {
            return LessonAttrPreViewFragment.getInstance(ccurriculumBeanData);
        }
        return null;
    }


    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return title[position];
    }

    @Override
    public int getCount() {
        return title.length;
    }
}
