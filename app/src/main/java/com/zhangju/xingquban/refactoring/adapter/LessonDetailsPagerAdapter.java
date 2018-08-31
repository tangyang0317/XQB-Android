package com.zhangju.xingquban.refactoring.adapter;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.zhangju.xingquban.interestclassapp.bean.near.CurriculumBean;
import com.zhangju.xingquban.interestclassapp.bean.near.LessonXqBean;
import com.zhangju.xingquban.refactoring.fragment.LessonAttrFragment;
import com.zhangju.xingquban.refactoring.fragment.LessonDetailsFragment;

/**
 * @packageName com.zhangju.xingquban.refactoring.adapter
 * @FileName LessonDetailsPagerAdapter
 * @Author tangyang
 * @DATE 2018/8/24
 **/
public class LessonDetailsPagerAdapter extends FragmentPagerAdapter {

    String[] title;
    LessonXqBean.AaDataBean ccurriculumBeanData;

    public LessonDetailsPagerAdapter(FragmentManager fm, String[] title, LessonXqBean.AaDataBean aaDataBean) {
        super(fm);
        this.title = title;
        this.ccurriculumBeanData = aaDataBean;
    }

    @Override
    public Fragment getItem(int position) {
        if (position == 0) {
            return LessonDetailsFragment.getInstance(ccurriculumBeanData);
        } else if (position == 1) {
            return LessonAttrFragment.getInstance(ccurriculumBeanData);
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
